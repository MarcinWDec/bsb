package pl.bsb.web.mb;


import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import pl.bsb.web.util.WebUtil;
 
@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private String password;
    private String message, uname;
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getUname() {
        return uname;
    }
 
    public void setUname(String uname) {
        this.uname = uname;
    }
 
    public String loginApp() {
        boolean result = login(uname, password);
        if (result) {
            HttpSession session = WebUtil.getSession();
            session.setAttribute("username", uname);
 
            return "index";
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
 
            return "login";
        }
    }
 
    private boolean login(String uname, String password) {
    	boolean loggedIn = false;  
        
        if(uname != null  && uname.equals("admin") && password != null  && password.equals("admin")) {  
            loggedIn = true;  
        } else {  
            loggedIn = false;  
        }  
        
        return loggedIn;
	}

	public String logout() {
      HttpSession session = WebUtil.getSession();
      session.invalidate();
      return "login";
   }
}