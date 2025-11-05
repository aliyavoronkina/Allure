import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DataGenerator {

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] cities = {"Москва", "Санкт-Петербург", "Казань", "Екатеринбург", "Новосибирск"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName() {
        String[] lastNames = {"Иванов", "Петров", "Сидоров", "Кузнецов", "Попов"};
        String[] firstNames = {"Иван", "Петр", "Алексей", "Сергей", "Дмитрий"};
        return lastNames[new Random().nextInt(lastNames.length)] + " " + firstNames[new Random().nextInt(firstNames.length)];
    }

    public static String generatePhone() {
        return "+7916" + String.format("%07d", new Random().nextInt(10000000));
    }

    public static UserInfo generateUser() {
        return new UserInfo(generateCity(), generateName(), generatePhone());
    }

    public static class UserInfo {
        private String city;
        private String name;
        private String phone;

        public UserInfo(String city, String name, String phone) {
            this.city = city;
            this.name = name;
            this.phone = phone;
        }

        public String getCity() { return city; }
        public String getName() { return name; }
        public String getPhone() { return phone; }
    }
}