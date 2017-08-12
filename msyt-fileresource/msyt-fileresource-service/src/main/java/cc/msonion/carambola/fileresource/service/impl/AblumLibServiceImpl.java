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
 * @Title: AblumLibServiceImpl.java
 * @Package: cc.msonion.carambola.fileresource.service.impl
 * @Description: 相册库实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月18日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月18日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.fileresource.common.constants.FileResourceConstants;
import cc.msonion.carambola.fileresource.interfaces.AblumLibService;
import cc.msonion.carambola.fileresource.pojo.AblumLib;
import cc.msonion.carambola.fileresource.pojo.AblumLibExample;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: AblumLibServiceImpl
 * @Description: 相册库实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月18日
 */
@Service
public class AblumLibServiceImpl extends MsOnionServiceExt<AblumLib, AblumLibExample>
        implements AblumLibService {


    private static final long serialVersionUID = -8200662492493097902L;

    /**
     * 保存
     *
     * @param apiVersion Api版本
     * @param ablumLib   对象
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult saveAblumLib(MsOnionApiVersion apiVersion, AblumLib ablumLib) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (null == ablumLib) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, FileResourceConstants.POJO_NOT_NULL);
            }

            final String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(ablumLib);
            if (StringUtils.isNotEmpty(valiateMsg)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
            }


            ablumLib.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

            // 生成Idx
            long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_FILERESOURCE_ABLUMLIB_IDX_PATH);
            ablumLib.setIdx(idx);


            Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_FILERESOURCE_ABLUMLIB_VERSION_IDX_PATH);
            ablumLib.setVersion(version);

            int result = this.save(apiVersion, ablumLib);
            if (result > 0) {
                this.getMsOnionLogger().info(this.getClass().getName(), "## 相册库新增成功, idx = " + idx);
                return MsOnionResult.ok(ablumLib);
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, FileResourceConstants.FAIL);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取 相册集合
     *
     * @param apiVersion Api版本
     * @param ablumLib   对象
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult getAblumLibs(MsOnionApiVersion apiVersion, AblumLib ablumLib) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (null == ablumLib) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, FileResourceConstants.POJO_NOT_NULL);
            }

            if (ablumLib.getAblumType() <= 0L) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "相册类型为空");
            }

            AblumLibExample example = new AblumLibExample();

            AblumLibExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
            criteria.andAblumTypeEqualTo(ablumLib.getAblumType());

            if (StringUtils.isNotBlank(ablumLib.getName())) {
                criteria.andNameEqualTo(ablumLib.getName().trim());
            }

            if (ablumLib.getPidx() > 0L) {
                criteria.andPidxEqualTo(ablumLib.getPidx());
            }

            if (ablumLib.getCategory() >= 0) {
                criteria.andCategoryEqualTo(ablumLib.getCategory());
            }

            List<AblumLib> ablumLibs = queryByExample(apiVersion, example);
            return MsOnionResult.ok(ablumLibs);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取 根目录
     *
     * @param apiVersion 版本号
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @Override
    public MsOnionResult getRootAblumLib(MsOnionApiVersion apiVersion) throws MsOnionException {

        AblumLibExample example = new AblumLibExample();

        AblumLibExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        criteria.andPidxEqualTo(Long.valueOf(MsOnionConstants.NO));
        List<AblumLib> ablumLibs = queryByExample(apiVersion, example);
        return MsOnionResult.ok(ablumLibs);
    }

    /**
     * 通过文件获取
     *
     * @param apiVersion 版本号
     * @param fileIdx    文件idx
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @Override
    public MsOnionResult getAblumLibByFileIdx(MsOnionApiVersion apiVersion, Long fileIdx) throws MsOnionException {
        AblumLibExample ablumLibExample = new AblumLibExample();
        AblumLibExample.Criteria criteria = ablumLibExample.createCriteria();
        criteria.andFileIdxEqualTo(Long.valueOf(fileIdx));
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        List<AblumLib> ablumLibs = queryByExample(apiVersion, ablumLibExample);
        if (MsOnionCollectionUtils.isNotEmpty(ablumLibs)) {
            return MsOnionResult.ok(ablumLibs.get(0));
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, null);
    }

    /**
     * 获取相册目录的子目录或者文件
     *
     * @param apiVersion 版本号
     * @param pIdx       文件idx
     * @param ablumType  相册类型
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @Override
    public MsOnionResult getAblumLibListByPidx(MsOnionApiVersion apiVersion, Long pIdx, Short ablumType) throws MsOnionException {
        AblumLibExample example = new AblumLibExample();
        AblumLibExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        criteria.andPidxEqualTo(pIdx);
        criteria.andAblumTypeEqualTo(ablumType);
        List<AblumLib> ablumLibs = queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isNotEmpty(ablumLibs)) {
            return MsOnionResult.ok(ablumLibs);
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, null);

    }
}
