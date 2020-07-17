package br.usp.InterpretadorMIPS.componentes_cpu;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Memoria extends ItemCPU {
	
	public Map<Integer, String> memoria = new HashMap<Integer, String>();
	int ultimoEnderecoLido = 0;
	String sinal = "";
	
	List<Integer> portasEntrada = Arrays.asList(27);
	List<Integer> portasSaida = Arrays.asList(28);
	String conteudo = "00000000000000000000000000000000";
	
	int ultimoEnderecoDataSegment = 200;
	
	
	int enderecoUsado = 1;
	
	Map<String, Integer> enderecoArrays = new HashMap<>();
	
	
	@Override
	public List<Integer> obterPortaEntrada() {
		return portasEntrada;
	}

	@Override
	public List<Integer> obterPortaSaida() {
		return portasSaida;
	}

	@Override
	public void atualizarConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public String obterConteudo() {
		String x = memoria.get(ultimoEnderecoLido);
		if (x == null) {
			memoria.put(ultimoEnderecoLido, "00000000000000000000000000000000");
		}
		return memoria.get(ultimoEnderecoLido);
	}
	
	public void lerMemoria(String endereco) { 
		this.ultimoEnderecoLido = Integer.valueOf(endereco, 2);
		sinal = "RD e AV";
	}
	
	public void escreverNaMemoria(String conteudo) {
		memoria.put(ultimoEnderecoLido, conteudo);
		sinal = "WT";

	}
	
	public void adicionarNaMemoria(String conteudo) {
		memoria.put(enderecoUsado, conteudo);
		enderecoUsado++;
	}
	
	public void adicionarNoDataSegment(String nomeArray, String conteudo) {
		memoria.put(ultimoEnderecoDataSegment, conteudo);
		if(!nomeArray.equals(""))
			enderecoArrays.put(nomeArray.trim(), ultimoEnderecoDataSegment);
		ultimoEnderecoDataSegment++;
	}
	
	public int obterEnderecoArray(String nomeArray) {
		return enderecoArrays.get(nomeArray.trim());
	}

}
