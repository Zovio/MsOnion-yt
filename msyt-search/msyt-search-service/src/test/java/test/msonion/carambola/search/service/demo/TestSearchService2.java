package test.msonion.carambola.search.service.demo;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionFixedLengthUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.elasticsearch.adapter.MsOnionQuerySchemeAdapter;
import cc.msonion.carambola.parent.ext.elasticsearch.client.MsOnionTransportClient;
import cc.msonion.carambola.parent.ext.elasticsearch.enums.MsOnionFullTextQueryPattern;
import cc.msonion.carambola.parent.ext.elasticsearch.enums.MsOnionTermLevelQueryPattern;
import cc.msonion.carambola.parent.ext.elasticsearch.pojo.MsOnionSearchCondition;
import cc.msonion.carambola.parent.ext.elasticsearch.pojo.paging.MsOnionESSearchPagingResult;
import cc.msonion.carambola.parent.ext.elasticsearch.pojo.queryscheme.MsOnionFullTextQueryScheme;
import cc.msonion.carambola.parent.ext.elasticsearch.pojo.queryscheme.MsOnionTermLevelQueryScheme;
import cc.msonion.carambola.parent.ext.elasticsearch.utils.MsOnionElasticsearchUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.msonion.carambola.pojo.TestCollectorItem;
import test.msonion.carambola.search.service.collector.TestCollectorItemSearchService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by HeroCao on 2017/6/10.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext-*.xml"})
public class TestSearchService2 {


    @Autowired
    private TestCollectorItemSearchService testCollectorItemSearchService;

    @Autowired
    private MsOnionLogger msOnionLogger;


    @Test
    public void testIndex() throws MsOnionException {

        msOnionLogger.info(getClass().getName(), "单元测试#testIndex#测试索引 # Beginning ...");


//        MsOnionTransportClient transportClient = new MsOnionTransportClient("");

        System.out.println("单元测试#testIndex#测试索引 # Beginning ...");


        TestCollectorItem collectorItem = new TestCollectorItem();

        collectorItem.setIdx(99990001L);
        collectorItem.setBarcode("barcode0001");
        collectorItem.setBatch(1);
        collectorItem.setBrandIdx(91990001L);
        collectorItem.setCategoryIdx(92990001L);
        collectorItem.setCnName("中华人民共和国合同法01");
        collectorItem.setCollectionRemark("香港出生的著名程序员。被著名游戏媒体网站Kotaku评为十年来最具影响的十位游戏界女性之一，也是其中唯一的女程序员。");

        collectorItem.setCollectionStatus((short)1);

        collectorItem.setCreateByMemberIdx(93990001L);
        collectorItem.setCreateTime(new Date(System.currentTimeMillis() - 100000L));
        collectorItem.setEnName("Gearbox Software");
        collectorItem.setExt("余国荔总是打扮的像摇滚乐明星一样特立独行，如果有人质疑女性在计算机程序设计方面的能力和贡献。");

        collectorItem.setIdxCode(94990001L);

        collectorItem.setImageBigId(95990001L);
        collectorItem.setImageMiddleId(96990001L);
        collectorItem.setImageSmallId(97990001L);

        collectorItem.setItemExtIdx(98990001L);

        collectorItem.setItemNo("100022001");
        collectorItem.setPlatformIdx(99090001L);

        collectorItem.setItemStateIdx(99190001L);

        collectorItem.setOriginIdx(99290001L);

        collectorItem.setPurchaseStatus((short)1);

        collectorItem.setSpecification("曾以技术总监的身份在数家3D游戏公司任职，如3D Realms、ION Storm与Gearbox；");


        collectorItem.setStatus((short)1);

        collectorItem.setUpdateByMemberIdx(99390001L);

//        collectorItem.setUpdateTime(new Date(System.currentTimeMillis()));

        collectorItem.setUpdateTime(new Date());

        collectorItem.setWarehouseTypeIdx(99490001L);

        collectorItem.setWeight(99590001L);

        collectorItem.setVersion(99690001L);

        System.out.println("collectorItem数据=" + collectorItem);

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

        String json = MsOnionJsonUtils.objectToJson(collectorItem);

        System.out.println("collectorItem # json=" + json);

        IndexResponse indexResponse = testCollectorItemSearchService.add(apiVersion, collectorItem.getIdx(), json);

        System.out.println("单元测试#testIndex#测试索引 # indexResponse=" + indexResponse);


        msOnionLogger.info(getClass().getName(), "单元测试#testIndex#测试索引 # End !!!");

        System.out.println("单元测试#testIndex#测试索引 # End !!!");

        /**
         * 单元测试#testIndex#测试索引 # indexResponse=IndexResponse[index=test-msyt-collector,type=test.msonion.carambola.pojo.TestCollectorItem,id=AVySavNr5mZxoSWzeBwk,version=1,result=created,
         * shards={"_shards":{"total":2,"successful":2,"failed":0}}]
         */

        /*
        collectorItem数据=TestCollectorItem [Hash = 1780313842, idx=99990001, idxCode=94990001, platformIdx=99090001, itemStateIdx=99190001, itemExtIdx=98990001, itemNo=100022001, barcode=barcode0001, cnName=中华人民共和国合同法01, enName=Gearbox Software, brandIdx=91990001, originIdx=99290001, categoryIdx=92990001, specification=曾以技术总监的身份在数家3D游戏公司任职，如3D Realms、ION Storm与Gearbox；, warehouseTypeIdx=99490001, batch=1, collectionRemark=香港出生的著名程序员。被著名游戏媒体网站Kotaku评为十年来最具影响的十位游戏界女性之一，也是其中唯一的女程序员。, imageSmallIdDynamicS=null, imageSmallId=97990001, imageMiddleIdDynamicS=null, imageMiddleId=96990001, imageBigIdDynamicS=null, imageBigId=95990001, purchaseStatus=1, collectionStatus=1, weight=99590001, createByMemberIdx=93990001, updateByMemberIdx=99390001, createTime=Sat Jun 10 23:05:52 CST 2017, updateTime=Sat Jun 10 23:07:32 CST 2017, status=1, version=99690001, ext=余国荔总是打扮的像摇滚乐明星一样特立独行，如果有人质疑女性在计算机程序设计方面的能力和贡献。, serialVersionUID=1, id=null]
collectorItem # json={"idx":99990001,"idxCode":94990001,"platformIdx":99090001,"itemStateIdx":99190001,"itemExtIdx":98990001,"itemNo":"100022001","barcode":"barcode0001","cnName":"中华人民共和国合同法01","enName":"Gearbox Software","brandIdx":91990001,"originIdx":99290001,"categoryIdx":92990001,"specification":"曾以技术总监的身份在数家3D游戏公司任职，如3D Realms、ION Storm与Gearbox；","warehouseTypeIdx":99490001,"batch":1,"collectionRemark":"香港出生的著名程序员。被著名游戏媒体网站Kotaku评为十年来最具影响的十位游戏界女性之一，也是其中唯一的女程序员。","imageSmallIdDynamicS":null,"imageSmallId":97990001,"imageMiddleIdDynamicS":null,"imageMiddleId":96990001,"imageBigIdDynamicS":null,"imageBigId":95990001,"purchaseStatus":1,"collectionStatus":1,"weight":99590001,"createByMemberIdx":93990001,"updateByMemberIdx":99390001,"createTime":1497107152171,"updateTime":1497107252171,"status":1,"version":99690001,"ext":"余国荔总是打扮的像摇滚乐明星一样特立独行，如果有人质疑女性在计算机程序设计方面的能力和贡献。","id":"99990001"}

         */

    }


