package com.ctg.itrdc.account.balance.dao;


public interface Mapper<T> {
	int deleteByPrimaryKey(Long pk);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long pk);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
