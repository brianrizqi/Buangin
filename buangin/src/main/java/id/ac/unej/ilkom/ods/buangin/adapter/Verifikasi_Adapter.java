package id.ac.unej.ilkom.ods.buangin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.ModelSampah;

public class Verifikasi_Adapter extends RecyclerView.Adapter<Verifikasi_Adapter.MyHolder> {

    List<ModelSampah> list;
    Context context;

    public Verifikasi_Adapter(List<ModelSampah> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_bank_verifikasi, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        ModelSampah model = list.get(position);
        holder.kode.setText(model.getKodeSampah());
        holder.submit.setText(model.getTanggalSubmit());
        holder.berat.setText(model.getBeratSampah());
        holder.jenis.setText(model.getJenisSampah());
        holder.poin.setText(model.getPoinSampah());
        holder.harga.setText(model.getHargaSampah());
        holder.status.setText(model.getStatusVerifikasi());
    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try {
            if (list.size() == 0) {
                arr = 0;
            } else {
                arr = list.size();
            }
        } catch (Exception e) {

        }
        return arr;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView kode, submit, poin, jenis, harga, berat, status;

        public MyHolder(View itemView) {
            super(itemView);
            kode = (TextView) itemView.findViewById(R.id.verifikasi_text_kode);
            submit = (TextView) itemView.findViewById(R.id.verifikasi_text_submit);
            poin = (TextView) itemView.findViewById(R.id.verifikasi_text_poin);
            jenis = (TextView) itemView.findViewById(R.id.verifikasi_text_jenis);
            harga = (TextView) itemView.findViewById(R.id.verifikasi_text_harga);
            berat = (TextView) itemView.findViewById(R.id.verifikasi_text_berat);
            status = (TextView) itemView.findViewById(R.id.verifikasi_text_status);
        }
    }
}
