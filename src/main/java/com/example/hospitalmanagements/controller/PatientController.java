package com.example.hospitalmanagements.controller;

import java.io.ByteArrayInputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hospitalmanagements.entity.ChildDietOption;
import com.example.hospitalmanagements.entity.DietType;
import com.example.hospitalmanagements.entity.Notification;
import com.example.hospitalmanagements.entity.Patient;
import com.example.hospitalmanagements.entity.PatientAdditionLog;
import com.example.hospitalmanagements.entity.PatientDeletionLog;
import com.example.hospitalmanagements.entity.PatientUpdateLog;
import com.example.hospitalmanagements.entity.User;
import com.example.hospitalmanagements.repository.ChildDietOptionRepository;
import com.example.hospitalmanagements.repository.DietOptionRepository;
import com.example.hospitalmanagements.repository.NotificationRepository;
import com.example.hospitalmanagements.repository.PatientDeletionLogRepository;
import com.example.hospitalmanagements.repository.PatientRepository;
import com.example.hospitalmanagements.repository.PatientUpdateLogRepository;
import com.example.hospitalmanagements.service.ArchiveService;
import com.example.hospitalmanagements.service.DeletionLogService;
import com.example.hospitalmanagements.service.DietOptionService;
import com.example.hospitalmanagements.service.DietService;
import com.example.hospitalmanagements.service.NotificationService;
import com.example.hospitalmanagements.service.PatientAdditionLogService;
import com.example.hospitalmanagements.service.PatientService;
import com.example.hospitalmanagements.service.PatientUpdateLogService;
import com.example.hospitalmanagements.service.PdfService;
import com.example.hospitalmanagements.service.UserService;
import com.example.hospitalmanagements.service.bedroom.AKU1BedroomService;
import com.example.hospitalmanagements.service.bedroom.AKU2BedroomService;
import com.example.hospitalmanagements.service.bedroom.FMBedroomService;
import com.example.hospitalmanagements.service.bedroom.FSBedroomService;
import com.example.hospitalmanagements.service.bedroom.ICUBedroomService;
import com.example.hospitalmanagements.service.bedroom.MMBedroomService;
import com.example.hospitalmanagements.service.bedroom.MsBedroomService;
import com.example.hospitalmanagements.service.bedroom.NURSERYBedroomService;
import com.example.hospitalmanagements.service.bedroom.OBBedroomService;
import com.example.hospitalmanagements.service.bedroom.PEDIABedroomService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/patients")
//@RequestMapping(value = "/patients", method = RequestMethod.GET)
//@RequestMapping("/api")
public class PatientController {

	@Autowired
	private PatientService patientService;
	@Autowired
	UserService userService;
	@Autowired
	ArchiveService archiveService;
	@Autowired
	private MsBedroomService msBedroomService; // Service للتعامل مع جدول ms_bedroom
	@Autowired
	private ChildDietOptionRepository repository;
	@Autowired
	private PatientDeletionLogRepository patientDeletionLogRepository;
	@Autowired
	private DeletionLogService deletionLogService;
	@Autowired
	private PdfService pdfService;
	@Autowired
	NotificationService notificationService;
	@Autowired
	private PatientUpdateLogRepository patientUpdateLogRepository;
	@Autowired
	private PatientUpdateLogService patientUpdateLogService;
	@Autowired
	private PatientAdditionLogService logService;
	@Autowired
	private DietOptionRepository dietOptionRepository;
	@Autowired
	DietService dietService;

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	private AKU1BedroomService aku1BedroomService;
	@Autowired
	private AKU2BedroomService aku2BedroomService;
	@Autowired
	private FMBedroomService fmBedroomService;
	@Autowired
	private FSBedroomService fsBedroomService;
	@Autowired
	private ICUBedroomService icuBedroomService;
	@Autowired
	private MMBedroomService mmBedroomService;
	@Autowired
	private NURSERYBedroomService nurseryBedroomService;
	@Autowired
	private OBBedroomService obBedroomService;
	@Autowired
	private PEDIABedroomService pediaBedroomService;
	@Autowired
	private DietOptionService service;

	@GetMapping("/list")
	public String listPatients(Model model) {
		List<Patient> patients = patientRepository.findAll();
		model.addAttribute("patients", patients);
		return "patients/list";
	}

	@GetMapping("/search")
	public String searchPatients(@RequestParam("name") String name, Model model) {
		List<Patient> patients = patientRepository.findByNameOfPatientContainingIgnoreCase(name);
		model.addAttribute("patients", patients);
		return "dietary"; // يجب إنشاء صفحة بإسم dietary
	}

	@GetMapping("/sort")
	public String sortPatientsByAge(Model model) {
		List<Patient> patients = patientRepository.findAllByOrderByAgeAsc();
		model.addAttribute("patients", patients);
		return "dietary"; // يجب إنشاء صفحة بإسم dietary
	}

	@GetMapping("/add")
	public String showAddPatientForm(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (request.getSession().getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Please log in first to add a new patient.");
			return "redirect:/patients/login";
		}

		String sessionDepartment = (String) request.getSession().getAttribute("department");

		if (!"MS".equalsIgnoreCase(sessionDepartment) && !"ICU".equalsIgnoreCase(sessionDepartment)
				&& !"AKU1".equalsIgnoreCase(sessionDepartment) && !"AKU2".equalsIgnoreCase(sessionDepartment)
				&& !"FM".equalsIgnoreCase(sessionDepartment) && !"FS".equalsIgnoreCase(sessionDepartment)
				&& !"CCU".equalsIgnoreCase(sessionDepartment) && !"Dietary".equalsIgnoreCase(sessionDepartment)
				&& !"MM".equalsIgnoreCase(sessionDepartment) && !"Pediatrics".equalsIgnoreCase(sessionDepartment)
				&& !"OB".equalsIgnoreCase(sessionDepartment) && !"Nursery".equalsIgnoreCase(sessionDepartment)) {

			redirectAttributes.addFlashAttribute("errorMessage", "You do not have access to this department.");
			return "redirect:/patients/dashboard";
		}

		List<String> rooms = new ArrayList<>();

		if ("MS".equalsIgnoreCase(sessionDepartment)) {
			rooms = msBedroomService.getAllRooms();
		} else if ("ICU".equalsIgnoreCase(sessionDepartment)) {
			rooms = icuBedroomService.getAllRooms();
		} else if ("AKU1".equalsIgnoreCase(sessionDepartment)) {
			rooms = aku1BedroomService.getAllRooms();
		} else if ("AKU2".equalsIgnoreCase(sessionDepartment)) {
			rooms = aku2BedroomService.getAllRooms();
		} else if ("FM".equalsIgnoreCase(sessionDepartment)) {
			rooms = fmBedroomService.getAllRooms();
		} else if ("FS".equalsIgnoreCase(sessionDepartment)) {
			rooms = fsBedroomService.getAllRooms();
		} else if ("MM".equalsIgnoreCase(sessionDepartment)) {
			rooms = mmBedroomService.getAllRooms();
		} else if ("Pediatrics".equalsIgnoreCase(sessionDepartment)) {
			rooms = pediaBedroomService.getAllRooms();
		} else if ("OB".equalsIgnoreCase(sessionDepartment)) {
			rooms = obBedroomService.getAllRooms();
		} else if ("Nursery".equalsIgnoreCase(sessionDepartment)) {
			rooms = nurseryBedroomService.getAllRooms();
		}

		model.addAttribute("patient", new Patient());
		model.addAttribute("sessionDepartment", sessionDepartment);
		model.addAttribute("rooms", rooms);
		System.out.println("Rooms loaded for department " + sessionDepartment + ": " + rooms);

		model.addAttribute("normalCategories", dietService.getCategoriesByType(DietType.NORMAL));
		model.addAttribute("hypoCategories", dietService.getCategoriesByType(DietType.HYPO));
		model.addAttribute("otherCategories", dietService.getCategoriesByType(DietType.OTHER));

		model.addAttribute("childDietOptions",
				repository.findByCategoryOrderByOrderIndex(ChildDietOption.Category.CHILD));
		model.addAttribute("hypoChildDietOptions",
				repository.findByCategoryOrderByOrderIndex(ChildDietOption.Category.HYPO_CHILD));
		model.addAttribute("otherChildDietOptions",
				repository.findByCategoryOrderByOrderIndex(ChildDietOption.Category.OTHER_CHILD));

		model.addAttribute("specialOptions", service.getByType("SPECIAL"));
		model.addAttribute("specialHypoOptions", service.getByType("SPECIAL_HYPO"));
		model.addAttribute("specialOtherOptions", service.getByType("OTHER_SPECIAL"));

		model.addAttribute("osfOptions", dietOptionRepository.findByType("OTHER_OSF"));
	    model.addAttribute("generalOptions", dietOptionRepository.findByType("OTHER_GENERAL"));
		return "addPatient";
	}