    @Test
    public void testIndex01_1() throws MsOnionException {

        msOnionLogger.info(getClass().getName(), "testIndex01_1#单元测试#testIndex#测试索引 # Beginning ...");


//        MsOnionTransportClient transportClient = new MsOnionTransportClient("");

        System.out.println("单元测试#testIndex01_1#测试索引 # Beginning ...");

        for (int i = 1; i < 100; i++) {

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

            System.out.println("collectorItem # json=" + json);

            IndexResponse indexResponse = testCollectorItemSearchService.add(apiVersion, collectorItem.getIdx(), json);

            System.out.println("单元测试#testIndex#测试索引 # indexResponse=" + indexResponse);


            msOnionLogger.info(getClass().getName(), "单元测试#testIndex#测试索引 # End !!! , i=" + i);

            System.out.println("单元测试#testIndex#测试索引 # End !!! i=" + i);

        }

        /**
         * 单元测试#testIndex#测试索引 # indexResponse=IndexResponse[index=test-msyt-collector,type=test.msonion.carambola.pojo.TestCollectorItem,id=AVySavNr5mZxoSWzeBwk,version=1,result=created,
         * shards={"_shards":{"total":2,"successful":2,"failed":0}}]
         */

        /*
        collectorItem数据=TestCollectorItem [Hash = 1780313842, idx=99990001, idxCode=94990001, platformIdx=99090001, itemStateIdx=99190001, itemExtIdx=98990001, itemNo=100022001, barcode=barcode0001, cnName=中华人民共和国合同法01, enName=Gearbox Software, brandIdx=91990001, originIdx=99290001, categoryIdx=92990001, specification=曾以技术总监的身份在数家3D游戏公司任职，如3D Realms、ION Storm与Gearbox；, warehouseTypeIdx=99490001, batch=1, collectionRemark=香港出生的著名程序员。被著名游戏媒体网站Kotaku评为十年来最具影响的十位游戏界女性之一，也是其中唯一的女程序员。, imageSmallIdDynamicS=null, imageSmallId=97990001, imageMiddleIdDynamicS=null, imageMiddleId=96990001, imageBigIdDynamicS=null, imageBigId=95990001, purchaseStatus=1, collectionStatus=1, weight=99590001, createByMemberIdx=93990001, updateByMemberIdx=99390001, createTime=Sat Jun 10 23:05:52 CST 2017, updateTime=Sat Jun 10 23:07:32 CST 2017, status=1, version=99690001, ext=余国荔总是打扮的像摇滚乐明星一样特立独行，如果有人质疑女性在计算机程序设计方面的能力和贡献。, serialVersionUID=1, id=null]
collectorItem # json={"idx":99990001,"idxCode":94990001,"platformIdx":99090001,"itemStateIdx":99190001,"itemExtIdx":98990001,"itemNo":"100022001","barcode":"barcode0001","cnName":"中华人民共和国合同法01","enName":"Gearbox Software","brandIdx":91990001,"originIdx":99290001,"categoryIdx":92990001,"specification":"曾以技术总监的身份在数家3D游戏公司任职，如3D Realms、ION Storm与Gearbox；","warehouseTypeIdx":99490001,"batch":1,"collectionRemark":"香港出生的著名程序员。被著名游戏媒体网站Kotaku评为十年来最具影响的十位游戏界女性之一，也是其中唯一的女程序员。","imageSmallIdDynamicS":null,"imageSmallId":97990001,"imageMiddleIdDynamicS":null,"imageMiddleId":96990001,"imageBigIdDynamicS":null,"imageBigId":95990001,"purchaseStatus":1,"collectionStatus":1,"weight":99590001,"createByMemberIdx":93990001,"updateByMemberIdx":99390001,"createTime":1497107152171,"updateTime":1497107252171,"status":1,"version":99690001,"ext":"余国荔总是打扮的像摇滚乐明星一样特立独行，如果有人质疑女性在计算机程序设计方面的能力和贡献。","id":"99990001"}

         */

    }

    @Test
    public void testIndex02() throws MsOnionException {

        msOnionLogger.info(getClass().getName(), "testIndex02#单元测试#testIndex#测试索引 # Beginning ...");


//        MsOnionTransportClient transportClient = new MsOnionTransportClient("");

        System.out.println("单元测试#testIndex#测试索引 # testIndex02# Beginning ...");


        TestCollectorItem collectorItem = new TestCollectorItem();

        collectorItem.setIdx(89990001L);
        collectorItem.setBarcode("Elasticsearch 01");
        collectorItem.setBatch(1);
        collectorItem.setBrandIdx(81990001L);
        collectorItem.setCategoryIdx(82990001L);
        collectorItem.setCnName("For more information on the index operation, check out the REST index docs");
        collectorItem.setCollectionRemark("The index API allows one to set the threading model the operation will be performed when the actual execution of the API is performed on the same node (the API is executed on a shard that is allocated on the same server)");

        collectorItem.setCollectionStatus((short)1);

        collectorItem.setCreateByMemberIdx(83990001L);
//        collectorItem.setCreateTime(new Date(System.currentTimeMillis() - 100000L));
        collectorItem.setEnName("Gearbox Software Elasticsearch");
        collectorItem.setExt("The index API allows one to index a typed JSON document into a specific index and make it searchable.");

        collectorItem.setIdxCode(84990001L);

        collectorItem.setImageBigId(85990001L);
        collectorItem.setImageMiddleId(86990001L);
        collectorItem.setImageSmallId(87990001L);

        collectorItem.setItemExtIdx(88990001L);

        collectorItem.setItemNo("200022001");
        collectorItem.setPlatformIdx(89090001L);

        collectorItem.setItemStateIdx(89190001L);

        collectorItem.setOriginIdx(89290001L);

        collectorItem.setPurchaseStatus((short)1);

        collectorItem.setSpecification("Note that you can also add arrays with startArray(String) and endArray() methods. By the way, the field method accepts many object types. ");


        collectorItem.setStatus((short)1);

        collectorItem.setUpdateByMemberIdx(89390001L);

//        collectorItem.setUpdateTime(new Date(System.currentTimeMillis()));

        collectorItem.setUpdateTime(new Date());

        collectorItem.setWarehouseTypeIdx(89490001L);

        collectorItem.setWeight(89590001L);

        collectorItem.setVersion(89690001L);

        System.out.println("collectorItem数据=" + collectorItem);

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

        String json = MsOnionJsonUtils.objectToJson(collectorItem);

        System.out.println("collectorItem # json=" + json);

        IndexResponse indexResponse = testCollectorItemSearchService.add(apiVersion, collectorItem.getIdx(), json);

        System.out.println("单元测试#testIndex#测试索引 # indexResponse=" + indexResponse);


        msOnionLogger.info(getClass().getName(), "单元测试#testIndex#测试索引 # End !!!");

        System.out.println("testIndex02#单元测试#testIndex#测试索引 # End !!!");

        /**
         * 单元测试#testIndex#测试索引 # indexResponse=IndexResponse[index=test-msyt-collector,type=test.msonion.carambola.pojo.TestCollectorItem,id=AVySavNr5mZxoSWzeBwk,version=1,result=created,
         * shards={"_shards":{"total":2,"successful":2,"failed":0}}]
         */

        /*
collectorItem #
 json={"idx":89990001,"idxCode":84990001,"platformIdx":89090001,"itemStateIdx":89190001,"itemExtIdx":88990001,"itemNo":"200022001","barcode":"Elasticsearch 01","cnName":"For more information on the index operation, check out the REST index docs","enName":"Gearbox Software Elasticsearch","brandIdx":81990001,"originIdx":89290001,"categoryIdx":82990001,"specification":"Note that you can also add arrays with startArray(String) and endArray() methods. By the way, the field method accepts many object types.","warehouseTypeIdx":89490001,"batch":1,"collectionRemark":"The index API allows one to set the threading model the operation will be performed when the actual execution of the API is performed on the same node (the API is executed on a shard that is allocated on the same server)","imageSmallIdDynamicS":null,"imageSmallId":87990001,"imageMiddleIdDynamicS":null,"imageMiddleId":86990001,"imageBigIdDynamicS":null,"imageBigId":85990001,"purchaseStatus":1,"collectionStatus":1,"weight":89590001,"createByMemberIdx":83990001,"updateByMemberIdx":89390001,"createTime":1497150610765,"updateTime":1497150710765,"status":1,"version":89690001,"ext":"The index API allows one to index a typed JSON document into a specific index and make it searchable.","id":"89990001"}
单元测试#testIndex#测试索引 #
indexResponse=IndexResponse[index=test-msyt-collector,type=test.msonion.carambola.pojo.TestCollectorItem,id=89990001,version=1,result=created,shards={"_shards":{"total":2,"successful":2,"failed":0}}]
testIndex02#单元测试#testIndex#测试索引 # End !!!

         */

    }

