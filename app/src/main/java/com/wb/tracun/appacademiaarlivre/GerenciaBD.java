package com.wb.tracun.appacademiaarlivre;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Tracun on 19/03/2017.
 */

public class GerenciaBD {

    private SQLiteDatabase bd;

    public GerenciaBD(Context context) {
        BDCore auxBD = new BDCore(context);
        bd = auxBD.getWritableDatabase();

    }

    void teste() {

        Cursor cur = bd.rawQuery("SELECT COUNT(*) FROM usuarios", null);

        if (cur != null) {
            cur.moveToFirst();
            if (cur.getInt(0) == 0) {
                System.out.println("Tabela zerada");
            } else {

                while (cur.moveToNext()) {
                    int i = 0;
                    System.out.println("Possui dados: " + cur.getInt(i));
                    i++;
                }

                System.out.println("Possui dados: " + cur.getCount());
            }
        }
    }


    public String inserir(Usuario usuario) {

        String msg;

        ContentValues valores = new ContentValues();

        valores.put("email", usuario.getEmail());
        valores.put("nome", usuario.getNome());
        valores.put("senha", usuario.getSenha());

        long resultado = bd.insert("usuarios", null, valores);

        if (resultado == -1)
            msg = "Erro ao inserir registro";
        else
            msg = "Adicionado com sucesso !";

        bd.close();

        return msg;

    }

    public Cursor buscaDadoByEmail(String email){
        Cursor cursor;
        String[] campos =  {"email", "nome","senha"};
        String where = "email" + "=" + email;
        cursor = bd.query("usuarios",campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        bd.close();

        return cursor;
    }

    public ArrayList buscaString(){

        String[] colunas = {"email", "nome"};

        String nome = "";
        String email = "";
        ArrayList array = null;

        Cursor cursor = bd.query("usuarios", colunas,null,null,null,null,null);

        if(!(cursor.getCount() <= 0)){
            cursor.moveToFirst();
            array = new ArrayList();
            do{
                email = String.valueOf(cursor.getLong(0));
                nome = cursor.getString(1);

                array.add(email + " " +nome);

            }while(cursor.moveToNext());
        }

        return array;
    }

    public void atualiazar(Usuario usuario){

        ContentValues valores = new ContentValues();
        valores.put("email", usuario.getNome());
        valores.put("nome", usuario.getEmail());
        valores.put("senha", usuario.getSenha());

        bd.update("usuarios",valores,"email = ?", new String[]{""+usuario.getEmail()});
        bd.close();

    }

    public void deletar(Usuario usuario){

        bd.delete("usuarios", "email = "+ usuario.getEmail(), null);
        bd.close();

    }


}

