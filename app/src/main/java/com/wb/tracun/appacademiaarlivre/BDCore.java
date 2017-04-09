package com.wb.tracun.appacademiaarlivre;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tracun on 19/03/2017.
 */

public class BDCore extends SQLiteOpenHelper{

    private static final String NOME_BD = "BDUsuario";
    private static final int VERSAO_BD = 3;


    public BDCore(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {

        bd.execSQL("create table usuarios(email text primary key, nome text not null, senha text not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {

        bd.execSQL("drop table usuarios;");
        onCreate(bd);

    }
}
