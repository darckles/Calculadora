package br.com.sad_diagnostico;

import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.util.ArrayList; 
import java.util.List; 
  
import android.content.Context; 
import android.database.Cursor; 
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase; 
import android.database.sqlite.SQLiteException; 
import android.database.sqlite.SQLiteOpenHelper; 

public class Conexao extends SQLiteOpenHelper {
	
	private static String DB_PATH="/data/data/br.com.sad_diagnostico/databases/";
	private static String DB_NAME = "doenca.db"; 
	private SQLiteDatabase dbQuery; 
	private final Context dbContexto;

	public Conexao(Context context) 
	{ 
	    super(context, DB_NAME, null, 1); 
	    this.dbContexto = context; 
	}
	
	public void CriarDataBase() throws IOException 
	{ 
	    
	boolean dbExist = checkDataBase(); 
	  
	if (!dbExist) 
	{    
	       this.getReadableDatabase(); 
	  
	       try 
	 { 
	              this.copiarDataBase(); 
	      } 
	 catch (IOException e) 
	 { 
	                  throw new Error("Erro ao copiar o Banco de Dados!"); 
	       } 
	 } 
	}
	
	private void copiarDataBase() throws IOException
	{
	 
	      InputStream myInput = dbContexto.getAssets().open(DB_NAME);
	      String outFileName = DB_PATH + DB_NAME;
	      OutputStream myOutput = new FileOutputStream(outFileName);
	 
	      byte[] buffer = new byte[1024];
	      int length;
	      while ((length = myInput.read(buffer))>0)
	{
	            myOutput.write(buffer, 0, length);
	      }
	 
	      myOutput.flush();
	      myOutput.close();
	      myInput.close();
	 
	}
	  
	private boolean checkDataBase()
	{
	 
	      SQLiteDatabase checkDB = null;
	 
	      try 
	{
	            String myPath = DB_PATH + DB_NAME;
	checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	     } 
	catch (SQLiteException e) 
	{}
	 
	      if (checkDB != null) 
	{
	            checkDB.close();
	     }
	 
	      return checkDB != null ? true : false;
	} 
	
	public void abrirDataBase() throws SQLException
	{
	      String myPath = DB_PATH + DB_NAME;
	dbQuery = SQLiteDatabase.openDatabase(myPath, null,      SQLiteDatabase.OPEN_READWRITE);
	}
	public List<String> selecionaTodos(String NomeTabela, String  OrdemCampo, String...TipoCampos) 
	{ 
	List<String> list = new ArrayList<String>(); 
	      Cursor cursor = dbQuery.query(NomeTabela, TipoCampos, 
	      null, null, null, null, OrdemCampo); 
	            if (cursor.moveToFirst()) 
	{ 
	               do { 
	list.add(cursor.getString(0) +" - " + cursor.getString(1) + " - "+ cursor.getString(2));
	                  } 
	while (cursor.moveToNext()); 
	            }
	            if (cursor != null && !cursor.isClosed()) 
	{ 
	               cursor.close(); 
	            } 
	            return list; 
	} 
	
	
	public List<String> mostra_sintomas(List campos, int cont){
		
		List<String> lista = new ArrayList<String>();
		
		
		Cursor cursor = dbQuery.rawQuery("SELECT "+ campos + "from SINTOMAS WHERE SINTOMAS"  , null);
		return null;
		
		
	}
	    
	@Override
	public synchronized void close() 
	{
	     if (dbQuery != null)
	     dbQuery.close();
	     super.close();
	}  
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
