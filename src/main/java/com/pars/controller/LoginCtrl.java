package com.pars.controller;

import com.pars.entity.User;
import com.pars.service.UserService;
import com.pars.system.response.ResponseType;
import com.pars.system.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginCtrl {

    @Autowired
    private UserService service;

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public ModelAndView login_GET(@AuthenticationPrincipal User user,
                                  @RequestParam(value = "invalid", required = false) String invalid) {
        if (user != null) return new ModelAndView("redirect:/home");
        ServerResponse response = new ServerResponse();
        if (invalid != null) {
            response.setResponseType(ResponseType.Erro);
            response.setMensagem("Usuário/Senha inválidos.");
        }
        System.out.println(response.toString());
        ModelAndView model = new ModelAndView("login");
        return model;
    }
}
