package com.v1.learn.mapper;

import com.v1.learn.dto.Classes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper
@Component
public interface ClassMapper {

    @Insert("insert into CLASS(CLASS_ID,TEACHER_ID,CLASS_NAME,COURSE_NAME,STUDENT_NUMBER) values(#{id},#{teacherID},#{name},#{courseName},0)")
    public void insert(String id,String teacherID,String name,String courseName);

    @Insert("insert into HELP_CLASS(CLASS_ID,STUDENT_ID) values(#{classID},#{s_ID})")
    public int addNewStudent(int classID,int s_ID);

    @Select("select * from CLASS where CLASS_ID = #{CLASS_ID}")
    public Classes search(int CLASS_ID);

    @Update("update CLASS set STUDENT_NUMBER=(STUDENT_NUMBER+1) where CLASS_ID = #{classId}")
    public int student_number(int classId);

    @Select("select CLASS_ID,CLASS_NAME,COURSE_NAME,STUDENT_NUMBER from CLASS where TEACHER_ID = #{id}")
    public List<Classes> searchClassByTid(String id);


}
