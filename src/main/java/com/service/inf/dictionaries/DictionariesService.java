package com.service.inf.dictionaries;

import com.common.model.DataInfo;
import com.model.dto.dictionaries.AddDictionariesDTO;
import com.model.dto.dictionaries.AddDictionariesInfoDTO;
import com.model.dto.dictionaries.AddDictionariesPronunciationDTO;
import com.model.entity.dictionaries.Dictionaries;
import com.model.entity.dictionaries.DictionariesInfo;
import com.model.entity.dictionaries.DictionariesPronunciation;
import com.model.vo.dictionaries.SelectDictionariesDetailsVO;
import com.model.vo.dictionaries.SelectDictionariesVO;

public interface DictionariesService {
    Dictionaries addDictionaries(AddDictionariesDTO addDictionariesDTO);

    DictionariesInfo addDictionariesInfo(AddDictionariesInfoDTO addDictionariesInfoDTO);

    DictionariesPronunciation addDictionariesPronunciation(AddDictionariesPronunciationDTO addDictionariesPronunciationDTO);

    DataInfo<SelectDictionariesVO> selectDictionaries(Integer page, Integer pageSize, String shape, String word, String radicals, Integer stroke);

    SelectDictionariesDetailsVO selectDictionariesInfoVO(Integer dictionariesId);
}
