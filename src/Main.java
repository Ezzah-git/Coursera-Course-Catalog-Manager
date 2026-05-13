import java.util.HashMap;

class Course {
    String id;
    String title;
    String difficulty;
    String organization;

    Course(String id, String title, String difficulty, String organization) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.organization = organization;
    }

    public String toString() {
        return "Course ID: " + id +
                ", Title: " + title +
                ", Difficulty: " + difficulty +
                ", Platform: " + organization;
    }
}

class CourseCatalog {
    private String[] titles;
    private int titleCount;
    private HashMap<String, Course> courses;

    public CourseCatalog(int maxCourses) {
        titles = new String[maxCourses];
        titleCount = 0;
        courses = new HashMap<>();
    }

    public void addCourse(Course course) {
        if (titleCount < titles.length) {
            titles[titleCount++] = course.title;
            courses.put(course.id, course);
            System.out.println("Course added: " + course.title);
        } else {
            System.out.println("Catalog is full! Cannot add more courses.");
        }
    }
    public void searchByTitle(String title) {
        for (int i = 0; i < titleCount; i++) {
            if (titles[i].equalsIgnoreCase(title)) {
                for (Course c : courses.values()) {
                    if (c.title.equalsIgnoreCase(title)) {
                        System.out.println("Found: " + c);
                        return;
                    }
                }
            }
        }
        System.out.println("Course not found: " + title);
    }

    public void displayAllCourses() {
        System.out.println("All Available Courses:");
        for (Course c : courses.values()) {
            System.out.println(c);
        }
    }

    public void filterByDifficulty(String difficulty) {
        System.out.println("\nCourses with Difficulty: " + difficulty);
        boolean found = false;
        for (Course c : courses.values()) {
            if (c.difficulty.equalsIgnoreCase(difficulty)) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No courses found with difficulty: " + difficulty);
        }
    }

    public void countCoursesPerOrg() {
        System.out.println("\nCourses per Organization:");
        HashMap<String, Integer> orgCount = new HashMap<>();
        for (Course c : courses.values()) {
            orgCount.put(c.organization, orgCount.getOrDefault(c.organization, 0) + 1);
        }
        for (String org : orgCount.keySet()) {
            System.out.println(org + " → " + orgCount.get(org) + " courses");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CourseCatalog catalog = new CourseCatalog(10);

        catalog.addCourse(new Course("1",
                "Complete GST Course & Certification - Grow Your CA Practice",
                "All Levels", "udemy"));

        catalog.addCourse(new Course("2",
                "Financial Modeling for Business Analysts and Consultants",
                "Intermediate Level", "udemy"));

        catalog.addCourse(new Course("3",
                "Beginner to Pro - Financial Analysis in Excel 2017",
                "All Levels", "udemy"));

        catalog.addCourse(new Course("6",
                "Investing And Trading For Beginners: Mastering Price Charts",
                "Beginner Level", "udemy"));

        catalog.addCourse(new Course("8",
                "Options Trading 3 : Advanced Stock Profit and Success Method",
                "Expert Level", "udemy"));

        catalog.displayAllCourses();
        catalog.searchByTitle("Beginner to Pro - Financial Analysis in Excel 2017");
        catalog.filterByDifficulty("All Levels");
        catalog.countCoursesPerOrg();
    }
}