	/*
	 * // Endpoint لجلب الأسرّة بناءً على الغرفة
	 * 
	 * @GetMapping("/getBedsByRoom")
	 * 
	 * @ResponseBody public List<String> getBedsByRoom(@RequestParam String room) {
	 * return msBedroomService.getBedsByRoom(room); // إرجاع قائمة الأسرّة }
	 */
	@GetMapping("/getBedsByRoom")
	@ResponseBody
	public List<String> getBedsByRoom(@RequestParam String room, @RequestParam String department) { // تم تغيير
																									// required=false
																									// إلى required=true

		if (department == null) {
			return new ArrayList<>();
		}

		// تحويل القسم لحروف كبيرة لتجنب مشاكل الحالة
		String dept = department.toUpperCase();

		switch (dept) {
		case "OB":
			return obBedroomService.getAvailableBedsByRoom(room);
		case "ICU":
			return icuBedroomService.getAvailableBedsByRoom(room);
		case "AKU1":
			return aku1BedroomService.getAvailableBedsByRoom(room);
		case "AKU2":
			return aku2BedroomService.getAvailableBedsByRoom(room);
		case "FM":
			return fmBedroomService.getAvailableBedsByRoom(room);
		case "FS":
			return fsBedroomService.getAvailableBedsByRoom(room);
		case "MM":
			return mmBedroomService.getAvailableBedsByRoom(room);
		case "PEDIATRICS":
			return pediaBedroomService.getAvailableBedsByRoom(room);
		case "MS":
			return msBedroomService.getAvailableBedsByRoom(room);
		case "NURSERY":
			return nurseryBedroomService.getAvailableBedsByRoom(room);
		default:
			return new ArrayList<>();
		}
	}

	@PostMapping("/add")
	public String addPatient(@ModelAttribute Patient patient, @RequestParam("department") String selectedDepartment,
			HttpServletRequest request, Model model) {

		// الحصول على القسم من الجلسة
		String sessionDepartment = (String) request.getSession().getAttribute("department");

		// التحقق من وجود اسم المرافق في نفس القسم
		if (patient.getCompanionName() != null && !patient.getCompanionName().trim().isEmpty()) {
			if (patientService.existsByCompanionNameAndDepartment(patient.getCompanionName(), sessionDepartment)) {
				model.addAttribute("errorMessage", "Companion name already exists in this department.");
				model.addAttribute("patient", patient);
				model.addAttribute("department", sessionDepartment);
				return "addPatient";
			}
		}

		// التحقق إذا كان رقم الملف في الأرشيف يطابق الرقم المدخل بالكامل
		if (archiveService.existsByFileNo(patient.getFileNo())) {
			model.addAttribute("errorMessage", "This patient exists in the archive and can be restored.");
			model.addAttribute("patient", patient); // إعادة بيانات المريض التي أدخلها المستخدم
			model.addAttribute("department", sessionDepartment); // الاحتفاظ بالقسم من الجلسة
			return "addPatient"; // إعادة توجيه المستخدم إلى صفحة إضافة المريض مع عرض الرسالة
		}

		// التحقق إذا كان رقم الملف في السجل يطابق الرقم المدخل بالكامل
		if (patientService.existsByFileNo(patient.getFileNo())) {
			model.addAttribute("errorMessage", "This patient is already registered.");
			model.addAttribute("patient", patient); // إعادة بيانات المريض التي أدخلها المستخدم
			model.addAttribute("department", sessionDepartment); // الاحتفاظ بالقسم من الجلسة
			return "addPatient"; // إعادة توجيه المستخدم إلى صفحة إضافة المريض مع عرض الرسالة
		}

		// الحصول على اسم المستخدم من الجلسة أو أي مصدر آخر
		String username = (String) request.getSession().getAttribute("username");

		// إضافة المريض وتسجيل السجل
		patientService.addPatient(patient, selectedDepartment, username);

		// التحقق مما إذا كان القسم في الجلسة هو "Dietary"
		if ("Dietary".equalsIgnoreCase(sessionDepartment)) {
			return "redirect:/patients/dietary";
		}

		// توجيه المستخدم إلى القسم المخزن في الجلسة فقط
		switch (sessionDepartment != null ? sessionDepartment.toUpperCase() : "") {
		case "PEDIATRICS":
			return "redirect:/patients/pediatrics";
		case "NURSERY":
			return "redirect:/patients/nursery";
		case "MS":
			return "redirect:/patients/MS";
		case "ICU":
			return "redirect:/patients/ICU";
		case "CCU":
			return "redirect:/patients/CCU";
		case "OB":
			return "redirect:/patients/OB";
		case "AKU1":
			return "redirect:/patients/AKU1";
		case "AKU2":
			return "redirect:/patients/AKU2";
		case "MM":
			return "redirect:/patients/MM";
		case "FS":
			return "redirect:/patients/FS";
		case "FM":
			return "redirect:/patients/FM";
		case "MANAGEMENT":
			return "redirect:/patients/management";
		default:
			return "redirect:/patients/" + selectedDepartment.toLowerCase();
		}
	}

