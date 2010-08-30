package de.vogella.emf.uuid;

import org.eclipse.emf.ecore.util.EcoreUtil;

public class MakeUniqueIds {
	public static void main(String[] args) {
		for (int i=0; i<100; i++){
			System.out.println(EcoreUtil.generateUUID());
		}
	}
}
