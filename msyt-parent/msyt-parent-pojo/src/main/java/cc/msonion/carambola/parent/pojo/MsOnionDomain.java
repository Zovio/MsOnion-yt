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

package cc.msonion.carambola.parent.pojo;

import cc.msonion.carambola.parent.common.utils.MsOnionDevelopUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 封装生产环境、预览环境、稳定环境、测试环境、开发环境的域名
 *
 * @Title: MsOnionDomain.java
 * @Package: cc.msonion.carambola.parent.pojo
 * @Description: 封装生产环境、预览环境、稳定环境、测试环境、开发环境的域名
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 上午10:06:58
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 上午10:06:58
 * @Modify-version: V2.0.0
 * @Modify-description: 封装生产环境、预览环境、测试环境、开发环境的域名
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年3月05日 上午12:06:58
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：稳定环境，修改：调整稳定环境相关实现
 */

/**
 * 封装生产环境、预览环境、稳定环境、测试环境、开发环境的域名
 *
 * @ClassName: MsOnionDomain
 * @Description: 封装生产环境、预览环境、稳定环境、测试环境、开发环境的域名
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 上午10:06:58
 */
@Component
public class MsOnionDomain extends MsOnionEnvironment {

    /**
     * @Fields serialVersionUID :  自动生成serialVersionUID
     */
    private static final long serialVersionUID = -4270769067749651433L;

    //// Begin

