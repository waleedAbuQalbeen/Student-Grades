package com.waleed.student;

import com.waleed.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;

@Controller
@SessionAttributes("ID")
public class StudentController {
    @Autowired
     private StudentService service;


    @RequestMapping(value = "/list-grades", method = RequestMethod.POST)
    public String gradesList(ModelMap model) throws SQLException {
        int id = (int) model.get("ID");

            model.addAttribute("grades", service.displayGrades(id));

        return "list-grades";
    }

    @RequestMapping(value = "/list-information", method = RequestMethod.POST)
    public String statisticsList(ModelMap model , @RequestParam int curs_id) throws SQLException {
        int id = (int) model.get("ID");
        boolean inCourse = service.isInCourse(id,curs_id);
        if(!inCourse){
            model.put("errorMessage", "you are not registered in this course !!");
            return "options";
        }
        model.addAttribute("grades", service.courseInformation(id, curs_id));
        return "list-grades";
    }


}
