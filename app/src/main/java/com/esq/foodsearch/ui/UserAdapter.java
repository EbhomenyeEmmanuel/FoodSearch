package com.esq.foodsearch.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.esq.foodsearch.R;
import com.esq.foodsearch.databinding.UserListItemsBinding;
import com.esq.foodsearch.model.Food;

import java.util.ArrayList;

/*
 *Class to bind data
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersViewHolder> {
    Context context;
    private ArrayList<Food> foodData;
    String TAG = "UserAdapter.this";
    private OnFoodItemListener onFoodItemListener;

    @NonNull
    @Override
    public UserAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserListItemsBinding mUserListItemsBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.user_list_items, parent, false);
        return new UsersViewHolder(mUserListItemsBinding, onFoodItemListener);
    }

    public UserAdapter(Context context, ArrayList<Food> foodData, OnFoodItemListener onFoodItemListener) {
        this.foodData = foodData;
        this.context = context;
        this.onFoodItemListener = onFoodItemListener;
        if (foodData == null) {
            Log.d(TAG, "Constructor: FoodData is null");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UsersViewHolder holder, final int position) {
        // set the data
        Food mFood = foodData.get(position);
        holder.mUserListItemsBinding.foodName.setText(mFood.getFoodName());
        holder.mUserListItemsBinding.foodType.setText(mFood.getFoodType());
        holder.mUserListItemsBinding.brandName.setText(mFood.getBrandName());
    }

    @Override
    public int getItemCount() {
        if (foodData == null) {
            Log.d(TAG, "getItemCount: Item is null");
        } else {
            Log.d(TAG, "getItemCount: Item is " + foodData.size());
        }
        return foodData != null ? foodData.size() : 0; // size of the list items;
    }

    class UsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // init the item view's
         UserListItemsBinding mUserListItemsBinding;
        OnFoodItemListener onFoodItemListener;

        UsersViewHolder(final UserListItemsBinding binding, OnFoodItemListener onFoodItemListener) {
            super(binding.getRoot());
            this.mUserListItemsBinding = binding;
            // get the reference of item view's
            this.onFoodItemListener = onFoodItemListener;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onFoodItemListener.onFoodItemClick(getAdapterPosition());
        }
    }

    public interface OnFoodItemListener {
        void onFoodItemClick(int position);
    }

}
