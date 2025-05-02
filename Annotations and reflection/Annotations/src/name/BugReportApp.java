package name;
import java.lang.annotation.*;
import java.lang.reflect.Method;

public class BugReportApp {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Repeatable(BugReports.class)
    @interface BugReport {
        String description();
    }
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface BugReports {
        BugReport[] value();
    }
    @BugReport(description = "Null pointer exception on line 42")
    @BugReport(description = "ArrayIndexOutOfBounds when input is empty")
    public void processTask() {
        System.out.println("Processing task...");
    }
    public static void main(String[] args) {
        try {
            Method method = BugReportApp.class.getMethod("processTask");

            if (method.isAnnotationPresent(BugReports.class)) {
                BugReports reports = method.getAnnotation(BugReports.class);
                for (BugReport report : reports.value()) {
                    System.out.println("Bug Description: " + report.description());
                }
            } else if (method.isAnnotationPresent(BugReport.class)) {
                BugReport report = method.getAnnotation(BugReport.class);
                System.out.println("Bug Description: " + report.description());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
