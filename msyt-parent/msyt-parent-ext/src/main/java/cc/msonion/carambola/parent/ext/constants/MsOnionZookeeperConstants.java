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

package cc.msonion.carambola.parent.ext.constants;

/**
 * @Title: MsOnionZookeeperConstants.java
 * @Package: cc.msonion.carambola.parent.ext.constants
 * @Description: 定义分布式全局唯一递增值的生成路径
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月25日 下午12:32:11
 * @Version: V2.0.0
 * @Modify-by: Johnny woo johnny-woo@msyc.cc
 * @Modify-date: 2017年7月5日 上午10:58:11
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：系统模块，物流中心模块，仓库中心模块
 */

/**
 * @ClassName: MsOnionZookeeperConstants
 * @Description: Zookeeper常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月25日 下午12:32:11
 */
public final class MsOnionZookeeperConstants {

    private MsOnionZookeeperConstants() {
    }

    /////////////////////////////////// 收集器模块 Begin ////////////////////////////////////

    /**
     * 收集器模块，商品表，主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_IDX_PATH = "/msyt-collector/item/idx";

    /**
     * 收集器模块，商品表，货号
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_NO_PATH = "/msyt-collector/item/no";

    /**
     * 收集器模块，商品表，版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_VERSION_PATH = "/msyt-collector/item/version";

    /**
     * 收集器模块，商品竞价表，主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_BIDDING_IDX_PATH = "/msyt-collector/itemBidding/idx";

    /**
     * 收集器模块，商品竞价表，版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_BIDDING_VERSION_IDX_PATH = "/msyt-collector/itemBidding/version";

    /**
     * 收集器模块，商品价格表，主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_PRICE_IDX_PATH = "/msyt-collector/itemPrice/idx";

    /**
     * 收集器模块，商品价格表，版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_PRICE_VERSION_IDX_PATH = "/msyt-collector/itemPrice/version";

    /**
     * 收集器模块，品牌主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_BRAND_IDX_PATH = "/msyt-collector/brand/idx";

    /**
     * 收集器模块，品牌编码
     */
    public static final String ZK_COUNTER_COLLECTOR_BRAND_CODE_PATH = "/msyt-collector/brand/code";

