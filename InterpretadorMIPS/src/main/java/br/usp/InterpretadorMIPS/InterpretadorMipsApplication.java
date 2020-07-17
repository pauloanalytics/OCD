package br.usp.InterpretadorMIPS;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.usp.InterpretadorMIPS.execucao.CPU;
import br.usp.InterpretadorMIPS.execucao.Reader;
import br.usp.InterpretadorMIPS.execucao.UC;

@SpringBootApplication
public class InterpretadorMipsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(InterpretadorMipsApplication.class, args);
		
		CPU cpu = new CPU();
		UC uc = new UC(cpu);
		Reader reader = new Reader();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira o caminho do diretorio:");
		String caminho = scanner.nextLine();
		reader.lerArquivo(caminho, cpu.getMemoria());
		System.out.println("Pressione ENTER");
		String next = scanner.nextLine();

		while (next.isEmpty()) { 
			uc.processar();
			next = scanner.nextLine();
		}

		
	}

}
