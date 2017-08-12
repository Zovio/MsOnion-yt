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

import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRandomUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 文件名工具类
 * @Title: MsOnionFilenameUtils.java
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
 * 文件名工具类
 * @ClassName: MsOnionFilenameUtils
 * @Description: 文件名工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月1日 下午7:01:37
 */
public final class MsOnionFilenameUtils extends FilenameUtils {

    /**
     * 随机最小值
     */
    private static final int RANDOM_MIN_VALUE = 100;

    /**
     * 随机最大值
     */
    private static final int RANDOM_MAX_VALUE = 999;

    private MsOnionFilenameUtils() { }

    /**
     * 生成文件名称，例如：20170501201921318_888.jpg
     * @param filename 文件名，必须包括扩展名，例如：xxx/xx/58d484a0N1d9d2ebf.jpg
     * @return 返回生成文件名
     * @throws MsOnionException 异常
     */
    public static String generateFilename(String filename) throws MsOnionException {
        if (StringUtils.isBlank(filename) || !filename.contains(".")) {
            // 非法文件名
            throw new MsOnionException(MsOnionMessageConstants.MESSAGE_FILENAME_ILLEGAL + filename,
                    MsOnionStatusConstants.STATUS_400);
        }
        filename = filename.trim();
        String extension = getExtension(filename);
        // 20170501201921318
        String time = MsOnionDateUtils.toYyyyMMddHHmmssSSSUnity(System.currentTimeMillis());
        // 随机
        int random = MsOnionRandomUtils.getRandom(RANDOM_MIN_VALUE, RANDOM_MAX_VALUE);
        return String.format("%s_%s.%s", time, random, extension);
    }

    /**
     * 上传上传到cos的路径
     * @param uploadPath 页面传过来的路径
     * @param filename 文件名称
     * @return cos上传路径
     * @throws MsOnionException 异常
     */
    public static String generateCOSFilePath(String uploadPath, String filename) throws MsOnionException {
        // 模块 + 年月日 + 文件名
        StringBuffer sb = new StringBuffer();
        String separator = MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR;
        if (StringUtils.isNotBlank(uploadPath)) {
            if (!uploadPath.startsWith(separator)) {
                uploadPath = separator + uploadPath.trim();
            }
            if (!uploadPath.endsWith(separator)) {
                uploadPath = uploadPath.trim() + separator;
            }
            sb.append(uploadPath);
        } else {
            sb.append(separator);
        }
        String tmp = MsOnionDateUtils.toYyyyMMddUnity(System.currentTimeMillis());
        return  sb.append(tmp).append(separator).append(generateFilename(filename)).toString();
    }

    /**
     * 判断是否是系统支持的 文件资源类型
     * @param fileName 文件名称
     * @return 结果
     */
    public static boolean isFileResource(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return false;
        }
        String extension = getExtension(fileName);
        if (MsOnionConstants.FILE_RESOURCE_TYPES.contains(extension.toLowerCase())) {
            return true;
        }
        return  false;
    }

    /**
     * 判断是否是系统支持的 图片类型
     * @param fileName 文件名称
     * @return 结果
     */
    public static boolean isImage(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return false;
        }
        String extension = getExtension(fileName);
        if (MsOnionConstants.FILE_PICTURE_TYPES.contains(extension.toLowerCase())) {
            return true;
        }
        return  false;
    }

}
