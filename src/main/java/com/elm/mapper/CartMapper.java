package com.elm.mapper;

import java.sql.SQLException;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elm.model.bo.Cart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    public List<Cart> listCart(String userId, Integer merchantId);

    @Insert("insert into cart(foodId, merchantId, userId, quantity, isDelete) values(#{foodId},#{merchantId},#{userId},1,0)")
    public int saveCart(Integer merchantId, String userId, Integer foodId) throws SQLException;

    @Select("select * from cart where foodId=#{foodId} and merchantId=#{merchantId} and userId=#{userId}")
    public Cart getCartById(Integer foodId, Integer merchantId, String userId) throws SQLException;
    @Update("update cart set quantity=#{quantity} where foodId=#{foodId} and merchantId=#{merchantId} and userId=#{userId}")
    public int updateCart(Integer merchantId, Integer foodId, String userId, Integer quantity) throws SQLException;

    public int removeCart(String userId, Integer merchantId, Integer foodId) throws SQLException;
}