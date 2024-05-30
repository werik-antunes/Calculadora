package calculadora;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CalculadoraSwing extends JFrame implements ActionListener {
	   private JTextField display;
	    private JButton[] botoesNumeros;
	    private JButton[] botoesOperadores;
	    private JButton botaoLimpar;
	    private JButton botaoResultado;
	    private double primeiroNumero;
	    private String operador;

	    public CalculadoraSwing() {
	        setTitle("Calculadora");
	        setSize(300, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout());

	        
	        display = new JTextField();
	        display.setEditable(false);
	        display.setHorizontalAlignment(JTextField.RIGHT);
	        add(display, BorderLayout.NORTH);

	        
	        JPanel painelBotoes = new JPanel();
	        painelBotoes.setLayout(new GridLayout(4, 4));

	        
	        botoesNumeros = new JButton[10];
	        for (int i = 0; i < 10; i++) {
	            botoesNumeros[i] = criarBotao(String.valueOf(i), painelBotoes);
	        }

	        
	        String[] operadores = {"+", "-", "*", "/"};
	        botoesOperadores = new JButton[operadores.length];
	        for (int i = 0; i < operadores.length; i++) {
	            botoesOperadores[i] = criarBotao(operadores[i], painelBotoes);
	        }

	        
	        botaoResultado = criarBotao("=", painelBotoes);
	        botaoLimpar = criarBotao("C", painelBotoes);

	        add(painelBotoes, BorderLayout.CENTER);
	        setVisible(true);
	    }

	    private JButton criarBotao(String label, JPanel painel) {
	        JButton botao = new JButton(label);
	        botao.addActionListener(this);
	        painel.add(botao);
	        return botao;
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        String comando = e.getActionCommand();

	        if (comando.equals("C")) {
	            display.setText("");
	        } else if (comando.equals("=")) {
	            calcularResultado();
	        } else if ("+-*/".contains(comando)) {
	            primeiroNumero = Double.parseDouble(display.getText());
	            operador = comando;
	            display.setText("");
	        } else {
	            display.setText(display.getText() + comando);
	        }
	    }

	    private void calcularResultado() {
	        try {
	            double segundoNumero = Double.parseDouble(display.getText());
	            double resultado = 0;

	            switch (operador) {
	                case "+":
	                    resultado = primeiroNumero + segundoNumero;
	                    break;
	                case "-":
	                    resultado = primeiroNumero - segundoNumero;
	                    break;
	                case "*":
	                    resultado = primeiroNumero * segundoNumero;
	                    break;
	                case "/":
	                    if (segundoNumero != 0) {
	                        resultado = primeiroNumero / segundoNumero;
	                    } else {
	                        mostrarErro("Não é possível dividir por zero!");
	                        return;
	                    }
	                    break;
	            }
	            display.setText(Double.toString(resultado));
	        } catch (NumberFormatException e) {
	            mostrarErro("Entrada inválida!");
	        }
	    }

	    private void mostrarErro(String mensagem) {
	        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(CalculadoraSwing::new);
	    }
	}

