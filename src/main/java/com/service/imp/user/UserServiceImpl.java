package com.service.imp.user;

import com.common.model.MemberInfo;
import com.common.model.ServiceException;
import com.common.utils.MD5Utils;
import com.common.utils.MemberUtils;
import com.common.utils.ProUtil;
import com.mapper.user.UserMapper;
import com.model.dto.user.LoginDTO;
import com.model.dto.user.RegisterDTO;
import com.model.dto.user.UpdatePasswordDTO;
import com.model.dto.user.UpdateUserDTO;
import com.model.entity.user.User;
import com.service.inf.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    private final static String CHAR_SET="UTF-8";

    @Override
    public void login(LoginDTO loginDTO) {
        if (ProUtil.isEmpty(loginDTO)){
            return;
        }
        String md5Encode = MD5Utils.MD5Encode(loginDTO.getPassword(), CHAR_SET);
        User user = userMapper.selectByUsernameAndPassword(loginDTO.getUserName(), md5Encode);
        if (user==null){
            return;
        }
        MemberInfo memberInfo=new MemberInfo();
        BeanUtils.copyProperties(user,memberInfo);
        MemberUtils.setMemberInfo(memberInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(RegisterDTO registerDTO) {
        if (registerDTO==null||registerDTO.getUsername()==null||registerDTO.getPassword()==null){
            return;
        }
        User user = userMapper.selectByUsername(registerDTO.getUsername());
        if (user!=null){
            throw new ServiceException("用户名存在");
        }
        user=new User();
        BeanUtils.copyProperties(registerDTO,user);
        user.setPassword(MD5Utils.MD5Encode(registerDTO.getPassword(),CHAR_SET));
        userMapper.insert(user);
    }

    @Override
    public Boolean isHasUsername(String username) {
        if (username==null){
            return null;
        }
        User user = userMapper.selectByUsername(username);
        if (user==null){
            return false;
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(UpdateUserDTO updateUserDTO) {
        if (updateUserDTO==null){
            return;
        }
        Integer id = MemberUtils.getMemberInfo().getId();
        User user = userMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(updateUserDTO,user);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        if (ProUtil.isEmpty(updatePasswordDTO)){
            return;
        }
        Integer id = MemberUtils.getMemberInfo().getId();
        User user = userMapper.selectByPrimaryKey(id);
        if (!user.getPassword().equals(MD5Utils.MD5Encode(updatePasswordDTO.getOriginPassword(),CHAR_SET))){
            throw new ServiceException("密码错误");
        }
        if (updatePasswordDTO.getOriginPassword().equals(updatePasswordDTO.getNewPassword())){
            throw new ServiceException("不能和原密码重复");
        }
        if (!updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getCheckNewPassword())){
            throw new ServiceException("2次密码不同");
        }
        String md5Encode = MD5Utils.MD5Encode(updatePasswordDTO.getNewPassword(), CHAR_SET);
        user.setPassword(md5Encode);
        userMapper.updateByPrimaryKey(user);
    }
}
