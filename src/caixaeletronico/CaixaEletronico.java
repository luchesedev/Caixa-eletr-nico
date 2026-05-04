package caixaeletronico;
public class CaixaEletronico  implements ICaixaEletronico {
	private int valor;
    private int[][] notas = new int[][] {
        {100, 100},
        {50, 200},
        {20, 300},
        {10, 350},
        {5, 450},
        {2, 500}
    };
    private int cotaMinima = 20;
    
    public String pegaRelatorioCedulas() {
    	String resposta = ("-").repeat(30)+"Relatório de células"+("-").repeat(30)+"\n";
        //logica de fazer o relatorio de cedulas
    	resposta = notas[0][1] != 0 ? resposta+="Notas de 100: "+notas[0][1]+" notas \n" :resposta+"";
        resposta = notas[1][1] != 0 ? resposta+="Notas de 50: "+notas[1][1]+" notas \n" :resposta+"";
        resposta = notas[2][1] != 0 ? resposta+="Notas de 20: "+notas[2][1]+" notas \n" :resposta+"";
        resposta = notas[3][1] != 0 ? resposta+="Notas de 10: "+notas[3][1]+" notas \n" :resposta+"";
        resposta = notas[4][1] != 0 ? resposta+="Notas de 5: "+notas[4][1]+" notas \n" :resposta+"";
        resposta = notas[5][1] != 0 ? resposta+="Notas de 2: "+notas[5][1]+" notas \n" :resposta+"";

        resposta+= "\n"+("-").repeat(90);
        return resposta;
    }

	public String pegaValorTotalDisponivel() {
	String resposta = "";
	//atributo que vai armazenar a soma total do valor
		double total = 0;
		
	//for para percorrer o array
		for (int i = 0; i < notas.length; i++) {
	//incrementa a multiplicação da quantidade de notas pelo valor no atributo "total"
			total += notas[i][0] * notas [i][1];
		}
		resposta = "Valor total disponível no caixa: R$ " + String.format("%.2f", total);
	return resposta;
	}
	public String reposicaoCedulas(Integer cedula, Integer quantidade) {
	String resposta = "";
	//logica de fazer a reposicao de cedulas e criar uma mensagem //(resposta)ao usuario
	return resposta;
	}
	
	
	
	public String sacar(Integer valor) {
		
		 int totalvalorUsado = 0;

	        if (valor <= 0) {
	            return "Valor inválido";
	        }

	        int[] usadas = new int[notas.length];
	        int restante = valor;

	        // percorre todas as notas
	        for (int i = 0; i < notas.length; i++) {
	        	
	            int valorNota = notas[i][0];
	            int qtdDisponivel = notas[i][1];
	            int qtdNecessaria = restante / valorNota;
	            int qtdUsada = Math.min(qtdNecessaria, qtdDisponivel);
	            
	            if(i == 4 && (restante == 8 || restante == 6) ) {
	            	continue;
	            }
	            usadas[i] = qtdUsada;
	            restante -= qtdUsada * valorNota;
	            
	        }

	            if (restante != 0)
	            	return "Não Temos Notas Para Este Saque";


	        // atualiza o caixa
	        for (int i = 0; i < notas.length; i++) {
	            notas[i][1] -= usadas[i];
	        }

	        
	        // monta resposta
	        String resposta = "Saque realizado com sucesso\n";
	        return resposta;
	
	}
	
	public String armazenaCotaMinima(Integer minimo) {
	String resposta = "";
	//logica de armazenar a cota minima para saque e criar um //mensagem(resposta)ao usuario
	return resposta;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String arg[]){
	GUI janela = new GUI();
	janela.show();
	}
}
