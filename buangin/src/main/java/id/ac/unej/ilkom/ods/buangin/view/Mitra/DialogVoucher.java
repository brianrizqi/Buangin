package id.ac.unej.ilkom.ods.buangin.view.Mitra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.BaseApi;
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucherVolunteer;


public class DialogVoucher extends DialogFragment {
    private TextView kodeVoucher, namaMitra, namaPembeli, poinVoucher, namaVoucher;
    private Button verifikasi, batal;

    private ModelVoucherVolunteer voucher;
    private String key;
    public DialogVoucher() {
    }

    public void setKodeVoucher(ModelVoucherVolunteer voucher, String key) {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_voucher, container, false);

        kodeVoucher = (TextView) view.findViewById(R.id.mitra_dialog_kode_voucher);
        namaVoucher = (TextView) view.findViewById(R.id.mitra_dialog_nama_voucher);
        namaMitra = (TextView) view.findViewById(R.id.mitra_dialog_mitra_voucher);
        namaPembeli = (TextView) view.findViewById(R.id.mitra_dialog_pembeli_voucher);
        poinVoucher = (TextView) view.findViewById(R.id.mitra_dialog_poin_voucher);
        verifikasi = (Button) view.findViewById(R.id.mitra_dialog_btn_verifikasi);
        batal = (Button) view.findViewById(R.id.mitra_dialog_btn_batal);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        kodeVoucher.setText(voucher.getKodeVoucher());

        verifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                        .setTitle("Verifikasi Voucher")
                        .setCancelable(false)
                        .setMessage("Penukaran kode " + voucher.getKodeVoucher() + " telah telah berhasil dilakukan")
                        .setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference(BaseApi.TABEL_VOUCHER_VOLUNTEER).child(key).child("statusVoucher");
                                ref.setValue(ModelVoucherVolunteer.VOUCHER_DITUKAR);
                                dismiss();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
