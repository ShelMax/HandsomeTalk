package kr.sofac.handsometalk.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.sofac.handsometalk.Constants;
import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.dto.EventDTO;
import timber.log.Timber;

import static kr.sofac.handsometalk.Constants.BASE_URL;

/**
 * Created by Maxim on 09.11.2017.
 */

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.ViewHolder> {
    private ArrayList<EventDTO> eventDTOS;
    private Context ctx;
    private LayoutInflater inflater;


    public AdapterEvent(Context context, ArrayList<EventDTO> eventDTOS) {
        this.eventDTOS = eventDTOS;
        this.ctx = context;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder view, int position) {

        EventDTO eventDTO = getEventDTO(position);

        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Uri uri = Uri.parse(BASE_URL + Constants.PART_IMAGE + eventDTO.getImage().getFilename());
        Timber.e(uri.toString());
        Glide.with(ctx)
                .load(uri)
                .override(300,300)
                .error(R.drawable.hover)
                .placeholder(R.drawable.hover)
//                .bitmapTransform(new CropCircleTransformation(ctx))
                .into(view.imageView);

    }

    @Override
    public int getItemCount() {
        return eventDTOS.size();
    }

    public Object getItem(int position) {
        return eventDTOS.get(position);
    }

    EventDTO getEventDTO(int position) {
        return ((EventDTO) getItem(position));
    }

    // CLASS VIEW HOLDER

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //LinearLayout linearLayoutFiles;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.idImageView);

        }
    }

}