package com.elm.controller;

import com.elm.common.BaseResponse;
import com.elm.common.ErrorCode;
import com.elm.common.ResultUtils;
import com.elm.model.vo.FoodVo;
import com.elm.service.FoodService;
import com.elm.exception.MerchantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/lists/{merchantId}")
    public BaseResponse<List<FoodVo>> listFoodByMerchantId(@PathVariable(value = "merchantId") Integer merchantId) throws Exception {
        if (merchantId == null) {
            throw new MerchantException(ErrorCode.PARAMS_ERROR, "请求参数不可为空");
        }
        List<FoodVo> foodVoList = foodService.listFoodByMerchantId(merchantId);
        if (foodVoList != null) {
            return ResultUtils.success(foodVoList);
        } else {
            throw new MerchantException(ErrorCode.SYSTEM_ERROR, "数据库操作失败，获取当前商家的商品列表失败");
        }
    }

}
