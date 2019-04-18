package com.mapper.dictionaries;

import com.model.entity.dictionaries.DictionariesPronunciation;
import com.model.vo.dictionaries.SelectDictionariesPronunciationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictionariesPronunciationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DictionariesPronunciation record);

    int insertSelective(DictionariesPronunciation record);

    DictionariesPronunciation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DictionariesPronunciation record);

    int updateByPrimaryKey(DictionariesPronunciation record);

    List<SelectDictionariesPronunciationVO> selectDictionariesPronunciation(Integer dictionariesId);
}