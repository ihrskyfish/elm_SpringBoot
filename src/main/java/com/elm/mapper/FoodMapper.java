package com.elm.mapper;

import java.sql.SQLException;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elm.model.bo.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {
    @Select("select * from food where merchantId=#{merchantId} order by foodId")
    public List<Food> listFoodByMerchantId(Integer merchantId) throws SQLException;

    @Select("select * from food where foodId=#{foodId}")
    public Food getFoodById(Integer foodId) throws SQLException;

    @Select("SELECT * FROM food WHERE foodId = #{foodId} AND merchantId = #{merchantId}")
    Food getFoodByIdAndMerchantId(@Param("foodId") Integer foodId, @Param("merchantId") Integer merchantId);

}