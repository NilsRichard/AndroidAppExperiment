package com.example.mmm_tp1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.UserInfoHolder> {

    private List<UserInfo> userInfos;
    private final UserInfoAdapterListener listener;


    public UserInfoAdapter(List<UserInfo> userInfos, UserInfoAdapterListener listener) {
        this.listener = listener;
        this.userInfos = userInfos;
    }

    public class UserInfoHolder extends RecyclerView.ViewHolder {
        public TextView nom, prenom, ville, date;

        public UserInfoHolder(View view) {
            super(view);
            nom = view.findViewById(R.id.nom);
            prenom = view.findViewById(R.id.prenom);
            ville = view.findViewById(R.id.ville);
            date = view.findViewById(R.id.date);

            view.setOnClickListener(view1 -> {
                // send selected contact in callback
                listener.onUserInfoSelected(userInfos.get(getAdapterPosition()));

            });

        }
    }

    public void setUserInfos(List<UserInfo> Users) {
        this.userInfos = Users;
        notifyDataSetChanged();
    }

    public UserInfo getUserAt(int adapterPosition) {
        return userInfos.get(adapterPosition);
    }

    @NonNull
    @Override
    public UserInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new UserInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoHolder holder, int position) {
        final UserInfo contact = userInfos.get(position);
        holder.nom.setText(contact.getNom());
        holder.prenom.setText(contact.getPrenom());
        holder.ville.setText(contact.getVilleNaissance());
        holder.date.setText(contact.getDateNaissance());
    }

    @Override
    public int getItemCount() {
        return userInfos.size();
    }

    public interface UserInfoAdapterListener {
        void onUserInfoSelected(UserInfo userInfo);
    }

}
