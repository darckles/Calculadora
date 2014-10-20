package br.com.sad_diagnostico;

import java.io.IOException;
import java.util.List;

import br.com.sad_diagnostico.R;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.os.Bundle;
import android.widget.TextView;

public class PrincipalActivity extends Activity {

	TextView output;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		output = (TextView) this.findViewById(R.id.out_text);

		Conexao db = new Conexao(this);

		try {
			db.CriarDataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}

		db.abrirDataBase();

		List<String> names = db.selecionaTodos("SINTOMAS", "ID", "DESCRICAO","ID");
		StringBuilder sb = new StringBuilder();
		sb.append("Clientes Cadastrados:\n");
		for (String name : names) {
			sb.append(name + "\n");
		}
		
		output.setText(sb.toString());
	}
}
