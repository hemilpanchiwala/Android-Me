package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

public class MasterListFragment extends Fragment {

    OnImageClickListener mCallback;

    public interface OnImageClickListener{
        void onImageSelected(int position);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try {
            mCallback = (OnImageClickListener) context;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    public MasterListFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        MasterListAdapter masterListAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view_images);

        gridView.setAdapter(masterListAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCallback.onImageSelected(i);
            }
        });

        return rootView;

    }

}
