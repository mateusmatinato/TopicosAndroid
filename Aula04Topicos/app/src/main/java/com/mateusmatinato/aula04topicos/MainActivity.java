package com.mateusmatinato.aula04topicos;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvResultado;
    private Button btnResultado;

    private TextInputLayout textInputAlcool, textInputGasolina;

    private EditText textAlcool, textGasolina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResultado = findViewById(R.id.tvResultado);
        btnResultado = findViewById(R.id.btnResultado);
        textInputAlcool = findViewById(R.id.textInputAlcool);
        textInputGasolina = findViewById(R.id.textInputGasolina);
        textAlcool = findViewById(R.id.etValorAlcool);
        textGasolina = findViewById(R.id.etValorGasolina);


        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clicou no botão de Ver Resultado

                if(possuiErros() == false){
                    // não possui erros, pode calcular

                    Double calculo = Double.parseDouble(textAlcool.getText().toString()) / Double.parseDouble(textGasolina.getText().toString());
                    if(calculo > 0.7){
                        //Escolhe gasolina
                        tvResultado.setText("Abasteça com Gasolina!");
                    }
                    else{
                        //Escolhe alcool
                        tvResultado.setText("Abasteça com Álcool!");
                    }

                }
                else{
                    // possui erros, mostra no resultado
                    tvResultado.setText("Por favor, insira valores para o álcool/gasolina.");
                }



            }
        });



    }

    protected boolean possuiErros(){
        boolean erro = false;

        if(this.textAlcool.getText().toString().length() == 0){
            //não inseriu valor pro alcool
            textInputAlcool.setError("Insira um preço para o álcool");
            textInputAlcool.setErrorEnabled(true);
            erro = true;
        }
        else{
            //inseriu valor, seta erro p false
            textInputAlcool.setErrorEnabled(false);
        }

        if(this.textGasolina.getText().toString().length() == 0){
            //não inseriu valor pro alcool
            textInputGasolina.setError("Insira um preço para a gasolina");
            textInputGasolina.setErrorEnabled(true);
            erro = true;
        }
        else{
            //inseriu valor, seta erro p false
            textInputGasolina.setErrorEnabled(false);
        }

        return erro;

    }
}
