package com.demo.test;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.demo.test.fragment.IndexFragment;
import com.demo.test.fragment.NewMsgFragment;
import com.demo.test.fragment.ProfileFragment;
import com.demo.test.view.NestedRadioGroup;

/**
 * Created by Administrator on 2018/6/4.
 */

public class MainActivity extends AppCompatActivity
{
    private Fragment newMsgFragment;
    private Fragment indexFragment;
    private Fragment profileFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        newMsgFragment=new NewMsgFragment();
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.add(R.id.container, newMsgFragment, "newMsgFragment");
        transaction.addToBackStack("newMsgFragment");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();

        NestedRadioGroup tabBarRg=(NestedRadioGroup) findViewById(R.id.rg_main_tabbar);
        tabBarRg.setOnCheckedChangeListener(new NestedRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(NestedRadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_new_msg:
                        if (newMsgFragment == null) {
                            newMsgFragment = new NewMsgFragment();
                        }
                        switchFragment(newMsgFragment, "newMsgFragment");
                        break;
                    case R.id.rb_index:
                        if (indexFragment == null) {
                            indexFragment = new IndexFragment();
                        }
                        switchFragment(indexFragment, "indexFragment");
                        break;
                    case R.id.rb_profile:
                        if (profileFragment == null) {
                            profileFragment = new ProfileFragment();
                        }
                        switchFragment(profileFragment, "profileFragment");
                        break;
                    default:
                        break;
                }
            }
        });
    }
    public void switchFragment(Fragment fragment,String tag){
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
}
