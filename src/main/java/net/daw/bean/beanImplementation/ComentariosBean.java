/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean.beanImplementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import net.daw.bean.genericBeanImplementation.GenericBeanImplementation;
import net.daw.bean.publicBeanInterface.BeanInterface;
import net.daw.dao.publicDaoInterface.DaoInterface;
import net.daw.factory.DaoFactory;
import net.daw.helper.EncodingHelper;

public class ComentariosBean extends GenericBeanImplementation implements BeanInterface {

//    @Expose
//    private int id;
    @Expose
    private String texto;
    @Expose(serialize = false)
    private int id_usuario;
    @Expose(serialize = false)
    private int id_noticia;
    @Expose(deserialize = false)
    private UsuarioBean obj_Usuario;
    @Expose(deserialize = false)
    private NoticiasBean obj_Noticia;

    public UsuarioBean getObj_Usuario() {
        return obj_Usuario;
    }

    public void setObj_Usuario(UsuarioBean obj_Usuario) {
        this.obj_Usuario = obj_Usuario;
    }

    public NoticiasBean getObj_Noticia() {
        return obj_Noticia;
    }

    public void setObj_Noticia(NoticiasBean obj_Noticia) {
        this.obj_Noticia = obj_Noticia;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_noticia() {
        return id_noticia;
    }

    public void setId_noticia(int id_noticia) {
        this.id_noticia = id_noticia;
    }
   

    @Override
    public ComentariosBean fill(ResultSet oResultSet, Connection oConnection, Integer expand, UsuarioBean oUsuarioBeanSession) throws Exception {

        this.setId(oResultSet.getInt("id"));
        this.setTexto(oResultSet.getString("texto"));

        if (expand > 0) {
           DaoInterface ousuarioDao = DaoFactory.getDao(oConnection, "usuario", oUsuarioBeanSession);
            this.setObj_Usuario((UsuarioBean) ousuarioDao.get(oResultSet.getInt("id_usuario"), expand - 1));
        } else {
            this.setId_usuario(oResultSet.getInt("id_usuario"));
        }

        if (expand > 0) {
            DaoInterface oNoticiaDao = DaoFactory.getDao(oConnection, "noticias", oUsuarioBeanSession);
            this.setObj_Noticia((NoticiasBean) oNoticiaDao.get(oResultSet.getInt("id_Noticia"), expand - 1));
        } else {
            this.setId_noticia(oResultSet.getInt("id_noticia"));
        }

        return this;
    }


    @Override
    public String getPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "texto=" + EncodingHelper.quotate(texto) + ",";
        strPairs += "id_usuario=" + id_usuario + ",";
        strPairs += "id_noticia=" + id_noticia;
        strPairs += " WHERE id=" + id;
        return strPairs;

    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "texto,";
        strColumns += "id_usuario,";
        strColumns += "id_noticia";
        return strColumns;
    }

    @Override
    public String getValues() {

        String strColumns = "";
        strColumns += "null,";
        strColumns += EncodingHelper.quotate(this.getTexto()) + ",";
        strColumns += this.getId_usuario() + ",";
        strColumns += this.getId_noticia();
        return strColumns;
    }

}
