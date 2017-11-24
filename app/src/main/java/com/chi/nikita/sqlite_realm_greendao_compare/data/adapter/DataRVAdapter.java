package com.chi.nikita.sqlite_realm_greendao_compare.data.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chi.nikita.sqlite_realm_greendao_compare.R;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.iCRUD;

import java.util.ArrayList;
import java.util.List;

public class DataRVAdapter extends RecyclerView.Adapter<DataRVAdapter.UserViewHolder> {

    private List<iCRUD> userModelList;

    public DataRVAdapter() {
        this.userModelList = new ArrayList<>();
    }


    public void swapTipsModelList(@NonNull final List<? extends iCRUD> userModelList) {
        this.userModelList.clear();
        this.userModelList.addAll(userModelList);
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {
        final iCRUD userModel = userModelList.get(position);
        holder.bind(userModel);
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView tvId, tvName, tvAge;

        UserViewHolder(@NonNull final View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
        }

        void bind(@NonNull final iCRUD userModel) {

            tvId.setText(String.valueOf(userModel.getId()));
            tvName.setText(userModel.getName());
            tvAge.setText(String.valueOf(userModel.getAge()));
        }
    }
}