package com.controller.category;

import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.DataInfo;
import com.common.model.Result;
import com.model.dto.category.AddCategoryDTO;
import com.model.vo.category.CategoryVO;
import com.model.vo.category.SelectCategoryListVO;
import com.service.inf.category.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

@RestController
@RequestMapping("category")
@Api(tags = "分类")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Logs
    @Login
    @PostMapping("addCategory")
    @ApiOperation("添加分类")
    public Result addCategory(@RequestBody AddCategoryDTO addCategoryDTO){
        categoryService.addCategory(addCategoryDTO);
        return new Result();
    }

    @Logs
    @Login
    @GetMapping("selectCategoryList")
    @ApiOperation("查询分类")
    public Result selectCategoryList(@RequestParam(required = false) String content,
                              @RequestParam(required = false) Integer page,
                              @RequestParam(required = false) Integer pageSize){
        DataInfo<SelectCategoryListVO> result=categoryService.selectCategoryList(content,page,pageSize);
        return new Result(result);
    }

    @Logs
    @Login
    @GetMapping("selectCategory")
    @ApiOperation("查询分类详情")
    public Result selectCategory(@RequestParam Integer categoryId){
        CategoryVO result=categoryService.selectCategory(categoryId);
        return new Result(result);
    }
}
