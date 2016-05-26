package io.wangxiao.edu.home.entity.user;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserIntegral implements Serializable {

	private Long id;// id
	private Long userId;// 用户id
	private Long currentScore;// 当前分数
	private Long totalScore;// 总分数
	private String email;// 用户邮箱
}
