package com.mateusmatinato.aula03topicos;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public RadioGroup rgSabor;
    public CheckBox cbBordaRecheada, cbBacon, cbMilho;
    public TextView tvPreco;
    public Button btFinalizar;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rgSabor = findViewById(R.id.rgSabor);
        cbBordaRecheada = findViewById(R.id.cbBordaRecheada);
        cbBacon = findViewById(R.id.cbBacon);
        cbMilho = findViewById(R.id.cbMilho);
        tvPreco = findViewById(R.id.tvPreco);
        btFinalizar = findViewById(R.id.btFinalizar);

        final DecimalFormat formatoPreco = new DecimalFormat("##.00");

        rgSabor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                double PrecoAtual = calculaPrecosOpcionais();
                if(checkedId == R.id.rbCalabresa){
                    //selecionou calabresa, soma 25
                    tvPreco.setText("R$ "+formatoPreco.format(PrecoAtual + 25.00));
                }
                else if(checkedId == R.id.rbFrango){
                    //selecionoufrango, soma 30
                    tvPreco.setText("R$ "+formatoPreco.format(PrecoAtual + 30.0));
                }
                else if(checkedId == R.id.rbMuçarela){
                    //selecionou muçarela, soma 20
                    tvPreco.setText("R$ "+formatoPreco.format(PrecoAtual + 20.00));
                }
                else{
                    //selecionou portuguesa, soma 30
                    tvPreco.setText("R$ "+formatoPreco.format(PrecoAtual + 30.00));

                }
            }
        });

        cbBordaRecheada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                double precoAtual = calculaPrecosOpcionais() + calculaPrecoSabor();
                // selecionou borda recheada
                    tvPreco.setText("R$ "+formatoPreco.format(precoAtual));
            }
        });

        cbBacon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                double precoAtual = calculaPrecosOpcionais() + calculaPrecoSabor();
                // selecionou bacon
                    tvPreco.setText("R$ "+formatoPreco.format(precoAtual));
            }
        });

        cbMilho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                double precoAtual = calculaPrecosOpcionais() + calculaPrecoSabor();
                // selecionou milho
                    tvPreco.setText("R$ "+formatoPreco.format(precoAtual));
            }
        });

        btFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPedido modal = new DialogPedido();
                String mensagem = "";
                if(rgSabor.getCheckedRadioButtonId() == R.id.rbCalabresa){
                    mensagem += "<b>Sabor:</b> Calabresa<br>";
                }
                else if(rgSabor.getCheckedRadioButtonId() == R.id.rbFrango){
                    mensagem += "<b>Sabor:</b> Frango<br>";
                }
                else if(rgSabor.getCheckedRadioButtonId() == R.id.rbPortuguesa){
                    mensagem += "<b>Sabor:</b> Portuguesa<br>";
                }
                else if(rgSabor.getCheckedRadioButtonId() == R.id.rbMuçarela){
                    mensagem += "<b>Sabor:</b> Muçarela<br>";
                }
                else{
                    mensagem += "<b>Sabor</b>: Não escolhido<br>";
                }

                if(cbBacon.isChecked() || cbBordaRecheada.isChecked() || cbMilho.isChecked()) {
                    mensagem += "<br><b>Opcionais:</b><br>";
                }

                if(cbBacon.isChecked()){
                    mensagem += " - Bacon<br>";
                }
                if(cbBordaRecheada.isChecked()){
                    mensagem += " - Borda Recheada<br>";
                }
                if(cbMilho.isChecked()){
                    mensagem += " - Milho<br>";
                }

                mensagem += "<br><b>Preço:</b> " + tvPreco.getText();
                mensagem += "<br><b><p style='text-align: center; color: #9A0007;'>Pedido efetuado com sucesso</p></b>";



                modal.setMensagem(mensagem);
                modal.show(getSupportFragmentManager(), "Finalizar Pedido");
            }
        });


    }


    double calculaPrecosOpcionais(){
        double preco = 0;
        if(cbMilho.isChecked()){
            preco += 3;
        }

        if(cbBacon.isChecked()){
            preco += 5;
        }

        if(cbBordaRecheada.isChecked()){
            preco += 8;
        }

        return preco;
    }

    double calculaPrecoSabor(){
        double preco = 0;
        if(rgSabor.getCheckedRadioButtonId() == R.id.rbCalabresa){
            preco = 25;
        }
        else if(rgSabor.getCheckedRadioButtonId() == R.id.rbFrango){
            preco = 30;
        }
        else if(rgSabor.getCheckedRadioButtonId() == R.id.rbMuçarela){
            preco = 20;
        }
        else if(rgSabor.getCheckedRadioButtonId() == R.id.rbPortuguesa){
            preco = 30;
        }

        return preco;
    }

    public static void mostraMensagem(View view){







    }
}