    /**
     * 收集器模块，品牌表，版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_BRAND_VERSION_PATH = "/msyt-collector/brand/version";

    /**
     * 收集器模块，类目主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_CATEGORY_IDX_PATH = "/msyt-collector/category/idx";

    /**
     * 收集器模块，类目版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_CATEGORY_VERSION_PATH = "/msyt-collector/category/version";

    /**
     * 收集器模块，产地主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ORIGIN_IDX_PATH = "/msyt-collector/origin/idx";

    /**
     * 收集器模块，产地版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ORIGIN_VERSION_PATH = "/msyt-collector/origin/version";

    /**
     * 收集器模块，属性主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ATTRIBUTE_IDX_PATH = "/msyt-collector/attribute/idx";

    /**
     * 收集器模块，属性版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ATTRIBUTE_VERSION_PATH = "/msyt-collector/attribute/version";

    /**
     * 收集器模块，属性组主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ATTRIBUTE_GROUP_IDX_PATH = "/msyt-collector/attributeGroup/idx";

    /**
     * 收集器模块，属性组版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ATTRIBUTE_GROUP_VERSION_PATH = "/msyt-collector/attributeGroup/version";

    /**
     * 收集器模块，属性组属性主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ATTRIBUTE_GROUP_ATTRIBUTE_IDX_PATH = "/msyt-collector/attributeGroupAttribute/idx";

    /**
     * 收集器模块，属性组属性版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ATTRIBUTE_GROUP_ATTRIBUTE_VERSION_PATH = "/msyt-collector/attributeGroupAttribute/version";

    /**
     * 收集器模块，采编主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_EDITOR_IDX_PATH = "/msyt-collector/editor/idx";

    /**
     * 收集器模块，采编版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_EDITOR_VERSION_PATH = "/msyt-collector/editor/version";

    /**
     * 收集器模块，商品属性主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_ATTRIBUTE_IDX_PATH = "/msyt-collector/itemAttribute/idx";

    /**
     * 收集器模块，商品属性版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_ATTRIBUTE_VERSION_PATH = "/msyt-collector/itemAttribute/version";

    /**
     * 收集器模块，商品关键属性图片主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_ATTRIBUTE_IMAGE_IDX_PATH = "/msyt-collector/itemAttributeImage/idx";

    /**
     * 收集器模块，商品关键属性图片版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_ATTRIBUTE_IMAGE_VERSION_IDX_PATH = "/msyt-collector/itemAttributeImage/version";

    /**
     * 收集器模块，商品历史主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_HISTORY_IDX_PATH = "/msyt-collector/itemHistory/idx";

    /**
     * 收集器模块，商品历史版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_HISTORY_VERSION_PATH = "/msyt-collector/itemHistory/version";

    /**
     * 收集器模块，商品采集主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_COLLECTION_IDX_PATH = "/msyt-collector/itemCollection/idx";

    /**
     * 收集器模块，商品采集版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_COLLECTION_VERSION_PATH = "/msyt-collector/itemCollection/version";

    /**
     * 收集器模块，商品内容主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_CONTENT_IDX_PATH = "/msyt-collector/itemContent/idx";

    /**
     * 收集器模块，商品内容版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_CONTENT_VERSION_PATH = "/msyt-collector/itemContent/version";

    /**
     * 收集器模块，商品图片主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_FORMAL_IMAGE_IDX_PATH = "/msyt-collector/itemFormalImages/idx";

    /**
     * 收集器模块，商品图片版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_FORMAL_IMAGE_VERSION_IDX_PATH = "/msyt-collector/itemFormalImages/version";

    /**
     * 收集器模块，条形码主键idx
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_BARCODE_IDX_PATH = "/msyt-collector/barcode/idx";

    /**
     * 收集器模块，条形码版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_ITEM_BARCODE_VERSION_IDX_PATH = "/msyt-collector/barcode/version";

    /**
     * 收集器模块，类目与属性组关联idx
     */
    public static final String ZK_COUNTER_COLLECTOR_CATEGORY_ATTRIBUTE_GROUP_IDX_PATH = "/msyt-collector/categoryAttributeGroup/idx";

    /**
     * 收集器模块，类目与属性组关联版本号
     */
    public static final String ZK_COUNTER_COLLECTOR_CATEGORY_ATTRIBUTE_GROUP_VERSION_IDX_PATH = "/msyt-collector/categoryAttributeGroup/version";

    /////////////////////////////////// 收集器模块 End ////////////////////////////////////////


    /////////////////////////////////// 商品模块 Begin ////////////////////////////////////

    /**
     * 商品模块，商品主键idx
     */
    public static final String ZK_COUNTER_ITEM_ITEM_IDX_PATH = "/msyt-item/item/idx";

    /**
     * 商品模块，商品版本号
     */
    public static final String ZK_COUNTER_ITEM_ITEM_VERSION_PATH = "/msyt-item/item/version";

    /**
     * 商品模块，商品属性值主键idx
     */
    public static final String ZK_COUNTER_ITEM_ITEM_ATTRIBUTE_IDX_PATH = "/msyt-item/itemAttribute/idx";

    /**
     * 商品模块，商品属性值版本号
     */
    public static final String ZK_COUNTER_ITEM_ITEM_ATTRIBUTE_VERSION_PATH = "/msyt-item/itemAttribute/version";

    /**
     * 商品模块，商品属性图片主键idx
     */
    public static final String ZK_COUNTER_ITEM_ITEM_ATTRIBUTE_IMAGE_IDX_PATH = "/msyt-item/itemAttributeImage/idx";

    /**
     * 商品模块，商品属性图片版本号
     */
    public static final String ZK_COUNTER_ITEM_ITEM_ATTRIBUTE_IMAGE_VERSION_PATH = "/msyt-item/itemAttributeImage/version";

