package com.project.doubleshop;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper {
    Article selectById(Long id);
}
