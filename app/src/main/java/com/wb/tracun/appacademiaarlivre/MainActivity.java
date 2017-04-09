package com.wb.tracun.appacademiaarlivre;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView lblNome;
    ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblNome = (TextView) findViewById(R.id.lblNome);
        imgProfile = (ImageView) findViewById(R.id.imgProfile);

        if(AccessToken.getCurrentAccessToken() == null){
            goLogin();
        }else if(AccessToken.getCurrentAccessToken() != null){
            carregarDadosFB(this);
        }

    }

    public void goLogin() {
        Intent intencao = new Intent(this, Login2Activity.class);
        //Fecha as atividades anteriores, isso evita que quando o botao de voltar for pressionado, o usuario vá para o main, no caso, o app se encerra
        intencao.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intencao);

    }

    public void logOut(View view){
        LoginManager.getInstance().logOut();
        goLogin();
    }

    public void telaQr(View view){
        Intent intencao = new Intent(this, QrCode.class);
        startActivity(intencao);
    }

    private void carregarDadosFB(Context context){

        if (Profile.getCurrentProfile() != null) {
            Profile profile = Profile.getCurrentProfile();
            String firstName = profile.getFirstName();
            String lastName = profile.getLastName();
            String photoUrl = profile.getProfilePictureUri(120, 120).toString();

            lblNome.setText("Bem vindo " + firstName + " " + lastName);

            //Transforma URL em imagemView
            Picasso.with(context).load(photoUrl).into(imgProfile);
        }

    }
}
