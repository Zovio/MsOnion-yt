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
package cc.msonion.carambola.parent.ext.excel.simple;
/**
 * @Title: ExcelWriteUtils
 * @Package: cc.msonion.carambola.manager.common.utils
 * @Description: Excel 写入工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月24日 10:26:52
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月24日 10:26:52
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.parent.common.constants.MsOnionExcelConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionTypeConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionIOUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.ext.excel.view.MsOnionReaderOperate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName: ExcelWriteUtils
 * @Description: Excel 写入工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月24日 10:26:52
 */
public final class MsOnionExcelWriteUtils {


    private MsOnionExcelWriteUtils() {

    }

    /**
     * 默认缓存中缓存的行的大小
     */
    public static final int WRITE_DEFAULT_MEMERY_LINE = 5000;


    /**
     * SXSSFWorkbook写入到Excel
     * <不能多次写，慧慈使用SXSSFWorkbook接口操作写会覆盖之前的数据></>
     *
     * @param toPath     写到路劲
     * @param titleLine  标题所在的行
     * @param titleList  标题集合
     * @param objectList 对象集合
     * @param <T>        泛型
     * @throws MsOnionException MsOnionException
     */
    public static <T> void saveXlsx(String toPath, Integer titleLine, List<String> titleList, List<T> objectList) throws MsOnionException {
        SXSSFWorkbook wb = null;
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(new File(toPath));
            wb = new SXSSFWorkbook(WRITE_DEFAULT_MEMERY_LINE);
            if (CollectionUtils.isEmpty(objectList)) {
                return;
            }
            if (CollectionUtils.isNotEmpty(titleList)) {
                titleList.add(MsOnionExcelConstants.MESSAGE_EXCEL_RESULT_STRING);
            }
            // 写数据行
            for (int i = 0; i < objectList.size(); i++) {
                T t = objectList.get(i);
                int sheetNum = (int) MsOnionReaderOperate.getFieldValue(t, MsOnionReaderOperate.CURSHEETNUM_FIELD);
                int sheetNumbers = wb.getNumberOfSheets();
                Sheet sheet = null;
                if (sheetNum > sheetNumbers) {
                    sheet = wb.createSheet("Sheet" + sheetNum);
                    writeTitle(sheet, titleList, titleLine);
                } else {
                    sheet = wb.getSheetAt(sheetNum - 1);
                }
                int rownum = (int) MsOnionReaderOperate.getFieldValue(t, MsOnionReaderOperate.CURLINE_FIELD);
                Row row = sheet.createRow(rownum);
                Field[] fields = t.getClass().getDeclaredFields();
                int j;
                for (j = 0; j < fields.length; j++) {
                    Cell cell = row.createCell(j);
                    fields[j].setAccessible(true);
                    Object object = fields[j].get(t);
                    setCellValue(t, fields[j], cell);
                }
                // 数据保存结果
                Cell cell1 = row.createCell(j);
                Object object = MsOnionReaderOperate.getFieldValue(t, MsOnionReaderOperate.ERROR_FIELD);
                cell1.setCellValue(MsOnionStringUtils.toStringDefault(object, ""));
            }
            wb.write(output);
        } catch (Exception e) {
            throw new MsOnionException(e);
        } finally {
            MsOnionIOUtils.closeQuietly(output);
            MsOnionIOUtils.closeQuietly(wb);
        }
    }

    /**
     * 写标题
     *
     * @param sheet     页
     * @param titleList 标题字符串集合
     * @param titleLine 标题所在的行
     */
    private static void writeTitle(Sheet sheet, List<String> titleList, int titleLine) {
        // 写标题
        if (CollectionUtils.isNotEmpty(titleList)) {
            titleLine = ((titleLine - 1) < 0) ? 0 : (titleLine - 1);
            Row titleRow = sheet.createRow(titleLine);
            for (int i = 0; i < titleList.size(); i++) {
                String str = titleList.get(i);
                Cell cell = titleRow.createCell(i);
                cell.setCellValue(MsOnionStringUtils.toStringDefault(str, ""));
            }
        }
    }


    /**
     * 设置单元格的值
     *
     * @param t     对象
     * @param field 字段
     * @param cell  单元格
     * @param <T>   对象
     * @throws Exception Exception
     */
    private static <T> void setCellValue(T t, Field field, Cell cell) throws Exception {
        String type = field.getGenericType().toString();
        // 返回的是一个类对象
        PropertyDescriptor pd = new PropertyDescriptor(field.getName(), t.getClass());
        // 获取某个属性的set方法
        Method setmd = pd.getWriteMethod();
        field.setAccessible(true);
        Object value = field.get(t);
        if (value == null) {
            CellStyle style = cell.getCellStyle();
            cell.setCellValue(new XSSFRichTextString(MsOnionExcelConstants.EXCEL_SPECIAL_STRING));
            return;
        }
        String str = MsOnionStringUtils.toStringDefault(value, "");
        CellStyle style = cell.getCellStyle();
        if (type.contains(MsOnionTypeConstants.TYPE_REFERENCE_INTEGER_NAME)
                || type.contains(MsOnionTypeConstants.TYPE_REFERENCE_LONG_NAME)
                || type.contains(MsOnionTypeConstants.TYPE_REFERENCE_SHORT_NAME)) {
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
            cell.setCellValue(MsOnionNumberUtils.toDouble(str));
            cell.setCellStyle(style);
        } else if (type.contains(MsOnionTypeConstants.TYPE_REFERENCE_DOUBLE_NAME)
                || type.contains(MsOnionTypeConstants.TYPE_REFERENCE_FLOAT_NAME)) {
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
            cell.setCellValue(MsOnionNumberUtils.toDouble(str));
            cell.setCellStyle(style);
        } else {
            cell.setCellValue(new XSSFRichTextString(str));
        }
    }


}
