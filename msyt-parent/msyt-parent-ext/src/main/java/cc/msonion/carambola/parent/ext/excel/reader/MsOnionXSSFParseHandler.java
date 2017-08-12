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

import cc.msonion.carambola.parent.common.constants.MsOnionExcelConstants;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.excel.view.MsOnionReaderOperate;
import cc.msonion.carambola.parent.ext.excel.view.MsOnionXSSfDataType;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Title: XSSFParseHandler
 * @Package: cc.msonion.carambola.manager.common.utils.excel
 * @Description: XSSFParseHandler
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月23日 18:04:44
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月23日 18:04:44
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

/**
 * @ClassName: XSSFParseHandler
 * @Description: XSSFParseHandler
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月23日 18:04:44
 */
public class MsOnionXSSFParseHandler extends DefaultHandler {

    /**
     * 默认的时间格式
     */
    private final String dateFmtString = "yyyy-MM-dd hh:mm:ss";
    /**
     * Table with styles
     */
    private StylesTable stylesTable;

    /**
     * Table with unique strings
     */
    private ReadOnlySharedStringsTable sharedStringsTable;

    /**
     * Set when V start element is seen
     */
    private boolean vIsOpen;

    /**
     * XssfDataType 表示列的类型
     */
    private MsOnionXSSfDataType nextDataType;

    /**
     * 表示该列的格式化类型的表达式的索引
     */
    private short formatIndex;
    /**
     * 表示该列的格式化的表达式
     */
    private String formatString;
    /**
     * formatter
     */
    private final DataFormatter formatter;

    /**
     * 开始行
     */
    private Integer headLine = MsOnionExcelConstants.DEFAULT_MIN_VALUE;

    /**
     * 当前列
     */
    private int thisColumn = MsOnionExcelConstants.DEFAULT_MIN_VALUE;
    /**
     * 当前行
     */
    private int thisLine = MsOnionExcelConstants.DEFAULT_MIN_VALUE;
    /**
     * 当前sheet
     */
    private int thisSheetNum = MsOnionExcelConstants.DEFAULT_MIN_VALUE;

    /**
     * 单元格的值
     */
    private StringBuffer value;
    /**
     * 行对象
     */
    private Object record;
    /**
     * 对象的类
     */
    private Class clazz;
    /**
     * 所有数据
     */
    private List rowObjectList;
    /**
     * 上一次的内容
     */
    private String lastContents;
    /**
     * 标题数据
     */
    private List<String> titleList;
    /**
     * 标题所在的行
     */
    private Integer titleLine = MsOnionExcelConstants.DEFAULT_MIN_VALUE;
    /**
     * 当前行是否是标题栏
     */
    private Boolean isTitleLime = false;


    /**
     * 读数据，可以设置标题行的位置titleLine，数据开始读取的位置beginLine，和当前页sheet
     */
    public MsOnionXSSFParseHandler(StylesTable styles, ReadOnlySharedStringsTable strings, List rowObjectList,
                                   Integer beginLine, List<String> titleList, Integer titleLine,
                                   Class clazz) throws Exception {
        this.stylesTable = styles;
        this.rowObjectList = rowObjectList;
        this.sharedStringsTable = strings;
        this.value = new StringBuffer();
        this.nextDataType = MsOnionXSSfDataType.NUMBER;
        this.formatter = new DataFormatter();
        if (beginLine != null && beginLine > 0) {
            this.headLine = beginLine - 1;
        }
        this.titleList = titleList;
        this.titleLine = titleLine;
        this.clazz = clazz;
    }

    /**
     * 初始化对象
     *
     * @return object
     * @throws Exception Exception
     */
    private Object createObject() throws Exception {
        Object object = Class.forName(clazz.getName()).newInstance();
        return object;
    }


