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


package cc.msonion.carambola.manager.web.controller.ext;

/**
 * @Title: ResourceExporterUtils.java
 * @Package: cc.msonion.carambola.manager.web.controller.ext
 * @Description: 资源下载导出工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月25日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月25日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import java.util.List;

/**
 * @ClassName: ResourceExporterUtils
 * @Description: 资源下载导出工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月25日
 */
public final class ResourceExporterUtils {

    private ResourceExporterUtils() {

    }
    /**
     * 获取excel 导出头部
     *
     * @param headList 表头集合
     * @param fileName 文件名称
     * @return 字符串
     */
    public static String getReportHead(List<String> headList, String fileName) {
        StringBuffer sb = new StringBuffer()
                .append("<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
                        + "xmlns:x=\"urn:schemas-microsoft-com:office:excel\" xmlns=\"http://www.w3.org/TR/REC-html40\">")
                .append("<head>")
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>")

                .append("<!--[if gte mso 9]>")
                .append("<xml>")
                .append("<x:ExcelWorkbook>")
                .append("     <x:ExcelWorksheets>")
                .append("      <x:ExcelWorksheet>")
                .append("       <x:Name>" + fileName + "</x:Name>")
                .append("       <x:WorksheetOptions>")
                .append("        <x:DefaultRowHeight>285</x:DefaultRowHeight>")
                /*.append("        <x:Unsynced/>")
                .append("        <x:Selected/>")
                .append("        <x:Panes>")
                .append("         <x:Pane>")
                .append("          <x:Number>3</x:Number>")
                .append("          <x:ActiveCol>0</x:ActiveCol>")
                .append("          <x:ActiveRow>0</x:ActiveRow>")
                .append("         </x:Pane>")
                .append("        </x:Panes>")*/
              /*  .append("        <x:FitToPage/>")
                .append("        <x:ProtectContents>False</x:ProtectContents>")
                .append("        <x:ProtectObjects>False</x:ProtectObjects>")
                .append("        <x:ProtectScenarios>False</x:ProtectScenarios>")
                .append("        <x:Print>")
                .append("         <x:ValidPrinterInfo/>")
                .append("         <x:NumberOfCopies>41550</x:NumberOfCopies>")
                .append("         <x:PaperSizeIndex>0</x:PaperSizeIndex>")
                .append("         <x:Scale>285</x:Scale>")
                .append("         <x:HorizontalResolution>-6846</x:HorizontalResolution>")
                .append("         <x:VerticalResolution>1</x:VerticalResolution>")
                .append("        </x:Print>")*/
                .append("       </x:WorksheetOptions>")
                .append("      </x:ExcelWorksheet>")
                .append("     </x:ExcelWorksheets>")
                /*.append("     <x:ProtectStructure>False</x:ProtectStructure>")
                .append("     <x:ProtectWindows>False</x:ProtectWindows>")*/
                .append("     <x:WindowHeight>8700</x:WindowHeight>")
                .append("     <x:WindowWidth>19200</x:WindowWidth>")
                .append("</x:ExcelWorkbook>")
                .append("</xml>")
                .append("<![endif]-->")
                .append("</head>")
                .append("<body >")
                .append("<table  border=\"0\" cellpadding=\"0\" cellspacing=\"0\""
                        + "style='border-collapse:collapse;table-layout:fixed;'>")
                .append("<tr height=\"19\" >");
        for (int i = 0; i < headList.size(); i++) {
            sb.append("<td width=\"100\"  x:str >" + headList.get(i) + "</td>");
        }
        return sb.append("</tr>").toString();
    }

    /**
     * @return 订单导出底部
     */
    public static String getReportTail() {
        return new StringBuffer().append("</table>").append("</body>").append("</html>")
                .toString();
    }


}
