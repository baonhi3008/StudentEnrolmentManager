import java.util.Objects;

public class Course {
    private String id;
    private String name;
    private Integer credits;

    /**
     * The constructor for the course class to construct course for further usage .
     * @param id - the id of the course
     * @param name - the course's name
     * @param credits - number of credit of that course
     */
    public Course(String id, String name, Integer credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    /**
     * Getter to take id property for further usage if necessary.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter to change property value
     * @param id - set the id for obj course if necessary
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter to get name propert in course class
     * @return name of the course
     */
    public String getName() {
        return name;
    }

    /**
     * Setter to set name for the property's value if necessary
     * @param name - of the course
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return credit for specific course
     */
    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }


    /** Ref: https://www.educative.io/edpresso/how-to-use-the-tostring-in-java
     * Built-in method to get String value of class's properties
     * @return the value given to it in string format.
     */
    @Override
    public String toString() {
        return this.id + ',' + this.name + ',' + this.credits.toString();
    }


    /** Ref: https://www.javatpoint.com/java-string-equals
     * Built-in method compares the two given strings based on the content of the string
     * @return true if all characters match with other objects
     * @return false if any character matches.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id) && name.equals(course.name) && credits.equals(course.credits);
    }


    /** ref: https://www.journaldev.com/21095/java-equals-hashcode#:~:text=to%20same%20object.-,Java%20hashCode(),in%20the%20equals()%20method.
     * Native method
     * @return the integer hash code value of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, credits);
    }
}
