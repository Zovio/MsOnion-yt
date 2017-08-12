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
 * @Title: MsOnionConstants.java
 * @Package: cc.msonion.carambola.parent.common.constants
 * @Description: 常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月26日 下午7:53:58
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月26日 下午7:53:58
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：字符集编码
 */

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: MsOnionConstants
 * @Description: 常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月26日 下午7:53:58
 */
public final class MsOnionConstants {

    private MsOnionConstants() {
    }

    /**
     * @Fields CHARSET_ISO8859_1 : 字符编码：ISO8859-1
     */
    public static final String CHARSET_ISO8859_1 = "ISO8859-1";

    /**
     * @Fields CHARSET_UTF8 :  字符编码：UTF8
     */
    public static final String CHARSET_UTF8 = "UTF8";

    /**
     * @Fields CHARSET_UTF_8 : 字符编码：UTF-8
     */
    public static final String CHARSET_UTF_8 = "UTF-8";

    /**
     * http
     */
    public static final String SCHEME_HTTP = "http";

    /**
     * http://
     */
    public static final String HTTP_ALL = "http://";

    /**
     * https
     */
    public static final String SCHEME_HTTPS = "https";

    /**
     * https://
     */
    public static final String HTTPS_ALL = "https://";

    /**
     * 端口：80
     */
    public static final int PORT_80 = 80;

    //////////////////////  Redis ， Begin ////////////////

    /**
     * Redis缓存周期最大值
     */
    public static final int REDIS_EXPIRE_MAX_VALUE = Integer.MAX_VALUE;

    /**
     * Redis缓存周期最小值
     */
    public static final int REDIS_EXPIRE_MIN_VALUE = 0;

    /**
     * Redis的Key非法，包括 "::"
     */
    public static final String REDIS_KEY_ILLEGAL_MULTI_COLON = "::";

    /**
     * Redis的Key的":"
     */
    public static final String REDIS_KEY_COLON = ":";

    /**
     * 目前图片存在数据中id和 url分割方式
     */
    public static final String IMAGE_KEY_COLON = "\\|\\|";

    /**
     * 默认版本号
     */
    public static final String API_VERSION = "V2.0.0";


    /**
     * 默认线程池大小
     */
    public static final int POOL_SIZE = 20;

    //////////////////////  Redis ， End ////////////////

    /**
     * 分布式文件系统：OSS（阿里云）
     */
    public static final String DFS_OSS = "OSS";

    /**
     * 分布式文件系统：FastDFS
     */
    public static final String DFS_FDFS = "FastDFS";

    /**
     * 分布式文件系统FastDFS的服务器协议和域名：http://yt02-dev/
     */
    public static final String DFS_FDFS_PROTOCOL_AND_DOMAIN = "http://yt02-dev/";

    /**
     * @Fields YES : 1 (是)
     */
    public static final String YES = "1";


    /**
     * @Fields NO : 0 （否）
     */
    public static final String NO = "0";


    /**
     * 资源类型
     */
    public static final List FILE_RESOURCE_TYPES = Arrays.asList(
            new String[]{"jpg", "gif", "bmp", "png", "jpeg", "pdf", "txt",
                    "chm", "zip", "rar", "docx", "doc", "xls", "xlsx", "pptx", "ppt", "mp4", "avi"});

    /**
     * @Fields 图片类型
     */
    public static final List FILE_PICTURE_TYPES = Arrays.asList(
            new String[]{"jpg", "gif", "bmp", "png", "jpeg"});

    /**
     * @Fields 图片上传大小
     */
    public static final long FILE_PICTURE_MAX_SIZE = 1024 * 1024;

    /**
     * @Fields 注册用户名铭感词
     */
    public static final String[] NAME_ARRS = {"admin", "super"};

    /**
     * 图片相册类型 1：采集相册
     */
    public static final Short ALBUM_COLLECT = 1;

    /**
     * 采集相册图片路径以什么开头
     */
    public static final String ALBUM_COLLECT_PATH_START = "/collect-item-images";

    /**
     * 图片相册类型 2：正式相册
     */
    public static final Short ALBUM_FORMAL = 2;

    /**
     * 正式相册图片路径以什么开头
     */
    public static final String ALBUM_FORMAL_PATH_START = "/fmal-item-images";

    /**
     * 1
     */
    public static final int ONE = 1;

    /**
     * 2
     */
    public static final int TWO = 2;

    /**
     * 3
     */
    public static final int THREE = 3;

    /**
     * 4
     */
    public static final int FOUR = 4;

    /**
     * 百分号
     */
    public static final String PERCENT = "%";

    /**
     * 验证码图片的宽度
     */
    public static final int VERIFY_CODE_PIC_WIDTH = 68;


    /**
     * 重试次数
     */
    public static final int RETRY_COUNT = 3;

    /**
     * 默认的Redis缓存周期：1小时：60 * 60 = 3600
     */
    public static final int DEFAULT_REDIS_CACHE_EXPIRE = 3600;

    /**
     * 默认的Redis缓存周期，最小值：1秒
     */
    public static final int REDIS_CACHE_EXPIRE_MIN = 1;

    /**
     * 默认的Redis缓存周期，最大值：10天
     * <p># Redis Global Expire : 60 * 60 * 24 => 86400，60 * 60 * 24 * 10 = 10 days</p>
     */
    public static final int REDIS_CACHE_EXPIRE_MAX = 86400;

}
