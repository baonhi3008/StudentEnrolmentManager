import java.util.HashSet;
import java.util.Objects;

public class StudentEnrollment {
    private Student student;
    private Course course;
    private String semester;


    /**
     * Constructor of Student Enrolment
     * @param student Student obj
     * @param course Course onj
     * @param semester string of semester
     */
    public StudentEnrollment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    /**
     * Getter to take the student obj - property from the Student class
     * @return Student object in the Student Enrollment
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Setter to set value for student obj inside the obj student enrollment
     * @param student property value.
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Getter to take the course obj for further usage
     * @return course obj in the Student Enrollment
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Setter for the course obj
     * @param course property value
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Getter to get value of semester in the Student Enrollment
     * @return string semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Setter to set value of semester in Student Enrollment
     * @param semester value
     */
    public void setSemester(String semester) {
        if (!isSemester(semester)) throw new IllegalArgumentException("Invalid ID Number.");
        this.semester = semester;
    }
    private boolean isSemester(String semester){
        return (semester.matches("[0-9]{4}[A-B-C]"));
    }


    /** Ref: https://www.educative.io/edpresso/how-to-use-the-tostring-in-java
     * Built-in method to get String value of class's properties
     * @return the value given to it in string format.
     */
    @Override
    public String toString() {
        return student.toString() + ',' + course.toString() + ',' + semester;
    }


    /** Ref: https://www.javatpoint.com/java-string-equals
     * Built-in method compares the two given strings based on the content of the string
     * @return true if all characters match with other objects
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEnrollment that = (StudentEnrollment) o;
        return student.equals(that.student) && course.equals(that.course) && semester.equals(that.semester);
    }


    /** ref: https://www.journaldev.com/21095/java-equals-hashcode#:~:text=to%20same%20object.-,Java%20hashCode(),in%20the%20equals()%20method.
     * Native method
     * @return the integer hash code value of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(student, course, semester);
    }
}
