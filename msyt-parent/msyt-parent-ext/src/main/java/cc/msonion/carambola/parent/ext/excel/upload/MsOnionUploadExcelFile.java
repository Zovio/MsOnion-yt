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
package cc.msonion.carambola.parent.ext.excel.upload;

/**
 * @Title: UpFileUtils
 * @Package: cc.msonion.carambola.manager.common.utils.upload
 * @Description: execl文件上传工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月01日 14:14:31
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月01日 14:14:31
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.parent.common.constants.MsOnionExcelConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFileUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFilenameUtils;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UpFileUtils
 * @Description: execl文件上传工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月01日 14:14:31
 */
@Component
public class MsOnionUploadExcelFile {


    /**
     * Execl 文件上传最大大小
     */
    @Value("${msonion_carambola_config_execl_file_size}")
    private Long maxFileSize;

    /**
     * 文件上传根目录
     */
    @Value("${msonion_carambola_config_execl_file_basePath}")
    private String basePath;

    /**
     * 文件上传后返回结果文件
     */
    @Value("${msonion_carambola_config_execl_file_result_suffix}")
    private String resultSuffix;


    /**
     * 上传文件
     *
     * @param file 文件
     * @return 上传结果
     * @throws MsOnionException 异常
     */
    public MsOnionResult doUpFile(CommonsMultipartFile file) throws MsOnionException {
        try {
            // 文件大小超过限制
            if (file.getSize() > maxFileSize) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        MsOnionExcelConstants.MESSAGE_EXCEL_FILE_SIZE_ERROR);
            }
            // 文件类型超过限制
            String fileName = file.getOriginalFilename();
            String fileExt = MsOnionFilenameUtils.getExtension(fileName);
            if (!fileExt.toLowerCase().equals(MsOnionExcelConstants.EXCEL_2007_FILE_EXT)
                    && (!fileExt.toLowerCase().equals(MsOnionExcelConstants.EXCEL_2003_FILE_EXT))) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        MsOnionExcelConstants.MESSAGE_EXCEL_TYPE_ERROR);
            }
            // 上传文件目录相对路劲
            String filePath = MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR
                    + MsOnionDateUtils.toYyyyMMddUnity(System.currentTimeMillis());
            // 上传文件的根目录绝对路径
            File rootFile = MsOnionFileUtils.getFileResourceUploadRootDir(basePath);
            String absolutePath = rootFile.getAbsolutePath();
            // 拼接全路径
            File curFile = new File(absolutePath + filePath);
            // 检查目录是否存在
            MsOnionFileUtils.forceMkdir(curFile);
            // 生成新文件
            String destFileName = MsOnionFilenameUtils.generateFilename(fileName);
            File destFile = new File(curFile, destFileName);
            // 生成返回结果的文件
            String resultFileName = getResultFile(destFileName);
            File resultFile = new File(curFile, resultFileName);
            resultFile.createNewFile();
            // 执行上传操作
            file.transferTo(destFile);
            // 用于返回的文件
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("destFile", destFile.getAbsolutePath());
            resultMap.put("destFileName", destFileName);
            resultMap.put("resultFile", resultFile.getAbsolutePath());
            resultMap.put("resultFileName", resultFileName);
            return MsOnionResult.ok(resultMap);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取返回结果的Excel文件的名字
     *
     * @param destFileName 上传的文件名
     * @return 文件的绝对路径
     * @throws MsOnionException 异常
     */
    private String getResultFile(String destFileName) {
        String prefix = destFileName.substring(0, destFileName.lastIndexOf("."));
        String suffix = destFileName.substring(destFileName.lastIndexOf("."), destFileName.length());
        String fileName = prefix + resultSuffix + suffix;
        return fileName;
    }


}
