package kr.sofac.handsometalk.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.dto.MessageDTO;
import kr.sofac.handsometalk.util.PreferenceApp;

import static kr.sofac.handsometalk.Constants.BASE_URL;
import static kr.sofac.handsometalk.Constants.PART_IMAGE;

/**
 * Created by Maxim on 09.11.2017.
 */

public class AdapterMessages extends RecyclerView.Adapter<AdapterMessages.ViewHolder> {
    private ArrayList<MessageDTO> messageDTOs;
    private Context ctx;
    private LayoutInflater inflater;


    public AdapterMessages(Context context, ArrayList<MessageDTO> messageDTOs) {
        this.messageDTOs = messageDTOs;
        this.ctx = context;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder view, int position) {

        MessageDTO messageDTO = getMessageDTO(position);
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (messageDTO.getUser_id().equals(new PreferenceApp(ctx).getUserID().toString())) {
            view.constraintLayoutMy.setVisibility(View.VISIBLE);
            view.constraintLayoutHim.setVisibility(View.GONE);
            if ("image".equals(messageDTO.getType())) {
                view.descriptionEstimationMy.setVisibility(View.GONE);
                view.imageViewMy.setVisibility(View.VISIBLE);
//                Timber.e(BASE_URL + PART_IMAGE + messageDTO.getMessage());
                Glide.with(view.itemView.getContext())
                        .load(BASE_URL + PART_IMAGE + messageDTO.getMessage())
                        .override(300, 300)
                        .error(R.drawable.hover)
                        .placeholder(R.drawable.hover)
                        .into(view.imageViewMy);
            } else {
                view.imageViewMy.setVisibility(View.GONE);
                view.descriptionEstimationMy.setVisibility(View.VISIBLE);
                view.descriptionEstimationMy.setText(messageDTO.getMessage());
            }
            view.dateEstimationMy.setText(messageDTO.getDate());
        } else {
            view.constraintLayoutHim.setVisibility(View.VISIBLE);
            view.constraintLayoutMy.setVisibility(View.GONE);
            if ("image".equals(messageDTO.getType())) {
                view.descriptionEstimationHim.setVisibility(View.GONE);
                view.imageViewHim.setVisibility(View.VISIBLE);
                Glide.with(view.itemView.getContext())
                        .load(BASE_URL + PART_IMAGE + messageDTO.getMessage())
                        .override(300, 300)
                        .error(R.drawable.hover)
                        .placeholder(R.drawable.hover)
                        .into(view.imageViewHim);
            } else {
                view.imageViewMy.setVisibility(View.GONE);
                view.descriptionEstimationHim.setVisibility(View.VISIBLE);
                view.descriptionEstimationHim.setText(messageDTO.getMessage());
            }
            view.dateEstimationHim.setText(messageDTO.getDate());
        }
    }

    @Override
    public int getItemCount() {
        return messageDTOs.size();
    }

    public Object getItem(int position) {
        return messageDTOs.get(position);
    }

    MessageDTO getMessageDTO(int position) {
        return ((MessageDTO) getItem(position));
    }

    // CLASS VIEW HOLDER

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //LinearLayout linearLayoutFiles;
        TextView descriptionEstimationMy, dateEstimationMy, descriptionEstimationHim, dateEstimationHim;
        ConstraintLayout constraintLayoutMy, constraintLayoutHim;
        ImageView imageViewMy, imageViewHim;

        public ViewHolder(View view) {
            super(view);

            descriptionEstimationMy = view.findViewById(R.id.idDescriptionEstimationMy);
            dateEstimationMy = view.findViewById(R.id.idDateEstimationMy);

            descriptionEstimationHim = view.findViewById(R.id.idDescriptionEstimationHim);
            dateEstimationHim = view.findViewById(R.id.idDateEstimationHim);

            constraintLayoutMy = view.findViewById(R.id.idMyMessage);
            constraintLayoutHim = view.findViewById(R.id.idHimMessage);

            imageViewMy = view.findViewById(R.id.imageViewMy);
            imageViewHim = view.findViewById(R.id.imageViewHim);

        }
    }

}