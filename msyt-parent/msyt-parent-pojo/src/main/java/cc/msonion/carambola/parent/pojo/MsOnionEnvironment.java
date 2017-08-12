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

import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.utils.MsOnionDevelopUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionUUIDUtils;
import cc.msonion.carambola.parent.pojo.base.MsOnionBasePojo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * 环境（生产环境、预览环境、稳定环境、测试环境、开发环境）
 *
 * @Title: Environment.java
 * @Package: cc.msonion.carambola.parent.pojo
 * @Description: 环境（生产环境、预览环境、稳定环境、测试环境、开发环境）
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月15日 下午3:07:52
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月15日 下午3:07:52
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：环境（生产环境、预览环境、稳定环境、测试环境、开发环境）相关实现
 */

/**
 * 环境（生产环境、预览环境、稳定环境、测试环境、开发环境）
 *
 * @ClassName: Environment
 * @Description: 环境（生产环境、预览环境、稳定环境、测试环境、开发环境）
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月15日 下午3:07:52
 */
public class MsOnionEnvironment extends MsOnionBasePojo {

    /**
     * @Fields serialVersionUID : 自动生成serialVersionUID
     */
    private static final long serialVersionUID = -3030330146205963977L;

    // 在 config-domain.properties


    // 注意： search --> msyt-search-web 域名，  search_api --》是指 taotao-search-api 域名

    //////////域名相关  Begin

    /**
     * Environment : pro（production ）, pre（preview) , dev（develop）, sta（stable），test
     */
    @Value("${msonion_carambola_config_environment}")
    protected String environment;

    /**
     * production protocol 生产环境协议
     */
    @Value("${msonion_carambola_config_protocol_pro}")
    protected String protocol_pro;

    /**
     * test protocol 测试环境协议
     */
    @Value("${msonion_carambola_config_protocol_test}")
    protected String protocol_test;

    /**
     * pre protocol 预览（预发布）环境协议
     */
    @Value("${msonion_carambola_config_protocol_pre}")
    protected String protocol_pre;

    /**
     * sta protocol 稳定环境协议
     */
    @Value("${msonion_carambola_config_protocol_sta}")
    protected String protocol_sta;

    /**
     * dev protocol 开发环境协议
     */
    @Value("${msonion_carambola_config_protocol_dev}")
    protected String protocol_dev;

    //////////  域名相关  End

    //////////  静态资源，  Begin

    /**
     * 前端静态资源JS版本号，解决缓存问题，
     * <p> 注意：Spring中Beans是单例的，只要服务器重启都会重新生成新的UUID </p>
     */
    private String jsVersion = MsOnionUUIDUtils.randomUUIDForVersion();

    /**
     * 前端静态资源图片版本号，解决缓存问题
     * <p> 注意：Spring中Beans是单例的，只要服务器重启都会重新生成新的UUID </p>
     */
    private String imgVersion = MsOnionUUIDUtils.randomUUIDForVersion();

    /**
     * 前端静态资源CSS版本号，解决缓存问题
     * <p> 注意：Spring中Beans是单例的，只要服务器重启都会重新生成新的UUID </p>
     */
    private String cssVersion = MsOnionUUIDUtils.randomUUIDForVersion();

    //////////  静态资源，   End

    ///////// 分布式文件系统 ， Begin /////////

    /**
     * 分布式文件系统：FastDFS、OSS，默认必须为OSS，线上环境使用
     */
    @Value("${msonion.carambola.dfs}")
    private String dfs = "OSS";

    ///////// 分布式文件系统 ， End  /////////

    //////////////////////  Setters、Getters， Begin  ///////////////////////

    /**
     * @return the environment 获取环境：pro（production ), pre（preview) , dev（develop）, sta（stable），test
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * @return the protocol 协议：生产环境协议、预览（预发布）环境协议、稳定环境协议、测试环境协议、开发环境协议
     */
    public String getProtocol() {
        try {
            return MsOnionDevelopUtils.getProtocol(environment, protocol_pro, protocol_pre, protocol_sta, protocol_test, protocol_dev);
        } catch (Exception e) {
            // https
            return protocol_pro;  // 默认生产环境协议
        }
    }

    /**
     * 得到JS版本号，解决静态资源缓存问题
     * @return
     */
    public String getJsVersion() {
        return jsVersion;
    }

    /**
     * 得到图片版本号，解决静态资源缓存问题
     * @return
     */
    public String getImgVersion() {
        return imgVersion;
    }

    /**
     * 得到CSS版本号，解决静态资源缓存问题
     * @return
     */
    public String getCssVersion() {
        return cssVersion;
    }

    /**
     * 是否OSS（阿里云提供分布式文件系统服务），默认都是OSS，线上环境使用OSS
     * @return 返回true：是，false：否
     */
    public boolean isOSS() {
        if (StringUtils.isBlank(this.dfs)) {
            return true;
        }
        if (MsOnionConstants.DFS_FDFS.equalsIgnoreCase(this.dfs.trim())) {
            return false;
        }
        // 默认都是OSS，线上环境使用OSS
        return true;
    }

    //////////////////////  Setters、Getters， End  ///////////////////////

    /**
     * toString
     *
     * @return 返回字符串
     */
    @Override
    public String toString() {
        return "Environment [environment=" + environment + ", protocol_pro=" + protocol_pro + ", protocol_test="
                + protocol_test + ", protocol_pre=" + protocol_pre + ", protocol_sta=" + protocol_sta
                + ", protocol_dev=" + protocol_dev + "]";
    }

}
