package com.example.question.data.database;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.question.R;
import com.example.question.data.Question;

import java.util.ArrayList;

public class AdapterTest extends RecyclerView.Adapter<AdapterTest.MyViewHolder>
{
    private ArrayList<Question> questions;
    private LayoutInflater layoutInflater;
    private Context context;
    public AdapterTest(ArrayList<Question> questions, Context context){
        this.questions=questions;
        this.layoutInflater = LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_fragment,parent,false);
        return new MyViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.setId(questions.get(position).getId());
        holder.question.setText(questions.get(position).getText());
        holder.textView.setText(holder.seekBar.getProgress()+"");
        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                holder.textView.setText(seekBar.getProgress()+"");
                Query.updateRate(seekBar.getProgress(),questions.get(position).getId());
                Query.updateDone(1,questions.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView,question;
        SeekBar seekBar;
        private int id,progress;
        private Context context;
        public MyViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            textView=itemView.findViewById(R.id.number);
            question=itemView.findViewById(R.id.question);
            seekBar=itemView.findViewById(R.id.seekBar);
            seekBar.setProgress(5);
            this.context=context;
            Query.getInstance(context);
        }



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }
    }
}
