package com.atdld.os.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * app课程管理
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppCourse implements Serializable {
	private static final long serialVersionUID = 1383373953853661904L;
	private long mainId;//app课程id
	private long courseId;// 课程id
}
