package com.wb.tracun.appacademiaarlivre;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

public class QrCode extends AppCompatActivity {

    final Activity activity = this;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        imageView = (ImageView) findViewById(R.id.imageView);

        //Site dos QRCODE
        //http://e-lemento.com/meus-qr-codes
    }

    //Definindo o nome das imagens
    String volanteRotacao = "http://e-qr.me/f6e0d5";//"https://www.dropbox.com/s/kquii8krzyv4jnr/Volante%20de%20rotacao.png?dl=0";
    String volanteAlongador = "http://e-qr.me/1d4976";//"https://www.dropbox.com/s/ktbuzgh5q9xre64/Volante%20de%20alongador.png?dl=0";
    String simuladorRemada = "http://e-qr.me/20bf98";//"https://www.dropbox.com/s/61hlkivzkoua07g/Simulador%20remada.png?dl=0";
    String simuladorCavalgada = "http://e-qr.me/16ae9e";//"https://www.dropbox.com/s/tfnz1v4ipgxm2oj/Simulador%20cavalgada.png?dl=0";
    String simuladorCaminhada = "http://e-qr.me/3f643b";//"https://www.dropbox.com/s/b87vdmehhds9b5p/Simulador%20caminhada.png?dl=0";
    String multiEstacao = "http://e-qr.me/cc7784";//"https://www.dropbox.com/s/s360yurqx8fezm4/multi%20estacao%20exercitadora.png?dl=0";
    String eliptico = "http://e-qr.me/518817";//"https://www.dropbox.com/s/k6mqfrc4h5izm0s/Eliptico.png?dl=0";
    String balancoSimuladorSurfe = "http://e-qr.me/1334f6";//"https://www.dropbox.com/s/0t3qf1l07z7sc69/balanco%20simulador%20de%20surfe.png?dl=0";

    String volanteRotacaoURL = "http://imageshack.com/a/img923/6117/7ggw03.png";
    String volanteAlongadorURL = "http://imageshack.com/a/img922/651/kZp6wU.png";
    String simuladorRemadaURL = "http://imageshack.com/a/img923/6893/w0mBPD.png";
    String simuladorCavalgadaURL = "http://imageshack.com/a/img924/2903/ccVk6K.png";
    String simuladorCaminhadaURL = "http://imageshack.com/a/img923/8806/9jXLV1.png";
    String multiEstacaoURL = "http://imageshack.com/a/img924/8696/z5A0Wu.png";
    String elipticoURL = "http://imageshack.com/a/img923/1843/EP7u7v.png";
    String balancoSimuladorSurfeURL = "http://imageshack.com/a/img923/9994/6tQGwF.png";

    public void lerQr(View view){
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "Scanner cancelado", Toast.LENGTH_LONG).show();
            }else{
                //Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();

                if(result.getContents().equals(volanteRotacao)){
                    //Transforma URL em imagemView
                    Picasso.with(this).load(volanteRotacaoURL).into(imageView);
                }else if(result.getContents().equals(volanteAlongador)){
                    //Transforma URL em imagemView
                    Picasso.with(this).load(volanteAlongadorURL).into(imageView);
                }else if(result.getContents().equals(simuladorRemada)){
                    //Transforma URL em imagemView
                    Picasso.with(this).load(simuladorRemadaURL).into(imageView);
                }else if(result.getContents().equals(simuladorCavalgada)){
                    //Transforma URL em imagemView
                    Picasso.with(this).load(simuladorCavalgadaURL).into(imageView);
                }else if(result.getContents().equals(simuladorCaminhada)){
                    //Transforma URL em imagemView
                    Picasso.with(this).load(simuladorCaminhadaURL).into(imageView);
                }else if(result.getContents().equals(multiEstacao)){
                    //Transforma URL em imagemView
                    Picasso.with(this).load(multiEstacaoURL).into(imageView);
                }else if(result.getContents().equals(eliptico)){
                    //Transforma URL em imagemView
                    Picasso.with(this).load(elipticoURL).into(imageView);
                }else if(result.getContents().equals(balancoSimuladorSurfe)){
                    //Transforma URL em imagemView
                    Picasso.with(this).load(balancoSimuladorSurfeURL).into(imageView);
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }




}
