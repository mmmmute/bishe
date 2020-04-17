package com.v1.learn.mapper;

import com.v1.learn.dto.Exp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface ExpMapper {

    @Insert("insert into EXPERIMENT(TEACHER_ID,EXPERIMENT_NAME,CLASS_ID,START_TIME,DEADLINE,PUBLISH_TIME,FILE) values(#{tId},#{exName},#{cId},#{sTime},#{deadLine},#{pTime},#{file})")
    public int insert(String tId, String exName, int cId, Date sTime,Date deadLine,Date pTime,String file);

    @Select("select * from EXPERIMENT where CLASS_ID = #{classId}")
    public List<Exp> search(int classId);

    @Select("select * from EXPERIMENT where ID = #{ID}")
    public  Exp searchById(int ID);

    @Select("select * from EXPERIMENT where TEACHER_ID = #{tId}")
    public List<Exp> searchByTid(String tId);

    @Update("update EXPERIMENT set START_TIME = #{sTime},DEADLINE = #{Dtime} , FILE = #{file} where ID = #{id}")
    public int update(Date sTime,Date Dtime,String file,int id);

    @Update("update EXPERIMENT set START_TIME = #{sTime}, DEADLINE = #{Dtime} where ID = #{id}")
    public int update2(Date sTime,Date Dtime,int id);

    @Update("update EXPERIMENT set SUBMISSION = (SUBMISSION+1)  where ID = #{id}")
    public int updateNum(int id);

    @Update("update EXPERIMENT set STATUS_COR = #{status} where ID = #{id}")
    public int updateStatus(boolean status,int id);
}
