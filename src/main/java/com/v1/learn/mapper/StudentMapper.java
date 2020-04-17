package com.v1.learn.mapper;

import com.v1.learn.dto.Student;
import com.v1.learn.dto.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface StudentMapper {
    @Insert("insert into STUDENT (STUDENT_ID,NAME,PASSWORD) values(#{STUDENT_ID},#{NAME},#{PASSWORD})")
    public void insert(Student student);

    @Select("select * from STUDENT where STUDENT_ID = #{account} and PASSWORD = #{password}")
    public Student search(int account, String password);

    @Select("select * from STUDENT where STUDENT_ID = #{account}")
    public Student searchStu(int account);

    @Select("select NAME from STUDENT where STUDENT_ID = #{sId} ")
    public String searchForName(int sId);


}
