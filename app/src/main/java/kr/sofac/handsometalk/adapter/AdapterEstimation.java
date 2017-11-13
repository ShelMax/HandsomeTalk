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

public class AdapterEstimation extends RecyclerView.Adapter<AdapterEstimation.ViewHolder> {
    private ArrayList<EventDTO> eventDTOS;
    private Context ctx;
    private LayoutInflater inflater;


    public AdapterEstimation(Context context, ArrayList<EventDTO> eventDTOS) {
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

//        view.titleItemPost.setText(postDTO.getName());
//        view.dateItemPost.setText(postDTO.getDate().toString());
//        view.dateItemPost.setText(new SimpleDateFormat("d MMM yyyy", Locale.GERMAN).format(postDTO.getDate())); //"d MMM yyyy HH:mm:ss"
//        if (postDTO.getBody_original() != null)
//            view.messageItemPost.setText(ConvertorHTML.fromHTML(postDTO.getBody_original()));

//        //FILES
//        if (postDTO.getDocs().size() > 0) {
//            view.linearLayoutFiles.setVisibility(View.VISIBLE);
//            view.linearLayoutFiles.removeAllViews();
//            View fileItemView = inflater.inflate(R.layout.item_preview_post_file, null);
//            TextView textView = (TextView) fileItemView.findViewById(R.id.idNameFile);
//            if (postDTO.getDocs().size() == 1) {
//                textView.setText("" + postDTO.getDocs().get(0) + "");
//            } else if (postDTO.getDocs().size() > 1) {
//                textView.setText(postDTO.getDocs().get(0) + " and " + (postDTO.getDocs().size() - 1) + " files");
//            }
//            view.linearLayoutFiles.addView(fileItemView, lParams);
//        } else {
//            view.linearLayoutFiles.setVisibility(View.GONE);
//        }

//        //PHOTO CAROUSEL
//        view.recyclerView.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false));
//        if (postDTO.getImages().size() > 0) {
//            view.recyclerView.setVisibility(View.VISIBLE);
//            for (String imageName : postDTO.getImages()) {
//                listImage.add(BASE_URL + PART_POST + imageName);
//            }
//            view.recyclerView.setAdapter(new AdapterGalleryGroup(listImage));
//        } else {
//            view.recyclerView.setVisibility(View.GONE);
//        }

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