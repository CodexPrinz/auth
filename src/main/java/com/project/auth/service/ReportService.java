package com.project.auth.service;

import com.project.auth.entity.User;
import com.project.auth.repository.UserRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private static final String reportDestination = System.getenv("DEST");


    public String exportReport(String reportFormat) throws JRException {
        logger.info("export report with format: "+reportFormat);
        List<User> users = (List<User>) userRepository.findAll();
        File file;
        // load file and compile
        try {
            file = ResourceUtils.getFile("classpath:User_Blue.jrxml");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error getting jasper report file");
        }

        if (file == null){

        }
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "com.projects.auth");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDestination+"\\users.html");  // your preferred destination
        } else if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDestination+"\\users.pdf");
        }
        return  "Report generated in "+reportDestination;
    }
}
