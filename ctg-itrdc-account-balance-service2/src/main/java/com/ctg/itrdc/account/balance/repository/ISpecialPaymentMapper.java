package com.ctg.itrdc.account.balance.repository;

import com.ctg.itrdc.account.balance.model.SpecialPaymentModel;

public interface ISpecialPaymentMapper {

    int deleteByPrimaryKey(Long spePaymentId);

    int insert(SpecialPaymentModel record);

    int insertSelective(SpecialPaymentModel record);

    SpecialPaymentModel selectByPrimaryKey(Long spePaymentId);

    int updateByPrimaryKeySelective(SpecialPaymentModel record);

    int updateByPrimaryKey(SpecialPaymentModel record);
}
