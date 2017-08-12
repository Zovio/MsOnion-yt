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
package cc.msonion.carambola.manager.web.controller.content;

/**
 * @Title: ContentController
 * @Package: cc.msonion.carambola.manager.web.controller.content
 * @Description: 内容controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月17日 09:46:14
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月17日 09:46:14
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.content.common.constants.ContentConstants;
import cc.msonion.carambola.content.pojo.ContentKeyword;
import cc.msonion.carambola.content.pojo.view.ContentImageView;
import cc.msonion.carambola.content.pojo.view.ContentView;
import cc.msonion.carambola.content.service.ContentKeywordService;
import cc.msonion.carambola.content.service.ContentService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityBase64Utils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: ContentController
 * @Description: 内容controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月17日 09:46:14
 */
@Controller
public class ContentController  extends MsOnionBaseAppController {
    /**
     * 内容服务
     */
    @Autowired
    private ContentService contentService;
    /**
     * 关键字服务
     */
    @Autowired
    private ContentKeywordService contentKeywordService;


    ///////////////////////////////////////////////////// 内容 begin //////////////////////////////////////////////////
    /**
    * @return 内容列表页面
    */
    @RequestMapping("/content/list")
    public String list() {
        return "/content/listContent";
    }


    /**
     * 内容编辑页面
     *
     * @param req     HttpServletRequest 对象
     * @param res     HttpServletResponse  对象
     * @param model   Model实例对象
     * @param idxStr     内容对象的ID
     * @return 页面视图
     */
    @RequestMapping("/content/list/edit")
    public String edit(HttpServletRequest req, HttpServletResponse res, Model model, String idxStr) {

        try {
            Long idxLong = 0L;
            if (MsOnionStringUtils.isNotBlank(idxStr)) {
                if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                    this.getMsOnionLogger().error(getClass().getName(),
                            MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idxStr=" + idxStr);
                    return getError();
                }
                idxLong = Long.parseLong(idxStr);
            }

            if (idxLong != null && idxLong > 0) {
                // 版本对象
                MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
                msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

                // 查询详情信息
                MsOnionResult contentDetailResult = contentService.getContentDetail(msOnionApiVersion, idxLong);
                model.addAttribute("content", contentDetailResult);


                model.addAttribute("imgurl", ManagerConstants.UEDITOR_UP_PATH_DEFAUL);
            }
            return "/content/editContent";
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "### 进入内容编辑页面失败..");
            return getError();
        }
    }


    /**
     * 查询内容数据分页列表
     * @param req  HttpServletRequest实例对象
     * @param res  HttpServletResponse实例对象
     * @param page 当前页
     * @param rows 页大小
     * @param sort 排序字段
     * @param order 排序方式
     * @param name 名称模糊查询
     * @param type 类型查询
     * @param isDelete 是否是删除数据 1-否 0-是
     * @return 返回MsOnionResult
     */
    @RequestMapping("/content/list/getContentPage")
    @ResponseBody
    public MsOnionResult getContentPage(HttpServletRequest req, HttpServletResponse res, String page, String rows,
                                        String sort, String order, String name, String type, String isDelete) {
        try {
            // 处理参数
            if (!MsOnionRegexUtils.checkDigit(page)) {
                page = MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE + "";
            }
            if (!MsOnionRegexUtils.checkDigit(rows)) {
                rows = MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE + "";
            }
            if (!MsOnionRegexUtils.isInteger(type)) {
                type = ContentConstants.CONTENT_DEFAULT_TYPE + "";
            }
            if (!MsOnionRegexUtils.isInteger(isDelete)) {
                isDelete = MsOnionStatusConstants.SQL_STATUS_ONE_VALUE + "";
            }
            int pageNum = Integer.parseInt(page);
            int pageSize = Integer.parseInt(rows);
            short typeInt = Short.parseShort(type);
            short isDeleteInt = Short.parseShort(isDelete);
            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 分页查询
            MsOnionResult pageResult = contentService.selectContentPage(msOnionApiVersion, pageNum,
                                                        pageSize, sort, order, name, typeInt, isDeleteInt);
            // 返回对象给客户端
            return pageResult;
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 查询内容数据分页列表失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 查询内容数据分页列表失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }


    /**
     * 保存内容对象数据
     *
     * @param req     HttpServletRequest 对象
     * @param res     HttpServletResponse  对象
     * @param contentView 内容对象
     * @param imageListStr 图片列表数据字符串
     * @param startTimeStr 有效开始时间字符串
     * @param endTimeStr 有效结束时间字符串
     * @return 页面视图
     */
    @RequestMapping("/content/list/doEditContent")
    @ResponseBody
    public MsOnionResult editContent(HttpServletRequest req, HttpServletResponse res, ContentView contentView,
                                     String imageListStr, String startTimeStr, String endTimeStr) {
        try {

            if (contentView == null) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }
            if (MsOnionStringUtils.isEmpty(startTimeStr)
                    || MsOnionStringUtils.isEmpty(endTimeStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }
            contentView.setStartTime(MsOnionDateUtils.parseWithYyyyMMddHHmmss(startTimeStr));
            contentView.setEndTime(MsOnionDateUtils.parseWithYyyyMMddHHmmss(endTimeStr));


            this.getMsOnionLogger().info(getClass().getName(),
                    "### 保存内容对象数据...content.." + contentView.toString());
            this.getMsOnionLogger().info(getClass().getName(),
                    "### 保存内容对象数据...imageList.." + imageListStr);
            List<ContentImageView> imageList = null;

            // 手动转换为对象
            if (MsOnionStringUtils.isNotBlank(imageListStr)) {
                this.getMsOnionLogger().info(getClass().getName(),
                        "### 加密.." + imageListStr);
                imageListStr = MsOnionSecurityBase64Utils.decode(imageListStr);
                this.getMsOnionLogger().info(getClass().getName(),
                        "### 解密.." + imageListStr);
                imageList = MsOnionJsonUtils.jsonToList(imageListStr, ContentImageView.class);
            }

            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 当前操作者ID
            long operateId = CurrentUserUtils.getCurrentUser().getIdx();

            // 保存数据
            MsOnionResult saveResult = contentService.saveContentDetail(msOnionApiVersion, contentView, operateId);
            return saveResult;
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 保存内容对象数据失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 保存内容对象数据失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }


    /**
    * 删除内容对象
    * @param req HttpServletRequest 对象
    * @param res HttpServletResponse  对象
    * @param idxStr 内容对象的ID
    * @return 页面视图
    */
    @RequestMapping("/content/list/removeContent")
    @ResponseBody
    public MsOnionResult getContentPage(HttpServletRequest req, HttpServletResponse res, String idxStr) {

        try {
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                this.getMsOnionLogger().error(getClass().getName(),
                        MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idxStr=" + idxStr);
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }
            Long idxLong = Long.parseLong(idxStr);

            if (idxLong == null || idxLong <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.DEL_CONTENT_PARAM_ERROR);
            }
            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 当前操作者ID
            long operateId = CurrentUserUtils.getCurrentUser().getIdx();

            MsOnionResult contentReomveResult = contentService.deleteContentByIdx(msOnionApiVersion, operateId, idxLong);

            return contentReomveResult;
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 删除内容对象失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 删除内容对象失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }
    ///////////////////////////////////////////////////// 内容 end ////////////////////////////////////////////////////

    ///////////////////////////////////////////////////// 关键词内容 begin ////////////////////////////////////////////

    /**
    * @return 关键词页面
    */
    @RequestMapping("/content/keywd/list")
    public String keywordList() {
        return "/content/listContentKeyword";
    }

   /**
   * 关键词详情
   * @param req HttpServletRequest 对象
   * @param res HttpServletResponse  对象
   * @param model  Model实例对象
   * @param idxStr 关键词主键ID
   * @throws MsOnionException 自定义异常
   * @return 页面视图
   */
    @RequestMapping("/content/keywd/detail")
    public String keywordDetail(HttpServletRequest req, HttpServletResponse res, Model model,  String idxStr) {
        try {
            Long idxLong = 0L;
            if (MsOnionStringUtils.isNotBlank(idxStr)) {
                if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                    this.getMsOnionLogger().error(getClass().getName(),
                            MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idxStr=" + idxStr);
                    return getError();
                }
                idxLong = Long.parseLong(idxStr);
            }
            if (idxLong != null && idxLong > 0) {
                // 版本对象
                MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
                msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

                // 查询对象详情
                ContentKeyword contentKeyword = contentKeywordService.queryByPrimaryKey(msOnionApiVersion, idxLong);
                if (contentKeyword != null) {
                    model.addAttribute("contentKeyword", MsOnionResult.ok(contentKeyword));
                } else {
                    MsOnionResult contentKeywordResult = MsOnionResult.
                                                    build(MsOnionStatusConstants.STATUS_400, ContentConstants.PARAM_ERROR);
                    model.addAttribute("contentKeyword", contentKeywordResult);
                }
            }
            return "/content/editContentKeyword";
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 进入关键词详情页面失败..");
            return getError();
        }
    }

    /**
     * 关键词分页数据查询
     *
     * @param req   HttpServletRequest 对象
     * @param res   HttpServletResponse  对象
     * @param page  当前页
     * @param rows  页大小
     * @param sort  排序字段
     * @param order 排序方式
     * @param keyword  关键词模糊查询
     * @param firstWord  首字母查询
     * @param isDelete 是否是删除数据 1-否 0-是
     * @return MsOnionResult
     */
    @RequestMapping("/content/keywd/getKeywordPage")
    @ResponseBody
    public MsOnionResult getKeywordPage(HttpServletRequest req, HttpServletResponse res, String page, String rows,
                                        String sort, String order, String keyword, String firstWord, String isDelete) {
        try {
            // 处理参数
            if (!MsOnionRegexUtils.checkDigit(page)) {
                page = MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE + "";
            }
            if (!MsOnionRegexUtils.checkDigit(rows)) {
                rows = MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE + "";
            }
            if (!MsOnionRegexUtils.isInteger(isDelete)) {
                isDelete = MsOnionStatusConstants.SQL_STATUS_ONE_VALUE + "";
            }
            int pageNum = Integer.parseInt(page);
            int pageSize = Integer.parseInt(rows);
            short isDeleteInt = Short.parseShort(isDelete);
            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 分页查询
            MsOnionResult pageResult = contentKeywordService.selectKeywordPage(msOnionApiVersion, pageNum,
                    pageSize, sort, order, keyword, firstWord, isDeleteInt);

            // 返回对象给客户端
            return new MsOnionResult().ok(pageResult.getData());
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 关键词分页数据查询失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 关键词分页数据查询失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }

    /**
    * 保存关键词
    * @param req HttpServletRequest 对象
    * @param res HttpServletResponse  对象
    * @param contentKeyword  关键词对象
    * @return MsOnionResult
    */
    @RequestMapping("/content/keywd/addKeyword")
    @ResponseBody
    public MsOnionResult addKeyword(HttpServletRequest req, HttpServletResponse res, ContentKeyword contentKeyword) {

        try {
            if (contentKeyword == null) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }
            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 当前操作者ID
            long operateId = CurrentUserUtils.getCurrentUser().getIdx();

            // 保存数据
            MsOnionResult keywordSaveResult = contentKeywordService.
                                              saveOrupdateKeyword(msOnionApiVersion, contentKeyword, operateId);

            return keywordSaveResult;
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 保存关键词失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 保存关键词失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }


    /**
     * 删除关键词
     * @param req HttpServletRequest 对象
     * @param res HttpServletResponse  对象
     * @param idxStr 内容对象的ID
     * @throws MsOnionException 自定义异常
     * @return 页面视图
     */
    @RequestMapping("/content/keywd/removeKeyword")
    @ResponseBody
    public MsOnionResult removeKeyword(HttpServletRequest req, HttpServletResponse res, String idxStr) {

        try {
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                this.getMsOnionLogger().error(getClass().getName(),
                        MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idxStr=" + idxStr);
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }
            Long idxLong = Long.parseLong(idxStr);
            if (idxLong == null || idxLong <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.DEL_CONTENT_PARAM_ERROR);
            }
            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 当前操作者ID
            long operateId = CurrentUserUtils.getCurrentUser().getIdx();

            // 删除对象
            MsOnionResult contentReomveResult = contentKeywordService.
                                                deleteKeywordByIdx(msOnionApiVersion, operateId, idxLong);

            return contentReomveResult;
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 删除关键词失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 删除关键词失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }
    ///////////////////////////////////////////////////// 关键词内容 end //////////////////////////////////////////////
}
