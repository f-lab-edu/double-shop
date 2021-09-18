package com.project.doubleshop.test.domain.mapper;

import com.project.doubleshop.test.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    Article selectById(Long id);
}
