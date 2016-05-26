package co.bluepx.edu.user.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

public class UserIntegral extends BaseIncrementIdModel {
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(Long currentScore) {
		this.currentScore = currentScore;
	}

	public Long getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private Long userId;// 用户id
	private Long currentScore;// 当前分数
	private Long totalScore;// 总分数
	private String email;// 用户邮箱
}
