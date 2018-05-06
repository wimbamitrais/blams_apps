package com.app.wimba.blams.util;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.wimba.blams.R;
import com.app.wimba.blams.model.Encyclopedia;
import com.app.wimba.blams.model.Kuliner;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IT02107 on 5/6/2018.
 */

public class KulinerRVAdapter extends RecyclerView.Adapter<KulinerRVAdapter.KulinerViewHolder> {


    List<Kuliner> kuliners;

    public KulinerRVAdapter(List<Kuliner> kuliners){
        this.kuliners= kuliners;
    }

    @Override
    public KulinerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_kuliner, viewGroup, false);
        KulinerViewHolder pvh = new KulinerViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(KulinerViewHolder holder, int position) {
        final Integer pos = position;

        holder.kulinerTitle.setText(kuliners.get(pos).getName());
        holder.kulinerIcon.setImageResource(kuliners.get(pos).getPhoto());

    }

    @Override
    public int getItemCount() {
        return kuliners.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class KulinerViewHolder extends RecyclerView.ViewHolder {
        ImageView kulinerIcon;
        TextView kulinerTitle;

        KulinerViewHolder(View itemView) {
            super(itemView);

            kulinerIcon = itemView.findViewById(R.id.kuliner_icon);
            kulinerTitle = itemView.findViewById(R.id.kuliner_title);

        }
    }
}
