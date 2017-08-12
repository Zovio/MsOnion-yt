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
package cc.msonion.carambola.manager.web.controller.excel;

/**
 * @Title: ExcelImportController
 * @Package: cc.msonion.carambola.manager.web.controller.excel
 * @Description: excel 文件数据导入
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月22日 19:37:53
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月22日 19:37:53
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.collector.ext.view.ExcelItemView;
import cc.msonion.carambola.collector.ext.view.ItemPriceView;
import cc.msonion.carambola.collector.service.ItemPriceService;
import cc.msonion.carambola.collector.service.ItemService;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.logistics.pojo.excel.CustomView;
import cc.msonion.carambola.logistics.service.LogisticsCustomsDeclareService;
import cc.msonion.carambola.logistics.service.LogisticsUnitService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.web.controller.excel.impl.CustomsExcelServiceImpl;
import cc.msonion.carambola.manager.web.controller.excel.impl.ItemExcelServiceImpl;
import cc.msonion.carambola.manager.web.controller.excel.impl.ItemPriceExcelServiceImpl;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionExcelConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.utils.MsOnionIOUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityBase64Utils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.excel.MsOnionExcelDataService;
import cc.msonion.carambola.parent.ext.excel.MsOnionExcelHandleAdapter;
import cc.msonion.carambola.parent.ext.excel.MsOnionExcelReader;
import cc.msonion.carambola.parent.ext.excel.MsOnionExcelReaderFactory;
import cc.msonion.carambola.parent.ext.excel.upload.MsOnionUploadExcelFile;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * @ClassName: ExcelImportController
 * @Description: excel 文件数据导入
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月22日 19:37:53
 */
@Controller
public class ExcelImportController extends MsOnionBaseAppController {

    /**
     * 商品service
     */
    @Autowired
    private ItemService itemService;

    /**
     * 商品价格service
     */
    @Autowired
    private ItemPriceService itemPriceService;


    /**
     * 商品报关service
     */
    @Autowired
    private LogisticsCustomsDeclareService logisticsCustomsDeclareService;

    /**
     * 计量单位服务
     */
    @Autowired
    private LogisticsUnitService logisticsUnitService;

    /**
     * 文件上传处理类
     */
    @Autowired
    private MsOnionUploadExcelFile upFileUtils;

    /**
     * buff流缓存区大小
     */
    private final int buffSize = 10240;

