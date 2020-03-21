package com.esq.foodsearch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersViewHolder>{
    Context context;
    ArrayList<Food> foodData;
    String TAG = "UserAdapter.this";

    @NonNull
    @Override
    public UserAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list_items, null);
        return new UsersViewHolder(view);
        }

    public UserAdapter(Context context, ArrayList<Food> foodData) {
        this.foodData = foodData;
        this.context = context;
        if (foodData == null) {
            Log.d(TAG,"Constuctor: FoodData is null" );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UsersViewHolder holder, final int position) {
        // set the data
        holder.name.setText("Name: " + foodData.get(position).getFoodName());
        holder.type.setText("Type: " + foodData.get(position).getFoodType());
        holder.brandName.setText("Brand Name: " + foodData.get(position).getBrandName());
        //implement setOnCLickListener on itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with user name
                Toast.makeText(context, foodData.get(position).getFoodName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (foodData == null) {
            Log.d(TAG,"getItemCount: Item is null" );
        }else{
            Log.d(TAG,"getItemCount: Item is " + foodData.size() );
        }
       return foodData != null ?foodData.size(): 0; // size of the list items;
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
