package com.atdld.os.exam.service.impl.exampaper;

import java.util.Comparator;

import com.atdld.os.exam.entity.exampaper.PaperMiddle;


public class ComparatorPaperMiddle implements Comparator<PaperMiddle> {

    public int compare(PaperMiddle PaperMiddle0, PaperMiddle PaperMiddle1) {
        if (PaperMiddle0.getSort() < PaperMiddle1.getSort()) {
            return -1;
        } else {
            return 1;
        }
    }


}
