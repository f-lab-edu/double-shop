package com.project.doubleshop.domain.member.entity;

import com.project.doubleshop.domain.member.entity.Authority;
import com.project.doubleshop.domain.member.entity.Grade;
import com.project.doubleshop.domain.member.entity.Status;
import com.project.doubleshop.domain.member.entity.Type;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Integer id; // PK

    private String userId;

    private String password;

    private String name;

    private String phone;

    private String email;

    private Status status;

    private Authority authority;

    private Grade grade;

    private Type type;

    private Integer count; // 로그인 횟수

    private Date lastLoginTime; // 마지막 로그인 시간

}
