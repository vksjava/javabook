package Builder;

import dto.Shop;

public class ShopBuilder {
    public static Shop createShop(int id, String city, String name, String country) {
        Shop shop = new Shop();
        shop.setCity(city);
        shop.setCountry(country);
        shop.setName(name);
        shop.setId(id);
        return shop;
    }


}
