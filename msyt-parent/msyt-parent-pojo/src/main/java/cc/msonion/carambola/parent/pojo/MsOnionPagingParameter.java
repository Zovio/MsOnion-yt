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

import cc.msonion.carambola.parent.pojo.base.MsOnionBaseParameter;

/**
 * @Title: MsOnionPagingParameter.java
 * @Package: cc.msonion.carambola.parent.pojo
 * @Description: MsOnionParameter
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月14日 下午1:43:54
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年3月16日 下午1:43:54
 * @Modify-version: V2.0.0
 * @Modify-description: 创建
 */

/**
 * @ClassName: MsOnionPagingParameter
 * @Description: 分页参数封装类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年3月16日 下午1:43:54
 */
public class MsOnionPagingParameter extends MsOnionBaseParameter {

//    /**
//     *  是否安全结果，也就是所有返回值的方法，是否对data进行加密，默认是加密的
//     */
//    private boolean securityResult = true;
//
//    /**
//     *  是否使用账号信息（用户名 + 密码[加密之后的密码])加密
//     */
//    private boolean useAccountInfoEncrypt = true;
//
//    private String username;
//
//    private String openKey;
//
//    /**
//     *  是否安全结果，也就是所有返回值的方法，是否对data进行加密，默认是加密的
//     */
//    public boolean isSecurityResult() {
//        return securityResult;
//    }
//
//    /**
//     *  是否安全结果，也就是所有返回值的方法，是否对data进行加密，默认是加密的
//     */
//    public void setSecurityResult(boolean securityResult) {
//        this.securityResult = securityResult;
//    }

    /**
     * 页码，从1开始
     */
    private int pageNum;

//    /**
//     * 总页数
//     */
//    private int totalPages;

    /**
     * 总记录数
     */
    private long totalCounts;

    /**
     * 每页容量（每页有多少条记录）
     */
    private int pageSize;

    /**
     * 获取页码
     *
     * @return 返回页码
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置页码
     *
     * @param pageNum 页码
     * @return 返回MsOnionPagingParameter实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月16日 下午1:43:54
     */
    public MsOnionPagingParameter setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    /**
     * 获取总记录数
     *
     * @return 返回总记录数
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月16日 下午1:43:54
     */
    public long getTotalCounts() {
        return totalCounts;
    }

    /**
     * 设置总记录数
     *
     * @param totalCounts 总记录数
     * @return 返回MsOnionPagingParameter实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月16日 下午1:43:54
     */
    public MsOnionPagingParameter setTotalCounts(long totalCounts) {
        this.totalCounts = totalCounts;
        return this;
    }

    /**
     * 获取每页数量
     *
     * @return 返回每页数量
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月16日 下午1:43:54
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页数量
     *
     * @param pageSize 每页数量
     * @return 返回MsOnionPagingParameter实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月16日 下午1:43:54
     */
    public MsOnionPagingParameter setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