    /**
     * 商品模块，商品采集主键idx
     */
    public static final String ZK_COUNTER_ITEM_ITEM_COLLECTION_IDX_PATH = "/msyt-item/itemCollection/idx";

    /**
     * 商品模块，商品采集版本号
     */
    public static final String ZK_COUNTER_ITEM_ITEM_COLLECTION_VERSION_PATH = "/msyt-item/itemCollection/version";

    /**
     * 商品模块，商品价格主键idx
     */
    public static final String ZK_COUNTER_ITEM_ITEM_PRICE_IDX_PATH = "/msyt-item/itemPrice/idx";

    /**
     * 商品模块，商品价格版本号
     */
    public static final String ZK_COUNTER_ITEM_ITEM_PRICE_VERSION_PATH = "/msyt-item/itemPrice/version";

    /**
     * 商品模块，正式商品主键idx
     */
    public static final String ZK_COUNTER_ITEM_ITEM_OFFICIAL_IDX_PATH = "/msyt-item/itemOfficial/idx";

    /**
     * 商品模块，正式商品版本号
     */
    public static final String ZK_COUNTER_ITEM_ITEM_OFFICIAL_VERSION_PATH = "/msyt-item/itemOfficial/version";


    /**
     * 商品模块，正式商品条形码主键idx
     */
    public static final String ZK_COUNTER_ITEM_BARCODE_OFFICIAL_IDX_PATH = "/msyt-item/itemBarcodeOfficial/idx";

    /**
     * 商品模块，正式商品条形码版本号
     */
    public static final String ZK_COUNTER_ITEM_BARCODE_OFFICIAL_VERSION_PATH = "/msyt-item/itemBarcodeOfficial/version";

    /**
     * 商品模块，商品日志表，主键idx
     */
    public static final String ZK_COUNTER_ITEM_LOG_IDX_PATH = "/msyt-item/itemLog/idx";

    /**
     * 商品模块，商品日志表，版本号
     */
    public static final String ZK_COUNTER_ITEM_LOG_VERSION_IDX_PATH = "/msyt-item/itemLog/version";

    /////////////////////////////////// 商品模块 End ////////////////////////////////////////


    /////////////////////////////////// 成员（Member）模块， Begin ////////////////////////////////////

    /**
     * 成员模块，成员表，主键idx
     */
    public static final String ZK_COUNTER_MEMBER_MEMBER_IDX_PATH = "/msyt-member/member/idx";

    /**
     * 成员模块，成员表，版本号idx
     */
    public static final String ZK_COUNTER_MEMBER_MEMBER_VESION_IDX_PATH = "/msyt-member/member/version";

    /**
     * 成员模块，菜单表，主键idx
     */
    public static final String ZK_COUNTER_MEMBER_MENU_IDX_PATH = "/msyt-member/menu/idx";

    /**
     * 成员模块，菜单表，版本号idx
     */
    public static final String ZK_COUNTER_MEMBER_MENU_VERSION_IDX_PATH = "/msyt-member/menu/version";

    /**
     * 成员模块，按钮表，主键idx
     */
    public static final String ZK_COUNTER_MEMBER_BUTTON_IDX_PATH = "/msyt-member/button/idx";

    /**
     * 成员模块，按钮表，版本号idx
     */
    public static final String ZK_COUNTER_MEMBER_BUTTON_VERSION_IDX_PATH = "/msyt-member/button/version";

    /**
     * 成员模块，用户组，主键idx
     */
    public static final String ZK_COUNTER_MEMBER_MEMBERCATE_IDX_PATH = "/msyt-member/memberCate/idx";

    /**
     * 成员模块，用户组，版本号idx
     */
    public static final String ZK_COUNTER_MEMBER_MEMBERCATE_VERSION_IDX_PATH = "/msyt-member/memberCate/version";

    /**
     * 成员模块，角色组，主键idx
     */
    public static final String ZK_COUNTER_MEMBER_ROLE_IDX_PATH = "/msyt-member/role/idx";

