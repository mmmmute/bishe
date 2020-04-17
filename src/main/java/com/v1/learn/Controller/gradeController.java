package com.v1.learn.Controller;

import com.v1.learn.dto.Classes;
import com.v1.learn.dto.EXP_GRADE;
import com.v1.learn.mapper.ClassMapper;
import com.v1.learn.mapper.gradesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class gradeController {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private gradesMapper GradesMapper;

    @GetMapping("/s_grade")
    public String s_gradeString(Model model, HttpSession session){
        int sId = Integer.parseInt(session.getAttribute("account").toString());
        List<Integer> cIdList = classMapper.searchForCId(sId);
        List<Classes> classesList = new ArrayList<>();
        for (Integer el:cIdList
             ) {
            classesList.add(classMapper.search(el.intValue()));
        }
        model.addAttribute("myClass",classesList);
        session.setAttribute("sId",sId);
        return "grade/s_myClass";
    }

    @PostMapping("/checkGrade")
    public String checkGrade(@RequestParam(name = "classId")int classId,HttpSession session,
                             Model model){
        int sId = Integer.parseInt(session.getAttribute("sId").toString());
        List<EXP_GRADE> gradeList = GradesMapper.searchForg(classId);
        for (EXP_GRADE el:gradeList
             ) {
            try {
                int g = GradesMapper.searchG(el.getID(),sId);
                el.setGRADE(g);
            }
            catch (Exception e){
                el.setGRADE(0);
            }
        }
        model.addAttribute("grades",gradeList);
        return "grade/grades";
    }

}