	@GetMapping("/report")
	public ResponseEntity<InputStreamResource> generateReport() {
		List<Patient> patients = patientService.getAllPatients();
		ByteArrayInputStream bis = pdfService.generatePatientReport(patients);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=patients_report.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/home")
	public String in() {
		return "index";
	}

	@GetMapping("/income")
	public String inx() {
		return "index";
	}

	@GetMapping("/homeindex")
	public String inn() {
		return "homeindex";
	}

	@GetMapping("/about")
	public String about() {
		return "about_us";
	}

	@GetMapping("/filtterPattern")
	public String filtterPattern() {
		return "filtterPattern";
	}

	@GetMapping("/card")
	public String cardPattern() {
		return "card";
	}

	@GetMapping("/aboutUs")
	public String aboutUs() {
		return "about_us";
	}
	
	// المسار الرئيسي المعدل
    @GetMapping("/diet-system")
    public String showDietSystem() {
        return "Diet-Order-System";
    }

	@GetMapping("/management")
	public String showmanagementPage(HttpServletRequest request, Model model) {
		request.getSession().setAttribute("department", "management");
		List<Patient> patients = patientRepository.findAll();
		patients.sort(Comparator.comparing(Patient::getRoom).thenComparing(Patient::getBed));
		model.addAttribute("patients", patients);
		List<Notification> notifications = notificationService.findAll();
		model.addAttribute("notifications", notifications);
		setUserDetailsInModel(request, model);
		return "management"; // يجب إنشاء صفحة بإسم dietary
	}

	@GetMapping("/dietary")
	public String showDietaryPage(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		// تعيين القسم في الجلسة
		HttpSession session = request.getSession();

		// التحقق من وجود اسم المستخدم في الجلسة
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login"; // إعادة توجيه المستخدم إلى صفحة تسجيل الدخول
		}

		// تعيين وقت انتهاء الجلسة بـ 30 دقيقة
		session.setMaxInactiveInterval(30 * 60); // 30 دقيقة بالثواني

		// تخزين اسم القسم
		session.setAttribute("department", "dietary");

		// استرجاع قائمة المرضى وترتيبها
		List<Patient> patients = patientRepository.findAll();

		patients.sort(Comparator.comparingInt(patient -> {
			// التحقق من أن رقم الغرفة ليس فارغًا أو null
			String roomStr = patient.getRoom();
			int room = 0; // Default value if parsing fails
			if (roomStr != null && !roomStr.isEmpty()) {
				try {
					room = Integer.parseInt(roomStr.trim()); // Trim to remove any leading/trailing whitespace
				} catch (NumberFormatException e) {
					// Log the error or handle it as needed
					// System.err.println("Invalid room number: " + roomStr);
					room = 0; // Use default value
				}
			}

			// التحقق من أن رقم السرير ليس فارغًا أو null
			String bedStr = patient.getBed();
			char bed = 'A'; // Default value if parsing fails
			if (bedStr != null && !bedStr.isEmpty()) {
				bedStr = bedStr.trim(); // Trim to remove any leading/trailing whitespace
				if (bedStr.length() > 0) {
					bed = bedStr.charAt(0);
				}
			}

			return room * 100 + (bed - 'A'); // ترتيب حسب الغرفة والسرير
		}));

		// إضافة قائمة المرضى للنموذج
		model.addAttribute("patients", patients);

		// استرجاع الإشعارات
		List<Notification> notifications = notificationService.findAll();
		model.addAttribute("notifications", notifications);

		// إعداد بيانات المستخدم في النموذج
		setUserDetailsInModel(request, model);

		return "dietary"; // تأكد من وجود صفحة بإسم dietary
	}

	@GetMapping("/nursery")
	public String shownurseryPage(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		// تعيين القسم في الجلسة
		HttpSession session = request.getSession();

		// التحقق من وجود اسم المستخدم في الجلسة
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login"; // إعادة توجيه المستخدم إلى تسجيل الدخول
		}

		// تعيين وقت انتهاء الجلسة بـ 30 دقيقة
		session.setMaxInactiveInterval(30 * 60); // 30 دقيقة بالثواني

		// تخزين اسم القسم
		session.setAttribute("department", "nursery");

		// استرجاع قائمة المرضى وترتيبها بترتيب تسلسلي بناءً على الغرفة والسرير
		List<Patient> patients = patientRepository.findAll();

		patients.sort(Comparator.comparingInt(patient -> {
			// التحقق من أن رقم الغرفة ليس فارغًا أو null
			String roomStr = patient.getRoom();
			int room = 0; // Default value if parsing fails
			if (roomStr != null && !roomStr.isEmpty()) {
				try {
					room = Integer.parseInt(roomStr.trim()); // Trim to remove any leading/trailing whitespace
				} catch (NumberFormatException e) {
					// Log the error or handle it as needed
					// // System.err.println("Invalid room number: " + roomStr);
					room = 0; // Use default value
				}
			}

			// التحقق من أن رقم السرير ليس فارغًا أو null
			String bedStr = patient.getBed();
			char bed = 'A'; // Default value if parsing fails
			if (bedStr != null && !bedStr.isEmpty()) {
				bedStr = bedStr.trim(); // Trim to remove any leading/trailing whitespace
				if (bedStr.length() > 0) {
					bed = bedStr.charAt(0);
				}
			}

			return room * 100 + (bed - 'A'); // ترتيب حسب الغرفة والسرير
		}));

		model.addAttribute("patients", patients);

		// إعداد بيانات المستخدم في النموذج
		setUserDetailsInModel(request, model);

