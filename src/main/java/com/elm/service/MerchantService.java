package com.elm.service;

import java.util.List;

import com.elm.model.vo.MerchantVo;

public interface MerchantService {
    public List<MerchantVo> listMerchantByOrderTypeId(Integer orderTypeId);

    public MerchantVo getMerchantById(Integer businessId);

    public List<MerchantVo> listMerchantByMerchantName(String merchantName);

    public List<MerchantVo> listMerchant();
}



