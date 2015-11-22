package wl.appsound;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends Activity {

    MediaPlayer m;
    private ListView listView = null;
    private int[] icones = null;
    private int[] peidos = null;

    ImageView imgFromGrid;
    //Guarda o estado da imagem anterior
    ImageView imgFromGridTEMP;
    public  int[] getImagens() {
        int count = R.raw.class.getFields().length;
        icones = new int[R.raw.class.getFields().length];
        for (int i = 0; i < count; i++) {
            icones[i] = R.mipmap.ic_launcher;
        }
        return icones;
    }

    public int[] soundsRaw() {
        int i = 0;
        peidos = new int[R.raw.class.getFields().length];
        for (Field teste : R.raw.class.getFields()){
            try {
                peidos[i] = Integer.parseInt(teste.get(teste).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            i++;
        }
        return peidos;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, getImagens(), soundsRaw());

        listView.setAdapter(customAdapter);
        for (Field teste : R.raw.class.getFields()){
            Log.i("NOME:", "" + teste.getName());

            try {
                Log.i("OBJETO:", "" + teste.get(teste));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//				int tempInt = position+1;

                String texto = "Listener position --> " + position;
                int duracao = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(parent.getContext(), texto, duracao);
                toast.show();

                if (m != null && m.isPlaying()) {
                    Log.i("Audio Session ID: ", m.getAudioSessionId() + "-------");

//                    if (imgFromGridTEMP !=null && !imgFromGridTEMP.equals(imgFromGrid)){
//                        imgFromGridTEMP.setImageResource(R.drawable.fart);
//                        m.stop();
//                    }
//                    imgFromGrid.setImageResource(R.drawable.fart);
                    m.stop();
                } else {
                    m = MediaPlayer.create(parent.getContext(), soundsRaw()[position]);
                    try {
                        m.prepare();
                    } catch (Exception e) {
                        Log.getStackTraceString(e);
                    }
                    m.start();
//                    imgFromGridTEMP = imgFromGrid;
//                    imgFromGrid.setImageResource(R.drawable.fart_stop);
                    Log.i("Posicao", m.getCurrentPosition() + "");
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mp) {
//                            imgFromGrid.setImageResource(R.drawable.fart);
                            m.stop();
                        }


                    });
                    try {
                        m.prepare();
                    } catch (Exception e) {
                        Log.getStackTraceString(e);
                    }
                    m.start();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (m !=null)
            m.stop();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_sobre:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
