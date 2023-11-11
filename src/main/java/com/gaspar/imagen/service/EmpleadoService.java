package com.gaspar.imagen.service;

import com.gaspar.imagen.entity.Empleado;
import com.gaspar.imagen.repository.CompanyRepository;
import com.gaspar.imagen.repository.EmpleadoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final CompanyRepository  companyRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository, CompanyRepository companyRepository) {
        this.empleadoRepository = empleadoRepository;
        this.companyRepository = companyRepository;
    }

    public List<Empleado> findAll(){
        return empleadoRepository.findAll();
    }

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path ="Q:\\Descargas\\pizzaty.pdf";
        List<Empleado> empleados = empleadoRepository.findAll();
//        load file and compile
        File file = ResourceUtils.getFile("classpath:pizzati.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleados);
        Map<String,Object> map = new HashMap<>();
//        map.put("logo",readImage("logo.png","png"));
        byte[] logo = companyRepository.findById(1).get().getLogo();
//        map.put("logo",readImage("logo.png","png"));
        map.put("logo", new ByteArrayInputStream(logo));
        System.out.println(map);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,map,dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint,path);

        return "";
    }

    public InputStream readImage(String fileName, String imageFormat){
        InputStream inputStream = null;
        File file = null;
        try{
            file = new File(fileFullPath("logo.png",""));

            BufferedImage bufferedImage = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            ImageIO.write(bufferedImage,imageFormat,byteArrayOutputStream);

            inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();
        }catch(FileNotFoundException e){
            System.err.println("1 " + e);
        }catch (IOException e){
            System.err.println("2 " + e);
        }
        return inputStream;
    }

    public String fileFullPath(String fileName, String path){
        String filePath = null;
        StringBuilder builder = new StringBuilder();

        filePath = builder.append("").append(path).append(fileName).toString().replace("/","\\");

        return filePath;

    }
}
