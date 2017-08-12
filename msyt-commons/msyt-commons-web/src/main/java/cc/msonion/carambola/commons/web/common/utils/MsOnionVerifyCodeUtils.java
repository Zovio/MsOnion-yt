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
 *
 * @Title: MsOnionVerifyCodeUtils.java
 * @Package: cc.msonion.carambola.commons.common.utils
 * @Description: 验证码工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月22日 下午6:48:46
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月22日 下午6:48:46
 * @Modify-version: V2.0.0
 * @Modify-description: 新增生成验证码的方法：generateVerifyCode
 */

/**
 * @Title: MsOnionVerifyCodeUtils.java
 * @Package: cc.msonion.carambola.commons.common.utils
 * @Description: 验证码工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月22日 下午6:48:46
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月22日 下午6:48:46
 * @Modify-version: V2.0.0
 * @Modify-description: 新增生成验证码的方法：generateVerifyCode
 */
package cc.msonion.carambola.commons.web.common.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.pojo.MsOnionPictureVerifyCode;
import cc.msonion.carambola.parent.pojo.MsOnionVerifyCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.List;


/**
 * @ClassName: MsOnionVerifyCodeUtils
 * @Description: 验证码工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月22日 下午6:48:46
 *
 */
public final class MsOnionVerifyCodeUtils {

    private MsOnionVerifyCodeUtils() {
    }

    /**
     * 验证码的名称
     */
    public static final String ATTRIBUTE_NAME_VERIFY_CODE = "cc.msyc.verifyCode";

    /**
     * 默认Expires
     */
    private static final int DEFAULT_EXPIRES = 0;

    /**
     * 生成验证码
     * @param req HttpServletRequest实例对象
     * @param res HttpServletResponse实例对象
     * @return 返回生成的验证码，也可以通过使用 session.getAttribute(ATTRIBUTE_NAME_VERIFY_CODE); 获取到验证码
     * @throws MsOnionException MsOnionException异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月25日 下午1:45:44
     */
    public static String generateVerifyCode(HttpServletRequest req, HttpServletResponse res) throws MsOnionException {
        try {
            // 设置响应的类型格式为图片格式
            res.setContentType("image/jpeg");
            // 禁止图像缓存。
            res.setHeader("Pragma", "no-cache");
            res.setHeader("Cache-Control", "no-cache");
            res.setDateHeader("Expires", DEFAULT_EXPIRES);

            MsOnionVerifyCode vCode = new MsOnionVerifyCode();

            // 存入会话session
//            HttpSession session = req.getSession(true);
            // 删除以前的
//            session.removeAttribute(ATTRIBUTE_NAME_VERIFY_CODE);

            String verifyCode = vCode.getCode();

            // 设置验证码
//            session.setAttribute(ATTRIBUTE_NAME_VERIFY_CODE, verifyCode);

            vCode.write(res.getOutputStream());

            // 返回生成的验证码
            return verifyCode;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }


    /**
     * 生成验证码
     * @param req HttpServletRequest实例对象
     * @param res HttpServletResponse实例对象
     * @param verifyCodePictureSize 验证码图片的宽高尺寸
     * @param ring 图片旋转一周的次数
     * @param fileList 图片集合
     * @return 返回生成的验证码的值
     * @throws MsOnionException MsOnionException异常
     */
    public static String generateVerifyCodePicture(HttpServletRequest req, HttpServletResponse res,
                                                   int verifyCodePictureSize, int ring, List<BufferedImage> fileList) throws MsOnionException {
        try {
            // 设置响应的类型格式为图片格式
            res.setContentType("image/jpeg");
            // 禁止图像缓存。
            res.setHeader("Pragma", "no-cache");
            res.setHeader("Cache-Control", "no-cache");
            res.setDateHeader("Expires", DEFAULT_EXPIRES);

            MsOnionPictureVerifyCode pictureVerifyCode = new MsOnionPictureVerifyCode(verifyCodePictureSize, ring, fileList);
            pictureVerifyCode.write(res.getOutputStream());

            int[] codeVaule = pictureVerifyCode.getCodeValue();
            StringBuffer sb = new StringBuffer();
            for (int i : codeVaule) {
                sb.append(i);
            }
            // 返回生成的验证码
            return sb.toString();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 验证码校验
     * @param serverCode 服务端的验证码的值
     * @param requestCode 客户端提交的验证码的值
     * @param verifyCodePictureNum 验证码图片个数
     * @param verifyCodePictureRing 验证码旋转一次的次数
     * @return 返回结果，true正确，false错误
     * @throws MsOnionException MsOnionException异常
     */
    public static boolean checkCode(String serverCode, String requestCode, int verifyCodePictureNum, int verifyCodePictureRing) throws MsOnionException {
        try {
            if (MsOnionStringUtils.isEmpty(serverCode)
                    || MsOnionStringUtils.isEmpty(requestCode)
                    || (serverCode.length() != verifyCodePictureNum)
                    || (requestCode.length() != verifyCodePictureNum)) {
                return false;
            }
            char[] words = requestCode.toCharArray();
            StringBuffer rValue = new StringBuffer();
            for (char c : words) {
                int i = Integer.parseInt(String.valueOf(c));
                int j = i % verifyCodePictureRing;
                rValue.append(j);
            }

            if (!serverCode.equals(rValue.toString())) {
                return false;
            }

            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    /**
     * 从Session中获取验证码
     * @param req HttpServletRequest实例对象
     * @param res HttpServletResponse实例对象
     * @return 返回验证码
     * @throws MsOnionException MsOnionException异常
     */
    @Deprecated
    public static String getVerifyCodeFromSession(HttpServletRequest req, HttpServletResponse res) throws MsOnionException {
        try {
            // 存入会话session
            HttpSession session = req.getSession();
            return (String) session.getAttribute(ATTRIBUTE_NAME_VERIFY_CODE);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

}