		return "nursery"; // تأكد من وجود صفحة بإسم nursery
	}

	@GetMapping("/pediatrics")
	public String showPediatricsPage(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login";
		}
		session.setMaxInactiveInterval(30 * 60);
		session.setAttribute("department", "pediatrics");

		List<Patient> patients = patientRepository.findAll();

		patients.sort(Comparator.comparingInt(patient -> {
			// التحقق من أن رقم الغرفة ليس فارغًا أو null
			String roomStr = patient.getRoom();
			int room = 0; // Default value if parsing fails
			if (roomStr != null && !roomStr.isEmpty()) {
				try {
					room = Integer.parseInt(roomStr.trim()); // Trim to remove any leading/trailing whitespace
				} catch (NumberFormatException e) {
					// Log the error or handle it as needed
					// System.err.println("Invalid room number: " + roomStr);
					room = 0; // Use default value
				}
			}

			// التحقق من أن رقم السرير ليس فارغًا أو null
			String bedStr = patient.getBed();
			char bed = 'A'; // Default value if parsing fails
			if (bedStr != null && !bedStr.isEmpty()) {
				bedStr = bedStr.trim(); // Trim to remove any leading/trailing whitespace
				if (bedStr.length() > 0) {
					bed = bedStr.charAt(0);
				}
			}

			return room * 100 + (bed - 'A'); // ترتيب حسب الغرفة والسرير
		}));

		model.addAttribute("patients", patients);
		model.addAttribute("patient", new Patient());
		setUserDetailsInModel(request, model);

		return "pediatrics";
	}

	@GetMapping("/MS")
	public String showMSPage(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login";
		}
		session.setMaxInactiveInterval(30 * 60);
		session.setAttribute("department", "MS");

		List<Patient> patients = patientRepository.findAll();

		patients.sort(Comparator.comparingInt(patient -> {
			// التحقق من أن رقم الغرفة ليس فارغًا أو null
			String roomStr = patient.getRoom();
			int room = 0; // Default value if parsing fails
			if (roomStr != null && !roomStr.isEmpty()) {
				try {
					room = Integer.parseInt(roomStr.trim()); // Trim to remove any leading/trailing whitespace
				} catch (NumberFormatException e) {
					// Log the error or handle it as needed
					// System.err.println("Invalid room number: " + roomStr);
					room = 0; // Use default value
				}
			}

			// التحقق من أن رقم السرير ليس فارغًا أو null
			String bedStr = patient.getBed();
			char bed = 'A'; // Default value if parsing fails
			if (bedStr != null && !bedStr.isEmpty()) {
				bedStr = bedStr.trim(); // Trim to remove any leading/trailing whitespace
				if (bedStr.length() > 0) {
					bed = bedStr.charAt(0);
				}
			}

			return room * 100 + (bed - 'A'); // ترتيب حسب الغرفة والسرير
		}));

		model.addAttribute("patients", patients);
		model.addAttribute("patient", new Patient());
		setUserDetailsInModel(request, model);

		return "MS";
	}

	@GetMapping("/MM")
	public String showMMPage(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login";
		}
		session.setMaxInactiveInterval(30 * 60);
		session.setAttribute("department", "MM");

		List<Patient> patients = patientRepository.findAll();

		patients.sort(Comparator.comparingInt(patient -> {
			// التحقق من أن رقم الغرفة ليس فارغًا أو null
			String roomStr = patient.getRoom();
			int room = 0; // Default value if parsing fails
			if (roomStr != null && !roomStr.isEmpty()) {
				try {
					room = Integer.parseInt(roomStr.trim()); // Trim to remove any leading/trailing whitespace
				} catch (NumberFormatException e) {
					// Log the error or handle it as needed
					// System.err.println("Invalid room number: " + roomStr);
					room = 0; // Use default value
				}
			}

			// التحقق من أن رقم السرير ليس فارغًا أو null
			String bedStr = patient.getBed();
			char bed = 'A'; // Default value if parsing fails
			if (bedStr != null && !bedStr.isEmpty()) {
				bedStr = bedStr.trim(); // Trim to remove any leading/trailing whitespace
				if (bedStr.length() > 0) {
					bed = bedStr.charAt(0);
				}
			}

			return room * 100 + (bed - 'A'); // ترتيب حسب الغرفة والسرير
		}));

		model.addAttribute("patients", patients);
		model.addAttribute("patient", new Patient());
		setUserDetailsInModel(request, model);

		return "MM";
	}

	@GetMapping("/ICU")
	public String showICUPage(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login";
		}
		session.setMaxInactiveInterval(30 * 60);
		session.setAttribute("department", "ICU");

		List<Patient> patients = patientRepository.findAll();

		patients.sort(Comparator.comparingInt(patient -> {
			// التحقق من أن رقم الغرفة ليس فارغًا أو null
			String roomStr = patient.getRoom();
			int room = 0; // Default value if parsing fails
			if (roomStr != null && !roomStr.isEmpty()) {
				try {
					room = Integer.parseInt(roomStr.trim()); // Trim to remove any leading/trailing whitespace
				} catch (NumberFormatException e) {
					// Log the error or handle it as needed
					// System.err.println("Invalid room number: " + roomStr);
					room = 0; // Use default value
				}
			}

			// التحقق من أن رقم السرير ليس فارغًا أو null
			String bedStr = patient.getBed();
			char bed = 'A'; // Default value if parsing fails
			if (bedStr != null && !bedStr.isEmpty()) {
				bedStr = bedStr.trim(); // Trim to remove any leading/trailing whitespace
				if (bedStr.length() > 0) {
					bed = bedStr.charAt(0);
				}
			}

			return room * 100 + (bed - 'A'); // ترتيب حسب الغرفة والسرير
		}));

		model.addAttribute("patients", patients);
		model.addAttribute("patient", new Patient());
		setUserDetailsInModel(request, model);

		return "ICU";
	}

	@GetMapping("/CCU")
	public String showCCUPage(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login";
		}
		session.setMaxInactiveInterval(30 * 60);
		session.setAttribute("department", "CCU");

		List<Patient> patients = patientRepository.findAll();

		patients.sort(Comparator.comparingInt(patient -> {
			// التحقق من أن رقم الغرفة ليس فارغًا أو null
			String roomStr = patient.getRoom();
			int room = 0; // Default value if parsing fails
			if (roomStr != null && !roomStr.isEmpty()) {
				try {
					room = Integer.parseInt(roomStr.trim()); // Trim to remove any leading/trailing whitespace
				} catch (NumberFormatException e) {
					// Log the error or handle it as needed
					// System.err.println("Invalid room number: " + roomStr);
					room = 0; // Use default value
				}
			}

			// التحقق من أن رقم السرير ليس فارغًا أو null
			String bedStr = patient.getBed();
			char bed = 'A'; // Default value if parsing fails
			if (bedStr != null && !bedStr.isEmpty()) {
				bedStr = bedStr.trim(); // Trim to remove any leading/trailing whitespace
				if (bedStr.length() > 0) {
					bed = bedStr.charAt(0);
				}
			}

			return room * 100 + (bed - 'A'); // ترتيب حسب الغرفة والسرير
		}));

		model.addAttribute("patients", patients);
		model.addAttribute("patient", new Patient());
		setUserDetailsInModel(request, model);

		return "CCU";
	}

	@GetMapping("/OB")
	public String showOBPage(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login";
		}
		session.setMaxInactiveInterval(30 * 60);
		session.setAttribute("department", "OB");

		List<Patient> patients = patientRepository.findAll();

		patients.sort(Comparator.comparingInt(patient -> {
			// التحقق من أن رقم الغرفة ليس فارغًا أو null
			String roomStr = patient.getRoom();
			int room = 0; // Default value if parsing fails
			if (roomStr != null && !roomStr.isEmpty()) {
				try {
					room = Integer.parseInt(roomStr.trim()); // Trim to remove any leading/trailing whitespace
				} catch (NumberFormatException e) {
					// Log the error or handle it as needed
					// System.err.println("Invalid room number: " + roomStr);
					room = 0; // Use default value
				}
			}

			// التحقق من أن رقم السرير ليس فارغًا أو null
			String bedStr = patient.getBed();
			char bed = 'A'; // Default value if parsing fails
			if (bedStr != null && !bedStr.isEmpty()) {
				bedStr = bedStr.trim(); // Trim to remove any leading/trailing whitespace
				if (bedStr.length() > 0) {
					bed = bedStr.charAt(0);
				}
			}

			return room * 100 + (bed - 'A'); // ترتيب حسب الغرفة والسرير
		}));

		model.addAttribute("patients", patients);
		model.addAttribute("patient", new Patient());
		setUserDetailsInModel(request, model);

		return "OB";
	}

	@GetMapping("/AKU1")
	public String showAKU1Page(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login";
		}
		session.setMaxInactiveInterval(30 * 60);
		session.setAttribute("department", "AKU1");

		List<Patient> patients = patientRepository.findAll();

		patients.sort(Comparator.comparingInt(patient -> {
			// التحقق من أن رقم الغرفة ليس فارغًا أو null
			String roomStr = patient.getRoom();
			int room = 0; // Default value if parsing fails
			if (roomStr != null && !roomStr.isEmpty()) {
				try {
					room = Integer.parseInt(roomStr.trim()); // Trim to remove any leading/trailing whitespace
				} catch (NumberFormatException e) {
					// Log the error or handle it as needed
					// System.err.println("Invalid room number: " + roomStr);
					room = 0; // Use default value
				}
			}

			// التحقق من أن رقم السرير ليس فارغًا أو null
			String bedStr = patient.getBed();
			char bed = 'A'; // Default value if parsing fails
			if (bedStr != null && !bedStr.isEmpty()) {
				bedStr = bedStr.trim(); // Trim to remove any leading/trailing whitespace
				if (bedStr.length() > 0) {
					bed = bedStr.charAt(0);
				}
			}

			return room * 100 + (bed - 'A'); // ترتيب حسب الغرفة والسرير
		}));

		model.addAttribute("patients", patients);
		model.addAttribute("patient", new Patient());
		setUserDetailsInModel(request, model);

		return "AKU1";
	}

	@GetMapping("/AKU2")
	public String showAKU2Page(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login";
		}
		session.setMaxInactiveInterval(30 * 60);
		session.setAttribute("department", "AKU2");

		List<Patient> patients = patientRepository.findAll();

		patients.sort(Comparator.comparingInt(patient -> {
			// التحقق من أن رقم الغرفة ليس فارغًا أو null
			String roomStr = patient.getRoom();
			int room = 0; // Default value if parsing fails
			if (roomStr != null && !roomStr.isEmpty()) {
				try {
					room = Integer.parseInt(roomStr.trim()); // Trim to remove any leading/trailing whitespace
				} catch (NumberFormatException e) {
					// Log the error or handle it as needed
					// System.err.println("Invalid room number: " + roomStr);
					room = 0; // Use default value
				}
			}

			// التحقق من أن رقم السرير ليس فارغًا أو null
			String bedStr = patient.getBed();
			char bed = 'A'; // Default value if parsing fails
			if (bedStr != null && !bedStr.isEmpty()) {
				bedStr = bedStr.trim(); // Trim to remove any leading/trailing whitespace
				if (bedStr.length() > 0) {
					bed = bedStr.charAt(0);
				}
			}

			return room * 100 + (bed - 'A'); // ترتيب حسب الغرفة والسرير
		}));

		model.addAttribute("patients", patients);
		model.addAttribute("patient", new Patient());
		setUserDetailsInModel(request, model);

		return "AKU2";
	}

	@GetMapping("/FS")
	public String showFSPage(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login";
		}
		session.setMaxInactiveInterval(30 * 60);
		session.setAttribute("department", "FS");

		List<Patient> patients = patientRepository.findAll();

		patients.sort(Comparator.comparingInt(patient -> {
			// التحقق من أن رقم الغرفة ليس فارغًا أو null
			String roomStr = patient.getRoom();
			int room = 0; // Default value if parsing fails
			if (roomStr != null && !roomStr.isEmpty()) {
				try {
					room = Integer.parseInt(roomStr.trim()); // Trim to remove any leading/trailing whitespace
				} catch (NumberFormatException e) {
					// Log the error or handle it as needed
					// System.err.println("Invalid room number: " + roomStr);
					room = 0; // Use default value
				}
			}

			// التحقق من أن رقم السرير ليس فارغًا أو null
			String bedStr = patient.getBed();
			char bed = 'A'; // Default value if parsing fails
			if (bedStr != null && !bedStr.isEmpty()) {
				bedStr = bedStr.trim(); // Trim to remove any leading/trailing whitespace
				if (bedStr.length() > 0) {
					bed = bedStr.charAt(0);
				}
			}

			return room * 100 + (bed - 'A'); // ترتيب حسب الغرفة والسرير
		}));

		model.addAttribute("patients", patients);
		model.addAttribute("patient", new Patient());
		setUserDetailsInModel(request, model);

		return "FS";
	}

	@GetMapping("/FM")
	public String showFMPage(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login";
		}
		session.setMaxInactiveInterval(30 * 60);
		session.setAttribute("department", "FM");

		List<Patient> patients = patientRepository.findAll();

		patients.sort(Comparator.comparingInt(patient -> {
			// التحقق من أن رقم الغرفة ليس فارغًا أو null
			String roomStr = patient.getRoom();
			int room = 0; // Default value if parsing fails
			if (roomStr != null && !roomStr.isEmpty()) {
				try {
					room = Integer.parseInt(roomStr.trim()); // Trim to remove any leading/trailing whitespace
				} catch (NumberFormatException e) {
					// Log the error or handle it as needed
					// System.err.println("Invalid room number: " + roomStr);
					room = 0; // Use default value
				}
			}

			// التحقق من أن رقم السرير ليس فارغًا أو null
			String bedStr = patient.getBed();
			char bed = 'A'; // Default value if parsing fails
			if (bedStr != null && !bedStr.isEmpty()) {
				bedStr = bedStr.trim(); // Trim to remove any leading/trailing whitespace
				if (bedStr.length() > 0) {
					bed = bedStr.charAt(0);
				}
			}

			return room * 100 + (bed - 'A'); // ترتيب حسب الغرفة والسرير
		}));

		model.addAttribute("patients", patients);
		model.addAttribute("patient", new Patient());
		setUserDetailsInModel(request, model);

		return "FM";
	}

	@GetMapping("/edit/{id}")
	public String editPatient(@PathVariable("id") Long id, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// تحقق مما إذا كانت الجلسة تحتوي على اسم المستخدم
		if (request.getSession().getAttribute("username") == null) {
			// إضافة رسالة تنبيه للمستخدم
			redirectAttributes.addFlashAttribute("errorMessage", "Please log in first to edit a patient.");
			return "redirect:/patients/login"; // إعادة توجيه المستخدم إلى صفحة تسجيل الدخول
		}
		Patient patient = patientService.getPatientById(id);
		model.addAttribute("patient", patient);
		model.addAttribute("normalCategories", dietService.getCategoriesByType(DietType.NORMAL));
		model.addAttribute("hypoCategories", dietService.getCategoriesByType(DietType.HYPO));
		model.addAttribute("otherCategories", dietService.getCategoriesByType(DietType.OTHER));

		model.addAttribute("childDietOptions",
				repository.findByCategoryOrderByOrderIndex(ChildDietOption.Category.CHILD));
		model.addAttribute("hypoChildDietOptions",
				repository.findByCategoryOrderByOrderIndex(ChildDietOption.Category.HYPO_CHILD));
		model.addAttribute("otherChildDietOptions",
				repository.findByCategoryOrderByOrderIndex(ChildDietOption.Category.OTHER_CHILD));

		model.addAttribute("specialOptions", service.getByType("SPECIAL"));
		model.addAttribute("specialHypoOptions", service.getByType("SPECIAL_HYPO"));
		model.addAttribute("specialOtherOptions", service.getByType("OTHER_SPECIAL"));

		model.addAttribute("osfOptions", dietOptionRepository.findByType("OTHER_OSF"));
	    model.addAttribute("generalOptions", dietOptionRepository.findByType("OTHER_GENERAL"));
	    

		return "editPatient";
	}

	@PostMapping("/update/{id}")
	public String updatePatient(@PathVariable("id") Long id, @ModelAttribute Patient updatedPatient,
			HttpServletRequest request) {
		Patient existingPatient = patientService.getPatientById(id);

		// تسجيل الحقول التي تم تعديلها
		String changes = getChanges(existingPatient, updatedPatient);

		// التأكد من عدم كون archived قيمة null
		if (updatedPatient.getArchived() == null) {
			updatedPatient.setArchived(false); // تعيين قيمة افتراضية إذا كانت null
		}

		updatedPatient.setId(id);
		patientService.updatePatient(updatedPatient);

		// تسجيل التعديل
		String username = (String) request.getSession().getAttribute("username");
		String department = (String) request.getSession().getAttribute("department");

		PatientUpdateLog log = new PatientUpdateLog();
		log.setUsername(username);
		log.setDepartment(department);
		log.setUpdateTime(LocalDateTime.now());
		log.setPatientFileNo(updatedPatient.getFileNo());
		log.setChanges(changes); // التأكد من تعيين التغييرات
		// التحقق من صحة التغييرات قبل الحفظ
		System.out.println("Changes: " + changes);

		patientUpdateLogService.saveUpdateLog(log);

		return "redirect:/patients/" + department;
	}

	private String getChanges(Patient existingPatient, Patient updatedPatient) {
		StringBuilder changes = new StringBuilder();
		if (!existingPatient.getNameOfPatient().equals(updatedPatient.getNameOfPatient())) {
			changes.append("Name changed from ").append(existingPatient.getNameOfPatient()).append(" to ")
					.append(updatedPatient.getNameOfPatient()).append("; ");
		}
		if (existingPatient.getAge() != updatedPatient.getAge()) {
			changes.append("Age changed from ").append(existingPatient.getAge()).append(" to ")
					.append(updatedPatient.getAge()).append("; ");
		}
		if (!existingPatient.getRoom().equals(updatedPatient.getRoom())) {
			changes.append("Room changed from ").append(existingPatient.getRoom()).append(" to ")
					.append(updatedPatient.getRoom()).append("; ");
		}
		if (!existingPatient.getBed().equals(updatedPatient.getBed())) {
			changes.append("Bed changed from ").append(existingPatient.getBed()).append(" to ")
					.append(updatedPatient.getBed()).append("; ");
		}
		if (!existingPatient.getPatternFeeds().equals(updatedPatient.getPatternFeeds())) {
			changes.append("Pattern Feeds changed from ").append(existingPatient.getPatternFeeds()).append(" to ")
					.append(updatedPatient.getPatternFeeds()).append("; ");
		}
		if (!existingPatient.getDiagnosis().equals(updatedPatient.getDiagnosis())) {
			changes.append("Diagnosis changed from ").append(existingPatient.getDiagnosis()).append(" to ")
					.append(updatedPatient.getDiagnosis()).append("; ");
		}
		if (!existingPatient.getDietOrder().equals(updatedPatient.getDietOrder())) {
			changes.append("Diet Order changed from ").append(existingPatient.getDietOrder()).append(" to ")
					.append(updatedPatient.getDietOrder()).append("; ");
		}
		if (!existingPatient.getAdmissionDate().equals(updatedPatient.getAdmissionDate())) {
			changes.append("Admission Date changed from ").append(existingPatient.getAdmissionDate()).append(" to ")
					.append(updatedPatient.getAdmissionDate()).append("; ");
		}
		if (!existingPatient.getCompanionName().equals(updatedPatient.getCompanionName())) {
			changes.append("Companion Name changed from ").append(existingPatient.getCompanionName()).append(" to ")
					.append(updatedPatient.getCompanionName()).append("; ");
		}
		// أضف مقارنة لبقية الحقول حسب الحاجة
		return changes.toString();
	}

	@GetMapping("/update-logs")
	public String viewUpdateLogs(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// تحقق مما إذا كانت الجلسة تحتوي على اسم المستخدم
		if (request.getSession().getAttribute("username") == null) {
			// إضافة رسالة تنبيه للمستخدم
			redirectAttributes.addFlashAttribute("errorMessage", "Please log in first to view update logs.");
			return "redirect:/patients/login"; // إعادة توجيه المستخدم إلى صفحة تسجيل الدخول
		}

		List<PatientUpdateLog> logs = patientUpdateLogService.getAllUpdateLogs();
		Collections.reverse(logs); // يعكس الترتيب مباشرةً
		model.addAttribute("updateLogs", logs);
		return "updateLogs";
	}

	@PostMapping("/archive/{id}")
	public String archivePatient(@PathVariable("id") Long id, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// استخرج اسم المستخدم والقسم من الجلسة
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String department = (String) session.getAttribute("department");

		// تأكد من أن اسم المستخدم ليس فارغًا
		if (username == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "User is not logged in.");
			return "redirect:/patients/login";
		}

		// تحديد الأوقات غير المسموح بها للأرشفة
		LocalTime currentTime = LocalTime.now(ZoneId.of("Asia/Riyadh"));
		LocalTime restrictedStart1 = LocalTime.of(6, 0);
		LocalTime restrictedEnd1 = LocalTime.of(12, 0);
		LocalTime restrictedStart2 = LocalTime.of(13, 0);
		LocalTime restrictedEnd2 = LocalTime.of(20, 0);

		if ((currentTime.isAfter(restrictedStart1) && currentTime.isBefore(restrictedEnd1))
				|| (currentTime.isAfter(restrictedStart2) && currentTime.isBefore(restrictedEnd2))) {
			redirectAttributes.addFlashAttribute("errorMessage",
					"Deleting is not allowed between 6:00 AM - 12:00 PM and 1:00 PM - 8:00 PM (Food preparation period)");
			return "redirect:/patients/" + department;
		}

		// تمرير اسم المستخدم إلى خدمة الأرشفة
		patientService.archivePatient(id, department, username);

		// إضافة رسالة نجاح
		redirectAttributes.addFlashAttribute("successMessage", "Patient archived successfully.");

		// جلب السجلات المُرتبة حسب وقت الحذف من قاعدة البيانات
		List<PatientDeletionLog> archivedPatients = patientService.getArchivedPatientsOrderedByDeletionTime();

		// تخزينها في session لعرضها بعد التوجيه
		HttpSession sessionData = request.getSession();
		sessionData.setAttribute("archivedPatients", archivedPatients);

		return "redirect:/patients/" + department;
	}

	@GetMapping("/archive")
	public String viewArchivedPatients(Model model, HttpServletRequest request) {
		/*
		 * String department = (String) request.getSession().getAttribute("department");
		 * List<Patient> archivedPatients =
		 * patientService.getArchivedPatientsByDepartment(department);
		 * model.addAttribute("archivedPatients", archivedPatients); return "archive";
		 * // ا
		 */
		List<Patient> archivedPatients = patientService.getArchivedPatients();
		model.addAttribute("archivedPatients", archivedPatients);
		return "archive"; // اسم الصفحة التي تعرض الأرشيف

	}

	@GetMapping("/archived")
	public String viewArchivedPatients(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		// التحقق من وجود اسم المستخدم في الجلسة
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login"; // إعادة توجيه المستخدم إلى صفحة تسجيل الدخول
		}

		// استرجاع المرضى المؤرشفين
		List<Patient> archivedPatients = patientService.getArchivedPatients();
		model.addAttribute("archivedPatients", archivedPatients);

		return "archive"; // اسم الصفحة التي تعرض الأرشيف
	}

	@PostMapping("/restore/{id}")
	public String restorePatient(@PathVariable("id") Long id, @ModelAttribute("patient") Patient patient,
			HttpServletRequest request) {
		// التحقق من تسجيل الدخول
		if (request.getSession().getAttribute("username") == null) {
			return "redirect:/patients/login";
		}

		// حفظ التغييرات النهائية
		patient.setArchived(false);
		patientRepository.save(patient);

		String currentDepartment = (String) request.getSession().getAttribute("department");
		return "redirect:/patients/" + currentDepartment;
	}

	@GetMapping("/send-notification")
	public String showNotificationForm(@PathVariable String departmentName, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		// تحقق مما إذا كانت الجلسة تحتوي على اسم المستخدم
		if (request.getSession().getAttribute("username") == null) {
			// إضافة رسالة تنبيه للمستخدم
			redirectAttributes.addFlashAttribute("errorMessage",
					"Please log in first to access the notification form.");
			return "redirect:/patients/login"; // إعادة توجيه المستخدم إلى صفحة تسجيل الدخول
		}

		// تخزين اسم القسم في الجلسة
		HttpSession session = request.getSession();
		session.setAttribute("department", departmentName);

		// إعداد النموذج
		Notification notification = new Notification();
		notification.setDepartmentName(departmentName);
		model.addAttribute("notification", notification);
		model.addAttribute("departmentName", departmentName);

		return "notification-form";
	}

	@PostMapping("/send-notification")
	public String submitNotificationForm(@ModelAttribute Notification notification,
			@RequestParam List<String> mealTypes, @RequestParam(required = false) List<String> dates, // معلمة جديدة
																										// للتواريخ
																										// المتعددة
			HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {

		HttpSession session = request.getSession();

		// التحقق مما إذا كانت الجلسة تحتوي على اسم المستخدم
		if (session.getAttribute("username") == null) {
			model.addAttribute("isLoggedIn", false); // حالة تسجيل الدخول
			redirectAttributes.addFlashAttribute("error", "Please log in first to send the notification.");
			return "redirect:/patients/login";
		} else {
			model.addAttribute("isLoggedIn", true);
		}

		// الحصول على اسم القسم من الجلسة
		String department = (String) session.getAttribute("department");
		notification.setDepartmentName(department);

		// تحديد التواريخ المراد معالجتها
		List<String> datesToProcess = (dates != null && !dates.isEmpty()) ? dates : List.of(notification.getDate());

		// تنفيذ عملية الإشعار
		for (String mealType : mealTypes) {
			for (String date : datesToProcess) {
				Notification newNotification = new Notification();
				newNotification.setMealType(mealType);
				newNotification.setDate(date);
				newNotification.setDay(getDayOfWeek(date)); // حساب اليوم من التاريخ
				newNotification.setNurseName(notification.getNurseName());
				newNotification.setNurseFileNumber(notification.getNurseFileNumber());
				newNotification.setDepartmentName(department);
				newNotification.setTimestamp(LocalDateTime.now());
				notificationService.save(newNotification);
			}
		}

		return "redirect:/patients/" + department;
	}

	// دالة لحساب اليوم من التاريخ
	private String getDayOfWeek(String date) {
		LocalDate localDate = LocalDate.parse(date);
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		return dayOfWeek.toString();
	}

	@GetMapping("/notification-list")
	public String showNotificationList(Model model) {
		List<Notification> notifications = notificationService.findAll();
		model.addAttribute("notifications", notifications);
		return "dietary";
	}

	@PostMapping("/updateNotificationStatus")
	public String updateNotificationStatus(@RequestParam("id") Long id, @RequestParam("status") String status,
			RedirectAttributes redirectAttributes) {
		boolean success = notificationService.updateNotificationStatus(id, status);
		if (success) {
			redirectAttributes.addFlashAttribute("message", "Notification status updated successfully.");
		} else {
			redirectAttributes.addFlashAttribute("error", "Failed to update notification status.");
		}
		return "redirect:/patients/dietary"; // استبدل /notifications بالمسار الفعلي لصفحة الإشعارات
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user, BindingResult result, Model model) {
		String registerResult = userService.registerUser(user);
		if (registerResult.equals("User already exists")) {
			result.rejectValue("username", "error.user", "Username already exists");
			model.addAttribute("message", "Username already exists");
			return "register";
		}
		return "redirect:/patients/login";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam String username, @RequestParam String password,
			@RequestParam String department, Model model, HttpServletRequest request) {
		// تحقق من صحة بيانات تسجيل الدخول
		String loginResult = userService.loginUser(username, password);
		if (loginResult.equals("Invalid username or password")) {
			model.addAttribute("message", "Invalid username or password");
			model.addAttribute("department", department);
			return "login";
		}

		User user = userService.findByUsername(username).orElse(null);
		if (user != null) {
			// تحقق من تطابق القسم مع تفاصيل المستخدم
			if (!user.getDepartment().equals(department)) {
				model.addAttribute("message", "Incorrect department login details");
				model.addAttribute("department", department);
				return "login";
			}

			// إعداد الجلسة لتخزين معلومات تسجيل الدخول الخاصة بالقسم
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			session.setAttribute("department", user.getDepartment());

			// توجيه المستخدم إلى الصفحة المناسبة بناءً على القسم
			return "redirect:/patients/" + user.getDepartment();
		}

		model.addAttribute("message", "User not found");
		model.addAttribute("department", department);
		return "login";
	}

	@PostMapping("/logout")
	public String logoutUser() {
		// Logic for logging out the user
		return "redirect:/patients/home";
	}

	private void setUserDetailsInModel(HttpServletRequest request, Model model) {
		String username = (String) request.getSession().getAttribute("username");
		model.addAttribute("username", username);
	}

	@GetMapping("/getDepartment")
	@ResponseBody
	public String getDepartment(@RequestParam String username) {
		Optional<String> department = userService.findDepartmentByUsername(username);
		return department.orElse("");
	}

	@GetMapping("/notificationArchive")
	public String showNotificationArchive(Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// تحقق مما إذا كانت الجلسة تحتوي على اسم المستخدم
		if (request.getSession().getAttribute("username") == null) {
			// إضافة رسالة تنبيه للمستخدم
			redirectAttributes.addFlashAttribute("errorMessage",
					"Please log in first to access the notification archive.");
			return "redirect:/patients/login"; // إعادة توجيه المستخدم إلى صفحة تسجيل الدخول
		}

		List<Notification> notifications = notificationService.getAllNotifications();
		model.addAttribute("notifications", notifications);
		return "notificationArchive";
	}

	@GetMapping("/notifications/archive")
	public String getNotificationsByDepartment(@RequestParam String department, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// تحقق مما إذا كانت الجلسة تحتوي على اسم المستخدم
		if (request.getSession().getAttribute("username") == null) {
			// إضافة رسالة تنبيه للمستخدم
			redirectAttributes.addFlashAttribute("errorMessage",
					"Please log in first to view notifications by department.");
			return "redirect:/patients/login"; // إعادة توجيه المستخدم إلى صفحة تسجيل الدخول
		}

		List<Notification> notifications = notificationService.findByDepartmentName(department);
		model.addAttribute("notifications", notifications);
		return "notificationArchive";
	}

	@GetMapping("/retrieve-nurse-file")
	public String showRetrieveForm() {
		return "retrieveForm"; // اسم الصفحة التي تحتوي على النموذج
	}

	@PostMapping("/retrieve-nurse-file")
	public String retrieveNurseFile(@RequestParam("nurseFileNumber") String nurseFileNumber) {
		notificationService.updateNotificationStatus(nurseFileNumber);
		return "redirect:/patients/dietary"; // إعادة التوجيه إلى صفحة نجاح أو الصفحة المناسبة
	}

	@PostMapping("/notifications/restore/{id}")
	@ResponseBody
	public ResponseEntity<String> restoreNotification(@PathVariable Long id) {
		// البحث عن الإشعار في قاعدة البيانات باستخدام الـ ID
		Optional<Notification> notificationOpt = notificationRepository.findById(id);
		if (notificationOpt.isPresent()) {
			Notification notification = notificationOpt.get();
			notification.setStatus(null); // تغيير الحالة إلى null
			notificationRepository.save(notification); // حفظ التغيير في قاعدة البيانات
			return ResponseEntity.ok("Notification restored successfully");
		}
		return ResponseEntity.status(404).body("Notification not found");
	}

	@GetMapping("/deletion-log")
	public String viewDeletionLog(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		// التحقق من وجود اسم المستخدم في الجلسة
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login"; // إعادة توجيه المستخدم إلى صفحة تسجيل الدخول
		}

		// استرجاع سجلات الحذف وترتيبها
		List<PatientDeletionLog> deletionLogs = patientDeletionLogRepository.findAll().stream()
				.sorted(Comparator.comparing(PatientDeletionLog::getId).reversed()).collect(Collectors.toList());
		model.addAttribute("deletionLogs", deletionLogs);
		return "deletionLog"; // اسم الصفحة التي تعرض سجلات الحذف
	}

	@PostMapping("/delete/{id}")
	public String deleteDeletionLog(@PathVariable("id") Long id, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// التحقق من وجود اسم المستخدم في الجلسة
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login"; // إعادة توجيه المستخدم إلى صفحة تسجيل الدخول
		}

		// حذف سجل الحذف
		deletionLogService.deleteLogById(id);
		return "redirect:/patients/deletion-log"; // إعادة التوجيه إلى صفحة سجلات الحذف
	}

	@GetMapping("/logs")
	public String viewLogs(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		// التحقق من وجود اسم المستخدم في الجلسة
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login"; // إعادة توجيه المستخدم إلى صفحة تسجيل الدخول
		}

		// استرجاع سجلات الإضافة وترتيبها
		List<PatientAdditionLog> logs = logService.getAllLogs().stream()
				.sorted(Comparator.comparing(PatientAdditionLog::getId).reversed()).collect(Collectors.toList());
		model.addAttribute("logs", logs);
		return "logs"; // اسم الصفحة التي تعرض سجلات الإضافة
	}

	@GetMapping("/deleteAddationlog")
	public String deleteLog(@RequestParam("id") Long id, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// التحقق من وجود اسم المستخدم في الجلسة
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Your session has expired. Please log in again.");
			return "redirect:/patients/login"; // إعادة توجيه المستخدم إلى صفحة تسجيل الدخول
		}

		try {
			logService.deleteLogById(id);
			redirectAttributes.addFlashAttribute("message", "Log deleted successfully");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error deleting log");
		}
		return "redirect:/patients/logs"; // إعادة التوجيه إلى صفحة عرض السجلات
	}

	@GetMapping("/{id}/pattern-feeds")
	public ResponseEntity<String> getPatternFeeds(@PathVariable Long id) {
		String patternFeeds = patientService.getPatternFeedsById(id);
		return ResponseEntity.ok(patternFeeds);
	}

	@DeleteMapping("/notifications/delete/{id}")
	public ResponseEntity<String> deleteNotification(@PathVariable Long id, HttpServletRequest request) {
		// التحقق من وجود اسم المستخدم في الجلسة
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Your session has expired. Please log in again.");
		}

		try {
			notificationService.deleteNotification(id);
			return ResponseEntity.ok("Notification deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete notification");
		}
	}

	@DeleteMapping("/archive/delete/{id}")
	public ResponseEntity<String> deletePatient(@PathVariable Long id, HttpServletRequest request) {
		// التحقق من وجود اسم المستخدم في الجلسة
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Your session has expired. Please log in again.");
		}

		try {
			patientService.deletePatient(id);
			return ResponseEntity.ok("تم حذف المريض بنجاح");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("فشل في حذف المريض: " + e.getMessage());
		}
	}

	@GetMapping("/checkFileNo")
	@ResponseBody
	public ResponseEntity<String> checkFileNo(@RequestParam String fileNo) {
		boolean exists = archiveService.existsByFileNo(fileNo);
		if (exists) {
			return ResponseEntity.ok("This patient exists in the archive and can be restored.");
		}
		return ResponseEntity.ok(""); // رسالة فارغة إذا لم يكن المريض موجودًا
	}

	@GetMapping("/filteres")
	@ResponseBody
	public List<Patient> filterPatients(@RequestParam(defaultValue = "All") String patternFeeds,
			@RequestParam(defaultValue = "All") String registeredByDepartment) {
		// استدعاء الخدمة لتصفية البيانات
		return patientService.getFilteredPatients(patternFeeds, registeredByDepartment);
	}

	@PostMapping("/deletees/{id}")
	public String deletePatientes(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		patientService.deletePatientById(id);

		// يمكنك إضافة رسالة نجاح إذا كنت ترغب في ذلك
		redirectAttributes.addFlashAttribute("message", "Patient deleted successfully!");

		// إعادة التوجيه إلى صفحة الأرشيف
		return "redirect:/patients/archived"; // توجيه إلى الصفحة المحددة
	}

	@GetMapping("/checkPatientExists")
	public ResponseEntity<String> checkPatientExists(@RequestParam("fileNo") String fileNo) {
		// ابحث عن المريض باستخدام fileNo
		Optional<Patient> patient = patientService.findByFileNo(fileNo);

		if (patient.isPresent()) {
			// إذا كان المريض موجودًا، احصل على القسم المسجل
			String department = patient.get().getRegisteredByDepartment();
			return ResponseEntity.ok("This patient is already registered in the " + department + " department.");
		}

		// إذا لم يكن المريض موجودًا
		return ResponseEntity.ok(""); // رسالة فارغة
	}

	private void sendEmail(String to, String subject, String body) {
		// إعداد خصائص Gmail
		String from = "your-email@gmail.com";
		String username = "nutrition.department2016@gmail.com";
		String password = "mfif nxmm ogmq rgjj";
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session mailSession = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
			System.out.println("الإشعار تم إرساله بنجاح.");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "users"; // اسم صفحة Thymeleaf
	}

	@GetMapping("/users/edit/{id}")
	public String editUserForm(@PathVariable Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "edit-user";
	}

	@PostMapping("/users/edit/{id}")
	public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
		userService.updateUser(id, user);
		return "redirect:/patients/users";
	}

	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		return "redirect:/patients/users";
	}

	@GetMapping("/checkCompanionName")
	@ResponseBody
	public Map<String, Boolean> checkCompanionName(@RequestParam String name, @RequestParam String department) {
		boolean exists = patientService.existsByCompanionNameAndDepartment(name, department);
		Map<String, Boolean> response = new HashMap<>();
		response.put("exists", exists);
		return response;
	}

	@GetMapping("/restore-and-edit/{id}")
	public String restoreAndEdit(@PathVariable Long id, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (request.getSession().getAttribute("username") == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Please log in first.");
			return "redirect:/patients/login";
		}

		Patient patient = patientService.prepareForRestore(id);
		redirectAttributes.addFlashAttribute("patient", patient);
		return "redirect:/patients/edit/" + id;
	}

	// استعادة مباشرة (POST)
	@PostMapping("/restore-direct/{id}")
	public String restoreDirect(@PathVariable Long id, HttpServletRequest request) {
		if (request.getSession().getAttribute("username") == null) {
			return "redirect:/patients/login";
		}

		String department = (String) request.getSession().getAttribute("department");
		patientService.restorePatientt(id, department);
		return "redirect:/patients/" + department;
	}

}
