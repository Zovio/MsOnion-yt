/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营采编洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际采编直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
package cc.msonion.carambola.manager.web.controller.collector;

/**
 * @Title: EditorController.java
 * @Package: cc.msonion.carambola.manager.web.controller.collector
 * @Description: 采编Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/22 14:34
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017/5/22 14:34
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.collector.pojo.CollectorItemEditor;
import cc.msonion.carambola.collector.pojo.CollectorItemEditorExample;
import cc.msonion.carambola.collector.service.EditorService;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.parent.common.constants.MsOnionDictCodeConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.image.MsOnionImageUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: EditorController
 * @Description: 采编Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/22 14:34
 */
@Controller
public class EditorController extends MsOnionBaseAppController {
    /**
     * 采编服务
     */
    @Autowired
    private EditorService editorService;

    /**
     * 数据字典服务
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;
    /**
     * 采编列表
     *
     * @param model 模型
     * @return 返回采编列表
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collector/editor/list")
    public String listEditor(Model model) throws MsOnionException {
        Map statusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION, MsOnionDictCodeConstants.MSYT_STATUS);
        model.addAttribute("statusMapJson", MsOnionJsonUtils.objectToJson(statusMap));
        model.addAttribute("statusMap", statusMap);
        return "/collector/editor/listEditor";
    }

    /**
     * 获取采编列表
     *
     * @param request    请求
     * @param itemEditor 采编
     * @param page       当前页码
     * @param rows       每页记录数量
     * @return 列表、数量
     * @throws MsOnionException 自定义异常
     */
    @ResponseBody
    @RequestMapping("/collector/editor/grid")
    public Map<String, Object> gridEditor(HttpServletRequest request, CollectorItemEditor itemEditor, String page, String rows)
            throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            int pageNum = Integer.parseInt(page);
            int pageSize = Integer.parseInt(rows);

            CollectorItemEditorExample example = new CollectorItemEditorExample();
            CollectorItemEditorExample.Criteria criteria = example.createCriteria();

            String name = itemEditor.getName();
            if (StringUtils.isNotBlank(name)) {
                criteria.andNameLike(ManagerConstants.PERCENT + name.trim() + ManagerConstants.PERCENT);
            }

            if (null != itemEditor.getStatus()) {
                criteria.andStatusEqualTo(itemEditor.getStatus());
            } else {
                criteria.andStatusNotEqualTo(MsOnionTableRecordStatus.DELETED.getValue());
            }

            String orderBy;
            String sort = request.getParameter("sort");
            String order = request.getParameter("order");
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
                orderBy = sort.trim() + ManagerConstants.SPACE + order.trim();
            } else {
                orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;
            }

            Map map = new HashMap<>();
            MsOnionResultAdapter resultAdapter = editorService.queryListByPageForResult(apiVersion, example, pageNum, pageSize, orderBy);
            if (null != resultAdapter) {
                MsOnionPagingResult pagingResult = (MsOnionPagingResult) resultAdapter;
                map.put("total", pagingResult.getTotalCounts());

                List<CollectorItemEditor> list = (List<CollectorItemEditor>) pagingResult.getData();
                list.stream().forEach(s -> {
                    // 查询图片
                    s.setHeadImgSmallDynamicS(MsOnionImageUtils.splitImgFiled(getImgurl(), s.getHeadImgSmall()));
                });
                map.put("rows", list);
            }
            return map;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 编辑采编
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return 返回编辑页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collector/editor/edit/{idxStr}")
    public String editEditor(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            if (StringUtils.isNotBlank(idxStr) && MsOnionRegexUtils.checkDigit(idxStr)) {
                Long idx = Long.parseLong(idxStr);
                MsOnionResult result = editorService.getItemEditorByIdx(apiVersion, idx);

                CollectorItemEditor itemEditor = (CollectorItemEditor) result.getData();
                // 查询图片
                itemEditor.setHeadImgBigDynamicS(MsOnionImageUtils.splitImgFiled(getImgurl(), itemEditor.getHeadImgBig()));


                request.setAttribute("itemEditor", itemEditor);
            }

            return "collector/editor/editEditor";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 保存采编
     *
     * @param itemEditor 采编信息
     * @return 返回保存结果
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collector/editor/save")
    @ResponseBody
    public MsOnionResult saveEditor(CollectorItemEditor itemEditor) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            MsOnionResult result;
            Long idx = itemEditor.getIdx();

            Member currentUser = CurrentUserUtils.getCurrentUser();

            if (null == idx) {
                itemEditor.setCreateByMemberIdx(currentUser.getIdx());
                itemEditor.setUpdateByMemberIdx(currentUser.getIdx());
                result = editorService.addItemEditor(apiVersion, itemEditor);
            } else {
                itemEditor.setUpdateByMemberIdx(currentUser.getIdx());
                result = editorService.updateItemEditor(apiVersion, itemEditor);
            }

            return result;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 删除采编信息
     *
     * @param ids 采编主键idx集合
     * @return 返回删除结果
     * @throws MsOnionException 自定义异常
     */
    @ResponseBody
    @RequestMapping("/collector/editor/delete")
    public MsOnionResult deleteEditor(@RequestParam(value = "ids[]", required = false, defaultValue = "") String[] ids) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        MsOnionResult result = editorService.batchDeleteItemEditor(apiVersion, ids);
        return result;
    }
}
