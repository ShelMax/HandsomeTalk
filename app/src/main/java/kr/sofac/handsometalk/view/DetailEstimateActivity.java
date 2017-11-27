package kr.sofac.handsometalk.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.adapter.AdapterMessages;
import kr.sofac.handsometalk.adapter.AdapterScrollPhotos;
import kr.sofac.handsometalk.adapter.RecyclerItemClickListener;
import kr.sofac.handsometalk.dto.EstimateDTO;
import kr.sofac.handsometalk.dto.MessageDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.util.PreferenceApp;

import static kr.sofac.handsometalk.Constants.ESTIMATION_ID;
import static kr.sofac.handsometalk.Constants.REQUEST_TAKE_PHOTO;

public class DetailEstimateActivity extends BaseActivity implements View.OnClickListener {

    Toolbar toolbar;

    public RecyclerView recyclerViewMessage;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdapterMessages adapterMessages;
    private ConstraintLayout emptyView;
    private ImageButton buttonSendMessage, buttonAddPhotoMessage;
    private EditText editTextMessage;

    private ArrayList<Uri> listPhoto;
    private AdapterScrollPhotos adapterScrollPhotos;
    private RecyclerView recyclerViewScrollPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_estimate);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Talk");

        listPhoto = new ArrayList<>();

        recyclerViewMessage = findViewById(R.id.idRecyclerMessage);
        recyclerViewScrollPhoto = findViewById(R.id.idRecyclerScrollPhotos);
        emptyView = findViewById(R.id.recyclerTalkEmpty);
        editTextMessage = findViewById(R.id.idEditMessage);

        buttonSendMessage = findViewById(R.id.buttonSend);
        buttonAddPhotoMessage = findViewById(R.id.imageButtonAttachPhoto);

        buttonSendMessage.setOnClickListener(this);
        buttonAddPhotoMessage.setOnClickListener(this);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewMessage.setHasFixedSize(true);
        recyclerViewMessage.setLayoutManager(mLayoutManager);

        adapterScrollPhotos = new AdapterScrollPhotos(listPhoto);
        adapterScrollPhotos.setItemClickListener((view, position) -> {
            switch (view.getId()) {
                case R.id.idButtonDeleting:
                    listPhoto.remove(position);
                    adapterScrollPhotos.notifyDataSetChanged();
                    if (listPhoto.isEmpty()) recyclerViewScrollPhoto.setVisibility(View.GONE);
                    break;
            }
        });
        recyclerViewScrollPhoto.setAdapter(adapterScrollPhotos);
        recyclerViewScrollPhoto.setHasFixedSize(false);
        recyclerViewScrollPhoto.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        newRequest();
    }

    public void newRequest() {
        progressBar.showView();
        new Connection<EstimateDTO>().getEstimation(
                new MessageDTO("", getIntent().getStringExtra(ESTIMATION_ID), new PreferenceApp(this).getUser().getId(), "", "", "", "", "", "", ""),
                (isSuccess, answerServerResponse) -> {
                    if (isSuccess) {
                        initUI(answerServerResponse.getDataTransferObject());
                    } else {
                        toastMessage(getString(R.string.connection_error));
                    }
                    progressBar.dismissView();
                });
    }

    public void initUI(EstimateDTO estimateDTO) {

        adapterMessages = new AdapterMessages(this, estimateDTO.getMessages());
        recyclerViewMessage.setAdapter(adapterMessages);

        recyclerViewMessage.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerViewMessage, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, final int position) {

            }
        }));
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
                    recyclerViewScrollPhoto.setVisibility(View.VISIBLE);
                }

            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.talk_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idRefresh:
                newRequest();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSend:
                if (editTextMessage.getText().toString().isEmpty()) {
                    Toast.makeText(this, R.string.field_empty, Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.showView();
                    new Connection<String>().newMessage(
                            this,
                            new MessageDTO("", getIntent().getStringExtra(ESTIMATION_ID), new PreferenceApp(this).getUser().getId(), "", editTextMessage.getText().toString(), "", "", "", "", ""),
                            listPhoto,
                            (isSuccess, answerServerResponse) -> {
                                if (isSuccess) {
                                    editTextMessage.setText("");
                                    listPhoto.clear();
                                    adapterScrollPhotos.notifyDataSetChanged();
                                    recyclerViewScrollPhoto.setVisibility(View.GONE);
                                    newRequest();
                                } else {
                                    toastMessage(getString(R.string.connection_error));
                                }
                                progressBar.dismissView();
                            }
                    );
                }
                break;
            case R.id.imageButtonAttachPhoto:
                if (isStoragePermissionGranted()) {
                    Intent takePhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    takePhotoIntent.setType("image/*");
                    startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO);
                    break;
                }
        }
    }

    public void toastMessage(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


}
