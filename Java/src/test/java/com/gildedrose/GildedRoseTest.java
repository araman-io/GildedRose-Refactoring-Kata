package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void quality_degardes_every_day() {
        Item item = new Item("foo", 5, 10);
        GildedRose app = new GildedRose(new Item[]{item});
        app.updateQuality();

        assertEquals(4, app.items[0].sellIn, "sell in should have lowered by 1");
        assertEquals(9, app.items[0].quality, "quality should have lowered by 1");
    }
    
}
