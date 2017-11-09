package kr.sofac.handsometalk.view.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.adapter.EventAdapter;
import kr.sofac.handsometalk.adapter.RecyclerItemClickListener;
import kr.sofac.handsometalk.dto.EventDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.server.type.ServerResponse;
import timber.log.Timber;

public class EventFragment extends BaseFragment {

    public RecyclerView recyclerViewEvent;
    private RecyclerView.LayoutManager mLayoutManager;
    private EventAdapter adapterEvent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);

        recyclerViewEvent = (RecyclerView) rootView.findViewById(R.id.idRecyclerEvent);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerViewEvent.setHasFixedSize(true);
        recyclerViewEvent.setLayoutManager(mLayoutManager);

        new Connection<ArrayList<EventDTO>>().allEvents("", new Connection.AnswerServerResponse<ArrayList<EventDTO>>() {
            @Override
            public void processFinish(Boolean isSuccess, ServerResponse<ArrayList<EventDTO>> answerServerResponse) {
                if(isSuccess){
                    initUI(answerServerResponse.getDataTransferObject());
                }else{
                    Timber.e("Error!");
                }
            }
        });

        // recyclerViewEvent = rootView.findViewById(R.id.);
        return rootView;
    }

    public void initUI(ArrayList<EventDTO> eventDTOs){

        adapterEvent = new EventAdapter(getActivity(), eventDTOs);
        recyclerViewEvent.setAdapter(adapterEvent);

        recyclerViewEvent.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerViewEvent, new RecyclerItemClickListener.OnItemClickListener() {
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
