package com.service.imp.article;

import com.common.model.DataInfo;
import com.common.model.ServiceException;
import com.common.utils.DateFormatUtil;
import com.common.utils.MemberUtils;
import com.common.utils.PageUtils;
import com.common.utils.ProUtil;
import com.mapper.article.ArticleInfoMapper;
import com.mapper.article.ArticleMapper;
import com.mapper.category.CategoryArticleMapper;
import com.mapper.category.CategoryMapper;
import com.model.dto.article.AddArticleDTO;
import com.model.entity.article.Article;
import com.model.entity.article.ArticleInfo;
import com.model.entity.category.Category;
import com.model.entity.category.CategoryArticle;
import com.model.vo.article.ArticleListVO;
import com.model.vo.article.GetArticleVO;
import com.model.vo.node.GetNodeInfoVO;
import com.service.inf.article.ArticleService;
import com.service.inf.node.NodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleInfoMapper infoMapper;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryArticleMapper categoryArticleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addArticle(AddArticleDTO addArticleDTO) {
        Date date = new Date();
        if (ProUtil.isEmpty(addArticleDTO)){
            throw new ServiceException("你是傻逼吧");
        }


        Article article=new Article();
        BeanUtils.copyProperties(addArticleDTO,article);
        article.setCreateTime(date);
        Integer id = MemberUtils.getMemberInfo().getId();
        article.setUserId(id);
        articleMapper.insert(article);



        List<Integer> nodeIds = addArticleDTO.getNodeIds();
        if (nodeIds.isEmpty()){
            throw new ServiceException("你是傻逼吧");
        }
        ArticleInfo articleInfo;
        int i=1;
        for (Integer nodeId:nodeIds){
            articleInfo=new ArticleInfo();
            articleInfo.setArticleId(article.getId());
            articleInfo.setNodeId(nodeId);
            articleInfo.setSort(i);
            infoMapper.insert(articleInfo);
            i++;
        }
    }

    @Override
    public DataInfo<ArticleListVO> selectArticleList(String title, String content, Integer categoryId, Integer page, Integer pageSize) {
        PageUtils.startPage(page,pageSize);
        DataInfo<ArticleListVO> dataInfo = PageUtils.getDataInfo(articleMapper.selectArticleList(title, content, categoryId));
        return dataInfo;
    }

    @Override
    public GetArticleVO selectArticleById(Integer id) {
        GetArticleVO result=new GetArticleVO();
        Article article = articleMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(article,result);
        result.setCreateTime(DateFormatUtil.formatDate(article.getCreateTime(),DateFormatUtil.FORMAT));
        List<Integer> list = infoMapper.selectNodeIds(article.getId());
        List<GetNodeInfoVO>  nodeInfos=new ArrayList<>();
        result.setList(nodeInfos);
        for (Integer nodeId:list){
            GetNodeInfoVO nodeInfo = nodeService.getNodeInfo(nodeId);
            nodeInfos.add(nodeInfo);
        }
        return result;
    }
}
