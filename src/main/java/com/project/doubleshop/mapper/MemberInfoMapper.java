package com.project.doubleshop.mapper;

import com.project.doubleshop.dto.MemberSaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberInfoMapper {

    void register(MemberSaveRequestDto requestDto);

    int checkId(String userId);

    int checkEmail(String email);

}
