import java.util.Objects;


public class Student {
    private String id;
    private String name;
    private String birthday;

    /**
     * Constructor for student
     * @param id student id
     * @param name student name
     * @param birthday of birthday
     */
    public Student(String id, String name, String birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    /**
     * Getter to get id of student
     * @return string id of student
     */
    public String getId() {
        return id;
    }

    /**
     * Setter to set new value of student id
     * @param id with value
     */
    public void setId(String id) {
        if (!isValidID(id)) throw new IllegalArgumentException("Invalid ID Number.");
        this.id = id;
    }

    /**
     * Getter to get student name
     * @return string of student name set.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter to set value for student name
     * @param name string of student name
     */
    public void setName(String name) {
        this.name = name;
    }



    /**
     * Getter to get string student birthday
     * @return string of student birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Setter to set value for student birthday
     * @return string student birthday
     */

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public boolean isValidID(String studentID) throws IllegalArgumentException{
        return (studentID.matches("s[0-9]{7}"));
    }

    /** Ref: https://www.educative.io/edpresso/how-to-use-the-tostring-in-java
     * Built-in method to get String value of class's properties
     * @return the value given to it in string format.
     */
    @Override

    public String toString() {
        return this.id + ',' + this.name + ',' + this.birthday;
    }

    /** Ref: https://www.javatpoint.com/java-string-equals
     * Built-in method compares the two given strings based on the content of the string
     * @return true if all characters match with other objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id) && name.equals(student.name) && birthday.equals(student.birthday);
    }

    /** ref: https://www.journaldev.com/21095/java-equals-hashcode#:~:text=to%20same%20object.-,Java%20hashCode(),in%20the%20equals()%20method.
     * Native method
     * @return the integer hash code value of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthday);
    }
}
