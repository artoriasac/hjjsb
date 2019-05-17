package com.service.imp.category;

import com.common.model.DataInfo;
import com.common.model.ServiceException;
import com.common.utils.MemberUtils;
import com.common.utils.PageUtils;
import com.common.utils.ProUtil;
import com.mapper.article.ArticleMapper;
import com.mapper.category.CategoryArticleMapper;
import com.mapper.category.CategoryMapper;
import com.model.dto.category.AddCategoryDTO;
import com.model.entity.category.Category;
import com.model.entity.category.CategoryArticle;
import com.model.vo.article.ArticleListVO;
import com.model.vo.category.CategoryVO;
import com.model.vo.category.SelectCategoryListVO;
import com.service.inf.category.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryArticleMapper categoryArticleMapper;

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCategory(AddCategoryDTO addCategoryDTO) {
        Date date = new Date();
        if (ProUtil.isEmpty(addCategoryDTO)){
            throw new ServiceException("你是傻逼吧");
        }

        Category category=categoryMapper.selectByContent(addCategoryDTO.getContent());
        if (category!=null){
            throw new ServiceException("你是傻逼吧");
        }
        List<Integer> articleIds = addCategoryDTO.getArticleIds();
        if (articleIds.isEmpty()){
            throw new ServiceException("你是傻逼吧");
        }

        category=new Category();
        category.setCreateTime(date);
        BeanUtils.copyProperties(addCategoryDTO,category);
        category.setUserId(MemberUtils.getMemberInfo().getId());
        categoryMapper.insert(category);

        for (Integer articleId:articleIds
             ) {
            CategoryArticle categoryArticle=new CategoryArticle();
            categoryArticle.setArticleId(articleId);
            categoryArticle.setCategoryId(category.getId());
            categoryArticleMapper.insert(categoryArticle);
        }
    }

    @Override
    public DataInfo<SelectCategoryListVO> selectCategoryList(String content, Integer page, Integer pageSize) {
        PageUtils.startPage(page,pageSize);
        return PageUtils.getDataInfo(categoryMapper.selectCategoryList(content));
    }

    @Override
    public CategoryVO selectCategory(Integer categoryId) {
        CategoryVO result=new CategoryVO();
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        result.setContent(category.getContent());
        List<ArticleListVO> articleListVOS = articleMapper.selectArticleList(null, null, categoryId);
        result.setList(articleListVOS);
        return result;
    }
}
