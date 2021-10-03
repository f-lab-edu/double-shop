package com.project.doubleshop.domain.member.entity;

import com.project.doubleshop.domain.member.entity.Authority;
import com.project.doubleshop.domain.member.entity.Grade;
import com.project.doubleshop.domain.member.entity.Status;
import com.project.doubleshop.domain.member.entity.Type;
import com.project.doubleshop.web.member.dto.MemberFindResponseDto;
import com.project.doubleshop.web.member.dto.MemberInfoDto;
import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Integer id;

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

    private Status status;

    private Authority authority;

    private Grade grade;

    private Type type;

    private Integer count; // 로그인 횟수

    private Date lastLoginTime; // 마지막 로그인 시간

    public MemberInfoDto toMemberInfoDto() {
        return MemberInfoDto.builder()
                .userId(this.getUserId())
                .name(this.getName())
                .phone(this.getPhone())
                .email(this.getEmail())
                .build();
    }

    public MemberFindResponseDto toMemberFindResponseDto() {
        return MemberFindResponseDto.builder()
                .email(this.getEmail())
                .phone(this.getPhone())
                .build();
    }

    public MemberSaveRequestDto toMemberSaveRequestDto() {
        return MemberSaveRequestDto.builder()
                .userId(this.getUserId())
                .password(this.getPassword())
                .name(this.getName())
                .email(this.getEmail())
                .phone(this.getPhone())
                .build();
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
