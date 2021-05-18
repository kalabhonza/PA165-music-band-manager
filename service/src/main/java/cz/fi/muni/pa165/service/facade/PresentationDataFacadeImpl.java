package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.facade.PresentationDataFacade;
import cz.fi.muni.pa165.service.service.presentation.PresentationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PresentationDataFacadeImpl implements PresentationDataFacade {

    private PresentationDataService presentationDataService;

    @Autowired
    public PresentationDataFacadeImpl(PresentationDataService presentationDataService) {this.presentationDataService = presentationDataService;}

    @Override
    public void createData() {
        presentationDataService.createData();
    }
}
