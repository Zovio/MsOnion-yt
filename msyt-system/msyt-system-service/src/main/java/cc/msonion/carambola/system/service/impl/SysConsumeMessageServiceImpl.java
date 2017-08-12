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

package cc.msonion.carambola.system.service.impl;

/**
 * RocketMQ 消费消息 Service实现类
 *
 * @Title: SysConsumeMessageServiceImpl.java
 * @Package: cc.msonion.carambola.system.service
 * @Description: RocketMQ 消费消息 Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月29日 上午10:45:02
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年5月29日 上午10:45:02
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.system.common.SystemMessageConstants;
import cc.msonion.carambola.system.pojo.SysConsumeMessage;
import cc.msonion.carambola.system.pojo.SysConsumeMessageExample;
import cc.msonion.carambola.system.service.SysConsumeMessageService;
import org.springframework.stereotype.Service;

/**
 * RocketMQ 消费消息 Service实现类，不可以继承 MsOnionServiceExt，因为MsOnionServiceExt有MQ
 * ，当前就是MQ的消费消息，否则就出现死循环！！！
 *
 * @ClassName: SysConsumeMessageServiceImpl
 * @Description: RocketMQ 消费消息 Service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月29日 上午10:45:02
 */
@Service
public class SysConsumeMessageServiceImpl extends MsOnionServiceExt<SysConsumeMessage, SysConsumeMessageExample> implements SysConsumeMessageService {

    /**
     * 保存消费消息
     *
     * @param apiVersion        Api版本
     * @param sysConsumeMessage 消费消息
     * @return 返回操作结果 MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult saveMessage(MsOnionApiVersion apiVersion, SysConsumeMessage sysConsumeMessage)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == sysConsumeMessage) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, SystemMessageConstants.POJO_NOT_NULL);
        }
        try {
            // 生成Idx
            long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_SYSTEM_CONSUME_MESSAGE_IDX_PATH);

            long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_SYSTEM_CONSUME_MESSAGE_VERSION_PATH);

            sysConsumeMessage.setIdx(idx);
            sysConsumeMessage.setVersion(version);

            // TODO：校验长度，message body 大于2000要截取！！！

            // 保存，RocketMQ自动插入数据
            int result = save(apiVersion, sysConsumeMessage);
            if (result >= 1) {
                return MsOnionResult.ok(result);
            }
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, SystemMessageConstants.FAIL);
    }
}
