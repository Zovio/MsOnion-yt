package test.msonion.carambola.commons.common.zk;

import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionZookeeperUtils;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFilenameUtils;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.zk.atomic.MsOnionDistributedAtomicLong;
import cc.msonion.carambola.parent.pojo.zk.children.MsOnionZookeeperPathChild;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import cc.msonion.carambola.parent.pojo.zk.retrypolicy.MsOnionRetryNTimes;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

/**
 * Created by HeroCao on 2017/6/29.
 */
@ContextConfiguration(locations = "classpath:dev/spring/applicationContext-*.xml")
public class TestZookeeper extends AbstractJUnit4SpringContextTests {


    @Autowired
    private MsOnionDomain domain;

    @Autowired
    private MsOnionCuratorZookeeperClient curatorZookeeperClient;

    @Autowired
    private MsOnionRetryNTimes retryNTimes;


    @Test
    public void testGetChildren01() throws Exception {


        System.out.println("测试 ZK # testGetChildren01 # domain=" + domain);


        System.out.println("测试 ZK # testGetChildren01 # curatorZookeeperClient=" + curatorZookeeperClient);

//        CuratorZookeeperClient zookeeperClient = curatorZookeeperClient.getCuratorFramework().getZookeeperClient().getZooKeeper();


        curatorZookeeperClient.getCuratorFramework().getChildren();

        ZooKeeper zooKeeper = curatorZookeeperClient.getCuratorFramework().getZookeeperClient().getZooKeeper();

        System.out.println("测试 ZK # testGetChildren01 # zooKeeper=" + zooKeeper);


        String zkRootCounterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_PATH;

//        List<String> children = client.getCuratorFramework().getZookeeperClient().getZooKeeper().getChildren(zkRootCounterPath, true);


        List<String> childrenDir = zooKeeper.getChildren(zkRootCounterPath, true);

        System.out.println("测试 ZK # testGetChildren01 # childrenDir=" + childrenDir);


        for (String dir : childrenDir) {

            System.out.println("测试 ZK # testGetChildren01 # dir=" + dir);
        }


        /*
        测试 ZK # testGetChildren01 # childrenDir=[pro, dev]
测试 ZK # testGetChildren01 # dir=pro
测试 ZK # testGetChildren01 # dir=dev

         */

//        zooKeeper.

        System.out.println("测试 ZK # testGetChildren01 #  The End !!!");

    }


    @Test
    public void testGetChildren03() throws Exception {


        System.out.println("测试 ZK # testGetChildren03 # domain=" + domain);


        System.out.println("测试 ZK # testGetChildren03 # curatorZookeeperClient=" + curatorZookeeperClient);

//        CuratorZookeeperClient zookeeperClient = curatorZookeeperClient.getCuratorFramework().getZookeeperClient().getZooKeeper();


        curatorZookeeperClient.getCuratorFramework().getChildren();

        ZooKeeper zooKeeper = curatorZookeeperClient.getCuratorFramework().getZookeeperClient().getZooKeeper();

        System.out.println("测试 ZK # testGetChildren01 # zooKeeper=" + zooKeeper);


        String zkRootCounterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_PATH;


//        String zkRootCounterPath = MsOnionDistributedAtomicCodeUtils.BRAND_CODE_PREFIX

//        List<String> children = client.getCuratorFramework().getZookeeperClient().getZooKeeper().getChildren(zkRootCounterPath, true);


        List<String> childrenDir = zooKeeper.getChildren(zkRootCounterPath, true);

        System.out.println("测试 ZK # testGetChildren01 # childrenDir=" + childrenDir);


        for (String dir : childrenDir) {

            System.out.println("测试 ZK # testGetChildren01 # dir=" + dir);
        }


        /*
        测试 ZK # testGetChildren01 # childrenDir=[pro, dev]
测试 ZK # testGetChildren01 # dir=pro
测试 ZK # testGetChildren01 # dir=dev

         */

        System.out.println("测试 ZK # testGetChildren01 #  The End !!!");

    }

    @Test
    public void testGetChildren05() throws Exception {


        System.out.println("测试 ZK # testGetChildren05 # domain=" + domain);

//        curatorZookeeperClient.getCuratorFramework().getZookeeperClient().getZooKeeper()

        String modulePath = "long";

//        List<String> children = MsOnionZookeeperUtils.getCounterPathChildren(curatorZookeeperClient, modulePath);
//
//
//        System.out.println("测试 ZK # testGetChildren05 # children=" + children);

    }

