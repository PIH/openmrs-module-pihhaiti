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
package org.openmrs.module.pihhaiti.web.extension;

import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.api.context.Context;
import org.openmrs.module.Extension;
import org.springframework.util.StringUtils;

/**
 * Provides links in the patient header
 */
public class PatientHeaderExtension extends Extension {

	/** 
	 * @see org.openmrs.module.Extension#getMediaType()
	 */
	@Override
	public MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}

	/** 
	 * @see org.openmrs.module.Extension#getOverrideContent(java.lang.String)
	 */
	@Override
	public String getOverrideContent(String bodyContent) {
		String patientId = getParameterMap().get("patientId");
		String idTypeName = Context.getAdministrationService().getGlobalProperty("pihhaiti.hivEmrIdentifierTypeName");
		if (StringUtils.hasText(patientId) && StringUtils.hasText(idTypeName)) {
			Patient p = Context.getPatientService().getPatient(Integer.parseInt(patientId));
			PatientIdentifier pi = p.getPatientIdentifier(idTypeName);
			if (pi != null) {
				StringBuilder sb = new StringBuilder();
				sb.append("<div style=\"font-weight:bold; background-color:yellow; width:100%;\">");
				sb.append("<a style=\"color:red;\" target=\"__blank\" href=\"https://haiti.pih-emr.org/hiv/patient?patient_id="+pi.getIdentifier()+"\">");
				sb.append(Context.getMessageSourceService().getMessage("pihhaiti.hivCoinfectedAlert"));
				sb.append("</a></div>");
				return sb.toString();
			}
		}
		return "";
	}
}
