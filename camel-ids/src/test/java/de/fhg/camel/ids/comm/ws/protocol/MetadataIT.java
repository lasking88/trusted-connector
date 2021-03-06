/*-
 * ========================LICENSE_START=================================
 * Camel IDS Component
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
package de.fhg.camel.ids.comm.ws.protocol;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fhg.aisec.ids.messages.Idscp.ConnectorMessage;
import de.fhg.ids.comm.ws.protocol.fsm.Event;
import de.fhg.ids.comm.ws.protocol.metadata.MetadataConsumerHandler;
import de.fhg.ids.comm.ws.protocol.metadata.MetadataProviderHandler;

//import de.fhg.ids.docker.Docker;
@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MetadataIT {
	
	private static MetadataConsumerHandler consumer;
	private static MetadataProviderHandler provider;
	private static Logger LOG = LoggerFactory.getLogger(MetadataIT.class);
	private long id = 87654321;

	private ConnectorMessage msg0 = ConnectorMessage.newBuilder().setType(ConnectorMessage.Type.RAT_LEAVE).setId(id).build();
	
	private static ConnectorMessage msg1;
	private static ConnectorMessage msg2;
	private static ConnectorMessage msg3;
	//private static Docker docker;
	
	@BeforeClass
	public static void initRepo() throws URISyntaxException {
        consumer = new MetadataConsumerHandler();
		provider = new MetadataProviderHandler();
		//docker = new Docker();
	}
	
    @Test
    public void test1() throws Exception {
    	msg1 = ConnectorMessage.parseFrom(consumer.request(new Event(msg0.getType(), msg0.toString(), msg0)).toByteString());
    	LOG.debug("msg1: " + msg1.toString());
    	assertTrue(msg0.getId() == id);
    	assertTrue(msg1.getId() == id + 1);
    	assertTrue(msg1.getType().equals(ConnectorMessage.Type.META_REQUEST));
    	//assertTrue(msg1.getMetadataExchange().getKeyCount() == 1);
    	//assertTrue(msg1.getMetadataExchange().getKeyList().get(0).equals("labels"));
    }
    
    @Test
    public void test2() throws Exception {
    	msg2 = ConnectorMessage.parseFrom(provider.response(new Event(msg1.getType(), msg1.toString(), msg1)).toByteString());
    	LOG.debug(msg2.toString());
    	assertTrue(msg2.getId() == id + 2);
    	assertTrue(msg2.getType().equals(ConnectorMessage.Type.META_RESPONSE));
    	//assertTrue(msg2.getMetadataExchange().getKeyCount() == 1);
    	//assertTrue(msg2.getMetadataExchange().getKeyList().get(0).equals("labels"));
    	//assertTrue(msg2.getMetadataExchange().getValueCount() == 1);
    }    
    
}
