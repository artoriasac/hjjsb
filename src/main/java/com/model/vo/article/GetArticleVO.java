package com.model.vo.article;

import com.model.vo.node.GetNodeInfoVO;
import lombok.Data;

import java.util.List;

@Data
public class GetArticleVO {
    private Integer id;
    private String content;
    private String title;
    private String createTime;
    private List<GetNodeInfoVO> list;
}
