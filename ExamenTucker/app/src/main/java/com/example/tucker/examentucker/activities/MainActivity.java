package com.example.tucker.examentucker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tucker.examentucker.DataBase.models.TodoTablEx;
import com.example.tucker.examentucker.R;
import com.example.tucker.examentucker.subclases.ExamViewHolder;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lista;
    private static Context QuickContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuickContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actividad = new Intent(getApplicationContext(), FormularioActivity.class);
                actividad.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(actividad);
            }
        });
        lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));

        List<TodoTablEx> info = SQLite.select().from(TodoTablEx.class).queryList();
        lista.setAdapter(new ToDoAdapter(info));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            List<TodoTablEx> info = SQLite.select().from(TodoTablEx.class).queryList();
            lista.setAdapter(new ToDoAdapter(info));
        }
        if (id == R.id.action_edit){
            Toast.makeText(this, getResources().getString(R.string.message),Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public static class ToDoAdapter extends RecyclerView.Adapter<ExamViewHolder> {
        private final List<TodoTablEx> listTodoTablEx;
        private final LayoutInflater inflater;

        public ToDoAdapter(List<TodoTablEx> listTodoTablExs) {
            this.inflater = LayoutInflater.from(QuickContext);
            this.listTodoTablEx = listTodoTablExs;
        }

        @Override
        public ExamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.objeto, parent, false);
            return new ExamViewHolder(view);
        }

        public void animateTo(List<TodoTablEx> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<TodoTablEx> newModels) {
            for (int i = listTodoTablEx.size() - 1; i >= 0; i--) {
                final TodoTablEx model = listTodoTablEx.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<TodoTablEx> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final TodoTablEx model = newModels.get(i);
                if (!listTodoTablEx.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<TodoTablEx> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final TodoTablEx model = newModels.get(toPosition);
                final int fromPosition = listTodoTablEx.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public TodoTablEx removeItem(int position) {
            final TodoTablEx model = listTodoTablEx.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, TodoTablEx model) {
            listTodoTablEx.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final TodoTablEx model = listTodoTablEx.remove(fromPosition);
            listTodoTablEx.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onBindViewHolder(final ExamViewHolder holder, final int position) {
            final TodoTablEx current = listTodoTablEx.get(position);
            holder.html.setHtml(ActividadaString(current),
                    new HtmlResImageGetter(holder.html));
            holder.html.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (current.estado < 2) {
                        current.estado = 2;
                    } else {
                        current.estado = 1;
                    }
                    notifyDataSetChanged();
                }
            });
            holder.borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current.delete();
                    removeItem(position);
                    notifyDataSetChanged();
                }
            });
        }


        private String ActividadaString(TodoTablEx todo) {

            String color = "#ffffff";

            if (todo.estado == 2) {
                color = "#f44242";
            }

            String html = "<a><big><b> <font color=\"" + color + "\">" + todo.nombre + " " + todo.apellido + "</b></big>";
            html += "<br>" + "<p>" + todo.numeroid + ", " + todo.codigoEmpleado + ", " + "<br>" + todo.Departamento + ", " + todo.numeroDeTelefono + "</p>"+"</a>";
            return html;
        }

        @Override
        public int getItemCount() {
            return listTodoTablEx.size();
        }
    }
}
