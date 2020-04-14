package lf.ssm.core.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname BasePageController
 * @Date 2019/4/18 14:45
 * @Created by 刘锋
 */

@Controller
@RequestMapping("/page")
public class BasePageController {
    /**
     * 根目录的跳转
     * @author liufeng
     * @date 2019/4/18 14:49
     * @return org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("/{page}")
    public ModelAndView toPage(@PathVariable("page") String page){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vue/"+page);
        return modelAndView;
    }

    /**
     *两层页面的跳转
     * @author liufeng
     * @date 2019/4/18 14:50
     * @return org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("/{moduleName}/{page}")
    public ModelAndView toPage(@PathVariable("moduleName") String moduleName,@PathVariable("page") String page){  //根目录跳转页controller
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vue/"+moduleName+"/"+page);
        return modelAndView;
    }


}
