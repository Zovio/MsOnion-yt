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

package cc.msonion.carambola.parent.ext.pojo.setting;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionZookeeperUtils;
import cc.msonion.carambola.parent.interfaces.setting.adapter.MsOnionDynamicSettingAdapter;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.springframework.stereotype.Component;

/**
 * 动态配置
 *
 * @ClassName: MsOnionDynamicSetting
 * @Description: MsOnionDynamicSetting
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年7月25日 下午3:34:54
 */
@Component
public class MsOnionDynamicSetting implements MsOnionDynamicSettingAdapter {

    /**
     * 获取是否禁用全局Redis查询缓存，true:禁用，false：开启Redis缓存
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 重试策略
     * @return 获取是否禁用全局Redis查询缓存，true:禁用，false：开启Redis缓存
     * @throws MsOnionException 异常
     */
    @Override
    public boolean getRedisCacheGlobalQueryDisabled(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient, RetryPolicy retryPolicy) throws MsOnionException {
        return MsOnionZookeeperUtils.getRedisCacheGlobalQueryDisabled(domain, zkClient, retryPolicy);
    }

    /**
     * 获取全局Redis缓存周期，单位：秒
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 重试策略
     * @return 获取是否禁用全局Redis查询缓存，true:禁用，false：开启Redis缓存
     * @throws MsOnionException 异常
     */
    @Override
    public int getRedisGlobalCacheExpire(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient, RetryPolicy retryPolicy) throws MsOnionException {
        return MsOnionZookeeperUtils.getRedisGlobalCacheExpire(domain, zkClient, retryPolicy);
    }
}
