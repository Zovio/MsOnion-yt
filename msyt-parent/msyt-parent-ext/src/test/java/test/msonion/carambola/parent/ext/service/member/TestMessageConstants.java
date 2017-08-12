package test.msonion.carambola.parent.ext.service.member;

/**
 * Created by HeroCao on 2017/5/19.
 */
public final class TestMessageConstants {

    private TestMessageConstants() { }

    //////////////////// 用户名（成员名）和密码等信息，Begin ////////////////////

    /**
     * 用户名不能为空
     */
    public static final String MESSAGE_MEMBER_NAME_NULL = "用户名不能为空";

    /**
     * 密码不能为空
     */
    public static final String MESSAGE_MEMBER_PASSWORD_NULL = "密码不能为空";

    /**
     * 用户名或密码错误
     */
    public static final String MESSAGE_MEMBER_NAME_OR_PASSWORD_ERROR = "用户名或密码错误";

    /**
     * 用户信息不能空
     */
    public static final String MESSAGE_MEMBER_INFO_NULL = "用户信息不能空";

    /**
     * 用户名非法，4-20个字符
     */
    public static final String MESSAGE_MEMBER_NAME_LENGTH_ILLEGAL = "用户名非法，4-20个字符";

    /**
     * 用户名非法，包含敏感词
     */
    public static final String MESSAGE_MEMBER_NAME_ILLEGAL_HASH_SENSITIVE_WORDS = "用户名非法，包含敏感词";

    /**
     * 密码非法，6-20个字符
     */
    public static final String MESSAGE_MEMBER_PASSWORD_LENGTH_ILLEGAL = "密码非法，6-20个字符";

    /**
     * 您的密码与账号或者邮箱太重合，有被盗风险，请更换
     */
    public static final String MESSAGE_MEMBER_PASSWORD_UNFRIENDLY = "您的密码与账号或者邮箱太重合，有被盗风险，请更换";

    /**
     * 密码必须是字母或符号和数字的结合，请更换
     */
    public static final String MESSAGE_MEMBER_PASSWORD_COMPOSE_ILLEGAL = "密码必须是字母或符号和数字的结合，请更换";

    /**
     * Email非法，6-30个字符
     */
    public static final String MESSAGE_MEMBER_EMAIL_LENGTH_ILLEGAL = "Email非法，6-30个字符";

    /**
     * Email不能为空
     */
    public static final String MESSAGE_MEMBER_EMAIL_NULL = "Email不能为空";
    /**
     * Email非法，格式错误
     */
    public static final String MESSAGE_MEMBER_EMAIL_FORMAT_ILLEGAL = "Email非法，格式错误";
    /**
     * Email非法
     */
    public static final String MESSAGE_MEMBER_EMAIL_ILLEGAL = "Email非法";

    /**
     * 手机号码非法
     */
    public static final String MESSAGE_MEMBER_PHONE_ILLEGAL = "手机号码非法";

    /**
     * 手机号码不能为空
     */
    public static final String MESSAGE_MEMBER_PHONE_NULL = "手机号码不能为空";

    /**
     * 当前用户账号未激活
     */
    public static final String MESSAGE_MEMBER_ACCOUNT_DISABLE = "当前用户账号未激活";

    /**
     * 电话号码长度非法
     */
    public static final String MESSAGE_MEMBER_TEL_LENGTH_ILLEGAL = "电话号码长度非法";

    /**
     * 电话号码非法
     */
    public static final String MESSAGE_MEMBER_TEL_ILLEGAL = "电话号码非法";

    /**
     * 用户名全非法，4-20个字符
     */
    public static final String MESSAGE_MEMBER_FULLNAME_LENGTH_ILLEGAL = "用户全名非法，4-20个字符";

    /**
     * 用户全名非法，包含敏感词
     */
    public static final String MESSAGE_MEMBER_FULLNAME_ILLEGAL_HASH_SENSITIVE_WORDS = "用户全名非法，包含敏感词";

    /**
     * 备注内容非法，最多50个字符
     */
    public static final String MESSAGE_MEMBER_REMARK_LENGTH_ILLEGAL = "备注内容非法，最多50个字符";

