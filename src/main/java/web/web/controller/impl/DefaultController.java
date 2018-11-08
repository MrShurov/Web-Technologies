package web.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.web.service.api.SiteService;
import web.web.service.api.WordService;

@Controller
public class DefaultController {

    @Autowired
    SiteService siteService;
    @Autowired
    WordService wordService;

    @GetMapping("/site")
    public String site(Model model) {
        model.addAttribute("site", siteService.getAll());
        model.addAttribute("search", null);
        return "/site";
    }

    @PostMapping("/addSite")
    public String addSite(Model model, @RequestParam String url){
        siteService.addSite(url);
        model.addAttribute("site", siteService.getAll());
        model.addAttribute("search", null);
        return "/site";
    }

    @PostMapping("/word")
    public String word(Model model, @RequestParam String searchString){
        model.addAttribute("site", siteService.getAll());
        model.addAttribute("search",wordService.searchWords(searchString));
        return "/site";
    }
}
