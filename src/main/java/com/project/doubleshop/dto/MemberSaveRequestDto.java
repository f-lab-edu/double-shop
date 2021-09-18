package com.project.doubleshop.dto;

import com.project.doubleshop.model.enums.Status;
import com.project.doubleshop.model.member.Member;
import com.project.doubleshop.utils.SHA256EncryptionUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {

    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9]).{6,12}$", message = "아이디는 영문/숫자 조합 6~12자리")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9`~!@#$%^&*()\\-_+=\\\\]).{10,15}$", message = "비밀번호는 영문/숫자/특수문자 조합 10~15자리")
    private String password;

    @NotBlank(message = "이름을 작성해주세요")
    @Size(max = 10, message = "이름은 10자 내외로 작성해주세요")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "잘못된 이메일 형식입니다")
    private String email;

    @NotBlank(message = "휴대폰 번호를 입력해주세요(숫자만 입력)")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요")
    private String phone;

    @Builder
    public MemberSaveRequestDto(String userId, String password, String name, String email, String phone) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void passwordEncryption(SHA256EncryptionUtil encryptionUtil) {
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
