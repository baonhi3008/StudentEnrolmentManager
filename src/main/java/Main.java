import java.util.List;
import java.util.Scanner;

public class Main {
    public static Main app = new Main();
    public Scanner scanner = new Scanner(System.in);
    private AcademicAssistant academicAssistant = new AcademicAssistant();

    /**
     *
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        app.askToLoadFromFile();
        while (true) {
            app.displayOptions();
            app.pressAnyKeyToContinue();
        }
    }


    /**
     * method that ask user if they want to load the file into the app.
     * if they input anything except "y" in the yesNoquestion method. This method will be passed.
     * if user entered the file path, the app will check the file and load it into the app
     */
    public void askToLoadFromFile()
    {
        boolean loadCustomFile = askYesNoQuestion("Do you want to load enrollments from any file?");
        String filename = null;
        if (loadCustomFile) {
            System.out.print("Enter the file name: ");
            filename = scanner.nextLine();
        }
        try {
            academicAssistant.loadEnrollments(filename);
            System.out.println("Enrollments loaded.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param question-
     * @return the user input for the yes no question
     * function will take "y" and pass into other methods to execute further action
     * and will skip the input if user enter any input different from "y"
     */
    private boolean askYesNoQuestion(String question) {
        System.out.print(question + " (y for yes) ");
        String answerString = scanner.nextLine();
        while (answerString.isEmpty()) answerString = scanner.nextLine();
        return answerString.equals("y");
    }

    /**
     *
     * Display menu options for user to choose among operational commands in the application
     * if the uss
     */
    public void displayOptions() throws Exception {
        String options =
                "1 >> Print all enrollments\n" +
                "2 >> Add enrollment\n" +
                "3 >> Update an enrollment\n" +
                "4 >> Delete an enrollment\n" +
                "5 >> Print all courses from a student in a semester\n" +
                "6 >> Print all students from a course in a semester\n" +
                "7 >> Print all courses in a semester\n" +
                "8 >> Quit";
        System.out.println(options);
        System.out.print("Choose an option: ");
        int optionValue = scanner.nextInt();scanner.nextLine();
        switch (optionValue) {
            case 1: app.printAllEnrollments(); break;
            case 2: app.addEnrollment(); break;
            case 3: app.updateEnrollment(); break;
            case 4: app.deleteEnrollment(); break;
            case 5: app.printCoursesOfStudentInSemester(); break;
            case 6: app.printStudentsOfCourseInSemester(); break;
            case 7: app.printCoursesInSemester(); break;
            case 8: app.quit(); break;
            default: System.out.println("Invalid option."); break;
        }
    }

    /**
     *
     */
    public void pressAnyKeyToContinue() {
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }


    /**
     * method to enrolment with student and course index and semester inputed from the user
     */
    public void addEnrollment() {
        int studentIndex = app.selectStudentByIndex();
        int courseIndex = app.selectCourseByIndex();
        System.out.println("Enter semester: ");
        String semester = scanner.nextLine();
        try {
            academicAssistant.addEnrollment(studentIndex, courseIndex, semester);
            System.out.println("Enrollment added.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /** Method used to get Student from particular index that is entered by user and will be used in addEnrolment.
     *  The method also checks whether chosen student index is valid or not.
     *  if the index is valid Student List students will get the student
     *  else the method will require user to input the student index again
     * @return index of student
     */
    private int selectStudentByIndex() {
        List<Student> students = academicAssistant.getStudents();
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d >> %s%n", i, students.get(i).toString());
        }
        System.out.print("Select a student: ");
        int studentIndex = scanner.nextInt();scanner.nextLine();
        while (studentIndex < 0 || studentIndex >= students.size()) {
            System.out.print("Try again, select a student: ");
            studentIndex = scanner.nextInt();scanner.nextLine();
        }
        return studentIndex;
    }

    /**
     *
     * Method used to get Course from particular index that is entered by user and will be used in addEnrolment.
     * The method also checks whether chosen course index is valid or not.
     * if the index is valid Course List courses will get the student
     * else the method will require user to input the student index again
     * @return index of course
     */
    private int selectCourseByIndex() {
        List<Course> courses = academicAssistant.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.printf("%d >> %s%n", i, courses.get(i).toString());
        }
        System.out.print("Select a course: ");
        int courseIndex = scanner.nextInt();scanner.nextLine();
        while (courseIndex < 0 || courseIndex >= courses.size()) {
            System.out.print("Try again, select a course: ");
            courseIndex = scanner.nextInt();scanner.nextLine();
        }
        return courseIndex;
    }


    private int selectEnrollmentByIndex() {
        List<StudentEnrollment> enrollments = academicAssistant.getAllEnrollments();
        for (int i = 0; i < enrollments.size(); i++) {
            System.out.printf("%d >> %s%n", i, enrollments.get(i).toString());
        }
        System.out.print("Select an enrollment: ");
        int enrollmentIndex = scanner.nextInt();scanner.nextLine();
        while (enrollmentIndex < 0 || enrollmentIndex >= enrollments.size()) {
            System.out.print("Try again, select an enrollment: ");
            enrollmentIndex = scanner.nextInt();scanner.nextLine();
        }
        return enrollmentIndex;
    }

    /**
     * Method to display all the enrolments from file and total of enrollments in that file. The method will ask if the user want to export
     * these enrolment to csv file for further usage.
     */
    public void printAllEnrollments() {
        List<StudentEnrollment> enrollments = academicAssistant.getAllEnrollments();
        for (StudentEnrollment enrollment:enrollments) {
            System.out.println(enrollment.toString());
        }
        System.out.printf("Total of %d enrollments listed.\n", enrollments.size());
        if (askYesNoQuestion("Export to csv file?")) {
            exportToFile(enrollments);
        }
    }

    /** the method acts as
     *Command to update Enrollment - calls the method updateEnrolment(args are old and new enrolment with certain indexes) from Academic Assistant class
     * to update Enrolment for student and course index
     * that taken from the user's input.
     * if the method successfully update an enrolment the message will displayed, if not then the exception will get error message.
     */
    public void updateEnrollment() {
        int enrollmentIndex = selectEnrollmentByIndex();
        try {
            StudentEnrollment enrollment = academicAssistant.getEnrollment(enrollmentIndex);
            int studentIndex = academicAssistant.getStudentIndex(enrollment.getStudent());
            int courseIndex = academicAssistant.getCourseIndex(enrollment.getCourse());
            String semester = enrollment.getSemester();
            if (askYesNoQuestion("Update student?")) {
                studentIndex = selectStudentByIndex();
            }
            if (askYesNoQuestion("Update course?")) {
                courseIndex = selectCourseByIndex();
            }
            if (askYesNoQuestion("Update semester?")) {
                System.out.print("Enter semester: ");
                semester = scanner.nextLine();
            }
            academicAssistant.updateEnrollment(enrollmentIndex, studentIndex, courseIndex, semester);
            System.out.println("Enrollment updated.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * The method acts as delete command to delete an enrolment that is chosen from a user by entered index from the enrolment list
     * the command calls the deleteEnrolment from the Academic Assistant class to execute the action.
     */
    public void deleteEnrollment() {
        int enrollmentIndex = selectEnrollmentByIndex();
        try {
            academicAssistant.deleteEnrollment(enrollmentIndex);
            System.out.println("Enrollment deleted.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to validate semester received from user
     * @param semester- from user intake
     * @return if a semester is valid or not
     */
    public boolean isValidSemesterInput(String semester){
        return semester.matches("[0-9]{4}[A-B-C]");
    }

    /**
     * Method to print the Course of one Student in specific semester
     * the student index will be taken from the user and call the getcourses(with studentindex and semester) in academic assistant class
     * to execute the command.
     *
     */

    public void printCoursesOfStudentInSemester() {
        int studentIndex = selectStudentByIndex();
        System.out.print("Enter semester: ");
        String semester = scanner.nextLine();
        boolean isValidsemester = isValidSemesterInput(semester);
        try {
            if(isValidsemester){
            List<Course> courses = academicAssistant.getCourses(studentIndex,semester);
            if(!courses.isEmpty()){
                for (Course course : courses) {
                    System.out.println(course.toString());
                    if (askYesNoQuestion("Export to csv file?")) {
                        exportToFile(courses);
                    }
                }
            }
            else{
                System.out.println("Cannot find any course of student number "+studentIndex+" in " +semester+"Try again");

            }
            }
            else{
                String message = "Invalid Semester please try again";
                System.out.println(message);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print all the Student of one course in specific semester by taking the course index and the input of semester from the user.
     * The method asks for user's option if they want to export the list into the csv file.
     */
    public void printStudentsOfCourseInSemester() {
        int courseIndex = selectCourseByIndex();
        System.out.print("Enter semester: ");
        String semester = scanner.nextLine();
        boolean isValidSemester = isValidSemesterInput(semester);
        try {
            if (isValidSemester){
                List<Student> students = academicAssistant.getStudents(courseIndex,semester);
                for (Student student : students) {
                    System.out.println(student.toString());
                }
                if (askYesNoQuestion("Export to csv file?")) {
                exportToFile(students);
            }
            else{
                String message = "Invalid semester";
                System.out.println(message);
            }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print all courses that are available in entered semester and asks user
     * if they want to print the course list of that semester to csv file
     */
    public void printCoursesInSemester() {
        System.out.println("Enter semester in 20[20,21,22] with quarter A or B or C. Valid input to process, i.e 2021B ");
        System.out.print("Enter semester: ");

        String semester = scanner.nextLine();
        try {
            List <Course> courses = academicAssistant.getCourses(semester);
            for (Course course : courses) {
                System.out.println(course.toString());
            }
            if (askYesNoQuestion("Export to csv file?")) {
                exportToFile(courses);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     * The method will take the certain list from previous actions (i.e print all student from particular course)
     * to csv file and with file name from user.
     * @param list-
     *
     *
     */
    private void exportToFile(List list) {
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
        StringBuilder buffer = new StringBuilder();
        for (Object item : list) {
            buffer.append(item.toString()).append("\n");
        }
        try {
            Helper.writeFile(fileName, buffer.toString());
            System.out.println("Exported.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to quit application
     */

    public void quit() {
        System.out.println("Application shutdown.");
        System.exit(0);
    }
}
