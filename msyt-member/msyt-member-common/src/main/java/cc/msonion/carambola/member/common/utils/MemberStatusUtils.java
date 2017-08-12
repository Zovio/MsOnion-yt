/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 *
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
 *
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 *
 * 洋桃商城：http://www.yunyangtao.com
 *
 */

package cc.msonion.carambola.member.common.utils;

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * 成员状态工具类
 * @ClassName: MemberStatusUtils
 * @Description: 成员状态工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月26日 下午8:09:06
 *
 */
public final class MemberStatusUtils {

    /**
     * 普通Member状态，包括：禁用、正常，不包括超级管理员super的状态
     * <p>不包括删除状态：0</p>
     */
    private static final List<Short> GENERAL_MEMBER_STATUSES = new ArrayList<>();

    /**
     * 所有Member状态，包括：禁用、正常，包括超级管理员super的状态：9，
     * <p>不包括删除状态：0</p>
     */
    private static final List<Short> ALL_MEMBER_STATUSES = new ArrayList<>();

    /**
     * 获取所有Member正常状态，包括超级管理员super的状态：9
     */
    private static final List<Short> ALL_MEMBER_NORMAL_STATUSES = new ArrayList<>();

    static {
        // 普通Member状态，从小到大排序
        GENERAL_MEMBER_STATUSES.add(MsOnionTableRecordStatus.NORMAL.getValue());
        GENERAL_MEMBER_STATUSES.add(MsOnionTableRecordStatus.DISABLE.getValue());

        // 所有Member状态，包括超级管理员super，从小到大排序
        ALL_MEMBER_STATUSES.add(MsOnionTableRecordStatus.NORMAL.getValue());
        ALL_MEMBER_STATUSES.add(MsOnionTableRecordStatus.DISABLE.getValue());
        ALL_MEMBER_STATUSES.add(MsOnionTableRecordStatus.SUPER.getValue());

        // 所有Member的正常状态，包括 super，从小到大排序
        ALL_MEMBER_NORMAL_STATUSES.add(MsOnionTableRecordStatus.NORMAL.getValue());
        ALL_MEMBER_NORMAL_STATUSES.add(MsOnionTableRecordStatus.SUPER.getValue());
    }

    private MemberStatusUtils() {

    }

    /**
     * 获取普通Member状态，包括：禁用、正常、不可用，不包括超级管理员super的状态
     * <p>不包括删除状态：0</p>
     *
     * @return 普通Member状态，包括：禁用、正常，不包括超级管理员super的状态
     */
    public static List<Short> getGeneralMemberStatuses() {
        return GENERAL_MEMBER_STATUSES;
    }

    /**
     * 获取所有Member状态，包括：禁用、正常，包括超级管理员super的状态：9，
     * <p>不包括删除状态：0</p>
     *
     * @return 所有Member状态，包括：禁用、正常，包括超级管理员super的状态：9，
     */
    public static List<Short> getAllMemberStatuses() {
        return ALL_MEMBER_STATUSES;
    }

    /**
     * 获取所有Member正常状态，包括超级管理员super的状态：9，
     *
     * @return 获取所有Member正常状态，包括超级管理员super的状态：9，
     */
    public static List<Short> getAllMemberNormalStatuses() {
        return ALL_MEMBER_NORMAL_STATUSES;
    }

}
