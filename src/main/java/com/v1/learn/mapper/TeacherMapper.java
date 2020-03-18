package com.v1.learn.mapper;

import com.v1.learn.dto.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeacherMapper {
    @Insert("insert into TEACHER (TEACHER_ID,PASSWORD,NAME) values(#{TEACHER_ID},#{PASSWORD},#{NAME})")
    public void insert(Teacher teacher);

    @Select("select * from TEACHER where TEACHER_ID = #{account} and PASSWORD = #{password}")
    public Teacher search(String account,String password);

    @Select("select NAME from TEACHER where TEACHER_ID = #{id}")
    public String searchTeacherNameByID(String id);


}
