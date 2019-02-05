package net.daw.service.specificServiceImplementation_1;

import net.daw.bean.beanImplementation.ReplyBean;
import net.daw.bean.beanImplementation.ProductoBean;
import net.daw.dao.specificDaoImplementation_1.ProductoDao_1;
import net.daw.service.genericServiceImplementation.GenericServiceImplementation;
import net.daw.service.publicServiceInterface.ServiceInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.publicBeanInterface.BeanInterface;
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
                        item.write(new File(".//..//webapps//images_server//productos//" + name));
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
    
       public ReplyBean fillProd() throws Exception {
        ReplyBean oReplyBean;
        ConnectionInterface oConnectionPool = null;
        Connection oConnection;
        try{
          int numero = Integer.parseInt(oRequest.getParameter("num"));
        Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
        oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
        oConnection = oConnectionPool.newConnection();
        String[] codigoSt = {"tom", "champ", "cal", "esp"};
        int[] codigoIn1 = {1, 2, 3, 4, 5};
        int[] codigoIn2 = {6, 7, 8, 9, 0};
        String[] nom = {"tomates", "setas", "esparragos","calabazas"};
        String[] nom2 = {"frescos", "de temporada","verdes","maduros","grandes"};
        String desc = "Consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.";
        String[] foto = {"calabazas.jpeg","esparragos.jpeg","setas.jpeg","tomates.jpeg"};
        int[] id_tipoProducto = {1, 2, 3};
        int[] existencias = {100, 200, 300, 400, 500};

       
        
        ArrayList<ProductoBean> resultado = new ArrayList<>();
        
        DaoInterface oDao = DaoFactory.getDao(oConnection, ob, oUsuarioBeanSession);
        for (int i = 0; i < numero; i++) {
            ProductoBean oBean =(ProductoBean) BeanFactory.getBean(ob);
            oBean.setCodigo(codigoSt[randomMath(codigoSt.length)] + "" + codigoIn1[randomMath(codigoSt.length)] + "" + codigoIn2[randomMath(codigoSt.length)]);
            oBean.setNombre(nom[randomMath(nom.length)] + " " + nom2[randomMath(nom2.length)]);
            oBean.setDesc(desc);
            oBean.setPrecio((float) (((int) (Math.random() * 10000)) * 0.01));
            oBean.setFoto(foto[randomMath(codigoSt.length)]);
            oBean.setId_tipoProducto(id_tipoProducto[randomMath(id_tipoProducto.length)]);
            oBean.setExistencias(existencias[randomMath(existencias.length)]);
            resultado.add(oBean);
            oDao.create(oBean);
        }
        oReplyBean = new ReplyBean(200, oGson.toJson(resultado));  
        }catch(Exception e){
            throw new Exception("ERROR: Service level: create method: " + ob + " object: "+e.getMessage(), e);
        }finally{
            oConnectionPool.disposeConnection();
        }
        
        
        return oReplyBean;
    }

    private int randomMath(int number) {
        return (int) (Math.random() * number);
    }

}