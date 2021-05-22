package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.band.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.dto.band.BandUpdateDTO;
import cz.fi.muni.pa165.api.facade.BandFacade;
import cz.fi.muni.pa165.entities.*;
import cz.fi.muni.pa165.service.service.album.AlbumService;
import cz.fi.muni.pa165.service.service.band.BandService;
import cz.fi.muni.pa165.service.mapping.mapstruct.BandMapperImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.ManagerMapperImpl;
import cz.fi.muni.pa165.service.service.concert.ConcertService;
import cz.fi.muni.pa165.service.service.song.SongService;
import cz.fi.muni.pa165.service.service.tour.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author Igor Ignác
 */
@Service
@Transactional
public class BandFacadeImpl implements BandFacade {

    private BandService bandService;
    private SongService songService;
    private AlbumService albumService;
    private TourService tourService;
    private ConcertService concertService;
    private BandMapperImpl bandMapper;
    private ManagerMapperImpl managerMapper;


    @Autowired
    public BandFacadeImpl(BandService bandService, SongService songService, AlbumService albumService, TourService tourService, ConcertService concertService, BandMapperImpl bandMapper, ManagerMapperImpl managerMapper) {
        this.bandService = bandService;
        this.bandMapper = bandMapper;
        this.managerMapper = managerMapper;
        this.songService = songService;
        this.albumService = albumService;
        this.tourService = tourService;
        this.concertService = concertService;
    }


    @Override
    public Long createBand(BandCreateDTO band) {
        Band createdBand = bandMapper.mapToEntity(band);
        return this.bandService.createBand(createdBand);
    }

    @Override
    public BandDTO updateBand(BandUpdateDTO band) {
        Band updateBand = bandMapper.mapToEntity(band);
        for(Album album : updateBand.getAlbums()){
            for(Song song : album.getSongs()){
                if(song.getId() == null){
                    songService.createSong(song);
                }
                else{
                    songService.updateSong(song);
                }
            }
            if(album.getId() == null){
                albumService.create(album);
            }
            else{
                albumService.update(album);
            }
        }
        for(Tour tour : updateBand.getTours()){
            for(Concert concert : tour.getConcerts()){
                if(concert.getId() == null){
                    concertService.create(concert);
                }
                else{
                    concertService.update(concert);
                }
            }
            if(tour.getId() == null){
                tourService.create(tour);
            }
            else{
                tourService.update(tour);
            }
        }
        updateBand = bandService.updateBand(updateBand);
        return bandMapper.mapToBandDTO(updateBand);
    }

    @Override
    public BandDTO findBandById(Long id) {
        Band band = bandService.findBandById(id);
        return bandMapper.mapToBandDTO(band);
    }

    @Override
    public List<BandDTO> findBandByName(String name) {
        List<Band> band = bandService.findBandByName(name);
        return bandMapper.mapToListDTO(band);
    }

    @Override
    public BandDTO findBandByManager(ManagerDTO manager) {
        Manager bandManager = managerMapper.mapToEntity(manager);
        Band band = bandService.findBandByManager(bandManager);
        return bandMapper.mapToBandDTO(band);
    }

    @Override
    public List<BandDTO> findAllBands() {
        List<Band> band = bandService.findAllBands();
        return bandMapper.mapToListDTO(band);
    }

    @Override
    public void deleteBand(BandDTO band) {
        Band deleteBand = bandMapper.mapToEntity(band);
        bandService.deleteBand(deleteBand);
    }
}
