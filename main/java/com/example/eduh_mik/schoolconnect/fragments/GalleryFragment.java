package com.example.eduh_mik.schoolconnect.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eduh_mik.schoolconnect.R;
import com.example.eduh_mik.schoolconnect.adapters.GalleryAdapter;
import com.example.eduh_mik.schoolconnect.interfaces.OnFragmentInteractionListener;
import com.example.eduh_mik.schoolconnect.models.Gallery;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eduh_mik on 4/21/2018.
 */

public class GalleryFragment extends Fragment {
    private static final int ACTION_REQUEST_GALLERY = 1;
    private static final int ACTION_REQUEST_CAMERA = 2;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //Unbinder unbinder;
    private OnFragmentInteractionListener mListener;
    private GalleryAdapter galleryAdapter;
    private List<Gallery> galleryList = new ArrayList<>();
   Uri initialURI;

    public GalleryFragment() {
        // Required empty public constructor
    }

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        FloatingActionButton myFab = (FloatingActionButton) view.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose Image Source");
                builder.setItems(new CharSequence[] {"Gallery", "Camera"},
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:

                                        // GET IMAGE FROM THE GALLERY
                                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                        intent.setType("image/*");

                                        Intent chooser = Intent.createChooser(intent, "Choose a Picture");
                                        startActivityForResult(chooser, ACTION_REQUEST_GALLERY);

                                        break;

                                    case 1:
                                        Intent getCameraImage = new Intent("android.media.action.IMAGE_CAPTURE");

                                        File cameraFolder;

                                        if (android.os.Environment.getExternalStorageState().equals
                                                (android.os.Environment.MEDIA_MOUNTED))
                                            cameraFolder = new File(android.os.Environment.getExternalStorageDirectory(),
                                                    "some_directory_to_save_images/");
                                        else
                                            cameraFolder=getActivity().getCacheDir();
                                        if(!cameraFolder.exists())
                                            cameraFolder.mkdirs();

                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
                                        String timeStamp = dateFormat.format(new Date());
                                        String imageFileName = "picture_" + timeStamp + ".jpg";

                                        File photo = new File(Environment.getExternalStorageDirectory(),
                                                "some_directory_to_save_images/" + imageFileName);
                                        getCameraImage.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
                                        initialURI = Uri.fromFile(photo);

                                        startActivityForResult(getCameraImage, ACTION_REQUEST_CAMERA);

                                        break;

                                    default:
                                        break;
                                }
                            }
                        });

                builder.show();
            }

        });
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        prepareGalleryData(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(getLayoutManager(getActivity()));
        galleryAdapter = new GalleryAdapter(getActivity(), galleryList);
        recyclerView.setAdapter(galleryAdapter);
        prepareGalleryData(getContext());
    }

    private void prepareGalleryData(Context context) {
        Gallery gallery = new Gallery("Prize giving", ContextCompat.getDrawable(context, R.drawable.image1));
        galleryList.add(gallery);
        gallery = new Gallery("Prize giving", ContextCompat.getDrawable(context, R.drawable.image1));
        galleryList.add(gallery);
        gallery = new Gallery("Prize giving", ContextCompat.getDrawable(context, R.drawable.image1));
        galleryList.add(gallery);
        gallery = new Gallery("Prize giving", ContextCompat.getDrawable(context, R.drawable.image1));
        galleryList.add(gallery);
        gallery = new Gallery("Prize giving", ContextCompat.getDrawable(context, R.drawable.image1));
        galleryList.add(gallery);
        gallery = new Gallery("Prize giving", ContextCompat.getDrawable(context, R.drawable.image1));
        galleryList.add(gallery);
        gallery = new Gallery("Prize giving", ContextCompat.getDrawable(context, R.drawable.image1));
        galleryList.add(gallery);
        gallery = new Gallery("Prize giving", ContextCompat.getDrawable(context, R.drawable.image1));
        galleryList.add(gallery);


        galleryAdapter.notifyDataSetChanged();
    }

    public static GridLayoutManager getLayoutManager(Context context) {
        GridLayoutManager l;
        if (checkIfPhone(context)) {
            if (getScreenOrientation(context) == 0) {
                l = new GridLayoutManager(context, 3);
                return l;
            }
            if (getScreenOrientation(context) == 1) {
                l = new GridLayoutManager(context, 3);
                return l;
            }
            if (getScreenOrientation(context) == 2) {
                l = new GridLayoutManager(context, 5);
                return l;
            }
        } else {
            if (getScreenOrientation(context) == 0) {
                l = new GridLayoutManager(context, 4);
                return l;
            }
            if (getScreenOrientation(context) == 1) {
                l = new GridLayoutManager(context, 4);
                return l;
            }
            if (getScreenOrientation(context) == 2) {
                l = new GridLayoutManager(context, 5);
                return l;
            }
        }
        return new GridLayoutManager(context, 1);

    }

    public static int getScreenOrientation(Context context) {
        Display getOrient = ((Activity) context).getWindowManager().getDefaultDisplay();
        int orientation = Configuration.ORIENTATION_UNDEFINED;
        if (getOrient.getWidth() == getOrient.getHeight()) {
            orientation = Configuration.ORIENTATION_SQUARE; //0 for square orientation
        } else {
            if (getOrient.getWidth() < getOrient.getHeight()) {
                orientation = Configuration.ORIENTATION_PORTRAIT; //1 for potrait
            } else {
                orientation = Configuration.ORIENTATION_LANDSCAPE;  //2 for landscape
            }
        }
        return orientation;
    }

    public static Boolean checkIfPhone(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float yInches = metrics.heightPixels / metrics.ydpi;
        float xInches = metrics.widthPixels / metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
        if (diagonalInches > 6.5)  //6.5 inch device or bigger
        {
            return false;
        } else    //standard device
        {
            return true;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
