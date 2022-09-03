package com.project.doubleshop.domain.member.entity;

import static java.time.LocalDateTime.*;
import static java.util.Objects.*;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userId;

	private String password;

	private String name;

	private String email;

	private String phone;

	private Integer count;

	private LocalDateTime lastLoginTime;

	@Convert(converter = StatusConverter.class)
	private Status status;

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
		this.password = passwordEncoder.encode(password);
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

	public static boolean isValidPassword(String password) {
		if (!password.matches("^(?=.*[a-zA-Z0-9`~!@#$%^&*()\\-_+=\\\\])$")) {
			throw new IllegalArgumentException("You must enter characters/special numbers.");
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
