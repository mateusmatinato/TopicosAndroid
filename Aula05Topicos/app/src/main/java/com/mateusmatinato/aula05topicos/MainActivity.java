package com.mateusmatinato.aula05topicos;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAbrirDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAbrirDialog = findViewById(R.id.btnAbrirDialog);
        btnAbrirDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criaDialog("Título","Esse é um Dialog teste.");
            }
        });

    }

    protected void criaDialog(String title, String msg){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setCancelable(false);
        dialog.setIcon(android.R.drawable.ic_dialog_info);

        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //CLicou no sim, abre o toast
                criaToast("Você clicou no sim!");
            }
        });


        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Clicou no não, abre o toast com não
                criaToast("Você clicou no não!");
            }
        });

        dialog.create();
        dialog.show();
    }

    protected void criaToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

}
