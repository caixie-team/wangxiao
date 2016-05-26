package co.bluepx.edu.course.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class VedioLive extends BaseIncrementIdModel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String title;
    private String code;
    private String teacher;
    private java.util.Date liveTime;
    private Long joinNum;
    private String content;
    private java.util.Date endTime;
    private java.util.Date addTime;
    private java.util.Date updateTime;
    private String dateFalg;
}
