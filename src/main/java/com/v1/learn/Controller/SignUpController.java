package com.v1.learn.Controller;

import com.v1.learn.dto.Student;
import com.v1.learn.dto.Teacher;
import com.v1.learn.mapper.StudentMapper;
import com.v1.learn.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @PostMapping("/SignUp")
    public String test(@RequestParam(name = "type")String type,
                       @RequestParam(name = "ID")String ID,
                       @RequestParam(name = "PASSWORD")String PASSWORD,
                       @RequestParam(name = "NAME")String NAME){
        //根据前端用户输入的账号、密码、名字创建数据库数据，这里把前端的数据构造成model，传过servermodel，server使用model插入数据库
        if(type.equals("教师")){
            System.out.println("是老师，应该创建教师数据");
            Teacher teacher = new Teacher(ID,PASSWORD,NAME);
            teacherMapper.insert(teacher);

        }
        else if(type.equals("学生")){
            System.out.println("创建学生数据");
            int student_ID = Integer.parseInt(ID);
            Student student = new Student(student_ID,NAME,PASSWORD);
            studentMapper.insert(student);
        }

        return "SignYes";
    }

}
