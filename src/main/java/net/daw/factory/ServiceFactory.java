package net.daw.factory;

import javax.servlet.http.HttpServletRequest;

import net.daw.bean.beanImplementation.ReplyBean;
import net.daw.bean.beanImplementation.UsuarioBean;
import net.daw.service.specificServiceImplementation_0.CarritoService_0;
import net.daw.service.specificServiceImplementation_0.ComentariosService_0;
import net.daw.service.specificServiceImplementation_0.NoticiasService_0;
import net.daw.service.specificServiceImplementation_0.UsuarioService_0;
import net.daw.service.specificServiceImplementation_1.CarritoService_1;
import net.daw.service.specificServiceImplementation_1.ComentariosService_1;
import net.daw.service.specificServiceImplementation_1.FacturaService_1;
import net.daw.service.specificServiceImplementation_1.LineaService_1;
import net.daw.service.specificServiceImplementation_1.NoticiasService_1;
import net.daw.service.specificServiceImplementation_1.ProductoService_1;
import net.daw.service.specificServiceImplementation_1.TipoproductoService_1;
import net.daw.service.specificServiceImplementation_1.TipousuarioService_1;
import net.daw.service.specificServiceImplementation_1.UsuarioService_1;
import net.daw.service.specificServiceImplementation_2.CarritoService_2;
import net.daw.service.specificServiceImplementation_2.ComentariosService_2;
import net.daw.service.specificServiceImplementation_2.FacturaService_2;
import net.daw.service.specificServiceImplementation_2.LineaService_2;
import net.daw.service.specificServiceImplementation_2.NoticiasService_2;
import net.daw.service.specificServiceImplementation_2.ProductoService_2;
import net.daw.service.specificServiceImplementation_2.TipoproductoService_2;
import net.daw.service.specificServiceImplementation_2.TipousuarioService_2;
import net.daw.service.specificServiceImplementation_2.UsuarioService_2;

public class ServiceFactory {

