package gei.soprapp;

import android.util.Log;
import android.view.View;

/**
 * Created by Clément Baudouin on 12/01/2016.
 */
public class ThreadKeepUpdated extends Thread {

    private View view;

    public ThreadKeepUpdated(View view){
        this.view=view;
    }

    @Override
    public void run() {
        synchronized (this) {
            Log.d("ThreadKeepUpdated", "started");
            while (!this.isInterrupted()) {
                Requests.getSites(view);
                if (this.isInterrupted()) break;
                Requests.getEquipments(view);
                if (this.isInterrupted()) break;
                Requests.getRooms(view);
                if (this.isInterrupted()) break;
                Requests.getReservationsCurrentUser(view);
                if (this.isInterrupted()) break;
                try {
                    this.wait(Globals.INTERVALLE_MISE_A_JOUR);
                } catch (InterruptedException e) {
                    this.interrupt();
                    break;
                }
            }
            Log.d("ThreadKeepUpdated", "stopped");
        }
    }
}
