package com.project.doubleshop.model.member;

import com.project.doubleshop.model.enums.Authority;
import com.project.doubleshop.model.enums.Grade;
import com.project.doubleshop.model.enums.Status;
import com.project.doubleshop.model.enums.Type;
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

    private Integer count;

    private Date lastLoginTime;

}
