package bu.mobile.app.tour;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;

public class conView extends LinearLayout {
    TextView fruitTextView;
    TextView priceTextView;
    ImageView iimgImgView;

    private static final String TAG = "conView";

    private static Context context;



    public conView(Context context) {
        super(context);
        inflatetion_init(context);
        this.context = context.getApplicationContext();

        fruitTextView = (TextView)findViewById(R.id.c_name);
        priceTextView = (TextView)findViewById(R.id.c_address);
        iimgImgView = (ImageView)findViewById(R.id.stamp1);



    }

    private void inflatetion_init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.conitem,this,true);
    }

    public void setFruit(String fruit){
        fruitTextView.setText(fruit);
    }
    public void setPrice(String price){
        priceTextView.setText(price);
    }
    public void setiimg(String iimg){

        String pak_name = context.getPackageName();
        int resID = context.getResources().getIdentifier(iimg, "drawable",pak_name);
        iimgImgView.setImageResource(resID);
    }
}
