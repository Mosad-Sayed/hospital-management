         function toggleNotifications() {
             var notificationCard = document.getElementById("notificationCard");
             if (notificationCard.style.display === "block") {
                 notificationCard.style.display = "none";
             } else {
                 notificationCard.style.display = "block";
             }
         }
         
         function markAsRead(event, notificationId) {
             event.preventDefault(); // Prevent form submission
         
             var form = event.target.closest('form');
             var notificationItem = form.closest('li');
             var notificationCountElement = document.querySelector(".notification-count");
         
             // Send AJAX request to update notification status
             var xhr = new XMLHttpRequest();
             xhr.open("POST", form.action, true);
             xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
             xhr.onload = function() {
                 if (xhr.status === 200) {
                     // Hide the notification item
                     notificationItem.style.display = 'none';
         
                     // Update the notification count
                     updateNotificationCount();
                 }
             };
             xhr.send(new URLSearchParams(new FormData(form)).toString());
         }
         
         function updateNotificationCount() {
             var notificationItems = document.querySelectorAll('.notification-list li');
             var unreadCount = 0;
         
             notificationItems.forEach(function(item) {
                 if (item.style.display !== 'none') {
                     unreadCount++;
                 }
             });
         
             var notificationCountElement = document.querySelector(".notification-count");
             notificationCountElement.textContent = unreadCount;
         }
         



        
    <!-- ---------------------------------------------------------------------------------- -->
        document.addEventListener("DOMContentLoaded", function() {
			function updateTotals() {
			    var totalN = 0;
			    var totalC = 0;
			    var totalS = 0;
			    var totalCompanion = 0;
			    var totalNumber = 0;

			    var rows = document.querySelectorAll("tbody tr");

			    rows.forEach(function(row) {
			        if (row.style.display !== "none") {
			            var cells = row.querySelectorAll(".pattern-feeds div");
			            var companionNameCell = row.querySelectorAll("td")[11]; // العمود رقم 11

			            // تحقق من وجود علامة في الأعمدة
			            if (cells[0] && cells[0].innerText.trim() === '✔') {
			                totalN++;
			            }
			            if (cells[1] && cells[1].innerText.trim() === '✔') {
			                totalC++;
			            }
			            if (cells[2] && cells[2].innerText.trim() === '✔') {
			                totalS++;
			            }

			            // تحقق من وجود قيمة في حقل المرافق، إذا كان فارغًا يتم اعتباره 0 في الحسابات فقط
			            if (companionNameCell && companionNameCell.innerText.trim() !== "") {
			                totalCompanion++;
			            } 
			            // إذا كان الحقل فارغًا، لا نضيف شيئًا إلى الـ totalCompanion
			        }
			    });

			    totalNumber = totalCompanion + totalN + totalC + totalS;

			    document.getElementById("totalN").innerText = totalN;
			    document.getElementById("totalC").innerText = totalC;
			    document.getElementById("totalS").innerText = totalS;
			    document.getElementById("totalCompanion").innerText = totalCompanion;
			    document.getElementById("total").innerText = totalNumber;
			}

			function filterByDepartment() {
			    var select = document.getElementById("departmentFilter");
			    var department = select.value.toUpperCase();
			    var table = document.getElementById("patientsTable");
			    var rows = table.getElementsByTagName("tr");
			    var notificationList = document.querySelector(".notification-list");
			    var notificationItems = notificationList.getElementsByTagName("li");

			    var foundData = false;
			    var foundNotifications = false;
			    var serialNo = 1; // الرقم التسلسلي يبدأ من 1

			    // Filter table rows
			    for (var i = 1; i < rows.length; i++) {
			        var cells = rows[i].getElementsByTagName("td");
			        if (cells.length > 12) {
			            var departmentCell = cells[12];
			            if (departmentCell) {
			                var departmentValue = (departmentCell.textContent || departmentCell.innerText).trim().toUpperCase();

			                if (department === "ALL" || departmentValue === department) {
			                    rows[i].style.display = ""; // عرض الصف
			                    cells[0].textContent = serialNo++; // تحديث الرقم التسلسلي
			                    foundData = true;
			                } else {
			                    rows[i].style.display = "none"; // إخفاء الصف
			                }
			            }
			        }
			    }

        // Filter notification items
        for (var j = 0; j < notificationItems.length; j++) {
            var notification = notificationItems[j];
            var notificationDepartment = notification.querySelector(".notification-info span").textContent.trim().toUpperCase();
            
            // Assuming notification department format is consistent
            var notificationDepartmentFormatted = notificationDepartment.includes(' WARD') ? notificationDepartment.replace(' WARD', '') : notificationDepartment;

            if (department === "ALL" || notificationDepartmentFormatted === department) {
                notification.style.display = "";
                foundNotifications = true;
            } else {
                notification.style.display = "none";
            }
        }

        // Update notification count and handle no notifications case
        if (!foundNotifications && department !== "ALL") {
            document.querySelector(".notification-count").textContent = "0";
            alert("No notifications found for the selected department.");
        } else {
            updateNotificationCount();
        }

        // Handle no data case
        if (!foundData && department !== "ALL") {
            alert("No data found for the selected department.");
        }

        updateTotals();
    }
	
	

    function searchTable() {
        var filter = document.getElementById('searchInput').value.toLowerCase();
        var rows = document.querySelectorAll("tbody tr");
        var foundData = false;

        rows.forEach(function(row) {
            var cells = row.querySelectorAll("td");
            var match = false;
            cells.forEach(function(cell) {
                if (cell.innerText.toLowerCase().indexOf(filter) > -1) {
                    match = true;
                }
            });
            if (match) {
                row.style.display = "";
                foundData = true;
            } else {
                row.style.display = "none";
            }
        });

        // Handle no data case
        if (!foundData) {
            alert("No data found for the entered search term.");
        }

        updateTotals(); // تحديث التوتال بناءً على نتائج البحث
    }

    // Initialize search functionality
    document.getElementById('searchInput').addEventListener('input', searchTable);

    function updateNotificationCount() {
        var notificationItems = document.querySelectorAll('.notification-list li');
        var unreadCount = 0;

        notificationItems.forEach(function(item) {
            if (item.style.display !== 'none') {
                unreadCount++;
            }
        });

        var notificationCountElement = document.querySelector(".notification-count");
        if (notificationCountElement) {
            notificationCountElement.textContent = unreadCount;
        }
    }

    // Initialize the filter functionality
    var selectElement = document.getElementById("departmentFilter");
    selectElement.addEventListener("change", filterByDepartment);

    // Initialize with "ALL" departments
    filterByDepartment();
});

    <!-- ---------------------------------------------------------------------------------- -->
 function toggleProfileDropdown() {
            const dropdown = document.getElementById('profileDropdown');
            dropdown.style.display = dropdown.style.display === 'none' || dropdown.style.display === '' ? 'block' : 'none';
        }
        // Close the dropdown if the user clicks outside of it
        window.onclick = function(event) {
            if (!event.target.matches('.profile-icon') && !event.target.matches('.profile-icon *')) {
                const dropdown = document.getElementById('profileDropdown');
                if (dropdown.style.display === 'block') {
                    dropdown.style.display = 'none';
                }
            }
        }
    <!-- ---------------------------------------------------------------------------------- -->
         function showRetrieveForm(button) {
        var content = document.getElementById('patientsTable');
        var notificationForm = document.getElementById('retrieveForm');

        // إذا كان نموذج التنبيهات مخفيًا
        if (notificationForm.style.display === 'none' || notificationForm.style.display === '') {
            notificationForm.style.display = 'block'; // عرض نموذج التنبيهات
            content.style.display = 'none'; // إخفاء المحتوى
        } else {
            notificationForm.style.display = 'none'; // إخفاء نموذج التنبيهات
            content.style.display = 'block'; // عرض المحتوى
        }
    }

    function closeRetrieveForm() {
        var content = document.getElementById('patientsTable');
        document.getElementById('retrieveForm').style.display = 'none';
        content.style.display = 'block'; // عرض المحتوى
        content.style.overflow = 'visible'; // التأكد من عدم وجود تمرير أفقي
        content.style.width = '100%'; // التأكد من عرض المحتوى بالكامل
    }
    
    <!-- ---------------------------------------------------------------------------------- -->
     document.addEventListener("DOMContentLoaded", function() {
             var daysOfWeek = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
             var today = new Date();
             var dayName = daysOfWeek[today.getDay()];
             document.getElementById("dayName").innerText = dayName;
         });
    
    <!-- ------------------------------------------------------------- -->
	function updateInputType() {
	    const filterColumn = document.getElementById('filterColumn').value;
	    const filterValueInput = document.getElementById('filterValue');

	    if (filterColumn === 'admission-date-col') {
	        filterValueInput.type = 'date'; // تغيير نوع الإدخال إلى تاريخ
	        filterValueInput.placeholder = ''; // إزالة نص placeholder
	    } else {
	        filterValueInput.type = 'text'; // العودة إلى النوع العادي
	        filterValueInput.placeholder = 'اكتب القيمة هنا'; // إعادة نص placeholder
	    }
	}

	function updateTotals() {
	    let totalN = 0;
	    let totalC = 0;
	    let totalS = 0;
	    let totalCompanion = 0;

	    const rows = document.getElementById('patientsTable').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
	    let serialNo = 1; // الرقم التسلسلي يبدأ من 1

	    for (let i = 0; i < rows.length; i++) {
	        if (rows[i].style.display !== 'none') {
	            // تحديث الرقم التسلسلي للصفوف الظاهرة فقط
	            rows[i].getElementsByTagName('td')[0].textContent = serialNo++; 

	            const patternFeeds = rows[i].getElementsByClassName('check-mark');

	            // حساب Pattern Feeds
	            if (patternFeeds[0].textContent === '✔') totalN++;
	            if (patternFeeds[1].textContent === '✔') totalC++;
	            if (patternFeeds[2].textContent === '✔') totalS++;

	            // العمود رقم 12 (Companion Name)
	            const companionNameCell = rows[i].getElementsByTagName('td')[11]; // العمود رقم 12 هو index 11
	            const companionName = companionNameCell.textContent.trim();

	            // احتساب إذا كانت الخلية غير فارغة
	            if (companionName !== "") {
	                totalCompanion++; // يحتسب فقط إذا كان هناك بيانات
	            }
	        }
	    }

	    // تحديث القيم في الجدول
	    document.getElementById('totalN').textContent = totalN;
	    document.getElementById('totalC').textContent = totalC;
	    document.getElementById('totalS').textContent = totalS;
	    document.getElementById('totalCompanion').textContent = totalCompanion;

	    // حساب الإجمالي الكلي (N + S + C + TotalCompanion)
	    const totalCompanionsAndPatients = totalN + totalC + totalS + totalCompanion;
	    document.getElementById('total').textContent = totalCompanionsAndPatients; // تأكد من أن ID صحيحة
	}

	// تأكد من استدعاء updateTotals بعد تطبيق الفلتر
	// دالة تطبيق الفلتر
	        function applyFilter() {
	            const filterColumn = document.getElementById('filterColumn').value;
	            let filterValue = document.getElementById('filterValue').value;

	            // إذا كان العمود المحدد هو "تاريخ الدخول"، نقوم بتحويل التنسيق إلى YY-MM-DD
	            if (filterColumn === 'admission-date-col') {
	                const date = new Date(filterValue); // تحويل القيمة المدخلة إلى كائن تاريخ
	                if (!isNaN(date)) { // التأكد من أن القيمة المدخلة تاريخ صحيح
					    const day = date.getDate().toString().padStart(2, '0'); // الحصول على اليوم بصيغة DD
						const month = (date.getMonth() + 1).toString().padStart(2, '0'); // الحصول على الشهر بصيغة MM
	                    const year = date.getFullYear().toString().slice(-2); // الحصول على السنة بصيغة YY
	                    filterValue = `${day}-${month}-${year}`; // التنسيق النهائي YY-MM-DD
	                }
	            }

	            // تحديث حقل الإدخال بالقيمة المحولة
	            document.getElementById('filterValue').value = filterValue;

	            const table = document.getElementById('patientsTable');
	            const rows = table.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
	            const columnIndex = Array.from(table.getElementsByTagName('th')).findIndex(th => th.id === filterColumn);

	            for (let i = 0; i < rows.length; i++) {
	                const cols = rows[i].getElementsByTagName('td');
	                if (columnIndex >= 0) {
	                    const cellValue = cols[columnIndex].textContent.toLowerCase();
	                    if (cellValue.includes(filterValue) || (filterColumn === 'admission-date-col' && cols[columnIndex].textContent === filterValue)) {
	                        rows[i].style.display = ''; // إظهار الصف
	                    } else {
	                        rows[i].style.display = 'none'; // إخفاء الصف
	                    }
	                }
	            }

	           
	                updateTotals();
	            
	        }

	// استدعاء الدالة عند تحميل الصفحة
	document.addEventListener("DOMContentLoaded", function() {
	    updateTotals(); // تأكد من استدعاءها لعرض الإجمالي عند تحميل الصفحة
	});


