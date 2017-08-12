/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营报关洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际报关直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */

package cc.msonion.carambola.logistics.service;


import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclare;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareExample;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;

import java.util.Map;

/**
 * @Title: LogisticsCustomsDeclareService.java
 * @Package: cc.msonion.carambola.collector.service
 * @Description: 商品报关Service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: Johnny woo 3028554324@qq.com
 * @Date: 2017年4月11日 下午8:21:21
 * @Version: V2.0.0
 * @Modify-by: Johnny woo 3028554324@qq.com
 * @Modify-date: 2017年4月11日 下午8:21:21
 * @Modify-version: 2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 */

/**
 * @ClassName: LogisticsCustomsDeclareService
 * @Description: 商品报关Service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: Johnny woo 3028554324@qq.com
 * @Date: 2017年4月11日 下午8:21:21
 */
public interface LogisticsCustomsDeclareService extends MsOnionBaseService<LogisticsCustomsDeclare, LogisticsCustomsDeclareExample> {

    /**
     * 检查是否已存在
     *
     * @param apiVersion Api版本
     * @param param      参数值
     * @param type       参数
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常
     * @throws MsOnionException                MsOnion异常类
     */
    MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param, Integer type) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @param apiVersion Api版本
     * @param logisticsCustomsDeclare 商品报关
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title addLogisticsCustomsDeclare
     * @Description 新增商品报关
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午8:21:21
     */
    MsOnionResult addLogisticsCustomsDeclare(MsOnionApiVersion apiVersion, LogisticsCustomsDeclare logisticsCustomsDeclare)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @param apiVersion Api版本
     * @param idx        商品报关主键idx
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteLogisticsCustomsDeclare
     * @Description 删除商品报关
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午8:21:21
     */
    MsOnionResult deleteLogisticsCustomsDeclare(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @param apiVersion Api版本
     * @param logisticsCustomsDeclare 商品报关
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateLogisticsCustomsDeclare
     * @Description 更新商品报关
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午8:21:21
     */
    MsOnionResult updateLogisticsCustomsDeclare(MsOnionApiVersion apiVersion, LogisticsCustomsDeclare logisticsCustomsDeclare)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @param apiVersion Api版本
     * @param idx        商品报关主键idx
     * @return the LogisticsCustomsDeclare by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getLogisticsCustomsDeclareByIdx
     * @Description 通过主键idx得到商品报关
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午8:21:21
     */
    MsOnionResult getLogisticsCustomsDeclareByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取报关信息
     *
     * @param apiVersion Api版本
     * @param itemIdx    商品主键idx
     * @return 返回报关信息
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    MsOnionResult getLogisticsCustomsDeclareByItemIdx(MsOnionApiVersion apiVersion, Long itemIdx)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 商品报关Excel导入编辑
     * @Title updateCustomerIgnoreNull
     * @Description 商品报关
     * @param apiVersion api版本
     * @param logisticsCustomsDeclare 指定需要删除的缓存key对象集合
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月13日 17:02:17
     */
    @Deprecated
    MsOnionResult updateCustomerIgnoreNull(MsOnionApiVersion apiVersion, LogisticsCustomsDeclare logisticsCustomsDeclare)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 批量保存商品报关信息
     *
     * @param apiVersion                 api版本
     * @param logisticsCustomsDeclareMap 商品报关信息
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title batchSaveLogisticsCustomsDeclare
     * @Description 批量保存商品报关信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月21日 14:01:25
     */
    MsOnionResult batchSaveLogisticsCustomsDeclare(MsOnionApiVersion apiVersion,
        Map<Integer, LogisticsCustomsDeclare> logisticsCustomsDeclareMap) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 批量更新商品报关
     *
     * @param apiVersion                 api版本
     * @param logisticsCustomsDeclareMap 商品报关对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title batchUpdateCollectorLogisticsCustoms
     * @Description 批量更新商品报关
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月21日 14:02:09
     */
    MsOnionResult batchUpdateCollectorLogisticsCustoms(MsOnionApiVersion apiVersion,
       Map<Integer, LogisticsCustomsDeclare> logisticsCustomsDeclareMap) throws MsOnionIllegalArgumentException, MsOnionException;



}
