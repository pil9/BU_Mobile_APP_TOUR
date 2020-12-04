package bu.mobile.app.tour;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> implements OnListItemClickListener{

    private ArrayList<TourList> arrayList;
    private Context context;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int prePosition = -1;
    OnListItemClickListener listener;



    public CustomAdapter(ArrayList<TourList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view,this);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImage())
                .into(holder.iv_image);
        holder.tv_name.setText(arrayList.get(position).getName());
        holder.tv_address.setText(arrayList.get(position).getAddress());
        holder.chk1.setOnCheckedChangeListener(null);
        holder.chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(context,arrayList.get(position).getName()+" 즐겨찾기 등록!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public void setOnItemClicklistener(OnListItemClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onItemClick(CustomViewHolder holder, View view, int position) {
        if (listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private CheckBox chk1;
        private ImageView iv_image;
        private ImageView iv_image2;
        private TextView tv_name;
        private TextView tv_address;
        LinearLayout linearLayout;


        public CustomViewHolder(View itemView, final OnListItemClickListener listener) {
            super(itemView);
            this.iv_image = itemView.findViewById(R.id.iv_image);
            this.iv_image2 = itemView.findViewById(R.id.iv_image2);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_address = itemView.findViewById(R.id.tv_address);
            this.chk1 = itemView.findViewById(R.id.chk1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(CustomViewHolder.this,v,position);
                    }
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.detaillist);
                    WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                    params.width = WindowManager.LayoutParams.MATCH_PARENT;
                    params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes((WindowManager.LayoutParams)params);


                    TextView tvname = (TextView)dialog.findViewById(R.id.tvname);
                    tvname.setText(arrayList.get(position).getName());

                    ImageView ivimage = (ImageView)dialog.findViewById(R.id.ivimage);
                    Glide.with(itemView)
                            .load(arrayList.get(position).getImage())
                            .into(ivimage);

                    TextView tvaddress = (TextView)dialog.findViewById(R.id.tvaddress);
                    tvaddress.setText(arrayList.get(position).getAddress());

                    TextView tvtxt = (TextView)dialog.findViewById(R.id.tvtxt);
                    tvtxt.setText(arrayList.get(position).getTxt());

                    ImageButton exitBtn = dialog.findViewById(R.id.exitBtn);
                    exitBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }

    }

}

