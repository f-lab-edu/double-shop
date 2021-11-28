package com.project.doubleshop.domain.member.entity;

import static java.time.LocalDateTime.*;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.doubleshop.domain.common.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Member {

	// 회원 pk
	private Long id;

	// 회원 아이디
	@NotBlank(message = "아이디를 입력해주세요")
	@Pattern(regexp = "^(?=.*[a-zA-Z0-9]).{6,12}$", message = "아이디는 영문/숫자 조합 6~12자리")
	private String userId;

	// 회원 비밀번호
	@NotBlank(message = "비밀번호를 입력해주세요")
	@Pattern(regexp = "^(?=.*[a-zA-Z0-9`~!@#$%^&*()\\-_+=\\\\]).{10,15}$", message = "비밀번호는 영문/숫자/특수문자 조합 10~15자리")
	private String password;

	// 회원 이름
	@NotBlank(message = "이름을 작성해주세요")
	@Size(max = 10, message = "이름은 10자 내외로 작성해주세요")
	private String name;

	// 회원 이메일
	@NotBlank(message = "이메일을 입력해주세요")
	@Email(message = "잘못된 이메일 형식입니다")
	private String email;

	// 회원 연락처
	@NotBlank(message = "휴대폰 번호를 입력해주세요(숫자만 입력)")
	@Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요")
	private String phone;

	// 로그인 횟수
	private Integer count;

	// 마지막 로그인 시간
	private LocalDateTime lastLoginTime;

	// 상태
	private Status status;

	// 상태 업데이트 시간
	@PastOrPresent(message = "field 'statusUpdateTime' must be present or past")
	private LocalDateTime statusUpdateTime;

	private LocalDateTime createTime;

	public Member(String userId, String password, String name, String email, String phone) {
		this(null, userId, password, name, email, phone, 0, null,
			Status.ACTIVATED, LocalDateTime.now(), LocalDateTime.now());
	}

	public void login(PasswordEncoder passwordEncoder, String credential) {
		if (!passwordEncoder.matches(credential, password)) {
			throw new IllegalArgumentException("Bad credential");
		}
	}

	public void afterSuccessLogin() {
		count++;
		lastLoginTime = now();
	}

	@Override
	public String toString() {
		return "Member{" +
			"id=" + id +
			", userId='" + userId + '\'' +
			", password='" + password + '\'' +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", phone='" + phone + '\'' +
			", count=" + count +
			", lastLoginTime=" + lastLoginTime +
			", status=" + status +
			", statusUpdateTime=" + statusUpdateTime +
			", createTime=" + createTime +
			'}';
	}
}
