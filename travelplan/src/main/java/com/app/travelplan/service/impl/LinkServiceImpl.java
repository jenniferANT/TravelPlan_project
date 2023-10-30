package com.app.travelplan.service.impl;

import com.app.travelplan.model.dto.LinkDto;
import com.app.travelplan.model.entity.Link;
import com.app.travelplan.model.form.LinkForm;
import com.app.travelplan.repository.LinkRepository;
import com.app.travelplan.service.LinkService;
import com.app.travelplan.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;

    @Override
    public LinkDto save(LinkForm linkForm) {
        return LinkDto.toDto(
                linkRepository.save(toEntity(linkForm)));
    }

    @Override
    public List<LinkDto> getAll() {
        return linkRepository.findAll()
                .stream()
                .map(LinkDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {
        linkRepository.deleteById(id);
    }

    @Override
    public LinkDto update(LinkForm linkForm, long id) {

        Link link = linkRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Link not found with id " + id));

        if(link.getPlaces().getUser().getUsername().equals(SecurityUtils.getUsernameOfPrincipal())) {
            Link l = toEntity(linkForm);
            l.setId(id);
            return LinkDto.toDto(linkRepository.save(l));
        }
        throw new IllegalArgumentException("Update places not permit");
    }

    @Override
    public LinkDto getById(long id) {
        return LinkDto.toDto(linkRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Link not found with id " + id)));
    }

    private Link toEntity(LinkForm linkForm) {
        return Link.builder()
                .name(linkForm.getName())
                .url(linkForm.getUrl())
                .build();
    }
}
