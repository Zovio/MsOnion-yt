package test.msonion.carambola.parent.service.reflection;

import cc.msonion.carambola.parent.common.constants.MsOnionTypeConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionSqlValidateUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by HeroCao on 2017/5/25.
 */
public class TestMsOnionReflectionUtils {

    @Test
    public void testAllField() {

        TestItem testItem = new TestItem();
        testItem.setExt("扩展，测试！！！");

//        TestItem.class

        MsOnionReflectionUtils.doWithFields(TestItem.class, new org.springframework.util.ReflectionUtils.FieldCallback() {

            /**
             * Perform an operation using the given field.
             *
             * @param field the field to operate on
             */
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {


                // 允许访问
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(testItem);
                String typeName = field.getType().getName();

                System.out.println(String.format("testAllField ## doWith ## name=%s, value=%s, typeName=%s", name, value, typeName));


                /*

testAllField ## doWith ## name=idx, value=null, typeName=java.lang.Long
testAllField ## doWith ## name=pidx, value=null, typeName=java.lang.Long
testAllField ## doWith ## name=platformIdx, value=null, typeName=java.lang.Long
testAllField ## doWith ## name=itemStateIdx, value=null, typeName=java.lang.Long
testAllField ## doWith ## name=itemNo, value=null, typeName=java.lang.String
testAllField ## doWith ## name=barcode, value=null, typeName=java.lang.String
testAllField ## doWith ## name=cnName, value=null, typeName=java.lang.String
testAllField ## doWith ## name=enName, value=null, typeName=java.lang.String
testAllField ## doWith ## name=brandIdx, value=null, typeName=java.lang.Long
testAllField ## doWith ## name=originIdx, value=null, typeName=java.lang.Long
testAllField ## doWith ## name=categoryIdx, value=null, typeName=java.lang.Long
testAllField ## doWith ## name=specification, value=null, typeName=java.lang.String
testAllField ## doWith ## name=warehouseType, value=null, typeName=java.lang.Short
testAllField ## doWith ## name=batch, value=null, typeName=java.lang.Integer
testAllField ## doWith ## name=collectionRemark, value=null, typeName=java.lang.String
testAllField ## doWith ## name=imageSmall, value=null, typeName=java.lang.String
testAllField ## doWith ## name=imageMiddle, value=null, typeName=java.lang.String
testAllField ## doWith ## name=imageBig, value=null, typeName=java.lang.String
testAllField ## doWith ## name=purchaseStatus, value=null, typeName=java.lang.Short
testAllField ## doWith ## name=collectionStatus, value=null, typeName=java.lang.Short
testAllField ## doWith ## name=homeshow, value=null, typeName=java.lang.Short
testAllField ## doWith ## name=createByMemberIdx, value=null, typeName=java.lang.Long
testAllField ## doWith ## name=updateByMemberIdx, value=null, typeName=java.lang.Long
testAllField ## doWith ## name=createTime, value=null, typeName=java.util.Date
testAllField ## doWith ## name=updateTime, value=null, typeName=java.util.Date
testAllField ## doWith ## name=status, value=null, typeName=java.lang.Short
testAllField ## doWith ## name=version, value=null, typeName=java.lang.Long
testAllField ## doWith ## name=ext, value=扩展，测试！！！, typeName=java.lang.String
testAllField ## doWith ## name=collectIdx, value=null, typeName=java.lang.Long
testAllField ## doWith ## name=serialVersionUID, value=1, typeName=long
testAllField ## doWith ## name=testLong01, value=0, typeName=long
testAllField ## doWith ## name=testInt01, value=0, typeName=int
testAllField ## doWith ## name=testFloat01, value=0.0, typeName=float
testAllField ## doWith ## name=testDouble01, value=0.0, typeName=double
testAllField ## doWith ## name=testDouble02, value=null, typeName=java.lang.Double
testAllField ## doWith ## name=testFloat02, value=null, typeName=java.lang.Float
testAllField ## doWith ## name=testChar01, value= , typeName=char
testAllField ## doWith ## name=testCharacter01, value=null, typeName=java.lang.Character
testAllField ## doWith ## name=id, value=null, typeName=java.lang.String
testAllField ## doWith ## name=pid, value=null, typeName=java.lang.String

                 */
            }
        });

    }


