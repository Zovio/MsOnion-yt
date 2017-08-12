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

import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionImageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.enums.MsOnionImageSizeMode;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionIOUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRandomUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;


/**
 * @Title: MsOnionImageUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils.image
 * @Description: 图片工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午9:08:09
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午9:08:09
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现获取图片Url
 */

/**
 * @ClassName: MsOnionImageUtils
 * @Description: 图片工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午9:08:09
 */
public final class MsOnionImageUtils {

    /**
     * 默认角度
     */
    public static final double DEFAULT_ANGLE = 0d;

    /**
     * 默认背景颜色，为白色
     */
    public static final Color DEFAULT_BGCOLOR = Color.WHITE; //Color.BLACK;

    /**
     * 默认的压缩质量比率：1.0f
     */
    public static final float DEFAULT_QUALITY = 1.0f; // 0.75f;

    /**
     * 横向合并
     */
    public static final int MERGE_TYPE_HORIZONTAL = 1;
    /**
     * 纵向合并
     */
    public static final int MERGE_TYPE_VERTICAL = 2;

    private MsOnionImageUtils() {
    }

    /**
     * @param image        以“,”分隔的图片地址字符串，例如：mm1.jpg,mm2.jpg,mm3.jpg
     * @param defaultImage 不存在显示的默认图片
     * @return
     * @Title: getFirstImage
     * @Description: 获取以“,”分隔的图片地址字符串，返回第一张图片，如果不存在返回默认图片
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午5:39:25
     */
    public static String getFirstImage(String image, String defaultImage) {
        if (StringUtils.isBlank(image)) {
            return defaultImage;
        }
        String[] images = image.split(",");
        if (null != images && images.length > 0) {
            return images[0];
        }
        return image;
    }

    ///////////////////////// 相似图片搜索， Begin ////////////////////////////

    /**
     * 生成缩略图 <br/>
     * 保存:ImageIO.write(BufferedImage, imgType[jpg/png/...], File);
     *
     * @param source 原图片
     * @param width  缩略图宽
     * @param height 缩略图高
     * @param b      是否等比缩放
     * @return 返回生成缩略图
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage thumb(BufferedImage source, int width,
                                      int height, boolean b) throws MsOnionIllegalArgumentException, MsOnionException {
        // targetW，targetH分别表示目标长和宽
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) width / source.getWidth();
        double sy = (double) height / source.getHeight();

        if (b) {
            if (sx > sy) {
                sx = sy;
                width = (int) (sx * source.getWidth());
            } else {
                sy = sx;
                height = (int) (sy * source.getHeight());
            }
        }

        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(width,
                    height);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else
            target = new BufferedImage(width, height, type);
        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    /**
     * 图片水印
     *
     * @param imgPath  待处理图片
     * @param markPath 水印图片
     * @param x        水印位于图片左上角的 x 坐标值
     * @param y        水印位于图片左上角的 y 坐标值
     * @param alpha    水印透明度 0.1f ~ 1.0f
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static void waterMark(String imgPath, String markPath, int x, int y,
                                 float alpha) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 加载待处理图片文件
            Image img = ImageIO.read(new File(imgPath));

            BufferedImage image = new BufferedImage(img.getWidth(null),
                    img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(img, 0, 0, null);

            // 加载水印图片文件
            Image watermark = ImageIO.read(new File(markPath));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            g.drawImage(watermark, x, y, null);
            g.dispose();

            // 保存处理后的文件
            FileOutputStream out = new FileOutputStream(imgPath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 文字水印
     *
     * @param imgPath 待处理图片
     * @param text    水印文字
     * @param font    水印字体信息
     * @param color   水印字体颜色
     * @param x       水印位于图片左上角的 x 坐标值
     * @param y       水印位于图片左上角的 y 坐标值
     * @param alpha   水印透明度 0.1f ~ 1.0f
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */

    public static void textMark(String imgPath, String text, Font font,
                                Color color, int x, int y, float alpha) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            Font targetFont = (font == null) ? new Font("宋体", 20, 13) : font;

            Image img = ImageIO.read(new File(imgPath));

