package de.vogella.webservice.soap.axis2;

import java.rmi.RemoteException;

import de.vogella.webservice.soap.axis2.RandomNumberStub.GetNumberResponse;

public class ServiceTest {
	public static void main(String[] args) throws RemoteException   {
		RandomNumberStub stub = new RandomNumberStub();
		GetNumberResponse res = stub.getNumber();
        System.out.println("Counter: "+res.get_return());
	}
}
