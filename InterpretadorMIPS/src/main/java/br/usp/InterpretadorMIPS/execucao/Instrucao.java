package br.usp.InterpretadorMIPS.execucao;

import br.usp.InterpretadorMIPS.componentes_cpu.Memoria;

public class Instrucao {
	
	public String instrucaoAN;
	
	public String instrucao = "";
	
	private boolean isDataSegment;
	
	private Memoria memoria;
	
	public void montarInstrucao(String instrucao, Memoria memoria) {
		this.memoria = memoria;
		this.instrucaoAN = instrucao;
		
		if(instrucao.contains(".data")) {
			isDataSegment = true;
			return;
		} else if (instrucao.contains(".text")) {
			isDataSegment = false;
			return;
		}
		
		if (isDataSegment) {
			obterInstrucaoDataSegment();
		} else {
			obterInstrucaoTextSegment();
		}
		
		
		
		
		
//		return this.instrucao;
	}
	
	private void obterInstrucaoTextSegment() {
				
		String operacao = instrucaoAN.substring(0, instrucaoAN.indexOf(" ")).trim();
		
		String primeiroOperador = "";
		String segundoOperador = "";
		String terceiroOperador = "";
		
		int contagem = (int) instrucaoAN.chars().filter(ch -> ch == ',').count();	
		
		switch (contagem) {
			case 0 : {
				primeiroOperador = instrucaoAN.substring(instrucaoAN.indexOf(" ")+1, instrucaoAN.length());
				break;
			}
			case 1 : {
				if (!instrucaoAN.contains("(")) {
					primeiroOperador = instrucaoAN.substring(instrucaoAN.indexOf(" ")+1, instrucaoAN.indexOf(","));
					segundoOperador = instrucaoAN.substring(instrucaoAN.indexOf(",")+1, instrucaoAN.length());
				} else {
					primeiroOperador = instrucaoAN.substring(instrucaoAN.indexOf(" ")+1, instrucaoAN.indexOf(","));
					segundoOperador = instrucaoAN.substring(instrucaoAN.indexOf(",")+1, instrucaoAN.indexOf("("));
					terceiroOperador = instrucaoAN.substring(instrucaoAN.indexOf("(")+1, instrucaoAN.indexOf(")"));
				}
				break;
			}
			case 2 : {
				primeiroOperador = instrucaoAN.substring(instrucaoAN.indexOf(" ")+1, instrucaoAN.indexOf(","));
				segundoOperador = instrucaoAN.substring(instrucaoAN.indexOf(",")+1, instrucaoAN.indexOf(",", instrucaoAN.indexOf(",")+1));
				terceiroOperador = instrucaoAN.substring(instrucaoAN.indexOf(",", instrucaoAN.indexOf(",")+1)+1, instrucaoAN.length());
				break;
			}
		}
		
		
		switch (operacao) {
		case "li":
			instrucao = "00001";
			break;
		case "lw":
			if (instrucaoAN.contains("(")) {
				instrucao = "01110";
			} else {
				instrucao = "00010";
			}
			break;
		case "sw":
			if (instrucaoAN.contains("(")) {
				instrucao = "01101";
			} else {
				instrucao = "00011";
			}
			break;
		case "move":
			instrucao = "00100";
			break;
		case "add":
			if (instrucaoAN.chars().filter(ch -> ch == '$').count() == 3) {
				instrucao = "00101";
			} else {
				instrucao = "00110";
			}
			break;
		case "sub":
			if (instrucaoAN.chars().filter(ch -> ch == '$').count() == 3) {
				instrucao = "00111";
			} else {
				instrucao = "01000";
			}
			break;
		case "beq":
			instrucao = "01001";
			break;
		case "bne":
			instrucao = "01010";
			break;
		case "j":
			instrucao = "01011";
			break;
		case "slt":
			instrucao = "01100";
			break;			
		default:
			throw new IllegalArgumentException("Unexpected value: " + instrucaoAN);
		}
		
		obterOperador(primeiroOperador);
		if (!segundoOperador.equalsIgnoreCase("")) obterOperador(segundoOperador);
		if (!terceiroOperador.equalsIgnoreCase("")) obterOperador(terceiroOperador);
		
		memoria.adicionarNaMemoria(instrucao);
		Grafico.instrucoesAlfanumericas.put(instrucao, instrucaoAN);

	}
	
	public void obterOperador(String operador) {
		
		String registrador = "";
		
		if (!operador.contains("$s") && !(operador.trim().matches("[0-9]+"))) {
			int enderecoArray = memoria.obterEnderecoArray(operador);
			registrador = Integer.toBinaryString(enderecoArray);
			if (registrador.length() < 9) {
				String zeros = Util.getZeros(9, registrador);
				registrador = zeros+registrador;
			}
		} else {
			switch (operador.trim()) {
			case "$s1" :
				registrador = "000000001";
				break;
			case "$s2" : 
				registrador = "000000010";
				break;
			case "$s3" : 
				registrador = "000000011";
				break;
			case "$s4" : 
				registrador = "000000100";
				break;
			default: 
				registrador = Integer.toBinaryString(Integer.valueOf(operador.trim()));
				if (registrador.length() < 9) {
					String zeros = Util.getZeros(9, registrador);
					registrador = zeros+registrador;
				}
			}
		}
		
		
		
		instrucao = instrucao.concat(registrador);
	}
	
	public void obterInstrucaoDataSegment() {
		boolean isFirst = true;
		String nomeArray = instrucaoAN.substring(0, instrucaoAN.indexOf(":"));
		String valoresArray = instrucaoAN.substring(instrucaoAN.indexOf(" ",instrucaoAN.indexOf(" ")+1), instrucaoAN.length());
		String[] valoresParaArmazenamento = valoresArray.split(",");
		for (String valor : valoresParaArmazenamento) {
			
			valor = Integer.toBinaryString(Integer.valueOf(valor.trim()));
			
			if (valor.length() < 9) {
				String zeros = Util.getZeros(9, valor);
				valor = zeros+valor;
			}
			
			if (isFirst) {
				memoria.adicionarNoDataSegment(nomeArray, valor);
				isFirst = false;
			} else {
				memoria.adicionarNoDataSegment("", valor);
			}
		}
		
		
	}
	
	
	
}
