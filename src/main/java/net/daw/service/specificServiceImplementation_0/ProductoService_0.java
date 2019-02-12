package net.daw.service.specificServiceImplementation_0;

import javax.servlet.http.HttpServletRequest;
import net.daw.service.genericServiceImplementation.GenericServiceImplementation;
import net.daw.service.publicServiceInterface.ServiceInterface;

public class ProductoService_0 extends GenericServiceImplementation implements ServiceInterface {

    HttpServletRequest oRequest;
    String ob = null;

    public ProductoService_0(HttpServletRequest oRequest) {
        super(oRequest);
        ob = oRequest.getParameter("ob");
    }

//    public ReplyBean loaddata() throws Exception {
//        ReplyBean oReplyBean;
//        ConnectionInterface oConnectionPool = null;
//        Connection oConnection;
//        ArrayList<ProductoBean> productos = new ArrayList<>();
//        RellenarService_1 oRellenarService = new RellenarService_1();
//        try {
//            Integer number = Integer.parseInt(oRequest.getParameter("number"));
//            oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
//            oConnection = oConnectionPool.newConnection();
//            ProductoDao_1 oProductoDao = new ProductoDao_1(oConnection, ob, oUsuarioBeanSession);
//            productos = oRellenarService.RellenarProducto(number);
//            for (ProductoBean producto : productos) {
//                oProductoDao.create(producto);
//            }
//            Gson oGson = new Gson();
//            oReplyBean = new ReplyBean(200, oGson.toJson("Productos creados: " + number));
//        } catch (Exception ex) {
//            oReplyBean = new ReplyBean(500,
//                    "ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
//        }
//
//        return oReplyBean;
//    }



}