    /**
     * 成员模块，角色组，版本号idx
     */
    public static final String ZK_COUNTER_MEMBER_ROLE_VERSION_IDX_PATH = "/msyt-member/role/version";

    /**
     * 成员模块，用户与角色组，主键idx
     */
    public static final String ZK_COUNTER_MEMBER_MEMBERROLE_IDX_PATH = "/msyt-member/memberRole/idx";

    /**
     * 成员模块，用户与角色组，版本号idx
     */
    public static final String ZK_COUNTER_MEMBER_MEMBERROLE_VERSION_IDX_PATH = "/msyt-member/memberRole/version";

    /**
     * 成员模块，部门表，主键idx
     */
    public static final String ZK_COUNTER_MEMBER_DEPT_IDX_PATH = "/msyt-member/dept/idx";

    /**
     * 成员模块，部门表，版本号idx
     */
    public static final String ZK_COUNTER_MEMBER_DEPT_VERSION_IDX_PATH = "/msyt-member/dept/version";

    /**
     * 成员模块，资源表，主键idx
     */
    public static final String ZK_COUNTER_MEMBER_RESOURCE_IDX_PATH = "/msyt-member/resource/idx";

    /**
     * 成员模块，资源表，版本号idx
     */
    public static final String ZK_COUNTER_MEMBER_RESOURCE_VERSION_IDX_PATH = "/msyt-member/resource/version";

    /**
     * 成员模块，资源组表，主键idx
     */
    public static final String ZK_COUNTER_MEMBER_RESOURCEGROUP_IDX_PATH = "/msyt-member/resourceGroup/idx";

    /**
     * 成员模块，资源组表，版本号idx
     */
    public static final String ZK_COUNTER_MEMBER_RESOURCEGROUP_VERSION_IDX_PATH = "/msyt-member/resourceGroup/version";

    /**
     * 成员模块，角色与资源组关系表，主键idx
     */
    public static final String ZK_COUNTER_MEMBER_ROLERESOURCEGROUP_IDX_PATH = "/msyt-member/roleResourceGroup/idx";

    /**
     * 成员模块，角色与资源组关系表，版本号idx
     */
    public static final String ZK_COUNTER_MEMBER_ROLERESOURCEGROUP_VERSION_IDX_PATH = "/msyt-member/roleResourceGroup/version";

    /**
     * 成员模块，成员编码
     */
    public static final String ZK_COUNTER_MEMBER_CODE_PATH = "/msyt-member/member/code";

    /////////////////////////////////// 成员（Member）模块， End ////////////////////////////////////


    /////////////////////////////////// 用户（User）模块 ，Begin ////////////////////////////////////

    /**
     * 用户模块，用户编码
     */
    public static final String ZK_COUNTER_USER_CODE_PATH = "/msyt-user/user/code";

    /////////////////////////////////// 用户（User）模块，End ////////////////////////////////////


    ////////////////////////////////// 内容（Content）模块，Begin //////////////////////////////////////////////////////

    /**
     * 内容模块，内容表，主键idx
     */
    public static final String ZK_COUNTER_CONTENT_IDX_PATH = "/msyt-content/content/idx";

    /**
     * 内容模块，内容表，版本号idx
     */
    public static final String ZK_COUNTER_CONTENT_VERSION_IDX_PATH = "/msyt-content/content/version";

    /**
     * 内容模块，内容图片表，主键idx
     */
    public static final String ZK_COUNTER_CONTENT_IMAGE_IDX_PATH = "/msyt-content/contentImage/idx";

    /**
     * 内容模块，内容图片表，版本号idx
     */
    public static final String ZK_COUNTER_CONTENT_IMAGE_VERSION_IDX_PATH = "/msyt-content/contentImage/version";

    /**
     * 内容模块，内容关键词模块，主键idx
     */
    public static final String ZK_COUNTER_CONTENT_KEYWORD_IDX_PATH = "/msyt-content/contentKeyword/idx";

