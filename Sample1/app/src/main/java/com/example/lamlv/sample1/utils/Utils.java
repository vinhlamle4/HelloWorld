package com.example.lamlv.sample1.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class Utils {

    // region ------ Fragment ------
    public static void addFragment(int layout, Fragment fragment,String tag, FragmentManager fragmentManagerAdd, boolean addToBackStack)
    {
        FragmentTransaction fragmentTransactionadd = fragmentManagerAdd.beginTransaction();

        if(addToBackStack) {
            fragmentTransactionadd.add(layout,fragment,tag);
            addToBackStack(fragment.getClass().getSimpleName(),fragmentTransactionadd);
            fragmentTransactionadd.commit();
        }
        else
        {
            fragmentTransactionadd.add(layout, fragment);
            fragmentTransactionadd.commit();
        }
    }

    public static void replaceFragment(int layout, Fragment fragment, FragmentManager fragmentManagerReplace, boolean addToBackStack)
    {
        FragmentTransaction fragmentTransactionReplace = fragmentManagerReplace.beginTransaction();

        if(addToBackStack) {
            fragmentTransactionReplace.replace(layout,fragment);
            addToBackStack(fragment.getClass().getSimpleName(), fragmentTransactionReplace);
            fragmentTransactionReplace.commit();
        }
        else
        {
            fragmentTransactionReplace.replace(layout, fragment);
            fragmentTransactionReplace.commit();
        }
    }

    public static void removeFragment(Fragment fragment, FragmentManager fragmentManagerRemove)
    {
        FragmentTransaction fragmentTransactionadd = fragmentManagerRemove.beginTransaction();

        fragmentTransactionadd.remove(fragment);
        fragmentTransactionadd.commit();
    }

    public static void addToBackStack(String s, FragmentTransaction fragmentTransactionAddToBackStack)
    {
        fragmentTransactionAddToBackStack.addToBackStack(s);
    }
// endregion ------ Fragment ------
}
