package com.ifpb.ads.fliplearn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.ads.fliplearn.entity.VideoAula;
import com.ifpb.ads.fliplearn.repository.VideoAulaRepository;

@Service
public class VideoAulaService {

    @Autowired
    private VideoAulaRepository videoAulaRepository;


    public VideoAula createVideoAula(VideoAula videoAula) {
        return videoAulaRepository.save(videoAula);
    }

    public VideoAula updateVideoAula(VideoAula videoAula) {
        return videoAulaRepository.save(videoAula);
    }

    public void deleteVideoAula(Long id) {
        videoAulaRepository.deleteById(id);
    }

    public VideoAula getVideoAulaById(Long id) {
        return videoAulaRepository.findById(id).orElse(null);
    }
    
    public List<VideoAula> getAllVideoAulas() {
        return videoAulaRepository.findAll();
    }

    public List<VideoAula> getVideoAulasByProfessorId(Long professorId) {
        return videoAulaRepository.findAll().stream()
                .filter(video -> video.getProfessorId().equals(professorId))
                .collect(Collectors.toList());
    }

    public List<VideoAula> getVideoAulasByTitulo(String titulo) {
        return videoAulaRepository.findAll().stream()
                .filter(video -> video.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
    }

    public List<VideoAula> getVideoAulasByDataPublicacao(String dataPublicacao) {
        return videoAulaRepository.findAll().stream()
                .filter(video -> video.getDataPublicacao().equals(dataPublicacao))
                .collect(Collectors.toList());
    }
    
    public List<VideoAula> getVideoAulasByDuracao(String duracao) {
        return videoAulaRepository.findAll().stream()
                .filter(video -> video.getDuracao().equals(duracao))
                .collect(Collectors.toList());
    }
    
}
