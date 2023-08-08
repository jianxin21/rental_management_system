/**
    class: Property

    name: chengjiapao
*/
public class User extends Person{
    
    private String userStatus;
    
	public User(String username, String password, String userStatus) {
		super(username,password);
                this.userStatus=userStatus;
	}

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
	
	public User() {
		super(null,null);
	}
	
	public String toCSVString(){
        return(getUsername() + "," + getPassword() + "," + getUserStatus());
    }
}
