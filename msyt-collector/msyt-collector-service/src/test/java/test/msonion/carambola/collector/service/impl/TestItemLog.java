package test.msonion.carambola.collector.service.impl;


import cc.msonion.carambola.collector.common.utils.ItemModifyLocationUtils;
import cc.msonion.carambola.collector.pojo.CollectorItemLog;
import cc.msonion.carambola.collector.service.ItemLogService;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext-*.xml"})
public class TestItemLog {





    @Autowired
    private ItemLogService itemLogService;

    @Test
    public void testAdd() throws MsOnionException {

        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion("V2.0.0");
        CollectorItemLog collectorItemLog = new CollectorItemLog();
        collectorItemLog.setItemIdx(1L);
        collectorItemLog.setModifyLocation(ItemModifyLocationUtils.TYPE_ITEM_CATEGORY);
        collectorItemLog.setOriginValue("aaa");
        collectorItemLog.setNewValue("bbb");
       MsOnionResult msOnionResult = itemLogService.saveItemLog(msOnionApiVersion,collectorItemLog,1l);
        System.out.println(JSONObject.toJSONString(msOnionResult));
    }

    @Test
    public void testBatchAdd() throws MsOnionException {


        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion("V2.0.0");


        CollectorItemLog collectorItemLog = new CollectorItemLog();
        collectorItemLog.setItemIdx(1L);
        collectorItemLog.setModifyLocation(ItemModifyLocationUtils.TYPE_ITEM_CATEGORY);
        collectorItemLog.setOriginValue("aaa");
        collectorItemLog.setNewValue("bbb");


        CollectorItemLog collectorItemLog2 = new CollectorItemLog();
        collectorItemLog2.setItemIdx(2L);
        collectorItemLog2.setModifyLocation(ItemModifyLocationUtils.TYPE_ITEM_CATEGORY);
        collectorItemLog2.setOriginValue("aaa");
        collectorItemLog2.setNewValue("bbb");

        List<CollectorItemLog> collectorItemLogList=new ArrayList<CollectorItemLog>();

        MsOnionResult msOnionResult = itemLogService.batchSaveItemLog(msOnionApiVersion,collectorItemLogList,1l);
        System.out.println(JSONObject.toJSONString(msOnionResult));

    }
}
