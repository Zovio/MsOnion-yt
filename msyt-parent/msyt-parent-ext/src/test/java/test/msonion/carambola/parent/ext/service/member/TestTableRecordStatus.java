package test.msonion.carambola.parent.ext.service.member;

/**
 * Created by HeroCao on 2017/5/19.
 */
public enum TestTableRecordStatus {

    /**
     *  状态：正常的、可用的、激活的，值为：1
     */
    NORMAL((short)1),
    /**
     *  状态：未激活，值为：2
     */
    DISABLE((short)2),
    /**
     * 状态：已经删除，软删除，值为：0
     */
    DELETED((short)0);

    private final short value;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    private TestTableRecordStatus(Short value) {
        this.value = value;
    }

    /**
     * @Title: getValue
     * @Description: 获取枚举的值
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月26日 下午12:19:24
     *
     * @return
     */
    public short getValue() {
        return value;
    }

}
