package com.example.hospitalmanagements.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.Patient;
import com.example.hospitalmanagements.entity.PatientAdditionLog;
import com.example.hospitalmanagements.entity.PatientDeletionLog;
import com.example.hospitalmanagements.entity.bedroom.MsBedroom;
import com.example.hospitalmanagements.repository.ArchivedPatientRepository;
import com.example.hospitalmanagements.repository.PatientAdditionLogRepository;
import com.example.hospitalmanagements.repository.PatientDeletionLogRepository;
import com.example.hospitalmanagements.repository.PatientRepository;
import com.example.hospitalmanagements.repository.PatientUpdateLogRepository;
import com.example.hospitalmanagements.repository.bedroom.MsBedroomRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ArchivedPatientRepository archivedPatientRepository;
    @Autowired
    private PatientDeletionLogRepository patientDeletionLogRepository; // Repository for logging deletions
    @Autowired
    private PatientUpdateLogRepository patientUpdateLogRepository;
    @Autowired
    private PatientAdditionLogRepository logRepository;
@Autowired
private MsBedroomRepository msBedroomRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
       
        patientRepository.deleteById(id);
    }
    
    public void archivePatient(Long id, String department) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found"));

        // إعداد بيانات الأرشيف
        patient.setArchived(true);
        patient.setDeletedFromDepartment(department);

        // تحديث حالة الأرشيف للمريض
        patientRepository.save(patient);
    }
    public List<Patient> getArchivedPatientsByDepartment(String department) {
        return patientRepository.findByRegisteredByDepartmentAndArchived(department, true);
    }

    public List<Patient> getArchivedPatients() {
        return patientRepository.findByArchived(true, Sort.by(Sort.Order.asc("archivedAt")));
    }

    public void restorePatient(Long id, String currentDepartment) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setArchived(false);
        
        if (!"dietary".equalsIgnoreCase(currentDepartment)) {
            patient.setRegisteredByDepartment(currentDepartment);
        }
        
        patientRepository.save(patient);
    }

    public void archivePatient(Long id, String department, String username) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setArchived(true);
        patient.setArchivedAt(LocalDateTime.now());  // تسجيل التاريخ والوقت الحالي
        patientRepository.save(patient);

        // تسجيل عملية الحذف
        PatientDeletionLog log = new PatientDeletionLog();
        log.setUsername(username);
        log.setDepartment(department);
        log.setDeletionTime(LocalDateTime.now());
        log.setPatientFileNo(patient.getFileNo());
        patientDeletionLogRepository.save(log);
        
    }
    
    
    public void addPatient(Patient patient, String department, String userName) {
        // تعيين القسم للمريض
        patient.setRegisteredByDepartment(department);
        
        // تحديث حالة السرير إلى "محجوز"
        MsBedroom bed = msBedroomRepository.findByRoomAndBed(patient.getRoom(), patient.getBed());
        if (bed != null) {
            bed.setAvailable(false);
            msBedroomRepository.save(bed);
        }

        // تعيين القيمة الافتراضية لعمود archived إذا لم تكن محددة
        if (patient.getArchived() == null) {
            patient.setArchived(false); // افتراضياً، يتم تعيينه إلى false
        }

        // حفظ المريض في قاعدة البيانات
        patientRepository.save(patient);

        // حفظ سجل الإضافة في قاعدة البيانات
        PatientAdditionLog log = new PatientAdditionLog();
        log.setUserName(userName);
        log.setAdditionDate(LocalDateTime.now());
        log.setDepartmentName(department);
        log.setPatientFileNo(patient.getFileNo());

        logRepository.save(log);
    }
    public String getPatternFeedsById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            return patient.get().getPatternFeeds();
        } else {
            throw new RuntimeException("Patient not found with id: " + id);
        }
        
    }
    
    public List<Patient> getFilteredPatients(String patternFeeds, String registeredByDepartment) {
        // استدعاء الدالة من المستودع مع تمرير قيم التصفية
        return patientRepository.findByPatternFeedsAndRegisteredByDepartment(patternFeeds, registeredByDepartment);
    }

    public void deletePatientById(Long id) {
    	Patient patient = patientRepository.findById(id).orElse(null);

    	if (patient != null) {
            // تحرير السرير عند حذف المريض
            MsBedroom bed = msBedroomRepository.findByRoomAndBed(patient.getRoom(), patient.getBed());
            if (bed != null) {
                bed.setAvailable(true);
                msBedroomRepository.save(bed);
            }
        patientRepository.deleteById(id);
    }
    }
    public boolean existsByFileNo(String fileNo) {
        // استخدم trim للتأكد من عدم وجود مسافات إضافية في الرقم
        return patientRepository.existsByFileNo(fileNo.trim());
    }
    public Optional<Patient> findByFileNo(String fileNo) {
        return patientRepository.findByFileNo(fileNo);
    }
    public List<PatientDeletionLog> getArchivedPatientsOrderedByDeletionTime() {
        return patientDeletionLogRepository.findAllByOrderByDeletionTimeDesc();
    }
    public boolean existsByRoomBedAndCompanionName(String room, String bed, String companionName, String department) {
        return patientRepository.existsByRoomAndBedAndCompanionNameAndRegisteredByDepartment(room, bed, companionName, department);
    }
    public boolean existsByCompanionNameAndDepartment(String name, String department) {
        return patientRepository.existsByCompanionNameAndRegisteredByDepartment(name, department);
    }
    public Patient prepareForRestore(Long id, String department) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        patient.setArchived(false);
        
        if (!"dietary".equalsIgnoreCase(department)) {
            patient.setRegisteredByDepartment(department);
        }
        
        return patient; // دون حفظ في DB
    }

    public Patient finalizeRestore(Patient patient) {
        patient.setArchived(false);
        return patientRepository.save(patient);
    }
    
    public Patient prepareForRestore(Long id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public void restorePatientt(Long id, String department) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        patient.setArchived(false);
        if (!"dietary".equalsIgnoreCase(department)) {
            patient.setRegisteredByDepartment(department);
        }
        patientRepository.save(patient);
    }
    

}