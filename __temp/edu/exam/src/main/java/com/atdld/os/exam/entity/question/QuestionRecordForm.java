package com.atdld.os.exam.entity.question;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionRecordForm {

    private List<QuestionRecordBean> record;

}