    @Test
    public void testGetChildren06() throws Exception {

        // 循环获取

        System.out.println("测试 ZK # testGetChildren06 # domain=" + domain);

//        curatorZookeeperClient.getCuratorFramework().getZookeeperClient().getZooKeeper()

//        String modulePath = "long";

//        List<String> children = MsOnionZookeeperUtils.getCounterPathChildren(curatorZookeeperClient, modulePath);

        List<String> children = MsOnionZookeeperUtils.getCounterRootBasePathChildren(curatorZookeeperClient);

        String modulePath = null;

        // TODO: 可以使用递归获取所有 子目录
        for (String path : children) {


            List<String> children2 = MsOnionZookeeperUtils.getCounterPathChildrenForList(curatorZookeeperClient, path);


//            List<String> children2 = null;

//                    MsOnionZookeeperUtils.getCounterPathChildren(curatorZookeeperClient, path);


            System.out.println("测试 ZK # testGetChildren06 # children2=" + children2);

            for (String path2 : children2) {

                // TODO: 必须拼接 上一级路径
                modulePath = String.format("%s/%s", path, path2);

//                List<String> children3 = null; // = MsOnionZookeeperUtils.getCounterPathChildren(curatorZookeeperClient, modulePath);

                List<String> children3 = MsOnionZookeeperUtils.getCounterPathChildrenForList(curatorZookeeperClient, modulePath);


                System.out.println("测试 ZK # testGetChildren06 # children3=" + children3);
            }
        }

        System.out.println("测试 ZK # testGetChildren06 # children=" + children);

    }


    @Test
    public void testGetChildren02() throws Exception {


        System.out.println("测试 ZK # testGetChildren01 # domain=" + domain);


        System.out.println("测试 ZK # testGetChildren01 # curatorZookeeperClient=" + curatorZookeeperClient);

//        CuratorZookeeperClient zookeeperClient = curatorZookeeperClient.getCuratorFramework().getZookeeperClient().getZooKeeper();


        GetChildrenBuilder childrenBuilder = curatorZookeeperClient.getCuratorFramework().getChildren();

        System.out.println("测试 ZK # testGetChildren01 # childrenBuilder=" + childrenBuilder);

//        childrenBuilder.forPath()

        ZooKeeper zooKeeper = curatorZookeeperClient.getCuratorFramework().getZookeeperClient().getZooKeeper();

        System.out.println("测试 ZK # testGetChildren01 # zooKeeper=" + zooKeeper);


        String zkRootCounterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_PATH;

//        List<String> children = client.getCuratorFramework().getZookeeperClient().getZooKeeper().getChildren(zkRootCounterPath, true);


        List<String> childrenDir = zooKeeper.getChildren(zkRootCounterPath, true);

        System.out.println("测试 ZK # testGetChildren01 # childrenDir=" + childrenDir);


        for (String dir : childrenDir) {

            System.out.println("测试 ZK # testGetChildren01 # dir=" + dir);
        }

        System.out.println("测试 ZK # testGetChildren01 #  The End !!!");

    }

    @Test
    public void test01() throws Exception {

        MsOnionDistributedAtomicLong distributedAtomicLong = null;

        //
//        distributedAtomicLong.trySet()

//        distributedAtomicLong.forceSet();

//        distributedAtomicLong.initialize()

//        distributedAtomicLong.increment()


//        distributedAtomicLong.get();

//        curatorZookeeperClient.getCuratorFramework().getZookeeperClient().getZooKeeper().delete();


    }


    @Test
    public void testLongInitialize() throws MsOnionException {

        String counterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH;

        counterPath = String.format("%s/%s", counterPath, "testLong01");

        long value = 1888;

        boolean initialize = MsOnionZookeeperUtils.initialize(curatorZookeeperClient, retryNTimes, counterPath, value);


        /*

        // 第一次：
        2017-06-30 21:38:24,680 [main-SendThread(k8s-master.msonion.dev:2181)] [org.apache.zookeeper.ClientCnxn]-[DEBUG] Reading reply sessionid:0x15ce872b88300a4, packet:: clientPath:null serverPath:null finished:false header:: 1,1  replyHeader:: 1,55834606076,0  request:: '/www.msyc.cc/carambola/atomic/testLong01,#000000760,v{s{31,s{'world,'anyone}}},0  response:: '/www.msyc.cc/carambola/atomic/testLong01
initialize=true

        第二次：
        initialize=false
         */
        System.out.println("initialize=" + initialize);

    }


