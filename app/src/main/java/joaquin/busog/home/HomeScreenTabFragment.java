package joaquin.busog.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import joaquin.busog.R;
import joaquin.busog.mealPlan.RestaurantActivity;

/**
 * Created by Migue909 on 03/11/2017.
 */

public class HomeScreenTabFragment extends Fragment{

    @BindView(R.id.createMealButton) Button createMealButton;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homescreen_tab_fragment,container, false);
        mUnbinder = ButterKnife.bind(this, view);

        RecyclerView recentMealsRecyclerView = view.findViewById(R.id.recentMealsList);
        RecentMealAdapter recentMealAdapter = new RecentMealAdapter();
        recentMealsRecyclerView.setAdapter(recentMealAdapter);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        recentMealsRecyclerView.setLayoutManager(layoutManager1);

        RecyclerView nearbyRestaurantsRecyclerView = view.findViewById(R.id.nearbyRestaurantsList);
        NearbyRestaurantAdapter nearbyRestaurantAdapter = new NearbyRestaurantAdapter();
        nearbyRestaurantsRecyclerView.setAdapter(nearbyRestaurantAdapter);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        nearbyRestaurantsRecyclerView.setLayoutManager(layoutManager2);

        createMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RestaurantActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
