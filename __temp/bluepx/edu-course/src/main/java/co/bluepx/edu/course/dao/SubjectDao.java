package co.bluepx.edu.course.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.core.Condition;
import co.bluepx.edu.core.Pagination;
import co.bluepx.edu.course.entity.Subject;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created by bison on 1/3/16.
 */
public interface SubjectDao extends BaseDao<Subject> {
    @Results(value = {
            @Result(property ="subjectName", column ="subject_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    @Override
    List<Subject> find(Pagination page, @Param(value = "conditions") List<Condition> conditions);

    List<Subject> getSubjectOneList();
}
