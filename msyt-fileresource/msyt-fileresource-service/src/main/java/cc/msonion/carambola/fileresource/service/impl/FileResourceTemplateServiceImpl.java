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


package cc.msonion.carambola.fileresource.service.impl;

/**
 * @Title: FileResourceTemplateServiceImpl.java
 * @Package: cc.msonion.carambola.fileresource.service.impl
 * @Description: 文件模板service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年07月19日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年07月19日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.fileresource.common.constants.FileResourceConstants;
import cc.msonion.carambola.fileresource.interfaces.FileResourceTemplateService;
import cc.msonion.carambola.fileresource.pojo.FileResourceTemplate;
import cc.msonion.carambola.fileresource.pojo.FileResourceTemplateExample;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionPropertiesConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName: FileResourceTemplateServiceImpl
 * @Description: 文件模板service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年07月19日
 */
@Service
public class FileResourceTemplateServiceImpl extends MsOnionServiceExt<FileResourceTemplate, FileResourceTemplateExample>
        implements FileResourceTemplateService {
    /**
     * @param apiVersion Api版本
     * @param param      参数
     * @return MsOnionResult
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     * @Description: 检查参数
     */
    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param) throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(param)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "文件模板标题为空");
        }
        FileResourceTemplateExample example = new FileResourceTemplateExample();
        FileResourceTemplateExample.Criteria criteria = example.createCriteria();
        criteria.andTitleEqualTo(param.trim());
        List<FileResourceTemplate> list = queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isNotEmpty(list)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "文件模板标题已存在");
        }
        return MsOnionResult.ok();
    }

    /**
     * 保存
     *
     * @param apiVersion           Api版本
     * @param fileResourceTemplate 对象
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult saveFileTemplate(MsOnionApiVersion apiVersion, FileResourceTemplate fileResourceTemplate)
            throws MsOnionIllegalArgumentException, MsOnionException {
        fileResourceTemplate.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
        String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(fileResourceTemplate);
        if (StringUtils.isNotEmpty(valiateMsg)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
        }

        // 校验标题是否唯一
        MsOnionResult result1 = inspectParameter(apiVersion, fileResourceTemplate.getTitle());
        if (result1.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, result1.getMsg());
        }

        MsOnionResult msOnionResult = queryOneTemplate(apiVersion, fileResourceTemplate.getCategoryId(), fileResourceTemplate.getAppPlatform());
        if (msOnionResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, FileResourceConstants.INVALID_FILE_TEMPLATE);
        }
        // 生成Idx
        long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_FILERESOURCE_TEMPLATE_IDX_PATH);

        fileResourceTemplate.setIdx(idx);

        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_FILERESOURCE_TEMPLATE_VERSION_IDX_PATH);
        fileResourceTemplate.setVersion(version);

        int result = this.save(apiVersion, fileResourceTemplate);
        if (result > 0) {
            this.getMsOnionLogger().info(this.getClass().getName(), "## 文件模板新增成功, idx = " + idx);
            return MsOnionResult.ok();
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 更新
     *
     * @param apiVersion           Api版本
     * @param fileResourceTemplate 对象
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult updateFileTemplate(MsOnionApiVersion apiVersion, FileResourceTemplate fileResourceTemplate)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // update
        FileResourceTemplate template = this.queryByPrimaryKey(apiVersion, fileResourceTemplate.getIdx());
        String valiateMsg1 = MsOnionPojoValidatorUtils.validatePojo(fileResourceTemplate);
        if (StringUtils.isNotEmpty(valiateMsg1)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg1);
        }

        // 这些属性排除不需要copy
        String[] excludeArr = new String[]{MsOnionPropertiesConstants.IDX, MsOnionPropertiesConstants.IDX_CODE,
                MsOnionPropertiesConstants.CREATE_BY_MEMBER_IDX, MsOnionPropertiesConstants.CREATE_TIME,
                MsOnionPropertiesConstants.VERSION, MsOnionPropertiesConstants.STATUS};
        BeanUtils.copyProperties(fileResourceTemplate, template, excludeArr);

        if (fileResourceTemplate.getUpdateByMemberIdx() > 0L) {
            template.setUpdateByMemberIdx(fileResourceTemplate.getUpdateByMemberIdx());
        }
        template.setUpdateTime(new Date());

        int result = this.updateByPrimaryKey(apiVersion, template);
        if (result > 0) {
            this.getMsOnionLogger().info(this.getClass().getName(), "## 文件模板更新成功, idx = " + fileResourceTemplate.getIdx());
            return MsOnionResult.ok();
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);

    }

    /**
     * 更新
     *
     * @param apiVersion Api版本
     * @param categoryId 模板对象类别
     * @param appPlatform       模板所属商城
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult queryOneTemplate(MsOnionApiVersion apiVersion, Short categoryId, Short appPlatform)
            throws MsOnionIllegalArgumentException, MsOnionException {
        FileResourceTemplateExample example = new FileResourceTemplateExample();
        FileResourceTemplateExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        criteria.andAppPlatformEqualTo(appPlatform);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        FileResourceTemplate fileResourceTemplate = queryOne(apiVersion, example);
        return Optional.ofNullable(fileResourceTemplate).map(s -> MsOnionResult.ok(fileResourceTemplate))
                .orElse(MsOnionResult.build(MsOnionStatusConstants.STATUS_400, null));
    }
}
