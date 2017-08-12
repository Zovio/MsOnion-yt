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
 * @Title: HSSFListenerBean
 * @Package: cc.msonion.carambola.manager.common.utils.excel
 * @Description: HSSFListener 监听器实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月23日 17:24:38
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月23日 17:24:38
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.parent.common.constants.MsOnionExcelConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.excel.view.MsOnionReaderOperate;
import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.record.*;

import java.util.List;

/**
 * @ClassName: HSSFListenerBean
 * @Description: 监听器实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月23日 17:24:38
 */
public class MsOnionHSSFListenerImpl implements HSSFListener {


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
     * 所有数据
     */
    private List rowObjectList;

    /**
     * 行对象
     */
    private Object rowObject;

    /**
     * 对象的类
     */
    private Class clazz;

    /**
     * sstrec
     */
    private SSTRecord sstrec;
    /**
     * stringFormulaRecord
     */
    private FormulaRecord stringFormulaRecord;
    /**
     * Internal <code>FormatTrackingHSSFListener</code> to handle cell
     * formatting within the extraction.
     */
    private FormatTrackingHSSFListener formatListener;
    /**
     * For handling formulas with string results
     */
    private int nextRow;
    /**
     * For handling formulas with string results
     */
    private int nextColumn;
    /**
     * outputNextStringRecord;
     */
    private boolean outputNextStringRecord;
    /**
     * 标题所在的行
     */
    private Integer titleLine = MsOnionExcelConstants.DEFAULT_MIN_VALUE;
    /**
     * 当前行是否是标题栏
     */
    private Boolean isTitleLime = false;
    /**
     * 标题数据
     */
    private List<String> titleList;


    public MsOnionHSSFListenerImpl(List rowObjectList, Integer beginLine, List<String> titleList, Integer titleLine,
                                   Class clazz) throws MsOnionException {
        this.rowObjectList = rowObjectList;
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
     * 监控入口
     *
     * @param record 记录
     */
    @Override
    public void processRecord(Record record) {
        try {
            String str = "";
            switch (record.getSid()) {
                // BOF
                case BOFRecord.sid:
                    BOFRecord bof = (BOFRecord) record;
                    if (bof.getType() == bof.TYPE_WORKSHEET) {
                        //读取新的一个Sheet页
                        if (thisSheetNum == MsOnionExcelConstants.DEFAULT_MIN_VALUE) {
                            thisSheetNum = 0;
                        }
                        thisSheetNum++;
                    }
                    break;
                // 开始解析Sheet的信息,获取sheet的名称等信息
                case BoundSheetRecord.sid:
                    BoundSheetRecord boundSheetRecord = (BoundSheetRecord) record;
                    break;
                // 执行行记录事件
                case RowRecord.sid:
                    RowRecord rowrec = (RowRecord) record;
                    rowrec.getFirstCol();
                    break;
                case SSTRecord.sid:
                    sstrec = (SSTRecord) record;
                    break;
                case FormulaRecord.sid: // Cell value from a formula
                    FormulaRecord frec = (FormulaRecord) record;
                    thisLine = frec.getRow();
                    thisColumn = frec.getColumn();
                    if (Double.isNaN(frec.getValue())) {
                        // Formula result is a string
                        // This is stored in the next record
                        outputNextStringRecord = true;
                        nextRow = frec.getRow();
                        nextColumn = frec.getColumn();
                    } else {
                        str = formatListener.formatNumberDateCell(frec);
                    }
                    setObjectData(str);
                    break;
                case StringRecord.sid:// 单元格中公式的字符串
                    if (outputNextStringRecord) {
                        // String for formula
                        StringRecord srec = (StringRecord) record;
                        str = srec.getString();
                        thisLine = nextRow;
                        thisColumn = nextColumn;
                        outputNextStringRecord = false;
                    }
                    setObjectData(str);
                    break;
                case LabelRecord.sid:
                    LabelRecord lrec = (LabelRecord) record;
                    thisLine = lrec.getRow();
                    thisColumn = lrec.getColumn();
                    str = lrec.getValue().trim();
                    setObjectData(str);
                    break;
                // 发现字符串类型，这儿要取字符串的值的话，跟据其index去字符串表里读取
                case LabelSSTRecord.sid:
                    LabelSSTRecord lsrec = (LabelSSTRecord) record;
                    thisLine = lsrec.getRow();
                    thisColumn = lsrec.getColumn();
                    if (sstrec == null) {
                        str = "";
                    } else {
                        str = sstrec.getString(lsrec.getSSTIndex()).toString().trim();
                    }
                    setObjectData(str);
                    break;
                // 发现数字类型的cell
                case NumberRecord.sid:
                    NumberRecord numrec = (NumberRecord) record;
                    thisLine = numrec.getRow();
                    thisColumn = numrec.getColumn();
                    str = formatListener.formatNumberDateCell(numrec).trim();
                    setObjectData(str);
                    break;
                // 解析boolean错误信息
                case BoolErrRecord.sid:
                    BoolErrRecord ber = (BoolErrRecord) record;
                    thisLine = ber.getRow();
                    thisColumn = ber.getColumn();
                    str = "";
                    setObjectData(str);
                    break;
                // 空白记录的信息
                case BlankRecord.sid:
                    BlankRecord brec = (BlankRecord) record;
                    thisLine = brec.getRow();
                    thisColumn = brec.getColumn();
                    str = "";
                    setObjectData(str);
                    break;
                default:
                    break;
            }

            // 空值的操作
            if (record instanceof MissingCellDummyRecord) {
                MissingCellDummyRecord mc = (MissingCellDummyRecord) record;
                thisLine = mc.getRow();
                thisColumn = mc.getColumn();
                str = "";
                setObjectData(str);

            }

            // 行结束时的操作
            if ((record instanceof LastCellOfRowDummyRecord)
                    && (!isTitleLime)
                    && (thisLine >= headLine)) {
                MsOnionReaderOperate.setDefaultField(thisSheetNum, thisLine, rowObject);
                rowObjectList.add(rowObject);
                rowObject = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置实体数据和标题数据
     *
     * @param str 单元格的值
     * @throws Exception Exception
     */
    private void setObjectData(String str) throws Exception {
        if ((titleLine > MsOnionExcelConstants.DEFAULT_MIN_VALUE)
                && (thisLine == (titleLine - 1))) {
            isTitleLime = true;
        } else {
            isTitleLime = false;
        }
        if (rowObject == null) {
            rowObject = createObject();
        }
        if (!isTitleLime) {
            if (MsOnionStringUtils.isNotEmpty(str.trim())
                    && !str.trim().equals(MsOnionExcelConstants.EXCEL_SPECIAL_STRING)) {
                MsOnionReaderOperate.setDeclaredField(rowObject, thisColumn, str.trim());
            }
        } else if (thisSheetNum == 1) {
            titleList.add(str.trim());
        }
    }

    /**
     * @return formatListener
     */
    public FormatTrackingHSSFListener getFormatListener() {
        return formatListener;
    }

    /**
     * @param formatListener formatListener
     */
    public void setFormatListener(FormatTrackingHSSFListener formatListener) {
        this.formatListener = formatListener;
    }
}