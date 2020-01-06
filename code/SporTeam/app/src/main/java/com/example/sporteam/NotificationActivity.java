package com.example.sporteam;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotificationActivity extends AppCompatActivity {

    Switch hydrationSwitch;
    Switch weatherSwitch;
    Switch nutritionSwitch;
    public static final String CHANNEL_ID = "com.example.sporteam.ANDROID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        hydrationSwitch = findViewById(R.id.chip_hidratare);
        weatherSwitch = findViewById(R.id.chip_vreme);
        nutritionSwitch = findViewById(R.id.chip_alimentatie);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        break;
                    case R.id.navigation_event:
                        startActivity(new Intent(NotificationActivity.this, ViewEventsActivity.class));
                        break;
                    case R.id.navigation_location:
                        startActivity(new Intent(NotificationActivity.this, LocationBookingActivity.class));
                        break;
                    case R.id.navigation_chat:
                        startActivity(new Intent(NotificationActivity.this, ConversationsActivity.class));
                        break;
                    case R.id.navigation_profile:
                        startActivity(new Intent(NotificationActivity.this, MyAccountActivity.class));
                        break;
                }
                return false;
            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private NotificationManager notifManager;

    public void createNotification(String aMessage, Context context, Class nextClass) {

        final int NOTIFY_ID = 0; // ID of notification
        String id = "default_channel_id"; // default_channel_id
        String title = "title"; // Default Channel
        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;
        if (notifManager == null) {
            notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, title, importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(context, id);
            intent = new Intent(context, nextClass);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentTitle(context.getString(R.string.app_name))                            // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)   // required
                    .setContentText(aMessage) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        } else {
            builder = new NotificationCompat.Builder(context, id);
            intent = new Intent(context, nextClass);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentTitle(context.getString(R.string.app_name))                            // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)   // required
                    .setContentText(aMessage) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        }
        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }

    public void startNotificationHydration(View view) {
        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                createNotification("Nu uita sa te hidratezi", NotificationActivity.this, NotificationActivity.class);
            }
        }.start();

    }

    public void startNotificationWeather(View view) {
        new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                createNotification("Vremea e minunata pentru sport. Alege ce vrei sa faci maine.", NotificationActivity.this, ViewEventsActivity.class);
            }
        }.start();
    }

    public void startNotificationNutrition(View view) {
        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                createNotification("Consulta planul tau personalizat de  nutritie", NotificationActivity.this, NotificationActivity.class);
            }
        }.start();

    }

}
