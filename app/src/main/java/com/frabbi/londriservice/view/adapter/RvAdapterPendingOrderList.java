package com.frabbi.londriservice.view.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.application.MyApplication;
import com.frabbi.londriservice.databinding.StatusListViewBinding;
import com.frabbi.londriservice.model.AddressModel;
import com.frabbi.londriservice.model.OrderGettingModel;
import com.frabbi.londriservice.roomdb.entities.OrderList;
import com.frabbi.londriservice.utils.JsonConverter;
import com.frabbi.londriservice.viewmodel.PendingOrderFragmentViewModel;
import com.frabbi.londriservice.viewmodel.PendingOrderFragmentViewModelFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RvAdapterPendingOrderList extends RecyclerView.Adapter<RvAdapterPendingOrderList.MyViewHolder> {
    private final Context context;
    private PendingOrderFragmentViewModel viewModel;

    private List<OrderList> list = new ArrayList<>();

    public void setList(List<OrderList> list) {
        this.list = list;
    }

    public List<OrderList> getList() {
        return list;
    }

    public RvAdapterPendingOrderList(final Context context,PendingOrderFragmentViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StatusListViewBinding vBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.status_list_view, parent, false);
        return new MyViewHolder(vBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OrderList object = getList().get(position);
        List<OrderGettingModel> orderList = JsonConverter.getJsonOrderListObject(object.getPOrderList());
        AddressModel addressModel = JsonConverter.getGsonAddressObject(object.getPAddress());

        OrderGettingModel orderGet = orderList.get(0);
        holder.viewBinding.orderId.setText("#"+object.getId());
        holder.viewBinding.orderDate.setText(object.getDateAndTime());
        holder.viewBinding.totalPrice.setText("৳"+orderGet.getTotalAmount());

        holder.itemView.setOnClickListener(v->{
            if(!orderList.isEmpty()){
                Bundle bundle = new Bundle();
                bundle.putSerializable("orderObject",(Serializable) object);
                bundle.putSerializable("data", (Serializable) addressModel);
                bundle.putSerializable("orderConfirm", (Serializable) orderList);
                Navigation.findNavController(v).navigate(R.id.action_pendingOrderFragment_to_checkoutFragment, bundle);
            }
        });



        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialog();
                return false;
            }

            private void dialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are You Sure,You want to delete?");
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Yes",((dialog, which) -> {
                    viewModel.deleteItem(object);
                }));
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    protected static class MyViewHolder extends RecyclerView.ViewHolder {
        final StatusListViewBinding viewBinding;
        public MyViewHolder(@NonNull StatusListViewBinding vBinding) {
            super(vBinding.getRoot());
            viewBinding = vBinding;
        }
    }
}
