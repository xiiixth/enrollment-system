import java.time.LocalDate;

public class Student extends Person {
    private static int nextId = 1; 
    private int id; 
    private String course;
    private boolean isRemoved;
    private boolean modificationPending;
    private String modifiedFirstName;
    private String modifiedLastName;
    private String modifiedCourse;
    private LocalDate modifiedBirthDate;

    public Student(String firstName, String lastName, LocalDate birthDate, String course) {
        super(firstName, lastName, birthDate);
        this.id = nextId++;
        this.course = course;
        this.isRemoved = false;
        this.modificationPending = false;
        this.modifiedFirstName = "";
        this.modifiedLastName = "";
        this.modifiedBirthDate = null;
        this.modifiedCourse = "";

    }

   public String getCourse() {
       return course;
   }

   public boolean isRemoved() {
       return isRemoved;
   }

   public void setRemovedStatus(boolean removed) {
       this.isRemoved = removed;
   }

   public int getId() {
        return id;
    }

   public void setId(int id) {
        this.id = id;
    }

    public boolean isModificationPending() {
        return modificationPending;
    }

    public void setModificationPending(boolean modificationPending) {
        this.modificationPending = modificationPending;
    }

    public String getModifiedFirstName() {
        return modifiedFirstName;
    }

    public void setModifiedFirstName(String modifiedFirstName) {
        this.modifiedFirstName = modifiedFirstName;
    }

    public String getModifiedLastName() {
        return modifiedLastName;
    }

    public void setModifiedLastName(String modifiedLastName) {
        this.modifiedLastName = modifiedLastName;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getModifiedCourse() {
        return modifiedCourse;
    }

    public void setModifiedCourse(String modifiedCourse) {
        this.modifiedCourse = modifiedCourse;
    }

    public LocalDate getModifiedBirthDate() {
        return modifiedBirthDate;
    }

    public void finalizeModifications() {
        if (modificationPending) {
            if (!modifiedFirstName.isEmpty()) {
                this.modifyFirstName(modifiedFirstName);
                modifiedFirstName = "";
            }
            if (!modifiedLastName.isEmpty()) {
                this.modifyLastName(modifiedLastName);
                modifiedLastName = "";
            }
            modificationPending = false;
            System.out.println("\nStudent modifications approved and updated successfully.");
        } else {
            System.out.println("No modifications pending for approval.");
        }
    }

    public Object getBirthDate() {
        return null;
    }

    public void finalizeModifyCourse() {
        if (modificationPending && !modifiedCourse.isEmpty()) {
            this.course = modifiedCourse; 
            modifiedCourse = ""; 
            modificationPending = false; 
            System.out.println("\nCourse modification approved and updated successfully.");
        } else {
            System.out.println("No course modifications pending for approval or course not provided.");
        }
    }

    public void setModifiedBirthDate(LocalDate modifiedBirthDate) {
        this.modifiedBirthDate = modifiedBirthDate;
    }

    public void finalizeModifyAge() {
        if (modificationPending && modifiedBirthDate != null) {
            this.setBirthDate(modifiedBirthDate); 
            modifiedBirthDate = null; 
            modificationPending = false;
            System.out.println("\nAge modification approved and updated successfully.");
        } else {
            System.out.println("No age modifications pending for approval or birthdate not provided.");
        }
    }
}