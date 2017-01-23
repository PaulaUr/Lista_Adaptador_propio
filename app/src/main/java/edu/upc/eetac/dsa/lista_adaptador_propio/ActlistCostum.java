package edu.upc.eetac.dsa.lista_adaptador_propio;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pauli on 21/12/2016.
 */
public class ActlistCostum extends ListActivity {
    private ListView lista;

    ArrayList<Lista_estructura> datos = new ArrayList<Lista_estructura>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.listado);

        /*datos.add(new Lista_estructura(R.drawable.dome_de_neige,"Dome de neige",1563));
        datos.add(new Lista_estructura(R.drawable.monseny_turo_home,"Montseny",3648));*/
        final String TAG = "ejemplo";
        String BASE_URL = "http://10.0.2.2:8080/lista";
        // String BASE_URL = "http://localhost:8080/myapp/juego";

        Log.i(TAG, "*get Llista de muntanyes!!");
        AsyncHttpClient client;
        client = new AsyncHttpClient();
        client.get(BASE_URL + "/ejemplo/all", null, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
                Type listType = new TypeToken<ArrayList<Lista_estructura>>() {
                }.getType();

                List<Lista_estructura> locationResults = new Gson().fromJson(responseString, listType);

                ArrayList<Lista_estructura> datos = new ArrayList<Lista_estructura>(locationResults);

                //locationResults = new ArrayList<Lista_estructura>();
                Log.i(TAG, responseString);
                lista = (ListView) findViewById(R.id.ListView_listado);
                lista.setAdapter(new PropioArrayAdapter(datos, R.layout.entrada, ActlistCostum.this) {

                    @Override
                    public void onEntrada(Object entrada, View view) {
                        if (entrada != null) {
                            TextView texto_superior_entrada = (TextView) view.findViewById(R.id.titulo);

                            if (texto_superior_entrada != null)
                                texto_superior_entrada.setText(((Lista_estructura) entrada).getName());

                            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.descripcion);
                            if (texto_inferior_entrada != null)
                                texto_inferior_entrada.setText(((Lista_estructura) entrada).getAltitude());

                            ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_image);
                            if (imagen_entrada != null)
                                imagen_entrada.setImageResource(((Lista_estructura) entrada).getIdImagen());
                        }
                    }
                });
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Lista_estructura elegido = (Lista_estructura) parent.getItemAtPosition(position);

                        CharSequence texto = "Seleccionado: " + elegido.getAltitude();
                        Toast toast = Toast.makeText(ActlistCostum.this, texto, Toast.LENGTH_LONG);
                        toast.show();
                    }
                });

            }
        });
    }

}