    /**
     * 内容模块，内容关键词模块，版本号idx
     */
    public static final String ZK_COUNTER_CONTENT_KEYWORD_VERSION_IDX_PATH = "/msyt-content/contentKeyword/version";

    ////////////////////////////////// 内容（Content）模块，End //////////////////////////////////////////////////////


    ////////////////////////////////// 系统（System）模块，Begin //////////////////////////////////////////////////////

    /**
     * 系统模块，数据字典表，主键idx
     */
    public static final String ZK_COUNTER_SYSTEM_DICT_IDX_PATH = "/msyt-system/sysDict/idx";

    /**
     * 系统模块，数据字典，版本号
     */
    public static final String ZK_COUNTER_SYSTEM_DICT_VERSION_IDX_PATH = "/msyt-system/sysDict/version";

    /**
     * 系统模块，后台登录日志表，主键idx
     */
    public static final String ZK_COUNTER_SYSTEM_LOGINLOG_IDX_PATH = "/msyt-system/sysLoginLog/idx";

    /**
     * 系统模块，后台登录日志表，版本号
     */
    public static final String ZK_COUNTER_SYSTEM_LOGINLOG_VERSION_IDX_PATH = "/msyt-system/sysLoginLog/version";

    /**
     * 系统模块，RocketMQ 消费信息，主键idx
     */
    public static final String ZK_COUNTER_SYSTEM_CONSUME_MESSAGE_IDX_PATH = "/msyt-system/sysConsumeMessage/idx";

    /**
     * 系统模块，RocketMQ 消费信息，版本号
     */
    public static final String ZK_COUNTER_SYSTEM_CONSUME_MESSAGE_VERSION_PATH = "/msyt-system/sysConsumeMessage/version";

    /**
     * 系统模块，系统设置表，主键idx
     */
    public static final String ZK_COUNTER_SYSTEM_SYSSETTING_IDX_PATH = "/msyt-system/sysSetting/idx";

    /**
     * 系统模块，系统设置表，version
     */
    public static final String ZK_COUNTER_SYSTEM_SYSSETTING_VERSION_IDX_PATH = "/msyt-system/sysSetting/version";


    /**
     * 系统模块，验证码图片表，主键idx
     */
    public static final String ZK_COUNTER_SYSTEM_VERIFY_CODE_PIC_IDX_PATH = "/msyt-system/verifyCodePicture/idx";

    /**
     * 系统模块，验证码图片表，版本号
     */
    public static final String ZK_COUNTER_SYSTEM_VERIFY_CODE_PIC_VERSION_IDX_PATH = "/msyt-system/verifyCodePicture/version";

    ////////////////////////////////// 系统（System）模块，End //////////////////////////////////////////////////////


    ////////////////////////////////// 物流中心（Logistics）模块，Begin //////////////////////////////////////////////////////

    /**
     * 物流中心模块，商品报关主键idx
     */
    public static final String ZK_COUNTER_LOGISTICS_CUSTOMS_DECLARE_IDX_PATH = "/msyt-logistics/customsDeclare/idx";

    /**
     * 物流中心模块，商品报关版本号
     */
    public static final String ZK_COUNTER_LOGISTICS_CUSTOMS_DECLARE_VERSION_PATH = "/msyt-logistics/customsDeclare/version";

    /**
     * 物流中心模块，商品报关主键idx
     */
    public static final String ZK_COUNTER_LOGISTICS_CUSTOMS_DECLARE_OFFICIAL_IDX_PATH = "/msyt-logistics/customsDeclareOfficial/idx";

    /**
     * 物流中心模块，商品报关版本号
     */
    public static final String ZK_COUNTER_LOGISTICS_CUSTOMS_DECLARE_OFFICIAL_VERSION_PATH = "/msyt-logistics/customsDeclareOfficial/version";

    /**
     * 物流中心模块，计量单位主键idx
     */
    public static final String ZK_COUNTER_LOGISTICS_UNIT_IDX_PATH = "/msyt-logistics/unit/idx";

