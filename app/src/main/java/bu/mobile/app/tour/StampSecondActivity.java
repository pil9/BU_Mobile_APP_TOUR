package bu.mobile.app.tour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class StampSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stampsecond);


        ImageView imageview = (ImageView)findViewById(R.id.imageview);
        TextView textview = (TextView)findViewById(R.id.textview);

        Bundle extras = getIntent().getExtras();
        String s = extras.getString("string");
        int i = extras.getInt("integer");
        double d = extras.getDouble("double");
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        String str = s+"\n"+i+"\n"+Double.toString(d)+"\n";
        textview.setText(str);
        imageview.setImageBitmap(bitmap);

    }
}
