package test.msonion.carambola.manager.web.controller.controller.fileresource;

import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseDFSAppController;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.pojo.dfs.MsOnionFastDFSParameter;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by HeroCao on 2017/4/20.
 */
@Controller
public class TestUploadController extends MsOnionBaseDFSAppController {

    /**
     * 测试上传
     */
    @RequestMapping("/testupload/upload1")
    @ResponseBody
    public MsOnionResultAdapter testUpload() {

        this.getMsOnionLogger().info(getClass().getName(), "测试上传 。。。");


//        String filename = "C:/Users/HeroCao/Desktop/Version的生成-20170413113927.png";

//        String filename = "C:/Users/HeroCao/Desktop/3.jpg";


        // 退款金额-20170328185524.png

        // TODO：需要加上参数判断，因为生产环境使用 OSS，其他环境使用 FastDFS
        if (this.getMsOnionDomain().isOSS()) {

            // TODO: OSS
            this.getMsOnionLogger().info(getClass().getName(), "======== OSS ==== 还没有实现，TODO: OSS");

        } else {

            // FastDFS 方式实现
            MsOnionFastDFSParameter parameter = new MsOnionFastDFSParameter();

            String filename = "C:/Users/HeroCao/Desktop/退款金额-20170328185524.png";

            parameter.setFilename(filename);

            // 文件byte[]
            // 可以使用这个
//            parameter.setFileContent();

            // 设置文件扩展名，不包括 .
//            parameter.setExtName("jpg");

            try {
                MsOnionResultAdapter resultAdapter = this.getMsOnionDFSClientAdapter().uploadFile(parameter);

                return resultAdapter;


            } catch (MsOnionException e) {

                this.getMsOnionLogger().error(getClass().getName(), e);

                // 测试，时间太晚了，直接写死字符串
                // 实际开发中，要定全局变量 ！！！
                return new MsOnionResult(MsOnionStatusConstants.STATUS_500, "上传失败", "");
            }
        }

        // 测试，时间太晚了，直接写死字符串
        // 实际开发中，要定全局变量 ！！！
        return new MsOnionResult(MsOnionStatusConstants.STATUS_500, "上传失败", "");

    }

}
