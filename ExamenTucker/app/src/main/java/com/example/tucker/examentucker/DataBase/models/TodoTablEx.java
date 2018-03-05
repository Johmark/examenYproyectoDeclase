package com.example.tucker.examentucker.DataBase.models;


import com.example.tucker.examentucker.DataBase.ExamDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.security.PublicKey;

/**
 * Created by John on 3/2/2018.
 */
@Table(database = ExamDataBase.class)
public class TodoTablEx extends BaseModel{

    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String nombre;

    @Column
    public String apellido;

    @Column
    public int numeroid;

    @Column
    public String codigoEmpleado;

    @Column
    public String Departamento;

    @Column
    public int numeroDeTelefono;

    @Column
    public int estado;



}
