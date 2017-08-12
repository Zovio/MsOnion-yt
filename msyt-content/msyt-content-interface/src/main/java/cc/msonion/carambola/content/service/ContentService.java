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
package cc.msonion.carambola.content.service;


/**
 * @Title: ContentService.java
 * @Package: cc.msonion.carambola.content.service
 * @Description: 内容Service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月16日 下午13:50:21
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年5月16日 下午13:50:21
 * @Modify-version: 2.0.0
 * @Modify-description: 新增Service
 */
import cc.msonion.carambola.content.pojo.Content;
import cc.msonion.carambola.content.pojo.ContentExample;
import cc.msonion.carambola.content.pojo.view.ContentView;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;



/**
 * @ClassName: ContentService
 * @Description: 内容Service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月16日 下午13:50:21
 */
public interface ContentService extends MsOnionBaseService<Content, ContentExample> {

    /**
     * 查询内容数据分页
     *
     * @param apiVersion 版本号
     * @param pageNum    当前页
     * @param pageSize   页大小
     * @param orderField 排序字段
     * @param orderType  排序方式
     * @param name       名称模糊查询
     * @param type       类型查询
     * @param isDelete  是否已删除 0 查删除的 1或者NULL查未删除的
     * @return MsOnionResult
     * @throws MsOnionIllegalArgumentException 参数异常
     * @throws MsOnionException                未知异常
     * @Title selectContentPage
     * @Description 查询内容数据分页
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月17日 15:36:39
     */
    MsOnionResult selectContentPage(MsOnionApiVersion apiVersion, Integer pageNum, Integer pageSize, String orderField,
                                    String orderType, String name, Short type, Short isDelete)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取内容对象的详情数据
     *
     * @param apiVersion 版本号
     * @param idx        内容对象ID
     * @return 对象的详情数据
     * @throws MsOnionIllegalArgumentException 参数异常
     * @throws MsOnionException                未知异常
     * @Title getContentDetail
     * @Description 获取内容对象的详情数据
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月17日 15:36:39
     */
    MsOnionResult getContentDetail(MsOnionApiVersion apiVersion, Long idx)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 保存内容对象数据
     * 新增/更新内容对象
     *
     * @param apiVersion api版本
     * @param contentView  内容对象数据
     * @param operateIdx 操作者ID
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title saveContentDetail
     * @Description 保存内容对象数据
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月17日 15:36:39
     */
    MsOnionResult saveContentDetail(MsOnionApiVersion apiVersion, ContentView contentView, Long operateIdx)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 删除内容对象
     *
     * @param apiVersion api版本
     * @param operateIdx 操作者ID
     * @param idx        内容对象ID
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteContentByIdx
     * @Description 描述信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月17日 14:55:24
     */
    MsOnionResult deleteContentByIdx(MsOnionApiVersion apiVersion, Long operateIdx, Long idx)
            throws MsOnionIllegalArgumentException, MsOnionException;


    /**
     * 查询标识码是否已经存在
     *
     * @param apiVersion api版本
     * @param code       标识码
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteCacheByKeys
     * @Description 查询标识码是否已经存在
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月18日 11:05:29
     */
    MsOnionResult selectCodeIsUseable(MsOnionApiVersion apiVersion, String code)
            throws MsOnionIllegalArgumentException, MsOnionException;

  /**
   * 根据编码查询内容
   * @Title getContentByCode
   * @Description 描述信息
   * @param apiVersion api版本
   * @param code 指定需要删除的缓存key对象集合
   * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
   * @throws MsOnionException                the ms onion exception
   * @return the ms onion result
   * @Author DorenWu DorenWu@msyc.cc
   * @Date 2017年05月22日 14:19:35
   */
    MsOnionResult getContentByCode(MsOnionApiVersion apiVersion, String code)
            throws MsOnionIllegalArgumentException, MsOnionException;
}

