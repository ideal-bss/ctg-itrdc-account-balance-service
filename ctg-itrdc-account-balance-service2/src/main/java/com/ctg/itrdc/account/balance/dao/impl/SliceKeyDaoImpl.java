package com.ctg.itrdc.account.balance.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ctg.itrdc.account.balance.dao.SliceKeyDao;
import com.ctg.itrdc.account.balance.repository.IIndexTableMapper;
import com.ctg.itrdc.account.balance.repository.ITestMapper;
@Repository
public class SliceKeyDaoImpl implements SliceKeyDao{
	@Autowired
	private IIndexTableMapper iIndexTableMapper;
	@Autowired
	private ITestMapper iTestMapper;
	@Override
	public int getSliceKey(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sliceKey="";
		if(iIndexTableMapper.selectSliceKeyID(map)!=null&&!iIndexTableMapper.selectSliceKeyID(map).equals("")){
			sliceKey=iIndexTableMapper.selectSliceKeyID(map);
		}else{
			sliceKey=iTestMapper.selectSequence();
			iIndexTableMapper.insertSelective(map);
		}
		
		return Integer.parseInt(sliceKey);
	}

}
