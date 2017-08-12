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

/**
 * @Title: ItemBrandServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: ItemBrand业务逻辑的实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月1日 下午2:26:21
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017年4月1日 下午2:26:21
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 *
 * @Modify-by: Mark 3028554324@qq.com
 * @Modify-date: 2017年6月21日14:12:45
 * @Modify-version: V2.0.0
 * @Modify-description: 品牌名称合并为一个字段
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.common.constants.ItemConstants;
import cc.msonion.carambola.collector.common.utils.ParamTypeUtils;
import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemBrand;
import cc.msonion.carambola.collector.pojo.CollectorItemBrandExample;
import cc.msonion.carambola.collector.pojo.CollectorItemExample;
import cc.msonion.carambola.collector.service.ItemBrandService;
import cc.msonion.carambola.collector.service.ItemService;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicCodeUtils;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ItemBrandServiceImpl
 * @Description: 商品品牌Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月1日 下午2:26:21
 */
@Service
public class ItemBrandServiceImpl extends MsOnionServiceExt<CollectorItemBrand, CollectorItemBrandExample> implements ItemBrandService {

    /**
     * 商品 Service
     */
    @Autowired
    private ItemService itemService;

    /**
     * 检查是否已存在
     *
     * @param apiVersion Api版本
     * @param param      参数值
     * @param type       参数类型 {@link ParamTypeUtils}
     * @return 返回检查结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param, Integer type)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return inspectParameter(apiVersion, param, type, null);
    }

    /**
     * 检查是否已存在
     *
     * @param apiVersion Api版本
     * @param param      参数值
     * @param type       参数类型 {@link ParamTypeUtils}
     * @param exclude    排除：主键idx
     * @return 返回检查结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param, Integer type, Long exclude)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(param)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        CollectorItemBrandExample example = new CollectorItemBrandExample();
        CollectorItemBrandExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        if (ParamTypeUtils.TYPE_BRAND_NAME == type) {
            criteria.andBrandNameEqualTo(param);
        } else {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        if (null != exclude && ItemConstants.DEFAULT_PRESENT_IDX < exclude) {
            criteria.andIdxNotEqualTo(exclude);
        }

        List<CollectorItemBrand> list = this.queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isEmpty(list)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, "不存在，可以使用" + ParamTypeUtils.getDescription(type), param);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "已存在，不可使用" + ParamTypeUtils.getDescription(type), param);
    }

    /**
     * 新增商品品牌
     *
     * @param apiVersion Api版本
     * @param itemBrand
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult addItemBrand(MsOnionApiVersion apiVersion, CollectorItemBrand itemBrand)
            throws MsOnionIllegalArgumentException, MsOnionException {

        // 品牌名称唯一性判断
        String brandName = itemBrand.getBrandName();
        if (StringUtils.isNotBlank(brandName)) {
            MsOnionResult result = inspectParameter(apiVersion, brandName, ParamTypeUtils.TYPE_BRAND_NAME);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_BRAND_IDX_PATH);
        itemBrand.setIdx(idx);

        String code = MsOnionDistributedAtomicCodeUtils.getBrandCode(this.getMsOnionDomain(),
                this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy());
        itemBrand.setCode(code);

        itemBrand.setLogoSmall(itemBrand.getLogoBig());
        itemBrand.setLogoMiddle(itemBrand.getLogoBig());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_BRAND_VERSION_PATH);
        itemBrand.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemBrand=" + itemBrand);

        int result = this.save(apiVersion, itemBrand);
        if (result > 0) {
            return MsOnionResult.ok(itemBrand);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteItemBrand
     * @Description 删除商品品牌
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月1日 下午2:26:21
     */
    @Override
    public MsOnionResult deleteItemBrand(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItemBrand itemBrand = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemBrand) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        // 若品牌有商品，则不能删除
        CollectorItemExample example = new CollectorItemExample();
        CollectorItemExample.Criteria criteria = example.createCriteria();
        criteria.andBrandIdxEqualTo(idx);

        List<CollectorItem> itemList = itemService.queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isNotEmpty(itemList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "删除失败，该品牌有商品", itemBrand);
        }

        int result = this.deleteByPrimaryKey(apiVersion, idx);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_DELETE_FAILURE);
    }

    /**
     * @param apiVersion
     * @param itemBrand
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateItemBrand
     * @Description 更新商品品牌
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月1日 下午2:26:21
     */
    @Override
    public MsOnionResult updateItemBrand(MsOnionApiVersion apiVersion, CollectorItemBrand itemBrand)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == itemBrand) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_BRAND_NULL);
        }

        Long idx = itemBrand.getIdx();

        // 品牌名称唯一性判断
        String brandName = itemBrand.getBrandName();
        if (StringUtils.isNotBlank(brandName)) {
            MsOnionResult result = inspectParameter(apiVersion, brandName, ParamTypeUtils.TYPE_BRAND_NAME, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        // 选择禁用状态时，若品牌有商品，则不能禁用
        Short status = itemBrand.getStatus();
        if (MsOnionTableRecordStatus.DISABLE.getValue() == status) {
            CollectorItemExample example = new CollectorItemExample();
            CollectorItemExample.Criteria criteria = example.createCriteria();
            criteria.andBrandIdxEqualTo(idx);

            List<CollectorItem> itemList = itemService.queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isNotEmpty(itemList)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "不能禁用，该品牌有商品", itemBrand);
            }
        }

        CollectorItemBrand temp = this.queryByPrimaryKey(apiVersion, idx);
        temp.setBrandName(brandName);
        temp.setState(itemBrand.getState());
        temp.setStatus(status);
        temp.setIntroduce(itemBrand.getIntroduce());
        temp.setUpdateTime(new Date());
        temp.setUpdateByMemberIdx(itemBrand.getUpdateByMemberIdx());

        temp.setLogoSmall(itemBrand.getLogoBig());
        temp.setLogoMiddle(itemBrand.getLogoBig());
        temp.setLogoBig(itemBrand.getLogoBig());


        int result = this.updateByPrimaryKey(apiVersion, temp);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, temp);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the itemBrand by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getItemBrandByIdx
     * @Description 通过主键idx得到商品品牌
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月1日 下午2:26:21
     */
    @Override
    public MsOnionResult getItemBrandByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItemBrand itemBrand = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemBrand) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemBrand);
    }

    /**
     * 获取所有品牌
     *
     * @param apiVersion Api版本
     * @return 返回所有品牌
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public List<CollectorItemBrand> getAllItemBrand(MsOnionApiVersion apiVersion) throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorItemBrandExample example = new CollectorItemBrandExample();
        CollectorItemBrandExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemBrand> itemBrandList = this.queryByExample(apiVersion, example);
        return itemBrandList;
    }

    /**
     * 获取中英文品牌拼接后按首字母顺序排列
     * @param apiVersion Api版本
     * @return 品牌
     * @throws MsOnionException 自定义异常
     */
    @Override
    public List<CollectorItemBrand> getItemBrandOrderByName(MsOnionApiVersion apiVersion) throws MsOnionException {
        CollectorItemBrandExample example = new CollectorItemBrandExample();
        CollectorItemBrandExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        example.setOrderByClause(" CONVERT(brand_name USING gbk) ASC ");

        List<CollectorItemBrand> itemBrandList = this.queryByExample(apiVersion, example);
        return itemBrandList;
    }
}
