package com.example.fragmentproducttest;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.fragmentproducttest.fragment.DetailedProductFragment;
import com.example.fragmentproducttest.fragment.ListProductFragment;
public class MainActivity extends AppCompatActivity
        implements ListProductFragment.OnProductSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ListProductFragment())
                    .commit();
        }
    }

    @Override
    public void onProductSelected(Product product) {
        DetailedProductFragment detail = DetailedProductFragment.newInstance(product);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detail)
                .addToBackStack(null)
                .commit();
    }
}

