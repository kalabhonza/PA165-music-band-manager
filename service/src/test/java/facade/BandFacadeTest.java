package facade;


import com.github.dozermapper.core.inject.Inject;
import cz.fi.muni.pa165.api.dto.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.BandDTO;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.service.BandService;
import cz.fi.muni.pa165.service.facade.BandFacadeImpl;
import cz.fi.muni.pa165.service.mapping.BeanMapper;
import cz.fi.muni.pa165.service.mapping.BeanMapperImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


import org.junit.Rule;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


@ContextConfiguration(classes = BeanMapperImpl.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class BandFacadeTest{

    @Mock
    private BandService bandService;

    @Mock
    private BeanMapper beanMapper;

    @Inject
    @InjectMocks
    private BandFacadeImpl bandFacade;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private BandCreateDTO bandCreateDTO;

    private BandDTO bandDTO;

    private Band band;

    private Band testBand1;

    @Before
    public void setUp(){
        this.band = new Band(1L, "Sabaton", Style.METAL);
        this.testBand1 = new Band(2L, "Kabat", Style.ROCK);


        this.bandCreateDTO = new BandCreateDTO();
        this.bandCreateDTO.setName("One Direction");
        this.bandCreateDTO.setStyle(Style.POP);

        when(bandService.updateBand(any(Band.class))).thenReturn(testBand1);



    }

    @Test
    public void createBandTest(){
        bandFacade.createBand(bandCreateDTO);
        verify(bandService, times(1)).createBand(any(Band.class));
    }

    @Test
    public void updateBandTest(){
        bandFacade.updateBand(bandDTO);
        verify(bandService, times(1)).updateBand(any(Band.class));
    }





}
