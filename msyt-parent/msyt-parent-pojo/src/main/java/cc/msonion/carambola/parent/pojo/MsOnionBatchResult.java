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

import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.pojo.base.MsOnionBaseResult;

import java.io.Serializable;

/**
 * @Title: MsOnionBatchResult.java
 * @Package: cc.msonion.carambola.parent.ext.pojo
 * @Description: 自定义批量结果
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月15日 下午11:25:38
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月15日 下午11:25:38
 * @Modify-version: V2.0.0
 * @Modify-description: 创建
 */

/**
 * @param <POJO> 目标POJO
 * @ClassName: MsOnionBatchResult
 * @Description: 自定义统一批量操作结果
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月15日 下午11:25:38
 */
public class MsOnionBatchResult<POJO extends Serializable> extends MsOnionBaseResult {

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 目标POJO对象
     */
    private POJO pojo;

    /**
     * OK
     */
    private static final String MESSAGE_OK = "OK";

    /**
     * @Fields serialVersionUID : 自动生成serialVersionUID
     */
    private static final long serialVersionUID = -1991985523438870491L;

    public MsOnionBatchResult() {
    }

    /**
     * 创建MsOnionBatchResult实例对象
     *
     * @param status 状态码
     * @param msg    提示信息
     * @return 返回MsOnionBatchResult实例对象
     */
    public static MsOnionBatchResult build(Integer status, String msg) {
        return new MsOnionBatchResult(status, msg, null);
    }

    /**
     * 创建MsOnionBatchResult实例对象
     *
     * @param status 状态码
     * @param msg    提示信息
     * @param pojo   目标POJO实例对象
     */
    public MsOnionBatchResult(Integer status, String msg, POJO pojo) {
        this.status = status;
        this.msg = msg;
        this.pojo = pojo;
    }

    /**
     * 创建MsOnionBatchResult实例对象
     *
     * @param pojo 目标POJO实例对象
     */
    public MsOnionBatchResult(POJO pojo) {
        this.status = MsOnionStatusConstants.STATUS_200;
        this.msg = MESSAGE_OK;
        this.pojo = pojo;
    }

    /**
     * 获取状态码
     *
     * @return 返回状态码
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取提示信息
     *
     * @return 返回提示信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置提示信息
     *
     * @param msg 提示信息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取目标POJO实例对象
     *
     * @return 返回POJO是实例对象
     */
    public POJO getPojo() {
        return pojo;
    }

    /**
     * 设置目标POJO
     *
     * @param pojo 目标POJO实例对象
     */
    public void setPojo(POJO pojo) {
        this.pojo = pojo;
    }

    /**
     * toString
     *
     * @return 返回字符串
     */
    @Override
    public String toString() {
        return "MsOnionResult [status=" + status + ", msg=" + msg + ", pojo=" + pojo + "]";
    }

}
