package ru.gb.springdemo.api.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.service.IssuerService;

@Controller
@RequestMapping("/ui/issues")
public class IssuerThymeleafController {

  @Autowired
  private IssuerService service;

  @GetMapping()
  public String  getIssue(Model model) {
    model.addAttribute("issues", service.getAllIssues());
    return "issues";
  }

  @GetMapping("/detail")
  public String  getIssueDetail(Model model) {
    model.addAttribute("issues", service.getAllIssuesDetail());
    return "issuesDetail";
  }

}
