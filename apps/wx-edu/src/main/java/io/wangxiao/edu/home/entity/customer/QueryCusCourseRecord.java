package io.wangxiao.edu.home.entity.customer;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @ClassName com.business.platform.edu.entity.customer.QueryCusCourseRecord
 * @description 查询自定义课程加入记录条件
 * @author guozhenzhou
 * @data   2014-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCusCourseRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 305042116431188055L;
	private int id;
	private long userId;
	private long cusCourseId;
	private Date createTime;

}
