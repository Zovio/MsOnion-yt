/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营类目洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际类目直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
package cc.msonion.carambola.collector.service.impl;

/**
 * @Title: ItemCategoryServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: ItemCategory业务逻辑的实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月1日 下午2:26:21
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017年4月1日 下午2:26:21
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.common.constants.ItemConstants;
import cc.msonion.carambola.collector.common.constants.OrderByConstants;
import cc.msonion.carambola.collector.common.utils.ParamTypeUtils;
import cc.msonion.carambola.collector.pojo.CollectorItemCategory;
import cc.msonion.carambola.collector.pojo.CollectorItemCategoryExample;
import cc.msonion.carambola.collector.service.ItemCategoryService;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.common.utils.MsOnionCategoryLevelUtils;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: ItemCategoryServiceImpl
 * @Description: 商品类目Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月1日 下午2:26:21
 */
@Service
public class ItemCategoryServiceImpl extends MsOnionServiceExt<CollectorItemCategory, CollectorItemCategoryExample> implements ItemCategoryService {
    /**
     * 检查是否已存在
     *
     * @param apiVersion Api版本
     * @param param      参数值
     * @param type       参数类型
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
     * @param type       参数类型
     * @param exclude    排除：主键idx
     * @return 返回检查结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param, Integer type, Long exclude)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(param)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        CollectorItemCategoryExample example = new CollectorItemCategoryExample();
        CollectorItemCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        if (ParamTypeUtils.TYPE_CATEGORY_CODE == type) {
            criteria.andCodeEqualTo(param);
        } else {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        if (null != exclude && ItemConstants.DEFAULT_PRESENT_IDX < exclude) {
            criteria.andIdxNotEqualTo(exclude);
        }

        List<CollectorItemCategory> list = this.queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isEmpty(list)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, "不存在，可以使用" + ParamTypeUtils.getDescription(type), param);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "已存在，不可使用" + ParamTypeUtils.getDescription(type), param);
    }

    /**
     * 新增商品类目
     *
     * @param apiVersion   Api版本
     * @param itemCategory
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult addItemCategory(MsOnionApiVersion apiVersion, CollectorItemCategory itemCategory)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 类目编码唯一性判断
        String code = itemCategory.getCode();
        MsOnionResult rs = inspectParameter(apiVersion, code, ParamTypeUtils.TYPE_CATEGORY_CODE);
        if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return rs;
        }

        // 设置类目等级值
        Long pidx = itemCategory.getPidx();
        Short levelNum = getLevelNum(apiVersion, pidx);
        itemCategory.setLevelNum(levelNum);

        itemCategory.setImageMiddle(itemCategory.getImageBig());
        itemCategory.setImageSmall(itemCategory.getImageBig());

        // 设置主键
        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_CATEGORY_IDX_PATH);
        itemCategory.setIdx(idx);

        // 设置顶级idx
        Long topIdx = getTopIdx(apiVersion, pidx);
        itemCategory.setTopIdx(topIdx == null ? idx : topIdx);

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_CATEGORY_VERSION_PATH);
        itemCategory.setVersion(version);

        // 设置状态
        itemCategory.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemCategory=" + itemCategory);

        int result = this.save(apiVersion, itemCategory);
        if (result > 0) {
            return MsOnionResult.ok(itemCategory);
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
     * @Title deleteItemCategory
     * @Description 删除商品类目
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月1日 下午2:26:21
     */
    @Override
    public MsOnionResult deleteItemCategory(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * @param apiVersion
     * @param itemCategory
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateItemCategory
     * @Description 更新商品类目
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月1日 下午2:26:21
     */
    @Override
    public MsOnionResult updateItemCategory(MsOnionApiVersion apiVersion, CollectorItemCategory itemCategory)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == itemCategory) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_CATEGORY_NULL);
        }

        // 类目编码唯一性判断
        Long idx = itemCategory.getIdx();
        String code = itemCategory.getCode();
        MsOnionResult rs = inspectParameter(apiVersion, code, ParamTypeUtils.TYPE_CATEGORY_CODE, idx);
        if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return rs;
        }

        CollectorItemCategory temp = this.queryByPrimaryKey(apiVersion, idx);
        temp.setCode(code);
        temp.setName(itemCategory.getName());
        temp.setFullName(itemCategory.getFullName());
        temp.setPurchaseLimit(itemCategory.getPurchaseLimit());
        temp.setPidx(itemCategory.getPidx());
        temp.setLevelNum(getLevelNum(apiVersion, itemCategory.getPidx()));
        temp.setAttributeGroupIdx(itemCategory.getAttributeGroupIdx());
        temp.setUpdateTime(new Date());
        temp.setUpdateByMemberIdx(itemCategory.getUpdateByMemberIdx());
        temp.setCategorySequence(itemCategory.getCategorySequence());

        temp.setImageBig(itemCategory.getImageBig());
        temp.setImageMiddle(itemCategory.getImageBig());
        temp.setImageSmall(itemCategory.getImageBig());
        temp.setPublishChannel(itemCategory.getPublishChannel());

        // 设置顶级idx
        Long topIdx = getTopIdx(apiVersion, itemCategory.getPidx());
        temp.setTopIdx(topIdx == null ? idx : topIdx);

        int result = this.updateByPrimaryKey(apiVersion, temp);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, temp);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the itemCategory by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getItemCategoryByIdx
     * @Description 通过主键idx得到商品类目
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月1日 下午2:26:21
     */
    @Override
    public MsOnionResult getItemCategoryByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItemCategory itemCategory = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemCategory) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemCategory);
    }

    /**
     * 获取类目树结构
     *
     * @param apiVersion Api版本
     * @param idx        商品类目主键idx
     * @return
     * @throws MsOnionIllegalArgumentException 无效参数类型异常
     * @throws MsOnionException                异常
     */
    @Override
    public Map<String, Object> getCategoryTree(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        Map<String, Object> map = generateTree(apiVersion, idx);
        return map;
    }

    /**
     * 获取类目树结构
     *
     * @param apiVersion Api版本
     * @param code       类目编码
     * @param name       类目名称
     * @return 返回类目树结构
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public List<Map<String, Object>> getCategoryTree(MsOnionApiVersion apiVersion, String code, String name)
            throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorItemCategoryExample example = new CollectorItemCategoryExample();
        CollectorItemCategoryExample.Criteria criteria = example.createCriteria();

        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        if (MsOnionStringUtils.isEmpty(code) && MsOnionStringUtils.isEmpty(name)) {
            criteria.andPidxEqualTo(ItemConstants.DEFAULT_PRESENT_IDX);
        } else {
            CollectorItemCategoryExample e = new CollectorItemCategoryExample();
            CollectorItemCategoryExample.Criteria c = e.createCriteria();
            c.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            if (MsOnionStringUtils.isNotEmpty(code)) {
                c.andCodeLike(code.trim());
            }
            if (MsOnionStringUtils.isNotEmpty(name)) {
                c.andNameLike(name.trim());
            }

            List<Long> categoryIdxList = new ArrayList<>();
            List<CollectorItemCategory> itemCategoryList = this.queryByExample(apiVersion, e);
            for (CollectorItemCategory ic : itemCategoryList) {
                if (null != ic) {
                    Long topIdx = ic.getTopIdx();
                    if (!categoryIdxList.contains(topIdx)) {
                        categoryIdxList.add(topIdx);
                    }
                }
            }
            criteria.andIdxIn(categoryIdxList);
        }

        example.setOrderByClause(OrderByConstants.ORDER_BY_UPDATE_TIME);
        List<Map<String, Object>> list = new ArrayList<>();
        List<CollectorItemCategory> itemCategoryList = this.queryByExample(apiVersion, example);
        for (CollectorItemCategory itemCategory : itemCategoryList) {
            Long idx = itemCategory.getIdx();
            Map<String, Object> map = generateTree(apiVersion, idx);
            list.add(map);
        }

        return list;
    }


    /**
     * 异步获取类目树结构
     *
     * @param apiVersion Api版本
     * @param parentId   类目编码
     * @return 返回类目树结构
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public List<Map<String, Object>> getCategoryByParentId(MsOnionApiVersion apiVersion, Long parentId,
                    String name, String code) throws MsOnionIllegalArgumentException, MsOnionException {

        CollectorItemCategoryExample example = new CollectorItemCategoryExample();
        CollectorItemCategoryExample.Criteria criteria = example.createCriteria();

        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        if (parentId == null || parentId <= 0) {
            criteria.andPidxEqualTo(ItemConstants.DEFAULT_PRESENT_IDX);
        } else {
            criteria.andPidxEqualTo(parentId);
        }

        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(name);
        }

        if (StringUtils.isNotBlank(code)) {
            criteria.andCodeEqualTo(code);
        }

        example.setOrderByClause(OrderByConstants.ORDER_BY_UPDATE_TIME);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        List<CollectorItemCategory> itemCategoryList = this.queryByExample(apiVersion, example);
        for (CollectorItemCategory itemCategory : itemCategoryList) {
            Map<String, Object> map = new HashMap();
            map.put("id", itemCategory.getIdx());
            map.put("name", itemCategory.getName());
            map.put("code", itemCategory.getCode());
            map.put("text", itemCategory.getName());
            Boolean hasChildren = this.hasChildren(apiVersion, itemCategory.getIdx());
            if (null == hasChildren || !hasChildren) {
                map.put("children", new ArrayList());
                map.put("state", "open");
            } else {
                map.put("state", "closed");
            }

            list.add(map);
        }

        return list;
    }

    /**
     * 生成树
     *
     * @param apiVersion Api版本
     * @param idx        商品类目主键idx
     * @return 返回树
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    Map<String, Object> generateTree(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorItemCategory itemCategory = this.queryByPrimaryKey(apiVersion, idx);

        String id = itemCategory.getId();
        String name = itemCategory.getName();
        String code = itemCategory.getCode();

        Map<String, Object> map = new HashMap();
        map.put("id", id);
        map.put("name", name);
        map.put("text", name);
        map.put("code", code);
//        final short leaf = 3;
//        map.put("state", (itemCategory.getLevelNum() < leaf) ? "closed" : "open");
        CollectorItemCategoryExample example = new CollectorItemCategoryExample();
        CollectorItemCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andPidxEqualTo(idx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<Map<String, Object>> childrenList = new ArrayList<>();
        List<CollectorItemCategory> categoryList = this.queryByExample(apiVersion, example);
        Iterator<CollectorItemCategory> it = categoryList.iterator();
        while (it.hasNext()) {
            CollectorItemCategory itemCategory1 = it.next();
            Long idx1 = itemCategory1.getIdx();

            Map<String, Object> children = generateTree(apiVersion, idx1);
            childrenList.add(children);
        }
        map.put("children", childrenList);

        return map;
    }

    /**
     * 根据ID查询类目树
     *
     * @param apiVersion api版本
     * @param idx        类目的ID
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getCategoryIdxListByPidx
     * @Description 描述信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月16日 14:39:55
     */
    @Override
    public MsOnionResult getCategoryIdxListByPidx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        List<Long> idxList = new ArrayList<Long>();
        Map<String, Object> categoryTreeMap = generateTree(apiVersion, idx);
        if (categoryTreeMap == null || categoryTreeMap.isEmpty()) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_CONDITION_ILLEGAL);
        }
        addCategoryIdx(idxList, categoryTreeMap);
        return MsOnionResult.ok(idxList);
    }

    /**
     * 添加ID集合
     *
     * @param idxList ID集合
     * @param map     类目树map
     */
    private void addCategoryIdx(List<Long> idxList, Map<String, Object> map) {
        List<Map<String, Object>> childrenList = (List<Map<String, Object>>) map.get("children");
        if (MsOnionCollectionUtils.isNotEmpty(childrenList)) {
            for (Map<String, Object> subMap : childrenList) {
                addCategoryIdx(idxList, subMap);
            }
        } else {
            Long idx = Long.parseLong((String) map.get("id"));
            idxList.add(idx);
        }
    }

    /**
     * 获取父级
     *
     * @param apiVersion Api版本
     * @param idx        主键idx
     * @return 返回父级
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getParent(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorItemCategory itemCategory = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemCategory) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        Long pidx = itemCategory.getPidx();
        MsOnionResult result = this.getItemCategoryByIdx(apiVersion, pidx);
        if (null == result.getData()) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARENT_NULL);
        }

        CollectorItemCategory parentItemCategory = (CollectorItemCategory) result.getData();
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, parentItemCategory);
    }

    /**
     * 获取从上级到当前节点的有序集合
     *
     * @param apiVersion Api版本
     * @param idx        主键idx
     * @return 返回父级
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getCurrentAndParents(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        // 目前只有三级
        LinkedList<CollectorItemCategory> list = new LinkedList();
        CollectorItemCategory itemCategory = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemCategory) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }
        list.addFirst(itemCategory);

        Long pidx = itemCategory.getPidx();
        MsOnionResult result = this.getItemCategoryByIdx(apiVersion, pidx);
        while (null != result.getData()) {
            CollectorItemCategory parentItemCategory = (CollectorItemCategory) result.getData();
            list.addFirst(parentItemCategory);
            result = this.getItemCategoryByIdx(apiVersion, parentItemCategory.getPidx());
        }
        return MsOnionResult.ok(list);
    }

    /**
     * 获取类目等级值
     *
     * @param apiVersion Api版本
     * @param pidx       父级主键idx
     * @return 返回类目等级值
     * @throws MsOnionException 自定义异常
     */
    Short getLevelNum(MsOnionApiVersion apiVersion, Long pidx) throws MsOnionException {
        if (null == pidx || 0 == pidx) {
            return MsOnionCategoryLevelUtils.LEVEL_FIRST;
        }

        MsOnionResult result = this.getItemCategoryByIdx(apiVersion, pidx);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return null;
        }

        CollectorItemCategory itemCategory = (CollectorItemCategory) result.getData();
        String levelNum = String.valueOf(itemCategory.getLevelNum().intValue() + 1);
        return Short.valueOf(levelNum);
    }

    /**
     * 获取顶级idx
     *
     * @param apiVersion Api版本
     * @param pidx       父级idx
     * @return 返回顶级idx
     * @throws MsOnionException 自定义异常
     */
    Long getTopIdx(MsOnionApiVersion apiVersion, Long pidx) throws MsOnionException {
        if (null == pidx || 0 == pidx) {
            return null;
        }

        CollectorItemCategory itemCategory = this.queryByPrimaryKey(apiVersion, pidx);
        if (MsOnionCategoryLevelUtils.LEVEL_FIRST.equals(itemCategory.getLevelNum())) {
            return pidx;
        }

        return getTopIdx(apiVersion, itemCategory.getPidx());
    }

    /**
     * 获取某个分类是否有子节点
     * @param apiVersion 版本
     * @param categoryIdx 分类idx
     * @return 查询结果
     * @throws MsOnionException 自定义异常
     */
    private Boolean hasChildren(MsOnionApiVersion apiVersion, Long categoryIdx) throws MsOnionException {
        CollectorItemCategoryExample example = new CollectorItemCategoryExample();
        CollectorItemCategoryExample.Criteria criteria = example.createCriteria();

        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        criteria.andPidxEqualTo(categoryIdx);

        long count = this.countByExample(apiVersion, example);
        return count > 0 ? true : false;
    }
}
