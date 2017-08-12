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


package cc.msonion.carambola.manager.web.controller.ext;

/**
 * @Title: StaticResourcesVersionUtil.java
 * @Package: cc.msonion.carambola.manager.web.security
 * @Description: 静态资源版本号相关工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月17日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月17日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.cache.service.DynamicCacheService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionDynamicRedisKeyConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionUUIDUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: StaticResourcesVersionUtil
 * @Description: 后台管理 静态资源版本号相关工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月17日
 */
public final class StaticResourcesVersionUtils {

    private StaticResourcesVersionUtils() {
    }

    /**
     * 从redis缓存中获取静态资源版本号，存入session中
     *
     * @param dynamicCacheService dynamicCacheService
     * @param req                 HttpServletRequest 对象
     * @param resp                HttpServletResponse 对象
     * @throws MsOnionException 异常
     */
    public static void initStaticVersionToSession(DynamicCacheService dynamicCacheService, HttpServletRequest req,
                                                  HttpServletResponse resp) throws MsOnionException {


        // 从session中获取静态资源版本号，没有的话则重新生成
        String jsVersion = dynamicCacheService.getFromRedisWithSecurity(MsOnionDynamicRedisKeyConstants.REDIS_JS_VERSION_KEY);
        String cssVersion = dynamicCacheService.getFromRedisWithSecurity(MsOnionDynamicRedisKeyConstants.REDIS_CSS_VERSION_KEY);
        String imgVersion = dynamicCacheService.getFromRedisWithSecurity(MsOnionDynamicRedisKeyConstants.REDIS_IMG_VERSION_KEY);
        // 没有值的话重新set到redis中,放入session中，便于页面获取
        if (StringUtils.isBlank(jsVersion)) {
            jsVersion = MsOnionUUIDUtils.randomUUIDForVersion();
        }
        dynamicCacheService.setForRedisWithSecurity(MsOnionDynamicRedisKeyConstants.REDIS_JS_VERSION_KEY, jsVersion);

        if (StringUtils.isBlank(cssVersion)) {
            cssVersion = MsOnionUUIDUtils.randomUUIDForVersion();
        }
        dynamicCacheService.setForRedisWithSecurity(MsOnionDynamicRedisKeyConstants.REDIS_CSS_VERSION_KEY, cssVersion);
        if (StringUtils.isBlank(imgVersion)) {
            imgVersion = MsOnionUUIDUtils.randomUUIDForVersion();
        }
        dynamicCacheService.setForRedisWithSecurity(MsOnionDynamicRedisKeyConstants.REDIS_IMG_VERSION_KEY, imgVersion);

        ServletContext aplication = req.getServletContext();
        aplication.setAttribute(ManagerConstants.SESSION_JS_VERSION, jsVersion);
        aplication.setAttribute(ManagerConstants.SESSION_CSS_VERSION, cssVersion);
        aplication.setAttribute(ManagerConstants.SESSION_IMG_VERSION, imgVersion);

    }
}
