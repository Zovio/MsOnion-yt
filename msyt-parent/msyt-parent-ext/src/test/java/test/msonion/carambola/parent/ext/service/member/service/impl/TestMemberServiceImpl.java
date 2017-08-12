package test.msonion.carambola.parent.ext.service.member.service.impl;

//import cc.msonion.carambola.parent.common.constants.TestMessageConstants;
//import cc.msonion.carambola.commons.common.enums.TestTableRecordStatus;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.*;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionPojoStringFieldUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import test.msonion.carambola.parent.ext.distributed.TestDistributedAtomicCodeUtils;
import test.msonion.carambola.parent.ext.distributed.TestDistributedAtomicLongUtils;
import test.msonion.carambola.parent.ext.service.member.*;
import test.msonion.carambola.parent.ext.service.member.TestMessageConstants;
import test.msonion.carambola.parent.ext.service.member.pojo.TestMember;
import test.msonion.carambola.parent.ext.service.member.pojo.TestMemberExample;
import test.msonion.carambola.parent.ext.service.member.redis.key.impl.TestMemberRedisKeyGeneratorImpl;
import test.msonion.carambola.parent.ext.service.member.service.TestMemberService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by HeroCao on 2017/5/19.
 */
@Repository
public class TestMemberServiceImpl extends MsOnionServiceExt<TestMember, TestMemberExample> implements TestMemberService {

    /**
     * TestMember的Redis查询缓存是否禁用，false：不禁用（可用），true：禁用，也就是不会使用Redis缓存
     */
    @Value("${msonion_member_is_disabled_redis_query_cache}")
    private boolean isTestMemberDisabledRedisQueryCache;

    /**
     * TestMember的Session，在Redis缓存周期，单位为毫秒
     */
    @Value("${msonion_redis_member_session_expire}")  // 不要忘记了 ${ }
    private int redisTestMemberSessionExpire;

    /**
     * TestMember模块中，Redis的缓存周期，单位为毫秒
     */
    @Value("${msonion_redis_member_cache_expire}")
    private int redisTestMemberCacheExpire;

    /**
     * TestMember模块Redis的Key生成器实现
     */
    @Autowired
    private TestMemberRedisKeyGeneratorImpl memberRedisKeyGeneratorImpl;

    /**
     * @Fields serialVersionUID : 自动生成 serialVersionUID
     */
    private static final long serialVersionUID = 3604626538753404592L;

