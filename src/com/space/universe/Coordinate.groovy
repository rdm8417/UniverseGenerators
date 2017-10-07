package com.space.universe

/**
 * Created by rdm8417 on 7/5/14.
 */
class Coordinate {
    int x
    int y
    int z

    public Coordinate(int x, int y, int z)
    {
        this.x = x
        this.y = y
        this.z = z
    }

    public double distanceTo(Coordinate destination)
    {
        Math.sqrt(Math.pow(destination.getX() - getX(), 2) +
            Math.pow(destination.getY() - getY(), 2) +
            Math.pow(destination.getZ() - getZ(), 2))
    }

    public String toString()
    {
        "[${x}, ${y}, ${z}]"
    }
}
