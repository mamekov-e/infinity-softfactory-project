package com.example.online_shop_mobile.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.online_shop_mobile.R;
import com.example.online_shop_mobile.adapters.ProductsAdapter;
import com.example.online_shop_mobile.api.RetrofitClient;
import com.example.online_shop_mobile.models.Product;
import com.example.online_shop_mobile.models.response.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductsAdapter adapter;
    private List<Product> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        Call<ProductResponse> call = RetrofitClient.getInstance().getApi().getProducts();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                productList = response.body().getProducts();
                adapter = new ProductsAdapter(getActivity(), productList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });

    }
}