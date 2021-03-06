/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.condition;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;

public class CountTest {

  @Test
  public void test() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "A single sentence", "And here is another one",
            "Testing the COUNT condition of Ruta System");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Testing the COUNT condition of Ruta System");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Testing the COUNT condition of Ruta System");
    
    cas.release();    
  }
  
  @Test
  public void testCountWithPeriodPostfix() throws ResourceInitializationException, InvalidXMLException, 
    IOException, AnalysisEngineProcessException, ResourceConfigurationException, URISyntaxException {
    
    String document = "Some text.";
    String script = "(CW SW) {-> T1};";
    script +="INT i = 0;";
    script +="T1{COUNT(PERIOD, i)};";
    script +="Document{(i>0)-> T2};";
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
  }
}
