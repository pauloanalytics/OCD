package br.usp.InterpretadorMIPS.execucao;

public class Util {

	public static String getZeros(int n, String number) {
		String zeros = "";
		for (int i = 0; i < (n - number.length()); i++) {
			zeros = zeros.concat("0");
		}
		
		return zeros;
	}
	
}
