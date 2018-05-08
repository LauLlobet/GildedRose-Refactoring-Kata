package com.gildedrose;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void
    have_empty_items_after_update_quality_when_empty_items_are_provided() {
        Item[] items = new Item[]{};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items, is(items));
    }

    @Test
    public void
    leave_quality_at_0_if_item_with_0_quality_is_provided() {
        Item item = new Item("", 0, 0);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].quality, is(0));
    }

    private GildedRose getGildedRoseWithSingleItem(Item item) {
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app;
    }

    // Quality = N , rest 0

    @Test
    public void
    BEHAVIOURdecreases_quality_to_0_when_1_quality_is_provided() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", 0, 1));
        assertThat(app.items[0].quality, is(0));
    }

    @Test
    public void
    BEHAVIOURdecreases_quality_to_0_when_2_quality_is_provided_and_0_sellin() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", 0, 2));
        assertThat(app.items[0].quality, is(0));
    }

    @Test
    public void
    BEHAVIOUR_decreases_quality_to_1_when_have_intial_3_and_sellin_0() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", 0, 3));
        assertThat(app.items[0].quality, is(1));
    }

    @Test
    public void
    BEHAVIOUR_decrease_quality_to_2_when_inital_quality_is_2() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", 0, 4));
        assertThat(app.items[0].quality, is(2));
    }

    // Sellin != 0, rest 0


    @Test
    public void
    BEHAVIOUR_decrease_sellin_by_one_when_0_sellin_and_0_quality_is_provided() {
        Item item = new Item("", 0, 0);
        GildedRose app = getGildedRoseWithSingleItem(item);
        assertThat(app.items[0].sellIn, is(-1));
    }

    @Test
    public void
    BEHAVIOUR_decreases_sellin_to_0_when_have_intital_sellin_is_1_and_quality_2() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("", 1, 2));
        assertThat(app.items[0].sellIn, is(0));
    }





    //Changeing Names

    /*@Test
    public void
    if_aged_brie_and_quality_2_its_leaved_as_it_is() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("Aged Brie", 1, 2));
        assertThat(app.items[0].quality, is(2));
    }

    @Test
    public void
    if_aged_brie_and_quality_0_aged_brie_leaved_as_it_is() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("Aged Brie", 1, 0));
        assertThat(app.items[0].quality, is(0));
    }

    @Test
    public void
    if_aged_brie_and_quality_0_remains_aged_brie() {
        GildedRose app = getGildedRoseWithSingleItem(new Item("Aged Brie", 1, 0));
        assertThat(app.items[0].name, is("Aged Brie"));
    }*/
}