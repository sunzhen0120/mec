package com.njupt.controller;

import com.njupt.dto.GetTokenReq;
import com.njupt.res.CommonResult;
import com.njupt.service.TokenService;
import com.njupt.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("getToken")
    public void getTokenByUserNameAndPwd(HttpServletRequest request,
                                         HttpServletResponse response) {
        CommonResult commonResult = tokenService.getTokenByUserNameAndPwd();
        RestUtil.printData(response, commonResult);

    }
}