            BufferedImage image = new BufferedImage(img.getWidth(null),
                    img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();

            g.drawImage(img, 0, 0, null);
            g.setColor(color);
            g.setFont(targetFont);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            g.drawString(text, x, y);
            g.dispose();
            FileOutputStream out = new FileOutputStream(imgPath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 读取JPEG图片
     *
     * @param filename 文件名
     * @return BufferedImage 图片对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage readJPEGImage(String filename) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            InputStream imageIn = new FileInputStream(new File(filename));
            // 得到输入的编码器，将文件流进行jpg格式编码
            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);
            // 得到编码后的图片对象
            BufferedImage sourceImage = decoder.decodeAsBufferedImage();

            return sourceImage;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 读取图片
     *
     * @param filename 文件全路径名
     * @return BufferedImage 图片对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage readImage(String filename)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(filename)) {
            throw new MsOnionException(MsOnionMessageConstants.MESSAGE_PARAMETER_IMAGE_PATH_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
        filename = filename.trim();
        try {
            File inputFile = new File(filename);
            BufferedImage sourceImage = ImageIO.read(inputFile);
            return sourceImage;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 读取图片
     *
     * @param imageFile 图片文件全路径名
     * @return BufferedImage 图片对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage readImage(File imageFile)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == imageFile || !imageFile.exists()) {
            throw new MsOnionException(MsOnionMessageConstants.MESSAGE_PARAMETER_IMAGE_PATH_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
        try {
            return ImageIO.read(imageFile);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 读取图片
     *
     * @param is 图片资源输入流
     * @return BufferedImage 图片对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage readImage(InputStream is)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == is) {
            throw new MsOnionException(MsOnionMessageConstants.MESSAGE_PARAMETER_IMAGE_SOURCE_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
        try {
            return ImageIO.read(is);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 读取图片
     *
     * @param imageBytes 图片资源byte数组
     * @return BufferedImage 图片对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage readImage(byte[] imageBytes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == imageBytes || imageBytes.length <= 0) {
            throw new MsOnionException(MsOnionMessageConstants.MESSAGE_PARAMETER_IMAGE_SOURCE_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
        try {
            return ImageIO.read(MsOnionIOUtils.bytes2Input(imageBytes));
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 灰度值计算
     *
     * @param pixels 像素
     * @return int 灰度值
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static int rgbToGray(int pixels) throws MsOnionIllegalArgumentException, MsOnionException {
        // int _alpha = (pixels >> 24) & 0xFF;
        int _red = (pixels >> 16) & 0xFF;
        int _green = (pixels >> 8) & 0xFF;
        int _blue = (pixels) & 0xFF;
        return (int) (0.3 * _red + 0.59 * _green + 0.11 * _blue);
    }

    /**
     * 计算数组的平均值
     *
     * @param pixels 数组
     * @return int 平均值
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static int average(int[] pixels) throws MsOnionIllegalArgumentException, MsOnionException {
        float m = 0;
        for (int i = 0; i < pixels.length; ++i) {
            m += pixels[i];
        }
        m = m / pixels.length;
        return (int) m;
    }

    /////////////////////////  相似图片搜索， End ////////////////////////////


    /////////////////////////  旋转图片， Begin ////////////////////////////

    /**
     * 旋转图片，默认背景颜色为黑色
     *
     * @param imageFilename 原始图片全路径
     * @param angle         旋转角度
     * @return 返回旋转之后的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage rotateImage(String imageFilename, double angle)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return rotateImage(imageFilename, angle, DEFAULT_BGCOLOR);
    }

    /**
     * 旋转图片
     *
     * @param imageFilename 原始图片全路径
     * @param angle         旋转角度
     * @param bgColor       背景颜色
     * @return 返回旋转之后的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage rotateImage(String imageFilename, double angle, Color bgColor)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(imageFilename)) {
            return null;
        }
        imageFilename = imageFilename.trim();
        try {
            File imageFile = new File(imageFilename);
            // 旋转图片
            return rotateImage(imageFile, angle, bgColor);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 旋转图片，默认背景颜色为：黑色
     *
     * @param imageFile 原始图片全路径文件
     * @param angle     旋转角度
     * @return 返回旋转之后的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage rotateImage(File imageFile, double angle)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return rotateImage(imageFile, angle, DEFAULT_BGCOLOR);
    }

    /**
     * 旋转图片
     *
     * @param imageFile 原始图片全路径文件
     * @param angle     旋转角度
     * @param bgColor   背景颜色
     * @return 返回旋转之后的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage rotateImage(File imageFile, double angle, Color bgColor)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == imageFile || !imageFile.exists()) {
            new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_IMAGE_PATH_ILLEGAL, MsOnionStatusConstants.STATUS_400);
        }
        try {
            BufferedImage image = ImageIO.read(imageFile);
            // 旋转
            return rotateImage(image, angle, bgColor);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 旋转图片，默认背景颜色为黑色
     *
     * @param img   原始图片
     * @param angle 旋转角度
     * @return 返回旋转之后的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage rotateImage(BufferedImage img, double angle)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return rotateImage(img, angle, DEFAULT_BGCOLOR);
    }

    /**
     * 旋转图片
     *
     * @param img     原始图片
     * @param angle   旋转角度
     * @param bgColor 背景颜色
     * @return 返回旋转之后的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage rotateImage(BufferedImage img, double angle, Color bgColor)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (null == bgColor) {
                bgColor = DEFAULT_BGCOLOR;
            }
            int width = img.getWidth();
            int height = img.getHeight();
            BufferedImage newImage;
            double[][] newPositions = new double[4][];
            newPositions[0] = calculatePosition(0, 0, angle);
            newPositions[1] = calculatePosition(width, 0, angle);
            newPositions[2] = calculatePosition(0, height, angle);
            newPositions[3] = calculatePosition(width, height, angle);
            double minX = Math.min(Math.min(newPositions[0][0], newPositions[1][0]), Math.min(newPositions[2][0], newPositions[3][0]));
            double maxX = Math.max(Math.max(newPositions[0][0], newPositions[1][0]), Math.max(newPositions[2][0], newPositions[3][0]));
            double minY = Math.min(Math.min(newPositions[0][1], newPositions[1][1]), Math.min(newPositions[2][1], newPositions[3][1]));
            double maxY = Math.max(Math.max(newPositions[0][1], newPositions[1][1]), Math.max(newPositions[2][1], newPositions[3][1]));
            int newWidth = (int) Math.round(maxX - minX);
            int newHeight = (int) Math.round(maxY - minY);
            // add，保持图片原始宽度和高度
            // 绝对值
//            double absAngle = Math.abs(angle);
            // newImage = new BufferedImageBuilder(newWidth, newHeight).build();
            newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = newImage.createGraphics();
            setRenderingHint(g);
            // 设置背景颜色
            g.setPaint(bgColor);
            g.fillRect(0, 0, newWidth, newHeight);
    /*
     * consider RenderingHints to use. The following are hints which
	 * have been chosen to give decent image quality. In the future, there
	 * may be a need to have a way to change these settings.
	 */
            double w = newWidth / 2.0;
            double h = newHeight / 2.0;
            int centerX = (int) Math.round((newWidth - width) / 2.0);
            int centerY = (int) Math.round((newHeight - height) / 2.0);
            g.rotate(Math.toRadians(angle), w, h);
            g.drawImage(img, centerX, centerY, null);
            g.dispose();
            return newImage;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 计算位置
     *
     * @param x     X坐标值
     * @param y     Y坐标值
     * @param angle 旋转角度
     * @return 返回x, y坐标值
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static double[] calculatePosition(double x, double y, double angle) {

        angle = Math.toRadians(angle);

        double nx = (Math.cos(angle) * x) - (Math.sin(angle) * y);
        double ny = (Math.sin(angle) * x) + (Math.cos(angle) * y);

        return new double[]{nx, ny};
    }

    /**
     * 创建图片
     *
     * @param img     图片
     * @param width   宽度
     * @param height  高度
     * @param bgcolor 背景颜色
     * @return 返回新的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static BufferedImage createImage(BufferedImage img, int width,
                                            int height, Color bgcolor)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            int type = BufferedImage.TYPE_INT_ARGB;
            BufferedImage newImage = new BufferedImage(width, height, type);

            Graphics2D g = newImage.createGraphics();
            setRenderingHint(g);
            if (bgcolor != null) {
                g.setPaint(bgcolor);
                g.fillRect(0, 0, width, width);
            }
            g.drawImage(img, 0, 0, width, height, null);
            g.dispose();
            return newImage;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 设置渲染提示
     *
     * @param g Graphics2D实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月21日 下午5:39:25
     */
    public static void setRenderingHint(Graphics2D g) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            Map<RenderingHints.Key, Object> m = new HashMap<RenderingHints.Key, Object>();
            m.put(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            m.put(RenderingHints.KEY_ALPHA_INTERPOLATION,
                    RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            m.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            m.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            m.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            m.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        /*
        m.put(RenderingHints. , RenderingHints.);
		*/
            g.setRenderingHints(m);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /////////////////////////  旋转图片， End ////////////////////////////


    /////////////////////////  生成图片文件， Begin ////////////////////////////

    /**
     * 生成图片文件，图片颜色质量比率默认为：0.75f
     *
     * @param bufferedImage 图片
     * @param filename      生成图片文件保存路径目标文件，如果父级目录不存在，会自动创建
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static void makeImage(BufferedImage bufferedImage, String filename)
            throws MsOnionIllegalArgumentException, MsOnionException {
        makeImage(bufferedImage, filename, DEFAULT_QUALITY);
    }

    /**
     * 生成图片文件
     *
     * @param bufferedImage 图片
     * @param filename      生成图片文件保存路径目标文件，如果父级目录不存在，会自动创建
     * @param quality       图片颜色质量比率
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static void makeImage(BufferedImage bufferedImage, String filename, float quality)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(filename)) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_IMAGE_SAVE_PATH_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
        try {
            filename = filename.trim();
            File file = new File(filename);
            makeImage(bufferedImage, file, quality);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 生成图片文件，图片颜色质量比率默认为：0.75f
     *
     * @param bufferedImage 图片
     * @param destFile      生成图片文件保存路径目标文件，如果父级目录不存在，会自动创建
     * @throws MsOnionIllegalArgumentException 非法异常
     * @throws MsOnionException                异常
     */
    public static void makeImage(BufferedImage bufferedImage, File destFile)
            throws MsOnionIllegalArgumentException, MsOnionException {
        makeImage(bufferedImage, destFile, DEFAULT_QUALITY);
    }

    /**
     * 生成图片文件
     *
     * @param bufferedImage 图片
     * @param destFile      生成图片文件保存路径目标文件，如果父级目录不存在，会自动创建
     * @param quality       图片颜色质量比率
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static void makeImage(BufferedImage bufferedImage, File destFile, float quality)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == destFile) {  // || !destFile.exists() 会自动生成路径吗？？？
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_IMAGE_SAVE_PATH_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
        try {
            // 如果父级目录还不存在，就自动创建
            if (!destFile.exists()) {
                FileUtils.forceMkdirParent(destFile);
            }
            String fileExtension = getExtension(destFile);
            if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg")
                    || fileExtension.equalsIgnoreCase("bmp")) {
                bufferedImage = copy(bufferedImage, BufferedImage.TYPE_INT_RGB);
            }

            ImageWriter imgWriter = ImageIO.getImageWritersByFormatName(fileExtension).next();
            ImageWriteParam imgWriteParam = imgWriter.getDefaultWriteParam();
            if (imgWriteParam.canWriteCompressed()) {
                if (fileExtension.equalsIgnoreCase("bmp")) {
                    imgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    imgWriteParam.setCompressionType("BI_RGB");
                } else if (fileExtension.equalsIgnoreCase("gif")) {
                    imgWriteParam.setCompressionMode(ImageWriteParam.MODE_COPY_FROM_METADATA);
                } else {
                    imgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    imgWriteParam.setCompressionQuality(quality);
                }
            }
            ImageOutputStream outputStream = ImageIO.createImageOutputStream(destFile);
            imgWriter.setOutput(outputStream);
            IIOImage outputImage = new IIOImage(bufferedImage, null, null);
            imgWriter.write(null, outputImage, imgWriteParam);
            imgWriter.dispose();
            outputStream.close();
            // 释放资源
//            MsOnionIOUtils.closeQuietly(outputStream);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * Returns a {@link BufferedImage} with the specified image type, where the
     * graphical content is a copy of the specified image.
     *
     * @param img       The image to copy.
     * @param imageType The image type for the image to return.
     * @return A copy of the specified image.
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage copy(BufferedImage img, int imageType) throws MsOnionIllegalArgumentException, MsOnionException {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, imageType);
        Graphics g = newImage.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return newImage;
    }

//    public static BufferedImage copy(BufferedImage img, int imageType, Color bgColor) {
//        int width = img.getWidth();
//        int height = img.getHeight();
//        BufferedImage newImage = new BufferedImage(width, height, imageType);
//        Graphics2D g = newImage.createGraphics();
//        // 设置背景
//        g.setBackground(bgColor);  // ?
//        g.setPaint(bgColor);  // ?
//
//        g.drawImage(img, 0, 0, null);
//        g.dispose();
//        return newImage;
//    }

    /**
     * 返回文件格式
     *
     * @param f {@link File} 文件
     * @return 返回文件格式
     */
    public static String getExtension(File f) {
        String fileName = f.getName();
        if (fileName.indexOf('.') != -1 && fileName.lastIndexOf('.') != fileName.length() - 1) {
            int lastIndex = fileName.lastIndexOf('.');
            return fileName.substring(lastIndex + 1);
        }
        return null;
    }

    /**
     * 保持图片的原比例，并计算原图片
     *
     * @param img          {@link BufferedImage} 原图片
     * @param targetWidth  {@link Integer} 目标宽度
     * @param targetHeight {@link Integer} 目标高度
     * @param bgColor      背景颜色
     * @return 返回计算结果图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    private BufferedImage keepImageRatio(BufferedImage img, int targetWidth, int targetHeight, Color bgColor)
            throws MsOnionIllegalArgumentException, MsOnionException {
        int sourceWidth = img.getWidth();
        int sourceHeight = img.getHeight();
        int x = 0;
        int y = 0;
        int drawWidth = targetWidth;
        int drawHeight = targetHeight;

        double sourceRatio = (double) sourceWidth / (double) sourceHeight;
        double targetRatio = (double) targetWidth / (double) targetHeight;

		/*
         * If the ratios are not the same, then the appropriate width and height
		 * must be picked.
		 */
        if (Double.compare(sourceRatio, targetRatio) != 0) {
            if (sourceRatio > targetRatio) {
                drawHeight = (int) Math.round(targetWidth / sourceRatio);
            } else {
                drawWidth = (int) Math.round(targetHeight * sourceRatio);
            }
        }
        x = (targetWidth - drawWidth) / 2;
        y = (targetHeight - drawHeight) / 2;
        targetWidth = (targetWidth == 0) ? 1 : targetWidth;
        targetHeight = (targetHeight == 0) ? 1 : targetHeight;
        /*
         * BufferedImage resizedImage = Utils.createImage(img, targetWidth,
		 * targetHeight, this.bgcolor);
		 */
        int type = BufferedImage.TYPE_INT_ARGB;
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, type);
        Graphics2D g = resizedImage.createGraphics();
        setRenderingHint(g);
        if (null != bgColor) {
            g.setPaint(bgColor);
            g.fillRect(0, 0, targetWidth, targetHeight);
        } else {
            g.setPaint(DEFAULT_BGCOLOR);
            g.fillRect(0, 0, targetWidth, targetHeight);
        }
        g.drawImage(img, x, y, drawWidth, drawHeight, null);
        g.dispose();

        return resizedImage;
    }

    /////////////////////////  生成图片文件， End ////////////////////////////

    /////////////////////////  将原图片生成灰度图片， Begin ////////////////////////////

    /*
     * 加权法灰度化（效果较好），但是 Otsu 不支持！！！
     *
     * 加权法：由于人眼颜色敏感度不同，按下一定的权值对RGB三分量进行加权平均能得到较合理的灰度图像。
     * 一般情况按照：Y = 0.30R + 0.59G + 0.11B。
    [注]加权法实际上是取一幅图片的亮度值（人眼对绿色的敏感最高，对蓝色敏感最低 ）作为灰度值来计算，
    用到了YUV模型。
    在[3]中会发现作者使用了Y = 0.21 * r + 0.71 * g + 0.07 * b
    （百度百科：Y = 0.30 * r + 0.59 * g + 0.11 * b）来计算灰度值。实际上，这种差别应该与是否使用伽马校正有关[1]。
     */

    /**
     * 图片灰化，采取加权法的方式
     *
     * @param imageBytes 待处理图片资源byte数组
     * @return 返回灰度化的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage grayImageWithWeighted(byte[] imageBytes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        BufferedImage bufferedImage = readImage(imageBytes);
        return grayImageWithWeighted(bufferedImage);
    }

    /**
     * 图片灰化，采取加权法的方式
     *
     * @param imageFilename 待处理图片资源文件全路径名称
     * @return 返回灰度化的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage grayImageWithWeighted(String imageFilename)
            throws MsOnionIllegalArgumentException, MsOnionException {
        BufferedImage bufferedImage = readImage(imageFilename);
        return grayImageWithWeighted(bufferedImage);
    }

    /**
     * 图片灰化，采取加权法的方式
     *
     * @param imageFile 待处理图片资源文件
     * @return 返回灰度化的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage grayImageWithWeighted(File imageFile)
            throws MsOnionIllegalArgumentException, MsOnionException {
        BufferedImage bufferedImage = readImage(imageFile);
        return grayImageWithWeighted(bufferedImage);
    }

    /**
     * 图片灰化，采取加权法的方式
     *
     * @param is 待处理图片资源输入流
     * @return 返回灰度化的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage grayImageWithWeighted(InputStream is)
            throws MsOnionIllegalArgumentException, MsOnionException {
        BufferedImage bufferedImage = readImage(is);
        return grayImageWithWeighted(bufferedImage);
    }

    /**
     * 图片灰化，采取加权法的方式
     *
     * @param bufferedImage 待处理图片
     * @return 返回灰度化的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage grayImageWithWeighted(BufferedImage bufferedImage)
            throws MsOnionIllegalArgumentException, MsOnionException {

        if (null == bufferedImage) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_IMAGE_SOURCE_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }

        try {
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            BufferedImage grayBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    // 计算灰度值
                    // 采取加权的方式
                    final int color = bufferedImage.getRGB(x, y);
                    final int r = (color >> 16) & 0xff;
                    final int g = (color >> 8) & 0xff;
                    final int b = color & 0xff;
                    int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
                    int newPixel = colorToRGB(255, gray, gray, gray);
                    grayBufferedImage.setRGB(x, y, newPixel);
                }
            }
            return grayBufferedImage;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 图片灰化，不采取加权法的方式
     *
     * @param imageBytes 待处理图片资源byte数组
     * @return 返回灰度化的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage grayImage(byte[] imageBytes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        BufferedImage bufferedImage = readImage(imageBytes);
        return grayImage(bufferedImage);
    }

    /**
     * 图片灰化，不采取加权法的方式
     *
     * @param imageFilename 待处理图片资源文件全路径名称
     * @return 返回灰度化的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage grayImage(String imageFilename)
            throws MsOnionIllegalArgumentException, MsOnionException {
        BufferedImage bufferedImage = readImage(imageFilename);
        return grayImage(bufferedImage);
    }

    /**
     * 图片灰化，不采取加权法的方式
     *
     * @param imageFile 待处理图片资源文件
     * @return 返回灰度化的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage grayImage(File imageFile)
            throws MsOnionIllegalArgumentException, MsOnionException {
        BufferedImage bufferedImage = readImage(imageFile);
        return grayImage(bufferedImage);
    }

    /**
     * 图片灰化，不采取加权法的方式
     *
     * @param is 待处理图片资源输入流
     * @return 返回灰度化的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage grayImage(InputStream is)
            throws MsOnionIllegalArgumentException, MsOnionException {
        BufferedImage bufferedImage = readImage(is);
        return grayImage(bufferedImage);
    }

    /**
     * 图片灰化，不采取加权的方式
     *
     * @param bufferedImage 待处理图片
     * @return 返回灰度化的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage grayImage(BufferedImage bufferedImage)
            throws MsOnionIllegalArgumentException, MsOnionException {

        if (null == bufferedImage) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_IMAGE_SOURCE_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }

        try {
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            BufferedImage grayBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    // 计算灰度值
                    // 采取加权的方式
//                    final int color = bufferedImage.getRGB(x, y);
//                    final int r = (color >> 16) & 0xff;
//                    final int g = (color >> 8) & 0xff;
//                    final int b = color & 0xff;
//                    int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
//                    int newPixel = colorToRGB(255, gray, gray, gray);
//                    grayBufferedImage.setRGB(x, y, newPixel);

                    // 不采取加权的方式
                    final int rgb = bufferedImage.getRGB(x, y);
                    grayBufferedImage.setRGB(x, y, rgb);
                }
            }
            return grayBufferedImage;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 颜色分量转换为RGB值
     *
     * @param alpha 透明度
     * @param red   红色
     * @param green 绿色
     * @param blue  蓝色
     * @return 返回颜色分量转换为RGB值
     */
    private static int colorToRGB(int alpha, int red, int green, int blue) {
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;
    }

    /////////////////////////  将原图片生成灰度图片， End ////////////////////////////

    /////////////////////////  图片尺寸，大，中，小， Begin ////////////////////////////


    /**
     * 获取图片动态URL，根据大中小三张图片返回值
     *
     * @param imageSizeMode  图片大小模式
     * @param smallImageUrl  小图片Url
     * @param middleImageUrl 中图片Url
     * @param bigImageUrl    大图片Url
     * @return 返回图片动态URL
     */
    public static String getImageDynamicUrl(MsOnionImageSizeMode imageSizeMode, String smallImageUrl, String middleImageUrl, String bigImageUrl) {
        // big > middle > small
        switch (imageSizeMode) {
            //  默认就是大图片
            default:
            case BIG:
                // 大图
                if (MsOnionStringUtils.isNotBlank(bigImageUrl)) {
                    return bigImageUrl;
                }
                // 大图为 null
                if (MsOnionStringUtils.isNotBlank(middleImageUrl)) {
                    return middleImageUrl;
                }
                // 大图为 null
                if (MsOnionStringUtils.isNotBlank(smallImageUrl)) {
                    return smallImageUrl;
                }
                break;
            case MIDDLE:
                // 中图
                if (MsOnionStringUtils.isNotBlank(middleImageUrl)) {
                    return middleImageUrl;
                }
                // 中图为 null
                if (MsOnionStringUtils.isNotBlank(bigImageUrl)) {
                    return bigImageUrl;
                }
                // 中图为 null
                if (MsOnionStringUtils.isNotBlank(smallImageUrl)) {
                    return smallImageUrl;
                }
                break;
            case SMALL:
                // 小图
                if (MsOnionStringUtils.isNotBlank(smallImageUrl)) {
                    return smallImageUrl;
                }
                // 小图为 null
                if (MsOnionStringUtils.isNotBlank(bigImageUrl)) {
                    return bigImageUrl;
                }
                // 小图为 null
                if (MsOnionStringUtils.isNotBlank(middleImageUrl)) {
                    return middleImageUrl;
                }
                break;
        }
        return "";
    }

    /////////////////////////  图片尺寸，大，中，小， End ////////////////////////////

    /**
     * 分割数据中图片字段
     *
     * @param prefix   图片前缀
     * @param imgFiled 图片字段
     * @return 图片地址
     */
    public static String splitImgFiled(String prefix, String imgFiled) {
        return Optional.ofNullable(imgFiled).map(s -> {
            String[] arr = imgFiled.trim().split(MsOnionConstants.IMAGE_KEY_COLON);
            if (arr.length > 1) {
                return prefix + arr[1];
            }
            return null;
        }).orElse(null);
    }


    /**
     * 横向合并图片
     * BufferedImage 实现，经测试 性能比 Graphics实现略好
     *
     * @param width       生成的图片的宽度
     * @param height      生成的图片的高度
     * @param type        1、横向合并，2、纵向合并
     * @param picFileList 待处理图片集合
     * @return 合成的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage mergePicFile(int width, int height, int type, java.util.List<File> picFileList)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (MsOnionCollectionUtils.isEmpty(picFileList)) {
                throw new MsOnionIllegalArgumentException("图片集合为空");
            }
            if (height <= 0) {
                throw new MsOnionIllegalArgumentException("合并图片高度值错误");
            }
            if ((type != MERGE_TYPE_HORIZONTAL) && (type != MERGE_TYPE_VERTICAL)) {
                throw new MsOnionIllegalArgumentException("合并图片类型错误");
            }
            BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // 遍历文件填充
            int startPoint = 0;
            for (int i = 0; i < picFileList.size(); i++) {
                File file = picFileList.get(i);
                if (!file.exists()) {
                    throw new MsOnionIllegalArgumentException("图片文件不存在");
                }
                BufferedImage imageSecond = ImageIO.read(file);
                int[] imageArraySecond = new int[imageSecond.getWidth() * imageSecond.getHeight()];
                int curWidth = imageSecond.getWidth();
                int curHeight = imageSecond.getHeight();
                imageArraySecond = imageSecond.getRGB(0, 0, curWidth, curHeight, imageArraySecond, 0, curWidth);
                if (type == MERGE_TYPE_HORIZONTAL) {
                    imageResult.setRGB(startPoint, 0, curWidth, curHeight, imageArraySecond, 0, curWidth);
                    startPoint += curWidth;
                } else if (type == MERGE_TYPE_VERTICAL) {
                    imageResult.setRGB(0, startPoint, curWidth, curHeight, imageArraySecond, 0, curWidth);
                    startPoint += curHeight;
                }
            }
            return imageResult;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 横向合并图片
     * Graphics实现
     *
     * @param width       生成的图片的宽度
     * @param height      生成的图片的高度
     * @param type        1、横向合并，2、纵向合并
     * @param picFileList 待处理图片集合
     * @return 合成的图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage mergePictureFileByGraphics(int width, int height, int type, java.util.List<File> picFileList)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (MsOnionCollectionUtils.isEmpty(picFileList)) {
                throw new MsOnionIllegalArgumentException("图片集合为空");
            }
            if (height <= 0) {
                throw new MsOnionIllegalArgumentException("合并图片高度值错误");
            }
            if ((type != MERGE_TYPE_HORIZONTAL) && (type != MERGE_TYPE_VERTICAL)) {
                throw new MsOnionIllegalArgumentException("合并图片类型错误");
            }
            BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // 遍历文件填充
            int startPoint = 0;
            for (int i = 0; i < picFileList.size(); i++) {
                File file = picFileList.get(i);
                if (!file.exists()) {
                    throw new MsOnionIllegalArgumentException("图片文件不存在");
                }
                Graphics g = imageResult.getGraphics();
                BufferedImage image = ImageIO.read(file);
                int curWidth = image.getWidth();
                int curHeight = image.getHeight();
                if (type == MERGE_TYPE_HORIZONTAL) {
                    g.drawImage(image, startPoint, 0, null);
                    startPoint += curWidth;
                } else if (type == MERGE_TYPE_VERTICAL) {
                    g.drawImage(image, 0, startPoint, null);
                    startPoint += curWidth;
                }
            }
            return imageResult;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 生成验证码并返回值
     *
     * @param picSize     图片大小
     * @param picFileList 图片集合
     * @param codeValue   验证码的值
     * @return 验证码图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage genVerifyCodePictureFile(int picSize, java.util.List<File> picFileList, int[] codeValue)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (MsOnionCollectionUtils.isEmpty(picFileList)) {
                throw new MsOnionIllegalArgumentException("图片集合为空");
            }
            if (picFileList.size() != codeValue.length) {
                throw new MsOnionIllegalArgumentException("值的长度和图片集合不等，不符合生成规则");
            }
            int iLength = picSize * picFileList.size();
            BufferedImage imageResult = new BufferedImage(iLength, iLength, BufferedImage.TYPE_INT_RGB);
            // 遍历文件填充
            int startYPoint = 0;
            int startXPoint = 0;
            for (int i = 0; i < picFileList.size(); i++) {
                startYPoint = 0;
                File file = picFileList.get(i);
                if (!file.exists()) {
                    throw new MsOnionIllegalArgumentException("图片文件不存在");
                }
                BufferedImage bufferedImage = ImageIO.read(file);
                int iWidth = bufferedImage.getWidth();
                int iHeight = bufferedImage.getHeight();
                if (iWidth != iHeight) {
                    throw new MsOnionIllegalArgumentException("图片宽和高不等，不符合生成规则");
                }
                // 旋转，纵向合并
                int[] randomArr = getCodeRandom(i, codeValue.length - 1);
                for (int j = 0; j < randomArr.length; j++) {
                    int jValue = randomArr[j];
                    if (jValue == 0) {
                        codeValue[i] = j;
                    }
                    BufferedImage rotateBufferedImage = rotateImage(bufferedImage, MsOnionImageConstants.IMAGE_ROTATE_RIGHT_90 * jValue);
                    int curWidth = rotateBufferedImage.getWidth();
                    int curHeight = rotateBufferedImage.getHeight();
                    int[] imageArraySecond = new int[curHeight * curWidth];
                    imageArraySecond = rotateBufferedImage.getRGB(0, 0, curWidth, curHeight, imageArraySecond, 0, curWidth);
                    imageResult.setRGB(startXPoint, startYPoint, curWidth, curHeight, imageArraySecond, 0, curWidth);
                    startYPoint += curHeight;
                    if (j == (randomArr.length - 1)) {
                        startXPoint += curWidth;
                    }
                }
            }
            return imageResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MsOnionException(e);
        }
    }

    /**
     * 生成验证码并返回值
     *
     * @param picSize     图片大小
     * @param ring 图片旋转一周的次数
     * @param picFileList 图片集合
     * @param codeValue   验证码的值
     * @return 验证码图片
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    public static BufferedImage genVerifyCodeBufferedImage(int picSize, int ring,  java.util.List<BufferedImage> picFileList, int[] codeValue)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (MsOnionCollectionUtils.isEmpty(picFileList)) {
                throw new MsOnionIllegalArgumentException("图片集合为空");
            }
            if (picFileList.size() != codeValue.length) {
                throw new MsOnionIllegalArgumentException("值的长度和图片集合不等，不符合生成规则");
            }
            int iAllWidth = picSize * picFileList.size();
            int iAllHeight = picSize * ring;
            BufferedImage imageResult = new BufferedImage(iAllWidth, iAllHeight, BufferedImage.TYPE_INT_RGB);
            // 遍历文件填充
            int startYPoint = 0;
            int startXPoint = 0;
            for (int i = 0; i < picFileList.size(); i++) {
                startYPoint = 0;
                BufferedImage bufferedImage = picFileList.get(i);
                if (bufferedImage == null) {
                    throw new MsOnionIllegalArgumentException("图片文件不存在");
                }
                int iWidth = bufferedImage.getWidth();
                int iHeight = bufferedImage.getHeight();
                if (iWidth != iHeight) {
                    throw new MsOnionIllegalArgumentException("图片宽和高不等，不符合生成规则");
                }
                // 旋转，纵向合并
                int[] randomArr = getCodeRandom(i, codeValue.length - 1);
                for (int j = 0; j < randomArr.length; j++) {
                    int jValue = randomArr[j];
                    if (jValue == 0) {
                        codeValue[i] = j;
                    }
                    BufferedImage rotateBufferedImage = rotateImage(bufferedImage, MsOnionImageConstants.IMAGE_ROTATE_RIGHT_90 * jValue);
                    int curWidth = rotateBufferedImage.getWidth();
                    int curHeight = rotateBufferedImage.getHeight();
                    int[] imageArraySecond = new int[curHeight * curWidth];
                    imageArraySecond = rotateBufferedImage.getRGB(0, 0, curWidth, curHeight, imageArraySecond, 0, curWidth);
                    imageResult.setRGB(startXPoint, startYPoint, curWidth, curHeight, imageArraySecond, 0, curWidth);
                    startYPoint += curHeight;
                    if (j == (randomArr.length - 1)) {
                        startXPoint += curWidth;
                    }
                }
            }
            return imageResult;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 图片随机旋转
     * @param cursor 当前指针
     * @param max 最大长度
     * @return
     */
    public static int[] getCodeRandom(int cursor, int max) {
        final int[] codeArr = new int[]{0, 1, 2, 3};
        int[] returnArr = MsOnionRandomUtils.shuffle(codeArr, codeArr.length);
        if ((cursor == 0 && returnArr[0] == 0)
                || (cursor == max && returnArr[0] == 0)) {
            return getCodeRandom(cursor, max);
        }
        return returnArr;
    }



}
