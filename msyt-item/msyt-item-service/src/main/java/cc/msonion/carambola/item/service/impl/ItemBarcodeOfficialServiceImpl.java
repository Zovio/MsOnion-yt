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


package cc.msonion.carambola.item.service.impl;

/**
 * @Title: ItemBarcodeOfficialServiceImpl.java
 * @Package: cc.msonion.carambola.item.service.impl
 * @Description: 正式商品条形码服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月23日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年06月23日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.item.pojo.ItemBarcodeOfficial;
import cc.msonion.carambola.item.pojo.ItemBarcodeOfficialExample;
import cc.msonion.carambola.item.pojo.ItemBasicOfficial;
import cc.msonion.carambola.item.pojo.ItemBasicOfficialExample;
import cc.msonion.carambola.item.service.ItemBarcodeOfficialService;
import cc.msonion.carambola.item.service.ItemBasicOfficialService;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ItemBarcodeOfficialServiceImpl
 * @Description: 正式商品条形码服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月23日
 */
@Service
public class ItemBarcodeOfficialServiceImpl extends MsOnionServiceExt<ItemBarcodeOfficial, ItemBarcodeOfficialExample>
        implements ItemBarcodeOfficialService {

    /**
     * itemBasicOfficialService
     */
    @Autowired
    private ItemBasicOfficialService itemBasicOfficialService;

    /**
     * 新增商品条形码
     *
     * @param apiVersion Api版本
     * @param list       正式商品条形码集合
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult addItemBarcodeOfficialList(MsOnionApiVersion apiVersion, List<String> list,
                                                    Long itemOfficialIdx) throws MsOnionIllegalArgumentException, MsOnionException {
        Map<Integer, ItemBarcodeOfficial> barcodeOfficialMap = new HashMap<>();
        if (MsOnionCollectionUtils.isNotEmpty(list)) {
            for (int index = 0; index < list.size(); index++) {

                ItemBarcodeOfficial itemBarCodeOfficial = new ItemBarcodeOfficial();
                itemBarCodeOfficial.setBarcode(list.get(index));
                itemBarCodeOfficial.setItemOfficialIdx(itemOfficialIdx);

                Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_ITEM_BARCODE_OFFICIAL_IDX_PATH);
                itemBarCodeOfficial.setIdx(idx);
                itemBarCodeOfficial.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

                // 版本号 , 不使用 idx 避免理解歧义
                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_ITEM_BARCODE_OFFICIAL_VERSION_PATH);
                itemBarCodeOfficial.setVersion(version);

                MsOnionResult result = this.isValid(apiVersion, itemBarCodeOfficial);
                if (MsOnionStatusConstants.STATUS_200 != result.getStatus()) {
                    return result;
                }

                barcodeOfficialMap.put(index, itemBarCodeOfficial);
            }

            this.getMsOnionLogger().info(this.getClass().getName(),
                    "ItemBarcodeOfficialServiceImpl barcodeOfficialMap=" + JSON.toJSONString(barcodeOfficialMap));

            MsOnionBatchReport batchReport = this.saveWithBatch(apiVersion, barcodeOfficialMap);

            this.getMsOnionLogger().info(this.getClass().getName(), "ItemBarcodeOfficialServiceImpl batchReport=" + JSON.toJSONString(batchReport));

            if (null != batchReport && batchReport.isAllSuccess()) {
                return MsOnionResult.ok();
            } else {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
            }
        }
        return MsOnionResult.ok();
    }

    /**
     * 新增商品条形码
     *
     * @param apiVersion          Api版本
     * @param itemBarCodeOfficial 正式商品条形码
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult addItemBarcodeOfficial(MsOnionApiVersion apiVersion,
                                                ItemBarcodeOfficial itemBarCodeOfficial) throws MsOnionIllegalArgumentException, MsOnionException {

        MsOnionResult msOnionResult = this.isValid(apiVersion, itemBarCodeOfficial);
        if (MsOnionStatusConstants.STATUS_200 != msOnionResult.getStatus()) {
            return msOnionResult;
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_ITEM_BARCODE_OFFICIAL_IDX_PATH);
        itemBarCodeOfficial.setIdx(idx);
        itemBarCodeOfficial.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_ITEM_BARCODE_OFFICIAL_VERSION_PATH);
        itemBarCodeOfficial.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemBarCodeOfficial=" + itemBarCodeOfficial);

        int result = this.save(apiVersion, itemBarCodeOfficial);
        if (result > 0) {
            return MsOnionResult.ok(itemBarCodeOfficial);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);

    }

    /**
     * 通过正式商品获取商品条形码，返回多个
     *
     * @param apiVersion      Api版本
     * @param itemOfficialIdx 正式商品idx
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getItemBarcodeOfficialByItemOfficalIdx(MsOnionApiVersion apiVersion,
                                                                Long itemOfficialIdx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (itemOfficialIdx == null || itemOfficialIdx <= 0L) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }
        ItemBarcodeOfficialExample example = new ItemBarcodeOfficialExample();
        ItemBarcodeOfficialExample.Criteria criteria = example.createCriteria();
        criteria.andItemOfficialIdxEqualTo(itemOfficialIdx);
        example.setOrderByClause("create_time asc");
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        List<ItemBarcodeOfficial> list = queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isNotEmpty(list)) {
            return MsOnionResult.ok(list);
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);

    }

    /**
     * 通过正式商品删除商品条形码
     *
     * @param apiVersion      Api版本
     * @param itemOfficialIdx 正式商品idx
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult deleteItemBarcodeOfficialByItemOfficalIdx(MsOnionApiVersion apiVersion,
                                                                   Long itemOfficialIdx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (itemOfficialIdx == null || itemOfficialIdx <= 0L) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }
        ItemBarcodeOfficialExample example = new ItemBarcodeOfficialExample();
        ItemBarcodeOfficialExample.Criteria criteria = example.createCriteria();
        criteria.andItemOfficialIdxEqualTo(itemOfficialIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        List<ItemBarcodeOfficial> list = queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isNotEmpty(list)) {
            List<Long> idxList = list.stream().map(ItemBarcodeOfficial::getIdx).collect(Collectors.toList());
           //   deleteByIdxes(apiVersion, idxList);
            for (int i = 0; i < idxList.size(); i++) {
                updateStatus(apiVersion, idxList.get(i), MsOnionTableRecordStatus.DELETED.getValue());
            }
        }
        return MsOnionResult.ok();
    }

    /**
     * 判断当前该条形码是否存在
     * MsOnionResult.status = 200,存在 其余不存在
     *
     * @param apiVersion Api版本
     * @param barcode    条形码
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult checkItemBarcodeOfficialByItemOfficalIdx(MsOnionApiVersion apiVersion, String barcode)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(barcode)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);

        }
        // 先判断商品条形码
        ItemBasicOfficialExample example1 = new ItemBasicOfficialExample();
        ItemBasicOfficialExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andBarcodeEqualTo(barcode.trim());
        ItemBasicOfficial itemBasicOfficial = itemBasicOfficialService.queryOne(apiVersion, example1);
        if (itemBasicOfficial != null) {
            return MsOnionResult.ok();
        }
        // 再判断商品仓库条形码
        ItemBarcodeOfficialExample example = new ItemBarcodeOfficialExample();
        ItemBarcodeOfficialExample.Criteria criteria = example.createCriteria();
        criteria.andBarcodeEqualTo(barcode.trim());
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        ItemBarcodeOfficial itemBarcodeOfficial = queryOne(apiVersion, example);
        if (itemBarcodeOfficial != null) {
            return MsOnionResult.ok();
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }


    /**
     * 检查是否参数有误
     *
     * @param apiVersion          版本
     * @param itemBarCodeOfficial 条形码
     * @return 检查结果
     * @throws MsOnionException 自定义异常
     */
    private MsOnionResult isValid(MsOnionApiVersion apiVersion, ItemBarcodeOfficial itemBarCodeOfficial)
            throws MsOnionException {
        if (null == itemBarCodeOfficial) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        String validatorMsg = MsOnionPojoValidatorUtils.validatePojo(itemBarCodeOfficial);
        if (StringUtils.isNotBlank(validatorMsg)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validatorMsg);
        }

        MsOnionResult existResult = checkItemBarcodeOfficialByItemOfficalIdx(apiVersion, itemBarCodeOfficial.getBarcode());
        if (existResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "该商品已存在此条形码");
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }
}
