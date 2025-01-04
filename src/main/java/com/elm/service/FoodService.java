package com.elm.service;

import java.util.List;

import com.elm.model.vo.FoodVo;

public interface FoodService {
    public List<FoodVo> listFoodByMerchantId(Integer businessId);
}