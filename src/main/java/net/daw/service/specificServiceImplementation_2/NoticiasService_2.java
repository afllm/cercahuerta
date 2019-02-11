package net.daw.service.specificServiceImplementation_2;

import net.daw.service.genericServiceImplementation.GenericServiceImplementation;
import net.daw.service.publicServiceInterface.ServiceInterface;
import javax.servlet.http.HttpServletRequest;

public class NoticiasService_2 extends GenericServiceImplementation implements ServiceInterface {

    HttpServletRequest oRequest;
    String ob = null;

    public NoticiasService_2(HttpServletRequest oRequest) {
        super(oRequest);
        this.oRequest = oRequest;
        ob = oRequest.getParameter("ob");
    }

   



}
