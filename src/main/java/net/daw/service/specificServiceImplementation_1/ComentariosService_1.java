/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.service.specificServiceImplementation_1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.beanImplementation.LineaBean;
import net.daw.bean.beanImplementation.ReplyBean;
import net.daw.bean.beanImplementation.UsuarioBean;
import net.daw.bean.publicBeanInterface.BeanInterface;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.constant.ConnectionConstants;
import net.daw.dao.publicDaoInterface.DaoInterface;
import net.daw.dao.specificDaoImplementation_1.LineaDao_1;
import net.daw.factory.ConnectionFactory;
import net.daw.factory.DaoFactory;
import net.daw.service.genericServiceImplementation.GenericServiceImplementation;
import net.daw.service.publicServiceInterface.ServiceInterface;

public class ComentariosService_1 extends GenericServiceImplementation implements ServiceInterface {

    public ComentariosService_1(HttpServletRequest oRequest) {
        super(oRequest);
        ob = oRequest.getParameter("ob");
        //oUsuarioBeanSession = (UsuarioBean) oRequest.getSession().getAttribute("user");
}

//    public ReplyBean getComments() throws Exception {
//        ReplyBean oReplyBean;
//        ConnectionInterface oConnectionPool = null;
//        Connection oConnection;
//        try {
//            oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
//            oConnection = oConnectionPool.newConnection();
//            DaoInterface oDao = DaoFactory.getDao(oConnection, ob, oUsuarioBeanSession);
//            ArrayList<BeanInterface> alBean = oDao.getComments(2);
//            Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
//            oReplyBean = new ReplyBean(200, oGson.toJson(alBean));
//        } catch (Exception ex) {
//            throw new Exception("ERROR: Service level: get page: " + ob + " object", ex);
//        } finally {
//            oConnectionPool.disposeConnection();
//        }
//        return oReplyBean;
//    }

   
}
