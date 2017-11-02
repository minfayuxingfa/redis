package com.lanou.controller;

import com.lanou.bean.Student;
import com.lanou.service.StudentService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/11/2.
 */
@Controller
public class Studentcontroller {

    @Resource
    private StudentService studentService;


    @RequestMapping(value = "/home")
    public String home(){
        return "home";
    }

    @ResponseBody
    @RequestMapping(value = "/findAll")
    public AjaxResult findAllStudent(){
        List<Student> allStudent = studentService.findAllStudent();

        return new AjaxResult(allStudent);
    }


}
