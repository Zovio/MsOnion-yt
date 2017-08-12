package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.constants.MsOnionImageConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.image.MsOnionImageUtils;
import cc.msonion.carambola.parent.common.utils.image.MsOnionSimilarImageSearchUtils;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeroCao on 2017/4/21.
 */
public class TestMsOnionSimilarImageSearchUtils {

    @Test
    public void test01() throws MsOnionException {

//        MsOnionSimilarImageSearchUtils.

        List<String> hashCodes = new ArrayList<String>();

        // add by Hero @ 2017-04-21
        String filename = "C:/Users/HeroCao/Downloads/HeroCao/Code/图片处理相关实现/11/22/33/";


        String hashCode = null;

        // 应该提供一张图片， 4个方向旋转的得到的值 ！！！
        // 正常 Normal ：就是上传的图片，不管方向怎么样，都是以这个为基础，后面旋转
        // 左旋转90: Rotate left 90
        // 右旋转90：Rotate right 90
        // 旋转180：Rotate 180
        // 垂直翻转： Flip vertical
        // 水平翻转：Horizontal flip
        // // 左旋转45: Rotate left 45
        // 右旋转90：Rotate right 90


        // 15、30、45、60、75、90
        //
        // 14 个字段

        // idx , item_idx , filename , url , createTime , updateTime ,
        // createByMemberIdx , updateByMemberIdx , status
        // remark ， ext

	    /*
	     *
	     * 7f7f004f7f7f7c7f
	     *
	     * 7f7f004f7f7f787f
	     *
	     * 7f7f004f7f7f7c7f
	     *
	     * 缩略图 ，或者缩小图
	     *
	     * 还要提供算法，只要后面3位中，任意2位相同就可以 ！！！
	     *
	     *  generateImageFingerprint
	     *
	     */


//	    for (int i = 0; i < 10; i++)

        for (int i = 0; i < 20; i++) {
            // 原始的
//		    hashCode = produceFingerPrint(filename + "example" + (i + 1) + ".jpg");

            // add by Hero @ 2017-04-21
            // // add by Hero @ 2017-04-21
            hashCode = MsOnionSimilarImageSearchUtils.generateImageFingerprint(filename + "退款金额" + (i + 1) + ".jpg");

            hashCodes.add(hashCode);

            // i=6，hashCode=7f7f004f7f7f7c7f，hashCode.length()=16
            System.out.println("i=" + i + "，hashCode=" + hashCode + "，hashCode.length()=" + hashCode.length());
        }
        System.out.println("Resources: ");
        System.out.println(hashCodes);
        System.out.println();

        String sourceHashCode = MsOnionSimilarImageSearchUtils.generateImageFingerprint(filename + "source.jpg");
        System.out.println("Source: ");
        System.out.println(sourceHashCode);
        System.out.println();

        List<Integer> differences = new ArrayList<Integer>();
        for (int i = 0; i < hashCodes.size(); i++) {
            int difference = MsOnionSimilarImageSearchUtils.hammingDistance(sourceHashCode, hashCodes.get(i));
            differences.add(difference);

            boolean isSimilarImage = MsOnionSimilarImageSearchUtils.isSimilarImage(sourceHashCode, hashCodes.get(i));

            System.out.println("sourceHashCode=" + sourceHashCode + "，hashCodes.get(i)=" + hashCodes.get(i) + "，i=" + i
                    + "，isSimilarImage=" + isSimilarImage);
        }

        System.out.println("differences=" + differences);


        /**
         *
         i=0，hashCode=70f8ffffff7f3f0f，hashCode.length()=16
         i=1，hashCode=0f3f7ffffffffc78，hashCode.length()=16
         i=2，hashCode=ffff00ffffffffff，hashCode.length()=16
         i=3，hashCode=ffffffffff00ffff，hashCode.length()=16
         i=4，hashCode=ff38bcbcbcbcbcfc，hashCode.length()=16
         i=5，hashCode=ffffffffff00ffff，hashCode.length()=16
         i=6，hashCode=ffffffffff00ffff，hashCode.length()=16
         i=7，hashCode=0078cc7c0f3f0f08，hashCode.length()=16
         i=8，hashCode=080f3f0f7cfc7800，hashCode.length()=16
         i=9，hashCode=bf80bfffffff80bf，hashCode.length()=16
         i=10，hashCode=fc80fffffffc00fc，hashCode.length()=16
         i=11，hashCode=bfbcbcbcbcbc0cff，hashCode.length()=16
         i=12，hashCode=bfbcbcbcbcbc0cff，hashCode.length()=16
         i=13，hashCode=bfbcbcbcbcbc08ff，hashCode.length()=16
         i=14，hashCode=bfbcbcbcbcbc08ff，hashCode.length()=16
         i=15，hashCode=bfbcbcbcbcbc08ff，hashCode.length()=16
         i=16，hashCode=bfbcbcbcbcbc08ff，hashCode.length()=16
         i=17，hashCode=bfbcbcbcbcbc08ff，hashCode.length()=16
         i=18，hashCode=ffcfffc7fbf3f3ff，hashCode.length()=16
         i=19，hashCode=0307380f0403030f，hashCode.length()=16
         Resources:
         [70f8ffffff7f3f0f, 0f3f7ffffffffc78, ffff00ffffffffff, ffffffffff00ffff, ff38bcbcbcbcbcfc, ffffffffff00ffff, ffffffffff00ffff, 0078cc7c0f3f0f08, 080f3f0f7cfc7800, bf80bfffffff80bf, fc80fffffffc00fc, bfbcbcbcbcbc0cff, bfbcbcbcbcbc0cff, bfbcbcbcbcbc08ff, bfbcbcbcbcbc08ff, bfbcbcbcbcbc08ff, bfbcbcbcbcbc08ff, bfbcbcbcbcbc08ff, ffcfffc7fbf3f3ff, 0307380f0403030f]

         Source:
         ff38bcbcbcbcbcfc

         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=70f8ffffff7f3f0f，i=0，isSimilarImage=false
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=0f3f7ffffffffc78，i=1，isSimilarImage=false
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=ffff00ffffffffff，i=2，isSimilarImage=false
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=ffffffffff00ffff，i=3，isSimilarImage=false
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=ff38bcbcbcbcbcfc，i=4，isSimilarImage=true
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=ffffffffff00ffff，i=5，isSimilarImage=false
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=ffffffffff00ffff，i=6，isSimilarImage=false
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=0078cc7c0f3f0f08，i=7，isSimilarImage=false
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=080f3f0f7cfc7800，i=8，isSimilarImage=false
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=bf80bfffffff80bf，i=9，isSimilarImage=false
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=fc80fffffffc00fc，i=10，isSimilarImage=false
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=bfbcbcbcbcbc0cff，i=11，isSimilarImage=true
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=bfbcbcbcbcbc0cff，i=12，isSimilarImage=true
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=bfbcbcbcbcbc08ff，i=13，isSimilarImage=true
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=bfbcbcbcbcbc08ff，i=14，isSimilarImage=true
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=bfbcbcbcbcbc08ff，i=15，isSimilarImage=true
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=bfbcbcbcbcbc08ff，i=16，isSimilarImage=true
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=bfbcbcbcbcbc08ff，i=17，isSimilarImage=true
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=ffcfffc7fbf3f3ff，i=18，isSimilarImage=false
         sourceHashCode=ff38bcbcbcbcbcfc，hashCodes.get(i)=0307380f0403030f，i=19，isSimilarImage=false
         differences=[15, 13, 13, 13, 0, 13, 13, 13, 14, 14, 12, 5, 5, 6, 6, 6, 6, 6, 13, 16]
         */
    }

