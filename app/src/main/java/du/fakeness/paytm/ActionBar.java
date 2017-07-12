package du.fakeness.paytm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by admin on 19-03-2017.
 */

public class ActionBar extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_bar_layout);
        ImageButton share= (ImageButton) findViewById(R.id.slideMenuButton);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent= new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "HI THIS PAYTM FAKE ACTIVITY");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }
}
