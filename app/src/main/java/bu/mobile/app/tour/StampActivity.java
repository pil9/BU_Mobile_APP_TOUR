package bu.mobile.app.tour;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import static bu.mobile.app.tour.MypageActivity.fruits;
import static bu.mobile.app.tour.MypageActivity.iimg;
import static bu.mobile.app.tour.MypageActivity.price;

public class StampActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);

        imageView = (ImageView)findViewById(R.id.imageview);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruits.addFirst("보탑사 스탬프");
                price.addFirst("(12월5일까지) 기념품관 20% 할인 쿠폰");
                iimg.addFirst("stamp1");

                finish();

//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
//                float scale = (float) (1024/(float)bitmap.getWidth());
//                int image_w = (int) (bitmap.getWidth() * scale);
//                int image_h = (int) (bitmap.getHeight() * scale);
//                Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
//                resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] byteArray = stream.toByteArray();
//
//                Intent intent = new Intent(StampActivity.this, StampSecondActivity.class);
//
//                startActivity(intent);
            }
        });


    }
}

