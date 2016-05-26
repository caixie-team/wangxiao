package com.atdld.os.exam.service.impl.exampaper;

import java.util.Comparator;

import com.atdld.os.exam.entity.question.QueryQstMiddle;


public class ComparatorQstMiddle implements Comparator<QueryQstMiddle> {

    public int compare(QueryQstMiddle qstMiddle0, QueryQstMiddle qstMiddle1) {
        if (qstMiddle0.getSort() < qstMiddle1.getSort()) {
            return -1;
        } else {
            return 1;
        }
    }

}
