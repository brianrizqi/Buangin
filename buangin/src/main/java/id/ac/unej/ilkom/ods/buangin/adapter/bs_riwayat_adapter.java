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
import id.ac.unej.ilkom.ods.buangin.model.bs_riwayat_model;

public class bs_riwayat_adapter extends RecyclerView.Adapter<bs_riwayat_adapter.MyViewHolder> {
    private Context context;
    private List<bs_riwayat_model> modelList;

    public bs_riwayat_adapter(Context context, List<bs_riwayat_model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_bank_riwayat, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        bs_riwayat_model model = modelList.get(position);
        holder.status.setText(model.getStatus());
        holder.berat.setText(model.getBerat());
        holder.tanggal.setText(model.getTanggal());
        holder.gambar.setImageResource(model.getGambar());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView gambar;
        TextView tanggal, berat, status;

        public MyViewHolder(View itemView) {
            super(itemView);
            gambar = (ImageView) itemView.findViewById(R.id.bank_gambar_riwayat);
            tanggal = (TextView) itemView.findViewById(R.id.bank_tanggal_riwayat);
            berat = (TextView) itemView.findViewById(R.id.bank_berat_riwayat);
            status = (TextView) itemView.findViewById(R.id.bank_status_riwayat);
        }
    }
}
