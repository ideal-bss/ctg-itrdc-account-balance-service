package com.ctg.itrdc.account.balance.repository;

import com.ctg.itrdc.account.balance.model.SpecialPaymentDescModel;

public interface ISpecialPaymentDescMapper {

    int deleteByPrimaryKey(Long spePaymentId);

    int insert(SpecialPaymentDescModel record);

    int insertSelective(SpecialPaymentDescModel record);

    SpecialPaymentDescModel selectByPrimaryKey(Long spePaymentId);

    int updateByPrimaryKeySelective(SpecialPaymentDescModel record);

    int updateByPrimaryKey(SpecialPaymentDescModel record);
}
