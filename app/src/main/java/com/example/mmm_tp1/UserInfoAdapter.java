package com.example.mmm_tp1;

import android.content.Context;
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

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.MyViewHolder> implements Filterable {
    private List<UserInfo> contactList;
    private List<UserInfo> contactListFiltered;
    private UserInfoAdapterListener listener;


    public UserInfoAdapter(List<UserInfo> contactList, UserInfoAdapterListener listener) {
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nom, prenom, ville, date;

        public MyViewHolder(View view) {
            super(view);
            nom = view.findViewById(R.id.nom);
            prenom = view.findViewById(R.id.prenom);
            ville = view.findViewById(R.id.ville);
            date = view.findViewById(R.id.date);

            view.setOnClickListener(view1 -> {
                // send selected contact in callback
                listener.onUserInfoSelected(contactListFiltered.get(getAdapterPosition()));

            });

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final UserInfo contact = contactListFiltered.get(position);
        holder.nom.setText(contact.getNom());
        holder.prenom.setText(contact.getPrenom());
        holder.ville.setText(contact.getVilleNaissance());
        holder.date.setText(contact.getDateNaissance());
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    List<UserInfo> filteredList = new ArrayList<>();
                    for (UserInfo row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getNom().toLowerCase().contains(charString.toLowerCase()) || row.getPrenom().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<UserInfo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface UserInfoAdapterListener {
        void onUserInfoSelected(UserInfo userInfo);
    }


}
