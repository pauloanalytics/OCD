package br.usp.InterpretadorMIPS;

import java.io.IOException;
import java.util.Scanner;

import br.usp.InterpretadorMIPS.execucao.CPU;
import br.usp.InterpretadorMIPS.execucao.Reader;
import br.usp.InterpretadorMIPS.execucao.UC;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		CPU cpu = new CPU();
		UC uc = new UC(cpu);
		Reader reader = new Reader();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira o caminho do diret�rio:");
		String caminho = scanner.nextLine();
		reader.lerArquivo(caminho, cpu.getMemoria());
		System.out.println("Pressione ENTER");
		String next = scanner.nextLine();

		while (next.isEmpty()) { 
			uc.processar();
			next = scanner.nextLine();
		}
		
		boolean a = true;
//		reader.lerArquivo("C:\\Users\\leoco\\Desktop\\teste.txt", cpu.getMemoria());
	}

}
