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
package de.fhg.aisec.ids.api.conm;

/**
 * Bean representing an "IDSCP Endpoint". This maps to a camel endpoint and is used to handle exposed endpoints of the IDSCP.
 * 
 * @author Gerd Brost (gerd.brost@aisec.fraunhofer.de)
 *
 */
	
public class IDSCPClientEndpoint {
	private String endpointIdentifier;
	private AttestationResult attestationResult;
	private String endpointKey;

	@Override
	public String toString() {
		return "IDSCPEndpoint [endpoint_identifier=" + endpointIdentifier + "]";
	}
	public String getEndpointIdentifier() {
		return endpointIdentifier;
	}
	public void setEndpointIdentifier(String endpointIdentifier) {
		this.endpointIdentifier = endpointIdentifier;
	}
	public AttestationResult getAttestationResult() {
		return attestationResult;
	}
	public void setAttestationResult(AttestationResult attestationResult) {
		this.attestationResult = attestationResult;
	}
	public void setEndpointKey(String endpointKey) {
		this.endpointKey = endpointKey;
	}
	public String getEndpointKey() {
		return endpointKey;
	}

	public IDSCPClientEndpoint() {
	}


}
