package com.example.tresenraya_jos_gilabert_camilo_alzate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textoVictoria;
    Integer[] botones;
    int[] tablero = new int[]{
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
                R.id.casilla1, R.id.casilla2, R.id.casilla3,
                R.id.casilla4, R.id.casilla5, R.id.casilla6,
                R.id.casilla7, R.id.casilla8, R.id.casilla9,
        };
    }

    public void colocarFicha(View v){
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