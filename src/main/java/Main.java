import java.sql.Connection;

public class Main {

    public void print(){
        System.out.println("SALAM");
    }

    public void print(String message){
        System.out.println(message);
    }

    public static void main(String []args){
       Connection connection =Config.getConnection();
        System.out.println(connection);
    }
}