    /**
     * 物流中心模块，计量单位版本号
     */
    public static final String ZK_COUNTER_LOGISTICS_UNIT_VERSION_PATH = "/msyt-logistics/unit/version";

    ////////////////////////////////// 物流中心（Logistics）模块，End //////////////////////////////////////////////////////


    ////////////////////////////////// 仓库中心（Warehouse）模块，Begin //////////////////////////////////////////////////////

    /**
     * 仓库中心模块，商品仓库表主键idx
     */
    public static final String ZK_COUNTER_WAREHOUSE_WAREHOUSE_IDX_PATH = "/msyt-warehouse/warehouse/idx";

    /**
     * 仓库中心模块，商品仓库表版本号
     */
    public static final String ZK_COUNTER_WAREHOUSE_WAREHOUSE_VERSION_PATH = "/msyt-warehouse/warehouse/version";

    /**
     * 仓库中心模块，正式商品仓库表主键idx
     */
    public static final String ZK_COUNTER_WAREHOUSE_WAREHOUSE_OFFICIAL_IDX_PATH = "/msyt-warehouse/warehouseOfficial/idx";

    /**
     * 仓库中心模块，正式商品仓库表版本号
     */
    public static final String ZK_COUNTER_WAREHOUSE_WAREHOUSE_OFFICIAL_VERSION_PATH = "/msyt-warehouse/warehouseOfficial/version";

    /**
     * 仓库中心模块，商品仓库类型表主键idx
     */
    public static final String ZK_COUNTER_WAREHOUSE_WAREHOUSETYPE_IDX_PATH = "/msyt-warehouse/warehouseType/idx";

    /**
     * 仓库中心模块，商品仓库类型表版本号
     */
    public static final String ZK_COUNTER_WAREHOUSE_WAREHOUSETYPE_VERSION_PATH = "/msyt-warehouse/warehouseType/version";

    ////////////////////////////////// 仓库中心（Warehouse）模块，End //////////////////////////////////////////////////////

    ////////////////////////////////// 文件资源（fileresource）模块，begin //////////////////////////////////////////////////////
    /**
     * 文件资源中心，上传记录，主键idx
     */
    public static final String ZK_COUNTER_FILERESOURCE_UPLOAD_IDX_PATH = "/msyt-fileresource/upload/idx";

    /**
     * 文件资源中心，上传记录，版本号
     */
    public static final String ZK_COUNTER_FILERESOURCE_UPLOAD_VERSION_IDX_PATH = "/msyt-fileresource/upload/version";

    /**
     * 文件资源中心，下载记录，主键idx
     */
    public static final String ZK_COUNTER_FILERESOURCE_DOWNLOAD_IDX_PATH = "/msyt-fileresource/download/idx";

    /**
     * 文件资源中心，下载记录，版本号
     */
    public static final String ZK_COUNTER_FILERESOURCE_DOWNLOAD_VERSION_IDX_PATH = "/msyt-fileresource/download/version";


    /**
     * 文件资源中心，相册库，主键idx
     */
    public static final String ZK_COUNTER_FILERESOURCE_ABLUMLIB_IDX_PATH = "/msyt-fileresource/ablumLib/idx";

    /**
     * 文件资源中心，相册库，版本号
     */
    public static final String ZK_COUNTER_FILERESOURCE_ABLUMLIB_VERSION_IDX_PATH = "/msyt-fileresource/ablumLib/version";

    /**
     * 文件资源中心，文件模板，主键idx
     */
    public static final String ZK_COUNTER_FILERESOURCE_TEMPLATE_IDX_PATH = "/msyt-fileresource/template/idx";

    /**
     * 文件资源中心，文件模板，版本号
     */
    public static final String ZK_COUNTER_FILERESOURCE_TEMPLATE_VERSION_IDX_PATH = "/msyt-fileresource/template/version";


    ////////////////////////////////// 文件资源（fileresource）模块，end //////////////////////////////////////////////////////

}