    /**
     * 检查参数
     *
     * @param param
     * @param type
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param, Integer type, Long targerIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (StringUtils.isBlank(param)) {
                return new MsOnionResult(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PARAMETER_TYPE_ILLEGAL, param);
            }
            // 去掉左右空格
            param = param.trim();
            // 创建 UserExample
            TestMemberExample example = new TestMemberExample();
            TestMemberExample.Criteria criteria = example.createCriteria();
            String msg = null;
            if (TestParamTypeConstants.TYPE_EMAIL == type || TestParamTypeConstants.TYPE_PHONE == type || TestParamTypeConstants.TYPE_NAME == type) {
                List<Short> statuses = new ArrayList<Short>();
                statuses.add(TestTableRecordStatus.NORMAL.getValue());
                statuses.add(TestTableRecordStatus.DISABLE.getValue());
                criteria.andStatusIn(statuses);
                if (targerIdx != null && targerIdx > 0L) {
                    criteria.andIdxNotEqualTo(targerIdx);
                }
                criteria.andNameEqualTo(param);
                //邮箱
                TestMemberExample.Criteria criteria2 = example.createCriteria();
                criteria2.andStatusIn(statuses);
                if (targerIdx != null && targerIdx > 0L) {
                    criteria2.andIdxNotEqualTo(targerIdx);
                }
                criteria2.andEmailEqualTo(param);
                example.or(criteria2);
                //手机号码
                TestMemberExample.Criteria criteria3 = example.createCriteria();
                criteria3.andStatusIn(statuses);
                if (targerIdx != null && targerIdx > 0L) {
                    criteria3.andIdxNotEqualTo(targerIdx);
                }
                criteria3.andPhoneEqualTo(param);
                example.or(criteria3);

                msg = (String) TestParamTypeUtils.getParamMap().get(type);
            }  else if (TestParamTypeConstants.TYPE_CODE == type) {
                List<Short> statuses = new ArrayList<Short>();
                statuses.add(TestTableRecordStatus.NORMAL.getValue());
                statuses.add(TestTableRecordStatus.DISABLE.getValue());
                criteria.andStatusIn(statuses);
                if (targerIdx != null && targerIdx > 0L) {
                    criteria.andIdxNotEqualTo(targerIdx);
                }
                criteria.andCodeEqualTo(param);
                msg = (String) TestParamTypeUtils.getParamMap().get(type);
            } else {
                return new MsOnionResult(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PARAMETER_TYPE_ILLEGAL, type);
            }
            // 查询
            List<TestMember> list = this.queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isEmpty(list)) {
                return MsOnionResult.ok();
            }
            return new MsOnionResult(MsOnionStatusConstants.STATUS_400, msg + "已经存在，不可以使用!", param);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * @param apiVersion Api版本
     * @param member     成員
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult register(MsOnionApiVersion apiVersion, TestMember member)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 用户名，密码，邮箱，手机号码，长度
        // 注册用户，首先校验用户名，手机号码，邮箱是否已经存在
        try {
            if (null == member) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_INFO_NULL);
            }

            MsOnionPojoStringFieldUtils.inspectPojoStringFieldValue(member);
            String name = member.getName();
            String password = member.getPassword();
            String phone = member.getPhone();
            String email = member.getEmail();
            String fullName = member.getFullName();
            String remark = member.getRemark();
            String tel = member.getTel();

            if (StringUtils.isBlank(name)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_NULL);
            }

            if (StringUtils.isBlank(password)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_NULL);
            }

            if (StringUtils.isBlank(phone)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PHONE_NULL);
            }

            if (StringUtils.isBlank(email)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PHONE_NULL);
            }

            if (StringUtils.isNotBlank(tel)) {
                if (tel.length() > TestMemberConstants.MEMBER_TEL_MAX_LENGTH) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_TEL_LENGTH_ILLEGAL);
                } else if (!MsOnionRegexUtils.isTelephone(tel)) {  // 正则表达式校验电话
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_TEL_ILLEGAL);
                }
            }

            if (fullName.length() > TestMemberConstants.MEMBER_FULLNAME_MAX_LENGTH) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_FULLNAME_LENGTH_ILLEGAL);
            }

            if (StringUtils.isNotBlank(remark)) {
                if (remark.length() > TestMemberConstants.MEMBER_REMARK_MAX_LENGTH) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_REMARK_LENGTH_ILLEGAL);
                }
            }


            // 校验长度
            // 用户名，4-20位，注意中文长度，数据库设计中，字段的长度
            if (MsOnionStringUtils.length(name) < TestMemberConstants.MEMBER_NAME_MIN_LENGTH || MsOnionStringUtils.length(name) > TestMemberConstants.MEMBER_NAME_MAX_LENGTH) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_LENGTH_ILLEGAL);
            }
            if (password.length() < TestMemberConstants.MEMBER_PASSWORD_MIN_LENGTH || password.length() > TestMemberConstants.MEMBER_PASSWORD_MAX_LENGTH) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_LENGTH_ILLEGAL);
            }

            if (password.equals(name) || password.equals(email)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UNFRIENDLY);
            }
            if (!MsOnionRegexUtils.isPasswordCompose(password)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_COMPOSE_ILLEGAL);
            }

            // 手机正则表达式
            boolean checkPhone = MsOnionRegexUtils.isMobilephone(phone);
            if (!checkPhone) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PHONE_ILLEGAL);
            }
            // 邮箱正则表达式
            boolean checkEmail = MsOnionRegexUtils.isEmail(email);
            if (!checkEmail) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_EMAIL_FORMAT_ILLEGAL);
            }

            // 邮箱长度校验
            if (email.length() < TestMemberConstants.MEMBER_PASSWORD_MIN_LENGTH || email.length() > TestMemberConstants.MEMBER_PASSWORD_MAX_LENGTH) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_EMAIL_LENGTH_ILLEGAL);
            }

            // 查询数据库，校验用户名
            MsOnionResult result1 = this.inspectParameter(apiVersion, name, TestParamTypeConstants.TYPE_NAME, member.getIdx());
            if (result1.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result1;
            }
            // 查询数据库，校验手机号码
            MsOnionResult result2 = this.inspectParameter(apiVersion, phone, TestParamTypeConstants.TYPE_PHONE, member.getIdx());
            if (result2.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result2;
            }
            // 查询数据库，校验邮箱
            MsOnionResult result3 = this.inspectParameter(apiVersion, email, TestParamTypeConstants.TYPE_EMAIL, member.getIdx());
            if (result3.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result3;
            }

            this.getMsOnionLogger().info(this.getClass().getName(), "注册，原始密码： password=" + password);
            // 密码md5
            String md5 = MsOnionSecurityUtils.md5ForPassword(password);
            this.getMsOnionLogger().info(this.getClass().getName(), "注册，加密之后密码： md5=" + md5);


            // 生成Idx
            // 可能生成失败，可以采取降级或者重新生成！！！
            long idx = TestDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), TestZookeeperConstants.ZK_COUNTER_MEMBER_MEMBER_IDX_PATH);

            // 设置MD5之后的密码
            member.setPassword(md5);
            // 注册的状态：都是未激活
            member.setStatus(TestTableRecordStatus.DISABLE.getValue());
            member.setIdx(idx);

            member.setCode(TestDistributedAtomicCodeUtils.getMemberCode(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy()));

            //设置创建人和修改人
            member.setCreateByMemberIdx(idx);
            member.setUpdateByMemberIdx(idx);

            Long version = TestDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), TestZookeeperConstants.ZK_COUNTER_MEMBER_MEMBER_VESION_IDX_PATH);
            member.setVersion(version);

            this.getMsOnionLogger().info(this.getClass().getName(), "准备注册， member=" + member);

            int result = this.save(apiVersion, member);

            this.getMsOnionLogger().info(this.getClass().getName(), "注册结果， result=" + result);

            if (result > 0) {
                return MsOnionResult.ok(member);
            }

            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_OR_PASSWORD_ERROR);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 登录状态: <br/>
     * 1、MsOnionStatusConstants.STATUS_200：登录成功，同时返回用户信息； <br/>
     * 2、MsOnionStatusConstants.STATUS_401：登录失败，用户未激活； <br/>
     * 3、MsOnionStatusConstants.STATUS_400：登录失败，用户名或者密码非法； <br/>
     *
     * @param name
     * @param password 明文密码
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult login(MsOnionApiVersion apiVersion, String name, String password) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.login(apiVersion, null, this.redisTestMemberSessionExpire, name, password);
    }

    /**
     * 登录状态: <br/>
     * 1、MsOnionStatusConstants.STATUS_200：登录成功，同时返回用户信息； <br/>
     * 2、MsOnionStatusConstants.STATUS_401：登录失败，用户未激活； <br/>
     * 3、MsOnionStatusConstants.STATUS_400：登录失败，用户名或者密码非法； <br/>
     *
     * @param token
     * @param memberName
     * @param password   明文密码
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult login(MsOnionApiVersion apiVersion, String token, String memberName, String password)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.login(apiVersion, token, this.redisTestMemberSessionExpire, memberName, password);
    }

    /**
     * 登录状态: <br/>
     * 1、MsOnionStatusConstants.STATUS_200：登录成功，同时返回用户信息； <br/>
     * 2、MsOnionStatusConstants.STATUS_401：登录失败，用户未激活； <br/>
     * 3、MsOnionStatusConstants.STATUS_400：登录失败，用户名或者密码非法； <br/>
     *
     * @param token
     * @param sessionExpire
     * @param memberName
     * @param password
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult login(MsOnionApiVersion apiVersion, String token, int sessionExpire, String memberName, String password)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (StringUtils.isBlank(memberName)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_NULL);
            }

            if (StringUtils.isBlank(password)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_NULL);
            }

            memberName = memberName.trim();
            password = password.trim();

            if (memberName.length() < TestMemberConstants.MEMBER_NAME_MIN_LENGTH || memberName.length() > TestMemberConstants.MEMBER_NAME_MAX_LENGTH) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_OR_PASSWORD_ERROR);
            }

            if (password.length() < TestMemberConstants.MEMBER_PASSWORD_MIN_LENGTH || password.length() > TestMemberConstants.MEMBER_PASSWORD_MAX_LENGTH) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_OR_PASSWORD_ERROR);
            }

            // 加密密码
            password = MsOnionSecurityUtils.md5ForPassword(password);

            return loginForSecurity(apiVersion, token, sessionExpire, memberName, password);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 登录状态: <br/>
     * 1、MsOnionStatusConstants.STATUS_200：登录成功，同时返回用户信息； <br/>
     * 2、MsOnionStatusConstants.STATUS_401：登录失败，用户未激活； <br/>
     * 3、MsOnionStatusConstants.STATUS_400：登录失败，用户名或者密码非法； <br/>
     *
     * @param memberName
     * @param password
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult loginForSecurity(MsOnionApiVersion apiVersion, String memberName, String password)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return loginForSecurity(apiVersion, null, memberName, password);
    }

    /**
     * 登录状态: <br/>
     * 1、MsOnionStatusConstants.STATUS_200：登录成功，同时返回用户信息； <br/>
     * 2、MsOnionStatusConstants.STATUS_401：登录失败，用户未激活； <br/>
     * 3、MsOnionStatusConstants.STATUS_400：登录失败，用户名或者密码非法； <br/>
     *
     * @param token
     * @param memberName
     * @param password
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult loginForSecurity(MsOnionApiVersion apiVersion, String token, String memberName, String password)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.loginForSecurity(apiVersion, token, this.redisTestMemberSessionExpire, memberName, password);
    }

    /**
     * 登录状态: <br/>
     * 1、MsOnionStatusConstants.STATUS_200：登录成功，同时返回用户信息； <br/>
     * 2、MsOnionStatusConstants.STATUS_401：登录失败，用户未激活； <br/>
     * 3、MsOnionStatusConstants.STATUS_400：登录失败，用户名或者密码非法； <br/>
     *
     * @param token         用户名 + 密码（加密）==》 MsOnionSecurityUtils.getToken(name, password);
     * @param sessionExpire
     * @param name
     * @param password      加密之后密码
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult loginForSecurity(MsOnionApiVersion apiVersion, String token, int sessionExpire, String name, String password)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(name)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_NULL);
        }

        if (StringUtils.isBlank(password)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_NULL);
        }

        // 去掉左右空格、字符串
        name = name.trim();
        password = password.trim();

        if (name.length() < TestMemberConstants.MEMBER_NAME_MIN_LENGTH || name.length() > TestMemberConstants.MEMBER_NAME_MAX_LENGTH) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_OR_PASSWORD_ERROR);
        }

        // MD5 --> 32 位密码
        if (password.length() < TestMemberConstants.MEMBER_PASSWORD_ENCODE_MIN_LENGTH
                || password.length() > TestMemberConstants.MEMBER_PASSWORD_ENCODE_MAX_LENGTH) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_OR_PASSWORD_ERROR);
        }

        // 查询Redis
        try {
            // 如果Token为null，先获取Token
            if (StringUtils.isBlank(token)) {
                token = MsOnionSecurityUtils.getToken(name, password);
                this.getMsOnionLogger().info(this.getClass().getName(), "loginForSecurity # 获取Token=" + token);
            }
            MsOnionResult msOnionResult = this.login(apiVersion, token, sessionExpire);
            // 200： 登录成功， 401： 未激活，  400： 登录失败，Token非法
            if (MsOnionStatusConstants.STATUS_200 == msOnionResult.getStatus() || MsOnionStatusConstants.STATUS_401 == msOnionResult.getStatus()) {
                return msOnionResult;
            }
        } catch (Exception e) {
            this.getMsOnionLogger().warn(this.getClass().getName(), e);
        }

        try {

            // 密码 MD5 ， 这里的密码已经加密了
            //		password = MsOnionSecurityUtils.md5ForPassword(password);

            // 查询数据库
            TestMemberExample example = new TestMemberExample();
            TestMemberExample.Criteria criteria = example.createCriteria();
            // 已经激活的
            //		criteria.andStatusEqualTo(TestTableRecordStatus.NORMAL.getValue());
            // 激活的 和 未激活的  用户名
            List<Short> statuses = new ArrayList<Short>();
            statuses.add(TestTableRecordStatus.NORMAL.getValue());
            statuses.add(TestTableRecordStatus.DISABLE.getValue());
            criteria.andStatusIn(statuses);
            criteria.andNameEqualTo(name);
            criteria.andPasswordEqualTo(password);
            //邮箱
            TestMemberExample.Criteria criteria2 = example.createCriteria();
            criteria2.andStatusIn(statuses);
            criteria2.andPasswordEqualTo(password);
            criteria2.andEmailEqualTo(name);
            example.or(criteria2);
            //手机号码
            TestMemberExample.Criteria criteria3 = example.createCriteria();
            criteria3.andStatusIn(statuses);
            criteria3.andPasswordEqualTo(password);
            criteria3.andPhoneEqualTo(name);
            example.or(criteria3);

            List<TestMember> list = this.queryByExample(apiVersion, example);

            if (MsOnionCollectionUtils.isEmpty(list)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_OR_PASSWORD_ERROR);
            }

            TestMember member = list.get(0);

            // Token
            //		token = UUID.randomUUID().toString();
            // 用户名+密码（加密）==》 MD5 ==》 Token
            //		token = MsOnionSecurityUtils.getToken(memberName, password);
            if (StringUtils.isBlank(token)) {
                token = MsOnionSecurityUtils.getToken(name, password);
            }

            //		String key = MsOnionRedisUtils.getUserSessionKey(token);

            String key = this.memberRedisKeyGeneratorImpl.getMemberSessionKey(token);

            this.getMsOnionLogger().info(this.getClass().getName(), " , loginForSecurity , Session的Key="
                    + key + " , token=" + token);

            // 必须把用户密码已经其他敏感数据去掉
            member.setPassword(null);  // 这里的密码是MD5之后的，其实也可以不处理，不过尽量不要包括密码

            String json = MsOnionJsonUtils.objectToJson(member);
            this.getMsOnionLogger().info(this.getClass().getName(), " , loginForSecurity , 从Session获取用户信息，json="
                    + json);

            // 加密的Session
            String session = MsOnionRedisUtils.getEncodeUserSession(json);
            this.getMsOnionLogger().info(this.getClass().getName(), " , loginForSecurity , 从Session获取用户信息，加密session="
                    + session);

            // 把数据缓存到Redis中，同时设置缓存周期
            this.setForRedis(key, session, this.redisTestMemberSessionExpire);

            // 登录成功，返回不加密的，用户信息
            if (member.getStatus() == TestTableRecordStatus.NORMAL.getValue()) {  // 已经激活、正常的
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, token, member);
            } else if (member.getStatus() == TestTableRecordStatus.DISABLE.getValue()) {  // 未激活
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_401, TestMessageConstants.MESSAGE_MEMBER_ACCOUNT_DISABLE);
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_OR_PASSWORD_ERROR);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @param token 成员名+密码（加密）==》 MD5 ==》 Token
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult login(MsOnionApiVersion apiVersion, String token) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.login(apiVersion, token, this.redisTestMemberSessionExpire);
    }

    /**
     * 登录 <br/>
     * <p>MsOnionStatusConstants.STATUS_200：登录成功，可以通过MsOnionResult实例的msg获取到Session</p>
     * <p>MsOnionStatusConstants.STATUS_401：登录失败，用户未激活</p>
     * <p>MsOnionStatusConstants.STATUS_400：登录失败，Token非法</p>
     *
     * @param token         成员名+密码（加密）==》 MD5 ==》 Token
     * @param sessionExpire
     * @return 如果 status是200就登录成功，可以通过data获取加密字符串session
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult login(MsOnionApiVersion apiVersion, String token, int sessionExpire)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (StringUtils.isBlank(token)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_TOKEN_ERROR);
            }
            // 获取Key
            String key = this.memberRedisKeyGeneratorImpl.getMemberSessionKey(token);
//            if (StringUtils.isNoneBlank(key) && this.getMsOnionJedisAdapter().exists(key)) {  // 必须判断 Key 是否存在 ！！！
            if (StringUtils.isNoneBlank(key) && this.existsKeyFromRedis(key)) {
                // 加密的Session
//                String session = this.getMsOnionJedisAdapter().get(key);
                String session = this.getFromRedis(key);
                if (StringUtils.isBlank(session)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_TOKEN_ERROR);
                }

                // 解密
                String json = MsOnionRedisUtils.getDecodeUserSession(session);
                TestMember member = MsOnionJsonUtils.jsonToPojo(json, TestMember.class);
                if (null != member) {
                    // 延长过期时间
//                    this.getMsOnionJedisAdapter().expire(key, sessionExpire);
                    this.expireForRedis(key, sessionExpire);

                    // 登录成功之后，返回用户信息（不可以包括密码）
                    // 去掉用密码
                    member.setPassword(null);
                    if (member.getStatus() == TestTableRecordStatus.NORMAL.getValue()) {  // 可用的，激活的
                        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, token, member);
                    } else if (member.getStatus() == TestTableRecordStatus.DISABLE.getValue()) {  // 未激活
                        return MsOnionResult.build(MsOnionStatusConstants.STATUS_401, TestMessageConstants.MESSAGE_MEMBER_ACCOUNT_DISABLE, member);
                    }
                }
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_TOKEN_ERROR);

        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 注销
     *
     * @param apiVersion Api版本
     * @param token      token
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult logout(MsOnionApiVersion apiVersion, String token) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (StringUtils.isBlank(token)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_LOGOUT_FAILURE_TOKEN_NULL);
            }

            String key = this.memberRedisKeyGeneratorImpl.getMemberSessionKey(token);

            if (StringUtils.isBlank(key)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_LOGOUT_FAILURE_MEMBER_INFO_ERROR);
            }

//            Long result = this.getMsOnionJedisAdapter().del(key);

            Long result = this.delKeyForRedis(key);

            if (result > 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_LOGOUT_SUCCESS);
            }

            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_LOGOUT_FAILURE_MEMBER_INFO_ERROR);

        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * @param apiVersion api版本
     * @param member     成员实例对象
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult update(MsOnionApiVersion apiVersion, TestMember member)
            throws MsOnionIllegalArgumentException, MsOnionException {

        // 对各个字段的值，长度进行校验 ！！！
        MsOnionPojoStringFieldUtils.inspectPojoStringFieldValue(member);
        String phone = member.getPhone();
        String email = member.getEmail();
        String fullName = member.getFullName();
        String remark = member.getRemark();
        String tel = member.getTel();
        String code = member.getCode();

        if (StringUtils.isBlank(phone)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PHONE_NULL);
        }

        if (StringUtils.isBlank(email)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PHONE_NULL);
        }

        if (StringUtils.isNotBlank(tel)) {
            if (tel.length() > TestMemberConstants.MEMBER_TEL_MAX_LENGTH) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_TEL_LENGTH_ILLEGAL);
            } else if (!MsOnionRegexUtils.isTelephone(tel)) {  // 正则表达式校验电话
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_TEL_ILLEGAL);
            }
        }

        if (fullName.length() > TestMemberConstants.MEMBER_FULLNAME_MAX_LENGTH) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_FULLNAME_LENGTH_ILLEGAL);
        }

        if (StringUtils.isNotBlank(remark)) {
            if (remark.length() > TestMemberConstants.MEMBER_REMARK_MAX_LENGTH) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_REMARK_LENGTH_ILLEGAL);
            }
        }

        // 查询数据库，校验用户编码
        MsOnionResult result1 = this.inspectParameter(apiVersion, code, TestParamTypeConstants.TYPE_CODE, member.getIdx());
        if (result1.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result1;
        }
        // 查询数据库，校验手机号码
        MsOnionResult result2 = this.inspectParameter(apiVersion, phone, TestParamTypeConstants.TYPE_PHONE, member.getIdx());
        if (result2.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result2;
        }
        // 查询数据库，校验邮箱
        MsOnionResult result3 = this.inspectParameter(apiVersion, email, TestParamTypeConstants.TYPE_EMAIL, member.getIdx());
        if (result3.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result3;
        }

        // 查询，在更新
        TestMember m = this.queryByPrimaryKey(apiVersion, member.getIdx());
        m.setFullName(member.getFullName());
        m.setSex(member.getSex());
        m.setCode(member.getCode());
        m.setPhone(member.getPhone());
        m.setTel(member.getTel());
        m.setEmail(member.getEmail());
        m.setRemark(member.getRemark());
        m.setUpdateTime(member.getUpdateTime());
        m.setUpdateByMemberIdx(member.getUpdateByMemberIdx());
        m.setMemberCateIdx(member.getMemberCateIdx());

        // 全部修改
        int result = this.updateByPrimaryKey(apiVersion, m);

        if (result >= 1) {
            // 更新用户信息，不包括密码，密码为 null ，因此必须重新查询，因为底层已经使用缓存，不影响性能
//            TestMember m = this.queryByPrimaryKey(apiVersion, member.getIdx());
            // 更新成功
            String token = MsOnionSecurityUtils.getToken(member.getName(), member.getPassword());

            String key = this.memberRedisKeyGeneratorImpl.getMemberSessionKey(token);

            this.getMsOnionLogger().info(this.getClass().getName(), "更新成功之后， token=" + token + "， key=" + key);

            // 0 没有删除成功！！！
            // 如果已经登录过了，更新Session缓存信息，如果没有登录过直接删除
//            if (this.getMsOnionJedisAdapter().exists(key)) {
            if (this.existsKeyFromRedis(key)) {
                // 如果缓存过去，Key就会被删除了，因此不存在，用户好久没有登录，但是更新缓存时间问题。

//                String key = this.memberRedisKeyGeneratorImpl.getTestMemberSessionKey(token);

                // 必须把用户密码已经其他敏感数据去掉
                member.setPassword(null);  // 这里的密码是MD5之后的，其实也可以不处理，不过尽量不要包括密码

                String json = MsOnionJsonUtils.objectToJson(member);

                this.getMsOnionLogger().info(this.getClass().getName(), "更新用户信息，更新Token , member=" + member);

                this.getMsOnionLogger().info(this.getClass().getName(), "更新用户信息，更新Token , json=" + json);

                // 加密的Session
                String session = MsOnionRedisUtils.getEncodeUserSession(json);

//                String setResult = this.getMsOnionJedisAdapter().set(key, session);

                String setResult = this.setForRedis(key, session);

                // 不需要设置缓存周期，因为不是用户自己登录，而是编辑信息，登录的时候，会更新缓存周期

                this.getMsOnionLogger().info(this.getClass().getName(), "更新用户信息，更新Token ， Session缓存是否成功 setResult=" + setResult);

            }

            return MsOnionResult.ok(m);
        }

        return new MsOnionResult(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_UPDATE_FULLNAME, null);
    }

    /**
     * (non-Javadoc)
     *
     * @param apiVersion
     * @param idx
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult updatePassword(MsOnionApiVersion apiVersion, Long idx, String oldPassword, String newPassword)
            throws MsOnionIllegalArgumentException, MsOnionException {

        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_IDX_ILLEGAL);
        }

        if (StringUtils.isBlank(oldPassword)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_OLD_PASSWORD_ILLEGAL);
        }

        if (StringUtils.isBlank(newPassword)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_NEW_PASSWORD_ILLEGAL);
        }

        // 去掉左右空格
        oldPassword = oldPassword.trim();
        newPassword = newPassword.trim();


        if (!MsOnionRegexUtils.isPasswordCompose(newPassword)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_COMPOSE_ILLEGAL);
        }

        // 对原始密码进行加密
        oldPassword = MsOnionSecurityUtils.md5ForPassword(oldPassword);
        // 对新密码进行加密
        newPassword = MsOnionSecurityUtils.md5ForPassword(newPassword);

        // 根据主键idx和原始密码，查询用户（成员）
        TestMemberExample example = new TestMemberExample();
        TestMemberExample.Criteria criteria = example.createCriteria();
        // 设置主键idx
        criteria.andIdxEqualTo(idx);
        criteria.andPasswordEqualTo(oldPassword);

        List<TestMember> list = this.queryByExample(apiVersion, example);

        if (MsOnionCollectionUtils.isEmpty(list)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_OLD_PASSWORD_ERROR);
        }


        TestMember member = list.get(0);
//        member.setIdx(idx);
        member.setPassword(newPassword);
        member.setUpdateTime(new Date());

        if (newPassword.equals(MsOnionSecurityUtils.md5ForPassword(member.getName())) || newPassword.equals(member.getEmail())) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UNFRIENDLY);
        }

        int result = this.updateByPrimaryKey(apiVersion, member);

        if (result >= 1) {
            this.getMsOnionLogger().info(getClass().getName(), "## 修改密码成功 :)" + member.getName());
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_SUCCESS);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE);
    }

    /**
     * 修改密码，基于Email
     *
     * @param apiVersion  Api版本号
     * @param email       邮箱
     * @param newPassword 新密码
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult updatePassword(MsOnionApiVersion apiVersion, String email,  String newPassword)
            throws MsOnionIllegalArgumentException, MsOnionException {

        // 必须校验API版本号
        if (null == apiVersion || StringUtils.isBlank(apiVersion.getRequestApiVersion())) {
            throw new MsOnionIllegalArgumentException(TestMessageConstants.MESSAGE_API_VERSION_NULL, MsOnionStatusConstants.STATUS_400);

        }

        if (StringUtils.isBlank(email)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_EMAIL_NULL);
        }
        if (StringUtils.isBlank(newPassword)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_NEW_PASSWORD_ILLEGAL);
        }

        // 去掉左右空格
        email = email.trim();
        newPassword = newPassword.trim();

        // 邮箱正则表达式
        boolean isEmail = MsOnionRegexUtils.isEmail(email);
        if (!isEmail) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_EMAIL_ILLEGAL);
        }

        // 邮箱长度校验
        if (email.length() < TestMemberConstants.MEMBER_PASSWORD_MIN_LENGTH || email.length() > TestMemberConstants.MEMBER_PASSWORD_MAX_LENGTH) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_EMAIL_LENGTH_ILLEGAL);
        }
        // 新密码长度校验
        if (newPassword.length() < TestMemberConstants.MEMBER_PASSWORD_MIN_LENGTH || newPassword.length() > TestMemberConstants.MEMBER_PASSWORD_MAX_LENGTH) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_NEW_PASSWORD_ILLEGAL);
        }

        if (!MsOnionRegexUtils.isPasswordCompose(newPassword)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_COMPOSE_ILLEGAL);
        }

        newPassword = MsOnionSecurityUtils.md5ForPassword(newPassword);

        // 邮箱是全局唯一的 ，但是，原先系统中，不是邮箱，而是Code（编码）

        TestMemberExample example1 = new TestMemberExample();
        TestMemberExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andEmailEqualTo(email);
//		criteria.andPasswordEqualTo(oldPassword);

        // 查询
        List<TestMember> list1 = this.queryByExample(apiVersion, example1);

        if (MsOnionCollectionUtils.isEmpty(list1)) {
            // 邮箱、密码可能错误，提示不会很明确
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_EMAIL_ERROR);
        }
        TestMember member = list1.get(0);
        member.setEmail(email);
        member.setPassword(newPassword);
        member.setUpdateTime(new Date());

        if (newPassword.equals(MsOnionSecurityUtils.md5ForPassword(member.getName()))
                || newPassword.equals(MsOnionSecurityUtils.md5ForPassword(member.getEmail()))) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UNFRIENDLY);
        }

        // 只更新有值的字段（密码），其他的不更新
//        int result = this.updateByPrimaryKeySelective(apiVersion, member);

        int result = this.updateByPrimaryKey(apiVersion, member);

        if (result >= 1) {
            this.getMsOnionLogger().info(getClass().getName(), "## 修改密码成功 :)" + member.getName());
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_SUCCESS);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE);

    }

    /**
     * (non-Javadoc)
     *
     * @param idx
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult delete(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {

        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_DELETE_BY_IDX_ILLEGAL);
        }

        // 用户名 和 密码
        // 不可以直接删除
        TestMember member = this.queryByPrimaryKey(apiVersion, idx);
        if (null == member) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_DELETE_FAILURE);
        }
        // 其实就是软删除
        int result = this.deleteByPrimaryKey(apiVersion, idx);


        if (result >= 1) {
            // 必须把Session缓存删除

            String token = MsOnionSecurityUtils.getToken(member.getName(), member.getPassword());

            String key = this.memberRedisKeyGeneratorImpl.getMemberSessionKey(token);

//            Long delResult = this.getMsOnionJedisAdapter().del(key);
            Long delResult = this.delKeyForRedis(key);
            this.getMsOnionLogger().info(this.getClass().getName(), "删除Token的缓存是否成功 delResult=" + delResult);

            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, TestMessageConstants.MESSAGE_MEMBER_DELETE_SUCCESS);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_DELETE_FAILURE);
    }

    @Override
    public MsOnionResult selectTestMemberByLoginName(MsOnionApiVersion apiVersion, String loginName) throws MsOnionException {
        if (StringUtils.isBlank(loginName)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_NULL);
        }
        // 去掉左右空格、字符串
        loginName = loginName.trim();
        if (loginName.length() < TestMemberConstants.MEMBER_NAME_MIN_LENGTH || loginName.length() > TestMemberConstants.MEMBER_NAME_MAX_LENGTH) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_OR_PASSWORD_ERROR);
        }
        try {
            // 查询数据库
            TestMemberExample example = new TestMemberExample();
            TestMemberExample.Criteria criteria = example.createCriteria();
            // 激活的 和 未激活的  用户名
            List<Short> statuses = new ArrayList<Short>();
            statuses.add(TestTableRecordStatus.NORMAL.getValue());
            statuses.add(TestTableRecordStatus.DISABLE.getValue());
            criteria.andStatusIn(statuses);
            criteria.andNameEqualTo(loginName);
            //邮箱
            TestMemberExample.Criteria criteria2 = example.createCriteria();
            criteria2.andStatusIn(statuses);
            criteria2.andEmailEqualTo(loginName);
            example.or(criteria2);
            //手机号码
            TestMemberExample.Criteria criteria3 = example.createCriteria();
            criteria3.andStatusIn(statuses);
            criteria3.andPhoneEqualTo(loginName);
            example.or(criteria3);

            List<TestMember> list = this.queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isEmpty(list)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_OR_PASSWORD_ERROR);
            }
            TestMember member = list.get(0);
            this.getMsOnionLogger().info(this.getClass().getName(), " , selectTestMemberByLoginName , Session , loginName=" + loginName);
            // 必须把用户密码已经其他敏感数据去掉
            member.setPassword(null);
            // 登录成功，返回不加密的，用户信息
            if (member.getStatus() == TestTableRecordStatus.NORMAL.getValue()) {  // 已经激活、正常的
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, "", member);
            } else if (member.getStatus() == TestTableRecordStatus.DISABLE.getValue()) {  // 未激活
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_401, TestMessageConstants.MESSAGE_MEMBER_ACCOUNT_DISABLE);
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_NAME_OR_PASSWORD_ERROR);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    @Override
    public void setEmailVefiyCache(String key, String value, int expire) throws MsOnionException {
        String memberEmailKey = memberRedisKeyGeneratorImpl.getMemberEmailKey(key);
        this.getMsOnionLogger().info(getClass().getName(), "## 邮箱验证redis key:" + memberEmailKey);
        setForRedis(memberEmailKey, MsOnionSecurityUtils.encodeForRedis(value), expire);
    }

    @Override
    public String getEmailVefiyCache(String key) throws MsOnionException {
        String memberEmailKey = memberRedisKeyGeneratorImpl.getMemberEmailKey(key);
        return MsOnionSecurityUtils.decodeForRedis(getFromRedis(memberEmailKey));
    }

    /**
     * 修改用户成员密码
     *
     * @param msOnionApiVersion 版本号
     * @param curIdx            当前修改人的idx
     * @param curPassword       当前修改人的密码
     * @param idx               修改账号idx
     * @param newPassword       新密码
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @Override
    public MsOnionResult editTestMemberPassword(MsOnionApiVersion msOnionApiVersion, Long curIdx, String curPassword, Long idx, String newPassword) throws MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_IDX_ILLEGAL);
        }

        if (null == curIdx || curIdx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "当前操作人账号非法");
        }

        if (StringUtils.isBlank(curPassword)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "当前操作人密码非法");
        }

        if (StringUtils.isBlank(newPassword)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_NEW_PASSWORD_ILLEGAL);
        }

        if (!MsOnionRegexUtils.isPasswordCompose(newPassword.trim())) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_COMPOSE_ILLEGAL);
        }

        TestMember curTestMember = queryByPrimaryKey(msOnionApiVersion, Long.valueOf(curIdx));
        if (!Optional.of(curTestMember).isPresent()){
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "当前操作人为空");
        }

        if (!curTestMember.getPassword().equals(MsOnionSecurityUtils.md5ForPassword(curPassword))){
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "当前操作人密码不正确");
        }

        TestMember member = queryByPrimaryKey(msOnionApiVersion, Long.valueOf(idx));
        if (!Optional.of(member).isPresent()){
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "用户不存在");
        }
        member.setPassword(MsOnionSecurityUtils.md5ForPassword(newPassword));
        member.setUpdateByMemberIdx(curIdx);
        member.setUpdateTime(new Date());
        int result = this.updateByPrimaryKey(msOnionApiVersion, member);

        if (result >= 1) {
            this.getMsOnionLogger().info(getClass().getName(), "## 修改密码成功 :)" + member.getName());
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_SUCCESS);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, TestMessageConstants.MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE);
    }
}
