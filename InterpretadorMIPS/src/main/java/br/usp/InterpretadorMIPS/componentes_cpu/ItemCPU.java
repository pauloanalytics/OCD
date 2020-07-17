package br.usp.InterpretadorMIPS.componentes_cpu;


import java.util.List;

public abstract class ItemCPU {
	
	public abstract List<Integer> obterPortaEntrada();
	public abstract List<Integer> obterPortaSaida();
	public abstract String obterConteudo();
	public abstract void atualizarConteudo(String conteudo);

	

}
