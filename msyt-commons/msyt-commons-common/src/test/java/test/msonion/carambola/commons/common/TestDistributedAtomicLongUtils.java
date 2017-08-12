package test.msonion.carambola.commons.common;

import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import org.junit.Test;

/**
 * Created by HeroCao on 2017/4/7.
 */
public class TestDistributedAtomicLongUtils {

    @Test
    public void test01() throws Exception {


        MsOnionDomain domain = new MsOnionDomain();


        String path1 = MsOnionDistributedAtomicLongUtils.generateZKCounterPath(domain, "/msyt-test-01");

        String path2 = MsOnionDistributedAtomicLongUtils.generateZKCounterPath(domain, "msyt-test-02");


        System.out.println("path1=" + path1);

        System.out.println("path2=" + path2);

//        MsOnionDistributedAtomicLongUtils.getVersion()

//        MsOnionDistributedAtomicLongUtils.get

        MsOnionCuratorZookeeperClient client = null;

//        client.getCuratorFramework().getConnectionStateListenable()

//        client.getCuratorFramework().getZookeeperClient().getZooKeeper()


//        client.getCuratorFramework().getData().


//        client.getCuratorFramework().getZookeeperClient().getZooKeeper()

//        String zkRootCounterPath = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_PATH;
//
//        List<String> children = client.getCuratorFramework().getZookeeperClient().getZooKeeper().getChildren(zkRootCounterPath, true);






    }






}
