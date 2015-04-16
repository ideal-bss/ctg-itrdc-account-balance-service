package com.ctg.itrdc.account.balance.repository;

import java.util.Map;

import com.ctg.itrdc.account.balance.model.IndexTableModel;

public interface IIndexTableMapper {
	
	int updateByPrimaryKeySelective(IndexTableModel record);

    int updateByPrimaryKey(IndexTableModel record);
    
    IndexTableModel selectByPrimaryKey(Long indexTableId);
    
    int deleteByPrimaryKey(Long indexTableId);

    int insert(IndexTableModel record);

    int insertSelective(IndexTableModel record);
    
    Map<String, String> selectbyBalanceId(Long acctBalanceId);
    
    int selectCountByBalanceId(Map<String, String> map);
}
