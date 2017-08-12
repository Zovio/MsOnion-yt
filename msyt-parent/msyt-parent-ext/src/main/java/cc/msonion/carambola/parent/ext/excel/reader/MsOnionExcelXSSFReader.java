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
package cc.msonion.carambola.parent.ext.excel.reader;
/**
 * @Title: Excel2007Reader
 * @Package: cc.msonion.carambola.manager.common.utils.excel
 * @Description: 读取高版本的Excel文件 2007及以上
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月23日 17:50:48
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月23日 17:50:48
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.parent.common.constants.MsOnionExcelConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionIOUtils;
import cc.msonion.carambola.parent.ext.excel.MsOnionAbstractExcelReader;
import cc.msonion.carambola.parent.ext.excel.MsOnionExcelReader;
import cc.msonion.carambola.parent.ext.excel.view.MsOnionBaseReaderView;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: Excel2007Reader
 * @param <T> 实体
 * @Description: 读取高版本的Excel文件 2007及以上
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月23日 17:50:48
 */
public class MsOnionExcelXSSFReader<T extends MsOnionBaseReaderView> extends MsOnionAbstractExcelReader<T>
        implements MsOnionExcelReader<T> {



    /**
     * 记录sheet数据
     */
    private List<T> rowObjectList = new LinkedList<T>();

    /**
     * 泛型的类型
     */
    private Class<T> clazz;

    /**
     * 输入流
     */
    private InputStream inputStream;

    /**
     * 开始行
     */
    private Integer beginLine = MsOnionExcelConstants.DEFAULT_MIN_VALUE;

    /**
     * 标题数据
     */
    private List<String> titleList = new ArrayList<String>();

    /**
     * 标题所在的行
     */
    private Integer titleLine;

    /**
     * OPCPackage
     */
    private OPCPackage opcPackage;

    /**
     * readOnlySharedStringsTable
     */
    private ReadOnlySharedStringsTable readOnlySharedStringsTable;

    /**
     * xssfReader
     */
    private XSSFReader xssfReader;

    /**
     * stylesTable
     */
    private StylesTable stylesTable;




    /**
     * 构造函数
     * @param clazz 实体的类
     * @param inputStream 流
     * @param titleLine 标题所在的行
     * @param beginLine 数据开始行
     * @throws MsOnionException 异常
     */
    public MsOnionExcelXSSFReader(Class<T> clazz, InputStream inputStream, Integer titleLine,
                                  Integer beginLine) throws MsOnionException {
        this.clazz = clazz;
        this.inputStream = new BufferedInputStream(inputStream);
        this.beginLine = beginLine;
        this.titleLine = titleLine;
        init();
    }

    /**
     * 初始化
     *
     * @throws MsOnionException MsOnionException
     */
    private void init() throws MsOnionException {
        try {
            opcPackage = OPCPackage.open(inputStream);
            readOnlySharedStringsTable = new ReadOnlySharedStringsTable(opcPackage);
            xssfReader = new XSSFReader(opcPackage);
            stylesTable = xssfReader.getStylesTable();
        } catch (Exception e) {
            throw new MsOnionException(e);
        }

    }

    /**
     * 解析
     *
     * @throws MsOnionException MsOnionException
     */
    @Override
    public void parse() throws MsOnionException {
        InputStream stream = null;
        try {
            int sheetCount = 1;
            // 遍历excel的sheet
            XSSFReader.SheetIterator sheetIterator = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
            MsOnionXSSFParseHandler handler = new MsOnionXSSFParseHandler(stylesTable, readOnlySharedStringsTable,
                    rowObjectList, beginLine, titleList, titleLine, clazz);
            while (sheetIterator.hasNext()) {
                // 读取每个sheet的流,使用sax方式进行解析，通过getRows()返回
                stream = sheetIterator.next();
                InputSource sheetSource = new InputSource(stream);
                handler.setThisSheetNum(sheetCount);
                SAXParserFactory saxFactory = SAXParserFactory.newInstance();
                SAXParser saxParser = saxFactory.newSAXParser();
                XMLReader sheetParser = saxParser.getXMLReader();
                sheetParser.setContentHandler(handler);
                sheetParser.parse(sheetSource);
                sheetCount++;
                stream.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            MsOnionIOUtils.closeQuietly(stream);
            MsOnionIOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 读某个页的数据
     *
     * @param sheetNum sheetNum
     * @throws MsOnionException MsOnionException
     */
    @Deprecated
    public void parseSheet(Integer sheetNum) throws MsOnionException {
        InputStream stream = null;
        try {
            if (sheetNum == null || sheetNum < 0) {
                return;
            }
            int sheetCount = 1;
            // 遍历excel的sheet
            XSSFReader.SheetIterator sheetIterator = (XSSFReader.SheetIterator) xssfReader.getSheetsData();

            MsOnionXSSFParseHandler handler = new MsOnionXSSFParseHandler(stylesTable, readOnlySharedStringsTable,
                    rowObjectList, beginLine, titleList, titleLine, clazz);
            while (sheetIterator.hasNext()) {
                // 读取每个sheet的流,使用sax方式进行解析，通过getRows()返回
                stream = sheetIterator.next();
                if (sheetCount != sheetNum) {
                    sheetCount++;
                    continue;
                }

                InputSource sheetSource = new InputSource(stream);
                SAXParserFactory saxFactory = SAXParserFactory.newInstance();
                SAXParser saxParser = saxFactory.newSAXParser();
                XMLReader sheetParser = saxParser.getXMLReader();
                sheetParser.setContentHandler(handler);
                sheetParser.parse(sheetSource);
                sheetCount++;
                stream.close();
            }
        } catch (Exception e) {
            throw new MsOnionException(e);
        } finally {
            MsOnionIOUtils.closeQuietly(stream);
            MsOnionIOUtils.closeQuietly(inputStream);
        }

    }


    /**
     * 获取队列集合
     *
     * @return
     */
    @Override
    public List<T> getRowObjectList() {
        return this.rowObjectList;
    }

    /**
     * 获取Excel文档的标题
     *
     * @return 标题数据集合
     * @throws MsOnionException MsOnionException
     */
    @Override
    public List<String> getTitleList() throws MsOnionException {
        return titleList;
    }

    /**
     * 获取到标题所在的行
     *
     * @return 行
     */
    @Override
    public Integer getTitleLine() {
        return this.titleLine;
    }
}
