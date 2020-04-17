package com.v1.learn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface HelpClassMapper {

    @Select("select STUDENT_ID from CHOOSE where CLASS_ID = #{classId}")
    public List<Integer> listStudent(int classId);

    @Select("select CLASS_ID from CHOOSE where STUDENT_ID = #{sId}")
    public  List<Integer> findClassIdBysId(int sId);
}
