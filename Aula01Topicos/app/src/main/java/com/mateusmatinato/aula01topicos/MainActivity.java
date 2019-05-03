package com.mateusmatinato.aula01topicos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textNumeroSorteado;
    private Button buttonClicar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNumeroSorteado = findViewById(R.id.tvNumeroSorteado);        // Linka o textTexto com o textView que foi criado com o id 'tvTexto'
        buttonClicar = findViewById(R.id.btClicar);    // Linka o buttonClicar com o button que foi criado com o id 'btClicar'

        buttonClicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Seta o listener do click do botão
                ArrayList cornos = new ArrayList();
                cornos.add("João Marcos");
                cornos.add("Jonatan");
                cornos.add("Adolfo");
                cornos.add("Matinato");
                cornos.add("Vechiato");

                Random obj = new Random();
                int i = obj.nextInt(cornos.size());

                String cornoDaVez = cornos.get(i).toString();
                textNumeroSorteado.setText("Corno sorteado: "+cornoDaVez);

            }
        });

    }






}
