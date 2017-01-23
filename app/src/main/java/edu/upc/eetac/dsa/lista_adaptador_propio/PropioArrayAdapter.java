package edu.upc.eetac.dsa.lista_adaptador_propio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by pauli on 21/12/2016.
 */
public abstract class PropioArrayAdapter  extends BaseAdapter{


    private ArrayList<?> entradas;
    private int R_layout_IdView;
    private Context contexto;

    public PropioArrayAdapter(ArrayList<?> entradas, int R_layout_IdView, Context contexto) {
        this.entradas = entradas;
        this.R_layout_IdView = R_layout_IdView;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return entradas.size();
    }

    @Override
    public Object getItem(int position) {
        return entradas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }
        onEntrada(entradas.get(position),view);
        return view;
    }
    //Da la informacion:el objeto con solo los datos de ela entrada q va a construir y la vista donde lo va a meter
    public abstract  void onEntrada(Object entrada, View view);
}
