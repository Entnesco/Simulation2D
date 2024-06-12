package org.entnes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class test {

    @Test
    void addFoodTestAdd5() {
        final int PANEL_WIDTH = 800;
        final int PANEL_HEIGHT = 800;
        int foodQuantity = 20;
        int maxFoodQuantity = 25;
        int foodWidth = 10;
        int foodHeight = 10;
        Food food = new Food(PANEL_WIDTH, PANEL_HEIGHT, foodQuantity, foodWidth, foodHeight);
        food.addFood(5,maxFoodQuantity);
        assertEquals(25, food.getFoodQuantity());
    }

    @Test
    void addFoodTestAdd5WithLimit() {
        final int PANEL_WIDTH = 800;
        final int PANEL_HEIGHT = 800;
        int foodQuantity = 20;
        int maxFoodQuantity = 20;
        int foodWidth = 10;
        int foodHeight = 10;
        Food food = new Food(PANEL_WIDTH, PANEL_HEIGHT, foodQuantity, foodWidth, foodHeight);
        food.addFood(5,maxFoodQuantity);
        assertEquals(20, food.getFoodQuantity());
    }

    @Test
    void addFoodTestAdd10WithPartialLimit() {
        final int PANEL_WIDTH = 800;
        final int PANEL_HEIGHT = 800;
        int foodQuantity = 20;
        int maxFoodQuantity = 25;
        int foodWidth = 10;
        int foodHeight = 10;
        Food food = new Food(PANEL_WIDTH, PANEL_HEIGHT, foodQuantity, foodWidth, foodHeight);
        food.addFood(10,maxFoodQuantity);
        assertEquals(25, food.getFoodQuantity());
    }

    @Test
    void deleteFoodTestDeleteFoodWithId5() {
        final int PANEL_WIDTH = 800;
        final int PANEL_HEIGHT = 800;
        int foodQuantity = 20;
        int foodWidth = 10;
        int foodHeight = 10;
        Food food = new Food(PANEL_WIDTH, PANEL_HEIGHT, foodQuantity, foodWidth, foodHeight);
        food.deleteFood(5);
        assertEquals(19, food.getFoodQuantity());
    }

    @Test
    void deleteFoodTestDeleteFoodWithNotExistingId() {
        final int PANEL_WIDTH = 800;
        final int PANEL_HEIGHT = 800;
        int foodQuantity = 20;
        int foodWidth = 10;
        int foodHeight = 10;
        Food food = new Food(PANEL_WIDTH, PANEL_HEIGHT, foodQuantity, foodWidth, foodHeight);
        food.deleteFood(25);
        assertEquals(20, food.getFoodQuantity());
    }

    @Test
    void deleteSomeFoodTestDelete5Foods() {
        final int PANEL_WIDTH = 800;
        final int PANEL_HEIGHT = 800;
        int foodQuantity = 20;
        int foodWidth = 10;
        int foodHeight = 10;
        Food food = new Food(PANEL_WIDTH, PANEL_HEIGHT, foodQuantity, foodWidth, foodHeight);
        food.deleteSomeFood(5);
        assertEquals(15, food.getFoodQuantity());
    }

    @Test
    void deleteSomeFoodTestDelete25FoodsWhile20FoodsExists() {
        final int PANEL_WIDTH = 800;
        final int PANEL_HEIGHT = 800;
        int foodQuantity = 20;
        int foodWidth = 10;
        int foodHeight = 10;
        Food food = new Food(PANEL_WIDTH, PANEL_HEIGHT, foodQuantity, foodWidth, foodHeight);
        food.deleteSomeFood(25);
        assertEquals(0, food.getFoodQuantity());
    }

    @Test
    void collisionCheckTestSameCords() {
        final int PANEL_WIDTH = 800;
        final int PANEL_HEIGHT = 800;
        int creatureWidth = 10;
        int creatureHeight = 10;
        int foodWidth = 10;
        int foodHeight = 10;
        Creature creature = new Creature(PANEL_WIDTH, PANEL_HEIGHT, creatureWidth, creatureHeight);
        creature.setX(10);
        creature.setY(10);

        ArrayList<Integer> xFoodCords = new ArrayList<Integer>();
        ArrayList<Integer> yFoodCords = new ArrayList<Integer>();
        xFoodCords.add(10);
        yFoodCords.add(10);


        assertEquals(0, creature.collisionCheck(xFoodCords, yFoodCords, foodWidth, foodHeight));
    }

    @Test
    void collisionCheckTestNoCollision() {
        final int PANEL_WIDTH = 800;
        final int PANEL_HEIGHT = 800;
        int creatureWidth = 10;
        int creatureHeight = 10;
        int foodWidth = 10;
        int foodHeight = 10;
        Creature creature = new Creature(PANEL_WIDTH, PANEL_HEIGHT, creatureWidth, creatureHeight);
        creature.setX(10);
        creature.setY(50);

        ArrayList<Integer> xFoodCords = new ArrayList<Integer>();
        ArrayList<Integer> yFoodCords = new ArrayList<Integer>();
        xFoodCords.add(10);
        yFoodCords.add(10);


        assertEquals(-1, creature.collisionCheck(xFoodCords, yFoodCords, foodWidth, foodHeight));
    }

    @Test
    void searchForFoodTest2Food() {
        final int PANEL_WIDTH = 800;
        final int PANEL_HEIGHT = 800;
        int creatureWidth = 10;
        int creatureHeight = 10;
        Creature creature = new Creature(PANEL_WIDTH, PANEL_HEIGHT, creatureWidth, creatureHeight);
        creature.setX(30);
        creature.setY(10);

        ArrayList<Integer> xFoodCords = new ArrayList<Integer>();
        ArrayList<Integer> yFoodCords = new ArrayList<Integer>();
        xFoodCords.add(10);
        yFoodCords.add(10);
        xFoodCords.add(100);
        yFoodCords.add(100);

        assertEquals(10, creature.searchForFood(xFoodCords, yFoodCords).get(0));
        assertEquals(10, creature.searchForFood(xFoodCords, yFoodCords).get(1));
        assertEquals(-20, creature.searchForFood(xFoodCords, yFoodCords).get(2));
        assertEquals(0, creature.searchForFood(xFoodCords, yFoodCords).get(3));
        assertEquals(20, creature.searchForFood(xFoodCords, yFoodCords).get(4));
        assertEquals(180, creature.searchForFood(xFoodCords, yFoodCords).get(5));
    }

}
