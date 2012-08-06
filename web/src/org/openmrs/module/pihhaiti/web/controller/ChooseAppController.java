package org.openmrs.module.pihhaiti.web.controller;

import javax.servlet.http.HttpSession;

import org.openmrs.module.pihhaiti.web.App;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChooseAppController {
    
    @RequestMapping("/module/pihhaiti/chooseApp.form") 
    public String chooseApp(@RequestParam(value="appName") String appName, HttpSession session) {
    	App app = App.lookupByName(appName);
    	session.setAttribute(App.SELECTED_APP_SESSION_KEY, app);
    	return "redirect:"+app.getIndexUrl();
    }
    
    @RequestMapping("/module/pihhaiti/chooseDashboard.form") 
    public String chooseDashboard(@RequestParam(value="patientId") Integer patientId, HttpSession session) {
    	App app = (App)session.getAttribute(App.SELECTED_APP_SESSION_KEY);
    	return "redirect:"+app.getPatientDashboardUrl()+"?patientId="+patientId;
    }
}
