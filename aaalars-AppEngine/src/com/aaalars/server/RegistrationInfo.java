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
package com.aaalars.server;

import com.google.android.c2dm.server.PMF;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

public class RegistrationInfo {

  private static final Logger log = Logger.getLogger(RegistrationInfo.class.getName());

  private static final int MAX_DEVICES = 5;

  String deviceId;

  String deviceRegistrationId;

  public RegistrationInfo() {
  }

  public String getDeviceId() {
    return deviceId;
  }

  public String getDeviceRegistrationId() {
    return deviceRegistrationId;
  }

  public void register() {
    log.info("register " + this);
    try {
      doRegister(getDeviceRegistrationId(), "ac2dm", getDeviceId(), getAccountName());
    } catch (Exception e) {
      log.info("Got exception in registration: " + e + " - " + e.getMessage());
      for (StackTraceElement ste : e.getStackTrace()) {
        log.info(ste.toString());
      }
    }
    log.info("Successfully registered");
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public void setDeviceRegistrationId(String deviceRegistrationId) {
    this.deviceRegistrationId = deviceRegistrationId;
  }

  @Override
  public String toString() {
    return "RegistrationInfo [deviceId=" + deviceId + ", deviceRegistrationId="
        + deviceRegistrationId + "]";
  }

  public void unregister() {
    log.info("unregister " + this);
    try {
      doUnregister(getDeviceRegistrationId(), getAccountName());
    } catch (Exception e) {
      log.info("Got exception in unregistration: " + e + " - " + e.getMessage());
      for (StackTraceElement ste : e.getStackTrace()) {
        log.info(ste.toString());
      }
    }
    log.info("Successfully unregistered");
  }

  private String getAccountName() {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user == null) {
      throw new RuntimeException("No one logged in");
    }
    return user.getEmail();
  }

  private void doRegister(String deviceRegistrationId, String deviceType, String deviceId,
      String accountName) throws Exception {
    log.info("in register: accountName = " + accountName);
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try {
      List<DeviceInfo> registrations = DeviceInfo.getDeviceInfoForUser(accountName);

      log.info("got registrations");
      if (registrations.size() > MAX_DEVICES) {
        log.info("got registrations > MAX_DEVICES");
        // we could return an error - but user can't handle it yet.
        // we can't let it grow out of bounds.
        // TODO: we should also define a 'ping' message and expire/remove
        // unused registrations
        DeviceInfo oldest = registrations.get(0);
        if (oldest.getRegistrationTimestamp() == null) {
          pm.deletePersistent(oldest);
        } else {
          long oldestTime = oldest.getRegistrationTimestamp().getTime();
          for (int i = 1; i < registrations.size(); i++) {
            if (registrations.get(i).getRegistrationTimestamp().getTime() < oldestTime) {
              oldest = registrations.get(i);
              oldestTime = oldest.getRegistrationTimestamp().getTime();
            }
          }
          pm.deletePersistent(oldest);
        }
      }

      // Get device if it already exists, else create
      String suffix =
          (deviceId != null ? "#" + Long.toHexString(Math.abs(deviceId.hashCode())) : "");
      log.info("suffix = " + suffix);
      Key key = KeyFactory.createKey(DeviceInfo.class.getSimpleName(), accountName + suffix);
      log.info("key = " + key);

      DeviceInfo device = null;
      try {
        device = pm.getObjectById(DeviceInfo.class, key);
      } catch (JDOObjectNotFoundException e) {
        log.info("Caught JDOObjectNotFoundException");
      }
      if (device == null) {
        device = new DeviceInfo(key, deviceRegistrationId);
        device.setType(deviceType);
      } else {
        // update registration id
        device.setDeviceRegistrationID(deviceRegistrationId);
        device.setRegistrationTimestamp(new Date());
      }

      pm.makePersistent(device);
      return;
    } catch (Exception e) {
      log.info("Caught exception: " + e);
      throw e;
    } finally {
      pm.close();
    }
  }

  private void doUnregister(String deviceRegistrationID, String accountName) {
    log.info("in unregister: accountName = " + accountName);
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try {
      List<DeviceInfo> registrations = DeviceInfo.getDeviceInfoForUser(accountName);
      for (int i = 0; i < registrations.size(); i++) {
        DeviceInfo deviceInfo = registrations.get(i);
        if (deviceInfo.getDeviceRegistrationID().equals(deviceRegistrationID)) {
          pm.deletePersistent(deviceInfo);
          // Keep looping in case of duplicates
        }
      }
    } catch (JDOObjectNotFoundException e) {
      log.warning("User " + accountName + " unknown");
    } catch (Exception e) {
      log.warning("Error unregistering device: " + e.getMessage());
    } finally {
      pm.close();
    }
  }
}
