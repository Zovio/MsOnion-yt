package cc.msonion.carambola.manager.web.controller.collector;

import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HeroCao on 2017/4/14.
 */
@Controller // 不可以继承  ItemController ，因为SpringMVC 已经 Mapping
public class ItemExtController extends MsOnionBaseAppController {

    // TODO: 如果不需要就要删除，需要就把相关信息补全， 类的描述等等， by Hero 测试的！！！！

    /**
     * 新增成员
     *
     * @return 返回新增成员路径
     */
    @RequestMapping("/collector/item/add2")
    public String add2() {
        this.getMsOnionLogger().info(getClass().getName(), "/member/add2 ");

//        return "/member/member/index";

        return "/collector/item/test-add";

        // 存在跨越问题！！！
        // 测试，因为还没有 /member/member/add 页面
//        return "redirect:http://www.baidu.com";
    }

    /**
     * 成员列表
     *
     * @return 返回成员列表路径
     */
    @RequestMapping("/collector/item/list2")
    public String list2() {
        this.getMsOnionLogger().info(getClass().getName(), "/member/list2");

        return "/collector/item/test-list";

        // 测试，因为还没有 /member/member/add 页面
//        return "redirect:http://www.jd.com";
    }

    /**
     * 删除成员
     *
     * @return 返回删除成员路径
     */
    @RequestMapping("/collector/item/delete2")
    public String delete2() {
        this.getMsOnionLogger().info(getClass().getName(), "/member/delete2");

        return "/collector/item/test-delete";

        // 测试，因为还没有 /member/member/add 页面
//        return "redirect:https://www.taobao.com/";
    }

    /**
     * 编辑成员
     *
     * @return 返回编辑成员路径
     */
    @RequestMapping("/collector/item/update2")
    public String update2() {
        this.getMsOnionLogger().info(getClass().getName(), "/member/update2");

        return "/collector/item/test-edit";

        // 测试，因为还没有 /member/member/add 页面
//        return "redirect:https://www.tmall.com/";
    }

}
