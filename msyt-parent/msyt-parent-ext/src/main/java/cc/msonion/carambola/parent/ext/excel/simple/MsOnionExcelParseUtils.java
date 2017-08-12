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
 * @Title: ExcelParseUtils
 * @Package: cc.msonion.carambola.manager.common.utils
 * @Description: Excel解析工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月22日 20:50:18
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月22日 20:50:18
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.parent.common.constants.MsOnionExcelConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 * @ClassName: ExcelParseUtils
 * @Description: Excel解析工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月22日 20:50:18
 */
public final class MsOnionExcelParseUtils {

    private MsOnionExcelParseUtils() {

    }
    /**
     * 自动根据版本解析Excel
     *
     * @param fileName    文件名称
     * @param is          输入流 方法调用者关闭流></>
     * @param clazz      目标类
     * @param <T>      POJO类型
     * @return the ms onion result
     * @throws MsOnionException the ms onion exception
     * @Title autoParse
     * @Description 自动根据版本解析Excel
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月22日 21:32:02
     */
    public static <T> LinkedList<T> autoParse(String fileName, InputStream is, Class<T> clazz) throws MsOnionException {

        try {
            String fileType = MsOnionFilenameUtils.getExtension(fileName);

            if (fileType.equalsIgnoreCase(MsOnionExcelConstants.EXCEL_2007_FILE_EXT)) {
                // 如果是2007 或2010的版本
                return parse2007(is, clazz);
            } else {
                // 如果是2003的版本
                return parse2003(is, clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MsOnionException(e);
        }
    }
    /**
     * Excel2003 解析类
     *
     * @param is          输入流<方法调用者关闭流></>
     * @param clazz      目标类
     * @param <T>      POJO类型
     * @return the ms onion result
     * @throws MsOnionException the ms onion exception
     * @Title parse2003
     * @Description 描述信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月22日 20:52:58
     */
    public static <T> LinkedList<T> parse2003(InputStream is, Class<T> clazz) throws MsOnionException {
        try {
            LinkedList<T> list = new LinkedList<T>();
            // 解析xsl文件
            POIFSFileSystem pfs = new POIFSFileSystem(is);
            HSSFWorkbook hwb = new HSSFWorkbook(pfs);
            DecimalFormat df = new DecimalFormat("#.##");
            // 获取第0个sheet页的数据
            HSSFSheet hs = hwb.getSheetAt(0);
            // 获取第一行的行数 0
            int rowStart = hs.getFirstRowNum();
            // 获取最后一行的行数-1
            int rowEnd = hs.getLastRowNum();
            // 行的遍历
            for (int i = rowStart + 1; i <= rowEnd; i++) {
                T obj = (T) Class.forName(clazz.getName()).newInstance();
                // 获取行
                HSSFRow hr = hs.getRow(i);
                if (hr == null) {
                    continue;
                }
                // 获取第一列
                int cellStart = hr.getFirstCellNum();
                // 获取列的总数
                int cellEnd = hr.getLastCellNum();
                // 列的遍历
                for (int k = cellStart; k < cellEnd; k++) {
                    // 获取列
                    HSSFCell hc = hr.getCell(k);
                    if (hc == null || hc.equals("")) {
                        continue;
                    }
                    switch (hc.getCellType()) {
                        // 字符串
                        case HSSFCell.CELL_TYPE_STRING:
                            obj = MsOnionReflectionUtils.reflectFieldArray(obj, k, hc.getStringCellValue());
                            break;
                        // boolean类型
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            obj = MsOnionReflectionUtils.reflectFieldArray(obj, k, hc.getBooleanCellValue());
                            break;
                        // 数字
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            obj = MsOnionReflectionUtils.reflectFieldArray(obj, k, df.format(Double.valueOf(hc.getNumericCellValue())));
                            break;
                        // 公式
                        case HSSFCell.CELL_TYPE_FORMULA:
                            obj = MsOnionReflectionUtils.reflectFieldArray(obj, k, hc.getCellFormula());
                            break;
                        // 空值
                        case HSSFCell.CELL_TYPE_BLANK:
                            obj = MsOnionReflectionUtils.reflectFieldArray(obj, k, null);
                            break;
                        // 错误
                        case HSSFCell.CELL_TYPE_ERROR:
                            obj = MsOnionReflectionUtils.reflectFieldArray(obj, k, null);
                            break;
                        default:
                            obj = MsOnionReflectionUtils.reflectFieldArray(obj, k, hc.getStringCellValue());
                            break;
                    }
                }
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * Excel2007 解析类
     *
     * @param is          文件输入流  <方法调用者关闭流></>
     * @param clazz      目标类
     * @param <T>      POJO类型
     * @return the ms onion result
     * @throws MsOnionException the ms onion exception
     * @Title parse2007
     * @Description 描述信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月22日 20:56:20
     */
    public static <T> LinkedList<T> parse2007(InputStream is, Class<T> clazz) throws MsOnionException {
        try {
            LinkedList<T> list = new LinkedList<T>();
            XSSFWorkbook xw = new XSSFWorkbook(is);
            DecimalFormat df = new DecimalFormat("#.##");
            // 获取xsl
            XSSFSheet xs = xw.getSheetAt(0);
            // 获取第一行的行数 0
            int rowStart = xs.getFirstRowNum();
            // 获取最后一行的行数-1
            int rowEnd = xs.getLastRowNum();
            // 行的遍历
            for (int i = rowStart + 1; i <= rowEnd; i++) {
                T obj = (T) Class.forName(clazz.getName()).newInstance();
                // 获取行
                XSSFRow hr = xs.getRow(i);
                if (hr == null) {
                    continue;
                }
                // 获取第一列
                int cellStart = hr.getFirstCellNum();
                // 获取列的总数
                int cellEnd = hr.getLastCellNum();
                // 列的遍历
                for (int k = cellStart; k < cellEnd; k++) {
                    // 获取列
                    XSSFCell hc = hr.getCell(k);
                    if (hc == null || hc.equals("")) {
                        continue;
                    }
                    switch (hc.getCellType()) {
                        // 字符串
                        case HSSFCell.CELL_TYPE_STRING:
                            obj = MsOnionReflectionUtils.reflectFieldArray(obj, k, hc.getStringCellValue());
                            break;
                        // boolean类型
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            obj = MsOnionReflectionUtils.reflectFieldArray(obj, k, hc.getBooleanCellValue());
                            break;
                        // 数字
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            obj = MsOnionReflectionUtils.reflectFieldArray(obj, k,
                                    df.format(Double.valueOf(hc.getNumericCellValue())));
                            break;
                        // 公式
                        case HSSFCell.CELL_TYPE_FORMULA:
                            obj = MsOnionReflectionUtils.reflectFieldArray(obj, k, hc.getCellFormula());
                            break;
                        // 空值
                        case HSSFCell.CELL_TYPE_BLANK:
                            obj =  MsOnionReflectionUtils.reflectFieldArray(obj, k, null);
                            break;
                        // 错误
                        case HSSFCell.CELL_TYPE_ERROR:
                            obj =  MsOnionReflectionUtils.reflectFieldArray(obj, k, null);
                            break;
                        default:
                            break;
                    }
                }
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }


}
