package com.hanghae.gallery.service;

import com.hanghae.gallery.model.Work;
import com.hanghae.gallery.repository.InfinityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class InfinityService {

    @Autowired
    private InfinityRepository infinityRepository;

     // 메인
    @Transactional
    public Page<Work> main(Pageable pageable) {

        return infinityRepository.findAll(pageable);
    }

   // 메인 무한스크롤
   @Transactional
    public Page<Work> mainInfinity(Long lastWorkId, Pageable pageable) {
        return infinityRepository.findAllByIdLessThan(lastWorkId, pageable);
    }

   // 작가 페이지 처음 접근
   @Transactional
    public Page<Work> artist(Long artistId, Pageable pageable) {
        return infinityRepository.findAllByArtistId(artistId,pageable);
    }

    // 작가 페이지 무한스크롤
    @Transactional
    public Page<Work> artistInfinity(Long artistId, Long lastWorkId, Pageable pageable) {
        return infinityRepository.findAllByArtistIdAndIdLessThan(artistId,lastWorkId,pageable);
    }
}
