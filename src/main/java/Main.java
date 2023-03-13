import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Human> humanList = new ArrayList<>();
        Faker faker = new Faker(new Locale("vi"));

        for (int i = 0; i < 1000 ; i++) {
            // Fake thong tin
            Human human = new Human();
            human.setId(i);

            human.setFirstName(faker.name().lastName());
            human.setLastName(faker.name().firstName());
            human.setCity(faker.address().cityName());
            human.setGender(faker.number().numberBetween(0, 2));
            human.setAge(faker.number().numberBetween(10,51));
            human.setSalary(faker.number().numberBetween(10,3001));
            // Them vao humanList
            humanList.add(human);
        }

        humanList.stream().forEach(p-> System.out.println(p));

        //co bao nhieu nam tren 18 tuoi
        long count=humanList.stream().filter(new Predicate<Human>() {
            @Override
            public boolean test(Human human) {
                    return human.getGender()==0&&human.getAge()>18;
            }
        }).count();
        System.out.println(count);

        //co bao nhieu nu va ten dem la 'Thi'

        long count1=humanList.stream().filter(h->h.getGender()==0&&h.getFirstName().contains("Thị")).count();
        System.out.println("So nguoi nu va ten dem la thi: "+count1);

//        humanList.stream().filter(h->h.getLastName())

        //h.tinh muc luong trung binh cua nam ow do tuoi 20-40

        System.out.println("Tinh muc luong trung binh cua nam ow do tuoi 20-40");
        final double[] tongLuong={0};
        final int[] soNguoi={0};
        humanList.stream().filter(h->h.getAge()>=20&&h.getAge()>=40).forEach(h->{
            soNguoi[0]++;
            tongLuong[0]=tongLuong[0]+h.getSalary();
        });
        System.out.println("Trung binh luong: "+tongLuong[0]/soNguoi[0]);

        //Sap xep danh sach theo do tuoi
        System.out.println("Sap xep danh sach theo do tuoi");
        humanList.stream().sorted(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                if (o1.getAge()>o2.getAge()){
                    return 1;
                }else if (o1.getAge()<o2.getAge()){
                    return -1;
                }
                return 0;
            }
        }).forEach(e-> System.out.println(e));


    }
}
