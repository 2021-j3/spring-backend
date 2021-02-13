package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.TagDto.TagApiRequest;
import com.ecommerce.j3.controller.dto.TagDto.TagApiResponse;
import com.ecommerce.j3.domain.entity.Tag;
import com.ecommerce.j3.domain.mapper.TagMapper;
import com.ecommerce.j3.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagApiLogicService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagApiResponse saveTag(TagApiRequest request) {
        Tag tag = tagMapper.toEntity(request);
        tagRepository.save(tag);
        return tagMapper.toApiResponse(tag);
    }

    public TagApiResponse updateTag(TagApiRequest request) {
        Tag tagFromDB = findById(request.getTagId());
        tagMapper.updateFromDto(tagFromDB, request);
        tagRepository.save(tagFromDB);
        return tagMapper.toApiResponse(tagFromDB);
    }

    public TagApiResponse findTag(Long tagId) {
        Tag tagFromDB = findById(tagId);
        return tagMapper.toApiResponse(tagFromDB);
    }

    public void removeTag(Long id) {
        tagRepository.deleteById(id);
    }

    // 패키지 한정자, service패키지 내에서만 접근 가능
    Tag findById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