    @Test
    public void testLongGet() throws MsOnionException {

        String counterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH;

        counterPath = String.format("%s/%s", counterPath, "testLong01");

        long value = 1888;

//        boolean initialize = MsOnionZookeeperUtils.initialize(curatorZookeeperClient, retryNTimes, counterPath, value);


        AtomicValue<Long> longAtomicValue = MsOnionZookeeperUtils.get(curatorZookeeperClient, retryNTimes, counterPath);

        /*
测试 # testLong02 # longAtomicValue.succeeded=true
测试 # testLong02 # longAtomicValue.preValue=1888
测试 # testLong02 # longAtomicValue.postValue=1888

         */
        System.out.println("测试 # testLong02 # longAtomicValue=" + longAtomicValue);


//        longAtomicValue.succeeded();

        System.out.println("测试 # testLong02 # longAtomicValue.succeeded=" + longAtomicValue.succeeded());

        System.out.println("测试 # testLong02 # longAtomicValue.preValue=" + longAtomicValue.preValue());


        System.out.println("测试 # testLong02 # longAtomicValue.postValue=" + longAtomicValue.postValue());

    }

    @Test
    public void testLongIncrement() throws MsOnionException {

        String counterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH;

        counterPath = String.format("%s/%s", counterPath, "testLong01");

        long value = 1888;

//        boolean initialize = MsOnionZookeeperUtils.initialize(curatorZookeeperClient, retryNTimes, counterPath, value);


        AtomicValue<Long> longAtomicValue = MsOnionZookeeperUtils.increment(curatorZookeeperClient, retryNTimes, counterPath);

        /*
测试 # testLong03 # longAtomicValue.succeeded=true
测试 # testLong03 # longAtomicValue.preValue=1888
测试 # testLong03 # longAtomicValue.postValue=1889

         */
        System.out.println("测试 # testLong02 # longAtomicValue=" + longAtomicValue);


//        longAtomicValue.succeeded();

        System.out.println("测试 # testLong03 # longAtomicValue.succeeded=" + longAtomicValue.succeeded());

        System.out.println("测试 # testLong03 # longAtomicValue.preValue=" + longAtomicValue.preValue());


        System.out.println("测试 # testLong03 # longAtomicValue.postValue=" + longAtomicValue.postValue());

    }


    @Test
    public void testLongDecrement() throws MsOnionException {

        String counterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH;

        counterPath = String.format("%s/%s", counterPath, "testLong01");

        long value = 1888;

//        boolean initialize = MsOnionZookeeperUtils.initialize(curatorZookeeperClient, retryNTimes, counterPath, value);


        AtomicValue<Long> longAtomicValue = MsOnionZookeeperUtils.decrement(curatorZookeeperClient, retryNTimes, counterPath);

        /*
测试 # testLong03 # longAtomicValue.succeeded=true
测试 # testLong03 # longAtomicValue.preValue=1889
测试 # testLong03 # longAtomicValue.postValue=1888

         */
        System.out.println("测试 # testLong02 # longAtomicValue=" + longAtomicValue);


//        longAtomicValue.succeeded();

        System.out.println("测试 # testLong03 # longAtomicValue.succeeded=" + longAtomicValue.succeeded());

        System.out.println("测试 # testLong03 # longAtomicValue.preValue=" + longAtomicValue.preValue());


        System.out.println("测试 # testLong03 # longAtomicValue.postValue=" + longAtomicValue.postValue());

    }

