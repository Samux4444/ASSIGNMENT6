
import java.util.Objects;

public class Road implements Comparable<Road> {
    private Town source;
    private Town destination;
    private int distance;
    private String name;

    /**
     * Constructor. Initializes a new Road instance.
     *
     * @param source      The source town for this road.
     * @param destination The destination town for this road.
     * @param distance    The distance of the road.
     * @param name        The name of the road.
     */
    public Road(Town source, Town destination, int distance, String name) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.name = name;
    }

    /**
     * Gets the road name.
     *
     * @return The name of the road.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the distance of the road.
     *
     * @return The distance of the road.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Gets the source town for this road.
     *
     * @return The source town.
     */
    public Town getSource() {
        return source;
    }

    /**
     * Gets the destination town for this road.
     *
     * @return The destination town.
     */
    public Town getDestination() {
        return destination;
    }

    /**
     * Compares this road with another Road object for order.
     *
     * @param other The Road to be compared.
     * @return A negative integer, zero, or a positive integer as this road is less than, equal to, or greater than the specified road.
     */
    @Override
    public int compareTo(Road other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Generates a hash code for this road.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, source, destination, distance);
    }

    /**
     * Compares this road to the specified object for equality.
     *
     * @param obj The object to compare this road against.
     * @return true if the given object represents a Road equivalent to this road, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Road road = (Road) obj;
        boolean sameTowns = (Objects.equals(source, road.source) && Objects.equals(destination, road.destination)) ||
                            (Objects.equals(source, road.destination) && Objects.equals(destination, road.source));
        return distance == road.distance && sameTowns && Objects.equals(name, road.name);
    }

    /**
     * Returns a string representation of the road.
     *
     * @return A string representation of the road.
     */
    @Override
    public String toString() {
        return "Road{" +
               "name='" + name + '\'' +
               ", source=" + source +
               ", destination=" + destination +
               ", distance=" + distance +
               '}';
    }
}
