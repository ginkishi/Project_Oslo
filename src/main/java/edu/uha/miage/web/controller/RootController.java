
package edu.uha.miage.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// #### V0.0 RootController permet de gérer les appels à l'url /
@Controller
@RequestMapping()
public class RootController {
    @RequestMapping(path = {"", "/home"}, method = RequestMethod.GET)
// #### V0.0 Une requête HTTP/GET sur / utilise le template src/main/resources/templates/home.html
    public String home() {       
        return "home";
    }
    @RequestMapping(path = {"/login"}, method = RequestMethod.GET)
// #### V0.0 Une requête HTTP/GET sur / utilise le template src/main/resources/templates/home.html
    public String login() {       
        return "login";
    }
     @RequestMapping(path = {"/logout"}, method = RequestMethod.GET)
// #### V0.0 Une requête HTTP/GET sur / utilise le template src/main/resources/templates/home.html
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/login";
    }
}
