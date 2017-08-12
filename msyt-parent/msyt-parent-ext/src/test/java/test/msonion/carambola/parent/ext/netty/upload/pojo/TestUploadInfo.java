package test.msonion.carambola.parent.ext.netty.upload.pojo;

import java.io.Serializable;

/**
 * Created by HeroCao on 2017/4/30.
 */
public class TestUploadInfo implements Serializable {


    private String filename;

    public TestUploadInfo() {

    }

    public TestUploadInfo(String filename) {
        this.filename = filename;
    }


    ////////////  Setters、 Getters ### Begin ///////////////////////

    public String getFilename() {
        return filename;
    }


    ////////////  Setters、 Getters ### End ///////////////////////
}
