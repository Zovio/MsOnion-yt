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

package cc.msonion.carambola.parent.common.utils;

/**
 * @Title: MsOnionPagingUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: 分页工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月15日 下午8:22:09
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年3月26日 下午8:22:09
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：1、计算总页数
 */

import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;

/**
 * @ClassName: MsOnionPagingUtils
 * @Description: 分页工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月15日 下午8:22:09
 */
public final class MsOnionPagingUtils {

    private MsOnionPagingUtils() {
    }

    /**
     * 计算总页数
     *
     * @param totalCounts 总记录数
     * @param pageSize    每页数量
     * @return
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    public static int computeTotalPageNum(long totalCounts, int pageSize) {
        // int totalPageNum = (int) ((totalCounts  +  pageSize  - 1) / pageSize);
        return (int) Math.ceil(totalCounts * 1.0 / pageSize);
    }

    /**
     * 获取正确的pageSize
     *
     * @param pageSize 每页记录数，例如：50、100
     * @return 返回正确的pageSize
     */
    public static int getPageSize(int pageSize) {
        // 如果非法，设置默认的
        if (pageSize < MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE) {
            return MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE;
        }
        if (pageSize > MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE) {
            return MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE;
        }
        return pageSize;
    }

    /**
     * 获取正确的from，Elasticsearch 中使用
     *
     * @param from 分页，从哪里开始，从0开始，而不是1
     * @return 返回正确的form
     */
    public static int getFrom(int from) {
        // 如果非法，设置默认的
        if (from < MsOnionPagingConstants.MS_ONION_PAGING_FROM_MIN_VALUE) {
            return MsOnionPagingConstants.MS_ONION_PAGING_FROM_MIN_VALUE;
        }
        if (from > MsOnionPagingConstants.MS_ONION_PAGING_FROM_MAX_VALUE) {
            return MsOnionPagingConstants.MS_ONION_PAGING_FROM_MAX_VALUE;
        }
        return from;
    }

    /**
     * 获取正确的to，Elasticsearch 中使用
     *
     * @param to 分页，从哪里结束，从0开始，而不是1
     * @return 返回正确的form
     */
    public static int getTo(int to) {
        // 如果非法，设置默认的
        if (to < MsOnionPagingConstants.MS_ONION_PAGING_TO_MIN_VALUE) {
            return MsOnionPagingConstants.MS_ONION_PAGING_TO_MIN_VALUE;
        }
        if (to > MsOnionPagingConstants.MS_ONION_PAGING_TO_MAX_VALUE) {
            return MsOnionPagingConstants.MS_ONION_PAGING_TO_MAX_VALUE;
        }
        return to;
    }
}
