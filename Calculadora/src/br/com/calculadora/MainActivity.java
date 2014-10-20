package br.com.calculadora;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	public static final String COMANDO_NUMERO = "NUMERO",
			COMANDO_SOMAR = "SOMAR", COMANDO_SUBTRAIR = "SUBTRAIR",
			COMANDO_MULTIPLICAR = "MULTIPLICAR", COMANDO_DIVIDIR = "DIVIDIR",
			COMANDO_RAIZ = "RAIZ", COMANDO_PORCENTO = "PORCENTO",
			COMANDO_EXP = "EXP", COMANDO_IGUAL = "IGUAL",
			COMANDO_LIMPIAR = "LIMPIAR",
			COMANDO_CAMBIAR_SIGNO = "CAMBIAR_SIGNO";

	public static enum Operacion {
		SOMA, SUBTRACAO, MULTIPLICACAO, DIVISAO, RAIZ, PORCENTO, EXP, NENHUMA
	};

	// Views
	private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
			btnMas, btnMenos, btnPor, btnDiv, btnIgual, btnLimpiar,
			btnMas_Menos, btnApagar, btponto, btnCE, btraiz, btporcento, btExp;
	EditText etCampoNumerico;

	private Calculo calc;
	private boolean ultimoPresionadoNumero;
	private boolean ultimoPresionadoOperacionado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		botoes();
		establecerTags();
		botoesEventos();
		etCampoNumerico.setText("0");
		calc = new Calculo();
	}

	private void botoes() {
		btn0 = (Button) findViewById(R.id.btn0);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn6 = (Button) findViewById(R.id.btn6);
		btn7 = (Button) findViewById(R.id.btn7);
		btn8 = (Button) findViewById(R.id.btn8);
		btn9 = (Button) findViewById(R.id.btn9);
		btnMas = (Button) findViewById(R.id.btnMas);
		btnMenos = (Button) findViewById(R.id.btnMenos);
		btnPor = (Button) findViewById(R.id.btnPor);
		btnDiv = (Button) findViewById(R.id.btnDiv);
		btnIgual = (Button) findViewById(R.id.btnIgual);
		btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
		btnMas_Menos = (Button) findViewById(R.id.btnMas_Menos);
		btnApagar = (Button) findViewById(R.id.btnApagar);
		btponto = (Button) findViewById(R.id.btponto);
		btnCE = (Button) findViewById(R.id.btnCe);
		btraiz = (Button) findViewById(R.id.btraiz);
		btporcento = (Button) findViewById(R.id.btporcento);
		btExp = (Button) findViewById(R.id.btExp);

		etCampoNumerico = (EditText) findViewById(R.id.etCampoNumerico);
	}

	private void establecerTags() {
		btn0.setTag(COMANDO_NUMERO);
		btn1.setTag(COMANDO_NUMERO);
		btn2.setTag(COMANDO_NUMERO);
		btn3.setTag(COMANDO_NUMERO);
		btn4.setTag(COMANDO_NUMERO);
		btn5.setTag(COMANDO_NUMERO);
		btn6.setTag(COMANDO_NUMERO);
		btn7.setTag(COMANDO_NUMERO);
		btn8.setTag(COMANDO_NUMERO);
		btn9.setTag(COMANDO_NUMERO);
		btponto.setTag(COMANDO_NUMERO);
		btnDiv.setTag(COMANDO_DIVIDIR);
		btnIgual.setTag(COMANDO_IGUAL);
		btnLimpiar.setTag(COMANDO_LIMPIAR);
		btnMas.setTag(COMANDO_SOMAR);
		btnMenos.setTag(COMANDO_SUBTRAIR);
		btnPor.setTag(COMANDO_MULTIPLICAR);
		btnMas_Menos.setTag(COMANDO_CAMBIAR_SIGNO);
		btnCE.setTag(COMANDO_LIMPIAR);
		btraiz.setTag(COMANDO_RAIZ);
		btporcento.setTag(COMANDO_PORCENTO);
		btExp.setTag(COMANDO_EXP);
	}

	private void botoesEventos() {
		btn0.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btnDiv.setOnClickListener(this);
		btnIgual.setOnClickListener(this);
		btnLimpiar.setOnClickListener(this);
		btnMas.setOnClickListener(this);
		btnMenos.setOnClickListener(this);
		btnPor.setOnClickListener(this);
		btnMas_Menos.setOnClickListener(this);
		btnCE.setOnClickListener(this);
		btporcento.setOnClickListener(this);
		btExp.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Button b = (Button) v;
		String numero =  (String) b.getText();
		if ((String) v.getTag() == COMANDO_NUMERO) {
			
			if (ultimoPresionadoNumero) {
				if (etCampoNumerico.getText().equals("0")) {
					etCampoNumerico.setText(numero);
				} else {
					etCampoNumerico.setText(etCampoNumerico.getText() + numero);
				}
			} else {
				etCampoNumerico.setText(numero);
			}

			ultimoPresionadoNumero = true;
			ultimoPresionadoOperacionado = false;
		} else if ((String) v.getTag() == COMANDO_CAMBIAR_SIGNO) {

			if (!etCampoNumerico.getText().toString().equalsIgnoreCase("0")) {
				if (etCampoNumerico.getText().toString().charAt(0) == '-') {
					etCampoNumerico.setText(etCampoNumerico.getText()
							.toString().substring(1));
				} else {
					etCampoNumerico.setText("-"
							+ etCampoNumerico.getText().toString());
				}
			}
		} else if ((String) v.getTag() == COMANDO_IGUAL
				&& ultimoPresionadoNumero
				&& calc.getOperacao() != Calculo.Operacao.NENHUMA) {
			this.etCampoNumerico
					.setText(""
							+ calc.realizaroperacao(Integer
									.parseInt(this.etCampoNumerico.getText()
											.toString())));
		} else if ((String) v.getTag() == COMANDO_SOMAR) {
			if (calc.getOperacao() == Calculo.Operacao.NENHUMA
					|| ultimoPresionadoOperacionado) {
				calc.setOperacao(Calculo.Operacao.SOMA, Integer
						.parseInt(this.etCampoNumerico.getText().toString()));
			} else {
				calc.realizaroperacao(Integer.parseInt(this.etCampoNumerico
						.getText().toString()));

				calc.setOperacao(Calculo.Operacao.SOMA, calc.getResultado());
				this.etCampoNumerico.setText(numero + calc.getResultado());
			}
			ultimoPresionadoNumero = false;
			ultimoPresionadoOperacionado = true;
		} else if ((String) v.getTag() == COMANDO_SUBTRAIR) {
			if (calc.getOperacao() == Calculo.Operacao.NENHUMA
					|| ultimoPresionadoOperacionado) {
				calc.setOperacao(Calculo.Operacao.SUBTRACAO, Integer
						.parseInt(this.etCampoNumerico.getText().toString()));
			} else {
				calc.realizaroperacao(Integer.parseInt(this.etCampoNumerico
						.getText().toString()));

				calc.setOperacao(Calculo.Operacao.SUBTRACAO,
						calc.getResultado());
				this.etCampoNumerico.setText("" + calc.getResultado());
			}
			ultimoPresionadoNumero = false;
			ultimoPresionadoOperacionado = true;
		} else if ((String) v.getTag() == COMANDO_MULTIPLICAR) {
			if (calc.getOperacao() == Calculo.Operacao.NENHUMA
					|| ultimoPresionadoOperacionado) {
				calc.setOperacao(Calculo.Operacao.MULTIPLICACAO, Integer
						.parseInt(this.etCampoNumerico.getText().toString()));
			} else {
				calc.realizaroperacao(Integer.parseInt(this.etCampoNumerico
						.getText().toString()));

				calc.setOperacao(Calculo.Operacao.MULTIPLICACAO,
						calc.getResultado());
				this.etCampoNumerico.setText("" + calc.getResultado());
			}
			ultimoPresionadoNumero = false;
			ultimoPresionadoOperacionado = true;
		} else if ((String) v.getTag() == COMANDO_DIVIDIR) {
			if (calc.getOperacao() == Calculo.Operacao.NENHUMA
					|| ultimoPresionadoOperacionado) {
				calc.setOperacao(Calculo.Operacao.DIVISAO, Integer
						.parseInt(this.etCampoNumerico.getText().toString()));
			} else {
				calc.realizaroperacao(Integer.parseInt(this.etCampoNumerico
						.getText().toString()));

				calc.setOperacao(Calculo.Operacao.DIVISAO, calc.getResultado());
				this.etCampoNumerico.setText("" + calc.getResultado());
			}
			ultimoPresionadoNumero = false;
			ultimoPresionadoOperacionado = true;
		} else if ((String) v.getTag() == COMANDO_LIMPIAR) {
			calc.reiniciar();
			this.etCampoNumerico.setText("0");
			ultimoPresionadoNumero = false;
		}
	}
}
