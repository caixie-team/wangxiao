package io.wangxiao.edu.home.entity.plan;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class PhaseDetailDTO extends PhaseDetail{

    private int complete;//完成时长

    private int peopleNum;//学习人数

    public int getAverageComplete(){
        if(peopleNum>0&&complete>0){
            return complete/peopleNum;
        }
        return 0;
    }

    public double getProgressPercentage(){
        if(this.getHours()>0){
            return getAverageComplete()*1.0/getHours();
        }
        return 0;
    }
}
