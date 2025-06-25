package com.example.fragmentproducttest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.fragmentproducttest.Product;
import com.example.fragmentproducttest.R;

public class DetailedProductFragment extends Fragment {

    private Product product;

    public DetailedProductFragment() {
        // Required empty public constructor
    }

    // Factory method để truyền Product vào Fragment
    public static DetailedProductFragment newInstance(Product product) {
        DetailedProductFragment fragment = new DetailedProductFragment();
        Bundle args = new Bundle();
        args.putSerializable("product", product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = (Product) getArguments().getSerializable("product");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailed_product, container, false);

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvDesc = view.findViewById(R.id.tvDesc);

        if (product != null) {
            tvName.setText(product.getName());
            tvDesc.setText(product.getDescription());
        }

        return view;
    }
}
