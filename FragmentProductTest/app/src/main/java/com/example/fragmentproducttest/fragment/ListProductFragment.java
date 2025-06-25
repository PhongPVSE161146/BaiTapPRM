package com.example.fragmentproducttest.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragmentproducttest.Product;
import com.example.fragmentproducttest.R;

import java.util.Arrays;
import java.util.List;

public class ListProductFragment extends Fragment {

    public interface OnProductSelectedListener {
        void onProductSelected(Product product);
    }

    private OnProductSelectedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnProductSelectedListener) {
            listener = (OnProductSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnProductSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_product, container, false);
        RecyclerView rvProduct = view.findViewById(R.id.rvProduct);
        rvProduct.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Product> productList = Arrays.asList(
                new Product("Laptop", "Dell XPS 13"),
                new Product("Phone", "Samsung Galaxy S23"),
                new Product("Watch", "Apple Watch Ultra")
        );

        rvProduct.setAdapter(new ProductAdapter(productList, listener));

        return view;
    }

    // Adapter inner class
    private static class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
        private final List<Product> productList;
        private final OnProductSelectedListener listener;

        ProductAdapter(List<Product> productList, OnProductSelectedListener listener) {
            this.productList = productList;
            this.listener = listener;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvName;

            ViewHolder(View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.tvProductName);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Product product = productList.get(position);
            holder.tvName.setText(product.getName());

            holder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onProductSelected(product);
                }
            });
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }
    }
}
