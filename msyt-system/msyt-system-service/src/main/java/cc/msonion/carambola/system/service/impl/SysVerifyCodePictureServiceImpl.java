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
package cc.msonion.carambola.system.service.impl;
/**
 * @Title: SysVerifyCodePictureServiceImpl
 * @Package: cc.msonion.carambola.system.service.impl
 * @Description: 验证码图片service实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年07月19日 11:20:32
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年07月19日 11:20:32
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRandomUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import cc.msonion.carambola.system.common.SystemConstants;
import cc.msonion.carambola.system.common.SystemMessageConstants;
import cc.msonion.carambola.system.pojo.SysVerifyCodePicture;
import cc.msonion.carambola.system.pojo.SysVerifyCodePictureExample;
import cc.msonion.carambola.system.service.SysVerifyCodePictureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: SysVerifyCodePictureServiceImpl
 * @Description: 验证码图片service实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年07月19日 11:20:32
 */
@Service
public class SysVerifyCodePictureServiceImpl extends MsOnionServiceExt<SysVerifyCodePicture, SysVerifyCodePictureExample>
        implements SysVerifyCodePictureService {


    /**
     * 查询
     *
     * @param apiVersion api版本
     * @param pageNum    当前页
     * @param pageSize   页大小
     * @param remark     备注
     * @param type       类型
     * @param status     状态
     * @param orderName  排序字段
     * @param orderType  排序方式
     * @return MsOnionResult 对象
     * @throws MsOnionIllegalArgumentException 参数异常
     * @throws MsOnionException                未知异常
     */
    @Override
    public MsOnionResult selectVerifyCodePictureage(MsOnionApiVersion apiVersion, Integer pageNum, Integer pageSize,
                                                    String remark, Short type, Short status, String orderName, String orderType)
            throws MsOnionIllegalArgumentException, MsOnionException {

        SysVerifyCodePictureExample sysVerifyCodePictureExample = new SysVerifyCodePictureExample();
        SysVerifyCodePictureExample.Criteria criteria = sysVerifyCodePictureExample.createCriteria();

        if (status == null) {
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        } else {
            criteria.andStatusEqualTo(status);
        }
        if (type != null && type > 0) {
            criteria.andTypeEqualTo(type);
        }
        if (MsOnionStringUtils.isNotEmpty(remark)) {
            criteria.andRemarkLike(MsOnionConstants.PERCENT + remark.trim() + MsOnionConstants.PERCENT);
        }

        // 排序
        String orderBy = SystemConstants.ORDER_BY_DEFAULT;
        if (StringUtils.isNotBlank(orderName)
                && StringUtils.isNotBlank(orderType)) {
            orderBy = orderName.trim() + " " + orderType.trim();
        }

        // 分页查询
        MsOnionResultAdapter msOnionResultAdapter = queryListByPageForResult(apiVersion, sysVerifyCodePictureExample,
                pageNum, pageSize, orderBy);
        // 组装数据
        MsOnionPagingResult msOnionPagingResult = (MsOnionPagingResult) msOnionResultAdapter;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", msOnionPagingResult.getTotalCounts());
        map.put("rows", msOnionPagingResult.getData());

        return MsOnionResult.ok(map);
    }

    /**
     * 新增图片
     *
     * @param apiVersion api版本
     * @param picPath    图片路径
     * @param remark     备注信息
     * @param type       类型
     * @param operateId  操作者ID
     * @return MsOnionResult 对象
     * @throws MsOnionIllegalArgumentException 参数异常
     * @throws MsOnionException                未知异常
     */
    @Override
    public MsOnionResult saveVerifyCodePicture(MsOnionApiVersion apiVersion, String picPath, String remark,
                                               Short type, Long operateId)
            throws MsOnionIllegalArgumentException, MsOnionException {
        SysVerifyCodePicture sysVerifyCodePicture = new SysVerifyCodePicture();
        sysVerifyCodePicture.setImagePath(picPath);
        // 设置默认值
        sysVerifyCodePicture.setCreateByMemberIdx(operateId);
        sysVerifyCodePicture.setUpdateByMemberIdx(operateId);
        sysVerifyCodePicture.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
        // 生成idx,version
        long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(),
                this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                MsOnionZookeeperConstants.ZK_COUNTER_SYSTEM_VERIFY_CODE_PIC_IDX_PATH);
        sysVerifyCodePicture.setIdx(idx);
        long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(),
                this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                MsOnionZookeeperConstants.ZK_COUNTER_SYSTEM_VERIFY_CODE_PIC_VERSION_IDX_PATH);
        sysVerifyCodePicture.setVersion(version);
        sysVerifyCodePicture.setType(type);
        Date curDate = new Date();
        sysVerifyCodePicture.setCreateTime(curDate);
        sysVerifyCodePicture.setUpdateTime(curDate);
        sysVerifyCodePicture.setRemark(remark);
        String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(sysVerifyCodePicture);
        if (MsOnionStringUtils.isNotBlank(valiateMsg)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
        }
        // 保存
        int saveResult = save(apiVersion, sysVerifyCodePicture);
        if (saveResult <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, SystemMessageConstants.SAVE_VERIFY_CODE_FAIL);
        }
        return MsOnionResult.ok();
    }

    /**
     * 软删除验证码图片
     *
     * @param apiVersion api版本
     * @param idx        图片数据的ID
     * @param operateId  操作者ID
     * @return MsOnionResult 对象
     * @throws MsOnionIllegalArgumentException 参数异常
     * @throws MsOnionException                未知异常
     */
    @Override
    public MsOnionResult deleteVerifyCodePicture(MsOnionApiVersion apiVersion, Long idx, Long operateId)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if ((idx == null || idx <= 0)
                || (operateId == null || operateId <= 0)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }
        SysVerifyCodePicture sysVerifyCodePicture = queryByPrimaryKey(apiVersion, idx);
        if (sysVerifyCodePicture == null) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }
        sysVerifyCodePicture.setUpdateTime(new Date());
        sysVerifyCodePicture.setUpdateByMemberIdx(operateId);
        sysVerifyCodePicture.setStatus(MsOnionTableRecordStatus.DELETED.getValue());
        // 软删除
        int saveResult = updateByPrimaryKey(apiVersion, sysVerifyCodePicture);
        if (saveResult <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, SystemMessageConstants.SAVE_VERIFY_CODE_FAIL);
        }
        return MsOnionResult.ok();
    }


    /**
     * 随机查询验证码图片
     *
     * @param apiVersion api版本
     * @param size       图片数据条数
     * @return MsOnionResult 对象
     * @throws MsOnionIllegalArgumentException 参数异常
     * @throws MsOnionException                未知异常
     */
    @Override
    public MsOnionResult queryRandomVerifyCodePicture(MsOnionApiVersion apiVersion, Integer size)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 查询所有的数据
        SysVerifyCodePictureExample sysVerifyCodePictureExample = new SysVerifyCodePictureExample();
        SysVerifyCodePictureExample.Criteria criteria = sysVerifyCodePictureExample.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        // 全部查询，性能比较低，必须使用 queryRandomByExample
