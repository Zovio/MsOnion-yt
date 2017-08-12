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
package cc.msonion.carambola.cache.pojo;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.TreeSet;
/**
 * @Title: CacheKey.java
 * @Package: cc.msonion.carambola.cache.pojo
 * @Description: 缓存键值对Key的对象
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月10日 16:23
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年5月10日 16:23
 * @Modify-version: V2.0.0
 * @Modify-description: 创建 缓存键值对Key的对象
 */
/**
 * @ClassName: CacheService
 * @Description: 缓存键值对Key的对象
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月10日 16:23
 *
 */
public class CacheKey implements Comparable<CacheKey>, Serializable {

    /**
     * key的名称
     */
    private String key;
    /**
     * 当前KEY是Set类型的名称
     * 用于查询Set集合中子键的数量
     */
    private String setKey;
    /**
     * key的全路径名
     */
    private String path;;
    /**
     * 是否是key节点
     */
    private Boolean isLeaf = false;
    /**
     * 子集的数量
     */
    private Long countNum;
    /**
     * 子集合key的
     */
    private TreeSet<CacheKey> subList;

    public CacheKey() {
    }

    public CacheKey(String key, String path) {
        this.key = key;
        this.path = path;
    }
    /**
     * 获取key的名称
     * @return key的名称
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置key
     * @param key key的值
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取KEYS的值
     * @return KEYS的值
     */
    public String getSetKey() {
        return setKey;
    }
    /**
     * 设置KEYS的值
     * @param setKey KEYS的值
     */
    public void setSetKey(String setKey) {
        this.setKey = setKey;
    }
    /**
     * 获取key的路径
     * @return key的路径
     */
    public String getPath() {
        return path;
    }
    /**
     * 设置key的路径
     * @param path key的路径
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * 是否是叶子节点
     * @return 是否是叶子节点
     */
    public Boolean getLeaf() {
        return isLeaf;
    }
    /**
     * 设置叶子节点
     * @param leaf 是否叶子节点
     */
    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }
    /**
     * 获取子key集合的数量
     * @return 子key集合的数量
     */
    public Long getCountNum() {
        return countNum;
    }
    /**
     * 设置子key集合的数量
     * @param countNum 子key集合的数量
     */
    public void setCountNum(Long countNum) {
        this.countNum = countNum;
    }
    /**
     * 获取子集合
     * @return 子集合
     */
    public TreeSet<CacheKey> getSubList() {
        return subList;
    }
    /**
     * 设置子集合
     * @param subList 子集合
     */
    public void setSubList(TreeSet<CacheKey> subList) {
        this.subList = subList;
    }

    @Override
    public int compareTo(@NotNull CacheKey o) {
        final int lt = -1, gt = 1, eq = 0;
        if (this == null || this.getKey() == null || "".equals(this.getKey())) {
            return lt;
        }
        if (o == null || o.getKey() == null || "".equals(o.getKey())) {
            return gt;
        }
        if (this.getKey().equals(o.getKey()) && this.path.equals(o.getPath())) {
            return eq;
        }
        if (this.getKey().charAt(0) < o.getKey().charAt(0)) {
            return lt;
        }
        return gt;
    }


}
