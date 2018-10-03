package cz.cvut.fit.tjv.employeeis.rest;

import cz.cvut.fit.tjv.employeeis.dto.DepartmentDTO;
import cz.cvut.fit.tjv.employeeis.dto.EmployeeDTO;
import cz.cvut.fit.tjv.employeeis.entity.Department;
import cz.cvut.fit.tjv.employeeis.entity.Employee;

/**
 * Konverzni utility trida mezi entitami a DTO tridami pro Employee.
 * @author guthondr
 */

public class EmployeeEntityDtoConverter {
    public static Employee dtoToEntity(EmployeeDTO dto) {
        Employee result = new Employee();
        
        result.setId(dto.getId());
        result.setName(dto.getName());
        
        Department d = new Department();
        if (dto.getDepartment() != null) {
            d.setSite(dto.getDepartment().getSite());
            d.setTitle(dto.getDepartment().getTitle());
        }
        
        result.setDepartment(d);
        
        return result;        
    }
    
    public static EmployeeDTO entityToDto(Employee entity) {
        if (entity == null)
            return null;
        
        EmployeeDTO result = new EmployeeDTO();
        
        result.setId(entity.getId());
        result.setName(entity.getName());
        
        DepartmentDTO d = new DepartmentDTO();
        d.setSite(entity.getDepartment().getSite());
        d.setTitle(entity.getDepartment().getTitle());
        
        result.setDepartment(d);
        
        return result;
    }
}
