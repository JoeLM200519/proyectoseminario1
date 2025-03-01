package com.example.guardador_contraseas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="BDcontras.db"; //Nombre base de datos

    private static final int DATABASE_VERSION = 1; //Version de Data

    //Definimos tablas y columnas
    public static final String NAME_TABLE = "contraseñas";
    public static final String ID_CONTRASEÑA = "idcontraseña";
    public static final String USUARIO = "usuario";
    public static final String PROVEEDOR = "proveedor";
    public static final  String CONTRASEÑA = "contraseñas";


    //crear base de datos y tablas

    private static final String CREATE_TABLE_CONTRASEÑAS =
            "CREATE TABLE " + NAME_TABLE + " (" +
                    ID_CONTRASEÑA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USUARIO + " TEXT, " +
                    PROVEEDOR + " TEXT, " +
                    CONTRASEÑA + " TEXT);";

    //crear DB
    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_CONTRASEÑAS); //crear la tabla
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){
        db.execSQL("DROP TABLE IF EXISTS " + NAME_TABLE);
        onCreate(db);
    }

}