    /**
     * 备注内容非法，包含敏感词
     */
    public static final String MESSAGE_MEMBER_REMARK_ILLEGAL_HASH_SENSITIVE_WORDS = "备注内容非法，包含敏感词";


    /**
     * 扩展内容非法，最多100个字符
     */
    public static final String MESSAGE_MEMBER_EXT_LENGTH_ILLEGAL = "扩展内容非法，最多100个字符";

    /**
     * 扩展内容非法，包含敏感词
     */
    public static final String MESSAGE_MEMBER_EX_ILLEGAL_HASH_SENSITIVE_WORDS = "扩展内容非法，包含敏感词";

    /**
     * 参数非法，未知参数类型
     */
    public static final String MESSAGE_MEMBER_PARAMETER_TYPE_ILLEGAL = "参数非法，未知参数类型";

    /**
     * 修改密码失败
     */
    public static final String MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE = "修改密码失败";

    /**
     * 修改密码成功
     */
    public static final String MESSAGE_MEMBER_PASSWORD_UPDATE_SUCCESS = "修改密码成功";

    /**
     * 修改密码失败，账号原始密码错误
     */
    public static final String MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_OLD_PASSWORD_ERROR = "修改密码失败，账号原始密码错误";

    /**
     * 用户账号非法
     */
    public static final String MESSAGE_MEMBER_IDX_ILLEGAL = "用户账号非法";

    /**
     * 原始密码非法
     */
    public static final String MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_OLD_PASSWORD_ILLEGAL = "原始密码非法";

    /**
     * 新密码非法
     */
    public static final String MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_NEW_PASSWORD_ILLEGAL = "新密码非法";
    /**
     * 修改密码失败，邮箱错误
     */
    public static final String MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_EMAIL_ERROR = "修改密码失败，邮箱错误";


    /**
     * 删除用户失败，非法主键idx
     */
    public static final String MESSAGE_MEMBER_DELETE_BY_IDX_ILLEGAL = "删除用户失败，非法主键idx";
    /**
     * 删除用户成功
     */
    public static final String MESSAGE_MEMBER_DELETE_SUCCESS = "删除用户成功";
    /**
     * 删除用户失败
     */
    public static final String MESSAGE_MEMBER_DELETE_FAILURE = "删除用户失败，用户不存在";

    /**
     * 更新用户信息失败
     */
    public static final String MESSAGE_MEMBER_UPDATE_FULLNAME = "更新用户信息失败";

    /**
     * 更新用户信息成功
     */
    public static final String MESSAGE_MEMBER_UPDATE_SUCCESS = "更新用户信息成功";

    //////////////////// 用户名（成员名）和密码等信息，End ////////////////////


    //////////////////// Token，Begin  ////////////////////

    /**
     * 登录失败
     */
    public static final String MESSAGE_TOKEN_ERROR = "登录失败";

    /**
     * 非法参数，Token不能为空
     */
    public static final String MESSAGE_TOKEN_ILLEGAL = "非法参数，Token不能为空";

    //////////////////// Token，End ////////////////////


    //////////////////// Logout（注销），Begin  ////////////////////
    /**
     * MESSAGE_LOGOUT_FAILURE_TOKEN_NULL
     */
    public static final String MESSAGE_LOGOUT_FAILURE_TOKEN_NULL = "注销失败，Token不能为空";

    /**
     * 注销失败，用户信息不存在
     */
    public static final String MESSAGE_LOGOUT_FAILURE_MEMBER_INFO_ERROR = "注销失败，用户信息不存在";

    /**
     * 注销成功
     */
    public static final String MESSAGE_LOGOUT_SUCCESS = "注销成功";

    //////////////////// Logout（注销），End  ////////////////////


    //////////////////// Api版本号，Begin  ////////////////////

    /**
     * API版本号，不可以为空
     */
    public static final String MESSAGE_API_VERSION_NULL = "API版本号，不可以为空";
    //////////////////// Api版本号，End  ////////////////////
    /**
     * 目标POJO实例为null
     */
    public static final String MESSAGE_INFO_NULL = "目标POJO实例为null";

    /**
     * 操作失败
     */
    public static final String MESSAGE_INFO_ERROR = "操作失败";


}
