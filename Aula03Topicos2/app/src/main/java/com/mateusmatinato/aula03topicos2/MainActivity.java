package com.mateusmatinato.aula03topicos2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnConfirmar, btnVoltar;
    private RadioGroup radioGoleiro, radioZagueiro, radioVolante, radioMeia, radioAtacante;
    private TextView tvEscalacao;

    private RadioButton rbBuffon, rbDeGea, rbSilva, rbRamos, rbKroos, rbModric, rbDeBruyne, rbCoutinho, rbMessi, rbNeymar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnVoltar = findViewById(R.id.btnVoltar);
        radioGoleiro = findViewById(R.id.radioGroupGoleiros);
        radioZagueiro = findViewById(R.id.radioGroupZagueiros);
        radioVolante = findViewById(R.id.radioGroupVolantes);
        radioMeia = findViewById(R.id.radioGroupMeias);
        radioAtacante = findViewById(R.id.radioGroupAtacantes);

        rbBuffon = findViewById(R.id.rbBuffon);
        rbDeGea = findViewById(R.id.rbDeGea);
        rbSilva = findViewById(R.id.rbSilva);
        rbRamos = findViewById(R.id.rbRamos);
        rbKroos = findViewById(R.id.rbKroos);
        rbModric = findViewById(R.id.rbModric);
        rbDeBruyne = findViewById(R.id.rbDeBruyne);
        rbCoutinho = findViewById(R.id.rbCoutinho);
        rbMessi = findViewById(R.id.rbMessi);
        rbNeymar = findViewById(R.id.rbNeymar);

        tvEscalacao = findViewById(R.id.tvEscalacao);



        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                escalaTime(); // coloca as fotos nas imagens que estão ocultadas
                changeInfos(1); // tipoView = 1 significa que é pra ocultar as informações dos jogaores e mostrar o campo

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeInfos(0); // tipoView = 0 significa que é pra ocultar o campo e mostrar as infos de novo

            }
        });
    }

    protected void changeInfos(int tipoView){
        int ViewInfo = 0;
        int ViewCampo = 0;
        if(tipoView == 1){
            // é pra ocultar as informações de escalação e mostrar o campo
            ViewInfo = View.INVISIBLE;
            ViewCampo = View.VISIBLE;
        }
        else{
            ViewInfo = View.VISIBLE;
            ViewCampo = View.INVISIBLE;
        }

        //Oculta logo
        findViewById(R.id.imgLogo).setVisibility(ViewInfo);

        //Oculta radioGroups
        findViewById(R.id.radioGroupAtacantes).setVisibility(ViewInfo);
        findViewById(R.id.radioGroupGoleiros).setVisibility(ViewInfo);
        findViewById(R.id.radioGroupMeias).setVisibility(ViewInfo);
        findViewById(R.id.radioGroupVolantes).setVisibility(ViewInfo);
        findViewById(R.id.radioGroupZagueiros).setVisibility(ViewInfo);

        //Oculta textViews
        findViewById(R.id.tvAtacantes).setVisibility(ViewInfo);
        findViewById(R.id.tvGoleiros).setVisibility(ViewInfo);
        findViewById(R.id.tvMeias).setVisibility(ViewInfo);
        findViewById(R.id.tvVolantes).setVisibility(ViewInfo);
        findViewById(R.id.tvZagueiros).setVisibility(ViewInfo);

        //Oculta divisórias
        findViewById(R.id.divider).setVisibility(ViewInfo);
        findViewById(R.id.divider2).setVisibility(ViewInfo);
        findViewById(R.id.divider3).setVisibility(ViewInfo);
        findViewById(R.id.divider4).setVisibility(ViewInfo);

        //Oculta confirmar
        findViewById(R.id.btnConfirmar).setVisibility(ViewInfo);

        //Mostra botão voltar
        findViewById(R.id.btnVoltar).setVisibility(ViewCampo);

        //Mostra campo e imagem dos jogadores
        findViewById(R.id.imgCampo).setVisibility(ViewCampo);
        findViewById(R.id.imgGoleiro).setVisibility(ViewCampo);
        findViewById(R.id.imgZagueiro).setVisibility(ViewCampo);
        findViewById(R.id.imgVolante).setVisibility(ViewCampo);
        findViewById(R.id.imgMeia).setVisibility(ViewCampo);
        findViewById(R.id.imgAtacante).setVisibility(ViewCampo);

        //Mostra escalação e preço
        findViewById(R.id.tvEscalacaoTitulo).setVisibility(ViewCampo);
        findViewById(R.id.tvEscalacao).setVisibility(ViewCampo);


    }

    protected void escalaTime(){
        ImageView imgGoleiro = findViewById(R.id.imgGoleiro);
        ImageView imgZagueiro = findViewById(R.id.imgZagueiro);
        ImageView imgVolante = findViewById(R.id.imgVolante);
        ImageView imgMeia = findViewById(R.id.imgMeia);
        ImageView imgAtacante = findViewById(R.id.imgAtacante);

        String escalacao = "";

        //Escala goleiros
        if(radioGoleiro.getCheckedRadioButtonId() == R.id.rbBuffon){
            imgGoleiro.setImageResource(R.drawable.buffon);
            escalacao += "<span style='color: #ff6f00'>GOL:</span> " + rbBuffon.getText() + "<br>";
        }
        else if(radioGoleiro.getCheckedRadioButtonId() == R.id.rbDeGea){
            imgGoleiro.setImageResource(R.drawable.degea);
            escalacao += "<span style='color: #ff6f00'>GOL:</span> " + rbDeGea.getText() + "<br>";
        }
        else{
            imgGoleiro.setImageResource(R.drawable.carta);
            escalacao += "<span style='color: #ff6f00'>GOL:</span> Não selecionado<br>";
        }

        //Escala zagueiros
        if(radioZagueiro.getCheckedRadioButtonId() == R.id.rbSilva){
            imgZagueiro.setImageResource(R.drawable.thiagosilva);
            escalacao += "<span style='color: #ff6f00'>ZAG:</span> " + rbSilva.getText() + "<br>";
        }
        else if(radioZagueiro.getCheckedRadioButtonId() == R.id.rbRamos){
            imgZagueiro.setImageResource(R.drawable.sergioramos);
            escalacao += "<span style='color: #ff6f00'>ZAG:</span> " + rbRamos.getText() + "<br>";
        }
        else{
            imgZagueiro.setImageResource(R.drawable.carta);
            escalacao += "<span style='color: #ff6f00'>ZAG:</span> Não selecionado<br>";
        }

        //Escala volantes
        if(radioVolante.getCheckedRadioButtonId() == R.id.rbKroos){
            imgVolante.setImageResource(R.drawable.kroos);
            escalacao += "<span style='color: #ff6f00'>VOL:</span> " + rbKroos.getText() + "<br>";
        }
        else if(radioVolante.getCheckedRadioButtonId() == R.id.rbModric){
            imgVolante.setImageResource(R.drawable.modric);
            escalacao += "<span style='color: #ff6f00'>VOL:</span> " + rbModric.getText() + "<br>";
        }
        else{
            imgVolante.setImageResource(R.drawable.carta);
            escalacao += "<span style='color: #ff6f00'>VOL:</span> Não selecionado<br>";
        }

        //Escala meias
        if(radioMeia.getCheckedRadioButtonId() == R.id.rbDeBruyne){
            imgMeia.setImageResource(R.drawable.debruyne);
            escalacao += "<span style='color: #ff6f00'>MEI:</span> " + rbDeBruyne.getText() + "<br>";
        }
        else if(radioMeia.getCheckedRadioButtonId() == R.id.rbCoutinho){
            imgMeia.setImageResource(R.drawable.coutinho);
            escalacao += "<span style='color: #ff6f00'>MEI:</span> " + rbCoutinho.getText() + "<br>";
        }
        else{
            imgMeia.setImageResource(R.drawable.carta);
            escalacao += "<span style='color: #ff6f00'>MEI:</span> Não selecionado<br>";
        }

        //Escala atacantes
        if(radioAtacante.getCheckedRadioButtonId() == R.id.rbMessi){
            imgAtacante.setImageResource(R.drawable.messi);
            escalacao += "<span style='color: #ff6f00'>ATA:</span> " + rbMessi.getText() + "<br>";
        }
        else if(radioAtacante.getCheckedRadioButtonId() == R.id.rbNeymar){
            imgAtacante.setImageResource(R.drawable.neymar);
            escalacao += "<span style='color: #ff6f00'>ATA:</span> " + rbNeymar.getText() + "<br>";
        }
        else{
            imgAtacante.setImageResource(R.drawable.carta);
            escalacao += "<span style='color: #ff6f00'>ATA:</span> Não selecionado<br>";
        }

        tvEscalacao.setText(Html.fromHtml(escalacao,  Html.FROM_HTML_MODE_LEGACY));


    }
}

