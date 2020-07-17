package br.usp.InterpretadorMIPS.componentes_cpu;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IR extends ItemCPU {
	
	List<Integer> portasEntrada = Arrays.asList(17, 19, 21, 23);
	List<Integer> portasSaida = Arrays.asList(15, 20, 22);
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

	public String obterConteudo(int indice) {
		if (indice == 18 || indice == 1) { 
			return conteudo.substring(5, 14);
		} else if (indice == 20 || indice == 2) {
			return conteudo.substring(14, 23);
		} else if (indice == 22 || indice == 3){
			return conteudo.substring(23, 32);
		} 
		
		return conteudo.substring(0, 5);
	}

}
