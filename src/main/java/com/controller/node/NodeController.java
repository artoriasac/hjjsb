package com.controller.node;

import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.DataInfo;
import com.common.model.Result;
import com.model.dto.node.AddNodeDTO;
import com.model.vo.node.GetNodeInfoVO;
import com.model.vo.node.GetNodesVO;
import com.service.inf.node.NodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("node")
@Api(tags = "笔记")
public class NodeController {
    @Autowired
    private NodeService nodeService;

    @Logs
    @Login
    @PostMapping("addNode")
    @ApiOperation("添加笔记")
    public Result addNode(@RequestBody AddNodeDTO addNodeDTO){
        nodeService.addNode(addNodeDTO);
        return new Result();
    }

    @Logs
    @Login
    @GetMapping("getNodes")
    @ApiOperation("查询笔记")
    public Result<DataInfo<GetNodesVO>> getNodes(@RequestParam(required = false) Integer page,
                           @RequestParam(required = false) Integer pageSize,
                           @RequestParam(required = false) String mark,
                           @RequestParam(required = false) String startTime,
                           @RequestParam(required = false) String endTime){
        DataInfo<GetNodesVO> result=nodeService.getNodes(page,pageSize,mark,startTime,endTime);
        return new Result(result);
    }

    @Logs
    @Login
    @GetMapping("getNodeInfo")
    @ApiOperation("笔记详情")
    public Result<GetNodeInfoVO> getNodeInfo(@RequestParam(required = false) Integer nodeId){
        GetNodeInfoVO result=nodeService.getNodeInfo(nodeId);
        return new Result(result);
    }
}
