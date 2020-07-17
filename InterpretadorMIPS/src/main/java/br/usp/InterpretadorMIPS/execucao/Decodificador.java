package br.usp.InterpretadorMIPS.execucao;

import br.usp.InterpretadorMIPS.componentes_cpu.ItemCPU;

public class Decodificador {
	
	private static String semDecodificacao= "0000";
	private static String decodificarPrimeiro = "0001";
	private static String decodificarPrimeiroSegundo = "0010";
	private static String decoficicarTodos = "0011";
	private static String decodificarPrimeiroSaida = "0101";
	private static String decodificarSegundoSaida = "0110";
	private static String decodificarTerceiroSaida = "0111";
	private static String decodificarEndereco = "1000";
	
	/*	
	 *  0					: 000
	 *  2 - 1�				: 001
	 *  2 - 1� e 2�			: 010
	 *  3 - 1� e 2� 		: 011	
	 *  3 - 1�, 2� e 3� 	: 100
	 *  OPCODE				: 111
	 *  
	 * 
	 */
	
	public static String decodificar(String codigo, CPU cpu) {
		
		String codigoDecodificacao = codigo.substring(28, 32);
		
		StringBuilder cdg = new StringBuilder(codigo); 
		
		if (codigoDecodificacao.equals(semDecodificacao)) {
			//\O/
		} else if (codigoDecodificacao.equals(decodificarPrimeiro)) {
			cdg.setCharAt(retornarRegistrador(cpu, 1).obterPortaEntrada().get(0)-1, '1');
		} else if (codigoDecodificacao.equals(decodificarPrimeiroSegundo)) {
			cdg.setCharAt(retornarRegistrador(cpu, 1).obterPortaEntrada().get(0)-1, '1');
			cdg.setCharAt(retornarRegistrador(cpu, 2).obterPortaSaida().get(0)-1, '1');

		} else if (codigoDecodificacao.equals(decoficicarTodos)) {
			cdg.setCharAt(retornarRegistrador(cpu, 1).obterPortaEntrada().get(0)-1, '1');
			cdg.setCharAt(retornarRegistrador(cpu, 2).obterPortaSaida().get(0)-1, '1');
			cdg.setCharAt(retornarRegistrador(cpu, 3).obterPortaSaida().get(0)-1, '1');

		} else if (codigoDecodificacao.equals(decodificarPrimeiroSaida)) {
			cdg.setCharAt(retornarRegistrador(cpu, 1).obterPortaSaida().get(0)-1, '1');
		} else if (codigoDecodificacao.equals(decodificarSegundoSaida)) {
			cdg.setCharAt(retornarRegistrador(cpu, 2).obterPortaSaida().get(0)-1, '1');
		} else if (codigoDecodificacao.equals(decodificarTerceiroSaida)) {
			cdg.setCharAt(retornarRegistrador(cpu, 3).obterPortaSaida().get(0)-1, '1');
		} else if (codigoDecodificacao.equals(decodificarEndereco)) {
			cdg = encontrarEndereco(codigo, cpu);
		}		
		return cdg.toString();
		
	}
	
	private static StringBuilder encontrarEndereco(String codigo, CPU cpu) {
		String opcode = cpu.getIr().obterConteudo(-1);
		StringBuilder cdg = new StringBuilder(codigo); 
		System.out.println(opcode);
		switch (opcode) {
		case "00001" :
			return cdg.replace(40, 46, "000101"); //5
		case "00010" :
			return cdg.replace(40, 46, "000110");//6 
		case "00011" :
			return cdg.replace(40, 46, "001010"); //10
		case "00100" : 
			return cdg.replace(40, 46, "001101"); //13
		case "00101" : 
			return cdg.replace(40, 46, "001110"); //14
		case "00110" : 
			return cdg.replace(40, 46, "010001"); //17
		case "00111" : 
			return cdg.replace(40, 46, "010100"); //20
		case "01000" : 
			return cdg.replace(40, 46, "010111"); //23
		case "01001" : 
			return cdg.replace(40, 46, "011010"); //26
		case "01010" : 
			return cdg.replace(40, 46, "011110"); //30
		case "01011" : 
			return cdg.replace(40, 46, "100010"); //34
		case "01100" : 
			return cdg.replace(40, 46, "100011"); //35
		case "01101" : 
			return cdg.replace(40, 46, "101000"); //40
		case "01110" : 
			return cdg.replace(40, 46, "101101"); //45
		default: 
			System.exit(0);
			}
		return cdg;
	}

	public static ItemCPU retornarRegistrador(CPU cpu, int pos) {		
		
		String codigo = cpu.getIr().obterConteudo(pos);

		switch (codigo) {
		case "000000001" :
			return cpu.getS1();
		case "000000010" :
			return cpu.getS2();
		case "000000011" :
			return cpu.getS3();
		case "000000100" : 
			return cpu.getS4();
		default: 
			return null;
		}
	}
		
	
	
}
