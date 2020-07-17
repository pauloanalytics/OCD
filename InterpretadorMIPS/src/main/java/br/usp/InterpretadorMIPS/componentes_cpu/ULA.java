package br.usp.InterpretadorMIPS.componentes_cpu;


import java.util.Arrays;
import java.util.List;

import br.usp.InterpretadorMIPS.execucao.CPU;
import br.usp.InterpretadorMIPS.execucao.Util;

public class ULA extends ItemCPU {
	
	List<Integer> portasEntrada = Arrays.asList(15);
	List<Integer> portasSaida = Arrays.asList(-1);
	
	String conteudo = "00000000000000000000000000000000";
	
	
	@Override
	public List<Integer> obterPortaEntrada() {
		return portasEntrada;
	}

	@Override
	public List<Integer> obterPortaSaida() {
		return portasSaida;
	}

	@Override
	public String obterConteudo() {
		return conteudo;
	}

	@Override
	public void atualizarConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	/*
	 * 
	 * N/A: 000
	 * INC: 001
	 * ADD: 010
	 * SUB: 011
	 * SLT: 100
	 * 
	 */
	int ZF = 0;
	int OF = 0;
	int SF = 0;
	
	
	public void operacao(String CBR, CPU CPU) {
		String codigo = CBR.substring(32, 35);
		int operando1 = Integer.parseInt(CPU.getX().obterConteudo(), 2);
		int operando2 = Integer.parseInt(this.conteudo, 2);
		int resultado = 0;
		switch (codigo) {
		case "001" :
			resultado = operando2 +1;
			break;
		case "010" :
			resultado = operando1+operando2;
			break;
		case "011" :
			resultado = operando1-operando2;
			break;
		case "100" :
			resultado = operando1-operando2;
			break;
		}
		
		if (!codigo.equals("001")) { 
			
			if (resultado == 0) ZF = 1;
			if (resultado < 0) SF = 1;

		}
		
		if (codigo.equals("100")) { 
			
			if (resultado >= 0) 
				resultado = 0;
			if (resultado < 0) 
				resultado = 1;

		}
				
		String r = Integer.toBinaryString(resultado);
		if (r.length() > 32) OF = 1;
		
		String zeros = Util.getZeros(32, r);
		
		
		CPU.getAc().atualizarConteudo(zeros.concat(r));
		
	}

	public int getZF() {
		return ZF;
	}

	public void setZF(int zF) {
		ZF = zF;
	}

	public int getOF() {
		return OF;
	}

	public void setOF(int oF) {
		OF = oF;
	}

	public int getSF() {
		return SF;
	}

	public void setSF(int sF) {
		SF = sF;
	}

}
