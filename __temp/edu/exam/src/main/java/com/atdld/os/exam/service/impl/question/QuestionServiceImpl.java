package com.atdld.os.exam.service.impl.question;

import com.atdld.os.core.common.exception.BaseException;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.security.ConvertUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.exam.dao.point.PointDao;
import com.atdld.os.exam.dao.question.*;
import com.atdld.os.exam.dao.subject.SubjectDao;
import com.atdld.os.exam.entity.exampaper.ExamRecord;
import com.atdld.os.exam.entity.exampaper.QueryPaperMiddle;
import com.atdld.os.exam.entity.favorite.Favorite;
import com.atdld.os.exam.entity.note.QueryNoteQuestion;
import com.atdld.os.exam.entity.point.ExamPoint;
import com.atdld.os.exam.entity.professional.ExamSubject;
import com.atdld.os.exam.entity.question.*;
import com.atdld.os.exam.entity.subject.QuerySubject;
import com.atdld.os.exam.service.exampaper.ExamPaperRecordService;
import com.atdld.os.exam.service.professional.ProfessionalService;
import com.atdld.os.exam.service.question.QuestionService;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;


/**
 * @author
 * @ClassName QuestionServiceImpl
 * @package com.atdld.os.exam.service.impl.question
 * @description
 * @Create Date: 2013-9-9 下午3:23:53
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private OptionDao optionDao;
    @Autowired
    private QstMiddleDao qstMiddleDao;
    @Autowired
    private PointDao pointDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private NoteDao noteDao;
    @Autowired
    private ExamPaperRecordService examPaperRecordService;
    @Autowired
    private ProfessionalService professionalService;

    public void addOneQuestion(Question question, List<String> asr) {
        question.setAddTime(new Date());
        question.setStatus(1);
        question.setUpdateTime(new Date());
        //主观题不用添加
        if (question.getQstType() != 6) {
            question.setIsAsr(question.getIsAsr().trim().replace(",", ""));
            questionDao.addOneQuestion(question);
            initOption(question, asr);
        } else {
            questionDao.addOneQuestion(question);
        }
    }

    public void initOption(Question question, List<String> asr) {
        // 存储ABCD选项时用到
        int AASC = 64;
        for (int i = 0; i < asr.size(); i++) {
            if (asr.get(i) != null) {
                QuestionOption option = new QuestionOption();
                option.setAddTime(new Date());
                option.setOptAnswer(question.getIsAsr());
                option.setOptContent(asr.get(i));
                option.setQstId(question.getId());
                char data[] = {backchar(AASC += 1)};
                option.setOptOrder(String.valueOf(data));
                optionDao.addOneOption(option);
            }
        }
    }

    /**
     * ASC转字符
     */
    public char backchar(int backnum) {
        char stchar;
        stchar = (char) backnum;
        return stchar;
    }

    @Override
    public List<QueryQuestion> getQuestionAllList(QueryQuestion queryQuestion,
                                                  PageEntity pageEntity) {
        return questionDao.getQuestionAllList(queryQuestion, pageEntity);
    }

    /**
     * 查询用户做过的主观题
     */
    public List<QueryQuestion> getQuestionSubjectiveList(QueryQuestion queryQuestion, PageEntity pageEntity) {

        Map<Long, QueryPaperMiddle> map = new HashMap<Long, QueryPaperMiddle>();
        List<QueryQuestion> queryQuestionList = questionDao.getQuestionSubjectiveList(queryQuestion, pageEntity);
        if (ObjectUtils.isNotNull(queryQuestionList) && queryQuestionList.size() > 0) {
            map = examPaperRecordService.queryPaperMiddleMap(queryQuestionList.get(0).getPaperId());
        }
        if (ObjectUtils.isNotNull(queryQuestionList)) {
            for (int i = 0; i < queryQuestionList.size(); i++) {
                QueryQuestion qst = queryQuestionList.get(i);
                if (ObjectUtils.isNotNull(map.get(qst.getId()))) {
                    qst.setScore(map.get(qst.getId()).getScore());
                }
            }
        }
        return queryQuestionList;
    }

    @Override
    public void delQuestionListBatch(String questionIds) {
        questionDao.delQuestionListBatch(questionIds.replaceAll(" ", "").split(","));
    }

    @Override
    public Question getOneQuestion(QueryQuestion queryQuestion) {
        List<Question> questionList = questionDao.getQuestionAllList(queryQuestion);
        if (questionList != null && questionList.size() > 0) {
            return questionList.get(0);
        }
        return null;
    }

    @Override
    public void updateQuestion(Question question, List<String> asr) {
        questionDao.updateQuestionById(question);
        optionDao.delOptionListBatch(question.getId());
        initOption(question, asr);
    }

    @Override
    public void updateQuestionByDelPoint(List<ExamPoint> pointList) {
        for (ExamPoint point : pointList) {
            // 当该父id为0时删除该考点同时删除该试题
            if (point.getParentId() != 0) {
                questionDao.updateQuestionWhenDelPointId(point);
            }
        }
    }

    public void delQstMiddleById(Long id) {
        QstMiddle qstMiddle = new QstMiddle();
        qstMiddle.setId(id);
        qstMiddleDao.delQstMiddleById(qstMiddle);
    }

    @Override
    public void delQstMiddleById(QstMiddle qstMiddle) {
        qstMiddleDao.delQstMiddleById(qstMiddle);
    }

    public void updateMoveUp(int oneSort, Long oneQstId, int twotSort, Long twoQstId,
                             QstMiddle qstMiddle) {
        if (qstMiddle.getComplexId() != 0) {
            qstMiddle.setQstType(0);
        }
        qstMiddle.setQstId(oneQstId);
        qstMiddle.setSort(twotSort);
        qstMiddleDao.updateQstMiddleBySort(qstMiddle);
        qstMiddle.setQstId(twoQstId);
        qstMiddle.setSort(oneSort);
        qstMiddleDao.updateQstMiddleBySort(qstMiddle);
    }

    public List<QueryQuestion> getRandomQuestionByPointIds(String pointIds, int qstType,
                                                           int num) {
        return questionDao.getRandomQuestionByPointIds(pointIds, qstType, num);
    }

    /**
     * 通过条件查询试题数量
     */
    public int getAllQuestionListCount(QueryQuestion queryQuestion) {
        return questionDao.getAllQuestionListCount(queryQuestion);
    }

    public QueryQuestion getParse(QueryQuestion queryQuestion) {
        List<QueryQuestion> queryQuestionlist = questionDao.getParse(queryQuestion);
        List<QuestionOption> optionlist = new ArrayList<QuestionOption>();
        if (ObjectUtils.isNotNull(queryQuestionlist) && queryQuestionlist.size() > 0) {
            for (QueryQuestion queryQuestions : queryQuestionlist) {
                QuestionOption option = new QuestionOption();
                option.setOptContent(queryQuestions.getOptContent());
                option.setOptOrder(queryQuestions.getOptOrder());
                option.setOptAnswer(queryQuestions.getOptAnswer());
                optionlist.add(option);
            }
            queryQuestionlist.get(0).setOptions(optionlist);
            return queryQuestionlist.get(0);
        }
        return null;
    }

    // 整理queryQuestionList数据
    public QueryQuestion initqueryQuestionlist(List<QueryQuestion> queryQuestionlist) {
        List<QuestionOption> optionlist = new ArrayList<QuestionOption>();
        for (QueryQuestion queryQuestions : queryQuestionlist) {
            QuestionOption option = new QuestionOption();
            option.setOptContent(queryQuestions.getOptContent());
            option.setOptOrder(queryQuestions.getOptOrder());
            option.setOptAnswer(queryQuestions.getOptAnswer());
            optionlist.add(option);
        }
        queryQuestionlist.get(0).setOptions(optionlist);
        return queryQuestionlist.get(0);
    }

    public int isFavorite(QueryQuestion queryQuestion) {
        return questionDao.isFavorite(queryQuestion);
    }

    public void delFavorite(Favorite favorite) {
        questionDao.delFavorite(favorite);
    }

    public void delQueryErrorQuestion(QueryErrorQuestion queryErrorQuestion) {
        questionDao.delQueryErrorQuestion(queryErrorQuestion);
    }

    /**
     * 清空错题
     */
    public void clearQueryErrorQuestion(QueryErrorQuestion queryErrorQuestion) {
        questionDao.clearQueryErrorQuestion(queryErrorQuestion);
    }

    public void addFavorite(Favorite favorite) {
        questionDao.addFavorite(favorite);
    }

    public void addOneQuestion(Question question) {
        questionDao.addOneQuestion(question);
    }

    public void addOneOption(QuestionOption option) {
        optionDao.addOneOption(option);
    }

    public String getRandomQuestionByPointIds(Long cusId, String pointIds, int num) {
        List<QueryQuestion> queryQuestionList = questionDao.getRandomQuestionByPointIds(
                pointIds, 0, num);// 获得试题id pointIds考点id num试题数量
        String ids = "";
        if (queryQuestionList != null && queryQuestionList.size() > 0) {
            for (QueryQuestion queryQuestion : queryQuestionList) {
                ids += queryQuestion.getId() + ",";
            }
            ids = ids.substring(0, ids.length() - 1);

        }
        return ids;
    }

    /**
     * 获得错题表列表
     */
    public List<QueryQuestion> getErrorQuestionList(
            QueryErrorQuestion queryErrorQuestion, PageEntity pageEntity) {
        return questionDao.getErrorQuestionList(queryErrorQuestion, pageEntity);
    }

    /**
     * 获得笔记题的列表
     */
    public List<QueryQuestion> getNoteQuestionList(QueryNoteQuestion queryNoteQuestion,
                                                   PageEntity pageEntity) {
        return questionDao.getNoteQuestionList(queryNoteQuestion, pageEntity);
    }

    // 从excel表中导出的数据处理
    public String updateImportExcel(MultipartFile myFile) throws Exception {
        // datalist拼装List<String[]> datalist,
        HSSFWorkbook wookbook = new HSSFWorkbook(myFile.getInputStream());
        HSSFSheet sheet = wookbook.getSheet("Sheet1");
        int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
        // datalist拼装
        QuerySubject querySubject = new QuerySubject();
        List<ExamSubject> subjectR = subjectDao.getSubjectList(querySubject);

        for (int i = 1; i <= rows; i++) {
            // 读取左上端单元格
            HSSFRow row = sheet.getRow(i);
            // 行不为空
            if (row != null) {
                // 获取到Excel文件中的所有的列
                int maxcell = row.getLastCellNum();
                // **读取cell**
                String biaoshi = getCellValue(row.getCell((short) 0));// 标识
                String type = getCellValue(row.getCell((short) 1));// 题型
                String subjectId = getCellValue(row.getCell((short) 2));// 专业
                String pointId = getCellValue(row.getCell((short) 3));// 考点
                String level = getCellValue(row.getCell((short) 4));// 试题难度
                String content = getCellValue(row.getCell((short) 5));//试题内容
                String isAsr = getCellValue(row.getCell((short) 6));//正确答案
                String analyze = getCellValue(row.getCell((short) 7));//解析
                String optionA = getCellValue(row.getCell((short) 8));//选项A
                String optionB = getCellValue(row.getCell((short) 9));//选项B
                String optionC = getCellValue(row.getCell((short) 10));//选项C
                String optionD = getCellValue(row.getCell((short) 11));//选项D
                String optionE = getCellValue(row.getCell((short) 12));//选项E
                String optionF = getCellValue(row.getCell((short) 13));//选项F
                String optionG = getCellValue(row.getCell((short) 14));//选项G

                //验证 赋值
                //如果为空白行则跳出本次循环
                if (StringUtils.isEmpty(biaoshi) && StringUtils.isEmpty(getCellValue(row.getCell((short) 1))) && StringUtils.isEmpty(getCellValue(row.getCell((short) 2))) &&
                        StringUtils.isEmpty(getCellValue(row.getCell((short) 3))) && StringUtils.isEmpty(getCellValue(row.getCell((short) 4))) && StringUtils.isEmpty(content) &&
                        StringUtils.isEmpty(isAsr)) {
                    break;
                }

                type = ConvertUtils.objectToInt(getCellValue(row.getCell((short) 1))) + "";// 题型
                subjectId = ConvertUtils.objectToInt(getCellValue(row.getCell((short) 2))) + "";// 专业
                pointId = ConvertUtils.objectToInt(getCellValue(row.getCell((short) 3))) + "";// 考点
                level = ConvertUtils.objectToInt(getCellValue(row.getCell((short) 4))) + "";// 试题难度

                if (maxcell < 7) {
                    throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据数据列数不正确");
                }
                //标识，题型，专业，考点，试题难度，试题内容，正确答案
                if (StringUtils.isEmpty(biaoshi) || StringUtils.isEmpty(type) || StringUtils.isEmpty(subjectId) ||
                        StringUtils.isEmpty(pointId) || StringUtils.isEmpty(level) || StringUtils.isEmpty(content) ||
                        StringUtils.isEmpty(isAsr)) {
                    throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据数据不能为空（标识" +
                            "，题型，专业，考点，试题难度，试题内容，正确答案的列不能为空）");
                }
                // 试题类型只能输入1, 2, 3, 5,
                if ("1,2,3,5,".indexOf(type) == -1) {
                    throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据试题类型不正确（类型只能输入1,2,3,5）");
                }

                // 专业id必须是大于0的正整数
                if (!subjectId.matches("^\\d+$")) {
                    throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据专业ID必须是大于0的正整数");
                }

                // 考点必须是大于0的正整数
                if (!pointId.matches("^\\d+$")) {
                    throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据考点ID必须是大于0的正整数");
                }
                // 试题难度必须是1,2,3
                if ("1,2,3,".indexOf(level) == -1) {
                    throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据的试题难度必须是1,2,3其中的一个数字（如1）");
                }

                int typeInt = ConvertUtils.objectToInt(type);
                // 如果为判断题只能有2个选项
                if (typeInt == 3) {
                    if (StringUtils.isEmpty(optionA) || StringUtils.isEmpty(optionB)) {
                        throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据为判断题，选项A或选项B不能为空");
                    }
                    if (StringUtils.isNotEmpty(optionC) || StringUtils.isNotEmpty(optionD) || StringUtils.isNotEmpty(optionE)
                            || StringUtils.isNotEmpty(optionF) || StringUtils.isNotEmpty(optionG)) {
                        throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据为判断题，选项C，D，E，F，G必须为空");
                    }
                }

                // 如果不是判断题，选项必须大于等于4个小于等于7个选项
                if (typeInt != 3) {
                    if (StringUtils.isEmpty(optionA) || StringUtils.isEmpty(optionB) || StringUtils.isEmpty(optionC)
                            || StringUtils.isEmpty(optionD)) {
                        throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据为判断题，选项A，B，C，D不能为空");
                    }
                }
                // 如果为多选题正确答案必须在两个以上
                if (typeInt == 2 && isAsr.trim().length() < 2) {
                    throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据的为多选题，正确答案必须在两个以上（例：A,B）");
                }
                // 如果为单选题或者判断题答案只能有一个
                if (typeInt == 1 || typeInt == 3) {
                    if (isAsr.trim().length() > 1) {
                        throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据的正确答案只能有一个（例：A）");
                    }
                }
                // 选项不能超过7个字符
                if (isAsr.trim().length() > 7) {
                    throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据正确答案不能超过7个字符（例AB）");
                }
                // 验证正确答案不能输入其他字符
                char[] asr = isAsr.toString().trim().toCharArray();
                for (int y = 0; y < asr.length; y++) {
                    if ("ABCDEFG".indexOf(String.valueOf(asr[y])) == -1) {
                        throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据正确答案输入字符格式不正确（例AB）");
                    }
                }

                Question question = new Question();
                question.setStatus(1);
                // 标志，方便查询批量插入的数据
                question.setFlag(biaoshi);
                // 试题类型
                int qstType = typeInt;
                question.setQstType(qstType);
                // 验证项目ID
                Long subjectIdLong = Long.valueOf(subjectId.trim());
                for (int x = 0; x < subjectR.size(); x++) {
                    querySubject.setSubjectId(subjectR.get(x).getSubjectId());
                    if (querySubject.getSubjectId().longValue() == subjectIdLong.longValue()) {
                        question.setSubjectId(subjectIdLong);
                    }
                }
                // 如果输入的专业不匹配
                if (question.getSubjectId() == null || question.getSubjectId().intValue() == 0) {
                    throw new BaseException("第" + i + "行，试题内容为<" + content + ">的那条数据的专业id不匹配");
                }
                // 验证考点
                Long pointIdLong = Long.valueOf(pointId.trim());
                ExamPoint point = new ExamPoint();
                point.setSubjectId(subjectIdLong);
                point.setId(pointIdLong);

                List<ExamPoint> pointList = pointDao.getPointList(point);
                if (!pointList.isEmpty()) {
                    question.setPointId(pointIdLong);
                    question.setLevel(ConvertUtils.objectToInt(level));
                    question.setQstContent(content);
                    question.setIsAsr(isAsr);
                    question.setQstAnalyze(analyze);
                    question.setAddTime(new Date());
                    question.setAuthor("sysadmin");
                    questionDao.addOneQuestion(question);
                    int AASC = 64;
                    List<String> str = new ArrayList<String>();
                    //把选项的值放入list中
                    str.add(optionA);
                    str.add(optionB);
                    str.add(optionC);
                    str.add(optionD);
                    str.add(optionE);
                    str.add(optionF);
                    str.add(optionG);


                    List<QuestionOption> optionList = new ArrayList<QuestionOption>();

                    for (int k = 0; k < str.size(); k++) {
                        // 如果选项为空字符串则不添加该选项
                        if (!"".equals(str.get(k).trim())) {
                            QuestionOption option = new QuestionOption();
                            option.setAddTime(new Date());
                            option.setOptAnswer(question.getIsAsr());
                            option.setOptContent(str.get(k).trim());
                            option.setQstId(question.getId());
                            char data[] = {backchar(AASC += 1)};
                            option.setOptOrder(String.valueOf(data));
                            optionList.add(option);
                        }
                    }
                    optionDao.addOptionBatch(optionList);
                } else {
                    throw new BaseException("第" + i + "行，试题内容为<" + content.toString() + ">的那条数据的于考点不匹配");
                }

                //插入数据库
                // **读取cell***
            }
        }
        return null;
    }

    // 从 word 表中导入的数据处理
    public void updateImportWord(MultipartFile myFile) throws Exception {
        InputStream is = myFile.getInputStream();
        @SuppressWarnings("resource")
        WordExtractor we = new WordExtractor(is);
        String[] exam_array = we.getText().split("\\r\\n--END--");// 试题数组

        QuerySubject querySubject = new QuerySubject();
        List<ExamSubject> subjectR = subjectDao.getSubjectList(querySubject);
        if (exam_array != null && exam_array.length > 0 && !exam_array[0].toString().trim().equals("")) {
            // 遍历试题数组，获得每个试题对象
            for (int i = 0; i < exam_array.length - 1; i++) {
                // 拆分试题详细信息
                String[] examArray = exam_array[i].split("\\r\\n@@@");
                if (examArray.length == 9) {
                    String examContent = examArray[0].toString();// 试题内容
                    if (StringUtils.isNotEmpty(examContent)) {
                        int type = ConvertUtils.objectToInt(examArray[1]);// 试题类型;
                        if (type != 0) {
                            String option = examArray[2].toString(); // 选项集合字符串
                            String isArt = examArray[3].toString(); // 正确答案
                            String analyze = examArray[4].toString(); // 试题解析
                            String biaoshi = examArray[5].toString().trim(); // 标识
                            String subjectId = examArray[6].toString().trim(); // 科目ID
                            String pointId = examArray[7].toString().trim(); // 考点ID
                            String level = examArray[8].toString().trim(); // 试题难度
                            // 科目id必须是大于0的正整数
                            if (!subjectId.matches("^\\d+$")) {
                                throw new BaseException("第" + (i + 1) + "题，试题内容为<" + examContent + ">的那条数据专业ID必须是大于0的正整数");
                            }
                            // 考点必须是大于0的正整数
                            if (!pointId.matches("^\\d+$")) {
                                throw new BaseException("第" + (i + 1) + "题，试题内容为<" + examContent + ">的那条数据考点ID必须是大于0的正整数");
                            }
                            // 试题难度必须是1,2,3
                            if ("1,2,3,".indexOf(level) == -1) {
                                throw new BaseException("第" + (i + 1) + "题，试题内容为<" + examContent + ">的那条数据的试题难度必须是1,2,3其中的一个数字（如1）");
                            }
                            // 拆分选项集合字符串成数组
                            String[] optionArray = option.split("=#="); // optionArray[0] 为特殊字符排除
                            // 获得选项
                            String optionA = null;
                            String optionB = null;
                            String optionC = null;
                            String optionD = null;
                            String optionE = null;
                            String optionF = null;
                            String optionG = null;
                            // 如果为判断题只能有2个选项
                            if (type == 3) {
                                if (optionArray.length > 3 || optionArray.length <= 1) {
                                    throw new BaseException("第" + (i + 1) + "题，试题内容为<" + examContent + ">的那条数据为判断题，只能存在选线A和选项B，且均不能为空");
                                } else {
                                    optionA = optionArray[1].toString().trim();
                                    optionB = optionArray[2].toString().trim();
                                }
                            }
                            // 如果不是判断题，选项必须大于等于4个小于等于7个选项
                            if (type != 3) {
                                String ty = type == 1 ? "单选" : "多选";
                                if (optionArray.length < 5) {
                                    throw new BaseException("第" + (i + 1) + "题，试题内容为<" + examContent + ">的那条数据为" + ty + "题，选项A，B，C，D不能为空");
                                }
                                if (optionArray.length >= 5) {
                                    optionA = optionArray[1].toString().trim();
                                    optionB = optionArray[2].toString().trim();
                                    optionC = optionArray[3].toString().trim();
                                    optionD = optionArray[4].toString().trim();
                                }
                                if (optionArray.length == 6) {
                                    optionE = optionArray[5].toString().trim();
                                }
                                if (optionArray.length == 7) {
                                    optionE = optionArray[5].toString().trim();
                                    optionF = optionArray[6].toString().trim();
                                }
                                if (optionArray.length == 8) {
                                    optionE = optionArray[5].toString().trim();
                                    optionF = optionArray[6].toString().trim();
                                    optionG = optionArray[7].toString().trim();
                                }
                            }
                            // 如果为多选题正确答案必须在两个以上
                            if (type == 2 && isArt.trim().length() < 2) {
                                throw new BaseException("第" + (i + 1) + "题，试题内容为<" + examContent + ">的那条数据的为多选题，正确答案必须在两个以上（例：A,B）");
                            }
                            // 如果为单选题或者判断题答案只能有一个
                            if (type == 1 || type == 3) {
                                if (isArt.trim().length() > 1) {
                                    throw new BaseException("第" + (i + 1) + "题，试题内容为<" + examContent + ">的那条数据的正确答案只能有一个（例：A）");
                                }
                            }
                            // 选项不能超过7个字符
                            if (isArt.trim().length() > 7) {
                                throw new BaseException("第" + (i + 1) + "题，试题内容为<" + examContent + ">的那条数据正确答案不能超过7个字符（例AB）");
                            }
                            // 验证正确答案不能输入其他字符
                            char[] asr = isArt.toString().trim().toCharArray();
                            for (int y = 0; y < asr.length; y++) {
                                if ("ABCDEFG".indexOf(String.valueOf(asr[y])) == -1) {
                                    throw new BaseException("第" + (i + 1) + "题，试题内容为<" + examContent + ">的那条数据正确答案输入字符格式不正确（例AB）");
                                }
                            }

                            Question question = new Question();
                            question.setStatus(1);
                            // 标志，方便查询批量插入的数据
                            question.setFlag(biaoshi);
                            // 试题类型
                            int qstType = type;
                            question.setQstType(qstType);
                            // 验证项目ID
                            Long subjectIdLong = Long.valueOf(subjectId.trim());
                            for (int x = 0; x < subjectR.size(); x++) {
                                querySubject.setSubjectId(subjectR.get(x).getSubjectId());
                                if (querySubject.getSubjectId().longValue() == subjectIdLong.longValue()) {
                                    question.setSubjectId(subjectIdLong);
                                }
                            }
                            // 如果输入的专业不匹配
                            if (question.getSubjectId() == null || question.getSubjectId().intValue() == 0) {
                                throw new BaseException("第" + (i + 1) + "题，试题内容为<" + examContent + ">的那条数据的科目id不匹配");
                            }
                            // 验证考点
                            Long pointIdLong = Long.valueOf(pointId.trim());
                            ExamPoint point = new ExamPoint();
                            point.setSubjectId(subjectIdLong);
                            point.setId(pointIdLong);

                            List<ExamPoint> pointList = pointDao.getPointList(point);
                            if (!pointList.isEmpty()) {
                                question.setPointId(pointIdLong);
                                question.setLevel(ConvertUtils.objectToInt(level));
                                question.setQstContent(examContent);
                                question.setIsAsr(isArt);
                                question.setQstAnalyze(analyze);
                                question.setAddTime(new Date());
                                question.setAuthor("sysadmin");
                                questionDao.addOneQuestion(question);
                                int AASC = 64;
                                List<String> str = new ArrayList<String>();
                                //把选项的值放入list中
                                str.add(optionA);
                                str.add(optionB);
                                str.add(optionC);
                                str.add(optionD);
                                str.add(optionE);
                                str.add(optionF);
                                str.add(optionG);

                                List<QuestionOption> optionList = new ArrayList<QuestionOption>();
                                for (int k = 0; k < str.size(); k++) {
                                    // 如果选项为空字符串则不添加该选项
                                    if (str.get(k) != null && !"".equals(str.get(k).trim())) {
                                        QuestionOption opt = new QuestionOption();
                                        opt.setAddTime(new Date());
                                        opt.setOptAnswer(question.getIsAsr());
                                        opt.setOptContent(str.get(k).trim());
                                        opt.setQstId(question.getId());
                                        char data[] = {backchar(AASC += 1)};
                                        opt.setOptOrder(String.valueOf(data));
                                        optionList.add(opt);
                                    } else {
                                        break;
                                    }
                                }
                                optionDao.addOptionBatch(optionList);
                            } else {
                                throw new BaseException("第" + (i + 1) + "题，试题内容为<" + examContent + ">的那条数据的考点不匹配");
                            }
                        } else {
                            throw new BaseException("第" + (i + 1) + "题，试题内容为<" + examContent + ">的那条数据试题类型不正确（类型只能为'单选'、'多选'、'判断'）");
                        }
                    } else {
                        throw new BaseException("第" + (i + 1) + "题，试题内容为不能为空！");
                    }
                } else {
                    throw new BaseException("每个试题内容必须包含试题题目、试题类型、选项、正确答案、解析、标识、科目ID、考点ID、试题难度！");
                }
            }
        } else {
            throw new BaseException("无试题信息，上传失败");
        }

    }

    // 查询用户收藏的试题
    public List<QueryQuestion> getFavoriteQuestion(QueryQuestion queryQuestion,
                                                   PageEntity pageEntity) {
        return questionDao.getFavoriteQuestion(queryQuestion, pageEntity);
    }

    public void insertNote(QueryNoteQuestion queryNoteQuestion) {
        queryNoteQuestion.setAddTime(new Date());
        if (ObjectUtils.isNull(noteDao.queryNote(queryNoteQuestion))) {
            noteDao.insertNote(queryNoteQuestion);
        } else {
            noteDao.updatetNote(queryNoteQuestion);
        }

    }

    public void updatetNote(QueryNoteQuestion queryNoteQuestion) {
        noteDao.updatetNote(queryNoteQuestion);
    }

    /**
     * 通过试题Id获得question
     */
    public List<QueryQuestion> getQuestionByQuestionIds(Long cusId, String qstIds) {
        List<QueryQuestion> queryQuestionList = questionDao.getQuestionByIds(cusId,
                qstIds);
        return queryQuestionList;
    }

    /**
     * 通过试题Id获得question
     */
    public Map<Long, QueryQuestion> getMapQuestionByQuestionIds(String qstIds) {
        List<QueryQuestion> queryQuestionList = questionDao.getQuestionByIds(0L,
                qstIds);
        Map<Long, QueryQuestion> map = new HashMap<Long, QueryQuestion>();
        if (ObjectUtils.isNotNull(queryQuestionList)) {
            for (QueryQuestion queryQuestion : queryQuestionList) {
                map.put(queryQuestion.getId(), queryQuestion);
            }
        }
        return map;
    }

    public List<QueryQuestion> getErrorQuestionitemList(
            QueryErrorQuestion queryErrorQuestion) {
        return questionDao.getErrorQuestionitemList(queryErrorQuestion);
    }

    public List<QueryQuestion> getNoteQuestionitemList(QueryNoteQuestion queryNoteQuestion) {
        return questionDao.getNoteQuestionitemList(queryNoteQuestion);
    }

    public String getRandomQuestionByErrorQst(int num, Long subjectId, Long cusId) {
        List<QueryErrorQuestion> queryErrorQuestionList = questionDao
                .getRandomQuestionByErrorQst(num, subjectId, cusId);
        String ids = "";
        if (queryErrorQuestionList != null && queryErrorQuestionList.size() > 0) {
            for (QueryErrorQuestion queryErrorQuestion : queryErrorQuestionList) {
                ids += queryErrorQuestion.getQstId() + ",";
            }
            ids = ids.substring(0, ids.length() - 1);
        }
        return ids;
    }

    /**
     * 顺序获得错误试题
     */
    public String getQuestionByErrorQst(int num, Long subjectId, Long cusId) {
        List<QueryErrorQuestion> queryErrorQuestionList = questionDao
                .getQuestionByErrorQst(num, subjectId, cusId);
        String ids = "";
        if (queryErrorQuestionList != null && queryErrorQuestionList.size() > 0) {
            for (QueryErrorQuestion queryErrorQuestion : queryErrorQuestionList) {
                ids += queryErrorQuestion.getQstId() + ",";
            }
            ids = ids.substring(0, ids.length() - 1);
        }
        return ids;
    }

    @Autowired
    private QuestionRecordDao questionRecordDao;

    public void updateQuestionByPaperRecord(ExamRecord examRecord) {

        // 查询大于上次的paperRecordId的每个试卷的参加人数和该试卷的分数和
        List<QuestionRecord> QuestionRecordList = questionRecordDao
                .queryQuestionRecordByGroupByQstId(examRecord);
        if (QuestionRecordList != null && QuestionRecordList.size() > 0) {
            for (QuestionRecord questionRecord1 : QuestionRecordList) {
                QueryQuestion queryQuestion = new QueryQuestion();
                queryQuestion.setId(questionRecord1.getQstId());
                // 通过试题id获得试题
                queryQuestion = questionDao.queryQuestionById(queryQuestion);
                if (queryQuestion != null) {
                    // 试题做过次数等于试题本来的试题次数加上新增加的
                    int time = queryQuestion.getTime() + questionRecord1.getQstNum();
                    // 该试题作对过的次数
                    int rightTime = queryQuestion.getRightTime()
                            + questionRecord1.getRightQstNum();
                    // 该试题作错过的次数
                    int errorTime = time - rightTime;
                    // 正确率
                    float accuracy = (float) rightTime / (float) time;
                    queryQuestion.setTime(time);
                    queryQuestion.setRightTime(rightTime);
                    queryQuestion.setErrorTime(errorTime);
                    queryQuestion.setAccuracy(accuracy);
                    // 更新试题
                    questionDao.updateQuestionForTimeAndRightTimeById(queryQuestion);
                }
            }
            // //获得最大的paperRecordId更新到ExamRecord中
            // Date updateTime =
            // examPaperRecordDao.queryExamPaperRecordMaxUpdateTime();
            // examRecord.setLastUpdateRecord(updateTime);
            // //更新examRecord
            // examRecordDao.updateExamRecordById(examRecord);
        }

    }

    /**
     * 获得试题所有的行数
     */
    @Override
    public Long getQuestionCount() {
        return questionDao.getQuestionCount();
    }

    /**
     * 获得Hsscell内容
     *
     * @param cell
     * @return
     */
    public String getCellValue(HSSFCell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_FORMULA:
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    value = cell.getNumericCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue().trim();
                    break;
                default:
                    value = "";
                    break;
            }
        }
        return value.trim();
    }
}
