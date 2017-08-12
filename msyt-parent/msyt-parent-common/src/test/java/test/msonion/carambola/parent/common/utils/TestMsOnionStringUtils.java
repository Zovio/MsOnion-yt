package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import org.junit.Test;

/**
 * Created by HeroCao on 2017/5/4.
 */
public class TestMsOnionStringUtils {

    @Test
    public void test01() {

        String str = "creaeByMebmerIdx";

//        str.toUpperCase()

        getUpCaseAndReverse(str);

        String allUpperCase = MsOnionStringUtils.getAllUpperCase(str);

        System.out.println("allUpperCase=" + allUpperCase);

    }



    /**
     * @Title: getUpCaseAndReverse
     * @Description: 输出字符串中的大写字母，并倒序输出
     * @param str
     * @return: void
     */
    private static void getUpCaseAndReverse(String str){
        StringBuffer buffer = new StringBuffer();
        // 转为char数组
        char[] ch = str.toCharArray();
        // 得到大写字母
        for(int i = 0; i < ch.length ; i++){
            if(ch[i] >= 'A' && ch[i] <= 'Z'){
                buffer.append(ch[i]);
            }
        }
        // 倒序
//        buffer = buffer.reverse();
        System.out.println(buffer.toString());
    }

    @Test
    public void testGetCorrectColumnNameForDB() {

        // OK
//        String strs = "createByMemberIdx  asc";

        // OK
//        String strs = "createByMemberIdx  ASC";

        // OK
//        String strs = "createByMemberIdx  desc";

        // OK
//        String strs = "createByMemberIdx  DESC";

        String strs = "createByMemberIdx";

//        String strs = "createByMemberIdx asc,updateByByMemberIdx desc,lastLoginTime";

//        String strs = "createTimeEnYyyyMMddHHmmssSSS asc,createTimeCnYyyyMMddHHmmssSSS desc,lastLoginTime";

        System.out.println("strs=" + strs);

        String newStr = MsOnionStringUtils.getCorrectOrderByForDB(strs);

        System.out.println("newStr=" + newStr);

    }

}
