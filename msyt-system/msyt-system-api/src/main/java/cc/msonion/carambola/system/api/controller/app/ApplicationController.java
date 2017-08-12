package cc.msonion.carambola.system.api.controller.app;

import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by HeroCao on 2017/4/17.
 */
@Controller
public class ApplicationController extends MsOnionBaseAppController {


    /**
     * 拦截所有匹配不到的路径，"/**" 拦截所有请求 , 包括 JS ， CSS，所有的请求，都会执行这里 ！！！
     *
     * @param req   HttpServletRequest实例对象
     * @param res   HttpServletResponse实例对象
     * @param model Model实例对象
     * @return 返回路径
     */
    @RequestMapping("/**")
    @ResponseBody
    public MsOnionResultAdapter showAll(HttpServletRequest req, HttpServletResponse res, Model model) {

        this.getMsOnionLogger().info(getClass().getName(), "showAll #  所有非法请求 ， 域名信息，this.domain=" + this.getMsOnionDomain());

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, "非法请求！");
    }
}
