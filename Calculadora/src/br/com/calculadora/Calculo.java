package br.com.calculadora;

public class Calculo {
	
	public static enum Operacao {
		SOMA, SUBTRACAO, MULTIPLICACAO, DIVISAO, RAIZ, PORCENTO, EXP, NENHUMA
	}

	private double resultado = 0;
	private Operacao OperAtual = Operacao.NENHUMA;

	public void setOperacao(Operacao op, double operando) {
		this.OperAtual = op;
		resultado = operando;
	}

	public double realizaroperacao(double operando) {
		switch (OperAtual) {
		case SOMA:
			resultado += operando;
			break;
		case SUBTRACAO:
			resultado -= operando;
			break;
		case MULTIPLICACAO:
			resultado *= operando;
			break;
		case DIVISAO:
			resultado /= operando;
			break;
		case RAIZ:
			resultado = Math.sqrt(operando);
			break;
		case PORCENTO:
			resultado *= (operando/100);
			break;
		case EXP:
			resultado *= Math.exp(operando);
			break;
			
		}
		OperAtual = Operacao.NENHUMA;
		return resultado;
	}

	public double getResultado() {
		return resultado;
	}

	public Operacao getOperacao() {
		return this.OperAtual;
	}

	public void reiniciar() {
		resultado = 0;
		OperAtual = Operacao.NENHUMA;
	}

}
