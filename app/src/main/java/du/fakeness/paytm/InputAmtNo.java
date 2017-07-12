package du.fakeness.paytm;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by admin on 20-03-2017.
 */

public class InputAmtNo extends AppCompatActivity implements View.OnClickListener {

    Button proceedAmt;
    //EditText
    PrefManager prefManager;
    TextView tvMerchantName;
    ImageView ivMerLogo;
    String name;
    LinearLayout lMerType;
    AppCompatEditText etPhone, etAmount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_amt_no);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(android.R.color.white));

        prefManager=new PrefManager(this);

        proceedAmt=(Button) findViewById(R.id.bt_proceed_amt);
        proceedAmt.setOnClickListener(this);
        etAmount=(AppCompatEditText) findViewById(R.id.et_amount);
        etPhone=(AppCompatEditText) findViewById(R.id.et_phone);
        tvMerchantName=(TextView) findViewById(R.id.tv_merchant_name);
        ivMerLogo=(ImageView) findViewById(R.id.iv_pic);
        lMerType=(LinearLayout) findViewById(R.id.l_mer_type);

        if(prefManager.isOtherMerchnat()){
            etPhone.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        if(!prefManager.getMerNo()){
            etPhone.setVisibility(View.GONE);
            lMerType.setVisibility(View.VISIBLE);
            String merName = prefManager.getMerchant();
            tvMerchantName.setText(merName);
            proceedAmt.setText("Pay ");
            if(merName.startsWith("Uber") |merName.startsWith("Sky")|merName.startsWith("IDD")){
                ivMerLogo.setImageDrawable(getDrawable(R.drawable.food));
            }else if(merName.startsWith("Happ")){
                ivMerLogo.setImageDrawable(getDrawable(R.drawable.health_care));
            }
            etAmount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    //int amt = Integer.parseInt(charSequence.toString());
                   // if (charSequence.toString().length() < 5){
                        //etAmount.setError("Please enter amount lower than ₹ 5000");
                    //}else{
                        proceedAmt.setText("Pay ₹ "+charSequence);
                    //}
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    int amt = Integer.parseInt(charSequence.toString());
                    //if (amt > 5000){
                        //etAmount.setError("Please enter amount lower than ₹ 5000");
                    //}else{
                        proceedAmt.setText("Pay ₹ "+charSequence);
                    //}
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            getSupportActionBar().setTitle(Html.fromHtml("<small>You are Paying</small>"));
        }else{
            getSupportActionBar().setTitle(Html.fromHtml("<small>Enter Mobile Number to Pay</small>"));
            lMerType.setVisibility(View.GONE);
            etPhone.setVisibility(View.VISIBLE);
        }

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
        switch (view.getId()){
            case R.id.bt_proceed_amt:
                //if(etPhone.getText().toString().isEmpty()){
                  //  Toast.makeText(this,"Enter Valid Phone no", Toast.LENGTH_SHORT).show();
                //}else {
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.confirm_paay_dialog, null);
                dialogBuilder.setView(dialogView);
                final EditText etName = (EditText) dialogView.findViewById(R.id.et_name);
                Button btPay = (Button) dialogView.findViewById(R.id.bt_proceed_pay);
                TextView tvName=(TextView) dialogView.findViewById(R.id.tv_name) ;
                btPay.setText("Proceed to Pay ₹ "+prefManager.getAmount());
                TextView tvCancel=(TextView) dialogView.findViewById(R.id.tv_cancel);

                //TextInputLayout =(TextInputLayout) dialogView.findViewById(R.id.text_input);
                prefManager.setAmount(etAmount.getText().toString());
                    if(prefManager.getMerNo()){
                        prefManager.setMerchant(etPhone.getText().toString());
                        etName.setVisibility(View.VISIBLE);
                        tvName.setVisibility(View.GONE);
                    }else{
                        etName.setVisibility(View.GONE);
                        tvName.setVisibility(View.VISIBLE);
                        name = "";
                    }
                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                btPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(prefManager.getMerNo()) {
                            prefManager.setName(etName.getText().toString());
                        }else {
                            if (prefManager.getMerNo()){
                                prefManager.setName("");
                            }
                        }
                        startActivity(new Intent(InputAmtNo.this, Payment.class));
                        alertDialog.dismiss();
                        finish();
                    }
                });

                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });


             //   }
                break;

        }
    }
}
