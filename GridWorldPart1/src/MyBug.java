/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 */


import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * A <code>Bug</code> is an actor that can move and turn. It drops flowers as
 * it moves. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MyBug extends Actor
{

	private Color colorBug;
	private boolean isAngry;
	private Color prevColor;
	private boolean hasRock;

	/**
	 * Constructs a red bug.
	 */
	public MyBug()
	{
		setColor(Color.RED);
		colorBug = (Color.RED);
		isAngry = false;
		prevColor = Color.RED;
		hasRock = true;
	}

	/**
	 * Constructs a bug of a given color.
	 * @param bugColor the color for this bug
	 */
	public MyBug(Color bugColor)
	{
		setColor(bugColor);
		colorBug = bugColor;
		isAngry = false;
		prevColor = bugColor;
		hasRock = true;
	}

	/**
	 * Moves if it can move, turns otherwise.
	 */

	//Color c = new Color (49, 2, 130);
	Color c = Color.MAGENTA.darker();
	
	
	public void act()
	{
		if (getColor() != c) {
			prevColor = getColor();
		}

		if (canMove()) {
			move();

			if (isAngry) {
				setColor(prevColor);
			} else {

			}
			isAngry = false;

		} else {
			if (hasRock) {
				isAngry = true;
				setColor(c);
				turn();
				turn();
				turn();
			} else {
				isAngry = true;
				setColor(c);
				turn();
			}
		}
		    	
	}
	/**
	 * Turns the bug 45 degrees to the right without changing its location.
	 */
	public void turn()
	{
		setDirection(getDirection() + Location.HALF_RIGHT);
	}

	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move()
	{
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		if (gr.isValid(next))
			moveTo(next);
		else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * @return true if this bug can move.
	 */
	public boolean canMove()
	{
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			setColor(c);
			return false;
		}
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		if (!gr.isValid(next)) {
			hasRock = true;
			return false;
		} 
		Actor neighbor = gr.get(next);
		return (neighbor == null) || (neighbor instanceof Flower);
		// ok to move into empty location or onto flower
		// not ok to move onto any other actor
	}

	//    public void hop () {
	//        Grid<Actor> gr = getGrid();
	//        Location loc = getLocation();
	//        
	//    		putSelfInGrid(gr, loc);
	//    		
	//    }
}
