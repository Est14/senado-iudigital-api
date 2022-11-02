package com.est14.senadoiudigital.service.impl;

import com.est14.senadoiudigital.dto.ProyectoDto;
import com.est14.senadoiudigital.model.*;
import com.est14.senadoiudigital.repo.EstadoProyectoRepo;
import com.est14.senadoiudigital.repo.ProponenteRepo;
import com.est14.senadoiudigital.repo.ProyectoRepo;
import com.est14.senadoiudigital.repo.VotoRepo;
import com.est14.senadoiudigital.service.ProyectoService;
import com.est14.senadoiudigital.service.SenadorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepo proyectoRepo;
    private final ProponenteRepo proponenteRepo;
    private final EstadoProyectoRepo estadoProyectoRepo;

    private final SenadorService senadorService;

    private final VotoRepo votoRepo;


    public Optional<Proyecto> getOne(Integer id){
        return proyectoRepo.findById(id);
    }

    public List<Proyecto> getAll(){
        return proyectoRepo.findAll();
    }

    @Transactional
    public void create(Integer idProponente, ProyectoDto proyectoDto){

        Proponente proponente = proponenteRepo.findById(idProponente).orElseThrow();
        Estado estadoProyecto = estadoProyectoRepo.findById(1).orElseThrow();
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(proyectoDto.getNombre());
        proyecto.setDescripcion(proyectoDto.getDescripcion());
        proyecto.setFecha(new Date());
        proyecto.setProponente(proponente);
        proyecto.setEstado(estadoProyecto);
        proyecto.setVotos(Collections.emptyList());

        proyectoRepo.save(proyecto);
    }

    public void update(Integer idproyecto, ProyectoDto proyectoDto){

        Proyecto proyecto = proyectoRepo.findById(idproyecto).orElseThrow();
        proyecto.setNombre(proyectoDto.getNombre());
        proyecto.setDescripcion(proyectoDto.getDescripcion());
        proyectoRepo.save(proyecto);

    }

    @Override
    public void delete(Integer id) {
        proyectoRepo.deleteById(id);
    }


    //Logic votes

    @Transactional
    public void votarProyecto(int idProyecto){

        String[] opciones = {"SI", "NO"};

        List<Senador> senadores = senadorService.getAll();
        Proyecto proyecto = proyectoRepo.findById(idProyecto).orElseThrow();


        // Votacion
        for (Senador senador: senadores) {
            Voto voto = new Voto();
            voto.setProyecto(proyecto);
            voto.setSenador(senador);
            voto.setVoto(opciones[(int) Math.round(Math.random())]);
            log.info(senador.getNombre() + " : " + voto.getVoto());
            votoRepo.save(voto);
        }

        // Conteo de votos
        List<Voto> votos = votoRepo.findByProyecto(proyecto);
        long si = votos.stream().filter(v -> v.getVoto().equals("SI")).count();
        long no = votos.stream().filter(v -> v.getVoto().equals("NO")).count();
        log.info("Votos por el SI: " + si);
        log.info("Votos por el NO: " + no);

        // Resultado final
        if (si > no){
            Estado estado = estadoProyectoRepo.findById(4).orElseThrow();
            proyecto.setEstado(estado);
            proyectoRepo.save(proyecto);
        }
        else {
            Estado estado = estadoProyectoRepo.findById(5).orElseThrow();
            proyecto.setEstado(estado);
            proyectoRepo.save(proyecto);
        }


    }


}
