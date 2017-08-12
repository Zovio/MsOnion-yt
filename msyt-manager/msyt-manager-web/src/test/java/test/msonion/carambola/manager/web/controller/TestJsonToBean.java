package test.msonion.carambola.manager.web.controller;

import cc.msonion.carambola.content.pojo.ContentImage;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityBase64Utils;
import org.junit.Test;

import java.util.List;

public class TestJsonToBean {

    @Test
    public void testDecodeStr()throws Exception{
            String es = "W3sibmFtZSI6IiIsInRpdGxlIjoiIiwib3JkZXJOdW1iZXIiOiIiLCJsaW5rVXJsIjoiIiwiaW1hZ2VTbWFsbCI6IiIsImltYWdlTWlkZGxlIjoiIiwiaW1hZ2VCaWciOiIifSx7Im5hbWUiOiIiLCJ0aXRsZSI6IiIsIm9yZGVyTnVtYmVyIjoiIiwibGlua1VybCI6IiIsImltYWdlU21hbGwiOiIiLCJpbWFnZU1pZGRsZSI6IiIsImltYWdlQmlnIjoiIn0seyJuYW1lIjoiIiwidGl0bGUiOiIiLCJvcmRlck51bWJlciI6IiIsImxpbmtVcmwiOiIiLCJpbWFnZVNtYWxsIjoiIiwiaW1hZ2VNaWRkbGUiOiIiLCJpbWFnZUJpZyI6IiJ9XQ==";
            List<ContentImage> imageList2 = MsOnionJsonUtils.jsonToList(MsOnionSecurityBase64Utils.decode(es), ContentImage.class);
            System.out.print(imageList2);
    }
}
