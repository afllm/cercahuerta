package net.daw.service.specificServiceImplementation_1;

import net.daw.bean.beanImplementation.ReplyBean;
import net.daw.bean.beanImplementation.NoticiasBean;
import net.daw.service.genericServiceImplementation.GenericServiceImplementation;
import net.daw.service.publicServiceInterface.ServiceInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.constant.ConnectionConstants;
import net.daw.dao.publicDaoInterface.DaoInterface;
import net.daw.factory.BeanFactory;
import net.daw.factory.ConnectionFactory;
import net.daw.factory.DaoFactory;
import net.daw.helper.EncodingHelper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class NoticiasService_1 extends GenericServiceImplementation implements ServiceInterface {

    HttpServletRequest oRequest;
    String ob = null;

    public NoticiasService_1(HttpServletRequest oRequest) {
        super(oRequest);
        this.oRequest = oRequest;
        ob = oRequest.getParameter("ob");
    }

    public ReplyBean loadimage() throws Exception {
        ReplyBean oReplyBean = null;
        String name = "";
        String strMessage = "";
        HashMap<String, String> hash = new HashMap<>();
        if (ServletFileUpload.isMultipartContent(oRequest)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(oRequest);
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        name = new File(item.getName()).getName();
                        item.write(new File(".//..//webapps//images_server//noticias//" + name));
                    } else {
                        hash.put(item.getFieldName(), item.getString());
                    }
                }
                strMessage = "File upload";
                Gson oGson = new Gson();
                oReplyBean = new ReplyBean(200, oGson.toJson(strMessage));
            } catch (Exception ex) {
                strMessage = "Failed to upload file";
                oReplyBean = new ReplyBean(500, "ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())) + strMessage);
            }
        } else {
            strMessage = "File no upload";
            oReplyBean = new ReplyBean(500, "ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(strMessage)));
        }

        return oReplyBean;

    }

    public ReplyBean fillNoticias() throws Exception {
        ReplyBean oReplyBean;
        ConnectionInterface oConnectionPool = null;
        Connection oConnection;
        Lorem lorem = LoremIpsum.getInstance();
        try {
            int numero = Integer.parseInt(oRequest.getParameter("num"));
            Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
            oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
            oConnection = oConnectionPool.newConnection();
            String[] titulo1 = {"La huerta", "El campo", "La cosecha", "El clima"};
            String[] titulo2 = {"de hoy", "este año", "mejora", "empeora", "en la ciudad"};
            String mensaje = lorem.getParagraphs(3, 3);
            String[] foto = {"noticias1.jpeg", "noticias2.jpeg", "noticias3.jpeg", "noticias4.jpeg", "noticias5.jpeg"};
            int[] id_usuario = {52, 53, 54, 55, 56};

            ArrayList<NoticiasBean> resultado = new ArrayList<>();

            DaoInterface oDao = DaoFactory.getDao(oConnection, ob, oUsuarioBeanSession);
            for (int i = 0; i < numero; i++) {
                NoticiasBean oBean = (NoticiasBean) BeanFactory.getBean(ob);
                oBean.setTitulo(titulo1[randomMath(titulo1.length)] + " " + titulo2[randomMath(titulo2.length)]);
                oBean.setMensaje(mensaje);
                oBean.setFoto(foto[randomMath(foto.length)]);
                oBean.setId_usuario(id_usuario[randomMath(id_usuario.length)]);
                resultado.add(oBean);
                oDao.create(oBean);
            }
            oReplyBean = new ReplyBean(200, oGson.toJson(resultado));
        } catch (Exception e) {
            throw new Exception("ERROR: Service level: create method: " + ob + " object: " + e.getMessage(), e);
        } finally {
            oConnectionPool.disposeConnection();
        }

        return oReplyBean;
    }

    private int randomMath(int number) {
        return (int) (Math.random() * number);
    }

}
