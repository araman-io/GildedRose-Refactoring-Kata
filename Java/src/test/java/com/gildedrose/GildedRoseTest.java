package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void quality_degardes_twice_as_fast_after_sellIn() {
        Item item = new Item("foo", 0, 7);
        GildedRose app = new GildedRose(new Item[]{item});
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn, "sell in should have lowered by 1");
        assertEquals(5, app.items[0].quality, "quality should have lowered by 1");
    }

    @Test
    void quality_can_never_be_negative() {
        Item item = new Item("foo", 5, 1);
        GildedRose app = new GildedRose(new Item[]{item});
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "quality should be 0");
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "quality shouldnt be negative");
    }

    @Test
    void aged_brie_increases_in_quality_every_day() {
        Item item = new Item("Aged Brie", 5, 1);
        GildedRose app = new GildedRose(new Item[]{item});
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn, "sellIn should have decremented");
        assertEquals(2, app.items[0].quality, "aged brie quality should have improved to 2");
        app.updateQuality();
        assertEquals(3, app.items[0].sellIn, "sellIn should have decremented");
        assertEquals(3, app.items[0].quality, "aged brie quality should have improved to 3");
    }

    @Test
    void quality_can_never_be_more_than_50() {
        Item item = new Item("Aged Brie", 5, 49);
        GildedRose app = new GildedRose(new Item[]{item});
        app.updateQuality();
        app.updateQuality();
        assertEquals(50, app.items[0].quality, "quality can never be more than 50");
    }

    @Test
    void item_can_have_quality_upto_50_only() {
        assertDoesNotThrow(() -> {
            Item anItem = new Item("foo", 6, 50);
            GildedRose app = new GildedRose(new Item[]{anItem});
        });
    }

    @Test
    void item_cant_have_quality_more_than_50() {
        assertThrows(RuntimeException.class, () -> {
            Item i = new Item("foo", 6, 51);
            GildedRose app = new GildedRose(new Item[]{i});
        });
    }

    @Test
    void sulfuras_quality_never_changes() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 5, 37);
        GildedRose app = new GildedRose(new Item[]{item});
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(37, app.items[0].quality, "Sulfuras quality should have never changed");
        assertEquals(5, app.items[0].sellIn, "Sulfuras sellIn should have never changed");
    }

    @Test
    void passes_increases_by_2_in_quality_with_10days_or_less() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 9, 37);
        GildedRose app = new GildedRose(new Item[]{item});
        app.updateQuality();
        assertEquals(39, app.items[0].quality, "quality should have increased by 2");
    }

    @Test
    void passes_increases_by_3_in_quality_with_5days_or_less() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 37);
        GildedRose app = new GildedRose(new Item[]{item});
        app.updateQuality();
        assertEquals(40, app.items[0].quality, "quality should have increased by 3");
    }

    @Test
    void passes_quality_is_0_after_sellin() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 37);
        GildedRose app = new GildedRose(new Item[]{item});
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "quality should have been 0");
    }

    void passes_quality_never_exceeds_50() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 47);
        GildedRose app = new GildedRose(new Item[]{item});
        app.updateQuality();
        assertEquals(3, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        app.updateQuality();
        assertEquals(2, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

}
