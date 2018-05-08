package com.gildedrose;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class SigleItemCharacterisationTest {

    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE = "Aged Brie";

    private GildedRose getGildedRoseWithSingleItem(Item item) {
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app;
    }

/*

    TODO: do the test trying to extract meaning ( does it makes sense to have no name items, negative quality....)
    backstage passes have a quality reason asociated with quantity
    brie quality increases with time ... so tests wording can be more human

    Extract the same style tests into files





 */
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
    if_no_name_is_provided_decreases_quality_to_0_when_1_quality_is_provided() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", 0, 1));
        assertThat(app.items[0].quality, is(0));
    }

    @Test
    public void
    if_no_name_is_provided_decreases_quality_to_0_when_2_quality_is_provided_and_0_sellin() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", 0, 2));
        assertThat(app.items[0].quality, is(0));
    }

    @Test
    public void
    if_no_name_is_provided_leaves_quality_to_minus_one_if_provided_so() {
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
    if_no_name_is_provided_decreases_quality_by_2_sellin_is_0(int intitalQuality, int expectedQuality) {
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
    if_no_name_is_provided_decreases_quality_by_1_when_sellin_is_100(int intitalQuality, int expectedQuality) {
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
    if_no_name_is_provided_decreases_quality_by_2_when_sellin_is_minus_one(int intitalQuality, int expectedQuality) {
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
    if_no_name_is_provided_decrases_sellin_by_one_when_quality_is_zero(int intitalSellin, int expectedSellin) {
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
    if_no_name_is_provided_decrases_sellin_by_one_when_quality_is_100(int intitalSellin, int expectedSellin) {
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
    if_no_name_is_provided_decreases_sellin_by_one_when_quality_is_negative(int intitalSellin, int expectedSellin) {
        Item item = new Item("", intitalSellin, -100);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].sellIn, is(expectedSellin));
    }


    //Changing Names
    @Test
    @Parameters({"-1,-2",
            "0,-1",
            "1,0",
            "2,1",
            "3,2"})
    public void
    if_aged_Brie_is_provided_and_quality_is_100_decrases_sellin_by_one(int intitalSellin, int expectedSellin) {
        Item item = new Item(AGED_BRIE, intitalSellin, 100);
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
    if_aged_brie_is_provided_and_sellin_is_0_increase_quality_by_two(int intitalQuality, int expectedQuality) {
        Item item = new Item(AGED_BRIE, 0, intitalQuality);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].quality, is(expectedQuality));
    }

    @Test
    @Parameters({"25-,25",
                 "19,--20"})
    public void
    x(int intitalQuality, int expectedQuality) {
        Item item = new Item(AGED_BRIE, 100, intitalQuality);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].quality, is(expectedQuality));
    }
    @Test
    @Parameters({"-1,0",
            "0,1",
            "1,2",
            "2,3",
            "3,4"})
    public void
    if_aged_brie_is_provided_and_sellin_is_1_increase_quality_by_one(int intitalQuality, int expectedQuality) {
        Item item = new Item(AGED_BRIE, 1, intitalQuality);
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
    if_sulfura_is_provided_quality_remains_as_it_is(int intitalQuality, int expectedQuality) {
        Item item = new Item(SULFURAS_HAND_OF_RAGNAROS, 0, intitalQuality);
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
    if_sulfuras_is_provided_sellin_remains_as_it_is_(int intitalSellin, int expectedSellin) {
        Item item = new Item(SULFURAS_HAND_OF_RAGNAROS, intitalSellin, 100);
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
    if_backstage_is_provided_quality_goes_to_zero(int intitalQuality, int expectedQuality) {
        Item item = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT, 0, intitalQuality);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].quality, is(expectedQuality));
    }
}