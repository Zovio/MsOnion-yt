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
 * @Title: LogController
 * @Package: cc.msonion.carambola.manager.web.controller.collector
 * @Description: 描述信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月24日 16:58:53
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月24日 16:58:53
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.collector.pojo.*;
import cc.msonion.carambola.collector.service.ItemLogService;
import cc.msonion.carambola.collector.service.ItemService;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.pojo.MemberExample;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionDictCodeConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.*;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @ClassName: LogController
 * @Description: 商品日志
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月24日 16:58:53
 */
@Controller
public class ItemLogController extends MsOnionBaseAppController {


    /**
     * 商品日志服务
     */
    @Autowired
    private ItemLogService itemLogService;

    /**
     * 用户服务
     */
    @Autowired
    private MemberService memberService;


    /**
     * 商品服务
     */
    @Autowired
    private ItemService itemService;

    /**
     * 数据字典服务
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;
    /**
     * 日志列表页面
     *
     * @param req   HttpServletRequest 对象
     * @param res   HttpServletResponse  对象
     * @param model Model实例对象
     * @return 页面视图
     */
    @RequestMapping("/log/itemLog/list")
    public String list(HttpServletRequest req, HttpServletResponse res, Model model) {

        try {

            Map itemModifyLocationMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.ITEM_MODIFY_LOCATION);
            model.addAttribute("itemModifyLocationJson", MsOnionJsonUtils.objectToJson(itemModifyLocationMap));

            return "/log/listItemLog";
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "### 进入商品日志列表页面失败..");
            return getError();
        }
    }


    /**
     * 获取商品价格列表
     *
     * @param request   请求
     * @param page      当前页码
     * @param rows      每页记录数量
     * @param itemIdx   商品ID
     * @param cnName    商品中文品名
     * @param userName  操作用户名
     * @param startTime 操作时间开始时间
     * @param endTime   操作时间结束时间
     * @return 列表、总数
     */
    @RequestMapping("/log/itemLog/grid")
    @ResponseBody
    public MsOnionResult gridItem(HttpServletRequest request, String page, String rows, String itemIdx,
                                  String cnName, String userName, String startTime, String endTime) {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            int pageNum = MsOnionRegexUtils.checkDigit(page) ? Integer.parseInt(page) : MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE;
            int pageSize = MsOnionRegexUtils.checkDigit(rows) ? Integer.parseInt(rows) : MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE;

            String orderBy;
            String sort = request.getParameter("sort");
            String order = request.getParameter("order");
            if (MsOnionStringUtils.isNotBlank(sort) && MsOnionStringUtils.isNotBlank(order)) {
                orderBy = sort.trim() + ManagerConstants.SPACE + order.trim();
            } else {
                orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;
            }
            Map map = new HashMap();
            CollectorItemLogExample collectorItemLogExample = new CollectorItemLogExample();
            setQueryCondition(collectorItemLogExample, itemIdx, cnName,
                    userName, startTime, endTime);
            MsOnionResultAdapter resultAdapter = itemLogService.queryListByPageForResult(apiVersion,
                    collectorItemLogExample, pageNum, pageSize, orderBy);
            if (null == resultAdapter) {
                return MsOnionResult.ok();
            }

            MsOnionPagingResult pagingResult = (MsOnionPagingResult) resultAdapter;

            long total = pagingResult.getTotalCounts();
            List<CollectorItemLog> collectorItemLogList = (List<CollectorItemLog>) pagingResult.getData();
            List<Map<String, Object>> mapList = new ArrayList<>();
            if (MsOnionCollectionUtils.isNotEmpty(collectorItemLogList)) {
                for (CollectorItemLog collectorItemLog : collectorItemLogList) {
                    Map<String, Object> itemMap = new HashMap<String, Object>();
                    itemMap.put("collectorItemLog", collectorItemLog);
                    // 类目
                    Long userIdx = collectorItemLog.getCreateByMemberIdx();
                    if (null != userIdx) {
                        MemberExample memberExample = new MemberExample();
                        MemberExample.Criteria criteria = memberExample.createCriteria();
                        criteria.andIdxEqualTo(userIdx);
                        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
                        MemberExample.Criteria criteria2 = memberExample.createCriteria();
                        criteria2.andIdxEqualTo(userIdx);
                        criteria2.andStatusEqualTo(MsOnionTableRecordStatus.SUPER.getValue());
                        memberExample.or(criteria2);
                        Member member = memberService.queryOne(apiVersion, memberExample);
                        if (null != member) {
                            itemMap.put("member", member);
                        }
                    }
                    // 商品
                    Long itemIdx1 = collectorItemLog.getItemIdx();
                    if (null != itemIdx1) {
                        MsOnionResult msOnionResult = itemService.getItemByIdx(apiVersion, itemIdx1);
                        if (msOnionResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                            CollectorItem collectorItem = (CollectorItem) msOnionResult.getData();
                            itemMap.put("item", collectorItem);
                        }
                    }
                    mapList.add(itemMap);
                }
            }
            map.put("total", total);
            map.put("rows", mapList);
            return MsOnionResult.ok(map);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }

    /**
     * 描述信息
     *
     * @param collectorItemLogExample collectorItemLogExample 对象
     * @param itemIdx                 商品ID
     * @param cnName                  商品中文品名
     * @param userName                操作人用户名
     * @param startTime               操作开始时间
     * @param endTime                 操作结束时间
     * @throws MsOnionException 自定义异常
     */
    private void setQueryCondition(CollectorItemLogExample collectorItemLogExample, String itemIdx, String cnName,
                                   String userName, String startTime, String endTime) throws MsOnionException {
        CollectorItemLogExample.Criteria criteria = collectorItemLogExample.createCriteria();
        if (MsOnionStringUtils.isNotEmpty(itemIdx) && MsOnionRegexUtils.checkDigit(itemIdx)) {
            criteria.andItemIdxEqualTo(Long.parseLong(itemIdx));
        }

        if (MsOnionStringUtils.isNotEmpty(cnName)) {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            CollectorItemExample collectorItemExample = new CollectorItemExample();
            CollectorItemExample.Criteria collectorItemExampleCriteria = collectorItemExample.createCriteria();
            collectorItemExampleCriteria.andCnNameLike(MsOnionConstants.PERCENT + cnName + MsOnionConstants.PERCENT);
            collectorItemExampleCriteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
            List<CollectorItem> memberList = itemService.queryByExample(apiVersion, collectorItemExample);
            List<Long> itemIdxList = new ArrayList<Long>();
            if (MsOnionCollectionUtils.isNotEmpty(memberList)) {
                for (CollectorItem collectorItem : memberList) {
                    itemIdxList.add(collectorItem.getIdx());
                }
            } else {
                final long noValue = -1L;
                itemIdxList.add(noValue);
            }
            criteria.andItemIdxIn(itemIdxList);
        }

        if (MsOnionStringUtils.isNotEmpty(userName)) {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            MemberExample memberExample = new MemberExample();
            MemberExample.Criteria memberExampleCriteria = memberExample.createCriteria();
            memberExampleCriteria.andNameLike(MsOnionConstants.PERCENT + userName + MsOnionConstants.PERCENT);
            memberExampleCriteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
            List<Member> memberList = memberService.queryByExample(apiVersion, memberExample);
            List<Long> userList = new ArrayList<Long>();
            if (MsOnionCollectionUtils.isNotEmpty(memberList)) {
                for (Member member : memberList) {
                    userList.add(member.getIdx());
                }
            } else {
                final long noValue = -1L;
                userList.add(noValue);
            }
            criteria.andCreateByMemberIdxIn(userList);
        }

        if (MsOnionStringUtils.isNotEmpty(startTime)) {
            try {
                Date startTimeDate = MsOnionDateUtils.parseWithYyyyMMddHHmmss(startTime);
                criteria.andCreateTimeGreaterThanOrEqualTo(startTimeDate);
            } catch (MsOnionException e) {

            }
        }

        if (MsOnionStringUtils.isNotEmpty(endTime)) {
            try {
                Date endTimeDate = MsOnionDateUtils.parseWithYyyyMMddHHmmss(endTime);
                criteria.andCreateTimeLessThanOrEqualTo(endTimeDate);
            } catch (MsOnionException e) {

            }
        }

    }

}
