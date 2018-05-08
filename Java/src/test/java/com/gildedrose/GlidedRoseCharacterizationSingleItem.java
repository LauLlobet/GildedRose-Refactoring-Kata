package com.gildedrose;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class GlidedRoseCharacterizationSingleItem {

    private GildedRose getGildedRoseWithSingleItem(Item item) {
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app;
    }

    @Test
    public void
    have_empty_items_when_empty_items_are_provided() {
        Item[] items = new Item[]{};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items, is(items));
    }

    // Quality = N , rest 0

    @Test
    public void
    decreases_quality_to_0_when_1_quality_is_provided() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", 0, 1));
        assertThat(app.items[0].quality, is(0));
    }

    @Test
    public void
    decreases_quality_to_0_when_2_quality_is_provided_and_0_sellin() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", 0, 2));
        assertThat(app.items[0].quality, is(0));
    }

    @Test
    public void
    leaves_quality_to_minus_one_if_provided_so() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", 0, -1));
        assertThat(app.items[0].quality, is(-1));
    }

    @Test
    @Parameters({"3, 1",
            "4, 2",
            "5, 3",
            "6, 4",
            "7, 5"})
    public void
    decreases_quality_by_2_sellin_is_0_and_name_is_empty(int intitalQuality, int expectedQuality) {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", 0, intitalQuality));
        assertThat(app.items[0].quality, is(expectedQuality));
    }


    @Test
    @Parameters({"1, 0",
            "2, 1",
            "3, 2",
            "4, 3",
            "5, 4",
            "6, 5",
            "7, 6"})
    public void
    decreases_quality_by_1_when_sellin_is_100_and_name_is_empty(int intitalQuality, int expectedQuality) {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", 1, intitalQuality));
        assertThat(app.items[0].quality, is(expectedQuality));
    }

    @Test
    @Parameters({"2, 0",
            "3, 1",
            "4, 2",
            "5, 3",
            "6, 4",
            "7, 5"})
    public void
    decreases_quality_by_2_when_sellin_is_minus_one(int intitalQuality, int expectedQuality) {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", -1, intitalQuality));
        assertThat(app.items[0].quality, is(expectedQuality));
    }


    // Sellin != 0, rest 0


    @Test
    @Parameters({"-1,-2",
            "0,-1",
            "1,0",
            "2,1",
            "3,2"})
    public void
    decrases_sellin_by_one_when_quality_is_zero_and_has_no_name(int intitalSellin, int expectedSellin) {
        Item item = new Item("", intitalSellin, 0);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].sellIn, is(expectedSellin));
    }

    @Test
    @Parameters({"-1,-2",
            "0,-1",
            "1,0",
            "2,1",
            "3,2"})
    public void
    decrases_sellin_by_one_when_quality_is_100_and_has_no_name(int intitalSellin, int expectedSellin) {
        Item item = new Item("", intitalSellin, 100);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].sellIn, is(expectedSellin));
    }

    @Test
    @Parameters({"-1,-2",
            "0,-1",
            "1,0",
            "2,1",
            "3,2"})
    public void
    decreases_sellin_by_one_when_quality_is_negative(int intitalSellin, int expectedSellin) {
        Item item = new Item("", intitalSellin, -100);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].sellIn, is(expectedSellin));
    }


    //Changeing Names
    @Test
    @Parameters({"-1,-2",
            "0,-1",
            "1,0",
            "2,1",
            "3,2"})
    public void
    decrases_sellin_by_one_when_Aged_Brie_is_provided(int intitalSellin, int expectedSellin) {
        Item item = new Item("Aged Brie", intitalSellin, 100);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].sellIn, is(expectedSellin));
    }

    @Test
    @Parameters({"-1,1",
            "0,2",
            "1,3",
            "2,4",
            "3,5"})
    public void
    increase_quality_when_aged_brie_is_provided(int intitalQuality, int expectedQuality) {
        Item item = new Item("Aged Brie", 0, intitalQuality);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].quality, is(expectedQuality));
    }

    @Test
    @Parameters({"-2,-2",
            "-1,-1",
            "0,0",
            "1,1",
            "2,2",
            "3,3"})
    public void
    if_sulfura_is_provided_quality_doesent_decrease(int intitalQuality, int expectedQuality) {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, intitalQuality);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].quality, is(expectedQuality));
    }

    @Test
    @Parameters({"-1,-1",
            "0,0",
            "1,1",
            "2,2",
            "3,3"})
    public void
    leaves_sellin_as_it_is_when_Sulfuras_is_provided(int intitalSellin, int expectedSellin) {
        Item item = new Item("Sulfuras, Hand of Ragnaros", intitalSellin, 100);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].sellIn, is(expectedSellin));
    }

    @Test
    @Parameters({"-2,0",
            "-1,0",
            "0,0",
            "1,0",
            "2,0",
            "3,0"})
    public void
    quality_goes_to_zero_for_backstage_pases(int intitalQuality, int expectedQuality) {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, intitalQuality);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].quality, is(expectedQuality));
    }

    @Test
    @Parameters({"-1,-1",
            "0,0",
            "1,1",
            "2,2",
            "3,3"})
    public void
    sellin_decreases_by_one_when_backstage_passes_are_provided(int intitalSellin, int expectedSellin) {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", intitalSellin, 100);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].sellIn, is(expectedSellin));
    }
}