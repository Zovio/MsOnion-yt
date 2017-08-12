package cc.msonion.carambola.fileresource.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileResourceTemplateExample implements MsOnionBaseExampleAdapter {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public FileResourceTemplateExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Short value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Short value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Short value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Short value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Short value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Short value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Short> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Short> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Short value1, Short value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Short value1, Short value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andAppPlatformIsNull() {
            addCriterion("app_platform is null");
            return (Criteria) this;
        }

        public Criteria andAppPlatformIsNotNull() {
            addCriterion("app_platform is not null");
            return (Criteria) this;
        }

        public Criteria andAppPlatformEqualTo(Short value) {
            addCriterion("app_platform =", value, "appPlatform");
            return (Criteria) this;
        }

        public Criteria andAppPlatformNotEqualTo(Short value) {
            addCriterion("app_platform <>", value, "appPlatform");
            return (Criteria) this;
        }

        public Criteria andAppPlatformGreaterThan(Short value) {
            addCriterion("app_platform >", value, "appPlatform");
            return (Criteria) this;
        }

        public Criteria andAppPlatformGreaterThanOrEqualTo(Short value) {
            addCriterion("app_platform >=", value, "appPlatform");
            return (Criteria) this;
        }

        public Criteria andAppPlatformLessThan(Short value) {
            addCriterion("app_platform <", value, "appPlatform");
            return (Criteria) this;
        }

        public Criteria andAppPlatformLessThanOrEqualTo(Short value) {
            addCriterion("app_platform <=", value, "appPlatform");
            return (Criteria) this;
        }

        public Criteria andAppPlatformIn(List<Short> values) {
            addCriterion("app_platform in", values, "appPlatform");
            return (Criteria) this;
        }

        public Criteria andAppPlatformNotIn(List<Short> values) {
            addCriterion("app_platform not in", values, "appPlatform");
            return (Criteria) this;
        }

        public Criteria andAppPlatformBetween(Short value1, Short value2) {
            addCriterion("app_platform between", value1, value2, "appPlatform");
            return (Criteria) this;
        }

        public Criteria andAppPlatformNotBetween(Short value1, Short value2) {
            addCriterion("app_platform not between", value1, value2, "appPlatform");
            return (Criteria) this;
        }

        public Criteria andFileMessageIdIsNull() {
            addCriterion("file_message_id is null");
            return (Criteria) this;
        }

        public Criteria andFileMessageIdIsNotNull() {
            addCriterion("file_message_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileMessageIdEqualTo(Long value) {
            addCriterion("file_message_id =", value, "fileMessageId");
            return (Criteria) this;
        }

        public Criteria andFileMessageIdNotEqualTo(Long value) {
            addCriterion("file_message_id <>", value, "fileMessageId");
            return (Criteria) this;
        }

        public Criteria andFileMessageIdGreaterThan(Long value) {
            addCriterion("file_message_id >", value, "fileMessageId");
            return (Criteria) this;
        }

        public Criteria andFileMessageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("file_message_id >=", value, "fileMessageId");
            return (Criteria) this;
        }

        public Criteria andFileMessageIdLessThan(Long value) {
            addCriterion("file_message_id <", value, "fileMessageId");
            return (Criteria) this;
        }

        public Criteria andFileMessageIdLessThanOrEqualTo(Long value) {
            addCriterion("file_message_id <=", value, "fileMessageId");
            return (Criteria) this;
        }

        public Criteria andFileMessageIdIn(List<Long> values) {
            addCriterion("file_message_id in", values, "fileMessageId");
            return (Criteria) this;
        }

        public Criteria andFileMessageIdNotIn(List<Long> values) {
            addCriterion("file_message_id not in", values, "fileMessageId");
            return (Criteria) this;
        }

        public Criteria andFileMessageIdBetween(Long value1, Long value2) {
            addCriterion("file_message_id between", value1, value2, "fileMessageId");
            return (Criteria) this;
        }

        public Criteria andFileMessageIdNotBetween(Long value1, Long value2) {
            addCriterion("file_message_id not between", value1, value2, "fileMessageId");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
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