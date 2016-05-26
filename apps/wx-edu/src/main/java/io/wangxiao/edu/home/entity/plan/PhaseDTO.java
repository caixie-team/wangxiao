package io.wangxiao.edu.home.entity.plan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class PhaseDTO extends Phase {

    private int complete;//完成时长

    private int peopleNum;//学习人数

    public int getAverageComplete(){
        if(peopleNum>0&&complete>0){
            return complete/peopleNum;
        }
        return 0;
    }

    public double getProgressPercentage(){
        if(this.getStudyTimeNo()>0){
            return getAverageComplete()*1.0/getStudyTimeNo();
        }
        return 0;
    }

    /**
     * 阶段详情进度
     */
    List<PhaseDetailDTO> phaseDetailList;
}
