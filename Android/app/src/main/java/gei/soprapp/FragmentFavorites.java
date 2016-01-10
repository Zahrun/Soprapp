package gei.soprapp;

import android.os.Bundle;
import android.view.View;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class FragmentFavorites extends FragmentAbstract {
    public static FragmentAbstract newInstance(int sectionNumber) {
        FragmentFavorites fragment = new FragmentFavorites();
        return FragmentAbstract.newInstance(fragment, sectionNumber, R.layout.fragment_favorites);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
