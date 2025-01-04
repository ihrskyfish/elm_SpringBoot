package com.elm.model.vo;

import lombok.Data;

@Data
public class MerchantVo {
    private Integer merchantId;
    private String merchantName;
    private String merchantAddress;
    private String merchantExplain;
    private String merchantImg;
    private Integer orderTypeId;
    private double starPrice; // 起送费
    private double deliveryPrice; // 配送费
    private String remarks;
    private int orderQuantity; // 订单数量

    @Override
    public String toString() {
        return "\n商家编号：" + this.merchantId +
                "\n商家名称：" + this.merchantName +
                "\n商家地址：" + this.merchantAddress +
                "\n商家介绍：" + this.merchantExplain +
                "\n起送费：" + this.starPrice +
                "\n配送费：" + this.deliveryPrice +
                "\n订单数量：" + this.orderQuantity;
    }
}
