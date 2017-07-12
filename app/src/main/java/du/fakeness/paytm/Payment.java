package du.fakeness.paytm;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.*;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Created by admin on 19-03-2017.
 */

public class Payment extends AppCompatActivity {

    int[] smokeImages = {R.drawable.payment_done_00000, R.drawable.payment_done_00001, R.drawable.payment_done_00002, R.drawable.payment_done_00003, R.drawable.payment_done_00004, R.drawable.payment_done_00005, R.drawable.payment_done_00006, R.drawable.payment_done_00007, R.drawable.payment_done_00008, R.drawable.payment_done_00009, R.drawable.payment_done_00010, R.drawable.payment_done_00011, R.drawable.payment_done_00012, R.drawable.payment_done_00013, R.drawable.payment_done_00014, R.drawable.payment_done_00015, R.drawable.payment_done_00016, R.drawable.payment_done_00017, R.drawable.payment_done_00018, R.drawable.payment_done_00019, R.drawable.payment_done_00020, R.drawable.payment_done_00021, R.drawable.payment_done_00022, R.drawable.payment_done_00023, R.drawable.payment_done_00024, R.drawable.payment_done_00025, R.drawable.payment_done_00026, R.drawable.payment_done_00027, R.drawable.payment_done_00028, R.drawable.payment_done_00029, R.drawable.payment_done_00030, R.drawable.payment_done_00031, R.drawable.payment_done_00032, R.drawable.payment_done_00033, R.drawable.payment_done_00034, R.drawable.payment_done_00035, R.drawable.payment_done_00036, R.drawable.payment_done_00037, R.drawable.payment_done_00038, R.drawable.payment_done_00039, R.drawable.payment_done_00040, R.drawable.payment_done_00041, R.drawable.payment_done_00042, R.drawable.payment_done_00043, R.drawable.payment_done_00044, R.drawable.payment_done_00045, R.drawable.payment_done_00046, R.drawable.payment_done_00047, R.drawable.payment_done_00048, R.drawable.payment_done_00049, R.drawable.payment_done_00050, R.drawable.payment_done_00051, R.drawable.payment_done_00052, R.drawable.payment_done_00053, R.drawable.payment_done_00054, R.drawable.payment_done_00055, R.drawable.payment_done_00056, R.drawable.payment_done_00057, R.drawable.payment_done_00058, R.drawable.payment_done_00059, R.drawable.payment_done_00060
                        };

    TextView amount,tvTime,tvTxnId,tvMerchantName,tvPaid,tvCallText,tvName;
    CardView cvPayment, cvCall, cvBalance;
    PrefManager prefManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        getSupportActionBar().setElevation(2);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(android.R.color.white));

        prefManager=new PrefManager(this);

        cvPayment=(CardView) findViewById(R.id.cv_payment);
        cvPayment.setElevation(0);

        cvCall=(CardView) findViewById(R.id.cv_call);
        cvCall.setElevation(0);

        cvBalance=(CardView) findViewById(R.id.cv_balance);
        cvBalance.setElevation(0);

        tvMerchantName=(TextView) findViewById(R.id.tv_merchant_name);
        tvPaid=(TextView) findViewById(R.id.tv_paid);
        tvCallText=(TextView) findViewById(R.id.tv_call_text);
        tvName=(TextView) findViewById(R.id.tv_name);

        tvName.setText(prefManager.getName());

        tvMerchantName.setText(prefManager.getMerchant());
        if(prefManager.getMerNo()) {
            tvPaid.setText("Sent Successfully to");
            cvCall.setVisibility(View.GONE);
        }
        tvCallText.setText("Request Paytm to call "+prefManager.getMerchant()+" to confirm payment");

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
        Typeface tp = Typeface.createFromAsset(getAssets(), "Rupee_Foradian.ttf");
        amount=(TextView) findViewById(R.id.tv_amount);
        amount.setText("₹ "+prefManager.getAmount());
        amount.setTypeface(Typeface.DEFAULT_BOLD);
        //amount.setTextSize(25);
        amount.setTextColor(Color.BLACK);
        //amount.setTypeface(null, Typeface.BOLD);
        //amount.setTypeface(tp);
        tvTime=(TextView) findViewById(R.id.tv_time);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a, dd MMM yyyy");
        String dateString = sdf.format(date);
        tvTime.setText(dateString);
        tvTxnId=(TextView) findViewById(R.id.tv_txn_id);
        //Random rnd = new Random();
        //long n = 502211000 + rnd.nextInt(900000);
        long number = (long) Math.floor(Math.random() * 1_000_000_000L) + 1_000_000_000L;
        tvTxnId.setText("Wallet Txn ID:"+number);


    }
}
