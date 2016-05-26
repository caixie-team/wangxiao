package io.wangxiao.edu.common.service;


public interface ExamHessianService {
    /**
     * 清空考试指定表
     *
     * @param name
     */
    void truncateTable(String name);
}