    ///////////////// 成员 Member，Begin /////////////////
    /**
     * 成员API的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_member_api_pro}")
    protected String member_api_pro;

    /**
     * 成员API的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_member_api_pre}")
    protected String member_api_pre;

    /**
     * 成员API的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_member_api_sta}")
    protected String member_api_sta;

    /**
     * 成员API的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_member_api_test}")
    protected String member_api_test;

    /**
     * 成员API的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_member_api_dev}")
    protected String member_api_dev;

    /**
     * 成员服务的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_member_service_pro}")
    protected String member_service_pro;

    /**
     * 成员服务的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_member_service_pre}")
    protected String member_service_pre;

    /**
     * 成员服务的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_member_service_sta}")
    protected String member_service_sta;

    /**
     * 成员服务的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_member_service_test}")
    protected String member_service_test;

    /**
     * 成员服务的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_member_service_dev}")
    protected String member_service_dev;

    /**
     * 成员WEB的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_member_web_pro}")
    protected String member_web_pro;

    /**
     * 成员WEB的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_member_web_pre}")
    protected String member_web_pre;

    /**
     * 成员WEB的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_member_web_sta}")
    protected String member_web_sta;


    /**
     * 成员WEB的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_member_web_test}")
    protected String member_web_test;

    /**
     * 成员WEB的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_member_web_dev}")
    protected String member_web_dev;

    ///////////////// 成员 Member，End /////////////////

    ///////////////// 用户 User，Begin /////////////////

    /**
     * 用户API的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_user_api_pro}")
    protected String user_api_pro;

    /**
     * 用户API的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_user_api_pre}")
    protected String user_api_pre;

    /**
     * 用户API的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_user_api_sta}")
    protected String user_api_sta;

    /**
     * 用户API的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_user_api_test}")
    protected String user_api_test;

    /**
     * 用户API的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_user_api_dev}")
    protected String user_api_dev;

    /**
     * 用户服务的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_user_service_pro}")
    protected String user_service_pro;

    /**
     * 用户服务的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_user_service_pre}")
    protected String user_service_pre;

    /**
     * 用户服务的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_user_service_sta}")
    protected String user_service_sta;

    /**
     * 用户服务的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_user_service_test}")
    protected String user_service_test;

    /**
     * 用户服务的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_user_service_dev}")
    protected String user_service_dev;

    /**
     * 用户服务的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_user_web_pro}")
    protected String user_web_pro;

    /**
     * 用户服务的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_user_web_pre}")
    protected String user_web_pre;

    /**
     * 用户服务的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_user_web_sta}")
    protected String user_web_sta;

    /**
     * 用户服务的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_user_web_test}")
    protected String user_web_test;

    /**
     * 用户服务的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_user_web_dev}")
    protected String user_web_dev;

    ///////////////// 用户 User，End /////////////////

    ///////////////// 后台管理系统 Manager，Begin /////////////////

    /**
     * 系统管理后台API的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_api_pro}")
    protected String manager_api_pro;

    /**
     * 系统管理后台API的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_api_pre}")
    protected String manager_api_pre;

    /**
     * 系统管理后台API的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_api_sta}")
    protected String manager_api_sta;

    /**
     * 系统管理后台API的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_api_test}")
    protected String manager_api_test;

    /**
     * 系统管理后台API的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_api_dev}")
    protected String manager_api_dev;

    /**
     * 系统管理后台服务的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_service_pro}")
    protected String manager_service_pro;

    /**
     * 系统管理后台服务的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_service_pre}")
    protected String manager_service_pre;

    /**
     * 系统管理后台服务的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_service_sta}")
    protected String manager_service_sta;

    /**
     * 系统管理后台服务的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_service_test}")
    protected String manager_service_test;

    /**
     * 系统管理后台服务的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_service_dev}")
    protected String manager_service_dev;

    /**
     * 系统管理后台WEB的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_web_pro}")
    protected String manager_web_pro;

    /**
     * 系统管理后台WEB的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_web_pre}")
    protected String manager_web_pre;

    /**
     * 系统管理后台WEB的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_web_sta}")
    protected String manager_web_sta;

    /**
     * 系统管理后台WEB的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_web_test}")
    protected String manager_web_test;

    /**
     * 系统管理后台WEB的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_manager_web_dev}")
    protected String manager_web_dev;

    ///////////////// 后台管理系统 Manager，Begin /////////////////

    ///////////////// 商品（产品）Item，Begin /////////////////

    /**
     * 产品（商品）API的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_item_api_pro}")
    protected String item_api_pro;

    /**
     * 产品（商品）API的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_item_api_pre}")
    protected String item_api_pre;

    /**
     * 产品（商品）API的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_item_api_sta}")
    protected String item_api_sta;

    /**
     * 产品（商品）API的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_item_api_test}")
    protected String item_api_test;

    /**
     * 产品（商品）API的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_item_api_dev}")
    protected String item_api_dev;

    /**
     * 产品（商品）服务的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_item_service_pro}")
    protected String item_service_pro;

    /**
     * 产品（商品）服务的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_item_service_pre}")
    protected String item_service_pre;

    /**
     * 产品（商品）服务的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_item_service_sta}")
    protected String item_service_sta;

    /**
     * 产品（商品）服务的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_item_service_test}")
    protected String item_service_test;

    /**
     * 产品（商品）服务的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_item_service_dev}")
    protected String item_service_dev;

    /**
     * 产品（商品）WEB的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_item_web_pro}")
    protected String item_web_pro;

    /**
     * 产品（商品）WEB的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_item_web_pre}")
    protected String item_web_pre;

    /**
     * 产品（商品）WEB的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_item_web_sta}")
    protected String item_web_sta;

    /**
     * 产品（商品）WEB的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_item_web_test}")
    protected String item_web_test;

    /**
     * 产品（商品）WEB的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_item_web_dev}")
    protected String item_web_dev;

    ///////////////// 商品（产品）Item，End /////////////////

    ///////////////// 收集器 Collector，Begin /////////////////

    /**
     * 收集器API的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_api_pro}")
    protected String collector_api_pro;

    /**
     * 收集器API的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_api_pre}")
    protected String collector_api_pre;

    /**
     * 收集器API的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_api_sta}")
    protected String collector_api_sta;

    /**
     * 收集器API的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_api_test}")
    protected String collector_api_test;

    /**
     * 收集器API的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_api_dev}")
    protected String collector_api_dev;

    /**
     * 收集器服务的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_service_pro}")
    protected String collector_service_pro;

    /**
     * 收集器服务预览环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_service_pre}")
    protected String collector_service_pre;

    /**
     * 收集器服务稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_service_sta}")
    protected String collector_service_sta;

    /**
     * 收集器服务的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_service_test}")
    protected String collector_service_test;

    /**
     * 收集器服务的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_service_dev}")
    protected String collector_service_dev;

    /**
     * 收集器WEB的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_web_pro}")
    protected String collector_web_pro;

    /**
     * 收集器WEB的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_web_pre}")
    protected String collector_web_pre;

    /**
     * 收集器WEB的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_web_sta}")
    protected String collector_web_sta;

    /**
     * 收集器WEB的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_web_test}")
    protected String collector_web_test;

    /**
     * 收集器WEB的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_collector_web_dev}")
    protected String collector_web_dev;

    ///////////////// 收集器 Collector，End /////////////////

    ///////////////// 内容 Content，Begin /////////////////

    /**
     * 内容API的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_content_api_pro}")
    protected String content_api_pro;

    /**
     * 内容API的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_content_api_pre}")
    protected String content_api_pre;

    /**
     * 内容API的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_content_api_sta}")
    protected String content_api_sta;

    /**
     * 内容API的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_content_api_test}")
    protected String content_api_test;

    /**
     * 内容API的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_content_api_dev}")
    protected String content_api_dev;

    /**
     * 内容服务的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_content_service_pro}")
    protected String content_service_pro;

    /**
     * 内容服务的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_content_service_pre}")
    protected String content_service_pre;

    /**
     * 内容服务的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_content_service_sta}")
    protected String content_service_sta;

    /**
     * 内容服务的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_content_service_test}")
    protected String content_service_test;

    /**
     * 内容服务的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_content_service_dev}")
    protected String content_service_dev;

    /**
     * 内容WEB的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_content_web_pro}")
    protected String content_web_pro;

    /**
     * 内容WEB的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_content_web_pre}")
    protected String content_web_pre;

    /**
     * 内容WEB的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_content_web_sta}")
    protected String content_web_sta;

    /**
     * 内容WEB的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_content_web_test}")
    protected String content_web_test;

    /**
     * 内容WEB的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_content_web_dev}")
    protected String content_web_dev;

    ///////////////// 内容 Content，End /////////////////

    ///////////////// 搜索 Search，Begin /////////////////

    /**
     * 搜索API的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_search_api_pro}")
    protected String search_api_pro;

    /**
     * 搜索API的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_search_api_pre}")
    protected String search_api_pre;

    /**
     * 搜索API的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_search_api_sta}")
    protected String search_api_sta;

    /**
     * 搜索API的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_search_api_test}")
    protected String search_api_test;

    /**
     * 搜索API的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_search_api_dev}")
    protected String search_api_dev;

    /**
     * 搜索服务的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_search_service_pro}")
    protected String search_service_pro;

    /**
     * 搜索服务的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_search_service_pre}")
    protected String search_service_pre;

    /**
     * 搜索服务的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_search_service_sta}")
    protected String search_service_sta;

    /**
     * 搜索服务的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_search_service_test}")
    protected String search_service_test;

    /**
     * 搜索服务的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_search_service_dev}")
    protected String search_service_dev;

    /**
     * 搜索WEB的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_search_web_pro}")
    protected String search_web_pro;

    /**
     * 搜索WEB的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_search_web_pre}")
    protected String search_web_pre;

    /**
     * 搜索WEB的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_search_web_sta}")
    protected String search_web_sta;

    /**
     * 搜索WEB的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_search_web_test}")
    protected String search_web_test;

    /**
     * 搜索WEB的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_search_web_dev}")
    protected String search_web_dev;

    ///////////////// 搜索 Search，End /////////////////

    ///////////////// 洋桃商城 Portal，Begin /////////////////

    /**
     * 洋桃商城的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_portal_web_pro}")
    protected String portal_web_pro;

    /**
     * 洋桃商城的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_portal_web_pre}")
    protected String portal_web_pre;

    /**
     * 洋桃商城的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_portal_web_sta}")
    protected String portal_web_sta;

    /**
     * 洋桃商城的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_portal_web_test}")
    protected String portal_web_test;

    /**
     * 洋桃商城的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_portal_web_dev}")
    protected String portal_web_dev;

    ///////////////// 洋桃商城 Portal，End /////////////////

    ///////////////// 单点登录 SSO，Begin /////////////////

    /**
     * 单点登录（SSO）WEB模块的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_sso_web_pro}")
    protected String sso_web_pro;

    /**
     * 单点登录（SSO）WEB模块的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_sso_web_pre}")
    protected String sso_web_pre;

    /**
     * 单点登录（SSO）WEB模块的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_sso_web_sta}")
    protected String sso_web_sta;

    /**
     * 单点登录（SSO）WEB模块的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_sso_web_test}")
    protected String sso_web_test;

    /**
     * 单点登录（SSO）WEB模块的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_sso_web_dev}")
    protected String sso_web_dev;

    ///////////////// 单点登录 SSO，End /////////////////

    ///////////////// 文件资源（上传、下载） fileresource，Begin /////////////////

    /**
     * 文件资源（上传、下载）API的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_api_pro}")
    protected String fileresource_api_pro;

    /**
     * 文件资源（上传、下载）API的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_api_pre}")
    protected String fileresource_api_pre;

    /**
     * 文件资源（上传、下载）API的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_api_sta}")
    protected String fileresource_api_sta;

    /**
     * 文件资源（上传、下载）API的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_api_test}")
    protected String fileresource_api_test;

    /**
     * 文件资源（上传、下载）API的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_api_dev}")
    protected String fileresource_api_dev;

    /**
     * 文件资源（上传、下载）服务模块的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_service_pro}")
    protected String fileresource_service_pro;

    /**
     * 文件资源（上传、下载）服务模块的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_service_pre}")
    protected String fileresource_service_pre;

    /**
     * 文件资源（上传、下载）服务模块的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_service_sta}")
    protected String fileresource_service_sta;

    /**
     * 文件资源（上传、下载）服务模块的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_service_test}")
    protected String fileresource_service_test;

    /**
     * 文件资源（上传、下载）服务模块的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_service_dev}")
    protected String fileresource_service_dev;

    /**
     * 文件资源（上传、下载）WEB模块的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_web_pro}")
    protected String fileresource_web_pro;

    /**
     * 文件资源（上传、下载）WEB模块的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_web_pre}")
    protected String fileresource_web_pre;

    /**
     * 文件资源（上传、下载）WEB模块的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_web_sta}")
    protected String fileresource_web_sta;

    /**
     * 文件资源（上传、下载）WEB模块的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_web_test}")
    protected String fileresource_web_test;

    /**
     * 文件资源（上传、下载）WEB模块的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_fileresource_web_dev}")
    protected String fileresource_web_dev;

    ///////////////// 文件资源（上传、下载） fileresource，End /////////////////

    ///////////////// 日志 Log，Begin /////////////////

    /**
     * 日志API的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_log_api_pro}")
    protected String log_api_pro;

    /**
     * 日志API的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_log_api_pre}")
    protected String log_api_pre;

    /**
     * 日志API的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_log_api_sta}")
    protected String log_api_sta;

    /**
     * 日志API的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_log_api_test}")
    protected String log_api_test;

    /**
     * 日志API的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_log_api_dev}")
    protected String log_api_dev;

    /**
     * 日志服务模块的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_log_service_pro}")
    protected String log_service_pro;

    /**
     * 日志服务模块的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_log_service_pre}")
    protected String log_service_pre;

    /**
     * 日志服务模块的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_log_service_sta}")
    protected String log_service_sta;

    /**
     * 日志服务模块的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_log_service_test}")
    protected String log_service_test;

    /**
     * 日志服务模块的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_log_service_dev}")
    protected String log_service_dev;

    /**
     * 日志WEB模块的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_log_web_pro}")
    protected String log_web_pro;

    /**
     * 日志WEB模块的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_log_web_pre}")
    protected String log_web_pre;

    /**
     * 日志WEB模块的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_log_web_sta}")
    protected String log_web_sta;

    /**
     * 日志WEB模块的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_log_web_test}")
    protected String log_web_test;

    /**
     * 日志WEB模块的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_log_web_dev}")
    protected String log_web_dev;

    ///////////////// 日志 Log，End /////////////////

    ///////////////// 系统模块，Begin /////////////////

    /**
     * 系统API的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_system_api_pro}")
    protected String system_api_pro;

    /**
     * 系统API的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_system_api_pre}")
    protected String system_api_pre;

    /**
     * 系统API的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_system_api_sta}")
    protected String system_api_sta;

    /**
     * 系统API的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_system_api_test}")
    protected String system_api_test;

    /**
     * 系统API的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_system_api_dev}")
    protected String system_api_dev;

    /**
     * 系统服务模块的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_system_service_pro}")
    protected String system_service_pro;

    /**
     * 系统服务模块的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_system_service_pre}")
    protected String system_service_pre;

    /**
     * 系统服务模块的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_system_service_sta}")
    protected String system_service_sta;

    /**
     * 系统服务模块的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_system_service_test}")
    protected String system_service_test;

    /**
     * 系统服务模块的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_system_service_dev}")
    protected String system_service_dev;

    /**
     * 系统WEB模块的生产环境域名
     */
    @Value("${msonion_carambola_config_domain_system_web_pro}")
    protected String system_web_pro;

