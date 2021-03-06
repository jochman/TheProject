package com.barhochman.theproject.Adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.barhochman.theproject.Nodes.Transfers;
import com.barhochman.theproject.R;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>  {
    private List<Transfers> lst;
    private Integer color;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private CardView cardView;

        public TextView category, name, amount;
        public ViewHolder(View v) {
            super(v);
            cardView = v.findViewById(R.id.card_view);
            category = v.findViewById(R.id.category_name);
            name = v.findViewById(R.id.transaction_name);
            amount = v.findViewById(R.id.amount);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecAdapter(List<Transfers> lst) {
        this.lst = lst;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        v.setOnLongClickListener(LongPressListener);
        return new ViewHolder(v);
    }

    private final View.OnLongClickListener LongPressListener = new onLongPressListener();


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        //holder.cardView.setBackgroundColor(Color.green(1));
        // - number format
        Transfers t = lst.get(position);

        if (color != null) { holder.cardView.setBackgroundColor(color); }

        holder.amount.setText(StringsHelper.numberFormatter(t.getAmount()));
        holder.name.setText(t.getName());
        holder.category.setText(t.getCategory());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lst.size();
    }

    //color setter
    public void setColor(String colorString){
        color = Color.parseColor(colorString);
    }

    private class onLongPressListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
