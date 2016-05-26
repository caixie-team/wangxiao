package com.atdld.os.exam.entity.question;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;
import com.atdld.os.core.util.StringUtils;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryQuestion extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -2019817547640158270L;
    /**
     * 试题Id
     */
    private Long id;
    /**
     * 试题内容
     */
    private String qstContent;
    /**
     * 正确选项
     */
    private String isAsr;
    /**
     * 试题类型
     */
    private int qstType;
    /**
     * 试题难度
     */
    private int level;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 试题分析
     */
    private String qstAnalyze;
    /**
     * 知识点考点Id
     */
    private Long pointId;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 项目id
     */
    private Long subjectId;
    /**
     * 状态
     */
    private int status;
    /**
     * 出题人
     */
    private String author;
    /**
     * 考点名称
     */
    private String pointName;
    /**
     * 专业名称
     */
    private String subjectName;
    /**
     * 判断材料题还是非材料题
     */
    private Long complexFalg;
    //用户一道题的答案
    private String answer;
    private List<QuestionOption> options;
    private String optContent;
    private String optOrder;
    private String optAnswer;
    private String name;
    //加入用户ID
    private Long cusId;
    //标志
    private String flag;
    /**
     * 用户答案
     */
    private String userAnswer;
    /**
     * 该题用户回答是否正确
     */
    private int qstRecordstatus;
    //用户笔记内容
    private String noteContent;
    /**
     * 收藏Id
     */
    private Long favoritesId;
    /**
     * 笔记Id
     */
    private Long noteId;
    /**
     * errorQuestion的Id
     */
    private Long errorQuestionId;
    /**
     * 考点父id
     */
    private Long parentId;
    /**
     * 错题记录添加时间
     */
    private Date errorQuestionAddTime;
    /**
     * 试题位置
     */
    private int placeNumber;
    /**
     * paperRecord的id
     */
    private Long paperRecordId;
    /**
     * 被考过多少次
     */
    private int time;
    /**
     * 正确次数
     */
    private int rightTime;
    /**
     * 错误次数
     */
    private int errorTime;
    /**
     * 正确率
     */
    private float accuracy;
    /**
     * 试卷id
     */
    private Long paperId;
    /**
     * 试题记录id
     */
    private Long qstrdId;
    /**
     * 分数
     */
    private int score;
    /**
     * 用户得分
     */
    private int userscore;
    /**
     * 0未审阅1已审阅
     */
    private int state;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 试题内容缩略
     */
    private String shortQstContent;
    //专业id
    private String professionalId;

    public void setQstContent(String qstContent) {
        if (StringUtils.isNotEmpty(qstContent)) {
            //去除html
            String noHtml = qstContent.replaceAll("\\s\\w+=\\\"[^\"]+\\\"", "");
            noHtml = noHtml.replaceAll("</?[^>]+>", "");//剔出了<html>的标签
            noHtml = noHtml.replace("&nbsp;", "");
            noHtml = noHtml.replaceAll("\r|\n|\t", "");
            this.qstContent = noHtml;
            //字符长度过长则截取
            if (noHtml.length() > 30) {
                shortQstContent = noHtml.substring(0, 30);
            } else {
                shortQstContent = noHtml;
            }
        }

    }

}
