package com.wen.weaving.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wen.weaving.entity.User;
import com.wen.weaving.entity.vo.UserColl.LoginInfo;
import com.wen.weaving.entity.vo.UserColl.UpdateInfo;
import com.wen.weaving.service.UserService;
import com.wen.weaving.utils.OpenIdUtil;
import com.wen.weaving.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.Semaphore;

@Api(tags = {"用户相关接口"},value = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private OpenIdUtil openIdUtil;
    @ApiOperation(value = "用户登录注册接口")
    @PostMapping("/login")
    public R<?> loginUser(@ApiParam(name = "loginInfo",value = "用户登录信息",required = true)@RequestBody LoginInfo loginInfo){
        System.out.println("user登录接口调用了");
        String openid = openIdUtil.getOpenid(loginInfo.getCode());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        User user = userService.getBaseMapper().selectOne(wrapper);
        if (user==null){
            User newUser = new User();
            newUser.setOpenid(openid);
            int res = userService.getBaseMapper().insert(newUser);
            if (res==1){
                return R.ok().data(newUser).message("用户注册成功");
            }else {
                return R.error().message("注册失败");
            }
        }else {
            return R.ok().data(user).message("用户登录成功");
        }
    }

    @ApiOperation(value = "修改用户信息")
    @PutMapping()
    public R<?> updateUser(@ApiParam(name = "updateInfo",value = "更新信息",required = true)@RequestBody UpdateInfo updateInfo){
        System.out.println("user修改信息接口调用了");
        System.out.println("suer:"+updateInfo);
        User user = userService.getBaseMapper().selectById(updateInfo.getId());
        user.setSex(updateInfo.getSex());
        user.setBirthday(updateInfo.getBirthday());
        int res = userService.getBaseMapper().updateById(user);
        if (res==1){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "用户注销操作")
    @DeleteMapping("/delete/{id}")
    public R<?> deleteUser(@ApiParam(name = "id",value = "用户id",required = true)@PathVariable Integer id){
        int res = userService.getBaseMapper().deleteById(id);
        if (res==1){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/{id}")
    public R<?> getUser(@ApiParam(name = "id",value = "用户id",required = true)@PathVariable Integer id){
        System.out.println("user获取信息接口调用了");
        User user = userService.getBaseMapper().selectById(id);
        return R.ok().data(user);
    }
}
