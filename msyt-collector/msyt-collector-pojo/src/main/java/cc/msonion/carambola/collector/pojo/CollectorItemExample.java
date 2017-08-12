package cc.msonion.carambola.collector.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollectorItemExample implements MsOnionBaseExampleAdapter {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CollectorItemExample() {
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

        public Criteria andItemStateIdxIsNull() {
            addCriterion("item_state_idx is null");
            return (Criteria) this;
        }

        public Criteria andItemStateIdxIsNotNull() {
            addCriterion("item_state_idx is not null");
            return (Criteria) this;
        }

        public Criteria andItemStateIdxEqualTo(Long value) {
            addCriterion("item_state_idx =", value, "itemStateIdx");
            return (Criteria) this;
        }

        public Criteria andItemStateIdxNotEqualTo(Long value) {
            addCriterion("item_state_idx <>", value, "itemStateIdx");
            return (Criteria) this;
        }

        public Criteria andItemStateIdxGreaterThan(Long value) {
            addCriterion("item_state_idx >", value, "itemStateIdx");
            return (Criteria) this;
        }

        public Criteria andItemStateIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("item_state_idx >=", value, "itemStateIdx");
            return (Criteria) this;
        }

        public Criteria andItemStateIdxLessThan(Long value) {
            addCriterion("item_state_idx <", value, "itemStateIdx");
            return (Criteria) this;
        }

        public Criteria andItemStateIdxLessThanOrEqualTo(Long value) {
            addCriterion("item_state_idx <=", value, "itemStateIdx");
            return (Criteria) this;
        }

        public Criteria andItemStateIdxIn(List<Long> values) {
            addCriterion("item_state_idx in", values, "itemStateIdx");
            return (Criteria) this;
        }

        public Criteria andItemStateIdxNotIn(List<Long> values) {
            addCriterion("item_state_idx not in", values, "itemStateIdx");
            return (Criteria) this;
        }

        public Criteria andItemStateIdxBetween(Long value1, Long value2) {
            addCriterion("item_state_idx between", value1, value2, "itemStateIdx");
            return (Criteria) this;
        }

        public Criteria andItemStateIdxNotBetween(Long value1, Long value2) {
            addCriterion("item_state_idx not between", value1, value2, "itemStateIdx");
            return (Criteria) this;
        }

        public Criteria andItemExtIdxIsNull() {
            addCriterion("item_ext_idx is null");
            return (Criteria) this;
        }

        public Criteria andItemExtIdxIsNotNull() {
            addCriterion("item_ext_idx is not null");
            return (Criteria) this;
        }

        public Criteria andItemExtIdxEqualTo(Long value) {
            addCriterion("item_ext_idx =", value, "itemExtIdx");
            return (Criteria) this;
        }

        public Criteria andItemExtIdxNotEqualTo(Long value) {
            addCriterion("item_ext_idx <>", value, "itemExtIdx");
            return (Criteria) this;
        }

        public Criteria andItemExtIdxGreaterThan(Long value) {
            addCriterion("item_ext_idx >", value, "itemExtIdx");
            return (Criteria) this;
        }

        public Criteria andItemExtIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("item_ext_idx >=", value, "itemExtIdx");
            return (Criteria) this;
        }

        public Criteria andItemExtIdxLessThan(Long value) {
            addCriterion("item_ext_idx <", value, "itemExtIdx");
            return (Criteria) this;
        }

        public Criteria andItemExtIdxLessThanOrEqualTo(Long value) {
            addCriterion("item_ext_idx <=", value, "itemExtIdx");
            return (Criteria) this;
        }

        public Criteria andItemExtIdxIn(List<Long> values) {
            addCriterion("item_ext_idx in", values, "itemExtIdx");
            return (Criteria) this;
        }

        public Criteria andItemExtIdxNotIn(List<Long> values) {
            addCriterion("item_ext_idx not in", values, "itemExtIdx");
            return (Criteria) this;
        }

        public Criteria andItemExtIdxBetween(Long value1, Long value2) {
            addCriterion("item_ext_idx between", value1, value2, "itemExtIdx");
            return (Criteria) this;
        }

        public Criteria andItemExtIdxNotBetween(Long value1, Long value2) {
            addCriterion("item_ext_idx not between", value1, value2, "itemExtIdx");
            return (Criteria) this;
        }

        public Criteria andItemNoIsNull() {
            addCriterion("item_no is null");
            return (Criteria) this;
        }

        public Criteria andItemNoIsNotNull() {
            addCriterion("item_no is not null");
            return (Criteria) this;
        }

        public Criteria andItemNoEqualTo(String value) {
            addCriterion("item_no =", value, "itemNo");
            return (Criteria) this;
        }

        public Criteria andItemNoNotEqualTo(String value) {
            addCriterion("item_no <>", value, "itemNo");
            return (Criteria) this;
        }

        public Criteria andItemNoGreaterThan(String value) {
            addCriterion("item_no >", value, "itemNo");
            return (Criteria) this;
        }

        public Criteria andItemNoGreaterThanOrEqualTo(String value) {
            addCriterion("item_no >=", value, "itemNo");
            return (Criteria) this;
        }

        public Criteria andItemNoLessThan(String value) {
            addCriterion("item_no <", value, "itemNo");
            return (Criteria) this;
        }

        public Criteria andItemNoLessThanOrEqualTo(String value) {
            addCriterion("item_no <=", value, "itemNo");
            return (Criteria) this;
        }

        public Criteria andItemNoLike(String value) {
            addCriterion("item_no like", value, "itemNo");
            return (Criteria) this;
        }

        public Criteria andItemNoNotLike(String value) {
            addCriterion("item_no not like", value, "itemNo");
            return (Criteria) this;
        }

        public Criteria andItemNoIn(List<String> values) {
            addCriterion("item_no in", values, "itemNo");
            return (Criteria) this;
        }

        public Criteria andItemNoNotIn(List<String> values) {
            addCriterion("item_no not in", values, "itemNo");
            return (Criteria) this;
        }

        public Criteria andItemNoBetween(String value1, String value2) {
            addCriterion("item_no between", value1, value2, "itemNo");
            return (Criteria) this;
        }

        public Criteria andItemNoNotBetween(String value1, String value2) {
            addCriterion("item_no not between", value1, value2, "itemNo");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNull() {
            addCriterion("barcode is null");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNotNull() {
            addCriterion("barcode is not null");
            return (Criteria) this;
        }

        public Criteria andBarcodeEqualTo(String value) {
            addCriterion("barcode =", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotEqualTo(String value) {
            addCriterion("barcode <>", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThan(String value) {
            addCriterion("barcode >", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThanOrEqualTo(String value) {
            addCriterion("barcode >=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThan(String value) {
            addCriterion("barcode <", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThanOrEqualTo(String value) {
            addCriterion("barcode <=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLike(String value) {
            addCriterion("barcode like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotLike(String value) {
            addCriterion("barcode not like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeIn(List<String> values) {
            addCriterion("barcode in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotIn(List<String> values) {
            addCriterion("barcode not in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeBetween(String value1, String value2) {
            addCriterion("barcode between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotBetween(String value1, String value2) {
            addCriterion("barcode not between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andCnNameIsNull() {
            addCriterion("cn_name is null");
            return (Criteria) this;
        }

        public Criteria andCnNameIsNotNull() {
            addCriterion("cn_name is not null");
            return (Criteria) this;
        }

        public Criteria andCnNameEqualTo(String value) {
            addCriterion("cn_name =", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameNotEqualTo(String value) {
            addCriterion("cn_name <>", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameGreaterThan(String value) {
            addCriterion("cn_name >", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameGreaterThanOrEqualTo(String value) {
            addCriterion("cn_name >=", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameLessThan(String value) {
            addCriterion("cn_name <", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameLessThanOrEqualTo(String value) {
            addCriterion("cn_name <=", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameLike(String value) {
            addCriterion("cn_name like", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameNotLike(String value) {
            addCriterion("cn_name not like", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameIn(List<String> values) {
            addCriterion("cn_name in", values, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameNotIn(List<String> values) {
            addCriterion("cn_name not in", values, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameBetween(String value1, String value2) {
            addCriterion("cn_name between", value1, value2, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameNotBetween(String value1, String value2) {
            addCriterion("cn_name not between", value1, value2, "cnName");
            return (Criteria) this;
        }

        public Criteria andEnNameIsNull() {
            addCriterion("en_name is null");
            return (Criteria) this;
        }

        public Criteria andEnNameIsNotNull() {
            addCriterion("en_name is not null");
            return (Criteria) this;
        }

        public Criteria andEnNameEqualTo(String value) {
            addCriterion("en_name =", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotEqualTo(String value) {
            addCriterion("en_name <>", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameGreaterThan(String value) {
            addCriterion("en_name >", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameGreaterThanOrEqualTo(String value) {
            addCriterion("en_name >=", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLessThan(String value) {
            addCriterion("en_name <", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLessThanOrEqualTo(String value) {
            addCriterion("en_name <=", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLike(String value) {
            addCriterion("en_name like", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotLike(String value) {
            addCriterion("en_name not like", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameIn(List<String> values) {
            addCriterion("en_name in", values, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotIn(List<String> values) {
            addCriterion("en_name not in", values, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameBetween(String value1, String value2) {
            addCriterion("en_name between", value1, value2, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotBetween(String value1, String value2) {
            addCriterion("en_name not between", value1, value2, "enName");
            return (Criteria) this;
        }

        public Criteria andBrandIdxIsNull() {
            addCriterion("brand_idx is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdxIsNotNull() {
            addCriterion("brand_idx is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdxEqualTo(Long value) {
            addCriterion("brand_idx =", value, "brandIdx");
            return (Criteria) this;
        }

        public Criteria andBrandIdxNotEqualTo(Long value) {
            addCriterion("brand_idx <>", value, "brandIdx");
            return (Criteria) this;
        }

        public Criteria andBrandIdxGreaterThan(Long value) {
            addCriterion("brand_idx >", value, "brandIdx");
            return (Criteria) this;
        }

        public Criteria andBrandIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("brand_idx >=", value, "brandIdx");
            return (Criteria) this;
        }

        public Criteria andBrandIdxLessThan(Long value) {
            addCriterion("brand_idx <", value, "brandIdx");
            return (Criteria) this;
        }

        public Criteria andBrandIdxLessThanOrEqualTo(Long value) {
            addCriterion("brand_idx <=", value, "brandIdx");
            return (Criteria) this;
        }

        public Criteria andBrandIdxIn(List<Long> values) {
            addCriterion("brand_idx in", values, "brandIdx");
            return (Criteria) this;
        }

        public Criteria andBrandIdxNotIn(List<Long> values) {
            addCriterion("brand_idx not in", values, "brandIdx");
            return (Criteria) this;
        }

        public Criteria andBrandIdxBetween(Long value1, Long value2) {
            addCriterion("brand_idx between", value1, value2, "brandIdx");
            return (Criteria) this;
        }

        public Criteria andBrandIdxNotBetween(Long value1, Long value2) {
            addCriterion("brand_idx not between", value1, value2, "brandIdx");
            return (Criteria) this;
        }

        public Criteria andOriginIdxIsNull() {
            addCriterion("origin_idx is null");
            return (Criteria) this;
        }

        public Criteria andOriginIdxIsNotNull() {
            addCriterion("origin_idx is not null");
            return (Criteria) this;
        }

        public Criteria andOriginIdxEqualTo(Long value) {
            addCriterion("origin_idx =", value, "originIdx");
            return (Criteria) this;
        }

        public Criteria andOriginIdxNotEqualTo(Long value) {
            addCriterion("origin_idx <>", value, "originIdx");
            return (Criteria) this;
        }

        public Criteria andOriginIdxGreaterThan(Long value) {
            addCriterion("origin_idx >", value, "originIdx");
            return (Criteria) this;
        }

        public Criteria andOriginIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("origin_idx >=", value, "originIdx");
            return (Criteria) this;
        }

        public Criteria andOriginIdxLessThan(Long value) {
            addCriterion("origin_idx <", value, "originIdx");
            return (Criteria) this;
        }

        public Criteria andOriginIdxLessThanOrEqualTo(Long value) {
            addCriterion("origin_idx <=", value, "originIdx");
            return (Criteria) this;
        }

        public Criteria andOriginIdxIn(List<Long> values) {
            addCriterion("origin_idx in", values, "originIdx");
            return (Criteria) this;
        }

        public Criteria andOriginIdxNotIn(List<Long> values) {
            addCriterion("origin_idx not in", values, "originIdx");
            return (Criteria) this;
        }

        public Criteria andOriginIdxBetween(Long value1, Long value2) {
            addCriterion("origin_idx between", value1, value2, "originIdx");
            return (Criteria) this;
        }

        public Criteria andOriginIdxNotBetween(Long value1, Long value2) {
            addCriterion("origin_idx not between", value1, value2, "originIdx");
            return (Criteria) this;
        }

        public Criteria andCategoryIdxIsNull() {
            addCriterion("category_idx is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdxIsNotNull() {
            addCriterion("category_idx is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdxEqualTo(Long value) {
            addCriterion("category_idx =", value, "categoryIdx");
            return (Criteria) this;
        }

        public Criteria andCategoryIdxNotEqualTo(Long value) {
            addCriterion("category_idx <>", value, "categoryIdx");
            return (Criteria) this;
        }

        public Criteria andCategoryIdxGreaterThan(Long value) {
            addCriterion("category_idx >", value, "categoryIdx");
            return (Criteria) this;
        }

        public Criteria andCategoryIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("category_idx >=", value, "categoryIdx");
            return (Criteria) this;
        }

        public Criteria andCategoryIdxLessThan(Long value) {
            addCriterion("category_idx <", value, "categoryIdx");
            return (Criteria) this;
        }

        public Criteria andCategoryIdxLessThanOrEqualTo(Long value) {
            addCriterion("category_idx <=", value, "categoryIdx");
            return (Criteria) this;
        }

        public Criteria andCategoryIdxIn(List<Long> values) {
            addCriterion("category_idx in", values, "categoryIdx");
            return (Criteria) this;
        }

        public Criteria andCategoryIdxNotIn(List<Long> values) {
            addCriterion("category_idx not in", values, "categoryIdx");
            return (Criteria) this;
        }

        public Criteria andCategoryIdxBetween(Long value1, Long value2) {
            addCriterion("category_idx between", value1, value2, "categoryIdx");
            return (Criteria) this;
        }

        public Criteria andCategoryIdxNotBetween(Long value1, Long value2) {
            addCriterion("category_idx not between", value1, value2, "categoryIdx");
            return (Criteria) this;
        }

        public Criteria andSpecificationIsNull() {
            addCriterion("specification is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationIsNotNull() {
            addCriterion("specification is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationEqualTo(String value) {
            addCriterion("specification =", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationNotEqualTo(String value) {
            addCriterion("specification <>", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationGreaterThan(String value) {
            addCriterion("specification >", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationGreaterThanOrEqualTo(String value) {
            addCriterion("specification >=", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationLessThan(String value) {
            addCriterion("specification <", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationLessThanOrEqualTo(String value) {
            addCriterion("specification <=", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationLike(String value) {
            addCriterion("specification like", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationNotLike(String value) {
            addCriterion("specification not like", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationIn(List<String> values) {
            addCriterion("specification in", values, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationNotIn(List<String> values) {
            addCriterion("specification not in", values, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationBetween(String value1, String value2) {
            addCriterion("specification between", value1, value2, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationNotBetween(String value1, String value2) {
            addCriterion("specification not between", value1, value2, "specification");
            return (Criteria) this;
        }

        public Criteria andWarehouseTypeIdxIsNull() {
            addCriterion("warehouse_type_idx is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseTypeIdxIsNotNull() {
            addCriterion("warehouse_type_idx is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseTypeIdxEqualTo(Long value) {
            addCriterion("warehouse_type_idx =", value, "warehouseTypeIdx");
            return (Criteria) this;
        }

        public Criteria andWarehouseTypeIdxNotEqualTo(Long value) {
            addCriterion("warehouse_type_idx <>", value, "warehouseTypeIdx");
            return (Criteria) this;
        }

        public Criteria andWarehouseTypeIdxGreaterThan(Long value) {
            addCriterion("warehouse_type_idx >", value, "warehouseTypeIdx");
            return (Criteria) this;
        }

        public Criteria andWarehouseTypeIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("warehouse_type_idx >=", value, "warehouseTypeIdx");
            return (Criteria) this;
        }

        public Criteria andWarehouseTypeIdxLessThan(Long value) {
            addCriterion("warehouse_type_idx <", value, "warehouseTypeIdx");
            return (Criteria) this;
        }

        public Criteria andWarehouseTypeIdxLessThanOrEqualTo(Long value) {
            addCriterion("warehouse_type_idx <=", value, "warehouseTypeIdx");
            return (Criteria) this;
        }

        public Criteria andWarehouseTypeIdxIn(List<Long> values) {
            addCriterion("warehouse_type_idx in", values, "warehouseTypeIdx");
            return (Criteria) this;
        }

        public Criteria andWarehouseTypeIdxNotIn(List<Long> values) {
            addCriterion("warehouse_type_idx not in", values, "warehouseTypeIdx");
            return (Criteria) this;
        }

        public Criteria andWarehouseTypeIdxBetween(Long value1, Long value2) {
            addCriterion("warehouse_type_idx between", value1, value2, "warehouseTypeIdx");
            return (Criteria) this;
        }

        public Criteria andWarehouseTypeIdxNotBetween(Long value1, Long value2) {
            addCriterion("warehouse_type_idx not between", value1, value2, "warehouseTypeIdx");
            return (Criteria) this;
        }

        public Criteria andBatchIsNull() {
            addCriterion("batch is null");
            return (Criteria) this;
        }

        public Criteria andBatchIsNotNull() {
            addCriterion("batch is not null");
            return (Criteria) this;
        }

        public Criteria andBatchEqualTo(Integer value) {
            addCriterion("batch =", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotEqualTo(Integer value) {
            addCriterion("batch <>", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchGreaterThan(Integer value) {
            addCriterion("batch >", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchGreaterThanOrEqualTo(Integer value) {
            addCriterion("batch >=", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLessThan(Integer value) {
            addCriterion("batch <", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLessThanOrEqualTo(Integer value) {
            addCriterion("batch <=", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchIn(List<Integer> values) {
            addCriterion("batch in", values, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotIn(List<Integer> values) {
            addCriterion("batch not in", values, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchBetween(Integer value1, Integer value2) {
            addCriterion("batch between", value1, value2, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotBetween(Integer value1, Integer value2) {
            addCriterion("batch not between", value1, value2, "batch");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Long value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Long value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Long value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Long value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Long value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Long value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Long> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Long> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Long value1, Long value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Long value1, Long value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkIsNull() {
            addCriterion("collection_remark is null");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkIsNotNull() {
            addCriterion("collection_remark is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkEqualTo(String value) {
            addCriterion("collection_remark =", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkNotEqualTo(String value) {
            addCriterion("collection_remark <>", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkGreaterThan(String value) {
            addCriterion("collection_remark >", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("collection_remark >=", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkLessThan(String value) {
            addCriterion("collection_remark <", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkLessThanOrEqualTo(String value) {
            addCriterion("collection_remark <=", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkLike(String value) {
            addCriterion("collection_remark like", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkNotLike(String value) {
            addCriterion("collection_remark not like", value, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkIn(List<String> values) {
            addCriterion("collection_remark in", values, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkNotIn(List<String> values) {
            addCriterion("collection_remark not in", values, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkBetween(String value1, String value2) {
            addCriterion("collection_remark between", value1, value2, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andCollectionRemarkNotBetween(String value1, String value2) {
            addCriterion("collection_remark not between", value1, value2, "collectionRemark");
            return (Criteria) this;
        }

        public Criteria andImageSmallIsNull() {
            addCriterion("image_small is null");
            return (Criteria) this;
        }

        public Criteria andImageSmallIsNotNull() {
            addCriterion("image_small is not null");
            return (Criteria) this;
        }

        public Criteria andImageSmallEqualTo(String value) {
            addCriterion("image_small =", value, "imageSmall");
            return (Criteria) this;
        }

        public Criteria andImageSmallNotEqualTo(String value) {
            addCriterion("image_small <>", value, "imageSmall");
            return (Criteria) this;
        }

        public Criteria andImageSmallGreaterThan(String value) {
            addCriterion("image_small >", value, "imageSmall");
            return (Criteria) this;
        }

        public Criteria andImageSmallGreaterThanOrEqualTo(String value) {
            addCriterion("image_small >=", value, "imageSmall");
            return (Criteria) this;
        }

        public Criteria andImageSmallLessThan(String value) {
            addCriterion("image_small <", value, "imageSmall");
            return (Criteria) this;
        }

        public Criteria andImageSmallLessThanOrEqualTo(String value) {
            addCriterion("image_small <=", value, "imageSmall");
            return (Criteria) this;
        }

        public Criteria andImageSmallLike(String value) {
            addCriterion("image_small like", value, "imageSmall");
            return (Criteria) this;
        }

        public Criteria andImageSmallNotLike(String value) {
            addCriterion("image_small not like", value, "imageSmall");
            return (Criteria) this;
        }

        public Criteria andImageSmallIn(List<String> values) {
            addCriterion("image_small in", values, "imageSmall");
            return (Criteria) this;
        }

        public Criteria andImageSmallNotIn(List<String> values) {
            addCriterion("image_small not in", values, "imageSmall");
            return (Criteria) this;
        }

        public Criteria andImageSmallBetween(String value1, String value2) {
            addCriterion("image_small between", value1, value2, "imageSmall");
            return (Criteria) this;
        }

        public Criteria andImageSmallNotBetween(String value1, String value2) {
            addCriterion("image_small not between", value1, value2, "imageSmall");
            return (Criteria) this;
        }

        public Criteria andImageMiddleIsNull() {
            addCriterion("image_middle is null");
            return (Criteria) this;
        }

        public Criteria andImageMiddleIsNotNull() {
            addCriterion("image_middle is not null");
            return (Criteria) this;
        }

        public Criteria andImageMiddleEqualTo(String value) {
            addCriterion("image_middle =", value, "imageMiddle");
            return (Criteria) this;
        }

        public Criteria andImageMiddleNotEqualTo(String value) {
            addCriterion("image_middle <>", value, "imageMiddle");
            return (Criteria) this;
        }

        public Criteria andImageMiddleGreaterThan(String value) {
            addCriterion("image_middle >", value, "imageMiddle");
            return (Criteria) this;
        }

        public Criteria andImageMiddleGreaterThanOrEqualTo(String value) {
            addCriterion("image_middle >=", value, "imageMiddle");
            return (Criteria) this;
        }

        public Criteria andImageMiddleLessThan(String value) {
            addCriterion("image_middle <", value, "imageMiddle");
            return (Criteria) this;
        }

        public Criteria andImageMiddleLessThanOrEqualTo(String value) {
            addCriterion("image_middle <=", value, "imageMiddle");
            return (Criteria) this;
        }

        public Criteria andImageMiddleLike(String value) {
            addCriterion("image_middle like", value, "imageMiddle");
            return (Criteria) this;
        }

        public Criteria andImageMiddleNotLike(String value) {
            addCriterion("image_middle not like", value, "imageMiddle");
            return (Criteria) this;
        }

        public Criteria andImageMiddleIn(List<String> values) {
            addCriterion("image_middle in", values, "imageMiddle");
            return (Criteria) this;
        }

        public Criteria andImageMiddleNotIn(List<String> values) {
            addCriterion("image_middle not in", values, "imageMiddle");
            return (Criteria) this;
        }

        public Criteria andImageMiddleBetween(String value1, String value2) {
            addCriterion("image_middle between", value1, value2, "imageMiddle");
            return (Criteria) this;
        }

        public Criteria andImageMiddleNotBetween(String value1, String value2) {
            addCriterion("image_middle not between", value1, value2, "imageMiddle");
            return (Criteria) this;
        }

        public Criteria andImageBigIsNull() {
            addCriterion("image_big is null");
            return (Criteria) this;
        }

        public Criteria andImageBigIsNotNull() {
            addCriterion("image_big is not null");
            return (Criteria) this;
        }

        public Criteria andImageBigEqualTo(String value) {
            addCriterion("image_big =", value, "imageBig");
            return (Criteria) this;
        }

        public Criteria andImageBigNotEqualTo(String value) {
            addCriterion("image_big <>", value, "imageBig");
            return (Criteria) this;
        }

        public Criteria andImageBigGreaterThan(String value) {
            addCriterion("image_big >", value, "imageBig");
            return (Criteria) this;
        }

        public Criteria andImageBigGreaterThanOrEqualTo(String value) {
            addCriterion("image_big >=", value, "imageBig");
            return (Criteria) this;
        }

        public Criteria andImageBigLessThan(String value) {
            addCriterion("image_big <", value, "imageBig");
            return (Criteria) this;
        }

        public Criteria andImageBigLessThanOrEqualTo(String value) {
            addCriterion("image_big <=", value, "imageBig");
            return (Criteria) this;
        }

        public Criteria andImageBigLike(String value) {
            addCriterion("image_big like", value, "imageBig");
            return (Criteria) this;
        }

        public Criteria andImageBigNotLike(String value) {
            addCriterion("image_big not like", value, "imageBig");
            return (Criteria) this;
        }

        public Criteria andImageBigIn(List<String> values) {
            addCriterion("image_big in", values, "imageBig");
            return (Criteria) this;
        }

        public Criteria andImageBigNotIn(List<String> values) {
            addCriterion("image_big not in", values, "imageBig");
            return (Criteria) this;
        }

        public Criteria andImageBigBetween(String value1, String value2) {
            addCriterion("image_big between", value1, value2, "imageBig");
            return (Criteria) this;
        }

        public Criteria andImageBigNotBetween(String value1, String value2) {
            addCriterion("image_big not between", value1, value2, "imageBig");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusIsNull() {
            addCriterion("purchase_status is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusIsNotNull() {
            addCriterion("purchase_status is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusEqualTo(Short value) {
            addCriterion("purchase_status =", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusNotEqualTo(Short value) {
            addCriterion("purchase_status <>", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusGreaterThan(Short value) {
            addCriterion("purchase_status >", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("purchase_status >=", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusLessThan(Short value) {
            addCriterion("purchase_status <", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusLessThanOrEqualTo(Short value) {
            addCriterion("purchase_status <=", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusIn(List<Short> values) {
            addCriterion("purchase_status in", values, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusNotIn(List<Short> values) {
            addCriterion("purchase_status not in", values, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusBetween(Short value1, Short value2) {
            addCriterion("purchase_status between", value1, value2, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusNotBetween(Short value1, Short value2) {
            addCriterion("purchase_status not between", value1, value2, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusIsNull() {
            addCriterion("collection_status is null");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusIsNotNull() {
            addCriterion("collection_status is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusEqualTo(Short value) {
            addCriterion("collection_status =", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusNotEqualTo(Short value) {
            addCriterion("collection_status <>", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusGreaterThan(Short value) {
            addCriterion("collection_status >", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("collection_status >=", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusLessThan(Short value) {
            addCriterion("collection_status <", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusLessThanOrEqualTo(Short value) {
            addCriterion("collection_status <=", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusIn(List<Short> values) {
            addCriterion("collection_status in", values, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusNotIn(List<Short> values) {
            addCriterion("collection_status not in", values, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusBetween(Short value1, Short value2) {
            addCriterion("collection_status between", value1, value2, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusNotBetween(Short value1, Short value2) {
            addCriterion("collection_status not between", value1, value2, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusIsNull() {
            addCriterion("publish_status is null");
            return (Criteria) this;
        }

        public Criteria andPublishStatusIsNotNull() {
            addCriterion("publish_status is not null");
            return (Criteria) this;
        }

        public Criteria andPublishStatusEqualTo(Short value) {
            addCriterion("publish_status =", value, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusNotEqualTo(Short value) {
            addCriterion("publish_status <>", value, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusGreaterThan(Short value) {
            addCriterion("publish_status >", value, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("publish_status >=", value, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusLessThan(Short value) {
            addCriterion("publish_status <", value, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusLessThanOrEqualTo(Short value) {
            addCriterion("publish_status <=", value, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusIn(List<Short> values) {
            addCriterion("publish_status in", values, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusNotIn(List<Short> values) {
            addCriterion("publish_status not in", values, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusBetween(Short value1, Short value2) {
            addCriterion("publish_status between", value1, value2, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusNotBetween(Short value1, Short value2) {
            addCriterion("publish_status not between", value1, value2, "publishStatus");
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