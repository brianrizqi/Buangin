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
import id.ac.unej.ilkom.ods.buangin.model.v_daftarBank_model;

public class v_daftarBank_adapter extends RecyclerView.Adapter<v_daftarBank_adapter.MyViewHolder> {
    private Context context;
    private List<v_daftarBank_model> modelList;

    public v_daftarBank_adapter(Context context, List<v_daftarBank_model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_volunteer_bank, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        v_daftarBank_model model = modelList.get(position);
        holder.pemilik.setText(model.getNamaPemilik());
        holder.instansi.setText(model.getNamaInstansi());
        holder.alamat.setText(model.getAlamat());
        holder.telp.setText(model.getTelp());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView instansi, pemilik, alamat, telp;
        private ImageView gambar;

        public MyViewHolder(View itemView) {
            super(itemView);
            instansi = (TextView) itemView.findViewById(R.id.volunteer_bank_instansi);
            pemilik = (TextView) itemView.findViewById(R.id.volunteer_bank_pemilik);
            alamat = (TextView) itemView.findViewById(R.id.volunteer_bank_alamat);
//            gambar = (ImageView) itemView.findViewById(R.id.volunteer_bank_img);
            telp = (TextView) itemView.findViewById(R.id.volunteer_bank_telp);
        }
    }
}
