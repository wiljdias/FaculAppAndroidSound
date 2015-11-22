package wl.appsound;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import wl.appsound.dao.UsuarioDAO;
import wl.appsound.service.UsuarioService;


public class AboutActivity extends Activity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        listarDados();

        Button botao = (Button) findViewById(R.id.buttonSave);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioService crud = new UsuarioService(getBaseContext());
                EditText ra = (EditText) findViewById((R.id.editTextRegister));
                EditText nomeAluno = (EditText) findViewById(R.id.editTextStudentName);
                String nomeString = nomeAluno.getText().toString();
                String raString = ra.getText().toString();
                String resultado;
                resultado = crud.insereDado(nomeString, raString);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                listarDados();
            }
        });
    }

    private void listarDados() {
        UsuarioService crud = new UsuarioService(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[]{UsuarioDAO.RA, UsuarioDAO.NOME};
        int[] idViews = new int[]{R.id.raUsuario, R.id.nomeUsuario};
        SimpleCursorAdapter adaptador = null;
        try {
            adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.grid_users, cursor, nomeCampos, idViews, 0);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        /*lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioDAO.ID));
                Intent intent = new Intent(AboutActivity.this, UsuarioActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });*/
    }

}

