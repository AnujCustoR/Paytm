package du.fakeness.paytm;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int[] smokeImages = {R.drawable.payment_done_00000, R.drawable.payment_done_00001, R.drawable.payment_done_00002, R.drawable.payment_done_00003, R.drawable.payment_done_00004, R.drawable.payment_done_00005, R.drawable.payment_done_00006, R.drawable.payment_done_00007, R.drawable.payment_done_00008, R.drawable.payment_done_00009, R.drawable.payment_done_00010, R.drawable.payment_done_00011, R.drawable.payment_done_00012, R.drawable.payment_done_00013, R.drawable.payment_done_00014, R.drawable.payment_done_00015, R.drawable.payment_done_00016, R.drawable.payment_done_00017, R.drawable.payment_done_00018, R.drawable.payment_done_00019, R.drawable.payment_done_00020, R.drawable.payment_done_00021, R.drawable.payment_done_00022, R.drawable.payment_done_00023, R.drawable.payment_done_00024, R.drawable.payment_done_00025, R.drawable.payment_done_00026, R.drawable.payment_done_00027, R.drawable.payment_done_00028, R.drawable.payment_done_00029, R.drawable.payment_done_00030, R.drawable.payment_done_00031, R.drawable.payment_done_00032, R.drawable.payment_done_00033, R.drawable.payment_done_00034, R.drawable.payment_done_00035, R.drawable.payment_done_00036, R.drawable.payment_done_00037, R.drawable.payment_done_00038, R.drawable.payment_done_00039, R.drawable.payment_done_00040, R.drawable.payment_done_00041, R.drawable.payment_done_00042, R.drawable.payment_done_00043, R.drawable.payment_done_00044, R.drawable.payment_done_00045, R.drawable.payment_done_00046, R.drawable.payment_done_00047, R.drawable.payment_done_00048, R.drawable.payment_done_00049, R.drawable.payment_done_00050, R.drawable.payment_done_00051, R.drawable.payment_done_00052, R.drawable.payment_done_00053, R.drawable.payment_done_00054, R.drawable.payment_done_00055, R.drawable.payment_done_00056, R.drawable.payment_done_00057, R.drawable.payment_done_00058, R.drawable.payment_done_00059, R.drawable.payment_done_00060
                        };

    PrefManager prefManager;
    Button proceed, totalZero;
    TextView totalExpense;
    private String android_id;
    List device_id = Arrays.asList("bbbc6b27137ecdb2");
    //BBBC6B27137ECDB2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        getSupportActionBar().setElevation(2);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(android.R.color.white));
        //ImageView title=(ImageView) findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        //title.setImageDrawable(R.drawable.app_icon);
        prefManager=new PrefManager(this);

        totalExpense = (TextView) findViewById(R.id.tv_expense);
        totalZero = (Button) findViewById(R.id.bt_zero);
        totalExpense.setText("Total Expenses till now:\n" + "\t\t\t\t\t\t\t\t\t\t\t\t₹ "+prefManager.getTotal());

        totalZero.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                prefManager.setTotalZero();
                totalExpense.setText("Total Expenses till now:\n\n" + "\t\t\t\t\t\t\t\t\t\t\t₹ "+prefManager.getTotal());
                return true;
            }
        });

        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        if(!device_id.contains(android_id)){
            finish();
        }

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Uber Hospitality Ventures pvt ltd");
        spinnerArray.add("Happiness Forever lifestyle chemist");
        spinnerArray.add("Skydream Hospitality");
        spinnerArray.add("IDDOS Idli and Dosas");
        spinnerArray.add("VENKATESWARA PETROLEUM");
        spinnerArray.add("Other Merchant...");
        spinnerArray.add("Payment to any Number...");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);
        int i=0;
        AnimationDrawable anim = new AnimationDrawable();
        while(i < smokeImages.length) {
            anim.addFrame(
                    getResources().getDrawable(smokeImages[i]),
                    45);
            i++;
        }
        ImageView paymentimg= (ImageView) findViewById(R.id.imageView);
        paymentimg.setImageDrawable(anim);
        anim.setOneShot(false);
        anim.start();
        proceed=(Button) findViewById(R.id.bt_proceed_merchant);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sItems.getSelectedItem().toString().equals("Payment to any Number...")){
                    prefManager.setMerchant(sItems.getSelectedItem().toString());
                    prefManager.setMerNo(true);
                    prefManager.setOtherMerchant(false);
                }else if(sItems.getSelectedItem().toString().equals("Other Merchant...")){
                    prefManager.setOtherMerchant(true);
                    prefManager.setMerNo(true);
                }else{
                    prefManager.setMerchant(sItems.getSelectedItem().toString());
                    prefManager.setMerNo(false);
                    prefManager.setOtherMerchant(false);
                    if(sItems.getSelectedItem().toString().equals("VENKATESWARA PETROLEUM")){
                        prefManager.setName("Get 0.75% cashback on the successful payment amount");
                    }else{
                        prefManager.setName("") ;
                    }
                }
                startActivity(new Intent(MainActivity.this, du.fakeness.paytm.ScanCode.class));
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        totalExpense.setText("Total Expenses till now:\n\n" + "\t\t\t\t\t\t\t\t\t\t\t₹ "+prefManager.getTotal());

    }
    /*private void startTimer() {

        while (timing) {
            for (int i = 0; i < 21; i++) {
                try {
                    Thread.sleep(250);
                    smokemageView.setImageResource(smokeImages[i]);
                } catch (InterruptedException localInterruptedException) {
                }
            }
        }
        smokemageView.setImageResource(R.drawable.smoke_22);
    }*/
}
