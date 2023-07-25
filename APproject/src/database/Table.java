package database;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

import Objects.*;
import utils.*;

public class Table {
    private String path;
    public Table (String path){
        this.path=path;
    }
    public void insertUser(user user) throws IOException {
        FileWriter fw=new FileWriter(path);
        if(user.getBooks().size()==0)
            fw.write(convertor.UserToString(user));
        else
            fw.write(convertor.userwithbooksToString(user));
        fw.close();
    }

    public void insertBooks(List<book> books) throws IOException {
        FileWriter fw=new FileWriter(path);
        fw.write(convertor.booksToString(books));
        fw.close();

    }

    public void insertInUsers(Map<String,String> map,user user) throws IOException {
        FileWriter fw=new FileWriter(path);
        map.put(user.getName(),user.getPassword());
        for(Map.Entry<String,String> entry:map.entrySet()){
            fw.write(entry.getKey()+"&&"+entry.getValue()+"\n");
        }
        fw.close();
    }

    public String getPath() {
        return path;
    }

    public Map<String,String> getusers() throws FileNotFoundException {
        FileReader f=new FileReader(path);
        Scanner sc=new Scanner(f);
        Map<String,String> map=new HashMap<>();
        while(sc.hasNextLine()){
            String[] str=sc.nextLine().split("&&");
            map.put(str[0],str[1]);
        }
        return map;
    }




}
