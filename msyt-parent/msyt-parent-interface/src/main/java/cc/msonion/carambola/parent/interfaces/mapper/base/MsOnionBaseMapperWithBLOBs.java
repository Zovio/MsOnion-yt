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


package cc.msonion.carambola.parent.interfaces.mapper.base;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: MsOnionBaseMapperWithBLOBs.java
 * @Package: cc.msonion.carambola.parent.interfaces.mapper.base
 * @Description: MsOnionBaseMapperWithBLOBs
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月13日 上午9:48:55
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月13日 上午9:48:55
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：定义相关接口方法
 */

/**
 * @param <T> 目标Pojo，都要实现java.io.Serializable 接口，例如：Item
 * @param <E> 目标Pojo的 Example，都要实现java.io.Serializable 接口，例如：ItemExample
 * @ClassName: MsOnionBaseMapperWithBLOBs
 * @Description: 数据库表中有Text类型列对应的父Mapper
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月13日 上午9:48:55
 */
public interface MsOnionBaseMapperWithBLOBs<T extends Serializable, E extends Serializable> extends MsOnionBaseMapper<T, E> {

    /**
     * 根据条件查询目标POJO列表
     *
     * @param example 目标POJO的Example实例对象
     * @return 返回目标POJO列表
     */
    List<T> selectByExampleWithBLOBs(E example);

    // BLOBs 对应 数据库  Text 类型的列
    // 不是所有逆向工程都有 ， WithBLOBs ，有的有，有的没有 ， 查看 ItemMapper 和  ItamParamMapper 对比

    /**
     * 根据条件进行更新
     *
     * @param record  目标POJO的实例对象
     * @param example 目标POJO的Example实例对象
     * @return 返回数据库更新操作影响行数
     */
    int updateByExampleWithBLOBs(T record, E example);

    /**
     * 根据主键idx更新目标POJO
     *
     * @param record 目标POJO实例对象
     * @return 返回数据库操作影响行数
     */
    int updateByPrimaryKeyWithBLOBs(T record);

}
