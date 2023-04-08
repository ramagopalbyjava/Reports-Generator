package com.ashoikIt.Insurance.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashoikIt.Insurance.dto.SearchRequest;
import com.ashoikIt.Insurance.entity.CitizenPlan;
import com.ashoikIt.Insurance.repo.CitizenPlanRepository;
import com.ashoikIt.Insurance.service.ReportService;

@Service
public class RequestServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepository;

	@Override
	public List<String> getPlanName() {

		return planRepository.getCitizenPlanName();
	}

	@Override
	public List<String> getPlanStatus() {

		return planRepository.getCitizenPlaNStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		// filter applying
		// using Dynamic Query using of Example of()

		CitizenPlan entity = new CitizenPlan();
		BeanUtils.copyProperties(request, entity);
		return planRepository.findAll(Example.of(entity));
		//Dates will not work because bugs there means diff formates
		//Remaining will works three plan names ,plan status and gender
		
		
	}

	@Override
	public boolean exportExcel() {

		return false;
	}

	@Override
	public boolean exportPdf() {

		return false;
	}

}
