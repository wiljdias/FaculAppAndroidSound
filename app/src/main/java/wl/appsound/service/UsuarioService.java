package wl.appsound.service;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import wl.appsound.dao.UsuarioDAO;

public class UsuarioService {
    private SQLiteDatabase db;
    private UsuarioDAO usrDAO;

    public UsuarioService(Context context) {
        usrDAO = new UsuarioDAO(context);
    }

    public String insereDado(String nome, String ra) {
        ContentValues valores;
        long resultado;
        db = usrDAO.getWritableDatabase();
        valores = new ContentValues();
        valores.put(UsuarioDAO.NOME, nome);
        valores.put(UsuarioDAO.RA, ra);
        resultado = db.insert(UsuarioDAO.TABELA, null, valores);
        db.close();
        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";


    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {usrDAO.ID, usrDAO.RA, usrDAO.NOME};
        db = usrDAO.getReadableDatabase();
        cursor = db.query(usrDAO.TABELA, campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id) {
        Cursor cursor;
        String[] campos = {usrDAO.ID, usrDAO.NOME, usrDAO.RA};
        String where = usrDAO.ID + "=" + id;
        db = usrDAO.getReadableDatabase();
        cursor = db.query(usrDAO.TABELA, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(int id, String nome, String ra) {
        ContentValues valores;
        String where;
        db = usrDAO.getWritableDatabase();
        where = UsuarioDAO.ID + "=" + id;
        valores = new ContentValues();
        valores.put(UsuarioDAO.NOME, nome);
        valores.put(UsuarioDAO.RA, ra);
        db.update(UsuarioDAO.TABELA, valores, where, null);
        db.close();
    }

    public void deletaRegistro(int id) {
        String where = UsuarioDAO.ID + "=" + id;
        db = usrDAO.getReadableDatabase();
        db.delete(UsuarioDAO.TABELA, where, null);
        db.close();
    }


}