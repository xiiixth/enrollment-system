import java.time.LocalDate;
import java.util.Scanner;

class Main {
    static final String[] AVAILABLE_COURSES = {"Information Technology", "Computer Science", "Architecture", "Accounting", "Hospitality Management"};

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) { 
            System.out.println();
        }
    }
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EnrollmentSystem enrollmentSystem = new EnrollmentSystem();

            String enrollerUsername = "enroller";
            String approverUsername = "approver";
            String exitUsername = "exit";
 
            boolean isLoggedIn = false;

            while (true) {
                System.out.println("Log In Menu");
                System.out.println("enroller | approver | exit");
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                if (username.equals(enrollerUsername)) {
                    clearConsole();
                    isLoggedIn = true;
                    while (isLoggedIn) {
                        System.out.println("\nMain Menu (Enroller)");
                        System.out.println("[1] Enroll");
                        System.out.println("[2] View");
                        System.out.println("[3] Modify");
                        System.out.println("[4] Log out");
                        System.out.println("[0] Exit");

                        System.out.print("Enter your choice: ");
                        int choice = scanner.nextInt();
                        clearConsole();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                                System.out.println("\nStudent Menu");
                                System.out.println("[1] Enroll a student");
                                System.out.println("[2] Add batch of students");
                                System.out.println("[3] Back to main menu");
                                System.out.print("Enter your choice: ");
                                int choice1 = scanner.nextInt();
                                scanner.nextLine();
                                
                                switch (choice1) {
                                    case 1:
                                        clearConsole();
                                        System.out.println("Enrolling");
                                        System.out.print("\nEnter student first name: ");
                                        String firstName = scanner.nextLine();
                                        System.out.print("Enter student last name: ");
                                        String lastName = scanner.nextLine();
                                        System.out.print("Enter student birthdate (YYYY-MM-DD): ");
                                        String birthDateString = scanner.nextLine();
                                        LocalDate birthDate = LocalDate.parse(birthDateString);

                                        System.out.println("\nAvailable Courses:");
                                        for (int i = 0; i < AVAILABLE_COURSES.length; i++) {
                                            System.out.println((i + 1) + ". " + AVAILABLE_COURSES[i]);
                                        }
                                        System.out.print("Select a course (enter the number): ");
                                        int courseChoice = scanner.nextInt();
                                        scanner.nextLine();
                                        if (courseChoice > 0 && courseChoice <= AVAILABLE_COURSES.length) {
                                            String enrollCourse = AVAILABLE_COURSES[courseChoice - 1];
                                            enrollmentSystem.enrollStudent(firstName, lastName, birthDate, enrollCourse);
                                        } else {
                                            System.out.println("Invalid course choice.");
                                        }
                                        break;
                                    case 2:
                                        clearConsole();
                                        System.out.println("Batch Enrolling");
                                        System.out.print("\nEnter the number of students to enroll: ");
                                        int numberOfStudentsToEnroll = scanner.nextInt();
                                        scanner.nextLine();
                                        for (int j = 0; j < numberOfStudentsToEnroll; j++) {
                                            System.out.println("\nStudent " + (j + 1) + ":");
                                            System.out.print("Enter student first name: ");
                                            String batchEnrollFirstName = scanner.nextLine();
                                            System.out.print("Enter student last name: ");
                                            String batchEnrollLastName = scanner.nextLine();
                                            System.out.println("Available Courses:");
                                            for (int i = 0; i < AVAILABLE_COURSES.length; i++) {
                                                System.out.println((i + 1) + ". " + AVAILABLE_COURSES[i]);
                                            }
                                            System.out.print("Select a course (enter the number): ");
                                            int batchCourseChoice = scanner.nextInt();
                                            scanner.nextLine();
                                            if (batchCourseChoice > 0 && batchCourseChoice <= AVAILABLE_COURSES.length) {
                                                String batchEnrollCourse = AVAILABLE_COURSES[batchCourseChoice - 1];
                                            
                                                System.out.print("Enter student birthdate (YYYY-MM-DD): ");
                                                birthDateString = scanner.nextLine();
                                                birthDate = LocalDate.parse(birthDateString);
                                            
                                                enrollmentSystem.enrollStudent(batchEnrollFirstName, batchEnrollLastName, birthDate, batchEnrollCourse);
                                            } else {
                                                System.out.println("Invalid course choice. Student not enrolled.");
                                                j--; 
                                            }
                                        }
                                        break;
                                    case 3:
                                        clearConsole();
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again.");
                                }
                                break;
                            case 2:
                            System.out.println("\nView Menu");
                            System.out.println("[1] View students by name");
                            System.out.println("[2] View students by course");
                            System.out.println("[3] View students by ID");
                            System.out.println("[4] Display all students");
                            System.out.println("[5] Back to main menu");

                            System.out.print("Enter your choice: ");
                            int viewChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (viewChoice) {
                                case 1:
                                    clearConsole();
                                    System.out.println("Viewing by name");
                                    System.out.print("Enter student name: ");
                                    String viewByName = scanner.nextLine();
                                    enrollmentSystem.viewStudentsByName(viewByName);
                                    break;
                                case 2:
                                    clearConsole();
                                    System.out.println("Viewing by course");
                                    System.out.println("\nAvailable Courses:");
                                    for (int i = 0; i < AVAILABLE_COURSES.length; i++) {
                                        System.out.println((i + 1) + ". " + AVAILABLE_COURSES[i]);
                                    }
                                    System.out.print("Select a course (enter the number): ");
                                    int viewByCourseChoice = scanner.nextInt();
                                    scanner.nextLine();
                                
                                    if (viewByCourseChoice > 0 && viewByCourseChoice <= AVAILABLE_COURSES.length) {
                                        String viewByCourse = AVAILABLE_COURSES[viewByCourseChoice - 1];
                                        enrollmentSystem.viewStudentsByCourse(viewByCourse);
                                    } else {
                                        System.out.println("Invalid course choice.");
                                    }
                                    break;
                                    case 3:
                                        clearConsole();
                                        System.out.println("Viewing by ID");
                                        System.out.print("Enter student ID: ");
                                        int viewById = scanner.nextInt();
                                        scanner.nextLine();
                                        enrollmentSystem.viewStudentsById(viewById);
                                        break;
                                    case 4:
                                        enrollmentSystem.displayAllStudents();
                                        break;
                                    case 5:
                                        clearConsole();
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again");
                                }
                                break;
                                case 3:
                                    System.out.println("\nModify Menu");
                                    System.out.println("[1] Modify name");
                                    System.out.println("[2] Modify age");
                                    System.out.println("[3] Modify course");
                                    System.out.println("[4] Delete student"); 
                                    System.out.println("[5] Main menu");
                                
                                    System.out.print("Enter your choice: ");
                                    int modifyChoice = scanner.nextInt();
                                    scanner.nextLine();
                                    
                                    switch (modifyChoice) {
                                        case 1:
                                            clearConsole();
                                            System.out.println("Modifying name");
                                            System.out.print("Enter student first name: ");
                                            String firstName = scanner.nextLine();
                                            System.out.print("Enter student last name: ");
                                            String lastName = scanner.nextLine();
                                            String fullName = firstName + " " + lastName;
                                            enrollmentSystem.modifyStudentName(fullName, scanner);
                                            break;
                                        case 2:
                                            clearConsole();
                                            System.out.println("Modifying age");
                                            System.out.print("Enter student full name to modify age: ");
                                            String nameToModifyAge = scanner.nextLine();
                                            enrollmentSystem.modifyStudentAge(nameToModifyAge, scanner);
                                            break;
                                        case 3:
                                            clearConsole();
                                            System.out.println("Modifying course");
                                            System.out.print("Enter student full name to modify course: ");
                                            String nameToModifyCourse = scanner.nextLine();
                                            enrollmentSystem.modifyStudentCourse(nameToModifyCourse, scanner);
                                            break;
                                        case 4:
                                            clearConsole();
                                            System.out.println("\nDelete Menu");
                                            System.out.println("[1] Delete a student");
                                            System.out.println("[2] Mark student as removed");
                                            System.out.println("[3] Main menu");

                                            System.out.print("Enter your choice: ");
                                            int deleteChoice = scanner.nextInt();
                                            scanner.nextLine();

                                            switch(deleteChoice) {
                                                case 1:
                                                    clearConsole();
                                                    System.out.println("Deleting");
                                                    System.out.print("Enter student name to delete: ");
                                                    String deleteName = scanner.nextLine();
                                                    enrollmentSystem.deleteStudent(deleteName);
                                                    break;
                                                case 2:
                                                    clearConsole();
                                                    System.out.println("Mark as Removed");
                                                    System.out.print("Enter student name to mark as removed: ");
                                                    String markAsRemovedName = scanner.nextLine();
                                                    enrollmentSystem.tagAsRemoved(markAsRemovedName);
                                                    break;
                                                case 3:
                                                    clearConsole();
                                                    break;
                                            }
                                            break;
                                        case 5:
                                            clearConsole();
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again");
                                            break;
                                    }
                                    break;
                            case 4:
                                isLoggedIn = false;
                                System.out.println("Logged out successfully.");
                                break;
                            case 0:
                                System.out.println("Exiting program...");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again");
                        }
                    }
                } else if (username.equals(approverUsername)) {
                    isLoggedIn = true;
                    while (isLoggedIn) {
                        System.out.println("\nMain Menu (Approver)");
                        System.out.println("[1] Approve modifications");
                        System.out.println("[2] Log out");
                        System.out.println("[0] Exit");

                        System.out.print("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch(choice) {
                            case 1:
                                clearConsole();
                                System.out.println("\nApprove:");
                                System.out.println("[1] Approve name modifications");
                                System.out.println("[2] Approve age modifications");
                                System.out.println("[3] Approve course modifications");
                                System.out.println("[4] Approve deletion");
                                System.out.println("[5] Approve mark as removed");
                                System.out.println("[6] View pending modifications");
                                System.out.println("[7] Back to main menu");

                                System.out.print("Enter your choice: ");
                                int choice2 = scanner.nextInt();
                                scanner.nextLine();

                                switch (choice2) {
                                    case 1:
                                        clearConsole();
                                        System.out.println("Name Approval");
                                        System.out.print("Enter student name to approve modifications: ");
                                        String approveModificationsName = scanner.nextLine();
                                        enrollmentSystem.approveNameModifications(approveModificationsName, scanner);
                                        break;
                                    case 2:
                                        clearConsole();
                                        System.out.println("Age Approval");
                                        System.out.print("Enter student name to approve modify age: ");
                                        String approveModifyAgeName = scanner.nextLine();
                                        enrollmentSystem.approveModifyAge(approveModifyAgeName);
                                        break;
                                    case 3:
                                        clearConsole();
                                        System.out.println("Course Approval");
                                        System.out.print("Enter student name to approve modify course: ");
                                        String approveModifyCourseName = scanner.nextLine();
                                        enrollmentSystem.approveModifyCourse(approveModifyCourseName);
                                        break;
                                    case 4:
                                        clearConsole();
                                        System.out.println("Deletion Approval");
                                        System.out.print("Enter student name to approve deletion: ");
                                        String approveDeletionName = scanner.nextLine();
                                        enrollmentSystem.approveDeletion(approveDeletionName);
                                        break;
                                    case 5:
                                        clearConsole();
                                        System.out.println("Mark as removed Approval");
                                        System.out.print("Enter student name to approve mark as removed: ");
                                        String approveMarkAsRemovedName = scanner.nextLine();
                                        enrollmentSystem.approveMarkAsRemoved(approveMarkAsRemovedName);
                                        break;
                                    case 6:
                                        clearConsole();
                                        enrollmentSystem.viewPendingModifications();
                                        break;
                                    case 7:
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again");
                                }
                                break;
                            case 2:
                                isLoggedIn = false;
                                System.out.println("Logged out successfully.");
                                break;
                            case 0:
                                System.out.println("Exiting program...");
                                        System.exit(0);
                                        break;
                            default:
                                        System.out.println("Invalid choice. Please try again");
                        }
                        
                    }
                } else if(username.equals(exitUsername)) {
                    System.out.println("Exiting...");
                    System.exit(0);
                } else {
                    System.out.println("Invalid username.");
                }
            }
        }
    }
}
