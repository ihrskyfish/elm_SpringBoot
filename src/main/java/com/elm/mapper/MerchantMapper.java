package com.elm.mapper;

import java.sql.SQLException;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elm.model.bo.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {
    @Select("select * from business where orderTypeId=#{orderTypeId} order by businessId")
    public List<Merchant> listBusinessByOrderTypeId(Integer orderTypeId) throws SQLException;

    @Select("select * from business where businessId=#{businessId}")
    public Merchant getBusinessById(Integer businessId) throws SQLException;

    @Select("select * from business order by businessId")
    public List<Merchant> listBusiness() throws SQLException;

    @Select("SELECT * FROM business WHERE businessName LIKE CONCAT('%', #{businessName}, '%')")
    List<Merchant> listBusinessByBusinessName(String businessName) throws SQLException;
}
