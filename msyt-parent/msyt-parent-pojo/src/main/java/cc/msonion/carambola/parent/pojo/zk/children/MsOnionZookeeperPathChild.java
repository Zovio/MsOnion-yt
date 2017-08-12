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

package cc.msonion.carambola.parent.pojo.zk.children;

import java.util.List;

/**
 * @Title: MsOnionZookeeperPathChild.java
 * @Package: cc.msonion.carambola.parent.pojo.zk.children
 * @Description: MsOnionZookeeperPathChild
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月30日 下午1:22:17
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月30日 下午1:22:17
 * @Modify-version: V2.0.0
 * @Modify-description: 修改：日志
 */

/**
 * @ClassName: MsOnionZookeeperPathChild
 * @Description: MsOnionZookeeperPathChild
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月30日 下午1:22:17
 */
public class MsOnionZookeeperPathChild {

    /**
     * 父级路径，例如：/www.msyc.cc/carambola/atomic/pro
     */
    private String parentPath;

    /**
     * 模块路径，例如：pro/longItemCode、dev/longItemCode、test/version
     */
    private String modulePath;

    /**
     * 路径，例如：pro、dev、version、code、idx
     */
    private String path;

    /**
     * 全路径，例如：例如：/www.msyc.cc/carambola/atomic/pro 、例如：/www.msyc.cc/carambola/atomic/idx
     */
    private String fullPath;

    /**
     * 子目录集合，会递归获取所有子目录
     */
    private List<MsOnionZookeeperPathChild> children;

    /**
     * 子目录集合，不会递归获取所有子目录
     */
    private List<String> childList;

    ///////////////////  Getters 、 Setters ### Begin ///////////////

    /**
     * 父级路径，例如：/www.msyc.cc/carambola/atomic/pro
     *
     * @return 父级路径，例如：/www.msyc.cc/carambola/atomic/pro
     */
    public String getParentPath() {
        return parentPath;
    }

    /**
     * 父级路径，例如：/www.msyc.cc/carambola/atomic/pro
     *
     * @param parentPath 父级路径，例如：/www.msyc.cc/carambola/atomic/pro
     */
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    /**
     * 模块路径，例如：pro/longItemCode、dev/longItemCode、test/version
     *
     * @return 模块路径，例如：pro/longItemCode、dev/longItemCode、test/version
     */
    public String getModulePath() {
        return modulePath;
    }

    /**
     * 模块路径，例如：pro/longItemCode、dev/longItemCode、test/version
     *
     * @param modulePath 模块路径，例如：pro/longItemCode、dev/longItemCode、test/version
     */
    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    /**
     * 路径，例如：pro、dev、version、code、idx
     *
     * @return 路径，例如：pro、dev、version、code、idx
     */
    public String getPath() {
        return path;
    }

    /**
     * 路径，例如：pro、dev、version、code、idx
     *
     * @param path 路径，例如：pro、dev、version、code、idx
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 全路径，例如：例如：/www.msyc.cc/carambola/atomic/pro 、例如：/www.msyc.cc/carambola/atomic/idx
     *
     * @return 全路径，例如：例如：/www.msyc.cc/carambola/atomic/pro 、例如：/www.msyc.cc/carambola/atomic/idx
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * 全路径，例如：例如：/www.msyc.cc/carambola/atomic/pro 、例如：/www.msyc.cc/carambola/atomic/idx
     *
     * @param fullPath 全路径，例如：例如：/www.msyc.cc/carambola/atomic/pro 、例如：/www.msyc.cc/carambola/atomic/idx
     */
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    /**
     * 子目录，会递归获取所有子目录
     *
     * @return 子目录，会递归获取所有子目录
     */
    public List<MsOnionZookeeperPathChild> getChildren() {
        return children;
    }

    /**
     * 子目录，会递归获取所有子目录
     *
     * @param children 子目录，会递归获取所有子目录
     */
    public void setChildren(List<MsOnionZookeeperPathChild> children) {
        this.children = children;
    }

    /**
     * 子目录集合，不会递归获取所有子目录
     * @return 子目录集合
     */
    public List<String> getChildList() {
        return childList;
    }

    /**
     * 子目录集合，不会递归获取所有子目录
     * @param childList 子目录集合
     */
    public void setChildList(List<String> childList) {
        this.childList = childList;
    }

    ///////////////////  Getters 、 Setters ### End ///////////////

    @Override
    public String toString() {
        return "MsOnionZookeeperPathChild{" +
                "parentPath='" + parentPath + '\'' +
                ", modulePath='" + modulePath + '\'' +
                ", path='" + path + '\'' +
                ", fullPath='" + fullPath + '\'' +
                ", children=" + children +
                ", childList=" + childList +
                '}';
    }
}
