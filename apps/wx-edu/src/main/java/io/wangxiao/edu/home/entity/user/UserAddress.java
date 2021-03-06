package io.wangxiao.edu.home.entity.user;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAddress implements Serializable{
	private Long id;
    private Long userId;
    private String receiver;
    private String address;
    private String postCode;
    private String mobile;
    private int isFirst;
    private int sendTime;
    private java.util.Date createTime;
    private Long provinceId;
    private Long cityId;
    private Long townId;
    private String provinceStr;
    private String cityStr;
    private String townStr;
    
}
