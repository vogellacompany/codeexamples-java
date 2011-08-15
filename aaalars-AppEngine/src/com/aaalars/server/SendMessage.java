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

import com.google.android.c2dm.server.C2DMessaging;
import com.google.android.c2dm.server.PMF;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletContext;

/**
 * Send a message using C2DM.
 */
public class SendMessage {

  private static final Logger log = Logger.getLogger(SendMessage.class.getName());

  public static String sendMessage(ServletContext context, String recipient, String message) {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    try {
      UserService userService = UserServiceFactory.getUserService();
      User user = userService.getCurrentUser();
      String sender = "nobody";
      if (user != null) {
        sender = user.getEmail();
      }
      log.info("sendMessage: sender = " + sender);
      log.info("sendMessage: recipient = " + recipient);
      log.info("sendMessage: message = " + message);

      // ok = we sent to at least one device.
      boolean ok = false;

      // Send push message to phone
      C2DMessaging push = C2DMessaging.get(context);
      boolean res = false;

      String collapseKey = "" + message.hashCode();

      // delete will fail if the pm is different than the one used to
      // load the object - we must close the object when we're done

      List<DeviceInfo> registrations = null;
      registrations = DeviceInfo.getDeviceInfoForUser(recipient);
      log.info("sendMessage: got " + registrations.size() + " registrations");

      // Deal with upgrades and multi-device:
      // If user has one device with an old version and few new ones -
      // the old registration will be deleted.
      if (registrations.size() > 1) {
        // Make sure there is no 'bare' registration
        // Keys are sorted - check the first
        DeviceInfo first = registrations.get(0);
        Key oldKey = first.getKey();
        if (oldKey.toString().indexOf("#") < 0) {
          // multiple devices, first is old-style.
          registrations.remove(0); // don't send to it
          pm.deletePersistent(first);
        }
      }

      int numSendAttempts = 0;
      for (DeviceInfo deviceInfo : registrations) {
        if (!"ac2dm".equals(deviceInfo.getType())) {
          continue; // user-specified device type
        }

        res = doSendViaC2dm(message, sender, push, collapseKey, deviceInfo);
        numSendAttempts++;

        if (res) {
          ok = true;
        }
      }

      if (ok) {
        return "Success: Message sent";
      } else if (numSendAttempts == 0) {
        return "Failure: User " + recipient + " not registered";
      } else {
        return "Failure: Unable to send message";
      }
    } catch (Exception e) {
      return "Failure: Got exception " + e;
    } finally {
      pm.close();
    }
  }

  private static boolean doSendViaC2dm(String message, String sender, C2DMessaging push,
      String collapseKey, DeviceInfo deviceInfo) {
    // Trim message if needed.
    if (message.length() > 1000) {
      message = message.substring(0, 1000) + "[...]";
    }

    return push.sendNoRetry(deviceInfo.getDeviceRegistrationID(), collapseKey, "sender", sender,
        "message", message);
  }
}
