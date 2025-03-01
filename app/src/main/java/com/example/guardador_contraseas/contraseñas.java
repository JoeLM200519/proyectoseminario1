package com.example.guardador_contraseas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class contraseñas {
    //agregamos clases del helper
    private SQLiteDatabase db;
    private DataBaseHelper dbhelper;

    public contraseñas (Context context){
        dbhelper = new DataBaseHelper(context);
        db = dbhelper.getWritableDatabase();
    }

    //GENERAMOS EL PROCESO CRUD.

    //insertar medico
    public long insertarcontraseña(String user, String proveedor, String contraseña){
        ContentValues contenido = new ContentValues();
        contenido.put(DataBaseHelper.USUARIO, user);
        contenido.put(DataBaseHelper.PROVEEDOR, proveedor);
        contenido.put(DataBaseHelper.CONTRASEÑA, contraseña);
        return db.insert(DataBaseHelper.NAME_TABLE, null, contenido);
    }

    public List<String> obtenercontraseña(){
        List<String> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DataBaseHelper.NAME_TABLE, null);

        while(cursor.moveToNext()){
            String contraseña = cursor.getInt(0) + " - " +
                    cursor.getString(1) + " - " +
                    cursor.getString(2) +  " - " +
                    cursor.getString(3);
            lista.add(contraseña);
        }
        cursor.close();
        return lista;

    }

    public int editarcontraseña(int id, String user, String proveedor, String contraseña){

        ContentValues contenido = new ContentValues();
        contenido.put(DataBaseHelper.USUARIO, user);
        contenido.put(DataBaseHelper.PROVEEDOR, proveedor);
        contenido.put(DataBaseHelper.CONTRASEÑA, contraseña);

        return db.update(DataBaseHelper.NAME_TABLE, contenido, "idcontraseña=?", new String[]{String.valueOf(id)});

    }

    public int eliminarcontraseña(int id){

        return db.delete(DataBaseHelper.NAME_TABLE, "idcontraseña=?", new String[]{String.valueOf(id)});

    }
}
