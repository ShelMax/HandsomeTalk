package kr.sofac.handsometalk.view.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.adapter.AdapterEstimation;
import kr.sofac.handsometalk.adapter.AdapterScrollPhotos;
import kr.sofac.handsometalk.adapter.RecyclerItemClickListener;
import kr.sofac.handsometalk.dto.EstimateDTO;
import kr.sofac.handsometalk.dto.GetEstimationsDTO;
import kr.sofac.handsometalk.dto.NewEstimateRequestDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.util.PreferenceApp;
import kr.sofac.handsometalk.util.ProgressBar;
import kr.sofac.handsometalk.view.DetailEstimateActivity;
import kr.sofac.handsometalk.view.NewEstimateActivity;
import timber.log.Timber;

import static kr.sofac.handsometalk.Constants.ESTIMATION_ID;
import static kr.sofac.handsometalk.Constants.REQUEST_TAKE_PHOTO;

public class TalkFragment extends BaseFragment implements View.OnClickListener {

    public RecyclerView recyclerViewEstimation;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdapterEstimation adapterEstimation;
    private ProgressBar progressBar;
    private ConstraintLayout emptyView;
    private Button buttonSendMessage, buttonAddPhotoMessage;
    private EditText editTextMessage;
    private FloatingActionButton floatingActionButton;

    private ArrayList<Uri> listPhoto;
    private LinearLayout linearLayoutPhoto;
    private AdapterScrollPhotos adapterScrollPhotos;
    private RecyclerView recyclerViewScrollPhoto;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_talk, container, false);
        recyclerViewEstimation = rootView.findViewById(R.id.idRecyclerEstimation);

        emptyView = rootView.findViewById(R.id.recyclerTalkEmpty);
        editTextMessage = rootView.findViewById(R.id.idEditMessage);
        floatingActionButton = rootView.findViewById(R.id.fabNewEstimate);
        buttonSendMessage = rootView.findViewById(R.id.buttonSendNewEstimation);
        buttonAddPhotoMessage = rootView.findViewById(R.id.buttonAddPhotoNewEstimation);
        buttonSendMessage.setOnClickListener(this);
        buttonAddPhotoMessage.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);

        recyclerViewScrollPhoto = rootView.findViewById(R.id.idRecyclerScrollPhotos);
        linearLayoutPhoto = rootView.findViewById(R.id.idLayoutPhotos);
        mLayoutManager = new LinearLayoutManager(this.getActivity());

        progressBar = new ProgressBar(getActivity());
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
        recyclerViewScrollPhoto.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        newRequest();
        return rootView;
    }

    public void newRequest() {
        progressBar.showView();
        new Connection<ArrayList<EstimateDTO>>().getEstimations(
                new GetEstimationsDTO(
                        new PreferenceApp(getActivity()).getUser().getId()),
                (isSuccess, answerServerResponse) -> {
                    if (isSuccess) {
                        if (answerServerResponse.getDataTransferObject().isEmpty()) {
                            Timber.e("empty");
                            recyclerViewEstimation.setVisibility(View.GONE);
                            emptyView.setVisibility(View.VISIBLE);
                        } else {
                            recyclerViewEstimation.setVisibility(View.VISIBLE);
                            emptyView.setVisibility(View.GONE);
                            floatingActionButton.setVisibility(View.VISIBLE);
                            initUI(answerServerResponse.getDataTransferObject());
                        }
                    } else {
                        toastMessage();
                    }
                    progressBar.dismissView();
                });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.talk_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
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

    public void toastMessage() {
        Toast.makeText(getActivity(), R.string.connection_error, Toast.LENGTH_SHORT).show();
    }

    public void initUI(ArrayList<EstimateDTO> estimateDTOs) {
        if(estimateDTOs.isEmpty()){
            setHasOptionsMenu(false);
        } else {
            setHasOptionsMenu(true);
        }
        adapterEstimation = new AdapterEstimation(getActivity(), estimateDTOs);
        recyclerViewEstimation.setAdapter(adapterEstimation);
        recyclerViewEstimation.setHasFixedSize(true);
        recyclerViewEstimation.setLayoutManager(mLayoutManager);

        recyclerViewEstimation.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerViewEstimation, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailEstimateActivity.class);
                if (estimateDTOs != null) {
                    intent.putExtra(ESTIMATION_ID, estimateDTOs.get(position).getId());
                    startActivityForResult(intent, 1);
                }
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
                    Toast.makeText(getActivity(), R.string.field_empty, Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.showView();
                    new Connection<String>().newEstimation(
                            getActivity(),
                            new NewEstimateRequestDTO(
                                    new PreferenceApp(getActivity()).getUserID(),
                                    editTextMessage.getText().toString()),
                            listPhoto,
                            (isSuccess, answerServerResponse) -> {
                                if (isSuccess) {
                                    newRequest();
                                } else {
                                    toastMessage();
                                }
                                progressBar.dismissView();
                            }
                    );
                }
                break;
            case R.id.buttonAddPhotoNewEstimation:
                if (isStoragePermissionGranted(this.getActivity())) {
                    Intent takePhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    takePhotoIntent.setType("image/*");
                    startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO);
                    break;
                }
            case R.id.fabNewEstimate:
                startActivity(new Intent(getActivity(), NewEstimateActivity.class));
                break;
        }
    }


    static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    static String[] PERMISSIONS_CAMERA = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    Boolean IS_PERMISSIONS_STORAGE = false;
    Boolean IS_PERMISSIONS_CAMERA = false;

    public boolean isStoragePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                IS_PERMISSIONS_STORAGE = false;
                ActivityCompat.requestPermissions(this.getActivity(), PERMISSIONS_STORAGE, 1);
                return IS_PERMISSIONS_STORAGE;
            }
        } else { //permission is automatically granted on sdk < 23 upon installation
            return true;
        }
    }

    public boolean isCameraPermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                IS_PERMISSIONS_CAMERA = false;
                ActivityCompat.requestPermissions(this.getActivity(), PERMISSIONS_CAMERA, 2);
                return IS_PERMISSIONS_CAMERA;
            }
        } else { //permission is automatically granted on sdk < 23 upon installation
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    IS_PERMISSIONS_STORAGE = true;
                }
                break;
            case 2:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    IS_PERMISSIONS_CAMERA = true;
                }
                break;
        }

    }
}
