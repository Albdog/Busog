package joaquin.busog.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import joaquin.busog.R;

/**
 * Created by Migue909 on 04/11/2017.
 */

public class RecentMealAdapter extends  RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_meals_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        RecentMeal tmp = new RecentMeal();
        return tmp.getContent().length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView1;
        private TextView mTextView2;
        private ImageView mImageView;

        public ListViewHolder(View itemView){
            super(itemView);
            mImageView = itemView.findViewById(R.id.recentMealRestaurantLogo);
            mTextView1 = itemView.findViewById(R.id.mealContents);
            mTextView2 = itemView.findViewById(R.id.mealPrice);
        }

        public void bindView(int position) {
            RecentMeal tmp = new RecentMeal();
            mTextView1.setText(tmp.getContent()[position]);
            mTextView2.setText(tmp.getPrices()[position]);
            mImageView.setImageResource(tmp.getResourceIds()[position]);
        }
    }
}
