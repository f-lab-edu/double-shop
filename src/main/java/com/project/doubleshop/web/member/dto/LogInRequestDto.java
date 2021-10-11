package com.project.doubleshop.web.member.dto;

import com.project.doubleshop.domain.utils.SHA256EncryptionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LogInRequestDto {

    private String userId;

    private String password;

    public void encryptPassword(SHA256EncryptionUtil encryptionUtil) {
        this.password = encryptionUtil.encrypt(password);
    }

}
