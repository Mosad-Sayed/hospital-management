# إدخال الجار مع استثناء boot loader
-injars target/hospitalmanagements.jar(!org/springframework/boot/loader/**)

# إخراج الجار
-outjars target/hospitalmanagements-protected.jar

# مكتبات جافا
-libraryjars <java.home>/jmods

# تعطيل التعديلات الخطرة
-dontoptimize
-dontshrink
-dontpreverify

# الاحتفاظ بالكلاس الرئيسي
-keep public class * {
    public static void main(java.lang.String[]);
}

# الاحتفاظ بسبرنج
-keep class org.springframework.** { *; }
-keep class jakarta.** { *; }

-keepattributes *Annotation*

-dontwarn
