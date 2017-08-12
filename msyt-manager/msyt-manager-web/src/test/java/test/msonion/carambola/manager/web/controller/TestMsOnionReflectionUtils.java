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


package test.msonion.carambola.manager.web.controller;

/**
 * @Title: TestMsOnionReflectionUtils.java
 * @Package: test.msonion.carambola.manager.web.controller
 * @Description: 测试反射工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月25日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月25日
 * @Modify-version: V2.0.0
 * @Modify-description:
 *
 */

import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.warehouse.pojo.Warehouse;
import cc.msonion.carambola.warehouse.pojo.WarehouseOfficial;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @ClassName: TestMsOnionReflectionUtils
 * @Description: 测试反射工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月25日
 *
 */
public class TestMsOnionReflectionUtils {

    @Test
    public void test1() throws  Exception {

        Class<?> clazz = Class.forName("cc.msonion.carambola.collector.pojo.CollectorItem");
        CollectorItem collectorItem = new CollectorItem();
        collectorItem.setItemNo("哈哈哈");
        System.out.println(collectorItem.getClass().getName());
        Field field = ReflectionUtils.findField(collectorItem.getClass(), "itemNo");
        ReflectionUtils.makeAccessible(field);
        System.out.println(field.get(collectorItem));

       /* Method method = MsOnionReflectionUtils.findMethod(clazz, "itemNo");
        CollectorItem collectorItem = (CollectorItem) clazz.newInstance();
        collectorItem.setItemNo("11111");
        Object o = MsOnionReflectionUtils.invokeMethod(method, collectorItem);*/
    }

    @Test
    public void test02() throws  Exception {

        List list1 = new ArrayList();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("4");
        list1.add("6");
        List list2 = new ArrayList();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");
        // 并集
        System.out.println("并集" + CollectionUtils.union(list2, list1));
        // 交集
        System.out.println("交集" + CollectionUtils.intersection(list1, list2));

        System.out.println("list1 与list2补集" + CollectionUtils.disjunction(list1, list2));
        System.out.println("list2 与list1补集" + CollectionUtils.disjunction(list2, list1));

        System.out.println("list1 与list2差集" + CollectionUtils.subtract(list1, list2));
        System.out.println("list2 与list1差集" + CollectionUtils.subtract(list2, list1));
    }

    @Test
    public void test03() throws  Exception {

        Warehouse wh = new Warehouse();
        wh.setIdx(111L);
        wh.setIsFreeShipping((short) 1);

        WarehouseOfficial whr  = new WarehouseOfficial();
        BeanUtils.copyProperties(wh,whr);
        System.out.println(whr);
    }

    @Test
    public void test04() {
        String str = "itemIdx|cc.msonion.carambola.item.pojo.ItemOfficial,itemNo|cc.msonion.carambola.item.pojo.ItemBasicOfficial,cnName|cc.msonion.carambola.item.pojo.ItemBasicOfficial,specification|cc.msonion.carambola.item.pojo.ItemBasicOfficial,offlineByMemberIdx|cc.msonion.carambola.item.pojo.ItemOfficial,offlineTime|cc.msonion.carambola.item.pojo.ItemOfficial,editorIdx|cc.msonion.carambola.item.collector.CollectorItemEditor,searchKeywords|cc.msonion.carambola.item.pojo.ItemCollectionOfficial,crossBorderTaxRate|cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareOfficial,isContainExcise|cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareOfficial,deliveryCoefficient|cc.msonion.carambola.warehouse.pojo.WarehouseOfficial,isKeyOrder|cc.msonion.carambola.warehouse.pojo.WarehouseOfficial,marketPrice|cc.msonion.carambola.item.pojo.ItemPriceOfficial,sellingPrice|cc.msonion.carambola.item.pojo.ItemPriceOfficial";
        List<String> keyList = Arrays.asList(str.split(","));
        Map<String, List<String>> map = new HashMap<>();
        Set<String> pojoSet = new HashSet<>();
        keyList.stream().forEach(s -> {
            String[] arr = s.split("\\|");
            pojoSet.add(arr[1]);
        });

        Iterator<String> it = pojoSet.iterator();
        while (it.hasNext()) {
            String pojo = it.next();
            List<String> l = new ArrayList();
            for (int j = 0; j < keyList.size(); j++) {
                if (keyList.get(j).indexOf(pojo) > 0 ) {
                    l.add(keyList.get(j).split("\\|")[0]);
                }
            }
            map.put(pojo, l);
        }

        System.out.println(map);

    }
}