    @Test
    public void testLongSubtract() throws MsOnionException {

        String counterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH;

        counterPath = String.format("%s/%s", counterPath, "testLong01");

        long value = 1888;

//        boolean initialize = MsOnionZookeeperUtils.initialize(curatorZookeeperClient, retryNTimes, counterPath, value);

        long value2 = 5;

        AtomicValue<Long> longAtomicValue = MsOnionZookeeperUtils.subtract(curatorZookeeperClient, retryNTimes, counterPath, value2);

        /*
测试 # testLongSubtract # longAtomicValue.succeeded=true
测试 # testLongSubtract # longAtomicValue.preValue=1888
测试 # testLongSubtract # longAtomicValue.postValue=1883

         */
        System.out.println("测试 # testLongSubtract # longAtomicValue=" + longAtomicValue);


//        longAtomicValue.succeeded();

        System.out.println("测试 # testLongSubtract # longAtomicValue.succeeded=" + longAtomicValue.succeeded());

        System.out.println("测试 # testLongSubtract # longAtomicValue.preValue=" + longAtomicValue.preValue());


        System.out.println("测试 # testLongSubtract # longAtomicValue.postValue=" + longAtomicValue.postValue());

    }


    @Test
    public void testLongAdd() throws MsOnionException {

        String counterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH;

        counterPath = String.format("%s/%s", counterPath, "testLong01");

//        long value = 1888;

//        long value = 1888;

        long value = 1888;

//        boolean initialize = MsOnionZookeeperUtils.initialize(curatorZookeeperClient, retryNTimes, counterPath, value);

        long value2 = 111;

        AtomicValue<Long> longAtomicValue = MsOnionZookeeperUtils.add(curatorZookeeperClient, retryNTimes, counterPath, value2);

        /*
测试 # testLongAdd # longAtomicValue.succeeded=true
测试 # testLongAdd # longAtomicValue.preValue=1883
测试 # testLongAdd # longAtomicValue.postValue=1888

         */
        System.out.println("测试 # testLongAdd # longAtomicValue=" + longAtomicValue);


//        longAtomicValue.succeeded();

        System.out.println("测试 # testLongAdd # longAtomicValue.succeeded=" + longAtomicValue.succeeded());

        System.out.println("测试 # testLongAdd # longAtomicValue.preValue=" + longAtomicValue.preValue());


        System.out.println("测试 # testLongAdd # longAtomicValue.postValue=" + longAtomicValue.postValue());

    }

    @Test
    public void testLongCompareAndSet() throws MsOnionException {

        String counterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH;

        counterPath = String.format("%s/%s", counterPath, "testLong01");

        long value = 1888;

//        boolean initialize = MsOnionZookeeperUtils.initialize(curatorZookeeperClient, retryNTimes, counterPath, value);

        long value2 = 5;

//        AtomicValue<Long> longAtomicValue = MsOnionZookeeperUtils.add(curatorZookeeperClient, retryNTimes, counterPath, value2);

        long expectedValue = 1888L;

        long newValue = 1889L;

        AtomicValue<Long> longAtomicValue = MsOnionZookeeperUtils.compareAndSet(curatorZookeeperClient,
                retryNTimes, counterPath, expectedValue, newValue);



        /*
测试 # testLongCompareAndSet # longAtomicValue.succeeded=true
测试 # testLongCompareAndSet # longAtomicValue.preValue=1888
测试 # testLongCompareAndSet # longAtomicValue.postValue=1889

         */
        System.out.println("测试 # testLongCompareAndSet # longAtomicValue=" + longAtomicValue);


//        longAtomicValue.succeeded();

        System.out.println("测试 # testLongCompareAndSet # longAtomicValue.succeeded=" + longAtomicValue.succeeded());

        System.out.println("测试 # testLongCompareAndSet # longAtomicValue.preValue=" + longAtomicValue.preValue());


        System.out.println("测试 # testLongCompareAndSet # longAtomicValue.postValue=" + longAtomicValue.postValue());

    }


