package com.sebastianruiz.elcorral;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
            "producto TEXT, descripcion TEXT, precio TEXT, imagen TEXT)";

    String sqlCreateProductos2 = "CREATE TABLE productos2 ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "producto TEXT, descripcion TEXT, precio TEXT, imagen TEXT)";

    String getSqlCreateMisFavoritos = "CREATE TABLE favoritos ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "idusuario TEXT, idproducto TEXT)";





  /*  String sqlPromociones1 =  "INSERT INTO productos VALUES (1,'Corralisima 1/2 Libra','Media libra de carne de res asada a la parilla con salsa BBQ. ' , '9500');";
    String sqlPromociones2 =  "INSERT INTO productos VALUES (2,'Corralisima','Tres cuartos de libra canre de res asada a la parrila, con salsa BBQ.' , '11500');";
    String sqlPromociones3 =  "INSERT INTO productos VALUES (3,'Brownie con helado',' Con cobertura de chocolate o arequipe, acompañado de una bola de helado y hojuelas de chocolate.' , '7500');";
    String sqlPromociones4 =  "INSERT INTO productos VALUES (4,'Malteada mediana',' Chocolate, Chocolate Ligtht, Frutos del Bosque Light, Macadamia, Café,Café Mocca, Fresa, Vainilla, Piña,Arequipe. Preparadas con helado super premium Von Glacet Para disfrutar con cuchara.' , '8500');";
    String sqlPromociones5 =  "INSERT INTO productos VALUES (5,'Anillos de cebolla','Crocantes anillos de cebolla apanados.' , '5500');";
 */
    List<String> Productos = new ArrayList();



    public Datos_DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        Productos.add("INSERT INTO productos VALUES (1,'Corralisima 1/2 Libra','"+ R.string.corralisima_descripcion  +" ', '9500','"+ R.drawable.tres_cuartos_de_libra +"');");
        Productos.add("INSERT INTO productos VALUES (2,'Corralisima 3/4 Libra','"+ R.string.corralisimaMediaLibra_promo +"' , '10500','"+ R.drawable.media_libra +"');");
        Productos.add("INSERT INTO productos VALUES (3,'Corral Casera','"+ R.string.corralisimaCasera_descripcion +" ', '1000','"+ R.drawable.casera +"');");
        Productos.add("INSERT INTO productos VALUES (4,'Corralisima Todoterreno','"+ R.string.corralisimaTodoterreno_descripcion +"' , '12000','"+ R.drawable.corralisima +"');");
        Productos.add("INSERT INTO productos VALUES (5,'Vaquero Especial','"+ R.string.Vaquero_descripcion +"' , '8500','"+ R.drawable.vaquero +"');");
        Productos.add("INSERT INTO productos VALUES (6,'Brownie con helado','"+ R.string.brownieConHelado_promo +"' , '10500','"+ R.drawable.brownie_con_helado +"');");
        Productos.add("INSERT INTO productos VALUES (7,'Malteada mediana','"+ R.string.malteadaMediana_promo +" ', '9500','"+ R.drawable.malteada_mediana +"');");
        Productos.add("INSERT INTO productos VALUES (8,'Malteada pequeña','"+ R.string.malteadaPequenna_descripcion +"' , '11500','"+ R.drawable.malteada_pequena +"');");
        Productos.add("INSERT INTO productos VALUES (9,'Pie','"+ R.string.pie_descripcion +"' , '10500','"+ R.drawable.desertpie +"');");
        Productos.add("INSERT INTO productos VALUES (10,'Anillos de cebolla','"+ R.string.anillosDeCebolla_promo +" ', '9500','"+ R.drawable.anillos_de_cebolla +"');");
        Productos.add("INSERT INTO productos VALUES (11,'papas en espiral','"+ R.string.papasEspiral_descripcion +" ', '8500','"+ R.drawable.papas_en_espiral +"');");
        Productos.add("INSERT INTO productos VALUES (12,'Papas a la francesa','"+ R.string.papasALaFrancesa_descripcion +"' , '8000','"+ R.drawable.papas_a_la_francesa +"');");
        Productos.add("INSERT INTO productos VALUES (13,'Lomitos de pollo','"+ R.string.LomitosDePollo_descripcion +" ', '9500','"+ R.drawable.lomitos_de_pollo +"');");


        db.execSQL(sqlCreate);
        db.execSQL(sqlCreateProductos);
        db.execSQL(sqlCreateProductos2);

        db.execSQL(getSqlCreateMisFavoritos);

        for(int i=0; i<Productos.size();i++){
            db.execSQL(Productos.get(i));
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Usuarios");
        db.execSQL(sqlCreate);
        db.execSQL("DROP TABLE IF EXIST Productos");
        db.execSQL(sqlCreateProductos);
        db.execSQL("DROP TABLE IF EXIST Productos2");
        db.execSQL(sqlCreateProductos2);
        db.execSQL("DROP TABLE IF EXIST favoritos");
        db.execSQL(getSqlCreateMisFavoritos);
    }

}
