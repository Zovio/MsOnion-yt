package test.msonion.carambola.parent.ext.service.member.service;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import test.msonion.carambola.parent.ext.service.member.pojo.TestMember;
import test.msonion.carambola.parent.ext.service.member.pojo.TestMemberExample;

/**
 * Created by HeroCao on 2017/5/19.
 */
public interface TestMemberService  extends MsOnionBaseService<TestMember, TestMemberExample> {

    /**
     * @Title: inspectParameter
     * @Description: 检查参数
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月26日 上午11:01:28
     *
     * @param apiVersion Api版本
     * @param param
     * @param type 参数类型，type为类型，可选参数1、2、3分别代表name、phone、email ，{@link cc.msonion.carambola.member.common.constants.ParamTypeConstants}
     * @param targerIdx  目标idx，当idx不为空时，需要过滤查询不等于idx的数据
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param,
                                   Integer type, Long targerIdx) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: register
     * @Description: 注册成员（公司内部） 
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月26日 下午10:59:47
     *
     * @param apiVersion Api版本
     * @param member
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult register(MsOnionApiVersion apiVersion, TestMember member) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: login
     * @Description: 登录
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月26日 下午1:02:24
     *
     * @param apiVersion Api版本
     * @param name 成员名
     * @param password 密码，明文
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult login(MsOnionApiVersion apiVersion, String name, String password) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: login
     * @Description: 登录
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月26日 下午2:36:04
     *
     * @param apiVersion Api版本
     * @param token
     * @param username
     * @param password 明文密码
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult login(MsOnionApiVersion apiVersion, String token, String username, String password) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: login
     * @Description: 登录
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月26日 下午2:35:26
     *
     * @param token
     * @param sessionExpire
     * @param username
     * @param password 明文密码
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult login(MsOnionApiVersion apiVersion, String token, int sessionExpire, String username, String password) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: loginForSecurity
     * @Description: 安全登录，密码通过加密
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月26日 下午1:04:06
     *
     * @param apiVersion Api版本
     * @param username 用户名
     * @param password 密码，加密
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult loginForSecurity(MsOnionApiVersion apiVersion, String username, String password) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: loginForSecurity
     * @Description: 安全登录，密码通过加密 
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月26日 下午2:14:47
     *
     * @param apiVersion Api版本
     * @param token
     * @param username
     * @param password
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult loginForSecurity(MsOnionApiVersion apiVersion, String token, String username, String password)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: loginForSecurity
     * @Description: 安全登录，密码通过加密 
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月26日 下午2:26:58
     *
     * @param apiVersion Api版本
     * @param token
     * @param sessionExpire Session周期，单位：秒
     * @param username
     * @param password
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult loginForSecurity(MsOnionApiVersion apiVersion, String token, int sessionExpire, String username, String password)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: login
     * @Description: 登录
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月26日 下午3:08:34
     *
     * @param token
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult login(MsOnionApiVersion apiVersion, String token) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: login
     * @Description: 登录
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月26日 下午3:08:22
     *
     * @param apiVersion Api版本
     * @param token
     * @param sessionExpire
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult login(MsOnionApiVersion apiVersion, String token, int sessionExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: logout
     * @Description: 注销
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月26日 下午5:31:32
     *
     * @param apiVersion Api版本
     * @param token
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult logout(MsOnionApiVersion apiVersion, String token) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: update
     * @Description: 更新用户（成员）信息，不包括密码，密码需要加密处理，需要单独对密码进行处理
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月3日 下午6:58:22
     *
     * @param apiVersion
     * @param member
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult update(MsOnionApiVersion apiVersion, TestMember member) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: updatePassword
     * @Description: 更新密码，基于主键idx
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月3日 下午7:03:35
     *
     * @param apiVersion 版本号
     * @param idx 用户（成员）主键
     * @param oldPassword 原始密码（明文密码）
     * @param newPassword 新密码（明文密码）
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult updatePassword(MsOnionApiVersion apiVersion, Long idx, String oldPassword, String newPassword) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: updatePassword
     * @Description: 更新密码，基于Email
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月3日 下午9:04:31
     *
     * @param apiVersion Api版本号
     * @param email 邮箱
     * @param newPassword 新密码（明文密码）
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    MsOnionResult updatePassword(MsOnionApiVersion apiVersion, String email, String newPassword) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @Title: delete
     * @Description: 删除成员
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月7日 下午8:46:43
     *
     * @param apiVersion Api版本号
     * @param idx 成员主键idx
     * @return 返回MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException 异常
     */
    MsOnionResult delete(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;


    /**
     * @Title: selectTestMemberByLoginName
     * @Description: 通过登录名查询成员
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月7日 下午8:46:43
     *
     * @param apiVersion Api版本号
     * @param loginName 登录名
     * @return 返回MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException 异常
     */
    MsOnionResult selectTestMemberByLoginName(MsOnionApiVersion apiVersion, String loginName) throws MsOnionException;


    /**
     * 将邮箱验证token 存入redis中
     *
     * @param key key
     * @param value 值
     * @param expire 生效时间
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException 异常
     */
    void setEmailVefiyCache(String key, String value, int expire) throws MsOnionException;

    /**
     * 获取redis中邮箱验证token
     *
     * @param  key key
     * @return 返回MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException 异常
     */
    String getEmailVefiyCache(String key) throws MsOnionException;

    /**
     * 修改用户成员密码
     * @param msOnionApiVersion 版本号
     * @param curIdx  当前修改人的idx
     * @param curPassword 当前修改人的密码
     * @param idx 修改账号idx
     * @param newPassword 新密码
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    MsOnionResult editTestMemberPassword(MsOnionApiVersion msOnionApiVersion, Long curIdx, String curPassword, Long idx, String newPassword) throws MsOnionException;



}
