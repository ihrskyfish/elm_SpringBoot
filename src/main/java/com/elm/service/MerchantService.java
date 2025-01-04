package com.elm.service;

import java.util.List;

import com.elm.model.vo.MerchantVo;

public interface MerchantService {
    public List<MerchantVo> listBusinessByOrderTypeId(Integer orderTypeId);

    public MerchantVo getBusinessById(Integer businessId);

    public List<MerchantVo> listBusinessByBusinessName(String businessName);

    public List<MerchantVo> listBusiness();
}



