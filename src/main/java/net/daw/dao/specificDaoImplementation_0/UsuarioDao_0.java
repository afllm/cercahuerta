/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao.specificDaoImplementation_0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.mail.MessagingException;

import net.daw.bean.beanImplementation.UsuarioBean;
import net.daw.bean.publicBeanInterface.BeanInterface;
import net.daw.dao.genericDaoImplementation.GenericDaoImplementation;
import net.daw.dao.publicDaoInterface.DaoInterface;
import net.daw.factory.BeanFactory;
import net.daw.factory.DaoFactory;
import net.daw.helper.UserActivationEmail;

public class UsuarioDao_0 extends GenericDaoImplementation implements DaoInterface {

    public UsuarioDao_0(Connection oConnection, String ob, UsuarioBean oUsuarioBeanSession) {
        super(oConnection, ob, oUsuarioBeanSession);

    }

    @Override
    public BeanInterface get(int id, Integer expand) throws Exception {
        throw new Exception("Error en Dao remove de " + ob + ": No autorizado");
    }

    @Override
    public int remove(int id) throws Exception {
        throw new Exception("Error en Dao remove de " + ob + ": No autorizado");
    }

//    @Override
//    public int getcount() throws Exception {
//        throw new Exception("Error en Dao remove de " + ob + ": No autorizado");
//    }
    @Override
    public BeanInterface create(BeanInterface oBean) throws Exception {
        UsuarioBean oBeanActivation;
        int id = oBean.getId();
        if (id == 0) {
            try {
                oBeanActivation = (UsuarioBean) super.create(oBean);
                String email = oBeanActivation.getEmail();
                String nombre = oBeanActivation.getNombre();                
                UsuarioBean oBeanToken = (UsuarioBean) super.get(oBeanActivation.getId(), 1);
                String token = oBeanToken.getToken();
                UserActivationEmail.sendActivationEmail(email, nombre, token);
            } catch (Exception ex) {
                throw new Exception("Error en Dao create de " + ob + ": " + ex.getMessage(), ex);
            }

            return oBeanActivation;
        } else {
            throw new Exception("Error en Dao update de " + ob + ": No autorizado");
        }
    }

//    @Override
//    public int update(BeanInterface oBean) throws Exception {
//        int id=oBean.getId();
//        if (id == oUsuarioBeanSession.getId()) {
//            return super.update(oBean);
//        } else {
//            throw new Exception("Error en Dao update de " + ob + ": No autorizado");
//        }
//    }
    @Override
    public int update(BeanInterface oBean) throws Exception {
        throw new Exception("Error en Dao remove de " + ob + ": No autorizado");
    }

    @Override
    public ArrayList<BeanInterface> getpage(int iRpp, int iPage, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        throw new Exception("Error en Dao remove de " + ob + ": No autorizado");
    }

    public UsuarioBean login(String strUserName, String strPassword) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE login=? AND pass=?";
        UsuarioBean oUsuarioBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setString(1, strUserName);
            oPreparedStatement.setString(2, strPassword);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oUsuarioBean = new UsuarioBean();
                oUsuarioBean.fill(oResultSet, oConnection, 1, oUsuarioBeanSession);
            } else {
                oUsuarioBean = null;
            }
        } catch (SQLException e) {
            throw new Exception("Error en Dao get de " + ob, e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return oUsuarioBean;
    }

    public BeanInterface activar(String token, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE token=?";
        BeanInterface oBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL_get);
            oPreparedStatement.setString(1, token);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oBean = BeanFactory.getBean(ob);
                oBean.fill(oResultSet, oConnection, expand, oUsuarioBeanSession);

            } else {
                oBean = null;
            }
        } catch (SQLException e) {
            throw new Exception("Error en Dao get de " + ob, e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return oBean;
    }

}
