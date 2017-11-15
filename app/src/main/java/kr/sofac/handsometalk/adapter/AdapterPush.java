package kr.sofac.handsometalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.dto.PushDTO;

/**
 * Created by Maxim on 09.11.2017.
 */

public class AdapterPush extends RecyclerView.Adapter<AdapterPush.ViewHolder> {
    private ArrayList<PushDTO> pushDTOs;
    private LayoutInflater inflater;


    public AdapterPush(Context context, ArrayList<PushDTO> pushDTOs) {
        this.pushDTOs = pushDTOs;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_push, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder view, int position) {

        view.titlePush.setText(getPushDTO(position).getTitle());
        view.descriptionPush.setText(getPushDTO(position).getBody());
        view.datePush.setText(getPushDTO(position).getDate());

    }

    @Override
    public int getItemCount() {
        return pushDTOs.size();
    }

    public Object getItem(int position) {
        return pushDTOs.get(position);
    }

    PushDTO getPushDTO(int position) {
        return ((PushDTO) getItem(position));
    }

    // CLASS VIEW HOLDER

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titlePush, descriptionPush, datePush;

        public ViewHolder(View view) {
            super(view);
            titlePush = view.findViewById(R.id.idTitleEstimation);
            descriptionPush = view.findViewById(R.id.idDescriptionPush);
            datePush = view.findViewById(R.id.idDatePush);


        }
    }

}