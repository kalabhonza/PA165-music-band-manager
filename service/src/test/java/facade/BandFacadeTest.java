package facade;

import cz.fi.muni.pa165.api.dto.band.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.band.BandUpdateDTO;

import cz.fi.muni.pa165.api.facade.*;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.service.service.album.AlbumService;
import cz.fi.muni.pa165.service.service.band.BandService;
import cz.fi.muni.pa165.service.facade.BandFacadeImpl;


import cz.fi.muni.pa165.service.mapping.mapstruct.BandMapperImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.ManagerMapperImpl;


import cz.fi.muni.pa165.service.service.concert.ConcertService;
import cz.fi.muni.pa165.service.service.song.SongService;
import cz.fi.muni.pa165.service.service.tour.TourService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;


public class BandFacadeTest{

    private BandFacade bandFacade;
    private TourFacade tourFacade;
    private ConcertFacade concertFacade;
    private AlbumFacade albumFacade;
    private SongFacade songFacede;

    @Mock
    private BandService bandService;
    @Mock
    private TourService tourService;
    @Mock
    private ConcertService concertService;
    @Mock
    private AlbumService albumService;
    @Mock
    private SongService songService;

    @Mock
    private BandMapperImpl bandMapper;

    @Mock
    private ManagerMapperImpl managerMapper;


    private BandCreateDTO bandCreateDTO;
    private BandUpdateDTO bandUpdateDTO;
    private BandDTO bandDTO;
    private Band band;
    private Band testBand1;
    private List<Band> bands;
    private List<BandDTO> bandDTOs;

    @BeforeMethod
    public void init(){
        MockitoAnnotations.initMocks(this);
        bandFacade = new BandFacadeImpl(bandService, songService, albumService, tourService, concertService, bandMapper,managerMapper);

        band = new Band(1L, "Sabaton", Style.METAL);


        bandDTO = new BandDTO();
        bandDTO.setId(band.getId());
        bandDTO.setName(band.getName());
        bandDTO.setStyle(band.getStyle());

        bandCreateDTO = new BandCreateDTO();

        bandCreateDTO.setName(band.getName());
        bandCreateDTO.setStyle(band.getStyle());

        bandUpdateDTO = new BandUpdateDTO();
        bandUpdateDTO.setId(band.getId());
        bandUpdateDTO.setName(band.getName());
        bandUpdateDTO.setStyle(band.getStyle());
    }

    @Test
    public void createBand(){
        given(bandMapper.mapToEntity(bandCreateDTO)).willReturn(band);
        bandFacade.createBand(bandCreateDTO);
        then(bandService).should().createBand(band);
    }

    @Test
    public void updateBand() {
        given(bandMapper.mapToEntity(bandUpdateDTO)).willReturn(band);
        bandFacade.updateBand(bandUpdateDTO);
        then(bandService).should().updateBand(band);
    }

    @Test
    public void findById(){
        given(bandService.findBandById(band.getId())).willReturn(band);
        given(bandMapper.mapToBandDTO(band)).willReturn(bandDTO);
        BandDTO res = bandFacade.findBandById((band.getId()));
        assertEquals(bandDTO, res);
        then(bandService).should().findBandById(band.getId());
    }

    @Test
    public void findByName() {
        given(bandService.findBandByName(band.getName())).willReturn(bands);
        given(bandMapper.mapToListDTO(bands)).willReturn(bandDTOs);
        List<BandDTO> res = bandFacade.findBandByName(band.getName());
        assertEquals(bandDTOs, res);
        then(bandService).should().findBandByName(band.getName());
    }

    @Test
    public void findAll() {
        given(bandService.findAllBands()).willReturn(bands);
        given(bandMapper.mapToListDTO(bands)).willReturn(bandDTOs);
        List<BandDTO> res = bandFacade.findAllBands();
        assertEquals(bandDTOs, res);
        then(bandService).should().findAllBands();
    }


    @Test
    public void removeBand() {
        bandFacade.deleteBand(bandDTO);
        then(bandService).should().deleteBand(bandMapper.mapToEntity(bandDTO));
    }
}
