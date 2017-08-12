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
package cc.msonion.carambola.parent.ext.excel.view;
/**
 * @Title: BaseReaderViewOperate
 * @Package: cc.msonion.carambola.manager.common.utils.excel
 * @Description: BaseReaderView操作类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月26日 09:47:21
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月26日 09:47:21
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;

/**
 * @ClassName: BaseReaderViewOperate
 * @Description: BaseReaderView操作类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月26日 09:47:21
 */
public final class MsOnionReaderOperate {

    private MsOnionReaderOperate() {

    }

    /**
     * errorMsg
     */
    public static final String ERROR_FIELD = "errorMsg";

    /**
     * isOk
     */
    public static final String OK_FIELD = "isOk";

    /**
     * curSheetNum
     */
    public static final String CURSHEETNUM_FIELD = "curSheetNum";
    /**
     * curLine
     */
    public static final String CURLINE_FIELD = "curLine";
    /**
     * 格式不正确提示
     */
    public static final String ERROR_MSG_STRING = "第%d列,(%s)格式不正确";
    /**
     * 数据异常提示
     */
    public static final String ERROR_MSG = "该行数据异常";

    /**
     * 异常设置字段
     *
     * @param record       实体对象
     * @param columnNumber 列的序号
     * @param str          错误提示字符串
     * @throws MsOnionException 异常
     */
    public static void setError(Object record, int columnNumber, String str) throws MsOnionException {
        try {
            StringBuffer sb = new StringBuffer();
            String errorMsg = MsOnionStringUtils.toStringDefault(MsOnionReflectionUtils.
                    getParentFieldValue(record, ERROR_FIELD), "");
            sb.append(errorMsg).append(" ").append(String.format(ERROR_MSG_STRING, columnNumber, str));
            MsOnionReflectionUtils.setParentFieldValue(record, ERROR_FIELD, sb.toString());
            MsOnionReflectionUtils.setParentFieldValue(record, OK_FIELD, false);
        } catch (MsOnionException e) {
            throw e;
        }
    }

    /**
     * 数据异常
     *
     * @param record      record
     * @param valueString valueString
     * @throws MsOnionException 异常
     */
    public static void setError(Object record, String valueString) throws MsOnionException {
        try {
            StringBuffer sb = new StringBuffer();
            String errorMsg = MsOnionStringUtils.toStringDefault(MsOnionReflectionUtils.
                    getParentFieldValue(record, ERROR_FIELD), "");
            sb.append(errorMsg).append(" ").append(ERROR_MSG);
            MsOnionReflectionUtils.setParentFieldValue(record, ERROR_FIELD, sb.toString());
            MsOnionReflectionUtils.setParentFieldValue(record, OK_FIELD, false);
        } catch (MsOnionException e) {
            throw e;
        }
    }

    /**
     * 反射设置字段值
     *
     * @param record     实体对象
     * @param thisColumn  当前列的序号
     * @param str 字段的值
     * @throws MsOnionException 异常
     */
    public static void setDeclaredField(Object record, int thisColumn, String str) throws MsOnionException {
        try {
            MsOnionReflectionUtils.reflectFieldArray(record, thisColumn, str);
        } catch (Exception e) {
            MsOnionReaderOperate.setError(record, thisColumn, str);
        }

    }

    /**
     * 设置默认字段
     *
     * @param curSheetNum 当前sheet
     * @param curLine     当前行
     * @param record      record
     * @throws MsOnionException 异常
     */
    public static void setDefaultField(Integer curSheetNum, Integer curLine, Object record) throws MsOnionException {
        try {
            MsOnionReflectionUtils.setParentFieldValue(record, CURSHEETNUM_FIELD, curSheetNum);
            MsOnionReflectionUtils.setParentFieldValue(record, CURLINE_FIELD, curLine);
        } catch (MsOnionException e) {
            setError(record, ERROR_MSG);
        }
    }

    /**
     * 返回字段值
     *
     * @param record      对象
     * @param fieldName 字段名
     * @throws MsOnionException 异常
     * @return 字段的值
     */
    public static Object getFieldValue(Object record, String fieldName) throws MsOnionException {

        try {
            return MsOnionReflectionUtils.getParentFieldValue(record, fieldName);
        } catch (MsOnionException e) {
            throw e;
        }
    }
}
