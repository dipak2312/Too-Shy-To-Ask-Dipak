package com.neuronimbus.metropolis.Models;

public class StoreHouseItems {
    int storehouse_items_img;
    String storehouse_item_txt;

    public StoreHouseItems(int storehouse_items_img, String storehouse_item_txt) {
        this.storehouse_items_img = storehouse_items_img;
        this.storehouse_item_txt = storehouse_item_txt;
    }

    public int getStorehouse_items_img() {
        return storehouse_items_img;
    }

    public void setStorehouse_items_img(int storehouse_items_img) {
        this.storehouse_items_img = storehouse_items_img;
    }

    public String getStorehouse_item_txt() {
        return storehouse_item_txt;
    }

    public void setStorehouse_item_txt(String storehouse_item_txt) {
        this.storehouse_item_txt = storehouse_item_txt;
    }
}
