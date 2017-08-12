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

import java.io.Serializable;
import java.util.Map;

/**
 * 批量报表，批量操作过程中，如果有报错，就记录报错异常信息，继续后续操作，同时把异常返回
 *
 * @Title: MsOnionBatchReport.java
 * @Package: cc.msonion.carambola.parent.ext.pojo
 * @Description: 批量报表
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月17日 下午4:02:11
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月17日 下午4:02:11
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * 批量报表，批量操作过程中，如果有报错，就记录报错异常信息，继续后续操作，同时把异常返回
 *
 * @param <T> 目标POJO
 * @ClassName: MsOnionBatchReport
 * @Description: 批量报表
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月17日 下午4:02:11
 */
public class MsOnionBatchReport<T extends Serializable>  implements Serializable {


    private static final long serialVersionUID = -7661629006048058988L;
    /**
     * 是否所有成功
     */
    private boolean isAllSuccess;

    /**
     * 总记录数，包括成功、失败
     */
    private int totalCount;

    /**
     * 成功记录，Key ：行号（从0开始） ， Value ： 目标POJO对象
     */
    private Map<Integer, T> successRecords;

    /**
     * 失败记录，Key ：行号（从0开始） ， Value ： 目标POJO对象
     */
    private Map<Integer, T> failureRecords;

    /**
     * 异常，Key ：行号（从0开始） ， Value ： 当前目标POJO对象操作的异常
     */
    private Map<Integer, Exception> exceptions;

    /**
     * 源数据记录集合，也就是需要批量操作的原始数据集合
     * <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     */
    private Map<Integer, T> sourceRecords;

    //////////////////// Setters、Getters  ### Begin ////////////////////

    /**
     * 是否所有成功
     *
     * @return 是否所有成功
     */
    public boolean isAllSuccess() {
        return isAllSuccess;
    }

    /**
     * 设置 是否所有成功
     *
     * @param allSuccess 是否所有成功
     */
    public void setAllSuccess(boolean allSuccess) {
        isAllSuccess = allSuccess;
    }

    /**
     * 总记录数，包括成功、失败
     *
     * @return 总记录数，包括成功、失败
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置 总记录数，包括成功、失败
     *
     * @param totalCount 总记录数，包括成功、失败
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 成功记录，Key ：行号（从0开始） ， Value ： 目标POJO对象
     *
     * @return 成功记录，Key ：行号（从0开始） ， Value ： 目标POJO对象
     */
    public Map<Integer, T> getSuccessRecords() {
        return successRecords;
    }

    /**
     * 成功记录，Key ：行号（从0开始） ， Value ： 目标POJO对象
     *
     * @param successRecords 成功记录，Key ：行号（从0开始） ， Value ： 目标POJO对象
     */
    public void setSuccessRecords(Map<Integer, T> successRecords) {
        this.successRecords = successRecords;
    }

    /**
     * 失败记录，Key ：行号（从0开始） ， Value ： 目标POJO对象
     *
     * @return 失败记录，Key ：行号（从0开始） ， Value ： 目标POJO对象
     */
    public Map<Integer, T> getFailureRecords() {
        return failureRecords;
    }

    /**
     * 设置 失败记录，Key ：行号（从0开始） ， Value ： 目标POJO对象
     *
     * @param failureRecords 失败记录，Key ：行号（从0开始） ， Value ： 目标POJO对象
     */
    public void setFailureRecords(Map<Integer, T> failureRecords) {
        this.failureRecords = failureRecords;
    }

    /**
     * 异常，Key ：行号（从0开始） ， Value ： 当前目标POJO对象操作的异常
     *
     * @return 异常，Key ：行号（从0开始） ， Value ： 当前目标POJO对象操作的异常
     */
    public Map<Integer, Exception> getExceptions() {
        return exceptions;
    }

    /**
     * 设置 异常，Key ：行号（从0开始） ， Value ： 当前目标POJO对象操作的异常
     *
     * @param exceptions 异常，Key ：行号（从0开始） ， Value ： 当前目标POJO对象操作的异常
     */
    public void setExceptions(Map<Integer, Exception> exceptions) {
        this.exceptions = exceptions;
    }

    /**
     * 源数据记录集合，也就是需要批量操作的原始数据集合
     * <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     *
     * @return 源数据记录集合
     */
    public Map<Integer, T> getSourceRecords() {
        return sourceRecords;
    }

    /**
     * 设置 源数据记录集合，也就是需要批量操作的原始数据集合
     * <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     *
     * @param sourceRecords 源数据记录集合
     */
    public void setSourceRecords(Map<Integer, T> sourceRecords) {
        this.sourceRecords = sourceRecords;
    }


    /////////////////// Setters、Getters  ### End ////////////////////


    @Override
    public String toString() {
        return "MsOnionBatchReport{" +
                "isAllSuccess=" + isAllSuccess +
                ", totalCount=" + totalCount +
                ", successRecords=" + successRecords +
                ", failureRecords=" + failureRecords +
                ", exceptions=" + exceptions +
                ", sourceRecords=" + sourceRecords +
                '}';
    }
}
