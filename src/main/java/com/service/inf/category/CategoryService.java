package com.service.inf.category;

import com.common.model.DataInfo;
import com.model.dto.category.AddCategoryDTO;
import com.model.vo.category.CategoryVO;
import com.model.vo.category.SelectCategoryListVO;

public interface CategoryService {
    void addCategory(AddCategoryDTO addCategoryDTO);

    DataInfo<SelectCategoryListVO> selectCategoryList(String content, Integer page, Integer pageSize);

    CategoryVO selectCategory(Integer categoryId);
}
