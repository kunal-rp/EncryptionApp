package com.example.kpadmin.encryptionapp;


        import android.app.ActionBar;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.text.TextUtils;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.Window;
        import android.view.animation.AlphaAnimation;
        import android.view.animation.Animation;
        import android.view.animation.AnimationSet;
        import android.view.animation.AnimationUtils;
        import android.view.animation.Interpolator;
        import android.view.animation.LinearInterpolator;
        import android.view.animation.RotateAnimation;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ScrollView;
        import android.widget.TextSwitcher;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.ViewSwitcher;

        import java.util.logging.Handler;
        import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    EditText code;
    EditText message;
    TextSwitcher textSwitcher;


    String[] rotorInputStrings = {
            "nD]Utvwc%[fLjZhH=mS-}lMTgO9r6@a8iG5$0Fx{W2K3|+,e;u4Es!`1RJ^CN?VYP*B(z 7IA_dQ~'kypbq/X):.#o",
            "nzi3.:mG -Bge]5Z@r#L/?PXRpuoU`0sqVf~)6b*dCWT7A_[Kl!;hQ=xvc}tDIOkHFS|yJ8+wNY,2a{(9M%1'$4^Ej",
            "`;M/|rE*y%'@f_)$7vn01]hc2PKGTxXHDOw=d5YAoq+IBe!#WugQ UJa:4ZNS([6k}.^sCm-F8{V?tiljL93bp~R,z",
            ":n2'O[]*hSgi)._R(vf,PtNjY0}$pVz3@=TE-8o1lrWsc6u!ae;4qX`#bByx%AU5^kL+I~JDd/?9FKQCG|7mwMZH{ ",
            "g-jTk7Kqy9FsnVPtpr6,lu5w~@Geh/`[M=iRQJD)LNO;]*E!8:zUW(m+{o41H?_bZ^Y2'cf%v}03.dIXC|#$B AaxS"};
    String reflector = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890qwertyuioplkjhgfdsazxcvbnm ,./;'[]=-`~!@#$%^*()_+{}|:?";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        button = (Button) findViewById(R.id.button);
        button.setText("Encrypt");
        code = (EditText) findViewById(R.id.code);
        message = (EditText) findViewById(R.id.message);


        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){


        RotorsInPlace rotors = new RotorsInPlace();

        rotors.importReflector(reflector);
        for (int i = 0; i < rotorInputStrings.length; i++) {
            rotors.setAllRotors(i + 1, rotorInputStrings[i]);

        }



        String key = code.getText().toString();

        if(TextUtils.isEmpty(code.getText())) {
            code.setError("Need to have an Key inputted");
            return;
        }
        else{
            code.setError(null);
            rotors.keyToSettings(key);
        }
        if(TextUtils.isEmpty(message.getText())) {
            message.setError("Need to have some Message inputted");
            return;
        }
        else {
            message.setError(null);
            RotateAnimation animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);


            animation.setInterpolator(new LinearInterpolator());
            animation.setRepeatCount(0);
            animation.setDuration(300);


            final Animation messageIn = new AlphaAnimation(0.0f, 1.0f);
            messageIn.setDuration(300);
            messageIn.setStartOffset(300);

            final Animation messageOut = new AlphaAnimation(1.0f, 0.0f);
            messageOut.setDuration(300);



            StringBuilder encrypted_text = new StringBuilder("");
            for (int i = 0; i < message.getText().length(); i++) {
                encrypted_text.append(rotors.passLetterThroughRotors(message.getText().toString().charAt(i)));
            }
            message.setText(encrypted_text);
            message.setAnimation(messageOut);


            button.setAnimation(animation);

            Toast.makeText(MainActivity.this, Integer.toString(encrypted_text.length()),
                    Toast.LENGTH_SHORT).show();

            //startActivity(new Intent(MainActivity.this, MessageEncryptionAnimation.class));


        }

    }
}

