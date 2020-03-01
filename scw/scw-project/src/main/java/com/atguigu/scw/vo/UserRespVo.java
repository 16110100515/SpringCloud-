package com.atguigu.scw.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**用户登录成功以后返回的信息 */
@Data
@ApiModel
@ToString
public class UserRespVo {
	private Integer id; //用户的主键
	@ApiModelProperty("访问令牌，请妥善保管，以后每次请求都要带上")
	private String accessToken;//访问令牌
	private String loginacct; //存储手机号
	private String username;
	private String email;
	private String authstatus;
	private String usertype;
	private String realname;
	private String cardnum;
	private String accttype;
	
	
	
}
