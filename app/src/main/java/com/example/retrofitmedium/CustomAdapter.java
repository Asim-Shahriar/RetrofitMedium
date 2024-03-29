package com.example.retrofitmedium;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<RetroPhoto> dataList;
    private Context context;

    public CustomAdapter(Context context,List<RetroPhoto> dataList){

      this.context=context;
      this.dataList=dataList;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View v=layoutInflater.inflate(R.layout.custom_row,parent,false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {

     holder.txtTitle.setText(dataList.get(position).getTitle());
     Picasso.Builder builder=new Picasso.Builder(context);
     builder.downloader(new OkHttp3Downloader(context));
     builder.build().load(dataList.get(position).getThumbnailUrl())
             .placeholder(R.drawable.ic_launcher_background)
             .error(R.drawable.ic_launcher_background)
             .into(holder.coverImage);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private TextView txtTitle;
        private ImageView coverImage;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;

            txtTitle=mView.findViewById(R.id.title);
            coverImage=mView.findViewById(R.id.coverImage);
        }
    }
}
