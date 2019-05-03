package com.mateusmatinato.aula02topicos;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonGerar;
    private TextView textFrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGerar = findViewById(R.id.btGerar);
        textFrases = findViewById(R.id.tvFrases);

        final ArrayList frases = new ArrayList();

        try {
            AssetManager assetManager = getResources().getAssets();
            InputStream inputStream = assetManager.open("frases.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String linha;
            while((linha = bufferedReader.readLine())!=null){
                if(linha.length() != 0) {
                    frases.add(linha);
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buttonGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int i = random.nextInt(frases.size());
                textFrases.setText(""+frases.get(i));
            }
        });




    }
}
