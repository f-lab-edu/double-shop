package com.project.doubleshop.domain.mapper;

import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberInfoMapper {

    void register(MemberSaveRequestDto requestDto);

    int getIdCount(String userId);

    int getEmailCount(String email);

    Optional<Member> findByUserId(String userId);

    boolean existsByUserIdAndPassword(String userId, String password);

}
