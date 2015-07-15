package com.ctg.itrdc.account.balance.repository;

import java.util.List;

import com.ctg.itrdc.account.balance.dao.Mapper;
import com.ctg.itrdc.account.balance.model.SpecialPaymentDescModel;

public interface ISpecialPaymentDescMapper extends Mapper<SpecialPaymentDescModel>{

    List<SpecialPaymentDescModel> selectAll();
    
    SpecialPaymentDescModel selectSpecialDescById(long spePaymentId);
}
