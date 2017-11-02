package kr.sofac.handsometalk.view.fragments;

import android.app.Fragment;
import android.os.Bundle;


public class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public BaseFragment() {
        //progressBar = new ProgressBar(this.getContext());
        //appUserID = new AppUserID(this.getContext());
        //userDTO = findById(UserDTO.class, appUserID.getID());

    }

}
