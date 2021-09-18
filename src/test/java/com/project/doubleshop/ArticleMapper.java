package com.project.doubleshop;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    Article selectById(Long id);
}
