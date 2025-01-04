package com.elm.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.elm.mapper.MerchantMapper;
import com.elm.model.bo.Merchant;
import com.elm.model.vo.MerchantVo;
import com.elm.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public List<MerchantVo> listBusinessByOrderTypeId(Integer orderTypeId) {
        try {
            List<Merchant> merchantList = merchantMapper.listBusinessByOrderTypeId(orderTypeId);
            return getBusinessVo(merchantList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MerchantVo getBusinessById(Integer businessId) {
        try {
            Merchant merchant = merchantMapper.getBusinessById(businessId);
            return getBusinessVo(merchant);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MerchantVo> listBusinessByBusinessName(String businessName) {
        try {
            List<Merchant> merchantList = merchantMapper.listBusinessByBusinessName(businessName);
            return getBusinessVo(merchantList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public MerchantVo getBusinessVo(Merchant merchant) {
        if (merchant == null) {
            return null;
        }
        MerchantVo merchantVo = new MerchantVo();
        BeanUtils.copyProperties(merchant, merchantVo);
        return merchantVo;
    }

    public List<MerchantVo> listBusiness() {
        try {
            List<Merchant> merchantList = merchantMapper.listBusiness();
            return getBusinessVo(merchantList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MerchantVo> getBusinessVo(List<Merchant> merchantList) {
        if (CollectionUtils.isEmpty(merchantList)) {
            return new ArrayList<>();
        }
        return merchantList.stream().map(this::getBusinessVo).collect(Collectors.toList());
    }
}
