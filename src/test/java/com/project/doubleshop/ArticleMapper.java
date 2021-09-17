package com.project.doubleshop;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ArticleMapper {
    @Select("SELECT * FROM ARTICLES WHERE id = #{id}")
    Article getArticle(@Param("id") Long id);
}
