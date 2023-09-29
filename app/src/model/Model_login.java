/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Novita
 */
public class Model_login {
    private String id_user;
    private String username;
    private String pass_user;
    
    public String getId_user(){
        return id_user;
    }
    
    public void setId_user (String id_user){
        this.id_user = id_user;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPass_user(){
        return pass_user;
    }
    
    public void setPass_user(String pass_user){
        this.pass_user = pass_user; 
    }
}
