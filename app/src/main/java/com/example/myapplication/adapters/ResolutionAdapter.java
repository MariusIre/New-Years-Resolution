package com.example.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.models.Resolution;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ResolutionAdapter extends RecyclerView.Adapter<ResolutionAdapter.ResolutionViewHolder> {
    public ArrayList<Resolution> resolutions;
    Context context;

    /*
    * Constructor of the adapter which receives the context and the list of resolutions
    * */
    public ResolutionAdapter(Context context, ArrayList<Resolution> resolutions) {
        this.resolutions = resolutions;
        this.context = context;
    }


    /*
    * The viewholder where we connect the views to the item_layout elements
    * */
    public class ResolutionViewHolder extends RecyclerView.ViewHolder{
        TextView tvResTitle, tvResDescription;
        ImageView ivCompleted;
        Button btnGo;
        boolean collapsed = false;
        public ResolutionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvResTitle = itemView.findViewById(R.id.tvResTitle);
            ivCompleted = itemView.findViewById(R.id.ivCompleted);
            tvResDescription = itemView.findViewById(R.id.tvResDescription);
            tvResDescription.setVisibility(View.GONE);
            itemView.setOnClickListener(t->{
                if(collapsed == false){
                    tvResDescription.setVisibility(View.VISIBLE);
                    collapsed = true;
                }else{
                    tvResDescription.setVisibility(View.GONE);
                    collapsed = false;

                }
            });

            ivCompleted.setOnClickListener(t->{
                Resolution res = resolutions.get(resolutions.indexOf((Resolution) itemView.getTag()));
                if (res.isCompleted().equals("false")){
                    res.setCompleted("true");
                    ivCompleted.setImageResource(R.drawable.done);

                }else{
                    res.setCompleted("false");
                    ivCompleted.setImageResource(R.drawable.notdone);
                }
                writeToFile();
            });
        }
    }


    /*
    * Creating the viewholder and loading the layout for each row
    * */
    @NonNull
    @Override
    public ResolutionAdapter.ResolutionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new ResolutionViewHolder(view);
    }


    /*
    * Binding each element from the viewholder with the list of resolution
    * */
    @Override
    public void onBindViewHolder(@NonNull ResolutionAdapter.ResolutionViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(resolutions.get(i));
        viewHolder.tvResTitle.setText(resolutions.get(i).getResolutionTitle());
        viewHolder.tvResDescription.setText(resolutions.get(i).getResolutionDescription());
        if(resolutions.get(i).isCompleted().equals("true")){
            viewHolder.ivCompleted.setImageResource(R.drawable.done);
        }else{
            viewHolder.ivCompleted.setImageResource(R.drawable.notdone);
        }
    }

    /*
    * Getting the total size of the resolution list
    * */
    @Override
    public int getItemCount() {
        return resolutions.size();
    }

    public void writeToFile(){
        try {

            FileOutputStream file = context.getApplicationContext().openFileOutput("res.txt",MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(file);
            for(Resolution resolution:resolutions){
                writer.write(resolution.getResolutionTitle() + "," +
                        resolution.getResolutionDescription() + "," + resolution.isCompleted() +  "\n");
            }
            writer.flush();
            writer.close();
            //Toast.makeText(context.getApplicationContext(), "User successfully added", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            //Toast.makeText(context.getApplicationContext(), "File couldn't be opened", Toast.LENGTH_SHORT).show();
        }
    }
}
