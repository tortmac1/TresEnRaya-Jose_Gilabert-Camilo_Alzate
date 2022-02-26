package com.example.tresenraya_jos_gilabert_camilo_alzate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView textoVictoria;
    Integer[] botones;
    int[] Tablero = new int[]{
            0, 0, 0,
            0, 0, 0,
            0, 0, 0,
    };

    int estado = 0;
    int fichasPuestas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoVictoria = (TextView) findViewById(R.id.textoVictoria);
        textoVictoria.setVisibility(View.INVISIBLE);

        botones  = new Integer[]{
                R.id.b1, R.id.b2, R.id.b3,
                R.id.b4, R.id.b5, R.id.b6,
                R.id.b7, R.id.b8, R.id.b9,
        };
    }

    public void ponerFicha(View v){
        if(estado == 0){
            int numBoton = Arrays.asList(botones).indexOf(v.getId());
            if(tablero[numBoton] == 0) {
                v.setBackgroundResource(R.drawable.cruz);
                tablero[numBoton] = 1;
                fichasPuestas += 1;
                estado = comprobarEstado();
                if (estado == 0) {
                    ia();
                    fichasPuestas += 1;
                    estado = comprobarEstado();
                }
            }
        }
    }

    public void ia(){
        Random ran = new Random();
        int pos = ran.nextInt(tablero.length);
        while(tablero[pos] != 0){
            pos = ran.nextInt(tablero.length);
        }
        Button b = (Button) findViewById(botones[pos]);
        b.setBackgroundResource(R.drawable.circulo);
        tablero[pos] = -1;
    }

    public int comprobarEstado(){
        if(fichasPuestas < 9){
            return 0;
        }
        else{
            return 2;
        }
    }
}