/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aeopensolutions.view.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.aeopensolutions.model.ejb.facades.AdUserFacade;
import org.aeopensolutions.model.entities.AdUser;
import org.aeopensolutions.model.utils.SecurityUtils;
import org.aeopensolutions.view.utils.JsfUtils;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginController implements Serializable {

    @Inject
    private AdUserFacade adUserFacade;
    
    @Inject
    private SecurityUtils security;

    private AdUser adUser;

    private String pass1;

    private String pass2;

    @PostConstruct
    public void initialize() {
        adUser = security.getAdUser();
        //prPeople = prPeopleFacade.findByAdUser(getAdUser());
        setPass1(null);
        setPass2(null);
    }

    public void errorInicioSesion() {
        try {
            JsfUtils.messageError(null, "Usuario y/o Contraseña incorrectos", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        System.out.println("logout...");
        try {
            HttpSession session = JsfUtils.getSession();
            session.invalidate();
            JsfUtils.getResponse().sendRedirect(JsfUtils.getRequest().getContextPath() + "/home.jsf?faces-redirect=true");
        } catch (Exception ex) {
            ex.getStackTrace();
        }

    }
    
    public void actualizarUsuario() {
        System.out.println("actualizarUsuario: "+getPass1()+" - "+getPass2());
        
        System.out.println("actualizarUsuario aduser: " + getAdUser());
            try {
                adUserFacade.save(getAdUser(), getPass1(), getPass2());
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
                return;
            }

            JsfUtils.messageInfo(null, "Usuario guardado correctamente.", null);
            JsfUtils.executeJS("PF('wvPerfilUsuario').hide();");
        
    }

    public AdUser getAdUser() {
        return adUser;
    }

    public void setAdUser(AdUser adUser) {
        this.adUser = adUser;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }
    
    

}
