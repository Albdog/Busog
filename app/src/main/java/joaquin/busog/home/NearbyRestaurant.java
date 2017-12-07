package joaquin.busog.home;

import android.util.Log;

import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Collections;

import joaquin.busog.R;

/**
 * Created by Migue909 on 04/11/2017.
 */

public class NearbyRestaurant {
    private static String[] addresses;
    private static String[] distances;
    private static int[] resourceIds;

    public NearbyRestaurant() {

        ArrayList<Place> places = MapTabFragment.mPlacesList;

        if (places.size() > 0) {
            int tmp = 0;

            if (places.size() < 5) {
                tmp = places.size();
            } else {
                tmp = 5;
            }

            for (int i = 0; i < tmp; i++) {
                addresses[i] = places.get(i).getPlace().get("vicinity");
                distances[i] = Double.toString(places.get(i).getDistance());
                if (places.get(i).getPlace().get("place_name").indexOf("Jollibee") >= 0) {
                    resourceIds[i] = R.drawable.jollibee_logo;
                } else {
                    resourceIds[i] = R.drawable.mcdo_logo;
                }
            }

        } else {

            addresses = new String[]{"Cor. Rosa Alvaro St., Katipunan Ave, Quezon City, 1800 Metro Manila", "Lower Ground Floor, Farmer's Plaza, Epifanio de los Santos Ave, Cubao, Quezon City, 1109 Metro Manila",
                    "Bedrock Building, Commonwealth Avenue Corner Luzon Avenue, Matandangy Balara, Quezon City, 1119 Metro Manila", "North Avenue corner, Mindanao Avenue, Project 6, Quezon City, 1105 Metro Manila",
                    "Ground Flr, Barrington Place, Congressional Ave, Project 8, Quezon City, Metro Manila"};
            distances = new String[]{"800m", "1.2km", "1.5km", "5km", "9km"};
            resourceIds = new int[]{R.drawable.mcdo_logo, R.drawable.mcdo_logo, R.drawable.mcdo_logo, R.drawable.mcdo_logo, R.drawable.mcdo_logo};

        }
    }


    public static String[] getAddresses() {
        return addresses;
    }

    public static String[] getDistances() {
        return distances;
    }

    public static int[] getResourceIds() {
        return resourceIds;
    }
}
