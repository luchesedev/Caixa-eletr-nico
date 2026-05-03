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
	String resposta = "";
	//logica de fazer o relatorio de cedulas]
	resposta = notas[0][1] != 0 ? resposta+="Notas de 100: " + notas[0][1]+"\n" :resposta+"";
	resposta = notas[1][1] != 0 ? resposta+="Notas de 50: " + notas[1][1]+"\n" :resposta+"";
	resposta = notas[2][1] != 0 ? resposta+="Notas de 20: " + notas[2][1]+"\n" :resposta+"";
	resposta = notas[3][1] != 0 ? resposta+="Notas de 10: " + notas[3][1]+"\n" :resposta+"";
	resposta = notas[4][1] != 0 ? resposta+="Notas de 5: " + notas[4][1]+"\n" :resposta+"";
	resposta = notas[5][1] != 0 ? resposta+="Notas de 2: " + notas[5][1]+"\n" :resposta+"";
	
	return resposta;
	}
	public String pegaValorTotalDisponivel() {
	String resposta = "";
	//logica de pega o valor total disponivel no caixa eletronio
	return resposta;
	}
	public String reposicaoCedulas(Integer cedula, Integer quantidade) {
	String resposta = "";
	//logica de fazer a reposicao de cedulas e criar uma mensagem //(resposta)ao usuario
	return resposta;
	}
	
	
	
	public String sacar(Integer valor) {
		
  
	    if (valor <= 0) {
	        return "Valor inválido";
	    }

	    int restante = valor;
	    int[] usadas = new int[notas.length];

	    // percorre todas as notas
	    for (int i = 0; i < notas.length; i++) {

	        int valorNota = notas[i][0];
	        int qtdDisponivel = notas[i][1];

	        int qtdUsada = Math.min(restante / valorNota, qtdDisponivel);

	        usadas[i] = qtdUsada;
	        restante -= qtdUsada * valorNota;
	    }

	    // se não conseguiu pagar
	    if (restante != 0) {
	        return "Não Temos Notas Para Este Saque";
	    }

	    // atualiza o caixa
	    for (int i = 0; i < notas.length; i++) {
	        notas[i][1] -= usadas[i];
	    }

	    // monta resposta
	    String resposta = "Saque realizado com sucesso:\n";

	    for (int i = 0; i < notas.length; i++) {
	        resposta += notas[i][0] + ": " + usadas[i] + "\n";
	    }

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