    @Test
    public void test02() throws MsOnionException {

//        String filename = "C:/Users/HeroCao/Downloads/HeroCao/Code/图片处理相关实现/11/22/55/";

        String filename = "C:/Users/HeroCao/Downloads/HeroCao/Code/图片处理相关实现/11/22/99/";

//        MsOnionImageUtils.rotateImage()

        String imageFilename = "C:\\Users\\HeroCao\\Desktop\\图片\\iPhone7\\iPhone7.jpg";

//        for (double angle : MsOnionImageConstants.IMAGE_ROTATE_ANGLES) {

          for (int i = 0; i < MsOnionImageConstants.IMAGE_ROTATE_ANGLES.length; i++) {

              double angle = MsOnionImageConstants.IMAGE_ROTATE_ANGLES[i];

            BufferedImage bufferedImage = MsOnionImageUtils.rotateImage(imageFilename, angle);

            MsOnionImageUtils.makeImage(bufferedImage, String.format("%siPhone7_%s.jpg", filename, i));
        }

        System.out.println("已经执行完成 。。。");

    }

    @Test
    public void test05() throws MsOnionException {

        // 生成 各个角度的图片

        String filename = "C:/Users/HeroCao/Downloads/HeroCao/Code/图片处理相关实现/11/22/99/";

//        MsOnionImageUtils.rotateImage()

//        String imageFilename = "C:\\Users\\HeroCao\\Desktop\\图片\\iPhone7\\iPhone7.jpg";

//        String imageFilename = "C:\\Users\\HeroCao\\Desktop\\图片\\鼠标\\sb.jpg";

        String imageFilename = "C:/Users/HeroCao/Downloads/HeroCao/Code/图片处理相关实现/11/22/99/sb_gray.png";

//        for (double angle : MsOnionImageConstants.IMAGE_ROTATE_ANGLES) {

        for (int i = 0; i < MsOnionImageConstants.IMAGE_ROTATE_ANGLES.length; i++) {

            double angle = MsOnionImageConstants.IMAGE_ROTATE_ANGLES[i];

            BufferedImage bufferedImage = MsOnionImageUtils.rotateImage(imageFilename, angle, Color.WHITE);

            MsOnionImageUtils.makeImage(bufferedImage, String.format("%ssb_gray_rotate_%s.jpg", filename, i));
        }

        System.out.println("已经执行完成 。。。");

    }

