package cc.msonion.carambola.parent.ext.elasticsearch.pojo.paging;

import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.enums.MsOnionExecuteResultStatus;
import cc.msonion.carambola.parent.ext.elasticsearch.constants.MsOnionElasticsearchConstants;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;

/**
 * Created by HeroCao on 2017/6/8.
 */
public class MsOnionESSearchPagingResult extends MsOnionPagingResult {


    /**
     * 创建 MsOnionPagingResult 实例对象
     *
     * @param totalCounts 总记录数
     * @param pageSize    页码
     * @param pageNum     每页数量
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public MsOnionESSearchPagingResult(int totalCounts, int pageSize, int pageNum) {
        super(totalCounts, pageSize, pageNum);
    }

    /**
     * 创建 MsOnionPagingResult 实例对象
     *
     * @param status      状态码
     * @param msg         提示信息
     * @param data        数据
     * @param totalCounts 总记录数
     * @param pageSize    页码
     * @param pageNum     每页数量
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public MsOnionESSearchPagingResult(Integer status, String msg, Object data, long totalCounts, int pageSize, int pageNum) {
        super(status, msg, data, totalCounts, pageSize, pageNum);
    }

    /**
     * 通过静态方法的方式创建 MsOnionESSearchPagingResult 实例对象
     *
     * @param status 状态码
     * @param msg    提示信息
     * @return 返回MsOnionResult 实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public static MsOnionESSearchPagingResult build(Integer status, String msg) {
        MsOnionESSearchPagingResult msOnionESSearchPagingResult =
                new MsOnionESSearchPagingResult(MsOnionElasticsearchConstants.ES_FAILURE_DEFAULT_TOTAL_COUNT,
                        MsOnionElasticsearchConstants.ES_FAILURE_DEFAULT_SIZE, MsOnionElasticsearchConstants.ES_FAILURE_DEFAULT_FROM);
        msOnionESSearchPagingResult.setStatus(status);
        msOnionESSearchPagingResult.setData(null);
        msOnionESSearchPagingResult.setMsg(msg);
        return msOnionESSearchPagingResult;
    }

//    /**
//     * 通过静态方法的方式创建 MsOnionESSearchPagingResult 实例对象
//     *
//     * @param status 状态码
//     * @param msg    提示信息
//     * @return 返回MsOnionResult 实例对象
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月15日 下午3:07:52
//     */
//    public static MsOnionESSearchPagingResult build(int status, String msg) {
//        return build(Integer.get, msg);
//    }

    /**
     * 通过静态方法的方式创建 MsOnionESSearchPagingResult 实例对象，失败
     *
     * @return MsOnionESSearchPagingResult 实例对象，失败
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    public static MsOnionESSearchPagingResult failure() {
        return MsOnionESSearchPagingResult.build(MsOnionStatusConstants.STATUS_400, MsOnionElasticsearchConstants.ES_SEARCH_FAILURE);
    }

    /**
     * 获取状态码
     *
     * @return 返回状态码
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    @Override
    public Integer getStatus() {
        return super.getStatus();
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     * @return 返回MsOnionResult实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    @Override
    public MsOnionESSearchPagingResult setStatus(Integer status) {
        super.setStatus(status);
        return this;
    }

    /**
     * 获取提示信息
     *
     * @return 返回提示信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    @Override
    public String getMsg() {
        return super.getMsg();
    }

    /**
     * 设置提示信息
     *
     * @param msg 提示信息
     * @return 返回MsOnionResult实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    @Override
    public MsOnionESSearchPagingResult setMsg(String msg) {
         super.setMsg(msg);
         return this;
    }

    /**
     * 获取目标数据
     *
     * @return 返回目标数据
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    @Override
    public Object getData() {
        return super.getData();
    }

    /**
     * 设置数据
     *
     * @param data 目标数据
     * @return 返回MsOnionResult实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月15日 下午3:07:52
     */
    @Override
    public MsOnionESSearchPagingResult setData(Object data) {
        super.setData(data);
        return this;
    }

    /**
     * toString
     *
     * @return 返回字符串
     */
    @Override
    public String toString() {
        return "MsOnionESSearchPagingResult [status=" + this.getStatus() + ", msg=" + this.getMsg() + ", data=" + this.getData() + "]";
    }


}
