package nee.firecard.com.retrofitdemo1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nee.firecard.com.retrofitdemo1.Model.Student;

/**
 * Created by hp on 02-Jun-17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Student> item;
    private int previousPosition = 0;

    public RecyclerViewAdapter(List<Student> list) {
        this.item = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row_item, parent, false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(layoutView);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.txt_name.setText(item.get(position).getName());
        holder.txt_email.setText(item.get(position).getEmail());
        holder.txt_username.setText(item.get(position).getUsername());
        holder.txt_gender.setText(item.get(position).getGender());
        holder.txt_country.setText(item.get(position).getCountry());


        if (position > previousPosition) { // We are scrolling DOWN
            AnimationUtil.animate(holder, true);

        } else { // We are scrolling UP

            AnimationUtil.animate(holder, false);

        }
    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txt_name, txt_username, txt_email, txt_gender, txt_country;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            txt_name = (TextView) itemView.findViewById(R.id.tv_name);
            txt_email = (TextView) itemView.findViewById(R.id.tv_email);
            txt_username = (TextView) itemView.findViewById(R.id.tv_username);
            txt_gender = (TextView) itemView.findViewById(R.id.tv_gender);
            txt_country = (TextView) itemView.findViewById(R.id.tv_country);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
        }
    }

    public void setFilter(List<Student> newList){
        item=new ArrayList<>();
        item.addAll(newList);
        notifyDataSetChanged();
    }

}