    @Test
    public void test03() throws MsOnionException {

        // 实现搜索，相似图片，根据图片指纹

        System.out.println("实现搜索，相似图片，根据图片指纹  # Beginning  。。。");

//        MsOnionSimilarImageSearchUtils.

        List<String> hashCodes = new ArrayList<String>();

        // add by Hero @ 2017-04-21
//        String filename = "C:/Users/HeroCao/Downloads/HeroCao/Code/图片处理相关实现/11/22/33/";


//        String filename = "C:/Users/HeroCao/Downloads/HeroCao/Code/图片处理相关实现/11/22/66/";


        String filename = "C:/Users/HeroCao/Downloads/HeroCao/Code/图片处理相关实现/11/22/88/";

        String hashCode = null;

        // 应该提供一张图片， 4个方向旋转的得到的值 ！！！
        // 正常 Normal ：就是上传的图片，不管方向怎么样，都是以这个为基础，后面旋转
        // 左旋转90: Rotate left 90
        // 右旋转90：Rotate right 90
        // 旋转180：Rotate 180
        // 垂直翻转： Flip vertical
        // 水平翻转：Horizontal flip
        // // 左旋转45: Rotate left 45
        // 右旋转90：Rotate right 90


        // 15、30、45、60、75、90
        //
        // 14 个字段

        // idx , item_idx , filename , url , createTime , updateTime ,
        // createByMemberIdx , updateByMemberIdx , status
        // remark ， ext

	    /*
	     *
	     * 7f7f004f7f7f7c7f
	     *
	     * 7f7f004f7f7f787f
	     *
	     * 7f7f004f7f7f7c7f
	     *
	     * 缩略图 ，或者缩小图
	     *
	     * 还要提供算法，只要后面3位中，任意2位相同就可以 ！！！
	     *
	     *  generateImageFingerprint
	     *
	     */


//	    for (int i = 0; i < 10; i++)

        for (int i = 0; i < 23; i++) {
            // 原始的
//		    hashCode = produceFingerPrint(filename + "example" + (i + 1) + ".jpg");

            // add by Hero @ 2017-04-21
            // // add by Hero @ 2017-04-21
            hashCode = MsOnionSimilarImageSearchUtils.generateImageFingerprint(filename + "iPhone7_" + i + ".jpg");

//            hashCode = MsOnionSimilarImageSearchUtils.generateImageFingerprint(filename + "sb_gray_rotate_" + i + ".jpg");

//            hashCode = MsOnionSimilarImageSearchUtils.generateImageFingerprint(filename + "sb_" + i + ".jpg");

            hashCodes.add(hashCode);

            // i=6，hashCode=7f7f004f7f7f7c7f，hashCode.length()=16
            System.out.println("i=" + i + "，hashCode=" + hashCode + "，hashCode.length()=" + hashCode.length());
        }
        System.out.println("Resources: ");
        System.out.println(hashCodes);
        System.out.println();

        // TODO: 所有搜索的图片，都将背景设置为白色
        // 当前搜索图片
        String sourceHashCode = MsOnionSimilarImageSearchUtils.generateImageFingerprint(filename + "source.jpg");

//        String sourceHashCode = MsOnionSimilarImageSearchUtils.generateImageFingerprint(filename + "source6.jpg");

        // differences=[8, 9, 7, 7, 8, 7, 10, 7, 8, 7, 8, 9, 9, 8, 9, 10, 9, 13, 9, 9, 9, 8, 9]
        // 5 才可以 ！！！
//        String sourceHashCode = MsOnionSimilarImageSearchUtils.generateImageFingerprint(filename + "source2.jpg");

//        String sourceHashCode = MsOnionSimilarImageSearchUtils.generateImageFingerprint(filename + "source.jpg");


//        String sourceHashCode = MsOnionSimilarImageSearchUtils.generateImageFingerprint(filename + "source2.png");

//        MsOnionImageUtils.rotateImage()

        BufferedImage bufferedImage = null;




        // differences=[9, 9, 7, 7, 7, 6, 9, 6, 8, 7, 7, 8, 8, 7, 8, 9, 7, 12, 8, 8, 8, 7, 8]

//        String sourceHashCode = MsOnionSimilarImageSearchUtils.generateImageFingerprint(filename + "source3.jpg");

        // differences=[8, 9, 7, 7, 8, 7, 10, 7, 8, 7, 8, 9, 9, 8, 9, 10, 9, 13, 9, 9, 9, 8, 9]
//        String sourceHashCode = MsOnionSimilarImageSearchUtils.generateImageFingerprint(filename + "source4.jpg");

        System.out.println("Source: ");
        System.out.println(sourceHashCode);
        System.out.println();

        List<Integer> differences = new ArrayList<Integer>();
        for (int i = 0; i < hashCodes.size(); i++) {
            int difference = MsOnionSimilarImageSearchUtils.hammingDistance(sourceHashCode, hashCodes.get(i));
            differences.add(difference);

            boolean isSimilarImage = MsOnionSimilarImageSearchUtils.isSimilarImage(sourceHashCode, hashCodes.get(i));

            System.out.println("sourceHashCode=" + sourceHashCode + "，hashCodes.get(i)=" + hashCodes.get(i) + "，i=" + i
                    + "，isSimilarImage=" + isSimilarImage);
        }

        System.out.println("differences=" + differences);

        System.out.println("执行 完成 test03 ## 。。。");

        System.out.println("实现搜索，相似图片，根据图片指纹  # End !!!");

    }

