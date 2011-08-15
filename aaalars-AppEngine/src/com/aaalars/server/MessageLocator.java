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

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

public class MessageLocator extends Locator<Message, Void> {
  
  @Override
  public Message create(Class<? extends Message> clazz) {
    return new Message(RequestFactoryServlet.getThreadLocalRequest().getSession().getServletContext());
  }

  @Override
  public Message find(Class<? extends Message> clazz, Void id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Class<Message> getDomainType() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Void getId(Message domainObject) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Class<Void> getIdType() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object getVersion(Message domainObject) {
    throw new UnsupportedOperationException();
  }
}
