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
package test.msonion.carambola.thirdplatform.erp.service;


import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import cc.msonion.carambola.thirdplatform.erp.common.constants.ErpConstants;
import cc.msonion.carambola.thirdplatform.erp.common.constants.ErpPagingConstants;
import cc.msonion.carambola.thirdplatform.erp.pojo.request.StockQueryRequest;
import cc.msonion.carambola.thirdplatform.erp.pojo.response.Stock;
import cc.msonion.carambola.thirdplatform.erp.service.ErpStockService;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSON;
import net.sf.json.util.JSONUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Title: TestErpStockService
 * @Package: test.msonion.carambola.erp.service
 * @Description: ErpStockService服务测试类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月07日 14:01:02
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月07日 14:01:02
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

/**
 * @ClassName: TestErpStockService
 * @Description: ErpStockService服务测试类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月07日 14:01:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext-*.xml"})
public class TestErpStockService {


    @Autowired
    private ErpStockService erpStockService;

    /**
     * 测试统计
     */
    @Test
    public void testCount() throws MsOnionException {
        // 版本对象
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion("V2.0.0");
        MsOnionResult msOnionResult = erpStockService.getStockCount(msOnionApiVersion,"","","");
        System.out.println(JSONObject.toJSONString(msOnionResult));
        // 20297
    }

    @Test
    public void testPage() throws MsOnionException {
        // 版本对象
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion("V2.0.0");

        StockQueryRequest stockQueryRequest=new StockQueryRequest();
        stockQueryRequest.setStartTime(null);
        stockQueryRequest.setEndTime(null);
        stockQueryRequest.setPageNo(0L);
        stockQueryRequest.setSpecNo("3034008320");
        stockQueryRequest.setPageSize(10L);
        MsOnionResult msOnionResult = erpStockService.queryStock(msOnionApiVersion, stockQueryRequest);
        List<Stock> stockList = (List<Stock>) msOnionResult.getData();
        if(MsOnionCollectionUtils.isNotEmpty(stockList)){
            double subStock=0d;
            for(Stock stock:stockList){
                subStock+= MsOnionNumberUtils.toDouble(stock.getStockNum());
            }
            System.out.println("subStock====="+subStock);
        }
        System.out.println(JSONObject.toJSONString(msOnionResult));
    }
    @Test
    public void testPageQuery() throws MsOnionException, InterruptedException {

        String startTime = "2017-07-14 15:20:00";
        String endTime = "2017-07-15 15:29:00";
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion("V2.0.0");
        for(int i=0 ;i<=60;i++){

        Thread.sleep(800);
        MsOnionResult totalCountResult = erpStockService.getStockCount(msOnionApiVersion,startTime,endTime,"");
        if(totalCountResult.getStatus()==200){
            long totalCount = (long) totalCountResult.getData();
            System.out.println("totalCount=="+totalCount);
            if(totalCount<=0){
                return;
            }

            // 获取总页数
            long pageSize = ErpPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE;
            long totalPage = 0;
            if (totalCount % pageSize == 0) {
                totalPage = totalCount / pageSize;
            } else {
                totalPage = (totalCount / pageSize) + 1;
            }
            System.out.println("totalPage=="+totalPage);

            StockQueryRequest stockQueryRequest=new StockQueryRequest();
            stockQueryRequest.setStartTime(startTime);
            stockQueryRequest.setEndTime(endTime);
//        stockQueryRequest.setWarehouseNo("api_test");
            stockQueryRequest.setPageNo(totalPage-1);
            stockQueryRequest.setPageSize(ErpPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE);
            MsOnionResult msOnionResult = erpStockService.queryStock(msOnionApiVersion, stockQueryRequest);
            if(msOnionResult.getStatus()==200){
                List<Stock> stockList = (List<Stock>) msOnionResult.getData();
                System.out.println("stockListSize=="+JSONObject.toJSONString(stockList));
            }else{
                System.out.println("======"+msOnionResult.getMsg());
            }
            System.out.println(totalCountResult.getStatus()+"==info=="+totalCountResult.getMsg()+"====="+totalCountResult.getData());
        }else{
            System.out.println(totalCountResult.getStatus()+"==error=="+totalCountResult.getMsg());

        }
    }

    }


    @Test
    public void testSyncTime() throws MsOnionException {
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion("V2.0.0");

        System.out.println("time==="+erpStockService.getStockSyncBeginTime(msOnionApiVersion));

    }

    @Test
    public void testEn() throws MsOnionException {

        System.out.println("time==="+ MsOnionSecurityUtils.encodeForRedis("2017-07-14 15:20:00"));

    }
}
