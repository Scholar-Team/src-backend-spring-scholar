package com.scholar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.DisciplineDTO;
import com.scholar.mapper.DisciplineMapper;
import com.scholar.model.Classroom;
import com.scholar.model.Discipline;
import com.scholar.model.Teacher;
import com.scholar.repository.ClassroomRepository;
import com.scholar.repository.DisciplineRepository;
import com.scholar.repository.TeacherRepository;
import com.scholar.request.DisciplineRequest;
import com.scholar.service.generic.BaseService;

@Service
public class DisciplineService extends BaseService<Discipline, DisciplineDTO, DisciplineRequest> {

	private DisciplineRepository repository;
	private ClassroomRepository crRepository;
	private TeacherRepository tRepository;
	private DisciplineMapper mapper;
	
	@Autowired
	public DisciplineService(DisciplineRepository repository, 
			DisciplineMapper mapper, ClassroomRepository crRepository,
			TeacherRepository tRepository) {
		super(repository, mapper);
		
		this.repository = repository;
		this.crRepository = crRepository;
		this.tRepository = tRepository;
		this.mapper = mapper;
	}
	
	@Override
	public Optional<DisciplineDTO> save(DisciplineRequest request) {
		Discipline discipline = mapper.requestToModel(request);
		List<Classroom> classrooms = new ArrayList<>();
		List<Teacher> teachers = new ArrayList<>();
		
		for(Classroom classroom : discipline.getClassrooms()) {
			Classroom newC = crRepository.findById(classroom.getId()).get();
			newC.getDisciplines().add(discipline);
			
			classrooms.add(newC);
		}
		
		for(Teacher teacher : discipline.getTeachers()) {
			Teacher newT = tRepository.findById(teacher.getId()).get();
			newT.getDisciplines().add(discipline);
			
			teachers.add(newT);
		}
		
		discipline.setClassrooms(classrooms);
		discipline.setTeachers(teachers);
		return Optional.of(mapper.modelToDTO(repository.save(discipline)));
	}

}
