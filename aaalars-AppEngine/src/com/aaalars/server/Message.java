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

import java.util.logging.Logger;

import javax.servlet.ServletContext;

public class Message {

  private static final Logger log = Logger.getLogger(Message.class.getName());

  private final ServletContext context;

  String recipient;

  String message;

  public Message(ServletContext context) {
    this.context = context;
  }

  public String getRecipient() {
    return recipient;
  }

  public String getMessage() {
    return message;
  }

  public String send() {
    log.info("send " + this);
    try {
      return SendMessage.sendMessage(context, recipient, message);
    } catch (Exception e) {
      return "Failure: Got exception in send: " + e.getMessage();
    }
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Message [recipient=" + recipient + ", message=" + message + "]";
  }
}
