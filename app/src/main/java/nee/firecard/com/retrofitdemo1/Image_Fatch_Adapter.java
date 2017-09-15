package nee.firecard.com.retrofitdemo1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import nee.firecard.com.retrofitdemo1.Model.FatchImageResponse;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by hp on 19-Jun-17.
 */

public class Image_Fatch_Adapter extends RecyclerView.Adapter<Image_Fatch_Adapter.MyViewholder>{

    private ArrayList<FatchImageResponse> image_fatch_adapters;
    private Context context;

    public Image_Fatch_Adapter(ArrayList<FatchImageResponse> image_fatch_adapters, Context context) {
        this.image_fatch_adapters = image_fatch_adapters;
        this.context = context;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row_image, parent,false);

        return new MyViewholder(layoutView,context,image_fatch_adapters);
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {

        holder.textView.setText(image_fatch_adapters.get(position).getTitle());
        Glide.with(context).load(image_fatch_adapters.get(position).getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return image_fatch_adapters.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
         GifImageView imageView;
         TextView textView;
         ArrayList<FatchImageResponse> image_fatch_adapters=new ArrayList<FatchImageResponse>();
        Context context;

        public MyViewholder(View itemView,Context context,ArrayList<FatchImageResponse> image_fatch_adapters) {

            super(itemView);
            this.image_fatch_adapters = image_fatch_adapters;
            this.context = context;

            itemView.setOnClickListener(this);
            imageView=(GifImageView)itemView.findViewById(R.id.imageView);
            textView=(TextView)itemView.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            FatchImageResponse fatchImageResponse=this.image_fatch_adapters.get(position);
            Intent intent=new Intent(this.context,Image_fatch_Detail.class);
            intent.putExtra("image",fatchImageResponse.getImage());
            intent.putExtra("title",fatchImageResponse.getTitle());
            intent.putExtra("id",fatchImageResponse.getId());
            intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(intent);


        }
    }


}
