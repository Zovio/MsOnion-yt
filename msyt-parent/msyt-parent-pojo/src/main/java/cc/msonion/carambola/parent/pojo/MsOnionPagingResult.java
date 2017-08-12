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

/**
 * @Title: MsOnionPagingResult.java
 * @Package: cc.msonion.carambola.parent.pojo
 * @Description: 分页结果，包括分页相关信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年3月15日 下午3:07:52
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年3月15日 下午3:07:52
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：页码、每页数量、总记录数、总页数等
 */

import cc.msonion.carambola.parent.common.utils.MsOnionPagingUtils;

/**
 * 分页结果，包括分页相关信息
 *
 * @ClassName: MsOnionPagingResult
 * @Description: 分页结果，包括分页相关信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年3月15日 下午3:07:52
 */
public class MsOnionPagingResult extends MsOnionResult {

    /**
     * 当前页码，从1开始
     */
    private int pageNum = 1; // 当前页
    /**
     * 总页数
     */
    private int totalPages = 0; // 总页数
    /**
     * 每页数量
     */
    private int pageSize; // 每页记录数量
    /**
     * 总记录数
     */
    private long totalCounts = 0; // 总数据数
//    private int pageStartRow = 0; // 每页的起始数
//    private int pageEndRow = 0; // 每页显示数据的终止数
    /**
     * 是否有下一页
     */
    private boolean hasNextPage = false; // 是否有下一页
    /**
     * 是否上一页
     */
    private boolean hasPrePage = false; // 是否有前一页

    /**
     * 是否第一页
     */
    private boolean isFirstPage;

    /**
     * 是否最后一页
     */
    private boolean isLastPage;

    /**
     * 下一页
     */
    private int nextPage;

    /**
     * 上一页
     */
    private int prePage;

    /**
     * 创建 MsOnionPagingResult 实例对象
     *
     * @param totalCounts 总记录数
     * @param pageSize    页码
     * @param pageNum     每页数量
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public MsOnionPagingResult(int totalCounts, int pageSize, int pageNum) {
        this(null, null, null, totalCounts, pageSize, pageNum);
    }

    /**
     * 创建 MsOnionPagingResult 实例对象
     *
     * @param status      状态码
     * @param msg         提示信息
     * @param data        数据
     * @param totalCounts 总记录数
     * @param pageSize    页码
     * @param pageNum     每页数量
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public MsOnionPagingResult(Integer status, String msg, Object data, long totalCounts, int pageSize, int pageNum) {
        super(status, msg, data);

        // 初始化
        init(totalCounts, pageSize, pageNum);
    }

    /**
     * 初始化
     *
     * @param totalCounts 总记录数
     * @param pageSize    页码
     * @param pageNum     每页数量
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    private void init(long totalCounts, int pageSize, int pageNum) {
        this.pageSize = pageSize;
        this.hasPrePage = false;
        this.hasNextPage = false;
        this.pageNum = pageNum;
        this.totalCounts = totalCounts;

        this.totalPages = MsOnionPagingUtils.computeTotalPageNum(totalCounts, pageSize);
    }

    /**
     * 创建 MsOnionPagingResult 实例对象
     *
     * @param status      状态码
     * @param msg         提示信息
     * @param data        数据
     * @param totalCounts 总记录数
     * @param pageSize    页码
     * @param pageNum     每页数量
     * @return 返回MsOnionPagingResult实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public static MsOnionPagingResult build(Integer status, String msg, Object data, long totalCounts, int pageSize, int pageNum) {
        return new MsOnionPagingResult(status, msg, data, totalCounts, pageSize, pageNum);
    }

    /**
     * 是否是首页（第一页），第一页页码为1
     *
     * @return 首页标识
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public boolean isFirstPage() {
        this.isFirstPage = pageNum <= 1;
        return this.isFirstPage;
    }

    /**
     * 是否是最后一页
     *
     * @return 末页标识
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public boolean isLastPage() {
        this.isLastPage = pageNum >= getTotalPages();
        return this.isLastPage;
    }

    /**
     * 获取上一页
     *
     * @return 返回上一页
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public long getPrePage() {
        if (isHasPrePage()) {
            this.prePage = pageNum - 1;
        } else {
            this.prePage = pageNum;
        }
        return this.prePage;
    }

    /**
     * 获取下一页
     *
     * @return 返回下一页
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public int getNextPage() {
        if (isHasNextPage()) {
            this.nextPage = pageNum + 1;
        } else {
            this.nextPage = pageNum;
        }
        return this.nextPage;
    }

    /**
     * 是否有上一页
     *
     * @return 上一页标识
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public boolean isHasPrePage() {
        this.hasPrePage = (pageNum - 1 >= 1);
        return this.hasPrePage;
    }

    /**
     * 是否有下一页
     *
     * @return 下一页标识
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public boolean isHasNextPage() {
        this.hasNextPage = (pageNum + 1 <= totalPages);
        return this.hasNextPage;
    }

//    /**
//     * 开始行
//     */
//    public int getPageStartRow() {
//        if (this.pageSize <= 0 || this.totalCounts <= 0) return 0;
////        return this.pageStartRow = pageNum > 0 ? (pageNum - 1) * this.pageSize + 1 : 0;
//
//        return this.pageStartRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
//    }

//    /**
//     * 结束行
//     */
//    public int getPageEndRow() {
////        return this.pageEndRow = pageNum > 0 ? (int) Math.min(this.pageSize * this.pageNum, this.totalCounts) : 0;
//
//        return this.pageEndRow = this.pageStartRow + this.pageSize * (this.pageNum > 0 ? 1 : 0);
//    }

//    /**
//     * 计算起止行号
//     */
//    private void calculateStartAndEndRow() {
//        this.pageStartRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
//        this.pageEndRow = this.pageStartRow + this.pageSize * (this.pageNum > 0 ? 1 : 0);
//    }

    /**
     * 获取当前页码
     *
     * @return 返回页码
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 获取总页数
     *
     * @return 返回总页数
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * 获取每页数量
     *
     * @return 返回每页数量
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 获取总记录数
     *
     * @return 返回总记录数
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public long getTotalCounts() {
        return totalCounts;
    }

}
