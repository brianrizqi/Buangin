package id.ac.unej.ilkom.ods.buangin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.bs_daftarPerusahaan_model;

public class bs_daftarPerusahaan_adapter extends RecyclerView.Adapter<bs_daftarPerusahaan_adapter.MyViewHolder> {
    private Context context;
    private List<bs_daftarPerusahaan_model> modelList;

    public bs_daftarPerusahaan_adapter(Context context, List<bs_daftarPerusahaan_model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bank_daftar_perusahaan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        bs_daftarPerusahaan_model model = modelList.get(position);
        holder.instansi.setText(model.getNamaInstansi());
        holder.pemilik.setText(model.getNamaPemilik());
        holder.alamat.setText(model.getAlamat());
        holder.deskripsi.setText(model.getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView instansi, pemilik, alamat, deskripsi;

        public MyViewHolder(View itemView) {
            super(itemView);
            instansi = (TextView) itemView.findViewById(R.id.bank_perusahaan_instansi);
            pemilik = (TextView) itemView.findViewById(R.id.bank_perusahaan_pemilik);
            alamat = (TextView) itemView.findViewById(R.id.bank_perusahaan_alamat);
            deskripsi = (TextView) itemView.findViewById(R.id.bank_perusahaan_deskripsi);
        }
    }
}
