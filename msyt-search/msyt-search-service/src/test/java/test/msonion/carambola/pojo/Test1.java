package test.msonion.carambola.pojo;

import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionFixedLengthUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import org.elasticsearch.action.index.IndexResponse;
import org.junit.Test;

/**
 * Created by HeroCao on 2017/6/10.
 */
public class Test1 {


    @Test
    public void test01() throws MsOnionException {


//        CollectorItem


//        MsOnionJsonUtils.


        int i = 1;

        TestCollectorItem collectorItem = new TestCollectorItem();

        collectorItem.setIdx(99990001L + i);
        collectorItem.setBarcode("brcode" + i);
        collectorItem.setBatch(1);
        collectorItem.setBrandIdx(91990001L + i);
        collectorItem.setCategoryIdx(92990001L + i);
        collectorItem.setCnName("中华人民共和国合同法中国香港" + i);
        collectorItem.setCollectionRemark("香港出生的著名程序员。被著名游戏媒体网站Kotaku评为十年来最具影响的十位游戏界女性之一，也是其中唯一的女程序员。" + i);

        collectorItem.setCollectionStatus((short) 1);

        collectorItem.setCreateByMemberIdx(93990001L + i);
//            collectorItem.setCreateTime(new Date(System.currentTimeMillis() - 100000L));
        collectorItem.setEnName("Gearbox Software，中国香港，" + i);
        collectorItem.setExt("中国香港，余国荔总是打扮的像摇滚乐明星一样特立独行，如果有人质疑女性在计算机程序设计方面的能力和贡献。" + i);

        collectorItem.setIdxCode(94990001L + i);

        collectorItem.setImageBigId(95990001L + i);
        collectorItem.setImageMiddleId(96990001L + i);
        collectorItem.setImageSmallId(97990001L + i);

        collectorItem.setItemExtIdx(98990001L + i);

        collectorItem.setItemNo("110022001" + i);
        collectorItem.setPlatformIdx(99090001L + i);

        collectorItem.setItemStateIdx(99190001L + i);

        collectorItem.setOriginIdx(99290001L + i);

        collectorItem.setPurchaseStatus((short) 1);

        collectorItem.setSpecification("中国香港，曾以技术总监的身份在数家3D游戏公司任职，如3D Realms、ION Storm与Gearbox；" + i);


        collectorItem.setStatus((short) 1);

        collectorItem.setUpdateByMemberIdx(99390001L + i);

//        collectorItem.setUpdateTime(new Date(System.currentTimeMillis()));

//            collectorItem.setUpdateTime(new Date());

//            if (i % 10 == 0)

        int value1 = (i % 10) + 1;
        int value2 = (i % 10) + 2;

        String month1 = MsOnionFixedLengthUtils.fixLength(value1, 2);
        String day1 = MsOnionFixedLengthUtils.fixLength(value2, 2);

        System.out.println(String.format("i=%s，value1=%s，value2=%s，month1=%s，day1=%s", i, value1, value2, month1, day1));

        int value3 = (i % 10) + 3;
        int value4 = (i % 10) + 12;

        String month2 = MsOnionFixedLengthUtils.fixLength(value3, 2);
        String day2 = MsOnionFixedLengthUtils.fixLength(value4, 2);

            /*
            i=14，value1=5，value2=6，month1=05，day1=06
i=14，value3=6，value4=14，month2=06，day2=14

                2017-02-08

                2017-05-18
             */
        System.out.println(String.format("i=%s，value3=%s，value4=%s，month2=%s，day2=%s", i, value3, value4, month2, day2));

        collectorItem.setCreateTime(MsOnionDateUtils.parseWithYyyyMMddHHmmss(String.format("2017-%s-%s 01:10:08", month1, day1)));

        collectorItem.setUpdateTime(MsOnionDateUtils.parseWithYyyyMMddHHmmss(String.format("2017-%s-%s 18:38:58", month2, day2)));

        collectorItem.setWarehouseTypeIdx(99490001L + i);

        collectorItem.setWeight(99590001L + i);

        collectorItem.setVersion(99690001L + i);

        System.out.println("collectorItem数据=" + collectorItem);

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

        String json = MsOnionJsonUtils.objectToJson(collectorItem);

        System.out.println("================= Json =========================");

        System.out.println("collectorItem # json=" + json);

//        IndexResponse indexResponse = testCollectorItemSearchService.add(apiVersion, collectorItem.getIdx(), json);
//
//        System.out.println("单元测试#testIndex#测试索引 # indexResponse=" + indexResponse);
//
//
//        msOnionLog.info(getClass().getName(), "单元测试#testIndex#测试索引 # End !!! , i=" + i);

        System.out.println("单元测试#testIndex#测试索引 # End !!! i=" + i);



    }
}
