package com.esq.foodsearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersViewHolder>{
    Context context;
    List<SearchListResponseModel> searchListResponseModelData;


    @NonNull
    @Override
    public UserAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.user_list_items, null);
        return new UsersViewHolder(view);
        }

    public UserAdapter(Context context, List<SearchListResponseModel> searchListResponseModelData) {
        this.searchListResponseModelData = searchListResponseModelData;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UsersViewHolder holder, final int position) {
        // set the data
        holder.name.setText("Name: " + searchListResponseModelData.get(position).getName());
        holder.type.setText("Type " + searchListResponseModelData.get(position).getType());
        holder.brandName.setText("Brand Name: " + searchListResponseModelData.get(position).getBrandName());
        // implement setONCLickListtener on itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with user name
                Toast.makeText(context, searchListResponseModelData.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
       return searchListResponseModelData.size(); // size of the list items;
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name, type, brandName;

        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            type = (TextView) itemView.findViewById(R.id.type);
            brandName = (TextView) itemView.findViewById(R.id.brandName);
        }
    }

}
