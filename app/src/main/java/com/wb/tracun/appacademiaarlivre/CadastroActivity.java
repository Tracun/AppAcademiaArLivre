package com.wb.tracun.appacademiaarlivre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtSenha;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

    }

    public void cadastro(View view){

        if(!txtNome.getText().toString().isEmpty()){

            if(!isValidEmail()){
                txtEmail.setError("Email inválido");
            }else{

                if(!isValidPassword()){
                    txtSenha.setError("Senha deve possuir mais de 4 caracteres");
                }

            }
        }

        if(isValidEmail() && isValidPassword()){
            Usuario usuario = null;

            String email = txtEmail.getText().toString();
            String nome = txtNome.getText().toString();
            String senha = txtSenha.getText().toString();

            usuario = new Usuario(nome, email, senha);

            GerenciaBD gerenciaBD = new GerenciaBD(this);

            Toast.makeText(this, gerenciaBD.inserir(usuario), Toast.LENGTH_SHORT).show();

            Intent intencao = new Intent(this, Login2Activity.class);
            startActivity(intencao);
        }

    }

    public boolean isValidEmail(){
        if(!txtEmail.getText().toString().contains("@")){
            return false;
        }
        return true;
    }

    public boolean isValidPassword(){
        if(txtSenha.getText().toString().length() < 4){
            return false;
        }
        return true;
    }



}
