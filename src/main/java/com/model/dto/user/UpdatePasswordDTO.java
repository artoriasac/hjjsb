package com.model.dto.user;

import lombok.Data;

@Data
public class UpdatePasswordDTO {
    private String originPassword;

    private String newPassword;

    private String checkNewPassword;
}
