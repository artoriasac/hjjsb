package com.model.vo.node;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GetNodeInfoVO {
    private Integer id;

    private String mark;

    private Integer userId;

    private Date createTime;

    private List<NodeInfoVO> list;
}
