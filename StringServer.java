import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    ArrayList<String> list = new ArrayList<String>();
    String listStr = "";

    public String handleRequest(URI url) {
    System.out.println("Path: " + url.getPath());
        if (url.getPath().contains("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
            list.add(parameters[1]);
            }
            listStr = list.toString();
            listStr = listStr.replace("[", "");
            listStr = listStr.replace("]", "");
            listStr = listStr.replace(", ", "\n");
        }
        return listStr;
    }
}

public class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);
        Server.start(port, new Handler());
    }
}