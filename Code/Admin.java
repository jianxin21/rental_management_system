/**
    class: Admin

    name: tamjianxin

*/
public class Admin extends Person{
	
	public Admin(String username, String password) {
		super(username,password);
	}
	
	public String toCSVString(){
        return(getUsername() + "," + getPassword());
    }
}
