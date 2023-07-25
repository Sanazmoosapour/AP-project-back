package database;

import java.util.HashMap;
import java.util.Map;

public class database {
    private static database instance=null;
    private Map<String,Table> tables=new HashMap<>();
    private database(){
        tables.put("books",new Table("src/data/books.txt"));
        tables.put("users",new Table("src/data/users.txt"));
    }

    public Map<String, Table> getTables() {
        return tables;
    }

//    public void setTables(Map<String, Table> tables) {
//        this.tables = tables;
//    }

    public static database getInstance(){
        if(instance==null){
            return new database();

        }
        return instance;
    }


    public void addTable(String str){
        tables.put(str,new Table("src/data/"+str+".txt"));
        System.out.println(tables);
    }
    public Table getTable(String name){
        return tables.get(name);
    }
}
