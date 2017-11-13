package kr.sofac.handsometalk.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.adapter.AdapterEstimation;
import kr.sofac.handsometalk.adapter.RecyclerItemClickListener;
import kr.sofac.handsometalk.dto.EstimateDTO;
import kr.sofac.handsometalk.dto.GetEstimationsDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.server.type.ServerResponse;
import kr.sofac.handsometalk.util.ProgressBar;
import kr.sofac.handsometalk.view.DetailEstimateActivity;
import timber.log.Timber;

public class TalkFragment extends BaseFragment {

    public RecyclerView recyclerViewEstimation;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdapterEstimation adapterEstimation;
    private ProgressBar processBar;
    private TextView emptyView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_talk, container, false);

        recyclerViewEstimation = (RecyclerView) rootView.findViewById(R.id.idRecyclerEstimation);
        emptyView = (TextView) rootView.findViewById(R.id.recyclerTalkEmpty);

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerViewEstimation.setHasFixedSize(true);
        recyclerViewEstimation.setLayoutManager(mLayoutManager);

        processBar = new ProgressBar(getActivity());
        processBar.showView();
        //new PreferenceApp(getActivity()).getUser().getId()
        new Connection<ArrayList<EstimateDTO>>().getEstimations(new GetEstimationsDTO("1"), new Connection.AnswerServerResponse<ArrayList<EstimateDTO>>() {
            @Override
            public void processFinish(Boolean isSuccess, ServerResponse<ArrayList<EstimateDTO>> answerServerResponse) {
                if (isSuccess) {
                    if (answerServerResponse.getDataTransferObject().isEmpty()) {
                        recyclerViewEstimation.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        recyclerViewEstimation.setVisibility(View.VISIBLE);
                        emptyView.setVisibility(View.GONE);
                    }
                    initUI(answerServerResponse.getDataTransferObject());
                } else {
                    Timber.e("Error!");
                }
                processBar.dismissView();
            }
        });


        // recyclerViewEvent = rootView.findViewById(R.id.);
        return rootView;
    }

    public void initUI(ArrayList<EstimateDTO> estimateDTOs) {

        adapterEstimation = new AdapterEstimation(getActivity(), estimateDTOs);
        recyclerViewEstimation.setAdapter(adapterEstimation);

        recyclerViewEstimation.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerViewEstimation, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                startActivity(new Intent(getActivity(), DetailEstimateActivity.class));
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
