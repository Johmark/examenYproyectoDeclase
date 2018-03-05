package com.example.tucker.examentucker.subclases;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.tucker.examentucker.R;

import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by John on 3/4/2018.
 */

public class ExamViewHolder extends RecyclerView.ViewHolder {
    public HtmlTextView html;

    public ImageView borrar;

    public ExamViewHolder(View itemView){
        super(itemView);
        html = itemView.findViewById(R.id.html_text);
        borrar = itemView.findViewById(R.id.delete);
    }
}
