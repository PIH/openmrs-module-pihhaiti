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
package org.openmrs.module.pihhaiti;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.layout.web.address.AddressSupport;
import org.openmrs.layout.web.address.AddressTemplate;
import org.openmrs.layout.web.name.NameSupport;
import org.openmrs.layout.web.name.NameTemplate;
import org.openmrs.module.Activator;

/**
 * This class contains the logic that is run every time this module
 * is either started or shutdown
 */
public class PihHaitiActivator implements Activator {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * @see org.openmrs.module.Activator#startup()
	 */
	public void startup() {
		log.info("Starting pihhaiti Module");
		registerHaitiAddressTemplate();
		registerHaitiNameTemplate();
	}
	
	/**
	 *  @see org.openmrs.module.Activator#shutdown()
	 */
	public void shutdown() {
		log.info("Shutting down pihhaiti Module");
		unregisterHaitiAddressTemplate();
		unregisterHaitiNameTemplate();
		CustomBrandingHandler.removeCustomizationFromWebapp();
	}
	
	/**
	 * Sets up the Zanmi Lansante Address Template
	 */
	public void registerHaitiAddressTemplate() {
		
		log.info("Registering Zanmi Lansante address format.");
		
		AddressTemplate at = new AddressTemplate("zanmi");
		at.setDisplayName("Zanmi Lansante Address Format");
		at.setCodeName("haiti_zl");
		at.setCountry("Haiti"); 
		
		Map<String, String> nameMappings = new HashMap<String, String>();
		nameMappings.put("country", "pihhaiti.address.country");
		nameMappings.put("stateProvince", "pihhaiti.address.stateProvince");
		nameMappings.put("cityVillage", "pihhaiti.address.cityVillage");
		nameMappings.put("neighborhoodCell", "pihhaiti.address.neighborhoodCell");
		nameMappings.put("address1", "pihhaiti.address.address1");
		nameMappings.put("address2", "pihhaiti.address.address2");
		at.setNameMappings(nameMappings);
		
		Map<String, String> sizeMappings = new HashMap<String, String>();
		sizeMappings.put("country", "40");
		sizeMappings.put("stateProvince", "40");
		sizeMappings.put("cityVillage", "40");
		sizeMappings.put("neighborhoodCell", "60");
		sizeMappings.put("address1", "60");
		sizeMappings.put("address2", "60");
		at.setSizeMappings(sizeMappings);
		
		Map<String, String> elementDefaults = new HashMap<String, String>();
		elementDefaults.put("country", "Haiti");	
		at.setElementDefaults(elementDefaults);
		
		at.setLineByLineFormat(Arrays.asList("address2","address1","neighborhoodCell cityVillage","stateProvince country"));
		
		AddressSupport.getInstance().getLayoutTemplates().add(at);
	}
	
	/**
	 * Unregisters the Zanmi Lansante Address Template
	 */
	public void unregisterHaitiAddressTemplate() {
		for (Iterator<AddressTemplate> i = AddressSupport.getInstance().getLayoutTemplates().iterator(); i.hasNext();) {
			AddressTemplate at = i.next();
			if ("haiti_zl".equals(at.getCodeName())) {
				i.remove();
			}
		}
	}
	
	/**
	 * Sets up the Zanmi Lansante Name Template
	 */
	public void registerHaitiNameTemplate () {
		
		log.info("Registering Zanmi Lansante name format.");
		
		NameTemplate nt = new NameTemplate();
		nt.setDisplayName("Zanmi Lansante Name Format");
		nt.setCodeName("haiti_zl");
		nt.setCountry("Haiti");
		
		Map<String, String> nameMappings = new HashMap<String, String>();
		nameMappings.put("givenName", "pihhaiti.name.givenName");
		nameMappings.put("familyName", "pihhaiti.name.familyName");
		nt.setNameMappings(nameMappings);
		
		Map<String, String> sizeMappings = new HashMap<String, String>();
		sizeMappings.put("givenName", "40");
		sizeMappings.put("familyName", "40");
		nt.setSizeMappings(sizeMappings);
		
		nt.setLineByLineFormat(Arrays.asList("givenName","familyName"));
		
		NameSupport.getInstance().getLayoutTemplates().add(nt);
	}
	
	/**
	 * Unregisters the Zanmi Lansante Name Template
	 */
	public void unregisterHaitiNameTemplate() {
		for (Iterator<NameTemplate> i = NameSupport.getInstance().getLayoutTemplates().iterator(); i.hasNext();) {
			NameTemplate nt = i.next();
			if ("haiti_zl".equals(nt.getCodeName())) {
				i.remove();
			}
		}
	}
 	
}
