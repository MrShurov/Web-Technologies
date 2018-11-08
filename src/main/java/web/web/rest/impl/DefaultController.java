package web.web.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import web.web.service.api.SiteService;

@Controller
public class DefaultController {

    @Autowired
    SiteService siteService;

    @GetMapping("/site")
    public String site(Model model) {
        model.addAttribute("site", siteService.getAll());
        return "/site";
    }


}
