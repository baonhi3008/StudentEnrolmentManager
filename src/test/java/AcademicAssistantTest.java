import org.junit.Test;
import org.junit.Assert;
import java.util.List;

public class AcademicAssistantTest {
    @Test(expected = Exception.class)
    public void testLoadEnrollments() throws Exception {
        AcademicAssistant aa = new AcademicAssistant();
        aa.loadEnrollments("filekhongtai");
    }
    @Test
    public void testGetAllEnrollments() throws Exception {
        AcademicAssistant aa = new AcademicAssistant();
        aa.loadEnrollments("default.csv");
        List<StudentEnrollment> enrollments = aa.getAllEnrollments();
        Assert.assertEquals(15, enrollments.size());
    }
    @Test
    public void testAddEnrollmentSemester() throws Exception {

        AcademicAssistant aa = new AcademicAssistant();
        aa.loadEnrollments("default.csv");
        aa.addEnrollment(1,1,"2021B");
        List<StudentEnrollment> enrollments =aa.getAllEnrollments();
        StudentEnrollment addedEnrollment = enrollments.get(enrollments.size() - 1);
        Assert.assertEquals("2021B", addedEnrollment.getSemester());
    }
    @Test
    public void testAddEnrollmentsStudent() throws Exception {
        AcademicAssistant aa = new AcademicAssistant();
        aa.loadEnrollments("default.csv");
        aa.addEnrollment(1,1,"2020B");
        List<StudentEnrollment> enrollments = aa.getAllEnrollments();
        Student addedStudent = aa.getStudents().get(1);
        Assert.assertEquals(addedStudent,enrollments.get(enrollments.size()-1).getStudent());
    }
    @Test
    public void testAddEnrollmentCourse()throws Exception{
        AcademicAssistant aa = new AcademicAssistant();
        aa.loadEnrollments("default.csv");
        aa.addEnrollment(1,1,"2021A");
        List<StudentEnrollment> enrollments = aa.getAllEnrollments();
        Course addedCourse = aa.getCourses().get(1);
        Assert.assertEquals(addedCourse,enrollments.get(enrollments.size()-1).getCourse());
    }
    @Test
    public void testDeleteEnrollments() throws Exception {
        AcademicAssistant aa = new AcademicAssistant();
        aa.loadEnrollments("default.csv");
        aa.deleteEnrollment(1);
        List<StudentEnrollment> enrollments = aa.getAllEnrollments();
        Assert.assertEquals(14,enrollments.size());

    }
    @Test(expected = Exception.class)
    public void testDeleteEnrollmentsWrongIndex() throws Exception{
        AcademicAssistant aa = new AcademicAssistant();
        aa.loadEnrollments("default.csv");
        aa.deleteEnrollment(16);

    }
    @Test
    public void testUpdateEnrollmentSemester() throws Exception {
        AcademicAssistant aa = new AcademicAssistant();
        aa.loadEnrollments("default.csv");
        aa.updateEnrollment(0,0,0,"2021A");
        List<StudentEnrollment> enrollments = aa.getAllEnrollments();
        StudentEnrollment updatedEnrollment = enrollments.get(0);
        Assert.assertEquals("2021A",updatedEnrollment.getSemester());

    }
//    @Test(expected = Exception.class)
//    public void TestUpdateEnrollmentStudent() throws Exception{
//        AcademicAssistant aa = new AcademicAssistant();
//        aa.loadEnrollments("default.csv");
//        aa.updateEnrollment(0,0,0,"2020B");
//        List<StudentEnrollment> enrollments = aa.getAllEnrollments();
//        StudentEnrollment updatedEnrollment = enrollments.get(0);
//        Student updatedStudent = aa.getStudents().get(0);
//        Assert.assertEquals(updatedStudent,updatedEnrollment.getStudent());
//
//    }
}

