package com.mapper.category;

import com.model.entity.category.Category;
import com.model.vo.category.SelectCategoryListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    Category selectByContent(@Param("content") String content);

    List<SelectCategoryListVO> selectCategoryList(@Param("content")String content);
}