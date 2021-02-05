package com.netcracker.controllers;

import com.netcracker.model.FindUser;

import com.netcracker.model.RequestParameters;
import com.netcracker.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class FindUserController {
    @GetMapping("/find-user")
    public String findUserForm(Model model) {
        model.addAttribute("user", new FindUser());
        return "find-user";
    }
    @GetMapping("/no-user-result")
    public String noUserResult() {
        return "no-user-result";
    }
    @PostMapping("/find-user")
    public String findUserSubmit(@ModelAttribute FindUser user, Model model,Model modelReq, @RequestHeader(value = "User-Agent") String userAgent, HttpServletRequest request,HttpServletResponse res) {
        List<String> as = new ArrayList<>();
        String fn= "",ln= "",mn= "",job= "",em= "";
        int age=0;
        double sal = 0;
        HttpSession session = request.getSession(true);
        modelReq.addAttribute("reqparam",new RequestParameters(new Date(session.getLastAccessedTime()),userAgent));
        try {
            Files.lines(Paths.get("users_data.txt"), StandardCharsets.UTF_8).forEach(x->{
                if(x.contains(user.getFirstName()) &&x.contains(user.getLastName()))
                    as.add(x);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (as.isEmpty())
            return "redirect:/no-user-result";
        else{
            Pattern firstNameP = Pattern.compile("firstName:([^;]*);");
            Pattern middleNameP = Pattern.compile("middleName:([^;]*);");
            Pattern lastNameP = Pattern.compile("lastName:([^;]*);");
            Pattern ageP = Pattern.compile("age:([^;]*);");
            Pattern salaryP = Pattern.compile("salary:([^;]*);");
            Pattern jobP = Pattern.compile("job:([^;]*);");
            Pattern emailP = Pattern.compile("email:([^;]*)");
            Matcher matcher1 = firstNameP.matcher(as.get(0));
            if(matcher1.find())
                fn =matcher1.group(1);

            Matcher matcher2 = middleNameP.matcher(as.get(0));
            if(matcher2.find())
                mn =matcher2.group(1);

            Matcher matcher3 = lastNameP.matcher(as.get(0));
            if(matcher3.find())
                ln=matcher3.group(1);

            Matcher matcher4 = ageP.matcher(as.get(0));
            if(matcher4.find())
                age=Integer.parseInt(matcher4.group(1));

            Matcher matcher5 = salaryP.matcher(as.get(0));
            if(matcher5.find())
                sal= Double.parseDouble(matcher5.group(1));

            Matcher matcher6 = jobP.matcher(as.get(0));
            if(matcher6.find())
                job =matcher6.group(1);

            Matcher matcher7 = emailP.matcher(as.get(0));
            if(matcher7.find())
                em=matcher7.group(1);

            model.addAttribute("user", new User(fn,mn,ln,age,sal,job,em));
            Cookie cookie = new Cookie("email", em);
            cookie.setMaxAge(24*60*60);
            res.addCookie(cookie);

            return "find-user-result";
        }
    }
}
