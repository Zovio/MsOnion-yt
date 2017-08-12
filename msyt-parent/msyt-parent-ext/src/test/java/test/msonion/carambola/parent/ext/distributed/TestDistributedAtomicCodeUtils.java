package test.msonion.carambola.parent.ext.distributed;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionFixedLengthUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRandomUtils;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import test.msonion.carambola.parent.ext.service.member.TestZookeeperConstants;

/**
 * Created by HeroCao on 2017/5/19.
 */
public class TestDistributedAtomicCodeUtils {

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
     * 商品货号生成数字长度：4
     */
    public static final int ITEM_NO_NUMBER_LENGTH = 4;

    private TestDistributedAtomicCodeUtils() {
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
            Long value = TestDistributedAtomicLongUtils.getCode(msOnionDomain,
                    zkClient, retryPolicy, TestZookeeperConstants.ZK_COUNTER_MEMBER_CODE_PATH);

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
            Long value = TestDistributedAtomicLongUtils.getCode(msOnionDomain,
                    zkClient, retryPolicy, TestZookeeperConstants.ZK_COUNTER_COLLECTOR_BRAND_CODE_PATH);

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
            Long value = TestDistributedAtomicLongUtils.getCode(msOnionDomain,
                    zkClient, retryPolicy, TestZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_NO_PATH);

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
