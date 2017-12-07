package joaquin.busog.mealPlan;

public class Order {
    public String mItemName, mMealType;
    public double mPrice;
    public int mQuantity, mImage;

    public Order(String itemName,  double price, int quantity, int image, String mealType) {
        mItemName = itemName;
        mPrice = price;
        mQuantity = quantity;
        mImage = image;
        mMealType = mealType;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public String getMealType() {
        return mMealType;
    }

    public void setMealType(String mealType) {
        mMealType = mealType;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public void incQuantity(int quantity) {
        mQuantity += quantity;
    }

    public void decQuantity(int quantity) {
        mQuantity -= quantity;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }
}
