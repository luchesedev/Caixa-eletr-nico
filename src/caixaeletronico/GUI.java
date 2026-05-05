package caixaeletronico;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CaixaEletronico caixa; 
	
	// Cores Originais
	private Color azulPrimario = new Color(41, 128, 185);
	private Color azulEscuro = new Color(44, 62, 80);
	private Color fundoCinza = new Color(236, 240, 241);
	private Color vermelhoSair = new Color(192, 57, 43);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		 this.caixa = new CaixaEletronico();
		setTitle("Sistema de Caixa Eletrônico");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 650);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// --- Módulo Cliente ---
		JLabel lblCliente = new JLabel("MÓDULO DO CLIENTE");
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setForeground(azulEscuro);
		lblCliente.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblCliente.setBounds(10, 30, 364, 25);
		contentPane.add(lblCliente);
		
		JButton btnSaque = new JButton("Efetuar Saque");
		btnSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String resultado = caixa.sacar(Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Digite o valor:")));
				JOptionPane.showMessageDialog(null,resultado);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"operação cancelada");
				}
			}
		});
		estilizarBotao(btnSaque, azulEscuro);
		btnSaque.setBounds(75, 70, 230, 40);
		contentPane.add(btnSaque);
		
		// --- Módulo Administrador ---
		JLabel lblAdmin = new JLabel("MÓDULO ADMINISTRATIVO");
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setForeground(azulEscuro);
		lblAdmin.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblAdmin.setBounds(10, 160, 364, 25);
		contentPane.add(lblAdmin);
		
		JButton btnRelatorio = new JButton("Relatório de Cédulas");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,caixa.pegaRelatorioCedulas());
                
			}
		});
		estilizarBotao(btnRelatorio, azulEscuro);
		btnRelatorio.setBounds(75, 200, 230, 35);
		contentPane.add(btnRelatorio);
		
		JButton btnValorTotal = new JButton("Valor Total Disponível");
		btnValorTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,caixa.pegaValorTotalDisponivel());
			}
		});
		estilizarBotao(btnValorTotal, azulEscuro);
		btnValorTotal.setBounds(75, 245, 230, 35);
		contentPane.add(btnValorTotal);
		
		JButton btnReposicao = new JButton("Reposição de Cédulas");
		btnReposicao.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        int valor1 = 0, valor2 = 0;
		        boolean isWrong = true;

		        while (isWrong) {
		            try {
		                String entrada1 = JOptionPane.showInputDialog(null, "Digite a cédula que deseja inserir:");

		                // CANCELAR ou FECHAR
		                if (entrada1 == null) {
		                    JOptionPane.showMessageDialog(null, "Operação cancelada");
		                    break;
		                }

		                String entrada2 = JOptionPane.showInputDialog(null, "Digite a quantidade de cédulas que ira repor:");

		                // CANCELAR ou FECHAR
		                if (entrada2 == null) {
		                    JOptionPane.showMessageDialog(null, "Operação cancelada");
		                    break;
		                }

		                valor1 = Integer.parseInt(entrada1);
		                valor2 = Integer.parseInt(entrada2);

		                isWrong = false;

		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null,
		                        "Erro: digite apenas números inteiros!");
		            }
		        }

		        // só executa se não cancelou
		        if (isWrong == false) {
		            String resultado = caixa.reposicaoCedulas(valor1, valor2);
		            JOptionPane.showMessageDialog(null, resultado);
		        }
		    }
		});
		estilizarBotao(btnReposicao, azulEscuro);
		btnReposicao.setBounds(75, 290, 230, 35);
		contentPane.add(btnReposicao);
		
		JButton btnCota = new JButton("Cota Mínima");
		btnCota.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e) {
        	int valor = 0;
        	boolean isWrong = true;
        	while (isWrong) {
            	try {
                	String entrada = JOptionPane.showInputDialog(null, "Digite a cota mínima para atendimento:");
                	if (entrada == null) { JOptionPane.showMessageDialog(null, "Operação cancelada"); break; }

                	valor = Integer.parseInt(entrada);
                	isWrong = false;
				}
           		catch (NumberFormatException ex) {
                	JOptionPane.showMessageDialog(null, "Erro: digite apenas números inteiros!");
            	}
        	}
        if (!isWrong) JOptionPane.showMessageDialog(null, caixa.armazenaCotaMinima(valor));
    }
});
		estilizarBotao(btnCota, azulEscuro);
		btnCota.setBounds(75, 335, 230, 35);
		contentPane.add(btnCota);
		
		// --- Geral ---
		JButton btnSair = new JButton("SAIR DO SISTEMA");
		btnSair.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSair.setBackground(new Color(255, 0, 0)); // Fundo Vermelho
		btnSair.setForeground(Color.WHITE);          // Texto Branco

		// --- AS LINHAS QUE RESOLVEM O BUG ---
		btnSair.setFocusPainted(false);      // Remove aquele retângulo em volta do texto
		btnSair.setContentAreaFilled(false); // Impede o Windows de pintar o fundo azul ao clicar
		btnSair.setOpaque(true);             // Garante que o SEU vermelho apareça no lugar
		// ------------------------------------

		btnSair.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		        // Agora o vermelho vai ficar "travado"
		        btnSair.setBackground(new Color(255, 0, 0));
		        btnSair.setForeground(Color.WHITE);
		    }

		    @Override
		    public void mouseReleased(MouseEvent e) {
		        btnSair.setBackground(new Color(255, 0, 0));
		        btnSair.setForeground(Color.WHITE);
		    }
		});

		btnSair.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if(caixa.somatotal==0)
		    	{
		    			JOptionPane.showMessageDialog(null,"Nenhum saque realizado");
		    	}
		    	else {
		    		JOptionPane.showMessageDialog(null,caixa.msg);
		    		
		    	}
		
		        System.exit(0);
		        
		    
		       
		    }
		});

		btnSair.setBounds(75, 455, 230, 40);
		contentPane.add(btnSair);}

	/**
	 * Método de estilização com efeito de clique personalizado
	 */
	private void estilizarBotao(JButton botao, Color corOriginal) {
		botao.setFont(new Font("Segoe UI", Font.BOLD, 13));
		botao.setForeground(Color.WHITE);
		botao.setBackground(corOriginal);
		botao.setBorder(new LineBorder(corOriginal.darker(), 1));
		botao.setFocusPainted(false);
		botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
		botao.setOpaque(true);
		botao.setContentAreaFilled(true);

		// Cores do Clique
		Color azulClaroClique = new Color(200, 230, 255);
		Color azulEscuroTexto = new Color(44, 62, 80);

		botao.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Efeito ao clicar (segurar)
				botao.setBackground(azulClaroClique);
				botao.setForeground(azulEscuroTexto);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// Volta ao normal ao soltar
				botao.setBackground(corOriginal);
				botao.setForeground(Color.WHITE);
			}
		});
	}
}