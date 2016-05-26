package com.atdld.os.exam.service.impl.point;

import java.util.Comparator;

import com.atdld.os.exam.entity.point.PointExtend;

/**
 * @author :
 * @ClassName com.atdld.os.exam.service.impl.point.ComparePoint
 * @description
 * @Create Date : 2014-4-16 上午11:27:52
 */
public class ComparePoint implements Comparator<PointExtend> {
    @Override
    public int compare(PointExtend o1, PointExtend o2) {
        if (o1.getId() < o2.getId()) {
            return -1;
        } else {
            return 1;
        }
    }

}
