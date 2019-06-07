package com.mateusmatinato.aula06topicos;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Button btnBuscar, btnAtualizar, btnExcluir, btnSalvar;
    private EditText raBuscar, raAluno, emailAluno, nomeAluno, telefoneAluno;
    private TextView tvAcao;
    private TextInputLayout layoutRABuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBuscar = findViewById(R.id.btnBuscar);
        db = openOrCreateDatabase("appCrud", MODE_PRIVATE, null);

        raBuscar = findViewById(R.id.raBuscar);
        raAluno = findViewById(R.id.raAluno);
        nomeAluno = findViewById(R.id.nomeAluno);
        emailAluno = findViewById(R.id.emailAluno);
        telefoneAluno = findViewById(R.id.telefoneAluno);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnExcluir = findViewById(R.id.btnExcluir);

        layoutRABuscar = findViewById(R.id.layoutRaBuscar);


        tvAcao = findViewById(R.id.tvAcao);

        mostraInfos(false);

        db.execSQL("CREATE TABLE IF NOT EXISTS alunos (ra VARCHAR PRIMARY KEY, nome VARCHAR, email VARCHAR, telefone VARCHAR)");

        try {
            db.execSQL("INSERT INTO alunos (ra,nome,email,telefone) VALUES ('161041108','Mateus Matinato','mateus.matinato@unesp.br','997712491')");
            db.execSQL("INSERT INTO alunos (ra,nome,email,telefone) VALUES ('161041109','Jonatan Rodrigues','jonatan.rodrigues@unesp.br','997721491')");
            db.execSQL("INSERT INTO alunos (ra,nome,email,telefone) VALUES ('161041110','João Rosa','joao.rosa@unesp.br','9817221991')");

        } catch (Exception e) {

        }

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Deve buscar por RA
                String ra = raBuscar.getText().toString();
                if (ra.length() == 9) {
                    mostraInfos(true);
                    layoutRABuscar.setErrorEnabled(false);
                    Cursor cursor = db.rawQuery("SELECT * FROM alunos WHERE ra = ?", new String[]{ra});
                    if (cursor.getCount() == 0) {
                        //não achou ninguém com o RA
                        Toast.makeText(getApplicationContext(), "Aluno não encontrado", Toast.LENGTH_LONG).show();
                        tvAcao.setText("Cadastrar Aluno");
                        btnExcluir.setVisibility(View.INVISIBLE);
                        btnAtualizar.setVisibility(View.INVISIBLE);
                        btnSalvar.setVisibility(View.VISIBLE);

                        raAluno.setEnabled(true);
                        raAluno.setText(raBuscar.getText().toString());
                        nomeAluno.setText("");
                        emailAluno.setText("");
                        telefoneAluno.setText("");

                    } else {
                        tvAcao.setText("Editar Aluno");
                        btnExcluir.setVisibility(View.VISIBLE);
                        btnAtualizar.setVisibility(View.VISIBLE);
                        btnSalvar.setVisibility(View.INVISIBLE);

                        cursor.moveToFirst();
                        String nome = cursor.getString(cursor.getColumnIndex("nome"));
                        String raBase = cursor.getString(cursor.getColumnIndex("ra"));
                        String email = cursor.getString(cursor.getColumnIndex("email"));
                        String telefone = cursor.getString(cursor.getColumnIndex("telefone"));

                        raAluno.setEnabled(false);
                        raAluno.setText(raBase);
                        nomeAluno.setText(nome);
                        emailAluno.setText(email);
                        telefoneAluno.setText(telefone);


                    }
                }
                else{
                    //Digitou RA inválido

                    mostraInfos(false);
                    layoutRABuscar.setErrorEnabled(true);
                    layoutRABuscar.setError("Digite um RA válido (9 dígitos).");
                }
            }
        });

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Atualiza os dados
                String nome = nomeAluno.getText().toString();
                String ra = raAluno.getText().toString();
                String email = emailAluno.getText().toString();
                String telefone = telefoneAluno.getText().toString();
                try {
                    db.execSQL("UPDATE  alunos SET nome = ?, email = ?, telefone = ? WHERE ra = ?", new String[]{nome, email, telefone, ra});
                    Toast.makeText(getApplicationContext(), "Aluno " + ra + " atualizado.", Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(), "Erro ao atualizar.", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Salvar novo usuário
                String nome = nomeAluno.getText().toString();
                String ra = raAluno.getText().toString();
                String email = emailAluno.getText().toString();
                String telefone = telefoneAluno.getText().toString();
                try {
                    db.execSQL("INSERT INTO  alunos (ra,nome,email,telefone) VALUES (?,?,?,?)", new String[]{ra, nome, email, telefone});
                    Toast.makeText(getApplicationContext(), "Aluno " + ra + " cadastrado.", Toast.LENGTH_LONG).show();
                    raBuscar.setText(raAluno.getText().toString());
                    btnBuscar.callOnClick();
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(), "Nãoi foi possível cadastrar.", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Excluir usuário
                String ra = raAluno.getText().toString();
                try {
                    db.execSQL("DELETE from alunos WHERE ra = ?", new String[]{ra});
                    Toast.makeText(getApplicationContext(), "Aluno " + ra + " excluído.", Toast.LENGTH_LONG).show();
                    nomeAluno.setText("");
                    emailAluno.setText("");
                    telefoneAluno.setText("");
                    raAluno.setText("");

                    mostraInfos(false);

                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(), "Não foi possível excluir.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    protected void mostraInfos(boolean mostrar) {
        if (mostrar) {
            //mostra os campos
            findViewById(R.id.btnExcluir).setVisibility(View.VISIBLE);
            findViewById(R.id.btnSalvar).setVisibility(View.VISIBLE);
            findViewById(R.id.btnAtualizar).setVisibility(View.VISIBLE);
            findViewById(R.id.tvAcao).setVisibility(View.VISIBLE);
            findViewById(R.id.textLayoutNome).setVisibility(View.VISIBLE);
            findViewById(R.id.textLayoutEmail).setVisibility(View.VISIBLE);
            findViewById(R.id.textLayoutRA).setVisibility(View.VISIBLE);
            findViewById(R.id.textLayoutTelefone).setVisibility(View.VISIBLE);
        } else {
            //oculta
            findViewById(R.id.btnExcluir).setVisibility(View.INVISIBLE);
            findViewById(R.id.btnSalvar).setVisibility(View.INVISIBLE);
            findViewById(R.id.btnAtualizar).setVisibility(View.INVISIBLE);
            findViewById(R.id.tvAcao).setVisibility(View.INVISIBLE);
            findViewById(R.id.textLayoutNome).setVisibility(View.INVISIBLE);
            findViewById(R.id.textLayoutEmail).setVisibility(View.INVISIBLE);
            findViewById(R.id.textLayoutRA).setVisibility(View.INVISIBLE);
            findViewById(R.id.textLayoutTelefone).setVisibility(View.INVISIBLE);
        }
    }
}
