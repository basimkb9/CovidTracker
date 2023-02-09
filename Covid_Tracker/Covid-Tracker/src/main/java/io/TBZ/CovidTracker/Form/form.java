package io.TBZ.CovidTracker.Form;

import java.util.Scanner;

abstract class Test
{
    int ycount=0,ncount=0;
    long age = 0;
    String gender = null;
    String name1 = null;
    char ch = 0;
    public abstract void assessment();
}


public class form extends Test{
    public void assessment(){

        Scanner sc = new Scanner(System.in);


        System.out.println("--------------------------------------------------------------");
        System.out.println("\n");
        System.out.println("\n               CORONA TESTING FORM FOR USER");

        System.out.println("=============================================================");
        System.out.println("Enter your age: ");
        age = sc.nextLong();
        System.out.println("What is your Name? ");
        name1 = sc.next();
        System.out.println("What is your Gender? (male/female/other): ");
        gender = sc.next();

        System.out.println("Do you have Severe Dry cough, Fever or Tiredness? (y/n) ");
        ch = sc.next().charAt(0);

        if(ch == 'y' || ch == 'Y')
        {
            ycount+=1;
        }

        else if(ch == 'n' || ch == 'N')
        {
            ncount+=1;
        }

        else
        {
            System.out.println("You did not enter correct option.");
        }

        System.out.println("Do you have Severe and constant pain or pressure in the chest? (y/n) ");
        ch = sc.next().charAt(0);

        if(ch == 'y' || ch == 'Y')
        {
            ycount+=1;
        }

        else if(ch == 'n' || ch == 'N')
        {
            ncount+=1;
        }

        else
        {
            System.out.println("You did not enter correct option.");
        }

        System.out.println("Extreme difficulty breathing (such as gasping for air, being unable to talk without catching your breath, severe wheezing, nostrils flaring) (y/n): ");
        ch = sc.next().charAt(0);

        if(ch == 'y' || ch == 'Y')
        {
            ycount+=1;
        }

        else if(ch == 'n' || ch == 'N')
        {
            ncount+=1;
        }

        else
        {
            System.out.println("You did not enter correct option.");
        }

        System.out.println("Do you feel Unconscious or is it sometimes very difficult to wake up (y/n):");
        ch = sc.next().charAt(0);

        if(ch == 'y' || ch == 'Y')
        {
            ycount+=1;
        }

        else if(ch == 'n' || ch == 'N')
        {
            ncount+=1;
        }

        else
        {
            System.out.println("You did not enter correct option.");
        }

        System.out.println("Any Signs of low blood pressure (too weak to stand, dizziness, lightheaded, feeling cold, pale, clammy skin) (y/n): ");
        ch = sc.next().charAt(0);

        if(ch == 'y' || ch == 'Y')
        {
            ycount+=1;
        }

        else if(ch == 'n' || ch == 'N')
        {
            ncount+=1;
        }

        else
        {
            System.out.println("You did not enter correct option.");
        }

        System.out.println("Dehydration (dry lips and mouth, not urinating much, sunken eyes) (y/n): ");
        ch = sc.next().charAt(0);

        if(ch == 'y' || ch == 'Y')
        {
            ycount+=1;
        }

        else if(ch == 'n' || ch == 'N')
        {
            ncount+=1;
        }

        else
        {
            System.out.println("You did not enter correct option.");
        }


        if(ycount >= 4){

            System.out.println("=============================================================");
            System.out.println("Name: " + name1);
            System.out.println("Age: " + age);
            System.out.println("Gender: " + gender);
            System.out.println("\nFinal Report: ");
            System.out.println("Based on your symptoms, you have to isolate yourself, \nyou may need urgent medical care. Please go to the nearest emergency department.");
            System.out.println("=============================================================");
        }

        else if(ycount == 3 && ncount == 3){

            System.out.println("=============================================================");
            System.out.println("Name: " + name1);
            System.out.println("Age: " + age);
            System.out.println("Gender: " + gender);
            System.out.println("\nFinal Report: ");
            System.out.println("As per your assessment, it is recommended that you have a Corona virus test, \nBut its upto your choice whether you want to have a test or not, \nbut WE Recommend you to have one.");
            System.out.println("=============================================================");
        }

        else {

            System.out.println("=============================================================");
            System.out.println("Name: " + name1);
            System.out.println("Age: " + age);
            System.out.println("Gender: " + gender);
            System.out.println("\nFinal Report: ");
            System.out.println("No COVID-19 testing needed at this time\nBased on the answers given, you do not need to get tested.\nLearn more about COVID-19 and steps you can take to protect yourself and others");
            System.out.println("=============================================================");
        }
    }
}
