package com.sebastianruiz.elcorral;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SEBASTIAN on 19/10/2016.
 */
public class Datos_DB extends SQLiteOpenHelper {

    private final String DATA_BASE_NAME = "datosDB";
    private final int DATA_BASE_VERSION = 1;

    String sqlCreate = "CREATE TABLE usuarios ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "usuario TEXT, contrasenna TEXT, correo TEXT)";
    String sqlCreateProductos = "CREATE TABLE productos ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "producto TEXT, descripcion TEXT, precio TEXT)";

    String getSqlCreateMisFavoritos = "CREATE TABLE favoritos ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "idusuario TEXT, idproducto TEXT)";

    String sqlPromociones1 =  "INSERT INTO productos VALUES (1,'Corralisima 1/2 Libra','Media libra de carne de res asada a la parilla con salsa BBQ. ' , '9500');";
    String sqlPromociones2 =  "INSERT INTO productos VALUES (2,'Corralisima','Tres cuartos de libra canre de res asada a la parrila, con salsa BBQ.' , '11500');";
    String sqlPromociones3 =  "INSERT INTO productos VALUES (3,'Brownie con helado',' Con cobertura de chocolate o arequipe, acompañado de una bola de helado y hojuelas de chocolate.' , '7500');";
    String sqlPromociones4 =  "INSERT INTO productos VALUES (4,'Malteada mediana',' Chocolate, Chocolate Ligtht, Frutos del Bosque Light, Macadamia, Café,Café Mocca, Fresa, Vainilla, Piña,Arequipe. Preparadas con helado super premium Von Glacet Para disfrutar con cuchara.' , '8500');";
    String sqlPromociones5 =  "INSERT INTO productos VALUES (5,'Anillos de cebolla','Crocantes anillos de cebolla apanados.' , '5500');";


    public Datos_DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreateProductos);
        db.execSQL(sqlPromociones1);
        db.execSQL(sqlPromociones2);
        db.execSQL(sqlPromociones3);
        db.execSQL(sqlPromociones4);
        db.execSQL(sqlPromociones5);
        db.execSQL(getSqlCreateMisFavoritos);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Usuarios");
        db.execSQL(sqlCreate);
        db.execSQL("DROP TABLE IF EXIST Productos");
        db.execSQL(sqlCreateProductos);
        db.execSQL("DROP TABLE IF EXIST favoritos");
        db.execSQL(getSqlCreateMisFavoritos);
    }

}
