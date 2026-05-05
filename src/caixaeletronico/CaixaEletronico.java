package caixaeletronico;
public class CaixaEletronico  implements ICaixaEletronico {
	private int valor;
	private int cotaMinima = 0;
    private int[][] notas = new int[][] {
        {100, 100},
        {50, 200},
        {20, 300},
        {10, 350},
        {5, 450},
        {2, 500}
    };
   private int[] historicoCedulas = new int[6];
    int somatotal=0;
    String msg="";
    
   
    
    
    
    
    public String pegaRelatorioCedulas() {
    	String resposta = ("-").repeat(30)+"Relatório de células"+("-").repeat(30)+"\n";
        //Concatenando a string resposta com operador ternario para concatenar apenas as notas presentes em caixa 
    	resposta = notas[0][1] != 0 ? resposta+="Notas de R$100,00: "+notas[0][1]+" notas \n" :resposta+"";
        resposta = notas[1][1] != 0 ? resposta+="Notas de R$50,00: "+notas[1][1]+" notas \n" :resposta+"";
        resposta = notas[2][1] != 0 ? resposta+="Notas de R$20,00: "+notas[2][1]+" notas \n" :resposta+"";
        resposta = notas[3][1] != 0 ? resposta+="Notas de R$10,00: "+notas[3][1]+" notas \n" :resposta+"";
        resposta = notas[4][1] != 0 ? resposta+="Notas de R$5,00: "+notas[4][1]+" notas \n" :resposta+"";
        resposta = notas[5][1] != 0 ? resposta+="Notas de R$2,00: "+notas[5][1]+" notas \n" :resposta+"";
        resposta+=("-").repeat(90);
        return resposta;
    }
    
    
    
    

	public String pegaValorTotalDisponivel() {
	String resposta = "";
	int total=0;
	//atributo que vai armazenar a soma total do valor
		
		
	//for para percorrer o array
		for (int i = 0; i < notas.length; i++) {
	//incrementa a multiplicação da quantidade de notas pelo valor no atributo "total"
			total += notas[i][0] * notas [i][1];
			
		}
		resposta = "Valor total disponível no caixa: R$ " + total;
	return resposta;
	}
	
	
	
	
	
	
	public String reposicaoCedulas(Integer cedula, Integer quantidade) {
		
		String resposta = "";
		        //possível erro de nulo
		        if (cedula == null || quantidade == null) {
		        	resposta = "Erro: Cédula e quantidade não podem ser nulos.";
		            return resposta;
		        }
		      //possível erro de input de um valor negativo
		        if (quantidade <= 0) {
		        	resposta = "Erro: A quantidade para reposição deve ser maior que zero.";
		            return resposta;
		        }
		        //Enfim adicionando as notas
		        for (int i = 0; i < notas.length; i++) {
		            
		            if (notas[i][0] == cedula) {
		               
		                notas[i][1] += quantidade;
		                
		                	resposta = "Sucesso! Foram adicionadas "+quantidade+" cédulas de R$"+cedula+",00";
		               return resposta;
		            }
		        }//caso digitem uma cedula invalida
		        		resposta = "Erro: Cédula de R$ "+cedula+",00 inválida. O caixa só aceita notas de 100, 50, 20, 10, 5 e 2."; 
		      return  resposta;
		    
	}
	
	
	
	
	
	
	
	public String sacar(Integer valor) {
		
		 int valorNota;
         int qtdDisponivel; 
         int qtdNecessaria; 
         int qtdUsada;
         int total=0;
         int quantidadeGeral=0;
     
     		for (int i = 0; i < notas.length; i++) {
     	
     			total += notas[i][0] * notas [i][1];	
     		}
         
     	//Condição para rodar o sistema apenas se não estiver em cota minima
         if(cotaMinima <total){
	        if (valor <= 0) {
	            return "Valor inválido";
	        }
	        if(valor<cotaMinima) {
	        	return "saque a partir da cota mínima estabelecida de "+cotaMinima;
	        }
	        
	      //Não é possível sacar mais que 3000
            if(valor>3000)
	 	       {
	 	    	   return "Saque não permitido, valor máximo atingido";
            	
	 	       }
	        
	        int totalAtual = 0;
	        for (int i = 0; i < notas.length; i++) {
	            totalAtual += notas[i][0] * notas[i][1];
	        }

	      

	        int[] usadas = new int[notas.length];
	        int restante = valor;

	        // percorre todas as notas
	        for (int i = 0; i < notas.length; i++) {
	        	//calcula o valor da nota, consulta a quantidade disponivel para calcular a quantidade que será usada 
	             valorNota = notas[i][0];
	             qtdDisponivel = notas[i][1];
	            qtdNecessaria = restante / valorNota;
	             qtdUsada = Math.min(qtdNecessaria, qtdDisponivel);
	            
	            //para casos com o resto 3 não é possível  
	            while(qtdUsada>0 && restante-(qtdUsada*valorNota)==3) {
	            	qtdUsada--;
	            }
	            //para casos que a ultima casa decimal é o 8 e 6 
	            if(i == 4 && (restante == 8 || restante == 6) ) {
	            	continue;
	            }
	            usadas[i] = qtdUsada;
	            quantidadeGeral += usadas[i];
	            restante -= qtdUsada * valorNota;
	            
	            
	           
	          //Não é possível caso exceda mais de 30 cedulas 
	            if(quantidadeGeral>30)
	            {
	            	return "Saque não permitido,excedeu o número de cédulas";
	            	
	            }
	            
	        }
	      
	        
	        //Saque impossiveis como 1 e 3 por exemplo ou a quantidade necessárias de notas
	            if (restante != 0)
	            	return "Não Temos Notas Para Este Saque";
	            
	            for (int i = 0; i < notas.length; i++) {
		                    
		            historicoCedulas[i] += usadas[i];  
		        }
	            
	            
	           somatotal+=valor;
	           
	           
	           
	          


	        // atualiza o caixa
	        for (int i = 0; i < notas.length; i++) {
	            notas[i][1] -= usadas[i];
	        }

	        
	        // monta resposta
	        String resposta = "Saque realizado com sucesso\n";
	        
	        
	        
	        
	        for (int i = 0; i < notas.length; i++) {
	            resposta += notas[i][0] + ": " + usadas[i] + " nota(s)\n";
	           
	        }
	        
	        msg = "====== EXTRATO ======\n";
	        msg+="Valor inicial: R$ 37250";
	        msg+="\nValor final: R$"+(37250-somatotal);
			msg += "\nValor total sacado: R$ " + somatotal + ",00\n";
			msg += "-----------------------------------";

			
			for (int i = 0; i < notas.length; i++) {
				
				if (historicoCedulas[i] > 0) {
					msg += "\n" + notas[i][0] + ",00: " + historicoCedulas[i] + " nota(s)";
				}
			}
	        
	       return resposta;
	      
	
	}
         	String resposta = "Caixa Vazio: Chame o Operador";
	        return resposta;
         
         
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public String armazenaCotaMinima(Integer minimo) {

		//logica de armazenar a cota minima para saque e criar um //mensagem(resposta)ao usuario
		this.cotaMinima = minimo;
		boolean caixaVazio;
		int total = 0;
		
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
