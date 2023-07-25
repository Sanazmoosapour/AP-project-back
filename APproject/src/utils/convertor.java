package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import  Objects.user;
import Objects.book;
import database.Table;
import database.database;

public class convertor {
    public static user stringToUser(String data){
        user user=new user();
        String[] array=data.split("&&");
        user.setName(array[0].split(":")[1]);
        user.setFamilyName(array[1].split(":")[1]);
        user.setEmail(array[2].split(":")[1]);
        user.setPassword(array[3].split(":")[1]);
        user.setCash(Double.parseDouble(array[4].split(":")[1]));
        return user;
    }
    public static String UserToString(user user){
        String output="Name:"+user.getName()+"&&FamilyName:"+user.getFamilyName()+"&&EmailAddress:"+user.getEmail()+"&&Password:"+user.getPassword()+"&&Cash:"+user.getCash();
        return output;
    }

    public static String UsersToStringforusers(user user){
        String output="Name:"+user.getName()+"&&Password:"+user.getPassword()+"\n";
        return output;
    }
    public static user stringToUserLogIn(String data){
        user user=new user();
        String[] array=data.split("&&");
        user.setName(array[0].split(":")[1]);
        user.setPassword(array[1].split(":")[1]);
        return user;
    }
    public static book stringToBook(String data){
        String[] book=data.split("&&");
        book temp=new book();
        temp.setName(book[0].split(":")[1]);
        temp.setPrice(Double.parseDouble(book[1].split(":")[1]));
        temp.setWriter(book[2].split(":")[1]);
        if(book[3].split(":")[1].equals("no"))
            temp.setAudioBook(false);
        else
            temp.setAudioBook(true);
        if(book[4].split(":")[1].equals("no"))
            temp.setBuy(false);
        else
            temp.setBuy(true);
        temp.setRating(Double.parseDouble(book[5].split(":")[1]));
        temp.setNumberOfPages(Integer.parseInt(book[6].split(":")[1]));
        if(book[7].split(":")[1].equals("no"))
            temp.setLike(false);
        else
            temp.setLike(true);

        if(book[8].split(":")[1].equals("no"))
            temp.setRead(false);
        else
            temp.setRead(true);
        temp.setSells(Integer.parseInt(book[9].split(":")[1]));
        temp.setNumberoflikes(Integer.parseInt(book[10].split(":")[1]));
        if(book[11].split(":")[1].equals("no"))
            temp.setNewrelease(false);
        else
            temp.setNewrelease(true);
        temp.setRating(Double.parseDouble(book[12].split(":")[1]));
        temp.setImage(book[13].split("::")[1]);
        return temp;


    }

    public static user loadUser(String name) throws FileNotFoundException {
        database instance=database.getInstance();
        instance.addTable(name);
        FileReader fr=new FileReader(instance.getTable(name).getPath());
        Scanner sc=new Scanner(fr);
        user user=convertor.stringToUser(sc.nextLine());
        while(sc.hasNextLine()){
            user.getBooks().add(convertor.stringToBook(sc.nextLine()));
        }
        return user;
    }
    public static List<book> loadbooks() throws IOException {
        List<book> books=new ArrayList<>();
        database instance=database.getInstance();
        FileReader fr=new FileReader(instance.getTable("books").getPath());
        Scanner sc=new Scanner(fr);
        while (sc.hasNextLine()){
            books.add(convertor.stringToBook(sc.nextLine()));
        }
        fr.close();
        sc.close();
        return books;
    }
    public static String bookToString(book b){
        String book="Name:"+b.getName()+"&&price:"+b.getPrice()+"&&witer:"+b.getWriter()+"&&audio:";
        if(b.isAudioBook())
            book+="yes&&buy:";
        else
            book+="no&&buy:";

        if(b.isBuy())
            book+="yes&&rate:";
        else
            book+="no&&rate:";
        book+=b.getRating()+"&&pagenumber:"+b.getNumberOfPages()+"&&like:";
        if(b.isLike())
            book+="yes&&read:";
        else
            book+="no&&read:";

        if(b.isRead())
            book+="yes";
        else
            book+="no";
        book+="&&sells:"+b.getSells()+"&&numberoflikes:"+b.getNumberoflikes()+"&&newrelease:";
        if(b.isNewrelease())
            book+="yes";
        else
            book+="no";
        book+="&&rating:"+b.getRating()+"&&image::"+b.getImage();

        return book;
    }

    public static String userwithbooksToString(user user){
        String result="";
        result+=convertor.UserToString(user)+"\n";
        for(int i=0;i<user.getBooks().size();i++){
            if(i==user.getBooks().size()-1)
                result+=convertor.bookToString(user.getBooks().get(i));
            else
                result+=convertor.bookToString(user.getBooks().get(i))+"\n";
        }

        return result;
    }
    public static String booksToString(List<book> books){
        String output="";
        for(int i=0;i<books.size();i++){
            if(i==books.size()-1)
                output+=convertor.bookToString(books.get(i));
            else
                output+=convertor.bookToString(books.get(i))+"\n";
        }
        return output;
    }
    public static String booksToStringsort(List<book> books){
        String output="";
        for(int i=0;i<books.size();i++){
            if(i==books.size()-1)
                output+=books.get(i).getName()+"&&"+books.get(i).getImage();
            else
                output+=books.get(i).getName()+"&&"+books.get(i).getImage()+"~~";
        }
        return output;
    }

}
