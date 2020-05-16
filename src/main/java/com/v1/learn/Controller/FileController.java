package com.v1.learn.Controller;

import com.v1.learn.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    private TaskMapper taskMapper;

    private String rootpath = "D:/test/";

    @PostMapping("/fileList")
    public String fileList(@RequestParam(name = "ID")int ID, HttpSession session,
                           Model model){
        int classId = Integer.parseInt(session.getAttribute("classId").toString());
        List<String> fileList = taskMapper.searchFileById(ID);
        model.addAttribute("fileList",fileList);
        return "correct/fileList";
    }

    @GetMapping("/downloadfile/{fileName}")
    public void download(@PathVariable("fileName")String filename,HttpSession session,
                         HttpServletResponse response)throws UnsupportedEncodingException{

        int classId = Integer.parseInt(session.getAttribute("classId").toString());
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
    }


    @GetMapping("/file")
    public String toFile(HttpSession session,Model model){
        int account = Integer.parseInt(session.getAttribute("account").toString());
        String myfile = rootpath + account;
        session.setAttribute("filepath",myfile);
        File fileroot = new File(myfile);
        String [] filelist = fileroot.list();
        model.addAttribute("filelist",filelist);
        return "file/file";
    }

    @GetMapping("/fileManager/{fileName}")
    public void downfile(@PathVariable("fileName")String filename,HttpSession session,
                         HttpServletResponse response)throws UnsupportedEncodingException{
        String filepath = session.getAttribute("filepath").toString();
        File file = new File(filepath + "/" + filename);
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
    }

    @PostMapping("/fileUpload")
    public String multifileUpload(@RequestParam("fileName")List<MultipartFile> files,HttpSession session){

        if(files.isEmpty()){
            System.out.println("选择上传的文件为空");
        }

        String path = session.getAttribute("filepath").toString() ;

        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            if(file.isEmpty()){
                System.out.println("文件为空");
            }else{
                File dest = new File(path + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/secondRefresh";
    }

    @GetMapping("/secondRefresh")
    public String refresh(HttpSession session,Model model){
        String path = session.getAttribute("filepath").toString();
        int account = Integer.parseInt(session.getAttribute("account").toString());
        File fileroot = new File(path);
        String [] filelist = fileroot.list();
        model.addAttribute("filelist",filelist);

        List<String> pathlist = new ArrayList<>();
        pathlist.add("根目录");
        model.addAttribute("pathlist",pathlist);
        return "file/dirfile";
    }

    @GetMapping("/filedir/{filename}")
    public String clickdir(@PathVariable("filename")String filename, HttpSession session,
                           Model model){
        String filepath = session.getAttribute("filepath").toString()+"/" + filename;
        File file = new File(filepath);
        String[] filelist = file.list();
        model.addAttribute("filelist",filelist);
        session.setAttribute("filepath",filepath);

        String account = session.getAttribute("account").toString();
        String str = filepath.replace("D:/test"+"/"+account,"");
        String [] pathlist = str.split("/");

        ArrayList<String> alist = new ArrayList<>();
        int length = pathlist.length;
        for (int i=1;i<length;i++){
            alist.add(pathlist[i]);
        }
        model.addAttribute("pathlist",alist);
        return "file/dirfile";
    }

    @PostMapping("/change_path")
    public String click_path(@RequestParam(name = "prePath")String path,
                             Model model,
                             HttpSession session){
        String account = session.getAttribute("account").toString();
        String Rootpath = rootpath + account + "/" + path;
        System.out.println(Rootpath);
        File file = new File(Rootpath);
        String[] filelist = file.list();
        session.setAttribute("filepath",Rootpath);
        model.addAttribute("filelist",filelist);

        String[] pathlist = path.split("/");
        model.addAttribute("pathlist",pathlist);
        return "file/dirrefresh";
    }

    @PostMapping("/delet_file")
    public String deletlist(@RequestParam(value = "deletlist[]")List<String> list,
                            HttpSession session, Model model){
        String rootpath = session.getAttribute("filepath").toString();
        for (String el:list
             ) {
            //File file = new File(rootpath + "/" + el);
            //file.delete();
            delete(rootpath + "/" + el);
        }

        File file = new File(rootpath);
        String[] filelist = file.list();
        model.addAttribute("filelist",filelist);

        return "file/refresh";
    }

    @GetMapping("/copy")
    @ResponseBody
    public String copy(HttpSession session,
                       @RequestParam(value = "deletlist[]")List<String> list){
        try{
            String source_path = session.getAttribute("filepath").toString();
            session.setAttribute("source_path",source_path);
            session.setAttribute("copylist",list);
            return "copy_suucess";
        }
        catch (Exception e){
            return "failed";
        }

    }

    @GetMapping("/pates")
    public String pates(HttpSession session,Model model){
        String target_path = session.getAttribute("filepath").toString();
        String source_path = session.getAttribute("source_path").toString();
        List<String> list = (List<String>) session.getAttribute("copylist");
        for (String el:list
             ) {
            try{
                File sourfile = new File(source_path+ "/" + el);
                File destfile = new File(target_path + "/" + el);
                sourfile.renameTo(destfile);
                String [] filelist = new File(target_path).list();
                model.addAttribute("filelist",filelist);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return "file/refresh";
    }

    @GetMapping("/newdir")
    public String newdir(@RequestParam(name = "newdir_name")String dir_name, HttpSession session, Model model){
        String path = session.getAttribute("filepath").toString();
        File file = new File(path + "/" + dir_name);
        if(!file.exists()){
            file.mkdir();
            file = new File(path);
            String [] filelist = file.list();
            model.addAttribute("filelist",filelist);

            String filepath = path;
            String account = session.getAttribute("account").toString();
            String str = filepath.replace("D:/test"+"/"+account,"");
            String [] pathlist = str.split("/");

            ArrayList<String> alist = new ArrayList<>();
            int length = pathlist.length;
            for (int i=1;i<length;i++){
                alist.add(pathlist[i]);
            }
            model.addAttribute("pathlist",alist);
            return "file/dirfile";
        }
        else{
            file = new File(path);
            String [] filelist = file.list();
            model.addAttribute("filelist",filelist);

            String filepath = path;
            String account = session.getAttribute("account").toString();
            String str = filepath.replace("D:/test"+"/"+account,"");
            String [] pathlist = str.split("/");

            ArrayList<String> alist = new ArrayList<>();
            int length = pathlist.length;
            for (int i=1;i<length;i++){
                alist.add(pathlist[i]);
            }
            model.addAttribute("pathlist",alist);
            return "file/newfile_fail";
        }
    }



    public boolean delete(String path){
        File file = new File(path);
        if(!file.exists()){
            return false;
        }
        if(file.isFile()){
            return file.delete();
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if(f.isFile()){
                if(!f.delete()){
                    System.out.println(f.getAbsolutePath()+" delete error!");
                    return false;
                }
            }else{
                if(!this.delete(f.getAbsolutePath())){
                    return false;
                }
            }
        }
        return file.delete();
    }

}
