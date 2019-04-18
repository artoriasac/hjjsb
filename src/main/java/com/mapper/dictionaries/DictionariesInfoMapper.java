package com.mapper.dictionaries;

import com.model.entity.dictionaries.DictionariesInfo;
import com.model.vo.dictionaries.SelectDictionariesInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictionariesInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DictionariesInfo record);

    int insertSelective(DictionariesInfo record);

    DictionariesInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DictionariesInfo record);

    int updateByPrimaryKey(DictionariesInfo record);

    List<SelectDictionariesInfoVO> selectDictionariesInfo(Integer pronunciationId);
}