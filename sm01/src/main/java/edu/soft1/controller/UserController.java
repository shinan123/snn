package edu.soft1.controller;

import edu.soft1.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping("hello")
    public String hello(){
        System.out.println("---hello()---");
        return "";
    }

    @RequestMapping(value = "/login")
    public String login(User user, HttpSession session, HttpServletRequest request) {
        System.out.println("---login()---");//进入方法
        int flag = 1;  //调用业务层(业务层调用dao层)，获取flag的值
        if (flag == 1) {
            System.out.println("username="+user.getUsername());
            session.setAttribute("user", user);
            return "welcome";//登陆成功
        }
        System.out.println("登录失败，返回首页index");
        request.setAttribute("error","登陆失败，请重试。；");
        return "forward:/index.jsp";//登陆失败
    }

    @RequestMapping("delete")
    public String delete(HttpServletRequest request){
        System.out.println("---执行delete()成功---");
        request.setAttribute("CRUDmsg","删除成功");
        return "hello";
    }

    @RequestMapping("welcome")
    public String welcome(){
        System.out.println("---welcome()---");
        return "welcome";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        System.out.println("已退出");
        session.invalidate();
        return "redirect:/index.jsp";
    }

   /* @RequestMapping("reg")
    public String reg(User user, HttpSession session) {
        System.out.println("username=" + user.getUsername());
        System.out.println("age=" + user.getAge());
        System.out.println("pwd=" + user.getPwd());
        System.out.println("birthday=" + user.getBirthday());
        System.out.println("city=" + user.getAddress().getCity());
        System.out.println("street=" + user.getAddress().getStreet());
        System.out.println("phon=" + user.getAddress().getPhone());
        session.setAttribute("name", user.getUsername());
        session.setAttribute("bir", user.getBirthday());
        return "redirect:/param/text";
    }*/

}