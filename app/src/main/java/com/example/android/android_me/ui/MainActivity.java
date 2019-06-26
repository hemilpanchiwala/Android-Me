package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex = 0;
    private int bodyIndex = 0;
    private int legIndex = 0;

    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.android_me_linear_layout) != null){
            twoPane = true;

            Button nextBtn = (Button) findViewById(R.id.nextBtn);
            nextBtn.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.grid_view_images);
            gridView.setNumColumns(2);


            if (savedInstanceState == null){
                //Head Fragment
                BodyPartFragment headFragment = new BodyPartFragment();

                headFragment.setImageIds(AndroidImageAssets.getHeads());
                headFragment.setIndex(headIndex);

                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                //Body Fragment
                BodyFragment bodyFragment = new BodyFragment();

                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                bodyFragment.setIndex(bodyIndex);

                FragmentManager fragmentManager1 = getSupportFragmentManager();

                fragmentManager1.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                //Leg Fragment
                LegFragment legFragment = new LegFragment();

                legFragment.setImageIds(AndroidImageAssets.getLegs());
                legFragment.setIndex(legIndex);

                FragmentManager fragmentManager2 = getSupportFragmentManager();

                fragmentManager2.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }

        }else {
            twoPane = false;
        }



    }

    @Override
    public void onImageSelected(int position) {

        int bodyPartNumber = position/12;

        int index = position - 12*bodyPartNumber;

        if (twoPane){

            BodyPartFragment fragment1 = new BodyPartFragment();
            BodyFragment fragment2 = new BodyFragment();
            LegFragment fragment3 = new LegFragment();

            switch (bodyPartNumber){
                case 0:
                    fragment1.setImageIds(AndroidImageAssets.getHeads());
                    fragment1.setIndex(index);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, fragment1)
                            .commit();

                case 1:
                    fragment2.setImageIds(AndroidImageAssets.getBodies());
                    fragment2.setIndex(index);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, fragment2)
                            .commit();

                case 2:
                    fragment3.setImageIds(AndroidImageAssets.getLegs());
                    fragment3.setIndex(index);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, fragment3)
                            .commit();

            }

        }else {
            switch (bodyPartNumber){
                case 0:
                    headIndex = index;
                    break;
                case 1:
                    bodyIndex = index;
                    break;
                case 2:
                    legIndex = index;
                    break;
                default: break;
            }

            Bundle bundle = new Bundle();
            bundle.putInt("headIndex", headIndex);
            bundle.putInt("bodyIndex", bodyIndex);
            bundle.putInt("legIndex", legIndex);

            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(bundle);

            Button nextBtn = (Button) findViewById(R.id.nextBtn);
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }
    }

}
