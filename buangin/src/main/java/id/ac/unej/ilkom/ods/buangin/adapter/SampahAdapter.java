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

public class SampahAdapter extends RecyclerView.Adapter<SampahAdapter.MyViewHolder> {
    private Context context;
    private List<ModelSampah> sampahList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_volunteer_sampah, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelSampah model = sampahList.get(position);
        holder.kode.setText(model.getKodeSampah());
        holder.status.setText(model.getStatusVerifikasi());
        holder.ambil.setText(model.getTanggalSubmit());
        holder.berakhir.setText(model.getTanggalAkhir());
    }

    @Override
    public int getItemCount() {
        return sampahList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView kode;
        public TextView status;
        public TextView ambil;
        public TextView berakhir;

        public MyViewHolder(View itemView) {
            super(itemView);
            kode = (TextView) itemView.findViewById(R.id.volunteer_kode_sampah);
            status = (TextView) itemView.findViewById(R.id.volunteer_status_sampah);
            ambil = (TextView) itemView.findViewById(R.id.volunteer_ambil_sampah);
            berakhir = (TextView) itemView.findViewById(R.id.volunteer_akhir_sampah);
        }
    }

    public SampahAdapter(Context context, List<ModelSampah> sampahList) {
        this.context = context;
        this.sampahList = sampahList;
    }
}
