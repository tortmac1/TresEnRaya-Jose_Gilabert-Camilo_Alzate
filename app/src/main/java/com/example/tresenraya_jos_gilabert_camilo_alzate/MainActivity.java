package com.example.tresenraya_jos_gilabert_camilo_alzate;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textoVictoria;
    Integer[] botones;
    Button[] btns;
    int[] tablero = new int[]{
            0, 0, 0,
            0, 0, 0,
            0, 0, 0,
    };

    int estado = 0;
    int fichasPuestas = 0;
    int turno =1;
    int[] posGanadora = new int[]{-1,-1,-1};

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

        btns =  new Button[]{
                findViewById(R.id.casilla1), findViewById(R.id.casilla2), findViewById(R.id.casilla3),
                findViewById(R.id.casilla4), findViewById(R.id.casilla5), findViewById(R.id.casilla6),
                findViewById(R.id.casilla7), findViewById(R.id.casilla8), findViewById(R.id.casilla9),
        };


    }

    public void colocarFicha(View v){
        if(estado == 0){
            turno = 1;
            int numBoton = Arrays.asList(botones).indexOf(v.getId());
            Button b = (Button) findViewById(botones[numBoton]);
            if(tablero[numBoton] == 0) {
                b.setText("X");
                tablero[numBoton] = 1;
                fichasPuestas += 1;
                estado = comprobarEstado();
                terminarPartida();
                if (estado == 0) {
                    turno = -1;
                    ia();
                    fichasPuestas += 1;
                    estado = comprobarEstado();
                    terminarPartida();
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
        b.setText("O");
        tablero[pos] = -1;
    }

    public int comprobarEstado(){
      int nuevoEstado = 0;

      if(Math.abs(tablero[0]+tablero[1]+tablero[2]) == 3){
          posGanadora = new int[]{0,1,2};
          nuevoEstado = 1*turno;
      } else if (Math.abs(tablero[3]+tablero[4]+tablero[5]) == 3){
          posGanadora = new int[]{3,4,5};
          nuevoEstado = 1*turno;
      }else if (Math.abs(tablero[6]+tablero[7]+tablero[8]) == 3){
          posGanadora = new int[]{6,7,8};
          nuevoEstado = 1*turno;
      }else if (Math.abs(tablero[0]+tablero[3]+tablero[6]) == 3){
          posGanadora = new int[]{0,3,6};
          nuevoEstado = 1*turno;
      }else if (Math.abs(tablero[1]+tablero[4]+tablero[7]) == 3){
          posGanadora = new int[]{1,4,7};
          nuevoEstado = 1*turno;
      }else if (Math.abs(tablero[2]+tablero[5]+tablero[8]) == 3){
          posGanadora = new int[]{2,5,8};
          nuevoEstado = 1*turno;
      }else if (Math.abs(tablero[0]+tablero[4]+tablero[8]) == 3){
          posGanadora = new int[]{0,4,8};
          nuevoEstado = 1*turno;
      }else if (Math.abs(tablero[2]+tablero[4]+tablero[6]) == 3){
          posGanadora = new int[]{2,4,6};
          nuevoEstado = 1*turno;
      }else if (fichasPuestas == 9){
          nuevoEstado = 2;
      }

      return nuevoEstado;
    }

    public void terminarPartida(){
        if(estado == 1 || estado == -1){
            if (estado == 1){
                textoVictoria.setVisibility(View.VISIBLE);
                textoVictoria.setTextColor(Color.GREEN);
            }else {
                textoVictoria.setVisibility(View.VISIBLE);
                textoVictoria.setText("Has perdido :c");
                textoVictoria.setTextColor(Color.RED);
            }

        }else if (estado == 2){
            textoVictoria.setVisibility(View.VISIBLE);
            textoVictoria.setText("Has empatado :/");
            textoVictoria.setTextColor(Color.YELLOW);
        }
    }
}