import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class EnrollmentSystem {
    private static int studentIdCounter = 2300; 
    ArrayList<Student> students;

    public EnrollmentSystem() {
        this.students = new ArrayList<>();
    }

    public void enrollStudent(String firstName, String lastName, LocalDate birthDate, String course) {
        Student student = new Student(firstName, lastName, birthDate, course);
        student.setId(studentIdCounter++);
        students.add(student);
        System.out.println("Student enrolled successfully. ID assigned: " + student.getId());
    }

    public void deleteStudent(String fullName) {
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                student.setRemovedStatus(true);
                student.setModificationPending(true);
                System.out.println("Student marked for deletion. Pending approval from approver.");
                return;
            }
        }
        System.out.println("Student not found.");
    }
    
    public void markAsRemoved(String fullName) {
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                student.setRemovedStatus(true);
                student.setModificationPending(true);
                System.out.println("Student marked as removed. Pending approval from approver.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void approveDeletion(String fullName) {
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                if (student.isModificationPending() && student.isRemoved()) {
                    students.remove(student);
                    System.out.println("Student deleted successfully.");
                    return;
                } else {
                    System.out.println("Deletion not approved or student not marked for deletion.");
                }
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void approveMarkAsRemoved(String fullName) {
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                if (student.isModificationPending() && student.isRemoved()) {
                    student.setRemovedStatus(true);
                    student.setModificationPending(false);
                    System.out.println("Student marked as removed approved.");
                    return;
                } else {
                    System.out.println("Mark as removed not approved or student not marked for removal.");
                }
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void modifyStudentName(String fullName, Scanner scanner) {
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                System.out.println("Choose what to modify:");
                System.out.println("[1] Modify first name");
                System.out.println("[2] Modify last name");
                System.out.print("Enter your choice: ");
                int nameChoice = scanner.nextInt();
                scanner.nextLine();

                switch (nameChoice) {
                    case 1:
                        System.out.print("Enter new first name: ");
                        String newFirstName = scanner.nextLine();
                        student.setModifiedFirstName(newFirstName);
                        student.setModificationPending(true);
                        System.out.println("Modification marked as pending approval.");
                        break;
                    case 2:
                        System.out.print("Enter new last name: ");
                        String newLastName = scanner.nextLine();
                        student.setModifiedLastName(newLastName);
                        student.setModificationPending(true);
                        System.out.println("Modification marked as pending approval.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again");
                        break;
                }
                return;
            }
        }
        System.out.println("Student not found.");
    }
    
    public void approveNameModifications(String fullName, Scanner scanner) {
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                if (student.isModificationPending()) {
                    System.out.println("Name modifications pending for student: " + fullName);
                    System.out.println("[1] Approve modify first name");
                    System.out.println("[2] Approve modify last name");
                    System.out.print("Enter your choice: ");
                    int approveChoice = scanner.nextInt();
                    scanner.nextLine();
    
                    switch (approveChoice) {
                        case 1:
                            if (!student.getModifiedFirstName().isEmpty()) {
                                student.finalizeModifications();
                            } else {
                                System.out.println("No first name modifications found.");
                            }
                            break;
                        case 2:
                            if (!student.getModifiedLastName().isEmpty()) {
                                student.finalizeModifications();
                            } else {
                                System.out.println("No last name modifications found.");
                            }
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    return;
                } else {
                    System.out.println("No name modifications pending for student: " + fullName);
                }
                return;
            }
        }
        System.out.println("Student not found.");
    }
    
    public void tagAsRemoved(String fullName) {
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                student.setRemovedStatus(true);
                student.setModificationPending(true);
                System.out.println("Student marked as removed. Pending approval from approver.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void displayAllStudents() {
        System.out.println("All Enrolled Students:");
        for (Student student : students) {
            int age = student.calculateAge();
            
            if (student.isRemoved() && student.isModificationPending()) {
                System.out.println("ID: " + student.getId() +
                        ", Name: " + student.getFullName() +
                        ", Age: " + age +
                        ", Course: " + student.getCourse() +
                        "");
            } else if (student.isRemoved() && !student.isModificationPending()) {
                System.out.println("ID: " + student.getId() +
                        ", Name: " + student.getFullName() +
                        ", Age: " + age +
                        ", Course: " + student.getCourse() +
                        "");
            } else {
                System.out.println("ID: " + student.getId() +
                        ", Name: " + student.getFullName() +
                        ", Age: " + age +
                        ", Course: " + student.getCourse());
            }
        }
    }

    public void viewStudentsByName(String viewByName) {
        System.out.println("Students with name '" + viewByName + "':");
        boolean foundByName = false;
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(viewByName)) {
                foundByName = true;
                int age = student.calculateAge(); 

                System.out.println("ID: " + student.getId() +
                        ", Name: " + student.getFullName() +
                        ", Age: " + age + 
                        ", Course: " + student.getCourse() +
                        (student.isRemoved() ? " (Removed)" : ""));
            }
        }
        if (!foundByName) {
            System.out.println("No students found with the given name.");
        }
    }

    public void viewStudentsByCourse(String viewByCourse) {
        System.out.println("Students with course '" + viewByCourse + "':");
        boolean foundByCourse = false;
        for (Student student : students) {
            if (student.getCourse().equalsIgnoreCase(viewByCourse)) {
                foundByCourse = true;
                int age = student.calculateAge(); 

                System.out.println("ID: " + student.getId() +
                        ", Name: " + student.getFullName() +
                        ", Age: " + age + 
                        ", Course: " + student.getCourse() +
                        (student.isRemoved() ? " (Removed)" : ""));
            }
        }
        if (!foundByCourse) {
            System.out.println("No students found in the given course.");
        }
    }

    public void viewStudentsById(int viewById) {
        boolean foundById = false;
        for (Student student : students) {
            if (student.getId() == viewById) {
                foundById = true;
                int age = student.calculateAge(); 

                System.out.println("ID: " + student.getId() +
                        ", Name: " + student.getFullName() +
                        ", Age: " + age + 
                        ", Course: " + student.getCourse() +
                        (student.isRemoved() ? " (Removed)" : ""));
                break;
            }
        }
        if (!foundById) {
            System.out.println("No student found with the given ID.");
        }
    }
    
    public void modifyStudentAge(String fullName, Scanner scanner) {
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                System.out.print("Enter new birthdate (YYYY-MM-DD): ");
                String newBirthDateString = scanner.nextLine();
                LocalDate newBirthDate = LocalDate.parse(newBirthDateString);
                student.setModifiedBirthDate(newBirthDate); 
                student.setModificationPending(true);
                System.out.println("Student's birthdate updated. Pending approval from approver.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void modifyStudentCourse(String fullName, Scanner scanner) {
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                System.out.println("\nAvailable Courses:");
                for (int i = 0; i < Main.AVAILABLE_COURSES.length; i++) {
                    System.out.println((i + 1) + ". " + Main.AVAILABLE_COURSES[i]);
                }
                System.out.print("Select a course (enter the number): ");
                int courseChoice = scanner.nextInt();
                scanner.nextLine();
    
                if (courseChoice > 0 && courseChoice <= Main.AVAILABLE_COURSES.length) {
                    String newCourse = Main.AVAILABLE_COURSES[courseChoice - 1];
                    student.setModifiedCourse(newCourse); 
                    student.setModificationPending(true);
                    System.out.println("Student's course modification marked as pending for approval.");
                } else {
                    System.out.println("Invalid course choice.");
                }
                return;
            }
        }
        System.out.println("Student not found.");
    }
    

    public void approveModifyAge(String fullName) {
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                student.finalizeModifyAge();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void approveModifyCourse(String fullName) {
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                if (student.isModificationPending()) {
                    student.finalizeModifyCourse(); 
                    return;
                } else {
                    System.out.println("No course modifications pending for student: " + fullName);
                    return;
                }
            }
        }
        System.out.println("Student not found.");
    }

    public void viewPendingModifications() {
        boolean foundPendingModifications = false;
        System.out.println("\nStudents with Pending Modifications:");
    
        for (Student student : students) {
            if (student.isModificationPending()) {
                foundPendingModifications = true;
                int age = student.calculateAge();
    
                System.out.println("ID: " + student.getId() +
                        ", Name: " + student.getFullName() +
                        ", Age: " + age +
                        ", Course: " + student.getCourse());
    
                // Check and display pending modifications
                if (!student.getModifiedFirstName().isEmpty() || !student.getModifiedLastName().isEmpty()) {
                    System.out.println("(Pending Name Modification)");
                }
                if (student.getModifiedBirthDate() != null) {
                    System.out.println("(Pending Age Modification)");
                }
                if (!student.getModifiedCourse().isEmpty()) {
                    System.out.println("(Pending Course Modification)");
                }
                if (student.isRemoved()) {
                    System.out.println("(Pending Deletion)");
                }
                // Additional condition (if needed for mark as removed status)
                // if student.setRemovedStatus(true) {
                //    System.out.println("Pending Mark as Removed");
                // }
                System.out.println();
            }
        }
    
        if (!foundPendingModifications) {
            System.out.println("No students found with pending modifications.");
        }
    }
    
}

