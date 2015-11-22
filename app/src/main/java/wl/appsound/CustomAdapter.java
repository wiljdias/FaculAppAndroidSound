package wl.appsound;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by daniel.pinheiro on 17/11/2015.
 */
public class CustomAdapter extends BaseAdapter {

    private Context context;
    private final int[] fotos;
    private final int[] sons;

    public CustomAdapter(Context context, int[] fotos, int[] sons ) {
        this.context = context;
        this.fotos = fotos;
        this.sons = sons;

    }
    @Override
    public int getCount() {
        return fotos.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list;
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);//(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list = inflater.inflate(R.layout.list_view_custom, null);
        }else {
            list = convertView;
        }

        ImageView imagem = (ImageView)list.findViewById(R.id.list_image);
        TextView texto = (TextView)list.findViewById(R.id.list_text);
        Resources res = context.getResources();
        texto.setText(soundName(res.getResourceEntryName(sons[position])));
        imagem.setImageResource(fotos[0]);
        return list;
    }

    public String soundName(String entry){
        StringBuffer res = new StringBuffer();

        String[] strArr = entry.split("_");
        for (String str : strArr) {
            char[] stringArray = str.trim().toCharArray();
            stringArray[0] = Character.toUpperCase(stringArray[0]);
            str = new String(stringArray);

            res.append(str).append(" ");
        }
        return res.toString();
    }
}
