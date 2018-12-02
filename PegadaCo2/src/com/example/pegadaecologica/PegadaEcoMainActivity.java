package com.example.pegadaecologica;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class PegadaEcoMainActivity extends Activity {
	
	// Inputs
	EditText  edt1Carne, edt2Frango, edt3Porco;
	
	// Botão de calculo
	Button btn_calcular;
	
	// Constantes: Emissão de Co2/t produzida por alimento;
	final double boi = 36;
	final double frango = 6;
	final double porco = 4;
	
	// Constante: Árvores necessárias por t/Co2 produzido
	final double arvores = 7.14;
	
	
	// Método de exibição AlertDialog
	public void CaixaDialogo(String titulo, String message){
	
		AlertDialog.Builder dialogo = new AlertDialog.Builder(PegadaEcoMainActivity.this);
		dialogo.setTitle(titulo);
		dialogo.setMessage(message);
		dialogo.setNeutralButton("Ok", null);
		dialogo.show();
	}
	
	// Método para limpar inputs
		public void LimparInputs(){
			edt1Carne.setText("");
			edt2Frango.setText("");
			edt3Porco.setText("");		
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pegada_eco_main);
           
        edt1Carne = (EditText) findViewById(R.id.editText3);
        edt2Frango = (EditText) findViewById(R.id.editText2);
        edt3Porco = (EditText) findViewById(R.id.editText1);
        
        btn_calcular = (Button) findViewById(R.id.button1);
        
    } // Encerramento do método onCreate
    
    
        // Calculo da pegada de Co2
        public void Calcular(View v){
          	
          	/* Verificação de EditTexts vazios:
          	 * Caso o usuário esqueça de fornecer algum valor, é mostrado um aviso.
          	 */
        	
        	// Conversões para String
          	String entradaCarne = edt1Carne.getText().toString();
          	String entradaFrango = edt2Frango.getText().toString();
          	String entradaPorco = edt3Porco.getText().toString();
          
          	// Validação de EditTexts
          	if (entradaCarne.isEmpty() || entradaFrango.isEmpty() || entradaPorco.isEmpty()){
          		CaixaDialogo("Ops...", "Preencha todas as entradas!");
          	} else {
          		
          	// Adquire os conteúdos das variáveis e as converte em Double
            	double t1 = Double.parseDouble(edt1Carne.getText().toString()); // 1
              	double t2 = Double.parseDouble(edt2Frango.getText().toString());
              	double t3 = Double.parseDouble(edt3Porco.getText().toString());
              	
              	// Quantidade de Co2/t
              	t1 = t1  * boi; // 36
              	t2 = t2  * frango;
              	t3 = t3  * porco;
             
              	// Quantidade de toneladas total na dieta
            	double resulDieta = (t1 + t2 + t3);
            	
            	// Quantidade de arvores a serem plantadas
            	int quantArvores = (int) (resulDieta * arvores);
            	
            	/* Verificação de valores < que 1.
              	 *  Para que nossos possam respirar um ar mais limpo,
              	 *  caso a quantidade de Co2/t seja menor que < 1,
              	 *  será requisitada a plantação de ao menos 1 árvore
              	 */ 
            	
            	// Validação quant. mínima
            	if(quantArvores < arvores){
            		CaixaDialogo("Emisão de Co2 menor que 1t", "Plante ao menos 1 árvore e ajude a salvar o planeta!");
            	} else {
            		CaixaDialogo(resulDieta +  "t de Co2 foram geradas.","Plante: " + quantArvores + " árvores e ajude a salvar o planeta.");
            		LimparInputs();
            	}
          	
          	}
          		
        } // Encerramento do metodo OnClick   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pegada_eco_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
