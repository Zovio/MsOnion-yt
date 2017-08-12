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

package cc.msonion.carambola.commons.web.controller.base;

import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionSysSetCodeConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.web.controller.base.MsOnionBaseController;
import cc.msonion.carambola.system.pojo.SysSetting;
import cc.msonion.carambola.system.service.SysSettingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


/**
 * @Title: MsOnionBaseAppController.java
 * @Package: cc.msonion.carambola.commons.web.controller.base
 * @Description: MsOnionBaseAppController，封装公共功能
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月12日 下午9:52:51
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月12日 下午9:52:51
 * @Modify-version: V2.0.0
 * @Modify-description: 封装公共功能
 */

/**
 * @ClassName: MsOnionBaseAppController
 * @Description: MsOnionBaseAppController，封装公共功能
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月12日 下午9:52:51
 */
public abstract class MsOnionBaseAppController extends MsOnionBaseController {

    /**
     * 后台 error 页面
     */
    private final String error = "/error/error";

    /**
     * MsOnion日志
     */
    @Autowired
    private MsOnionLogger msOnionLogger;

    /**
     * sysSettingService
     */
    @Autowired
    private SysSettingService sysSettingService;


    /**
     * 解决 GET请求参数中文乱码问题
     *
     * @param parameter GET请求参数
     * @return 返回正常（非乱码）参数
     * @throws MsOnionException 异常
     */
    public final String getNormalGetRequestParameter(String parameter) throws MsOnionException {
        if (MsOnionStringUtils.isBlank(parameter)) {
            return null;
        }
        parameter = parameter.trim();
        try {
            // 解决 GET请求参数，中文乱码问题
            return new String(parameter.getBytes(MsOnionConstants.CHARSET_ISO8859_1), MsOnionConstants.CHARSET_UTF_8);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ////////////////////////// Setters、 Getters ## Begin  ////////////////////////

    /**
     * 获取MsOnionLogger
     *
     * @return 返回MsOnionLogger
     */
    public MsOnionLogger getMsOnionLogger() {
        return msOnionLogger;
    }

    /**
     * @return 获取错误页面
     */
    public String getError() {
        return error;
    }


    /**
     * 通过key获取 value
     *
     * @param setKey 设置key
     * @return 系统设置
     */
    public String getSysValueBySetKey(String setKey) {
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(MsOnionConstants.API_VERSION);
        MsOnionResult result = null;
        try {
            result = sysSettingService.getValueBySetKey(msOnionApiVersion, setKey);
        } catch (MsOnionException e) {
            getMsOnionLogger().error(this.getClass().getName(), e, "## 查询系统设置失败，key = " + setKey);
            return null;
        }
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return null;
        }

        return Optional.ofNullable(result.getData()).map(s -> {
            SysSetting ss = (SysSetting) s;
            return ss.getSettingValue();
        }).orElse(null);
    }


    /**
     * 获取图片url
     *
     * @return imgurl
     */
    public String getImgurl() {
        return getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_IMGUTL);
    }

    ////////////////////////// Setters、 Getters ## End  ////////////////////////
}
