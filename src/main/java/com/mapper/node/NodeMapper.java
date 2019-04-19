package com.mapper.node;

import com.model.entity.node.Node;
import com.model.vo.node.GetNodesVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Node record);

    int insertSelective(Node record);

    Node selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);

    List<GetNodesVO> getNodes(@Param("mark") String mark,@Param("startTime") String startTime,@Param("endTime") String endTime);
}