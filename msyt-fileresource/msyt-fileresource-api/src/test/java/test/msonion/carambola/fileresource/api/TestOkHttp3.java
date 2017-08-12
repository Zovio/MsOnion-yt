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


package test.msonion.carambola.fileresource.api;

/**
 * @Title: TestOkHttp3.java
 * @Package: test.msonion.carambola.fileresource.api
 * @Description: 测试okhttp3上传文件
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月04日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月04日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.fileresource.pojo.FileResourceInfo;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionOkHttp3Utils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFileUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFilenameUtils;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TestOkHttp3
 * @Description: 测试okhttp3上传文件
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月04日
 *
 */
public class TestOkHttp3 {





/*
    @Test
    public void test04() throws Exception {
        File file1 = new File("D:\\testFile\\eclipse-jee-neon-2-win32-x86_64.zip");

        //File file1 = new File("D:\\testFile\\123.rar");
        File file2 = new File("D:\\testFile\\abc.zip");
        if (file2.exists()) {
            file2.createNewFile();
        }
        RandomAccessFile f1 = new RandomAccessFile(file1, "r");
        //RandomAccessFile f2 = new RandomAccessFile(file2,"rw");

        byte[] bytes = null;
        int bufferSize = 10485760;
        long nStart = 0L;
        while (nStart <= f1.length()) {


            if (f1.length() - nStart < bufferSize) {
                bufferSize = (int) (f1.length() - nStart);
            }

            bytes = new byte[bufferSize];
            int hasRead = f1.read(bytes);


            if (hasRead <= 0) {
                break;
            }
            RandomAccessFile f2 = new RandomAccessFile(file2, "rw");
            f2.seek(nStart);
            f2.write(bytes);
            f2.close();

            nStart += hasRead;
        }


    }*/

    @Test
    public void test01() throws Exception {
//        File file2 = new File("D:\\testFile\\eclipse-jee-neon-2-win32-x86_64.zip");
//        File file1 = new File("D:\\testFile\\Java设计模式(第2版)中文版.pdf");
//        File file3 = new File("D:\\testFile\\testbig.rar");
//        File file4= new File("D:\\testFile\\test2");

        File file2 = new File("E:\\test.jpg");

        final String url = "http://172.16.10.154:8140/cos/uploadFiles";
        Map map = new HashMap();
        map.put("filePath", "/test/aa");
        JSONObject jsonObject = MsOnionOkHttp3Utils.postFiles(url, map, file2);
        System.out.println(jsonObject);

    }

    @Test
    public void test04() throws Exception {
        File file4= new File("D:\\testFile\\test2");
        System.out.println(MsOnionFileUtils.transitionPath(file4.getAbsolutePath()));
    }
    @Test
    public void testPath(){

        String s=MsOnionFilenameUtils.getFullPath("/test/aa/20170701/20170701162527717_962.jpg");
        System.out.println(s);
    }
    @Test
    public void testParse()throws Exception {
        File file2 = new File("E:\\test.jpg");

        final String url = "http://172.16.10.154:8140/cos/uploadFiles";
        Map map = new HashMap();
        map.put("filePath", "/test/aa");
        JSONObject jsonObject = MsOnionOkHttp3Utils.postFiles(url, map, file2);
        MsOnionResult msOnionResult = jsonObject.toJavaObject(MsOnionResult.class);
        List<FileResourceInfo> fileResourceInfoList = JSONObject.parseArray(JSONObject.toJSONString(jsonObject.get("data")), FileResourceInfo.class);
        FileResourceInfo fileResourceInfo = fileResourceInfoList.get(0);
        System.out.println("===="+fileResourceInfo.getPath());
    }

}
