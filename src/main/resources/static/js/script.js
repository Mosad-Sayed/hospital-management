const credentials = {
    nutrition: { username: 'nutritionUser', password: 'nutritionPass' },
    maternity: { username: 'maternityUser', password: 'maternityPass' },
    pediatrics: { username: 'pediatricsUser', password: 'pediatricsPass' }
};

function openLoginModal(department) {
    document.getElementById('loginModal').style.display = 'block';
    document.getElementById('loginForm').onsubmit = function(event) {
        event.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        if (username === credentials[department].username && password === credentials[department].password) {
            alert('تسجيل الدخول ناجح لقسم: ' + department);
            closeLoginModal();
            window.location.href = department + '.html'; // فتح صفحة القسم المناسب
        } else {
            alert('اسم المستخدم أو كلمة المرور غير صحيحة');
        }
    }
}

function closeLoginModal() {
    document.getElementById('loginModal').style.display = 'none';
    document.getElementById('loginForm').reset();
}
