package com.controller.user;

import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.MemberInfo;
import com.common.model.Result;
import com.common.utils.MemberUtils;
import com.model.dto.user.LoginDTO;
import com.model.dto.user.RegisterDTO;
import com.model.dto.user.UpdatePasswordDTO;
import com.model.dto.user.UpdateUserDTO;
import com.service.inf.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Api(tags = "用户")
public class UserController {
    @Autowired
    private UserService userService;

    @Logs
    @PostMapping("login")
    @ApiOperation("登录")
    public Result login(@RequestBody LoginDTO loginDTO){
        userService.login(loginDTO);
        return new Result();
    }

    @Logs
    @PostMapping("register")
    @ApiOperation("注册")
    public Result register(@RequestBody RegisterDTO registerDTO){
        userService.register(registerDTO);
        return new Result();
    }

    @Logs
    @GetMapping("isHasUsername")
    @ApiOperation("是否存在用户名")
    public Result<Boolean> isHasUsername(@RequestParam String username){
        Boolean result=userService.isHasUsername(username);
        return new Result(result);
    }

    @Login
    @Logs
    @PostMapping("updateUser")
    @ApiOperation("修改用户信息")
    public Result<Boolean> updateUser(@RequestBody UpdateUserDTO updateUserDTO){
        userService.updateUser(updateUserDTO);
        return new Result();
    }

    @Login
    @Logs
    @PostMapping("updatePassword")
    @ApiOperation("修改密码")
    public Result updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO){
        userService.updatePassword(updatePasswordDTO);
        return new Result();
    }

    @Login
    @Logs
    @GetMapping("userInfo")
    @ApiOperation("获取用户信息")
    public Result<MemberInfo> userInfo(){
        return new Result(MemberUtils.getMemberInfo());
    }
}
