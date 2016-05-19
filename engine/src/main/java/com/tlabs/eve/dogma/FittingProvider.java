package com.tlabs.eve.dogma;

import com.tlabs.eve.dogma.model.Item;

public interface FittingProvider {

    Item findItem(final long itemID);

    Item findItem(final String name);
}
