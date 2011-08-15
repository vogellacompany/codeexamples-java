/*
 * Copyright 2011 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.aaalars.shared;

import com.google.web.bindery.requestfactory.shared.ProxyForName;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

/**
 * A proxy object containing device registration information:
 * email account name, device id, and device registration id.
 */
@ProxyForName("com.aaalars.server.RegistrationInfo")
public interface RegistrationInfoProxy extends ValueProxy {
  String getDeviceId();
  String getDeviceRegistrationId();
  void setDeviceId(String deviceId);
  void setDeviceRegistrationId(String deviceRegistrationId);
}
