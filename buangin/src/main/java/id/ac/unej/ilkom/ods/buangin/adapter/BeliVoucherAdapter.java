package id.ac.unej.ilkom.ods.buangin.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucher;

import static id.ac.unej.ilkom.ods.buangin.util.Util.MB;

public class BeliVoucherAdapter extends RecyclerView.Adapter<BeliVoucherAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(ModelVoucher model);
    }

    private List<ModelVoucher> listVoucher;
    private OnItemClickListener listener;

    public BeliVoucherAdapter(List<ModelVoucher> listVoucher, OnItemClickListener lisetner) {
        this.listener = lisetner;
        this.listVoucher = listVoucher;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_volunteer_voucher, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.bind(listVoucher.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return listVoucher.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgVoucher;
        public TextView namaMitra;
        public TextView namaVoucher;
        public TextView hargaVoucher;
        public TextView kuotaVoucher;


        public MyViewHolder(View itemView) {
            super(itemView);
            imgVoucher = (ImageView) itemView.findViewById(R.id.img_voucher);
            kuotaVoucher = (TextView) itemView.findViewById(R.id.kuota_voucher);
            namaVoucher = (TextView) itemView.findViewById(R.id.nama_voucher);
            namaMitra = (TextView) itemView.findViewById(R.id.nama_mitra);
            hargaVoucher = (TextView) itemView.findViewById(R.id.harga_voucher);
        }

        public void bind(final ModelVoucher model, final OnItemClickListener listener) {
            namaMitra.setText(model.getNamaMitra());
            namaVoucher.setText(model.getNamaVoucher());
            hargaVoucher.setText(model.getHargaPoin());
            kuotaVoucher.setText(model.getJumlahKuota());

            StorageReference ref = FirebaseStorage.getInstance().getReferenceFromUrl(model.getUrl_foto());
            ref.getBytes(MB).addOnCompleteListener(new OnCompleteListener<byte[]>() {
                @Override
                public void onComplete(@NonNull Task<byte[]> task) {
                    if (task.isSuccessful()) {
                        Log.d("InfoAdap", "download foto");
                        Bitmap fotoDownload = BitmapFactory.decodeByteArray(task.getResult(), 0, task.getResult().length);
                        imgVoucher.setImageBitmap(fotoDownload);
                        imgVoucher.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    } else {
                        Log.w(VoucherAdapter.class.getSimpleName(), "gagal download foto:" + task.getException());
                        imgVoucher.setImageResource(R.drawable.download_failed);
                        imgVoucher.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(model);
                }
            });
        }
    }

}
