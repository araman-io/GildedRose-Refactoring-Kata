package com.gildedrose;

import static java.util.Arrays.stream;

class GildedRose {
    Item[] items;
    StrategyFactory factory = new StrategyFactory();

    public GildedRose(Item[] items) {
        this.items = items;
        stream(items).forEach(i -> {
            if (i.quality > 50) {
                throw new RuntimeException(i.toString() + "cant have quality more than 50");
            }
        });
    }

    public void updateQuality() {
        stream(items)
            .forEach(item -> {
                Strategy chosenStrategy = factory.fetchStrategyFor(item);
                chosenStrategy.updateQuality(item);
                chosenStrategy.trimQualityToUpperAndLowerLimits(item);
            });
    }
}
