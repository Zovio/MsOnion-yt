package cc.msonion.carambola.collector.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollectorItemOriginExample implements MsOnionBaseExampleAdapter {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CollectorItemOriginExample() {
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

        public Criteria andOriginCodeIsNull() {
            addCriterion("origin_code is null");
            return (Criteria) this;
        }

        public Criteria andOriginCodeIsNotNull() {
            addCriterion("origin_code is not null");
            return (Criteria) this;
        }

        public Criteria andOriginCodeEqualTo(String value) {
            addCriterion("origin_code =", value, "originCode");
            return (Criteria) this;
        }

        public Criteria andOriginCodeNotEqualTo(String value) {
            addCriterion("origin_code <>", value, "originCode");
            return (Criteria) this;
        }

        public Criteria andOriginCodeGreaterThan(String value) {
            addCriterion("origin_code >", value, "originCode");
            return (Criteria) this;
        }

        public Criteria andOriginCodeGreaterThanOrEqualTo(String value) {
            addCriterion("origin_code >=", value, "originCode");
            return (Criteria) this;
        }

        public Criteria andOriginCodeLessThan(String value) {
            addCriterion("origin_code <", value, "originCode");
            return (Criteria) this;
        }

        public Criteria andOriginCodeLessThanOrEqualTo(String value) {
            addCriterion("origin_code <=", value, "originCode");
            return (Criteria) this;
        }

        public Criteria andOriginCodeLike(String value) {
            addCriterion("origin_code like", value, "originCode");
            return (Criteria) this;
        }

        public Criteria andOriginCodeNotLike(String value) {
            addCriterion("origin_code not like", value, "originCode");
            return (Criteria) this;
        }

        public Criteria andOriginCodeIn(List<String> values) {
            addCriterion("origin_code in", values, "originCode");
            return (Criteria) this;
        }

        public Criteria andOriginCodeNotIn(List<String> values) {
            addCriterion("origin_code not in", values, "originCode");
            return (Criteria) this;
        }

        public Criteria andOriginCodeBetween(String value1, String value2) {
            addCriterion("origin_code between", value1, value2, "originCode");
            return (Criteria) this;
        }

        public Criteria andOriginCodeNotBetween(String value1, String value2) {
            addCriterion("origin_code not between", value1, value2, "originCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeIsNull() {
            addCriterion("customs_code is null");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeIsNotNull() {
            addCriterion("customs_code is not null");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeEqualTo(String value) {
            addCriterion("customs_code =", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeNotEqualTo(String value) {
            addCriterion("customs_code <>", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeGreaterThan(String value) {
            addCriterion("customs_code >", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("customs_code >=", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeLessThan(String value) {
            addCriterion("customs_code <", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeLessThanOrEqualTo(String value) {
            addCriterion("customs_code <=", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeLike(String value) {
            addCriterion("customs_code like", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeNotLike(String value) {
            addCriterion("customs_code not like", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeIn(List<String> values) {
            addCriterion("customs_code in", values, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeNotIn(List<String> values) {
            addCriterion("customs_code not in", values, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeBetween(String value1, String value2) {
            addCriterion("customs_code between", value1, value2, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeNotBetween(String value1, String value2) {
            addCriterion("customs_code not between", value1, value2, "customsCode");
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

        public Criteria andNationalFlagImageSmallIsNull() {
            addCriterion("national_flag_image_small is null");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallIsNotNull() {
            addCriterion("national_flag_image_small is not null");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallEqualTo(String value) {
            addCriterion("national_flag_image_small =", value, "nationalFlagImageSmall");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallNotEqualTo(String value) {
            addCriterion("national_flag_image_small <>", value, "nationalFlagImageSmall");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallGreaterThan(String value) {
            addCriterion("national_flag_image_small >", value, "nationalFlagImageSmall");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallGreaterThanOrEqualTo(String value) {
            addCriterion("national_flag_image_small >=", value, "nationalFlagImageSmall");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallLessThan(String value) {
            addCriterion("national_flag_image_small <", value, "nationalFlagImageSmall");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallLessThanOrEqualTo(String value) {
            addCriterion("national_flag_image_small <=", value, "nationalFlagImageSmall");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallLike(String value) {
            addCriterion("national_flag_image_small like", value, "nationalFlagImageSmall");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallNotLike(String value) {
            addCriterion("national_flag_image_small not like", value, "nationalFlagImageSmall");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallIn(List<String> values) {
            addCriterion("national_flag_image_small in", values, "nationalFlagImageSmall");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallNotIn(List<String> values) {
            addCriterion("national_flag_image_small not in", values, "nationalFlagImageSmall");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallBetween(String value1, String value2) {
            addCriterion("national_flag_image_small between", value1, value2, "nationalFlagImageSmall");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageSmallNotBetween(String value1, String value2) {
            addCriterion("national_flag_image_small not between", value1, value2, "nationalFlagImageSmall");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleIsNull() {
            addCriterion("national_flag_image_middle is null");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleIsNotNull() {
            addCriterion("national_flag_image_middle is not null");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleEqualTo(String value) {
            addCriterion("national_flag_image_middle =", value, "nationalFlagImageMiddle");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleNotEqualTo(String value) {
            addCriterion("national_flag_image_middle <>", value, "nationalFlagImageMiddle");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleGreaterThan(String value) {
            addCriterion("national_flag_image_middle >", value, "nationalFlagImageMiddle");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleGreaterThanOrEqualTo(String value) {
            addCriterion("national_flag_image_middle >=", value, "nationalFlagImageMiddle");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleLessThan(String value) {
            addCriterion("national_flag_image_middle <", value, "nationalFlagImageMiddle");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleLessThanOrEqualTo(String value) {
            addCriterion("national_flag_image_middle <=", value, "nationalFlagImageMiddle");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleLike(String value) {
            addCriterion("national_flag_image_middle like", value, "nationalFlagImageMiddle");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleNotLike(String value) {
            addCriterion("national_flag_image_middle not like", value, "nationalFlagImageMiddle");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleIn(List<String> values) {
            addCriterion("national_flag_image_middle in", values, "nationalFlagImageMiddle");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleNotIn(List<String> values) {
            addCriterion("national_flag_image_middle not in", values, "nationalFlagImageMiddle");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleBetween(String value1, String value2) {
            addCriterion("national_flag_image_middle between", value1, value2, "nationalFlagImageMiddle");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageMiddleNotBetween(String value1, String value2) {
            addCriterion("national_flag_image_middle not between", value1, value2, "nationalFlagImageMiddle");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigIsNull() {
            addCriterion("national_flag_image_big is null");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigIsNotNull() {
            addCriterion("national_flag_image_big is not null");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigEqualTo(String value) {
            addCriterion("national_flag_image_big =", value, "nationalFlagImageBig");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigNotEqualTo(String value) {
            addCriterion("national_flag_image_big <>", value, "nationalFlagImageBig");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigGreaterThan(String value) {
            addCriterion("national_flag_image_big >", value, "nationalFlagImageBig");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigGreaterThanOrEqualTo(String value) {
            addCriterion("national_flag_image_big >=", value, "nationalFlagImageBig");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigLessThan(String value) {
            addCriterion("national_flag_image_big <", value, "nationalFlagImageBig");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigLessThanOrEqualTo(String value) {
            addCriterion("national_flag_image_big <=", value, "nationalFlagImageBig");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigLike(String value) {
            addCriterion("national_flag_image_big like", value, "nationalFlagImageBig");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigNotLike(String value) {
            addCriterion("national_flag_image_big not like", value, "nationalFlagImageBig");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigIn(List<String> values) {
            addCriterion("national_flag_image_big in", values, "nationalFlagImageBig");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigNotIn(List<String> values) {
            addCriterion("national_flag_image_big not in", values, "nationalFlagImageBig");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigBetween(String value1, String value2) {
            addCriterion("national_flag_image_big between", value1, value2, "nationalFlagImageBig");
            return (Criteria) this;
        }

        public Criteria andNationalFlagImageBigNotBetween(String value1, String value2) {
            addCriterion("national_flag_image_big not between", value1, value2, "nationalFlagImageBig");
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