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
package cc.msonion.carambola.manager.web.controller.collector.common;

import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemExample;
import cc.msonion.carambola.collector.service.ItemCategoryService;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;

import java.util.List;
/**
 * @Title: ItemCommonQuery
 * @Package: cc.msonion.carambola.manager.web.controller.collector.common
 * @Description: 商品查询统一处理接口
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月16日 15:37:19
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月16日 15:37:19
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

/**
 * @ClassName: ItemCommonQuery
 * @Description: 商品查询统一处理接口
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月16日 15:37:19
 */
public class ItemCommonQuery {

    /**
     * 商品分类接口
     */
    private ItemCategoryService itemCategoryService;

    public ItemCommonQuery(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

    /**
     * 设置商品搜索参数
     *
     * @param collectorItemExample collectorItemExample
     * @param collectorItem        商品信息
     * @param firstCategoryIdx     第一品类
     * @param secondCategoryIdx    第二品类
     * @param threeCategoryIdx     第三品类
     * @param itemIdxList          商品主键idx集合
     * @throws MsOnionException 自定义异常
     */
    public void setGoodsQueryParams(CollectorItemExample collectorItemExample, CollectorItem collectorItem,
                                    String firstCategoryIdx, String secondCategoryIdx, String threeCategoryIdx,
                                    List<Long> itemIdxList)
            throws MsOnionException {
        CollectorItemExample.Criteria criteria = collectorItemExample.createCriteria();

        // 只筛选出itemIdxList集合内的idx
        if (MsOnionCollectionUtils.isNotEmpty(itemIdxList)) {
            criteria.andIdxIn(itemIdxList);
        }

        // ID
        if (collectorItem.getIdx() != null && collectorItem.getIdx() > 0) {
            criteria.andIdxEqualTo(collectorItem.getIdx());
        }

        // 品名
        if (MsOnionStringUtils.isNotEmpty(collectorItem.getCnName())) {
            criteria.andCnNameLike(ManagerConstants.PERCENT + collectorItem.getCnName().trim() + ManagerConstants.PERCENT);
        }

        // 货号
        if (MsOnionStringUtils.isNotEmpty(collectorItem.getItemNo())) {
            criteria.andItemNoEqualTo(collectorItem.getItemNo().trim());
        }

        // 条形码
        if (MsOnionStringUtils.isNotEmpty(collectorItem.getBarcode())) {
            criteria.andBarcodeEqualTo(collectorItem.getBarcode().trim());
        }

        // 权重
        if (collectorItem.getWeight() != null && collectorItem.getWeight() > 0) {
            criteria.andWeightEqualTo(collectorItem.getWeight());
        }

        // 品牌
        if (collectorItem.getBrandIdx() != null && collectorItem.getBrandIdx() > 0) {
            criteria.andBrandIdxEqualTo(collectorItem.getBrandIdx());
        }

        // 批次
        if (collectorItem.getBatch() != null && collectorItem.getBatch() > 0) {
            criteria.andBatchEqualTo(collectorItem.getBatch());
        }

        // 采购状态
        if (collectorItem.getPurchaseStatus() != null && collectorItem.getPurchaseStatus() > 0) {
            criteria.andPurchaseStatusEqualTo(collectorItem.getPurchaseStatus());
        }

        // 采集状态
        if (collectorItem.getCollectionStatus() != null && collectorItem.getCollectionStatus() > 0) {
            criteria.andCollectionStatusEqualTo(collectorItem.getCollectionStatus());
        }

        if (MsOnionStringUtils.isNotEmpty(firstCategoryIdx)
                || MsOnionStringUtils.isNotEmpty(secondCategoryIdx)
                || MsOnionStringUtils.isNotEmpty(threeCategoryIdx)) {
            // 商品类目
            List<Long> categoryIdxList = getCategoryIdxList(firstCategoryIdx, secondCategoryIdx, threeCategoryIdx);
            if (MsOnionCollectionUtils.isNotEmpty(categoryIdxList)) {
                criteria.andCategoryIdxIn(categoryIdxList);
            }
        }

        criteria.andStatusNotEqualTo(MsOnionTableRecordStatus.DELETED.getValue());
    }

    /**
     * 获取类目ID集合
     *
     * @param firstCategoryIdx  第一品类
     * @param secondCategoryIdx 第二品类
     * @param threeCategoryIdx  第三品类
     * @return 三级类目集合
     * @throws MsOnionException MsOnionException
     */
    public List<Long> getCategoryIdxList(String firstCategoryIdx,
                                         String secondCategoryIdx, String threeCategoryIdx)
            throws MsOnionException {
        long categoryId = 0;
        if (MsOnionStringUtils.isNotEmpty(firstCategoryIdx)
                && MsOnionRegexUtils.checkDigit(firstCategoryIdx)) {
            categoryId = Long.parseLong(firstCategoryIdx);
            if (MsOnionStringUtils.isNotEmpty(secondCategoryIdx)
                    && MsOnionRegexUtils.checkDigit(secondCategoryIdx)) {
                categoryId = Long.parseLong(secondCategoryIdx);
                if (MsOnionStringUtils.isNotEmpty(threeCategoryIdx)
                        && MsOnionRegexUtils.checkDigit(threeCategoryIdx)) {
                    categoryId = Long.parseLong(threeCategoryIdx);
                }
            }
        }
        if (categoryId > 0) {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            MsOnionResult msOnionResult = itemCategoryService.getCategoryIdxListByPidx(apiVersion, categoryId);
            if (msOnionResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                return (List<Long>) msOnionResult.getData();
            }

        }
        return null;
    }
}
