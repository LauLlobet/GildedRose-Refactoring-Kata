package com.gildedrose;

import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class MultipleItemsCharacterizationTest {

    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE = "Aged Brie";

    private Item[] updatedItems;

    private Item secondItem() {
        return updatedItems[1];
    }

    private Item firstItem() {
        return updatedItems[0];
    }

    public void glidedRoseExecutionWithTwoItems(String name1, int sellin1, int quality1, String name2, int sellin2, int quality2) {
        Item[] items = new Item[]{new Item(name1, sellin1, quality1), new Item(name2, sellin2, quality2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        ;
        updatedItems = app.items;

    }

    private void glidedRoseExecutionWithOneItem(String name, int sellin, int quality) {
        Item[] items = new Item[]{new Item(name, sellin, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        ;
        updatedItems = app.items;
    }

    @Test
    public void
    if_two_aged_bries_are_provided_quality_incrases_by_two() {
        glidedRoseExecutionWithTwoItems(AGED_BRIE, 0, 10, AGED_BRIE, 0, 10);
        assertThat(secondItem().quality, is(12));
        assertThat(firstItem().quality, is(12));
    }

    @Test
    public void
    if_aged_brie_and_backstage_are_provided_at_sellin_zero_they_behave_as_if_they_where_alone() {
        glidedRoseExecutionWithTwoItems(AGED_BRIE, 0, 10, BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT, 0, 10);
        assertThat(firstItem().quality, is(12));
        assertThat(secondItem().quality, is(0));
    }

}
