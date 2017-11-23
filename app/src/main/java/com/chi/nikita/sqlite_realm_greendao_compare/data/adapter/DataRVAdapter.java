package com.chi.nikita.sqlite_realm_greendao_compare.data.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chi.nikita.sqlite_realm_greendao_compare.R;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelSQLite;

import java.util.ArrayList;
import java.util.List;

public class DataRVAdapter extends RecyclerView.Adapter<DataRVAdapter.UserViewHolder> {

    private List<UserModelSQLite> userModelSQLites;

    public DataRVAdapter() {
        this.userModelSQLites = new ArrayList<>();
    }

    /**
     * Method type of List<UserModelSQLite>
     *
     * @return collection userModelSQLites
     */
    public void swapTipsModelList(List<UserModelSQLite> userModelSQLiteList) {
        this.userModelSQLites.clear();
        this.userModelSQLites.addAll(userModelSQLiteList);
        notifyDataSetChanged();

    }

    @Override
    public UserViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {
        UserModelSQLite userModelSQLite = userModelSQLites.get(position);
        holder.bind(userModelSQLite);
    }

    @Override
    public int getItemCount() {
        return userModelSQLites.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView tvId, tvName, tvAge;

        public UserViewHolder(@NonNull final View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
        }

        public void bind(UserModelSQLite userModelSQLite) {

            tvId.setText((int) userModelSQLite.getId());
            tvName.setText(userModelSQLite.getName());
            tvAge.setText(userModelSQLite.getAge());
        }
    }
}