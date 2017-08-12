package test.msonion.carambola.member.service.batch.service.demo;

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.msonion.carambola.member.service.batch.service.TestMemberBatchService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeroCao on 2017/6/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:*/spring/applicationContext-*.xml"})
//@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class TestMemberServiceDemo {

    @Autowired
    private MsOnionLogger msOnionLog;

    @Autowired
    private TestMemberBatchService testMemberBatchService;

    @Test
    public void test01() throws MsOnionException {

        System.out.println("test01 # 批量操作 ## Beginning ..." );

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

//        Member member = new Member();


        Member member = new Member();

        member.setIdx(198000L);
        member.setIdxCode(198000L);
        member.setCode("13111228899");
        member.setEmail("test101@gmail.com");
        member.setFullName("全名测试011");
        member.setLastLoginIp("127.0.1.28");
        member.setName("测试011");
        member.setPassword("11345688");
        member.setPhone("13911228899");
        member.setRemark("测试备注011");
        member.setStatus(MsOnionTableRecordStatus.DISABLE.getValue());
        member.setTel("020-81123488");


        List<Member> list = new ArrayList<>();

        list.add(member);


        //MsOnionBatchReport batchReport = testMemberBatchService.saveWithBatch(apiVersion, list);
        MsOnionBatchReport batchReport = testMemberBatchService.saveWithBatch(apiVersion, new HashedMap());

        System.out.println("test01 # 批量操作 ## batchReport=" + batchReport);

        System.out.println("test01 # 批量操作 ## End !!!" );

        /*
        test01 # 批量操作 ## batchReport=MsOnionBatchReport{isAllSuccess=true, totalCount=1, successRecords=[Member [Hash = 1788432625, idx=198000, idxCode=198000, name=测试011, fullName=全名测试011, code=13111228899, password=11345688, phone=13911228899, tel=020-81123488, sex=0, email=test101@gmail.com, createByMemberIdx=0, updateByMemberIdx=0, createTime=Sat Jun 17 21:04:16 CST 2017, updateTime=Sat Jun 17 21:04:16 CST 2017, status=2, version=0, remark=测试备注011, storeIdx=0, departmentIdx=0, vipMemberIdx=0, memberCateIdx=0, lastLoginIp=127.0.1.28, lastLoginTimeEnYyyyMMddHHmmss=, lastLoginTime=Sat Jun 17 21:04:16 CST 2017, ext=, serialVersionUID=1, id=]], failureRecords=[], exception=null, sourceRecords=[Member [Hash = 1788432625, idx=198000, idxCode=198000, name=测试011, fullName=全名测试011, code=13111228899, password=11345688, phone=13911228899, tel=020-81123488, sex=0, email=test101@gmail.com, createByMemberIdx=0, updateByMemberIdx=0, createTime=Sat Jun 17 21:04:16 CST 2017, updateTime=Sat Jun 17 21:04:16 CST 2017, status=2, version=0, remark=测试备注011, storeIdx=0, departmentIdx=0, vipMemberIdx=0, memberCateIdx=0, lastLoginIp=127.0.1.28, lastLoginTimeEnYyyyMMddHHmmss=, lastLoginTime=Sat Jun 17 21:04:16 CST 2017, ext=, serialVersionUID=1, id=]], currentFailureRecord=null}
test01 # 批量操作 ## End !!!

         */

    }


    @Test
    public void test02() throws MsOnionException {

        System.out.println("test01 # 批量操作 ## Beginning ...");

        long beginTime = System.currentTimeMillis();

        System.out.println("beginTime=" + beginTime);

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

        List<Member> list = new ArrayList<>();

        // 1万条记录

//        for (int i = 128001; i < 138000; i++) {

        // 100 条记录
//        for (int i = 138001; i < 138100; i++) {

//        for (int i = 138101; i < 138200; i++) {

//        for (int i = 138201; i < 138300; i++) {

//        for (int i = 138301; i < 138400; i++) {

//        for (int i = 138401; i < 138500; i++) {

        // 前面已经添加！！！

//        for (int i = 138501; i < 138600; i++) {

//        for (int i = 138601; i < 138700; i++) {

        // 前面已经添加！！！

        for (int i = 138701; i < 138800; i++) {

            Member member = new Member();

            member.setIdx(i + 1L);
            member.setIdxCode(i + 1L);
            member.setCode("13111" + i);
            member.setEmail(String.format("b%s@b.com", i));

            member.setFullName("全名批量测试" + i);
//            member.setLastLoginIp("127.0.1.28");
            member.setName("批量测试" + i);
            String password = MsOnionSecurityUtils.md5ForPassword(i + "88");
            member.setPassword(password);
            member.setPhone("13911" + i);
            member.setRemark("测试批量备注：" + i);
            member.setStatus(MsOnionTableRecordStatus.DISABLE.getValue());
            member.setTel("020-81" + i);

            if (i % 2 == 0) {
                member.setSex((short) 1);
            } else {
                member.setSex((short) 2);
            }

            list.add(member);

            System.out.println("批量 # i=" + i);

        }

        System.out.println("批量 # list.size()=" + list.size());


        // OK
        MsOnionBatchReport batchReport = testMemberBatchService.saveWithBatch(apiVersion, new HashedMap());

       // System.out.println("test02 # 批量操作 ## batchReport.getCurrentFailureRecord=" + batchReport.getCurrentFailureRecord());

        System.out.println("test02 # 批量操作 ## batchReport.getFailureRecords().size()=" + batchReport.getFailureRecords().size());


        System.out.println("test02 # 批量操作 ## batchReport.getSuccessRecords().size()=" + batchReport.getSuccessRecords().size());

        //System.out.println("test02 # 批量操作 ## batchReport.getException()=" + batchReport.getException());


        System.out.println("test02 # 批量操作 ## batchReport.getSourceRecords().size()=" + batchReport.getSourceRecords().size());


        System.out.println("test02 # 批量操作 ##======================= ");

        System.out.println("test02 # 批量操作 ## batchReport=" + batchReport);



        // OK
//        MsOnionResultAdapter msOnionResultAdapter = testMemberBatchService.saveWithBatchForResult(apiVersion, list);
//
//
//        System.out.println("test02 # 批量操作 ## batchReport=" + msOnionResultAdapter);




        long endTime = System.currentTimeMillis();

        System.out.println("endTime=" + endTime);

        System.out.println("总消耗时间， 秒=" + (endTime - beginTime) / 1000.0f);

        System.out.println("总消耗时间， 分钟=" + (endTime - beginTime) / 1000.0f / 60.0f);


        /*

        总消耗时间， 秒=176.173  , 1万条记录
test02 # 批量操作 ## End !!!


test02 # 批量操作 ## batchReport.getCurrentFailureRecord=Member [Hash = 558913557, idx=138351, idxCode=138351, name=批量测试138350, fullName=全名批量测试138350, code=13111138350, password=13835088, phone=13911138350, tel=020-81138350, sex=null, email=b138350@b.com, createByMemberIdx=null, updateByMemberIdx=null, createTime=null, updateTime=null, status=2, version=null, remark=测试批量备注：138350, storeIdx=null, departmentIdx=null, vipMemberIdx=null, memberCateIdx=null, lastLoginIp=null, lastLoginTimeEnYyyyMMddHHmmss=null, lastLoginTime=null, ext=null, serialVersionUID=1, id=null]
test02 # 批量操作 ## batchReport.getFailureRecords().size()=50
test02 # 批量操作 ## batchReport.getSuccessRecords().size()=49
test02 # 批量操作 ## batchReport.getException()=java.lang.ArithmeticException: / by zero
test02 # 批量操作 ## batchReport.getSourceRecords().size()=99
test02 # 批量操作 ##=======================
test02 # 批量操作 ## batchReport=MsOnionBatchReport{isAllSuccess=false, totalCount=99, successRecords=[Member [Hash = 2060799061, idx=138302, idxCode=138302, name=批量测试138301, fullName=全名批量测试138301,



         */
        System.out.println("test02 # 批量操作 ## End !!!" );


    }


    @Test
    public void test03() throws MsOnionException {

        System.out.println("test03 # 批量操作 ## Beginning ...");

        long beginTime = System.currentTimeMillis();

        System.out.println("beginTime=" + beginTime);

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRequestApiVersion("V2.0.0");

        List<Member> list = new ArrayList<>();

        // 1万条记录

//        for (int i = 128001; i < 138000; i++) {

        // 100 条记录
//        for (int i = 138001; i < 138100; i++) {

//        for (int i = 138101; i < 138200; i++) {

//        for (int i = 138201; i < 138300; i++) {

//        for (int i = 138301; i < 138400; i++) {

//        for (int i = 138401; i < 138500; i++) {

            // 前面已经添加！！！

            // 测试 100 万

//        for (int i = 2000000; i < 3000000; i++) {

        // 10 万
//        for (int i = 2200000; i < 2300000; i++) {

        for (int i = 2300000; i < 2400000; i++) {

            Member member = new Member();

            member.setIdx(i + 1L);
            member.setIdxCode(i + 1L);
            member.setCode("188" + i);
            member.setEmail(String.format("b%s@b.com", i));

            member.setFullName("全名批量测试" + i);
//            member.setLastLoginIp("127.0.1.28");
            member.setName("批量测试" + i);
            String password = MsOnionSecurityUtils.md5ForPassword(i + "88");
            member.setPassword(password);
            member.setPhone("1392" + i);
            member.setRemark("测试批量备注：" + i);
//            member.setStatus(MsOnionTableRecordStatus.DISABLE.getValue());

//            member.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
            member.setTel("020-8" + i);

            if (i % 2 == 0) {
                member.setSex((short) 1);
                member.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
            } else {
                member.setSex((short) 2);
                member.setStatus(MsOnionTableRecordStatus.DISABLE.getValue());
            }

            list.add(member);

            System.out.println("批量 # i=" + i);

        }

        System.out.println("批量 # list.size()=" + list.size());


        // OK
//        MsOnionBatchReport batchReport = testMemberBatchService.saveWithBatch(apiVersion, list);
//
//        System.out.println("test02 # 批量操作 ## batchReport.getCurrentFailureRecord=" + batchReport.getCurrentFailureRecord());
//
//        System.out.println("test02 # 批量操作 ## batchReport.getFailureRecords().size()=" + batchReport.getFailureRecords().size());
//
//
//        System.out.println("test02 # 批量操作 ## batchReport.getSuccessRecords().size()=" + batchReport.getSuccessRecords().size());
//
//        System.out.println("test02 # 批量操作 ## batchReport.getException()=" + batchReport.getException());
//
//
//        System.out.println("test02 # 批量操作 ## batchReport.getSourceRecords().size()=" + batchReport.getSourceRecords().size());
//
//
//        System.out.println("test02 # 批量操作 ##======================= ");
//
//        System.out.println("test02 # 批量操作 ## batchReport=" + batchReport);


        MsOnionResultAdapter msOnionResultAdapter = testMemberBatchService.saveWithBatchForResult(apiVersion, new HashedMap());


        System.out.println("test03 # 批量操作 ## batchReport=" + msOnionResultAdapter);




        long endTime = System.currentTimeMillis();

        System.out.println("endTime=" + endTime);

        System.out.println("总消耗时间， 秒=" + (endTime - beginTime) / 1000.0f);

        System.out.println("总消耗时间， 分钟=" + (endTime - beginTime) / 1000.0f / 60.0f);


        /*

        总消耗时间， 秒=176.173  , 1万条记录
test02 # 批量操作 ## End !!!


test02 # 批量操作 ## batchReport.getCurrentFailureRecord=Member [Hash = 558913557, idx=138351, idxCode=138351, name=批量测试138350, fullName=全名批量测试138350, code=13111138350, password=13835088, phone=13911138350, tel=020-81138350, sex=null, email=b138350@b.com, createByMemberIdx=null, updateByMemberIdx=null, createTime=null, updateTime=null, status=2, version=null, remark=测试批量备注：138350, storeIdx=null, departmentIdx=null, vipMemberIdx=null, memberCateIdx=null, lastLoginIp=null, lastLoginTimeEnYyyyMMddHHmmss=null, lastLoginTime=null, ext=null, serialVersionUID=1, id=null]
test02 # 批量操作 ## batchReport.getFailureRecords().size()=50
test02 # 批量操作 ## batchReport.getSuccessRecords().size()=49
test02 # 批量操作 ## batchReport.getException()=java.lang.ArithmeticException: / by zero
test02 # 批量操作 ## batchReport.getSourceRecords().size()=99
test02 # 批量操作 ##=======================
test02 # 批量操作 ## batchReport=MsOnionBatchReport{isAllSuccess=false, totalCount=99, successRecords=[Member [Hash = 2060799061, idx=138302, idxCode=138302, name=批量测试138301, fullName=全名批量测试138301,


test.msonion.carambola.member.service.batch.service.impl.TestMemberBatchServiceImpl, saveWithBatchForOne , 批量新增，检查和SQL注入过滤之后，record=Member [Hash = 2093259574, idx=2133101, idxCode=2133101, name=批量测试2133100, fullName=全名批量测试2133100, code=1882133100, password=213310088, phone=13922133100, tel=020-82133100, sex=0, email=b2133100@b.com, createByMemberIdx=0, updateByMemberIdx=0, createTime=Sat Jun 17 22:43:53 CST 2017, updateTime=Sat Jun 17 22:43:53 CST 2017, status=2, version=0, remark=测试批量备注：2133100, storeIdx=0, departmentIdx=0, vipMemberIdx=0, memberCateIdx=0, lastLoginIp=, lastLoginTimeEnYyyyMMddHHmmss=, lastLoginTime=Sat Jun 17 22:43:53 CST 2017, ext=, serialVersionUID=1, id=]
2017-06-17 22:43:53,100 [New I/O worker #9] [com.alibaba.dubbo.remoting.transport.AbstractServer]-[WARN]  [DUBBO] All clients has discontected from /172.16.10.129:20813. You can graceful shutdown now., dubbo version: 2.8.4, current host: 127.0.0.1





         */
        System.out.println("test03 # 批量操作 ## End !!!" );


    }


}
