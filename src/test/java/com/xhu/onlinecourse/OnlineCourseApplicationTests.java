package com.xhu.onlinecourse;

import com.xhu.onlinecourse.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SpringBootTest
class OnlineCourseApplicationTests {

    @Test
    void studentContextLoads() {
        Faker faker = new Faker(Locale.CHINA);
        Faker faker1 = new Faker(Locale.ENGLISH);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 20; i++) {
            String name = faker.name().firstName() + faker.name().lastName();
            String password = "000000";
            String gender = faker.options().option("男","女");
            Date birthday = faker.date().birthday(18, 22);
            String phoneNum = faker.phoneNumber().cellPhone();
            String email = faker1.internet().emailAddress();
            String address = faker.address().city();

            System.out.println(String.format("(NULL,'%s','%s','%s','%s','%s','%s','%s'),",
                    name, password, gender, dateFormat.format(birthday)
                    , phoneNum, email, address));
        }
    }
    @Test
    void teacherContextLoads() {
        Faker faker = new Faker(Locale.CHINA);
        Faker faker1 = new Faker(Locale.ENGLISH);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 20; i++) {
            String name = faker.name().firstName() + faker.name().lastName();
            String password = faker.internet().password(6,11);
            String gender = faker.options().option("男","女");
            Date birthday = faker.date().birthday(30, 50);
            String phoneNum = faker.phoneNumber().cellPhone();
            String email = faker1.internet().emailAddress();
            String address = faker.address().city();

            System.out.println(String.format("(NULL,'%s','%s','%s','%s','%s','%s','%s'),",
                    name, password, gender, dateFormat.format(birthday)
                    , phoneNum, email, address));
        }
    }
    @Test
    void courseContextLoads() {
        Locale locale=new Locale("zh","CN");
        Faker faker = new Faker(locale);
        for (int i = 0; i < 20; i++) {
            int TeacherNumer=faker.random().nextInt(6120100,6120130);
            System.out.printf("(NULL,'%d',NULL,NULL,NULL),\n",TeacherNumer);
        }

    }
}
