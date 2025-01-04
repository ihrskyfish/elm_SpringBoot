package com.elm.service;

import java.util.List;

import com.elm.model.vo.CartVo;

public interface CartService {
    public List<CartVo> listCart(String userId, Integer merchantId);

    public int saveCart(Integer merchantId, String userId,Integer foodId);

    public int updateCart(Integer merchantId, Integer foodId, String userId, Integer quantity);

    public int removeCart(String userId, Integer merchantId, Integer foodId);

    public CartVo getCartVoByID(Integer merchantId, Integer foodId, String userId);
}
