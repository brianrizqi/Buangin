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
import id.ac.unej.ilkom.ods.buangin.model.p_ubahHarga_model;
import id.ac.unej.ilkom.ods.buangin.model.v_daftarBank_model;

public class p_ubahHarga_adapter extends RecyclerView.Adapter<p_ubahHarga_adapter.MyViewHolder> {
    private Context context;
    private List<v_daftarBank_model> modelList;

    public p_ubahHarga_adapter(Context context, List<v_daftarBank_model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_perusahaan_daftar_bank, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        v_daftarBank_model model = modelList.get(position);
        holder.instansi.setText(model.getNamaInstansi());
        holder.pemilik.setText(model.getNamaPemilik());
        holder.alamat.setText(model.getAlamat());
        holder.telp.setText(model.getTelp());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView instansi, pemilik, telp, alamat;
        ImageView gambar;

        public MyViewHolder(View itemView) {
            super(itemView);
            instansi = (TextView) itemView.findViewById(R.id.perusahaan_bank_instansi);
            pemilik = (TextView) itemView.findViewById(R.id.perusahaan_bank_pemilik);
            telp = (TextView) itemView.findViewById(R.id.perusahaan_bank_telp);
            alamat = (TextView) itemView.findViewById(R.id.perusahaan_bank_alamat);
//            gambar = (ImageView) itemView.findViewById(R.id.perusahaan_bank_gambar);
        }
    }
}
