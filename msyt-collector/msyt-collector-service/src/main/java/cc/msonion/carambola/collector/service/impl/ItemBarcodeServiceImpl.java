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
package cc.msonion.carambola.collector.service.impl;

import cc.msonion.carambola.collector.pojo.CollectorItemBarcode;
import cc.msonion.carambola.collector.pojo.CollectorItemBarcodeExample;
import cc.msonion.carambola.collector.service.ItemBarcodeService;
import cc.msonion.carambola.collector.service.ItemService;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ItemBarcodeServiceImpl
 * @Description: 商品条形码服务
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: Mark 3563249160@qq.com
 * @Date: 2017/6/21
 */
@Service
public class ItemBarcodeServiceImpl extends MsOnionServiceExt<CollectorItemBarcode, CollectorItemBarcodeExample> implements ItemBarcodeService {

    /**
     * itemService
     */
    @Autowired
    private ItemService itemService;

    /**
     * 添加条形码
     *
     * @param apiVersion 版本
     * @param barcode    条形码
     * @return 处理结果
     * @throws MsOnionException 自定义异常
     */
    @Override
    public MsOnionResult addItemBarcode(MsOnionApiVersion apiVersion, CollectorItemBarcode barcode) throws MsOnionException {
        if (barcode == null || barcode.getItemIdx() == null || StringUtils.isBlank(barcode.getBarcode())) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        String msg = MsOnionPojoValidatorUtils.validatePojo(barcode);
        if (StringUtils.isNotBlank(msg)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, msg);
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_BARCODE_IDX_PATH);
        barcode.setIdx(idx);

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_BARCODE_VERSION_IDX_PATH);
        barcode.setVersion(version);
        barcode.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        this.getMsOnionLogger().info(this.getClass().getName(), "新增，itemBarcode=" + barcode);

        int saveResult = this.save(apiVersion, barcode);
        if (saveResult < 1) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS, barcode);
    }

    /**
     * 保存条形码，不发布
     *
     * @param apiVersion 版本
     * @param barcodes   条形码
     * @param itemIdx    商品idx
     * @return 处理结果
     * @throws MsOnionException 自定义异常
     */
    @Override
    public MsOnionResult addItemBarcode(MsOnionApiVersion apiVersion, List<String> barcodes, Long itemIdx) throws MsOnionException {
        return this.addItemBarcode(apiVersion, barcodes, itemIdx, false);
    }


    /**
     * 删除条形码
     *
     * @param apiVersion 版本
     * @param idx        主键idx
     * @return 处理结果
     */
    @Override
    public MsOnionResult deleteItemBarcode(MsOnionApiVersion apiVersion, Long idx) throws MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        int result = this.deleteByPrimaryKey(apiVersion, idx);
        if (result > 0) {
            return MsOnionResult.ok();
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_DELETE_FAILURE);
    }

    /**
     * 获取商品的条形码
     *
     * @param apiVersion 版本
     * @param itemIdx    商品idx
     * @return 条形码
     * @throws MsOnionException 自定义异常
     */
    @Override
    public List<CollectorItemBarcode> getItemBarcodeList(MsOnionApiVersion apiVersion, Long itemIdx) throws MsOnionException {
        CollectorItemBarcodeExample example = new CollectorItemBarcodeExample();
        CollectorItemBarcodeExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        example.setOrderByClause("create_time asc");

        return this.queryByExample(apiVersion, example);
    }

    /**
     * 添加条形码
     *
     * @param apiVersion    版本
     * @param barcodes      条形码
     * @param itemIdx       商品idx
     * @param isNeedPublish 是否发布（false：否；true：是），默认为否
     * @return 处理结果
     * @throws MsOnionException 自定义异常
     */
    private MsOnionResult addItemBarcode(MsOnionApiVersion apiVersion, List<String> barcodes,
                                         Long itemIdx, Boolean isNeedPublish) throws MsOnionException {

        // 清空某个商品下的条形码
        MsOnionResult result = this.deleteItemBarcodeByItemIdx(apiVersion, itemIdx);
        if (MsOnionStatusConstants.STATUS_200 != result.getStatus()) {
            return result;
        }

        if (barcodes == null || barcodes.size() < 1) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        Map<Integer, CollectorItemBarcode> barcodeMap = new HashMap<>();
        for (int index = 0; index < barcodes.size(); index++) {
            CollectorItemBarcode itemBarcode = new CollectorItemBarcode();

            Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_BARCODE_IDX_PATH);
            itemBarcode.setIdx(idx);

            // 版本号 , 不使用 idx 避免理解歧义
            Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_BARCODE_VERSION_IDX_PATH);
            itemBarcode.setVersion(version);
            itemBarcode.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

            itemBarcode.setItemIdx(itemIdx);
            itemBarcode.setBarcode(barcodes.get(index));

            String msg = MsOnionPojoValidatorUtils.validatePojo(itemBarcode);
            if (StringUtils.isNotBlank(msg)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, msg);
            }

            barcodeMap.put(index, itemBarcode);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "ItemBarcodeServiceImpl barcodeMap=" + JSON.toJSONString(barcodeMap));

        MsOnionBatchReport batchReport = this.saveWithBatch(apiVersion, barcodeMap);

        this.getMsOnionLogger().info(this.getClass().getName(), "ItemBarcodeServiceImpl batchReport=" + JSON.toJSONString(batchReport));

        if (null != batchReport && batchReport.isAllSuccess()) {
            return MsOnionResult.ok();
        } else {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }
    }

    /**
     * 检查是否唯一
     *
     * @param apiVersion 版本
     * @param barcode    商品条形码
     * @return 是否唯一
     * @throws MsOnionException 自定义异常
     */
    @Override
    public MsOnionResult checkUnique(MsOnionApiVersion apiVersion, String barcode) throws MsOnionException {

        if (StringUtils.isBlank(barcode)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        CollectorItemBarcodeExample example = new CollectorItemBarcodeExample();
        CollectorItemBarcodeExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        criteria.andBarcodeEqualTo(barcode.trim());

        CollectorItemBarcode barcode2 = this.queryOne(apiVersion, example);
        if (barcode2 != null) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "该条形码已存在");
        }

        return MsOnionResult.ok();
    }

    /**
     * 清空某个商品下的条形码
     *
     * @param apiVersion 版本
     * @param itemIdx    商品主键idx
     * @return 处理结果
     * @throws MsOnionException 异常
     */
    private MsOnionResult deleteItemBarcodeByItemIdx(MsOnionApiVersion apiVersion, Long itemIdx) throws MsOnionException {
        if (null == itemIdx || itemIdx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItemBarcodeExample example = new CollectorItemBarcodeExample();
        CollectorItemBarcodeExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemBarcode> barcodes = this.queryByExample(apiVersion, example);
        if (barcodes != null && barcodes.size() > 0) {
            List<Long> idxs = barcodes.stream().map(CollectorItemBarcode::getIdx).collect(Collectors.toList());
            // int i = this.deleteByIdxes(apiVersion, idxs); 全部删除时候缓存有bug
            for (int i = 0; i < idxs.size(); i++) {
                updateStatus(apiVersion, idxs.get(i), MsOnionTableRecordStatus.DELETED.getValue());
            }
        }

        return MsOnionResult.ok();
    }
}
