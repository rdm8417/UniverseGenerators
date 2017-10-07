package com.space.universe

/**
 * Created by rdm8417 on 7/5/14.
 */
class UniverseGenerator {

    Random generator

    public UniverseGenerator()
    {
        generator = new Random()

        // Stabilize generator
        for (int i in 1..500) generator.nextInt()
    }

    public randomInRange(int min, int max)
    {
        generator.nextInt((max-min)+1) + min
    }

    private List<Coordinate> generateNodes(int numSpheres, int sphereDepth, int sphereDensity)
    {
        List<Coordinate> result = []

        for (int sphere in 0..numSpheres-1)
        {
            // Minimum coordinate value
            int min = sphere * sphereDepth

            // Maximum coordinate value
            int max = (sphere + 1) * sphereDepth - 1
            //println "\nmin = ${min}, max = ${max}"

            for (int coordinateIndex in 1..sphereDensity)
            {
                int[] values = new int[3]

                // First value:  in [min, max] range to fall with limits of current sphere, i.e. 10-19 or 40-49
                values[0] = randomInRange(min, max)

                // Second value:  Randomly generate in the range acceptable to prevent distance from origin from
                // exceeding max coordinate value, accounting for previously picked first value
                values[1] = randomInRange(0, (int)Math.sqrt(Math.max(1, max*max - values[0]*values[0])))

                // Third value:  Generate same as second value, maintaining limit on distance <= max
                values[2] = randomInRange(0, (int)Math.sqrt(Math.max(1, max*max-values[0]*values[0]-values[1]*values[1])))

                // This method results in larger numbers for the first value, smaller for the second value, and even
                // smaller for the third.  To prevent clustering along the x-axis, assign the first value to x, y, and
                // z in order as new coordinates are generated.  To avoid all coordinates landing in the positive x, y,
                // z section of the universe (with respect to the origin), randomly flip some coordinates negative
                int x = coordinateIndex % 2 == 0 ? values[0] :
                        (coordinateIndex % 3 == 0 ? values[1] :
                                values[2])  * (generator.nextBoolean() ? 1 : -1)

                int y = coordinateIndex % 2 == 0 ? values[1] :
                        (coordinateIndex % 3 == 0 ? values[2] :
                                values[1])  * (generator.nextBoolean() ? 1 : -1)

                int z = coordinateIndex % 2 == 0 ? values[2] :
                        (coordinateIndex % 3 == 0 ? values[0] :
                                values[0])  * (generator.nextBoolean() ? 1 : -1)

                int distance = (int)Math.sqrt(x*x+y*y+z*z)
                //println "${x}, ${y}, ${z} = ${distance}"

                result << new Coordinate(x, y, z)
            }
        }

        return result
    }

    Node connectGraph(List<Coordinate> coordinateList, int distanceThreshold)
    {
        Node result = new Node();

        for (coordinate in coordinateList)
        {
            for (coordinate2 in coordinateList)
            {
                println coordinate.distanceTo(coordinate2)
            }
            break
        }

        return result
    }
    public static void main(String[] args)
    {
        UniverseGenerator generator = new UniverseGenerator()
        List<Coordinate> coordinateList = generator.generateNodes(10, 10, 10)
        generator.connectGraph(coordinateList)

    }
}
