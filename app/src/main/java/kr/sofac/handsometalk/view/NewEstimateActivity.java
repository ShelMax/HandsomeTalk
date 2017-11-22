package kr.sofac.handsometalk.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.adapter.AdapterScrollPhotos;
import kr.sofac.handsometalk.dto.NewEstimateRequestDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.util.PreferenceApp;
import kr.sofac.handsometalk.util.ProgressBar;

import static kr.sofac.handsometalk.Constants.REQUEST_TAKE_PHOTO;

public class NewEstimateActivity extends BaseActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private ConstraintLayout emptyView;
    private Button buttonSendMessage, buttonAddPhotoMessage;
    private EditText editTextMessage;
    private Toolbar toolbar;

    private ArrayList<Uri> listPhoto;
    private LinearLayout linearLayoutPhoto;
    private AdapterScrollPhotos adapterScrollPhotos;
    private RecyclerView recyclerViewScrollPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_estimate);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Create new estimate");

        emptyView = findViewById(R.id.recyclerTalkEmpty);
        editTextMessage = findViewById(R.id.idEditMessage);

        buttonSendMessage = findViewById(R.id.buttonSendNewEstimation);
        buttonAddPhotoMessage = findViewById(R.id.buttonAddPhotoNewEstimation);
        buttonSendMessage.setOnClickListener(this);
        buttonAddPhotoMessage.setOnClickListener(this);

        recyclerViewScrollPhoto = findViewById(R.id.idRecyclerScrollPhotos);
        linearLayoutPhoto = findViewById(R.id.idLayoutPhotos);


        progressBar = new ProgressBar(this);
        listPhoto = new ArrayList<>();

        adapterScrollPhotos = new AdapterScrollPhotos(listPhoto);
        adapterScrollPhotos.setItemClickListener((view, position) -> {
            switch (view.getId()) {
                case R.id.idButtonDeleting:
                    listPhoto.remove(position);
                    adapterScrollPhotos.notifyDataSetChanged();
                    if (listPhoto.isEmpty()) linearLayoutPhoto.setVisibility(View.GONE);
                    break;
            }
        });

        recyclerViewScrollPhoto.setAdapter(adapterScrollPhotos);
        recyclerViewScrollPhoto.setHasFixedSize(true);
        recyclerViewScrollPhoto.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    public void toastMessage() {
        Toast.makeText(this, R.string.connection_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (data != null) {

                final Uri fileUri = data.getData();

                if (requestCode == REQUEST_TAKE_PHOTO) {

                    for (Uri urlPhoto : listPhoto) {
                        if (fileUri.equals(urlPhoto)) return;
                    }

                    listPhoto.add(fileUri);
                    adapterScrollPhotos.notifyDataSetChanged();
                    linearLayoutPhoto.setVisibility(View.VISIBLE);
                }

            }

        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSendNewEstimation:
                if (editTextMessage.getText().toString().isEmpty()) {
                    Toast.makeText(this, R.string.field_empty, Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.showView();
                    new Connection<String>().newEstimation(
                            this,
                            new NewEstimateRequestDTO(
                                    new PreferenceApp(this).getUser().getId(),
                                    editTextMessage.getText().toString()),
                            listPhoto,
                            (isSuccess, answerServerResponse) -> {
                                if (isSuccess) {
                                    finish();
                                } else {
                                    toastMessage();
                                }
                                progressBar.dismissView();
                            }
                    );
                }
                break;
            case R.id.buttonAddPhotoNewEstimation:
                if (isStoragePermissionGranted()) {
                    Intent takePhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    takePhotoIntent.setType("image/*");
                    startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO);
                    break;
                }
        }
    }

}
