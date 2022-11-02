package com.example.contador;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    public int contador;
    public EditText et1;
    TextView resultadoTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultadoTexto = (TextView) findViewById(R.id.contadorNumero);
        contador = 0;

    }
    /*  public void onSaveInstanceState(Bundle estado){
        estado.putInt("contador",contador);
        super.onSaveInstanceState(estado);
    }
    public void onRestoreInstanceState(Bundle estado){
        super.onRestoreInstanceState(estado);
        contador = estado.getInt("contador");
        textoAMostrar.setText(""+contador);
    }*/
    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences datosGuardar = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor miEditor = datosGuardar.edit();
        miEditor.putInt("contador",contador);
        miEditor.apply();
    }
    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences datosGuardar = PreferenceManager.getDefaultSharedPreferences(this);
        contador = datosGuardar.getInt("contador",0);
        resultadoTexto.setText(""+contador);
    }
    public void incrementar(View vista) {
        contador++;
        resultadoTexto.setText("" + contador);
    }

    public void resetear(View vista) {
        et1 = (EditText) findViewById(R.id.n_reseteo);

        contador = Integer.parseInt(et1.getText().toString());

        et1.setText("");

        resultadoTexto.setText("" + contador);

        InputMethodManager miteclado = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        miteclado.hideSoftInputFromWindow(et1.getWindowToken(),0);
    }

    public void decrementar(View vista) {
        contador--;
        if (contador < 0) {
            CheckBox negativo = (CheckBox) findViewById(R.id.checkBoxNegativos);
            if (!negativo.isChecked()) {
                contador = 0;
            }
        }
        mostrarRestultado();
    }

    public void mostrarRestultado() {
        TextView resultadoTexto = (TextView) findViewById(R.id.contadorNumero);
        resultadoTexto.setText("" + contador);

    }



}



