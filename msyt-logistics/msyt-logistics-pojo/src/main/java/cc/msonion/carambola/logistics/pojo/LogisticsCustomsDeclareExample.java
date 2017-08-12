package cc.msonion.carambola.logistics.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogisticsCustomsDeclareExample implements MsOnionBaseExampleAdapter {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public LogisticsCustomsDeclareExample() {
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

        public Criteria andCustomNameIsNull() {
            addCriterion("custom_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomNameIsNotNull() {
            addCriterion("custom_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomNameEqualTo(String value) {
            addCriterion("custom_name =", value, "customName");
            return (Criteria) this;
        }

        public Criteria andCustomNameNotEqualTo(String value) {
            addCriterion("custom_name <>", value, "customName");
            return (Criteria) this;
        }

        public Criteria andCustomNameGreaterThan(String value) {
            addCriterion("custom_name >", value, "customName");
            return (Criteria) this;
        }

        public Criteria andCustomNameGreaterThanOrEqualTo(String value) {
            addCriterion("custom_name >=", value, "customName");
            return (Criteria) this;
        }

        public Criteria andCustomNameLessThan(String value) {
            addCriterion("custom_name <", value, "customName");
            return (Criteria) this;
        }

        public Criteria andCustomNameLessThanOrEqualTo(String value) {
            addCriterion("custom_name <=", value, "customName");
            return (Criteria) this;
        }

        public Criteria andCustomNameLike(String value) {
            addCriterion("custom_name like", value, "customName");
            return (Criteria) this;
        }

        public Criteria andCustomNameNotLike(String value) {
            addCriterion("custom_name not like", value, "customName");
            return (Criteria) this;
        }

        public Criteria andCustomNameIn(List<String> values) {
            addCriterion("custom_name in", values, "customName");
            return (Criteria) this;
        }

        public Criteria andCustomNameNotIn(List<String> values) {
            addCriterion("custom_name not in", values, "customName");
            return (Criteria) this;
        }

        public Criteria andCustomNameBetween(String value1, String value2) {
            addCriterion("custom_name between", value1, value2, "customName");
            return (Criteria) this;
        }

        public Criteria andCustomNameNotBetween(String value1, String value2) {
            addCriterion("custom_name not between", value1, value2, "customName");
            return (Criteria) this;
        }

        public Criteria andFirstQuantityIsNull() {
            addCriterion("first_quantity is null");
            return (Criteria) this;
        }

        public Criteria andFirstQuantityIsNotNull() {
            addCriterion("first_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andFirstQuantityEqualTo(Integer value) {
            addCriterion("first_quantity =", value, "firstQuantity");
            return (Criteria) this;
        }

        public Criteria andFirstQuantityNotEqualTo(Integer value) {
            addCriterion("first_quantity <>", value, "firstQuantity");
            return (Criteria) this;
        }

        public Criteria andFirstQuantityGreaterThan(Integer value) {
            addCriterion("first_quantity >", value, "firstQuantity");
            return (Criteria) this;
        }

        public Criteria andFirstQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_quantity >=", value, "firstQuantity");
            return (Criteria) this;
        }

        public Criteria andFirstQuantityLessThan(Integer value) {
            addCriterion("first_quantity <", value, "firstQuantity");
            return (Criteria) this;
        }

        public Criteria andFirstQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("first_quantity <=", value, "firstQuantity");
            return (Criteria) this;
        }

        public Criteria andFirstQuantityIn(List<Integer> values) {
            addCriterion("first_quantity in", values, "firstQuantity");
            return (Criteria) this;
        }

        public Criteria andFirstQuantityNotIn(List<Integer> values) {
            addCriterion("first_quantity not in", values, "firstQuantity");
            return (Criteria) this;
        }

        public Criteria andFirstQuantityBetween(Integer value1, Integer value2) {
            addCriterion("first_quantity between", value1, value2, "firstQuantity");
            return (Criteria) this;
        }

        public Criteria andFirstQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("first_quantity not between", value1, value2, "firstQuantity");
            return (Criteria) this;
        }

        public Criteria andSecondQuantityIsNull() {
            addCriterion("second_quantity is null");
            return (Criteria) this;
        }

        public Criteria andSecondQuantityIsNotNull() {
            addCriterion("second_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andSecondQuantityEqualTo(Integer value) {
            addCriterion("second_quantity =", value, "secondQuantity");
            return (Criteria) this;
        }

        public Criteria andSecondQuantityNotEqualTo(Integer value) {
            addCriterion("second_quantity <>", value, "secondQuantity");
            return (Criteria) this;
        }

        public Criteria andSecondQuantityGreaterThan(Integer value) {
            addCriterion("second_quantity >", value, "secondQuantity");
            return (Criteria) this;
        }

        public Criteria andSecondQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("second_quantity >=", value, "secondQuantity");
            return (Criteria) this;
        }

        public Criteria andSecondQuantityLessThan(Integer value) {
            addCriterion("second_quantity <", value, "secondQuantity");
            return (Criteria) this;
        }

        public Criteria andSecondQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("second_quantity <=", value, "secondQuantity");
            return (Criteria) this;
        }

        public Criteria andSecondQuantityIn(List<Integer> values) {
            addCriterion("second_quantity in", values, "secondQuantity");
            return (Criteria) this;
        }

        public Criteria andSecondQuantityNotIn(List<Integer> values) {
            addCriterion("second_quantity not in", values, "secondQuantity");
            return (Criteria) this;
        }

        public Criteria andSecondQuantityBetween(Integer value1, Integer value2) {
            addCriterion("second_quantity between", value1, value2, "secondQuantity");
            return (Criteria) this;
        }

        public Criteria andSecondQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("second_quantity not between", value1, value2, "secondQuantity");
            return (Criteria) this;
        }

        public Criteria andFirstUnitIdxIsNull() {
            addCriterion("first_unit_idx is null");
            return (Criteria) this;
        }

        public Criteria andFirstUnitIdxIsNotNull() {
            addCriterion("first_unit_idx is not null");
            return (Criteria) this;
        }

        public Criteria andFirstUnitIdxEqualTo(Long value) {
            addCriterion("first_unit_idx =", value, "firstUnitIdx");
            return (Criteria) this;
        }

        public Criteria andFirstUnitIdxNotEqualTo(Long value) {
            addCriterion("first_unit_idx <>", value, "firstUnitIdx");
            return (Criteria) this;
        }

        public Criteria andFirstUnitIdxGreaterThan(Long value) {
            addCriterion("first_unit_idx >", value, "firstUnitIdx");
            return (Criteria) this;
        }

        public Criteria andFirstUnitIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("first_unit_idx >=", value, "firstUnitIdx");
            return (Criteria) this;
        }

        public Criteria andFirstUnitIdxLessThan(Long value) {
            addCriterion("first_unit_idx <", value, "firstUnitIdx");
            return (Criteria) this;
        }

        public Criteria andFirstUnitIdxLessThanOrEqualTo(Long value) {
            addCriterion("first_unit_idx <=", value, "firstUnitIdx");
            return (Criteria) this;
        }

        public Criteria andFirstUnitIdxIn(List<Long> values) {
            addCriterion("first_unit_idx in", values, "firstUnitIdx");
            return (Criteria) this;
        }

        public Criteria andFirstUnitIdxNotIn(List<Long> values) {
            addCriterion("first_unit_idx not in", values, "firstUnitIdx");
            return (Criteria) this;
        }

        public Criteria andFirstUnitIdxBetween(Long value1, Long value2) {
            addCriterion("first_unit_idx between", value1, value2, "firstUnitIdx");
            return (Criteria) this;
        }

        public Criteria andFirstUnitIdxNotBetween(Long value1, Long value2) {
            addCriterion("first_unit_idx not between", value1, value2, "firstUnitIdx");
            return (Criteria) this;
        }

        public Criteria andSecondUnitIdxIsNull() {
            addCriterion("second_unit_idx is null");
            return (Criteria) this;
        }

        public Criteria andSecondUnitIdxIsNotNull() {
            addCriterion("second_unit_idx is not null");
            return (Criteria) this;
        }

        public Criteria andSecondUnitIdxEqualTo(Long value) {
            addCriterion("second_unit_idx =", value, "secondUnitIdx");
            return (Criteria) this;
        }

        public Criteria andSecondUnitIdxNotEqualTo(Long value) {
            addCriterion("second_unit_idx <>", value, "secondUnitIdx");
            return (Criteria) this;
        }

        public Criteria andSecondUnitIdxGreaterThan(Long value) {
            addCriterion("second_unit_idx >", value, "secondUnitIdx");
            return (Criteria) this;
        }

        public Criteria andSecondUnitIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("second_unit_idx >=", value, "secondUnitIdx");
            return (Criteria) this;
        }

        public Criteria andSecondUnitIdxLessThan(Long value) {
            addCriterion("second_unit_idx <", value, "secondUnitIdx");
            return (Criteria) this;
        }

        public Criteria andSecondUnitIdxLessThanOrEqualTo(Long value) {
            addCriterion("second_unit_idx <=", value, "secondUnitIdx");
            return (Criteria) this;
        }

        public Criteria andSecondUnitIdxIn(List<Long> values) {
            addCriterion("second_unit_idx in", values, "secondUnitIdx");
            return (Criteria) this;
        }

        public Criteria andSecondUnitIdxNotIn(List<Long> values) {
            addCriterion("second_unit_idx not in", values, "secondUnitIdx");
            return (Criteria) this;
        }

        public Criteria andSecondUnitIdxBetween(Long value1, Long value2) {
            addCriterion("second_unit_idx between", value1, value2, "secondUnitIdx");
            return (Criteria) this;
        }

        public Criteria andSecondUnitIdxNotBetween(Long value1, Long value2) {
            addCriterion("second_unit_idx not between", value1, value2, "secondUnitIdx");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxRateIsNull() {
            addCriterion("postal_articles_tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxRateIsNotNull() {
            addCriterion("postal_articles_tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxRateEqualTo(Integer value) {
            addCriterion("postal_articles_tax_rate =", value, "postalArticlesTaxRate");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxRateNotEqualTo(Integer value) {
            addCriterion("postal_articles_tax_rate <>", value, "postalArticlesTaxRate");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxRateGreaterThan(Integer value) {
            addCriterion("postal_articles_tax_rate >", value, "postalArticlesTaxRate");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("postal_articles_tax_rate >=", value, "postalArticlesTaxRate");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxRateLessThan(Integer value) {
            addCriterion("postal_articles_tax_rate <", value, "postalArticlesTaxRate");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxRateLessThanOrEqualTo(Integer value) {
            addCriterion("postal_articles_tax_rate <=", value, "postalArticlesTaxRate");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxRateIn(List<Integer> values) {
            addCriterion("postal_articles_tax_rate in", values, "postalArticlesTaxRate");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxRateNotIn(List<Integer> values) {
            addCriterion("postal_articles_tax_rate not in", values, "postalArticlesTaxRate");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxRateBetween(Integer value1, Integer value2) {
            addCriterion("postal_articles_tax_rate between", value1, value2, "postalArticlesTaxRate");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxRateNotBetween(Integer value1, Integer value2) {
            addCriterion("postal_articles_tax_rate not between", value1, value2, "postalArticlesTaxRate");
            return (Criteria) this;
        }

        public Criteria andCrossBorderTaxRateIsNull() {
            addCriterion("cross_border_tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andCrossBorderTaxRateIsNotNull() {
            addCriterion("cross_border_tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCrossBorderTaxRateEqualTo(Integer value) {
            addCriterion("cross_border_tax_rate =", value, "crossBorderTaxRate");
            return (Criteria) this;
        }

        public Criteria andCrossBorderTaxRateNotEqualTo(Integer value) {
            addCriterion("cross_border_tax_rate <>", value, "crossBorderTaxRate");
            return (Criteria) this;
        }

        public Criteria andCrossBorderTaxRateGreaterThan(Integer value) {
            addCriterion("cross_border_tax_rate >", value, "crossBorderTaxRate");
            return (Criteria) this;
        }

        public Criteria andCrossBorderTaxRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("cross_border_tax_rate >=", value, "crossBorderTaxRate");
            return (Criteria) this;
        }

        public Criteria andCrossBorderTaxRateLessThan(Integer value) {
            addCriterion("cross_border_tax_rate <", value, "crossBorderTaxRate");
            return (Criteria) this;
        }

        public Criteria andCrossBorderTaxRateLessThanOrEqualTo(Integer value) {
            addCriterion("cross_border_tax_rate <=", value, "crossBorderTaxRate");
            return (Criteria) this;
        }

        public Criteria andCrossBorderTaxRateIn(List<Integer> values) {
            addCriterion("cross_border_tax_rate in", values, "crossBorderTaxRate");
            return (Criteria) this;
        }

        public Criteria andCrossBorderTaxRateNotIn(List<Integer> values) {
            addCriterion("cross_border_tax_rate not in", values, "crossBorderTaxRate");
            return (Criteria) this;
        }

        public Criteria andCrossBorderTaxRateBetween(Integer value1, Integer value2) {
            addCriterion("cross_border_tax_rate between", value1, value2, "crossBorderTaxRate");
            return (Criteria) this;
        }

        public Criteria andCrossBorderTaxRateNotBetween(Integer value1, Integer value2) {
            addCriterion("cross_border_tax_rate not between", value1, value2, "crossBorderTaxRate");
            return (Criteria) this;
        }

        public Criteria andBcPriceIsNull() {
            addCriterion("bc_price is null");
            return (Criteria) this;
        }

        public Criteria andBcPriceIsNotNull() {
            addCriterion("bc_price is not null");
            return (Criteria) this;
        }

        public Criteria andBcPriceEqualTo(Integer value) {
            addCriterion("bc_price =", value, "bcPrice");
            return (Criteria) this;
        }

        public Criteria andBcPriceNotEqualTo(Integer value) {
            addCriterion("bc_price <>", value, "bcPrice");
            return (Criteria) this;
        }

        public Criteria andBcPriceGreaterThan(Integer value) {
            addCriterion("bc_price >", value, "bcPrice");
            return (Criteria) this;
        }

        public Criteria andBcPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("bc_price >=", value, "bcPrice");
            return (Criteria) this;
        }

        public Criteria andBcPriceLessThan(Integer value) {
            addCriterion("bc_price <", value, "bcPrice");
            return (Criteria) this;
        }

        public Criteria andBcPriceLessThanOrEqualTo(Integer value) {
            addCriterion("bc_price <=", value, "bcPrice");
            return (Criteria) this;
        }

        public Criteria andBcPriceIn(List<Integer> values) {
            addCriterion("bc_price in", values, "bcPrice");
            return (Criteria) this;
        }

        public Criteria andBcPriceNotIn(List<Integer> values) {
            addCriterion("bc_price not in", values, "bcPrice");
            return (Criteria) this;
        }

        public Criteria andBcPriceBetween(Integer value1, Integer value2) {
            addCriterion("bc_price between", value1, value2, "bcPrice");
            return (Criteria) this;
        }

        public Criteria andBcPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("bc_price not between", value1, value2, "bcPrice");
            return (Criteria) this;
        }

        public Criteria andHsCodeIsNull() {
            addCriterion("hs_code is null");
            return (Criteria) this;
        }

        public Criteria andHsCodeIsNotNull() {
            addCriterion("hs_code is not null");
            return (Criteria) this;
        }

        public Criteria andHsCodeEqualTo(Long value) {
            addCriterion("hs_code =", value, "hsCode");
            return (Criteria) this;
        }

        public Criteria andHsCodeNotEqualTo(Long value) {
            addCriterion("hs_code <>", value, "hsCode");
            return (Criteria) this;
        }

        public Criteria andHsCodeGreaterThan(Long value) {
            addCriterion("hs_code >", value, "hsCode");
            return (Criteria) this;
        }

        public Criteria andHsCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("hs_code >=", value, "hsCode");
            return (Criteria) this;
        }

        public Criteria andHsCodeLessThan(Long value) {
            addCriterion("hs_code <", value, "hsCode");
            return (Criteria) this;
        }

        public Criteria andHsCodeLessThanOrEqualTo(Long value) {
            addCriterion("hs_code <=", value, "hsCode");
            return (Criteria) this;
        }

        public Criteria andHsCodeIn(List<Long> values) {
            addCriterion("hs_code in", values, "hsCode");
            return (Criteria) this;
        }

        public Criteria andHsCodeNotIn(List<Long> values) {
            addCriterion("hs_code not in", values, "hsCode");
            return (Criteria) this;
        }

        public Criteria andHsCodeBetween(Long value1, Long value2) {
            addCriterion("hs_code between", value1, value2, "hsCode");
            return (Criteria) this;
        }

        public Criteria andHsCodeNotBetween(Long value1, Long value2) {
            addCriterion("hs_code not between", value1, value2, "hsCode");
            return (Criteria) this;
        }

        public Criteria andGrossWeightIsNull() {
            addCriterion("gross_weight is null");
            return (Criteria) this;
        }

        public Criteria andGrossWeightIsNotNull() {
            addCriterion("gross_weight is not null");
            return (Criteria) this;
        }

        public Criteria andGrossWeightEqualTo(String value) {
            addCriterion("gross_weight =", value, "grossWeight");
            return (Criteria) this;
        }

        public Criteria andGrossWeightNotEqualTo(String value) {
            addCriterion("gross_weight <>", value, "grossWeight");
            return (Criteria) this;
        }

        public Criteria andGrossWeightGreaterThan(String value) {
            addCriterion("gross_weight >", value, "grossWeight");
            return (Criteria) this;
        }

        public Criteria andGrossWeightGreaterThanOrEqualTo(String value) {
            addCriterion("gross_weight >=", value, "grossWeight");
            return (Criteria) this;
        }

        public Criteria andGrossWeightLessThan(String value) {
            addCriterion("gross_weight <", value, "grossWeight");
            return (Criteria) this;
        }

        public Criteria andGrossWeightLessThanOrEqualTo(String value) {
            addCriterion("gross_weight <=", value, "grossWeight");
            return (Criteria) this;
        }

        public Criteria andGrossWeightLike(String value) {
            addCriterion("gross_weight like", value, "grossWeight");
            return (Criteria) this;
        }

        public Criteria andGrossWeightNotLike(String value) {
            addCriterion("gross_weight not like", value, "grossWeight");
            return (Criteria) this;
        }

        public Criteria andGrossWeightIn(List<String> values) {
            addCriterion("gross_weight in", values, "grossWeight");
            return (Criteria) this;
        }

        public Criteria andGrossWeightNotIn(List<String> values) {
            addCriterion("gross_weight not in", values, "grossWeight");
            return (Criteria) this;
        }

        public Criteria andGrossWeightBetween(String value1, String value2) {
            addCriterion("gross_weight between", value1, value2, "grossWeight");
            return (Criteria) this;
        }

        public Criteria andGrossWeightNotBetween(String value1, String value2) {
            addCriterion("gross_weight not between", value1, value2, "grossWeight");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxNumberIsNull() {
            addCriterion("postal_articles_tax_number is null");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxNumberIsNotNull() {
            addCriterion("postal_articles_tax_number is not null");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxNumberEqualTo(Long value) {
            addCriterion("postal_articles_tax_number =", value, "postalArticlesTaxNumber");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxNumberNotEqualTo(Long value) {
            addCriterion("postal_articles_tax_number <>", value, "postalArticlesTaxNumber");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxNumberGreaterThan(Long value) {
            addCriterion("postal_articles_tax_number >", value, "postalArticlesTaxNumber");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("postal_articles_tax_number >=", value, "postalArticlesTaxNumber");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxNumberLessThan(Long value) {
            addCriterion("postal_articles_tax_number <", value, "postalArticlesTaxNumber");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxNumberLessThanOrEqualTo(Long value) {
            addCriterion("postal_articles_tax_number <=", value, "postalArticlesTaxNumber");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxNumberIn(List<Long> values) {
            addCriterion("postal_articles_tax_number in", values, "postalArticlesTaxNumber");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxNumberNotIn(List<Long> values) {
            addCriterion("postal_articles_tax_number not in", values, "postalArticlesTaxNumber");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxNumberBetween(Long value1, Long value2) {
            addCriterion("postal_articles_tax_number between", value1, value2, "postalArticlesTaxNumber");
            return (Criteria) this;
        }

        public Criteria andPostalArticlesTaxNumberNotBetween(Long value1, Long value2) {
            addCriterion("postal_articles_tax_number not between", value1, value2, "postalArticlesTaxNumber");
            return (Criteria) this;
        }

        public Criteria andIsContainExciseIsNull() {
            addCriterion("is_contain_excise is null");
            return (Criteria) this;
        }

        public Criteria andIsContainExciseIsNotNull() {
            addCriterion("is_contain_excise is not null");
            return (Criteria) this;
        }

        public Criteria andIsContainExciseEqualTo(Short value) {
            addCriterion("is_contain_excise =", value, "isContainExcise");
            return (Criteria) this;
        }

        public Criteria andIsContainExciseNotEqualTo(Short value) {
            addCriterion("is_contain_excise <>", value, "isContainExcise");
            return (Criteria) this;
        }

        public Criteria andIsContainExciseGreaterThan(Short value) {
            addCriterion("is_contain_excise >", value, "isContainExcise");
            return (Criteria) this;
        }

        public Criteria andIsContainExciseGreaterThanOrEqualTo(Short value) {
            addCriterion("is_contain_excise >=", value, "isContainExcise");
            return (Criteria) this;
        }

        public Criteria andIsContainExciseLessThan(Short value) {
            addCriterion("is_contain_excise <", value, "isContainExcise");
            return (Criteria) this;
        }

        public Criteria andIsContainExciseLessThanOrEqualTo(Short value) {
            addCriterion("is_contain_excise <=", value, "isContainExcise");
            return (Criteria) this;
        }

        public Criteria andIsContainExciseIn(List<Short> values) {
            addCriterion("is_contain_excise in", values, "isContainExcise");
            return (Criteria) this;
        }

        public Criteria andIsContainExciseNotIn(List<Short> values) {
            addCriterion("is_contain_excise not in", values, "isContainExcise");
            return (Criteria) this;
        }

        public Criteria andIsContainExciseBetween(Short value1, Short value2) {
            addCriterion("is_contain_excise between", value1, value2, "isContainExcise");
            return (Criteria) this;
        }

        public Criteria andIsContainExciseNotBetween(Short value1, Short value2) {
            addCriterion("is_contain_excise not between", value1, value2, "isContainExcise");
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