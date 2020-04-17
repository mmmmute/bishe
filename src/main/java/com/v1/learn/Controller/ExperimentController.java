package com.v1.learn.Controller;

import com.v1.learn.dto.*;
import com.v1.learn.mapper.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ExperimentController {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private gradesMapper gMapper;

    @Autowired
    private ExpMapper expMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private ClassMapper classMapper;

    private String path = "D:/test";

    @PostMapping("/createExp")
    public String createExp(@RequestParam(name = "eName") String eName,
                            @RequestParam(name = "sTime") Date sTime,
                            @RequestParam(name = "deadline") Date deadline,
                            @RequestParam("fileName") MultipartFile file, HttpSession session) {
        String classId = session.getAttribute("classId").toString();
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        String path = "D:/test";
        File dest = new File(path + "/" + classId + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            expMapper.insert(session.getAttribute("teacherId").toString(), eName, Integer.parseInt(classId), sTime, deadline, new Date(), fileName);
            return "createExpSuccess";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }

    @PostMapping("/listExp")
    public String listExp(@RequestParam(name = "classId") int classId, Model model, HttpSession session) {

        List<Exp> list = expMapper.search(classId);
        model.addAttribute("expList", list);

        String className = classMapper.search(classId).getCLASS_NAME().toString();
        model.addAttribute("className", className);

        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

        int account = Integer.parseInt(session.getAttribute("account").toString());

        session.setAttribute("classId", classId);

        Task result;
        for (Exp el : list
        ) {
            el.setsTime(df2.format(el.getSTART_TIME()));
            el.setpTime(df2.format(el.getPUBLISH_TIME()));
            result = taskMapper.searchFor(el.getTEACHER_ID(), el.getID(), account);
            if (result != null) {
                el.setStatus("已提交");
            } else {
                el.setStatus("未提交");
            }
        }
        return "expList";
    }

    @PostMapping("/more")
    public String more(@RequestParam(name = "ID") int ID, Model model,HttpSession session) {
        Exp result = expMapper.searchById(ID);

        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        result.setsTime(df2.format(result.getSTART_TIME()));
        result.setpTime(df2.format(result.getPUBLISH_TIME()));
        model.addAttribute("result",result);

        //根据ID取TASK表找到提交的文件和相关记录
        Task task = taskMapper.searchById(ID);
        if(task == null){
            task = new Task();
            task.setSign(false);
            System.out.println("null");
        }
        else{
            task.setSign(true);
            System.out.println(task.getID());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            task.setTrans(simpleDateFormat.format(task.getSUB_TIME()));
        }
        model.addAttribute("record",task);

        //session存实验的ID 方便 学生提交之后搜索数据
        session.setAttribute("ID",ID);
        model.addAttribute("ID",ID);
        return "moreinfo";
    }


    @GetMapping("/more/{ID}")
    public String moreGet(@PathVariable("ID")int ID, Model model, HttpSession session) {
        Exp result = expMapper.searchById(ID);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        result.setsTime(df2.format(result.getSTART_TIME()));
        result.setpTime(df2.format(result.getPUBLISH_TIME()));
        model.addAttribute("result",result);

        //根据ID取TASK表找到提交的文件和相关记录
        Task task = taskMapper.searchById(ID);
        if(task == null){
            task = new Task();
            task.setSign(false);
            System.out.println("null");
        }
        else{
            task.setSign(true);
            System.out.println(task.getID());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            task.setTrans(simpleDateFormat.format(task.getSUB_TIME()));
        }
        model.addAttribute("record",task);

        //session存实验的ID 方便 学生提交之后搜索数据
        session.setAttribute("ID",ID);
        model.addAttribute("ID",ID);
        return "test";
    }






    @GetMapping("/download/{filename}")
    public String download(@PathVariable("filename")String filename,
                           HttpSession session,
                           HttpServletResponse response) throws UnsupportedEncodingException{
        String filePath = "D:/test/" + session.getAttribute("classId");
        File file = new File(filePath + "/" + filename);
        if (file.exists()) { //判断文件父目录是否存在
            //response.setContentType("application/octet-stream");
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                System.out.println("sucessful");

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    @PostMapping("/s_submit")
    public String submit(@RequestParam("fileName") MultipartFile file, HttpSession session){
        int ID = Integer.parseInt(session.getAttribute("ID").toString());
        Exp exp = expMapper.searchById(ID);
        String tId = exp.getTEACHER_ID();
        int classId = exp.getCLASS_ID();
        int sId = Integer.parseInt(session.getAttribute("account").toString());
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        String path = "D:/test";
        File dest = new File(path + "/" + classId + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            Task task = taskMapper.searchById(ID);
            if(task == null){
                taskMapper.insert(tId,ID,new Date(),fileName,sId);
                expMapper.updateNum(ID);
            }
            else{
                File f = new File(path + "/" + classId + "/" + task.getFILE());
                f.delete();
                taskMapper.update(fileName,ID);
            }
            return "redirect:/more/"+ID;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }

    @GetMapping("/setExp")
    public String setExp(HttpSession session,Model model){
        //获取教师ID，查找教师的所有实验记录
        String tId = session.getAttribute("account").toString();

        List<Exp> list = expMapper.searchByTid(tId);

        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        for (Exp el:list
             ) {
            el.setsTime(df2.format(el.getSTART_TIME()));
            el.setpTime(df2.format(el.getPUBLISH_TIME()));
        }
        model.addAttribute("tExp",list);
        return "tMyExp";
    }




    @PostMapping("/toNeed")
    public String toNeed(@RequestParam(name = "ID")int id,HttpSession session){
        session.setAttribute("ID",id);
        return "setNeed";
    }

    @PostMapping("/setNeed")
    public String toNeed(HttpSession session,Model model,
                         MultipartFile file,
                         @RequestParam(name = "sTime")Date sTime,
                         @RequestParam(name = "deadline")Date deadline){
        int id = Integer.parseInt(session.getAttribute("ID").toString());
        Exp exp = expMapper.searchById(id);
        String oldFile = exp.getFile();
        int classId = exp.getCLASS_ID();


        if(file == null){
            expMapper.update2(sTime,deadline,id);
            return "teacher_home";
        }

        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        String path = "D:/test";
        File dest = new File(path + "/" + classId + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            expMapper.update(sTime,deadline,fileName,id);
            return "teacher_home";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }


    @GetMapping("/checkExp")
    public String checkExp(HttpSession session,Model model){
        String tId = session.getAttribute("account").toString();
        List<Exp> list = expMapper.searchByTid(tId);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        int total;
        int s;
        String str;
        for (Exp el:list
             ) {
            el.setpTime(df2.format(el.getPUBLISH_TIME()));
            el.setsTime(df2.format(el.getSTART_TIME()));
            total = classMapper.getStudentNum(el.getCLASS_ID());
            s = el.getSUBMISSION();
            str = s + "/" + total;
            el.setT_s(str);
            if(total == s){
                el.setFlag_s(false);
            }
            else{
                el.setFlag_s(true);
            }
        }

        //前端展示实验名称，开始时间，截止时间，提交情况（分数），未提交名单
        model.addAttribute("expList",list);
        return "check/checkExp";
    }

    @PostMapping("/unSubmit")
    public String unList(@RequestParam(name = "classId")int id,Model model){
        int classId = expMapper.searchById(id).getCLASS_ID();
        List<Integer> list = classMapper.unSubmit(classId,id);
        List<Student> sList = new ArrayList<>();
        Student s;
        for (Integer el:list
             ) {
            sList.add(studentMapper.searchStu(el.intValue()));
        }
        model.addAttribute("sList",sList);
        return "check/unSubmit";
    }

    @GetMapping("/correct")
    public String t_myClass(HttpSession session,Model model){
        List<Classes> list = classMapper.searchClassByTid(session.getAttribute("account").toString());
        model.addAttribute("t_myClass",list);
        return "correct/t_myClass";
    }

    @PostMapping("/toNew/{classId}")
    public String toNew(@PathVariable("classId")int classId,HttpSession session,Model model){
        session.setAttribute("classId",classId);
        model.addAttribute("classId",classId);
        return "correct/newListExp";
    }

    @GetMapping("/data")
    @ResponseBody
    public JSONObject listExp(HttpSession session){
        int classId = Integer.parseInt(session.getAttribute("classId").toString());
        System.out.println(classId);
        List<Exp> list = expMapper.search(classId);
        JSONObject json = new JSONObject();

        List<ExpHelp1> List = new ArrayList<>();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        for (Exp el:list
             ) {
            ExpHelp1 expHelp1 = new ExpHelp1();
            expHelp1.setEXPERIMENT_NAME(el.getEXPERIMENT_NAME());
            expHelp1.setsTime(df2.format(el.getSTART_TIME()));
            expHelp1.setDeadline(el.getDeadline());
            expHelp1.setID(el.getID());
            if(el.isSTATUS_COR()){
                expHelp1.setSTATUS_COR("已批改");
                expHelp1.setPrice(1);
            }
            else{
                expHelp1.setSTATUS_COR("未批改");
                expHelp1.setPrice(0);
            }
            List.add(expHelp1);
        }

        json.put("rows",List);
        json.put("total",list.size());
        return json;
    }

    @PostMapping("/correct_s")
    public String toC(@RequestParam(name = "ID")int ID,HttpSession session){
        Exp exp = expMapper.searchById(ID);
        session.setAttribute("classId",exp.getCLASS_ID());
        session.setAttribute("ID",ID);
        return "correct/stuList";
    }

    @GetMapping("/GradeDate")
    @ResponseBody
    public JSONObject coorect_s(Model model,HttpSession session){
        int ID = Integer.parseInt(session.getAttribute("ID").toString());
        int classId = Integer.parseInt(session.getAttribute("classId").toString());
        JSONObject json = new JSONObject();
        Exp exp = expMapper.searchById(ID);
        String tId = exp.getTEACHER_ID();
        List<Grades> gList = new ArrayList<>();
        System.out.println("ID:"+ID);
        if(!exp.isSTATUS_COR()){
            List<Integer> sIdList = classMapper.searchForSId(classId);
            List<Student> sList = new ArrayList<>();
            for (Integer el:sIdList
                 ) {
                sList.add(studentMapper.searchStu(el.intValue()));
            }
            for (Student s:sList
                 ) {
                Grades grades = new Grades();
                gMapper.insert(s.getSTUDENT_ID(),classId,tId,ID);
                gList.add(new Grades(s.getSTUDENT_ID(),classId,tId,0,ID,studentMapper.searchForName(s.getSTUDENT_ID())));
            }
            expMapper.updateStatus(true,ID);
        }
        else {
            gList = gMapper.select(ID);
            for (Grades el:gList
                 ) {
                el.setSTUDENT_NAME(studentMapper.searchForName(el.getSTUDENT_ID()));
            }
        }
        session.setAttribute("ID",ID);
        json.put("total",gList.size());
        json.put("rows",gList);
        return json;
    }


    @PostMapping("/SignGra")
    @ResponseBody
    public String update(@RequestParam(name = "grade")int grade,
                      @RequestParam(name = "ID")int ID,
                      @RequestParam(name = "studentId")int studentId){
        gMapper.updateGrade(grade,ID,studentId);
        return String.valueOf(grade);
    }

    @GetMapping("/sMyExp")
    public String sMyExp(HttpSession session,Model model){
        int account = Integer.parseInt(session.getAttribute("account").toString());
        List<Integer> classIdList = classMapper.searchForCId(account);
        List<Exp> expList = new ArrayList<>();
        for (Integer el:classIdList
             ) {
            expList.addAll(expMapper.search(el.intValue()));
        }

        int ID;
        SimpleDateFormat df2 = new SimpleDateFormat();

        List<Exp> hadsubmit = new ArrayList<>();
        List<Exp> unsubmit = new ArrayList<>();
        for (Exp el:expList
             )
        {
            ID = el.getID();
            el.setpTime(df2.format(el.getPUBLISH_TIME()));
            el.setsTime(df2.format(el.getSTART_TIME()));
            if(taskMapper.searchCheck(ID,account)!=null){
                hadsubmit.add(el);
            }
            else{
                unsubmit.add(el);
            }

        }

        model.addAttribute("hadsubmit",hadsubmit);
        model.addAttribute("unsubmit",unsubmit);
        return "stu_checkExp/expList";
    }
}
