package com.example.guardador_contraseas;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtid, txtuser, txtproveedor, txtcontraseña;
    Button btnguardar, btneditar, btneliminar;
    ListView lstcontraseñas;

    ArrayAdapter<String> adapter;
    contraseñas contraseñasjava;
    int idSeleccionado  = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtid = findViewById(R.id.txtID);
        txtuser = findViewById(R.id.txtUser);
        txtproveedor = findViewById(R.id.txtProvee);
        txtcontraseña = findViewById(R.id.txtContra);
        btnguardar = findViewById(R.id.btnGuardar);
        btneditar = findViewById(R.id.btnEditar);
        btneliminar = findViewById(R.id.btnEliminar);
        lstcontraseñas = findViewById(R.id.lstContraseñas);
        contraseñasjava =new contraseñas(this);


        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contraseñasjava.insertarcontraseña(
                        txtuser.getText().toString(),
                        txtproveedor.getText().toString(),
                        txtcontraseña.getText().toString());
                actualizarlista();
                limpiarcampos();
            }
        });

        actualizarlista();

        lstcontraseñas.setOnItemClickListener(((parent, view, position, id) -> {
            String itemSeleccionado = (String)parent.getItemAtPosition(position);
            String[] parts = itemSeleccionado.split(" - ");

            if (parts.length == 4){
                idSeleccionado = Integer.parseInt(parts[0]);
                txtid.setText(parts[0]);
                txtuser.setText(parts[1]);
                txtproveedor.setText(parts[2]);
                txtcontraseña.setText(parts[3]);
            } else {
                Toast.makeText(MainActivity.this, "Error al seleccionar el contraseña", Toast.LENGTH_SHORT);
            }
        }));

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idSeleccionado != -1){
                    contraseñasjava.eliminarcontraseña(idSeleccionado);
                    limpiarcampos();
                    actualizarlista();
                } else {
                    Toast.makeText(MainActivity.this, "SIN CONTRASEÑA SELECCIONADA", Toast.LENGTH_SHORT);
                }
            }
        });

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idSeleccionado != -1){
                    contraseñasjava.editarcontraseña(
                            idSeleccionado,
                            txtuser.getText().toString(),
                            txtproveedor.getText().toString(),
                            txtcontraseña.getText().toString());
                    actualizarlista();
                    limpiarcampos();
                } else {
                    Toast.makeText(MainActivity.this, "SIN CONTRASEÑA SELECCIONADA", Toast.LENGTH_SHORT);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void actualizarlista(){
            List<String> contraseñas = contraseñasjava.obtenercontraseña();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contraseñas);
            lstcontraseñas.setAdapter(adapter);
    }

    private void limpiarcampos(){
        txtid.setText("");
        txtuser.setText("");
        txtproveedor.setText("");
        txtcontraseña.setText("");
        txtuser.requestFocus();
    }
}