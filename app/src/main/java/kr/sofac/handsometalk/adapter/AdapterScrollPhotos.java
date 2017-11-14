package kr.sofac.handsometalk.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.sofac.handsometalk.R;

/**
 * Created by Maxim on 04.10.2017.
 */

public class AdapterScrollPhotos extends RecyclerView.Adapter<AdapterScrollPhotos.ViewHolder> {

    private ArrayList<Uri> uriArrayList;
    private ClickListener itemClickListener;

    public interface ClickListener {
        void onMyClick(View view, int position);
    }

    public AdapterScrollPhotos(ArrayList<Uri> uriArrayList) {
        this.uriArrayList = uriArrayList;
    }

    public void setItemClickListener(ClickListener myClickListener) {
        this.itemClickListener = myClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_scroll_photo, parent, false);
        return new ViewHolder(v);
    }

    public static int getPxFromDp(int dimensionDp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dimensionDp * density + 0.5f);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //holder.linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            Glide.with(holder.itemView.getContext())
                    .load(uriArrayList.get(position))
                    .override(getPxFromDp(50, holder.itemView.getContext()), getPxFromDp(50, holder.itemView.getContext()))
                    .error(R.drawable.hover)
                    .placeholder(R.drawable.hover)
                    .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return uriArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView image;
        private FrameLayout imageButton;
        //private LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            //linearLayout = (LinearLayout) itemView.findViewById(R.id.idFrameGallery);
            image = (ImageView) itemView.findViewById(R.id.imagePhotoPreview);
            imageButton= (FrameLayout) itemView.findViewById(R.id.idButtonDeleting);
            itemView.setOnClickListener(this);
            imageButton.setOnClickListener(this);
            image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onMyClick(v, getAdapterPosition());
        }
    }















}
