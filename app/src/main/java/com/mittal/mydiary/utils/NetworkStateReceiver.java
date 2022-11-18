package com.mittal.mydiary.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;

public class NetworkStateReceiver  extends BroadcastReceiver{

    protected NetworkStateReceiverListener listener;
    protected Boolean connected;
    private Context context;

    public NetworkStateReceiver(Context context, NetworkStateReceiverListener l) {
        this.context = context;
        context.registerReceiver(this, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
        listener = l;
        connected = null;
    }

    public void onReceive(Context context, Intent intent) {
        if (isInitialStickyBroadcast())
            return;
        if (intent == null || intent.getExtras() == null)
            return;

        if (((NetworkInfo) intent.getExtras().get("networkInfo")).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        } else if (((NetworkInfo) intent.getExtras().get("networkInfo")).getState() == NetworkInfo.State.DISCONNECTED) {
            connected = false;
        }

        notifyState();
    }

    private void notifyState() {
        if (connected == null || listener == null)
            return;

        if (connected)
            listener.networkAvailable();
        else
            listener.networkUnavailable();
    }

    public void unregistered() {
        listener = null;
        context.unregisterReceiver(this);
    }

    public interface NetworkStateReceiverListener {
        public void networkAvailable();

        public void networkUnavailable();
    }

}