//        List<SysVerifyCodePicture> list = queryByExample(apiVersion, sysVerifyCodePictureExample);
        // 解决新增的验证码图片，没有马上使用的问题，加上排序 orderBy
        // 根据创建时间，随机时间进行查询，有序值，使用 between 性能比 in 高
//        criteria.andCreateTimeBetween(MsOnionDateUtils.randomDateForVerificationCode(), new Date());
        // 注意，这里随机查询的图片数量，不可以使用 size（4张），而是指定另外的数量，为了查询多条数据，然后再随机 size个
        List<SysVerifyCodePicture> list = queryRandomByExample(apiVersion, null, sysVerifyCodePictureExample, true,
                SystemConstants.VERIFYCODE_QUERY_SIZE, SystemConstants.VERIFYCODE_QUERY_RANDOM);
//        // 重试次数
//        int count = 0;
//        // 循环重试
//        while (MsOnionCollectionUtils.isEmpty(list) && count <= MsOnionConstants.RETRY_COUNT) {
////            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
////                    SystemMessageConstants.VERIFY_CODE_PICTURE_DATA_DEFICIENCIES);
//            // 重试次数 + 1
//            count++;
//            sysVerifyCodePictureExample = new SysVerifyCodePictureExample();
//            criteria = sysVerifyCodePictureExample.createCriteria();
//            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
//
//            criteria.andCreateTimeBetween(MsOnionDateUtils.randomDateForVerificationCode(), new Date());
////            // 注意，这里随机查询的图片数量，不可以使用 size（4张），而是指定另外的数量，为了查询多条数据，然后再随机 size个
//            list = queryRandomByExample(apiVersion, sysVerifyCodePictureExample,
//                    MsOnionPagingConstants.MS_ONION_QUERY_RANDOM_PAGESIZE_VALUE, SystemConstants.CREATE_TIME_ORDER_BY_DESC);
//        }
        if (MsOnionCollectionUtils.isEmpty(list)
                || list.size() < size) {
            // 返回失败结果
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    SystemMessageConstants.VERIFY_CODE_PICTURE_DATA_DEFICIENCIES);
        }
        // 然后随机抽取size条数据
//        List<SysVerifyCodePicture> returnList = new ArrayList<SysVerifyCodePicture>();
//        int[] nums = MsOnionRandomUtils.randomNoRepeat(1, list.size(), size);
//        for (int i : nums) {
//            returnList.add(list.get(i - 1));
//        }
        return MsOnionResult.ok(list);
    }
}
