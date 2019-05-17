package com.service.inf.user;

import com.model.dto.user.LoginDTO;
import com.model.dto.user.RegisterDTO;
import com.model.dto.user.UpdatePasswordDTO;
import com.model.dto.user.UpdateUserDTO;

public interface UserService {
    void login(LoginDTO loginDTO);

    void register(RegisterDTO registerDTO);

    Boolean isHasUsername(String username);

    void updateUser(UpdateUserDTO updateUserDTO);

    void updatePassword(UpdatePasswordDTO updatePasswordDTO);

}
