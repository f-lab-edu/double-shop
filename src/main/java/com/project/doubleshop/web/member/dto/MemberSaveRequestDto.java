package com.project.doubleshop.web.member.dto;

import com.project.doubleshop.domain.member.entity.Status;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.utils.SHA256EncryptionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveRequestDto {

    private String userId;

    private String password;

    private String name;

    private String email;

    private String phone;

    public void encryptPassword(SHA256EncryptionUtil encryptionUtil) {
        this.password = encryptionUtil.encrypt(password);
    }

    public Member toEntity() {
        return Member.builder()
                .userId(this.userId)
                .password(this.password)
                .email(this.email)
                .phone(this.phone)
                .status(Status.NORMAL)
                .count(0)
                .build();
    }

}
