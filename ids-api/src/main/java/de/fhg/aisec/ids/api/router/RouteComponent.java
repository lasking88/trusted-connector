/*-
 * ========================LICENSE_START=================================
 * IDS Core Platform API
 * %%
 * Copyright (C) 2017 Fraunhofer AISEC
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */
package de.fhg.aisec.ids.api.router;

/**
 * Representation of a "route component", i.e. a protocol adapter to attach
 * route endpoints to external services.
 * 
 * @author Julian Schuette (julian.schuette@aisec.fraunhofer.de)
 *
 */
public class RouteComponent {
	private String bundle;
	private String description;

	public RouteComponent() {	/* Bean std c'tor */	}

	public RouteComponent(String bundleName, String description) {
		this.bundle = bundleName;
		this.description = description;
	}

	public String getBundle() {
		return bundle;
	}

	public String getDescription() {
		return description;
	}
}
