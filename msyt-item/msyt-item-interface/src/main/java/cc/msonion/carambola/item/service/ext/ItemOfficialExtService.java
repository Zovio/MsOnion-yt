package cc.msonion.carambola.item.service.ext;

/**
 * @Title: ItemOfficialExtService.java
 * @Package: cc.msonion.carambola.item.service.ext
 * @Description: 正式商品扩展服务
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/6/27 14:51
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017/6/27 14:51
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：搜索方法
 */

import cc.msonion.carambola.item.ext.pojo.ItemOfficialExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;

import java.util.List;

/**
 * @ClassName: ItemOfficialExtService
 * @Description: 正式商品扩展服务
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/6/27 14:51
 */
public interface ItemOfficialExtService {

    /**
     * 查询正式商品数量
     *
     * @param apiVersion      Api版本
     * @param itemOfficialExt 正式商品扩展
     * @return 返回正式商品数量
     */
    long getCount(MsOnionApiVersion apiVersion, ItemOfficialExt itemOfficialExt);

    /**
     * 查询正式商品列表
     *
     * @param apiVersion      Api版本
     * @param itemOfficialExt 正式商品扩展
     * @return 返回正式商品列表
     */
    List<ItemOfficialExt> selectByPage(MsOnionApiVersion apiVersion, ItemOfficialExt itemOfficialExt);

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion      Api版本
     * @param itemOfficialExt 正式商品扩展
     * @return 返回查询目标POJO集合
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, ItemOfficialExt itemOfficialExt);
}
