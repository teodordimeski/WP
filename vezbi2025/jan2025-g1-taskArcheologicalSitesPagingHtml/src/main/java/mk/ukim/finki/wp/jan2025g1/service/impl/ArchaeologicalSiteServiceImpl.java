package mk.ukim.finki.wp.jan2025g1.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.jan2025g1.model.ArchaeologicalSite;
import mk.ukim.finki.wp.jan2025g1.model.HistoricalPeriod;
import mk.ukim.finki.wp.jan2025g1.model.SiteLocation;
import mk.ukim.finki.wp.jan2025g1.model.exceptions.InvalidArchaeologicalSiteIdException;
import mk.ukim.finki.wp.jan2025g1.repository.ArchaeologicalSiteRepository;
import mk.ukim.finki.wp.jan2025g1.repository.SiteLocationRepository;
import mk.ukim.finki.wp.jan2025g1.service.ArchaeologicalSiteService;
import mk.ukim.finki.wp.jan2025g1.service.SiteLocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArchaeologicalSiteServiceImpl implements ArchaeologicalSiteService {

    private final ArchaeologicalSiteRepository archaeologicalSiteRepository;
    private final SiteLocationService siteLocationService;

    @Override
    public List<ArchaeologicalSite> listAll() {
        return archaeologicalSiteRepository.findAll();
    }

    @Override
    public ArchaeologicalSite findById(Long id) {
        return archaeologicalSiteRepository.findById(id).orElseThrow(InvalidArchaeologicalSiteIdException::new);
    }

    @Override
    public ArchaeologicalSite create(String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId) {

        SiteLocation siteLocation = siteLocationService.findById(locationId);

        return archaeologicalSiteRepository.save(new ArchaeologicalSite(name, areaSize, rating, period, siteLocation));
    }

    @Override
    public ArchaeologicalSite update(Long id, String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId) {

        SiteLocation siteLocation = siteLocationService.findById(locationId);
        ArchaeologicalSite archaeologicalSite = findById(id);

        archaeologicalSite.setName(name);
        archaeologicalSite.setAreaSize(areaSize);
        archaeologicalSite.setRating(rating);
        archaeologicalSite.setPeriod(period);
        archaeologicalSite.setLocation(siteLocation);
        archaeologicalSiteRepository.save(archaeologicalSite);

        return archaeologicalSiteRepository.save(archaeologicalSite);
    }

    @Override
    public ArchaeologicalSite delete(Long id) {

        ArchaeologicalSite archaeologicalSite = findById(id);
        archaeologicalSiteRepository.delete(archaeologicalSite);

        return archaeologicalSite;
    }

    @Override
    public ArchaeologicalSite close(Long id) {

        ArchaeologicalSite archaeologicalSite = findById(id);
        archaeologicalSite.setClosed(true);

        return archaeologicalSiteRepository.save(archaeologicalSite);
    }

    @Override
    public Page<ArchaeologicalSite> findPage(String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId, int pageNum, int pageSize) {

        Specification<ArchaeologicalSite> specification = Specification.allOf(
                FieldFilterSpecification.filterContainsText(ArchaeologicalSite.class,"name" , name),
                FieldFilterSpecification.greaterThan(ArchaeologicalSite.class,"areaSize" , areaSize),
                FieldFilterSpecification.greaterThan(ArchaeologicalSite.class,"rating" , rating),
                FieldFilterSpecification.filterEqualsV(ArchaeologicalSite.class,"period" , period),
                FieldFilterSpecification.filterEqualsV(ArchaeologicalSite.class,"location.Id" , locationId)
        );

        return archaeologicalSiteRepository.findAll(specification, PageRequest.of(pageNum, pageSize));
    }
}
