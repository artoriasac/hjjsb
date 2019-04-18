package com.controller.dictionaries;

import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.DataInfo;
import com.common.model.Result;
import com.model.dto.dictionaries.AddDictionariesDTO;
import com.model.dto.dictionaries.AddDictionariesInfoDTO;
import com.model.dto.dictionaries.AddDictionariesPronunciationDTO;
import com.model.entity.dictionaries.Dictionaries;
import com.model.entity.dictionaries.DictionariesInfo;
import com.model.entity.dictionaries.DictionariesPronunciation;
import com.model.vo.dictionaries.SelectDictionariesDetailsVO;
import com.model.vo.dictionaries.SelectDictionariesVO;
import com.service.inf.dictionaries.DictionariesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dictionaries")
@Api(tags = "字典")
public class DictionariesController {
    @Autowired
    private DictionariesService dictionariesService;

    @Logs
    @Login
    @PostMapping("addDictionaries")
    @ApiOperation("添加字")
    public Result addDictionaries(@RequestBody AddDictionariesDTO addDictionariesDTO){
        Dictionaries dictionaries=dictionariesService.addDictionaries(addDictionariesDTO);
        return new Result(dictionaries);
    }

    @Logs
    @Login
    @PostMapping("addDictionariesPronunciation")
    @ApiOperation("添加字音")
    public Result<DictionariesPronunciation> addDictionariesPronunciation(@RequestBody AddDictionariesPronunciationDTO addDictionariesPronunciationDTO){
        DictionariesPronunciation result=dictionariesService.addDictionariesPronunciation(addDictionariesPronunciationDTO);
        return new Result(result);
    }

    @Logs
    @Login
    @PostMapping("addDictionariesInfo")
    @ApiOperation("添加字详情")
    public Result<DictionariesInfo> addDictionariesInfo(@RequestBody AddDictionariesInfoDTO addDictionariesInfoDTO){
        DictionariesInfo result=dictionariesService.addDictionariesInfo(addDictionariesInfoDTO);
        return new Result(result);
    }

    @Logs
    @Login
    @GetMapping("selectDictionaries")
    @ApiOperation("查询字")
    public Result<SelectDictionariesVO> selectDictionaries(@RequestParam(required = false) Integer page,
                                                       @RequestParam(required = false) Integer pageSize,
                                                       @RequestParam(required = false) String shape,
                                                       @RequestParam(required = false) String word,
                                                       @RequestParam(required = false) String radicals,
                                                       @RequestParam(required = false) Integer stroke){
        DataInfo<SelectDictionariesVO> result=dictionariesService.selectDictionaries(page,pageSize,shape,word,radicals,stroke);
        return new Result(result);
    }

    @Logs
    @Login
    @GetMapping("selectDictionariesInfoVO")
    @ApiOperation("查询字的音和详情")
    public Result<SelectDictionariesDetailsVO> selectDictionariesInfoVO(@RequestParam(required = false) Integer dictionariesId){
        SelectDictionariesDetailsVO result=dictionariesService.selectDictionariesInfoVO(dictionariesId);
        return new Result(result);
    }
}
