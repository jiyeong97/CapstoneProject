package com.example.capstoneproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstoneproject.databinding.ItemDataBinding;
import com.example.capstoneproject.model.DataModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context context;
    ArrayList<DataModel> list;
    ItemDataBinding binding;
    CollectionReference reference;
    private String currentDate;
    FirebaseUser user;
    private String TipsId;

    private final ArrayList<String> idList = new ArrayList<>();

    private String PurchaseTipsId;
    private String PurchaseUserId;

    private String balance;

    public DataAdapter(Context context, ArrayList<DataModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemDataBinding.inflate(LayoutInflater.from(context));
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        currentDate = format.format(new Date());
        user = FirebaseAuth.getInstance().getCurrentUser();

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel model = list.get(position);

        if (model != null) {

            binding.dropdownTextView.setTitleText(model.getTitle());
            binding.dropdownTextView.setContentText(model.getDescription());

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemDataBinding binding;

        public ViewHolder(@NonNull ItemDataBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
