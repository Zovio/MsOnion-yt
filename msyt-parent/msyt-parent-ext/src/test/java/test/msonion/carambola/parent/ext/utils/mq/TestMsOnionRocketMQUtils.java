package test.msonion.carambola.parent.ext.utils.mq;

import cc.msonion.carambola.parent.common.enums.MsOnionCrudAction;
import cc.msonion.carambola.parent.ext.utils.mq.MsOnionRocketMQUtils;
import org.junit.Test;

/**
 * Created by HeroCao on 2017/5/29.
 */
public class TestMsOnionRocketMQUtils {


    @Test
    public void test01() {

        String targetName = "Item";
        MsOnionCrudAction crudAction = MsOnionCrudAction.SAVE;

        String tags = MsOnionRocketMQUtils.getTags(targetName, crudAction);

        // 测试 ## tags=Item@Save
        System.out.println(String.format("测试 ## tags=%s", tags));

    }
}
