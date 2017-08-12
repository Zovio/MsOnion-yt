package test.msonion.carambola.commons.common.zk;

import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionZookeeperUtils;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionRandomUtils;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import cc.msonion.carambola.parent.pojo.zk.retrypolicy.MsOnionRetryNTimes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by HeroCao on 2017/7/8.
 */
@ContextConfiguration(locations = "classpath:dev/spring/applicationContext-*.xml")
public class TestIdxCode extends AbstractJUnit4SpringContextTests {

    @Autowired
    private MsOnionDomain domain;

    @Autowired
    private MsOnionCuratorZookeeperClient curatorZookeeperClient;

    @Autowired
    private MsOnionRetryNTimes retryNTimes;

    @Test
    public void test01() throws MsOnionException {

        System.out.println("测试生成 idxCode # test01 ...");

        long idxCode = MsOnionZookeeperUtils.getIdxCode(domain, curatorZookeeperClient, retryNTimes);

        System.out.println("测试生成 idxCode # test01 ## idxCode=" + idxCode);
    }

    @Test
    public void test02() throws MsOnionException {

        System.out.println("测试生成 idxCode # test01 ...");

//        for (int i = 0; i < 100; i++) {
        for (int i = 0; i < 10; i++) {

            long idxCode = MsOnionZookeeperUtils.getIdxCode(domain, curatorZookeeperClient, retryNTimes);

            System.out.println("测试生成 idxCode # test01 ## idxCode=" + idxCode);

        }

        System.out.println("测试生成 idxCode # test01 ， the End !!!");

    }



    @Test
    public void testRandom01() throws MsOnionException {

        System.out.println("测试生成 idxCode # testRandom01 ...");

        for (int i = 0; i < 100; i++) {

            int random = MsOnionRandomUtils.getRandom(0, 10);

            System.out.println("测试生成 idxCode # testRandom01 # random=" + random);
        }

        System.out.println("测试生成 idxCode # testRandom01 , the End !!!");

    }

    @Test
    public void testRandom02() {

        System.out.println("测试生成 idxCode # testRandom02 ...");

        int[] steps = {1, 2, 3, 4, 5};

        int[] shuffle = MsOnionRandomUtils.shuffle(steps);


        System.out.println("测试生成 idxCode # testRandom02 # steps=" + steps);

        System.out.println("测试生成 idxCode # testRandom02 # shuffle=" + shuffle);

        System.out.println("测试生成 idxCode # testRandom02 # shuffle[0]=" + shuffle[0]);


    }


    @Test
    public void testRandom03() {

        System.out.println("测试生成 idxCode # testRandom03 ...");

        int[] steps = {1, 2, 3, 4, 5};



        for (int i = 0; i < 100; i++) {

            int[] shuffle = MsOnionRandomUtils.shuffle(steps);

            System.out.println("测试生成 idxCode # testRandom03 # steps=" + steps);

            System.out.println("测试生成 idxCode # testRandom03 # shuffle=" + shuffle);

            System.out.println("测试生成 idxCode # testRandom03 # shuffle.length=" + shuffle.length);

        }

        /*
        测试生成 idxCode # testRandom03 # shuffle=[I@7d9d0818
测试生成 idxCode # testRandom03 # shuffle.length=0
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@221a3fa4
测试生成 idxCode # testRandom03 # shuffle.length=4
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@451001e5
测试生成 idxCode # testRandom03 # shuffle.length=2
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@2b40ff9c
测试生成 idxCode # testRandom03 # shuffle.length=3
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@3e08ff24
测试生成 idxCode # testRandom03 # shuffle.length=2
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@4d1c005e
测试生成 idxCode # testRandom03 # shuffle.length=3
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@8462f31
测试生成 idxCode # testRandom03 # shuffle.length=4
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@24569dba
测试生成 idxCode # testRandom03 # shuffle.length=4
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@5ddeb7cb
测试生成 idxCode # testRandom03 # shuffle.length=4
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@70ed52de
测试生成 idxCode # testRandom03 # shuffle.length=3
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@496bc455
测试生成 idxCode # testRandom03 # shuffle.length=3
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@59402b8f
测试生成 idxCode # testRandom03 # shuffle.length=4
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@7188af83
测试生成 idxCode # testRandom03 # shuffle.length=1
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@6be968ce
测试生成 idxCode # testRandom03 # shuffle.length=2
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@7c37508a
测试生成 idxCode # testRandom03 # shuffle.length=3
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@247310d0
测试生成 idxCode # testRandom03 # shuffle.length=0
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@1033576a
测试生成 idxCode # testRandom03 # shuffle.length=2
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@303cf2ba
测试生成 idxCode # testRandom03 # shuffle.length=2
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@76494737
测试生成 idxCode # testRandom03 # shuffle.length=3
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@4a003cbe
测试生成 idxCode # testRandom03 # shuffle.length=4
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@4082ba93
测试生成 idxCode # testRandom03 # shuffle.length=2
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@17fc391b
测试生成 idxCode # testRandom03 # shuffle.length=3
测试生成 idxCode # testRandom03 # steps=[I@4be29ed9
测试生成 idxCode # testRandom03 # shuffle=[I@2b30a42c
测试生成 idxCode # testRandom03 # shuffle.length=1

         */

    }

}
