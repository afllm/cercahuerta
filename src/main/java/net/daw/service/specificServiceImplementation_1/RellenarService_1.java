///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package net.daw.service.specificServiceImplementation_1;
//
//import java.util.ArrayList;
//import net.daw.bean.beanImplementation.ProductoBean;
//
//
//public class RellenarService_1 {
//
//    public ArrayList<ProductoBean> RellenarProducto(int numero) {
//        String[] codigoSt = {"tom","champ","cal","esp"};
//        int[] codigoIn1 = {1, 2, 3, 4, 5};
//        int[] codigoIn2 = {6, 7, 8, 9, 0};
//        String[] nom = {"tomates", "setas", "esparragos","calabazas"};
//        String[] nom2 = {"frescos", "de temporada","verdes","maduros","grandes"};
//        String desc = "Consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.";
//        String[] foto = {"calabazas.jpeg","esparragos.jpeg","setas.jpeg","tomates.jpeg"};
//        int[] id_tipoProducto = {1, 2, 3};
//        int[] existencias = {100, 200, 300, 400, 500};
//        
//        //Para tener mayor control de el maximo de objetos que tengo en los arrays
//       // int maxDatos = 5;
//        
//        ArrayList<ProductoBean> resultado = new ArrayList<>();
//        ProductoBean resultadoProducto;
//        for (int i  = 0;i < numero; i++ ){
//            resultadoProducto = new ProductoBean();
//            resultadoProducto.setCodigo(codigoSt[randomMath(codigoSt.length)]+" "+codigoIn1[randomMath(codigoSt.length)]+" "+codigoIn2[randomMath(codigoSt.length)]);
//            resultadoProducto.setNombre(nom[randomMath(nom.length)] + " " + nom2[randomMath(nom2.length)]);
//            
//            resultadoProducto.setPrecio((float) (((int)(Math.random()*10000))* 0.01));
//            resultadoProducto.setFoto(foto[randomMath(codigoSt.length)]);
//            resultadoProducto.setId_tipoProducto(id_tipoProducto[randomMath(id_tipoProducto.length)]);
//            resultadoProducto.setExistencias(existencias[randomMath(existencias.length)]);
//            resultado.add(resultadoProducto);
//        }
//        return resultado;
//    }
//    private int randomMath(int number){
//        return (int) (Math.random()*number);
//    }
//
//}
