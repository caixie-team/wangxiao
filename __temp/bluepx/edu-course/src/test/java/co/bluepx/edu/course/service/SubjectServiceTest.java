package co.bluepx.edu.course.service;

import co.bluepx.edu.course.BaseTest;
import com.google.gson.Gson;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bison on 1/3/16.
 */
public class SubjectServiceTest extends BaseTest {
    @Autowired
    SubjectService subjectService;

    private Gson gson = new Gson();

    @Test
    public void testFindOneLevelSubjects(){


        System.out.println(gson.toJson(subjectService.findOneLevelSubject()));
    }
}
