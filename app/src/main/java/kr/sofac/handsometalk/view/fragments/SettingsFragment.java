package kr.sofac.handsometalk.view.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.dto.UserDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.util.PreferenceApp;
import kr.sofac.handsometalk.util.ProgressBar;

import static kr.sofac.handsometalk.Constants.BASE_URL;
import static kr.sofac.handsometalk.Constants.PART_IMAGE;

public class SettingsFragment extends BaseFragment {

    private FloatingActionButton button;
    private EditText phoneNumberEdit, emailEdit;
    private ProgressBar progressBar;
    private View view;
    private UserDTO userDTO;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        progressBar = new ProgressBar(getActivity());

        Glide.with(view.getContext())
                .load(BASE_URL + PART_IMAGE + new PreferenceApp(this.getActivity()).getUser().getAvatar())
                .bitmapTransform(new CropCircleTransformation(this.getActivity()))
                .override(200, 200)
                .error(R.drawable.avatar)
                .placeholder(R.drawable.avatar)
                .into((ImageView) view.findViewById(R.id.imageAvatar));

        button = view.findViewById(R.id.fabSendProfile);
        phoneNumberEdit = view.findViewById(R.id.phone_number_edit);
        emailEdit = view.findViewById(R.id.email_edit);

        setDataTextView();

        button.setOnClickListener(view1 -> {
            if (!phoneNumberEdit.getText().toString().isEmpty() && !emailEdit.getText().toString().isEmpty()) {
                newRequest(phoneNumberEdit.getText().toString(), emailEdit.getText().toString());
            } else {
                Toast.makeText(getActivity(), "Field empty!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void newRequest(String phone, String email) {
        progressBar.showView();
        userDTO = new PreferenceApp(getActivity()).getUser();
        userDTO.setPhone(phone);
        userDTO.setEmail(email);
        userDTO.setUser_id(userDTO.getId());
        new Connection<String>().updateUser(userDTO,
                (isSuccess, answerServerResponse) -> {
                    if (isSuccess) {
                        new PreferenceApp(getActivity()).setUser(userDTO);
                        setDataTextView();
                    } else {
                        Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                    }
                    progressBar.dismissView();
                });
    }

    public void setDataTextView() {
        ((TextView) view.findViewById(R.id.tv_name_profile)).setText(new PreferenceApp(this.getActivity()).getUser().getName());
        ((TextView) view.findViewById(R.id.tv_email_profile)).setText(new PreferenceApp(this.getActivity()).getUser().getEmail());
        phoneNumberEdit.setText(new PreferenceApp(this.getActivity()).getUser().getPhone());
        emailEdit.setText(new PreferenceApp(this.getActivity()).getUser().getEmail());
    }


}
