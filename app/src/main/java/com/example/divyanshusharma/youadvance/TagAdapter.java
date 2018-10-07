package com.example.divyanshusharma.youadvance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class TagAdapter extends RecyclerView.Adapter<TagAdapter.MyViewHolder>{

    private String[] tags;
    Context context;
    private Set<Integer> taggedPositions = new HashSet<Integer>(); ;

    public Set<Integer> getTaggedPositions(){
        return taggedPositions;
    }

    public TagAdapter(Context context, String[] tags) {
        this.tags = tags;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckedTextView simpleCheckedTextView;

        public MyViewHolder(View view) {
            super(view);
            simpleCheckedTextView = (CheckedTextView) view.findViewById(R.id.checkedTextView);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tags_layout, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.simpleCheckedTextView.setText(tags[position]);

        holder.simpleCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Boolean checkedOrNot = ;
                if (holder.simpleCheckedTextView.isChecked()) {

                    taggedPositions.remove(position);
                    holder.simpleCheckedTextView.setChecked(false);
                    holder.simpleCheckedTextView.setCheckMarkDrawable(null);

                    //Toast.makeText(context, "un-Checked", Toast.LENGTH_LONG).show();
                } else {
                    taggedPositions.add(position);
                    holder.simpleCheckedTextView.setCheckMarkDrawable(R.drawable.check_mark);
                    holder.simpleCheckedTextView.setChecked(true);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return tags.length;
    }

}
