package br.usp.InterpretadorMIPS.componentes_cpu;


import java.util.Arrays;
import java.util.List;

public class S1 extends ItemCPU {

	List<Integer> portasEntrada = Arrays.asList(6);
	List<Integer> portasSaida = Arrays.asList(7);
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

}
