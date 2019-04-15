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

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.MyViewHolder> {

    private Context context;
    private List<Pengguna> penggunaList;

    public BankAdapter(Context context, List<Pengguna> penggunaList) {
        this.context = context;
        this.penggunaList = penggunaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_bank_sampah, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Pengguna model = penggunaList.get(i);
        myViewHolder.nama.setText(model.getNamaPemilik());
        myViewHolder.instansi.setText(model.getNamaInstansi());
        myViewHolder.email.setText(model.getEmail());
        myViewHolder.alamat.setText(model.getAlamat());
        myViewHolder.telp.setText(model.getTelp());
    }

    @Override
    public int getItemCount() {
        return penggunaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, instansi, alamat, email, telp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.bank_pemilik);
            instansi = (TextView) itemView.findViewById(R.id.bank_instansi);
            alamat = (TextView) itemView.findViewById(R.id.bank_alamat);
            email = (TextView) itemView.findViewById(R.id.bank_email);
            telp = (TextView) itemView.findViewById(R.id.bank_telp);
        }
    }
}
