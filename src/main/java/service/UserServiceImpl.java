package service;
import entity.User;
import api.UserService;
import java.util.List;
import java.util.ArrayList;

public class UserServiceImpl implements UserService{

	List<User> users;
    public UserServiceImpl() {
        this.users = new ArrayList<User>();
		}
		public UserServiceImpl(List<User> users){
			this.users = users;
		}
		public List<User> getAllUsers(){
			return users;
		}
		public void addUser(User user){
			users.add(user);
		}
		public void removeUserById(Long userId){
			for(int i = 0; i < users.size(); i++){
				User userFromList = users.get(i);
				if(userFromList.getId() == userId){
					users.remove(i);
					break;
				}
			}
		}
}
