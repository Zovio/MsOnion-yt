package test.msonion.carambola.parent.ext.service.member;

/**
 * Created by HeroCao on 2017/5/19.
 */
public final class TestMemberConstants {

    private TestMemberConstants() { }

    /**
     * V2.0.0
     */
    public static final String API_VERSION = "V2.0.0";

    //////////////////// Token，Begin  //////////////////////////////

    /**
     * 异常状态码：Token非法
     */
//	public static final int EXCEPTION_STATUS_TOKEN_ILLEGAL = 1000100;

    //////////////////// Token，End  //////////////////////////////


    //////////////////// 注销（Logout）、登录，Begin  //////////////////////////////

//	/**
//	 * 状态码：注销失败
//	 */
//	public static final int STATUS_LOGOUT_FAILURE = 1000200;
//	/**
//	 * 状态码：登录失败
//	 */
//	public static final int STATUS_LOGIN_FAILURE = 1000300;

    //////////////////// 注销（Logout）、登录，End  //////////////////////////////


    //////////////////// 用户名（成员名）和密码等基本信息，Begin  //////////////////////////////

    /**
     * @Fields MEMBER_NAME_MIN_LENGTH : 用户名最小长度，4个字符
     */
    public static final int MEMBER_NAME_MIN_LENGTH = 4;

    /**
     * @Fields MEMBER_NAME_MAX_LENGTH : 用户名最大长度，20个字符
     */
    public static final int MEMBER_NAME_MAX_LENGTH = 20;

    /**
     * @Fields MEMBER_PASSWORD_MIN_LENGTH : 密码最小长度，6 个字符
     */
    public static final int MEMBER_PASSWORD_MIN_LENGTH = 6;

    /**
     * @Fields MEMBER_PASSWORD_MAX_LENGTH : 密码最大长度，20个字符
     */
    public static final int MEMBER_PASSWORD_MAX_LENGTH = 20;

    /**
     * @Fields MEMBER_PASSWORD_ENCODE_MIN_LENGTH : 加密后密码最小长度，32位
     */
    public static final int MEMBER_PASSWORD_ENCODE_MIN_LENGTH = 32;

    /**
     * @Fields MEMBER_PASSWORD_ENCODE_MAX_LENGTH : 加密后密码最大长度，32位，MD5是32位
     */
    public static final int MEMBER_PASSWORD_ENCODE_MAX_LENGTH = 32;
    /**
     * @Fields MEMBER_EMAIL_MIN_LENGTH : 邮箱最小长度，6个字符
     */
    public static final int MEMBER_EMAIL_MIN_LENGTH = 6;

    /**
     * @Fields MEMBER_EMAIL_MAX_LENGTH : Email最大长度，30个字符
     */
    public static final int MEMBER_EMAIL_MAX_LENGTH = 30;
    /**
     * @Fields MEMBER_REMARK_MIN_LENGTH : 备注最小长度，0个字符
     */
    public static final int MEMBER_REMARK_MIN_LENGTH = 0;
    /**
     * @Fields MEMBER_REMARK_MAX_LENGTH : 备注最大长度，50个字符
     */
    public static final int MEMBER_REMARK_MAX_LENGTH = 50;
    /**
     * @Fields MEMBER_TEL_MAX_LENGTH : 电话最大长度，20个字符
     */
    public static final int MEMBER_TEL_MAX_LENGTH = 20;
    /**
     * @Fields MEMBER_EXT_MAX_LENGTH : 扩展最大长度，50个字符
     */
    public static final int MEMBER_EXT_MAX_LENGTH = 50;
    /**
     * @Fields MEMBER_FULLNAME_MAX_LENGTH : 全名最大长度，20个字符
     */
    public static final int MEMBER_FULLNAME_MAX_LENGTH = 20;
    //////////////////// 用户名（成员名）和密码等基本信息，End  //////////////////////////////

}
