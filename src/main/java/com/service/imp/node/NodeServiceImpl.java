package com.service.imp.node;

import com.common.model.DataInfo;
import com.common.model.ServiceException;
import com.common.utils.MemberUtils;
import com.common.utils.PageUtils;
import com.mapper.node.NodeInfoMapper;
import com.mapper.node.NodeMapper;
import com.model.dto.node.AddNodeDTO;
import com.model.entity.node.Node;
import com.model.entity.node.NodeInfo;
import com.model.vo.node.GetNodeInfoVO;
import com.model.vo.node.GetNodesVO;
import com.model.vo.node.NodeInfoVO;
import com.service.inf.dictionaries.DictionariesService;
import com.service.inf.node.NodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class NodeServiceImpl implements NodeService {
    @Autowired
    private NodeMapper nodeMapper;

    @Autowired
    private NodeInfoMapper nodeInfoMapper;

    @Autowired
    private DictionariesService dictionariesService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addNode(AddNodeDTO addNodeDTO) {
        Date date = new Date();
        if (addNodeDTO==null||addNodeDTO.getDictionariesIds()==null){
            throw new ServiceException("不能为空，你是傻逼吧");
        }
        Node node=new Node();
        BeanUtils.copyProperties(addNodeDTO,node);
        node.setCreateTime(date);
        if (MemberUtils.getMemberInfo()!=null){
            node.setUserId(MemberUtils.getMemberInfo().getId());
        }
        nodeMapper.insert(node);

        List<Integer> dictionariesIds = addNodeDTO.getDictionariesIds();
        if (dictionariesIds.isEmpty()){
            throw new ServiceException("不能为空，你是傻逼吧");
        }
        int i=1;
        for (Integer dictionariesId :dictionariesIds){
            if (dictionariesService.selectById(dictionariesId)==null){
                throw new ServiceException("字不存在");
            }
            NodeInfo nodeInfo=new NodeInfo();
            nodeInfo.setDictionariesId(dictionariesId);
            nodeInfo.setSort(i);
            nodeInfo.setNodeId(node.getId());
            nodeInfoMapper.insert(nodeInfo);
            i++;
        }
    }

    @Override
    public DataInfo<GetNodesVO> getNodes(Integer page, Integer pageSize, String mark, String startTime, String endTime) {
        PageUtils.startPage(page,pageSize);
        DataInfo<GetNodesVO> dataInfo = PageUtils.getDataInfo(nodeMapper.getNodes(mark, startTime, endTime));
        return dataInfo;
    }

    @Override
    public GetNodeInfoVO getNodeInfo(Integer nodeId) {
        Node node = nodeMapper.selectByPrimaryKey(nodeId);
        if (node==null){
            throw new ServiceException("笔记不存在");
        }
        GetNodeInfoVO vo=new GetNodeInfoVO();
        BeanUtils.copyProperties(node,vo);
        List<NodeInfoVO> list= nodeInfoMapper.getNodeInfo(nodeId);
        vo.setList(list);
        return vo;
    }
}
