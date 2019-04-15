package id.ac.unej.ilkom.ods.buangin.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.cv_poin_model;

public class rv_poin_adapter extends RecyclerView.Adapter<rv_poin_adapter.list_poin> {

    private ArrayList<cv_poin_model> dataList;

    public rv_poin_adapter(ArrayList<cv_poin_model> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public list_poin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cv_menu_point, parent, false);
        return new list_poin(view);
    }

    @Override
    public void onBindViewHolder(@NonNull list_poin holder, int position) {
        holder.txtNo.setText(dataList.get(position).getNo());
        holder.txtTanggal.setText(dataList.get(position).getTanggal());
        holder.txtPoin.setText(dataList.get(position).getPoin());
        holder.txtSampah.setText(dataList.get(position).getSampah());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class list_poin extends RecyclerView.ViewHolder {
        private TextView txtNo, txtTanggal, txtPoin, txtSampah;

        public list_poin(View itemView) {
            super(itemView);
            txtNo = (TextView) itemView.findViewById(R.id.text_poinNo);
            txtTanggal = (TextView) itemView.findViewById(R.id.text_poinTanggal);
            txtPoin = (TextView) itemView.findViewById(R.id.text_poinPoin);
            txtSampah = (TextView) itemView.findViewById(R.id.text_poinSampah);
        }
    }
}
