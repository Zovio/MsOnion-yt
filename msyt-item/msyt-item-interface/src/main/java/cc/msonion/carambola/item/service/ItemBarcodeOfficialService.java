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


package cc.msonion.carambola.item.service;

/**
 * @Title: ItemBarcodeOfficialService.java
 * @Package: cc.msonion.carambola.item.service
 * @Description: 正式商品条形码
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月23日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年06月23日
 * @Modify-version: V2.0.0
 * @Modify-description:
 *
 */

import cc.msonion.carambola.item.pojo.ItemBarcodeOfficial;
import cc.msonion.carambola.item.pojo.ItemBarcodeOfficialExample;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;

import java.util.List;

/**
 * @ClassName: ItemBarcodeOfficialService
 * @Description: 正式商品条形码service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月23日
 *
 */
public interface ItemBarcodeOfficialService extends MsOnionBaseService<ItemBarcodeOfficial, ItemBarcodeOfficialExample> {


    /**
     *
     * 新增商品条形码
     * @param apiVersion Api版本
     * @param list  正式商品条形码集合
     * @param itemOfficialIdx  正式商品idx
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    MsOnionResult addItemBarcodeOfficialList(MsOnionApiVersion apiVersion,
                                             List<String> list, Long itemOfficialIdx) throws MsOnionIllegalArgumentException, MsOnionException;



    /**
     *
     * 新增商品条形码
     * @param apiVersion Api版本
     * @param itemBarCodeOfficial  正式商品条形码
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    MsOnionResult addItemBarcodeOfficial(MsOnionApiVersion apiVersion,
                                         ItemBarcodeOfficial itemBarCodeOfficial) throws MsOnionIllegalArgumentException, MsOnionException;


    /**
     * 通过正式商品获取商品条形码，返回多个
     *
     * @param apiVersion Api版本
     * @param itemOfficialIdx  正式商品idx
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    MsOnionResult getItemBarcodeOfficialByItemOfficalIdx(MsOnionApiVersion apiVersion,
                                                         Long itemOfficialIdx) throws MsOnionIllegalArgumentException, MsOnionException;


    /**
     * 通过正式商品删除商品条形码
     *
     * @param apiVersion Api版本
     * @param itemOfficialIdx  正式商品idx
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    MsOnionResult deleteItemBarcodeOfficialByItemOfficalIdx(MsOnionApiVersion apiVersion,
                                                            Long itemOfficialIdx) throws MsOnionIllegalArgumentException, MsOnionException;


    /**
     * 判断当前正式商品是否包括该条形码
     *
     * @param apiVersion Api版本
     * @param barcode 条形码
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    MsOnionResult checkItemBarcodeOfficialByItemOfficalIdx(MsOnionApiVersion apiVersion, String barcode)
            throws MsOnionIllegalArgumentException, MsOnionException;

}
