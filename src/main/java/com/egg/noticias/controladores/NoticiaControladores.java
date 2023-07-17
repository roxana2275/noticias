package com.egg.noticias.controladores;

import com.egg.noticias.entidades.Noticia;
import com.egg.noticias.excepciones.MiExcepcion;
import com.egg.noticias.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/noticia")//localhost:8080/noticia
public class NoticiaControladores {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/ver")
    public String ver() {

        return "noticia_ver.html";
    }

    @GetMapping("/administrar")
    public String administrar(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.listadoNoticias();

        modelo.addAttribute("noticias", noticias);

        return "noticia_administrar.html";
    }

    @GetMapping("/cargar")
    public String cargar() {

        return "noticia_cargar.html";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) throws MiExcepcion {

        try {
            noticiaServicio.cargarNoticia(titulo, cuerpo);
            modelo.put("exito", "La noticia se guardo exitosamente");

        } catch (MiExcepcion ex) {
            modelo.put("error", ex.getMessage());
            return "index.html";
        }
        return "index.html";
    }

    @GetMapping("/borrar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) throws MiExcepcion {
        System.out.println(id);
        try {
            noticiaServicio.eliminar(id);
            modelo.put("exito", "La noticia se borró exitosamente");
        } catch (MiExcepcion ex) {
            modelo.put("error", ex.getMessage());
            return "noticia_administrar.html";
        }
        return "noticia_administrar.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificarNoticia(@PathVariable String id, ModelMap modelo){
        
        modelo.put("noticia", noticiaServicio.getOne(id));
        return "noticia_modificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificarNoticia(@PathVariable String id, String titulo, String cuerpo, ModelMap modelo){
        System.out.println(id+" "+titulo+" "+cuerpo);
        try {
            noticiaServicio.modificarNoticia(id, titulo, cuerpo);
            modelo.put("exito", "Noticia modificada");
            return "redirecto:../lista";
        } catch (MiExcepcion ex) {
            modelo.put("error", ex.getMessage());
            return "noticia_modificar.html";
        }
        
    }

}

