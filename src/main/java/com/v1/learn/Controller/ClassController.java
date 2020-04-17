package com.v1.learn.Controller;

import com.v1.learn.dto.Classes;
import com.v1.learn.dto.Student;
import com.v1.learn.mapper.ClassMapper;
import com.v1.learn.mapper.HelpClassMapper;
import com.v1.learn.mapper.StudentMapper;
import com.v1.learn.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
public class ClassController {

    private String path="D:/test";

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private HelpClassMapper helpClassMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping("/toCreateClass")
    public String toCreate(){
        return "create_class";
    }

    @PostMapping("/createClass")
    public String createClass(@RequestParam(name = "classID")String id,
                              @RequestParam(name = "className")String name,
                              @RequestParam(name = "courseName")String courseName,
                              HttpSession session){
        classMapper.insert(id,session.getAttribute("account").toString(),name,courseName);
        File dest = new File(path + "/" + id);
        dest.mkdir();
        return "createSuccess";
    }

    @PostMapping("/searchClass")
    public String search(@RequestParam(name = "class_id")int id, Model model){
        Classes result_class = classMapper.search(id);
        model.addAttribute("result_class",result_class);
        String teahcerID = result_class.getTEACHER_ID();
        String teacherName = teacherMapper.searchTeacherNameByID(teahcerID);
        model.addAttribute("teacherName",teacherName);
        model.addAttribute("classId",id);
        model.addAttribute("teacherId",teahcerID);
        return "search_class_result";
    }

    @PostMapping("/addToClass")
    @ResponseBody
    public String addToClass(@RequestParam(name = "classId")int classId,HttpSession session,Model model){
        System.out.println(classId);
        int studentId = Integer.parseInt(session.getAttribute("account").toString());
        System.out.println(studentId);
        int result = classMapper.addNewStudent(classId,studentId);
        if(result!=0){
            classMapper.student_number(classId);
            Classes result_class = classMapper.search(classId);
            model.addAttribute("result_class",result_class);
            return "success";
        }
        else{
            return  "failed";
        }
    }

    @GetMapping("/t_myClass")
    public String t_myClass(HttpSession session,Model model){
        List<Classes> list = classMapper.searchClassByTid(session.getAttribute("account").toString());
         model.addAttribute("t_myClass",list);
        return "t_myClass";
    }

    @PostMapping("/listStuOfClass")
    public String listStu(@RequestParam(name = "classId")int classId,Model model){
        List<Integer> arry = helpClassMapper.listStudent(classId);
        List<Student> students = new ArrayList<Student>();
        for (Integer el:arry) {
            students.add(studentMapper.searchStu(el.intValue()));
        }
        for (Student s:students
             ) {
            System.out.println(s.getSTUDENT_ID());
            System.out.println(s.getNAME());
        }
        model.addAttribute("listStu",students);
        return "t_listStu";
    }

    @GetMapping("/publish_exp")
    public String show_class(HttpSession session,Model model){
        List<Classes> list = classMapper.searchClassByTid(session.getAttribute("account").toString());
        model.addAttribute("t_myClass",list);
        return "publish_exp";
    }

    @PostMapping("/to_publish")
    public String to_publish(HttpSession session,Model model,@RequestParam(name = "classId")int classId,
                             @RequestParam(name = "teacherId")String teacherId){
        model.addAttribute("classId",classId);
        model.addAttribute("teacherId",teacherId);
        session.setAttribute("classId",classId);
        session.setAttribute("teacherId",teacherId);
        return "publish_need";
    }

    @GetMapping("/sMyClass")
    public String sClass(HttpSession session,Model model){
        int classId = Integer.parseInt(session.getAttribute("account").toString());

        //根据学生ID从choose表里找到 班级的序列号集合
        List<Integer> classIdList = helpClassMapper.findClassIdBysId(classId);


        //根据班级序列号集合 以一个查找班级信息
        List<Classes> classList = new ArrayList<>();
        for (Integer el:classIdList
             ) {
            classList.add(classMapper.search(el.intValue()));
        }
        model.addAttribute("s_myClass",classList);
        return "s_myClass";
    }
}
