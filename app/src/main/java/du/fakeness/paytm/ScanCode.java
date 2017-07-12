package du.fakeness.paytm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by admin on 20-03-2017.
 */

public class ScanCode extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(net.one97.paytm.wallet.newdesign.activity.PaySendActivity);
        setContentView(R.layout.scan_code);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setElevation(0);
  //      getSupportActionBar().setTitle(Html.fromHtml("<small>Enter Mobile Number to Pay</font></small>"));
    //    getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(android.R.color.black));
        ImageView scanEdit, scanImg;
        scanEdit=(ImageView) findViewById(R.id.iv_scan_edit);
        scanImg=(ImageView) findViewById(R.id.iv_scan_img);
        scanEdit.setOnClickListener(this);
        scanImg.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        //startActivity(new Intent("net.one97.paytm.wallet.newdesign.activity.PaySendActivity"));
        startActivity(new Intent(ScanCode.this, InputAmtNo.class));
        finish();
    }
}
