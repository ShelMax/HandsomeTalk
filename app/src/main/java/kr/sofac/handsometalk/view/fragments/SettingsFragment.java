package kr.sofac.handsometalk.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.util.PreferenceApp;

import static kr.sofac.handsometalk.Constants.BASE_URL;
import static kr.sofac.handsometalk.Constants.PART_IMAGE;

public class SettingsFragment extends BaseFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Glide.with(view.getContext())
                .load(BASE_URL + PART_IMAGE + new PreferenceApp(this.getActivity()).getUser().getAvatar())
                .bitmapTransform(new CropCircleTransformation(this.getActivity()))
                .override(200, 200)
                .error(R.drawable.avatar)
                .placeholder(R.drawable.avatar)
                .into((ImageView) view.findViewById(R.id.imageAvatar));

        ((TextView) view.findViewById(R.id.tv_name_profile)).setText((new PreferenceApp(this.getActivity()).getUser().getName()));
        ((TextView) view.findViewById(R.id.tv_email_profile)).setText((new PreferenceApp(this.getActivity()).getUser().getEmail()));

                return view;
    }

}
