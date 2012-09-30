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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;
import org.openmrs.layout.web.name.NameSupport;
import org.openmrs.layout.web.name.NameTemplate;
import org.openmrs.module.ModuleActivator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class contains the logic that is run every time this module
 * is either started or shutdown
 */
public class PihHaitiActivator implements ModuleActivator {

    private String ADDRESS_FORMAT_GP = "layout.address.format";

    private String VISITS_ENABLED_GP = "visits.enabled";

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * @see org.openmrs.module.Activator#startup()
	 */
    @Override
	public void started() {
		log.info("Starting pihhaiti Module");
		registerHaitiAddressTemplate();
		registerHaitiNameTemplate();
        disableVisits();
	}
	
	/**
	 *  @see org.openmrs.module.Activator#shutdown()
	 */
    @Override
	public void stopped() {
		log.info("Shutting down pihhaiti Module");
		unregisterHaitiNameTemplate();
		CustomBrandingHandler.removeCustomizationFromWebapp();
	}

    @Override
    public void willRefreshContext() {

    }

    @Override
    public void contextRefreshed() {

    }

    @Override
    public void willStart() {

    }

    @Override
    public void willStop() {

    }

    /**
	 * Sets up the Zanmi Lansante Address Template
	 */
	public void registerHaitiAddressTemplate() {
		
		log.info("Registering Zanmi Lansante address format.");

        StringBuffer addressTemplate = new StringBuffer();

        addressTemplate.append("<org.openmrs.layout.web.address.AddressTemplate>");

        addressTemplate.append("<nameMappings class=\"properties\">");
        addressTemplate.append("<property name=\"country\" value=\"pihhaiti.address.country\"/>");
        addressTemplate.append("<property name=\"stateProvince\" value=\"pihhaiti.address.stateProvince\"/>");
        addressTemplate.append("<property name=\"cityVillage\" value=\"pihhaiti.address.cityVillage\"/>");
        addressTemplate.append("<property name=\"address3\" value=\"pihhaiti.address.neighborhoodCell\"/>");
        addressTemplate.append("<property name=\"address1\" value=\"pihhaiti.address.address1\"/>");
        addressTemplate.append("<property name=\"address2\" value=\"pihhaiti.address.address2\"/>");
        addressTemplate.append("</nameMappings>");

        addressTemplate.append("<sizeMappings class=\"properties\">");
        addressTemplate.append("<property name=\"country\" value=\"40\"/>");
        addressTemplate.append("<property name=\"stateProvince\" value=\"40\"/>");
        addressTemplate.append("<property name=\"cityVillage\" value=\"40\"/>");
        addressTemplate.append("<property name=\"address3\" value=\"60\"/>");
        addressTemplate.append("<property name=\"address1\" value=\"60\"/>");
        addressTemplate.append("<property name=\"address2\" value=\"60\"/>");
        addressTemplate.append("</sizeMappings>");

	    addressTemplate.append("<elementDefaults class=\"properties\">");
        addressTemplate.append("<property name=\"country\" value=\"Haiti\"/>");
        addressTemplate.append("</elementDefaults>");

        addressTemplate.append("<lineByLineFormat>");
        addressTemplate.append("<string>address2</string>");
        addressTemplate.append("<string>address1</string>");
        addressTemplate.append("<string>address3 cityVillage</string>");
        addressTemplate.append("<string>stateProvince country</string>");
        addressTemplate.append("</lineByLineFormat>");

        addressTemplate.append("</org.openmrs.layout.web.address.AddressTemplate>");

        GlobalProperty addressFormat = Context.getAdministrationService().getGlobalPropertyObject(ADDRESS_FORMAT_GP);
        addressFormat.setPropertyValue(addressTemplate.toString());
        Context.getAdministrationService().saveGlobalProperty(addressFormat);

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


    /**
     * Sets the global property to enable visits to false
     */
    public void disableVisits() {
        GlobalProperty visitsEnabled = Context.getAdministrationService().getGlobalPropertyObject(VISITS_ENABLED_GP);
        visitsEnabled.setValue(false);
        Context.getAdministrationService().saveGlobalProperty(visitsEnabled);
    }
}
