package org.openmrs.module.pihhaiti.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.definition.service.ReportDefinitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RehabController {
    
    @RequestMapping("/module/pihhaiti/rehab/index.form") 
    public void viewIndex(ModelMap model) {
    	model.addAttribute("showAnalysis", ModuleFactory.getStartedModulesMap().containsKey("reportingcompatibility"));
    	String reportsToShow = Context.getAdministrationService().getGlobalProperty("pihhaiti.rehab.reportsToDisplay");
    	List<ReportDefinition> reports = new ArrayList<ReportDefinition>();
    	if (StringUtils.isNotBlank(reportsToShow)) {
        	for (String s : reportsToShow.split("\\|")) {
        		reports.addAll(Context.getService(ReportDefinitionService.class).getDefinitions(s, true));
        	}
    	}
    	model.addAttribute("reports", reports);
    }
}
