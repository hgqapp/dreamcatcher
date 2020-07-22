package com.hgq.dreamcatcher.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @ResponseBody
    @RequestMapping("/getNodeList")
    List<String> getNodeList(@RequestParam(defaultValue = "false") boolean refresh) throws Exception {
        NodeFetcher me = NodeFetcher.me();
        me.refresh(refresh);
        return me.fetch();
    }
}
