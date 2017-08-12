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

package cc.msonion.carambola.parent.common.utils.image;

import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import org.apache.commons.lang3.StringUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

/**
 * @Title: MsOnionSimilarImageSearchUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils.image
 * @Description: 相似图片搜索工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月21日 下午9:08:09
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年4月21日 下午9:08:09
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相似图片搜索相关方法
 */

/**
 * @ClassName: MsOnionSimilarImageSearchUtils
 * @Description: 相似图片搜索工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月21日 下午9:08:09
 */
public final class MsOnionSimilarImageSearchUtils {

    /**
     * "汉明距离"（Hamming distance）边界值，也就是判断两张图片是否相似最大差距值
     */
    private static final int HAMMING_DISTANCE_BOUNDARY_VALUE = 5;

    private MsOnionSimilarImageSearchUtils() {
    }

    /**
     * 计算"汉明距离"（Hamming distance）。
     * 如果不相同的数据位不超过5，就说明两张图片很相似；如果大于10，就说明这是两张不同的图片。
     *
     * @param sourceHashCode 源hashCode，也就是当前搜索的图片HashCode
     * @param hashCode       与之比较的hashCode，也就是图片库中某一张图片HashCode
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午9:08:09
     */
    public static int hammingDistance(String sourceHashCode, String hashCode)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            int difference = 0;
            int len = sourceHashCode.length();

            for (int i = 0; i < len; i++) {
                if (sourceHashCode.charAt(i) != hashCode.charAt(i)) {
                    difference++;
                }
            }