    /**
     * 导入新商品数据
     *
     * @param req  HttpServletRequest 对象
     * @param res  HttpServletResponse  对象
     * @param file 文件对象
     * @return 全局统一返回对象
     */
    @RequestMapping(value = "/collect/item/importNewItemData", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult importNewItemData(HttpServletRequest req, HttpServletResponse res,
                                           @RequestParam("file") CommonsMultipartFile file) {
        InputStream inputStream = null;
        String resultFile = null;
        File tmpFile = null;
        try {

            if (file == null || file.getSize() == 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }

            // 参数信息
            this.getMsOnionLogger().info(getClass().getName(), " ####文件大小#######" + file.getSize());
            this.getMsOnionLogger().info(getClass().getName(),
                    " ####文件名称#######" + file.getOriginalFilename());

            // 上传该文件
            MsOnionResult upfileResult = upFileUtils.doUpFile(file);
            if (upfileResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return upfileResult;
            }
            Map<String, String> upfileResultMap = (Map<String, String>) upfileResult.getData();
            String fileName = upfileResultMap.get("destFileName");
            String destFile = upfileResultMap.get("destFile");
            resultFile = upfileResultMap.get("resultFile");
            inputStream = new FileInputStream(destFile);
            tmpFile = new File(destFile);
            // 版本信息
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 当前操作用户
            Member currentUser = CurrentUserUtils.getCurrentUser();

            // 解析和插入数据
            final int beginLine = 2;
            final int titleLine = 1;
            MsOnionExcelReader<ExcelItemView> reader = MsOnionExcelReaderFactory.getInstance().
                    getReader(ExcelItemView.class, fileName, inputStream, titleLine, beginLine);
            MsOnionExcelDataService excelDataService = new ItemExcelServiceImpl(currentUser,
                    itemService, apiVersion, this.getMsOnionLogger());
            MsOnionExcelHandleAdapter<ExcelItemView> adapter =
                    new MsOnionExcelHandleAdapter<ExcelItemView>(reader, excelDataService);
            MsOnionResult msOnionResult = adapter.parseAndInsert(ExcelItemView.class, resultFile, this.getMsOnionLogger());
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return msOnionResult;
            }
            // 写处理结果到客户端
            resultFile = MsOnionSecurityBase64Utils.encode(resultFile);
            return MsOnionResult.ok(resultFile);
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e, " #### 导入新商品数据出错 #######");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionExcelConstants.MESSAGE_EXCEL_FILE_FORMAT_ERROR);
        } finally {
            MsOnionIOUtils.closeQuietly(inputStream);
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }

    }


    /**
     * 商品价格数据导入
     *
     * @param req  HttpServletRequest 对象
     * @param res  HttpServletResponse  对象
     * @param file 文件对象
     * @return 全局统一返回对象
     */
    @RequestMapping(value = "/collect/item/price/importItemPriceData", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult importItemPriceData(HttpServletRequest req, HttpServletResponse res,
                                             @RequestParam("file") CommonsMultipartFile file) {
        InputStream inputStream = null;
        String resultFile = null;
        File tmpFile = null;
        try {
            if (file == null || file.getSize() == 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }
            // 参数信息
            this.getMsOnionLogger().info(getClass().getName(), " ####文件大小####### " + file.getSize());
            this.getMsOnionLogger().info(getClass().getName(),
                    " ####文件名称####### " + file.getOriginalFilename());

            // 上传该文件
            MsOnionResult upfileResult = upFileUtils.doUpFile(file);
            if (upfileResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return upfileResult;
            }
            Map<String, String> upfileResultMap = (Map<String, String>) upfileResult.getData();
            String fileName = upfileResultMap.get("destFileName");
            String destFile = upfileResultMap.get("destFile");
            resultFile = upfileResultMap.get("resultFile");
            inputStream = new FileInputStream(destFile);
            tmpFile = new File(destFile);
            // 版本信息
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 当前操作用户
            Member currentUser = CurrentUserUtils.getCurrentUser();
            // 解析和插入数据
            final int beginLine = 2;
            final int titleLine = 1;
            MsOnionExcelReader<ItemPriceView> reader = MsOnionExcelReaderFactory.getInstance().
                    getReader(ItemPriceView.class, fileName, inputStream, titleLine, beginLine);
            MsOnionExcelDataService excelDataService = new ItemPriceExcelServiceImpl(currentUser,
                    itemPriceService, apiVersion, this.getMsOnionLogger());
            MsOnionExcelHandleAdapter<ItemPriceView> adapter =
                    new MsOnionExcelHandleAdapter<ItemPriceView>(reader, excelDataService);
            MsOnionResult msOnionResult = adapter.parseAndInsert(ItemPriceView.class, resultFile, this.getMsOnionLogger());
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return msOnionResult;
            }
            // 写处理结果到客户端
            resultFile = MsOnionSecurityBase64Utils.encode(resultFile);
            return MsOnionResult.ok(resultFile);
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e, " #### 导入商品价格数据出错 #######");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionExcelConstants.MESSAGE_EXCEL_FILE_FORMAT_ERROR);
        } finally {
            MsOnionIOUtils.closeQuietly(inputStream);
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }

    }


    /**
     * 报关信息数据导入
     *
     * @param req  HttpServletRequest 对象
     * @param res  HttpServletResponse  对象
     * @param file 文件对象
     * @return 全局统一返回对象
     */
    @RequestMapping(value = "/logistics/customs/importCustomData", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult importCustomData(HttpServletRequest req, HttpServletResponse res,
                                          @RequestParam("file") CommonsMultipartFile file) {
        InputStream inputStream = null;
        String resultFile = null;
        File tmpFile = null;
        try {
            if (file == null || file.getSize() == 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }
            // 参数信息
            this.getMsOnionLogger().info(getClass().getName(), " #### 文件大小 ####### " + file.getSize());
            this.getMsOnionLogger().info(getClass().getName(),
                    " #### 文件名称 ####### " + file.getOriginalFilename());
            // 上传该文件
            MsOnionResult upfileResult = upFileUtils.doUpFile(file);
            if (upfileResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return upfileResult;
            }
            Map<String, String> upfileResultMap = (Map<String, String>) upfileResult.getData();
            String fileName = upfileResultMap.get("destFileName");
            String destFile = upfileResultMap.get("destFile");
            resultFile = upfileResultMap.get("resultFile");
            inputStream = new FileInputStream(destFile);
            tmpFile = new File(destFile);
            // 版本信息
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 当前操作用户
            Member currentUser = CurrentUserUtils.getCurrentUser();

            // 解析和插入数据
            final int beginLine = 2;
            final int titleLine = 1;

            MsOnionExcelReader<CustomView> reader = MsOnionExcelReaderFactory.getInstance().
                    getReader(CustomView.class, fileName, inputStream, titleLine, beginLine);
            MsOnionExcelDataService excelDataService = new CustomsExcelServiceImpl(apiVersion, this.getMsOnionLogger(), currentUser, itemService,
                    logisticsCustomsDeclareService, logisticsUnitService);
            MsOnionExcelHandleAdapter<CustomView> adapter =
                    new MsOnionExcelHandleAdapter<CustomView>(reader, excelDataService);
            MsOnionResult msOnionResult = adapter.parseAndInsert(CustomView.class, resultFile, this.getMsOnionLogger());
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return msOnionResult;
            }
            // 写处理结果到客户端
            resultFile = MsOnionSecurityBase64Utils.encode(resultFile);
            return MsOnionResult.ok(resultFile);
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e, " #### 导入报关信息数据出错 #######");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionExcelConstants.MESSAGE_EXCEL_FILE_FORMAT_ERROR);
        } finally {
            MsOnionIOUtils.closeQuietly(inputStream);
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }

    }


    /**
     * Excel文件下载
     *
     * @param req      请求
     * @param res      响应
     * @param filePath 文件路劲（Base64加密）
     * @return
     */
    @RequestMapping("/collect/item/downExcel")
    public void downExcel(HttpServletRequest req, HttpServletResponse res, String filePath) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        ServletOutputStream out = null;
        File destFile = null;
        try {
            res.setCharacterEncoding(MsOnionConstants.CHARSET_UTF_8);
            out = res.getOutputStream();
            // 判断参数
            if (MsOnionStringUtils.isEmpty(filePath)) {
                writeMessage(out, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
                return;
            }

            destFile = new File(MsOnionSecurityBase64Utils.decode(filePath));
            if (destFile == null || !destFile.exists()) {
                writeMessage(out, MessageConstants.MESSAGE_EXCEL_FILE_NOT_EXIST);
                return;
            }
            final int minSize = -1;
            res.setContentType("application/x-download");
            res.addHeader("Content-Disposition", "attachment;filename=" + destFile.getName());

            bis = new BufferedInputStream(new FileInputStream(destFile));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[buffSize];
            int bytesRead;
            while (minSize != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            writeMessage(out, MessageConstants.MESSAGE_SERVER_ERROR);
        } finally {
            MsOnionIOUtils.closeQuietly(bos);
            MsOnionIOUtils.closeQuietly(bis);
            MsOnionIOUtils.closeQuietly(out);
            if (destFile != null) {
                destFile.delete();
            }
        }
    }

    /**
     * 写信息到客户端
     *
     * @param out 输出流
     * @param msg 消息
     */
    private void writeMessage(ServletOutputStream out, String msg) {
        try {
            out.println(msg);
        } catch (IOException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, " #### 下载Excel文件出错 #######");
        } finally {
            MsOnionIOUtils.closeQuietly(out);
        }
    }
}
