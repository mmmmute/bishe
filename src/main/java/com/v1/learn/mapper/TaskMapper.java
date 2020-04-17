package com.v1.learn.mapper;

import com.v1.learn.dto.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface TaskMapper {

    @Select("select * from TASK where TEACHER_ID = #{teacherId} and ID = #{id} and STUDENT_ID = #{studentId}")
    public Task searchFor(String teacherId,int id,int studentId);

    @Select("select * from TASK where ID = #{ID}")
    public Task searchById(int ID);

    @Insert("insert into TASK(TEACHER_ID,ID,SUB_TIME,FILE,STUDENT_ID) values(#{tId},#{ID},#{subTime},#{file},#{sId})")
    public int insert(String tId, int ID, Date subTime, String file, int sId);

    @Update("update TASK set file = #{filename} where ID = #{id}")
    public int update(String filename,int id);

    @Select("select FILE from TASK where ID = #{ID}")
    public List<String> searchFileById(int ID);

    @Select("select * from TASK where ID = #{ID} and STUDENT_ID = #{sId}")
    public Task searchCheck(int ID,int sId);
}
