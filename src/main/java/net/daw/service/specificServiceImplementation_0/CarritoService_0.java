package net.daw.service.specificServiceImplementation_0;

import com.google.gson.Gson;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.daw.bean.beanImplementation.ItemBean;
import net.daw.bean.beanImplementation.ReplyBean;
import net.daw.bean.beanImplementation.UsuarioBean;
import net.daw.helper.EncodingHelper;

public class CarritoService_0 {

    HttpServletRequest oRequest;
    String ob = null;
    Gson oGson = new Gson();
    //ReplyBean oReplyBean;
    ArrayList<ItemBean> carrito = null;
    //Connection oConnection = null;
    UsuarioBean oUsuarioBeanSession;

    public CarritoService_0(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        ob = oRequest.getParameter("ob");
    }

    protected Boolean checkPermission(String strMethodName) {
        oUsuarioBeanSession = (UsuarioBean) oRequest.getSession().getAttribute("user");
        if (oUsuarioBeanSession != null) {
            return true;
        } else {
            return false;
        }
    }

    public ReplyBean add() throws Exception {
        throw new Exception("Error en Service add de " + ob + ": No autorizado");

    }

    public ReplyBean reduce() throws Exception {
        throw new Exception("Error en Service reduce de " + ob + ": No autorizado");

    }

    public ReplyBean show() throws Exception {
                ReplyBean oReplyBean;
        if (checkPermission("show")) {
            HttpSession sesion = oRequest.getSession();

            if (sesion.getAttribute("carrito") == null) {
                oReplyBean = new ReplyBean(200, EncodingHelper.quotate("Carrito vacio"));
            } else {
                oReplyBean = new ReplyBean(200, oGson.toJson(sesion.getAttribute("carrito")));
            }

        } else {
            oReplyBean = new ReplyBean(401, "Unauthorized");
        }
        return oReplyBean;
    }

    public ReplyBean empty() throws Exception {
        throw new Exception("Error en Service empty de " + ob + ": No autorizado");

    }

    public ReplyBean buy() throws Exception {
        throw new Exception("Error en Service buy de " + ob + ": No autorizado");
    }

}
