package com.frabbi.londriservice.view.adapter;

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
import com.frabbi.londriservice.databinding.StatusListViewBinding;

public class RvAdapterActiveOrderList extends RecyclerView.Adapter<RvAdapterActiveOrderList.MyViewHolder> {
    private Fragment fragment;
    public RvAdapterActiveOrderList(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StatusListViewBinding vBinding = DataBindingUtil.inflate(LayoutInflater.from(fragment.getContext()),
                R.layout.status_list_view,parent,false);
        return new MyViewHolder(vBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NavController controller = Navigation.findNavController(fragment.requireView());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_activeOrderFragment_to_orderDetailsFragment);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    protected static class MyViewHolder extends RecyclerView.ViewHolder {
        public StatusListViewBinding vBinding;
        public MyViewHolder(@NonNull StatusListViewBinding vBinding) {
            super(vBinding.getRoot());
        }
    }
}
