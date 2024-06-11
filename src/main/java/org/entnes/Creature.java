package org.entnes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Creature {
	
	private final int PANEL_WIDTH;
	private final int PANEL_HEIGHT;
    private int creatureWidth;
	private int creatureHeight;
    private double x = 0 ,y = 0;
    private double  xVelocity = 0;
    private double  yVelocity = 0;
    private double velocity = 0;
    private Double angle = 0.0;
    private int foodConsumed = 0;
	
	Creature(int PANEL_WIDTH, int PANEL_HEIGHT, int creatureWidth, int creatureHeight) {
		this.creatureWidth = creatureWidth;
		this.creatureHeight = creatureHeight;
		this.PANEL_WIDTH = PANEL_WIDTH;
		this.PANEL_HEIGHT = PANEL_HEIGHT;
	}
	
//    public void moveCreatureFollowNearestFood(double  velocityInit, double angleInit) {
//    	if(velocity == 0) velocity = velocityInit;
//    	angle = angleInit;
//
//
//        if(angle >= 360) angle = angle - 360;
//        if(angle <= -360) angle = angle + 360;
//
//    	xVelocity = Math.round(Math.cos(Math.toRadians(angle))*velocity*1000)/1000d;
//    	yVelocity = Math.round(Math.sin(Math.toRadians(angle))*velocity*1000)/1000d;
//
//        x = x + xVelocity;
//        y = y + yVelocity;
//
//		if(x > PANEL_WIDTH-creatureWidth || x < 0){
//			x = x - xVelocity;
//		}
//
//		if(y > PANEL_HEIGHT-creatureHeight || y < 0){
//			y = y - yVelocity;
//		}

		public void moveCreatureFollowNearestFood(double  velocityInit, ArrayList<Integer> xFoodCords, ArrayList<Integer> yFoodCords) {
			if(velocity == 0) velocity = velocityInit;

			ArrayList<Double> nearestFood = new ArrayList<>();

			if(xFoodCords.size() > 1) {
				double xFood = xFoodCords.get(0);
				double yFood = yFoodCords.get(0);
				double xDist = xFood-x;
				double yDist = yFood-y;
				double distCreatureFood = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
				angle = Math.toDegrees(Math.acos(xDist/distCreatureFood));
				if(yDist<0) angle = -angle;

				nearestFood.clear();
				nearestFood.add(xFood);
				nearestFood.add(yFood);
				nearestFood.add(xDist);
				nearestFood.add(yDist);
				nearestFood.add(distCreatureFood);
				nearestFood.add(angle);

				for(int id = 1; id < xFoodCords.size(); id++) {
					xFood = xFoodCords.get(id);
					yFood = yFoodCords.get(id);
					xDist = xFood-x;
					yDist = yFood-y;
					distCreatureFood = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
					angle = Math.toDegrees(Math.acos(xDist/distCreatureFood));
					if(yDist<0) angle = -angle;

					if(Math.abs(distCreatureFood) < Math.abs(nearestFood.get(4))) {
						nearestFood.set(0, xFood);
						nearestFood.set(1, yFood);
						nearestFood.set(2, xDist);
						nearestFood.set(3, yDist);
						nearestFood.set(4, distCreatureFood);
						nearestFood.set(5, angle);
					}
				}

			}
			else if(xFoodCords.size() == 1){
				double xFood = xFoodCords.get(0);
				double yFood = yFoodCords.get(0);
				double xDist = xFood-x;
				double yDist = yFood-y;
				double distCreatureFood = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
				angle = Math.toDegrees(Math.acos(xDist/distCreatureFood));
				if(yDist<0) angle = -angle;


				nearestFood.clear();
				nearestFood.add(xFood);
				nearestFood.add(yFood);
				nearestFood.add(xDist);
				nearestFood.add(yDist);
				nearestFood.add(distCreatureFood);
				nearestFood.add(angle);
			}

			if(!nearestFood.isEmpty()) angle = nearestFood.get(5);

			if(angle >= 360) angle = angle - 360;
			if(angle <= -360) angle = angle + 360;

			xVelocity = Math.round(Math.cos(Math.toRadians(angle))*velocity*1000)/1000d;
			yVelocity = Math.round(Math.sin(Math.toRadians(angle))*velocity*1000)/1000d;

			if(!nearestFood.isEmpty()) {
				x = x + xVelocity;
				y = y + yVelocity;
			}

			if(x > PANEL_WIDTH-creatureWidth || x < 0){
				x = x - xVelocity;
				y = y - yVelocity;
			}

			if(y > PANEL_HEIGHT-creatureHeight || y < 0){
				x = x - xVelocity;
				y = y - yVelocity;
			}
    }

	public void moveCreatureSimpleBounce(double  velocityInit, double angleInit) {
		if(velocity == 0) velocity = velocityInit;
		if(angle == 0) angle = angleInit;

        if(x > PANEL_WIDTH-creatureWidth || x < 0){
            angle = 180-angle;
        }

        if(y > PANEL_HEIGHT-creatureHeight || y < 0){
        	angle = -angle;
        }

		xVelocity = Math.round(Math.cos(Math.toRadians(angle))*velocity*1000)/1000d;
		yVelocity = Math.round(Math.sin(Math.toRadians(angle))*velocity*1000)/1000d;

		if(angle >= 360) angle = angle - 360;
		if(angle <= -360) angle = angle + 360;

		x = x + xVelocity;
		y = y + yVelocity;
	}
    
    public void drawCreature(Graphics2D g2D) {
        g2D.setPaint(Color.gray);
        g2D.fillRect((int)x, (int)y, creatureWidth, creatureHeight);
    }
    
    public int collisionCheck(ArrayList<Integer> xFoodCords, ArrayList<Integer> yFoodCords, int foodWidth, int foodHeight) {
    	for(int id = 0; id < xFoodCords.size(); id++) {
    		if(!((xFoodCords.get(id) + foodWidth < x) || (xFoodCords.get(id) > x + creatureWidth))) {
        		if(!((yFoodCords.get(id) + foodHeight < y) || (yFoodCords.get(id) > y + creatureHeight))) {
//        			creatureWidth++;
//        			creatureHeight++;
//        			if(creatureWidth >= PANEL_WIDTH) creatureWidth = PANEL_WIDTH;
//        			if(creatureHeight >= PANEL_HEIGHT) creatureHeight = PANEL_HEIGHT;
        			foodConsumed++;
//        			System.out.println("Food consumed: " + foodConsumed);
        			return id;
        		}
    		}
    	}
    	return -1;
    }
    
    public ArrayList<Double> searchForFood(ArrayList<Integer> xFoodCords, ArrayList<Integer> yFoodCords)
    {
		ArrayList<Double> nerestFood = new ArrayList<>();

    	if(xFoodCords.size() > 1) {
	    	double xFood = xFoodCords.get(0);
	    	double yFood = yFoodCords.get(0);
	    	double xDist = xFood-x;
	    	double yDist = yFood-y;
	    	double distCreatureFood = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
	    	angle = Math.toDegrees(Math.acos(xDist/distCreatureFood));
			if(yDist<0) angle = -angle;
	    	
	    	nerestFood.clear();
	    	nerestFood.add(xFood);
	    	nerestFood.add(yFood);
	    	nerestFood.add(xDist);
	    	nerestFood.add(yDist);
	    	nerestFood.add(distCreatureFood);
	    	nerestFood.add(angle);
	    	
	    	for(int id = 1; id < xFoodCords.size(); id++) {
		    	xFood = xFoodCords.get(id);
		    	yFood = yFoodCords.get(id);
		    	xDist = xFood-x;
		    	yDist = yFood-y;
		    	distCreatureFood = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
				angle = Math.toDegrees(Math.acos(xDist/distCreatureFood));
				if(yDist<0) angle = -angle;
		    	
		    	if(Math.abs(distCreatureFood) < Math.abs(nerestFood.get(4))) {
			    	nerestFood.set(0, xFood);
			    	nerestFood.set(1, yFood);
			    	nerestFood.set(2, xDist);
			    	nerestFood.set(3, yDist);
			    	nerestFood.set(4, distCreatureFood);
			    	nerestFood.set(5, angle);
		    	}
	    	}
	    	
	    	return nerestFood;
	    	
    	}    	
    	else if(xFoodCords.size() == 1){
	    	double xFood = xFoodCords.get(0);
	    	double yFood = yFoodCords.get(0);
	    	double xDist = xFood-x;
	    	double yDist = yFood-y;
	    	double distCreatureFood = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
			angle = Math.toDegrees(Math.acos(xDist/distCreatureFood));
			if(yDist<0) angle = -angle;


			nerestFood.clear();
			nerestFood.add(xFood);
			nerestFood.add(yFood);
			nerestFood.add(xDist);
			nerestFood.add(yDist);
			nerestFood.add(distCreatureFood);
			nerestFood.add(angle);
	    	
	    	return nerestFood;
    	}

		return nerestFood;
    }
}
