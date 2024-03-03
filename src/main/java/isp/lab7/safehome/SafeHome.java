package isp.lab7.safehome;
import com.sun.tools.jdeprscan.scan.Scan;


import java.util.Scanner;

public class SafeHome {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Door Control System");
        System.out.println("----------------------------------");

        // Prompt for user type selection
        UserType userType = promptUserType();

        if (userType == UserType.ADMIN) {
            handleAdminInterface();
        } else if (userType == UserType.TENANT) {
            handleTenantInterface();
        }

        scanner.close();
    }

    public static UserType promptUserType() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Select User Type:");
        System.out.println("1. Admin");
        System.out.println("2. Tenant");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                return UserType.ADMIN;
            case 2:
                return UserType.TENANT;
            default:
                System.out.println("Invalid choice. Defaulting to Tenant.");
                return UserType.TENANT;
        }
    }

    private static void handleAdminInterface() {

        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.println("\nAdmin Interface");
            System.out.println("---------------");
            System.out.println("Select an option:");
            System.out.println("1. Add Tenant");
            System.out.println("2. Remove Tenant");
            System.out.println("3. View Access Logs");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTenant();
                    break;
                case 2:
                    removeTenant();
                    break;
                case 3:
                    viewAccessLogs();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleTenantInterface() {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.println("\nTenant Interface");
            System.out.println("----------------");
            System.out.println("Select an option:");
            System.out.println("1. Enter Pin to Open/Close Door");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    enterPinToOpenCloseDoor();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTenant() {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the Pin for the new Tenant:");
        String pin = scanner.nextLine();

        // Perform necessary logic to add the new Tenant with the provided pin
        // ...

        System.out.println("Tenant added successfully.");
    }

    private static void removeTenant() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the Pin of the Tenant to remove:");
        String pin = scanner.nextLine();

        System.out.println("Tenant removed successfully.");
    }

    private static void viewAccessLogs() {


        System.out.println("Access Logs:");

    }

    private static void enterPinToOpenCloseDoor() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your Pin:");
        String pin = scanner.nextLine();

        try {
            DoorStatus doorStatus = door.enterPin(pin);
            if (doorStatus == DoorStatus.OPEN) {
                System.out.println("Door is open.");
            } else {
                System.out.println("Door is closed.");
            }
        } catch (InvalidPinException e) {
            System.out.println("Invalid pin entered. Door remains closed.");
        } catch (TooManyAttemptsException e) {
            System.out.println("Too many consecutive failed attempts. Door is locked.");
        }
    }

    private enum UserType {
        ADMIN,
        TENANT
    }
    }
}
