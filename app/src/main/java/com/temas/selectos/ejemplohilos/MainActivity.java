package com.temas.selectos.ejemplohilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnTareaLarga;
    Button btnHilo;
    TextView txtVSegundos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTareaLarga = findViewById(R.id.btnTareaLarga);
        txtVSegundos= findViewById(R.id.textView);
        btnHilo = findViewById(R.id.btnHilo);
        btnTareaLarga.setOnClickListener(onClickTareaLarga);
        btnHilo.setOnClickListener(onClickHilo);



    }

    View.OnClickListener onClickHilo= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    tareaLarga();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtVSegundos.setText("Se ejecutó un proceso en segundo plano");
                        }
                    });

                    Log.d("Hilo","Hilo Terminado");
                }
            }).start();
        }
    };

    private void tareaLarga() {
        try {
            for( int i=1;i <= 15;i++)
            {
                Thread.sleep(1000);

                //Las vistas solamente se pueden invocar desde el hilo
                //principal
                //txtVSegundos.setText("Se ejecutó un proceso");

                Log.d("Segunos",i + "[s]");
            }

        }

        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    View.OnClickListener onClickTareaLarga = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            tareaLarga();
        }
    };

    public void onClickMensaje(View v)
    {
        Toast.makeText(this,"Hola",Toast.LENGTH_SHORT).show();
    }


}
