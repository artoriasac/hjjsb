package com.service.imp.dictionaries;

import com.common.model.DataInfo;
import com.common.model.ServiceException;
import com.common.utils.PageUtils;
import com.common.utils.ProUtil;
import com.mapper.dictionaries.DictionariesInfoMapper;
import com.mapper.dictionaries.DictionariesMapper;
import com.mapper.dictionaries.DictionariesPronunciationMapper;
import com.model.dto.dictionaries.AddDictionariesDTO;
import com.model.dto.dictionaries.AddDictionariesInfoDTO;
import com.model.dto.dictionaries.AddDictionariesPronunciationDTO;
import com.model.entity.dictionaries.Dictionaries;
import com.model.entity.dictionaries.DictionariesInfo;
import com.model.entity.dictionaries.DictionariesPronunciation;
import com.model.vo.dictionaries.SelectDictionariesDetailsVO;
import com.model.vo.dictionaries.SelectDictionariesInfoVO;
import com.model.vo.dictionaries.SelectDictionariesPronunciationVO;
import com.model.vo.dictionaries.SelectDictionariesVO;
import com.service.inf.dictionaries.DictionariesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DictionariesServiceImpl implements DictionariesService {
    @Autowired
    private DictionariesMapper dictionariesMapper;

    @Autowired
    private DictionariesInfoMapper infoMapper;

    @Autowired
    private DictionariesPronunciationMapper pronunciationMapper;

    @Override
    public Dictionaries addDictionaries(AddDictionariesDTO addDictionariesDTO) {
        Date date = new Date();
        if (addDictionariesDTO==null||addDictionariesDTO.getWord()==null){
            throw new ServiceException("字不能为空，你是傻逼吧");
        }
        Dictionaries has = dictionariesMapper.selectByWord(addDictionariesDTO.getWord());
        if (has!=null){
            throw new ServiceException("字已经存在了，你是傻逼吧");
        }
        Dictionaries dictionaries=new Dictionaries();
        BeanUtils.copyProperties(addDictionariesDTO,dictionaries);
        dictionaries.setCreateTime(date);
        dictionariesMapper.insert(dictionaries);
        return dictionaries;
    }

    @Override
    public DictionariesPronunciation addDictionariesPronunciation(AddDictionariesPronunciationDTO addDictionariesPronunciationDTO) {
        Date date = new Date();
        if (ProUtil.isEmpty(addDictionariesPronunciationDTO)){
            throw new ServiceException("不能为空，你是傻逼吧");
        }
        Dictionaries dictionaries = dictionariesMapper.selectByPrimaryKey(addDictionariesPronunciationDTO.getDictionariesId());
        if (dictionaries==null){
            throw new ServiceException("字不经存在，你是傻逼吧");
        }
        DictionariesPronunciation pronunciation=new DictionariesPronunciation();
        BeanUtils.copyProperties(addDictionariesPronunciationDTO,pronunciation);
        pronunciation.setCreateTime(date);
        pronunciationMapper.insert(pronunciation);
        return pronunciation;
    }

    @Override
    public DictionariesInfo addDictionariesInfo(AddDictionariesInfoDTO addDictionariesInfoDTO) {
        Date date = new Date();
        if (ProUtil.isEmpty(addDictionariesInfoDTO)){
            throw new ServiceException("不能为空，你是傻逼吧");
        }
        DictionariesPronunciation dictionariesPronunciation = pronunciationMapper.selectByPrimaryKey(addDictionariesInfoDTO.getPronunciationId());
        if (dictionariesPronunciation==null){
            throw new ServiceException("音不存在，你是傻逼吧");
        }
        DictionariesInfo info=new DictionariesInfo();
        BeanUtils.copyProperties(addDictionariesInfoDTO,info);
        info.setCreateTime(date);
        infoMapper.insert(info);
        return info;
    }

    @Override
    public DataInfo<SelectDictionariesVO> selectDictionaries(Integer page, Integer pageSize, String shape, String word, String radicals, Integer stroke) {
        PageUtils.startPage(page,pageSize);
        DataInfo<SelectDictionariesVO> dataInfo = PageUtils.getDataInfo(dictionariesMapper.selectDictionaries(shape, word, radicals, stroke));
        return dataInfo;
    }

    @Override
    public SelectDictionariesDetailsVO selectDictionariesInfoVO(Integer dictionariesId) {
        SelectDictionariesDetailsVO result=new SelectDictionariesDetailsVO();
        List<SelectDictionariesPronunciationVO> pronunciationVOList=pronunciationMapper.selectDictionariesPronunciation(dictionariesId);
        if (pronunciationVOList==null){
            return result;
        }
        for (SelectDictionariesPronunciationVO pronunciationVO:pronunciationVOList){
            List<SelectDictionariesInfoVO> infoVOS=infoMapper.selectDictionariesInfo(pronunciationVO.getId());
            pronunciationVO.setList(infoVOS);
        }
        result.setList(pronunciationVOList);
        return result;
    }

    @Override
    public Dictionaries selectById(Integer id) {
        return dictionariesMapper.selectByPrimaryKey(id);
    }
}
