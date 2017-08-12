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
 * @Title: ContentKeywordService.java
 * @Package: cc.msonion.carambola.content.service
 * @Description: 内容图片Service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月16日 下午13:50:21
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年5月16日 下午13:50:21
 * @Modify-version: 2.0.0
 * @Modify-description: 新增Service
 */

import cc.msonion.carambola.content.pojo.ContentKeyword;
import cc.msonion.carambola.content.pojo.ContentKeywordExample;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;

/**
 * @ClassName: ContentKeywordService
 * @Description: 内容图片Service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月16日 下午13:50:21
 */
public interface ContentKeywordService extends MsOnionBaseService<ContentKeyword, ContentKeywordExample> {

    /**
     * 查询关键字分页
     * @Title deleteCacheByKeys
     * @Description 查询关键字分页
     * @param apiVersion api版本
     * @param pageNum    当前页
     * @param pageSize   页大小
     * @param orderField 排序字段
     * @param orderType 排序方式
     * @param keyword       名称模糊查询
     * @param firstWord       类型查询
     * @param isDelete  是否已删除 0 查删除的 1或者NULL查未删除的
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月17日 16:51:19
     */
    MsOnionResult selectKeywordPage(MsOnionApiVersion apiVersion, Integer pageNum, Integer pageSize, String orderField,
                                    String orderType, String keyword, String firstWord, Short isDelete)
                                    throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 保存关键字对象
     * 新增/更新数据
     * @Title deleteCacheByKeys
     * @Description 保存关键字对象
     * @param apiVersion api版本
     * @param contentKeyword 指定需要删除的缓存key对象集合
     * @param operateIdx 操作者ID
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月17日 18:00:24
     */
    MsOnionResult saveOrupdateKeyword(MsOnionApiVersion apiVersion, ContentKeyword contentKeyword, Long operateIdx)
                                    throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 删除关键词
     * @Title deleteCacheByKeys
     * @Description 删除关键词
     * @param apiVersion api版本
     * @param operateIdx 操作者ID
     * @param idx 指定需要删除的对象ID
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月17日 19:46:31
     */
    MsOnionResult deleteKeywordByIdx(MsOnionApiVersion apiVersion, Long operateIdx, Long idx)
            throws MsOnionIllegalArgumentException, MsOnionException;
}
