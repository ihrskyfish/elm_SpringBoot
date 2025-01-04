package com.elm.controller;

import com.elm.common.BaseResponse;
import com.elm.common.ErrorCode;
import com.elm.common.ResultUtils;
import com.elm.model.vo.MerchantVo;
import com.elm.service.MerchantService;
import com.elm.exception.MerchantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/business")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/lists/{orderTypeId}")
    public BaseResponse<List<MerchantVo>> listMerchantByOrderTypeId(@PathVariable(value = "orderTypeId") Integer OrderTypeId) {
        if (OrderTypeId == null) {
            throw new MerchantException(ErrorCode.PARAMS_ERROR, "请求参数不可为空");
        }
        List<MerchantVo> merchantVoList = merchantService.listMerchantByOrderTypeId(OrderTypeId);
        if (merchantVoList != null) {
            return ResultUtils.success(merchantVoList);
        } else {
            throw new MerchantException(ErrorCode.SYSTEM_ERROR, "数据库操作失败，获取商家列表失败");
        }
    }

    @GetMapping("/businesses/{businessId}")
    public BaseResponse<MerchantVo> getMerchantById(@PathVariable(value = "businessId") Integer merchantId) {
        if (merchantId == null) {
            throw new MerchantException(ErrorCode.PARAMS_ERROR, "请求参数不可为空");
        }
        MerchantVo merchantVo = merchantService.getMerchantById(merchantId);
        if (merchantVo != null) {
            return ResultUtils.success(merchantVo);
        } else {
            throw new MerchantException(ErrorCode.SYSTEM_ERROR, "数据库操作失败，获取商家失败");
        }
    }

    @GetMapping("/businessLists")
    public BaseResponse<List<MerchantVo>> listMerchant() {
        List<MerchantVo> merchantVoList = merchantService.listMerchant();
        if (merchantVoList != null) {
            return ResultUtils.success(merchantVoList);
        } else {
            throw new MerchantException(ErrorCode.SYSTEM_ERROR, "数据库操作失败，获取商家列表失败");
        }
    }
    @GetMapping("/businessNameLists/{businessName}")
    public BaseResponse<List<MerchantVo>> listBusinessByBusinessName(@PathVariable(value = "businessName", required = false) String merchantName) {
        // 此处传入的Name参数可以为空
        List<MerchantVo> merchantVoList = merchantService.listMerchantByMerchantName(merchantName);
        if (merchantVoList != null) {
            return ResultUtils.success(merchantVoList);
        } else {
            throw new MerchantException(ErrorCode.SYSTEM_ERROR, "数据库操作失败，查询商家列表失败");
        }
    }
}