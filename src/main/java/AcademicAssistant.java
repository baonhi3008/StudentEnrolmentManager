import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AcademicAssistant {
    private final List<Student> students = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();
    private final StudentEnrollmentManager enrollments = new StudentEnrollmentList();


    /**
     *
     * @param fileName - from user's file input by enter in the console interface
     *                 if user doesn't take their own file the method will load the default.csv file.
     * @throws Exception if there is any error the error will be caught askToLoadFromFile() in main.
     */
    public void loadEnrollments(String fileName) throws Exception {
        fileName = (fileName == null) ? "default.csv" : fileName;
        List<String> lines = Helper.readFile(fileName);
        for (String line:lines) {
            String[] data = line.split(",");
            Student student = this.getStudent(new Student(data[0], data[1], data[2]));
            Course course = this.getCourse(new Course(data[3], data[4], Integer.valueOf(data[5])));
            String semester = data[6];
            this.enrollments.add(new StudentEnrollment(student, course, semester));
        }
    }

    /**
     * Method to get student for loadEnrollments method.
     * @param queryStudent - the information that user enter in the console interface such as student id or name
     * @return student if the query match any record in Student list. Then add the found result to the list of student
     */
    private Student getStudent(Student queryStudent) {
        for (Student student : this.students) {
            if (student.equals(queryStudent)) return student;
        }
        this.students.add(queryStudent);
        return this.students.get(this.students.size() - 1);
    }

    private Course getCourse(Course queryCourse) {
        for (Course course : this.courses) {
            if (course.equals(queryCourse)) return course;
        }
        this.courses.add(queryCourse);
        return this.courses.get(this.courses.size() - 1);
    }

    /**
     * The boolean method to add enrolment from the index of their choice
     * after view all the list of student and course that are
     * availble. The method will take
     * @param studentIndex - take  student index from the user to getStudent -> to construct student obj in enrolment
     * @param courseIndex - take course index from the user to getCourse -> to construct course obj in enrolment
     * @param semester - take semester from user input (console scanner) -> to construction enrolment
     * @return the new enrolment that will be added into the enrolment list - enrolments
     * @throws Exception for any invalid or not existed index or null value of semester input
     */
    public boolean addEnrollment(int studentIndex,
                                 int courseIndex,
                                 String semester) throws Exception {
        Student student = this.students.get(studentIndex);
        Course course = this.courses.get(courseIndex);
        if (student == null) throw new Exception("Student index is invalid.");
        if (course == null) throw new Exception("Course index is invalid.");
        if (semester == null) throw new Exception("Semester can't be empty.");
        return this.enrollments.add(new StudentEnrollment(student, course, semester));
    }

    public StudentEnrollment getEnrollment(int enrollmentIndex) throws Exception {
        return this.enrollments.getOne(enrollmentIndex);
    }

    /**
     * Method to take all enrolment for operational command number 1 - which allow user to see all enrolment
     * @return all value in the enrolment list
     */
    public List<StudentEnrollment> getAllEnrollments() {
        return this.enrollments.getAll();
    }

    /**
     * Method to update existing enrolment
     * @param enrollmentIndex- pass to get enrolment index from user to get enrolment
     * @param studentIndex- pass to student index from user to get user
     * @param courseIndex- pass course index fromm user to get course
     * @param semester- take input of user to pass in the semester
     * @return an update enrolment with new values from that match user's need
     * @throws Exception when any of the index is invalid or missing
     */
    public boolean updateEnrollment(int enrollmentIndex,
                                    int studentIndex,
                                    int courseIndex,
                                    String semester) throws Exception {
        StudentEnrollment oldEnrollment = this.enrollments.getOne(enrollmentIndex);
        Student student = this.students.get(studentIndex);
        Course course = this.courses.get(courseIndex);
        if (oldEnrollment == null) throw new Exception("Enrollment index is invalid.");
        if (student == null) throw new Exception("Student index is invalid.");
        if (course == null) throw new Exception("Course index is invalid.");
        if (semester == null) throw new Exception("Semester can't be empty.");
        return this.enrollments.update(
                enrollmentIndex,
                new StudentEnrollment(student, course, semester)
        );
    }

    /**
     *
     * This method call delete method in StudentEnrollmentList with the passed in enrollmentIndex
     * to delete an enrolment from StudentEnrollments List.
     * @param enrollmentIndex- enrolment that user want to delete
     * @return enrollmentsList remove specific enrollment
     * @throws Exception
     */
    public boolean deleteEnrollment(int enrollmentIndex) throws Exception {
        return this.enrollments.delete(enrollmentIndex);
    }

    /**
     * The method will get the course based on student index and semester, which will be implemented later
     * for to get all enrollment of one student in one semester in main class.
     * @param studentIndex-studentIndex from a displayed list of student
     * @param semester-Semester's input
     * @return a list of course that filtered with student and semester.
     * @throws Exception if student index  = -1 or is out of range or is invalid.
     */
    public List<Course> getCourses(int studentIndex, String semester) throws Exception {
        Student student = this.students.get(studentIndex);
        if (student == null) throw new Exception("Student index is invalid.");
        List<StudentEnrollment> enrollments = this.filterEnrollment(student, null, semester);
        List<Course> courses = new ArrayList<>();
        for (StudentEnrollment enrollment:enrollments) {
            courses.add(enrollment.getCourse());
        }
        return courses;
    }

    /**
     * Method used to get to-pointed list of Students
     * @return list of students
     */
    public List<Student> getStudents() {
        return this.students;
    }

    /**
     * This method will get all student from a course in certain semester (which is the user's intake)
     * @param courseIndex- index of course from user
     * @param semester-input for specific taken from user
     * @return list of student in course - in a semester
     * @throws Exception if semester or/and student is not valid/ entered
     */
    public List<Student> getStudents(int courseIndex,String semester) throws Exception {
        Course course = this.courses.get(courseIndex);
        boolean isValidSemester = semester.matches("[0-9]{4}[A-B-C]");
        if (course == null) throw new Exception("Course index is invalid.");
        if(semester==null || !isValidSemester) throw new Exception("Semester is invalid or missing");
        List<StudentEnrollment> enrollments = this.filterEnrollment(null, course,semester);
        List<Student> students = new ArrayList<>();
        for (StudentEnrollment enrollment:enrollments) {
            students.add(enrollment.getStudent());
        }
        if (students.isEmpty()){
            System.out.println("Cannot find any record of course in"+semester);
            return null;
        }
        else{
            System.out.println("List of student in"+semester);
            return students;}

    }

    /**
     * This method gets the courses list
     * @return current pointed courses list
     */
    public List<Course> getCourses() {
        return this.courses;
    }

    /**
     * This method gets user's intake as string of semester to return certain courses in the semester
     * @param semester from user
     * @return list of courses in specific semester
     * @throws Exception if any error is existed
     */
    public List<Course> getCourses(String semester) throws Exception{
        List<StudentEnrollment> enrollments = this.filterEnrollment(null, null,semester);
        boolean isValidSemester = semester.matches("[0-9]{4}[A-B-C]");
        if (isValidSemester){
        List<Course> courses = new ArrayList<>();

        for (StudentEnrollment enrollment: enrollments) {
            courses.add(enrollment.getCourse()); }
            List<Course> uniqueCourses = courses.stream().distinct().collect(Collectors.toList());
            return uniqueCourses;}
        else{
            throw new Exception("Semester is not valid. Try again");}

    }

    /**
     * The method is used as a filter to serve 3 main purposes :
     * - take all students from one course with specified semester
     * - take all courses that a student enrols in a semester
     * - take all courses in a semester
     *
     * @param student - object student gotten by index or can be null for some implementations
     * @param course - object course gotten by index or can be null for some implementations
     * @param semester - input from user or can be null for some implementations
     * @return a list of student enrolments that match user's need.
     */
    private List<StudentEnrollment> filterEnrollment(Student student,
                                                     Course course,
                                                     String semester) {
        List<StudentEnrollment> filteredEnrollments = new ArrayList<>();
        List<StudentEnrollment> enrollments = this.enrollments.getAll();
        for (StudentEnrollment enrollment:enrollments) {
            if (student != null && !enrollment.getStudent().equals(student)) continue;
            if (course != null && !enrollment.getCourse().equals(course)) continue;
            if (semester != null && semester.matches("[0-9]{4}[A-B-C]") && !enrollment.getSemester().equals(semester)) continue;
            filteredEnrollments.add(enrollment);
        }
        return filteredEnrollments;
    }

    /**
     * Method to get student index - implemented in updateEnrolment - main class
     * @param student-student obj
     * @return int of student from param
     */
    public int getStudentIndex(Student student) {
        for (int i = 0; i < this.students.size(); i++) {
            if (this.students.get(i).equals(student)) return i;
        }
        return 0;
    }

    /**
     * Method to get course index - implemented in updateEnrolment - main class
     * @param course- course obj
     * @return int of course from param
     */
    public int getCourseIndex(Course course) {
        for (int i = 0; i < this.courses.size(); i++) {
            if (this.courses.get(i).equals(course)) return i;
        }
        return 0;
    }
}
