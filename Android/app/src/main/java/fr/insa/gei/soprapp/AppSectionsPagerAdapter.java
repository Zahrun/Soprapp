package fr.insa.gei.soprapp;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AppSectionsPagerAdapter extends FragmentPagerAdapter {

    public AppSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                // 1ère section : recherche d'une salle.
                return new SearchingFragment();

            default:
                // 2ème section : consulter/modifier une réservation déjà existante.
                return new ManageReservationFragment();
        }
    }

    @Override
    /**
     * Renvoie le nombre de fragments de l'app
     */
    public int getCount() {
        return 2;
    }

    @Override
    /**
     * Renvoie le titre du fragment en fonction de l'ordre defini
     */
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Recherche";
            default:
                return "Vos réservations";
        }

    }


}
