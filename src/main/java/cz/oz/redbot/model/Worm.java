package cz.oz.redbot.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ondrej Zizka
 */
public class Worm {
    
    private int id;

    private Coords head;
    private Coords tail;
    private int frozen;
    private int points;
    private int direction;
       
    private final List<Coords> elements = new LinkedList<Coords>();

    
    public Worm(int id, Coords head, Coords tail, int frozen, int points) {
        this.id = id;
        this.head = head;
        this.tail = tail;
        this.frozen = frozen;
        this.points = points;
    }

    public Coords getHead() {        return head;    }
    public void setHead(Coords head) {        this.head = head;    }
    
    public Coords getTail() {        return tail;    }
    public void setTail(Coords tail) {        this.tail = tail;    }

    public int getPoints() {        return points;    }
    public void setPoints(int points) {        this.points = points;    }
    
    public int getFrozen() {        return frozen;    }
    public void setFrozen(int frozen) {        this.frozen = frozen;    }

    
    public List<Coords> getElements() {        return elements;    }

    public int getDirection() {        return direction;    }
    public void setDirection(int direction) {        this.direction = direction;    }

    
    
    
}// class

