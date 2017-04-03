package org.seckill.web;

import org.seckill.service.PreLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value ="/seckill")
public class PreLoginController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PreLoginService preLoginService;
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
    @RequestMapping("/register")
    public String register()
    {
        return "register";
    }
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
//    @ResponseBody
    public String doLogin(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("phone") Long phone,
                        Model model)
    {
        if (!preLoginService.isExist(phone))
            return "redirect:/seckill/register";
        boolean b=preLoginService.login(username,password,phone);
        if(b)
        {
            model.addAttribute("username",username);
            model.addAttribute("phone",phone);
            return "redirect:/seckill/list";
        }
        else
            return "login";
    }
    @RequestMapping(value = "/doRegister",method = RequestMethod.POST)

    public String doRegister(@RequestParam(value = "username")String username,
                             @RequestParam(value = "password")String password,
                             @RequestParam(value = "phone")Long phone)
    {
        boolean br=preLoginService.registerUser(username,password,phone);
        if (br)
            return "redirect:/seckill/login";
        return "register";
    }
}
