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
package cc.msonion.carambola.parent.ext.excel;
/**
 * @Title: ExcelHandleAdapter
 * @Package: cc.msonion.carambola.manager.common.utils.excel
 * @Description: excel适配器抽象类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月26日 11:38:01
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月26日 11:38:01
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.parent.common.constants.MsOnionExcelConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.ext.excel.simple.MsOnionExcelWriteUtils;
import cc.msonion.carambola.parent.ext.excel.view.MsOnionBaseReaderView;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @param <T> view
 * @ClassName: ExcelHandleAdapter
 * @Description: excel适配器抽象类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月26日 11:38:01
 */
public class MsOnionExcelHandleAdapter<T extends MsOnionBaseReaderView> {


    /**
     * reader 接口
     */
    private MsOnionExcelReader reader;

    /**
     * 日志
     */
    private MsOnionLogger msOnionLogger;

    /**
     * 数据处理类
     */
    private MsOnionExcelDataService<T> excelDataService;

    public MsOnionExcelHandleAdapter(MsOnionExcelReader reader, MsOnionExcelDataService<T> excelDataService) {
        this.reader = reader;
        this.excelDataService = excelDataService;
    }

    /**
     * 边读边写
     *
     * @param clazz          类
     * @param resultFilePath 返回结果的文件位置
     * @param msOnionLogger  msOnionLogger
     * @return MsOnionResult MsOnionResult
     * @throws MsOnionException 异常信息
     */
    public MsOnionResult parseAndInsert(Class<T> clazz, String resultFilePath, MsOnionLogger msOnionLogger) throws MsOnionException {
        final double f = 1000.0d;
        // 存放返回结果的EXCEL 结果集合
        long beginTime = System.currentTimeMillis();
        reader.parse();
        long endTime = System.currentTimeMillis();
        msOnionLogger.info(this.getClass().getName(), "#### EXCEL导入解析时间 #####" + ((endTime - beginTime) / f));
        // 存放标题结果的集合
        List<String> titleList = reader.getTitleList();
        // 待处理数据集合
        List<T> rowObjectList = reader.getRowObjectList();
        // 检查格式
        if (!checkTemplate(this.excelDataService.getTemplateTitle(), titleList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionExcelConstants.MESSAGE_EXCEL_FILE_FORMAT_ERROR);
        }
        // 写出数据集合
        beginTime = System.currentTimeMillis();
        List<T> resultRowList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(rowObjectList)) {
            List<T> writeBuffer = new ArrayList<T>();
            for (int i = 1; i <= rowObjectList.size(); i++) {
                T t = rowObjectList.get(i - 1);
                writeBuffer.add(t);
                // 只有解析正常才能写入到数据库
                int j = i % MsOnionExcelConstants.DEFAULT_EXCEL_WRITE_PAGESIZE;
                if ((i > 0) && (j == 0)
                        || (i == rowObjectList.size())) {
                    long beginTime2 = System.currentTimeMillis();
                    writeBuffer = this.excelDataService.writeToDataBases(writeBuffer);
                    long endTime2 = System.currentTimeMillis();
                    msOnionLogger.info(this.getClass().getName(), "#### EXCEL导入单词调用服务操作时间 #####" + ((endTime2 - beginTime2) / f));
                    resultRowList.addAll(writeBuffer);
                    writeBuffer.clear();
                }
            }
        }
        endTime = System.currentTimeMillis();
        msOnionLogger.info(this.getClass().getName(), "#### EXCEL导入操作数据总时间 #####" + ((endTime - beginTime) / f));
        // 写出Excel到客户端
        MsOnionExcelWriteUtils.saveXlsx(resultFilePath, reader.getTitleLine(), titleList, resultRowList);
        return MsOnionResult.ok();
    }

    /**
     * 检查模版
     *
     * @param tmpTitleList Excel模型类
     * @param titleList    Excel标题的集合
     * @return boolean boolean
     * @throws MsOnionException MsOnionException
     */
    private boolean checkTemplate(List<String> tmpTitleList, List<String> titleList) throws MsOnionException {
        try {
            if (MsOnionCollectionUtils.isEmpty(titleList)) {
                return false;
            }
            if (tmpTitleList.size() != titleList.size()) {
                return false;
            }
            for (int i = 0; i < tmpTitleList.size(); i++) {
                if (!tmpTitleList.get(i).equals(titleList.get(i))) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            throw new MsOnionException(MsOnionExcelConstants.MESSAGE_EXCEL_FILE_FORMAT_ERROR,
                    MsOnionStatusConstants.STATUS_400);
        }
    }

}
