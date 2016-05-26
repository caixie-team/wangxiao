package co.bluepx.edu.member.service;

import co.bluepx.edu.member.entity.MemberType;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by bison on 1/6/16.
 */
public class MemberTypeServiceTest extends BaseTest{

    @Autowired
    MemberTypeService memberTypeService;

    private Gson gson = new Gson();


    @Test
    public void testFindMemberTypes() {

        List<MemberType> memberTypes = memberTypeService.findAll();

        System.out.println(gson.toJson(memberTypes));

    }
}
