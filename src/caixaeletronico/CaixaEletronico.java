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
    
    public String pegaRelatorioCedulas() {
    	String resposta = ("-").repeat(30)+"Relatório de células"+("-").repeat(30)+"\n";
        //logica de fazer o relatorio de cedulas
    	resposta = notas[0][1] != 0 ? resposta+="Notas de R$100,00: "+notas[0][1]+" notas \n" :resposta+"";
        resposta = notas[1][1] != 0 ? resposta+="Notas de R$50,00: "+notas[1][1]+" notas \n" :resposta+"";
        resposta = notas[2][1] != 0 ? resposta+="Notas de R$20,00: "+notas[2][1]+" notas \n" :resposta+"";
        resposta = notas[3][1] != 0 ? resposta+="Notas de R$10,00: "+notas[3][1]+" notas \n" :resposta+"";
        resposta = notas[4][1] != 0 ? resposta+="Notas de R$5,00: "+notas[4][1]+" notas \n" :resposta+"";
        resposta = notas[5][1] != 0 ? resposta+="Notas de R$2,00: "+notas[5][1]+" notas \n" :resposta+"";

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
		        
		        if (cedula == null || quantidade == null) {
		        	resposta = "Erro: Cédula e quantidade não podem ser nulos.";
		            return resposta;
		        }
		        
		        if (quantidade <= 0) {
		        	resposta = "Erro: A quantidade para reposição deve ser maior que zero.";
		            return resposta;
		        }

		  
		        for (int i = 0; i < notas.length; i++) {
		            
		            if (notas[i][0] == cedula) {
		               
		                notas[i][1] += quantidade;
		                
		                	resposta = "Sucesso! Foram adicionadas "+quantidade+" cédulas de R$"+cedula+",00";
		               return resposta;
		            }
		        }
		        		resposta = "Erro: Cédula de R$ "+cedula+",00 inválida. O caixa só aceita notas de 100, 50, 20, 10, 5 e 2."; 
		      return  resposta;
		    
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
	        	//calcula o valor da nota, consulta a quantidade disponivel para calcular a quantidade que será usada 
	            int valorNota = notas[i][0];
	            int qtdDisponivel = notas[i][1];
	            int qtdNecessaria = restante / valorNota;
	            int qtdUsada = Math.min(qtdNecessaria, qtdDisponivel);
	            
	            //para casos com o resto 3 não é possível  
	            while(qtdUsada>0 && restante-(qtdUsada*valorNota)==3) {
	            	qtdUsada--;
	            }
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

		//logica de armazenar a cota minima para saque e criar um //mensagem(resposta)ao usuario
		boolean caixaVazio;
		double total = 0;
		
	//for para percorrer o array
		for (int i = 0; i < notas.length; i++) {
	//incrementa a multiplicação da quantidade de notas pelo valor no atributo "total"
			total += notas[i][0] * notas [i][1];
		}

		if(total < minimo){
			caixaVazio = true;
			String resposta = "Caixa Vazio: Chame o Operador";
			return resposta;
		}
		else{
			caixaVazio = false;
			String resposta = "Cota Minima de R$: "+ minimo + "\nCaixa OK";
			return resposta;
		}

	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String arg[]){
	GUI janela = new GUI();
	janela.show();
	}
}
