package com.mapper.node;

import com.model.entity.node.NodeInfo;
import com.model.vo.node.NodeInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NodeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NodeInfo record);

    int insertSelective(NodeInfo record);

    NodeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NodeInfo record);

    int updateByPrimaryKey(NodeInfo record);

    List<NodeInfoVO> getNodeInfo(Integer nodeId);
}