    @Test
    public void testLongTrySet() throws MsOnionException {

        String counterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH;

        counterPath = String.format("%s/%s", counterPath, "testLong01");

        long value = 1888;

//        boolean initialize = MsOnionZookeeperUtils.initialize(curatorZookeeperClient, retryNTimes, counterPath, value);

        long value2 = 5;

//        AtomicValue<Long> longAtomicValue = MsOnionZookeeperUtils.add(curatorZookeeperClient, retryNTimes, counterPath, value2);

//        long expectedValue = 1888L;

        long newValue = 2889L;

        AtomicValue<Long> longAtomicValue = MsOnionZookeeperUtils.trySet(curatorZookeeperClient,
                retryNTimes, counterPath, newValue);



        /*
测试 # testLongCompareAndSet # longAtomicValue.succeeded=true
测试 # testLongCompareAndSet # longAtomicValue.preValue=1889
测试 # testLongCompareAndSet # longAtomicValue.postValue=2889

         */
        System.out.println("测试 # testLongCompareAndSet # longAtomicValue=" + longAtomicValue);


//        longAtomicValue.succeeded();

        System.out.println("测试 # testLongCompareAndSet # longAtomicValue.succeeded=" + longAtomicValue.succeeded());

        System.out.println("测试 # testLongCompareAndSet # longAtomicValue.preValue=" + longAtomicValue.preValue());


        System.out.println("测试 # testLongCompareAndSet # longAtomicValue.postValue=" + longAtomicValue.postValue());

    }


    @Test
    public void testLongForceSet() throws MsOnionException {

        String counterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH;

        counterPath = String.format("%s/%s", counterPath, "testLong01");

        /**
         * 测试 # testLongForceSet # counterPath=/www.msyc.cc/carambola/atomic/testLong01
         */
        System.out.println("测试 # testLongForceSet # counterPath=" + counterPath);

        long value = 1888;

//        boolean initialize = MsOnionZookeeperUtils.initialize(curatorZookeeperClient, retryNTimes, counterPath, value);

        long value2 = 5;

//        AtomicValue<Long> longAtomicValue = MsOnionZookeeperUtils.add(curatorZookeeperClient, retryNTimes, counterPath, value2);

//        long expectedValue = 1888L;

//        long newValue = 22889L;

        long newValue = 229988L;

        MsOnionZookeeperUtils.forceSet(curatorZookeeperClient,
                retryNTimes, counterPath, newValue);


        AtomicValue<Long> longAtomicValue = MsOnionZookeeperUtils.get(curatorZookeeperClient, retryNTimes, counterPath);


        /*
测试 # testLongCompareAndSet # longAtomicValue.succeeded=true
测试 # testLongCompareAndSet # longAtomicValue.preValue=22889
测试 # testLongCompareAndSet # longAtomicValue.postValue=22889

         */
        System.out.println("测试 # testLongCompareAndSet # longAtomicValue=" + longAtomicValue);


//        longAtomicValue.succeeded();

        System.out.println("测试 # testLongCompareAndSet # longAtomicValue.succeeded=" + longAtomicValue.succeeded());

        System.out.println("测试 # testLongCompareAndSet # longAtomicValue.preValue=" + longAtomicValue.preValue());


        System.out.println("测试 # testLongCompareAndSet # longAtomicValue.postValue=" + longAtomicValue.postValue());

    }


//    @Test
//    public void testGetCounterPathChildren() throws MsOnionException {
//
//        /*
//        cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException:
//         MsOnionDistributedAtomicLongUtils # generateZKCounterPath方法， modulePath参数非法，modulePath=/www.msyc.cc/carambola/atomic
//
//         */
////        String counterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH;
//
//        String modulePath = "testLong01";
//
//
//        List<String> counterPathChildren = MsOnionZookeeperUtils.getCounterPathChildren(curatorZookeeperClient, modulePath);
//
//
//        System.out.println("测试 # testLongCompareAndSet # counterPathChildren=" + counterPathChildren);
//
//    }


    @Test
    public void testPath01() {

       String path =  MsOnionZookeeperUtils.ZK_ROOT_COUNTER_BASE_PATH;

        String parentPath = MsOnionFilenameUtils.getPath(path);

        /*
        测试 # testPath01 # path=/www.msyc.cc/carambola/atomic
测试 # testPath01 # parentPath=www.msyc.cc/carambola/

         */
        System.out.println("测试 # testPath01 # path=" + path);

        System.out.println("测试 # testPath01 # parentPath=" + parentPath);

    }

    @Test
    public void testPath02() {

//        String path =  MsOnionZookeeperUtils.ZK_ROOT_COUNTER_BASE_PATH;

        String path = "wwww.msyc.cc/yt/zk/data/version/idx";

        String parentPath = MsOnionFilenameUtils.getPath(path);

//        MsOnionFilenameUtils.getPathNoEndSeparator()

        /*
       测试 # testPath01 # path=wwww.msyc.cc/yt/zk/data/version/idx
测试 # testPath01 # parentPath=wwww.msyc.cc/yt/zk/data/version/

         */
        System.out.println("测试 # testPath01 # path=" + path);

        System.out.println("测试 # testPath01 # parentPath=" + parentPath);

    }

