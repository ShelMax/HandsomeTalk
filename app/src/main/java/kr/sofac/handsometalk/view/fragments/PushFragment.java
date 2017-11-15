package kr.sofac.handsometalk.view.fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.adapter.AdapterPush;
import kr.sofac.handsometalk.adapter.RecyclerItemClickListener;
import kr.sofac.handsometalk.dto.GetPushDTO;
import kr.sofac.handsometalk.dto.PushDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.server.type.ServerResponse;
import kr.sofac.handsometalk.util.PreferenceApp;
import kr.sofac.handsometalk.util.ProgressBar;
import timber.log.Timber;

public class PushFragment extends BaseFragment {


    public RecyclerView recyclerViewPush;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdapterPush adapterPush;
    private ProgressBar processBar;
    private ConstraintLayout emptyView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_push, container, false);

        recyclerViewPush = (RecyclerView) rootView.findViewById(R.id.idRecyclerPush);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerViewPush.setHasFixedSize(true);
        recyclerViewPush.setLayoutManager(mLayoutManager);
        processBar = new ProgressBar(getActivity());
        processBar.showView();
        emptyView = rootView.findViewById(R.id.idRecyclerIsEmpty);

        new Connection<ArrayList<PushDTO>>().getListPush(
                new GetPushDTO(
                        new PreferenceApp(getActivity()).getUser().getId(),
                        new PreferenceApp(getActivity()).getGoogleKey()),
                (isSuccess, answerServerResponse) -> {
            if (isSuccess) {
                if (answerServerResponse.getDataTransferObject().isEmpty()) {
                    Timber.e("empty");
                    recyclerViewPush.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    recyclerViewPush.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                    initUI(answerServerResponse.getDataTransferObject());
                }
            } else {
                Timber.e("Error!");
            }
            processBar.dismissView();
        });

        // recyclerViewEvent = rootView.findViewById(R.id.);
        return rootView;
    }

    public void initUI(ArrayList<PushDTO> pushDTOs) {

        adapterPush = new AdapterPush(getActivity(), pushDTOs);
        recyclerViewPush.setAdapter(adapterPush);

        recyclerViewPush.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerViewPush, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                if (postDTOs != null) {
//                    intentDetailPostActivity.putExtra(POST_ID, postDTOs.get(position).getId());
//                    startActivityForResult(intentDetailPostActivity, 1);
//                }
            }

            @Override
            public void onLongItemClick(View view, final int position) {
//                postDTO = postDTOs.get(position);
//                GroupFragment.idPost = postDTOs.get(position).getId();
//
//                if (postDTO.getUser_id().equals(userDTO.getId()) || userDTO.isAdmin()) {
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                    builder.setItems(R.array.choice_double_click_post, (dialog, which) -> {
//                        switch (which) {
//                            case 0: // Edit
//                                changePost(GroupFragment.idPost);
//                                break;
//                            case 1: // Delete
//                                Timber.e("Click delete");
//                                deletePost();
//                                break;
//                        }
//                    });
//                    builder.show();
//                }
            }
        }));
    }
}
