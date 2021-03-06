/*-
 * ========================LICENSE_START=================================
 * IDS Core Platform Webconsole
 * %%
 * Copyright (C) 2017 - 2018 Fraunhofer AISEC
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
package de.fhg.aisec.ids.webconsole.api.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

class StreamGobbler extends Thread {
    private static final Logger LOG = LoggerFactory.getLogger(StreamGobbler.class);
	InputStream is;
	OutputStream out;

	public StreamGobbler(InputStream is, OutputStream out) {
		this.is = is;
		this.out = out;
	}

	@Override
	public void run() {
		try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
			if (out != null) {
				try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8))) {
					String line;
					while ((line = br.readLine()) != null) {
						bw.write(line);
						bw.newLine();
					}
				}
			} else {
				while (true) {
					if (br.readLine() == null) break;
				}
			}
		} catch (IOException ioe) {
			LOG.error(ioe.getMessage(), ioe);
		}
	}
}
