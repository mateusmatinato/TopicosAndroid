package com.mateusmatinato.aula02_topicos_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView escolhaApp, escolhaPedra, escolhaPapel, escolhaTesoura;
    private TextView textResultado, textPontUser, textPontApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        escolhaApp = findViewById(R.id.imEscolha);
        escolhaPedra = findViewById(R.id.imPedra);
        escolhaPapel = findViewById(R.id.imPapel);
        escolhaTesoura = findViewById(R.id.imTesoura);
        textResultado = findViewById(R.id.tvResultado);
        textPontApp = findViewById(R.id.tvPontApp);
        textPontUser = findViewById(R.id.tvPontUser);

        escolhaPedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se clicar no pedra
                sorteiaEscolha();
                escolhaPedra.setColorFilter(R.color.colorAccent);
                escolhaPapel.clearColorFilter();
                escolhaTesoura.clearColorFilter();
                mostraResultado(escolhaPedra);
            }
        });

        escolhaPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorteiaEscolha();
                escolhaPapel.setColorFilter(R.color.colorAccent);
                escolhaPedra.clearColorFilter();
                escolhaTesoura.clearColorFilter();
                mostraResultado(escolhaPapel);
            }
        });

        escolhaTesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorteiaEscolha();
                escolhaTesoura.setColorFilter(R.color.colorAccent);
                escolhaPapel.clearColorFilter();
                escolhaPedra.clearColorFilter();
                mostraResultado(escolhaTesoura);
            }
        });



    }

    protected void sorteiaEscolha(){
        Random random = new Random();
        int i = random.nextInt(3);
        if(i == 0){
            //escolhe pedra
            escolhaApp.setImageResource(R.drawable.pedra);

        }
        else if(i == 1){
            //escolhe papel
            escolhaApp.setImageResource(R.drawable.papel);
        }
        else{
            //escolhe tesoura
            escolhaApp.setImageResource(R.drawable.tesoura);
        }
    }

    protected void mostraResultado(ImageView escolhaUsuario){
        if(escolhaUsuario.getId() == R.id.imPapel){
            // escolheu papel
            if(escolhaApp.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.pedra).getConstantState())){
                // o app escolheu pedra
                textResultado.setText("Você venceu!!");
                textPontUser.setText(""+(Integer.parseInt(textPontUser.getText().toString()) + 1));
            }
            else if(escolhaApp.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.papel).getConstantState())){
                // o app escolheu papel
                textResultado.setText("Empate!!");
            }
            else{
                // o app escolheu tesoura
                textResultado.setText("Você perdeu!!");
                textPontApp.setText(""+(Integer.parseInt(textPontApp.getText().toString()) + 1));
            }

        }
        else if(escolhaUsuario.getId() == R.id.imPedra){
            //escolheu pedra
            if(escolhaApp.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.pedra).getConstantState())){
                // o app escolheu pedra
                textResultado.setText("Empate!!");
            }
            else if(escolhaApp.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.papel).getConstantState())){
                // o app escolheu papel
                textResultado.setText("Você perdeu!!");
                textPontApp.setText(""+(Integer.parseInt(textPontApp.getText().toString()) + 1));
            }
            else{
                // o app escolheu tesoura
                textResultado.setText("Você venceu!!");
                textPontUser.setText(""+(Integer.parseInt(textPontUser.getText().toString()) + 1));
            }
        }
        else{
            //escolheu tesoura
            if(escolhaApp.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.pedra).getConstantState())){
                // o app escolheu pedra
                textResultado.setText("Você perdeu!!");
                textPontApp.setText(""+(Integer.parseInt(textPontApp.getText().toString()) + 1));
            }
            else if(escolhaApp.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.papel).getConstantState())){
                // o app escolheu papel
                textResultado.setText("Você venceu!!");
                textPontUser.setText(""+(Integer.parseInt(textPontUser.getText().toString()) + 1));

            }
            else{
                // o app escolheu tesoura
                textResultado.setText("Empate!!");
            }
        }

        if(Integer.parseInt(textPontUser.getText().toString()) == 10 || Integer.parseInt(textPontApp.getText().toString()) == 10){
            //jogo acabou
            if(Integer.parseInt(textPontUser.getText().toString()) == 10){
                //usuário ganhou
                textResultado.setText("Fim de Jogo!\nParabéns você ganhou!");
                textPontUser.setText("0");
                textPontApp.setText("0");

                escolhaPedra.clearColorFilter();
                escolhaPapel.clearColorFilter();
                escolhaTesoura.clearColorFilter();
            }
            else{
                //usuário perdeu
                textResultado.setText("Fim de Jogo!\nHahahah você perdeu!");
                textPontUser.setText("0");
                textPontApp.setText("0");

                escolhaPedra.clearColorFilter();
                escolhaPapel.clearColorFilter();
                escolhaTesoura.clearColorFilter();
            }
        }



    }
}
