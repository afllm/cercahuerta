/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean.beanImplementation;

import java.sql.Connection;
import java.sql.ResultSet;

import com.google.gson.annotations.Expose;
import net.daw.bean.genericBeanImplementation.GenericBeanImplementation;
import net.daw.bean.publicBeanInterface.BeanInterface;
import net.daw.dao.publicDaoInterface.DaoInterface;
import net.daw.dao.specificDaoImplementation_0.FacturaDao_0;
import net.daw.dao.specificDaoImplementation_1.FacturaDao_1;

import net.daw.helper.EncodingHelper;
import net.daw.dao.specificDaoImplementation_1.TipousuarioDao_1;
import net.daw.dao.specificDaoImplementation_2.FacturaDao_2;
import net.daw.factory.DaoFactory;
import net.daw.helper.TokenGenerator;


public class UsuarioBean extends GenericBeanImplementation implements BeanInterface {

    @Expose
    private String dni;
    @Expose
    private String nombre;
    @Expose
    private String ape1;
    @Expose
    private String ape2;
    @Expose
    private String email;
    @Expose
    private String login;
    @Expose(serialize = false)
    private String pass;
    @Expose(serialize = false)
    private int id_tipoUsuario;
    @Expose(deserialize = false)
    private TipousuarioBean obj_tipoUsuario;
    @Expose(serialize = false)
    private String token;
    @Expose(serialize = false)
    private Integer activo;
    @Expose(deserialize = false)
    private int link_factura;

    public TipousuarioBean getObj_tipoUsuario() {
        return obj_tipoUsuario;
    }

    public void setObj_tipoUsuario(TipousuarioBean obj_tipoUsuario) {
        this.obj_tipoUsuario = obj_tipoUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId_tipoUsuario() {
        return id_tipoUsuario;
    }

    public void setId_tipoUsuario(int id_tipoUsuario) {
        this.id_tipoUsuario = id_tipoUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
    
    public int getLink_factura() {
        return link_factura;
    }

    public void setLink_factura(int link_factura) {
        this.link_factura = link_factura;
    }

    @Override
    public UsuarioBean fill(ResultSet oResultSet, Connection oConnection, Integer expand, UsuarioBean oUsuarioBeanSession) throws Exception {
        if (oUsuarioBeanSession != null) {
            if (oResultSet.getInt("id") == oUsuarioBeanSession.getId() || oUsuarioBeanSession.getId() == 1) {
                this.setId(oResultSet.getInt("id"));
                this.setDni(oResultSet.getString("dni"));
                this.setNombre(oResultSet.getString("nombre"));
                this.setApe1(oResultSet.getString("ape1"));
                this.setApe2(oResultSet.getString("ape2"));
                this.setEmail(oResultSet.getString("email"));
                this.setLogin(oResultSet.getString("login"));
                this.setPass(oResultSet.getString("pass"));
                this.setToken(oResultSet.getString("token"));
                this.setActivo(oResultSet.getInt("activo"));

                DaoInterface oFacturaDao = DaoFactory.getDao(oConnection, "factura", oUsuarioBeanSession);
                this.setLink_factura(oFacturaDao.getcountX(oResultSet.getInt("id")));

                if (expand > 0) {
                    DaoInterface otipousuarioDao = DaoFactory.getDao(oConnection, "tipousuario", oUsuarioBeanSession);
                    this.setObj_tipoUsuario((TipousuarioBean) otipousuarioDao.get(oResultSet.getInt("id_tipoUsuario"), expand - 1));
                } else {
                    this.setId_tipoUsuario(oResultSet.getInt("id_tipoUsuario"));
                }
            } else {
                this.setNombre(oResultSet.getString("nombre"));
                this.setApe1(oResultSet.getString("ape1"));
            }
        }else {
                this.setNombre(oResultSet.getString("nombre"));
                this.setApe1(oResultSet.getString("ape1"));
            }

       

        return this;
}

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "dni,";
        strColumns += "nombre,";
        strColumns += "ape1,";
        strColumns += "ape2,";
        strColumns += "email,";
        strColumns += "login,";
        strColumns += "pass,";
        strColumns += "id_tipoUsuario,";
        strColumns += "token,";
        strColumns += "activo";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += "null,";
        strColumns += EncodingHelper.quotate(dni) + ",";
        strColumns += EncodingHelper.quotate(nombre) + ",";
        strColumns += EncodingHelper.quotate(ape1) + ",";
        strColumns += EncodingHelper.quotate(ape2) + ",";
        strColumns += EncodingHelper.quotate(email) + ",";
        strColumns += EncodingHelper.quotate(login) + ",";
        strColumns += EncodingHelper.quotate(pass) + ",";
        //Si no lo crea el administrador, el tipo de usuario es 0 (cero)
        if(this.id_tipoUsuario!=1 && this.id_tipoUsuario!=2 && this.id_tipoUsuario!=3){
            this.id_tipoUsuario=0;
        }
        strColumns += id_tipoUsuario + ",";
        strColumns += EncodingHelper.quotate(TokenGenerator.nextToken()) + ",";
        strColumns += 0;
        return strColumns;
    }

    @Override
    public String getPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "dni=" + EncodingHelper.quotate(dni) + ",";
        strPairs += "nombre=" + EncodingHelper.quotate(nombre) + ",";
        strPairs += "ape1=" + EncodingHelper.quotate(ape1) + ",";
        strPairs += "ape2=" + EncodingHelper.quotate(ape2) + ",";
        strPairs += "email=" + EncodingHelper.quotate(email) + ",";
        strPairs += "login=" + EncodingHelper.quotate(login) + ",";
        //strPairs += "pass=" + EncodingHelper.quotate(pass) + ",";
        strPairs += "id_tipoUsuario=" + id_tipoUsuario;
        strPairs += " WHERE id=" + id;
        return strPairs;

    }
    
    @Override
    public String getPairsPass() {
        String strPairs = "";
        strPairs += "pass=" + EncodingHelper.quotate(pass);
        strPairs += " WHERE id=" + id;
        return strPairs;

    }

}
