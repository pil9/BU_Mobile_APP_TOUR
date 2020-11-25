package bu.mobile.app.tour;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<TourList> arrayList;
    private Context context;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int prePosition = -1;



    public CustomAdapter(ArrayList<TourList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImage())
                .into(holder.iv_image);
        holder.onBind(arrayList.get(position), position);
        holder.tv_name.setText(arrayList.get(position).getName());
        holder.tv_address.setText(arrayList.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    void addItem(TourList tourList){
        arrayList.add(tourList);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_image;
        private ImageView iv_image2;
        private TextView tv_name;
        private TextView tv_address;
        private int position;
        private TourList tourList;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_image = itemView.findViewById(R.id.iv_image);
            this.iv_image2 = itemView.findViewById(R.id.iv_image2);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_address = itemView.findViewById(R.id.tv_address);
        }
        void onBind(TourList tourList, int position){
            this.tourList = tourList;
            this.position = position;

            tv_name.setText(tourList.getTxt());
            tv_address.setText(tourList.getTxt());

            changeVisibility(selectedItems.get(position));

            itemView.setOnClickListener(this);
            tv_name.setOnClickListener(this);
            tv_address.setOnClickListener(this);
            iv_image.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.linear:
                    if(selectedItems.get(position)){
                        selectedItems.delete(position);
                    }else {
                        selectedItems.delete(prePosition);
                        selectedItems.put(position, true);
                    }
                    if(prePosition != -1) notifyItemChanged(prePosition);
                    notifyItemChanged(position);
                    prePosition = position;
                    break;
                case R.id.tv_name:
                case R.id.tv_address:
                case R.id.iv_image:
                    break;

            }

        }
        private void changeVisibility(final boolean isExpanded) {
            int dpValue = 150;
            float d = context.getResources().getDisplayMetrics().density;
            int height = (int) (dpValue * d);

            ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
            va.setDuration(600);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    iv_image2.getLayoutParams().height = value;
                    iv_image2.requestLayout();
                    iv_image2.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

                }
            });
            va.start();
        }

    }

}

