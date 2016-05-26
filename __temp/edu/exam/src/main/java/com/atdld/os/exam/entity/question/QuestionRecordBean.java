package com.atdld.os.exam.entity.question;

import java.util.Arrays;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionRecordBean {

    private String userAnswer;
    private String answerLite;
    private String qstIdsLite;
    private Integer score;
    private Long paperMiddle;
    private Integer qstType;
    private Long pointId;
    private String answer;

    public boolean isRight() {
        //用户答案去空白去掉，号
        userAnswer = userAnswer.trim().replace(",", "");
        answerLite = answerLite.trim().replace(",", "");
        char[] cs1 = userAnswer.toCharArray(), cs2 = answerLite.trim().toCharArray();
        Arrays.sort(cs1);
        Arrays.sort(cs2);
        return !new String(cs1).equals(new String(cs2));
    }
}