    @Test
    public void testIndex03() throws MsOnionException {

        msOnionLogger.info(getClass().getName(), "testIndex03#单元测试#testIndex#测试索引 # Beginning ...");


//        MsOnionTransportClient transportClient = new MsOnionTransportClient("");

        System.out.println("单元测试#testIndex#测试索引 # testIndex03# Beginning ...");


        for (int i = 1; i <= 100; i++) {

            TestCollectorItem collectorItem = new TestCollectorItem();

            collectorItem.setIdx(89990001L + i);
            collectorItem.setBarcode("Elasticsearch 01");
            collectorItem.setBatch(1);
            collectorItem.setBrandIdx(81990001L + i);
            collectorItem.setCategoryIdx(82990001L + i);
            collectorItem.setCnName("For more information on the index operation, check out the REST index docs");
            collectorItem.setCollectionRemark("The index API allows one to set the threading model the operation will be performed when the actual execution of the API is performed on the same node (the API is executed on a shard that is allocated on the same server)");

            collectorItem.setCollectionStatus((short) 1);

            collectorItem.setCreateByMemberIdx(83990001L + i);
//        collectorItem.setCreateTime(new Date(System.currentTimeMillis() - 100000L));
            collectorItem.setEnName("Gearbox Software Elasticsearch");
            collectorItem.setExt("The index API allows one to index a typed JSON document into a specific index and make it searchable.");

            collectorItem.setIdxCode(84990001L + i);

            collectorItem.setImageBigId(85990001L + i);
            collectorItem.setImageMiddleId(86990001L + i);
            collectorItem.setImageSmallId(87990001L + i);

            collectorItem.setItemExtIdx(88990001L + i);

            collectorItem.setItemNo("200022001" + i);
            collectorItem.setPlatformIdx(89090001L + i);

            collectorItem.setItemStateIdx(89190001L + i);

            collectorItem.setOriginIdx(89290001L + i);

            collectorItem.setPurchaseStatus((short) 1);

            collectorItem.setSpecification("Note that you can also add arrays with startArray(String) and endArray() methods. By the way, the field method accepts many object types. ");


            collectorItem.setStatus((short) 1);

            collectorItem.setUpdateByMemberIdx(89390001L + i);

//        collectorItem.setUpdateTime(new Date(System.currentTimeMillis()));

//            if (i % 10 == 0)
            int value1 = (i % 10) + 1;
            int value2 = (i % 10) + 2;

            String month1 = MsOnionFixedLengthUtils.fixLength(value1, 2);
            String day1 = MsOnionFixedLengthUtils.fixLength(value2, 2);

            System.out.println(String.format("i=%s，value1=%s，value2=%s，month1=%s，day1=%s", i, value1, value2, month1, day1));

            int value3 = (i % 10) + 2;
            int value4 = (i % 10) + 10;

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

//            System.out.println(String.format("i=%，创建时间：%s，更新时间：%s", i, collectorItem.getCreateTime(), collectorItem.getUpdateTime()));

            collectorItem.setWarehouseTypeIdx(89490001L + i);

            collectorItem.setWeight(89590001L + i);

            collectorItem.setVersion(89690001L + i);

            System.out.println("collectorItem数据=" + collectorItem);

            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

            apiVersion.setRequestApiVersion("V2.0.0");

            String json = MsOnionJsonUtils.objectToJson(collectorItem);

            System.out.println("collectorItem # json=" + json);

            IndexResponse indexResponse = testCollectorItemSearchService.add(apiVersion, collectorItem.getIdx(), json);

            System.out.println("单元测试#testIndex#测试索引 # indexResponse=" + indexResponse + "，i=" + i);


            msOnionLogger.info(getClass().getName(), "单元测试#testIndex#测试索引 # End !!! i=" + i);

            System.out.println("testIndex02#单元测试#testIndex#测试索引 # End !!!");

        }

        /**
         * 单元测试#testIndex#测试索引 # indexResponse=IndexResponse[index=test-msyt-collector,type=test.msonion.carambola.pojo.TestCollectorItem,id=AVySavNr5mZxoSWzeBwk,version=1,result=created,
         * shards={"_shards":{"total":2,"successful":2,"failed":0}}]
         */

        /*
collectorItem #
 json={"idx":89990001,"idxCode":84990001,"platformIdx":89090001,"itemStateIdx":89190001,"itemExtIdx":88990001,"itemNo":"200022001","barcode":"Elasticsearch 01","cnName":"For more information on the index operation, check out the REST index docs","enName":"Gearbox Software Elasticsearch","brandIdx":81990001,"originIdx":89290001,"categoryIdx":82990001,"specification":"Note that you can also add arrays with startArray(String) and endArray() methods. By the way, the field method accepts many object types.","warehouseTypeIdx":89490001,"batch":1,"collectionRemark":"The index API allows one to set the threading model the operation will be performed when the actual execution of the API is performed on the same node (the API is executed on a shard that is allocated on the same server)","imageSmallIdDynamicS":null,"imageSmallId":87990001,"imageMiddleIdDynamicS":null,"imageMiddleId":86990001,"imageBigIdDynamicS":null,"imageBigId":85990001,"purchaseStatus":1,"collectionStatus":1,"weight":89590001,"createByMemberIdx":83990001,"updateByMemberIdx":89390001,"createTime":1497150610765,"updateTime":1497150710765,"status":1,"version":89690001,"ext":"The index API allows one to index a typed JSON document into a specific index and make it searchable.","id":"89990001"}
单元测试#testIndex#测试索引 #
indexResponse=IndexResponse[index=test-msyt-collector,type=test.msonion.carambola.pojo.TestCollectorItem,id=89990001,version=1,result=created,shards={"_shards":{"total":2,"successful":2,"failed":0}}]
testIndex02#单元测试#testIndex#测试索引 # End !!!

         */

    }

