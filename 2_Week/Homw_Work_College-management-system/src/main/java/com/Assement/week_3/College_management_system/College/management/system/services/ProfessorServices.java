package com.Assement.week_3.College_management_system.College.management.system.services;

import com.Assement.week_3.College_management_system.College.management.system.dto.ProfessorDTO;
import com.Assement.week_3.College_management_system.College.management.system.entities.ProfessorEntity;
import com.Assement.week_3.College_management_system.College.management.system.entities.StudentEntity;
import com.Assement.week_3.College_management_system.College.management.system.entities.SubjectEntity;
import com.Assement.week_3.College_management_system.College.management.system.repositires.ProfessorRepositires;
import com.Assement.week_3.College_management_system.College.management.system.repositires.StudentRepositires;
import com.Assement.week_3.College_management_system.College.management.system.repositires.SubjectRepositires;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProfessorServices {

    private final ProfessorRepositires professorRepositires;
    private  final ModelMapper modelMapper;
    private final SubjectRepositires subjectRepositires;
    private  final StudentRepositires studentRepositires;

    public ProfessorServices(ProfessorRepositires professorRepositires, ModelMapper modelMapper, SubjectRepositires subjectRepositires, StudentRepositires studentRepositires) {
        this.professorRepositires = professorRepositires;
        this.modelMapper = modelMapper;
        this.subjectRepositires = subjectRepositires;
        this.studentRepositires = studentRepositires;
    }


    public ProfessorDTO createProfessor(ProfessorDTO professor) {
        ProfessorEntity professorEntity =  modelMapper.map(professor, ProfessorEntity.class);
         professorEntity = professorRepositires.save(professorEntity);
        return modelMapper.map(professorEntity, ProfessorDTO.class);
    }


    public List<ProfessorDTO> getAllProfessor() {
        return professorRepositires.findAll().stream()
                .map(professorEntity -> modelMapper.map(professorEntity, ProfessorDTO.class))
                .collect(Collectors.toList());
    }

    public ProfessorDTO getProfessorByID(Long professorID) {
        ProfessorEntity professorEntity = professorRepositires.findById(professorID).orElse(null);
        return modelMapper.map(professorEntity, ProfessorDTO.class);
    }


    public ProfessorDTO professorOneToManyMappingWithSubject(Long professorId, Long subjectId) {
        Optional<ProfessorEntity> professorEntity = professorRepositires.findById(professorId);
        Optional<SubjectEntity> subjectEntity = subjectRepositires.findById(subjectId);

        if(professorEntity.isPresent() && subjectEntity.isPresent()){
            ProfessorEntity professor = professorEntity.get();
            SubjectEntity subject = subjectEntity.get();

            subject.setProfessor(professor);
            subjectRepositires.save(subject);

            professor.getSubjects().add(subject);
            professorRepositires.save(professor);

            return modelMapper.map(professorEntity, ProfessorDTO.class);
        }else {
            throw  new EntityNotFoundException("Professor and  Subject entity Not found");
        }
    }

    public ProfessorDTO professorManyToManyMappingWithStudent(Long professorId, Long studentId) {
        Optional<ProfessorEntity> professorEntity = professorRepositires.findById(professorId);
        Optional<StudentEntity> studentEntity = studentRepositires.findById(studentId);

        if(professorEntity.isPresent() && studentEntity.isPresent()){
            ProfessorEntity professor = professorEntity.get();
            StudentEntity student = studentEntity.get();

            professor.getStudents().add(student);
            student.getProfessers().add(professor);

            professorRepositires.save(professor);
            studentRepositires.save(student);

            return  modelMapper.map(professor, ProfessorDTO.class);

        }else{
            throw  new EntityNotFoundException("Professor and  Student entity Not found");
        }
    }
}
