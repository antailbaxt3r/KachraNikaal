package com.antailbaxt3r.kachranikaal;

public class ShopItem {

    private int price;
    private String name, img;

    public ShopItem() {
    }

    public ShopItem(int price, String name, String img) {
        this.price = price;
        this.name = name;
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
