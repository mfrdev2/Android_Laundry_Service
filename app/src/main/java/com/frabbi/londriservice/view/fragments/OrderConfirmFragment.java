package com.frabbi.londriservice.view.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.DynamicViewForOrderCollectBinding;
import com.frabbi.londriservice.databinding.FragmentOrderConfirmBinding;
import com.frabbi.londriservice.model.OrderGettingModel;
import com.frabbi.londriservice.sharedpref.SharedPref;
import com.frabbi.londriservice.viewmodel.OrderConfirmViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderConfirmFragment extends Fragment {
    private FragmentOrderConfirmBinding vBinding;
    private List<OrderGettingModel> orderList;
    private static String[] listItem;

    private Integer TOTAL_QUANTITY = 0;
    private Double TOTAL_PRICE = 0.0;
    private static SharedPref sharedPref;

    private static OrderConfirmViewModel liveData;


    public OrderConfirmFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_confirm, container, false);


        return vBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        liveData = ViewModelProviders.of(this).get(OrderConfirmViewModel.class);


        listItem = getResources().getStringArray(R.array.select_items);
        assert getArguments() != null;
        vBinding.titleText.setText(getArguments().getString("title"));
        vBinding.addMore.setOnClickListener(v -> {
            addCustomView(false, null);
        });

        vBinding.confirmButton.setOnClickListener(v -> {
            if (isValidInput()) {
                dataStore();
                Bundle bundle = new Bundle();
                assert orderList != null;
                liveData.setOrderList(orderList);
                bundle.putSerializable("orderConfirm", (Serializable) orderList);
                Navigation.findNavController(view).navigate(
                        R.id.action_orderConfirmFragment_to_checkoutFragment, bundle

                );
            } else {
                Toast.makeText(requireActivity(), "Something wrong!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Data store in array list
    private void dataStore() {
        orderList = new ArrayList<>();
        for (int i = 0; i < vBinding.addViewLayout.getChildCount(); i++) {
            View v = vBinding.addViewLayout.getChildAt(i);
            AutoCompleteTextView selectedItem = v.findViewById(R.id.selected_item);
            EditText selectedQty = v.findViewById(R.id.selected_qty);
            TextView unitPrice = v.findViewById(R.id.tv_per_unit_price);

            String select_item = selectedItem.getText().toString();
            String select_qty = selectedQty.getText().toString();
            String unit_price = unitPrice.getText().toString();

            if (!select_item.isEmpty() &&
                    !select_qty.isEmpty() &&
                    !unit_price.isEmpty()
            ) {
                try {
                    Double price = Double.valueOf(unit_price);
                    Integer qty = Integer.valueOf(select_qty);
                    orderList.add(new OrderGettingModel(
                            vBinding.titleText.getText().toString(),
                            selectedItem.getText().toString(),
                            selectedQty.getText().toString(),
                            qty * price,
                            TOTAL_QUANTITY,
                            TOTAL_PRICE
                    ));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    //validation check
    private boolean isValidInput() {
        for (int i = 0; i < vBinding.addViewLayout.getChildCount(); i++) {
            View v = vBinding.addViewLayout.getChildAt(i);
            AutoCompleteTextView selectedItem = v.findViewById(R.id.selected_item);
            EditText selectedQty = v.findViewById(R.id.selected_qty);
            if (selectedItem.getText().toString().isEmpty() || selectedQty.getText().toString().isEmpty()) {
                return false;
            }
        }

        return true;
    }

    //add view
    private void addCustomView(Boolean isBack, List<OrderGettingModel> orderList) {


        //backstack handling
        if (isBack && orderList != null) {

            for (OrderGettingModel order : orderList) {
                final DynamicViewForOrderCollectBinding customView2 = DynamicViewForOrderCollectBinding.inflate(getLayoutInflater());
                View view = customView2.getRoot();
                customView2.selectedItem.setText(order.getSelectedItem());
                customView2.selectedQty.setText(order.getSelectedQty());
                customView2.selectedQty.setEnabled(true);
                vBinding.addViewLayout.addView(view, vBinding.addViewLayout.getChildCount());

                String msg = "Unit Price: $" + customView2.selectedItem.getText().toString();
                //get unit price
                String unitPrice = String.valueOf(getServicePrice(vBinding.titleText.getText().toString()
                        , customView2.selectedItem.getText().toString())
                );
                customView2.tvUnitPriceTitle.setText(msg);
                customView2.tvPerUnitPrice.setText(unitPrice);
                customView2.tvUnitPriceTitle.setVisibility(View.VISIBLE);
                customView2.tvPerUnitPrice.setVisibility(View.VISIBLE);
                dynamicViewHandling(customView2);
            }
            vBinding.addMore.setText("ADD MORE");
            vBinding.confirmButton.setEnabled(true);
            vBinding.confirmButton.setClickable(true);


        } else {
            final DynamicViewForOrderCollectBinding customView = DynamicViewForOrderCollectBinding.inflate(getLayoutInflater());
            View view = customView.getRoot();
            vBinding.addViewLayout.addView(view, vBinding.addViewLayout.getChildCount());
            vBinding.addMore.setText("ADD MORE");
            vBinding.confirmButton.setEnabled(true);
            vBinding.confirmButton.setClickable(true);
            dynamicViewHandling(customView);
        }

    }//#END

    //dynamic view handling
    private void dynamicViewHandling(@NonNull DynamicViewForOrderCollectBinding customView){

        customView.selectedItem.setAdapter(new ArrayAdapter<String>(
                requireContext(),
                R.layout.drop_down_text_list_view,
                listItem
        ));


        customView.delete.setOnClickListener(v -> {
            removeView(customView.getRoot(), customView);
        });

        customView.selectedItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = "Unit Price: $" + customView.selectedItem.getText().toString();
                //get unit price
                String unitPrice = String.valueOf(getServicePrice(vBinding.titleText.getText().toString()
                        , customView.selectedItem.getText().toString())
                );
                customView.tvUnitPriceTitle.setText(msg);
                customView.tvPerUnitPrice.setText(unitPrice);
                customView.tvUnitPriceTitle.setVisibility(View.VISIBLE);
                customView.tvPerUnitPrice.setVisibility(View.VISIBLE);
                customView.tk.setVisibility(View.VISIBLE);
                customView.selectedQty.setEnabled(true);
            }
        });

        //task for Total qty and amount
        customView.selectedQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!customView.selectedQty.getText().toString().isEmpty()) {
                    Integer integer = Integer.valueOf(customView.selectedQty.getText().toString());
                    TOTAL_QUANTITY = TOTAL_QUANTITY - integer;

                    String serviceTitle = vBinding.titleText.getText().toString();
                    String itemName = customView.selectedItem.getText().toString();

                    TOTAL_PRICE = TOTAL_PRICE - (integer * getServicePrice(serviceTitle, itemName));

                    setTotalQtyAndAmount(TOTAL_QUANTITY, TOTAL_PRICE);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!customView.selectedQty.getText().toString().isEmpty()) {
                    Integer integer = Integer.valueOf(customView.selectedQty.getText().toString());
                    TOTAL_QUANTITY = TOTAL_QUANTITY + integer;


                    String serviceTitle = vBinding.titleText.getText().toString();
                    String itemName = customView.selectedItem.getText().toString();

                    TOTAL_PRICE = TOTAL_PRICE + (integer * getServicePrice(serviceTitle, itemName));


                    setTotalQtyAndAmount(TOTAL_QUANTITY, TOTAL_PRICE);
                }

            }
        });
    }//#End

    //remove view
    private void removeView(View view, DynamicViewForOrderCollectBinding customView) {

        vBinding.addViewLayout.removeView(view);

        if (vBinding.addViewLayout.getChildCount() == 0) {
            vBinding.addMore.setText("ADD");
            vBinding.confirmButton.setEnabled(false);
            vBinding.confirmButton.setClickable(false);
        }
        //task for Total qty and amount
        if (!customView.selectedQty.getText().toString().isEmpty()) {
            Integer integer = Integer.valueOf(customView.selectedQty.getText().toString());
            TOTAL_QUANTITY = TOTAL_QUANTITY - integer;

            String serviceTitle = vBinding.titleText.getText().toString();
            String itemName = customView.selectedItem.getText().toString();

            TOTAL_PRICE = TOTAL_PRICE - (integer * getServicePrice(serviceTitle, itemName));

            setTotalQtyAndAmount(TOTAL_QUANTITY, TOTAL_PRICE);
        }

    }

    private void setTotalQtyAndAmount(Integer totalQty, Double totalPrice) {
        vBinding.totalQuantity.setText(String.valueOf(totalQty));
        vBinding.totalAmount.setText(String.valueOf(totalPrice));
    }


    private static Double getServicePrice(String selectedService, String itemName) {

        switch (selectedService) {
            case "Wash+Iron":
                switch (itemName) {
                    case "Shirt":
                        return 100.0;
                    case "Pant":
                        return 100.0;
                    case "Skirts":
                        return 100.0;
                    case "Shorts":
                        return 100.0;
                    case "Socks":
                        return 100.0;
                    case "Sweater":
                        return 100.0;
                    case "Corsets":
                        return 100.0;
                    case "Jackets":
                        return 100.0;
                    case "Jeans":
                        return 100.0;
                    default:
                        throw new IllegalStateException("Unexpected value: " + itemName);
                }
            case "Only Wash":
                switch (itemName) {
                    case "Shirt":
                        return 50.0;
                    case "Pant":
                        return 50.0;
                    case "Skirts":
                        return 50.0;
                    case "Shorts":
                        return 50.0;
                    case "Socks":
                        return 50.0;
                    case "Sweater":
                        return 50.0;
                    case "Corsets":
                        return 50.0;
                    case "Jackets":
                        return 50.0;
                    case "Jeans":
                        return 50.0;
                    default:
                        throw new IllegalStateException("Unexpected value: " + itemName);
                }
            case "Iron":
                switch (itemName) {
                    case "Shirt":
                        return 15.0;
                    case "Pant":
                        return 15.0;
                    case "Skirts":
                        return 15.0;
                    case "Shorts":
                        return 15.0;
                    case "Socks":
                        return 15.0;
                    case "Sweater":
                        return 15.0;
                    case "Corsets":
                        return 15.0;
                    case "Jackets":
                        return 15.0;
                    case "Jeans":
                        return 15.0;
                    default:
                        throw new IllegalStateException("Unexpected value: " + itemName);
                }
        }

        return 0.0;
    }

    @Override
    public void onResume() {
        super.onResume();


        List<OrderGettingModel> list = liveData.getOrderList().getValue();

        if (list != null && !list.isEmpty()) {
            OrderGettingModel order = list.get(0);
            TOTAL_PRICE = order.getTotalAmount();
            TOTAL_QUANTITY = order.getTotalQty();
            vBinding.totalAmount.setText(String.valueOf(TOTAL_PRICE));
            vBinding.totalQuantity.setText(String.valueOf(TOTAL_QUANTITY));
            addCustomView(true, list);

        } else {
            addCustomView(false, null);
        }


    }
}