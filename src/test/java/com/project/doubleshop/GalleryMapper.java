package com.project.doubleshop;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GalleryMapper {
    @Select("SELECT * FROM GALLERY WHERE id = #{id}")
    Gallery getArticle(@Param("id") Long id);
}
