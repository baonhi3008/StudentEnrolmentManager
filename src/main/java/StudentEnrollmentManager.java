import java.util.List;

public interface StudentEnrollmentManager {
    boolean add(StudentEnrollment studentEnrollment);
    boolean update(int index, StudentEnrollment studentEnrollment) throws Exception;
    boolean delete(int index) throws Exception;
    StudentEnrollment getOne(int index) throws Exception;
    List<StudentEnrollment> getAll();
}
// interface class for StudentEnrollment List
