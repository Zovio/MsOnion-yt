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

import cc.msonion.carambola.collector.ext.view.ExcelItemView;
import cc.msonion.carambola.parent.ext.excel.MsOnionExcelDataService;
import cc.msonion.carambola.parent.ext.excel.MsOnionExcelHandleAdapter;
import cc.msonion.carambola.parent.ext.excel.reader.MsOnionExcelHSSFReader;
import cc.msonion.carambola.parent.ext.excel.reader.MsOnionExcelXSSFReader;
import cc.msonion.carambola.parent.ext.excel.view.MsOnionReaderOperate;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * @Title: TestExcelReader
 * @Package: test.msonion.carambola.manager.web.controller
 * @Description: Excel导入测试工具类------事件驱动模式
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月25日 17:54:49
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月25日 17:54:49
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

/**
 * @ClassName: TestExcelReader
 * @Description: Excel导入测试工具类------事件驱动模式
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月25日 17:54:49
 */
public class TestExcelReader {


    /**
     * 读取03版Excel
     * @throws FileNotFoundException
     */
    @Test
    public void read03() throws Exception{
//        String filePath="E:\\新增商品模版2.xls";
//        String bFilePath="E:\\新增商品模版2_b.xls";
//        File excelFile = new File(filePath);
//
//        InputStream inputStream = new FileInputStream(excelFile);
//
//        long beginTime = System.currentTimeMillis();
//        MsOnionExcelHSSFReader<ExcelItemView> reader = new MsOnionExcelHSSFReader<ExcelItemView>(ExcelItemView.class,
//                inputStream, 1, 2);
//        // 经过接口统一处理
//        MsOnionExcelHandleAdapter<ExcelItemView> adapter=
//                new MsOnionExcelHandleAdapter<ExcelItemView>(reader, new MsOnionExcelDataService<ExcelItemView>() {
//                    @Override
//                    public List<ExcelItemView> writeToDataBases(List<ExcelItemView> dataObjectList) {
//                        return null;
//                    }
//                });
//        adapter.parseAndInsert(ExcelItemView.class,bFilePath);
//        long endTime = System.currentTimeMillis();
//        System.out.print("#### 解析时间 #####"+((endTime-beginTime)/1000.0d));

    }

    /**
     * 读取07版Excel
     * @throws FileNotFoundException
     */
    @Test
    public void read07() throws Exception{
//        String filePath="E:\\excel\\新增商品模版.xlsx";
//        String bFilePath="E:\\excel\\新增商品模版_b.xlsx";
//        File excelFile = new File(filePath);
//
//        InputStream inputStream = new FileInputStream(excelFile);
//
//        long beginTime = System.currentTimeMillis();
//        MsOnionExcelXSSFReader<ExcelItemView> reader = new MsOnionExcelXSSFReader<ExcelItemView>(ExcelItemView.class,
//                inputStream, 1, 2);
//        // 经过接口统一处理
//        MsOnionExcelHandleAdapter<ExcelItemView> adapter=
//                new MsOnionExcelHandleAdapter<ExcelItemView>(reader, new MsOnionExcelDataService<ExcelItemView>() {
//                    @Override
//                    public List<ExcelItemView> writeToDataBases(List<ExcelItemView> dataObjectList) {
//                        return null;
//                    }
//                });
//        adapter.parseAndInsert(ExcelItemView.class,bFilePath);
//        long endTime = System.currentTimeMillis();
//        System.out.print("#### 解析时间 #####"+((endTime-beginTime)/1000.0d));


    }






    @Test
    public void testReflect()throws Exception{
        ExcelItemView newItemView=new ExcelItemView();
        MsOnionReaderOperate.setError(newItemView,"aaaaaa");
    }

    /**
     * 打印List
     * @param list
     */
    private void printList(List<String[]> list){
        for(int i = 0, size = list.size(); i < size; i++){
            System.out.println("row" + i);
            String[] str = list.get(i);
            for(int j = 0, length = str.length; j < length; j++){
                System.out.print(str[j] +", ");
            }
            System.out.println("");
        }
    }


}
