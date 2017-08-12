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

package cc.msonion.carambola.warehouse.service;
/**
 * @Title: WarehouseService.java
 * @Package: cc.msonion.carambola.warehouse.service
 * @Description: 商品仓库service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月23日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月23日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.thirdplatform.erp.pojo.response.Stock;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.warehouse.pojo.Warehouse;
import cc.msonion.carambola.warehouse.pojo.WarehouseExample;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WarehouseService
 * @Description: 商品仓库service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月23日
 */
public interface WarehouseService extends MsOnionBaseService<Warehouse, WarehouseExample> {

    /**
     * 新增商品仓库
     *
     * @param apiVersion Api版本
     * @param warehouse  商品仓库
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Date 2017年4月11日 下午8:21:21
     */
    MsOnionResult addWarehouse(MsOnionApiVersion apiVersion, Warehouse warehouse) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 删除商品仓库
     *
     * @param apiVersion Api版本
     * @param idx        商品报关主键idx
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Date 2017年4月11日 下午8:21:21
     */
    MsOnionResult deleteWarehouse(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 更新商品仓库
     *
     * @param apiVersion Api版本
     * @param warehouse  商品仓库
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Date 2017年4月11日 下午8:21:21
     */
    MsOnionResult updateWarehouse(MsOnionApiVersion apiVersion, Warehouse warehouse) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 通过idx获取商品仓库
     *
     * @param apiVersion Api版本
     * @param idx        商品报关主键idx
     * @return the LogisticsCustomsDeclare by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Date 2017年4月11日 下午8:21:21
     */
    MsOnionResult getWarehouseByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 通过商品获取仓库
     *
     * @param apiVersion Api版本
     * @param itemIdx    商品主键idx
     * @return 返回报关信息
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    MsOnionResult getWarehouseByItemIdx(MsOnionApiVersion apiVersion, Long itemIdx) throws MsOnionIllegalArgumentException, MsOnionException;



    /**
     * 商品库存
     *
     * @param apiVersion Api版本
     * @param warehouseList  商品库存对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月08日 18:06:44
     */
    @Deprecated
    MsOnionResult syncWarehouseStock(MsOnionApiVersion apiVersion, List<Warehouse> warehouseList)
            throws MsOnionIllegalArgumentException, MsOnionException;


    /**
     * 新增或更新库存
     * @param apiVersion Api版本
     * @param warehouse 库存对象
     * @return MsOnionResult  the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月08日 18:06:44
     */
    @Deprecated
    MsOnionResult saveOrupdateWarehouseStock(MsOnionApiVersion apiVersion, Warehouse warehouse)
            throws MsOnionIllegalArgumentException, MsOnionException;


    /**
     * 批量同步商品库存
     *
     * @param apiVersion Api版本
     * @param erpStockList  ERP库存对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月08日 18:06:44
     */
    MsOnionResult batchSyncStock(MsOnionApiVersion apiVersion, List<Stock> erpStockList);

    /**
     * 批量同步商品库存
     *
     * @param apiVersion Api版本
     * @param erpStockList  ERP库存对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月08日 18:06:44
     */
    MsOnionResult batchSyncStock(MsOnionApiVersion apiVersion, Map<Integer, Stock> erpStockList);

}
