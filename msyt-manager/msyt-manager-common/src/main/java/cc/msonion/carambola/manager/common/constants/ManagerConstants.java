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

package cc.msonion.carambola.manager.common.constants;


/**
 * @Title: ManagerConstants.java
 * @Package: cc.msonion.carambola.manager.common.constants
 * @Description: Manager常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月28日 下午8:50:01
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月28日 下午8:50:01
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建、版本号
 */

/**
 * @ClassName: ManagerConstants
 * @Description: Manager常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月28日 下午8:50:01
 */
public final class ManagerConstants {

    private ManagerConstants() {
    }

    /**
     * 管理后台系统API 版本号
     */
    public static final String API_VERSION = "V2.0.0";

    /**
     * 管理后台系统member存在session中key
     */
    public static final String SESSION_MEMBER = "member$msyt";

    /**
     * 管理后台系统urlMapping存在session中key
     */
    public static final String SESSION_URL_MAPPING = "urlMappingList$msyt";

    /**
     * 管理后台系统menu存在session中key
     */
    public static final String SESSION_MENU = "menu$msyt";

    /**
     * 管理后台系统首页顶部展示 menu存在session中key
     */
    public static final String SESSION_TOP_SHOW_MENU = "topshow$msyt";

    /**
     * 管理后台系统  登录menu Json存在session中key
     */
    public static final String SESSION_MENU_JSON = "menuJson$msyt";

    /**
     * 管理后台系统  菜单列表menu JSON存在request的key
     */
    public static final String REQ_MENU_JSON = "menuListJson$msyt";

    /**
     * 管理后台系统  菜单列表menu list
     */
    public static final String REQ_MENU_LIST = "menuList$msyt";


    /**
     * 管理后台系统 CSS版本号 存在session中key
     */
    public static final String SESSION_CSS_VERSION = "css$version";

    /**
     * 管理后台系统 JS版本号 存在session中key
     */
    public static final String SESSION_JS_VERSION = "js$version";

    /**
     * 管理后台系统 IMG版本号 存在session中key
     */
    public static final String SESSION_IMG_VERSION = "img$version";

    /**
     * 管理后台系统 静态资源域名
     */
    public static final String SESSION_STATIC_DOMAIN = "static$domain";


//    /**
//     * 管理后台系统 CSS版本号 存在redis缓存中key
//     */
//    public static final String REDIS_CSS_VERSION_KEY = "MEMBER_INFO:MEMBER:STATIC:CSSVERSION";


    /**
     * 新增操作
     */
    public static final String ADD = "add";

    /**
     * 编辑操作
     */
    public static final String EDIT = "edit";

    /**
     * 查看操作
     */
    public static final String VIEW = "view";

    /**
     * 30分钟 毫秒单位
     */
    public static final long THIRTY_MIN = 1800000L;

    /**
     * 15分钟 毫秒单位
     */
    public static final long FIFTEEN_MIN = 900000L;

    /**
     * 30分钟 秒单位
     */
    public static final int THIRTY_MIN_SECOND = 1800;

    /**
     * 15分钟 秒单位
     */
    public static final int FIFTEEN_MIN_SECOND = 900;


    /**
     * 查询默认排序条件 （修改时间降序）
     */
    public static final String ORDER_BY_UPDATE_TIME = "update_time desc";


    /**
     * zindex 升序，有于改字段的表格
     */
    public static final String ORDER_BY_ZINDEX_ASC = "zindex asc,update_time desc";

    /**
     * zindex 降序，有于改字段的表格
     */
    public static final String ORDER_BY_ZINDEX_DESC = "zindex desc,update_time desc";


    /**
     * 菜单类型
     */
    public static final Short MENU_TYPE = 1;

    /**
     * 按钮类型
     */
    public static final Short BUTTON_TYPE = 2;

    /**
     * 线程池大小
     */
    public static final Integer THREAD_SIZE = 2;

    /**
     * 是
     */
    public static final String YES = "1";

    /**
     * 否
     */
    public static final String NO = "0";

