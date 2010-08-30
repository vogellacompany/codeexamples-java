package calculator;

import javax.jws.WebService;

@WebService
public class Multiply {
	public int multiply (int a, int b){
		return a*b;
	}
}
