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

package cc.msonion.carambola.parent.common.constants;

/**
 * @Title: MsOnionImageConstants.java
 * @Package: cc.msonion.carambola.parent.common.constants
 * @Description: 图片常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月21日 下午7:53:58
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年4月21日 下午7:53:58
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：字符集编码
 */

/**
 * @ClassName: MsOnionImageConstants
 * @Description: 图片常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月21日 下午7:53:58
 *
 */
public final class MsOnionImageConstants {

    private MsOnionImageConstants() {
    }

//    /**
//     * 分布式文件系统：OSS（阿里云）
//     */
//    public static final String DFS_OSS = "OSS";
//
//    /**
//     * 分布式文件系统：FastDFS
//     */
//    public static final String DFS_FDFS = "FastDFS";
//
//    /**
//     * 分布式文件系统FastDFS的服务器协议和域名：http://yt02-dev/
//     */
//    public static final String DFS_FDFS_PROTOCOL_AND_DOMAIN = "http://yt02-dev/";

    //////////////////////////////  相似图片搜索， Begin ////////////////////

    // 正常 Normal ：就是上传的图片，不管方向怎么样，都是以这个为基础，后面旋转
    // 左旋转90: Rotate left 90
    // 右旋转90：Rotate right 90
    // 旋转180：Rotate 180
    // 垂直翻转： Flip vertical
    // 水平翻转：Horizontal flip
    // // 左旋转45: Rotate left 45
    // 右旋转90：Rotate right 90

    /**
     * 旋转0：0.0d
     */
    public static final double IMAGE_ROTATE_0 = 0.0d;

    /**
     * 左旋转15：-15.0d
     */
    public static final double IMAGE_ROTATE_LEFT_15 = -15.0d;

    /**
     * 左旋转30：-30.0d
     */
    public static final double IMAGE_ROTATE_LEFT_30 = -30.0d;

    /**
     * 左旋转45：-45.0d
     */
    public static final double IMAGE_ROTATE_LEFT_45 = -45.0d;

    /**
     * 左旋转60：-60.0d
     */
    public static final double IMAGE_ROTATE_LEFT_60 = -60.0d;

    /**
     * 左旋转75：-75.0d
     */
    public static final double IMAGE_ROTATE_LEFT_75 = -75.0d;

    /**
     * 左旋转90：-90.0d
     */
    public static final double IMAGE_ROTATE_LEFT_90 = -90.0d;

    /**
     * 左旋转105：-105.0d
     */
    public static final double IMAGE_ROTATE_LEFT_105 = -105.0d;

    /**
     * 左旋转120：-120.0d
     */
    public static final double IMAGE_ROTATE_LEFT_120 = -120.0d;

    /**
     * 左旋转135：-135.0d
     */
    public static final double IMAGE_ROTATE_LEFT_135 = -135.0d;

    /**
     * 左旋转150：-150.0d
     */
    public static final double IMAGE_ROTATE_LEFT_150 = -150.0d;

    /**
     * 左旋转165：-165.0d
     */
    public static final double IMAGE_ROTATE_LEFT_165 = -165.0d;

    //////////  左旋转180 和 右旋转 180 都是一样的，因此，有右旋转就可以了

    /**
     * 右旋转15：15.0d
     */
    public static final double IMAGE_ROTATE_RIGHT_15 = 15.0d;

    /**
     * 右旋转30：30.0d
     */
    public static final double IMAGE_ROTATE_RIGHT_30 = 30.0d;

    /**
     * 右旋转45：45.0d
     */
    public static final double IMAGE_ROTATE_RIGHT_45 = 45.0d;

    /**
     * 右旋转60：60.0d
     */
    public static final double IMAGE_ROTATE_RIGHT_60 = 60.0d;

    /**
     * 右旋转75：75.0d
     */
    public static final double IMAGE_ROTATE_RIGHT_75 = 75.0d;

    /**
     * 右旋转90：90.0d
     */
    public static final double IMAGE_ROTATE_RIGHT_90 = 90.0d;

    /**
     * 右旋转105：105.0d
     */
    public static final double IMAGE_ROTATE_RIGHT_105 = 105.0d;

    /**
     * 右旋转120：120.0d
     */
    public static final double IMAGE_ROTATE_RIGHT_120 = 120.0d;

    /**
     * 右旋转135：135.0d
     */
    public static final double IMAGE_ROTATE_RIGHT_135 = 135.0d;

    /**
     * 右旋转150：150.0d
     */
    public static final double IMAGE_ROTATE_RIGHT_150 = 150.0d;

    /**
     * 右旋转165：165.0d
     */
    public static final double IMAGE_ROTATE_RIGHT_165 = 165.0d;

    /**
     * 右旋转180：180.0d
     */
    public static final double IMAGE_ROTATE_RIGHT_180 = 180.0d;

    /**
     * 图片旋转角度数组
     */
    public static final double[] IMAGE_ROTATE_ANGLES = {0, IMAGE_ROTATE_LEFT_15, IMAGE_ROTATE_LEFT_30,
            IMAGE_ROTATE_LEFT_45, IMAGE_ROTATE_LEFT_60, IMAGE_ROTATE_LEFT_75, IMAGE_ROTATE_LEFT_90,
            IMAGE_ROTATE_LEFT_105, IMAGE_ROTATE_LEFT_120, IMAGE_ROTATE_LEFT_135, IMAGE_ROTATE_LEFT_150,
            IMAGE_ROTATE_LEFT_165, IMAGE_ROTATE_RIGHT_15, IMAGE_ROTATE_RIGHT_30,
            IMAGE_ROTATE_RIGHT_45, IMAGE_ROTATE_RIGHT_60, IMAGE_ROTATE_RIGHT_75, IMAGE_ROTATE_RIGHT_90,
            IMAGE_ROTATE_RIGHT_105, IMAGE_ROTATE_RIGHT_120, IMAGE_ROTATE_RIGHT_135, IMAGE_ROTATE_RIGHT_150,
            IMAGE_ROTATE_RIGHT_165, IMAGE_ROTATE_RIGHT_180};


    //////////////////////////////  相似图片搜索， End ////////////////////



}