    @Test
    public void testDelete() throws MsOnionException {

        System.out.println("删除 # Beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

        long idx = 89990081;

        MsOnionResult msOnionResult = testCollectorItemSearchService.deleteForResult(apiVersion, idx);

        /*

        // 删除成功
        删除 # msOnionResult=MsOnionResult [status=200, msg=OK, data=DeleteResponse[index=test-msyt-collector2,type=TestCollectorItem,id=89990081,version=2,
        result=deleted,
        shards=ShardInfo{total=2, successful=2, failures=[]}]]

             result=deleted,

             // 删除失败
             删除 # msOnionResult=MsOnionResult [status=200, msg=OK,
              data=DeleteResponse[index=test-msyt-collector2,type=TestCollectorItem,id=89990081,
              version=1,
              result=not_found,shards=ShardInfo{total=2, successful=2, failures=[]}]]
删除 # End !!!



         */
        System.out.println("删除 # msOnionResult=" + msOnionResult);

        System.out.println("删除 # End !!!");

    }

    @Test
    public void testUpdate() throws MsOnionException {


        System.out.println("更新 # Beginning ...");

//        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
//
//        apiVersion.setRequestApiVersion("V2.0.0");

//        long idx = 89990081;

        int i = 0;

        long idx = 89990090L + i;

        TestCollectorItem collectorItem = new TestCollectorItem();

        collectorItem.setIdx(idx);
        collectorItem.setBarcode("Elasticsearch update01");
        collectorItem.setBatch(1);
        collectorItem.setBrandIdx(81990001L + i);
        collectorItem.setCategoryIdx(82990001L + i);
        collectorItem.setCnName("update01,For more information on the index operation, check out the REST index docs");
        collectorItem.setCollectionRemark("update01,The index API allows one to set the threading model the operation will be performed when the actual execution of the API is performed on the same node (the API is executed on a shard that is allocated on the same server)");

        collectorItem.setCollectionStatus((short) 1);

        collectorItem.setCreateByMemberIdx(83990001L + i);
//        collectorItem.setCreateTime(new Date(System.currentTimeMillis() - 100000L));
        collectorItem.setEnName("Gearbox Software Elasticsearch");
        collectorItem.setExt("update01,The index API allows one to index a typed JSON document into a specific index and make it searchable.");

        collectorItem.setIdxCode(84990001L + i);

        collectorItem.setImageBigId(85990001L + i);
        collectorItem.setImageMiddleId(86990001L + i);
        collectorItem.setImageSmallId(87990001L + i);

        collectorItem.setItemExtIdx(88990001L + i);

        collectorItem.setItemNo("200022001" + i);
        collectorItem.setPlatformIdx(89090001L + i);

        collectorItem.setItemStateIdx(89190001L + i);

        collectorItem.setOriginIdx(89290001L + i);

        collectorItem.setPurchaseStatus((short) 1);

        collectorItem.setSpecification("update01,Note that you can also add arrays with startArray(String) and endArray() methods. By the way, the field method accepts many object types. ");


        collectorItem.setStatus((short) 1);

        collectorItem.setUpdateByMemberIdx(89390001L + i);

//        collectorItem.setUpdateTime(new Date(System.currentTimeMillis()));

//            if (i % 10 == 0)
        int value1 = (i % 10) + 1;
        int value2 = (i % 10) + 2;

        String month1 = MsOnionFixedLengthUtils.fixLength(value1, 2);
        String day1 = MsOnionFixedLengthUtils.fixLength(value2, 2);

        System.out.println(String.format("i=%s，value1=%s，value2=%s，month1=%s，day1=%s", i, value1, value2, month1, day1));

        int value3 = (i % 10) + 2;
        int value4 = (i % 10) + 10;

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

//            System.out.println(String.format("i=%，创建时间：%s，更新时间：%s", i, collectorItem.getCreateTime(), collectorItem.getUpdateTime()));

        collectorItem.setWarehouseTypeIdx(89490001L + i);

        collectorItem.setWeight(89590001L + i);

        collectorItem.setVersion(89690001L + i);

        System.out.println("collectorItem数据=" + collectorItem);

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

        String json = MsOnionJsonUtils.objectToJson(collectorItem);

        System.out.println("collectorItem # json=" + json);

        IndexResponse indexResponse = testCollectorItemSearchService.add(apiVersion, collectorItem.getIdx(), json);

        System.out.println("更新 ###测试索引 # indexResponse=" + indexResponse + "，i=" + i);


        msOnionLogger.info(getClass().getName(), "单元测试#testIndex#测试索引 # End !!! i=" + i);

        System.out.println("更新 ##单元测试#testIndex#测试索引 # End !!!");

//        String json2 = MsOnionJsonUtils.objectToJson(collectorItem);

        MsOnionResult msOnionResult = testCollectorItemSearchService.updateForResult(apiVersion, idx, json);


        /*
        更新 # msOnionResult=MsOnionResult
         [status=200, msg=OK, data=UpdateResponse[index=test-msyt-collector2,
         type=TestCollectorItem,id=89990085,
         version=2,
         result=noop,shards=ShardInfo{total=0, successful=0, failures=[]}]]
更新 # End !!!



         */
        System.out.println("更新 # msOnionResult=" + msOnionResult);

        System.out.println("更新 # End !!!");

    }


    @Test
    public void testUpdate02() throws MsOnionException {


        System.out.println("更新 testUpdate02 # Beginning ...");

//        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
//
//        apiVersion.setRequestApiVersion("V2.0.0");

//        long idx = 89990081;

        int i = 0;

        long idx = 89110090L + i;

        TestCollectorItem collectorItem = new TestCollectorItem();

        collectorItem.setIdx(idx);
        collectorItem.setBarcode("Elasticsearch update02#更新02");
        collectorItem.setBatch(1);
        collectorItem.setBrandIdx(81990001L + i);
        collectorItem.setCategoryIdx(82990001L + i);
        collectorItem.setCnName("update02,For more information on the index operation, check out the REST index docs");
        collectorItem.setCollectionRemark("update01,The index API allows one to set the threading model the operation will be performed when the actual execution of the API is performed on the same node (the API is executed on a shard that is allocated on the same server)");

        collectorItem.setCollectionStatus((short) 1);

        collectorItem.setCreateByMemberIdx(83990001L + i);
//        collectorItem.setCreateTime(new Date(System.currentTimeMillis() - 100000L));
        collectorItem.setEnName("Gearbox Software Elasticsearch");
        collectorItem.setExt("update02,The index API allows one to index a typed JSON document into a specific index and make it searchable.");

        collectorItem.setIdxCode(84990001L + i);

        collectorItem.setImageBigId(85990001L + i);
        collectorItem.setImageMiddleId(86990001L + i);
        collectorItem.setImageSmallId(87990001L + i);

        collectorItem.setItemExtIdx(88990001L + i);

        collectorItem.setItemNo("200022008" + i);
        collectorItem.setPlatformIdx(89090001L + i);

        collectorItem.setItemStateIdx(89190001L + i);

        collectorItem.setOriginIdx(89290001L + i);

        collectorItem.setPurchaseStatus((short) 1);

        collectorItem.setSpecification("update02,Note that you can also add arrays with startArray(String) and endArray() methods. By the way, the field method accepts many object types. ");


        collectorItem.setStatus((short) 1);

        collectorItem.setUpdateByMemberIdx(89390001L + i);

//        collectorItem.setUpdateTime(new Date(System.currentTimeMillis()));

//            if (i % 10 == 0)
        int value1 = (i % 10) + 1;
        int value2 = (i % 10) + 2;

        String month1 = MsOnionFixedLengthUtils.fixLength(value1, 2);
        String day1 = MsOnionFixedLengthUtils.fixLength(value2, 2);

        System.out.println(String.format("i=%s，value1=%s，value2=%s，month1=%s，day1=%s", i, value1, value2, month1, day1));

        int value3 = (i % 10) + 2;
        int value4 = (i % 10) + 10;

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

//            System.out.println(String.format("i=%，创建时间：%s，更新时间：%s", i, collectorItem.getCreateTime(), collectorItem.getUpdateTime()));

        collectorItem.setWarehouseTypeIdx(89490001L + i);

        collectorItem.setWeight(89590001L + i);

        collectorItem.setVersion(89690001L + i);

        System.out.println("collectorItem数据=" + collectorItem);

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

        String json = MsOnionJsonUtils.objectToJson(collectorItem);

        System.out.println("collectorItem # json=" + json);

        IndexResponse indexResponse = testCollectorItemSearchService.add(apiVersion, collectorItem.getIdx(), json);

        System.out.println("更新 ###测试索引 # indexResponse=" + indexResponse + "，i=" + i);


        msOnionLogger.info(getClass().getName(), "单元测试#testIndex#测试索引 # End !!! i=" + i);

        System.out.println("更新 ##单元测试#testIndex#测试索引 # End !!!");

//        String json2 = MsOnionJsonUtils.objectToJson(collectorItem);

        MsOnionResult msOnionResult = testCollectorItemSearchService.updateForResult(apiVersion, idx, json);


        /*
        更新 # msOnionResult=MsOnionResult
         [status=200, msg=OK, data=UpdateResponse[index=test-msyt-collector2,
         type=TestCollectorItem,id=89990085,
         version=2,
         result=noop,shards=ShardInfo{total=0, successful=0, failures=[]}]]
更新 # End !!!



         */
        System.out.println("更新 # msOnionResult=" + msOnionResult);

        System.out.println("更新 # End !!!");

    }

    @Test
    public void testSearch01() throws MsOnionException {

        System.out.println("搜索结果 # testSearch01 # beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

        MsOnionSearchCondition condition = new MsOnionSearchCondition();


        // 高亮
        List<HighlightBuilder.Field> highlightFields = new ArrayList<>();

        HighlightBuilder.Field field1 = new HighlightBuilder.Field("collectionRemark");

        HighlightBuilder.Field field2 = new HighlightBuilder.Field("specification");

        HighlightBuilder.Field field3 = new HighlightBuilder.Field("ext");

        highlightFields.add(field1);

        highlightFields.add(field2);

        highlightFields.add(field3);

        condition.setHighlightFields(highlightFields);

        condition.setHighlight(true);

        condition.setExplain(true);

        condition.setFrom(1);

        List<String> postTags = new ArrayList<>();

        postTags.add("<div style='font:20;color:red;'>");
        postTags.add("<span style='font:18;color:blue;'>");

        condition.setPostTags(postTags);

        List<String> preTags = new ArrayList<>();

        preTags.add("</span style='font:18;color:blue;'>");
        preTags.add("</div style='font:20;color:red;'>");

        condition.setPreTags(preTags);

        condition.setRequireFieldMatch(true);

        condition.setSize(10);

        // 可以使用其他
//        condition.setSearchType(SearchType.QUERY_THEN_FETCH);


        List<SortBuilder> sortBuilders = new ArrayList<>();


//        SortBuilders.fieldSort(name).order(order)
//        ScoreSortBuilder order = SortBuilders.scoreSort().order(SortOrder.ASC);


       // 注意，和顺序有关 ！！

        FieldSortBuilder fieldSortBuilder2 = SortBuilders.fieldSort("updateTime").order(SortOrder.DESC);

        FieldSortBuilder fieldSortBuilder3 = SortBuilders.fieldSort("weight").order(SortOrder.DESC);

        FieldSortBuilder fieldSortBuilder1 = SortBuilders.fieldSort("idx").order(SortOrder.ASC);

        sortBuilders.add(fieldSortBuilder1);

        sortBuilders.add(fieldSortBuilder2);

        sortBuilders.add(fieldSortBuilder3);

//        SortBuilder sortBuilder1 = new

        condition.setSortBuilders(sortBuilders);

        List<MsOnionQuerySchemeAdapter> querySchemes = new ArrayList<>();

        MsOnionTermLevelQueryScheme termLevelQueryScheme = new MsOnionTermLevelQueryScheme();

        termLevelQueryScheme.setName("collectionRemark");

        termLevelQueryScheme.setFrom(1);

        termLevelQueryScheme.setTo(2);

        // 中文
        termLevelQueryScheme.setValue("十");


        termLevelQueryScheme.setQueryPattern(MsOnionTermLevelQueryPattern.TERM_QUERY);

        // 2

        MsOnionTermLevelQueryScheme termLevelQueryScheme2 = new MsOnionTermLevelQueryScheme();

        termLevelQueryScheme2.setName("collectionRemark");

        termLevelQueryScheme2.setFrom(1);

        termLevelQueryScheme2.setTo(2);

        // 中文
        termLevelQueryScheme2.setValue("十");

        termLevelQueryScheme2.setQueryPattern(MsOnionTermLevelQueryPattern.FUZZY_QUERY);

        // 3

        MsOnionTermLevelQueryScheme termLevelQueryScheme3 = new MsOnionTermLevelQueryScheme();

        termLevelQueryScheme3.setName("collectionRemark");

        termLevelQueryScheme3.setFrom(1);

        termLevelQueryScheme3.setTo(2);

        // 中文
        termLevelQueryScheme3.setValue("十");

        termLevelQueryScheme3.setQueryPattern(MsOnionTermLevelQueryPattern.FUZZY_QUERY);

        // 添加到集合中

        querySchemes.add(termLevelQueryScheme);
        querySchemes.add(termLevelQueryScheme2);
        querySchemes.add(termLevelQueryScheme3);

        condition.setQuerySchemes(querySchemes);

        MsOnionESSearchPagingResult msOnionESSearchPagingResult = testCollectorItemSearchService.search(apiVersion, condition);


        System.out.println("搜索结果 # msOnionESSearchPagingResult=" + msOnionESSearchPagingResult);

    }


    @Test
    public void testSearch02() throws MsOnionException {

        System.out.println("搜索结果 # testSearch02 # beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

        MsOnionSearchCondition condition = new MsOnionSearchCondition();


        // 高亮
        List<HighlightBuilder.Field> highlightFields = new ArrayList<>();

        HighlightBuilder.Field field1 = new HighlightBuilder.Field("collectionRemark");

        HighlightBuilder.Field field2 = new HighlightBuilder.Field("specification");

        HighlightBuilder.Field field3 = new HighlightBuilder.Field("ext");

        highlightFields.add(field1);

        highlightFields.add(field2);

        highlightFields.add(field3);

        condition.setHighlightFields(highlightFields);

        condition.setHighlight(true);

//        condition.setExplain(true);

//        condition.setRequireFieldMatch(true);

        condition.setFrom(1);
        condition.setSize(10);

        List<String> postTags = new ArrayList<>();

        postTags.add("<div style='font:20;color:red;'>");
        postTags.add("<span style='font:18;color:blue;'>");

        condition.setPostTags(postTags);

        List<String> preTags = new ArrayList<>();

        preTags.add("</span style='font:18;color:blue;'>");
        preTags.add("</div style='font:20;color:red;'>");

        condition.setPreTags(preTags);


        // 可以使用其他
//        condition.setSearchType(SearchType.QUERY_THEN_FETCH);


        List<SortBuilder> sortBuilders = new ArrayList<>();


//        SortBuilders.fieldSort(name).order(order)
//        ScoreSortBuilder order = SortBuilders.scoreSort().order(SortOrder.ASC);


        // 注意，和顺序有关 ！！

        FieldSortBuilder fieldSortBuilder2 = SortBuilders.fieldSort("updateTime").order(SortOrder.DESC);

        FieldSortBuilder fieldSortBuilder3 = SortBuilders.fieldSort("weight").order(SortOrder.DESC);

        FieldSortBuilder fieldSortBuilder1 = SortBuilders.fieldSort("idx").order(SortOrder.ASC);

        sortBuilders.add(fieldSortBuilder1);

        sortBuilders.add(fieldSortBuilder2);

        sortBuilders.add(fieldSortBuilder3);

//        SortBuilder sortBuilder1 = new

        condition.setSortBuilders(sortBuilders);

        List<MsOnionQuerySchemeAdapter> querySchemes = new ArrayList<>();

        MsOnionTermLevelQueryScheme termLevelQueryScheme = new MsOnionTermLevelQueryScheme();

        termLevelQueryScheme.setName("idx");  // 这里是 idx


//        termLevelQueryScheme.setName("id");  // 这里是 id

//        termLevelQueryScheme.setFrom(1);

//        termLevelQueryScheme.setTo(2);

        // 不可以是 long ，必须是 String ，否则查询不到
        termLevelQueryScheme.setValue(89990001);  // idx  ， 有 totalHits=1


//        termLevelQueryScheme.setValue(String.valueOf(89990001L));  // 1 条记录


//        termLevelQueryScheme.setValue(String.valueOf(8999000188L));  // 错误的，没有搜索结果


        termLevelQueryScheme.setQueryPattern(MsOnionTermLevelQueryPattern.TERM_QUERY);

        // 2

        MsOnionTermLevelQueryScheme termLevelQueryScheme2 = new MsOnionTermLevelQueryScheme();

        termLevelQueryScheme2.setName("cnName");

//        termLevelQueryScheme2.setFrom(1);

//        termLevelQueryScheme2.setTo(2);

        // 中文
//        termLevelQueryScheme2.setValue("*index");
//        termLevelQueryScheme2.setValue("*in*");  // 模糊匹配

        termLevelQueryScheme2.setValue("*in*");  // 通配符搜索  doSearch#搜索#总记录totalHits=1

//        termLevelQueryScheme2.setValue("*inAAA*");  // 通配符搜索  doSearch#搜索#总记录totalHits=0

//        termLevelQueryScheme2.setQueryPattern(MsOnionTermLevelQueryPattern.FUZZY_QUERY);

        termLevelQueryScheme2.setQueryPattern(MsOnionTermLevelQueryPattern.WILDCARD_QUERY);

        // 3

        MsOnionTermLevelQueryScheme termLevelQueryScheme3 = new MsOnionTermLevelQueryScheme();

        termLevelQueryScheme3.setName("ext");

        // 只有范围才有效！！！
//        termLevelQueryScheme3.setFrom(1);

//        termLevelQueryScheme3.setTo(2);

        // 中文
//        termLevelQueryScheme3.setValue("index");

        termLevelQueryScheme3.setValue("in");  // doSearch#搜索#总记录totalHits=1

//        termLevelQueryScheme3.setValue("BBBin");  // doSearch#搜索#总记录totalHits=0

        termLevelQueryScheme3.setQueryPattern(MsOnionTermLevelQueryPattern.PREFIX_QUERY);

        // 4

        MsOnionFullTextQueryScheme fullTextQueryScheme1 = new MsOnionFullTextQueryScheme();

//        fullTextQueryScheme1.setTo();
//        fullTextQueryScheme1.setFrom();

        fullTextQueryScheme1.setName("specification");

        fullTextQueryScheme1.setValue("the");  // doSearch#搜索#总记录totalHits=1

        fullTextQueryScheme1.setQueryPattern(MsOnionFullTextQueryPattern.MATCH_QUERY);

        //  5
        MsOnionFullTextQueryScheme fullTextQueryScheme2 = new MsOnionFullTextQueryScheme();

//        fullTextQueryScheme2.setTo();
//        fullTextQueryScheme2.setFrom();

//        fullTextQueryScheme2.setName("specification,ext");
//
//        fullTextQueryScheme2.setValue("the");  // doSearch#搜索#总记录totalHits=1

        // TODO: 必须调换过来，也不行 ！！！

        fullTextQueryScheme2.setName("the");  // query 字符串 the

        // 字段
        fullTextQueryScheme2.setValue("specification,ext");  // doSearch#搜索#总记录totalHits=1

        fullTextQueryScheme2.setQueryPattern(MsOnionFullTextQueryPattern.MULTI_MATCH_QUERY);  // 有问题

        // 添加到集合中

//        querySchemes.add(termLevelQueryScheme);  // OK
        querySchemes.add(termLevelQueryScheme2);  // OK
//        querySchemes.add(termLevelQueryScheme3);  // OK

//        querySchemes.add(fullTextQueryScheme1);  // OK

//        querySchemes.add(fullTextQueryScheme2);  // 不可以 ！！

        condition.setQuerySchemes(querySchemes);

        MsOnionESSearchPagingResult msOnionESSearchPagingResult = testCollectorItemSearchService.search(apiVersion, condition);


        System.out.println("搜索结果 # msOnionESSearchPagingResult=" + msOnionESSearchPagingResult);

    }


    @Test
    public void testSearch03() throws MsOnionException {

        System.out.println("搜索结果 # testSearch03 # beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

        MsOnionSearchCondition condition = new MsOnionSearchCondition();


        // 高亮
        List<HighlightBuilder.Field> highlightFields = new ArrayList<>();

        HighlightBuilder.Field field1 = new HighlightBuilder.Field("collectionRemark");

        HighlightBuilder.Field field2 = new HighlightBuilder.Field("specification");

        HighlightBuilder.Field field3 = new HighlightBuilder.Field("ext");

        highlightFields.add(field1);

        highlightFields.add(field2);

        highlightFields.add(field3);

        condition.setHighlightFields(highlightFields);

        condition.setHighlight(true);

        condition.setExplain(true);

        condition.setRequireFieldMatch(true);

        condition.setFrom(0);  // 从0开始！！！
        condition.setSize(10);

        List<String> postTags = new ArrayList<>();

        postTags.add("<div style='font:20;color:red;'>");
        postTags.add("<span style='font:18;color:blue;'>");

        condition.setPostTags(postTags);

        List<String> preTags = new ArrayList<>();

        preTags.add("</span style='font:18;color:blue;'>");
        preTags.add("</div style='font:20;color:red;'>");

        condition.setPreTags(preTags);


        // 可以使用其他
//        condition.setSearchType(SearchType.QUERY_THEN_FETCH);


        List<SortBuilder> sortBuilders = new ArrayList<>();


//        SortBuilders.fieldSort(name).order(order)
//        ScoreSortBuilder order = SortBuilders.scoreSort().order(SortOrder.ASC);


        // 注意，和顺序有关 ！！

        FieldSortBuilder fieldSortBuilder2 = SortBuilders.fieldSort("updateTime").order(SortOrder.DESC);

        FieldSortBuilder fieldSortBuilder3 = SortBuilders.fieldSort("weight").order(SortOrder.DESC);

        FieldSortBuilder fieldSortBuilder1 = SortBuilders.fieldSort("idx").order(SortOrder.ASC);

        sortBuilders.add(fieldSortBuilder1);

        sortBuilders.add(fieldSortBuilder2);

        sortBuilders.add(fieldSortBuilder3);

//        SortBuilder sortBuilder1 = new

        condition.setSortBuilders(sortBuilders);

        List<MsOnionQuerySchemeAdapter> querySchemes = new ArrayList<>();

        MsOnionTermLevelQueryScheme termLevelQueryScheme = new MsOnionTermLevelQueryScheme();

        termLevelQueryScheme.setName("idx");  // 这里是 idx


//        termLevelQueryScheme.setName("id");  // 这里是 id

//        termLevelQueryScheme.setFrom(1);

//        termLevelQueryScheme.setTo(2);

        // 不可以是 long ，必须是 String ，否则查询不到
//        termLevelQueryScheme.setValue(89990090L);  // idx  ， 有 totalHits=1

        // 89110090L

        termLevelQueryScheme.setValue(89110090L);  // idx  ， 有 totalHits=1



//        termLevelQueryScheme.setValue(String.valueOf(89990001L));  // 1 条记录


//        termLevelQueryScheme.setValue(String.valueOf(8999000188L));  // 错误的，没有搜索结果


        termLevelQueryScheme.setQueryPattern(MsOnionTermLevelQueryPattern.TERM_QUERY);

        // 2

        MsOnionTermLevelQueryScheme termLevelQueryScheme2 = new MsOnionTermLevelQueryScheme();

        termLevelQueryScheme2.setName("cnName");

//        termLevelQueryScheme2.setFrom(1);

//        termLevelQueryScheme2.setTo(2);

        // 中文
//        termLevelQueryScheme2.setValue("*index");
//        termLevelQueryScheme2.setValue("*in*");  // 模糊匹配

//        termLevelQueryScheme2.setValue("*in*");  // 通配符搜索  doSearch#搜索#总记录totalHits=1

          String queryValue = "in";

          queryValue = MsOnionElasticsearchUtils.getWildcardQueryValueExpression(queryValue);

        termLevelQueryScheme2.setValue(queryValue);

//        termLevelQueryScheme2.setValue("*inAAA*");  // 通配符搜索  doSearch#搜索#总记录totalHits=0

//        termLevelQueryScheme2.setQueryPattern(MsOnionTermLevelQueryPattern.FUZZY_QUERY);

        termLevelQueryScheme2.setQueryPattern(MsOnionTermLevelQueryPattern.WILDCARD_QUERY);

        // 3

        MsOnionTermLevelQueryScheme termLevelQueryScheme3 = new MsOnionTermLevelQueryScheme();

        termLevelQueryScheme3.setName("ext");

        // 只有范围才有效！！！
//        termLevelQueryScheme3.setFrom(1);

//        termLevelQueryScheme3.setTo(2);

        // 中文
//        termLevelQueryScheme3.setValue("index");

        termLevelQueryScheme3.setValue("in");  // doSearch#搜索#总记录totalHits=1

//        termLevelQueryScheme3.setValue("BBBin");  // doSearch#搜索#总记录totalHits=0

        termLevelQueryScheme3.setQueryPattern(MsOnionTermLevelQueryPattern.PREFIX_QUERY);

        ////////  范围
        /*
        2017-02-08

                2017-05-18

                weight":89590020,"createByMemberIdx":83990020,"updateByMemberIdx":89390020,"createTime":1507655408000,"updateTime":1511087938000,"status":1,"
         */

        MsOnionTermLevelQueryScheme termLevelQueryScheme4 = new MsOnionTermLevelQueryScheme();
        termLevelQueryScheme4.setName("createTime");

        // TODO: 报错： Caused by: java.lang.NumberFormatException: For input string: "Wed Feb 08 01:02:18 CST 2017"
        // http://yt02-dev:18200/test-msyt-collector2/
        // TODO: 查看各个数据类型在 ES中的映射： http://yt02-dev:18200/test-msyt-collector2/
        // TODO: test-msyt-collector2 是 index 名称
        /*
         "updateTime": {
            "type": "long"
          },

          "createTime": {
            "type": "long"
          },

         */
        // TODO: 报错：不可以Date , 要转换成 long
//        termLevelQueryScheme4.setFrom(MsOnionDateUtils.parseWithYyyyMMddHHmmss("2017-02-08 01:02:18"));
//
//        termLevelQueryScheme4.setTo(MsOnionDateUtils.parseWithYyyyMMddHHmmss("2017-05-18 08:18:38"));

//        new Date().getTime()

        // TODO: OK
//        termLevelQueryScheme4.setFrom(MsOnionDateUtils.parseWithYyyyMMddHHmmss("2017-02-08 01:02:18").getTime());
//
//        termLevelQueryScheme4.setTo(MsOnionDateUtils.parseWithYyyyMMddHHmmss("2017-05-18 08:18:38").getTime());

        // OK ， 30 条记录
//        termLevelQueryScheme4.setFrom(MsOnionDateUtils.parseWithYyyyMMdd("2017-02-08").getTime());
//
//        termLevelQueryScheme4.setTo(MsOnionDateUtils.parseWithYyyyMMdd("2017-05-18").getTime());

        // OK , 10 条记录
        termLevelQueryScheme4.setFrom(MsOnionDateUtils.parseWithYyyyMMdd("2017-04-08").getTime());

        termLevelQueryScheme4.setTo(MsOnionDateUtils.parseWithYyyyMMdd("2017-05-18").getTime());

        // 范围查询
        termLevelQueryScheme4.setQueryPattern(MsOnionTermLevelQueryPattern.RANGE_QUERY);

        // 4

        MsOnionFullTextQueryScheme fullTextQueryScheme1 = new MsOnionFullTextQueryScheme();

//        fullTextQueryScheme1.setTo();
//        fullTextQueryScheme1.setFrom();

        fullTextQueryScheme1.setName("specification");

        fullTextQueryScheme1.setValue("the");  // doSearch#搜索#总记录totalHits=1

        fullTextQueryScheme1.setQueryPattern(MsOnionFullTextQueryPattern.MATCH_QUERY);

        //  5
        MsOnionFullTextQueryScheme fullTextQueryScheme2 = new MsOnionFullTextQueryScheme();

//        fullTextQueryScheme2.setTo();
//        fullTextQueryScheme2.setFrom();

//        fullTextQueryScheme2.setName("specification,ext");
//
//        fullTextQueryScheme2.setValue("the");  // doSearch#搜索#总记录totalHits=1

        // TODO: 必须调换过来，也不行 ！！！

        fullTextQueryScheme2.setName("the");  // query 字符串 the

        // 字段
        fullTextQueryScheme2.setValue("specification,ext");  // doSearch#搜索#总记录totalHits=1

        fullTextQueryScheme2.setQueryPattern(MsOnionFullTextQueryPattern.MULTI_MATCH_QUERY);  // 有问题

        // 添加到集合中

        querySchemes.add(termLevelQueryScheme);  // OK
//        querySchemes.add(termLevelQueryScheme2);  // OK
//        querySchemes.add(termLevelQueryScheme3);  // OK

        // 范围查询
//        querySchemes.add(termLevelQueryScheme4);

//        querySchemes.add(fullTextQueryScheme1);  // OK

//        querySchemes.add(fullTextQueryScheme2);  // 不可以 ！！

        condition.setQuerySchemes(querySchemes);

        MsOnionESSearchPagingResult msOnionESSearchPagingResult = testCollectorItemSearchService.search(apiVersion, condition);


        System.out.println("搜索结果3 # msOnionESSearchPagingResult=" + msOnionESSearchPagingResult);

    }

    @Test
    public void testSearch04() throws MsOnionException {

        System.out.println("搜索结果 # testSearch04 # beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

        MsOnionSearchCondition condition = new MsOnionSearchCondition();


        // 高亮
        List<HighlightBuilder.Field> highlightFields = new ArrayList<>();

        HighlightBuilder.Field field1 = new HighlightBuilder.Field("collectionRemark");

        HighlightBuilder.Field field2 = new HighlightBuilder.Field("specification");

        HighlightBuilder.Field field3 = new HighlightBuilder.Field("ext");

        highlightFields.add(field1);

        highlightFields.add(field2);

        highlightFields.add(field3);

        condition.setHighlightFields(highlightFields);

        condition.setHighlight(true);

        condition.setExplain(true);

        condition.setRequireFieldMatch(true);

        condition.setFrom(0);  // 从0开始！！！
        condition.setSize(10);

        List<String> postTags = new ArrayList<>();

        postTags.add("<div style='font:20;color:red;'>");
        postTags.add("<span style='font:18;color:blue;'>");

        condition.setPostTags(postTags);

        List<String> preTags = new ArrayList<>();

        preTags.add("</span style='font:18;color:blue;'>");
        preTags.add("</div style='font:20;color:red;'>");

        condition.setPreTags(preTags);


        // 可以使用其他
//        condition.setSearchType(SearchType.QUERY_THEN_FETCH);


        List<SortBuilder> sortBuilders = new ArrayList<>();


//        SortBuilders.fieldSort(name).order(order)
//        ScoreSortBuilder order = SortBuilders.scoreSort().order(SortOrder.ASC);


        // 注意，和顺序有关 ！！

        FieldSortBuilder fieldSortBuilder2 = SortBuilders.fieldSort("updateTime").order(SortOrder.DESC);

        FieldSortBuilder fieldSortBuilder3 = SortBuilders.fieldSort("weight").order(SortOrder.DESC);

        FieldSortBuilder fieldSortBuilder1 = SortBuilders.fieldSort("idx").order(SortOrder.ASC);

        sortBuilders.add(fieldSortBuilder1);

        sortBuilders.add(fieldSortBuilder2);

        sortBuilders.add(fieldSortBuilder3);

//        SortBuilder sortBuilder1 = new

        condition.setSortBuilders(sortBuilders);

        List<MsOnionQuerySchemeAdapter> querySchemes = new ArrayList<>();

        MsOnionTermLevelQueryScheme termLevelQueryScheme = new MsOnionTermLevelQueryScheme();

        termLevelQueryScheme.setName("idx");  // 这里是 idx


//        termLevelQueryScheme.setName("id");  // 这里是 id

//        termLevelQueryScheme.setFrom(1);

//        termLevelQueryScheme.setTo(2);

        // 不可以是 long ，必须是 String ，否则查询不到
//        termLevelQueryScheme.setValue(89990090L);  // idx  ， 有 totalHits=1

        // 89110090L

        // 99990001L
        termLevelQueryScheme.setValue(99990001L);  // idx  ， 有 totalHits=1



//        termLevelQueryScheme.setValue(String.valueOf(89990001L));  // 1 条记录


//        termLevelQueryScheme.setValue(String.valueOf(8999000188L));  // 错误的，没有搜索结果


        termLevelQueryScheme.setQueryPattern(MsOnionTermLevelQueryPattern.TERM_QUERY);

        // 2

        MsOnionTermLevelQueryScheme termLevelQueryScheme2 = new MsOnionTermLevelQueryScheme();

//        termLevelQueryScheme2.setName("cnName");

//        termLevelQueryScheme2.setName("ext");

        termLevelQueryScheme2.setName("specification");

//        termLevelQueryScheme2.setFrom(1);

//        termLevelQueryScheme2.setTo(2);

        // 中文
//        termLevelQueryScheme2.setValue("*index");
//        termLevelQueryScheme2.setValue("*in*");  // 模糊匹配

//        termLevelQueryScheme2.setValue("*in*");  // 通配符搜索  doSearch#搜索#总记录totalHits=1

//        String queryValue = "in";

        // ext

        //
//        String queryValue = "明星";

        String queryValue = "明星";

        queryValue = MsOnionElasticsearchUtils.getWildcardQueryValueExpression(queryValue);

        termLevelQueryScheme2.setValue(queryValue);

//        termLevelQueryScheme2.setValue("*inAAA*");  // 通配符搜索  doSearch#搜索#总记录totalHits=0

//        termLevelQueryScheme2.setQueryPattern(MsOnionTermLevelQueryPattern.FUZZY_QUERY);



        termLevelQueryScheme2.setQueryPattern(MsOnionTermLevelQueryPattern.WILDCARD_QUERY);

        // 3

        MsOnionTermLevelQueryScheme termLevelQueryScheme3 = new MsOnionTermLevelQueryScheme();

//        termLevelQueryScheme3.setName("ext");

        termLevelQueryScheme3.setName("specification");

        // 只有范围才有效！！！
//        termLevelQueryScheme3.setFrom(1);

//        termLevelQueryScheme3.setTo(2);

        // 中文
//        termLevelQueryScheme3.setValue("index");

        termLevelQueryScheme3.setValue("in");  // doSearch#搜索#总记录totalHits=1

//        termLevelQueryScheme3.setValue("BBBin");  // doSearch#搜索#总记录totalHits=0

        termLevelQueryScheme3.setQueryPattern(MsOnionTermLevelQueryPattern.PREFIX_QUERY);

        ////////  范围
        /*
        2017-02-08

                2017-05-18

                weight":89590020,"createByMemberIdx":83990020,"updateByMemberIdx":89390020,"createTime":1507655408000,"updateTime":1511087938000,"status":1,"
         */

        MsOnionTermLevelQueryScheme termLevelQueryScheme4 = new MsOnionTermLevelQueryScheme();
        termLevelQueryScheme4.setName("createTime");

        // TODO: 报错： Caused by: java.lang.NumberFormatException: For input string: "Wed Feb 08 01:02:18 CST 2017"
        // http://yt02-dev:18200/test-msyt-collector2/
        // TODO: 查看各个数据类型在 ES中的映射： http://yt02-dev:18200/test-msyt-collector2/
        // TODO: test-msyt-collector2 是 index 名称
        /*
         "updateTime": {
            "type": "long"
          },

          "createTime": {
            "type": "long"
          },

         */
        // TODO: 报错：不可以Date , 要转换成 long
//        termLevelQueryScheme4.setFrom(MsOnionDateUtils.parseWithYyyyMMddHHmmss("2017-02-08 01:02:18"));
//
//        termLevelQueryScheme4.setTo(MsOnionDateUtils.parseWithYyyyMMddHHmmss("2017-05-18 08:18:38"));

//        new Date().getTime()

        // TODO: OK
//        termLevelQueryScheme4.setFrom(MsOnionDateUtils.parseWithYyyyMMddHHmmss("2017-02-08 01:02:18").getTime());
//
//        termLevelQueryScheme4.setTo(MsOnionDateUtils.parseWithYyyyMMddHHmmss("2017-05-18 08:18:38").getTime());

        // OK ， 30 条记录
//        termLevelQueryScheme4.setFrom(MsOnionDateUtils.parseWithYyyyMMdd("2017-02-08").getTime());
//
//        termLevelQueryScheme4.setTo(MsOnionDateUtils.parseWithYyyyMMdd("2017-05-18").getTime());

        // OK , 10 条记录
        termLevelQueryScheme4.setFrom(MsOnionDateUtils.parseWithYyyyMMdd("2017-04-08").getTime());

        termLevelQueryScheme4.setTo(MsOnionDateUtils.parseWithYyyyMMdd("2017-05-18").getTime());

        // 范围查询
        termLevelQueryScheme4.setQueryPattern(MsOnionTermLevelQueryPattern.RANGE_QUERY);

        // 4

        MsOnionFullTextQueryScheme fullTextQueryScheme1 = new MsOnionFullTextQueryScheme();

//        fullTextQueryScheme1.setTo();
//        fullTextQueryScheme1.setFrom();

        fullTextQueryScheme1.setName("specification");

        fullTextQueryScheme1.setValue("the");  // doSearch#搜索#总记录totalHits=1

        fullTextQueryScheme1.setQueryPattern(MsOnionFullTextQueryPattern.MATCH_QUERY);

        //  5
        MsOnionFullTextQueryScheme fullTextQueryScheme2 = new MsOnionFullTextQueryScheme();

//        fullTextQueryScheme2.setTo();
//        fullTextQueryScheme2.setFrom();

//        fullTextQueryScheme2.setName("specification,ext");
//
//        fullTextQueryScheme2.setValue("the");  // doSearch#搜索#总记录totalHits=1

        // TODO: 必须调换过来，也不行 ！！！

        fullTextQueryScheme2.setName("the");  // query 字符串 the

        // 字段
        fullTextQueryScheme2.setValue("specification,ext");  // doSearch#搜索#总记录totalHits=1

        fullTextQueryScheme2.setQueryPattern(MsOnionFullTextQueryPattern.MULTI_MATCH_QUERY);  // 有问题

        // 添加到集合中

//        querySchemes.add(termLevelQueryScheme);  // OK
//        querySchemes.add(termLevelQueryScheme2);  // OK
//        querySchemes.add(termLevelQueryScheme3);  // OK

        // 范围查询
//        querySchemes.add(termLevelQueryScheme4);

//        querySchemes.add(fullTextQueryScheme1);  // OK

//        querySchemes.add(fullTextQueryScheme2);  // 不可以 ！！

        // termLevelQueryScheme2

        querySchemes.add(termLevelQueryScheme2);

        condition.setQuerySchemes(querySchemes);

        MsOnionESSearchPagingResult msOnionESSearchPagingResult = testCollectorItemSearchService.search(apiVersion, condition);


        System.out.println("搜索结果4 # msOnionESSearchPagingResult=" + msOnionESSearchPagingResult);

        /*
        搜索结果4 # msOnionESSearchPagingResult=MsOnionESSearchPagingResult [status=200,
        msg=成功, data=[{itemExtIdx=98990001, imageBigId=95990001, idxCode=94990001,
         createByMemberIdx=93990001, brandIdx=91990001, itemNo=100022001,
          imageMiddleIdDynamicS=null, platformIdx=99090001, enName=Gearbox Software,
           imageBigIdDynamicS=null, itemStateIdx=99190001, id=99990001, barcode=barcode0001,
           imageMiddleId=96990001, warehouseTypeIdx=99490001,
           ext=余国荔总是打扮的像摇滚乐明星一样特立独行，如果有人质疑女性在计算机程序设计方面的能力和贡献。,
            updateByMemberIdx=99390001, batch=1, originIdx=99290001, specification=曾以技术总监的身份在数家3D游戏公司任职，如3D Realms、ION Storm与Gearbox；, weight=99590001, updateTime=1497162751194, version=99690001, imageSmallIdDynamicS=null, cnName=中华人民共和国合同法01, createTime=1497162651194, collectionStatus=1, categoryIdx=92990001, imageSmallId=97990001, purchaseStatus=1, idx=99990001, collectionRemark=香港出生的著名程序员。被著名游戏媒体网站Kotaku评为十年来最具影响的十位游戏界女性之一，也是其中唯一的女程序员。, status=1}]]

         */

    }


    @Test
    public void testGet01() throws MsOnionException {


        /*
        单元测试#testIndex#测试索引 # indexResponse=IndexResponse[index=test-msyt-collector,type=TestCollectorItem,id=89990001,version=2,result=updated,shards={"_shards":{"total":2,"successful":2,"failed":0}}]
testIndex02#单元测试#testIndex#测试索引 # End !!!

id=89990001
         */


        System.out.println("testGet01 # Beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

//        long idx = 89990036L;

        long idx = 89110090L;

        GetResponse getResponse = testCollectorItemSearchService.get(apiVersion, idx);


        System.out.println("testGet01 # getResponse=" + getResponse);


        System.out.println("testGet01 # End !!!");

    }


    @Test
    public void testMapping01() {


        System.out.println("testMapping01 # Begin ...");


        MsOnionTransportClient transportClient = this.testCollectorItemSearchService.getMsOnionTransportClient();

//        transportClient.admin().indices().prepareAnalyze("");

//        transportClient.prepareIndex("", "", "").setSource("").

//        transportClient.admin().indices().prepareCreate("").addMapping()

//        transportClient.admin().indices().preparePutMapping("").setType("").setSource("").

//        transportClient.admin().indices().prepareCreate("").addMapping("", "")


        // ext 字段的
//        transportClient.admin().indices().prepareCreate("test-msyt-collector")
//                .addMapping("TestCollectorItem", "ext",
//                        "type=text,analyzer=ik_max_word,search_analyzer=ik_max_word,term_vector=no,store=true");


        // index :  test-msyt-collector , type : TestCollectorItem , fields : _all ，所有字段

        CreateIndexRequestBuilder createIndexRequestBuilder = transportClient.admin().indices().prepareCreate("test-msyt-collector")
                .addMapping("TestCollectorItem", "_all",
                        "type=text,analyzer=ik_max_word,search_analyzer=ik_max_word,term_vector=no,store=true");


        // testMapping01 # createIndexRequestBuilder=org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder@17aa8a11

        System.out.println("testMapping01 # createIndexRequestBuilder=" + createIndexRequestBuilder);

        System.out.println("testMapping01 # End !!!");


        // https://github.com/medcl/elasticsearch-analysis-ik

        /*


        curl -XPOST http://localhost:9200/index/fulltext/_mapping -d'
{
    "fulltext": {
             "_all": {
            "analyzer": "ik_max_word",
            "search_analyzer": "ik_max_word",
            "term_vector": "no",
            "store": "false"
        },
        "properties": {
            "content": {
                "type": "text",
                "analyzer": "ik_max_word",
                "search_analyzer": "ik_max_word",
                "include_in_all": "true",
                "boost": 8
            }
        }
    }
}'

         */

    }

}
