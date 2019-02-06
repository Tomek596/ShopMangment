package dao;

import api.UserDao;
import entity.User;
import entity.parser.UserParser;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public final String fileName;

    public UserDaoImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void saveUser(User user) throws IOException {
        List<User> userList = getAllUsers();
        userList.add(user);
        saveUsers(userList);
    }

    @Override
    public void saveUsers(List<User> users) throws FileNotFoundException {
        FileUtils.clearFile(fileName);
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(fileName, true));
        for(User user : users){
            printWriter.write(user.toString() + "\r\n");
        }
        printWriter.close();
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        List<User> userList = new ArrayList<User>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        String readLine = bufferedReader.readLine();
        while (readLine != null) {
            User user = UserParser.convertToUser(readLine);
            if (user != null) {
                userList.add(user);
            }
        }
        bufferedReader.close();
        return userList;
    }

    @Override
    public User getUserByLogin(String login) throws IOException{
        List<User> userList = getAllUsers();
        for(User user : userList){
            boolean isFoundUser = user.getLogin().equals(login);
            if(isFoundUser){
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserById(Long userId) throws IOException{
        List<User> userList = getAllUsers();
        for (User user : userList){
            boolean isFounduser = user.getId().equals(userId);
            if(isFounduser){
                return user;
            }
        }
        return null;
    }

    @Override
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

    @Override
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
