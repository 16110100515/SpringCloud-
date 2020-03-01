package com.atguigu.scw.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAddressVo {

	@ApiModelProperty("地址id")
	private Integer id = 1;

	@ApiModelProperty("会员地址")
	private String address = "牙齿大街1号";



}
