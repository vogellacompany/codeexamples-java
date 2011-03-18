package de.vogella.gae.java.c2dm.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class RegisteredDevice {
	@Id
	private String deviceId;
	private String registrationId;
	
	public RegisteredDevice(String deviceId, String registrationId) {
		this.deviceId = deviceId;
		this.registrationId = registrationId;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
