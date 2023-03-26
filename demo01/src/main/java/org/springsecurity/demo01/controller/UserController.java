package org.springsecurity.demo01.controller;

import com.mysql.jdbc.log.Log;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springsecurity.demo01.common.CommonResponse;
import org.springsecurity.demo01.model.UserVO;
import org.springsecurity.demo01.security.NotAuthentication;

/**
 * 用户类
 *
 * @author wangtongzhou 
 * @since 2023-03-02 22:01
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @PreAuthorize("@ssc.hasPermission('sys:user:query2')")
    @PostMapping("/helloWord")
    public String hellWord(){
        return "hello word";
    }

    @GetMapping("/test")
    @NotAuthentication
    public String test(@RequestParam("type") Integer type){
        return "不需要认证";
    }


    @GetMapping("/test2/{id}")
    @NotAuthentication
    public String test(@PathVariable String id){
        return id+"不需要认证";
    }

}