    public static ReplyBean executeService(HttpServletRequest oRequest) throws Exception {

        String ob = oRequest.getParameter("ob");
        String op = oRequest.getParameter("op");

        int idSessionUserTipe;
        UsuarioBean oUsuarioBeanSession = (UsuarioBean) oRequest.getSession().getAttribute("user");
        if (oUsuarioBeanSession != null) {
            idSessionUserTipe = oUsuarioBeanSession.getObj_tipoUsuario().getId();
        } else {
            idSessionUserTipe = 0;
        }

        ReplyBean oReplyBean = null;

        switch (idSessionUserTipe) {
            case 1:
                switch (ob) {
                    case "tipousuario":
                        TipousuarioService_1 oTipousuarioService = new TipousuarioService_1(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oTipousuarioService.get();
                                break;
                            case "create":
                                oReplyBean = oTipousuarioService.create();
                                break;
                            case "update":
                                oReplyBean = oTipousuarioService.update();
                                break;
                            case "remove":
                                oReplyBean = oTipousuarioService.remove();
                                break;
                            case "getcount":
                                oReplyBean = oTipousuarioService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oTipousuarioService.getpage();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "usuario":
                        UsuarioService_1 oUsuarioService = new UsuarioService_1(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oUsuarioService.get();
                                break;
                            case "create":
                                oReplyBean = oUsuarioService.create();
                                break;
                            case "update":
                                oReplyBean = oUsuarioService.update();
                                break;
                            case "updatepass":
                                oReplyBean = oUsuarioService.updatePass();
                                break;
                            case "remove":
                                oReplyBean = oUsuarioService.remove();
                                break;
                            case "getcount":
                                oReplyBean = oUsuarioService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oUsuarioService.getpage();
                                break;
//                            case "login":
//                                oReplyBean = oUsuarioService.login();
//                                break;
                            case "logout":
                                oReplyBean = oUsuarioService.logout();
                                break;
                            case "check":
                                oReplyBean = oUsuarioService.check();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "factura":
                        FacturaService_1 oFacturaService = new FacturaService_1(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oFacturaService.get();
                                break;
                            case "create":
                                oReplyBean = oFacturaService.create();
                                break;
                            case "update":
                                oReplyBean = oFacturaService.update();
                                break;
                            case "remove":
                                oReplyBean = oFacturaService.remove();
                                break;
                            case "getcount":
                                oReplyBean = oFacturaService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oFacturaService.getpage();
                                break;
                            case "getpagex":
                                oReplyBean = oFacturaService.getpageX();
                                break;
                            case "getcountx":
                                oReplyBean = oFacturaService.getcountX();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "linea":
                        LineaService_1 oLineaService = new LineaService_1(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oLineaService.get();
                                break;
                            case "create":
                                oReplyBean = oLineaService.create();
                                break;
                            case "update":
                                oReplyBean = oLineaService.update();
                                break;
                            case "remove":
                                oReplyBean = oLineaService.remove();
                                break;
                            case "getcount":
                                oReplyBean = oLineaService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oLineaService.getpage();
                                break;
                            case "getpagex":
                                oReplyBean = oLineaService.getpageX();
                                break;
                            case "getcountx":
                                oReplyBean = oLineaService.getcountX();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;

                    case "producto":
                        ProductoService_1 oProductoService = new ProductoService_1(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oProductoService.get();
                                break;
                            case "create":
                                oReplyBean = oProductoService.create();
                                break;
                            case "update":
                                oReplyBean = oProductoService.update();
                                break;
                            case "remove":
                                oReplyBean = oProductoService.remove();
                                break;
                            case "getcount":
                                oReplyBean = oProductoService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oProductoService.getpage();
                                break;
//                            case "loaddata":
//                                oReplyBean = oProductoService.loaddata();
//                                break;
                            case "loadimage":
                                oReplyBean = oProductoService.loadimage();
                                break;
                            case "fillprod":
                                oReplyBean = oProductoService.fillProd();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "tipoproducto":
                        TipoproductoService_1 oTipoproductoService = new TipoproductoService_1(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oTipoproductoService.get();
                                break;
                            case "create":
                                oReplyBean = oTipoproductoService.create();
                                break;
                            case "update":
                                oReplyBean = oTipoproductoService.update();
                                break;
                            case "remove":
                                oReplyBean = oTipoproductoService.remove();
                                break;
                            case "getcount":
                                oReplyBean = oTipoproductoService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oTipoproductoService.getpage();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "carrito":
                        CarritoService_1 oCarritoService = new CarritoService_1(oRequest);
                        switch (op) {
                            case "add":
                                oReplyBean = oCarritoService.add();
                                break;
                            case "empty":
                                oReplyBean = oCarritoService.empty();
                                break;
                            case "reduce":
                                oReplyBean = oCarritoService.reduce();
                                break;
                            case "show":
                                oReplyBean = oCarritoService.show();
                                break;
                            case "buy":
                                oReplyBean = oCarritoService.buy();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "noticias":
                        NoticiasService_1 oNoticiasService = new NoticiasService_1(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oNoticiasService.get();
                                break;
                            case "create":
                                oReplyBean = oNoticiasService.create();
                                break;
                            case "update":
                                oReplyBean = oNoticiasService.update();
                                break;
                            case "remove":
                                oReplyBean = oNoticiasService.remove();
                                break;
                            case "getcount":
                                oReplyBean = oNoticiasService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oNoticiasService.getpage();
                                break;
//                            case "loaddata":
//                                oReplyBean = oProductoService.loaddata();
//                                break;
                            case "loadimage":
                                oReplyBean = oNoticiasService.loadimage();
                                break;
                            case "fillnoticias":
                                oReplyBean = oNoticiasService.fillNoticias();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "comentarios":
                        ComentariosService_1 oComentariosService = new ComentariosService_1(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oComentariosService.get();
                                break;
                            case "create":
                                oReplyBean = oComentariosService.create();
                                break;
                            case "update":
                                oReplyBean = oComentariosService.update();
                                break;
                            case "remove":
                                oReplyBean = oComentariosService.remove();
                                break;
                            case "getcount":
                                oReplyBean = oComentariosService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oComentariosService.getpage();
                                break;
                            case "getpagex":
                                oReplyBean = oComentariosService.getpageX();
                                break;
                            case "getcountx":
                                oReplyBean = oComentariosService.getcountX();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;

                    default:
                        oReplyBean = new ReplyBean(500, "Object doesn't exist");
                        break;
                }
                break;
            case 2:
                switch (ob) {
                    case "tipousuario":
                        TipousuarioService_2 oTipousuarioService = new TipousuarioService_2(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oTipousuarioService.get();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "usuario":
                        UsuarioService_2 oUsuarioService = new UsuarioService_2(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oUsuarioService.get();
                                break;

                            case "update":
                                oReplyBean = oUsuarioService.update();
                                break;
                            case "updatepass":
                                oReplyBean = oUsuarioService.updatePass();
                                break;
//                            case "login":
//                                oReplyBean = oUsuarioService.login();
//                                break;
                            case "logout":
                                oReplyBean = oUsuarioService.logout();
                                break;
                            case "check":
                                oReplyBean = oUsuarioService.check();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "factura":
                        FacturaService_2 oFacturaService = new FacturaService_2(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oFacturaService.get();
                                break;
                            case "getcount":
                                oReplyBean = oFacturaService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oFacturaService.getpage();
                                break;
                            case "getpagex":
                                oReplyBean = oFacturaService.getpageX();
                                break;
                            case "getcountx":
                                oReplyBean = oFacturaService.getcountX();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "linea":
                        LineaService_2 oLineaService = new LineaService_2(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oLineaService.get();
                                break;
                            case "getcount":
                                oReplyBean = oLineaService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oLineaService.getpage();
                                break;
                            case "getpagex":
                                oReplyBean = oLineaService.getpageX();
                                break;
                            case "getcountx":
                                oReplyBean = oLineaService.getcountX();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;

                    case "producto":
                        ProductoService_2 oProductoService = new ProductoService_2(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oProductoService.get();
                                break;
                            case "getcount":
                                oReplyBean = oProductoService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oProductoService.getpage();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "tipoproducto":
                        TipoproductoService_2 oTipoproductoService = new TipoproductoService_2(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oTipoproductoService.get();
                                break;
                            case "getcount":
                                oReplyBean = oTipoproductoService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oTipoproductoService.getpage();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "carrito":
                        CarritoService_2 oCarritoService = new CarritoService_2(oRequest);
                        switch (op) {
                            case "add":
                                oReplyBean = oCarritoService.add();
                                break;
                            case "empty":
                                oReplyBean = oCarritoService.empty();
                                break;
                            case "reduce":
                                oReplyBean = oCarritoService.reduce();
                                break;
                            case "show":
                                oReplyBean = oCarritoService.show();
                                break;
                            case "buy":
                                oReplyBean = oCarritoService.buy();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "noticias":
                        NoticiasService_2 oNoticiasService = new NoticiasService_2(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oNoticiasService.get();
                                break;
                            case "getcount":
                                oReplyBean = oNoticiasService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oNoticiasService.getpage();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "comentarios":
                        ComentariosService_2 oComentariosService = new ComentariosService_2(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oComentariosService.get();
                                break;
                            case "create":
                                oReplyBean = oComentariosService.create();
                                break;
                            case "update":
                                oReplyBean = oComentariosService.update();
                                break;
                            case "remove":
                                oReplyBean = oComentariosService.remove();
                                break;
                            case "getcount":
                                oReplyBean = oComentariosService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oComentariosService.getpage();
                                break;
                            case "getpagex":
                                oReplyBean = oComentariosService.getpageX();
                                break;
                            case "getcountx":
                                oReplyBean = oComentariosService.getcountX();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Object doesn't exist");
                        break;
                }
                break;
            case 0:
                switch (ob) {
                    case "usuario":
                        UsuarioService_0 oUsuarioService = new UsuarioService_0(oRequest);
                        switch (op) {
                            case "create":
                                oReplyBean = oUsuarioService.create();
                                break;
                            case "login":
                                oReplyBean = oUsuarioService.login();
                                break;
                            case "check":
                                oReplyBean = oUsuarioService.check();
                                break;
                            case "activar":
                                oReplyBean = oUsuarioService.activar();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "producto":
                        ProductoService_2 oProductoService = new ProductoService_2(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oProductoService.get();
                                break;
                            case "getcount":
                                oReplyBean = oProductoService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oProductoService.getpage();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "tipoproducto":
                        TipoproductoService_2 oTipoproductoService = new TipoproductoService_2(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oTipoproductoService.get();
                                break;
                            case "getcount":
                                oReplyBean = oTipoproductoService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oTipoproductoService.getpage();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "carrito":
                        CarritoService_0 oCarritoService = new CarritoService_0(oRequest);
                        switch (op) {
                            case "show":
                                oReplyBean = oCarritoService.show();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "noticias":
                        NoticiasService_0 oNoticiasService = new NoticiasService_0(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oNoticiasService.get();
                                break;
                            case "getcount":
                                oReplyBean = oNoticiasService.getcount();
                                break;
                            case "getpage":
                                oReplyBean = oNoticiasService.getpage();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    case "comentarios":
                        ComentariosService_0 oComentariosService = new ComentariosService_0(oRequest);
                        switch (op) {
                            case "get":
                                oReplyBean = oComentariosService.get();
                                break;
                            case "getpagex":
                                oReplyBean = oComentariosService.getpageX();
                                break;
                            case "getcountx":
                                oReplyBean = oComentariosService.getcountX();
                                break;
                            default:
                                oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                                break;
                        }
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Object doesn't exist");
                        break;
                }
                break;
            default:
                oReplyBean = new ReplyBean(500, "Profile doesn't exist");
                break;
        }
        return oReplyBean;
    }

}