    @Test
    public void test6() throws MsOnionException {


        // 去掉背景颜色

        String imageFilename = "C:\\Users\\HeroCao\\Downloads\\HeroCao\\Code\\图片处理相关实现\\11\\22\\88\\source2.jpg";


        String imageFilename2 = "C:\\Users\\HeroCao\\Downloads\\HeroCao\\Code\\图片处理相关实现\\11\\22\\88\\source5.jpg";

        BufferedImage bufferedImage = MsOnionImageUtils.rotateImage(imageFilename, 180, Color.WHITE);

        MsOnionImageUtils.makeImage(bufferedImage, imageFilename2);


        System.out.println("完成 。。。");
    }

    /*


		for (int i = 0; i < hashCodes.size(); i++)
        {
		    int difference = hammingDistance(sourceHashCode, hashCodes.get(i));
		    System.out.print("汉明距离:"+difference+"     ");
		    if(difference==0){
		    	System.out.println("source.jpg图片跟example"+(i+1)+".jpg一样");
		    }else if(difference<=5){
		    	System.out.println("source.jpg图片跟example"+(i+1)+".jpg非常相似");
		    }else if(difference<=10){
		    	System.out.println("source.jpg图片跟example"+(i+1)+".jpg有点相似");
		    }else if(difference>10){
		    	System.out.println("source.jpg图片跟example"+(i+1)+".jpg完全不一样");
		    }
        }


        https://github.com/yaoliangdong/SimilarImageSearch/blob/master/SimilarImageSearch/src/com/test/image/SimilarImageSearch.java


     */

    @Test
    public void testMakeGrayImage01() throws MsOnionException {

        System.out.println("执行生成灰度化图片，Begin 。。。");

        // 生成灰度化图片

//        String imageFilename = "";

//        String imageFilename = "C:/Users/HeroCao/Downloads/HeroCao/Code/相似图片搜索/otsuDemo/images/sb.jpg";


        String imageFilename = "C:/Users/HeroCao/Downloads/HeroCao/Code/相似图片搜索/otsuDemo/images/sb.jpg";


        String destFilename = "C:/Users/HeroCao/Downloads/HeroCao/Code/相似图片搜索/otsuDemo/images/sb_gray.jpg";

        // source2_gray_1.jpg

        BufferedImage bufferedImage = MsOnionImageUtils.grayImageWithWeighted(imageFilename);

        MsOnionImageUtils.makeImage(bufferedImage, destFilename);


        System.out.println("执行生成灰度化图片，完成 ！！！");

    }


}
