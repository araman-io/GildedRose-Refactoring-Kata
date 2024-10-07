package com.gildedrose;

public interface Strategy {

    boolean applies(Item i);

    void updateQuality(Item i);

    default void trimQualityToUpperAndLowerLimits(Item item) {
        if (item.quality < 0) item.quality = 0;
        if (item.quality > 50) item.quality = 50;
    }
}

class DefaultStrategy implements Strategy {
    @Override
    public boolean applies(Item i) {
        return true;
    }

    @Override
    public void updateQuality(Item item) {
        item.sellIn = item.sellIn - 1;
        item.quality = item.sellIn <= 0 ? item.quality - 2 : item.quality - 1;
    }
}

class AgedBrieStrategy implements Strategy {

    @Override
    public boolean applies(Item i) {
        return i.name.toLowerCase().startsWith("aged brie");
    }

    @Override
    public void updateQuality(Item i) {
        i.sellIn = i.sellIn - 1;
        i.quality = i.quality + 1;
    }
}

class PassesStrategy implements Strategy {

    @Override
    public boolean applies(Item i) {
        return i.name.toLowerCase().startsWith("backstage passes");
    }

    @Override
    public void updateQuality(Item i) {
        i.sellIn = i.sellIn - 1;
        if (i.sellIn <= 0) {
            i.quality = 0;
        } else if (i.sellIn <= 5) {
            i.quality = i.quality + 3;
        } else if (i.sellIn <= 10) {
            i.quality = i.quality + 2;
        }
    }
}

class SulfurasStrategy implements Strategy {

    @Override
    public boolean applies(Item i) {
        return i.name.toLowerCase().startsWith("sulfuras");
    }

    @Override
    public void updateQuality(Item i) {
        //do nothing
    }

    @Override
    public void trimQualityToUpperAndLowerLimits(Item item) {
        item.quality = 80;
    }
}

class ConjuredStartegy implements Strategy {

    @Override
    public boolean applies(Item i) {
        return i.name.toLowerCase().startsWith("conjured");
    }

    @Override
    public void updateQuality(Item i) {
        i.sellIn = i.sellIn - 1;
        i.quality = (i.sellIn <= 0) ? i.quality - 4 : i.quality - 2;
    }
}
