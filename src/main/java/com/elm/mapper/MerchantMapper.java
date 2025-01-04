package com.elm.mapper;

import java.sql.SQLException;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elm.model.bo.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {
    @Select("select * from merchant where orderTypeId=#{orderTypeId} order by merchantId")
    public List<Merchant> listMerchantByOrderTypeId(Integer orderTypeId) throws SQLException;

    @Select("select * from merchant where merchantId=#{merchantId}")
    public Merchant getMerchantById(Integer merchantId) throws SQLException;

    @Select("select * from merchant order by merchantId")
    public List<Merchant> listMerchant() throws SQLException;

    @Select("SELECT * FROM merchant WHERE merchantName LIKE CONCAT('%', #{merchantName}, '%')")
    List<Merchant> listMerchantByMerchantName(String merchantName) throws SQLException;
}
