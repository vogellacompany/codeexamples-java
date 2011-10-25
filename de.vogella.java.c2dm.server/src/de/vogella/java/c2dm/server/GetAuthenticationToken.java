package de.vogella.java.c2dm.server;

import java.io.IOException;

import de.vogella.java.c2dm.server.secret.SecureStorage;
import de.vogella.java.c2dm.server.util.AuthenticationUtil;

public class GetAuthenticationToken {

	public static void main(String[] args) throws IOException {
		String token = AuthenticationUtil.getToken(SecureStorage.USER,
				SecureStorage.PASSWORD);
		System.out.println(token);
	}
}
