
import java.util.Objects;

public class Town implements Comparable<Town> {
    private String name;

    /**
     * Constructor. Initializes a new Town instance with the specified name.
     *
     * @param name The name of the town.
     */
    public Town(String name) {
        this.name = name;
    }

    /**
     * Copy constructor. Initializes a new Town instance with the details from the provided Town instance.
     *
     * @param templateTown The Town instance from which to copy details.
     */
    public Town(Town templateTown) {
        this.name = templateTown.name;
    }

    /**
     * Returns the name of the town.
     *
     * @return The name of the town.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the town.
     *
     * @param name The name to set for the town.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Compares this town to another Town object for order.
     *
     * @param other The Town to be compared.
     * @return A negative integer, zero, or a positive integer as this town is less than, equal to, or greater than the specified town.
     */
    @Override
    public int compareTo(Town other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Generates a hash code for this town.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Compares this town to the specified object for equality.
     *
     * @param obj The object to compare this town against.
     * @return true if the given object represents a Town equivalent to this town, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Town town = (Town) obj;
        return Objects.equals(name, town.name);
    }

    /**
     * Returns a string representation of the town.
     *
     * @return A string representation of the town.
     */
    @Override
    public String toString() {
        return "Town{" +
               "name='" + name + '\'' +
               '}';
    }
}