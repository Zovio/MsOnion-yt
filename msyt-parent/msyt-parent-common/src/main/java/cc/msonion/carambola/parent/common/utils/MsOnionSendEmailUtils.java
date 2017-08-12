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


package cc.msonion.carambola.parent.common.utils;

/**
 * @Title: MsOnionSendEmailUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: 邮件发送
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月12日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月12日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.parent.common.exception.MsOnionException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName: MsOnionSendEmailUtils
 * @Description: 邮件发送工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月12日
 *
 */
public final class MsOnionSendEmailUtils {

    /**
     * 配置文件
     */
    private static Properties properties = null;

    /**
     * 邮箱名称
     */
    private static String name = null;

    /**
     * 密码
     */
    private static String pwd = null;

    private MsOnionSendEmailUtils() {
    }

    static {
        properties = new Properties();
        InputStream ins = null;
        try {
            // 读取配置文件
            ins = MsOnionSendEmailUtils.class.getResourceAsStream("/properties/config-mail.properties");
            properties.load(ins);

            name = properties.getProperty("username");
            pwd = properties.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ins != null) {
                // 关闭输入流
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     *  发送邮件
     * @param email  接收人
     * @param subject 主题
     * @param content 内容
     * @throws MsOnionException 异常
     */
    public static void sendEmail(String email, String subject, String content) throws MsOnionException {
        try {
           /* Properties props = new Properties();
            // 连接协议
            props.put("mail.transport.protocol", "smtp");
            // 主机名
            props.put("mail.smtp.host", "smtp.qq.com");
            // 端口号
            props.put("mail.smtp.port", "465");
            // 设置是否使用ssl安全连接  ---一般都使用
            props.put("mail.smtp.auth", "true");
            // 设置是否使用ssl安全连接  ---一般都使用
            props.put("mail.smtp.ssl.enable", "true");
            // 设置是否显示debug信息  true 会在控制台显示相关信息
            props.put("mail.debug", "true");*/
            // 得到会话对象
            Session session = Session.getInstance(properties);
            // 获取邮件对象
            Message message = new MimeMessage(session);
            // 设置发送时间
            message.setSentDate(new Date());
            // 设置发件人邮箱地址
            message.setFrom(new InternetAddress(name));
            // 设置收件人地址
            message.setRecipients(MimeMessage.RecipientType.TO, new InternetAddress[] {new InternetAddress(email)});
            // 设置邮件标题
            message.setSubject(subject);
            // 设置邮件内容
            message.setText(content);
            // 得到邮差对象
            Transport transport = session.getTransport();
            // 连接自己的邮箱账户 ,qq邮箱密码为授权码（其他邮箱需要验证）
            transport.connect(name, pwd);
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new MsOnionException(e);
        }
    }


    /**
     *  测试
     * @param args 参数
     * @throws Exception 异常
     */
    public static void main(String[] args) throws  Exception {
        MsOnionSendEmailUtils.sendEmail("286319801@qq.com", "杨桃后台找回密码", "请点击如下链接进行密码修改:http://www.hao123.com");
        System.out.println("发送完成..........");
    }
}
