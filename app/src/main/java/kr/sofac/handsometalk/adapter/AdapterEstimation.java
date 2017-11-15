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
import kr.sofac.handsometalk.dto.EstimateDTO;

/**
 * Created by Maxim on 09.11.2017.
 */

public class AdapterEstimation extends RecyclerView.Adapter<AdapterEstimation.ViewHolder> {
    private ArrayList<EstimateDTO> estimateDTOs;
    private Context ctx;
    private LayoutInflater inflater;


    public AdapterEstimation(Context context, ArrayList<EstimateDTO> estimateDTOs) {
        this.estimateDTOs = estimateDTOs;
        this.ctx = context;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_talk, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder view, int position) {

        EstimateDTO estimateDTO = getEstimateDTO(position);
        //Timber.e(estimateDTO.toString());
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        view.titleEstimation.setText(estimateDTO.getSku());
            view.descriptionEstimation.setText(estimateDTO.getMessage().getMessage());

        view.dateEstimation.setText(estimateDTO.getMessage_date());

    }

    @Override
    public int getItemCount() {
        return estimateDTOs.size();
    }

    public Object getItem(int position) {
        return estimateDTOs.get(position);
    }

    EstimateDTO getEstimateDTO(int position) {
        return ((EstimateDTO) getItem(position));
    }

    // CLASS VIEW HOLDER

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //LinearLayout linearLayoutFiles;
        TextView titleEstimation, descriptionEstimation, dateEstimation;

        public ViewHolder(View view) {
            super(view);
            titleEstimation = view.findViewById(R.id.idTitleEstimation);
            descriptionEstimation = view.findViewById(R.id.idDescriptionEstimation);
            dateEstimation = view.findViewById(R.id.idDateEstimation);


        }
    }

}