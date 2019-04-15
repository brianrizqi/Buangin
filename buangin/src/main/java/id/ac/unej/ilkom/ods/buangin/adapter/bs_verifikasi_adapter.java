package id.ac.unej.ilkom.ods.buangin.adapter;//package id.ac.unej.ilkom.ods.buangin.Adapter;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.List;
//
//import id.ac.unej.ilkom.ods.buangin.Model.bs_verifikasi_model;
//import id.ac.unej.ilkom.ods.buangin.R;
//
//public class bs_verifikasi_adapter extends RecyclerView.Adapter<bs_verifikasi_adapter.MyViewHolder> {
//    Context context;
//    List<bs_verifikasi_model> modelList;
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_bank_verifikasi, parent, false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        bs_verifikasi_model model = modelList.get(position);
//        holder.gambar.setImageResource(model.getGambar());
//        holder.status.setText(model.getStatus());
//        holder.tanggal.setText(model.getTanggal());
//        holder.berat.setText(model.getBerat());
//        holder.waktu.setText(model.getWaktu());
//    }
//
//    @Override
//    public int getItemCount() {
//        return modelList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView tanggal, berat, waktu, status;
//        ImageView gambar;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            tanggal = (TextView) itemView.findViewById(R.id.bank_tanggal_verifikasi);
//            berat = (TextView) itemView.findViewById(R.id.bank_berat_sampah);
//            waktu = (TextView) itemView.findViewById(R.id.bank_waktu_verifikasi);
//            status = (TextView) itemView.findViewById(R.id.bank_status_verifikasi);
//            gambar = (ImageView) itemView.findViewById(R.id.bank_gambar_verifikasi);
//        }
//    }
//
//    public bs_verifikasi_adapter(Context context, List<bs_verifikasi_model> modelList) {
//        this.context = context;
//        this.modelList = modelList;
//    }
//}
