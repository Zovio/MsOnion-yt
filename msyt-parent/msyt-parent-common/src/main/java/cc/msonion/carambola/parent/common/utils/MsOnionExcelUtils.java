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

package cc.msonion.carambola.parent.common.utils;

/**
 * @Title: MsOnionExcelUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: Excel工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017/4/8 14:02
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017/4/8 14:02
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：解析Excel方法
 */

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MsOnionExcelUtils
 * @Description: Excel工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017/4/8 14:02
 */
public final class MsOnionExcelUtils {

    private MsOnionExcelUtils() {
    }

    /**
     * 解析Excel
     *
     * @param fileUrl    文件地址
     * @param attributes 属性集合
     * @param options    选项，包括：isUseLowVersion 是否使用低版本
     * @param beanType   POJO的Class
     * @param <T>        POJO类型
     * @return 返回目标POJO的集合
     * @throws MsOnionException 异常
     */
    public static <T> List<T> parse(String fileUrl, Map<String, String> attributes, Map<String, Object> options, Class<T> beanType)
            throws MsOnionException {
        InputStream io = null;
        List<String> headerFields = new ArrayList<>();
        List<Map<String, Object>> list = new ArrayList<>();
        boolean isUseLowVersion = (boolean) options.get("isUseLowVersion");

        try {
            io = new FileInputStream(fileUrl);

            if (isUseLowVersion) {
                HSSFWorkbook wb = new HSSFWorkbook(io);

                // 取出第一个工作表，索引是0
                HSSFSheet sheet = wb.getSheetAt(0);

                // 第一行，获取列名
                HSSFRow headerRow = sheet.getRow(0);
                for (int i = 0; i <= headerRow.getLastCellNum(); i++) {
                    HSSFCell cell = headerRow.getCell(i);
                    String cellValue = cell.getStringCellValue();

                    headerFields.add(attributes.get(cellValue));
                }

                // 开始循环遍历行，表头不处理，从1开始
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    HSSFRow row = sheet.getRow(i); // 获取行对象
                    if (row == null) { // 如果为空，不处理
                        continue;
                    }

                    Map<String, Object> map = new HashMap<>();

                    // 循环遍历单元格
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        HSSFCell cell = row.getCell(j); // 获取单元格对象

                        HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
                        String value = dataFormatter.formatCellValue(cell);
                        map.put(headerFields.get(j), value);
                    }

                    list.add(map);
                }

                String json = MsOnionJsonUtils.objectToJson(list);
                return MsOnionJsonUtils.jsonToList(json, beanType);
            } else {
                XSSFWorkbook wb = new XSSFWorkbook(io);
            }
        } catch (FileNotFoundException e) {
            throw new MsOnionException(e);
        } catch (IOException e) {
            throw new MsOnionException(e);
        } finally {
            if (null != io) {
                try {
                    io.close();
                } catch (IOException e) {
                    throw new MsOnionException(e);
                }
            }
        }

        return null;
    }
}
