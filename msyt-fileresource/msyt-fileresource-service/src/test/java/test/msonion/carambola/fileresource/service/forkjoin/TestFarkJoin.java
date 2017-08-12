/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */


package test.msonion.carambola.fileresource.service.forkjoin;

/**
 * @Title: TestFarkJoin.java
 * @Package: test.msonion.carambola.fileresource.service.forkjoin
 * @Description: 测试
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月15日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月15日
 * @Modify-version: V2.0.0
 * @Modify-description:
 *
 */

import cc.msonion.carambola.fileresource.common.viewobject.AlbumViewObject;
import cc.msonion.carambola.fileresource.pojo.FileResourceUpload;
import cc.msonion.carambola.fileresource.service.ext.AblumTask;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionSystemUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionUUIDUtils;
import cc.msonion.carambola.parent.ext.utils.netty.MsOnionNettyUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

/**
 * @ClassName: TestFarkJoin
 * @Description: 测试
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月15日
 *
 */
public class TestFarkJoin {

    @Test
    public void test02() throws Exception{
        System.out.println(MsOnionSystemUtils.getUserDir());
        String path ="/sss/as/bb/cc/1.jpg";
        System.out.println(StringUtils.substring(path,0,(path.indexOf("bb")+"bb".length())));
    }

    @Test
    public void test01() throws Exception{


        /**
         * get()和join()有两个主要的区别：
         * join()方法同步返回,不能被中断。如果你中断调用join()方法的线程，这个方法将抛出InterruptedException异常。如果任务抛出任何未受检异常，
         * get()方法异步返回将返回一个ExecutionException异常，而join()方法将返回一个RuntimeException异常。
         */
        //同步返回结果
        //Future<Integer> result = pool.submit(new CountTask(0, 2000));
        //System.out.println(result.get());
        //异步返回结果
        List<FileResourceUpload> list = new ArrayList<>();
        for (int i = 1; i <=20 ; i++) {
            FileResourceUpload fru = new FileResourceUpload();
            fru.setIdx((long)i);
            fru.setName("test"+i+".jpg");
            fru.setAlbumType(MsOnionConstants.ALBUM_COLLECT);
            fru.setCreateTime(new Date());
            fru.setMessageId(MsOnionNettyUtils.generateMessageId());
            fru.setPath("\\test\\20170516\\20170516161440778_"+i+".png");
            list.add(fru);
        }
        long starTime= System.currentTimeMillis();
        AblumTask task = new AblumTask(list, "D:\\msyt-upload");
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        pool.shutdown();
        ArrayList<AlbumViewObject> dataList = task.get();
        System.out.println(MsOnionJsonUtils.objectToJson(dataList));
        System.out.println(dataList.size());
        // 313
        System.out.println(System.currentTimeMillis() - starTime + "ms");

        // 排序
       List<AlbumViewObject> dataList2 = dataList.parallelStream().filter(s -> s.getParentName().equals("test"))
                .sorted((p1, p2) -> p1.getCreateTime().compareTo(p2.getCreateTime())).collect(Collectors.toList());
        System.out.println(MsOnionJsonUtils.objectToJson(dataList2));

        // 统计






    }
}
