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
package cc.msonion.carambola.manager.web.controller.cache;
/**
 * @Title: CacheCenterController.java
 * @Package: cc.msonion.carambola.manager.web.controller.cache
 * @Description: 缓存中心controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: Doren Wu  DorenWu@msyc.cc
 * @Date: 2017年05月09日
 * @Version: V2.0.0
 * @Modify-by: Doren Wu  DorenWu@msyc.cc
 * @Modify-date: 2017年05月09日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.cache.common.constants.CacheConstants;
import cc.msonion.carambola.cache.ext.utils.CacheKeyTreeToMenu;
import cc.msonion.carambola.cache.pojo.CacheKey;
import cc.msonion.carambola.cache.pojo.CacheObject;
import cc.msonion.carambola.cache.pojo.MenuTree;
import cc.msonion.carambola.cache.service.CacheService;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @ClassName: CacheCenterController
 * @Description: 缓存中心controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: Doren Wu  DorenWu@msyc.cc
 * @Date: 2017年05月09日
 *
 */
@Controller
public class CacheCenterController extends MsOnionBaseAppController {

    /**
     * 缓存中心服务
     */
    @Autowired
    private CacheService cacheService;

    /**
     * @return 缓存列表页面
     */
    @RequestMapping("/cache/key/list")
    public String keyList() {

        return "/cache/key/listKey";
    }

    /**
     * 缓存详情页面
     * @param req HttpServletRequest 对象
     * @param res HttpServletResponse  对象
     * @param model  Model实例对象
     * @param keyPath key的键
     * @throws MsOnionException 自定义异常
     * @return 页面视图
     */
    @RequestMapping("/cache/key/detail")
    public String keyDetail(HttpServletRequest req, HttpServletResponse res, Model model, String keyPath)  {
        try {
            if (MsOnionStringUtils.isNoneBlank(keyPath)) {
                // 版本对象
                MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
                msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

                //查询缓存对象详情
                CacheObject cacheObject = new CacheObject();
                cacheObject.setKey(keyPath);
                MsOnionResult cacheObjectResult = cacheService.getCacheObjectDetail(msOnionApiVersion, cacheObject);

                if (cacheObjectResult.getStatus() == MsOnionResult.ok().getStatus().intValue()) {
                    model.addAttribute("cacheObject", (CacheObject) cacheObjectResult.getData());
                }
            }
            return "/cache/key/keyDetail";
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 进入缓存详情页面失败..");
            return getError();
        }
    }

    /**
     *  获取缓存key的树形菜单
     * @param req  HttpServletRequest 对象
     * @param res  HttpServletResponse  对象
     * @param id key的路径
     * @param model  Model实例对象
     * @throws MsOnionException 自定义异常
     * @return 返回缓存key的树形菜单
     */
    @RequestMapping("/cache/key/treeList")
    @ResponseBody
    public MsOnionResult treeList(HttpServletRequest req, HttpServletResponse res, String id, Model model) {
        try {

            // new对象
            CacheKey ck = new CacheKey();
            ck.setPath(id);

            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 存放目录和数据集合的缓存key对象
            MsOnionResult keyTreeListResult = cacheService.getCacheKeyList(msOnionApiVersion, ck);
            TreeSet<CacheKey> keyTreeList = (TreeSet<CacheKey>) keyTreeListResult.getData();

            // 数据集合
            TreeSet<CacheKey> treeSetData = new TreeSet<CacheKey>();
            CacheKeyTreeToMenu.treeDataSeparation(treeSetData, keyTreeList);

            // 转换对象
            List<MenuTree> menuViewObjectList = CacheKeyTreeToMenu.converToMenuView(keyTreeList);

            // 返回对象给客户端
            return new MsOnionResult().ok(menuViewObjectList);
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 获取缓存key的树形菜单失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 获取缓存key的树形菜单失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }
    /**
     *  获取缓存key列表
     * @param req  HttpServletRequest 对象
     * @param res  HttpServletResponse  对象
     * @param id key的路径
     * @param model  Model实例对象
     * @throws MsOnionException 自定义异常
     * @return 返回缓存key的树形菜单
     */
    @RequestMapping("/cache/key/keyList")
    @ResponseBody
    public MsOnionResult keyList(HttpServletRequest req, HttpServletResponse res, String id, Model model) {
        try {

            // new对象
            CacheKey ck = new CacheKey();
            ck.setPath(id);

            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 存放目录和数据集合的缓存key对象
            MsOnionResult keyTreeListResult = cacheService.getCacheKeyList(msOnionApiVersion, ck);
            TreeSet<CacheKey> keyTreeList = (TreeSet<CacheKey>) keyTreeListResult.getData();

            // 数据集合
            TreeSet<CacheKey> treeSetData = new TreeSet<CacheKey>();
            CacheKeyTreeToMenu.treeDataSeparation(treeSetData, keyTreeList);
            // 返回对象给客户端
            return new MsOnionResult().ok(treeSetData);
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 获取缓存key数据列表失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 获取缓存key数据列表失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }

    /**
     * 删除缓存对象
     * @param req HttpServletRequest 对象
     * @param res HttpServletResponse  对象
     * @param model  Model实例对象
     * @param ids 需要删除的Keys数组
     * @return 返回缓存key的树形菜单
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/cache/key/deleteKey")
    @ResponseBody
    public MsOnionResult deleteKey(HttpServletRequest req, HttpServletResponse res, Model model,
                                   @RequestParam(value = "ids[]", required = false, defaultValue = "")String[] ids)  {

        try {
            // 参数校验
            if (ArrayUtils.isEmpty(ids)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CacheConstants.DELETE_CACHE_PARAMS_NULL_MSG);
            }

            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            //转换为集合对象
            List<CacheKey> cacheKeyList = new ArrayList<CacheKey>();
            for (String str : ids) {
                if (MsOnionStringUtils.isBlank(str)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CacheConstants.DELETE_CACHE_PARAMS_NULL_MSG);
                }
                CacheKey ck = new CacheKey();
                ck.setPath(str);
                cacheKeyList.add(ck);
            }

            MsOnionResult deleteKeyResult = cacheService.deleteCacheByKeys(msOnionApiVersion, cacheKeyList);
            return deleteKeyResult;
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 删除缓存对象失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 删除缓存对象失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }

    /**
     * 删除缓存树节点
     * @param req HttpServletRequest 对象
     * @param res HttpServletResponse  对象
     * @param model  Model实例对象
     * @param ids 需要删除的Keys数组
     * @return 返回缓存key的树形菜单
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/cache/key/deleteNode")
    @ResponseBody
    public MsOnionResult deleteNode(HttpServletRequest req, HttpServletResponse res, Model model,
                                   @RequestParam(value = "ids[]", required = false, defaultValue = "")String[] ids)  {

        try {
            // 参数校验
            if (ArrayUtils.isEmpty(ids)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CacheConstants.DELETE_CACHE_PARAMS_NULL_MSG);
            }

            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            //转换为集合对象
            List<CacheKey> cacheKeyList = new ArrayList<CacheKey>();
            for (String str : ids) {
                if (MsOnionStringUtils.isBlank(str)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CacheConstants.DELETE_CACHE_PARAMS_NULL_MSG);
                }
                CacheKey ck = new CacheKey();
                ck.setPath(str);
                cacheKeyList.add(ck);
            }

            MsOnionResult deleteKeyResult = cacheService.deleteCacheByNodes(msOnionApiVersion, cacheKeyList);
            return deleteKeyResult;
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 删除缓存节点失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 删除缓存节点失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }
}
