package in.virit.views;

public record Person(String firstName, String lastName, String pictureUrl) {
    public String account() {
        return "@" + firstName.toLowerCase() + "." + lastName.toLowerCase();
    }

    public String fullName() {
        return firstName() + " " + lastName();
    }
}
