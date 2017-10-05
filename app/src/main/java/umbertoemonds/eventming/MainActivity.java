package umbertoemonds.eventming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import umbertoemonds.mingevent.EventMing;

public class MainActivity extends AppCompatActivity {

    private Button mSendBTN;
    private Button mSendBTN2;
    private TextView mResultTV;
    private EditText mInputET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendBTN = (Button) findViewById(R.id.sendBtn);
        mSendBTN2 = (Button) findViewById(R.id.sendBtn2);
        mResultTV = (TextView) findViewById(R.id.resultTv);
        mInputET = (EditText) findViewById(R.id.messageEt);

        mSendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("input", mInputET.getText().toString());

                EventMing.sendNotification(getApplicationContext(), "myIdentifier", bundle);
            }
        });

        mSendBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("input", mInputET.getText().toString());

                EventMing.sendNotification(getApplicationContext(), "myOtherIdentifier", bundle);
            }
        });

        EventMing.setOnNotificationReceived(new EventMing.OnNotificationReceived() {
            @Override
            public void onReceived(String identifier, @Nullable Bundle data) {
                if (data != null) {
                    if(identifier.equals("myIdentifier")){
                        mResultTV.setText("First Identifier: " + identifier + " / Data: " + data.getString("input"));
                    }else if(identifier.equals("myOtherIdentifier")){
                        mResultTV.setText("Other Identifier: " + identifier + " / Data: " + data.getString("input"));
                    }
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventMing.subscribeNotifications(getApplicationContext(), "myIdentifier");
        EventMing.subscribeNotifications(getApplicationContext(), "myOtherIdentifier");
    }
}