    /**
     * 百分号
     */
    public static final String PERCENT = "%";

    /**
     * $$
     */
    public static final String DOLLAR = "$$";

    /**
     * $$
     */
    public static final String DOLLAR_ESCAPE = "\\$\\$";

    /**
     * 斜杠
     */
    public static final String SLASH = "/";

    /**
     * 空格
     */
    public static final String SPACE = " ";

    /**
     * 用户修改密码权限
     */
    public static final String MEMBER_EDIT_PWD = "member:editpwd";

    /**
     * 商城链接内容key
     */
    public static final String SHOP_INDEX_LINK = "manage:yt_index";

    /**
     * 洋葱海外仓商城链接内容key
     */
    public static final String YC_INDEX_LINK = "manage:yc_index";

    /**
     * 洋葱官网链接内容key
     */
    public static final String YC_WWW_LINK = "manage:yc_www";

    /**
     * 洋葱官网链接内容key
     */
    public static final String YT_SYSTEM_INDEX_CONTENT = "system:index";


    /**
     * 版权信息内容key
     */
    public static final String MANAGE_COPYRIGHT = "manage:copyright";

    /**
     * 资源下载文件配置
     */
    public static final String REASOURCE_DOWNLOAD_FILE = "/config/config-resourcedownload.conf";

    /**
     * topshow
     */
    public static final String TOP_SHOW = "topshow";

    /**
     * 默认6条
     */
    public static final String TOP_SHOW_NUM = "6";

    /**
     * 1分钟 秒单位
     */
    public static final int VERIFYCODE_EXPIRE_TIME = 180;

    /**
     * 新增商品
     */
    public static final String[] EXCEL_TEMPLATE_ITEM_TITLE = new String[]{"商品ID", "条形码", "中文品名",
            "英文品名", "品牌", "产地", "亚马逊售价", "天猫售价", "京东售价", "小红书售价", "国外官网价", "中国官网价",
            "原产国售价", "考拉售价", "聚美售价", "批次", "权重", "销售状态", "采集备注"};

    /**
     * 商品价格
     */
    public static final String[] EXCEL_TEMPLATE_ITEM_PRICE_TITLE = new String[]{"商品ID", "货号", "市场价", "售价", "供货价"};

    /**
     * 商品报关
     */
    public static final String[] EXCEL_TEMPLATE_CUSTOMS_TITLE = new String[]{"商品ID", "报关品名", "第一数量",
            "第二数量", "第一单位", "第二单位", "行邮税率", "跨境税率", "BC价", "HS编码", "毛重", "行邮税号",
            "是否含有消费税"};

    /**
     * 正式商品基本信息表别名
     */
    public static final String ITEM_BASIC_OFFICIAL_TABLE_ALIAS = "tibo.";

    /**
     * 商品ID
     */
    public static final String ITEM_IDX = "itemIdx";

    /**
     * 正式商品表别名
     */
    public static final String ITEM_OFFICIAL_TABLE_ALIAS = "tio.";

    /**
     * 正式商品表默认排序
     */
    public static final String ITEM_OFFICIAL_TABLE_DEFAULT_ORDER = ITEM_OFFICIAL_TABLE_ALIAS + ORDER_BY_UPDATE_TIME;

    /**
     * 正式商品表别名
     */
    public static final String UEDITOR_UP_PATH_DEFAUL = "/ueditor";

    /**
     * cookie验证码对应的key
     */
    public static final String VERIFYCODE_COOKIE_NAME = "vcode.ms.yt.msyt.cc";

    /**
     * cookie验证码对应的key
     */
    public static final String PUBLISH_ITEMOFFICIAL_FAIL = "请先到预发布商品发布该商品";


    /**
     * 文件模板：报关信息
     */
    public static final Short FILE_TEMPLATE_LOGISTICS = 1;

    /**
     * 文件模板：设置价格
     */
    public static final Short FILE_TEMPLATE_PRICE = 2;

    /**
     * 文件模板：新增商品
     */
    public static final Short FILE_TEMPLATE_ITEM = 3;


}
