package com.example.tucker.examentucker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tucker.examentucker.DataBase.ExamDataBase;
import com.example.tucker.examentucker.DataBase.models.TodoTablEx;
import com.example.tucker.examentucker.R;

public class FormularioActivity extends AppCompatActivity {

    private TextView lblcondicion;
    private TextView lblnombre;
    private TextView lblapellido;
    private TextView lblid;
    private TextView lblcodigoEmpleado;
    private TextView lblDepartamento;
    private TextView lbltelefono;

    private EditText txtnombre;
    private EditText txtapellido;
    private EditText txtid;
    private EditText txtcodigoEmpleado;
    private EditText txtDepartamento;
    private EditText txttelefono;

    private Button btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_activity);

        lblcondicion = findViewById(R.id.lblcondicion);
        lblnombre = findViewById(R.id.lblNombre);
        lblapellido = findViewById(R.id.lblApellido);
        lblid = findViewById(R.id.lblNumeroId);
        lblcodigoEmpleado = findViewById(R.id.lblEmpleadoId);
        lblDepartamento = findViewById((R.id.lblDepartamento));
        lbltelefono = findViewById(R.id.lblTelefono);

        txtnombre = findViewById(R.id.txtnombre);
        txtapellido = findViewById(R.id.txtApellido);
        txtid = findViewById(R.id.txtNumeroID);
        txtcodigoEmpleado = findViewById(R.id.txtEmpleadoID);
        txtDepartamento = findViewById(R.id.txtDepartamento);
        txttelefono = findViewById(R.id.txtTelefono);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                save();
            }
        });
    }
    private boolean validacion(){
        boolean isOK = true;
        if (txtnombre.getText().toString().isEmpty()) { isOK = false;}
        if (txtapellido.getText().toString().isEmpty()) { isOK = false;}
        if (txtid.getText().toString().isEmpty()) { isOK = false;}
        if (txtcodigoEmpleado.getText().toString().isEmpty()) { isOK = false;}
        if (txtDepartamento.getText().toString().isEmpty()){ isOK = false;}
        if (txttelefono.getText().toString().isEmpty()) { isOK = false;}

        return isOK;
    }
    private void save(){
        if (validacion())
        {
            TodoTablEx registro = new TodoTablEx();
            registro.nombre = txtnombre.getText().toString();
            registro.apellido = txtapellido.getText().toString();
            registro.numeroid = Integer.parseInt(txtid.getText().toString());
            registro.codigoEmpleado = txtcodigoEmpleado.getText().toString();
            registro.Departamento = txtDepartamento.getText().toString();
            registro.numeroDeTelefono = Integer.parseInt(txttelefono.getText().toString());
            registro.save();
            finish();
            Toast.makeText(this,getResources().getString(R.string.ok_valid),Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,getResources().getString(R.string.error_valid),Toast.LENGTH_LONG).show();
        }
    }
}
