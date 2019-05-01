package com.kumararaja.healinesnews.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kumararaja.healinesnews.Activity.RetrofitSingleton;
import com.kumararaja.healinesnews.Apis;
import com.kumararaja.healinesnews.Model.Modela;
import com.kumararaja.healinesnews.R;

import java.util.List;

public class AdapterA extends RecyclerView.Adapter<AdapterA.ViewHolder> {
    Context context;
    List<Modela.Rover> arraylist;
    Apis apiService;

    public AdapterA(Context context, List<Modela.Rover>arraylist){
        this.arraylist=arraylist;
        this.context=context;
        apiService= RetrofitSingleton.createService(Apis.class);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewTypes){
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.newlistview,parent,false);
        AdapterA.ViewHolder myholder=new AdapterA.ViewHolder(v);
        return myholder;
    }

@Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.iddd.setText(Integer.toString(arraylist.get(position).getId()));
       // holder.name.setText(arraylist.get(position).getNamme());
        holder.landing_date.setText(arraylist.get(position).getLandingDate());
        holder.launching_date.setText(arraylist.get(position).getLaunchDate());
        holder.status.setText(arraylist.get(position).getStatus());
        holder.max_sol.setText(Integer.toString(arraylist.get(position).getMaxSol()));
        holder.max_date.setText(arraylist.get(position).getMaxDate());
        holder.tot_photo.setText(Integer.toString(arraylist.get(position).getTotalPhotos()));
     //   holder.cam.setText(arraylist.get(position).getCameras());
        holder.cname.setText(arraylist.get(position).getName());
       // holder.cfname.setText(arraylist.get(position).getFullName());

}

@Override
    public int getItemCount(){
        return arraylist.size();
}

public class ViewHolder extends RecyclerView.ViewHolder{
        TextView iddd,name,landing_date,launching_date,status,max_sol,max_date,tot_photo,cam,cname,cfname;

        public ViewHolder(View itemView){
            super(itemView);

            iddd=itemView.findViewById(R.id.idd);
            name=itemView.findViewById(R.id.name);
            landing_date=itemView.findViewById(R.id.land_date);
            launching_date=itemView.findViewById(R.id.lanc_date);
            status=itemView.findViewById(R.id.status);
            max_sol=itemView.findViewById(R.id.max_sol);
            max_date=itemView.findViewById(R.id.maxi_date);
            tot_photo=itemView.findViewById(R.id.tot_photo);
            cam=itemView.findViewById(R.id.cam);
            cname=itemView.findViewById(R.id.cName);
            cfname=itemView.findViewById(R.id.cfname);
        }
    }
}
