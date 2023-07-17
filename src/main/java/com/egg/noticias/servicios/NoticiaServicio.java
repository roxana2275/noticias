package com.egg.noticias.servicios;

import com.egg.noticias.entidades.Noticia;
import com.egg.noticias.excepciones.MiExcepcion;
import com.egg.noticias.repositorio.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticiaServicio {

    @Autowired
    NoticiaRepositorio noticiaRepositorio;
    
    // CARGAR NUEVA NOTICIA

    @Transactional
    public void cargarNoticia(String titulo, String cuerpo) throws MiExcepcion{

        validar(titulo);
        validar(cuerpo);

        Noticia noticia = new Noticia();

        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);

        noticiaRepositorio.save(noticia);

    }
    
    //LISTAR NOTICIAS

    @Transactional(readOnly = true)
    public List<Noticia> listadoNoticias() {

        List<Noticia> noticias = new ArrayList();
        noticias = noticiaRepositorio.findAll();
        return noticias;

    }

    
    //MODIFICAR NOTICIAS
    
    
    @Transactional
    public void modificarNoticia(String id, String titulo, String nombre) throws MiExcepcion {
        validar(titulo);
        validar(nombre);

        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();

            noticia.setTitulo(titulo);
            noticia.setCuerpo(titulo);

            noticiaRepositorio.save(noticia);
        }
    }

    //BUSCAR NOTICIA POR ID
    
    
    @Transactional(readOnly = true)
    public Noticia getOne(String id) {
        return noticiaRepositorio.getOne(id);
    }
    
    //ELIMINAR NOTICIA

    @Transactional
    public void eliminar(String id) throws MiExcepcion {

        Noticia noticia = noticiaRepositorio.getById(id);

        noticiaRepositorio.delete(noticia);

    }
    
    //VALIDAR QUE LAS VARIABLES NO ESTEN VACIAS

    private void validar(String nombre) throws MiExcepcion {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiExcepcion("el nombre no puede ser nulo o estar vacio");
        }
    }
}

