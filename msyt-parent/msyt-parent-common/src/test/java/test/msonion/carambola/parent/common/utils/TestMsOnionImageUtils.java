package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.image.MsOnionImageUtils;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by HeroCao on 2017/4/21.
 */
public class TestMsOnionImageUtils {

    @Test
    public void test01() throws MsOnionException {

        String filename = "C:/Users/HeroCao/Desktop/退款金额-20170328185524.png";

//        String destFilename = "C:/Users/HeroCao/Desktop/退款金额-01.png";


        String destFilename = "C:\\Users\\HeroCao\\Downloads\\HeroCao\\Code\\图片处理相关实现\\11\\22\\33\\退款金额18.jpg";

//        double angle = 30.0d;

//        double angle = -30.0d;

//        double angle = -90.0d;

//        double angle = 90.0d;

//        double angle = 0.0d;

//        double angle = -180.0d;

        double angle = 180.0d;

        // 拍照的图片，和原始的图片不同 ！！！

        // -90 之后，高度 和 宽度 还是按照之前的，那么图片会裁切变小 ！！！
        BufferedImage bufferedImage = MsOnionImageUtils.rotateImage(filename, angle, Color.WHITE);

//        BufferedImage bufferedImage = MsOnionImageUtils.rotateImage(filename, angle, Color.ORANGE);

//        BufferedImage bufferedImage = MsOnionImageUtils.rotateImage(filename, angle, Color.ORANGE);

        MsOnionImageUtils.makeImage(bufferedImage, destFilename, 0.50f);

        System.out.println("生成图片完成 。。。");

    }

}
