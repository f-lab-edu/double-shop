package com.project.doubleshop.domain.mapper;

import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberInfoMapper {

    void register(MemberSaveRequestDto requestDto);

    int getIdCount(String userId);

    int getEmailCount(String email);

}
