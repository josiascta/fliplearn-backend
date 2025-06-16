package com.ifpb.ads.fliplearn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ifpb.ads.fliplearn.entity.VideoAula;
import com.ifpb.ads.fliplearn.service.VideoAulaService;

@RestController
public class VideoAulaController {

    @Autowired
    private VideoAulaService videoAulaService;


    @GetMapping("/video-aulas")
    public List<VideoAula> getAllVideoAulas() {
        return videoAulaService.getAllVideoAulas();
    }

    @GetMapping("/video-aulas/titulo")
    public List<VideoAula> getVideoAulasByTitulo(String titulo) {
        return videoAulaService.getVideoAulasByTitulo(titulo);
    }

    @GetMapping("/video-aulas/professor")
    public List<VideoAula> getVideoAulasByProfessorId(Long professorId) {
        return videoAulaService.getVideoAulasByProfessorId(professorId);
    }

    @GetMapping("/video-aulas/data-publicacao")
    public List<VideoAula> getVideoAulasByDataPublicacao(String dataPublicacao) {
        return videoAulaService.getVideoAulasByDataPublicacao(dataPublicacao);
    }

    @GetMapping("/video-aulas/duracao")
    public List<VideoAula> getVideoAulasByDuracao(String duracao) {
        return videoAulaService.getVideoAulasByDuracao(duracao);
    }
    
    @GetMapping("/video-aulas/id")
    public VideoAula getVideoAulaById(Long id) {
        return videoAulaService.getVideoAulaById(id);
    }

    @PostMapping("/video-aulas")
    public VideoAula createVideoAula(@RequestBody VideoAula videoAula) {
        return videoAulaService.createVideoAula(videoAula);
    }

    @PutMapping("/video-aulas")
    public VideoAula updateVideoAula(@RequestBody VideoAula videoAula) {
        return videoAulaService.updateVideoAula(videoAula);
    }

    @DeleteMapping("/video-aulas/{id}")
    public void deleteVideoAula(@PathVariable Long id) {
        videoAulaService.deleteVideoAula(id);
    }
}
