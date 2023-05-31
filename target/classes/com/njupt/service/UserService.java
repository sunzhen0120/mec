package com.njupt.service;

import com.njupt.dto.*;
import com.njupt.res.CommonResult;

public interface UserService {

    CommonResult create(CreateUserReq userReq);

    CommonResult selectByPage(SelectUserReq selectUserReq);

    CommonResult update(UpdateUserReq userReq);

    CommonResult resetpassword(Long userId);

    CommonResult statusChange(Long userId,String status);

    CommonResult delete(Long userId);

    CommonResult login(LoginReq loginReq);

    CommonResult getUserInfo(String userId);

}
