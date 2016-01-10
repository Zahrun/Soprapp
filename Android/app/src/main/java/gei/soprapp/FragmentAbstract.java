package gei.soprapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Clément Baudouin on 09/01/2016.
 */

    public abstract class FragmentAbstract extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_FRAGMENT_LAYOUT = "fragment_layout_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static FragmentAbstract newInstance(FragmentAbstract fragment, int sectionNumber, int fragmentLayout) {
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt(ARG_FRAGMENT_LAYOUT, fragmentLayout);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(getArguments().getInt(ARG_FRAGMENT_LAYOUT), container, false);
            return rootView;
        }
    }
