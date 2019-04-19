package com.service.inf.node;

import com.common.model.DataInfo;
import com.model.dto.node.AddNodeDTO;
import com.model.vo.node.GetNodeInfoVO;
import com.model.vo.node.GetNodesVO;

public interface NodeService {
    void addNode(AddNodeDTO addNodeDTO);

    DataInfo<GetNodesVO> getNodes(Integer page, Integer pageSize, String mark, String startTime, String endTime);

    GetNodeInfoVO getNodeInfo(Integer nodeId);
}
