package joaquin.busog.home;

import android.location.Location;
import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by Migue909 on 07/12/2017.
 */

public class Place implements Comparable<Place> {

    private HashMap<String, String>  place;
    private Location myLocation;

    public Place(HashMap<String, String> place, Location myLocation) {
        this.place = place;
        this.myLocation = myLocation;
    }

    public double getDistance() {
        double r = 6371000;
        double lat1 = Math.toRadians(Double.parseDouble(place.get("lat")));
        double lat2 = Math.toRadians(myLocation.getLatitude());

        double diffLat = Math.toRadians(myLocation.getLatitude() - Double.parseDouble(place.get("lat")));
        double diffLng = Math.toRadians(myLocation.getLongitude() - Double.parseDouble(place.get("lng")));

        double a = Math.sin(diffLat/2) * Math.sin(diffLat/2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(diffLng/2) * Math.sin(diffLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));

        return r * c;

    }

    @Override
    public int compareTo(@NonNull Place o) {

        if(this.getDistance() == o.getDistance())
            return 0;
        else if(this.getDistance() < o.getDistance())
            return -1;
        else
            return 1;
    }

    public HashMap<String, String> getPlace() {
        return place;
    }

    public Location getMyLocation() {
        return myLocation;
    }
}
