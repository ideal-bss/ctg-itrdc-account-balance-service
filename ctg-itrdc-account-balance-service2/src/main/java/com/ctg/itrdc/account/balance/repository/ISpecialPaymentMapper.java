package com.ctg.itrdc.account.balance.repository;

import java.util.List;

import com.ctg.itrdc.account.balance.dao.Mapper;
import com.ctg.itrdc.account.balance.model.SpecialPaymentModel;

public interface ISpecialPaymentMapper extends Mapper<SpecialPaymentModel>{

    List<SpecialPaymentModel> selectAllSpecial();
    
    SpecialPaymentModel selectByPrimaryKey(Long spePaymentId);
}
