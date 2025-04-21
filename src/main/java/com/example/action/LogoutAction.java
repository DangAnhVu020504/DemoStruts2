package com.example.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Map;

public class LogoutAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    private Map<String, Object> session;

    public String execute() {
        // Xóa session
        if (session != null) {
            session.clear();
        }

        return SUCCESS; // Điều hướng qua struts.xml
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}