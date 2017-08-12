/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
package cc.msonion.carambola.manager.web.controller.system;

import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionZookeeperUtils;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.zk.children.MsOnionZookeeperPathChild;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ClassName: ZookeeperController
 * @Description: zookeeper 值配置
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: Mark 3563249160@qq.com
 * @Date: 2017/7/3
 */
@Controller
public class ZookeeperController extends MsOnionBaseAppController {

    /**
     * zookeeper 客户端
     */
    @Autowired
    private MsOnionCuratorZookeeperClient msOnionCuratorZookeeperClient;

    /**
     * 重试次数
     */
    @Autowired
    private RetryNTimes retryNTimes;

    /**
     * ZK 查看页面
     * @return 页面
     */
    @RequestMapping("/system/zk/list")
    public String list() {
        return "/system/zk/zklist";
    }

    /**
     * ZK树
     * @param modulePath 模块路径
     * @return 处理结果
     */
    @RequestMapping("/system/zk/treeList")
    @ResponseBody
    public MsOnionResult treeList(@RequestParam(value = "path", defaultValue = "/") String modulePath) {
        try {
            MsOnionZookeeperPathChild msOnionZookeeperPathChild =
                    MsOnionZookeeperUtils.getCounterPathCurrentChildren(msOnionCuratorZookeeperClient, modulePath);

            List<Map<String, Object>> datas = new ArrayList<>();
            List<Map<String, Object>> childrenList = new ArrayList<>();
            List<String> childStrList = msOnionZookeeperPathChild.getChildList();
            if (null != childStrList && childStrList.size() > 0) {
                for (String childStr : childStrList) {
                    Map<String, Object> childData = new HashMap<>();
                    childData.put("state", "closed");
                    childData.put("text", childStr);

                    String childModulePath = msOnionZookeeperPathChild.getModulePath() + "/" + childStr;
                    childModulePath = childModulePath.replace("//", "/");
                    childData.put("path", childModulePath);

                    childrenList.add(childData);
                }
            }

            Map<String, Object> data = new HashMap<>();
            data.put("state", "closed");
            data.put("text", msOnionZookeeperPathChild.getPath());
            data.put("fullPath", msOnionZookeeperPathChild.getFullPath());
            data.put("childList", childrenList);

            datas.add(data);

            return MsOnionResult.ok(datas);
        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_QUERY_FAILURE);
        }
    }

    /**
     * 获取ZK值
     * @param fullPath 全路径
     * @return 查询结果
     */
    @RequestMapping("/system/zk/getZKValue")
    @ResponseBody
    public MsOnionResult getZKValue(@RequestParam("fp") String fullPath) {
        try {
            AtomicValue<Long> longAtomicValue =
                    MsOnionZookeeperUtils.get(msOnionCuratorZookeeperClient, retryNTimes, fullPath);

            List<Map<String, Object>> datas = null;

            if (null != longAtomicValue && longAtomicValue.succeeded()) {
                Map<String, Object> data = new HashMap<>();
                data.put("value", longAtomicValue.postValue());
                data.put("fp", fullPath);
                datas = new ArrayList<>();
                datas.add(data);
            }

            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, datas);
        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_QUERY_FAILURE);
        }
    }

    /**
     * 获取该节点下所有子节点的值
     * @param modulePath 相对路径
     * @return 值
     */
    @RequestMapping("/system/zk/getZKChildValues")
    @ResponseBody
    public MsOnionResult getZKChildValues(@RequestParam("path") String modulePath) {
        try {
//            modulePath = new String(Base64.getDecoder().decode(modulePath), "UTF-8");

            List<Map<String, Object>> childrenValues = this.getChildrenValue(modulePath);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, childrenValues);
        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_QUERY_FAILURE);
        }
    }

    /**
     * 编辑ZK值
     * @param fullPath 全路径
     * @param model 参数
     * @return 页面
     */
    @RequestMapping("/system/zk/editZKValue")
    public String editZKValue(@RequestParam("fp") String fullPath, Model model) {
        Map<String, Object> data = new HashMap<>();

        try {
            AtomicValue<Long> longAtomicValue =
                    MsOnionZookeeperUtils.get(msOnionCuratorZookeeperClient, retryNTimes, fullPath);

            if (null != longAtomicValue && longAtomicValue.succeeded()) {
                data.put("value", longAtomicValue.postValue());
                data.put("fullPath", fullPath);
            }

        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
        }

        model.addAttribute("zk", data);
        return "system/zk/editZk";
    }

    /**
     * 保存ZK值
     * @param fullPath 全路径
     * @param valueStr ZK值
     * @return 处理结果
     */
    @RequestMapping("/system/zk/saveZKValue/{fp}/{value}")
    @ResponseBody
    public MsOnionResult saveZKValue(@PathVariable("fp") String fullPath, @PathVariable("value") String valueStr) {
        try {

            if (!MsOnionRegexUtils.checkDigit(valueStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", value=" + valueStr);
            }

            Long value = Long.valueOf(valueStr);

            fullPath = new String(Base64.getDecoder().decode(fullPath), "UTF-8");

            AtomicValue<Long> longAtomicValue =
                MsOnionZookeeperUtils.trySet(msOnionCuratorZookeeperClient, retryNTimes, fullPath, value);

            if (!longAtomicValue.succeeded()) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        MsOnionMessageConstants.MESSAGE_QUERY_FAILURE);
            } else {
                return MsOnionResult.ok();
            }
        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_QUERY_FAILURE);
        }
    }

    /**
     * 一键设置ZK值
     * @param fps ZK路径数组
     * @param value ZK值
     * @return 处理结果
     */
    @RequestMapping(value = "/system/zk/batchSaveZKValue", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult batchSaveZKValue(@RequestParam(value = "fps[]", required = false, defaultValue = "")
                                                  String[] fps, Long value) {
        try {
            // 参数校验
            if (ArrayUtils.isEmpty(fps) || null == value || value < 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
            }

            for (String fp : fps) {
                AtomicValue<Long> longAtomicValue =
                        MsOnionZookeeperUtils.trySet(msOnionCuratorZookeeperClient, retryNTimes, fp, value);

                if (!longAtomicValue.succeeded()) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                            MsOnionMessageConstants.MESSAGE_QUERY_FAILURE);
                }
            }
            return MsOnionResult.ok();
        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_QUERY_FAILURE);
        }

    }

    private List<Map<String, Object>> getChildrenValue(String modulePath) throws MsOnionException {
        List<Map<String, Object>> values = new ArrayList<>();

        Set<String> paths = new LinkedHashSet<>();
        paths = this.getLeafPath(modulePath, paths);
        if (null != paths && paths.size() > 0) {
            for (String fullPath : paths) {
                AtomicValue<Long> longAtomicValue =
                        MsOnionZookeeperUtils.get(msOnionCuratorZookeeperClient, retryNTimes, fullPath);

                if (null != longAtomicValue && longAtomicValue.succeeded()) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("value", longAtomicValue.postValue());
                    data.put("fp", fullPath);
                    String path = fullPath.replace(MsOnionZookeeperUtils.ZK_ROOT_COUNTER_BASE_PATH, "");
                    data.put("path", path);
                    values.add(data);
                }
            }
        }
        return values;
    }

    private Set<String> getLeafPath(String modulePath, Set<String> paths) throws MsOnionException {
        MsOnionZookeeperPathChild msOnionZookeeperPathChild =
                MsOnionZookeeperUtils.getCounterPathCurrentChildren(msOnionCuratorZookeeperClient, modulePath);

        List<String> childStrList = msOnionZookeeperPathChild.getChildList();
        if (null == childStrList || childStrList.size() < 1 ) {
            String path = msOnionZookeeperPathChild.getFullPath();
            paths.add(path);
            return paths;
        }
        if (!modulePath.endsWith("/")) {
            modulePath = modulePath + "/";
        }
        for (String childStr : childStrList) {
            Set path = this.getLeafPath(modulePath + childStr, paths);
            if (null != path && path.size() > 0) {
                paths.addAll(path);
            }
        }

        return paths;
    }

}