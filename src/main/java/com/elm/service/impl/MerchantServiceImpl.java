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
    public List<MerchantVo> listMerchantByOrderTypeId(Integer orderTypeId) {
        try {
            List<Merchant> merchantList = merchantMapper.listMerchantByOrderTypeId(orderTypeId);
            return getMerchantVo(merchantList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MerchantVo getMerchantById(Integer merchantId) {
        try {
            Merchant merchant = merchantMapper.getMerchantById(merchantId);
            return getMerchantVo(merchant);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MerchantVo> listMerchantByMerchantName(String merchantName) {
        try {
            List<Merchant> merchantList = merchantMapper.listMerchantByMerchantName(merchantName);
            return getMerchantVo(merchantList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public MerchantVo getMerchantVo(Merchant merchant) {
        if (merchant == null) {
            return null;
        }
        MerchantVo merchantVo = new MerchantVo();
        BeanUtils.copyProperties(merchant, merchantVo);
        return merchantVo;
    }

    public List<MerchantVo> listMerchant() {
        try {
            List<Merchant> merchantList = merchantMapper.listMerchant();
            return getMerchantVo(merchantList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MerchantVo> getMerchantVo(List<Merchant> merchantList) {
        if (CollectionUtils.isEmpty(merchantList)) {
            return new ArrayList<>();
        }
        return merchantList.stream().map(this::getMerchantVo).collect(Collectors.toList());
    }
}
