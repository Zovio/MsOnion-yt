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


package cc.msonion.carambola.parent.pojo;

import cc.msonion.carambola.parent.common.exception.MsOnionException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

/**
 * @Title: MsOnionVerifyCode.java
 * @Package: cc.msonion.carambola.parent.pojo
 * @Description: 验证码
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月25日 下午1:25:39
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月25日 下午1:25:39
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：所有的验证码相关功能
 */

/**
 * 验证码
 *
 * @ClassName: VerifyCode
 * @Description: 验证码
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月25日 下午1:25:39
 */
public class MsOnionVerifyCode {

    /**
     * 图片的宽度
     */
    private int width = 160;
    /**
     * 图片的高度
     */
    private int height = 40;
    /**
     * 验证码字符个数
     */
    private int codeCount = 5;
    /**
     * 验证码干扰线数
     */
    private int lineCount = 25; // 30
    /**
     * 验证码
     */
    private String code = null;
    /**
     * 验证码图片Buffer
     */
    private BufferedImage buffImg = null;

    /**
     * 随机实例对象
     */
    private Random random = new Random();

    /**
     * 生成验证码，默认验证码背景区域宽度：160，高度：40，验证码个数：5，验证码干扰线：30条
     */
    public MsOnionVerifyCode() {
        creatImage();
    }

    /**
     * @param width  验证码背景区域宽度，建议：160
     * @param height 验证码背景区域高度，建议：40
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月25日 下午1:25:39
     */
    public MsOnionVerifyCode(int width, int height) {
        this.width = width;
        this.height = height;
        creatImage();
    }

    /**
     * @param width     验证码背景区域宽度，建议：160
     * @param height    验证码背景区域高度，建议：40
     * @param codeCount 验证码数量，建议：5
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月25日 下午1:25:39
     */
    public MsOnionVerifyCode(int width, int height, int codeCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        creatImage();
    }

    /**
     * @param width     验证码背景区域宽度，建议：160
     * @param height    验证码背景区域高度，建议：40
     * @param codeCount 验证码数量，建议：5
     * @param lineCount 干扰线条数量，建议：30，不要太多，也不要太少
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月25日 下午1:25:39
     */
    public MsOnionVerifyCode(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        creatImage();
    }

    /**
     * @Title: creatImage
     * @Description: 生成图片
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月25日 下午1:47:01
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月25日 下午1:25:39
     */
    private void creatImage() {
        int fontWidth = width / codeCount; // 字体的宽度
        int fontHeight = height - 5; // 字体的高度
        int codeY = height - 8;

        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.getGraphics();
        // Graphics2D g = buffImg.createGraphics();
        // 设置背景色
        // 为了扁平化，去掉背景颜色
//        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        // 设置字体
        // Font font1 = getFont(fontHeight);
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        g.setFont(font);

        // 设置干扰线
        for (int i = 0; i < lineCount; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width);
            int ye = ys + random.nextInt(height);
            g.setColor(getRandColor(1, 255));
            g.drawLine(xs, ys, xe, ye);
        }

        // 添加噪点
        float yawpRate = 0.01f; // 噪声率
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            buffImg.setRGB(x, y, random.nextInt(255));
        }

        String str1 = randomStr(codeCount); // 得到随机字符
        this.code = str1;
        for (int i = 0; i < codeCount; i++) {
            String strRand = str1.substring(i, i + 1);
            g.setColor(getRandColor(1, 255));
            // g.drawString(a,x,y);
            // a为要画出来的东西，x和y表示要画的东西最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处

            g.drawString(strRand, i * fontWidth + 3, codeY);
        }
    }

    /**
     * 随机字符
     *
     * @param n 多少个随机字符
     * @return 返回字符串
     * @Description: 得到随机字符
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月25日 下午1:43:00
     */
    private String randomStr(int n) {
//        String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        // 去掉 I 和 l，因为很难辨别 , 去掉 O 和 o
        String str1 = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }

    /**
     * @param fc
     * @param bc
     * @return
     * @Title: getRandColor
     * @Description: 得到随机颜色
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月25日 下午1:42:40
     */
    private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 产生随机字体
     * @param size 字体大小
     * @return
     * @Title: getFont
     * @Description: 产生随机字体
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月25日 下午1:42:21
     */
    private Font getFont(int size) {
        Random random = new Random();
        Font font[] = new Font[5];
        font[0] = new Font("Ravie", Font.PLAIN, size);
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
        font[2] = new Font("Fixedsys", Font.PLAIN, size);
        font[3] = new Font("Wide Latin", Font.PLAIN, size);
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
        return font[random.nextInt(5)];
    }

    /**
     * 扭曲方法
     * @param g Graphics实例对象
     * @param w1 宽度
     * @param h1 高度
     * @param color 颜色
     * @Description: 扭曲方法
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月25日 下午1:42:03
     */
    private void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    /**
     * shearX
     * @param g Graphics实例对象
     * @param w1 宽度
     * @param h1 高度
     * @param color 颜色
     */
    private void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }
    }

    /**
     * shearY
     * @param g Graphics实例对象
     * @param w1 宽度
     * @param h1 高度
     * @param color 颜色
     */
    private void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }
        }
    }

    /**
     * 写入
     *
     * @param sos 输出流
     * @throws MsOnionException 异常
     */
    public void write(OutputStream sos) throws MsOnionException {
        try {
            ImageIO.write(buffImg, "png", sos);
            sos.close();
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取BufferedImage
     *
     * @return 返回BufferedImage实例对象
     */
    public BufferedImage getBuffImg() {
        return buffImg;
    }

    /**
     * 得到验证码
     *
     * @return 返回验证码
     */
    public String getCode() {
        return code.toLowerCase();
    }

}
