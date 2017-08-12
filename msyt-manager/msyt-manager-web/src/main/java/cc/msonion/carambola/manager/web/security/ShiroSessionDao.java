/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */


package cc.msonion.carambola.manager.web.security;

/**
 * @Title: MySessionDao.java
 * @Package: cc.msonion.carambola.manager.web.security
 * @Description: 自定义shiro Sessiondao
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年07月07日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年07月07日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.cache.service.DynamicCacheService;
import cc.msonion.carambola.parent.common.constants.MsOnionDynamicRedisKeyConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionSerializeUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @ClassName: MySessionDao
 * @Description: 自定义shiro Sessiondao
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年07月07日
 */
public class ShiroSessionDao extends CachingSessionDAO {

    /**
     * cacheService
     */
    @Autowired
    private DynamicCacheService cacheService;


    /**
     * msOnionLogger
     */
    @Autowired
    private MsOnionLogger msOnionLogger;

    /**
     * 设置会话的过期时间 通过配置文件注入
     */
    private int seconds = 0;


    /**
     * 重写CachingSessionDAO中readSession方法，如果Session中没有登陆信息就调用doReadSession方法从Redis中重读
     */
    @Override
    public Session readSession(Serializable sessionId) {
        Session session = getCachedSession(sessionId);
        if (session == null
                || session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
            session = this.doReadSession(sessionId);
            if (session == null) {
                msOnionLogger.info(getClass().getName(), "There is no session with id [" + sessionId + "]");
            } else {
                // 缓存
                cache(session, session.getId());
            }
        }
        return session;
    }

    @Override
    protected Serializable doCreate(Session session) {
        // 创建一个Id并设置给Session
        Serializable sessionId = this.generateSessionId(session);
        assignSessionId(session, sessionId);
        String redisSessionKey = getRedisSessionKey(sessionId);
        try {
            cacheService.setForRedisWithSecurity(redisSessionKey, MsOnionSerializeUtils.serializeToString((Serializable) session), seconds);
            msOnionLogger.info(getClass().getName(), String.format("## sessionId %s 被创建，存入redis中", sessionId));
        } catch (MsOnionException e) {
            msOnionLogger.error(getClass().getName(), e, "## 创建Session失败");
        }
        return sessionId;


    }

    /**
     * 通过sessionid 读取没有过期的session
     *
     * @param sessionId sessionId
     * @return Session
     */
    public Session doReadSessionWithoutExpire(Serializable sessionId) {
        Session session = null;
        String redisSessionKey = getRedisSessionKey(sessionId);
        String sessionStr = null;
        try {
            sessionStr = cacheService.getFromRedisWithSecurity(redisSessionKey);
            if (StringUtils.isNotBlank(sessionStr)) {
                session = MsOnionSerializeUtils.deserializeFromString(sessionStr);
            }
        } catch (MsOnionException e) {
            msOnionLogger.error(getClass().getName(), e, "## 读取Session失败");
        }
        return session;

    }


    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 第一次访问站点时
        Session session = null;
        String redisSessionKey = getRedisSessionKey(sessionId);
        String sessionStr = null;
        try {
            sessionStr = cacheService.getFromRedisWithSecurity(redisSessionKey);
            if (StringUtils.isNotBlank(sessionStr)) {
                session = MsOnionSerializeUtils.deserializeFromString(sessionStr);
                // 重置Redis中缓存过期时间
                cacheService.expireForRedis(redisSessionKey, seconds);
               // msOnionLogger.info(getClass().getName(), String.format("## sessionId %s 被读取", sessionId));
            }
        } catch (MsOnionException e) {
            msOnionLogger.error(getClass().getName(), e, "## 读取Session失败");
        }
        return session;
    }


    @Override
    protected void doUpdate(Session session) {
        //如果会话过期/停止 没必要再更新了
        try {
            if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
                return;
            }
            String redisSessionKey = getRedisSessionKey(session.getId());
            cacheService.setForRedisWithSecurity(redisSessionKey, MsOnionSerializeUtils.serializeToString((Serializable) session), seconds);
        } catch (Exception e) {
            msOnionLogger.error(getClass().getName(), e, "## ValidatingSession error");
        }
    }

    @Override
    protected void doDelete(Session session) {
        try {
            String redisSessionKey = getRedisSessionKey(session.getId());
            cacheService.delKeyForRedis(redisSessionKey);
            msOnionLogger.info(getClass().getName(), String.format("## sessionId %s 被删除", session.getId()));
        } catch (MsOnionException e) {
            e.printStackTrace();
            msOnionLogger.error(getClass().getName(), e, "## 删除Session失败");
        }
    }

    /**
     * 获取redis中sessionid 的key
     *
     * @param sessionId sessionId
     * @return redisKey
     */
    private String getRedisSessionKey(Serializable sessionId) {
        // 替换 -
        String sid = sessionId.toString().replace("-", "").toUpperCase();
        return MsOnionDynamicRedisKeyConstants.REDIS_SESSION_KEY_PREFIX + sid;
    }

    /**
     * @return seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * @param seconds seconds
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
