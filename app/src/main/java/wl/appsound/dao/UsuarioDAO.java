package wl.appsound.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UsuarioDAO extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "dados_usuarios.db";
    public static final String TABELA = "usuarios";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String RA = "RA";
    private static final int VERSAO = 1;

    public UsuarioDAO(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + "(" + ID + " integer primary key autoincrement, " + NOME + " text," + RA + " text" + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }

}

