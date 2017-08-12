package cc.msonion.carambola.warehouse.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WarehouseOfficialExample implements MsOnionBaseExampleAdapter {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public WarehouseOfficialExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        private static final long serialVersionUID = 1L;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdxIsNull() {
            addCriterion("idx is null");
            return (Criteria) this;
        }

        public Criteria andIdxIsNotNull() {
            addCriterion("idx is not null");
            return (Criteria) this;
        }

        public Criteria andIdxEqualTo(Long value) {
            addCriterion("idx =", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotEqualTo(Long value) {
            addCriterion("idx <>", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxGreaterThan(Long value) {
            addCriterion("idx >", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("idx >=", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxLessThan(Long value) {
            addCriterion("idx <", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxLessThanOrEqualTo(Long value) {
            addCriterion("idx <=", value, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxIn(List<Long> values) {
            addCriterion("idx in", values, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotIn(List<Long> values) {
            addCriterion("idx not in", values, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxBetween(Long value1, Long value2) {
            addCriterion("idx between", value1, value2, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxNotBetween(Long value1, Long value2) {
            addCriterion("idx not between", value1, value2, "idx");
            return (Criteria) this;
        }

        public Criteria andIdxCodeIsNull() {
            addCriterion("idx_code is null");
            return (Criteria) this;
        }

        public Criteria andIdxCodeIsNotNull() {
            addCriterion("idx_code is not null");
            return (Criteria) this;
        }

        public Criteria andIdxCodeEqualTo(Long value) {
            addCriterion("idx_code =", value, "idxCode");
            return (Criteria) this;
        }

        public Criteria andIdxCodeNotEqualTo(Long value) {
            addCriterion("idx_code <>", value, "idxCode");
            return (Criteria) this;
        }

        public Criteria andIdxCodeGreaterThan(Long value) {
            addCriterion("idx_code >", value, "idxCode");
            return (Criteria) this;
        }

        public Criteria andIdxCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("idx_code >=", value, "idxCode");
            return (Criteria) this;
        }

        public Criteria andIdxCodeLessThan(Long value) {
            addCriterion("idx_code <", value, "idxCode");
            return (Criteria) this;
        }

        public Criteria andIdxCodeLessThanOrEqualTo(Long value) {
            addCriterion("idx_code <=", value, "idxCode");
            return (Criteria) this;
        }

        public Criteria andIdxCodeIn(List<Long> values) {
            addCriterion("idx_code in", values, "idxCode");
            return (Criteria) this;
        }

        public Criteria andIdxCodeNotIn(List<Long> values) {
            addCriterion("idx_code not in", values, "idxCode");
            return (Criteria) this;
        }

        public Criteria andIdxCodeBetween(Long value1, Long value2) {
            addCriterion("idx_code between", value1, value2, "idxCode");
            return (Criteria) this;
        }

        public Criteria andIdxCodeNotBetween(Long value1, Long value2) {
            addCriterion("idx_code not between", value1, value2, "idxCode");
            return (Criteria) this;
        }

        public Criteria andItemOfficialIdxIsNull() {
            addCriterion("item_official_idx is null");
            return (Criteria) this;
        }

        public Criteria andItemOfficialIdxIsNotNull() {
            addCriterion("item_official_idx is not null");
            return (Criteria) this;
        }

        public Criteria andItemOfficialIdxEqualTo(Long value) {
            addCriterion("item_official_idx =", value, "itemOfficialIdx");
            return (Criteria) this;
        }

        public Criteria andItemOfficialIdxNotEqualTo(Long value) {
            addCriterion("item_official_idx <>", value, "itemOfficialIdx");
            return (Criteria) this;
        }

        public Criteria andItemOfficialIdxGreaterThan(Long value) {
            addCriterion("item_official_idx >", value, "itemOfficialIdx");
            return (Criteria) this;
        }

        public Criteria andItemOfficialIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("item_official_idx >=", value, "itemOfficialIdx");
            return (Criteria) this;
        }

        public Criteria andItemOfficialIdxLessThan(Long value) {
            addCriterion("item_official_idx <", value, "itemOfficialIdx");
            return (Criteria) this;
        }

        public Criteria andItemOfficialIdxLessThanOrEqualTo(Long value) {
            addCriterion("item_official_idx <=", value, "itemOfficialIdx");
            return (Criteria) this;
        }

        public Criteria andItemOfficialIdxIn(List<Long> values) {
            addCriterion("item_official_idx in", values, "itemOfficialIdx");
            return (Criteria) this;
        }

        public Criteria andItemOfficialIdxNotIn(List<Long> values) {
            addCriterion("item_official_idx not in", values, "itemOfficialIdx");
            return (Criteria) this;
        }

        public Criteria andItemOfficialIdxBetween(Long value1, Long value2) {
            addCriterion("item_official_idx between", value1, value2, "itemOfficialIdx");
            return (Criteria) this;
        }

        public Criteria andItemOfficialIdxNotBetween(Long value1, Long value2) {
            addCriterion("item_official_idx not between", value1, value2, "itemOfficialIdx");
            return (Criteria) this;
        }

        public Criteria andRealTimeInventoryIsNull() {
            addCriterion("real_time_inventory is null");
            return (Criteria) this;
        }

        public Criteria andRealTimeInventoryIsNotNull() {
            addCriterion("real_time_inventory is not null");
            return (Criteria) this;
        }

        public Criteria andRealTimeInventoryEqualTo(Integer value) {
            addCriterion("real_time_inventory =", value, "realTimeInventory");
            return (Criteria) this;
        }

        public Criteria andRealTimeInventoryNotEqualTo(Integer value) {
            addCriterion("real_time_inventory <>", value, "realTimeInventory");
            return (Criteria) this;
        }

        public Criteria andRealTimeInventoryGreaterThan(Integer value) {
            addCriterion("real_time_inventory >", value, "realTimeInventory");
            return (Criteria) this;
        }

        public Criteria andRealTimeInventoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_time_inventory >=", value, "realTimeInventory");
            return (Criteria) this;
        }

        public Criteria andRealTimeInventoryLessThan(Integer value) {
            addCriterion("real_time_inventory <", value, "realTimeInventory");
            return (Criteria) this;
        }

        public Criteria andRealTimeInventoryLessThanOrEqualTo(Integer value) {
            addCriterion("real_time_inventory <=", value, "realTimeInventory");
            return (Criteria) this;
        }

        public Criteria andRealTimeInventoryIn(List<Integer> values) {
            addCriterion("real_time_inventory in", values, "realTimeInventory");
            return (Criteria) this;
        }

        public Criteria andRealTimeInventoryNotIn(List<Integer> values) {
            addCriterion("real_time_inventory not in", values, "realTimeInventory");
            return (Criteria) this;
        }

        public Criteria andRealTimeInventoryBetween(Integer value1, Integer value2) {
            addCriterion("real_time_inventory between", value1, value2, "realTimeInventory");
            return (Criteria) this;
        }

        public Criteria andRealTimeInventoryNotBetween(Integer value1, Integer value2) {
            addCriterion("real_time_inventory not between", value1, value2, "realTimeInventory");
            return (Criteria) this;
        }

        public Criteria andSafeInventoryIsNull() {
            addCriterion("safe_inventory is null");
            return (Criteria) this;
        }

        public Criteria andSafeInventoryIsNotNull() {
            addCriterion("safe_inventory is not null");
            return (Criteria) this;
        }

        public Criteria andSafeInventoryEqualTo(Integer value) {
            addCriterion("safe_inventory =", value, "safeInventory");
            return (Criteria) this;
        }

        public Criteria andSafeInventoryNotEqualTo(Integer value) {
            addCriterion("safe_inventory <>", value, "safeInventory");
            return (Criteria) this;
        }

        public Criteria andSafeInventoryGreaterThan(Integer value) {
            addCriterion("safe_inventory >", value, "safeInventory");
            return (Criteria) this;
        }

        public Criteria andSafeInventoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("safe_inventory >=", value, "safeInventory");
            return (Criteria) this;
        }

        public Criteria andSafeInventoryLessThan(Integer value) {
            addCriterion("safe_inventory <", value, "safeInventory");
            return (Criteria) this;
        }

        public Criteria andSafeInventoryLessThanOrEqualTo(Integer value) {
            addCriterion("safe_inventory <=", value, "safeInventory");
            return (Criteria) this;
        }

        public Criteria andSafeInventoryIn(List<Integer> values) {
            addCriterion("safe_inventory in", values, "safeInventory");
            return (Criteria) this;
        }

        public Criteria andSafeInventoryNotIn(List<Integer> values) {
            addCriterion("safe_inventory not in", values, "safeInventory");
            return (Criteria) this;
        }

        public Criteria andSafeInventoryBetween(Integer value1, Integer value2) {
            addCriterion("safe_inventory between", value1, value2, "safeInventory");
            return (Criteria) this;
        }

        public Criteria andSafeInventoryNotBetween(Integer value1, Integer value2) {
            addCriterion("safe_inventory not between", value1, value2, "safeInventory");
            return (Criteria) this;
        }

        public Criteria andDeliveryCoefficientIsNull() {
            addCriterion("delivery_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCoefficientIsNotNull() {
            addCriterion("delivery_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCoefficientEqualTo(Short value) {
            addCriterion("delivery_coefficient =", value, "deliveryCoefficient");
            return (Criteria) this;
        }

        public Criteria andDeliveryCoefficientNotEqualTo(Short value) {
            addCriterion("delivery_coefficient <>", value, "deliveryCoefficient");
            return (Criteria) this;
        }

        public Criteria andDeliveryCoefficientGreaterThan(Short value) {
            addCriterion("delivery_coefficient >", value, "deliveryCoefficient");
            return (Criteria) this;
        }

        public Criteria andDeliveryCoefficientGreaterThanOrEqualTo(Short value) {
            addCriterion("delivery_coefficient >=", value, "deliveryCoefficient");
            return (Criteria) this;
        }

        public Criteria andDeliveryCoefficientLessThan(Short value) {
            addCriterion("delivery_coefficient <", value, "deliveryCoefficient");
            return (Criteria) this;
        }

        public Criteria andDeliveryCoefficientLessThanOrEqualTo(Short value) {
            addCriterion("delivery_coefficient <=", value, "deliveryCoefficient");
            return (Criteria) this;
        }

        public Criteria andDeliveryCoefficientIn(List<Short> values) {
            addCriterion("delivery_coefficient in", values, "deliveryCoefficient");
            return (Criteria) this;
        }

        public Criteria andDeliveryCoefficientNotIn(List<Short> values) {
            addCriterion("delivery_coefficient not in", values, "deliveryCoefficient");
            return (Criteria) this;
        }

        public Criteria andDeliveryCoefficientBetween(Short value1, Short value2) {
            addCriterion("delivery_coefficient between", value1, value2, "deliveryCoefficient");
            return (Criteria) this;
        }

        public Criteria andDeliveryCoefficientNotBetween(Short value1, Short value2) {
            addCriterion("delivery_coefficient not between", value1, value2, "deliveryCoefficient");
            return (Criteria) this;
        }

        public Criteria andIsKeyOrderIsNull() {
            addCriterion("is_key_order is null");
            return (Criteria) this;
        }

        public Criteria andIsKeyOrderIsNotNull() {
            addCriterion("is_key_order is not null");
            return (Criteria) this;
        }

        public Criteria andIsKeyOrderEqualTo(Short value) {
            addCriterion("is_key_order =", value, "isKeyOrder");
            return (Criteria) this;
        }

        public Criteria andIsKeyOrderNotEqualTo(Short value) {
            addCriterion("is_key_order <>", value, "isKeyOrder");
            return (Criteria) this;
        }

        public Criteria andIsKeyOrderGreaterThan(Short value) {
            addCriterion("is_key_order >", value, "isKeyOrder");
            return (Criteria) this;
        }

        public Criteria andIsKeyOrderGreaterThanOrEqualTo(Short value) {
            addCriterion("is_key_order >=", value, "isKeyOrder");
            return (Criteria) this;
        }

        public Criteria andIsKeyOrderLessThan(Short value) {
            addCriterion("is_key_order <", value, "isKeyOrder");
            return (Criteria) this;
        }

        public Criteria andIsKeyOrderLessThanOrEqualTo(Short value) {
            addCriterion("is_key_order <=", value, "isKeyOrder");
            return (Criteria) this;
        }

        public Criteria andIsKeyOrderIn(List<Short> values) {
            addCriterion("is_key_order in", values, "isKeyOrder");
            return (Criteria) this;
        }

        public Criteria andIsKeyOrderNotIn(List<Short> values) {
            addCriterion("is_key_order not in", values, "isKeyOrder");
            return (Criteria) this;
        }

        public Criteria andIsKeyOrderBetween(Short value1, Short value2) {
            addCriterion("is_key_order between", value1, value2, "isKeyOrder");
            return (Criteria) this;
        }

        public Criteria andIsKeyOrderNotBetween(Short value1, Short value2) {
            addCriterion("is_key_order not between", value1, value2, "isKeyOrder");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingIsNull() {
            addCriterion("is_free_shipping is null");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingIsNotNull() {
            addCriterion("is_free_shipping is not null");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingEqualTo(Short value) {
            addCriterion("is_free_shipping =", value, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingNotEqualTo(Short value) {
            addCriterion("is_free_shipping <>", value, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingGreaterThan(Short value) {
            addCriterion("is_free_shipping >", value, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingGreaterThanOrEqualTo(Short value) {
            addCriterion("is_free_shipping >=", value, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingLessThan(Short value) {
            addCriterion("is_free_shipping <", value, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingLessThanOrEqualTo(Short value) {
            addCriterion("is_free_shipping <=", value, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingIn(List<Short> values) {
            addCriterion("is_free_shipping in", values, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingNotIn(List<Short> values) {
            addCriterion("is_free_shipping not in", values, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingBetween(Short value1, Short value2) {
            addCriterion("is_free_shipping between", value1, value2, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingNotBetween(Short value1, Short value2) {
            addCriterion("is_free_shipping not between", value1, value2, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreateByMemberIdxIsNull() {
            addCriterion("create_by_member_idx is null");
            return (Criteria) this;
        }

        public Criteria andCreateByMemberIdxIsNotNull() {
            addCriterion("create_by_member_idx is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByMemberIdxEqualTo(Long value) {
            addCriterion("create_by_member_idx =", value, "createByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andCreateByMemberIdxNotEqualTo(Long value) {
            addCriterion("create_by_member_idx <>", value, "createByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andCreateByMemberIdxGreaterThan(Long value) {
            addCriterion("create_by_member_idx >", value, "createByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andCreateByMemberIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("create_by_member_idx >=", value, "createByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andCreateByMemberIdxLessThan(Long value) {
            addCriterion("create_by_member_idx <", value, "createByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andCreateByMemberIdxLessThanOrEqualTo(Long value) {
            addCriterion("create_by_member_idx <=", value, "createByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andCreateByMemberIdxIn(List<Long> values) {
            addCriterion("create_by_member_idx in", values, "createByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andCreateByMemberIdxNotIn(List<Long> values) {
            addCriterion("create_by_member_idx not in", values, "createByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andCreateByMemberIdxBetween(Long value1, Long value2) {
            addCriterion("create_by_member_idx between", value1, value2, "createByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andCreateByMemberIdxNotBetween(Long value1, Long value2) {
            addCriterion("create_by_member_idx not between", value1, value2, "createByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andUpdateByMemberIdxIsNull() {
            addCriterion("update_by_member_idx is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByMemberIdxIsNotNull() {
            addCriterion("update_by_member_idx is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByMemberIdxEqualTo(Long value) {
            addCriterion("update_by_member_idx =", value, "updateByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andUpdateByMemberIdxNotEqualTo(Long value) {
            addCriterion("update_by_member_idx <>", value, "updateByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andUpdateByMemberIdxGreaterThan(Long value) {
            addCriterion("update_by_member_idx >", value, "updateByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andUpdateByMemberIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("update_by_member_idx >=", value, "updateByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andUpdateByMemberIdxLessThan(Long value) {
            addCriterion("update_by_member_idx <", value, "updateByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andUpdateByMemberIdxLessThanOrEqualTo(Long value) {
            addCriterion("update_by_member_idx <=", value, "updateByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andUpdateByMemberIdxIn(List<Long> values) {
            addCriterion("update_by_member_idx in", values, "updateByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andUpdateByMemberIdxNotIn(List<Long> values) {
            addCriterion("update_by_member_idx not in", values, "updateByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andUpdateByMemberIdxBetween(Long value1, Long value2) {
            addCriterion("update_by_member_idx between", value1, value2, "updateByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andUpdateByMemberIdxNotBetween(Long value1, Long value2) {
            addCriterion("update_by_member_idx not between", value1, value2, "updateByMemberIdx");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Long value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Long value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Long value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Long value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Long value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Long value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Long> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Long> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Long value1, Long value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Long value1, Long value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andExtIsNull() {
            addCriterion("ext is null");
            return (Criteria) this;
        }

        public Criteria andExtIsNotNull() {
            addCriterion("ext is not null");
            return (Criteria) this;
        }

        public Criteria andExtEqualTo(String value) {
            addCriterion("ext =", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotEqualTo(String value) {
            addCriterion("ext <>", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtGreaterThan(String value) {
            addCriterion("ext >", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtGreaterThanOrEqualTo(String value) {
            addCriterion("ext >=", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtLessThan(String value) {
            addCriterion("ext <", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtLessThanOrEqualTo(String value) {
            addCriterion("ext <=", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtLike(String value) {
            addCriterion("ext like", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotLike(String value) {
            addCriterion("ext not like", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtIn(List<String> values) {
            addCriterion("ext in", values, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotIn(List<String> values) {
            addCriterion("ext not in", values, "ext");
            return (Criteria) this;
        }

        public Criteria andExtBetween(String value1, String value2) {
            addCriterion("ext between", value1, value2, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotBetween(String value1, String value2) {
            addCriterion("ext not between", value1, value2, "ext");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {
        private static final long serialVersionUID = 1L;

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        private static final long serialVersionUID = 1L;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}