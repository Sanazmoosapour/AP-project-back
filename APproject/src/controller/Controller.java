package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import utils.convertor;
import Objects.*;
import database.*;

public class Controller {
    private String logIn(user user) throws FileNotFoundException {
        database instance=database.getInstance();
        //instance.add();
        Table users=instance.getTable("users");
        Map<String,String> map =users.getusers();
        if(!map.get(user.getName()).equals(user.getPassword()))
            return "wrong password or name";
        //System.out.println(user.getName());
        instance.addTable(user.getName());
        //new File(instance.getTable(user.getName()).getPath()).renameTo(new File("src/data/"+Thread.currentThread().getName()+".txt"));
        //Files.copy()
        return "succesfull&&"+user.getName();
    }
    private String signUp(user user) throws IOException {


        if(user.getPassword().length()<8)
            return "at least 8 character require";
        if(user.getPassword().toLowerCase().equals(user.getPassword()) ||
                user.getPassword().toUpperCase().equals(user.getPassword()))
            return "use both uppercase letters and lowercase";
        if(!user.getPassword().contains("1") && !user.getPassword().contains("2") && !user.getPassword().contains("3") &&
                !user.getPassword().contains("4") && !user.getPassword().contains("5") && !user.getPassword().contains("6") &&
                !user.getPassword().contains("7") && !user.getPassword().contains("8") && !user.getPassword().contains("9") &&
                !user.getPassword().contains("0") )
            return "use at least one number";
        if(user.getPassword().contains(user.getName()))
            return "you cant use your name in password";

        database instance=database.getInstance();
        //instance.add();
        Table users=instance.getTable("users");
        Map<String,String> map =users.getusers();
        if(map.containsKey(user.getName()) || map.containsValue(user.getPassword()))
            return "this password or name have been used";
        users.insertInUsers(map,user);
        instance.addTable(user.getName());
        instance.getTable(user.getName()).insertUser(user);

        return "successfull";

    }
    private String viewBook(String data) throws IOException {
        String username=data.split("&&")[0].split(":")[1];
        String bookname=data.split("&&")[1].split(":")[1];
        database.getInstance().addTable(username);
        user user=convertor.loadUser(username);
        List<book> library=convertor.loadbooks();
        if(user.getBooks().stream().filter(a->a.getName().equals(bookname)).collect(Collectors.toList()).size()>0)
            return convertor.bookToString(user.getBooks().stream().filter(a -> a.getName().equals(bookname)).collect(Collectors.toList()).get(0));
        else
            return convertor.bookToString(library.stream().filter(a->a.getName().equals(bookname)).collect(Collectors.toList()).get(0));
    }
    private String like(String data) throws IOException {
        String username=data.split("&&")[0].split(":")[1];
        String bookname=data.split("&&")[1].split(":")[1];
        database instance=database.getInstance();
        instance.addTable(username);
        user user=convertor.loadUser(username);
        List<book> library=convertor.loadbooks();
        book b=user.getBooks().stream().filter(a->a.getName().equals(bookname)).collect(Collectors.toList()).get(0);
        b.setLike(true);
        b.setNumberoflikes(b.getNumberoflikes()+1);
        instance.getTable("books").insertBooks(library);
        //user.getBooks().add(b);
        instance.getTable(username).insertUser(user);
        return "successfull";

    }
    private String rate(String data) throws IOException {
        String username=data.split("&&")[0].split(":")[1];
        String bookname=data.split("&&")[1].split(":")[1];
        double rate=Double.parseDouble(data.split("&&")[2].split(":")[1]);
        database instance=database.getInstance();
        instance.addTable(username);
        user user=convertor.loadUser(username);
        List<book> library=convertor.loadbooks();
        if(user.getBooks().stream().filter(a->a.getName().equals(bookname)).collect(Collectors.toList()).size()==0)
            return "you cant rate a book that you havent buy yet";
        book b=user.getBooks().stream().filter(a->a.getName().equals(bookname)).collect(Collectors.toList()).get(0);
        b.setRating((b.getRating()+rate)/2);
        book bo=library.stream().filter(a->a.getName().equals(bookname)).collect(Collectors.toList()).get(0);
        bo.setRating((bo.getRating()+rate)/2);
        //user.getBooks().add(b);
        instance.getTable("books").insertBooks(library);
        instance.getTable(username).insertUser(user);
        return "successfull";
    }
    private String read(String data) throws IOException {
        String username=data.split("&&")[0].split(":")[1];
        String bookname=data.split("&&")[1].split(":")[1];
        database instance=database.getInstance();
        instance.addTable(username);
        user user=convertor.loadUser(username);
        List<book> library=convertor.loadbooks();
        book b=user.getBooks().stream().filter(a->a.getName().equals(bookname)).collect(Collectors.toList()).get(0);
        b.setRead(true);
        //user.getBooks().add(b);
        instance.getTable(username).insertUser(user);
        return "successfull";
    }
    private String buy(String data) throws IOException {
        String username=data.split("&&")[0].split(":")[1];
        String bookname=data.split("&&")[1].split(":")[1];
        //String pass=data.split("&&")[2].split(":")[1];

        database instance=database.getInstance();
        instance.addTable(username);
        user user=convertor.loadUser(username);
        List<book> library=convertor.loadbooks();
        book b=library.stream().filter(a->a.getName().equals(bookname)).collect(Collectors.toList()).get(0);
        System.out.println("usercash:"+user.getCash()+"bookprice: "+b.getPrice());
        if(user.getCash()<b.getPrice())
            return "not enough money";
        b.setBuy(true);
        b.setSells(b.getSells()+1);
        instance.getTable("books").insertBooks(library);
        user.setCash(user.getCash()-b.getPrice());
        user.getBooks().add(b);
        instance.getTable(username).insertUser(user);
        return "successfull";
    }
    private String account(String data) throws FileNotFoundException {
        String username=data.split(":")[1];
        database instance=database.getInstance();
        instance.addTable(username);
        user user=convertor.loadUser(username);
        return "name:"+user.getName()+"&&familyname:"+user.getFamilyName()+"&&email:"+user.getEmail()+"&&cash:"+user.getCash();
    }
    private String changeinfo(String data) throws IOException {
        String[] arr=data.split("&&");
        String oldname=arr[0].split(":")[1];
        String newname=arr[1].split(":")[1];
        String newfamilyname=arr[2].split(":")[1];
        String newemail=arr[3].split(":")[1];
        System.out.println("why");
        database instance=database.getInstance();
        instance.addTable(oldname);
        boolean result=new File(instance.getTable(oldname).getPath()).renameTo(new File("src/data/"+newname+".txt"));
        System.out.println(result);
        instance.getTables().remove(oldname);
        instance.addTable(newname);

        user user=convertor.loadUser(newname);

        user.setName(newname);
        user.setFamilyName(newfamilyname);
        user.setEmail(newemail);

        instance.getTable(newname).insertUser(user);


        Map<String,String> map=instance.getTable("users").getusers();
        map.remove(oldname);
        map.put(newname,user.getPassword());
        instance.getTable("users").insertInUsers(map,user);



        //instance.getTable(oldname);
        //instance.addTable(newname);

        return "successfull";

    }
    private String raisecredit(String data) throws IOException {
        System.out.println("raise:"+data);
        String username=data.split("&&")[0].split(":")[1];
        String amountOfMoney=data.split("&&")[1].split(":")[1];
        String pass=data.split("&&")[2].split(":")[1];
        if(!pass.equals("3090"))
            return "wrong password";
        database instance=database.getInstance();
        instance.addTable(username);
        user user=convertor.loadUser(username);
        user.setCash(user.getCash()+Double.parseDouble(amountOfMoney));
        instance.getTable(username).insertUser(user);
        return "successfull";
    }
    private String vipAccount(String data){
        return "";
    }
    private List<book> sortBooks(String data) throws IOException {
        database instance=database.getInstance();
        List<book> books=convertor.loadbooks();

        System.out.println(data);
        String username=data.split("&&")[0].split(":")[1];
        String sortBy=data.split("&&")[1].split(":")[1];
        user user=convertor.loadUser(username);
        switch (sortBy){
            case "fav":
                return books.stream().sorted((a,b)->a.getNumberoflikes()-b.getNumberoflikes()).collect(Collectors.toList());
            case "favebook":
                return books.stream().filter(a->!a.isAudioBook()).sorted((a,b)-> (int) (a.getRating()-b.getRating())).collect(Collectors.toList());
            case "favaudiobook":
                return books.stream().filter(a->a.isAudioBook()).sorted((a,b)-> (int) (a.getRating()-b.getRating())).collect(Collectors.toList());
            case "audiobook":
                return books.stream().filter(a->a.isAudioBook()).collect(Collectors.toList());
            case "ebook":
                return books.stream().filter(a->!a.isAudioBook()).collect(Collectors.toList());
            case "popular":
                return books.stream().sorted((a,b)-> (int) (a.getRating()-b.getRating())).collect(Collectors.toList());
            case "newrelease":
                return books.stream().filter(a->a.isNewrelease()).collect(Collectors.toList());
            case "newreleaseebook":
                return books.stream().filter(a->a.isNewrelease()).filter(a->!a.isAudioBook()).collect(Collectors.toList());
            case "newreleaseaudiobook":
                return books.stream().filter(a->a.isNewrelease()).filter(a->a.isAudioBook()).collect(Collectors.toList());
            case "topselling":
                return books.stream().sorted((a,b)->a.getSells()-b.getSells()).collect(Collectors.toList());
            case "topsellingebook":
                return books.stream().sorted((a,b)->a.getSells()-b.getSells()).filter(a->!a.isAudioBook()).collect(Collectors.toList());
            case "topsellingaudiobook":
                return books.stream().sorted((a,b)->a.getSells()-b.getSells()).filter(a->a.isAudioBook()).collect(Collectors.toList());
            case "expensive":
                return books.stream().sorted((a,b)-> (int) (a.getPrice()-b.getPrice())).collect(Collectors.toList());
            case "cheap":
                return books.stream().sorted((a,b)-> (int) (b.getPrice()-a.getPrice())).collect(Collectors.toList());
            case "read":
                return user.getBooks().stream().filter(a->a.isRead()).collect(Collectors.toList());
            case "like":
                return user.getBooks().stream().filter(a->a.isLike()).collect(Collectors.toList());
            case "all":
                return user.getBooks();
        }
        return null;
    }
    private book find(String data) throws IOException {
        List<book> books=convertor.loadbooks();
        String username=data.split("&&")[0].split(":")[1];
        String bookname=data.split("&&")[1].split(":")[1];
        return books.stream().filter(a->a.getName().equals(bookname)).collect(Collectors.toList()).get(0);
    }
    private String ratewant(String data) throws IOException {
        String username=data.split("&&")[0].split(":")[1];
        String bookname=data.split("&&")[1].split(":")[1];
        List<book> books=convertor.loadbooks();
        return  String.valueOf(books.stream().filter(a->a.getName().equals(bookname)).collect(Collectors.toList()).get(0).getRating());
    }
    public String run(String command,String data) throws IOException {

        switch (command){
            case "logIn":
                return logIn(convertor.stringToUserLogIn(data));
            case "signUp":
                return signUp(convertor.stringToUser(data));
            case "viewBook":
                return viewBook(data);
            case "rate":
                return rate(data);
            case "like":
                return like(data);
            case "buy":
                return buy(data);
            case "read":
                return read(data);
            case "account":
                return account(data);
            case "infochange":
                return changeinfo(data);
            case "raisecredit":
                return raisecredit(data);
            case "vipAccount":
                return vipAccount(data);
            case "sortBooks":
                return convertor.booksToStringsort(sortBooks(data));
            case "find":
                return convertor.bookToString(find(data));
            case "ratewant":
                return ratewant(data);
        }
        return "error";
    }


}




















