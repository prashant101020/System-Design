public class Item {
    private int itemId;
    private String itemName;
    private double itemPrice;

        public Item(int itemId, String itemName, double itemPrice) {
            this.itemId = itemId;
            this.itemName = itemName;
            this.itemPrice = itemPrice;
        }
        public int getItemId() {
            return itemId;
        }

        public String getItemName() {
            return itemName;
        }
        public double getItemPrice() {
            return itemPrice;
        }
        public void setItemPrice(double itemPrice) {
            this.itemPrice = itemPrice;
        }


}
