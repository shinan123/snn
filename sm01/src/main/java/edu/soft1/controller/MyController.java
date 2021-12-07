package edu.soft1.controller;

import edu.soft1.pojo.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/param1")
public class MyController {
    @RequestMapping(value = "upload",method = {RequestMethod.POST})
    public String fileUpload(MultipartFile image,HttpServletRequest request) throws IOException {
        InputStream is =image.getInputStream();
        String filename = image.getOriginalFilename();
        String realPath = request.getServletContext().getRealPath("/images");
        System.out.println("上传路径="+realPath);
        FileOutputStream os = new FileOutputStream(new File(realPath,doFileName(filename)));
        int size = IOUtils.copy(is,os);
        System.out.println("完成上传size="+size+"Byte");
        os.close();is.close();
        return "welcome";
    }

    private String doFileName(String filename){
        String extension = FilenameUtils.getExtension(filename);
        String uuid = UUID.randomUUID().toString();
        System.out.println("上传文件名="+uuid);
        return uuid+"."+extension;
    }
    @RequestMapping(value = "upload2.do",method = {RequestMethod.POST})
    public  String fileUpload2(MultipartFile[] images, HttpServletRequest request) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        for (MultipartFile image:images) {
            is = image.getInputStream(); //获取文件的输入流对象
            String filename = image.getOriginalFilename();//获取文件的真实名字
            System.out.println("文件原名称="+filename);
            if (filename.equals("")){
                System.out.println("空字符串，进入下一轮循环");
                continue;
            }
            String realPath = request.getServletContext().getRealPath("/images");
            System.out.println("上传路径realPath=" + realPath);
            os = new FileOutputStream(new File(realPath, doFileName2(filename)));
            IOUtils.copy(is, os);
            os.close();is.close();//关闭流

        }
        return "welcome";
    }

    private  String doFileName2(String filename){
        String extension = FilenameUtils.getExtension(filename);//获取文件的后缀名称
        String uuid = UUID.randomUUID().toString();//读取uuid字符，规避名称重复
        System.out.println("上传文件名="+uuid);
        return uuid+"."+extension;

    }
    @RequestMapping(value = "/use")
    public String use(){
        System.out.println("---- use() ----");
        return "hello";
    }


//    /*@RequestMapping("/hello.do")
//    public String hello(){
//        return "hello";
//    }*/
//
//    /*@RequestMapping(value = "/param1", method = {RequestMethod.GET})
//    public String param1(HttpServletRequest request) {
//        //接受client发来的数据
//        String name = request.getParameter("name");
//        System.out.println("name" + name);//打印数据
//        request.setAttribute("name", name);//将数据存入request
//        return "/hello.jsp";
//    }
//
//    @RequestMapping(value = "/param2", method = {RequestMethod.GET, RequestMethod.POST})
//    public String param2(HttpServletRequest request, HttpSession session) {
//        String name = request.getParameter("username");
//        String age = request.getParameter("age");
//        System.out.println("name=" + name + ",age+" + age);
//        session.setAttribute("age", age);
//        session.setAttribute("name", name);
//        return "/hello.jsp";
//    }
//
//    @RequestMapping(value = "/param3", method = {RequestMethod.POST})
//    public String param3(String username, int age) {
//        System.out.println("---param3()---");
//        System.out.println("username=" + username);
//        System.out.println("age=" + age);
//        return "/hello.jsp";
//    }
//
//    @RequestMapping(value = "/param4", method = {RequestMethod.POST})
//    public String param4(@RequestParam(value = "username", required = false) String u,
//                         @RequestParam(value = "age", defaultValue = "18") int a, HttpSession request) {
//        System.out.println("---param4()---");
//        System.out.println("username=" + u);
//        System.out.println("age=" + a);
//        request.setAttribute("name", u);
//        return "redirect:text";
//    }
//
//    @RequestMapping(value = "/param5", method = {RequestMethod.POST})
//    public String param5(User user, HttpSession session) {
//        System.out.println("---param5()---");
//        System.out.println("username=" + user.getUsername());
//        System.out.println("age=" + user.getAge());
//        session.setAttribute("name", user.getUsername());
//        return "redirect:text";
//    }
//
//    @RequestMapping("text")
//    public String text() {
//        System.out.println("---text()---");
//        return "hello";
//    }
//
//    @RequestMapping("reg")
//    public String reg(User user, HttpSession session) {
//        System.out.println("username=" + user.getUsername());
//        System.out.println("age=" + user.getAge());
//        System.out.println("birthday=" + user.getBirthday());
//        System.out.println("city=" + user.getAddress().getCity());
//        System.out.println("street=" + user.getAddress().getStreet());
//        System.out.println("phon=" + user.getAddress().getPhone());
//        session.setAttribute("name", user.getUsername());
//        session.setAttribute("bir", user.getBirthday());
//        return "redirect:/param1/text";
//    }*/
//
//
//  /*  @RequestMapping("/hello")
//    public String hello(String username, Model model){
//        System.out.println("----hello()---");
//        model.addAttribute("username",username);
//        return "hello";
//    }*/
//
//
//   /* @RequestMapping("/text")
//    public String hello(String username, Map<String, String> map) {
//        System.out.println("----hello()---");
//        map.put("username", username);
//        return "hello";
//    }*/
}
