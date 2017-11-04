package joaquin.busog;

/**
 * Created by Migue909 on 03/11/2017.
 */

public class RecentMeal {

    private static String[] content;
    private static String[] prices;
    private static int[] resourceIds;

    public RecentMeal(){
        content = new String[]{"Ala Carte Burger McDo, Small Crispy Chicken Sandwich Meal, Oreo McFlurry", "Large Quarter Pounder, Ala Carte Cheeseburger",
                "Medium Fries, Coke Float", "Large Fries, Oreo McFlurry, Large Sprite", "Small Cheesburger, with upgrade to Large Fries"};
        prices = new String[]{"350.00", "574.00", "220.00", "70.00", "100.00"};
        resourceIds = new int[]{R.drawable.mcdo_logo,R.drawable.mcdo_logo,R.drawable.mcdo_logo,R.drawable.mcdo_logo,R.drawable.mcdo_logo};
    }

    public static String[] getContent() {
        return content;
    }

    public static String[] getPrices() {
        return prices;
    }

    public static int[] getResourceIds() {
        return resourceIds;
    }
}
