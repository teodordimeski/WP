package mk.ukim.finki.wp.june2025g1.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.june2025g1.model.Founder;
import mk.ukim.finki.wp.june2025g1.model.Industry;
import mk.ukim.finki.wp.june2025g1.model.Startup;
import mk.ukim.finki.wp.june2025g1.model.exceptions.InvalidStartupIdException;
import mk.ukim.finki.wp.june2025g1.repository.FounderRepository;
import mk.ukim.finki.wp.june2025g1.repository.StartupRepository;
import mk.ukim.finki.wp.june2025g1.service.FounderService;
import mk.ukim.finki.wp.june2025g1.service.StartupService;
import mk.ukim.finki.wp.june2025g1.service.specifications.FieldFilterSpecification;
import org.springframework.data.domain.Page;
import mk.ukim.finki.wp.june2025g1.service.specifications.FieldFilterSpecification.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StartupServiceImpl implements StartupService {

    private final FounderService founderService;
    private final StartupRepository startupRepository;

    @Override
    public List<Startup> listAll() {
        return startupRepository.findAll();
    }

    @Override
    public Startup findById(Long id) {
        return startupRepository.findById(id).orElseThrow(InvalidStartupIdException::new);
    }

    @Override
    public Startup create(String name, Double valuation, Integer yearFounded, Industry industry, Long founderId) {

        Founder founder = founderService.findById(founderId);

        return startupRepository.save(new Startup(name, valuation, yearFounded, industry, founder));
    }

    @Override
    public Startup update(Long id, String name, Double valuation, Integer yearFounded, Industry industry, Long founderId) {

        Founder founder = founderService.findById(founderId);
        Startup startup = findById(id);

        startup.setName(name);
        startup.setValuation(valuation);
        startup.setYearFounded(yearFounded);
        startup.setIndustry(industry);
        startup.setFounder(founder);

        return startupRepository.save(startup);
    }

    @Override
    public Startup delete(Long id) {

        Startup startup = findById(id);
        startupRepository.delete(startup);

        return startup;
    }

    @Override
    public Startup deactivate(Long id) {

        Startup startup = findById(id);
        startup.setActive(false);


        return startupRepository.save(startup);
    }

    @Override
    public Page<Startup> findPage(String name, Double valuation, Integer yearFounded, Industry industry, Long founderId, int pageNum, int pageSize) {

        Specification<Startup> startupSpecification = Specification.allOf(
                FieldFilterSpecification.filterContainsText(Startup.class,"name",name),
                FieldFilterSpecification.greaterThan(Startup.class,"valuation",valuation),
                FieldFilterSpecification.greaterThan(Startup.class,"yearFounded",yearFounded),
                FieldFilterSpecification.filterEqualsV(Startup.class,"industry",industry),
                FieldFilterSpecification.filterEqualsV(Startup.class,"founder.Id",founderId)
        );

        return startupRepository.findAll(startupSpecification, PageRequest.of(pageNum, pageSize));
    }
}
