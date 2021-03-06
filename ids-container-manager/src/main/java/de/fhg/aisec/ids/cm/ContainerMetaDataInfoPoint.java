/*-
 * ========================LICENSE_START=================================
 * IDS Container Manager
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
package de.fhg.aisec.ids.cm;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fhg.aisec.ids.api.MetaDataInfoPoint;
import de.fhg.aisec.ids.api.cm.ContainerManager;
import de.fhg.aisec.ids.api.cm.NoContainerExistsException;

/**
 * Provides meta data (= information) about running applications (= containers).
 * 
 * Meta data is stored in labels attached to containers. This service retrieves
 * the data from the container label and provides it as a key/value list.
 * 
 * @author Julian Schütte (julian.schuette@aisec.fraunhofer.de)
 *
 */
@Component(enabled = true, immediate = true, name = "ids-metadata")
public class ContainerMetaDataInfoPoint implements MetaDataInfoPoint {
	private static final Logger LOG = LoggerFactory.getLogger(ContainerManagerService.class);
	private ContainerManager cm;

	/**
	 * Just for logging.
	 */
	@Activate
	protected void activate() {
		LOG.debug("Container Meta Data Info Point activating");
	}

	/**
	 * Just for logging.
	 */
	@Deactivate
	protected void deactivate() {
		LOG.debug("Container Meta Data Info Point deactivating");
	}

	/**
	 * Called by OSGi declarative service framework when a ContainerManager
	 * service becomes available.
	 * 
	 * @param cm
	 */
	@Reference(name = "container.service", service = ContainerManager.class, cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC, unbind = "unbindConfigurationService")
	protected void bindContainerManager(ContainerManager cm) {
		this.cm = cm;
	}

	/**
	 * Called by OSGi DS when the ContainerManager service has been removed.
	 * 
	 * @param cm
	 */
	protected void unbindContainerManager(ContainerManager cm) {
		this.cm = null;
	}

	@Override
	public Map<String, String> getContainerLabels(String containerID) {
		HashMap<String, String> result = new HashMap<String, String>();
		if (this.cm == null) {
			return result;
		}

		// TODO currently returns only the output of "docker inspect <id>". Should
		// be converted to some standardized meta data format
		String containerLabels;
		try {
			containerLabels = (String) this.cm.getMetadata(containerID);
			String[] labelLines = containerLabels.split("\n");
			for (String line : labelLines) {
				String[] kv = line.split(":");
				if (kv.length == 2) {
					result.put(kv[0], kv[1]);
				}
			}
		} catch (NoContainerExistsException e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
}