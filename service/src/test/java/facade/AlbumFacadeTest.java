package facade;

import cz.fi.muni.pa165.api.facade.AlbumFacade;
import cz.fi.muni.pa165.entities.Album;
import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.service.AlbumService;

import org.mockito.Mock;

import java.util.List;

/**
 * @author Aleš Paroulek
 */
public class AlbumFacadeTest {
    private AlbumFacade albumFacade;
    private Song songA;
    private Song songB;
    private Album albumA;
    private Album albumB;
    private List<Album> albums;

    @Mock
    private AlbumService albumService;

    //@Mock
    //private BeanMapper beanMapper;


}
