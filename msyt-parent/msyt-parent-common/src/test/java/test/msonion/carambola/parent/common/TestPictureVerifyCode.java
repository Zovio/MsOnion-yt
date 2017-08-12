package test.msonion.carambola.parent.common;

import cc.msonion.carambola.parent.common.constants.MsOnionImageConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionOkHttp3Utils;
import cc.msonion.carambola.parent.common.utils.MsOnionRandomUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.image.MsOnionImageUtils;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestPictureVerifyCode {



    /**
     * 测试横向合并图片
     */
    @Test
    public void test() throws MsOnionException, Exception {
        long beginTime=System.currentTimeMillis();
        File file1 = new File("E:\\test\\ssd2_13.jpg");
        File file2 = new File("E:\\test\\ssd2_14.jpg");
        File file3 = new File("E:\\test\\ssd2_15.jpg");
        File file4 = new File("E:\\test\\ssd2_16.jpg");
        List<File> fileList=new ArrayList<File>();
        fileList.add(file1);
        fileList.add(file2);
        fileList.add(file3);
        fileList.add(file4);
        File outFile = new File("E:\\test\\merge.jpg");
        BufferedImage image = MsOnionImageUtils.mergePicFile(68, 272,2, fileList);
        ImageIO.write(image, "jpg", outFile);
        long endTime=System.currentTimeMillis();
        System.out.println(("==================="+(endTime-beginTime)/1000.0));
    }

    /**
     * 测试横向合并图片，Graphics实现
     */
    @Test
    public void test2() throws MsOnionException, Exception {
        long beginTime=System.currentTimeMillis();
        File file1 = new File("E:\\test\\ssd2_13.jpg");
        File file2 = new File("E:\\test\\ssd2_14.jpg");
        File file3 = new File("E:\\test\\ssd2_15.jpg");
        File file4 = new File("E:\\test\\ssd2_16.jpg");
        List<File> fileList=new ArrayList<File>();
        fileList.add(file1);
        fileList.add(file2);
        fileList.add(file3);
        fileList.add(file4);
        File outFile = new File("E:\\test\\merge2.jpg");
        BufferedImage image = MsOnionImageUtils.mergePictureFileByGraphics(68, 272,2, fileList);
        ImageIO.write(image, "jpg", outFile);
        long endTime=System.currentTimeMillis();
        System.out.println(("==================="+(endTime-beginTime)/1000.0));
    }

    @Test
    public void testRandomValue() throws MsOnionException, Exception {
//        int[] value=getCodeRandom();
//        StringBuffer sb=new StringBuffer();
//        for(int i=0;i<value.length;i++){
//            System.out.println(value[i]);
//            sb.append(value[i]);
//        }
//        System.out.println(sb.toString());



            // 测试第一组的第一个值和最后一组的第一个值不能为0
            for(int i=0;i<500;i++){
                for(int j=0;j<4;j++) {
                    int[] arr = MsOnionImageUtils.getCodeRandom(j, 3);
                    System.out.println(arr[0]+"=="+arr[1]+"=="+arr[2]+"=="+arr[3]);
                }
                System.out.println("---------------------");
            }

    }

    @Test
    public void testRotateImage() throws MsOnionException, Exception {
        long beginTime=System.currentTimeMillis();
        File file1 = new File("E:\\test\\ssd2_13.jpg");
        File file2 = new File("E:\\test\\ssd2_14.jpg");
        File file3 = new File("E:\\test\\ssd2_15.jpg");
        File file4 = new File("E:\\test\\ssd2_16.jpg");
        List<File> fileList=new ArrayList<File>();
        fileList.add(file1);
        fileList.add(file2);
        fileList.add(file3);
        fileList.add(file4);
        File outFile = new File("E:\\test\\mergeGenRotate.jpg");
        int[] codeValue= new int[4];
//        BufferedImage image = MsOnionImageUtils.genVerifyCodePictureFile(68,fileList,codeValue);
        BufferedImage rotateBufferedImage = MsOnionImageUtils.rotateImage(ImageIO.read(file1), MsOnionImageConstants.IMAGE_ROTATE_RIGHT_90 );
        ImageIO.write(rotateBufferedImage, "jpg", outFile);
        long endTime=System.currentTimeMillis();
        System.out.println(("==================="+(endTime-beginTime)/1000.0));
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<codeValue.length;i++){
            sb.append(codeValue[i]);
        }
        System.out.println(sb.toString());



    }

    @Test
    public void testGenPic() throws MsOnionException, Exception {
        long beginTime=System.currentTimeMillis();
        File file1 = new File("E:\\test\\ssd2_13.jpg");
        File file2 = new File("E:\\test\\ssd2_14.jpg");
        File file3 = new File("E:\\test\\ssd2_15.jpg");
        File file4 = new File("E:\\test\\ssd2_16.jpg");
        List<File> fileList=new ArrayList<File>();
        fileList.add(file1);
        fileList.add(file2);
        fileList.add(file3);
        fileList.add(file4);
        File outFile = new File("E:\\test\\mergeGen.jpg");
        int[] codeValue= new int[4];
        BufferedImage image = MsOnionImageUtils.genVerifyCodePictureFile(68,fileList,codeValue);
        ImageIO.write(image, "jpg", outFile);
        long endTime=System.currentTimeMillis();
        System.out.println(("==================="+(endTime-beginTime)/1000.0));
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<codeValue.length;i++){
            sb.append(codeValue[i]);
        }
        System.out.println(sb.toString());
    }



    private  int[] getCodeRandom(){
        int[] codeArr = new int[]{0, 1, 2, 3};

        codeArr= MsOnionRandomUtils.shuffle(codeArr, 4);
        return codeArr;
    }




    @Test
    public void testUrlGenPic() throws MsOnionException, Exception {
        long beginTime=System.currentTimeMillis();
        String s1="http://yangtaodev-1253852034.cosgz.myqcloud.com/verifyCode/20170720/20170720135153251_303.jpg";
        String s2="http://yangtaodev-1253852034.cosgz.myqcloud.com/verifyCode/20170720/20170720135144479_584.jpg";
        String s3="http://yangtaodev-1253852034.cosgz.myqcloud.com/verifyCode/20170720/20170720135135569_458.jpg";
        String s4="http://yangtaodev-1253852034.cosgz.myqcloud.com/verifyCode/20170720/20170720135119083_198.jpg";
        URL url1 =new URL(s1);
        URL url2 =new URL(s2);
        URL url3 =new URL(s3);
        URL url4 =new URL(s4);
        BufferedImage b1 = ImageIO.read(url1);
        BufferedImage b2 = ImageIO.read(url2);
        BufferedImage b3 = ImageIO.read(url3);
        BufferedImage b4 = ImageIO.read(url4);
        List<BufferedImage> fileList=new ArrayList<BufferedImage>();
        fileList.add(b1);
        fileList.add(b2);
        fileList.add(b3);
        fileList.add(b4);
        File outFile = new File("E:\\test\\mergeGen.jpg");
        int[] codeValue= new int[4];
        BufferedImage image = MsOnionImageUtils.genVerifyCodeBufferedImage(68, 4,fileList,codeValue);
        ImageIO.write(image, "jpg", outFile);
        long endTime=System.currentTimeMillis();
        System.out.println(("==================="+(endTime-beginTime)/1000.0));
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<codeValue.length;i++){
            sb.append(codeValue[i]);
        }
        System.out.println(sb.toString());
    }
}
