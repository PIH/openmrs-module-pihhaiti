/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.pihhaiti.web;

import org.openmrs.User;

/**
 * Represents all of the supported clinics/services that
 * appear as separate applications within the system
 */
public enum App {
	
	OUTPATIENT (
		"outpatient", 
		"/module/patientregistration/workflow/selectLocationAndService.form", 
		"/module/patientregistration/workflow/patientDashboard.form",
		"Outpatient Application User"
	),
	MDR_TB (
		"mdrtb", 
		"/module/mdrtb/mdrtbIndex.form", 
		"/module/mdrtb/dashboard/dashboard.form",
		"MDR-TB Application User"
	),
	REHAB(
		"rehab", 
		"/module/pihhaiti/rehab/index.form", 
		"/patientDashboard.form",
		"Rehab Application User"
	);
	
	App(String name, String indexUrl, String patientDashboardUrl, String requiredRoleName) {
		this.name = name;
		this.indexUrl = indexUrl;
		this.patientDashboardUrl = patientDashboardUrl;
		this.requiredRoleName = requiredRoleName;
	}
	
	private String name;
	private String indexUrl;
	private String patientDashboardUrl;
	private String requiredRoleName;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the indexUrl
	 */
	public String getIndexUrl() {
		return indexUrl;
	}
	
	/**
	 * @return the patientDashboardUrl
	 */
	public String getPatientDashboardUrl() {
		return patientDashboardUrl;
	}
	
	/**
	 * @return the requiredRoleName
	 */
	public String getRequiredRoleName() {
		return requiredRoleName;
	}

	/**
	 * @return whether the passed user can access the application
	 */
	public boolean canAccess(User u) {
		return u.hasRole(requiredRoleName, true);
	}
	
	public static final String SELECTED_APP_SESSION_KEY = "pihhaiti_selected_app";
	
	/**
	 * @return the App that has the passed name
	 */
	public static final App lookupByName(String name) {
		for (App a : App.values()) {
			if (a.getName().equalsIgnoreCase(name)) {
				return a;
			}
		}
		return null;
	}
}
