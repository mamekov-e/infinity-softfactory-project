package com.example.online_shop_mobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_shop_mobile.R;
import com.example.online_shop_mobile.models.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private Context mCtx;
    private List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_products, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewProductName.setText(product.getProductName());
        holder.textViewProductPrice.setText(product.getProductPrice());
        holder.textViewProductImage.setText(product.getImage());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewProductName, textViewProductPrice, textViewProductImage;

        public ProductsViewHolder(View itemView) {
            super(itemView);

            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
            textViewProductImage = itemView.findViewById(R.id.textViewProductImage);
        }
    }
}