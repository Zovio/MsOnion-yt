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
package cc.msonion.carambola.manager.web.controller.excel.impl;
/**
 * @Title: CustomsExcelServiceImpl
 * @Package: cc.msonion.carambola.manager.web.controller.excel.impl
 * @Description: 描述信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月21日 15:07:09
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月21日 15:07:09
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.service.ItemService;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.logistics.common.constants.LogisticsConstants;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclare;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareExample;
import cc.msonion.carambola.logistics.pojo.LogisticsUnit;
import cc.msonion.carambola.logistics.pojo.LogisticsUnitExample;
import cc.msonion.carambola.logistics.pojo.excel.CustomView;
import cc.msonion.carambola.logistics.service.LogisticsCustomsDeclareService;
import cc.msonion.carambola.logistics.service.LogisticsUnitService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.parent.common.constants.MsOnionExcelConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionBeanUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.excel.MsOnionExcelDataService;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.system.ext.dto.CustomFillData;

import java.util.*;

/**
 * @ClassName: CustomsExcelServiceImpl
 * @Description: 报关导入
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月21日 15:07:09
 */
public class CustomsExcelServiceImpl implements MsOnionExcelDataService {

    /**
     * 当前操作用户
     */
    private Member currentUser;
    /**
     * 报关服务
     */
    private LogisticsCustomsDeclareService logisticsCustomsDeclareService;
    /**
     * 商品服务
     */
    private ItemService itemService;
    /**
     * 版本
     */
    private MsOnionApiVersion apiVersion;
    /**
     * 日志
     */
    private MsOnionLogger msOnionLogger;

    /**
     * 计量单位服务
     */
    private LogisticsUnitService logisticsUnitService;

    public CustomsExcelServiceImpl(MsOnionApiVersion apiVersion, MsOnionLogger msOnionLogger, Member currentUser, ItemService itemService,
                                   LogisticsCustomsDeclareService logisticsCustomsDeclareService, LogisticsUnitService logisticsUnitService) {
        this.currentUser = currentUser;
        this.logisticsCustomsDeclareService = logisticsCustomsDeclareService;
        this.itemService = itemService;
        this.apiVersion = apiVersion;
        this.msOnionLogger = msOnionLogger;
        this.logisticsUnitService = logisticsUnitService;
    }

