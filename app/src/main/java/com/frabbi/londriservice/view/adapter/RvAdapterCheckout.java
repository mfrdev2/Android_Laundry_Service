package com.frabbi.londriservice.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.InvoiceViewBinding;
import com.frabbi.londriservice.model.OrderGettingModel;

import java.util.List;

public class RvAdapterCheckout extends RecyclerView.Adapter<RvAdapterCheckout.MyViewHolder> {
   private Fragment fragment;
   private List<OrderGettingModel> list;

    public RvAdapterCheckout(Fragment fragment, List<OrderGettingModel> list) {
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final InvoiceViewBinding viewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext())
                ,R.layout.invoice_view
                ,parent
                ,false);
        return new MyViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      final OrderGettingModel order = list.get(position);
      holder.viewBinding.itemId.setText(order.getSelectedItem());
      holder.viewBinding.qtyId.setText(order.getSelectedQty());
      holder.viewBinding.priceId.setText(String.valueOf(order.getGotPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       final InvoiceViewBinding viewBinding;
        public MyViewHolder(@NonNull InvoiceViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }
    }
}