            return difference;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

//    /**
//     * 生成图片指纹，HashCode
//     * @param filename 文件全路径名
//     * @return 图片指纹，HashCode
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月21日 下午9:08:09
//     */
//    public static String generateImageFingerprint(String filename) throws MsOnionIllegalArgumentException, MsOnionException {
//        if (StringUtils.isBlank(filename)) {
//            throw new MsOnionException(MsOnionMessageConstants.MESSAGE_PARAMETER_IMAGE_PATH_ILLEGAL,
//                    MsOnionStatusConstants.STATUS_400);
//        }
//        filename = filename.trim();
//        try {
//            BufferedImage source = MsOnionImageUtils.readImage(filename); // 读取文件
//
//            int width = 8;
//            int height = 8;
//
//            // 第一步，缩小尺寸。
//            // 将图片缩小到8x8的尺寸，总共64个像素。这一步的作用是去除图片的细节，只保留结构、明暗等基本信息，摒弃不同尺寸、比例带来的图片差异。
//            BufferedImage thumb = MsOnionImageUtils.thumb(source, width, height, false);
//
//            // 第二步，简化色彩。
//            // 将缩小后的图片，转为64级灰度。也就是说，所有像素点总共只有64种颜色。
//            int[] pixels = new int[width * height];
//            for (int i = 0; i < width; i++) {
//                for (int j = 0; j < height; j++) {
//                    pixels[i * height + j] = MsOnionImageUtils.rgbToGray(thumb.getRGB(i, j));
//                }
//            }
//
//            // 第三步，计算平均值。
//            // 计算所有64个像素的灰度平均值。
//            int avgPixel = MsOnionImageUtils.average(pixels);
//
//            // 第四步，比较像素的灰度。
//            // 将每个像素的灰度，与平均值进行比较。大于或等于平均值，记为1；小于平均值，记为0。
//            int[] comps = new int[width * height];
//            for (int i = 0; i < comps.length; i++) {
//                if (pixels[i] >= avgPixel) {
//                    comps[i] = 1;
//                } else {
//                    comps[i] = 0;
//                }
//            }
//
//            // 第五步，计算哈希值。
//            // 将上一步的比较结果，组合在一起，就构成了一个64位的整数，这就是这张图片的指纹。组合的次序并不重要，只要保证所有图片都采用同样次序就行了。
//            StringBuffer hashCode = new StringBuffer();
//            for (int i = 0; i < comps.length; i += 4) {
//                int result = comps[i] * (int) Math.pow(2, 3) + comps[i + 1] * (int) Math.pow(2, 2)
// + comps[i + 2] * (int) Math.pow(2, 1) + comps[i + 2];
//                hashCode.append(binaryToHex(result));
//            }
//
//            // 得到指纹以后，就可以对比不同的图片，看看64位中有多少位是不一样的。
//            return hashCode.toString();
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }


    /**
     * 两张是否相似的图片
     *
     * @param sourceImageHashCode 原图片hashCode，也就是当前搜索的图片HashCode
     * @param destImageHashCode   与之比较的hashCode，也就是图片库中某一张图片HashCode
     * @return 返回true：是相似的图片，false：不是相似的图片
     * @throws MsOnionException 异常
     */
    public static boolean isSimilarImage(String sourceImageHashCode, String destImageHashCode) throws MsOnionException {
        // 若HashCode相同，就是同一张图片
        if (StringUtils.isBlank(sourceImageHashCode) || StringUtils.isBlank(destImageHashCode)) {
            return false;
        }
        sourceImageHashCode = sourceImageHashCode.trim();
        destImageHashCode = destImageHashCode.trim();
        if (sourceImageHashCode.equals(destImageHashCode)) {
            return  true;
        }
        int distance = hammingDistance(sourceImageHashCode, destImageHashCode);
        return distance <= HAMMING_DISTANCE_BOUNDARY_VALUE;
    }

    /**
     * 生成图片指纹，HashCode
     *
     * @param is 图片资源输入流
     * @return 图片指纹，HashCode
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午9:08:09
     */
    public static String generateImageFingerprint(InputStream is)
            throws MsOnionIllegalArgumentException, MsOnionException {
        BufferedImage bufferedImage = MsOnionImageUtils.readImage(is);
        return generateImageFingerprint(bufferedImage);
    }

    /**
     * 生成图片指纹，HashCode
     *
     * @param file 图片资源文件全路径
     * @return 图片指纹，HashCode
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午9:08:09
     */
    public static String generateImageFingerprint(File file)
            throws MsOnionIllegalArgumentException, MsOnionException {
        BufferedImage bufferedImage = MsOnionImageUtils.readImage(file);
        return generateImageFingerprint(bufferedImage);
    }

    /**
     * 生成图片指纹，HashCode
     *
     * @param filename 图片资源文件全路径
     * @return 图片指纹，HashCode
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午9:08:09
     */
    public static String generateImageFingerprint(String filename)
            throws MsOnionIllegalArgumentException, MsOnionException {
        BufferedImage bufferedImage = MsOnionImageUtils.readImage(filename);
        return generateImageFingerprint(bufferedImage);
    }

    /**
     * 生成图片指纹，HashCode
     *
     * @param imageBytes 图片资源byte数组数据
     * @return 图片指纹，HashCode
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午9:08:09
     */
    public static String generateImageFingerprint(byte[] imageBytes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        BufferedImage bufferedImage = MsOnionImageUtils.readImage(imageBytes);
        return generateImageFingerprint(bufferedImage);
    }

    /**
     * 生成图片指纹，HashCode
     *
     * @param sourceImage 图片资源
     * @return 图片指纹，HashCode
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午9:08:09
     */
    public static String generateImageFingerprint(BufferedImage sourceImage)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == sourceImage) {
            throw new MsOnionException(MsOnionMessageConstants.MESSAGE_PARAMETER_IMAGE_SOURCE_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
        try {
            int width = 8;
            int height = 8;

            // 第一步，缩小尺寸。
            // 将图片缩小到8x8的尺寸，总共64个像素。这一步的作用是去除图片的细节，只保留结构、明暗等基本信息，摒弃不同尺寸、比例带来的图片差异。
            BufferedImage thumb = MsOnionImageUtils.thumb(sourceImage, width, height, false);

            // 第二步，简化色彩。
            // 将缩小后的图片，转为64级灰度。也就是说，所有像素点总共只有64种颜色。
            int[] pixels = new int[width * height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    pixels[i * height + j] = MsOnionImageUtils.rgbToGray(thumb.getRGB(i, j));
                }
            }

            // 第三步，计算平均值。
            // 计算所有64个像素的灰度平均值。
            int avgPixel = MsOnionImageUtils.average(pixels);

            // 第四步，比较像素的灰度。
            // 将每个像素的灰度，与平均值进行比较。大于或等于平均值，记为1；小于平均值，记为0。
            int[] comps = new int[width * height];
            for (int i = 0; i < comps.length; i++) {
                if (pixels[i] >= avgPixel) {
                    comps[i] = 1;
                } else {
                    comps[i] = 0;
                }
            }

            // 第五步，计算哈希值。
            // 将上一步的比较结果，组合在一起，就构成了一个64位的整数，这就是这张图片的指纹。组合的次序并不重要，只要保证所有图片都采用同样次序就行了。
            StringBuffer hashCode = new StringBuffer();
            for (int i = 0; i < comps.length; i += 4) {
                int result = comps[i] * (int) Math.pow(2, 3) + comps[i + 1] * (int) Math.pow(2, 2)
                        + comps[i + 2] * (int) Math.pow(2, 1) + comps[i + 2];
                hashCode.append(binaryToHex(result));
            }

            // 得到指纹以后，就可以对比不同的图片，看看64位中有多少位是不一样的。
            return hashCode.toString();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 二进制转为十六进制
     *
     * @param binary 二进制 int
     * @return char hex 字符
     */
    private static char binaryToHex(int binary) {
        char ch = ' ';
        switch (binary) {
            case 0:
                ch = '0';
                break;
            case 1:
                ch = '1';
                break;
            case 2:
                ch = '2';
                break;
            case 3:
                ch = '3';
                break;
            case 4:
                ch = '4';
                break;
            case 5:
                ch = '5';
                break;
            case 6:
                ch = '6';
                break;
            case 7:
                ch = '7';
                break;
            case 8:
                ch = '8';
                break;
            case 9:
                ch = '9';
                break;
            case 10:
                ch = 'a';
                break;
            case 11:
                ch = 'b';
                break;
            case 12:
                ch = 'c';
                break;
            case 13:
                ch = 'd';
                break;
            case 14:
                ch = 'e';
                break;
            case 15:
                ch = 'f';
                break;
            default:
                ch = ' ';
        }
        return ch;
    }
}