    /**
     * 写入到数据库
     *
     * @param dataObjectList 实体对象的集合
     * @return 返回实体对象
     */
    @Override
    public List writeToDataBases(List dataObjectList) {
        if (MsOnionCollectionUtils.isEmpty(dataObjectList)) {
            return null;
        }
        List<CustomView> customViewList = dataObjectList;
        try {
            Map<Integer, LogisticsCustomsDeclare> saveCollectorItemMap = new HashMap<Integer, LogisticsCustomsDeclare>();
            Map<Integer, LogisticsCustomsDeclare> updateCollectorItemMap = new HashMap<Integer, LogisticsCustomsDeclare>();
            List<Long> itemIdx = new ArrayList<Long>();
            for (int i = 0; i < customViewList.size(); i++) {
                CustomView customView = customViewList.get(i);
                try {
                    if (customView == null) {
                        continue;
                    }
                    LogisticsCustomsDeclare logisticsCustomsDeclare = CustomFillData.
                            convertToData(customView, currentUser.getIdx());

                    if (itemIdx.contains(customView.getItemIdx())) {
                        customView.setOk(false);
                        customView.setErrorMsg(CollectorMessageConstants.ITEM_IDX_REPEAT);
                        continue;
                    } else {
                        itemIdx.add(customView.getItemIdx());
                    }

                    // 查询所有旧的可用的数据
                    LogisticsCustomsDeclareExample example = new LogisticsCustomsDeclareExample();
                    LogisticsCustomsDeclareExample.Criteria criteria = example.createCriteria();
                    criteria.andItemIdxEqualTo(customView.getItemIdx());
                    criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
                    List<LogisticsCustomsDeclare> logisticsCustomsDeclareList = logisticsCustomsDeclareService.queryByExample(apiVersion, example);

                    if (MsOnionCollectionUtils.isNotEmpty(logisticsCustomsDeclareList)) {
                        LogisticsCustomsDeclare logisticsCustomsDeclare1 = logisticsCustomsDeclareList.get(0);
                        MsOnionBeanUtils.copyPropertiesIgnoreNull(logisticsCustomsDeclare, logisticsCustomsDeclare1, true);
                        // 字段校验
                        MsOnionResult msOnionResult = checkField(logisticsCustomsDeclare1);
                        if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                            customView.setOk(false);
                            customView.setErrorMsg(msOnionResult.getMsg());
                            continue;
                        }
                        updateCollectorItemMap.put(i, logisticsCustomsDeclare1);
                    } else {
                        // 字段校验
                        MsOnionResult msOnionResult = checkField(logisticsCustomsDeclare);
                        if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                            customView.setOk(false);
                            customView.setErrorMsg(msOnionResult.getMsg());
                            continue;
                        }
                        saveCollectorItemMap.put(i, logisticsCustomsDeclare);
                    }
                    customView.setOk(true);
                    customView.setErrorMsg(MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS);
                } catch (MsOnionException e) {
                    msOnionLogger.error(getClass().getName(), e,
                            " #### 插入报关信息数据出错 #######");
                    customView.setOk(false);
                    customView.setErrorMsg(MsOnionExcelConstants.MESSAGE_EXCEL_FILE_FORMAT_ERROR);
                }
            }
            MsOnionBatchReport<LogisticsCustomsDeclare> msOnionBatchReport = null;
            Map<Integer, LogisticsCustomsDeclare> msOnionBatchFailMap = new HashMap<Integer, LogisticsCustomsDeclare>();
            // 新增的商品价格集合
            MsOnionResult saveMsOnionResult = logisticsCustomsDeclareService.batchSaveLogisticsCustomsDeclare(apiVersion, saveCollectorItemMap);
            if (saveMsOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                msOnionBatchFailMap.putAll(saveCollectorItemMap);
            } else {
                msOnionBatchReport = (MsOnionBatchReport<LogisticsCustomsDeclare>) saveMsOnionResult.getData();
                msOnionBatchFailMap.putAll(msOnionBatchReport.getFailureRecords());
            }
            // 更新的商品价格集合
            MsOnionResult updateMsOnionResult = logisticsCustomsDeclareService.
                    batchUpdateCollectorLogisticsCustoms(apiVersion, updateCollectorItemMap);
            if (updateMsOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                msOnionBatchFailMap.putAll(updateCollectorItemMap);
            } else {
                msOnionBatchReport = (MsOnionBatchReport<LogisticsCustomsDeclare>) updateMsOnionResult.getData();
                msOnionBatchFailMap.putAll(msOnionBatchReport.getFailureRecords());
            }
            // 如果有失败的，竞价map需要去掉这个对象
            if (msOnionBatchFailMap != null && !msOnionBatchFailMap.isEmpty()) {
                for (Integer idx : msOnionBatchFailMap.keySet()) {
                    // 更新错误结果
                    customViewList.get(idx).setOk(false);
                    customViewList.get(idx).setErrorMsg(CollectorMessageConstants.MESSAGE_ITEM_SAVE_CUSTOMS_FAIL);
                }
            }
        } catch (MsOnionException e) {
            msOnionLogger.error(this.getClass().getName(), e,
                    CollectorMessageConstants.MESSAGE_ITEM_SAVE_CUSTOMS_FAIL);
        }
        return customViewList;
    }

    /**
     * 字段校验
     *
     * @param logisticsCustomsDeclare 商品报关对象
     * @return MsOnionResult
     * @throws MsOnionException MsOnionException
     */
    private MsOnionResult checkField(LogisticsCustomsDeclare logisticsCustomsDeclare) throws MsOnionException {
        if (logisticsCustomsDeclare.getItemIdx() == null || logisticsCustomsDeclare.getItemIdx() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_NOT_EXIST);
        }
        MsOnionResult itemMsOnionResult = itemService.getItemByIdx(apiVersion,
                logisticsCustomsDeclare.getItemIdx());
        if (itemMsOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_NOT_EXIST);
        }
        /**
         * 报关品名长度
         */
        if (MsOnionStringUtils.isNotEmpty(logisticsCustomsDeclare.getCustomName())
                && (logisticsCustomsDeclare.getCustomName().length()
                > LogisticsConstants.MESSAGE_ITEM_CUSTOM_NAME_MAX_LENGTH)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_CUSTOM_NAME_EXCEED_LENGTH);
        }
        /**
         * 第一数量不能为空
         */
        if (logisticsCustomsDeclare.getFirstQuantity() == null || logisticsCustomsDeclare.getFirstQuantity() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_FIRST_QUANTITY_NULL);
        }
        /**
         * 第一数量超过最大值
         */
        if (logisticsCustomsDeclare.getFirstQuantity() > LogisticsConstants.MESSAGE_ITEM_FIRST_QUANTITY_MAX) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_FIRST_QUANTITY_EXCEED_MAX);
        }

        /**
         * 第二数量不能为空
         */
        if (logisticsCustomsDeclare.getFirstQuantity() == null || logisticsCustomsDeclare.getFirstQuantity() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_SECOND_QUANTITY_NULL);
        }
        /**
         * 第二数量超过最大值
         */
        if (logisticsCustomsDeclare.getFirstQuantity() > LogisticsConstants.MESSAGE_ITEM_SECOND_QUANTITY_MAX) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_SECOND_QUANTITY_EXCEED_MAX);
        }

        // 第一单位参数非法
        Long firstUnitIdx = logisticsCustomsDeclare.getFirstUnitIdx();
        if (firstUnitIdx == null || firstUnitIdx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_ITEM_FIRST_PARAM_ILLEGAL);
        }

        // 第一单位查询不到
        LogisticsUnitExample logisticsUnitExample = new LogisticsUnitExample();
        logisticsUnitExample.createCriteria().andIdxEqualTo(firstUnitIdx).andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        LogisticsUnit firstLogisticsUnit = logisticsUnitService.queryOne(apiVersion, logisticsUnitExample);
        if (null == firstLogisticsUnit) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_ITEM_FIRST_UNIT_NOT_FOUND);
        }

        // 第二单位参数非法
        Long secondUnitIdx = logisticsCustomsDeclare.getSecondUnitIdx();
        if (secondUnitIdx == null || secondUnitIdx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_ITEM_SECOND_PARAM_ILLEGAL);
        }

        // 第二单位查询不到
        logisticsUnitExample = new LogisticsUnitExample();
        logisticsUnitExample.createCriteria().andIdxEqualTo(secondUnitIdx).andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        LogisticsUnit secondLogisticsUnit = logisticsUnitService.queryOne(apiVersion, logisticsUnitExample);
        if (null == secondLogisticsUnit) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_ITEM_SECOND_UNIT_NOT_FOUND);
        }

        /**
         * 行邮税率不能为空
         */
        if (logisticsCustomsDeclare.getPostalArticlesTaxRate() == null || logisticsCustomsDeclare.getPostalArticlesTaxRate() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_POSTALARTICLESTAXRATE_NULL);
        }
        /**
         * 行邮税率超过最大值
         */
        if (logisticsCustomsDeclare.getPostalArticlesTaxRate() > LogisticsConstants.MESSAGE_ITEM_POSTALARTICLESTAXRATE_MAX) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_POSTALARTICLESTAXRATE_EXCEED_MAX);
        }


        return checkField2(logisticsCustomsDeclare);
    }

    /**
     * 字段校验
     *
     * @param logisticsCustomsDeclare 商品报关对象
     * @return MsOnionResult
     * @throws MsOnionException MsOnionException
     */
    private MsOnionResult checkField2(LogisticsCustomsDeclare logisticsCustomsDeclare) throws MsOnionException {
        /**
         * 跨境税率不能为空
         */
        if (logisticsCustomsDeclare.getCrossBorderTaxRate() == null || logisticsCustomsDeclare.getCrossBorderTaxRate() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_CROSSBORDERTAXRATE_NULL);
        }
        /**
         * 跨境税率超过最大值
         */
        if (logisticsCustomsDeclare.getCrossBorderTaxRate() > LogisticsConstants.MESSAGE_ITEM_CROSSBORDERTAXRATE_MAX) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_CROSSBORDERTAXRATE_EXCEED_MAX);
        }

        /**
         * BC价不能为空
         */
        if (logisticsCustomsDeclare.getBcPrice() == null || logisticsCustomsDeclare.getBcPrice() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_BCPRICE_NULL);
        }
        /**
         * BC价超过最大值
         */
        if (logisticsCustomsDeclare.getBcPrice() > LogisticsConstants.MESSAGE_ITEM_PRICE_MAX) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_BCPRICE_EXCEED_MAX);
        }

        /**
         * HS编码不能为空
         */
        if (logisticsCustomsDeclare.getHsCode() == null || logisticsCustomsDeclare.getHsCode() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_HSCODE_NULL);
        }
        /**
         * HS编码超过最大值
         */
        if (logisticsCustomsDeclare.getHsCode() > LogisticsConstants.MESSAGE_ITEM_HSCODE_MAX) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_HSCODE_EXCEED_MAX);
        }

        /**
         * 毛重长度
         */
        if (MsOnionStringUtils.isNotEmpty(logisticsCustomsDeclare.getGrossWeight())
                && (logisticsCustomsDeclare.getGrossWeight().length() > LogisticsConstants.MESSAGE_ITEM_GROSSWEIGHT_MAX_LENGTH)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_GROSSWEIGHT_EXCEED_MAX);
        }

        /**
         * 行邮税号不能为空
         */
        if (logisticsCustomsDeclare.getPostalArticlesTaxNumber() == null || logisticsCustomsDeclare.getPostalArticlesTaxNumber() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_POSTALARTICLESTAXNUMBER_NULL);
        }
        /**
         * 行邮税号超过最大值
         */
        if (logisticsCustomsDeclare.getPostalArticlesTaxNumber() > LogisticsConstants.MESSAGE_ITEM_POSTALARTICLESTAXNUMBER_MAX) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_POSTALARTICLESTAXNUMBER_EXCEED_MAX);
        }
        /**
         * 是否含有消费税不能为空
         */
        if (logisticsCustomsDeclare.getIsContainExcise() == null || logisticsCustomsDeclare.getIsContainExcise() < 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_ISCONTAINEXCISE_NULL);
        }
        /**
         * 是否含有消费税超过最大值
         */
        if (logisticsCustomsDeclare.getIsContainExcise() > LogisticsConstants.MESSAGE_ITEM_ISCONTAINEXCISE_MAX) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_ISCONTAINEXCISE_EXCEED_MAX);
        }
        return MsOnionResult.ok();
    }

    /**
     * 获取模版标题集合
     *
     * @return 标题集合
     */
    @Override
    public List<String> getTemplateTitle() {
        return Arrays.asList(ManagerConstants.EXCEL_TEMPLATE_CUSTOMS_TITLE);
    }
}
