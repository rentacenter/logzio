package com.rentacenter.examples.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import javax.annotation.PreDestroy;

@Controller
public class BaseController {
  private static int counter = 0;
  private static final String VIEW_INDEX = "index";
  private final static Logger logger = LogManager.getLogger(BaseController.class);

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String welcome(ModelMap model) {
    // programatic way to inject specific tags across all logger calls
    MDC.put("team", "DevOps");
    MDC.put("project", "Logz.io examples");

    // sample logs
    logger.info("Testing logz.io!");
    logger.warn("Winter is coming");

    model.addAttribute("line1", "Testing logz.io!");
    model.addAttribute("line2", "Winter is coming");

    // Spring uses InternalResourceViewResolver and return back index.jsp
    return VIEW_INDEX;
  }

  @PreDestroy
  private void destroy() {
    logger.info("BaseController shutting down LogManager");
    LogManager.shutdown();
  }
}
