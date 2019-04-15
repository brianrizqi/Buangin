package id.ac.unej.ilkom.ods.buangin.admin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.ac.unej.ilkom.ods.buangin.admin.R;
import id.ac.unej.ilkom.ods.buangin.admin.model.Pengguna;

public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.MyViewholder> {
    private Context context;
    private List<Pengguna> penggunaList;

    public VolunteerAdapter(Context context, List<Pengguna> penggunaList) {
        this.context = context;
        this.penggunaList = penggunaList;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_volunteer, viewGroup, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, int i) {
        Pengguna model = penggunaList.get(i);
        myViewholder.nama.setText(model.getNama());
        myViewholder.email.setText(model.getEmail());
    }

    @Override
    public int getItemCount() {
        return penggunaList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        private TextView nama, email;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.volunteer_nama);
            email = (TextView) itemView.findViewById(R.id.volunteer_email);
        }
    }
}
