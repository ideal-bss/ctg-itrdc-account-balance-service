package com.ctg.itrdc.account.balance.dao;


public interface Mapper<T> {
	int deleteByPrimaryKey(T record);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
