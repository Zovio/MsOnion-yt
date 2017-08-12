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
 * @Title: TestExcelWrite
 * @Package: test.msonion.carambola.manager.web.controller
 * @Description: SXSSF导出测试类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月27日 11:31:06
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月27日 11:31:06
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.collector.ext.view.ExcelItemView;
import cc.msonion.carambola.parent.ext.excel.reader.MsOnionExcelXSSFReader;
import cc.msonion.carambola.parent.ext.excel.simple.MsOnionExcelWriteUtils;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ClassName: TestExcelWrite
 * @Description: SXSSF导出测试类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月27日 11:31:06
 */
public class TestExcelWrite {


    @Test
    public void testWriteXlsx() throws Exception {

        long beginTime = System.currentTimeMillis();
        InputStream in = new FileInputStream("E:\\新增商品模版.xlsx");
        POIFSFileSystem poifs = new POIFSFileSystem(in);
        SXSSFWorkbook wb1 = new SXSSFWorkbook(1000);
        Sheet sheet = wb1.getSheetAt(0);
        for (int rownum = 0; rownum < 55000; rownum++) {
            Row row1 = sheet.getRow(rownum);
            Cell cell1 = row1.createCell(18);
            String address1 = new CellReference(cell1).formatAsString();
            cell1.setCellValue(address1);
        }
        FileOutputStream out1 = new FileOutputStream("E:\\新增商品模版33.xlsx");
        poifs.writeFilesystem(out1);
        wb1.write(out1);
        out1.close();
        wb1.dispose();
        long endTime = System.currentTimeMillis();
        System.out.print("#### 解析时间 #####" + ((endTime - beginTime) / 1000.0d));

    }


    @Test
    public void testUpdateXlsx() throws Exception {
        long beginTime = System.currentTimeMillis();
        InputStream in = new FileInputStream("E:\\新增商品模版33.xlsx");
        XSSFWorkbook xs = new XSSFWorkbook(in);
        SXSSFWorkbook wb = new SXSSFWorkbook(xs, 10000);
        Sheet sheet = xs.getSheetAt(0);
        for (int rownum = 1; rownum < 1000; rownum++) {
            Row row1 = sheet.getRow(rownum);
            Cell cell1 = row1.createCell(19);
            cell1.setCellValue("22222");
        }
        FileOutputStream out1 = new FileOutputStream("E:\\新增商品模版33.xlsx");
        wb.write(out1);
        out1.close();
        wb.close();
        long endTime = System.currentTimeMillis();
        System.out.print("#### 解析时间 #####" + ((endTime - beginTime) / 1000.0d));
    }


    @Test
    public void testWriteResult() throws Exception {
//        long beginTime = System.currentTimeMillis();
//        String fromPath = "E:\\新增商品模版.xlsx";
//        String bkPath = "E:\\新增商品模版_bk.xlsx";
//
//        File excelFile = new File(fromPath);
//        InputStream inputStream = new FileInputStream(excelFile);

        // 经过接口统一处理
//        BlockingQueue<ExcelItemView> dataList = new LinkedBlockingDeque<ExcelItemView>();
//        MsOnionExcelXSSFReader<ExcelItemView> reader = new MsOnionExcelXSSFReader<ExcelItemView>(ExcelItemView.class,
//                inputStream, 1, 2);
//        ExcelHandleAdapter<NewItemView> adapter=
//                new ExcelHandleAdapter<NewItemView>(reader, new ExcelDataService<NewItemView>() {
//                    @Override
//                    public NewItemView writeToDataBases(NewItemView dataObject) {
//                        System.out.println(dataObject.toString());
//                        return  dataObject;
//                    }
//                });
//        adapter.parseAndInsert(bkPath);
//
//        List<String> titleList=reader.getTitleList();
//            reader.parseSheet(0);
//            List<ExcelItemView> blockingQueue = reader.getRowObjectList();
//            System.out.print("======" + blockingQueue.size());
//            MsOnionExcelWriteUtils.saveXlsx(bkPath, reader.getTitleLine(), titleList, blockingQueue);
//        long endTime = System.currentTimeMillis();
//        System.out.print("#### 解析时间 #####" + ((endTime - beginTime) / 1000.0d));

    }
}
