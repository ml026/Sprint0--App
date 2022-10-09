package com.example.mlahmag.prest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
public class MainActivity extends AppCompatActivity {

    private TextView elTexto;
    private Button elBotonEnviar;

    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidNetworking.initialize(getApplicationContext());


        this.elTexto = (TextView) findViewById(R.id.elTexto);
        this.elBotonEnviar = (Button) findViewById(R.id.botonEnviar);


        Log.d("clienterestandroid", "fin onCreate()");
    }


    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    public void boton_enviar_pulsado(View quien) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ID", "24");
            jsonObject.put("medida", 15.0);
            jsonObject.put("fecha", "martes");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post("http://192.168.1.16:8080/insertarMedicion")
                .addJSONObjectBody(jsonObject) // posting json
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}

      