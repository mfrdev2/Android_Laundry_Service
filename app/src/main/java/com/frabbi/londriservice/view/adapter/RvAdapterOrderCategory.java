package com.frabbi.londriservice.view.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.CategoryListViewBinding;
import com.frabbi.londriservice.model.OrderCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class RvAdapterOrderCategory extends RecyclerView.Adapter<RvAdapterOrderCategory.MyViewHolder> {
    private static List<OrderCategoryModel> orderCategories = new ArrayList<>();
    private Fragment fg;
    public RvAdapterOrderCategory(Fragment fg) {
        this.fg = fg;
    }

    public void setList(List<OrderCategoryModel> orderCategories) {
        this.orderCategories = orderCategories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RvAdapterOrderCategory.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(fg.getContext());
        CategoryListViewBinding cBinding = DataBindingUtil.inflate(inflater, R.layout.category_list_view,parent,false);
        return new MyViewHolder(cBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterOrderCategory.MyViewHolder holder, int position) {
        OrderCategoryModel category = orderCategories.get(position);
        holder.mBinding.ivImage.setImageResource(category.getImgSrc());
        holder.mBinding.tvTitle.setText(category.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", category.getTitle());
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_orderFragment_to_orderConfirmFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderCategories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CategoryListViewBinding mBinding;
        public MyViewHolder(@NonNull CategoryListViewBinding cBinding) {
            super(cBinding.getRoot());
            mBinding = cBinding;
        }
    }
}