    @Test
    public void testGetCounterPathChildren01() throws MsOnionException {

        // TODO: 测试，一次性，递归加载当前目录（根目录）下的所有子目录

//        MsOnionZookeeperPathChild counterPathChildren =
//                MsOnionZookeeperUtils.getCounterPathChildren(curatorZookeeperClient, null);

        MsOnionZookeeperPathChild counterPathChildren =
                MsOnionZookeeperUtils.getCounterRootPathChildren(curatorZookeeperClient);


//        System.out.println("测试 # testGetCounterPathChildren01 # counterPathChildren=" + counterPathChildren);


        String json = MsOnionJsonUtils.objectToJson(counterPathChildren);

        System.out.println("测试 # testGetCounterPathChildren01 # \r\n\r\n ============== \r\n\r\n");


        // Json
        System.out.println("测试 # testGetCounterPathChildren01 # json=" + json);


    }

    @Test
    public void testGetCounterPathChildren02() throws MsOnionException {

        // 只是加载当前目录下的所有子目录，并没有递归

        MsOnionZookeeperPathChild counterPathChildren =
                MsOnionZookeeperUtils.getCounterRootPathCurrentChildren(curatorZookeeperClient);

        System.out.println("测试 # testGetCounterPathChildren02 # counterPathChildren=" + counterPathChildren);

        // Json

        String json = MsOnionJsonUtils.objectToJson(counterPathChildren);

        System.out.println("测试 # testGetCounterPathChildren02 # json=" + json);

        /*
        测试 # testGetCounterPathChildren02 # json={"parentPath":"www.msyc.cc/carambola","modulePath":"/","path":"/",
        "fullPath":"www.msyc.cc/carambola/","children":null,"childList":["long","testLong01"]}
         */

    }


    @Test
    public void testGetCounterPathChildren03() throws MsOnionException {

        // 只是加载当前目录下的所有子目录，并没有递归

//        MsOnionZookeeperPathChild counterPathChildren =
//                MsOnionZookeeperUtils.getCounterRootPathCurrentChildren(curatorZookeeperClient);

        // 前面没有 "/" 也可以
//        String modulePath = "long";

        // 前面有 “/” 也可以
        String modulePath = "/long";

        MsOnionZookeeperPathChild counterPathChildren =
                MsOnionZookeeperUtils.getCounterPathCurrentChildren(curatorZookeeperClient, modulePath);

        System.out.println("测试 # testGetCounterPathChildren03 # counterPathChildren=" + counterPathChildren);

        // Json

        String json = MsOnionJsonUtils.objectToJson(counterPathChildren);

        System.out.println("测试 # testGetCounterPathChildren03 # json=" + json);

        // TODO: 不是一次性加载 使用 childList 获取数据，一次性加载（递归）使用 children

        /*

        测试 # testGetCounterPathChildren03 # counterPathChildren=MsOnionZookeeperPathChild{parentPath='www.msyc.cc/carambola/atomic', modulePath='long', path='long', fullPath='/www.msyc.cc/carambola/atomic/long/long', children=null, childList=[pro, dev]}

测试 # testGetCounterPathChildren03 #
 json={"parentPath":"www.msyc.cc/carambola/atomic","modulePath":"long","path":"long",
 "fullPath":"/www.msyc.cc/carambola/atomic/long/long","children":null,

 "childList":["pro","dev"]}

         */

    }

    @Test
    public void testPath08() throws MsOnionException {


//        curatorZookeeperClient.getCuratorFramework().getZookeeperClient().getZooKeeper();

        String modulePath = "long";

        List<String> counterRootBasePathChildren =
                MsOnionZookeeperUtils.getCounterRootBasePathChildren(curatorZookeeperClient);
//

        /*
        测试 # testPath08 # counterRootBasePathChildren=[long, testLong01]
         */
        System.out.println("测试 # testPath08 # counterRootBasePathChildren=" + counterRootBasePathChildren);


    }


}
