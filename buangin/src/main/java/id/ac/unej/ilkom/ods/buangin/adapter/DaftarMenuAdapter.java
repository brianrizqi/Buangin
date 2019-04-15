package id.ac.unej.ilkom.ods.buangin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.DaftarMenu;

public class DaftarMenuAdapter extends BaseAdapter {

    private Context konteks;
    private List<DaftarMenu> daftarMenu;

    public DaftarMenuAdapter(Context konteks, List<DaftarMenu> list) {
        this.konteks = konteks;
        this.daftarMenu = list;
    }

    @Override
    public int getCount() {
        return daftarMenu.size();
    }

    @Override
    public Object getItem(int position) {
        return daftarMenu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DaftarMenu m = daftarMenu.get(position);
        View v = View.inflate(konteks, R.layout.list_item, null);
        ImageView img = (ImageView) v.findViewById(R.id.img_menu);
        img.setImageResource(m.getIDFoto());
        TextView t = (TextView) v.findViewById(R.id.title_menu);
        t.setText(m.getNama());
        return v;
    }
}
