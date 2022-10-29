package com.example.apitest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinHolder> {

    private List<Coin> coinList;
    private Context context;
    private ItemClickable itemClickable;

    public CoinAdapter(Context context, List<Coin> coinList, ItemClickable itemClickable) {
        this.coinList = coinList;
        this.context = context.getApplicationContext();
        this.itemClickable = itemClickable;
    }

    @NonNull
    @Override
    public CoinHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CoinHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_item_lauout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CoinHolder holder, int position) {
        holder.bind(coinList.get(position));

    }

    @Override
    public int getItemCount() {
        return coinList.size();
    }

    public class CoinHolder extends RecyclerView.ViewHolder {

        private TextView baseAsset, highPrice, lowPrice, lastPrice, symbol;

        public CoinHolder(@NonNull View itemView) {
            super(itemView);

            baseAsset = itemView.findViewById(R.id.tv_baseAsset_showItemLayout);
            highPrice = itemView.findViewById(R.id.tv_highPrice_showItemLayout);
            lowPrice = itemView.findViewById(R.id.tv_lowPrice_showItemPrice);
            lastPrice = itemView.findViewById(R.id.tv_lastPrice_showItemLAyout);
            symbol = itemView.findViewById(R.id.tv_symbol_showItemPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickable.onItemClicked(getAdapterPosition());
                }
            });

        }

        public void bind(Coin coin) {
            baseAsset.setText(coin.getBaseAsset());
            highPrice.setText("HP :" + coin.getHighPrice());
            lowPrice.setText("LowP :" + coin.getLowPrice());
            lastPrice.setText("Lp :" + coin.getLastPrice());
            symbol.setText("Symbol :" + coin.getSymbol());

        }
    }

    public interface ItemClickable {
        void onItemClicked(int position);
    }
}
