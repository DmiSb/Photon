package ru.dmisb.photon.screens.selector;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mortar.MortarScope;
import ru.dmisb.photon.core.BaseScreen;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.screens.selector.filter.FilterScreen;
import ru.dmisb.photon.screens.selector.search.SearchScreen;

public class SelectorAdapter extends PagerAdapter {

    List<String> titleList = new ArrayList<>();

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseScreen screen = null;
        Context context = container.getContext();
        switch (position) {
            case 0:
                screen = new SearchScreen();
                break;
            case 1:
                screen = new FilterScreen();
                break;
        }

        if (screen != null) {
            MortarScope screenScope = ScreenScoper.getScreenScope(screen);
            Context screenContext = screenScope.createContext(context);
            View view = LayoutInflater.from(screenContext).inflate(screen.getLayoutResId(), container, false);
            container.addView(view);
            return view;
        } else
            return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    public void addPageTitle(String title) {
        titleList.add(title);
    }
}
