package kr.sofac.handsometalk.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.dto.EstimateDTO;
import kr.sofac.handsometalk.dto.GetEstimationsDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.util.PreferenceApp;
import kr.sofac.handsometalk.util.ProgressBar;


public class CalendarFragment extends BaseFragment {

    private CalendarView calendarView;
    private SimpleAdapter simpleAdapter;
    private ListView listViewTime;
    private ProgressBar progressBar;
    String[] texts = { "sometext 1", "sometext 2", "sometext 3", "sometext 4", "sometext 5" };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        listViewTime = view.findViewById(R.id.listViewTime);
        progressBar = new ProgressBar(getActivity());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 23);//not sure this is needed
        long endOfMonth = calendar.getTimeInMillis();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        long startOfMonth = calendar.getTimeInMillis();
        //calendarView.setMaxDate(endOfMonth);
        calendarView.setMinDate(startOfMonth);

        ArrayAdapter<String[]> arrayAdapter = new ArrayAdapter<String[]>(this, R.layout.item_calendar, texts);


        calendarView.setOnDateChangeListener((calendarView, i, i1, i2) -> {

            Toast.makeText(this.getActivity(), "i= " + i + " i1= " + i1 + " i2= " + i2, Toast.LENGTH_SHORT).show();

        });

        return view;
    }

    public void newRequest(Date date) {
        progressBar.showView();
        new Connection<ArrayList<EstimateDTO>>().getEstimations(
                new GetEstimationsDTO( new PreferenceApp(getActivity()).getUser().getId()),
                (isSuccess, answerServerResponse) -> {

                    progressBar.dismissView();
                });
    }


//    public void initUI(ArrayList<EstimateDTO> estimateDTOs) {
//
//        adapterEstimation = new AdapterEstimation(getActivity(), estimateDTOs);
//        recyclerViewEstimation.setAdapter(adapterEstimation);
//        recyclerViewEstimation.setHasFixedSize(true);
//        recyclerViewEstimation.setLayoutManager(mLayoutManager);
//
//        recyclerViewEstimation.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerViewEstimation, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(getActivity(), DetailEstimateActivity.class);
//                if (estimateDTOs != null) {
//                    intent.putExtra(ESTIMATION_ID, estimateDTOs.get(position).getId());
//                    startActivityForResult(intent, 1);
//                }
//            }
//
//            @Override
//            public void onLongItemClick(View view, final int position) {
////                postDTO = postDTOs.get(position);
////                GroupFragment.idPost = postDTOs.get(position).getId();
////
////                if (postDTO.getUser_id().equals(userDTO.getId()) || userDTO.isAdmin()) {
////
////                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
////                    builder.setItems(R.array.choice_double_click_post, (dialog, which) -> {
////                        switch (which) {
////                            case 0: // Edit
////                                changePost(GroupFragment.idPost);
////                                break;
////                            case 1: // Delete
////                                Timber.e("Click delete");
////                                deletePost();
////                                break;
////                        }
////                    });
////                    builder.show();
////                }
//            }
//        }));
//    }

}
