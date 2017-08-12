package test.msonion.carambola.parent.ext.service.member;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HeroCao on 2017/5/19.
 */
public class TestParamTypeUtils {

    private TestParamTypeUtils() {
    }

    /**
     *  参数集合
     */
    private static Map paramMap = new HashMap();

    static {

        paramMap.put(TestParamTypeConstants.TYPE_NAME, TestParamTypeConstants.TYPE_NAME_MSG);
        paramMap.put(TestParamTypeConstants.TYPE_PHONE, TestParamTypeConstants.TYPE_PHONE_MSG);
        paramMap.put(TestParamTypeConstants.TYPE_EMAIL, TestParamTypeConstants.TYPE_EMAIL_MSG);
        paramMap.put(TestParamTypeConstants.TYPE_CODE, TestParamTypeConstants.TYPE_CODE_MSG);

        paramMap.put(TestParamTypeConstants.MENU_TYPE_CODE, TestParamTypeConstants.MENU_TYPE_CODE_MSG);

        paramMap.put(TestParamTypeConstants.BUTTON_TYPE_CODE, TestParamTypeConstants.BUTTON_TYPE_CODE_MSG);

        paramMap.put(TestParamTypeConstants.ROLE_TYPE_CODE, TestParamTypeConstants.ROLE_TYPE_CODE_MSG);

        paramMap.put(TestParamTypeConstants.DEPT_TYPE_CODE, TestParamTypeConstants.DEPT_TYPE_CODE_MSG);

        paramMap.put(TestParamTypeConstants.RESOURCEGROUP_TYPE_CODE, TestParamTypeConstants.RESOURCEGROUP_TYPE_CODE_MSG);

        paramMap.put(TestParamTypeConstants.RESOURCE_TYPE_CODE, TestParamTypeConstants.RESOURCE_TYPE_CODE_MSG);

    }

    /**
     *  返回map
     * @return map
     */
    public static Map getParamMap() {
        return paramMap;
    }

    /**
     *
     * @param paramMap map
     */
    private static void setParamMap(Map paramMap) {
        paramMap = paramMap;
    }


    /**
     *  构造 属性字符串
     * @param str 属性str
     * @param split 分隔符
     * @return 属性字符串
     */
    public static  String  printStr(String str, String split) {
        StringBuilder sb = new StringBuilder();
        return  sb.append(str).append(split).toString();
    }
}
