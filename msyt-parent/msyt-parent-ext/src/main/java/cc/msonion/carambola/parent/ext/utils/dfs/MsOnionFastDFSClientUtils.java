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

package cc.msonion.carambola.parent.ext.utils.dfs;

import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.ext.dfs.clients.MsOnionFastDFSClient;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.ext.pojo.dfs.MsOnionFastDFSParameter;
import cc.msonion.carambola.parent.interfaces.dfs.clients.adapter.MsOnionDFSParameterAdapter;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.NameValuePair;

/**
 * 分布式文件系统FastDFS客户端工具类
 *
 * @Title: MsOnionFastDFSClientUtils.java
 * @Package: cc.msonion.carambola.parent.ext.utils.dfs
 * @Description: 分布式文件系统FastDFS客户端工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月20日 下午1:48:16
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年4月20日 下午1:48:16
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * 分布式文件系统FastDFS客户端工具类
 *
 * @ClassName: MsOnionFastDFSClientUtils
 * @Description: 分布式文件系统FastDFS客户端工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月19日 下午1:48:16
 */
public final class MsOnionFastDFSClientUtils {

    private MsOnionFastDFSClientUtils() {
    }

    /**
     * 上传文件
     *
     * @param client    FastDFS客户端
     * @param logger       日志
     * @param parameter 参数
     * @return 返回上传结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static MsOnionResultAdapter uploadFile(MsOnionFastDFSClient client, MsOnionLogger logger, MsOnionDFSParameterAdapter parameter) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            logger.info(MsOnionFastDFSClientUtils.class.getName(), "uploadFile # 上传 # parameter=" + parameter);

            if (parameter instanceof MsOnionFastDFSParameter) {
                MsOnionFastDFSParameter fastDFSParameter = (MsOnionFastDFSParameter) parameter;

                if (StringUtils.isNotBlank(fastDFSParameter.getFilename())) {
                    String path = uploadFile1(client, fastDFSParameter.getFilename(),
                            fastDFSParameter.getExtName(), fastDFSParameter.getMetas());
                    return MsOnionResult.ok(String.format("%s%s", MsOnionConstants.DFS_FDFS_PROTOCOL_AND_DOMAIN, path));
                } else if (ArrayUtils.isNotEmpty(fastDFSParameter.getFileContent())) {
                    String path = uploadFile1(client, fastDFSParameter.getFileContent(),
                            fastDFSParameter.getExtName(), fastDFSParameter.getMetas());
                    return MsOnionResult.ok(String.format("%s%s", MsOnionConstants.DFS_FDFS_PROTOCOL_AND_DOMAIN, path));
                }
            }
            return null;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile1</p>
     * <p>Description: </p>
     *
     * @param fdfsClient FastDFS客户端
     * @param filename   文件全路径
     * @param extName    文件扩展名，不包含（.）
     * @param metas      文件扩展信息
     * @return 返回上传成功路径，例如：group1/M00/00/00/rBAy8lj4sViALzRNAABjYaRbeZk171.jpg
     * @throws MsOnionException 异常
     */
    public static String uploadFile1(MsOnionFastDFSClient fdfsClient, String filename, String extName, NameValuePair[] metas) throws MsOnionException {
//        if (StringUtils.isBlank(filename)) {
//            return null;
//        }
//        filename = filename.toString();
//        if (StringUtils.isNotBlank(extName)) {
//            extName = extName.toString();
//        }
        if (!inspectParameters(filename, extName)) {
            return null;
        }
        try {
            return fdfsClient.getStorageClient1().upload_file1(filename, extName, metas);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 上传文件
     *
     * @param fdfsClient FastDFS客户端
     * @param filename   文件全路径，例如：C:/Users/HeroCao/Desktop/yt.png （Windows路径）
     * @param extName    文件扩展名，不包括.，例如：jpg、png
     * @param metas      文件扩展信息
     * @return 返回上传成功路径数组，[0]: group1，[1]: M00/00/00/rBAy8lj4sViALzRNAABjYaRbeZk171.jpg ，
     * <p>最后访问：http://yt02-dev/group1/M00/00/00/rBAy8lj4rhSAB3d4AABFCHGtIO0597.jpg</p>
     * @throws MsOnionException 异常
     */
    public static String[] uploadFile(MsOnionFastDFSClient fdfsClient, String filename, String extName, NameValuePair[] metas)
            throws MsOnionException {
//        if (StringUtils.isBlank(filename)) {
//            return null;
//        }
//        filename = filename.toString();
//        if (StringUtils.isNotBlank(extName)) {
//            extName = extName.toString();
//        }
        if (!inspectParameters(filename, extName)) {
            return null;
        }
        try {
            String[] result = fdfsClient.getStorageClient().upload_file(filename, extName, metas);
            return result;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 上传文件
     *
     * @param fdfsClient FastDFS客户端
     * @param filename   文件全路径，例如：C:/Users/HeroCao/Desktop/yt.png （Windows路径）
     * @return 返回上传成功路径，例如：group1/M00/00/00/rBAy8lj4sViALzRNAABjYaRbeZk171.jpg
     * @throws MsOnionException 异常
     * @Title: uploadFile1
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月20日 下午1:48:16
     */
    public static String uploadFile1(MsOnionFastDFSClient fdfsClient, String filename) throws MsOnionException {
        try {
            return uploadFile1(fdfsClient, filename, null, null);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 上传文件
     *
     * @param fdfsClient FastDFS客户端
     * @param filename   文件全路径，例如：C:/Users/HeroCao/Desktop/yt.png （Windows路径）
     * @return 回上传成功路径数组，[0]: group1，[1]: M00/00/00/rBAy8lj4sViALzRNAABjYaRbeZk171.jpg ，
     * <p>最后访问：http://yt02-dev/group1/M00/00/00/rBAy8lj4rhSAB3d4AABFCHGtIO0597.jpg</p>
     * @throws MsOnionException 异常
     * @Title: uploadFile
     * @Description: 上传文件
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年7月31日 下午11:08:59
     */
    public static String[] uploadFile(MsOnionFastDFSClient fdfsClient, String filename) throws MsOnionException {
        return uploadFile(fdfsClient, filename, null, null);
    }

    /**
     * 上传文件
     *
     * @param fdfsClient FastDFS客户端
     * @param filename   文件全路径，例如：C:/Users/HeroCao/Desktop/yt.png （Windows路径）
     * @param extName    文件扩展名，不包括.，例如：jpg、png
     * @return 返回上传成功路径，例如：group1/M00/00/00/rBAy8lj4sViALzRNAABjYaRbeZk171.jpg
     * @throws MsOnionException 异常
     * @Title: uploadFile1
     * @Description: 上传文件
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年7月31日 下午11:08:51
     */
    public static String uploadFile1(MsOnionFastDFSClient fdfsClient, String filename, String extName) throws MsOnionException {
        return uploadFile1(fdfsClient, filename, extName, null);
    }

    /**
     * 上传文件
     *
     * @param fdfsClient FastDFS客户端
     * @param filename   文件全路径，例如：C:/Users/HeroCao/Desktop/yt.png （Windows路径）
     * @param extName    文件扩展名，不包括.，例如：jpg、png
     * @return 返回上传成功路径数组，[0]: group1，[1]: M00/00/00/rBAy8lj4sViALzRNAABjYaRbeZk171.jpg ，
     * <p>最后访问：http://yt02-dev/group1/M00/00/00/rBAy8lj4rhSAB3d4AABFCHGtIO0597.jpg</p>
     * @throws MsOnionException 异常
     * @Title: uploadFile
     * @Description: 上传文件
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年7月31日 下午11:08:41
     */
    public static String[] uploadFile(MsOnionFastDFSClient fdfsClient, String filename, String extName) throws MsOnionException {
        return uploadFile(fdfsClient, filename, extName, null);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     *
     * @param fdfsClient  FastDFS客户端
     * @param fileContent 文件的内容，字节数组
     * @param extName     文件扩展名，不包括.，例如：jpg、png
     * @param metas       文件扩展信息
     * @return 返回上传成功路径，例如：group1/M00/00/00/rBAy8lj4sViALzRNAABjYaRbeZk171.jpg
     * @throws MsOnionException 异常
     */
    public static String uploadFile1(MsOnionFastDFSClient fdfsClient, byte[] fileContent,
                                     String extName, NameValuePair[] metas) throws MsOnionException {
        try {
            return fdfsClient.getStorageClient1().upload_file1(fileContent, extName, metas);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 上传文件
     *
     * @param fdfsClient  FastDFS客户端
     * @param fileContent 文件的内容，字节数组
     * @param extName     文件扩展名，不包括.，例如：jpg、png
     * @param metas       文件扩展信息
     * @return 返回上传成功路径数组，[0]: group1，[1]: M00/00/00/rBAy8lj4sViALzRNAABjYaRbeZk171.jpg ，
     * <p>最后访问：http://yt02-dev/group1/M00/00/00/rBAy8lj4rhSAB3d4AABFCHGtIO0597.jpg</p>
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年7月31日 下午11:08:27
     */
    public static String[] uploadFile(MsOnionFastDFSClient fdfsClient, byte[] fileContent, String extName, NameValuePair[] metas)
            throws MsOnionException {
        try {
            String[] result = fdfsClient.getStorageClient().upload_file(fileContent, extName, metas);
            return result;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 上传文件
     *
     * @param fdfsClient  FastDFS客户端
     * @param fileContent 文件的内容，字节数组
     * @return 返回上传成功路径，例如：group1/M00/00/00/rBAy8lj4sViALzRNAABjYaRbeZk171.jpg
     * @throws MsOnionException 异常
     * @Title: uploadFile1
     * @Description: 上传文件
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年7月31日 下午11:08:14
     */
    public static String uploadFile1(MsOnionFastDFSClient fdfsClient, byte[] fileContent) throws MsOnionException {
        return uploadFile1(fdfsClient, fileContent, null, null);
    }

    /**
     * 上传文件
     *
     * @param fdfsClient  FastDFS客户端
     * @param fileContent 文件的内容，字节数组
     * @return 返回上传成功路径数组，[0]: group1，[1]: M00/00/00/rBAy8lj4sViALzRNAABjYaRbeZk171.jpg ，
     * <p>最后访问：http://yt02-dev/group1/M00/00/00/rBAy8lj4rhSAB3d4AABFCHGtIO0597.jpg</p>
     * @throws MsOnionException 异常
     * @Title: uploadFile
     * @Description: 上传文件
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年7月31日 下午11:08:05
     */
    public static String[] uploadFile(MsOnionFastDFSClient fdfsClient, byte[] fileContent) throws MsOnionException {
        return uploadFile(fdfsClient, fileContent, null, null);
    }

    /**
     * 上传文件
     *
     * @param fdfsClient  FastDFS客户端
     * @param fileContent 文件的内容，字节数组
     * @param extName     文件扩展名，不包括.，例如：jpg、png
     * @return 返回上传成功路径：group1/M00/00/00/rBAy8lj4rhSAB3d4AABFCHGtIO0597.jpg
     * @throws Exception
     * @Title: uploadFile1
     * @Description: 上传文件
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年7月31日 下午11:07:49
     */
    public static String uploadFile1(MsOnionFastDFSClient fdfsClient, byte[] fileContent, String extName) throws MsOnionException {
        return uploadFile1(fdfsClient, fileContent, extName, null);
    }

    /**
     * 上传文件
     *
     * @param fdfsClient  FastDFS客户端
     * @param fileContent 文件的内容，字节数组
     * @param extName     文件扩展名，不包括.，例如：jpg、png
     * @return 返回上传成功路径数组，[0]: group1，[1]: M00/00/00/rBAy8lj4sViALzRNAABjYaRbeZk171.jpg ，
     * <p>最后访问：http://yt02-dev/group1/M00/00/00/rBAy8lj4rhSAB3d4AABFCHGtIO0597.jpg</p>
     * @throws MsOnionException 异常
     * @Title: uploadFile
     * @Description: 上传文件
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年7月31日 下午11:05:11
     */
    public static String[] uploadFile(MsOnionFastDFSClient fdfsClient, byte[] fileContent, String extName) throws MsOnionException {
        return uploadFile(fdfsClient, fileContent, extName, null);
    }

    /**
     * 检查参数
     *
     * @param filename 文件名
     * @param extName  扩展名
     * @return
     */
    private static boolean inspectParameters(String filename, String extName) {
        if (StringUtils.isBlank(filename)) {
            return false;
        }
        filename = filename.toString();
        if (StringUtils.isNotBlank(extName)) {
            extName = extName.toString();
        }
        return true;
    }

}
