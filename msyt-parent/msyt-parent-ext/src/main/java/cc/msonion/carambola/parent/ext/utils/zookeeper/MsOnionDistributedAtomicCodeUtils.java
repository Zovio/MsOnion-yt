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
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */

package cc.msonion.carambola.parent.ext.utils.zookeeper;


/**
 * 分布式原子性Code编码工具类
 *
 * @Title: MsOnionDistributedAtomicCodeUtils.java
 * @Package: cc.msonion.carambola.commons.common.utils.distributed
 * @Description: 分布式原子性Code编码工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月24日 下午3:36:42
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月24日 下午3:36:42
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：获取分布式架构中，成员（公司内部）的主键idx
 */

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionFixedLengthUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRandomUtils;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import org.apache.curator.RetryPolicy;

/**
 * 分布式原子性Long类型值工具类
 *
 * @ClassName: MsOnionDistributedAtomicCodeUtils
 * @Description: 分布式原子性Code编码工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月8日 下午3:36:42
 */
public final class MsOnionDistributedAtomicCodeUtils {

    /**
     * 成员（Member）编码前缀：YT
     */
    public static final String MEMBER_CODE_PREFIX = "YT";

    /**
     * 成员（Member）编码随机生成数字+字母的长度：3
     */
    public static final int MEMBER_RANDOM_LENGTH = 3;

    /**
     * 品牌（Brand）编码前缀：
     */
    public static final String BRAND_CODE_PREFIX = "BR";

    /**
     * 品牌（Brand）编码生成数字总长度：6，不包括前缀
     */
    public static final int BRAND_CODE_NUMBER_LENGTH = 6;

    /**
     * 商品货号生成数字长度：5
     */
    public static final int ITEM_NO_NUMBER_LENGTH = 5;

    private MsOnionDistributedAtomicCodeUtils() {
    }

    /**
     * 得到成员（Member）编码，前缀YT + 分布式全局原子性唯一递增值 + 3位随机数字和字母，例如：YT88VNB、YT99NB8
     *
     * @param msOnionDomain 域名
     * @param zkClient      Zookeeper客户端
     * @param retryPolicy   重试策略
     * @return 成员（Member）编码，前缀YT + 分布式全局原子性唯一递增值 + 3位随机数字和字母，例如：YT88VNB、YT99NB8
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static String getMemberCode(MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
                                       RetryPolicy retryPolicy)
            throws MsOnionIllegalArgumentException, MsOnionException {

        try {
            Long value = MsOnionDistributedAtomicLongUtils.getCode(msOnionDomain,
                    zkClient, retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_CODE_PATH);

            // 前缀YT + 分布式全局原子性唯一递增值 + 3位随机数字和字母，例如：YT88VNB、YT99NB8
            // yt + value + NB8;  // 不可以超过30位！！！
            String numbersAndLetters = MsOnionRandomUtils.getRandomNumbersAndLetters(MEMBER_RANDOM_LENGTH);

            return String.format("%s%s%s", MEMBER_CODE_PREFIX, value, numbersAndLetters);

        } catch (MsOnionIllegalArgumentException ex) {
            throw ex;
        } catch (MsOnionException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 得到品牌（Brand）编码，前缀BR + 分布式全局原子性唯一递增值（1-999999，固定6位，不够前补0），例如：BR000001 - BR999999
     *
     * @param msOnionDomain 域名
     * @param zkClient      Zookeeper客户端
     * @param retryPolicy   重试策略
     * @return String
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static String getBrandCode(MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
                                      RetryPolicy retryPolicy)
            throws MsOnionIllegalArgumentException, MsOnionException {

        try {
            Long value = MsOnionDistributedAtomicLongUtils.getCode(msOnionDomain,
                    zkClient, retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_BRAND_CODE_PATH);

            String numbers = MsOnionFixedLengthUtils.fixLength(value, BRAND_CODE_NUMBER_LENGTH);

            return String.format("%s%s", BRAND_CODE_PREFIX, numbers);

        } catch (MsOnionIllegalArgumentException ex) {
            throw ex;
        } catch (MsOnionException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 得到商品货号中间数字值，分布式全局原子性唯一递增值（1-9999，固定4位，不够前补0），例如：0001 - 9999
     *
     * @param msOnionDomain 域名
     * @param zkClient      Zookeeper客户端
     * @param retryPolicy   重试策略
     * @return 返回数字值
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    public static String getItemNo(MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient, RetryPolicy retryPolicy)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            Long value = MsOnionDistributedAtomicLongUtils.getCode(msOnionDomain,
                    zkClient, retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_NO_PATH);

            String numbers = MsOnionFixedLengthUtils.fixLength(value, ITEM_NO_NUMBER_LENGTH);
            return numbers;
        } catch (MsOnionIllegalArgumentException ex) {
            throw ex;
        } catch (MsOnionException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }
}
