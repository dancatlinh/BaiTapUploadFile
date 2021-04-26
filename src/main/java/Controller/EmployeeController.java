package Controller;

import Service.EmployeeService;
import Service.IEmployeeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import Model.Employee;
import Model.EmployeeForm;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Controller
@RequestMapping("/employee")

public class EmployeeController {
    private final IEmployeeService employeeService = new EmployeeService();
    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping("")
    public String index(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "/index";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("employeeForm", new EmployeeForm());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveEmployee(@ModelAttribute EmployeeForm employeeForm) {
        MultipartFile multipartFile = employeeForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Employee employee = new Employee(employeeForm.getIdEmployee(), employeeForm.getNameEmployee(),
                employeeForm.getDate(),fileName,employeeForm.isGender());
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("employeeForm", employeeForm);
        modelAndView.addObject("message", "Created new employee successfully !");
        return modelAndView;
    }
}
