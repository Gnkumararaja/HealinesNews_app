package com.kumararaja.healinesnews.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kumararaja.healinesnews.Activity.RetrofitSingleton;
import com.kumararaja.healinesnews.Apis;
import com.kumararaja.healinesnews.Model.Article;
import com.kumararaja.healinesnews.R;

import java.util.List;

import retrofit2.Retrofit;

public class NewsRecycler extends RecyclerView.Adapter<NewsRecycler.ViewHolder> {
    Context context;
    List<Article> arrayList;
    Apis apiService;

public NewsRecycler(Context context,List<Article>data){
    this.arrayList=data;
this.context=context;
apiService= RetrofitSingleton.createService(Apis.class);
}

@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewTypes){
    View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listview,parent,false);
    NewsRecycler.ViewHolder myViewHolder=new NewsRecycler.ViewHolder(v);
    return myViewHolder;
}

@Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
    holder.author.setText(arrayList.get(position).getAuthor());
    holder.title.setText(arrayList.get(position).getTitle());
    holder.description.setText(arrayList.get(position).getDescription());
    holder.content.setText(arrayList.get(position).getContent());
    Glide.with(context).load(arrayList.get(position).getUrlToImage()).into(holder.imageView);
}

@Override
    public int getItemCount(){
    return arrayList.size();
}

public class ViewHolder extends RecyclerView.ViewHolder{
    TextView author,title,description,content;
    ImageView imageView;

    public ViewHolder(View itemView){
        super(itemView);

        author=itemView.findViewById(R.id.author);
        title=itemView.findViewById(R.id.titlee);
        description=itemView.findViewById(R.id.desp);
        content=itemView.findViewById(R.id.contt);
        imageView=itemView.findViewById(R.id.img);

    }
}
}


