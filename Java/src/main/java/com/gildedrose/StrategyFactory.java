package com.gildedrose;

import static java.util.Arrays.stream;

public class StrategyFactory {
    Strategy[] strategies = new Strategy[]{
        new ConjuredStartegy(),
        new SulfurasStrategy(),
        new PassesStrategy(),
        new AgedBrieStrategy()};
    private DefaultStrategy defaultStrategy = new DefaultStrategy();

    Strategy fetchStrategyFor(Item item) {
        return stream(strategies)
            .filter(s -> s.applies(item))
            .findFirst()
            .orElse(defaultStrategy);
    }
}

