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

package cc.msonion.carambola.manager.web.controller.collector;

/**
 * @Title: BrandController.java
 * @Package: cc.msonion.carambola.manager.web.controller.collector
 * @Description: 品牌Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月12日 下午9:24:42
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017年4月12日 下午9:24:42
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.collector.pojo.CollectorItemBrand;
import cc.msonion.carambola.collector.pojo.CollectorItemBrandExample;
import cc.msonion.carambola.collector.service.ItemBrandService;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.parent.common.constants.MsOnionDictCodeConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
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
 * @ClassName: BrandController
 * @Description: 品牌Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月12日 下午9:24:42
 */
@Controller
public class BrandController extends MsOnionBaseAppController {

    /**
     * 商品品牌服务
     */
    @Autowired
    private ItemBrandService itemBrandService;

    /**
     * 系统数据字典服务
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;

    /**
     * 品牌列表
     *
     * @param model 模型
     * @return 返回品牌列表
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/br/list")
    public String listBrand(Model model) throws MsOnionException {
        Map statusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                MsOnionDictCodeConstants.MSYT_STATUS);
        model.addAttribute("statusMap", statusMap);
        model.addAttribute("statusMapJson", MsOnionJsonUtils.objectToJson(statusMap));

        return "collector/brand/listBrand";
    }

    /**
     * 获取品牌列表
     *
     * @param request   请求
     * @param itemBrand 商品品牌
     * @param page      当前页码
     * @param rows      每页记录数量
     * @return 列表、数量
     * @throws MsOnionException 异常
     */
    @ResponseBody
    @RequestMapping("/collect/br/grid")
    public Map<String, Object> gridBrand(HttpServletRequest request, CollectorItemBrand itemBrand, String page, String rows) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            int pageNum = Integer.parseInt(page);
            int pageSize = Integer.parseInt(rows);

            CollectorItemBrandExample example = new CollectorItemBrandExample();
            CollectorItemBrandExample.Criteria criteria = example.createCriteria();

            String brandName = itemBrand.getBrandName();
            if (StringUtils.isNotBlank(brandName)) {
                criteria.andBrandNameLike(ManagerConstants.PERCENT + brandName.trim() + ManagerConstants.PERCENT);
            }

            if (null != itemBrand.getStatus()) {
                criteria.andStatusEqualTo(itemBrand.getStatus());
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
            MsOnionResultAdapter resultAdapter = itemBrandService.queryListByPageForResult(apiVersion, example, pageNum, pageSize, orderBy);
            if (null != resultAdapter) {
                MsOnionPagingResult pagingResult = (MsOnionPagingResult) resultAdapter;
                map.put("total", pagingResult.getTotalCounts());

                List<CollectorItemBrand> list = (List<CollectorItemBrand>) pagingResult.getData();
                list.stream().forEach(s -> {
                    s.setLogoSmallDynamicS(MsOnionImageUtils.splitImgFiled(getImgurl(), s.getLogoSmall()));
                });
                map.put("rows", pagingResult.getData());
            }
            return map;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 编辑品牌
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return String
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/br/edit/{idxStr}")
    public String editBrand(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            if (StringUtils.isNotBlank(idxStr) && MsOnionRegexUtils.checkDigit(idxStr)) {
                Long idx = Long.parseLong(idxStr);
                MsOnionResult result = itemBrandService.getItemBrandByIdx(apiVersion, idx);

                CollectorItemBrand itemBrand = (CollectorItemBrand) result.getData();

                // 查询图片
                itemBrand.setLogoBigDynamicS(MsOnionImageUtils.splitImgFiled(getImgurl(), itemBrand.getLogoBig()));


                request.setAttribute("itemBrand", itemBrand);
            }

            return "collector/brand/editBrand";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 查看品牌
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return 返回页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/br/detail/{idxStr}")
    public String detailBrand(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        try {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            if (StringUtils.isNotBlank(idxStr)) {
                Long idx = Long.parseLong(idxStr);
                MsOnionResult result = itemBrandService.getItemBrandByIdx(apiVersion, idx);
                if (null != result.getData()) {
                    CollectorItemBrand itemBrand = (CollectorItemBrand) result.getData();

                    itemBrand.setLogoBigDynamicS(MsOnionImageUtils.splitImgFiled(getImgurl(), itemBrand.getLogoBig()));


                    request.setAttribute("itemBrand", itemBrand);
                }
            }

            Map statusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.MSYT_STATUS);
            request.setAttribute("statusMap", statusMap);
            request.setAttribute("statusMapJson", MsOnionJsonUtils.objectToJson(statusMap));
            return "collector/brand/detailBrand";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 保存品牌
     *
     * @param itemBrand 商品品牌
     * @return 返回保存结果
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/br/save")
    @ResponseBody
    public MsOnionResult saveBrand(CollectorItemBrand itemBrand) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            MsOnionResult result;
            Long idx = itemBrand.getIdx();

            Member currentUser = CurrentUserUtils.getCurrentUser();

            if (null == idx) {
                itemBrand.setCreateByMemberIdx(currentUser.getIdx());
                itemBrand.setUpdateByMemberIdx(currentUser.getIdx());
                result = itemBrandService.addItemBrand(apiVersion, itemBrand);
            } else {
                itemBrand.setUpdateByMemberIdx(currentUser.getIdx());
                result = itemBrandService.updateItemBrand(apiVersion, itemBrand);
            }

            return result;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 删除品牌
     *
     * @param ids 品牌主键idx集合
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @ResponseBody
    @RequestMapping("/collect/br/delete")
    public MsOnionResult deleteBrand(@RequestParam(value = "ids[]", required = false, defaultValue = "") String[] ids) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        for (String idxStr : ids) {
            if (StringUtils.isBlank(idxStr)) {
                continue;
            }

            Long idx = Long.parseLong(idxStr);

            try {
                MsOnionResult result = itemBrandService.deleteItemBrand(apiVersion, idx);
                if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return result;
                }
            } catch (MsOnionException e) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_DELETE_FAILURE);
            }
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
    }
}
