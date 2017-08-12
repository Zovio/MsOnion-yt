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

package cc.msonion.carambola.parent.common.utils.file;

import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Optional;

/**
 * 文件操作工具类
 *
 * @Title: MsOnionFileUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils.file
 * @Description: 日期工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月1日 下午7:01:37
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年5月1日 下午7:01:37
 * @Modify-version: V2.0.0
 * @Modify-description: 创建
 */

/**
 * 文件操作工具类
 *
 * @ClassName: MsOnionFileUtils
 * @Description: 文件操作工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月1日 下午7:01:37
 */
public final class MsOnionFileUtils extends FileUtils {

    /**
     * Windows平台当前工程根目录：/
     */
    public static final String WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR = "/";

    /**
     * 每次读取多少字节 （10MB）
     */
    public static final int BUFFER_SIZE = 10485760;


    /**
     * 20MB
     */
    public static final long DEFALUT_COS_FILESIZE = 20971520L;

    private MsOnionFileUtils() {
    }

    /**
     *  将windows下 的\ 替换 /
     * @param path 路径
     * @return
     */
    public static String transitionPath(String path){
        return Optional.ofNullable(path).map(s ->  path.replace("\\", WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR)).orElse(path);
    }

    /**
     * 根据运行系统，获取FileResource上传根目录
     *
     * @param path 路径
     * @return 返回FileResource上传根目录
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static File getFileResourceUploadRootDir(String path)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(path)) {
            throw new MsOnionException(MsOnionMessageConstants.MESSAGE_PARAMETER_NETTY_UPLOAD_ROOT_DIR_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
        try {
            path = path.trim();
            String osName = MsOnionSystemUtils.getOsName();
            if (osName.toLowerCase().contains(MsOnionSystemUtils.OS_WINDOWS.toLowerCase())
                    && path.startsWith(WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR)) {
                // Windows，当前工程根目录
                return new File(MsOnionSystemUtils.getUserDir(), path);
            }
            return new File(path);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /////////////////////////  上传文件 ### Begin //////////////////////////////////////////////

    /**
     * 上传文件，会自动关闭 Source Stream
     *
     * @param sourceFile 源文件，全路径
     * @param destFile   保存目标文件，全路径
     * @throws MsOnionException 异常
     */
    public static void uploadFile(final String sourceFile, final String destFile) throws MsOnionException {
        sourceFile.trim();
        destFile.trim();
        uploadFile(new File(sourceFile), new File(destFile));
    }

    /**
     * 上传文件，会自动关闭 Source Stream
     *
     * @param sourceFile 源文件
     * @param destFile   保存目标文件
     * @throws MsOnionException 异常
     */
    public static void uploadFile(final File sourceFile, final File destFile) throws MsOnionException {
        try {
            FileInputStream fileInputStream = openInputStream(sourceFile);
            uploadFile(fileInputStream, destFile);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 上传文件，会自动关闭 Source Stream
     *
     * @param source   源文件 InputStream
     * @param destFile 保存目标文件
     * @throws MsOnionException 异常
     */
    public static void uploadFile(final InputStream source, final File destFile) throws MsOnionException {
        try {
            copyInputStreamToFile(source, destFile);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ///////////////// 上传大文件 ## Begin ///////////////////////////

    /**
     * 上传大文件，会自动关闭 Source Stream
     *
     * @param sourceFile 源文件，全路径
     * @param destFile   保存目标文件，全路径
     * @throws MsOnionException 异常
     */
    public static void uploadLargeFile(final String sourceFile, final String destFile) throws MsOnionException {
        sourceFile.trim();
        destFile.trim();
        uploadLargeFile(new File(sourceFile), new File(destFile));
    }

    /**
     * 上传大文件，会自动关闭 Source Stream
     *
     * @param sourceFile 源文件
     * @param destFile   保存目标文件
     * @throws MsOnionException 异常
     */
    public static void uploadLargeFile(final File sourceFile, final File destFile) throws MsOnionException {
        RandomAccessFile randomAccessFile = null;
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(destFile, "rw");
            // 文件大小（单位:B）
            long length = sourceFile.length();
            int mBufferSize = BUFFER_SIZE; //每次处理 1024*1024 字节,1MB
            byte[] buffer = new byte[mBufferSize];
            long nStart = 0L;
            int i = 0;
            while (nStart < length) {
                randomAccessFile = new RandomAccessFile(sourceFile, "r");
                randomAccessFile.seek(nStart);
                int hasRead = randomAccessFile.read(buffer);
                if (hasRead > 0) {
                    raf.seek(nStart);
                    raf.write(buffer);
                    //MsOnionFileUtils.writeByteArrayToFile(destFile, buffer, 0, hasRead, true);
                }
                nStart += hasRead;
            }
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                throw new MsOnionException(e);
            }


        }
    }


    /**
     * 上传大文件，会自动关闭 Source Stream
     *
     * @param source   源文件 InputStream
     * @param destFile 保存目标文件
     * @throws MsOnionException 异常
     */
    public static void uploadLargeFile(final InputStream source, final File destFile) throws MsOnionException {
        try {
            // TODO: 采取 RandomAccessFile(filename, "rw");
            copyInputStreamToFile(source, destFile);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ///////////////// 上传大文件 ## End ///////////////////////////

    /////////////////////////  上传文件 ### End  //////////////////////////////////////////////

}
