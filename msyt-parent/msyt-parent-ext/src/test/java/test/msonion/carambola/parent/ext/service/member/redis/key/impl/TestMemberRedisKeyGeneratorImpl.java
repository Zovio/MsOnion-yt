package test.msonion.carambola.parent.ext.service.member.redis.key.impl;

import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.redis.key.MsOnionRedisKeyGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import test.msonion.carambola.parent.ext.service.member.TestMessageConstants;
import test.msonion.carambola.parent.ext.service.member.pojo.TestMember;
import test.msonion.carambola.parent.ext.service.member.pojo.TestMemberExample;

/**
 * Created by HeroCao on 2017/5/19.
 */
@Component
public class TestMemberRedisKeyGeneratorImpl  extends MsOnionRedisKeyGenerator<TestMember, TestMemberExample> {

    /**
     * EMIAL
     */
    private static final String EMAIL = "EMIAL:";

    /**
     * @param token Token , 用户名 + 密码（加密） # MsOnionSecurityUtils.getToken(name, password)
     * @return 返回MemberSession的Key
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Title: getMemberSessionKey
     * @Description: 成员信息Session存在Redis中的Key
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月26日 下午7:46:12
     */
    public String getMemberSessionKey(String token) throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(token)) {
            throw new MsOnionIllegalArgumentException(TestMessageConstants.MESSAGE_TOKEN_ILLEGAL, MsOnionStatusConstants.STATUS_400);
        }
        return String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());
    }


    /**
     *
     * @param token  Token
     * @return 返回 Key
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException 异常
     */
    public String getMemberEmailKey(String token) throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(token)) {
            throw new MsOnionIllegalArgumentException(TestMessageConstants.MESSAGE_TOKEN_ILLEGAL, MsOnionStatusConstants.STATUS_400);
        }
        return String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), EMAIL, token.toUpperCase());
    }

}
