package com.v1.learn.Controller;

import com.v1.learn.dto.Result;
import com.v1.learn.dto.Student;
import com.v1.learn.mapper.StudentMapper;
import net.sf.json.JSONObject;
import com.v1.learn.dto.Teacher;
import com.v1.learn.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.servlet.function.EntityResponse.fromObject;

@Controller
public class SignInController {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @PostMapping("/SignIn")
    @ResponseBody
    public Object SignIn(@RequestParam(name = "account")String account,
                         @RequestParam(name = "password")String password,
                         @RequestParam(name = "type")String type, HttpSession session){
        if(type.equals("教师")){
            //调用教师的接口，从数据库中根据账号密码搜索
            Teacher teacher = teacherMapper.search(account,password);
            System.out.println(teacher);
            if (teacher!=null){
                /*JSONObject object = JSONObject.fromObject(teacher);
                String joust = object.toString();
                return joust*/;
                session.setAttribute("account",teacher.getTEACHER_ID());
                session.setAttribute("name",teacher.getNAME());
                session.setAttribute("type","teacher");
                return "success";
            }
            else{
                return "failed";
            }
        }
        else{
            //调用学生的接口，从数据库中根据账号密码搜索
            int s_account = Integer.parseInt(account);
            Student student = studentMapper.search(s_account,password);
            if (student!=null){
                /*JSONObject object = JSONObject.fromObject(teacher);
                String joust = object.toString();
                return joust*/;
                session.setAttribute("account",student.getSTUDENT_ID());
                session.setAttribute("name",student.getNAME());
                session.setAttribute("type","student");
                return "success";
            }
            else{
                return "failed";
            }
        }
    }

    @RequestMapping("/signed")
    public String y(HttpSession session){

        if(session.getAttribute("type").equals("teacher")){
            return "teacher_home";
        }
        else{
            return "student_home";
        }
    }

}
