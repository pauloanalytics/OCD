package br.usp.InterpretadorMIPS.execucao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import br.usp.InterpretadorMIPS.componentes_cpu.ItemCPU;
import br.usp.InterpretadorMIPS.componentes_cpu.Memoria;

public class Grafico {
	
	public static String[] comandosAlfanumericos = 
		{"PC > MAR & PC > ULA", 
		"MAR > MEM�RIA & AC >PC", 
		"MEM > MBR", 
		"MBR > IR", 
		"Instru��o de Encaminhamento", 
		"IR2 > RR", 
		"IR2 > MAR", 
		"MAR > MEM READ E AV", 
		"MEM > MBR", 
		"MBR > RR", 
		"IR2 > MAR", 
		"MAR > MEM & RD -> MBR READ E AV", 
		"MBR > MEM WT", 
		"RD > RR",
		"RD > X", 
		"RD > ULA", 
		"AC  > RR", 
		"RD > X", 
		"IR3 > ULA", 
		"AC > RR",		
		"RD > X", 
		"RD > ULA", 
		"AC  > RR", 
		"RD > X", 
		"IR3 > ULA", 
		"AC > RR",	
		"RD > X",
		"RD > ULA",
		"Testando JUMP",
		"IR3 > PC",
		"RD > X",
		"RD > ULA",
		"Testando JUMP",
		"IR3 > PC",
		"IR1 > PC",
		"RD > X",
		"RD > ULA",
		"AC > RR",
		"PC > ULA",
		"AC > PC",
		"IR2 > X",
		"RD > ULA",
		"AC > MAR",
		"MAR > MEM�RIA READ E AV & RD > MBR",
		"MBR > MEM�RIA WT",
		"IR2 > X",
		"RD > ULA",
		"AC > MAR",
		"MAR > MEM�RIA READ E AV",
		"MEM�RIA > MBR",
		"MBR > RR"
};
	public static Map<String, String> instrucoesAlfanumericas = new HashMap<String, String>();
	
	public static void imprimir(CPU cPU, UC uc) {

		Set<ItemCPU> uniqueItens = new HashSet<ItemCPU>(Arrays.asList(cPU.getComponentesCPU()));
		Map<String, String> map = new HashMap<>();
		for (ItemCPU icpu : uniqueItens) {
			if (!(icpu instanceof Memoria))
				map.put((icpu.toString().substring(icpu.toString().indexOf(".")+1, icpu.toString().indexOf("@"))), icpu.obterConteudo());
		}
        Map<String, String> treeMap = new TreeMap<String, String>(map);
        for (String chave : treeMap.keySet()) {
        		
        	System.out.println(chave + " : "+ valorFormatado(treeMap.get(chave)));
        }
    	System.out.println("Barramento Interno:"+uc.getValorBarramentoInterno());
    	System.out.println("Barramento Externo:"+uc.getValorBarramentoExterno());
    	System.out.println("Flags ULA: ZF:"+cPU.getUla().getZF()+" OF:"+cPU.getUla().getOF()+" SF:"+
    	cPU.getUla().getSF());

    	
    	
    	System.out.println("\n\nMEM�RIA\n\n");
    	
    	for (Integer chave : cPU.getMemoria().memoria.keySet()) {
    		System.out.println(chave+" : "+valorFormatado(cPU.getMemoria().memoria.get(chave)));
    	}

	}
		
	public static void imprimirInstucao(String instrucao, String CBR) {
    	if (instrucao.contains("RR"))
    		instrucao = substituirRegistrador(instrucao, 'R', CBR);
    	if (instrucao.contains("RD")) 
    		instrucao = substituirRegistrador(instrucao, 'D', CBR);
    	System.out.println(instrucao+"\n");
	}
	private static String substituirRegistrador(String chave, char c, String CBR) {
		
		if (c == 'R') {
			if (CBR.charAt(5) == '1') {
				chave = chave.replace("RR", "$s1");
			} else if (CBR.charAt(7) == '1') {
				chave = chave.replace("RR", "$s2");
			} else if (CBR.charAt(9) == '1') {
				chave = chave.replace("RR", "$s3");
			} else {
				chave = chave.replace("RR", "$s4");
			}
		} else {
			
			if (CBR.charAt(6) == '1') {
				chave = chave.replace("RD", "$s1");
			} else if (CBR.charAt(8) == '1') {
				chave = chave.replace("RD", "$s2");
			} else if (CBR.charAt(10) == '1') {
				chave = chave.replace("RD", "$s3");
			} else {
				chave = chave.replace("RD", "$s4");
			}
		}
		return chave;
	}
	
	public static String valorFormatado(String valor) { 
		String zeros = Util.getZeros(32, valor);
		
		return (zeros+valor);
	}

}
