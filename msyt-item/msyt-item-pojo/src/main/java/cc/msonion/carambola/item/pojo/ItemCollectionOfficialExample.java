package cc.msonion.carambola.item.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemCollectionOfficialExample implements MsOnionBaseExampleAdapter {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ItemCollectionOfficialExample() {
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

        public Criteria andSellingPointIsNull() {
            addCriterion("selling_point is null");
            return (Criteria) this;
        }

        public Criteria andSellingPointIsNotNull() {
            addCriterion("selling_point is not null");
            return (Criteria) this;
        }

        public Criteria andSellingPointEqualTo(String value) {
            addCriterion("selling_point =", value, "sellingPoint");
            return (Criteria) this;
        }

        public Criteria andSellingPointNotEqualTo(String value) {
            addCriterion("selling_point <>", value, "sellingPoint");
            return (Criteria) this;
        }

        public Criteria andSellingPointGreaterThan(String value) {
            addCriterion("selling_point >", value, "sellingPoint");
            return (Criteria) this;
        }

        public Criteria andSellingPointGreaterThanOrEqualTo(String value) {
            addCriterion("selling_point >=", value, "sellingPoint");
            return (Criteria) this;
        }

        public Criteria andSellingPointLessThan(String value) {
            addCriterion("selling_point <", value, "sellingPoint");
            return (Criteria) this;
        }

        public Criteria andSellingPointLessThanOrEqualTo(String value) {
            addCriterion("selling_point <=", value, "sellingPoint");
            return (Criteria) this;
        }

        public Criteria andSellingPointLike(String value) {
            addCriterion("selling_point like", value, "sellingPoint");
            return (Criteria) this;
        }

        public Criteria andSellingPointNotLike(String value) {
            addCriterion("selling_point not like", value, "sellingPoint");
            return (Criteria) this;
        }

        public Criteria andSellingPointIn(List<String> values) {
            addCriterion("selling_point in", values, "sellingPoint");
            return (Criteria) this;
        }

        public Criteria andSellingPointNotIn(List<String> values) {
            addCriterion("selling_point not in", values, "sellingPoint");
            return (Criteria) this;
        }

        public Criteria andSellingPointBetween(String value1, String value2) {
            addCriterion("selling_point between", value1, value2, "sellingPoint");
            return (Criteria) this;
        }

        public Criteria andSellingPointNotBetween(String value1, String value2) {
            addCriterion("selling_point not between", value1, value2, "sellingPoint");
            return (Criteria) this;
        }

        public Criteria andSloganIsNull() {
            addCriterion("slogan is null");
            return (Criteria) this;
        }

        public Criteria andSloganIsNotNull() {
            addCriterion("slogan is not null");
            return (Criteria) this;
        }

        public Criteria andSloganEqualTo(String value) {
            addCriterion("slogan =", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotEqualTo(String value) {
            addCriterion("slogan <>", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganGreaterThan(String value) {
            addCriterion("slogan >", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganGreaterThanOrEqualTo(String value) {
            addCriterion("slogan >=", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganLessThan(String value) {
            addCriterion("slogan <", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganLessThanOrEqualTo(String value) {
            addCriterion("slogan <=", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganLike(String value) {
            addCriterion("slogan like", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotLike(String value) {
            addCriterion("slogan not like", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganIn(List<String> values) {
            addCriterion("slogan in", values, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotIn(List<String> values) {
            addCriterion("slogan not in", values, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganBetween(String value1, String value2) {
            addCriterion("slogan between", value1, value2, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotBetween(String value1, String value2) {
            addCriterion("slogan not between", value1, value2, "slogan");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideIsNull() {
            addCriterion("shopping_guide is null");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideIsNotNull() {
            addCriterion("shopping_guide is not null");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideEqualTo(String value) {
            addCriterion("shopping_guide =", value, "shoppingGuide");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideNotEqualTo(String value) {
            addCriterion("shopping_guide <>", value, "shoppingGuide");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideGreaterThan(String value) {
            addCriterion("shopping_guide >", value, "shoppingGuide");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideGreaterThanOrEqualTo(String value) {
            addCriterion("shopping_guide >=", value, "shoppingGuide");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideLessThan(String value) {
            addCriterion("shopping_guide <", value, "shoppingGuide");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideLessThanOrEqualTo(String value) {
            addCriterion("shopping_guide <=", value, "shoppingGuide");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideLike(String value) {
            addCriterion("shopping_guide like", value, "shoppingGuide");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideNotLike(String value) {
            addCriterion("shopping_guide not like", value, "shoppingGuide");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideIn(List<String> values) {
            addCriterion("shopping_guide in", values, "shoppingGuide");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideNotIn(List<String> values) {
            addCriterion("shopping_guide not in", values, "shoppingGuide");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideBetween(String value1, String value2) {
            addCriterion("shopping_guide between", value1, value2, "shoppingGuide");
            return (Criteria) this;
        }

        public Criteria andShoppingGuideNotBetween(String value1, String value2) {
            addCriterion("shopping_guide not between", value1, value2, "shoppingGuide");
            return (Criteria) this;
        }

        public Criteria andEditorIdxIsNull() {
            addCriterion("editor_idx is null");
            return (Criteria) this;
        }

        public Criteria andEditorIdxIsNotNull() {
            addCriterion("editor_idx is not null");
            return (Criteria) this;
        }

        public Criteria andEditorIdxEqualTo(Long value) {
            addCriterion("editor_idx =", value, "editorIdx");
            return (Criteria) this;
        }

        public Criteria andEditorIdxNotEqualTo(Long value) {
            addCriterion("editor_idx <>", value, "editorIdx");
            return (Criteria) this;
        }

        public Criteria andEditorIdxGreaterThan(Long value) {
            addCriterion("editor_idx >", value, "editorIdx");
            return (Criteria) this;
        }

        public Criteria andEditorIdxGreaterThanOrEqualTo(Long value) {
            addCriterion("editor_idx >=", value, "editorIdx");
            return (Criteria) this;
        }

        public Criteria andEditorIdxLessThan(Long value) {
            addCriterion("editor_idx <", value, "editorIdx");
            return (Criteria) this;
        }

        public Criteria andEditorIdxLessThanOrEqualTo(Long value) {
            addCriterion("editor_idx <=", value, "editorIdx");
            return (Criteria) this;
        }

        public Criteria andEditorIdxIn(List<Long> values) {
            addCriterion("editor_idx in", values, "editorIdx");
            return (Criteria) this;
        }

        public Criteria andEditorIdxNotIn(List<Long> values) {
            addCriterion("editor_idx not in", values, "editorIdx");
            return (Criteria) this;
        }

        public Criteria andEditorIdxBetween(Long value1, Long value2) {
            addCriterion("editor_idx between", value1, value2, "editorIdx");
            return (Criteria) this;
        }

        public Criteria andEditorIdxNotBetween(Long value1, Long value2) {
            addCriterion("editor_idx not between", value1, value2, "editorIdx");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsIsNull() {
            addCriterion("search_keywords is null");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsIsNotNull() {
            addCriterion("search_keywords is not null");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsEqualTo(String value) {
            addCriterion("search_keywords =", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsNotEqualTo(String value) {
            addCriterion("search_keywords <>", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsGreaterThan(String value) {
            addCriterion("search_keywords >", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("search_keywords >=", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsLessThan(String value) {
            addCriterion("search_keywords <", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsLessThanOrEqualTo(String value) {
            addCriterion("search_keywords <=", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsLike(String value) {
            addCriterion("search_keywords like", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsNotLike(String value) {
            addCriterion("search_keywords not like", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsIn(List<String> values) {
            addCriterion("search_keywords in", values, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsNotIn(List<String> values) {
            addCriterion("search_keywords not in", values, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsBetween(String value1, String value2) {
            addCriterion("search_keywords between", value1, value2, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsNotBetween(String value1, String value2) {
            addCriterion("search_keywords not between", value1, value2, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIsNull() {
            addCriterion("attachment_id is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIsNotNull() {
            addCriterion("attachment_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdEqualTo(Long value) {
            addCriterion("attachment_id =", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotEqualTo(Long value) {
            addCriterion("attachment_id <>", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdGreaterThan(Long value) {
            addCriterion("attachment_id >", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("attachment_id >=", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdLessThan(Long value) {
            addCriterion("attachment_id <", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdLessThanOrEqualTo(Long value) {
            addCriterion("attachment_id <=", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIn(List<Long> values) {
            addCriterion("attachment_id in", values, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotIn(List<Long> values) {
            addCriterion("attachment_id not in", values, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdBetween(Long value1, Long value2) {
            addCriterion("attachment_id between", value1, value2, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotBetween(Long value1, Long value2) {
            addCriterion("attachment_id not between", value1, value2, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andVideoLinkIsNull() {
            addCriterion("video_link is null");
            return (Criteria) this;
        }

        public Criteria andVideoLinkIsNotNull() {
            addCriterion("video_link is not null");
            return (Criteria) this;
        }

        public Criteria andVideoLinkEqualTo(String value) {
            addCriterion("video_link =", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkNotEqualTo(String value) {
            addCriterion("video_link <>", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkGreaterThan(String value) {
            addCriterion("video_link >", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkGreaterThanOrEqualTo(String value) {
            addCriterion("video_link >=", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkLessThan(String value) {
            addCriterion("video_link <", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkLessThanOrEqualTo(String value) {
            addCriterion("video_link <=", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkLike(String value) {
            addCriterion("video_link like", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkNotLike(String value) {
            addCriterion("video_link not like", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkIn(List<String> values) {
            addCriterion("video_link in", values, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkNotIn(List<String> values) {
            addCriterion("video_link not in", values, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkBetween(String value1, String value2) {
            addCriterion("video_link between", value1, value2, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkNotBetween(String value1, String value2) {
            addCriterion("video_link not between", value1, value2, "videoLink");
            return (Criteria) this;
        }

        public Criteria andEditTypeIsNull() {
            addCriterion("edit_type is null");
            return (Criteria) this;
        }

        public Criteria andEditTypeIsNotNull() {
            addCriterion("edit_type is not null");
            return (Criteria) this;
        }

        public Criteria andEditTypeEqualTo(Short value) {
            addCriterion("edit_type =", value, "editType");
            return (Criteria) this;
        }

        public Criteria andEditTypeNotEqualTo(Short value) {
            addCriterion("edit_type <>", value, "editType");
            return (Criteria) this;
        }

        public Criteria andEditTypeGreaterThan(Short value) {
            addCriterion("edit_type >", value, "editType");
            return (Criteria) this;
        }

        public Criteria andEditTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("edit_type >=", value, "editType");
            return (Criteria) this;
        }

        public Criteria andEditTypeLessThan(Short value) {
            addCriterion("edit_type <", value, "editType");
            return (Criteria) this;
        }

        public Criteria andEditTypeLessThanOrEqualTo(Short value) {
            addCriterion("edit_type <=", value, "editType");
            return (Criteria) this;
        }

        public Criteria andEditTypeIn(List<Short> values) {
            addCriterion("edit_type in", values, "editType");
            return (Criteria) this;
        }

        public Criteria andEditTypeNotIn(List<Short> values) {
            addCriterion("edit_type not in", values, "editType");
            return (Criteria) this;
        }

        public Criteria andEditTypeBetween(Short value1, Short value2) {
            addCriterion("edit_type between", value1, value2, "editType");
            return (Criteria) this;
        }

        public Criteria andEditTypeNotBetween(Short value1, Short value2) {
            addCriterion("edit_type not between", value1, value2, "editType");
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