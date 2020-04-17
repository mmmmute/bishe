package com.v1.learn.mapper;

import com.v1.learn.dto.EXP_GRADE;
import com.v1.learn.dto.Grades;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Mapper
@Component
public interface gradesMapper {
    @Insert("insert into GRADES(STUDENT_ID,CLASS_ID,TEACHER_ID,ID) values(#{sId},#{classId},#{tId},#{ID})")
    public int insert(int sId,int classId,String tId,int ID);

    @Select("select * from GRADES where ID = #{id}")
    public List<Grades> select(int id);

    @Update("update GRADES set GRADE = #{grade} where ID = #{id} and STUDENT_ID = #{sId}")
    public int updateGrade(int grade,int id,int sId);

    @Select("select EXPERIMENT_NAME,START_TIME,DEADLINE,ID from EXPERIMENT where CLASS_ID = #{classId}")
    public List<EXP_GRADE> searchForg(int classId);

    @Select("select GRADE from GRADES where ID = #{ID} and STUDENT_ID = #{sId}")
    public int searchG(int ID,int sId);
}
