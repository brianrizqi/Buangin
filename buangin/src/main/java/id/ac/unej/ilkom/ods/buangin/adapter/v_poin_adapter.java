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
import id.ac.unej.ilkom.ods.buangin.model.v_poin_model;

public class v_poin_adapter extends RecyclerView.Adapter<v_poin_adapter.MyViewHolder> {

    private Context context;
    private List<v_poin_model> modelList;


    public v_poin_adapter(Context context, List<v_poin_model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_volunteer_poin, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        v_poin_model model = modelList.get(position);
        holder.tanggal.setText(model.getTanggal());
        holder.poin.setText(model.getPoin());
        holder.berat.setText(model.getBerat());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tanggal, poin, berat;

        public MyViewHolder(View itemView) {
            super(itemView);
            tanggal = (TextView) itemView.findViewById(R.id.volunteer_poin_tanggal);
            poin = (TextView) itemView.findViewById(R.id.volunteer_poin_poin);
            berat = (TextView) itemView.findViewById(R.id.volunteer_poin_beratSampah);
        }
    }
}
