/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.service.specificServiceImplementation_1;

import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.beanImplementation.FacturaBean;
import net.daw.bean.beanImplementation.ReplyBean;
import net.daw.bean.beanImplementation.UsuarioBean;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.constant.ConnectionConstants;
import net.daw.dao.publicDaoInterface.DaoInterface;
import net.daw.dao.specificDaoImplementation_1.FacturaDao_1;
import net.daw.dao.specificDaoImplementation_2.FacturaDao_2;
import net.daw.factory.ConnectionFactory;
import net.daw.factory.DaoFactory;
import net.daw.service.genericServiceImplementation.GenericServiceImplementation;
import net.daw.service.publicServiceInterface.ServiceInterface;

public class FacturaService_1 extends GenericServiceImplementation implements ServiceInterface {

    public FacturaService_1(HttpServletRequest oRequest) {
        super(oRequest);
        ob = oRequest.getParameter("ob");
        //oUsuarioBeanSession = (UsuarioBean) oRequest.getSession().getAttribute("user");
    }

    


}
