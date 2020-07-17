package br.usp.InterpretadorMIPS.execucao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import br.usp.InterpretadorMIPS.componentes_cpu.Memoria;

public class Reader {
	
	Instrucao it = new Instrucao();
	
	public void lerArquivo(String arquivo, Memoria memoria) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
		String linha;
		while(((linha = reader.readLine()) != null)) {
			it.montarInstrucao(linha, memoria);
		}
		
		reader.close();


	}

}
