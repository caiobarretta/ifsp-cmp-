package br.com.ifspcmp.mappedwallet.data.dao.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DAO extends SQLiteOpenHelper {

    public DAO(@Nullable Context context) { super(context, "Mappedwallet", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE Lancamento ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "descricao TEXT NOT NULL, " +
                "dataPagamento TEXT, " +
                "valor TEXT, " +
                "lat TEXT, " +
                "lng TEXT, " +
                "tipoLancamento INTERGER);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
