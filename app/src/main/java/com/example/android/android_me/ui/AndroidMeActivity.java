/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if (savedInstanceState == null){

            Bundle extras = getIntent().getExtras();

            if(extras!=null){
                headIndex = extras.getInt("headIndex");
                bodyIndex = extras.getInt("bodyIndex");
                legIndex = extras.getInt("legIndex");
            }

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

    }
}