    /**
     * 扫描元素开始时执行
     *
     * @param uri        uri
     * @param localName  localName
     * @param name       name
     * @param attributes attributes
     * @throws SAXException SAXException
     */
    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        try {
            // 读取标签内容初始化
            if ("dimension".equals(name)) {
                String d = attributes.getValue("ref");
            } else if ("inlineStr".equals(name) || "v".equals(name)) {
                vIsOpen = true;
                // 清空缓存
                value.setLength(0);
            } else if ("c".equals(name)) {
                // C 表示单元格
                // 获取R的值 （例如：A1，A2，B1，B2）
                String r = attributes.getValue("r");
                // 获取列的位置
                int firstDigit = MsOnionExcelConstants.DEFAULT_MIN_VALUE;
                for (int c = 0; c < r.length(); ++c) {
                    if (Character.isDigit(r.charAt(c))) {
                        firstDigit = c;
                        break;
                    }
                }
                thisColumn = nameToColumn(r.substring(0, firstDigit));
                // 设置默认值（数字）
                this.nextDataType = MsOnionXSSfDataType.NUMBER;

                this.formatIndex = MsOnionExcelConstants.DEFAULT_MIN_VALUE;
                this.formatString = null;
                // 获取列的类型
                String cellType = attributes.getValue("t");
                String cellStyleStr = attributes.getValue("s");
                if ("b".equals(cellType)) {
                    // bool类型
                    nextDataType = MsOnionXSSfDataType.BOOL;
                } else if ("e".equals(cellType)) {
                    // 错误类型
                    nextDataType = MsOnionXSSfDataType.ERROR;
                } else if ("inlineStr".equals(cellType)) {
                    // 读取标签内容初始化
                    nextDataType = MsOnionXSSfDataType.INLINESTR;
                } else if ("s".equals(cellType)) {
                    // 字符串格式
                    nextDataType = MsOnionXSSfDataType.SSTINDEX;
                } else if ("str".equals(cellType)) {
                    // 字符串格式
                    nextDataType = MsOnionXSSfDataType.FORMULA;
                } else if (cellStyleStr != null) {
                    // 如果格式化字符串不为空
                    int styleIndex = Integer.parseInt(cellStyleStr);
                    XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                    this.formatIndex = style.getDataFormat();
                    this.formatString = style.getDataFormatString();

                    if (formatString == null) {
                        nextDataType = MsOnionXSSfDataType.NULL;
                        formatString = BuiltinFormats.getBuiltinFormat(formatIndex);
                    }  else {
                        nextDataType = MsOnionXSSfDataType.FORMULA;
                    }
    //                else if (cellStyleStr.equals("2")) {
    //                    nextDataType = XSSfDataType.DATE;
    //                    formatString = dateFmtString;
    //                }
                }
            } else if ("row".equals(name)) {
                String spans = attributes.getValue("spans");
                String r = attributes.getValue("r");
                this.thisLine = Integer.parseInt(r) - 1;
                if ((titleLine > MsOnionExcelConstants.DEFAULT_MIN_VALUE)
                        && (thisLine == (titleLine - 1))) {
                    isTitleLime = true;
                } else {
                    isTitleLime = false;
                }
                // 置空数组
                record = createObject();
            }
        } catch (Exception e) {
            throw new SAXException(e);
        }
    }

    /**
     * end Element
     *
     * @param uri       uri
     * @param localName localName
     * @param name      name
     * @throws SAXException SAXException
     */
    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {
        try {
            String str = null;
            // t元素也包含字符串
            if ("t".equals(name)) {
                if (lastContents != null && lastContents.length() > 0) {
                    int num = lastContents.length();
                    XSSFRichTextString rtsi = new XSSFRichTextString(value.toString());
                    lastContents = rtsi.toString();
                    str = lastContents.substring(num);
                } else {
                    XSSFRichTextString rtsi = new XSSFRichTextString(value.toString());
                    lastContents = rtsi.toString();
                    str = lastContents;
                }
                MsOnionReaderOperate.setDeclaredField(record, thisColumn, str.trim());
            } else if ("v".equals(name)) {
                switch (nextDataType) {

                    case BOOL:
                        char first = value.charAt(0);
                        str = first == '0' ? "FALSE" : "TRUE";
                        break;

                    case ERROR:
                        str = value.toString();
                        break;
                    case FORMULA:
                        str = value.toString();
                        break;

                    case INLINESTR:
                        XSSFRichTextString rtsi = new XSSFRichTextString(value.toString());
                        str = rtsi.toString();
                        break;

                    case SSTINDEX:
                        String sstIndex = value.toString();
                        try {
                            int idx = Integer.parseInt(sstIndex);

                            XSSFRichTextString rtss = new XSSFRichTextString(sharedStringsTable.getEntryAt(idx));
                            str = rtss.toString().trim();
                            rtss = null;
                        } catch (NumberFormatException ex) {
                            str = value.toString();
                        }
                        break;

                    case NUMBER:
                        String n = value.toString();
                        // 数值格式
                        if (this.formatString != null) {
                            str = formatter.formatRawCellContents(
                                    Double.parseDouble(n), this.formatIndex,
                                    this.formatString);
                        } else {
                            str = n;
                        }
                        break;
                    case DATE:
                        Double d = Double.parseDouble(value.toString());
                        Date date = HSSFDateUtil.getJavaDate(d);
                        str = formateDateToString(date);
                        break;
                    default:
                        str = "";
                        break;
                }
                if (!isTitleLime) {
                    if (MsOnionStringUtils.isNotEmpty(str.trim())
                            && !str.trim().equals(MsOnionExcelConstants.EXCEL_SPECIAL_STRING)) {
                        MsOnionReaderOperate.setDeclaredField(record, thisColumn, str.trim());
                    }

                } else if (thisSheetNum == 1) {
                    titleList.add(str.trim());
                }
            } else if ("row".equals(name)) {
                if ((record != null)
                        && (thisLine >= headLine)
                        && (!isTitleLime)) {
                    MsOnionReaderOperate.setDefaultField(thisSheetNum, thisLine, record);
                    rowObjectList.add(record);
                }
                thisLine++;
            }
        } catch (Exception e) {
            throw new SAXException(e);
        }

    }

    /**
     * 字符处理
     *
     * @param ch     ch
     * @param start  start
     * @param length length
     * @throws SAXException SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (vIsOpen) {
            value.append(ch, start, length);
        }
    }

    /**
     * nameToColumn
     *
     * @param name name
     * @return column
     */
    private int nameToColumn(String name) {
        int column = MsOnionExcelConstants.DEFAULT_MIN_VALUE;
        final int l = 26;
        for (int i = 0; i < name.length(); ++i) {
            int c = name.charAt(i);
            column = (column + 1) * l + c - 'A';
        }
        return column;
    }

    /**
     * formateDate
     *
     * @param date date
     * @return String
     */
    private String formateDateToString(Date date) {
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat(dateFmtString);
        return sdf.format(date);

    }





    /**
     * @return thisSheetNum
     */
    public int getThisSheetNum() {
        return thisSheetNum;
    }

    /**
     * @param thisSheetNum thisSheetNum
     */
    public void setThisSheetNum(int thisSheetNum) {
        this.thisSheetNum = thisSheetNum;
    }
}
