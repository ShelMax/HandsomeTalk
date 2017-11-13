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
    private Context ctx;
    private LayoutInflater inflater;


    public AdapterPush(Context context, ArrayList<PushDTO> pushDTOs) {
        this.pushDTOs = pushDTOs;
        this.ctx = context;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_push, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder view, int position) {

        PushDTO pushDTO = getPushDTO(position);

        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        view.titlePush.setText(pushDTO.getTitle());
        view.descriptionPush.setText(pushDTO.getBody());
        view.datePush.setText(pushDTO.getDate());

        //Uri uri = Uri.parse(BASE_URL + Constants.PART_IMAGE + pushDTO.getImage().getFilename());
//        Timber.e(uri.toString());
//        Glide.with(ctx)
//                .load(uri)
//                .override(300,300)
//                .error(R.drawable.hover)
//                .placeholder(R.drawable.hover)
////                .bitmapTransform(new CropCircleTransformation(ctx))
//                .into(view.imageView);

//        view.titleItemPost.setText(postDTO.getName());
//        view.dateItemPost.setText(postDTO.getDate().toString());
//        view.dateItemPost.setText(new SimpleDateFormat("d MMM yyyy", Locale.GERMAN).format(postDTO.getDate())); //"d MMM yyyy HH:mm:ss"
//        if (postDTO.getBody_original() != null)
//            view.messageItemPost.setText(ConvertorHTML.fromHTML(postDTO.getBody_original()));


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
            titlePush = view.findViewById(R.id.idTitlePush);
            descriptionPush = view.findViewById(R.id.idDescriptionPush);
            datePush = view.findViewById(R.id.idDatePush);


        }
    }

}