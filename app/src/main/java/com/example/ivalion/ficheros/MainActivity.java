package com.example.ivalion.ficheros;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText texto;
    TextView texto_salida;
    Button mostrar, añadir;

    String FILE_NAME = "datos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = (EditText) findViewById(R.id.et_texto);
        texto_salida = (TextView)findViewById(R.id.tv_texto);

        mostrar = (Button) findViewById(R.id.btn_mostrar);
        mostrar.setOnClickListener(clic_mostrar);
        añadir = (Button) findViewById(R.id.btn_añadir);
        añadir.setOnClickListener(clic_añadir);
    }


    View.OnClickListener clic_añadir = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            try {
                FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
                String cadenaOutput = texto.getText().toString()+"\n";
                DataOutputStream dos = new DataOutputStream(fos);
                dos.writeBytes(cadenaOutput);

                fos.close();
                dos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    View.OnClickListener clic_mostrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            try {
                FileInputStream fin = openFileInput(FILE_NAME);
                DataInputStream dis = new DataInputStream(fin);
                byte[] buff = new byte[1000];
                dis.read(buff);
                texto_salida.setText(new String(buff));
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };



}
