package com.project.doubleshop.domain.member.entity;

import static java.time.LocalDateTime.*;
import static java.util.Objects.*;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.common.StatusConverter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

	// 회원 pk
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 회원 아이디
	@NotBlank(message = "UserId must be provided.")
	@Pattern(regexp = "^(?=.*[a-zA-Z0-9]).{6,12}$", message = "Must have 6 to 12 alphanumeric characters.")
	private String userId;

	// 회원 비밀번호
	@NotBlank(message = "Password must be provided.")
	private String password;

	// 회원 이름
	@NotBlank(message = "Name must be provided.")
	@Size(max = 10, message = "Must write your name in less than 10 characters.")
	private String name;

	// 회원 이메일
	@NotBlank(message = "Email must be provided.")
	@Email(message = "잘못된 이메일 형식입니다")
	private String email;

	// 회원 연락처
	@NotBlank(message = "Phone number must be provided.")
	@Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "Enter a valid mobile phone number(without hyphen sign).")
	private String phone;

	// 로그인 횟수
	private Integer count;

	// 마지막 로그인 시간
	private LocalDateTime lastLoginTime;

	// 상태
	@Convert(converter = StatusConverter.class)
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

	public void changePassword(PasswordEncoder passwordEncoder, String newPassword) {
		if (!this.password.equals(newPassword)) {
			encodePassword(passwordEncoder, newPassword);
		} else {
			throw new IllegalArgumentException("New Password Must be different with previous one");
		}
	}

	public void encodePassword(PasswordEncoder passwordEncoder, String password) {
		if (isValidPassword(password)) {
			this.password = passwordEncoder.encode(password);
		}
	}

	public void updateProfile(String userId, String name, String email, String phone) {
		if (nonNull(userId)) {
			this.userId = userId;
		}
		if (nonNull(name)) {
			this.name = name;
		}
		if (nonNull(email)) {
			this.email = email;
		}
		if (nonNull(phone)) {
			this.phone = phone;
		}
	}

	private boolean isValidPassword(String password) {
		if (!password.matches("^(?=.*[a-zA-Z0-9`~!@#$%^&*()\\-_+=\\\\]).{10,15}$")) {
			throw new IllegalArgumentException("You must enter 10 to 15 alphanumeric characters/special numbers.");
		}
		return true;
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
