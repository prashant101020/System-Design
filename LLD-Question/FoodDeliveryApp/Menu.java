import java.util.ArrayList;
import java.util.List;

public class Menu {
    private int menuId;
    private String menuName;
    private List<Item> menuItems;
    public Menu(){
        menuItems=new ArrayList<>();
    }
    void addItem(Item item){
        menuItems.add(item);
    }
        void removeItem(Item item){
            menuItems.remove(item);
        }
        List<Item> getMenuItems(){
            return menuItems;
        }
        public int getMenuId() {
            return menuId;
        }
        public String getMenuName() {
            return menuName;
        }
        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }
}
