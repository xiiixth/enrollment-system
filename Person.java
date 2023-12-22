import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String firstName;
    private String lastName;
    protected LocalDate birthDate;

    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void modifyFirstName(String newFirstName) {
        this.firstName = newFirstName;
        System.out.println("Student's first name updated successfully.");
    }

    public void modifyLastName(String newLastName) {
        this.lastName = newLastName;
        System.out.println("Student's last name updated successfully.");
    }

    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}