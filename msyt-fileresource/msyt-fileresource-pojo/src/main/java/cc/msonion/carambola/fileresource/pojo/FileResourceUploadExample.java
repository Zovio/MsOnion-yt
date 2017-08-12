package cc.msonion.carambola.fileresource.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileResourceUploadExample implements MsOnionBaseExampleAdapter {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public FileResourceUploadExample() {
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

        public Criteria andMessageIdIsNull() {
            addCriterion("message_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageIdIsNotNull() {
            addCriterion("message_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageIdEqualTo(Long value) {
            addCriterion("message_id =", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotEqualTo(Long value) {
            addCriterion("message_id <>", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThan(Long value) {
            addCriterion("message_id >", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("message_id >=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThan(Long value) {
            addCriterion("message_id <", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThanOrEqualTo(Long value) {
            addCriterion("message_id <=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdIn(List<Long> values) {
            addCriterion("message_id in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotIn(List<Long> values) {
            addCriterion("message_id not in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdBetween(Long value1, Long value2) {
            addCriterion("message_id between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotBetween(Long value1, Long value2) {
            addCriterion("message_id not between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("path is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("path is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("path =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("path <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("path >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("path >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("path <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("path <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("path like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("path not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("path in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("path not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("path between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("path not between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andTotalSizeIsNull() {
            addCriterion("total_size is null");
            return (Criteria) this;
        }

        public Criteria andTotalSizeIsNotNull() {
            addCriterion("total_size is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSizeEqualTo(Long value) {
            addCriterion("total_size =", value, "totalSize");
            return (Criteria) this;
        }

        public Criteria andTotalSizeNotEqualTo(Long value) {
            addCriterion("total_size <>", value, "totalSize");
            return (Criteria) this;
        }

        public Criteria andTotalSizeGreaterThan(Long value) {
            addCriterion("total_size >", value, "totalSize");
            return (Criteria) this;
        }

        public Criteria andTotalSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("total_size >=", value, "totalSize");
            return (Criteria) this;
        }

        public Criteria andTotalSizeLessThan(Long value) {
            addCriterion("total_size <", value, "totalSize");
            return (Criteria) this;
        }

        public Criteria andTotalSizeLessThanOrEqualTo(Long value) {
            addCriterion("total_size <=", value, "totalSize");
            return (Criteria) this;
        }

        public Criteria andTotalSizeIn(List<Long> values) {
            addCriterion("total_size in", values, "totalSize");
            return (Criteria) this;
        }

        public Criteria andTotalSizeNotIn(List<Long> values) {
            addCriterion("total_size not in", values, "totalSize");
            return (Criteria) this;
        }

        public Criteria andTotalSizeBetween(Long value1, Long value2) {
            addCriterion("total_size between", value1, value2, "totalSize");
            return (Criteria) this;
        }

        public Criteria andTotalSizeNotBetween(Long value1, Long value2) {
            addCriterion("total_size not between", value1, value2, "totalSize");
            return (Criteria) this;
        }

        public Criteria andUploadSizeIsNull() {
            addCriterion("upload_size is null");
            return (Criteria) this;
        }

        public Criteria andUploadSizeIsNotNull() {
            addCriterion("upload_size is not null");
            return (Criteria) this;
        }

        public Criteria andUploadSizeEqualTo(Long value) {
            addCriterion("upload_size =", value, "uploadSize");
            return (Criteria) this;
        }

        public Criteria andUploadSizeNotEqualTo(Long value) {
            addCriterion("upload_size <>", value, "uploadSize");
            return (Criteria) this;
        }

        public Criteria andUploadSizeGreaterThan(Long value) {
            addCriterion("upload_size >", value, "uploadSize");
            return (Criteria) this;
        }

        public Criteria andUploadSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("upload_size >=", value, "uploadSize");
            return (Criteria) this;
        }

        public Criteria andUploadSizeLessThan(Long value) {
            addCriterion("upload_size <", value, "uploadSize");
            return (Criteria) this;
        }

        public Criteria andUploadSizeLessThanOrEqualTo(Long value) {
            addCriterion("upload_size <=", value, "uploadSize");
            return (Criteria) this;
        }

        public Criteria andUploadSizeIn(List<Long> values) {
            addCriterion("upload_size in", values, "uploadSize");
            return (Criteria) this;
        }

        public Criteria andUploadSizeNotIn(List<Long> values) {
            addCriterion("upload_size not in", values, "uploadSize");
            return (Criteria) this;
        }

        public Criteria andUploadSizeBetween(Long value1, Long value2) {
            addCriterion("upload_size between", value1, value2, "uploadSize");
            return (Criteria) this;
        }

        public Criteria andUploadSizeNotBetween(Long value1, Long value2) {
            addCriterion("upload_size not between", value1, value2, "uploadSize");
            return (Criteria) this;
        }

        public Criteria andUploadStatusIsNull() {
            addCriterion("upload_status is null");
            return (Criteria) this;
        }

        public Criteria andUploadStatusIsNotNull() {
            addCriterion("upload_status is not null");
            return (Criteria) this;
        }

        public Criteria andUploadStatusEqualTo(Short value) {
            addCriterion("upload_status =", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusNotEqualTo(Short value) {
            addCriterion("upload_status <>", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusGreaterThan(Short value) {
            addCriterion("upload_status >", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("upload_status >=", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusLessThan(Short value) {
            addCriterion("upload_status <", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusLessThanOrEqualTo(Short value) {
            addCriterion("upload_status <=", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusIn(List<Short> values) {
            addCriterion("upload_status in", values, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusNotIn(List<Short> values) {
            addCriterion("upload_status not in", values, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusBetween(Short value1, Short value2) {
            addCriterion("upload_status between", value1, value2, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusNotBetween(Short value1, Short value2) {
            addCriterion("upload_status not between", value1, value2, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andMd5ValueIsNull() {
            addCriterion("md5_value is null");
            return (Criteria) this;
        }

        public Criteria andMd5ValueIsNotNull() {
            addCriterion("md5_value is not null");
            return (Criteria) this;
        }

        public Criteria andMd5ValueEqualTo(String value) {
            addCriterion("md5_value =", value, "md5Value");
            return (Criteria) this;
        }

        public Criteria andMd5ValueNotEqualTo(String value) {
            addCriterion("md5_value <>", value, "md5Value");
            return (Criteria) this;
        }

        public Criteria andMd5ValueGreaterThan(String value) {
            addCriterion("md5_value >", value, "md5Value");
            return (Criteria) this;
        }

        public Criteria andMd5ValueGreaterThanOrEqualTo(String value) {
            addCriterion("md5_value >=", value, "md5Value");
            return (Criteria) this;
        }

        public Criteria andMd5ValueLessThan(String value) {
            addCriterion("md5_value <", value, "md5Value");
            return (Criteria) this;
        }

        public Criteria andMd5ValueLessThanOrEqualTo(String value) {
            addCriterion("md5_value <=", value, "md5Value");
            return (Criteria) this;
        }

        public Criteria andMd5ValueLike(String value) {
            addCriterion("md5_value like", value, "md5Value");
            return (Criteria) this;
        }

        public Criteria andMd5ValueNotLike(String value) {
            addCriterion("md5_value not like", value, "md5Value");
            return (Criteria) this;
        }

        public Criteria andMd5ValueIn(List<String> values) {
            addCriterion("md5_value in", values, "md5Value");
            return (Criteria) this;
        }

        public Criteria andMd5ValueNotIn(List<String> values) {
            addCriterion("md5_value not in", values, "md5Value");
            return (Criteria) this;
        }

        public Criteria andMd5ValueBetween(String value1, String value2) {
            addCriterion("md5_value between", value1, value2, "md5Value");
            return (Criteria) this;
        }

        public Criteria andMd5ValueNotBetween(String value1, String value2) {
            addCriterion("md5_value not between", value1, value2, "md5Value");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNull() {
            addCriterion("file_type is null");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNotNull() {
            addCriterion("file_type is not null");
            return (Criteria) this;
        }

        public Criteria andFileTypeEqualTo(Short value) {
            addCriterion("file_type =", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotEqualTo(Short value) {
            addCriterion("file_type <>", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThan(Short value) {
            addCriterion("file_type >", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("file_type >=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThan(Short value) {
            addCriterion("file_type <", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThanOrEqualTo(Short value) {
            addCriterion("file_type <=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeIn(List<Short> values) {
            addCriterion("file_type in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotIn(List<Short> values) {
            addCriterion("file_type not in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeBetween(Short value1, Short value2) {
            addCriterion("file_type between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotBetween(Short value1, Short value2) {
            addCriterion("file_type not between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andAlbumTypeIsNull() {
            addCriterion("album_type is null");
            return (Criteria) this;
        }

        public Criteria andAlbumTypeIsNotNull() {
            addCriterion("album_type is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumTypeEqualTo(Short value) {
            addCriterion("album_type =", value, "albumType");
            return (Criteria) this;
        }

        public Criteria andAlbumTypeNotEqualTo(Short value) {
            addCriterion("album_type <>", value, "albumType");
            return (Criteria) this;
        }

        public Criteria andAlbumTypeGreaterThan(Short value) {
            addCriterion("album_type >", value, "albumType");
            return (Criteria) this;
        }

        public Criteria andAlbumTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("album_type >=", value, "albumType");
            return (Criteria) this;
        }

        public Criteria andAlbumTypeLessThan(Short value) {
            addCriterion("album_type <", value, "albumType");
            return (Criteria) this;
        }

        public Criteria andAlbumTypeLessThanOrEqualTo(Short value) {
            addCriterion("album_type <=", value, "albumType");
            return (Criteria) this;
        }

        public Criteria andAlbumTypeIn(List<Short> values) {
            addCriterion("album_type in", values, "albumType");
            return (Criteria) this;
        }

        public Criteria andAlbumTypeNotIn(List<Short> values) {
            addCriterion("album_type not in", values, "albumType");
            return (Criteria) this;
        }

        public Criteria andAlbumTypeBetween(Short value1, Short value2) {
            addCriterion("album_type between", value1, value2, "albumType");
            return (Criteria) this;
        }

        public Criteria andAlbumTypeNotBetween(Short value1, Short value2) {
            addCriterion("album_type not between", value1, value2, "albumType");
            return (Criteria) this;
        }

        public Criteria andOssPathIsNull() {
            addCriterion("oss_path is null");
            return (Criteria) this;
        }

        public Criteria andOssPathIsNotNull() {
            addCriterion("oss_path is not null");
            return (Criteria) this;
        }

        public Criteria andOssPathEqualTo(String value) {
            addCriterion("oss_path =", value, "ossPath");
            return (Criteria) this;
        }

        public Criteria andOssPathNotEqualTo(String value) {
            addCriterion("oss_path <>", value, "ossPath");
            return (Criteria) this;
        }

        public Criteria andOssPathGreaterThan(String value) {
            addCriterion("oss_path >", value, "ossPath");
            return (Criteria) this;
        }

        public Criteria andOssPathGreaterThanOrEqualTo(String value) {
            addCriterion("oss_path >=", value, "ossPath");
            return (Criteria) this;
        }

        public Criteria andOssPathLessThan(String value) {
            addCriterion("oss_path <", value, "ossPath");
            return (Criteria) this;
        }

        public Criteria andOssPathLessThanOrEqualTo(String value) {
            addCriterion("oss_path <=", value, "ossPath");
            return (Criteria) this;
        }

        public Criteria andOssPathLike(String value) {
            addCriterion("oss_path like", value, "ossPath");
            return (Criteria) this;
        }

        public Criteria andOssPathNotLike(String value) {
            addCriterion("oss_path not like", value, "ossPath");
            return (Criteria) this;
        }

        public Criteria andOssPathIn(List<String> values) {
            addCriterion("oss_path in", values, "ossPath");
            return (Criteria) this;
        }

        public Criteria andOssPathNotIn(List<String> values) {
            addCriterion("oss_path not in", values, "ossPath");
            return (Criteria) this;
        }

        public Criteria andOssPathBetween(String value1, String value2) {
            addCriterion("oss_path between", value1, value2, "ossPath");
            return (Criteria) this;
        }

        public Criteria andOssPathNotBetween(String value1, String value2) {
            addCriterion("oss_path not between", value1, value2, "ossPath");
            return (Criteria) this;
        }

        public Criteria andOssSyncIsNull() {
            addCriterion("oss_sync is null");
            return (Criteria) this;
        }

        public Criteria andOssSyncIsNotNull() {
            addCriterion("oss_sync is not null");
            return (Criteria) this;
        }

        public Criteria andOssSyncEqualTo(Short value) {
            addCriterion("oss_sync =", value, "ossSync");
            return (Criteria) this;
        }

        public Criteria andOssSyncNotEqualTo(Short value) {
            addCriterion("oss_sync <>", value, "ossSync");
            return (Criteria) this;
        }

        public Criteria andOssSyncGreaterThan(Short value) {
            addCriterion("oss_sync >", value, "ossSync");
            return (Criteria) this;
        }

        public Criteria andOssSyncGreaterThanOrEqualTo(Short value) {
            addCriterion("oss_sync >=", value, "ossSync");
            return (Criteria) this;
        }

        public Criteria andOssSyncLessThan(Short value) {
            addCriterion("oss_sync <", value, "ossSync");
            return (Criteria) this;
        }

        public Criteria andOssSyncLessThanOrEqualTo(Short value) {
            addCriterion("oss_sync <=", value, "ossSync");
            return (Criteria) this;
        }

        public Criteria andOssSyncIn(List<Short> values) {
            addCriterion("oss_sync in", values, "ossSync");
            return (Criteria) this;
        }

        public Criteria andOssSyncNotIn(List<Short> values) {
            addCriterion("oss_sync not in", values, "ossSync");
            return (Criteria) this;
        }

        public Criteria andOssSyncBetween(Short value1, Short value2) {
            addCriterion("oss_sync between", value1, value2, "ossSync");
            return (Criteria) this;
        }

        public Criteria andOssSyncNotBetween(Short value1, Short value2) {
            addCriterion("oss_sync not between", value1, value2, "ossSync");
            return (Criteria) this;
        }

        public Criteria andSearchByImageIsNull() {
            addCriterion("search_by_image is null");
            return (Criteria) this;
        }

        public Criteria andSearchByImageIsNotNull() {
            addCriterion("search_by_image is not null");
            return (Criteria) this;
        }

        public Criteria andSearchByImageEqualTo(Short value) {
            addCriterion("search_by_image =", value, "searchByImage");
            return (Criteria) this;
        }

        public Criteria andSearchByImageNotEqualTo(Short value) {
            addCriterion("search_by_image <>", value, "searchByImage");
            return (Criteria) this;
        }

        public Criteria andSearchByImageGreaterThan(Short value) {
            addCriterion("search_by_image >", value, "searchByImage");
            return (Criteria) this;
        }

        public Criteria andSearchByImageGreaterThanOrEqualTo(Short value) {
            addCriterion("search_by_image >=", value, "searchByImage");
            return (Criteria) this;
        }

        public Criteria andSearchByImageLessThan(Short value) {
            addCriterion("search_by_image <", value, "searchByImage");
            return (Criteria) this;
        }

        public Criteria andSearchByImageLessThanOrEqualTo(Short value) {
            addCriterion("search_by_image <=", value, "searchByImage");
            return (Criteria) this;
        }

        public Criteria andSearchByImageIn(List<Short> values) {
            addCriterion("search_by_image in", values, "searchByImage");
            return (Criteria) this;
        }

        public Criteria andSearchByImageNotIn(List<Short> values) {
            addCriterion("search_by_image not in", values, "searchByImage");
            return (Criteria) this;
        }

        public Criteria andSearchByImageBetween(Short value1, Short value2) {
            addCriterion("search_by_image between", value1, value2, "searchByImage");
            return (Criteria) this;
        }

        public Criteria andSearchByImageNotBetween(Short value1, Short value2) {
            addCriterion("search_by_image not between", value1, value2, "searchByImage");
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