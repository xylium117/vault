import java.util.Scanner;

public class TimeNDate{
    public static void main(String[] args) {
        // force proper input
        Scanner sc = new Scanner(System.in);
        String in = "";
        do {
            System.out.print("Enter a date (dd/mm/yyyy): ");
            in = sc.next();
            if (isValidStr(in)) break;
            System.out.println("Invalid format. Try again!");
        } while (true);
        System.out.println(in);
        
        System.out.println("\n----\n");
        
        // addDays function
        String date = "15/03/2023";
        int N = 20;
        String newDate = addDays(date, N);
        System.out.println("Original date: " + date);
        System.out.println("After adding " + N + " days: " + newDate);
        
        System.out.println("\n----\n");
        
        // monthLen functions
        int m = 2;
        int y = 2024;
        System.out.println(monthName(m) + ", " + y + ": " + monthLen(m, y) + " days");
        System.out.println(monthName(m) + ", " + y + ": " + monthLen2(m, y) + " days");
        System.out.println(monthName(m) + ", " + y + ": " + monthLen3(m, y) + " days");
        
        System.out.println("\n----\n");

        // isLeap function
        int leapYear = 2020;
        int nonLeapYear = 2021;
        System.out.println("Is " + leapYear + " a leap year? " + isLeap(leapYear));
        System.out.println("Is " + nonLeapYear + " a leap year? " + isLeap(nonLeapYear));
        
        System.out.println("\n----\n");

        //  daysBetween function
        String startDate = "15/03/2023";
        String endDate = "5/4/2023";
        int n = daysBetween(startDate, endDate);
        System.out.println("Number of days: " + n);
        
        System.out.println("\n----\n");

        // isValid function
        int day = 29;
        int invalidMonth = 13;
        int validMonth = 2;
        int validYear = 2024;
        int invalidDay = 32;
        System.out.println("Is \"29/02/2024\" valid? " + isValid(day, validMonth, validYear));
        System.out.println("Is \"32/01/2023\" valid? " + isValid(invalidDay, 1, 2023));
        System.out.println("Is \"15/13/2023\" valid? " + isValid(15, invalidMonth, 2023));
    }

    public static String addDays(String date, int N){
        try {
            String[] p = date.split("/");
            int d = Integer.parseInt(p[0]) + N;
            int m = Integer.parseInt(p[1]);
            int y = Integer.parseInt(p[2]);
            while (d > monthLen(m , y)){
                d -= monthLen(m , y);
                m++;
                if (m > 12){ 
                    m = 1; 
                    y++;
                }
            }
            return d + "/" + m + "/" + y;
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            return "Invalid date format.";
        }
    }

    public static int monthLen(int m, int y){
        switch (m){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 2:
                return (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0)) ? 29 : 28;
            case 4: case 6: case 9: case 11: 
                return 30;
            default: 
                return -1;
        }
    }

    public static int monthLen2(int m, int y){
        return (m == 2) ? (isLeap(y) ? 29 : 28) : (m < 8 ? 30 + m % 2 : 31 - m % 2);
    }

    public static int monthLen3(int m, int y){
        int[] monthLen = {31, isLeap(y) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return monthLen[m - 1];
    }

    public static boolean isLeap(int y){
        return (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
    }

    public static int daysBetween(String sD, String eD){
        int n = 0;
        String curr = "";
        while (true){
            curr = addDays(sD, n++);
            if (isEqual(curr, eD)) break;
        }
        return n;
    }

    public static boolean isValid(int d, int m, int y){
        if (y < 1 || m < 1 || m > 12 || d < 1) 
            return false;

        int[] monthLen = {31, isLeap(y) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return d <= monthLen[m - 1];
    }
    
    public static boolean isValidStr(String d){
        String[] p = d.split("/");
        try{
            return isValid(
                        Integer.parseInt(p[0]),
                        Integer.parseInt(p[1]),
                        Integer.parseInt(p[2]));
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            return false;
        }
    }

    public static String monthName(int m){
        String[] name = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JULY", "AUG", "SEP", "OCT", "NOV", "DEC"};
        return name[m - 1];
    }
    
    public static boolean isEqual(String d1, String d2){
        String[] p1 = d1.split("/");
        String[] p2 = d2.split("/");
        
        if (p1.length != p2.length) return false;
        for (int i = 0; i < p1.length; i++) 
            if ( Integer.parseInt(p1[i]) != Integer.parseInt(p2[i])) 
                return false;
        return true;
    }
}