    /**
     * 系统WEB模块的预览环境域名
     */
    @Value("${msonion_carambola_config_domain_system_web_pre}")
    protected String system_web_pre;

    /**
     * 系统WEB模块的稳定环境域名
     */
    @Value("${msonion_carambola_config_domain_system_web_sta}")
    protected String system_web_sta;

    /**
     * 系统WEB模块的测试环境域名
     */
    @Value("${msonion_carambola_config_domain_system_web_test}")
    protected String system_web_test;

    /**
     * 系统WEB模块的开发环境域名
     */
    @Value("${msonion_carambola_config_domain_system_web_dev}")
    protected String system_web_dev;

    ///////////////// 系统模块， End /////////////////

    /// End

    /// Begin

    /**
     * @return 根据环境，获取成员API模块的HTTP协议+域名，例如：http://member-api.yunyangtao.com，域名最后面不包括"/"
     */
    public String getMemberApi() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.member_api_pro, this.member_api_pre, this.member_api_sta, this.member_api_test, this.member_api_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), this.member_api_pro);
        }
    }

    /**
     * @return 根据环境，获取成员服务模块的HTTP协议+域名，例如：http://member-s.yunyangtao.com，域名最后面不包括"/"
     */
    public String getMemberService() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.member_service_pro, this.member_service_pre, this.member_service_sta, this.member_service_test, member_service_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), member_service_pro);
        }
    }

    /**
     * @return 根据环境，获取成员WEB模块的HTTP协议+域名，例如：http://member.yunyangtao.com，域名最后面不包括"/"
     */
    public String getMemberWeb() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.member_web_pro, this.member_web_pre, this.member_web_sta, this.member_web_test, this.member_web_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), member_web_pro);
        }
    }

    /**
     * @return 根据环境，获取用户（会员）API模块的HTTP协议+域名，例如：http://user-api.yunyangtao.com，域名最后面不包括"/"
     */
    public String getUserApi() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.user_api_pro, this.user_api_pre, this.user_api_sta, this.user_api_test, this.user_api_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), user_api_pro);
        }
    }

    /**
     * @return 根据环境，获取用户（会员）服务模块的HTTP协议+域名，例如：http://user-s.yunyangtao.com，域名最后面不包括"/"
     */
    public String getUserService() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.user_service_pro, this.user_service_pre, this.user_service_sta, this.user_service_test, this.user_service_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), user_service_pro);
        }
    }

    /**
     * @return 根据环境，获取用户（会员）WEB模块的HTTP协议+域名，例如：http://user.yunyangtao.com，域名最后面不包括"/"
     */
    public String getUserWeb() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.user_web_pro, this.user_web_pre, this.user_web_sta, this.user_web_test, this.user_web_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), user_web_pro);
        }
    }

    /**
     * @return 根据环境，获取系统管理后台API模块的HTTP协议+域名，例如：http://manager-api.yunyangtao.com，域名最后面不包括"/"
     */
    public String getManagerApi() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.manager_api_pro, this.manager_api_pre, this.manager_api_sta, this.manager_api_test, this.manager_api_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), manager_api_pro);
        }
    }

    /**
     * @return 根据环境，获取系统管理后台服务模块的HTTP协议+域名，例如：http://manager-s.yunyangtao.com，域名最后面不包括"/"
     */
    public String getManagerService() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.manager_service_pro, this.manager_service_pre, this.manager_service_sta,
                    this.manager_service_test, this.manager_service_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), manager_service_pro);
        }
    }

    /**
     * @return 根据环境，获取系统管理后台WEB模块的HTTP协议+域名，例如：http://manager.yunyangtao.com，域名最后面不包括"/"
     */
    public String getManagerWeb() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.manager_web_pro, this.manager_web_pre, this.manager_web_sta,
                    this.manager_web_test, this.manager_web_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), manager_web_pro);
        }
    }

    /**
     * @return 根据环境，获取商品（产品）API模块的HTTP协议+域名，例如：http://item-api.yunyangtao.com，域名最后面不包括"/"
     */
    public String getItemApi() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.item_api_pro, this.item_api_pre, this.item_api_sta, this.item_api_test, this.item_api_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), item_api_pro);
        }
    }

    /**
     * @return 根据环境，获取商品（产品）服务模块的HTTP协议+域名，例如：http://item-s.yunyangtao.com，域名最后面不包括"/"
     */
    public String getItemService() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.item_service_pro, this.item_service_pre, this.item_service_sta, this.item_service_test, this.item_service_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), item_service_pro);
        }
    }

    /**
     * @return 根据环境，获取商品（产品）WEB模块的HTTP协议+域名，例如：http://item.yunyangtao.com，域名最后面不包括"/"
     */
    public String getItemWeb() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.item_web_pro, this.item_web_pre, this.item_web_sta, this.item_web_test, this.item_web_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), item_web_pro);
        }
    }

    /**
     * @return 根据环境，获取收集器API模块的HTTP协议+域名，例如：http://collector-api.yunyangtao.com，域名最后面不包括"/"
     */
    public String getCollectorApi() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.collector_api_pro, this.collector_api_pre, this.collector_api_sta, this.collector_api_test, this.collector_api_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), collector_api_pro);
        }
    }

    /**
     * @return 根据环境，获取收集器服务模块的HTTP协议+域名，例如：http://collector-s.yunyangtao.com，域名最后面不包括"/"
     */
    public String getCollectorService() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.collector_service_pro, this.collector_service_pre, this.collector_service_sta,
                    this.collector_service_test, this.collector_service_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), collector_service_pro);
        }
    }

    /**
     * @return 根据环境，获取收集器WEB模块的HTTP协议+域名，例如：http://collector.yunyangtao.com，域名最后面不包括"/"
     */
    public String getCollectorWeb() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.collector_web_pro, this.collector_web_pre, this.collector_web_sta, this.collector_web_test, this.collector_web_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), collector_web_pro);
        }
    }

    /**
     * @return 根据环境，获取内容API模块的HTTP协议+域名，例如：http://content-api.yunyangtao.com，域名最后面不包括"/"
     */
    public String getContentApi() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.content_api_pro, this.content_api_pre, this.content_api_sta, this.content_api_test, this.content_api_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), content_api_pro);
        }
    }

    /**
     * @return 根据环境，获取内容服务模块的HTTP协议+域名，例如：http://content-s.yunyangtao.com，域名最后面不包括"/"
     */
    public String getContentService() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.content_service_pro, this.content_service_pre,
                    this.content_service_sta, this.content_service_test, this.content_service_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), content_service_pro);
        }
    }

    /**
     * 获取内容WEB模块
     * @return 根据环境，获取内容WEB模块的HTTP协议+域名，例如：http://content-s.yunyangtao.com，域名最后面不包括"/"
     */
    public String getContentWeb() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.content_web_pro, this.content_web_pre, this.content_web_sta,
                    this.content_web_test, this.content_web_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), content_web_pro);
        }
    }

    /**
     * @return 根据环境，获取搜索API模块的HTTP协议+域名，例如：http://search-api.yunyangtao.com，域名最后面不包括"/"
     */
    public String getSearchApi() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.search_api_pro, this.search_api_pre, this.search_api_sta, this.search_api_test, this.search_api_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), search_api_pro);
        }
    }

    /**
     * @return 根据环境，获取搜索服务模块的HTTP协议+域名，例如：http://search-s.yunyangtao.com，域名最后面不包括"/"
     */
    public String getSearchService() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.search_service_pro, this.search_service_pre, this.search_service_sta, this.search_service_test, this.search_service_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), search_service_pro);
        }
    }

    /**
     * @return 根据环境，获取搜索WEB模块的HTTP协议+域名，例如：http://search.yunyangtao.com，域名最后面不包括"/"
     */
    public String getSearchWeb() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.search_web_pro, this.search_web_pre, this.search_web_sta, this.search_web_test, this.search_web_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), search_web_pro);
        }
    }

    /**
     * @return 根据环境，获取洋桃商城的HTTP协议+域名，例如：http://www.yunyangtao.com，域名最后面不包括"/"
     */
    public String getPortalWeb() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.portal_web_pro, this.portal_web_pre, this.portal_web_sta, this.portal_web_test, this.portal_web_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), portal_web_pro);
        }
    }

    /**
     * @return 根据环境，获取单点登录的HTTP协议+域名，例如：http://sso.yunyangtao.com，域名最后面不包括"/"
     */
    public String getSsoWeb() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.sso_web_pro, this.sso_web_pre, this.sso_web_sta, this.sso_web_test, this.sso_web_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), sso_web_pro);
        }
    }

    /////////////////////  File Resource（文件资源），Begin //////////////////////////

    /**
     * @return 根据环境，获取文件资源（包括上传、下载）API模块的HTTP协议+域名，例如：http://fres-api.yunyangtao.com，域名最后面不包括"/"
     */
    public String getFileResourceApi() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.fileresource_api_pro, this.fileresource_api_pre, this.fileresource_api_sta,
                    this.fileresource_api_test, this.fileresource_api_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), fileresource_api_pro);
        }
    }

    /**
     * @return 根据环境，获取文件资源（包括上传、下载）服务模块的HTTP协议+域名，例如：http://fres-s.yunyangtao.com，域名最后面不包括"/"
     */
    public String getFileResourceService() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.fileresource_service_pro, this.fileresource_service_pre,
                    this.fileresource_service_sta, this.fileresource_service_test, this.fileresource_service_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), fileresource_service_pro);
        }
    }

    /**
     * @return 根据环境，获取文件资源（包括上传、下载）WEB模块的HTTP协议+域名，例如：http://fres.yunyangtao.com，域名最后面不包括"/"
     */
    public String getFileResourceWeb() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.fileresource_web_pro, this.fileresource_web_pre, this.fileresource_web_sta,
                    this.fileresource_web_test, this.fileresource_web_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), fileresource_web_pro);
        }
    }

    /////////////////////  File Resource（文件资源），End //////////////////////////

    /////////////////////  日志 ，Begin //////////////////////////

    /**
     * @return 根据环境，获取日志API模块的HTTP协议+域名，例如：http://log-api.yunyangtao.com，域名最后面不包括"/"
     */
    public String getLogApi() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.log_api_pro, this.log_api_pre, this.log_api_sta, this.log_api_test, this.log_api_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), log_api_pro);
        }
    }

    /**
     * @return 根据环境，获取日志服务模块的HTTP协议+域名，例如：http://log-s.yunyangtao.com，域名最后面不包括"/"
     */
    public String getLogService() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.log_service_pro, this.log_service_pre, this.log_service_sta, this.log_service_test, this.log_service_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), log_service_pro);
        }
    }

    /**
     * @return 根据环境，获取日志WEB模块的HTTP协议+域名，例如：http://log.yunyangtao.com，域名最后面不包括"/"
     */
    public String getLogWeb() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.log_web_pro, this.log_web_pre, this.log_web_sta, this.log_web_test, this.log_web_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), log_web_pro);
        }
    }

    /////////////////////  日志 ，End //////////////////////////


    /////////////////////  系统 ，Begin //////////////////////////

    /**
     * @return 根据环境，获取系统API模块的HTTP协议+域名，例如：http://sys-api.yunyangtao.com，域名最后面不包括"/"
     */
    public String getSystemApi() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.system_api_pro, this.system_api_pre, this.system_api_sta, this.system_api_test, this.system_api_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), system_api_pro);
        }
    }

    /**
     * @return 根据环境，获取系统服务模块的HTTP协议+域名，例如：http://sys-s.yunyangtao.com，域名最后面不包括"/"
     */
    public String getSystemService() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.system_service_pro, this.system_service_pre, this.system_service_sta, this.system_service_test, this.system_service_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), system_service_pro);
        }
    }

    /**
     * @return 根据环境，获取系统WEB模块的HTTP协议+域名，例如：http://sys.yunyangtao.com，域名最后面不包括"/"
     */
    public String getSystemWeb() {
        try {
            return MsOnionDevelopUtils.getProtocolAndDomain(this.getEnvironment(),
                    this.protocol_pro, this.protocol_pre, this.protocol_sta, this.protocol_test, this.protocol_dev,
                    this.system_web_pro, this.system_web_pre, this.system_web_sta, this.system_web_test, this.system_web_dev);
        } catch (Exception e) {
            // https://mariadb.com
            // 默认生产环境
            return String.format("%s://%s", this.getProtocol(), system_web_pro);
        }
    }

    /////////////////////  系统 ，End //////////////////////////

}
