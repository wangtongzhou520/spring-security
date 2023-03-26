package org.springsecurity.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springsecurity.demo2.common.CommonResponse;
import org.springsecurity.demo2.model.LoginVO;
import org.springsecurity.demo2.model.LoginDTO;
import org.springsecurity.demo2.security.NotAuthentication;
import org.springsecurity.demo2.service.LoginService;

/**
 * 用户类
 *
 * @author wangtongzhou 
 * @since 2023-03-02 22:01
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private LoginService loginService;

    @PreAuthorize("@ssc.hasPermission('sys:user:query')")
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


    @PostMapping("/login")
    @NotAuthentication
    public CommonResponse<LoginVO> test(@RequestBody LoginDTO dto){
        return CommonResponse.ok(loginService.login(dto));
    }


    @PostMapping("/refresh")
    @NotAuthentication
    public CommonResponse<LoginVO> test(@RequestHeader(name = "token") String token,
                                        @RequestParam String refreshToken){
        return CommonResponse.ok(loginService.refreshToken(token,refreshToken));
    }


}
