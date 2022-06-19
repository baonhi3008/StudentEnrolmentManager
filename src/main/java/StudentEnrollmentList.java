import java.util.ArrayList;
import java.util.List;

public class StudentEnrollmentList implements StudentEnrollmentManager {
    private List<StudentEnrollment> enrollments = new ArrayList<>();

    @Override

    /** This method uses to add a Student Enrollment into enrollments - enrollments list
     * @param - obj student enrolment that needed to add to the list of enrollments
     * @return true if student enrolment is successfully added to the enrollment list
     * @return false if student enrolment is duplicated ( cannot be added)
     */
    public boolean add(StudentEnrollment studentEnrollment) {
        for (StudentEnrollment enrollment:this.enrollments) {
            if (enrollment.equals(studentEnrollment)) return false;
        }
        this.enrollments.add(studentEnrollment);
        return true;
    }

    @Override

    /** This method uses to update a student enrollment in the enrollments list.
     * @param- index of to-be-updated enrollment
     * @return true if student enrollment is successfully executed
     */
    public boolean update(int index, StudentEnrollment studentEnrollment) throws Exception {
        StudentEnrollment oldEnrollment = this.enrollments.get(index);
        if (oldEnrollment == null) throw new Exception("Enrollment index is invalid.");
        oldEnrollment.setStudent(studentEnrollment.getStudent());
        oldEnrollment.setCourse(studentEnrollment.getCourse());
        oldEnrollment.setSemester(studentEnrollment.getSemester());
        return true;
    }

    @Override
    /** This method will implemented to delete particular enrollment
     * @param index of to-be-deleted student enrollment
     * @return true if enrolment is removed from a list
     */
    public boolean delete(int index) throws Exception {
        StudentEnrollment enrollment = this.enrollments.get(index);
        if (enrollment == null) throw new Exception("Enrollment index is invalid.");
        this.enrollments.remove(index);
        return true;
    }

    @Override
    /** The method get one enrollment from the student enrollments list
     * @param index of needed enrollment
     * @return enrollment object with specific index
     */
    public StudentEnrollment getOne(int index) throws Exception {
        StudentEnrollment enrollment = this.enrollments.get(index);
        if (enrollment == null) throw new Exception("Enrollment index is invalid.");
        return enrollment;
    }

    @Override
    /** This method get all enrollments available in file
     * @return all values in the student enrollments list
     */
    public List<StudentEnrollment> getAll() {
        return this.enrollments;
    }
}
