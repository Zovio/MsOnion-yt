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


package cc.msonion.carambola.manager.web.controller.collector;

/**
 * @Title: AblumController.java
 * @Package: cc.msonion.carambola.manager.web.controller.collector
 * @Description: 商品相册controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月18日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月18日
 * @Modify-version: V2.0.0
 * @Modify-description:
 *
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.fileresource.interfaces.AblumLibService;
import cc.msonion.carambola.fileresource.pojo.AblumLib;
import cc.msonion.carambola.fileresource.pojo.AblumLibExample;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionDictCodeConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName: AblumController
 * @Description: 商品相册controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月18日
 *
 */
@Controller
public class AblumController extends MsOnionBaseAppController {

    /**
     * sysDataDictionaryService
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;

    /**
     * ablumLibService
     */
    @Autowired
    private AblumLibService ablumLibService;

    /**
     *   文件资源中心
     */
    private String fileResouruceDomain;


    /**
     *  商品主页
     * @param req 请求对象
     * @param res 响应对象
     * @param model model
     * @return 页面
     * @throws MsOnionException 页面
     */
    @RequestMapping("/collect/ablum/home")
    public String home(HttpServletRequest req, HttpServletResponse res, Model model) throws MsOnionException {
        Map ablumMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                MsOnionDictCodeConstants.MSYT_ABLUM_TYPE);
        model.addAttribute("statusMap", ablumMap);
        return "/collector/ablum/home";
    }

    /**
     * 查询数据
     * @param request 请求对象
     * @param res 响应对象
     * @param model 实体
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/ablum/qureyDate")
    @ResponseBody
    public MsOnionPagingResult qureyDate(HttpServletRequest request, HttpServletResponse res, Model model) throws MsOnionException {
        // 相册类型
        String ablumType = request.getParameter("ablumType");
        // 相册目录类别
        String ablumCatagory = request.getParameter("ablumCatagory");
        // 目录名称
        String ablumName = request.getParameter("ablumName");

        // 父目录名称 (第一次进来查询时候需要这个字段、后续都是走pidx)
        String parentAblumName = request.getParameter("parentAblumName");

        // pidx 父目录Idx
        String pidx = request.getParameter("pidx");

        // 当前页
        String pageNumStr = request.getParameter("pageNum");

        if (!MsOnionRegexUtils.checkDigit(ablumType)) {
            ablumType = MsOnionConstants.ALBUM_COLLECT + "";
        }

        if (!MsOnionRegexUtils.checkDigit(pageNumStr)) {
            pageNumStr = MsOnionConstants.YES;
        }

        if (!MsOnionRegexUtils.checkDigit(ablumCatagory)) {
            //目录
            ablumCatagory = MsOnionConstants.ONE + "";
        }
        if (StringUtils.isBlank(parentAblumName) && StringUtils.isBlank(pidx)) {
            switch (Integer.valueOf(ablumType).intValue()) {
                case MsOnionConstants.ONE:
                    parentAblumName = MsOnionConstants.ALBUM_COLLECT_PATH_START.substring(1);
                    break;
                case MsOnionConstants.TWO:
                    parentAblumName = MsOnionConstants.ALBUM_FORMAL_PATH_START.substring(1);
                    break;
                default:
                    parentAblumName = null;
            }
        }
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        final String orderBy  = ManagerConstants.ORDER_BY_UPDATE_TIME;
        int pageSize  = MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_DEFAULT_VALUE;
        AblumLibExample example = new AblumLibExample();
        AblumLibExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        criteria.andAblumTypeEqualTo(Short.valueOf(ablumType));
        criteria.andCategoryEqualTo((short) (Integer.valueOf(ablumCatagory).intValue() + 1));

        if (StringUtils.isNotBlank(parentAblumName)) {
            criteria.andParentNameEqualTo(parentAblumName);
        }

        if (MsOnionRegexUtils.checkDigit(pidx)) {
            criteria.andPidxEqualTo(Long.valueOf(pidx));
        }

        if (StringUtils.isNotBlank(ablumName)) {
            criteria.andNameLike("%" + ablumName.trim() + "%");
        }

        MsOnionResultAdapter msOnionResultAdapter = this.ablumLibService.queryListByPageForResult(apiVersion, example,
                Integer.valueOf(pageNumStr), pageSize, orderBy);

        if (!Optional.ofNullable(msOnionResultAdapter).isPresent()) {
                return null;
        }
        MsOnionPagingResult  pagingResult = (MsOnionPagingResult) msOnionResultAdapter;
        List<AblumLib> list = (List<AblumLib>) pagingResult.getData();

        list = list.stream().peek(s -> {
            if (StringUtils.isNotBlank(s.getExt())) {
                s.setRelativePath(getImgurl() + s.getRelativePath());
            }
        }).collect(Collectors.toList());
        pagingResult.setData(list);
        return pagingResult;
    }


}
