package org.victor.mycashbookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.victor.mycashbookapp.Model.data;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

public class CustomAdapter extends ArrayAdapter<data> {
    public static final String Tag = "data";
    private Context context;
    int resource;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<data> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).getId();
        String simbol = getItem(position).getSimbol();
        String nominal = getItem(position).getNominal();
        String keterangan = getItem(position).getKeterangan();
        String tgl = getItem(position).getTgl();
        String status = getItem(position).getStatus();

        data dt = new data(id, simbol,tgl, nominal,keterangan, status);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource, parent, false);

        TextView txsimbol = (TextView) convertView.findViewById(R.id.item_simbol);
        TextView txnominal = (TextView) convertView.findViewById(R.id.item_nominal);
        TextView txket = (TextView) convertView.findViewById(R.id.item_keterangan);
        TextView txtgl = (TextView) convertView.findViewById(R.id.item_tanggal);
        ImageView arrow = (ImageView) convertView.findViewById(R.id.gmbr);
        String amount = formatRupiah(Integer.valueOf(nominal));
        System.out.println("masukan: " + status);
        if (status.equals("masuk")){
            txnominal.setText(amount);
            arrow.setImageResource(R.drawable.green_arrow);
            txsimbol.setText("[ + ]");
        }else if(status.equals("keluar")){
            txnominal.setText(amount);
            arrow.setImageResource(R.drawable.red_arrow);
            txsimbol.setText("[ - ]");
        }
        txtgl.setText(tgl);
        txket.setText(keterangan);

        return convertView;
    }
    private String formatRupiah(int number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}
//public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
//    private Context context;
////    private ArrayList id,simbol,nominal,keterangan,tgl,status;
//    ImageView arrow;
//    private final List<data> dataList;
//    RecyclerView recyclerView;
//
//    public CustomAdapter(Context applicationContext, int item_detail, List<data> dataList){
//        this.dataList=dataList;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail,parent,false);
//        return new MyViewHolder(inflater);
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.simbol.setText(dataList.get(position).getSimbol());
//        holder.nominal.setText(dataList.get(position).getNominal());
//        holder.keterangan.setText(dataList.get(position).getKeterangan());
//        holder.tgl.setText(dataList.get(position).getTgl());
//        holder.status.setText(dataList.get(position).getStatus());
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView simbol;
//        TextView nominal;
//        TextView keterangan;
//        TextView tgl;
//        TextView status;
//        ImageView arrow;
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            simbol = (TextView) itemView.findViewById(R.id.item_simbol);
//            nominal = (TextView) itemView.findViewById(R.id.item_nominal);
//            keterangan = (TextView) itemView.findViewById(R.id.item_keterangan);
//            tgl = (TextView) itemView.findViewById(R.id.item_tanggal);
//            status = (TextView) itemView.findViewById(R.id.item_status);
//            arrow = (ImageView) itemView.findViewById(R.id.item_arrow);
//
//            //System.out.println("masukan: " + dataList.get(getAdapterPosition()).getSimbol().trim());
//            if (itemView){
//                //status.setVisibility(View.VISIBLE);
//                arrow.setImageResource(R.drawable.green_arrow);
//            }else{
//                //status.setVisibility(View.GONE);
//                arrow.setImageResource(R.drawable.red_arrow);
//            }
//            }
//        }
//}
