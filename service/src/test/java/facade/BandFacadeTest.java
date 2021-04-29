package facade;

import cz.fi.muni.pa165.api.dto.band.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.band.BandUpdateDTO;

import cz.fi.muni.pa165.api.facade.BandFacade;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.service.BandService;
import cz.fi.muni.pa165.service.facade.BandFacadeImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.BandMapper;

import cz.fi.muni.pa165.service.mapping.mapstruct.BandMapperImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.ManagerMapperImpl;
import org.junit.Assert;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


public class BandFacadeTest{

    private BandFacade bandFacade;

    @Mock
    private BandService bandService;

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
        MockitoAnnotations.openMocks(this);
        bandFacade = new BandFacadeImpl(bandService, bandMapper,managerMapper);

        Band band = new Band(1L, "Sabaton", Style.METAL);
        //this.testBand1 = new Band(2L, "Kabat", Style.ROCK);


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
    public void createBandTest(){
        Assert.assertNotEquals(bandCreateDTO,null);
        given(bandMapper.mapToEntity(bandCreateDTO)).willReturn(band);
        bandFacade.createBand(bandCreateDTO);
        then(bandService).should().createBand(band);
    }

    @Test
    public void updateBandTests() {
        given(bandMapper.mapToEntity(bandUpdateDTO)).willReturn(band);
        bandFacade.updateBand(bandUpdateDTO);
        then(bandService).should().updateBand(band);
    }

//    @Test
//    public void updateBandTest(){
//        bandFacade.updateBand(bandUpdateDTO);
//        verify(bandService, times(1)).updateBand(any(Band.class));
//    }
}
