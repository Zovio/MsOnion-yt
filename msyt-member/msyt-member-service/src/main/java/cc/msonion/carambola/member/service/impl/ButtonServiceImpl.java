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


package cc.msonion.carambola.member.service.impl;

/**
 * @Title: ButtonServiceImpl.java
 * @Package: cc.msonion.carambola.member.service.impl
 * @Description: 功能按钮service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月05日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月05日
 * @Modify-version: V2.0.0
 * @Modify-description:
 *
 */

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.member.common.constants.MessageConstants;
import cc.msonion.carambola.member.common.constants.ParamTypeConstants;
import cc.msonion.carambola.member.common.constants.PropertiesConstants;
import cc.msonion.carambola.member.pojo.Button;
import cc.msonion.carambola.member.pojo.ButtonExample;
import cc.msonion.carambola.member.pojo.Menu;
import cc.msonion.carambola.member.service.ButtonService;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionPojoStringFieldUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ButtonServiceImpl
 * @Description: 功能按钮service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月05日
 *
 */
@Service
public class ButtonServiceImpl extends MsOnionServiceExt<Button, ButtonExample> implements ButtonService {


    private static final long serialVersionUID = -6796391725915389622L;



    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion,
                                          String param, Integer type) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (org.apache.commons.lang3.StringUtils.isBlank(param)) {
                return new MsOnionResult(MsOnionStatusConstants.STATUS_400,
                        MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL, param);
            }
            // 去掉左右空格
            param = param.trim();

            ButtonExample example = new ButtonExample();
            ButtonExample.Criteria criteria = example.createCriteria();

            String msg = null;
            if (ParamTypeConstants.BUTTON_TYPE_CODE == type) {
                criteria.andCodeEqualTo(param);
                msg = ParamTypeConstants.BUTTON_TYPE_CODE_MSG;
            } else {
                return new MsOnionResult(MsOnionStatusConstants.STATUS_400,
                        MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL, type);
            }
            // 查询
            List<Button> list = this.queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isEmpty(list)) {
                return new MsOnionResult(MsOnionStatusConstants.STATUS_200,  msg + "还不存在，可以使用", param);
            }

            return new MsOnionResult(MsOnionStatusConstants.STATUS_400, msg + "已经存在，不可以使用" + msg, param);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    @Override
    public MsOnionResult saveOrUpdate(MsOnionApiVersion apiVersion, Button button) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (null == button) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_INFO_NULL);
            }

            if (button.getIdx() == null) {
                //add
                button.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

                MsOnionPojoStringFieldUtils.inspectPojoStringFieldValue(button);
                String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(button);
                if (StringUtils.isNotEmpty(valiateMsg)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
                }
                // 查询数据库，校验唯一
                MsOnionResult moResult = this.inspectParameter(apiVersion, button.getCode(), ParamTypeConstants.BUTTON_TYPE_CODE);
                if (moResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return moResult;
                }

                // 生成Idx
                long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_BUTTON_IDX_PATH);
                // 设置 Idx
                button.setIdx(idx);


                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_BUTTON_VERSION_IDX_PATH);
                button.setVersion(version);

                int result = this.save(apiVersion, button);
                if (result > 0) {
                    this.getMsOnionLogger().info(this.getClass().getName(), "## 菜单新增成功, result = " + result);
                    return MsOnionResult.ok(button);
                }
            } else {
                // update
                MsOnionPojoStringFieldUtils.inspectPojoStringFieldValue(button);
                String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(button);
                if (StringUtils.isNotEmpty(valiateMsg)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
                }
                Button bt = this.queryByPrimaryKey(apiVersion, button.getIdx());
                // 这些属性排除不需要copy
                String[] excludeArr = new String[]{PropertiesConstants.CODE, PropertiesConstants.IDX_CODE,
                        PropertiesConstants.IDX, PropertiesConstants.CREATE_BY_MEMBER_IDX,
                        PropertiesConstants.CREATE_TIME, PropertiesConstants.VERSION, PropertiesConstants.STATUS};
                BeanUtils.copyProperties(button, bt, excludeArr);

                int result = this.updateByPrimaryKey(apiVersion, bt);
                if (result > 0) {
                    this.getMsOnionLogger().info(this.getClass().getName(), "## 按钮更新成功, result = " + result);
                    return MsOnionResult.ok();
                }
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_INFO_ERROR);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    @Override
    public MsOnionResult saveDefaultButtons(MsOnionApiVersion msOnionApiVersion, Menu menu) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            ButtonExample example = new ButtonExample();
            ButtonExample.Criteria criteria = example.createCriteria();
            criteria.andMenuIdxEqualTo(menu.getIdx());
            List<Button> buttonList = queryByExample(msOnionApiVersion, example);
            if (MsOnionCollectionUtils.isNotEmpty(buttonList)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, "### 菜单底下存在按钮了，就不默认生成了。 menuIdx:" + menu.getIdx());
            }

            // 生成四个默认安按钮（新增、修改、禁用、恢复）
            Map buttonMap = PropertiesConstants.getButtonMap();
            Iterator<Map.Entry<String, String>> it = buttonMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                Button button = new Button();
                button.setCreateByMemberIdx(menu.getUpdateByMemberIdx());
                button.setUpdateByMemberIdx(menu.getUpdateByMemberIdx());
                button.setMenuIdx(menu.getIdx());
                button.setName(entry.getValue());
                button.setCode(menu.getCode().replaceAll("\\*", entry.getKey()));
                saveOrUpdate(msOnionApiVersion, button);
            }
            return MsOnionResult.ok();
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }
}
