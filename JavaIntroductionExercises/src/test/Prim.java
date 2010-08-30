package test;

public class Prim {
	public boolean isPrim(int zahl) {
		boolean primzahl = true;
		// 0 und 1 sind keine Primzahlen
		if (zahl > 1)
			// Prüfe jede Zahl zwischen 2 und der eingegebenen Zahl
			for (int i = 2; i < zahl; i++) {
				// Wenn die eingegebe Zahl durch die aktuelle Zahl restlos
				// teilbar ist,
				// setze das Flag und breche die Schleife ab.
				if ((zahl % i) == 0) {
					primzahl = false;
					break; // Bricht die Schleife ab.
				}
			}// for(int i = 1; i < zahl; i++)
		return primzahl;
	}

	public static void main(String[] args) {
		Prim test = new Prim();
		int sum = 0;
		for (int i = 0; i <= 100; i++) {
			if (test.isPrim(i)) {
				sum++;
			}
		}
		System.out.println(sum);
	}
}
