package com.mapper.dictionaries;

import com.model.entity.dictionaries.Dictionaries;
import com.model.vo.dictionaries.SelectDictionariesVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictionariesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dictionaries record);

    int insertSelective(Dictionaries record);

    Dictionaries selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dictionaries record);

    int updateByPrimaryKey(Dictionaries record);

    Dictionaries selectByWord(String word);

    List<SelectDictionariesVO> selectDictionaries(@Param("shape") String shape,@Param("word") String word,@Param("radicals") String radicals,@Param("stroke") Integer stroke);
}