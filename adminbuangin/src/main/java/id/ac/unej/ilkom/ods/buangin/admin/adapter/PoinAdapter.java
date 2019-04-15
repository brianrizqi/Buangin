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
import id.ac.unej.ilkom.ods.buangin.admin.model.ModelPenjualan;

public class PoinAdapter extends RecyclerView.Adapter<PoinAdapter.MyViewHolder> {
    private Context context;
    private List<ModelPenjualan> penjualanList;

    public PoinAdapter(Context context, List<ModelPenjualan> pembelianList) {
        this.context = context;
        this.penjualanList = pembelianList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_poin, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        ModelPenjualan model = penjualanList.get(i);
        myViewHolder.instansi.setText(model.getNamaInstansi());
        myViewHolder.pemilik.setText(model.getNamaPemilik());
        myViewHolder.tanggal.setText(model.getTanggalBeli());
        myViewHolder.poin.setText(model.getJumlahPoin());
    }

    @Override
    public int getItemCount() {
        return penjualanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView pemilik, instansi, poin, tanggal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pemilik = (TextView) itemView.findViewById(R.id.poin_pemilik);
            instansi = (TextView) itemView.findViewById(R.id.poin_instansi);
            poin = (TextView) itemView.findViewById(R.id.poin_jumlah_poin);
            tanggal = (TextView) itemView.findViewById(R.id.poin_tanggal);
        }
    }
}
