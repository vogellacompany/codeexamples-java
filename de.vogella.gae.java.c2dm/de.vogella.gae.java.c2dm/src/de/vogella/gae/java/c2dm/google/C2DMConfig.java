/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.vogella.gae.java.c2dm.google;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * Persistent config info for the server - authentication token
 */
@PersistenceCapable
public final class C2DMConfig {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @SuppressWarnings("unused")
    private Key key;

    @Persistent
    private String authToken;

    public String getAuthToken() {
        return (authToken == null) ? "" : authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    
    public void setKey(Key key) {
        this.key = key;
    }
}