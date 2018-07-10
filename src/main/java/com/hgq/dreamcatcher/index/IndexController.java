package com.hgq.dreamcatcher.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author houguangqiang
 * @date 2018-07-10
 * @since 1.0
 */
@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
