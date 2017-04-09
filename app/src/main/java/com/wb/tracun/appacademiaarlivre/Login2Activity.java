package com.wb.tracun.appacademiaarlivre;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login2Activity extends AppCompatActivity {

    private LoginButton loginBotao;
    private CallbackManager callbackManager;
    EditText txtEmail;
    EditText txtPassword;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //Verifica a quantidade de usuarios cadastrados no BD
        GerenciaBD gBD = new GerenciaBD(this);
        gBD.teste();

        //Verifica se ha alguem logado com o facebook
        usuarioLogadoFacebook();

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnEntrar = (Button) findViewById(R.id.btnLogin);

        //Login facebook
        loginBotao = (LoginButton) findViewById(R.id.loginButton);
        callbackManager = CallbackManager.Factory.create();
        loginBotao.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Vá para a tela inicial
                goMain();
                Toast.makeText(getApplicationContext(), "Entrei no face", Toast.LENGTH_LONG);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Operação de login cancelada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Erro: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //pegando hashKey

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {

        }
        catch (NoSuchAlgorithmException e) {

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dada){
        super.onActivityResult(requestCode, resultCode, dada);
        callbackManager.onActivityResult(requestCode,resultCode,dada);
    }

    public void goMain(){
        Intent intencao = new Intent(this, MainActivity.class);
        //Fecha as atividades anteriores, isso evita que quando o botao de voltar for pressionado, o usuario volte a tela de login, no caso, o app se encerra
        intencao.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intencao);
    }

    public void cadastro(View view){
        Intent intencao = new Intent(this, CadastroActivity.class);
        startActivity(intencao);
    }

    public void usuarioLogadoFacebook(){
        if(AccessToken.getCurrentAccessToken() != null){
            goMain();
        }

    }

    public void validarLogin(View view){

        if(!isValidEmail()){
            txtEmail.setError("Email inválido");
        }else{

            if(!isValidPassword()){
                txtPassword.setError("Senha deve possuir mais de 4 caracteres");
            }else{

                GerenciaBD gerenciaBD = new GerenciaBD(this);

                gerenciaBD.buscaDadoByEmail(txtEmail.getText().toString());

                Toast.makeText(this, "Logado com sucesso !", Toast.LENGTH_SHORT);

            }
        }






    }

    public boolean isValidEmail(){
        if(!txtEmail.getText().toString().contains("@")){
            return false;
        }
        return true;
    }

    public boolean isValidPassword(){
        if(txtPassword.getText().toString().isEmpty() || txtPassword.getText().length() < 4){
            return false;
        }
        return true;
    }

}
