package com.mateusmatinato.aula05topicos2;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    SeekBar seekGorjeta;
    TextInputLayout inputValorLayout;
    EditText inputValor;
    TextView tvGorjeta, tvTotal, tvPorcentagem;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekGorjeta = findViewById(R.id.seekGorjeta);
        inputValorLayout = findViewById(R.id.inputLayoutConta);
        inputValor = findViewById(R.id.inputValorConta);
        tvGorjeta = findViewById(R.id.tvGorjeta);
        tvTotal = findViewById(R.id.tvTotal);
        tvPorcentagem = findViewById(R.id.tvPorcentagem);

        seekGorjeta.setProgress(15); // valor inicial = 15

        seekGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Manipulou o progress bar

                if(inputValor.getText().toString().length() != 0){
                    tvPorcentagem.setText(""+progress+"%");
                    Double valorConta = Double.parseDouble(inputValor.getText().toString());

                    Double valorGorjeta =valorConta * (seekGorjeta.getProgress()/100.00);
                    Double valorTotal = valorConta + valorGorjeta;

                    tvGorjeta.setText("R$: "+df2.format(valorGorjeta));
                    tvTotal.setText("R$: "+df2.format(valorTotal));

                    inputValorLayout.setErrorEnabled(false);
                }
                else{
                    inputValorLayout.setError("Digite um valor para a conta!");
                    inputValorLayout.setErrorEnabled(true);
                    tvGorjeta.setText("Erro");
                    tvTotal.setText("Erro");

                }




            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
