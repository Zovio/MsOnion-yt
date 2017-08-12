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

/**
 * @Title: MsOnionBaseMapper.java
 * @Package: cc.msonion.carambola.parent.interfaces.mapper.base
 * @Description: MyBatis的BaseMapper
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月13日 上午9:47:42
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月13日 上午9:47:42
 * @Modify-version: V2.0.0
 * @Modify-description: 定义接口所有相关方法
 */

import java.util.List;

/**
 * @param <T> 目标Pojo，都要实现java.io.Serializable 接口，例如：Item
 * @param <E> 目标Pojo的 Example，都要实现java.io.Serializable 接口，例如：ItemExample
 * @ClassName: MsOnionBaseMapper
 * @Description: MyBatis的BaseMapper
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月13日 上午9:47:42
 */
public interface MsOnionBaseMapper<T extends java.io.Serializable, E extends java.io.Serializable> {

    /**
     * 根据条件查询
     *
     * @param example 目标POJO的Example实例对象
     * @return 返回目标POOJO的数量
     */
    long countByExample(E example);

    /**
     * 根据条件删除（暂时没有使用到，因为使用deleteByIdxesWithExample替换）
     *
     * @param example 目标POJO的Example
     * @return 返回数据库删除操作影响行数
     */
    int deleteByExample(E example);

    /**
     * 根据主键idx删除POJO
     *
     * @param idx 主键idx
     * @return 返回数据库删除操作影响行数，1：成功，0：不成功、不存在
     */
    int deleteByPrimaryKey(Long idx);  // Key 固定是 Long类型

    /**
     * 根据主键idx集合进行删除，也就是批量删除，其实是软删除（更新状态），
     * <p> 使用目标POJO的Example的Criteria andIdxIn(List<Long> values) </p>
     *
     * @param example 目标POJO的Example实例对象
     * @return 返回数据库批量删除操作影响行数
     */
    int deleteByIdxesWithExample(E example);

    /**
     * 插入（新增）记录
     *
     * @param record 目标POJO实例对象
     * @return 返回数据库新增操作影响行数，1：成功、0：失败
     */
    int insert(T record);

//    /**
//     * 由于底层架构全部设置默认值，因此不使用
//     * @param record
//     * @return
//     */
//    int insertSelective(T record);

//    List<T> selectByExampleWithBLOBs(E example);

    /**
     * 根据条件查询目标POJO
     *
     * @param example 目POJO的Example实例对象
     * @return 返回条件查询目标POJO的列表
     */
    List<T> selectByExample(E example);

    /**
     * 根据主键idx查询
     *
     * @param idx 主键idx
     * @return 返回目标POJO实例对象
     */
    T selectByPrimaryKey(Long idx);

//    /**
//     * 由于底层架构全部设置默认值，因此不使用
//     * @param record 目标POJO实例对象
//     * @param example 目标POJO的Example实例对象
//     * @return 返回数据库更新影响行数
//     */
//    int updateByExampleSelective(T record, E example);

    // 不是所有逆向工程都有 ， WithBLOBs ，有的有，有的没有
//    int updateByExampleWithBLOBs(T record, E example);

    /**
     * 根据条件进行更新
     *
     * @param record  目标POJO实例对象
     * @param example 目标POJO的Example的实例对象
     * @return 返回数据库更新操作影响行数
     */
    int updateByExample(T record, E example);

//    /**
//     * 由于底层架构全部设置默认值，因此不使用
//     *
//     * @param record 目标POJO的实例对象
//     * @return 返回数据库更新操作影响行数
//     */
//    int updateByPrimaryKeySelective(T record);

//    int updateByPrimaryKeyWithBLOBs(T record);

    /**
     * 根据主键idx更新目标POJO
     *
     * @param record 目标POJO实例对象
     * @return 返回数据库更新操作影响行数，1：成功、0：失败
     */
    int updateByPrimaryKey(T record);

}
