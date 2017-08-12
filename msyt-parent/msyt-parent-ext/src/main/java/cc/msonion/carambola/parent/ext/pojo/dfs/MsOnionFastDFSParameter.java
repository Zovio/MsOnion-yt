package cc.msonion.carambola.parent.ext.pojo.dfs;

import cc.msonion.carambola.parent.interfaces.dfs.clients.adapter.MsOnionDFSParameterAdapter;
import org.csource.common.NameValuePair;

/**
 * Created by HeroCao on 2017/4/20.
 */
public class MsOnionFastDFSParameter implements MsOnionDFSParameterAdapter {


    /**
     * 文件的内容，字节数组
     */
    private byte[] fileContent;

    /**
     * 文件全路径，例如：C:/Users/HeroCao/Desktop/yt.png （Windows路径）
     */
    private String filename;

    /**
     * 文件扩展名，不包括.，例如：jpg、png
     */
    private String extName;

    /**
     * 文件扩展信息
     */
    private NameValuePair[] metas;

    /////////////////////  Setters、Getters ， Begin //////////////////////

    /**
     * 获取文件的内容，字节数组
     *
     * @return 返回文件的内容，字节数组
     */
    public byte[] getFileContent() {
        return fileContent;
    }

    /**
     * 设置 文件的内容，字节数组
     *
     * @param fileContent 文件的内容，字节数组
     */
    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    /**
     * 获取 文件全路径，例如：C:/Users/HeroCao/Desktop/yt.png （Windows路径）
     *
     * @return 返回文件全路径，例如：C:/Users/HeroCao/Desktop/yt.png （Windows路径）
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 设置 文件全路径，例如：C:/Users/HeroCao/Desktop/yt.png （Windows路径）
     *
     * @param filename 文件全路径，例如：C:/Users/HeroCao/Desktop/yt.png （Windows路径）
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 获取 文件扩展名，不包括.，例如：jpg、png
     *
     * @return 返回 文件扩展名，不包括.，例如：jpg、png
     */
    public String getExtName() {
        return extName;
    }

    /**
     * 设置 文件扩展名，不包括.，例如：jpg、png
     *
     * @param extName 文件扩展名，不包括.，例如：jpg、png
     */
    public void setExtName(String extName) {
        this.extName = extName;
    }

    /**
     * 获取 文件扩展信息
     *
     * @return 返回 文件扩展信息
     */
    public NameValuePair[] getMetas() {
        return metas;
    }

    /**
     * 设置  文件扩展信息
     *
     * @param metas 文件扩展信息
     */
    public void setMetas(NameValuePair[] metas) {
        this.metas = metas;
    }


    /////////////////////  Setters、Getters ， End //////////////////////
}
