package kr.sofac.handsometalk.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.dto.FeedbackDTO;
import kr.sofac.handsometalk.dto.GetEstimationsDTO;
import kr.sofac.handsometalk.dto.MessageDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.util.PreferenceApp;
import kr.sofac.handsometalk.util.ProgressBar;
import timber.log.Timber;


public class CalendarFragment extends BaseFragment {

    Dialog dialog;
    AlertDialog.Builder builder;
    Date date;

    private MaterialCalendarView materialCalendarView;
    private SimpleAdapter simpleAdapter;
    private ListView listViewTime;
    private ProgressBar progressBar;
    final String[] texts = {"08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 AM", "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM", "08:00 PM"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        materialCalendarView = view.findViewById(R.id.calendarView);
        listViewTime = view.findViewById(R.id.listViewTime);
        progressBar = new ProgressBar(getActivity());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(calendar))
                .setMaximumDate(CalendarDay.from(2018+1, 1, 1))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this.getActivity(), R.layout.item_calendar, texts);

        listViewTime.setAdapter(arrayAdapter);
        listViewTime.setOnItemClickListener((adapterView, view1, i, l) -> {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            date = new Date();
            if (materialCalendarView.getSelectedDate()!= null) {
                date.setTime(materialCalendarView.getSelectedDate().getDate().getTime());
            } else {
                date.setTime(date.getTime());
            }
            date.setHours(i + 8);
            date.setMinutes(0);
            date.setSeconds(0);
            builder = new AlertDialog.Builder(getActivity());
            builder.setPositiveButton("SEND", (dialogInterface, i12) -> newRequestAdd(date));
            builder.setNegativeButton("CANCEL", (dialogInterface, i1) -> {
            });
            builder.setTitle(getString(R.string.you_choice_this_date) + simpleDateFormat.format(date));
            builder.setMessage(R.string.do_you_want_make);
            builder.show();

        });

        newRequestGetList();
        return view;
    }

    public void newRequestAdd(Date date) {
        progressBar.showView();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        new Connection<ArrayList<MessageDTO>>().addFeedback(
                new MessageDTO("", "", new PreferenceApp(getActivity()).getUser().getId(), "", "", simpleDateFormat.format(date), "", "", "", ""),
                (isSuccess, answerServerResponse) -> {
                    if (isSuccess) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        LayoutInflater inflater = getActivity().getLayoutInflater();
                        View view = inflater.inflate(R.layout.dialog_custom, null, false);
                        builder.setView(view);
                        dialog = builder.create();
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                        new Handler().postDelayed(() -> dialog.dismiss(), 3000);

                    } else {
                        Toast.makeText(getActivity(), R.string.some_error, Toast.LENGTH_SHORT).show();
                    }

                    progressBar.dismissView();
                });
    }

    public void newRequestGetList() {

        progressBar.showView();
        new Connection<ArrayList<FeedbackDTO>>().feedbackList(new GetEstimationsDTO(new PreferenceApp(getActivity()).getUser().getId()),
                (isSuccess, answerServerResponse) -> {
                    if (isSuccess) {
                        for (FeedbackDTO feedbackDTO : answerServerResponse.getDataTransferObject()) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                            String strDate = feedbackDTO.getDate();
                            Date date = new Date();
                            try {
                                date = format.parse(strDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Timber.e("\n%s", date.toString());
                            materialCalendarView.selectRange(CalendarDay.from(date), CalendarDay.from(date));
                        }

                    } else {
                        //Toast.makeText(getActivity(), "Some error!", Toast.LENGTH_SHORT).show();
                    }
                    progressBar.dismissView();
                });
    }

}
