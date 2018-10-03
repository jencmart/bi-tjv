package cz.cvut.fit.tjv.employeeis.rest;

import cz.cvut.fit.tjv.employeeis.dto.DepartmentDTO;
import cz.cvut.fit.tjv.employeeis.entity.Department;

/**
 * Konverzni utility trida mezi entitami a DTO tridami pro Department.
 * @author guthondr
 */

public class DepartmentEntityDtoConverter {

    public static DepartmentDTO entityToDto(Department entity) {
        if (entity == null) {
            return null;
        }
        DepartmentDTO result = new DepartmentDTO();

        result.setSite(entity.getSite());
        result.setTitle(entity.getTitle());

//        List<EmployeeDTO> empsDto = new ArrayList<>();
//        for (Employee e : entity.getEmployees()) {
//            EmployeeDTO eDto = EmployeeEntityDtoConverter.entityToDto(e);
//            eDto.setDepartment(result);
//            empsDto.add(eDto);
//        }
//        result.setEmployees(empsDto);
        return result;
    }

    public static Department dtoToEntity(DepartmentDTO dto) {
        Department result = new Department();

        result.setSite(dto.getSite());
        result.setTitle(dto.getTitle());

//        List<Employee> empsEntity = new ArrayList<>();
//        for (EmployeeDTO e : dto.getEmployees()) {
//            Employee eEntity = EmployeeEntityDtoConverter.dtoToEntity(e);
//            eEntity.setDepartment(result);
//            empsEntity.add(eEntity);
//        }
//        result.setEmployees(empsEntity);
        return result;
    }
}
