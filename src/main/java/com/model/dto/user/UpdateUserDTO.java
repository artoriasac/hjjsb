package com.model.dto.user;

import lombok.Data;

@Data
public class UpdateUserDTO {

    private String realName;

    private String phone;

    private String eMail;

    private String gender;

    private String headUrl;

    private String mark;
}
