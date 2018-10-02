//Hotel Management System
import java.io.*;
import java.lang.*;
import java.util.*;
class oopm_project //Main/Driver class
{
    public static void main(String args[]) throws Exception
    {
        System.out.println("-------------------------------------------------");
        System.out.println("-------Welcome to Hotel Reservation System-------");
        System.out.println("-------------------------------------------------");
        menu();
    }
    public static void menu() throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("MENU:");
        System.out.println("1.Add a reservation");
        System.out.println("2.Delete a reservation");
        System.out.println("3.Edit a reservation");
        System.out.println("4.Search a reservation");
        System.out.println("5.Show all reservations");
        System.out.println("6.Logout");
        System.out.println("7.Exit");
        System.out.println("Enter your choice");
        int ch=sc.nextInt();
        switch(ch)
        {
            case 1:AddReserv ad=new AddReserv();
                   break;
            case 2:DeleteReserv de=new DeleteReserv();
                   break;
            case 3:EditReserv ed=new EditReserv();
                   break;
            case 4:SearchReserv se=new SearchReserv();
                   break;
            case 5:ShowReserv sh=new ShowReserv();
                   break;
            /*case 6://Don't Know what to do here bc
                   break;*/
            case 7:System.exit(0);
                   break;
            default:System.out.println("Invalid Choice");
                    System.exit(0);
                    break;                                                  
        }
        sc.close();
    }
    
}
class AddReserv
{
    AddReserv() throws Exception
    {
        String fname,fno_people,fadd;
        int fstart_dd,fstart_mm,fend_dd,fend_mm,froom_no;
        int j,booked=0;
        File file=new File("ReservDetails.txt");
        Scanner sc=new Scanner(file);
        Scanner sco=new Scanner(System.in);
        System.out.println("Enter room number to book:");
        int room_no=sco.nextInt();
        System.out.println("Enter Check-In Date:");
        System.out.println("Month:");
        int start_mm=sco.nextInt();
        System.out.println("Date:");
        int start_dd=sco.nextInt();
        System.out.println("Enter Check-Out Date:");
        System.out.println("Month:");
        int end_mm=sco.nextInt();
        System.out.println("Date:");
        int end_dd=sco.nextInt();
        while(sc.hasNextLine())
        {
            froom_no=Integer.parseInt(sc.next());
            fname=sc.next();
            fno_people=sc.next();
            fadd=sc.next();
            fstart_dd=Integer.parseInt(sc.next());
            fstart_mm=Integer.parseInt(sc.next());
            fend_dd=Integer.parseInt(sc.next());
            fend_mm=Integer.parseInt(sc.next());
            if(room_no==froom_no)
            {
                if(start_mm==fstart_mm && end_mm==fend_mm)
                {
                    if((start_dd==fstart_dd) || (start_dd==fend_dd) || (end_dd==fstart_dd) || (end_dd==fend_dd))
                    {
                        System.out.println("Selected room is booked from "+fstart_dd+"/"+fstart_mm+" to "+fend_dd+"/"+fend_mm);
                        System.out.println("Please select another room");
                        booked=1;
                        break;
                    }
                    else if((start_dd>fstart_dd && start_dd<fend_dd) || (end_dd>fstart_dd && end_dd<fend_dd))
                    {
                        System.out.println("Selected room is booked from "+fstart_dd+"/"+fstart_mm+" to "+fend_dd+"/"+fend_mm);
                        System.out.println("Please select another room");
                        booked=1;
                        break;
                    }
                }
                else if((start_mm<fstart_mm) && (end_mm==fstart_mm))
                {
                    if((end_dd==fend_dd) || (end_dd>fstart_dd && end_dd<fend_dd))
                    {
                        System.out.println("Selected room is booked from "+fstart_dd+"/"+fstart_mm+" to "+fend_dd+"/"+fend_mm);
                        System.out.println("Please select another room");
                        booked=1;
                        break;
                    }
                }
                else if((start_mm==fstart_mm) && (end_mm>fstart_mm))
                {
                    if((start_dd==fstart_dd) || (start_dd>fstart_dd && start_dd<fend_dd))
                    {
                        System.out.println("Selected room is booked from "+fstart_dd+"/"+fstart_mm+" to "+fend_dd+"/"+fend_mm);
                        System.out.println("Please select another room");
                        booked=1;
                        break; 
                    }
                }
            }
            
        }
        if(booked==1)
        {
            System.out.println("Enter new room number:");
            room_no=sco.nextInt();
        }
        System.out.println("Enter your name:");
        String name=sco.next();
        System.out.println("Enter no of people:");
        int no_people=sco.nextInt();
        System.out.println("Enter your address:");
        String add=sco.next();
        FileWriter fr=new FileWriter(file, true);
        BufferedWriter br=new BufferedWriter(fr);
        br.newLine();
        br.write(" "+room_no+" "+name+" "+no_people+" "+add+" "+start_dd+" "+start_mm+" "+end_dd+" "+end_mm);
        System.out.println("Press any key to exit");
        sco.next();
        br.close();
        sc.close();
        sco.close();
        fr.close();
    }
}
class ShowReserv
{
    ShowReserv() throws Exception
    {
        File file=new File("E:/Vinayak/Impor/College/SE/OOPM/Mini Project/ReservDetails.txt");
        Scanner sc=new Scanner(file);
        String print;
        System.out.println("Room Number  Name of Customer  No of People      Address  Check-In Date  Check-Out Date");
        do
        {
            System.out.println("------------------------------------------------");
            print=sc.nextLine();
            System.out.println(print);
            System.out.println("------------------------------------------------");
        }while(sc.hasNextLine());
        sc.close();
        System.out.println("");
    }
}
class DeleteReserv
{
    DeleteReserv() throws Exception
    {
        String fname,fno_people,fadd;
        int fstart_dd,fstart_mm,fend_dd,fend_mm,froom_no,room_no;
        File fileo=new File("E:/Vinayak/Impor/College/SE/OOPM/Mini Project/ReservDetails.txt");
        File filen=new File("E:/Vinayak/Impor/College/SE/OOPM/Mini Project/temp.txt");
        Scanner sc=new Scanner(fileo);
        Scanner sco=new Scanner(System.in);
        FileWriter fr=new FileWriter(filen);
        BufferedWriter br=new BufferedWriter(fr);
        int deleted=0;
        System.out.println("Enter room number to delete the reservation:");
        room_no=sco.nextInt();
        while(sc.hasNextLine())
        {
            froom_no=Integer.parseInt(sc.next());
            fname=sc.next();
            fno_people=sc.next();
            fadd=sc.next();
            fstart_dd=Integer.parseInt(sc.next());
            fstart_mm=Integer.parseInt(sc.next());
            fend_dd=Integer.parseInt(sc.next());
            fend_mm=Integer.parseInt(sc.next());
            if(room_no!=froom_no)
            {
                br.newLine();
                br.write(" "+froom_no+" "+fname+" "+fno_people+" "+fadd+" "+fstart_dd+" "+fstart_mm+" "+fend_dd+" "+fend_mm);
            }
            else
            {
                deleted=1;
            }
        }    
        if(deleted==0)
        {
            System.out.println("No such record found");
        }
        else
        {
            System.out.println("Record Deleted Successfully");
        }
        sc.close();
        br.close();
        fr.close();
        //System.out.println(filen.getName());
        //System.out.println(fileo.getName());
        if(fileo.delete())
        {
            System.out.println("..");
        }
        else
        {
            System.out.println("Error while deleting");
        }
        boolean rename=filen.renameTo(new File("ReservDetails.txt"));
        if(rename)
        {
            System.out.println("..");
        }
        else
        {
            System.out.println("Error while renaming");
        }
        System.out.println("Press any key to return to menu");
        sco.next();
        sco.close();
    }
}
class EditReserv
{
    EditReserv() throws Exception
    {
        String fname,fno_people,fadd,name,add;
        int fstart_dd,fstart_mm,fend_dd,fend_mm,froom_no,room_no,no_people;
        File fileo=new File("E:/Vinayak/Impor/College/SE/OOPM/Mini Project/ReservDetails.txt");
        File filen=new File("E:/Vinayak/Impor/College/SE/OOPM/Mini Project/temp.txt");
        Scanner sc=new Scanner(fileo);
        Scanner sco=new Scanner(System.in);
        FileWriter fr=new FileWriter(filen);
        BufferedWriter br=new BufferedWriter(fr);
        int edited=0;
        System.out.println("Enter room number of customer to edit record:");
        room_no=sco.nextInt();
        while(sc.hasNextLine())
        {
            froom_no=Integer.parseInt(sc.next());
            fname=sc.next();
            fno_people=sc.next();
            fadd=sc.next();
            fstart_dd=Integer.parseInt(sc.next());
            fstart_mm=Integer.parseInt(sc.next());
            fend_dd=Integer.parseInt(sc.next());
            fend_mm=Integer.parseInt(sc.next());
            if(room_no!=froom_no)
            {
                br.newLine();
                br.write(" "+froom_no+" "+fname+" "+fno_people+" "+fadd+" "+fstart_dd+" "+fstart_mm+" "+fend_dd+" "+fend_mm);
            }
            else
            {
                edited=1;
                System.out.println("Record Found...");
                System.out.println("EXISTING DETAILS");
                System.out.println("Room number:"+room_no);
                System.out.println("Name:"+fname);
                System.out.println("Number of People:"+fno_people);
                System.out.println("Address:"+fadd);
                System.out.println("Check-In Date: "+fstart_dd+"/"+fstart_mm);
                System.out.println("Check-Out Date: "+fend_dd+"/"+fend_mm);
                System.out.println("ENTER NEW DETAILS:");
                System.out.println("Number of People");
                no_people=sco.nextInt();
                System.out.println("Address");
                add=sco.next();
                br.newLine();
                br.write(" "+froom_no+" "+fname+" "+no_people+" "+add+" "+fstart_dd+" "+fstart_mm+" "+fend_dd+" "+fend_mm);
            }
        }
        sc.close();
        br.close();
        fr.close();
        if(fileo.delete())
        {
            System.out.println("..");
        }
        else
        {
            System.out.println("Error while deleting");
        }
        boolean rename=filen.renameTo(new File("ReservDetails.txt"));
        if(rename)
        {
            System.out.println("..");
        }
        else
        {
            System.out.println("Error while renaming");
        }
        if(edited==0)
        {
            System.out.println("Record doesn't exist");
        }
        else
        {
            System.out.println("Record Edited Successfully");
        }
        System.out.println("Press any key to return to menu");
        sco.next();
        sco.close();
    }
}
class SearchReserv
{
    SearchReserv() throws Exception
    {
        File file=new File("E:/Vinayak/Impor/College/SE/OOPM/Mini Project/ReservDetails.txt");
        Scanner sc=new Scanner(file);
        Scanner sco=new Scanner(System.in);
        String fname,fadd,name="0",fno_people;
        int froom_no,fstart_dd,fstart_mm,fend_dd,fend_mm,room_no=0;
        System.out.println("Enter your choice");
        System.out.println("1.Search by room number");
        System.out.println("2.Search by name");
        int ch=sco.nextInt();
        int found=0;
        switch(ch)
        {
            case 1:System.out.println("Enter room number of customer:");
                   room_no=sco.nextInt();
                   break;
            case 2:System.out.println("Enter name of customer");
                   name=sco.next();
                   break;
            default:System.out.println("Invalid choice");
                    break;  
        }
        while(sc.hasNextLine())
        {
            froom_no=Integer.parseInt(sc.next());
            fname=sc.next();
            fno_people=sc.next();
            fadd=sc.next();
            fstart_dd=Integer.parseInt(sc.next());
            fstart_mm=Integer.parseInt(sc.next());
            fend_dd=Integer.parseInt(sc.next());
            fend_mm=Integer.parseInt(sc.next());
            if(room_no==froom_no || name==fname)
            {
                found=1;
                System.out.println("Record Found..");
                System.out.println("Room number:"+froom_no);
                System.out.println("Name:"+fname);
                System.out.println("Number of People:"+fno_people);
                System.out.println("Address:"+fadd);
                System.out.println("Check-In Date: "+fstart_dd+"/"+fstart_mm);
                System.out.println("Check-Out Date: "+fend_dd+"/"+fend_mm);
            }
        }
        if(found==0)
        {
            System.out.println("Record dosen't exist..!!");
        }
        sc.close();
        System.out.println("Press any key to return to menu");
        sco.next();
        sco.close();
    }
}