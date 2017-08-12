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

package cc.msonion.carambola.parent.pojo;

import cc.msonion.carambola.parent.common.enums.MsOnionExecuteResultStatus;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.pojo.base.MsOnionBaseResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @Title: MsOnionResult.java
 * @Package: cc.msonion.carambola.parent.pojo
 * @Description: 自定义统一结果
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月15日 下午11:25:38
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月15日 下午11:25:38
 * @Modify-version: V2.0.0
 * @Modify-description: 创建
 */

/**
 * 自定义统一结果
 *
 * @ClassName: MsOnionResult
 * @Description: 自定义统一结果
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月15日 下午11:25:38
 */
public class MsOnionResult extends MsOnionBaseResult {

    /**
     * 创建jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 目标数据data
     */
    private Object data;

    /**
     * OK状态码：200
     */
    private static final int OK_STATUS = 200;

    /**
     * OK
     */
    private static final String OK_MSG = "OK";

    /**
     * @Fields serialVersionUID : 自动生成serialVersionUID
     */
    private static final long serialVersionUID = -1991985523438870491L;

//    /*
//	 * @Fields url : 如果失败，可以跳转到错误提示页面的链接
//	 */
//	private String url;

    /**
     * 创建MsOnionResult实例对象
     *
     * @param status 状态码
     * @param msg    提示信息
     * @param data   目标数据
     * @return 返回MsOnionResult实例对象
     */
    public static MsOnionResult build(Integer status, String msg, Object data) {
        return new MsOnionResult(status, msg, data);
    }

    /**
     * 创建MsOnionResult实例对象
     *
     * @param status 数据库执行结果状态码
     * @param msg    提示信息
     * @param data   目标数据
     * @return 创建MsOnionResult实例对象
     */
    public static MsOnionResult build(MsOnionExecuteResultStatus status, String msg, Object data) {
        return new MsOnionResult(status.getValue(), msg, data);
    }

    /**
     * 创建MsOnionResult实例对象
     *
     * @param status 数据库执行结果状态码
     * @param msg    提示信息
     * @return 创建MsOnionResult实例对象
     */
    public static MsOnionResult build(MsOnionExecuteResultStatus status, String msg) {
        return new MsOnionResult(status.getValue(), msg, null);
    }

    /**
     * 创建MsOnionResult实例对象
     *
     * @param data 目标数据
     * @return 返回MsOnionResult实例对象
     */
    public static MsOnionResult ok(Object data) {
        return new MsOnionResult(data);
    }

    /**
     * 创建MsOnionResult实例对象
     *
     * @return 返回MsOnionResult实例对象
     */
    public static MsOnionResult ok() {
        return new MsOnionResult(null);
    }

    /**
     * 创建MsOnionResult实例对象，无参数构造方法
     */
    public MsOnionResult() {

    }

    /**
     * 通过静态方法的方式创建 MsOnionResult 实例对象
     *
     * @param status 状态码
     * @param msg    提示信息
     * @return 返回MsOnionResult 实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public static MsOnionResult build(Integer status, String msg) {
        return new MsOnionResult(status, msg, null);
    }

    /**
     * 创建 MsOnionResult 实例对象
     *
     * @param status 状态码
     * @param msg    提示信息
     * @param data   数据
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public MsOnionResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 创建 MsOnionResult 实例对象，状态是200
     *
     * @param data 数据
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public MsOnionResult(Object data) {
        this.status = OK_STATUS;
        this.msg = OK_MSG;
        this.data = data;
    }

    //    public Boolean isOK() {
    //        return this.status == 200;
    //    }

    /**
     * 获取状态码
     *
     * @return 返回状态码
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     * @return 返回MsOnionResult实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public MsOnionResult setStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * 获取提示信息
     *
     * @return 返回提示信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置提示信息
     *
     * @param msg 提示信息
     * @return 返回MsOnionResult实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public MsOnionResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 获取目标数据
     *
     * @return 返回目标数据
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置数据
     *
     * @param data 目标数据
     * @return 返回MsOnionResult实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public MsOnionResult setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 将json结果集转化为MsOnionResult对象
     *
     * @param json  json数据
     * @param clazz MsOnionResult中的object类型
     * @return 返回MsOnionResult实例对象
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public static MsOnionResult formatToPojo(String json, Class<?> clazz) throws MsOnionException {
        try {
            if (null == clazz) {
                return MAPPER.readValue(json, MsOnionResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(json);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json JSON字符串
     * @return 返回MsOnionResult实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public static MsOnionResult format(String json) {
        try {
            return MAPPER.readValue(json, MsOnionResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param json  json数据
     * @param clazz 集合中的类型
     * @return 返回MsOnionResult实例对象
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public static MsOnionResult formatToList(String json, Class<?> clazz) throws MsOnionException {
        try {
            JsonNode jsonNode = MAPPER.readTree(json);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

//	/**
//	 * @return the url
//	 */
//	public String getUrl() {
//		return url;
//	}
//
//	/**
//	 * @param url the url to set
//	 */
//	public void setUrl(String url) {
//		this.url = url;
//	}

    /**
     * toString
     *
     * @return 返回字符串
     */
    @Override
    public String toString() {
        return "MsOnionResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
    }

}
