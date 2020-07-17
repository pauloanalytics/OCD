package br.usp.InterpretadorMIPS.execucao;
import java.util.LinkedList;
import java.util.List;

import br.usp.InterpretadorMIPS.componentes_cpu.IR;
import br.usp.InterpretadorMIPS.componentes_cpu.ItemCPU;
import br.usp.InterpretadorMIPS.componentes_cpu.Memoria;

public class UC {
	
	private String CBR;
	private int CAR;
	
	private CPU CPU;
	private ItemCPU[] componentesCPU;
	
	private String valorBarramentoInterno;
	private String valorBarramentoExterno;
	
	private List<ItemCPU> componentesInternos;
	private List<ItemCPU> componentesExternos;
	
	public UC(CPU CPU) {
		this.CPU = CPU;
		this.CAR = 0;
		this.valorBarramentoExterno = "00000000000000000000000000000000";
		this.valorBarramentoInterno = "00000000000000000000000000000000";
		this.componentesCPU = CPU.getComponentesCPU();
		this.componentesExternos = new LinkedList<ItemCPU>();
		this.componentesInternos = new LinkedList<ItemCPU>();
	}
	
	
	public void processar() {
		
		if (CAR > 4) {
			System.out.println("Ciclo de Execu��o\n\n"+Grafico.instrucoesAlfanumericas.get(CPU.getIr().obterConteudo())+"   "+CPU.getIr().obterConteudo());
		} else {
			System.out.println("Ciclo de Busca\n");
		}
		
		CBR = Microprograma.obterInstrucao(CAR);		
		CBR = Decodificador.decodificar(CBR, CPU);

		System.out.println("\nCBR:"+CBR+"\n");

		this.componentesExternos.clear();
		this.componentesInternos.clear();
				
		Grafico.imprimirInstucao(Grafico.comandosAlfanumericos[CAR], CBR);
		
		ItemCPU componenteAux;
		
		for (int i = 0; i < CBR.length(); i++) {
			if (CBR.charAt(i) == '1' && Integer.valueOf(i) < componentesCPU.length ) {
				componenteAux = componentesCPU[Integer.valueOf(i)];
				adicionarAoBarramento(componenteAux, i);
			}
		}
			
		atualizarValores();
		
		processarULA();
		
		calcularEndereco();
		
		Grafico.imprimir(CPU, this);
		
		System.out.println("\nCAR:"+Integer.toBinaryString(CAR)+"\n");

		
	}
	
	private void atualizarValores() {
		
		for (ItemCPU i : componentesInternos)
			i.atualizarConteudo(valorBarramentoInterno);
		
		for (ItemCPU i : componentesExternos) {
			if (i instanceof Memoria) {
				Memoria auxM = (Memoria) i;
				if (CBR.charAt(35) == '1') {
					auxM.lerMemoria(valorBarramentoExterno);
				} else if (CBR.charAt(36) == '1') {
					auxM.escreverNaMemoria(valorBarramentoExterno);
				}
			} else {
				i.atualizarConteudo(valorBarramentoExterno);
			}
		}
	}


	private void processarULA() {
		if(CBR.substring(31, 35).contains("1")) {
			CPU.getUla().operacao(CBR, CPU);
			if(CPU.getUla().getOF() == 1) {
				System.out.println("OVERFLOW!!!!!!!");
				System.exit(0);

		}
		}
	}


	private void calcularEndereco() {
		
		if (CBR.substring(38, 40).contains("1")) {
			
			String codigoJump = CBR.substring(38, 40);
			
			if (codigoJump.equals(Codigos.BNE) && CPU.getUla().getZF() == 1) {
					CAR = Integer.valueOf(CBR.substring(40, CBR.length()), 2);
			} else if (codigoJump.equals(Codigos.BEQ) && CPU.getUla().getZF() == 0) {				
					CAR = Integer.valueOf(CBR.substring(40, CBR.length()), 2);
			} else if (codigoJump.equals(Codigos.J)) {
				CAR = Integer.valueOf(CBR.substring(40, CBR.length()), 2);
			} else {
				CAR = CAR+1;
			}
			
		} else {
			
			CAR = CAR+1;
		}
		
	}


	public void adicionarAoBarramento(ItemCPU componente, int indice) {
		indice = indice + 1;
		if (componente.obterPortaEntrada().contains(indice)) {
			
			if (pertenceAoBarramentoExterno(indice)) {
				
				componentesExternos.add(componente);
			
			} else {
			
				componentesInternos.add(componente);

			}
			
		} else {
			
			if (pertenceAoBarramentoExterno(indice)) {
				
					valorBarramentoExterno = componente.obterConteudo();
			} else {
				if (componente instanceof IR) {
					IR ir = (IR) componente;
					valorBarramentoInterno = ir.obterConteudo(indice);
				} else {
					valorBarramentoInterno = componente.obterConteudo();
				}
			}
			
		}
	}
	
	
	
	private boolean pertenceAoBarramentoExterno(int indice) {
		// TODO Auto-generated method stub
		return indice > 23;
	}


	public String getValorBarramentoInterno() {
		return valorBarramentoInterno;
	}


	public String getValorBarramentoExterno() {
		return valorBarramentoExterno;
	}
	
	public String getCBR() {
		return CBR;
	}
	
	
	
	
	
}
