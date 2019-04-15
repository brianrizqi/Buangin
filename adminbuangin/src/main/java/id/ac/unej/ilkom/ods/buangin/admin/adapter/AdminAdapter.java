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

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyViewHolder> {
    private Context context;
    private List<Pengguna> penggunaList;

    public AdminAdapter(Context context, List<Pengguna> penggunaList) {
        this.context = context;
        this.penggunaList = penggunaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_admin, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Pengguna model = penggunaList.get(i);
        myViewHolder.nama.setText(model.getNama());
        myViewHolder.email.setText(model.getEmail());
        myViewHolder.alamat.setText(model.getAlamat());
        myViewHolder.telp.setText(model.getTelp());
    }

    @Override
    public int getItemCount() {
        return penggunaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, email, alamat, telp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.admin_nama);
            email = (TextView) itemView.findViewById(R.id.admin_email);
            alamat = (TextView) itemView.findViewById(R.id.admin_alamat);
            telp = (TextView) itemView.findViewById(R.id.admin_telp);
        }
    }
}
