package com.controller.article;

import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.DataInfo;
import com.common.model.Result;
import com.model.dto.article.AddArticleDTO;
import com.model.vo.article.ArticleListVO;
import com.model.vo.article.GetArticleVO;
import com.service.inf.article.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
@Api(tags = "文章")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Logs
    @Login
    @PostMapping("addArticle")
    @ApiOperation("添加文章")
    public Result addArticle(@RequestBody AddArticleDTO addArticleDTO){
        articleService.addArticle(addArticleDTO);
        return new Result();
    }

    @Logs
    @Login
    @GetMapping("selectArticleList")
    @ApiOperation("查询文章列表")
    public Result<DataInfo<ArticleListVO>> selectArticleList(@RequestParam(required = false) String title,
                                    @RequestParam(required = false) String content,
                                    @RequestParam(required = false) Integer categoryId,
                                    @RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer pageSize){
        DataInfo<ArticleListVO> result=articleService.selectArticleList(title,content,categoryId,page,pageSize);
        return new Result(result);
    }

    @Logs
    @Login
    @GetMapping("selectArticleById")
    @ApiOperation("查询文章详情")
    public Result selectArticleById(@RequestParam() Integer id){
        GetArticleVO result=articleService.selectArticleById(id);
        return new Result(result);
    }
}
