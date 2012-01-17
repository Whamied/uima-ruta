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

package org.apache.uima.textmarker.ide.debug.ui.interpreters;

import org.eclipse.dltk.debug.ui.launchConfigurations.IMainLaunchConfigurationTab;
import org.eclipse.dltk.debug.ui.launchConfigurations.InterpreterTab;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.IInterpreterComboBlockContext;

public class TextMarkerInterpreterTab extends InterpreterTab {

   public TextMarkerInterpreterTab(IMainLaunchConfigurationTab mainTab) {
    super(mainTab);
  }

  @Override
  protected AbstractInterpreterComboBlock createInterpreterBlock(
      IInterpreterComboBlockContext context) {
    return new TextMarkerInterpreterComboBlock(context);
  }

  @Override
  protected void refreshInterpreters() {
//    ((TextMarkerInterpreterComboBlock) fInterpreterBlock)
//        .initialize(getScriptProject());
    super.refreshInterpreters();
  }
}
