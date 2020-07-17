package br.usp.InterpretadorMIPS.execucao;
import br.usp.InterpretadorMIPS.componentes_cpu.AC;
import br.usp.InterpretadorMIPS.componentes_cpu.IR;
import br.usp.InterpretadorMIPS.componentes_cpu.ItemCPU;
import br.usp.InterpretadorMIPS.componentes_cpu.MAR;
import br.usp.InterpretadorMIPS.componentes_cpu.MBR;
import br.usp.InterpretadorMIPS.componentes_cpu.Memoria;
import br.usp.InterpretadorMIPS.componentes_cpu.PC;
import br.usp.InterpretadorMIPS.componentes_cpu.S1;
import br.usp.InterpretadorMIPS.componentes_cpu.S2;
import br.usp.InterpretadorMIPS.componentes_cpu.S3;
import br.usp.InterpretadorMIPS.componentes_cpu.S4;
import br.usp.InterpretadorMIPS.componentes_cpu.ULA;
import br.usp.InterpretadorMIPS.componentes_cpu.X;

public class CPU {

	private S1 s1;
	private S2 s2;
	private S3 s3;
	private S4 s4;

	private PC pc;
	private MAR mar;
	private MBR mbr;
	private IR ir;
	private AC ac;
	private ULA ula;
	private X x;
	private Memoria memoria;
	
	public CPU() {

		this.s1 = new S1();
		this.s2 = new S2();
		this.s3 = new S3();
		this.s4 = new S4();
		this.pc = new PC();
		this.mar = new MAR();
		this.mbr = new MBR();
		this.ir = new IR();
		this.ac = new AC();
		this.ula = new ULA();
		this.x = new X();
		this.memoria = new Memoria();

	}

	public S1 getS1() {
		return s1;
	}

	public S2 getS2() {
		return s2;
	}

	public S3 getS3() {
		return s3;
	}

	public S4 getS4() {
		return s4;
	}

	public PC getPc() {
		return pc;
	}

	public MBR getMbr() {
		return mbr;
	}

	public IR getIr() {
		return ir;
	}

	public AC getAc() {
		return ac;
	}

	public ULA getUla() {
		return ula;
	}

	public X getX() {
		return x;
	}
	
	public Memoria getMemoria() {
		return memoria;
	}

	public ItemCPU[] getComponentesCPU() {
		ItemCPU[] componentesCPU =
			{pc, pc, mar, mbr, mbr, s1, s1, s2, s2, s3, s3, s4, s4, ac, ula, x, ir, ir, ir, ir, ir, ir, ir, mar, mbr, mbr, memoria, memoria};
		return componentesCPU;

	}

}
