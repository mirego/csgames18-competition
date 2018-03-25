package com.mirego.csmapapplication.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.mirego.csmapapplication.R;
import com.mirego.csmapapplication.model.Piece;

import java.util.List;

public class PiecesAdapter extends RecyclerView.Adapter {

    private List<Piece> mItems;
    private Context mContext;
    private LayoutInflater inflater;
    private OnItemClickListener mItemClickListener;

    public PiecesAdapter(Context context, List<Piece> pieces) {
        this.mContext = context;
        this.mItems = pieces;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_piece, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;

        final Piece piece = getItem(holder.getAdapterPosition());
        viewHolder.txtName.setText(piece.getName());

        int id = mContext.getResources().getIdentifier("ic_part_" + piece.getType(), "drawable", mContext.getPackageName());
        viewHolder.imgIcon.setBackgroundResource(id);

        viewHolder.txtComponent.setText(piece.getComponent());
        viewHolder.txtGPS.setText(String.valueOf(piece.getLatitude()) + "° N " + String.valueOf(piece.getLongitude()) + "° W");


    }

    public void setOnItemClickListener(PiecesAdapter.OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public Piece getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView txtName;
        ImageView imgIcon;
        TextView txtComponent;
        TextView txtGPS;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtName = itemView.findViewById(R.id.txtName);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            txtComponent = itemView.findViewById(R.id.txtComponent);
            txtGPS = itemView.findViewById(R.id.txtGPS);

        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                int position = getAdapterPosition();
                mItemClickListener.onItemClick(view, getItem(position), position);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, Piece item, int position);
    }
}