    @Test
    public void testDoWithFields() {

        TestItem pojo = new TestItem();



        MsOnionReflectionUtils.doWithFields(pojo.getClass(), new org.springframework.util.ReflectionUtils.FieldCallback() {

            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                try {
                    // 允许访问
                    field.setAccessible(true);
                    String name = field.getName();
                    Object value = field.get(pojo);
                    String typeName = field.getType().getName();
                    System.out.println("typeName=" + typeName);

                    // equals , equalsIgnoreCase
                    if (MsOnionTypeConstants.TYPE_REFERENCE_STRING_NAME.equals(typeName)
                            || MsOnionTypeConstants.TYPE_REFERENCE_CHARSEQUENCE_NAME.equals(typeName)) {  // 字符串类型 String

                        // 是null 或者 "" , " " ， "   "
                        if (null == value || StringUtils.isBlank(value + "")) {
                            // 设置默认空字符串
                            field.set(pojo, "");
                            // 获取值
                            Object value2 = field.get(pojo);
                        } else if (StringUtils.isNotBlank(value + "")) {
                            // 过滤SQL注入关键词
                            String newValue = MsOnionSqlValidateUtils.sqlFilter(value + "");

//						// 去掉左右两边空格
//						newValue = newValue.trim();
//
//						// 过滤SQL注入关键词之后，设置值给Field
//						field.set(pojo, newValue);

                            // 去掉左右两边空格
                            trimForString(field, pojo, newValue);
                        } else {
                            // 去掉左右两边空格
//						String newValue = value + "";
//						// 去掉左右两边空格
//						newValue = newValue.trim();
//						// 过滤SQL注入关键词之后，设置值给Field
//						field.set(pojo, newValue);

                            trimForString(field, pojo, value + "");
                        }
                    } else if (MsOnionTypeConstants.TYPE_REFERENCE_LONG_NAME.equals(typeName)
                            || MsOnionTypeConstants.TYPE_LONG_NAME.equals(typeName)) {  // Long类型

                        // 报错的，因为 field.getLong(member) 返回值 是 long , 而不是 Long
                        //					this.msOnionLog.info(getClass(), "doWithFields # 字段, Long，field.get(member)=" + field.getLong(member));

                        if (null == value) {
                            // 设置默认值
                            // 必须加上 0L ,否则报错
                            field.set(pojo, 0L);  // 0 , 0L

                            // 获取值
                            Object value2 = field.get(pojo);
                        }
                    } else if (MsOnionTypeConstants.TYPE_REFERENCE_DATE_NAME.equals(typeName)) {  // 日期类型 Date
                        if (null == value) {
                            // 设置日期为当前时间
                            field.set(pojo, new Date());
                        }
                    } else if (MsOnionTypeConstants.TYPE_REFERENCE_INTEGER_NAME.equals(typeName)
                            || MsOnionTypeConstants.TYPE_INT_NAME.equals(typeName)) {  // int类型
                        if (null == value) {
                            // 设置默认值为0
                            field.set(pojo, 0);
                        }
                    } else if (MsOnionTypeConstants.TYPE_SHORT_NAME.equals(typeName)
                            || MsOnionTypeConstants.TYPE_REFERENCE_SHORT_NAME.equals(typeName)) {  // Short类型
                        if (null == value) {
                            // 设置默认值为0
                            // 必须类型转换为： (short)0
                            field.set(pojo, (short) 0);
                        }
                    }
                } catch (Exception ex) {
                   ex.printStackTrace();
                }
            }
        });

        System.out.println("pojo=" + pojo);

    }

    /**
     * 字符串去掉两边空格
     *
     * @param field 字段
     * @param pojo  目标POJO实例对象
     * @param value 值
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void trimForString(Field field, MsOnionBasePojoAdapter pojo, String value) throws MsOnionException {
        try {
            String newValue = value.trim();

            // 去掉左右两边空格之后，设置值给Field
            field.set(pojo, newValue);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

}
