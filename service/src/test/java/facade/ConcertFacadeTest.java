package facade;


import cz.fi.muni.pa165.api.dto.concert.ConcertCreateDTO;
import cz.fi.muni.pa165.api.dto.concert.ConcertDTO;
import cz.fi.muni.pa165.api.dto.concert.ConcertUpdateDTO;

import cz.fi.muni.pa165.api.facade.ConcertFacade;

import cz.fi.muni.pa165.entities.Concert;

import cz.fi.muni.pa165.service.service.concert.ConcertService;

import cz.fi.muni.pa165.service.facade.ConcertFacadeImpl;

import cz.fi.muni.pa165.service.mapping.mapstruct.ConcertMapperImpl;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;


public class ConcertFacadeTest {
    private ConcertFacade concertFacade;

    @Mock
    private ConcertService concertService;

    @Mock
    private ConcertMapperImpl concertMapper;


    private ConcertCreateDTO concertCreateDTO;
    private ConcertUpdateDTO concertUpdateDTO;
    private ConcertDTO concertDTO;
    private Concert concert;
    private Concert testConcert1;
    private List<Concert> concerts;
    private List<ConcertDTO> concertDTOS;

    @BeforeMethod
    public void init(){
        MockitoAnnotations.initMocks(this);
        concertFacade = new ConcertFacadeImpl(concertService, concertMapper);

        concert = new Concert(1L, "Brno", LocalDate.of(2020,10,10));


        concertDTO = new ConcertDTO();
        concertDTO.setId(concert.getId());
        concertDTO.setName(concert.getName());
        concertDTO.setDate(concert.getDate());

        concertCreateDTO = new ConcertCreateDTO();
        concertCreateDTO.setName(concert.getName());
        concertCreateDTO.setDate(concert.getDate());

        concertUpdateDTO = new ConcertUpdateDTO();
        concertUpdateDTO.setId(concert.getId());
        concertUpdateDTO.setName(concert.getName());
        concertUpdateDTO.setDate(concert.getDate());



    }

    @Test
    public void createConcertTest(){
        given(concertMapper.mapToEntity(concertCreateDTO)).willReturn(concert);
        concertFacade.create(concertCreateDTO);
        then(concertService).should().create(concert);
    }

    @Test
    public void updateConcertTests() {

        given(concertMapper.mapToEntity(concertUpdateDTO)).willReturn(concert);
        concertFacade.update(concertUpdateDTO);
        then(concertService).should().update(concert);
    }

    @Test
    public void findById() {
        given(concertService.findById(concert.getId())).willReturn(concert);
        given(concertMapper.mapToConcertDTO(concert)).willReturn(concertDTO);
        ConcertDTO res = concertFacade.findById(concert.getId());
        assertEquals(concertDTO, res);
        then(concertService).should().findById(concert.getId());
    }

    @Test
    public void findAllByDate() {
        given(concertService.findAllByDate(concert.getDate())).willReturn(concerts);
        given(concertMapper.mapToListDTO(concerts)).willReturn(concertDTOS);
        List<ConcertDTO> res = concertFacade.findAllByDate(concert.getDate());
        assertEquals(concertDTOS, res);
        then(concertService).should().findAllByDate(concert.getDate());
    }

    @Test
    public void findAll() {
        given(concertService.findAll()).willReturn(concerts);
        given(concertMapper.mapToListDTO(concerts)).willReturn(concertDTOS);
        List<ConcertDTO> res = concertFacade.findAll();
        assertEquals(concertDTOS, res);
        then(concertService).should().findAll();
    }

    @Test
    public void removeConcert() {
        concertFacade.remove(concertDTO);
        then(concertService).should().remove(concertMapper.mapToEntity(concertDTO));
    }
}