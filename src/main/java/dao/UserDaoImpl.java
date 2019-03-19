package dao;

import api.UserDao;
import entity.User;
import entity.parser.UserParser;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String fileName = "users.data";
    private static UserDaoImpl instance = null;

    private UserDaoImpl() {
        try {
            FileUtils.createNewFile(fileName);
        }catch (IOException e){
            System.out.println("Error with file path");
            //exit close application
            System.exit(-1);
        }

    }
    public static UserDaoImpl getInstance(){
        if(instance == null){
            instance = new UserDaoImpl();
        }
        return instance;
    }
    public void saveUser(User user) throws IOException {
        List<User> userList = getAllUsers();
        userList.add(user);
        saveUsers(userList);
    }

    public void saveUsers(List<User> users) throws FileNotFoundException {
        FileUtils.clearFile(fileName);
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(fileName, true));
        for(User user : users){
            printWriter.write(user.toString() + "\n");
        }
        printWriter.close();
    }

    public List<User> getAllUsers() throws IOException {
        List<User> userList = new ArrayList<User>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        String readLine = bufferedReader.readLine();
        while (readLine != null) {
            User user = UserParser.convertToUser(readLine);
            userList.add(user);
            readLine = bufferedReader.readLine();
        }
        return userList;
    }

    public void removeUserByLogin(String login) throws IOException{
        List<User> userList = getAllUsers();
        for(int i = 0; i < userList.size(); i++){
            boolean isFoundUser = userList.get(i).getLogin().equals(login);
            if(isFoundUser){
                userList.remove(i);
            }
        }
        saveUsers(userList);
    }

    public void removeUserById(Long id) throws IOException{
        List<User> userList = getAllUsers();
        for(int i = 0; i < userList.size(); i++){
            boolean isFoundUser = userList.get(i).getId().equals(id);
            if(isFoundUser){
                userList.remove(i);
            }
        }
        saveUsers(userList);
    }
}
