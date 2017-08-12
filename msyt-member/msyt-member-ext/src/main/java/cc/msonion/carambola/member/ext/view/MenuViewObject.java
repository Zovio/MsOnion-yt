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
package cc.msonion.carambola.member.ext.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MenuViewObject
 * @Description: 菜单树 视图 配合jquery easyui的 tree控件
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月13日
 */
public class MenuViewObject implements Serializable {

    private static final long serialVersionUID = 7023613456653960152L;

    /**
     * 菜单主键idx
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String text;


    /**
     * 节点状态，'open' 或 'closed'
     */
    private String state;

    /**
     *  节点状态是否选中
     */
//    private Boolean checked = false;

    /**
     * 菜单顺序
     */
    private Short zindex;

    /**
     * 是否首页顶部显示
     */
    private Short topShow;

    /**
     * 菜单urlMapping
     */
    private String urlMapping;



    /**
     * 绑定该节点的自定义属性 例如 url
     */
   private List<Map> attributes = new ArrayList<Map>();


    /**
     * 子菜单集合
     */
    private List<MenuViewObject> children  = new ArrayList<MenuViewObject>();

    /**
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return text
     */
    public String getText() {
        return text;
    }


    /**
     *
     * @param text text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return zindex
     */
    public Short getZindex() {
        return zindex;
    }


    /**
     *
     * @param zindex zindex
     */
    public void setZindex(Short zindex) {
        this.zindex = zindex;
    }

    /**
     *
     * @return attributes
     */
    public List<Map> getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes attributes
     */
    public void setAttributes(List<Map> attributes) {
        this.attributes = attributes;
    }


    /**
     *
     * @return children
     */
    public List<MenuViewObject> getChildren() {
        return children;
    }

    /**
     *
     * @param children children
     */
    public void setChildren(List<MenuViewObject> children) {
        this.children = children;
    }

    /**
     *
     * @return topShow
     */
    public Short getTopShow() {
        return topShow;
    }

    /**
     *
     * @param  topShow topShow
     */
    public void setTopShow(Short topShow) {
        this.topShow = topShow;
    }

    /**
     * 获取urlmapping
     * @return urlMapping
     */
    public String getUrlMapping() {
        return urlMapping;
    }

    /**
     *
     * @param urlMapping urlMapping
     */
    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }

    /**
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * state
     * @param state state
     */
    public void setState(String state) {
        this.state = state;
    }
}
