package cc.msonion.carambola.collector.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollectorItemFormalImageLibExample implements MsOnionBaseExampleAdapter {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CollectorItemFormalImageLibExample() {
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

        public Criteria andItemIdxIsNull() {
            addCriterion("item_idx is null");
            return (Criteria) this;
        }

        public Criteria andItemIdxIsNotNull() {
            addCriterion("item_idx is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdxEqualTo(Long value) {
            addCriterion("item_idx =", value, "itemIdx");
            return (Criteria) this;
        }

        public Criteria andItemIdxNotEqualTo(Long value) {
            addCriterion("item_idx <>", value, "itemIdx");
            return (Criteria) this;
        }

        public Criteria andItemIdxGreaterThan(Long value) {
            addCriterion("item_idx >", value, "itemIdx");
            return (Criteria) this;
        }

        public Criteria andItemIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("item_idx >=", value, "itemIdx");
            return (Criteria) this;
        }

        public Criteria andItemIdxLessThan(Long value) {
            addCriterion("item_idx <", value, "itemIdx");
            return (Criteria) this;
        }

        public Criteria andItemIdxLessThanOrEqualTo(Long value) {
            addCriterion("item_idx <=", value, "itemIdx");
            return (Criteria) this;
        }

        public Criteria andItemIdxIn(List<Long> values) {
            addCriterion("item_idx in", values, "itemIdx");
            return (Criteria) this;
        }

        public Criteria andItemIdxNotIn(List<Long> values) {
            addCriterion("item_idx not in", values, "itemIdx");
            return (Criteria) this;
        }

        public Criteria andItemIdxBetween(Long value1, Long value2) {
            addCriterion("item_idx between", value1, value2, "itemIdx");
            return (Criteria) this;
        }

        public Criteria andItemIdxNotBetween(Long value1, Long value2) {
            addCriterion("item_idx not between", value1, value2, "itemIdx");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallIsNull() {
            addCriterion("white_background_image_small is null");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallIsNotNull() {
            addCriterion("white_background_image_small is not null");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallEqualTo(String value) {
            addCriterion("white_background_image_small =", value, "whiteBackgroundImageSmall");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallNotEqualTo(String value) {
            addCriterion("white_background_image_small <>", value, "whiteBackgroundImageSmall");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallGreaterThan(String value) {
            addCriterion("white_background_image_small >", value, "whiteBackgroundImageSmall");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallGreaterThanOrEqualTo(String value) {
            addCriterion("white_background_image_small >=", value, "whiteBackgroundImageSmall");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallLessThan(String value) {
            addCriterion("white_background_image_small <", value, "whiteBackgroundImageSmall");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallLessThanOrEqualTo(String value) {
            addCriterion("white_background_image_small <=", value, "whiteBackgroundImageSmall");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallLike(String value) {
            addCriterion("white_background_image_small like", value, "whiteBackgroundImageSmall");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallNotLike(String value) {
            addCriterion("white_background_image_small not like", value, "whiteBackgroundImageSmall");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallIn(List<String> values) {
            addCriterion("white_background_image_small in", values, "whiteBackgroundImageSmall");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallNotIn(List<String> values) {
            addCriterion("white_background_image_small not in", values, "whiteBackgroundImageSmall");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallBetween(String value1, String value2) {
            addCriterion("white_background_image_small between", value1, value2, "whiteBackgroundImageSmall");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageSmallNotBetween(String value1, String value2) {
            addCriterion("white_background_image_small not between", value1, value2, "whiteBackgroundImageSmall");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleIsNull() {
            addCriterion("white_background_image_middle is null");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleIsNotNull() {
            addCriterion("white_background_image_middle is not null");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleEqualTo(String value) {
            addCriterion("white_background_image_middle =", value, "whiteBackgroundImageMiddle");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleNotEqualTo(String value) {
            addCriterion("white_background_image_middle <>", value, "whiteBackgroundImageMiddle");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleGreaterThan(String value) {
            addCriterion("white_background_image_middle >", value, "whiteBackgroundImageMiddle");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleGreaterThanOrEqualTo(String value) {
            addCriterion("white_background_image_middle >=", value, "whiteBackgroundImageMiddle");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleLessThan(String value) {
            addCriterion("white_background_image_middle <", value, "whiteBackgroundImageMiddle");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleLessThanOrEqualTo(String value) {
            addCriterion("white_background_image_middle <=", value, "whiteBackgroundImageMiddle");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleLike(String value) {
            addCriterion("white_background_image_middle like", value, "whiteBackgroundImageMiddle");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleNotLike(String value) {
            addCriterion("white_background_image_middle not like", value, "whiteBackgroundImageMiddle");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleIn(List<String> values) {
            addCriterion("white_background_image_middle in", values, "whiteBackgroundImageMiddle");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleNotIn(List<String> values) {
            addCriterion("white_background_image_middle not in", values, "whiteBackgroundImageMiddle");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleBetween(String value1, String value2) {
            addCriterion("white_background_image_middle between", value1, value2, "whiteBackgroundImageMiddle");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageMiddleNotBetween(String value1, String value2) {
            addCriterion("white_background_image_middle not between", value1, value2, "whiteBackgroundImageMiddle");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigIsNull() {
            addCriterion("white_background_image_big is null");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigIsNotNull() {
            addCriterion("white_background_image_big is not null");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigEqualTo(String value) {
            addCriterion("white_background_image_big =", value, "whiteBackgroundImageBig");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigNotEqualTo(String value) {
            addCriterion("white_background_image_big <>", value, "whiteBackgroundImageBig");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigGreaterThan(String value) {
            addCriterion("white_background_image_big >", value, "whiteBackgroundImageBig");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigGreaterThanOrEqualTo(String value) {
            addCriterion("white_background_image_big >=", value, "whiteBackgroundImageBig");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigLessThan(String value) {
            addCriterion("white_background_image_big <", value, "whiteBackgroundImageBig");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigLessThanOrEqualTo(String value) {
            addCriterion("white_background_image_big <=", value, "whiteBackgroundImageBig");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigLike(String value) {
            addCriterion("white_background_image_big like", value, "whiteBackgroundImageBig");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigNotLike(String value) {
            addCriterion("white_background_image_big not like", value, "whiteBackgroundImageBig");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigIn(List<String> values) {
            addCriterion("white_background_image_big in", values, "whiteBackgroundImageBig");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigNotIn(List<String> values) {
            addCriterion("white_background_image_big not in", values, "whiteBackgroundImageBig");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigBetween(String value1, String value2) {
            addCriterion("white_background_image_big between", value1, value2, "whiteBackgroundImageBig");
            return (Criteria) this;
        }

        public Criteria andWhiteBackgroundImageBigNotBetween(String value1, String value2) {
            addCriterion("white_background_image_big not between", value1, value2, "whiteBackgroundImageBig");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallIsNull() {
            addCriterion("main_push_image_small is null");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallIsNotNull() {
            addCriterion("main_push_image_small is not null");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallEqualTo(String value) {
            addCriterion("main_push_image_small =", value, "mainPushImageSmall");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallNotEqualTo(String value) {
            addCriterion("main_push_image_small <>", value, "mainPushImageSmall");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallGreaterThan(String value) {
            addCriterion("main_push_image_small >", value, "mainPushImageSmall");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallGreaterThanOrEqualTo(String value) {
            addCriterion("main_push_image_small >=", value, "mainPushImageSmall");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallLessThan(String value) {
            addCriterion("main_push_image_small <", value, "mainPushImageSmall");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallLessThanOrEqualTo(String value) {
            addCriterion("main_push_image_small <=", value, "mainPushImageSmall");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallLike(String value) {
            addCriterion("main_push_image_small like", value, "mainPushImageSmall");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallNotLike(String value) {
            addCriterion("main_push_image_small not like", value, "mainPushImageSmall");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallIn(List<String> values) {
            addCriterion("main_push_image_small in", values, "mainPushImageSmall");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallNotIn(List<String> values) {
            addCriterion("main_push_image_small not in", values, "mainPushImageSmall");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallBetween(String value1, String value2) {
            addCriterion("main_push_image_small between", value1, value2, "mainPushImageSmall");
            return (Criteria) this;
        }

        public Criteria andMainPushImageSmallNotBetween(String value1, String value2) {
            addCriterion("main_push_image_small not between", value1, value2, "mainPushImageSmall");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleIsNull() {
            addCriterion("main_push_image_middle is null");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleIsNotNull() {
            addCriterion("main_push_image_middle is not null");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleEqualTo(String value) {
            addCriterion("main_push_image_middle =", value, "mainPushImageMiddle");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleNotEqualTo(String value) {
            addCriterion("main_push_image_middle <>", value, "mainPushImageMiddle");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleGreaterThan(String value) {
            addCriterion("main_push_image_middle >", value, "mainPushImageMiddle");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleGreaterThanOrEqualTo(String value) {
            addCriterion("main_push_image_middle >=", value, "mainPushImageMiddle");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleLessThan(String value) {
            addCriterion("main_push_image_middle <", value, "mainPushImageMiddle");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleLessThanOrEqualTo(String value) {
            addCriterion("main_push_image_middle <=", value, "mainPushImageMiddle");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleLike(String value) {
            addCriterion("main_push_image_middle like", value, "mainPushImageMiddle");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleNotLike(String value) {
            addCriterion("main_push_image_middle not like", value, "mainPushImageMiddle");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleIn(List<String> values) {
            addCriterion("main_push_image_middle in", values, "mainPushImageMiddle");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleNotIn(List<String> values) {
            addCriterion("main_push_image_middle not in", values, "mainPushImageMiddle");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleBetween(String value1, String value2) {
            addCriterion("main_push_image_middle between", value1, value2, "mainPushImageMiddle");
            return (Criteria) this;
        }

        public Criteria andMainPushImageMiddleNotBetween(String value1, String value2) {
            addCriterion("main_push_image_middle not between", value1, value2, "mainPushImageMiddle");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigIsNull() {
            addCriterion("main_push_image_big is null");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigIsNotNull() {
            addCriterion("main_push_image_big is not null");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigEqualTo(String value) {
            addCriterion("main_push_image_big =", value, "mainPushImageBig");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigNotEqualTo(String value) {
            addCriterion("main_push_image_big <>", value, "mainPushImageBig");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigGreaterThan(String value) {
            addCriterion("main_push_image_big >", value, "mainPushImageBig");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigGreaterThanOrEqualTo(String value) {
            addCriterion("main_push_image_big >=", value, "mainPushImageBig");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigLessThan(String value) {
            addCriterion("main_push_image_big <", value, "mainPushImageBig");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigLessThanOrEqualTo(String value) {
            addCriterion("main_push_image_big <=", value, "mainPushImageBig");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigLike(String value) {
            addCriterion("main_push_image_big like", value, "mainPushImageBig");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigNotLike(String value) {
            addCriterion("main_push_image_big not like", value, "mainPushImageBig");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigIn(List<String> values) {
            addCriterion("main_push_image_big in", values, "mainPushImageBig");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigNotIn(List<String> values) {
            addCriterion("main_push_image_big not in", values, "mainPushImageBig");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigBetween(String value1, String value2) {
            addCriterion("main_push_image_big between", value1, value2, "mainPushImageBig");
            return (Criteria) this;
        }

        public Criteria andMainPushImageBigNotBetween(String value1, String value2) {
            addCriterion("main_push_image_big not between", value1, value2, "mainPushImageBig");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallIsNull() {
            addCriterion("detail_page_main_image_small is null");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallIsNotNull() {
            addCriterion("detail_page_main_image_small is not null");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallEqualTo(String value) {
            addCriterion("detail_page_main_image_small =", value, "detailPageMainImageSmall");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallNotEqualTo(String value) {
            addCriterion("detail_page_main_image_small <>", value, "detailPageMainImageSmall");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallGreaterThan(String value) {
            addCriterion("detail_page_main_image_small >", value, "detailPageMainImageSmall");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallGreaterThanOrEqualTo(String value) {
            addCriterion("detail_page_main_image_small >=", value, "detailPageMainImageSmall");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallLessThan(String value) {
            addCriterion("detail_page_main_image_small <", value, "detailPageMainImageSmall");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallLessThanOrEqualTo(String value) {
            addCriterion("detail_page_main_image_small <=", value, "detailPageMainImageSmall");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallLike(String value) {
            addCriterion("detail_page_main_image_small like", value, "detailPageMainImageSmall");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallNotLike(String value) {
            addCriterion("detail_page_main_image_small not like", value, "detailPageMainImageSmall");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallIn(List<String> values) {
            addCriterion("detail_page_main_image_small in", values, "detailPageMainImageSmall");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallNotIn(List<String> values) {
            addCriterion("detail_page_main_image_small not in", values, "detailPageMainImageSmall");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallBetween(String value1, String value2) {
            addCriterion("detail_page_main_image_small between", value1, value2, "detailPageMainImageSmall");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageSmallNotBetween(String value1, String value2) {
            addCriterion("detail_page_main_image_small not between", value1, value2, "detailPageMainImageSmall");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleIsNull() {
            addCriterion("detail_page_main_image_middle is null");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleIsNotNull() {
            addCriterion("detail_page_main_image_middle is not null");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleEqualTo(String value) {
            addCriterion("detail_page_main_image_middle =", value, "detailPageMainImageMiddle");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleNotEqualTo(String value) {
            addCriterion("detail_page_main_image_middle <>", value, "detailPageMainImageMiddle");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleGreaterThan(String value) {
            addCriterion("detail_page_main_image_middle >", value, "detailPageMainImageMiddle");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleGreaterThanOrEqualTo(String value) {
            addCriterion("detail_page_main_image_middle >=", value, "detailPageMainImageMiddle");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleLessThan(String value) {
            addCriterion("detail_page_main_image_middle <", value, "detailPageMainImageMiddle");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleLessThanOrEqualTo(String value) {
            addCriterion("detail_page_main_image_middle <=", value, "detailPageMainImageMiddle");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleLike(String value) {
            addCriterion("detail_page_main_image_middle like", value, "detailPageMainImageMiddle");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleNotLike(String value) {
            addCriterion("detail_page_main_image_middle not like", value, "detailPageMainImageMiddle");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleIn(List<String> values) {
            addCriterion("detail_page_main_image_middle in", values, "detailPageMainImageMiddle");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleNotIn(List<String> values) {
            addCriterion("detail_page_main_image_middle not in", values, "detailPageMainImageMiddle");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleBetween(String value1, String value2) {
            addCriterion("detail_page_main_image_middle between", value1, value2, "detailPageMainImageMiddle");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageMiddleNotBetween(String value1, String value2) {
            addCriterion("detail_page_main_image_middle not between", value1, value2, "detailPageMainImageMiddle");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigIsNull() {
            addCriterion("detail_page_main_image_big is null");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigIsNotNull() {
            addCriterion("detail_page_main_image_big is not null");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigEqualTo(String value) {
            addCriterion("detail_page_main_image_big =", value, "detailPageMainImageBig");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigNotEqualTo(String value) {
            addCriterion("detail_page_main_image_big <>", value, "detailPageMainImageBig");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigGreaterThan(String value) {
            addCriterion("detail_page_main_image_big >", value, "detailPageMainImageBig");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigGreaterThanOrEqualTo(String value) {
            addCriterion("detail_page_main_image_big >=", value, "detailPageMainImageBig");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigLessThan(String value) {
            addCriterion("detail_page_main_image_big <", value, "detailPageMainImageBig");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigLessThanOrEqualTo(String value) {
            addCriterion("detail_page_main_image_big <=", value, "detailPageMainImageBig");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigLike(String value) {
            addCriterion("detail_page_main_image_big like", value, "detailPageMainImageBig");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigNotLike(String value) {
            addCriterion("detail_page_main_image_big not like", value, "detailPageMainImageBig");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigIn(List<String> values) {
            addCriterion("detail_page_main_image_big in", values, "detailPageMainImageBig");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigNotIn(List<String> values) {
            addCriterion("detail_page_main_image_big not in", values, "detailPageMainImageBig");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigBetween(String value1, String value2) {
            addCriterion("detail_page_main_image_big between", value1, value2, "detailPageMainImageBig");
            return (Criteria) this;
        }

        public Criteria andDetailPageMainImageBigNotBetween(String value1, String value2) {
            addCriterion("detail_page_main_image_big not between", value1, value2, "detailPageMainImageBig");
            return (Criteria) this;
        }

        public Criteria andContentInfoIsNull() {
            addCriterion("content_info is null");
            return (Criteria) this;
        }

        public Criteria andContentInfoIsNotNull() {
            addCriterion("content_info is not null");
            return (Criteria) this;
        }

        public Criteria andContentInfoEqualTo(String value) {
            addCriterion("content_info =", value, "contentInfo");
            return (Criteria) this;
        }

        public Criteria andContentInfoNotEqualTo(String value) {
            addCriterion("content_info <>", value, "contentInfo");
            return (Criteria) this;
        }

        public Criteria andContentInfoGreaterThan(String value) {
            addCriterion("content_info >", value, "contentInfo");
            return (Criteria) this;
        }

        public Criteria andContentInfoGreaterThanOrEqualTo(String value) {
            addCriterion("content_info >=", value, "contentInfo");
            return (Criteria) this;
        }

        public Criteria andContentInfoLessThan(String value) {
            addCriterion("content_info <", value, "contentInfo");
            return (Criteria) this;
        }

        public Criteria andContentInfoLessThanOrEqualTo(String value) {
            addCriterion("content_info <=", value, "contentInfo");
            return (Criteria) this;
        }

        public Criteria andContentInfoLike(String value) {
            addCriterion("content_info like", value, "contentInfo");
            return (Criteria) this;
        }

        public Criteria andContentInfoNotLike(String value) {
            addCriterion("content_info not like", value, "contentInfo");
            return (Criteria) this;
        }

        public Criteria andContentInfoIn(List<String> values) {
            addCriterion("content_info in", values, "contentInfo");
            return (Criteria) this;
        }

        public Criteria andContentInfoNotIn(List<String> values) {
            addCriterion("content_info not in", values, "contentInfo");
            return (Criteria) this;
        }

        public Criteria andContentInfoBetween(String value1, String value2) {
            addCriterion("content_info between", value1, value2, "contentInfo");
            return (Criteria) this;
        }

        public Criteria andContentInfoNotBetween(String value1, String value2) {
            addCriterion("content_info not between", value1, value2, "contentInfo");
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