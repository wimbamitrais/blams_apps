package com.app.wimba.blams.util;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.wimba.blams.R;
import com.app.wimba.blams.activity.ResortDetailActivity;
import com.app.wimba.blams.model.Encyclopedia;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IT02107 on 1/14/2018.
 */

public class EncyclopediaRVAdapter extends RecyclerView.Adapter<EncyclopediaRVAdapter.EncyclopediaViewHolder> {

    List<Encyclopedia> encyclopedias;

    public EncyclopediaRVAdapter(List<Encyclopedia> encyclopedias){
        this.encyclopedias = encyclopedias;
    }

    @Override
    public EncyclopediaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_encyclopedia, viewGroup, false);
        EncyclopediaViewHolder pvh = new EncyclopediaViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final EncyclopediaViewHolder holder, int position) {
        final Integer pos = position;

        holder.resortId = encyclopedias.get(position).getId();
        holder.title.setText(encyclopedias.get(position).getTitle());
        holder.location.setText(encyclopedias.get(position).getLocation());
        holder.profilePhoto.setImageResource(encyclopedias.get(position).getImages().get(1));
        holder.mainPhoto.setImageResource(encyclopedias.get(position).getImages().get(0));
        holder.secondaryPhoto1.setImageResource(encyclopedias.get(position).getImages().get(0));
        holder.secondaryPhoto2.setImageResource(encyclopedias.get(position).getImages().get(1));
        holder.secondaryPhoto3.setImageResource(encyclopedias.get(position).getImages().get(2));
        holder.secondaryPhoto4.setImageResource(encyclopedias.get(position).getImages().get(3));
        holder.likeSize.setText(encyclopedias.get(position).getLikes().toString());
        holder.commentSize.setText(encyclopedias.get(position).getComments().toString());
        holder.description.setText(encyclopedias.get(position).getDescription());

        /*Set Onclick for title*/
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ResortDetailActivity.class);
                intent.putExtra("RESORT_ID", holder.resortId.toString());
                intent.putExtra("RESORT_NAME", holder.title.getText());
                view.getContext().startActivity(intent);
             }
        });


        /*Set Onclick for changing picture*/
        holder.secondaryPhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mainPhoto.setImageResource(encyclopedias.get(pos).getImages().get(0));
            }
        });

        holder.secondaryPhoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mainPhoto.setImageResource(encyclopedias.get(pos).getImages().get(1));
            }
        });

        holder.secondaryPhoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mainPhoto.setImageResource(encyclopedias.get(pos).getImages().get(2));
            }
        });

        holder.secondaryPhoto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mainPhoto.setImageResource(encyclopedias.get(pos).getImages().get(3));
            }
        });
    }

    @Override
    public int getItemCount() {
        return encyclopedias.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class EncyclopediaViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        Long resortId;
        TextView title;
        TextView location;
        CircleImageView profilePhoto;
        ImageView mainPhoto;
        ImageView secondaryPhoto1;
        ImageView secondaryPhoto2;
        ImageView secondaryPhoto3;
        ImageView secondaryPhoto4;
        TextView likeSize;
        TextView commentSize;
        TextView description;

        EncyclopediaViewHolder (View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.card_view);
            title = itemView.findViewById(R.id.title);
            location = itemView.findViewById(R.id.location);
            profilePhoto = itemView.findViewById(R.id.profile_photo);
            mainPhoto = itemView.findViewById(R.id.main_photo);
            secondaryPhoto1= itemView.findViewById(R.id.secondaryPhoto1);
            secondaryPhoto2= itemView.findViewById(R.id.secondaryPhoto2);
            secondaryPhoto3= itemView.findViewById(R.id.secondaryPhoto3);
            secondaryPhoto4= itemView.findViewById(R.id.secondaryPhoto4);
            likeSize = itemView.findViewById(R.id.like_size);
            commentSize = itemView.findViewById(R.id.comment_size);
            description = itemView.findViewById(R.id.description);
        }
    }

}
